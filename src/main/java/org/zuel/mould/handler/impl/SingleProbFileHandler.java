package org.zuel.mould.handler.impl;

import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.handler.IProbFileHandler;
import org.zuel.mould.util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SingleProbFileHandler implements IProbFileHandler {

    private static SingleProbFileHandler singleton;

    private SingleProbFileHandler() {}

    public static SingleProbFileHandler getSingleton() {

        if(singleton == null) {
            synchronized (SingleProbFileHandler.class) {
                if(singleton == null) {
                    singleton = new SingleProbFileHandler();
                }
            }
        }

        return singleton;
    }

    /**
     * 单个探针文件直接复制并追加结果
     * @param probFilePath
     */
    @Override
    public void handleProbFile(String resultPath, List<String> probFilePath) throws IOException {
        String probResultPath = resultPath + File.separator + NcConstant.PROB_HANDLE_RESULT;
        Files.deleteIfExists(Paths.get(probResultPath));
        copyProbFile(probFilePath.get(0), probResultPath);
        appendProbHeader(probResultPath);
        appendProbTail(probFilePath.get(0), probResultPath);
    }

    /**
     * 复制内容
     * @param source
     * @param target
     */
    private void copyProbFile(String source, String target) throws IOException{
        try(FileChannel inputChannel = new RandomAccessFile(source, "r").getChannel();
            FileChannel outputChannel = new RandomAccessFile(target, "rw").getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        }  catch (IOException e) {
            throw e;
        }
    }

    /**
     * 修改文件头
     * @param target
     */
    private void appendProbHeader(String target) throws IOException{
        List<String> txtLines = Files.readAllLines(Paths.get(target));
        try(FileWriter writer = new FileWriter(target, false)) {
            writer.write(txtLines.get(0));
            writer.write(System.lineSeparator());
            writer.write(NcConstant.PROB_HANDLE_RESULT);
            writer.write(System.lineSeparator());
            for(int i = 1; i < txtLines.size(); ++i) {
                writer.write(txtLines.get(i));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 修改文件尾
     * @param probFile
     * @param target
     * @throws IOException
     */
    private void appendProbTail(String probFile, String target) throws IOException {
        List<String> txtLines = Files.readAllLines(Paths.get(target));
        // 获取最后一个探针插入符的行数
        int endLine = 0;
        for(int i = txtLines.size() - 1; i > 0; --i) {
            if(NcConstant.PROB_FILE_END_INS.equals(txtLines.get(i)) && NcConstant.PROB_FILE_BEGIN_INS.equals(txtLines.get(i - 1))) {
                endLine = i;
                break;
            }
        }
        if(endLine > 0) {
            try(FileWriter writer = new FileWriter(target, false)) {
                for(int i = 0; i < endLine; ++i) {
                    writer.write(txtLines.get(i));
                    writer.write(System.lineSeparator());
                }
                // 插入探针标志
                String probTagCode = FileUtil.getProbFileNum(probFile) + NcConstant.PROB_FILE_RESULT_POSTFIX;
                writer.write(NcConstant.PROB_FILE_RESULT_TAG + probTagCode + NcConstant.PROB_FILE_RESULT_PREFIX + probTagCode);
                writer.write(System.lineSeparator());
                for(int i = endLine; i < txtLines.size(); ++i) {
                    writer.write(txtLines.get(i));
                    writer.write(System.lineSeparator());
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }
}
