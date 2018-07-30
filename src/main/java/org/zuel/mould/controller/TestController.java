package org.zuel.mould.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.service.IJobExecuteService;
import org.zuel.mould.service.IKnifeToolService;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.util.RespUtil;
import org.zuel.mould.vo.NcPathVo;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    IKnifeToolService knifeToolService;

    @Autowired
    IJobExecuteService jobExecuteService;

    @RequestMapping(value = "/home")
    public RespMsg home(@RequestBody NcPathVo ncPathVo) throws RuntimeException {
        NcPathVo returnPahtVo = new NcPathVo();
        returnPahtVo.setInputPath(ncPathVo.getInputPath());
        returnPahtVo.setOutputPath("newPath");
        System.out.println("Get request.");
        return RespUtil.success(returnPahtVo);
    }

    @RequestMapping(value = "/knife")
    public void knife() throws RuntimeException {
        KnifeGeneral knifeGeneral = knifeToolService.getKnifeGeneralByDiaAndRad(new BigDecimal(0), new BigDecimal(0));
        System.out.println(knifeGeneral);
    }

    @RequestMapping(value = "/ncJob")
    public RespMsg handleNcDir() throws Exception {
        NcPathVo ncPathVo = new NcPathVo();
        ncPathVo.setInputPath("/Users/athrun/Work/Docs/mould/730-1220");
        ncPathVo.setOutputPath("/Users/athrun/Work/Docs/mould/result");
        return jobExecuteService.handleNcDir(ncPathVo);
    }

}
