package org.zuel.mould.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuel.mould.bean.ReplaceRecord;
import org.zuel.mould.service.IReplaceRecordService;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;

import java.util.List;

@RestController
@RequestMapping(value = "/record")
public class ReplaceRecordController {

    @Autowired
    private IReplaceRecordService replaceRecordService;

    @RequestMapping(value = "/list")
    public RespMsg getQueryList(@RequestBody ReqPager reqPager) {
        return replaceRecordService.getQueryList(reqPager);
    }

    @RequestMapping(value = "/get/{id}")
    public RespMsg getModel(@PathVariable("id") Long id) {
        return replaceRecordService.getModel(id);
    }

    @RequestMapping(value = "/save")
    public RespMsg saveModel(@RequestBody ReplaceRecord model) {
        return replaceRecordService.saveModel(model);
    }

    @RequestMapping(value = "/del")
    public RespMsg delModel(@RequestBody List<Long> models) {
        return replaceRecordService.delModel(models);
    }
}
