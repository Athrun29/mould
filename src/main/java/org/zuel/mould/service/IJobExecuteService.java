package org.zuel.mould.service;

import org.springframework.transaction.annotation.Transactional;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.vo.NcPathVo;

import javax.servlet.http.HttpServletRequest;

public interface IJobExecuteService {

    @Transactional(rollbackFor = Exception.class)
    RespMsg handleNcDir(NcPathVo ncPathVo) throws Exception;

}
