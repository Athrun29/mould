package org.zuel.mould.task.impl;

import org.zuel.mould.handler.impl.ProcessFileHandler;
import org.zuel.mould.task.NcTaskExecutor;
import org.zuel.mould.util.StringUtil;

import java.io.IOException;

public class NcProcessFileTask extends NcTaskExecutor {

    private boolean runningFlag = false;
    private String processDirPath;

    public NcProcessFileTask(String processDirPath) throws Exception {
        if(StringUtil.isBlank(processDirPath)) {
            throw new Exception("加工文件路径为空, 创建任务失败.");
        }
        this.processDirPath = processDirPath;
    }

    @Override
    public boolean isRunning() {
        return runningFlag;
    }

    @Override
    public void run() {
        try {
            new ProcessFileHandler().handleProcessDir(processDirPath);
        } catch (IOException e) {
            System.out.println("Handle process files '" + processDirPath + "' failed.");
            e.printStackTrace();
        }
    }
}
