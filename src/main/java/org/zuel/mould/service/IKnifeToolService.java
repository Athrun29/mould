package org.zuel.mould.service;

import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;

import java.util.List;

public interface IKnifeToolService {

    KnifeGeneral getKnifeGeneralByInfo(String name, Double dia, Double rad);

    List<KnifeGeneral> getAllKnifeGeneral();

    void modifyKnifeGeneralInfo(KnifeGeneral knifeGeneral);

    RespMsg getQueryList(ReqPager reqPager);

    RespMsg getModel(Long id);

    RespMsg saveModel(KnifeGeneral model);

    RespMsg delModel(List<Long> models);
}
