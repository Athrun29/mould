package org.zuel.mould.service;

import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.bean.ReplaceRecord;

import java.util.List;

public interface IKnifeToolService {

    KnifeGeneral getKnifeGeneralByInfo(String name, Double dia, Double rad);

    List<KnifeGeneral> getAllKnifeGeneral();

    void modifyKnifeGeneralInfo(KnifeGeneral knifeGeneral);

    void addReplaceRecord(ReplaceRecord replaceRecord);
}
