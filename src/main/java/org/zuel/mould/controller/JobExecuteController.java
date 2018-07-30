package org.zuel.mould.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuel.mould.service.IJobExecuteService;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.vo.NcPathVo;

@RestController
@RequestMapping(value = "/jobExecute")
public class JobExecuteController {

    @Autowired
    IJobExecuteService jobExecuteService;

    @RequestMapping("ncJob")
    public RespMsg handleNcDir(NcPathVo ncPathVo) throws Exception {
        return jobExecuteService.handleNcDir(ncPathVo);
    }
}
