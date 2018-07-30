package org.zuel.mould.task.impl;

import org.zuel.mould.handler.impl.ProcessFileHandler;
import org.zuel.mould.handler.impl.ToolReplaceHandler;
import org.zuel.mould.task.NcTaskExecutor;
import org.zuel.mould.util.StringUtil;

import java.io.IOException;

public class NcReplaceToolTask extends NcTaskExecutor {

    private boolean runningFlag = false;

    private String resultPath;

    public NcReplaceToolTask(String resultPath) throws Exception {
        if(StringUtil.isBlank(resultPath)) {
            throw new Exception("文件路径为空, 创建任务失败.");
        }
        this.resultPath = resultPath;
    }

    @Override
    public boolean isRunning() {
        return runningFlag;
    }

    @Override
    public void run() {
        try {
            new ToolReplaceHandler().handleToolInfo(resultPath);
        } catch (IOException e) {
            System.out.println("Replace tool '" + resultPath + "' failed.");
            e.printStackTrace();
        }
    }
}
