package org.zuel.mould.task.impl;


import org.springframework.stereotype.Component;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.handler.impl.MultiProbFileHandler;
import org.zuel.mould.handler.impl.SingleProbFileHandler;
import org.zuel.mould.task.INcJobService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    private void prepare(String basePath) throws RuntimeException {
        File baseDir = new File(basePath);
        if(!baseDir.exists() || !baseDir.isDirectory()) {
            throw new RuntimeException("文件目录不存在.");
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
    public void execute(String basePath) throws Exception {
        if(!preExecute(basePath)) {
            throw new RuntimeException("文件目录有误.");
        }
        prepare(basePath);
        executeProb(basePath);
        executeProcess();
        afterExecute();
    }

    private void afterExecute() {

    }

    /**
     * 处理探针程序文件
     * @param basePath
     */
    private void executeProb(String basePath) throws IOException {
        if(probFilePath.size() == 1) {
            SingleProbFileHandler.getSingleton().handleProbFile(basePath, probFilePath);
        } else if(probFilePath.size() > 1) {
            MultiProbFileHandler.getSingleton().handleProbFile(basePath, probFilePath);
        }
    }

    /**
     * 处理加工程序文件
     */
    private void executeProcess() throws Exception {
        ExecutorService processPool = new ThreadPoolExecutor(0, processDirPath.size(), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        for(String processDir : processDirPath) {
            processPool.submit(new NcProcessFileTask(processDir));
        }
        processPool.shutdown();
        while(true){
            if(processPool.isTerminated()){
                System.out.println("all processDir has finished.");
                break;
            }
        }
    }

}
