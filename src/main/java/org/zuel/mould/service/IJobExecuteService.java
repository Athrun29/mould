package org.zuel.mould.service;

import org.zuel.mould.util.RespMsg;
import org.zuel.mould.vo.NcPathVo;

import javax.servlet.http.HttpServletRequest;

public interface IJobExecuteService {

    RespMsg handleNcDir(NcPathVo ncPathVo) throws Exception;

}
