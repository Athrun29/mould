package org.zuel.mould.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.service.impl.KnifeToolService;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;

import java.util.List;

@RestController
@RequestMapping(value = "/app/cutter")
public class CutterController {

    @Autowired
    private KnifeToolService knifeToolService;

    @RequestMapping(value = "/list")
    public RespMsg getQueryList(@RequestBody ReqPager<KnifeGeneral> reqPager) {
        return knifeToolService.getQueryList(reqPager);
    }

    @RequestMapping(value = "/get/{id}")
    public RespMsg getModel(@PathVariable("id") Long id) {
        return knifeToolService.getModel(id);
    }

    @RequestMapping(value = "/save")
    public RespMsg saveModel(@RequestBody KnifeGeneral model) {
        return knifeToolService.saveModel(model);
    }

    @RequestMapping(value = "/del")
    public RespMsg delModel(@RequestBody List<Long> models) {
        return knifeToolService.delModel(models);
    }
}
