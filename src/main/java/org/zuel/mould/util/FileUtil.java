package org.zuel.mould.util;

import org.zuel.mould.constant.NcConstant;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {


    /**
     * 解析探针
     * @param probFile
     * @return
     */
    public static String getProbFileNum(String probFile) {
        String[] arrPath = probFile.split("\\" + File.separator);
        String[] arrFileName = arrPath[arrPath.length - 1].split("\\.");
        String[] arrProbNum = arrFileName[0].split("_");
        String ProbFileNum = arrProbNum[1];
        if(ProbFileNum.length() == 1) {
            ProbFileNum = "0" + ProbFileNum;
        }
        return ProbFileNum;
    }

    /**
     * 构造两位加工文件序号
     * @param processFilePath
     * @return
     */
    public static String get2BitProbCode(String processFilePath) {
        String[] arrFileName = processFilePath.split("\\.");
        String[] arrProbNum = arrFileName[0].split("_");
        String ProbCode = arrProbNum[1];
        if(ProbCode.length() == 1) {
            ProbCode = "0" + ProbCode;
        }
        return ProbCode;
    }

    /**
     * 构造两位加工结果序号
     * @param resultNum
     * @return
     */
    public static String get2BitProcessCode(int resultNum) {
        String processCode = String.valueOf(resultNum);
        if(processCode.length() == 1) {
            processCode = "0" + processCode;
        }
        return processCode;
    }

    /**
     * 写入结果
     * @param resultPath
     * @param txtLines
     */
    public static void writeResult(String resultPath, List<String> txtLines) throws IOException {
        Files.deleteIfExists(Paths.get(resultPath));
        try(FileWriter writer = new FileWriter(resultPath, false)) {
            for(String txtLine : txtLines) {
                writer.write(txtLine);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 从文件路径解析文件名
     * @param filePath
     * @return
     */
    public static String getFileNameFromFilePath(String filePath) {
        String[] arrPath = filePath.split("\\" + File.separator);
        return arrPath[arrPath.length - 1];
    }

    /**
     * 从加工结果路径解析加工文件序号
     * @param resultPath
     * @return
     */
    public static String getProbNumFromProcessResult(String resultPath) {
        String[] arrPath = resultPath.split("\\" + File.separator);
        return arrPath[arrPath.length - 1].substring(1, 3);
    }

    /**
     * 判断是否NC文件
     * @param filePath
     * @return
     */
    public static boolean isNcFile(String filePath) {
        return filePath.endsWith(".nc") || filePath.endsWith(".NC") || filePath.endsWith(".Nc") || filePath.endsWith(".nC");
    }

    /**
     * 写入探测内容至总结果
     * baseDir/processDir/resultDir
     * @param resultDir
     * @param appendList
     * @param processCode
     */
    public static synchronized void writeDetectTagToProbResult(String resultDir, List<String> appendList, String processCode) throws IOException {
        System.out.println("Current Thread : " + Thread.currentThread().getName() + " start to write probResult...");
//        String baseDir = new File(resultDir).getParentFile().getParentFile().getAbsolutePath();
        String baseDir = new File(resultDir).getAbsolutePath();
        String probResultPath = baseDir + File.separator + NcConstant.PROB_HANDLE_RESULT;
        List<String> resultLines = Files.readAllLines(Paths.get(probResultPath));
        Files.deleteIfExists(Paths.get(probResultPath));
        try(FileWriter writer = new FileWriter(probResultPath, false)) {
            for(String resultLine : resultLines) {
                String probTagStr = NcConstant.PROB_FILE_RESULT_TAG + processCode + NcConstant.PROB_FILE_RESULT_POSTFIX
                        + NcConstant.PROB_FILE_RESULT_PREFIX + processCode + NcConstant.PROB_FILE_RESULT_POSTFIX;
                if(!(probTagStr).equals(resultLine.trim())) {
                    writer.write(resultLine);
                    writer.write(System.lineSeparator());
                } else {
                    for(String appendLine : appendList) {
                        writer.write(appendLine);
                        writer.write(System.lineSeparator());
                    }
                }
            }
        }
        System.out.println("Current Thread : " + Thread.currentThread().getName() + " has written probResult...");
    }


    /**
     * 获取结果目录下所有加工结果文件
     * @param resultPath
     * @return
     */
    public static File[] getProcessResultFiles(String resultPath) {
        File[] resultFiles = new File(resultPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isFile() && pathname.getName().startsWith(NcConstant.PROCESS_HANDLE_PREFIX)
                        && !NcConstant.PROB_HANDLE_RESULT.equals(pathname.getName())) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        return resultFiles;
    }

    /**
     * 默认写入
     * @param writer
     * @param txtLine
     * @throws IOException
     */
    public static void writeWithLine(FileWriter writer, String txtLine) throws IOException {
        writer.write(txtLine);
        writer.write(System.lineSeparator());
    }

    /**
     * 清空文件夹
     * @param dirPath
     */
    public static void clearDir(String dirPath) {
        File[] files = new File(dirPath).listFiles();
        for(File file : files) {
            file.delete();
        }
    }
}
