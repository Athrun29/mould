package org.zuel.mould.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.bean.KnifeGeneralExample;
import org.zuel.mould.dao.KnifeGeneralMapper;
import org.zuel.mould.dao.ReplaceRecordMapper;
import org.zuel.mould.service.IKnifeToolService;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.vo.KnifeGeneralVo;

import java.util.List;

@Service
public class KnifeToolService implements IKnifeToolService {

    @Autowired
    private KnifeGeneralMapper knifeGeneralMapper;

    @Autowired
    private ReplaceRecordMapper replaceRecordMapper;

    /**
     * 通过名称、直径、半径获取刀具信息
     * @param name
     * @param dia
     * @param rad
     * @return
     */
    @Override
    public KnifeGeneral getKnifeGeneralByInfo(String name, Double dia, Double rad) {
        KnifeGeneralExample knifeGeneralExample = new KnifeGeneralExample();
        knifeGeneralExample.setDistinct(true);
        knifeGeneralExample.createCriteria().andNameEqualTo(name.trim().toUpperCase()).andDiaEqualTo(dia).andRadEqualTo(rad);
        List<KnifeGeneral> knifeGeneralList = knifeGeneralMapper.selectByExample(knifeGeneralExample);
        if(knifeGeneralList.size() > 0) {
            return knifeGeneralList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取所有刀具信息
     * @return
     */
    @Override
    public List<KnifeGeneral> getAllKnifeGeneral() {
        KnifeGeneralExample knifeGeneralExample = new KnifeGeneralExample();
        knifeGeneralExample.setDistinct(true);
        List<KnifeGeneral> models = knifeGeneralMapper.selectByExample(knifeGeneralExample);
        return models;
    }

    /**
     * 修改刀具
     * @param knifeGeneral
     */
    @Override
    public void modifyKnifeGeneralInfo(KnifeGeneral knifeGeneral) {
        knifeGeneralMapper.updateByPrimaryKey(knifeGeneral);
    }

    @Override
    public RespMsg getQueryList(ReqPager reqPager) {
        return null;
    }

    @Override
    public RespMsg getModel(Long id) {
        return null;
    }

    @Override
    public RespMsg saveModel(KnifeGeneral model) {
        return null;
    }

    @Override
    public RespMsg delModel(List<Long> models) {
        return null;
    }
}
