package org.zuel.mould.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zuel.mould.bean.BaseDic;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.service.IDicDataService;
import org.zuel.mould.service.IJobExecuteService;
import org.zuel.mould.service.IKnifeToolService;
import org.zuel.mould.util.BasePager;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.util.RespUtil;
import org.zuel.mould.vo.NcPathVo;

import java.util.List;


@RestController
@RequestMapping(value = "/app/test")
public class TestController {

    @Autowired
    IKnifeToolService knifeToolService;

    @Autowired
    IJobExecuteService jobExecuteService;

    @Autowired
    IDicDataService dicDataService;

    @RequestMapping(value = "/home")
    public RespMsg home(@RequestBody NcPathVo ncPathVo) throws RuntimeException {
        NcPathVo returnPahtVo = new NcPathVo();
        returnPahtVo.setInput(ncPathVo.getInput());
        returnPahtVo.setOutput("newPath");
        System.out.println("Get request.");
        return RespUtil.success(returnPahtVo);
    }

    @RequestMapping(value = "/ncJob")
    public RespMsg handleNcDir() throws Exception {
        NcPathVo ncPathVo = new NcPathVo();
        ncPathVo.setInput("/Users/athrun/Work/Docs/mould/730-1220");
        ncPathVo.setOutput("/Users/athrun/Work/Docs/mould");
        return jobExecuteService.handleNcDir(ncPathVo);
    }

    @RequestMapping(value = "/page")
    public RespMsg page() {
        ReqPager reqPager = new ReqPager();
        reqPager.setPager(new BasePager());
        reqPager.getPager().setPageNum(2);
        reqPager.getPager().setPageSize(2);
        BaseDic queryModel = new BaseDic();
        queryModel.setParent(NcConstant.GLASS_CUTTER_ROOT_ID);
        reqPager.setQueryModel(queryModel);
        return dicDataService.getGlassCutterQueryList(reqPager);
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public RespMsg param(@RequestParam("input") String inputPath, @RequestParam("output") String outputPath) throws Exception {
        System.out.println(inputPath + ", " + outputPath);
        NcPathVo ncPathVo = new NcPathVo();
        ncPathVo.setInput(inputPath);
        ncPathVo.setOutput(outputPath);
        return jobExecuteService.handleNcDir(ncPathVo);
    }

}
