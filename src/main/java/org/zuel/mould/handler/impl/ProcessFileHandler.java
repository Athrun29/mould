package org.zuel.mould.handler.impl;

import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessFileHandler {

    /**
     * 处理加工文件目录
     *
     * @param processDirPath
     */
    public void handleProcessDir(String processDirPath) throws IOException {
        String resultDir = getResultDir(processDirPath);
        File processDir = new File(processDirPath);
        File[] processFiles = processDir.listFiles();
        String processCode = "-1";
        List<String> processFilePaths = new ArrayList<>();
        for (File processFile : processFiles) {
            if (FileUtil.isNcFile(processFile.getName())) {
                if (!processFile.getName().startsWith(NcConstant.PROB_FILE_NAME_PREFIX) && processFile.isFile()) {
                    processFilePaths.add(processFile.getAbsolutePath());
                } else if (processFile.getName().startsWith(NcConstant.PROB_FILE_NAME_PREFIX) && processFile.isFile()) {
                    processCode = FileUtil.get2BitProbCode(processFile.getName());
                }
            }
        }
        // 没有文件不做处理
        if (processFilePaths.size() == 0) {
            return;
        } else {
            Collections.sort(processFilePaths);
            generateProcessResult(processFilePaths, resultDir, processCode);
        }
    }

    /**
     * 如果不存在处理结果目录则创建 20180719
     * 直接取加工文件父目录
     *
     * @param processDirPath
     */
    private String getResultDir(String processDirPath) {
        File resultDir = new File(processDirPath).getParentFile();
        if (!resultDir.exists()) {
            resultDir.mkdir();
        }
        return resultDir.getAbsolutePath();
    }

    /**
     * 生成加工文件处理结果
     *
     * @param processFilePaths
     * @param resultDir
     * @param processCode
     * @throws IOException
     */
    private void generateProcessResult(List<String> processFilePaths, String resultDir, String processCode) throws IOException {
        // 保存探测加工文件序号列表
        List<Integer> detectIndexs = new ArrayList<>();
        for (int i = 0; i < processFilePaths.size(); ++i) {
            List<String> processLines = Files.readAllLines(Paths.get(processFilePaths.get(i)));
            for (String processLine : processLines) {
                if (processLine.trim().startsWith(NcConstant.PROCESS_DETECT_TAG)) {
                    detectIndexs.add(i);
                    break;
                }
            }
        }
        Collections.sort(detectIndexs);
        if (detectIndexs.size() == 0) {
            writeNoDetectResult(processFilePaths, resultDir, processCode);
        } else {
            // 保存添加到总生成结果内容
            List<String> appendList = new ArrayList<>();
            writeDetectResult(processFilePaths, resultDir, processCode, detectIndexs, appendList);
            // 追加内容至总结果
            FileUtil.writeDetectTagToProbResult(resultDir, appendList, processCode);
        }
    }

    /**
     * 没有探测加工文件时逐个按一般规则处理
     *
     * @param processFilePaths
     * @param resultDir
     * @param processCode
     */
    private void writeNoDetectResult(List<String> processFilePaths, String resultDir, String processCode) throws IOException {
        String mergeResultName = NcConstant.PROCESS_HANDLE_PREFIX + processCode + FileUtil.get2BitProcessCode(1);
        mergeProcessFile(0, processFilePaths.size(), resultDir + File.separator + mergeResultName, processFilePaths);
    }

    /**
     * 检测到探测加工文件则:
     * 1. 合并加工文件并移动加工文件合并位置
     * 2. 解析探测加工文件（按特殊字分割首尾段写入新结果，中间段写入总结果，如果没有尾段把空内容写入新结果）并追加生成结果数量
     * 3. 处理剩余加工文件（如果有）
     *
     * @param processFilePaths
     * @param resultDir
     * @param processCode
     * @param detectIndexs
     */
    private void writeDetectResult(List<String> processFilePaths, String resultDir, String processCode, List<Integer> detectIndexs, List<String> appendList) throws IOException {
        // 探测加工文件前的加工文件起始位置
        int mergeIndex = 0;
        // 生成结果数
        int resultNum = 0;
        for (int i = 0; i < detectIndexs.size(); ++i) {
            if (mergeIndex < detectIndexs.get(i)) {
                // 合并加工文件
                String mergeResultPath = resultDir + File.separator + NcConstant.PROCESS_HANDLE_PREFIX + processCode + FileUtil.get2BitProcessCode(++resultNum);
                mergeProcessFile(mergeIndex, detectIndexs.get(i), mergeResultPath, processFilePaths);
                String mergeProbTagCode = processCode + FileUtil.get2BitProcessCode(resultNum);
                appendList.add(NcConstant.PROB_FILE_RESULT_TAG + mergeProbTagCode + NcConstant.PROB_FILE_RESULT_PREFIX + mergeProbTagCode);
                mergeIndex = detectIndexs.get(i) + 1;
                // 分解探测加工文件
                resolveDetectFile(processFilePaths.get(detectIndexs.get(i)), resultNum, resultDir, processCode, appendList);
                resultNum = resultNum + 2;
            }
        }
        // 处理剩余加工文件（如果有）
        if (mergeIndex < processFilePaths.size()) {
            mergeProcessFile(mergeIndex, processFilePaths.size(), resultDir + File.separator + NcConstant.PROCESS_HANDLE_PREFIX + processCode + FileUtil.get2BitProcessCode(++resultNum), processFilePaths);
            String mergeProbTagCode = processCode + FileUtil.get2BitProcessCode(resultNum);
            appendList.add(NcConstant.PROB_FILE_RESULT_TAG + mergeProbTagCode + NcConstant.PROB_FILE_RESULT_PREFIX + mergeProbTagCode);
        }
    }

    /**
     * 单个加工文件写入结果
     *
     * @param processFilePath
     * @param resultDir
     * @param resultName
     * @throws IOException
     */
    private void writeProcessResult(String processFilePath, String resultDir, String resultName) throws IOException {
        List<String> processLines = Files.readAllLines(Paths.get(processFilePath));
        List<String> txtLines = new ArrayList<>();
        // 添加文件头
        txtLines.add(NcConstant.FILE_START_TAG);
        txtLines.add(resultName);
        // 添加文件内容
        for (String processLine : processLines) {
            handleProcessLineByResult(txtLines, processLine, resultName);
        }
        // 追加结束指令与文件终止符
        txtLines.add(NcConstant.PROCESS_FILE_END_INS);
        txtLines.add(NcConstant.FILE_TERMINAL_TAG);
        FileUtil.writeResult(resultDir + File.separator + resultName, txtLines);
    }

    /**
     * 合并加工文件
     *
     * @param beginIndex
     * @param endIndex
     * @param resultPath
     * @param processFilePaths
     */
    private void mergeProcessFile(int beginIndex, int endIndex, String resultPath, List<String> processFilePaths) throws IOException {
        List<String> txtLines = new ArrayList<>();
        // 添加文件头
        txtLines.add(NcConstant.FILE_START_TAG);
        txtLines.add(FileUtil.getFileNameFromFilePath(resultPath));
        // 添加合并内容
        for (int i = beginIndex; i < endIndex; ++i) {
            List<String> processLines = Files.readAllLines(Paths.get(processFilePaths.get(i)));
            for (String processLine : processLines) {
                handleProcessLineByResult(txtLines, processLine, resultPath);
            }
            txtLines.add(" ");
        }
        // 追加结束指令与文件终止符
        txtLines.add(NcConstant.PROCESS_FILE_END_INS);
        txtLines.add(NcConstant.FILE_TERMINAL_TAG);
        FileUtil.writeResult(resultPath, txtLines);
    }

    /**
     * 解析探测加工文件
     *
     * @param detectPath
     * @param resultNum
     * @param resultDir
     * @param processCode
     * @return
     */
    private void resolveDetectFile(String detectPath, int resultNum, String resultDir, String processCode, List<String> appendList) throws IOException {
        String section1thResult = NcConstant.PROCESS_HANDLE_PREFIX + processCode + FileUtil.get2BitProcessCode(resultNum + 1);
        List<String> section1th = getInitDetectSectionList(section1thResult);
        List<String> section2nd = new ArrayList<>();
        String section3rdResult = NcConstant.PROCESS_HANDLE_PREFIX + processCode + FileUtil.get2BitProcessCode(resultNum + 2);
        List<String> section3rd = getInitDetectSectionList(section3rdResult);
        String curSectionTag = NcConstant.DETECT_1TH_TAG;
        List<String> processLines = Files.readAllLines(Paths.get(detectPath));
        boolean section3rdHeaderFlag = true;
        boolean section3rdReplaceFlag = true;
        boolean hasSection3rdFlag = false;
        for (String processLine : processLines) {
            if (NcConstant.FILE_START_TAG.equals(processLine.trim()) || NcConstant.PROCESS_TERMINAL_INS.equals(processLine.trim())) {
                continue;
            }
            if (NcConstant.DETECT_1TH_TAG.equals(curSectionTag) && processLine.trim().startsWith(NcConstant.DETECT_1TH_2ND_SEPARATOR) && processLine.trim().length() < NcConstant.DETECT_1TH_2ND_SEPARATOR_LENGTH) {
                curSectionTag = NcConstant.DETECT_2ND_TAG;
                continue;
            }
            if (NcConstant.DETECT_2ND_TAG.equals(curSectionTag) && processLine.trim().startsWith(NcConstant.DETECT_2ND_3RD_SEPARATOR)) {
                curSectionTag = NcConstant.DETECT_3RD_TAG;
                hasSection3rdFlag = true;
                continue;
            }
            if (NcConstant.DETECT_1TH_TAG.equals(curSectionTag)) {
                handleProcessLineByCode(section1th, processLine, processCode);
                if (section3rdHeaderFlag) {
                    handleProcessLineByCode(section3rd, processLine, processCode);
                }
                if (NcConstant.DETECT_3RD_HEADER_END_TAG.equals(processLine.trim())) {
                    section3rdHeaderFlag = false;
                }
            } else if (NcConstant.DETECT_2ND_TAG.equals(curSectionTag)) {
                handleProcessLineByCode(section2nd, processLine, processCode);
            } else if (NcConstant.DETECT_3RD_TAG.equals(curSectionTag)) {
                if (section3rdReplaceFlag && processLine.contains(NcConstant.DETECT_3RD_REPLACE_SOURCE_INS)) {
                    section3rdReplaceFlag = false;
                    handleProcessLineByCode(section3rd, processLine.replace(NcConstant.DETECT_3RD_REPLACE_SOURCE_INS, NcConstant.DETECT_3RD_REPLACE_TARGET_INS), processCode);
                } else {
                    handleProcessLineByCode(section3rd, processLine, processCode);
                }
            }
        }
        // 生成首尾两段内容
        writeDetectSection(section1th, resultDir, section1thResult);
        if (!hasSection3rdFlag) {
            section3rd = getInitDetectSectionList(section3rdResult);
        }
        writeDetectSection(section3rd, resultDir, section3rdResult);
        // 添加中间段落内容
        appendDetectSection(section2nd, appendList, resultNum, processCode);
    }

    /**
     * 构造初始检测加工文件段落
     *
     * @param resultName
     * @return
     */
    private List<String> getInitDetectSectionList(String resultName) {
        List<String> sectionList = new ArrayList<>();
        sectionList.add(NcConstant.FILE_START_TAG);
        sectionList.add(resultName);
        return sectionList;
    }

    /**
     * 追加检测加工文件段落尾
     *
     * @param sectionList
     */
    private void appendDetectSectionListTail(List<String> sectionList) {
        sectionList.add(NcConstant.PROCESS_FILE_END_INS);
        sectionList.add(NcConstant.FILE_TERMINAL_TAG);
    }

    /**
     * 检测加工文件段落写入结果
     *
     * @param sectionList
     * @param resultDir
     * @param resultName
     */
    private void writeDetectSection(List<String> sectionList, String resultDir, String resultName) throws IOException {
        appendDetectSectionListTail(sectionList);
        FileUtil.writeResult(resultDir + File.separator + resultName, sectionList);
    }

    /**
     * 构造添加到总结果的内容
     *
     * @param sectionList
     * @param appendList
     * @param resultNum
     */
    private void appendDetectSection(List<String> sectionList, List<String> appendList, int resultNum, String processCode) {
        String preCode = processCode + FileUtil.get2BitProcessCode(resultNum + 1);
        String postCode = processCode + FileUtil.get2BitProcessCode(resultNum + 2);
        appendList.add(NcConstant.PROB_FILE_RESULT_TAG + preCode + NcConstant.PROB_FILE_RESULT_PREFIX + preCode);
        for (int i = 0; i < sectionList.size(); ++i) {
            if (sectionList.get(i).trim().startsWith(NcConstant.DETECT_2ND_BRANCH_IF) && sectionList.get(i).trim().contains(NcConstant.DETECT_2ND_BRANCH_GOTO)) {
                appendList.add(replaceDetectSectionDetermineBranch(sectionList.get(i), postCode));
                appendList.add(sectionList.get(++i));
                appendList.add(NcConstant.DETECT_2ND_BRANCH_GOTO + " " + preCode);
            } else {
                appendList.add(sectionList.get(i));
            }
        }
        appendList.add(NcConstant.PROB_FILE_RESULT_TAG + postCode + NcConstant.PROB_FILE_RESULT_PREFIX + postCode);
    }

    /**
     * 替换探测加工文件判断分支
     *
     * @param sectionLine
     * @param postCode
     * @return
     */
    private String replaceDetectSectionDetermineBranch(String sectionLine, String postCode) {
        String determineBranch = sectionLine.substring(0, sectionLine.indexOf(NcConstant.DETECT_2ND_BRANCH_GOTO));
        return determineBranch + NcConstant.DETECT_2ND_BRANCH_GOTO + " " + postCode;
    }

    /**
     * 替换固定路径
     *
     * @param txtLines
     * @param processCode
     */
    private void replaceProcessFixedPath(List<String> txtLines, String processCode) {
        txtLines.add(NcConstant.PROCESS_TARGET_FIXED_PATH + processCode);
        txtLines.add(NcConstant.PROCESS_FIXED_PATH_PREFIX + (NcConstant.PROCESS_PATH_INIT_VALUE + Integer.valueOf(processCode)));
    }

    /**
     * 按生成结果路径处理加工文件中的固定路径
     *
     * @param txtLines
     * @param processLine
     * @param resultPath
     */
    private void handleProcessLineByResult(List<String> txtLines, String processLine, String resultPath) {
        if (!NcConstant.FILE_START_TAG.equals(processLine.trim()) && !NcConstant.PROCESS_TERMINAL_INS.equals(processLine.trim())) {
            if (NcConstant.PROCESS_SOURCE_FIXED_PATH.equals(processLine.trim())) {
                replaceProcessFixedPath(txtLines, FileUtil.getProbNumFromProcessResult(resultPath));
            } else {
                txtLines.add(processLine);
            }
        }
    }

    /**
     * 按探针序号理加工文件中的固定路径
     *
     * @param txtLines
     * @param processLine
     * @param processCode
     */
    private void handleProcessLineByCode(List<String> txtLines, String processLine, String processCode) {
        if (!NcConstant.FILE_START_TAG.equals(processLine.trim()) && !NcConstant.PROCESS_TERMINAL_INS.equals(processLine.trim())) {
            if (NcConstant.PROCESS_SOURCE_FIXED_PATH.equals(processLine.trim())) {
                replaceProcessFixedPath(txtLines, processCode);
            } else {
                txtLines.add(processLine);
            }
        }
    }
}
