package org.zuel.mould.service;

import org.zuel.mould.bean.BaseDic;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;

import java.util.List;

public interface IDicDataService {

    List<BaseDic> selectByParentId(Long parent) throws RuntimeException;

    RespMsg getGlassCutterQueryList(ReqPager reqPager) throws RuntimeException;

    RespMsg getGlassCutterModel(Long id) throws RuntimeException;

    RespMsg saveGlassCutterModel(BaseDic model) throws RuntimeException;

    RespMsg delGlassCutterModel(List<Long> models) throws RuntimeException;
}
