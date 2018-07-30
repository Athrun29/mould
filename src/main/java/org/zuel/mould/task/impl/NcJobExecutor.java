package org.zuel.mould.task.impl;


import org.springframework.stereotype.Component;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.handler.impl.MultiProbFileHandler;
import org.zuel.mould.handler.impl.SingleProbFileHandler;
import org.zuel.mould.task.INcJobService;
import org.zuel.mould.context.SpringBeanProxy;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Component
public class NcJobExecutor implements INcJobService {

    private List<String> processDirPath;

    private List<String> probFilePath;

    /**
     * 预处理
     * @param basePath
     * @return
     */
    private boolean preExecute(String basePath) {
        return true;
    }

    /**
     * 检查目录结构并保存相关文件路径
     * @param basePath
     */
    private void prepare(String basePath, String resultPath) throws RuntimeException {
        File baseDir = new File(basePath);
        if(!baseDir.exists() || !baseDir.isDirectory()) {
            throw new RuntimeException("文件目录不存在.");
        }
        File resultDir = new File(resultPath);
        if(!resultDir.exists()) {
            resultDir.mkdir();
        }
        processDirPath = new ArrayList<String>();
        probFilePath = new ArrayList<String>();
        // 保存文件路径
        File[] baseChildFiles = baseDir.listFiles();
        for(File baseChildFile : baseChildFiles) {
            if(baseChildFile.isDirectory() && !NcConstant.PROCESS_HANDLE_DIR.equals(baseChildFile.getName())) {
                processDirPath.add(baseChildFile.getAbsolutePath());
                File[] processFiles = baseChildFile.listFiles();
                for(File processFile : processFiles) {
                    if(processFile.isFile() && processFile.getName().startsWith(NcConstant.PROB_FILE_NAME_PREFIX)) {
                        probFilePath.add(processFile.getAbsolutePath());
//                        processDirPath.add(baseChildFile.getAbsolutePath());
                    }
                }
            }
        }
        // 按文件名排序
        Collections.sort(processDirPath);
        Collections.sort(probFilePath);
    }

    /**
     * 执行处理
     * @param basePath
     */
    @Override
    public void execute(String basePath, String resultPath) throws Exception {
        if(!preExecute(basePath)) {
            throw new RuntimeException("文件目录有误.");
        }
        prepare(basePath, resultPath);
        executeProb(resultPath);
        executeProcess(resultPath);
        executeToolInfo(resultPath);
        afterExecute();
    }

    private void afterExecute() {

    }

    /**
     * 处理探针程序文件
     * @param resultPath
     */
    private void executeProb(String resultPath) throws IOException {
        if(probFilePath.size() == 1) {
            SingleProbFileHandler.getSingleton().handleProbFile(resultPath, probFilePath);
        } else if(probFilePath.size() > 1) {
            MultiProbFileHandler.getSingleton().handleProbFile(resultPath, probFilePath);
        }
    }

    /**
     * 处理加工程序文件
     */
    private void executeProcess(String resultPath) throws Exception {
//        ThreadFactory ncThreadFactory = new ThreadFactoryBuilder().setNameFormat("nc-pool-%d").build();
//        ExecutorService processPool = new ThreadPoolExecutor(2, processDirPath.size(), 0L,
//                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), ncThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        ExecutorService processPool = new ThreadPoolExecutor(2, processDirPath.size(), 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        for(String processDir : processDirPath) {
            processPool.submit(new NcProcessFileTask(processDir, resultPath));
        }
        processPool.shutdown();
        while(true){
            if(processPool.isTerminated()){
                System.out.println("all processDir has finished.");
                break;
            }
        }
    }

    /**
     * 替换处理结果中的刀具信息
     * @param resultPath
     */
    private void executeToolInfo(String resultPath) throws Exception {
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
        ExecutorService processPool = new ThreadPoolExecutor(4, resultFiles.length, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        for(File resultFile : resultFiles) {
            NcReplaceToolTask ncReplaceToolTask = (NcReplaceToolTask) SpringBeanProxy.getBean("ncReplaceToolTask");
            ncReplaceToolTask.setResultPath(resultFile.getAbsolutePath());
            processPool.submit(ncReplaceToolTask);
        }
        processPool.shutdown();
        while(true){
            if(processPool.isTerminated()){
                System.out.println("all tool replace has finished.");
                break;
            }
        }
    }
}
