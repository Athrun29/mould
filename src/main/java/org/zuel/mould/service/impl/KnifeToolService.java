package org.zuel.mould.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.bean.KnifeGeneralExample;
import org.zuel.mould.dao.KnifeGeneralMapper;
import org.zuel.mould.service.IKnifeToolService;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.util.RespUtil;
import org.zuel.mould.util.StringUtil;
import org.zuel.mould.vo.KnifeGeneralVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class KnifeToolService implements IKnifeToolService {

    @Autowired
    private KnifeGeneralMapper knifeGeneralMapper;

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
        KnifeGeneral queryModel = (KnifeGeneral) reqPager.getQueryModel();
        Page page = PageHelper.startPage(reqPager.getPager().getPageNum(), reqPager.getPager().getPageSize());
        List<KnifeGeneral> models = knifeGeneralMapper.selectByExample(createQueryCondition(queryModel));
        List<KnifeGeneralVo> vos = new ArrayList<>();
        for(KnifeGeneral model : models) {
            vos.add(model2vo(model));
        }
        return RespUtil.success(page, vos);
    }

    @Override
    public RespMsg getModel(Long id) {
        KnifeGeneral model = knifeGeneralMapper.selectByPrimaryKey(id);
        return RespUtil.success(model2vo(model));
    }

    @Override
    public RespMsg saveModel(KnifeGeneral model) {
        if(model.getId() == null) {
            knifeGeneralMapper.insertSelective(model);
        } else {
            knifeGeneralMapper.updateByPrimaryKey(model);
        }
        return RespUtil.success(model2vo(model));
    }

    @Override
    public RespMsg delModel(List<Long> models) {
        if(models.size() == 0) {
            models.add(-1L);
        }
        KnifeGeneralExample example = new KnifeGeneralExample();
        example.createCriteria().andIdIn(models);
        knifeGeneralMapper.deleteByExample(example);
        return RespUtil.success();
    }

    private KnifeGeneralVo model2vo(KnifeGeneral model) {
        KnifeGeneralVo vo = new KnifeGeneralVo();
        vo.setId(model.getId());
        vo.setCode(model.getCode());
        vo.setDia(model.getDia());
        vo.setRad(model.getRad());
        vo.setLen(model.getLen());
        vo.setRemark(model.getRemark());
        return vo;
    }

    private KnifeGeneralExample createQueryCondition(KnifeGeneral queryModel) {
        KnifeGeneralExample example = new KnifeGeneralExample();
        example.setDistinct(true);
        KnifeGeneralExample.Criteria criteria = example.createCriteria();
        if(queryModel.getId() != null) {
            criteria.andIdEqualTo(queryModel.getId());
        }
        if(!StringUtil.isBlank(queryModel.getCode())) {
            criteria.andCodeLike(queryModel.getCode());
        }
        if(!StringUtil.isBlank(queryModel.getName())) {
            criteria.andNameLike(queryModel.getName().trim().toUpperCase());
        }
        return example;
    }
}
