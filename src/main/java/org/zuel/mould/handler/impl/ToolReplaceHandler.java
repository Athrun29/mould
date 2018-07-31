package org.zuel.mould.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.service.IKnifeToolService;
import org.zuel.mould.util.FileUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ToolReplaceHandler {

    @Autowired
    private IKnifeToolService knifeToolService;

    /**
     * 处理刀具信息
     * @param resultPath
     * @throws IOException
     */
    public void handleToolInfo(String resultPath) throws IOException {
        List<String> resultLines = Files.readAllLines(Paths.get(resultPath));
        try (FileWriter writer = new FileWriter(resultPath, false)) {
            for (int i = 0; i < resultLines.size(); ++i) {
                if (resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_HEAD) && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_DIA)
                        && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_RAD) && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_LEN)) {
                    String[] arrVal = resultLines.get(i).split("\\ ");
                    String toolName = arrVal[0].split("\\" + NcConstant.KNIFE_TOOL_START_TAG)[1];
                    Double toolDia = Double.valueOf(arrVal[1].split("\\=")[1]);
                    Double toolRad = Double.valueOf(arrVal[2].split("\\=")[1]);
                    Double toolLen = Double.valueOf(arrVal[5].split("\\=")[1].replace(NcConstant.KNIFE_TOOL_END_CHAR, ""));

                    if (toolDia.doubleValue() == 0 && toolRad.doubleValue() == 0) {
                        FileUtil.writeWithLine(writer, resultLines.get(i));
                    } else {
                        KnifeGeneral knifeGeneral = knifeToolService.getKnifeGeneralByInfo(toolName, toolDia, toolRad);
                        if (knifeGeneral == null) {
                            FileUtil.writeWithLine(writer, resultLines.get(i));
                        } else {
                            System.out.println(knifeGeneral);
                            String replaceStr = resultLines.get(i);
                            replaceStr = replaceStr.substring(replaceStr.indexOf(NcConstant.KNIFE_TOOL_START_CHAR) + 1, replaceStr.indexOf(NcConstant.KNIFE_TOOL_START_TAG));
                            writer.write(resultLines.get(i).replace(replaceStr, NcConstant.KNIFE_TOOL_INFO_HEAD + knifeGeneral.getCode()));
                            writer.write(System.lineSeparator());
                            // 默认写入中间行
                            for(int j = i + 1; j < i + NcConstant.KNIFE_TOOL_LINE_INTERVAL; ++j) {
                                FileUtil.writeWithLine(writer, resultLines.get(j));
                            }
                            FileUtil.writeWithLine(writer, NcConstant.KNIFE_TOOL_INFO_HEAD + knifeGeneral.getCode());
                            i += NcConstant.KNIFE_TOOL_LINE_INTERVAL;
                        }
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
