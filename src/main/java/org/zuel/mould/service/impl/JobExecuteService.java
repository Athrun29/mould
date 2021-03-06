package org.zuel.mould.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.constant.RespEnum;
import org.zuel.mould.exception.DefinedException;
import org.zuel.mould.service.IJobExecuteService;
import org.zuel.mould.task.impl.NcJobExecutor;
import org.zuel.mould.util.FileUtil;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.util.RespUtil;
import org.zuel.mould.util.StringUtil;
import org.zuel.mould.vo.NcPathVo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class JobExecuteService implements IJobExecuteService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespMsg handleNcDir(NcPathVo ncPathVo) {
        DateFormat dateFormat = new SimpleDateFormat(NcConstant.DATE_FORMAT_MINI);
        String curTime = dateFormat.format(new Date());
        String resultPath = ncPathVo.getOutput() + File.separator + NcConstant.PROCESS_HANDLE_DIR + "_" + curTime;
        try {
            if (StringUtil.isBlank(ncPathVo.getInput()) || StringUtil.isBlank(ncPathVo.getOutput())) {
                throw new DefinedException(RespEnum.PATH_ERROR);
            }
            long beginTime = System.currentTimeMillis();
            new NcJobExecutor().execute(ncPathVo.getInput(), resultPath, curTime);
            long endTime = System.currentTimeMillis();
            System.out.println("NcJob cost: " + (endTime - beginTime));
            return RespUtil.success("文件夹名称: " + resultPath + ", 用时: " + (endTime - beginTime) + "ms");
        } catch (Exception e) {
            try {
                FileUtil.clearDir(resultPath);
                Files.deleteIfExists(Paths.get(resultPath));
            } catch(Exception err) {
            }
            return RespUtil.error(0, e.getMessage());
        }
    }
}
