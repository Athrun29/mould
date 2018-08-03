package org.zuel.mould.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.mould.bean.BaseDic;
import org.zuel.mould.bean.BaseDicExample;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.dao.BaseDicMapper;
import org.zuel.mould.service.IDicDataService;
import org.zuel.mould.util.*;
import org.zuel.mould.vo.BaseDicVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class DicDataService implements IDicDataService {

    @Autowired
    private BaseDicMapper baseDicMapper;

    @Override
    public List<BaseDic> selectByParentId(Long parent) throws RuntimeException {
        BaseDicExample baseDicExample = new BaseDicExample();
        baseDicExample.setDistinct(true);
        baseDicExample.createCriteria().andParentEqualTo(parent);
        return baseDicMapper.selectByExample(baseDicExample);
    }

    @Override
    public RespMsg getGlassCutterQueryList(ReqPager reqPager) throws RuntimeException {
        Page page = null;
        BaseDic queryModel = (BaseDic) reqPager.getQueryModel();
        queryModel.setParent(NcConstant.GLASS_CUTTER_ROOT_ID);
        if(reqPager.getPager() != null) {
            page = PageHelper.startPage(reqPager.getPager().getPageNum(), reqPager.getPager().getPageSize());
        }
        List<BaseDic> models = baseDicMapper.selectByExample(createQueryCondition(queryModel));
        List<BaseDicVo> vos = new ArrayList<>();
        for(BaseDic model : models) {
            vos.add(model2vo(model));
        }
        return RespUtil.success(page, vos);
    }

    @Override
    public RespMsg getGlassCutterModel(Long id) throws RuntimeException {
        BaseDic model = baseDicMapper.selectByPrimaryKey(id);
        return RespUtil.success(model2vo(model));
    }

    @Override
    public RespMsg saveGlassCutterModel(BaseDic model) throws RuntimeException {
        model.setParent(NcConstant.GLASS_CUTTER_ROOT_ID);
        if(!StringUtil.isBlank(model.getCode())) {
            model.setCode(model.getCode().trim().toUpperCase());
        }
        if(!StringUtil.isBlank(model.getName())) {
            model.setName(model.getName().trim().toUpperCase());
        }
        if(model.getId() == null) {
            baseDicMapper.insertSelective(model);
        } else {
            baseDicMapper.updateByPrimaryKey(model);
        }
        return RespUtil.success(model2vo(model));
    }

    @Override
    public RespMsg delGlassCutterModel(List<Long> models) throws RuntimeException {
        if(models.size() == 0) {
            models.add(-1L);
        }
        BaseDicExample example = new BaseDicExample();
        example.createCriteria().andIdIn(models).andParentEqualTo(NcConstant.GLASS_CUTTER_ROOT_ID);
        baseDicMapper.deleteByExample(example);
        return RespUtil.success();
    }

    private BaseDicVo model2vo(BaseDic model) throws RuntimeException {
        BaseDicVo vo = new BaseDicVo();
        vo.setId(model.getId());
        vo.setCode(model.getCode());
        vo.setName(model.getName());
        vo.setRemark(model.getRemark());
        return vo;
    }

    private BaseDic vo2Model(BaseDicVo vo) {
        BaseDic model = new BaseDic();
        model.setId(vo.getId());
        model.setCode(model.getCode());
        model.setName(model.getName());
        model.setRemark(model.getRemark());
        return model;
    }

    private BaseDicExample createQueryCondition(BaseDic queryModel) {
        BaseDicExample example = new BaseDicExample();
        example.setDistinct(true);
        BaseDicExample.Criteria criteria = example.createCriteria();
        if(queryModel.getId() != null) {
            criteria.andIdEqualTo(queryModel.getId());
        }
        if(!StringUtil.isBlank(queryModel.getCode())) {
            criteria.andCodeLike(queryModel.getCode());
        }
        if(!StringUtil.isBlank(queryModel.getName())) {
            criteria.andNameLike(queryModel.getName().trim().toUpperCase());
        }
        if(queryModel.getParent() != null) {
            criteria.andParentEqualTo(queryModel.getParent());
        }
        return example;
    }
}
