package org.zuel.mould.task.impl;

import org.zuel.mould.handler.impl.ProcessFileHandler;
import org.zuel.mould.task.NcTaskExecutor;
import org.zuel.mould.util.StringUtil;

import java.io.IOException;

public class NcProcessFileTask extends NcTaskExecutor {

    private boolean runningFlag = false;

    private String processDirPath;
    private String resultDir;

    public NcProcessFileTask(String processDirPath, String resultDir) throws Exception {
        if(StringUtil.isBlank(processDirPath) || StringUtil.isBlank(resultDir)) {
            throw new Exception("文件路径为空, 创建任务失败.");
        }
        this.processDirPath = processDirPath;
        this.resultDir = resultDir;
    }

    @Override
    public boolean isRunning() {
        return runningFlag;
    }

    @Override
    public void run() {
        try {
            new ProcessFileHandler().handleProcessDir(processDirPath, resultDir);
        } catch (IOException e) {
            System.out.println("Handle process files '" + processDirPath + "' failed.");
            e.printStackTrace();
        }
    }
}
