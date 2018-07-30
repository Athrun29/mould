package org.zuel.mould.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.bean.KnifeGeneralExample;
import org.zuel.mould.bean.KnifeSpcl;
import org.zuel.mould.bean.KnifeSpclExample;
import org.zuel.mould.dao.KnifeGeneralMapper;
import org.zuel.mould.dao.KnifeSpclMapper;
import org.zuel.mould.service.IKnifeToolService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class KnifeToolService implements IKnifeToolService {

    @Autowired
    private KnifeGeneralMapper knifeGeneralMapper;

    @Autowired
    private KnifeSpclMapper knifeSpclMapper;

    @Override
    public KnifeGeneral getKnifeGeneralByDiaAndRad(BigDecimal dia, BigDecimal rad) {
        System.out.println("query dia: " + dia + ", rad: " + rad);
        KnifeGeneralExample knifeGeneralExample = new KnifeGeneralExample();
        knifeGeneralExample.setDistinct(true);
        knifeGeneralExample.createCriteria().andDiaEqualTo(dia).andRadEqualTo(rad);
        List<KnifeGeneral> knifeGeneralList = knifeGeneralMapper.selectByExample(knifeGeneralExample);
        if(knifeGeneralList.size() > 0) {
            return knifeGeneralList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public KnifeSpcl getKnifeSpclByDiaAndRad(BigDecimal dia, BigDecimal rad) {
        KnifeSpclExample knifeSpclExample = new KnifeSpclExample();
        knifeSpclExample.setDistinct(true);
        knifeSpclExample.createCriteria().andDiaEqualTo(dia).andRadEqualTo(rad);
        List<KnifeSpcl> knifeSpclList = knifeSpclMapper.selectByExample(knifeSpclExample);
        if(knifeSpclList.size() > 0) {
            return knifeSpclList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void addReplaceRecord() {

    }
}
