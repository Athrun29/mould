package org.zuel.mould.service;

import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.bean.KnifeSpcl;

import java.math.BigDecimal;

public interface IKnifeToolService {

    KnifeGeneral getKnifeGeneralByDiaAndRad(BigDecimal dia, BigDecimal rad);

    KnifeSpcl getKnifeSpclByDiaAndRad(BigDecimal dia, BigDecimal rad);

    void addReplaceRecord();
}
