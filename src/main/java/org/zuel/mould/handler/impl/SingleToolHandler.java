package org.zuel.mould.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zuel.mould.bean.BaseDic;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.bean.ReplaceRecord;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.service.IDicDataService;
import org.zuel.mould.service.IKnifeToolService;
import org.zuel.mould.service.IReplaceRecordService;
import org.zuel.mould.util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SingleToolHandler {

    @Autowired
    IKnifeToolService knifeToolService;

    @Autowired
    IReplaceRecordService replaceRecordService;

    @Autowired
    IDicDataService dicDataService;

    // 已用刀具
    private Map<String, KnifeGeneral> usedTools = new HashMap<>();

    // 可用刀具
    private Map<Long, KnifeGeneral> avlTools= new HashMap<>();

    // 未检索到的刀具信息 key:name^dia^rad, val: len
    private Map<String, Double> unknownToolInfo = new HashMap<>();

    // 钻刀库
    private List<BaseDic> glassCutterList;

    /**
     * 处理加工结果文件中的刀具信息
     * 1. 默认所有刀具均可用
     * 2. 区分刀具使用情况
     * 3. 检查是否超过限制，若超过则不处理直接输出日志
     * 4. 没有超过限制则替换文本与数据库
     * @param resultPath
     * @param curTime
     * @throws IOException
     */
    public void handleToolInfo(String resultPath, String curTime) throws IOException {
        loadGlassCutter();
        addAllTools2Avl();
        sumToolInfo(resultPath);
        if(checkHandleIsAbled()) {
            replaceToolInfo(resultPath, curTime);
        } else {
            FileUtil.clearDir(resultPath);
            printErrLog(resultPath);
        }
    }

    /**
     * 预置刀具为可用，并剔除探针刀具
     * TODO: 数据库数据量本来就超过限制时的容错
     */
    private void addAllTools2Avl() {
        List<KnifeGeneral> knifeGeneralList = knifeToolService.getAllKnifeGeneral();
        for(KnifeGeneral knifeGeneral : knifeGeneralList) {
            if(NcConstant.KNIFE_TOOL_PROB_NAME.equals(knifeGeneral.getName())) {
                usedTools.put(knifeGeneral.getName() + "^" + knifeGeneral.getDia() + "^" + knifeGeneral.getRad(), knifeGeneral);
            } else {
                avlTools.put(knifeGeneral.getId(), knifeGeneral);
            }
        }
    }

    /**
     * 通过名称判断是否钻刀
     * @param toolName
     * @return
     */
    private boolean isGlassCutter(String toolName) {
        boolean flag = false;
        for(BaseDic glassCutter : glassCutterList) {
            if(toolName.trim().toUpperCase().startsWith(glassCutter.getCode().trim().toUpperCase())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 加载钻刀信息
     */
    private void loadGlassCutter() {
        glassCutterList = dicDataService.selectByParentId(NcConstant.GLASS_CUTTER_ROOT_ID);
    }
    /**
     * 汇总所有刀具(已占用与仍可用)
     * @param resultPath
     * @throws IOException
     */
    private void sumToolInfo(String resultPath) throws IOException {
        File[] resultFiles = FileUtil.getProcessResultFiles(resultPath);
        // 已检查过的刀具 name^dia^rad
        List<String> checkedTools = new ArrayList<>();
        // 检索刀具信息并缓存
        for(File resultFile : resultFiles) {
            List<String> txtLines = Files.readAllLines(Paths.get(resultFile.getAbsolutePath()));
            for(String txtLine : txtLines) {
                if (isToolLine(txtLine)) {
                    String[] arrVal = txtLine.split("\\ ");
                    String toolName = arrVal[0].split("\\" + NcConstant.KNIFE_TOOL_START_TAG)[1];
                    Double toolDia = Double.valueOf(arrVal[1].split("\\=")[1]);
                    Double toolRad = Double.valueOf(arrVal[2].split("\\=")[1]);
                    Double toolLen = Double.valueOf(arrVal[5].split("\\=")[1].replace(NcConstant.KNIFE_TOOL_END_CHAR, ""));
                    if((toolDia.doubleValue() == 0 && toolRad.doubleValue() == 0) || checkedTools.contains(toolName + "^" + toolDia + "^" + toolRad)) {
                        continue;
                    }
                    checkedTools.add(toolName + "^" + toolDia + "^" + toolRad);
                    KnifeGeneral knifeGeneral = knifeToolService.getKnifeGeneralByInfo(toolName, toolDia, toolRad);
                    if(knifeGeneral == null || (!isGlassCutter(toolName) && knifeGeneral.getLen().compareTo(toolLen) <= 0)) {
                        unknownToolInfo.put(toolName + "^" + toolDia + "^" + toolRad, toolLen);
                    } else {
                        // 添加占用刀具并从可用中移除
                        String toolKey = knifeGeneral.getName() + "^" + knifeGeneral.getDia() + "^" + knifeGeneral.getRad();
                        if(!usedTools.containsKey(toolKey)) {
                            usedTools.put(toolKey, knifeGeneral);
                            avlTools.remove(knifeGeneral.getId());
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断是否刀具信息行
     * @param txtLine
     * @return
     */
    private boolean isToolLine(String txtLine) {
        return txtLine.contains(NcConstant.KNIFE_TOOL_INFO_HEAD) && txtLine.contains(NcConstant.KNIFE_TOOL_INFO_DIA)
                && txtLine.contains(NcConstant.KNIFE_TOOL_INFO_RAD) && txtLine.contains(NcConstant.KNIFE_TOOL_INFO_LEN);
    }

    /**
     * 检查是否可处理
     * 暂时只检查刀具数量限制
     * @return
     */
    private boolean checkHandleIsAbled() {
        return NcConstant.KNIFE_TOOL_MAX_NUM - (usedTools.size() + unknownToolInfo.size()) >= 0;
    }

    /**
     * 替换刀具信息
     * @param resultPath
     * @param curTime
     * @throws IOException
     */
    private void replaceToolInfo(String resultPath, String curTime) throws IOException {
        File[] resultFiles = FileUtil.getProcessResultFiles(resultPath);
        for(File resultFile : resultFiles) {
            List<String> resultLines = Files.readAllLines(Paths.get(resultFile.getAbsolutePath()));
            try (FileWriter writer = new FileWriter(resultFile.getAbsolutePath(), false)) {
                for (int i = 0; i < resultLines.size(); ++i) {
                    if (resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_HEAD) && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_DIA)
                            && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_RAD) && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_LEN)) {
                        String[] arrVal = resultLines.get(i).split("\\ ");
                        String toolName = arrVal[0].split("\\" + NcConstant.KNIFE_TOOL_START_TAG)[1];
                        BigDecimal toolDia = BigDecimal.valueOf(Double.valueOf(arrVal[1].split("\\=")[1]));
                        BigDecimal toolRad = BigDecimal.valueOf(Double.valueOf(arrVal[2].split("\\=")[1]));
                        if (toolDia.doubleValue() == 0 && toolRad.doubleValue() == 0) {
                            FileUtil.writeWithLine(writer, resultLines.get(i));
                        } else {
                            String toolKey = toolName + "^" + toolDia + "^" + toolRad;
                            replaceToolLine(writer, resultLines.get(i), toolKey, curTime);
                            // 写入刀具信息行与编号之间的内容
                            for(int j = i + 1; j < i + NcConstant.KNIFE_TOOL_HEAD_INTERVAL; ++j) {
                                FileUtil.writeWithLine(writer, resultLines.get(j));
                            }
                            FileUtil.writeWithLine(writer, NcConstant.KNIFE_TOOL_INFO_HEAD + usedTools.get(toolKey).getCode());
                            i += NcConstant.KNIFE_TOOL_HEAD_INTERVAL;
                            // 继续写入直到刀具信息尾
                            for(int k = i + 1; k < i + NcConstant.KNIFE_TOOL_TAIL_INTERVAL; ++k, ++i) {
                                if(resultLines.get(k).contains(NcConstant.KNIFE_TOOL_TAIL_TAG)) {
                                    String toolCode = usedTools.get(toolKey).getCode().length() == 1 ? "0" + usedTools.get(toolKey).getCode() : usedTools.get(toolKey).getCode();
                                    FileUtil.writeWithLine(writer, resultLines.get(k)
                                            .replace(NcConstant.KNIFE_TOOL_OCCPY_CHAR_H + NcConstant.KNIFE_TOOL_OCCUPY_CODE, NcConstant.KNIFE_TOOL_OCCPY_CHAR_H + toolCode));
                                    FileUtil.writeWithLine(writer, NcConstant.KNIFE_TOOL_TAIL_STR + toolCode);
                                    break;
                                } else {
                                    FileUtil.writeWithLine(writer, resultLines.get(k));
                                }
                            }
                            ++i;
                        }
                    } else {
                        FileUtil.writeWithLine(writer, resultLines.get(i));
                    }
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /**
     * 替换刀具信息行
     * @param writer
     * @param toolLine
     * @param toolKey
     * @param curTime
     * @throws IOException
     */
    private void replaceToolLine(FileWriter writer, String toolLine, String toolKey, String curTime) throws IOException {
        KnifeGeneral curTool = null;
        if(usedTools.containsKey(toolKey)) {
            curTool = usedTools.get(toolKey);
        } else if(unknownToolInfo.containsKey(toolKey)) {
            curTool = usedTools.get(addReplaceRecord(toolKey, curTime));
        }
        String replaceStr = toolLine;
        String replaceChar = toolLine.substring(toolLine.indexOf(NcConstant.KNIFE_TOOL_START_CHAR) + 1, toolLine.indexOf(NcConstant.KNIFE_TOOL_START_TAG));
        replaceStr = replaceStr.replace(replaceChar, NcConstant.KNIFE_TOOL_INFO_HEAD + curTool.getCode());
        if(replaceStr.contains(NcConstant.KNIFE_TOOL_OCCPY_CHAR_H + NcConstant.KNIFE_TOOL_OCCUPY_CODE)) {
            replaceStr = replaceStr.replace(NcConstant.KNIFE_TOOL_OCCPY_CHAR_H + NcConstant.KNIFE_TOOL_OCCUPY_CODE, NcConstant.KNIFE_TOOL_OCCPY_CHAR_H + curTool.getCode());
        }
        if(replaceStr.contains(NcConstant.KNIFE_TOOL_OCCPY_CHAR_D + NcConstant.KNIFE_TOOL_OCCUPY_CODE)) {
            replaceStr = replaceStr.replace(NcConstant.KNIFE_TOOL_OCCPY_CHAR_D + NcConstant.KNIFE_TOOL_OCCUPY_CODE, NcConstant.KNIFE_TOOL_OCCPY_CHAR_D + curTool.getCode());
        }
        FileUtil.writeWithLine(writer, replaceStr);
    }

    /**
     * 添加替换记录
     * 1. 更新一条可用记录
     * 2. 把更新的记录从可用列表里删除
     * 3. 已占用列表里追加更新的记录并返回Map键值
     * @param toolKey
     * @param curTime
     * @return
     */
    private String addReplaceRecord(String toolKey, String curTime) {
        Map.Entry<Long, KnifeGeneral> tmpEntry = null;
        for(Map.Entry<Long, KnifeGeneral> entry : avlTools.entrySet()) {
            tmpEntry = entry;
            break;
        }
        avlTools.remove(tmpEntry.getKey());
        KnifeGeneral knifeGeneral = tmpEntry.getValue();
        knifeGeneral.setRemark(curTime);
        // 构造并写入数据库
        addReplaceRecord(toolKey, knifeGeneral);
        // 改变当前刀具属性并更新至数据库
        modifyToolModel(toolKey, knifeGeneral);
        String replaceKey = knifeGeneral.getName() + "^" + knifeGeneral.getDia() + "^" + knifeGeneral.getRad();
        usedTools.put(replaceKey, knifeGeneral);
        return replaceKey;
    }

    /**
     * 添加替换记录
     * @param toolKey
     * @param avlTool
     * @return
     */
    private void addReplaceRecord(String toolKey, KnifeGeneral avlTool) {
        String[] arrToolInfo = toolKey.split("\\^");
        ReplaceRecord replaceRecord = new ReplaceRecord();
        replaceRecord.setSrcName(avlTool.getName());
        replaceRecord.setSrcDia(avlTool.getDia());
        replaceRecord.setSrcRad(avlTool.getRad());
        replaceRecord.setSrcLen(avlTool.getLen());
        replaceRecord.setTarName(arrToolInfo[0]);
        replaceRecord.setTarDia(Double.valueOf(arrToolInfo[1]));
        replaceRecord.setTarRad(Double.valueOf(arrToolInfo[2]));
        replaceRecord.setTarLen(getToolLengthByToolType(arrToolInfo[0]));
        replaceRecord.setRemark(avlTool.getRemark());
        replaceRecord.setCreateTime(new Date());
//        System.out.println("Replace record: " + replaceRecord);
        replaceRecordService.addReplaceRecord(replaceRecord);
    }

    /**
     * 通过刀具名解析长度，钻刀长度为0
     * @param toolName
     * @return
     */
    private Double getToolLengthByToolType(String toolName) {
        if(isGlassCutter(toolName)) {
            return (double) 0;
        } else {
            String nameStr = toolName;
            if(nameStr.contains(NcConstant.KNIFE_TOOL_SPCL_NAME_TAG)) {
                nameStr = nameStr.split("\\" + NcConstant.KNIFE_TOOL_SPCL_NAME_TAG)[0];
            }
            return Double.valueOf(nameStr.substring(nameStr.lastIndexOf(NcConstant.KNIFE_TOOL_NAME_LENGTH_TAG) + 1, nameStr.length()));
        }
    }

    /**
     * 更新并写入数据库
     * @param toolKey
     * @param toolModel
     * @return
     */
    private void modifyToolModel(String toolKey, KnifeGeneral toolModel) {
//        System.out.println("ToolModel before modify:" + toolModel);
        String[] arrToolInfo = toolKey.split("\\^");
        toolModel.setName(arrToolInfo[0]);
        toolModel.setDia(Double.valueOf(arrToolInfo[1]));
        toolModel.setRad(Double.valueOf(arrToolInfo[2]));
        toolModel.setLen(getToolLengthByToolType(arrToolInfo[0]));
        toolModel.setTimestamp(new Date());
        knifeToolService.modifyKnifeGeneralInfo(toolModel);
//        System.out.println("ToolModel after modify:" + toolModel);
    }

    /**
     * 输出日志
     * @param resultPath
     * @throws IOException
     */
    public void printErrLog(String resultPath) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat(NcConstant.DATE_FORMAT_MINI);
        String curTime = dateFormat.format(new Date());
        String logPath = resultPath + File.separator + NcConstant.NC_ERROR_LOG_PREFIX + curTime + NcConstant.NC_ERROR_LOG_POSTFIX;
        try(FileWriter writer = new FileWriter(logPath, false)) {
            String logHeader = "日志打印时间: " + curTime;
            FileUtil.writeWithLine(writer, logHeader);
            FileUtil.writeWithLine(writer, NcConstant.NC_ERROR_LOG_LINE_SEPARATOR);
            String sectionUsed = "检测到已使用刀具: ";
            FileUtil.writeWithLine(writer, sectionUsed);
            for (String key : usedTools.keySet()) {
                KnifeGeneral tmpTool = usedTools.get(key);
                StringBuffer strToolInfo = new StringBuffer();
                strToolInfo.append("刀具名称: ").append(tmpTool.getName()).append(", 直径: ").append(tmpTool.getDia())
                        .append(", 半径: ").append(tmpTool.getRad()).append(", 长度: ").append(tmpTool.getLen());
                FileUtil.writeWithLine(writer, strToolInfo.toString());
            }
            FileUtil.writeWithLine(writer, NcConstant.NC_ERROR_LOG_LINE_SEPARATOR);
            String sectionReplace = "未检测到或长度超过限制的刀具: ";
            FileUtil.writeWithLine(writer, sectionReplace);
            for(String toolInfo : unknownToolInfo.keySet()) {
                String[] arrToolInfo = toolInfo.split("\\^");
                StringBuffer strToolInfo = new StringBuffer();
                strToolInfo.append("刀具名称: ").append(arrToolInfo[0]).append(", 直径: ").append(arrToolInfo[1])
                        .append(", 半径: ").append(arrToolInfo[2]).append(", 长度: ").append(unknownToolInfo.get(toolInfo));
                FileUtil.writeWithLine(writer, strToolInfo.toString());
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
