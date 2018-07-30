package org.zuel.mould.handler.impl;

import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.handler.IProbFileHandler;
import org.zuel.mould.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MultiProbFileHandler implements IProbFileHandler {

    private static MultiProbFileHandler singleton;

    private MultiProbFileHandler() {}

    public static MultiProbFileHandler getSingleton() {
        if(singleton == null) {
            synchronized (MultiProbFileHandler.class) {
                if (singleton == null) {
                    singleton = new MultiProbFileHandler();
                }
            }
        }
        return singleton;
    }

    /**
     * 多个探针文件合并
     * @param resultPath
     * @param probFilePath
     * @throws IOException
     */
    @Override
    public void handleProbFile(String resultPath, List<String> probFilePath) throws IOException {
        List<String> mergeResult = getMergeProbResult(probFilePath);
        writeProbResult(resultPath, mergeResult);
    }

    /**
     * 合并多个探针文件内容
     * 删除多余的文件起始符与结束指令
     * @param probFilePath
     * @return
     * @throws IOException
     */
    private List<String> getMergeProbResult(List<String> probFilePath) throws IOException {
        List<String> txtLines = new ArrayList<>();
        List<String> probTags = new ArrayList<>();
        txtLines.add(NcConstant.FILE_START_TAG);
        txtLines.add(NcConstant.PROB_HANDLE_RESULT);
        for(int i = 0; i < probFilePath.size(); ++i) {
            // 添加探针指令内容
            List<String> probLines = Files.readAllLines(Paths.get(probFilePath.get(i)));
            for(String probLine : probLines) {
                if(!NcConstant.FILE_START_TAG.equals(probLine.trim()) && !NcConstant.PROB_FILE_END_INS.equals(probLine.trim())) {
                    txtLines.add(probLine);
                }
            }
            // 文件之间加空行
            if(i != probFilePath.size() - 1) {
                txtLines.add(" ");
            }
            // 构造探针标志
            String probTagCode = FileUtil.getProbFileNum(probFilePath.get(i)) + NcConstant.PROB_FILE_RESULT_POSTFIX;
            probTags.add(NcConstant.PROB_FILE_RESULT_TAG + probTagCode + NcConstant.PROB_FILE_RESULT_PREFIX + probTagCode);
        }
        // 添加合并标志
        for(String probTag : probTags) {
            txtLines.add(probTag);
        }
        // 追加结束指令与文件终止符
        txtLines.add(NcConstant.PROB_FILE_END_INS);
        txtLines.add(NcConstant.FILE_TERMINAL_TAG);
        return txtLines;
    }

    /**
     * 写入合并结果
     * @param resultPath
     * @param txtLines
     * @throws IOException
     */
    private void writeProbResult(String resultPath, List<String> txtLines) throws IOException {
        String probResultPath = resultPath + File.separator + NcConstant.PROB_HANDLE_RESULT;
        FileUtil.writeResult(probResultPath, txtLines);
    }
}
