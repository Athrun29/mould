package org.zuel.mould.service.impl;

import org.springframework.stereotype.Service;
import org.zuel.mould.constant.RespEnum;
import org.zuel.mould.exception.DefinedException;
import org.zuel.mould.service.IJobExecuteService;
import org.zuel.mould.task.impl.NcJobExecutor;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.util.RespUtil;
import org.zuel.mould.util.StringUtil;
import org.zuel.mould.vo.NcPathVo;


@Service
public class JobExecuteService implements IJobExecuteService {

    @Override
    public RespMsg handleNcDir(NcPathVo ncPathVo) throws Exception {
        if(StringUtil.isBlank(ncPathVo.getInputPath()) || StringUtil.isBlank(ncPathVo.getOutputPath())) {
            throw new DefinedException(RespEnum.PATH_ERROR);
        }
        new NcJobExecutor().execute(ncPathVo.getInputPath(), ncPathVo.getOutputPath());
        return RespUtil.success();
    }

    
}
