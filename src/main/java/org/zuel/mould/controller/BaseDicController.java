package org.zuel.mould.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuel.mould.bean.BaseDic;
import org.zuel.mould.service.IDicDataService;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;

import java.util.List;

@RestController
@RequestMapping(value = "/dic")
public class BaseDicController {

    @Autowired
    IDicDataService dicDataService;

    @RequestMapping(value = "/glassCutter/list")
    public RespMsg getQueryList(@RequestBody ReqPager reqPager) {
        return dicDataService.getGlassCutterQueryList(reqPager);
    }

    @RequestMapping(value = "/glassCutter/get/{id}")
    public RespMsg getModel(@PathVariable("id") Long id) {
        return dicDataService.getGlassCutterModel(id);
    }

    @RequestMapping(value = "/glassCutter/save")
    public RespMsg saveModel(@RequestBody BaseDic model) {
        return dicDataService.saveGlassCutterModel(model);
    }

    @RequestMapping(value = "/glassCutter/del")
    public RespMsg delModel(@RequestBody List<Long> models) {
        return dicDataService.delGlassCutterModel(models);
    }
}
