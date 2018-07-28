package org.zuel.mould.service;

import org.zuel.mould.util.RespMsg;
import org.zuel.mould.vo.NcPathVo;

import javax.servlet.http.HttpServletRequest;

public interface INcJobService {

    RespMsg handleNcDir(HttpServletRequest request, NcPathVo ncPathVo) throws RuntimeException;

}
