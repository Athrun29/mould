package org.zuel.mould.task.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zuel.mould.handler.impl.ToolReplaceHandler;
import org.zuel.mould.task.NcTaskExecutor;
import org.zuel.mould.util.StringUtil;

import java.io.IOException;

@Component
@Data
public class NcReplaceToolTask extends NcTaskExecutor {

    @Autowired
    private ToolReplaceHandler toolReplaceHandler;

    private boolean runningFlag = false;

    private String resultPath;

    public NcReplaceToolTask() {}

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
            toolReplaceHandler.handleToolInfo(resultPath);
        } catch (IOException e) {
            System.out.println("Replace tool '" + resultPath + "' failed.");
            e.printStackTrace();
        }
    }
}
