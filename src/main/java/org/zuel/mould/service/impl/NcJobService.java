package org.zuel.mould.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.zuel.mould.dao.KnifeGeneralMapper;
import org.zuel.mould.dao.KnifeSpclMapper;
import org.zuel.mould.service.INcJobService;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.vo.NcPathVo;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.ServiceMode;

@ServiceMode
public class NcJobService implements INcJobService {

    @Autowired
    private KnifeGeneralMapper knifeGeneralMapper;

    @Autowired
    private KnifeSpclMapper knifeSpclMapper;

    @Override
    public RespMsg handleNcDir(HttpServletRequest request, NcPathVo ncPathVo) throws RuntimeException {
        return null;
    }

    
}
