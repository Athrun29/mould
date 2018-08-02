package org.zuel.mould.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.mould.bean.ReplaceRecord;
import org.zuel.mould.bean.ReplaceRecordExample;
import org.zuel.mould.dao.ReplaceRecordMapper;
import org.zuel.mould.service.IReplaceRecordService;
import org.zuel.mould.util.*;
import org.zuel.mould.vo.ReplaceRecordVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplaceRecordService implements IReplaceRecordService {

    @Autowired
    private ReplaceRecordMapper replaceRecordMapper;

    @Override
    public void addReplaceRecord(ReplaceRecord replaceRecord) {
        replaceRecordMapper.insertSelective(replaceRecord);
    }

    @Override
    public RespMsg getQueryList(ReqPager reqPager) {
        ReplaceRecord queryModel = (ReplaceRecord) reqPager.getQueryModel();
        Page page = PageHelper.startPage(reqPager.getPager().getPageNum(), reqPager.getPager().getPageSize());
        List<ReplaceRecord> models = replaceRecordMapper.selectByExample(createQueryCondition(queryModel));
        List<ReplaceRecordVo> vos = new ArrayList<>();
        for(ReplaceRecord model : models) {
            vos.add(model2vo(model));
        }
        return RespUtil.success(page, vos);
    }

    @Override
    public RespMsg getModel(Long id) {
        ReplaceRecord model = replaceRecordMapper.selectByPrimaryKey(id);
        return RespUtil.success(model2vo(model));
    }

    @Override
    public RespMsg saveModel(ReplaceRecord model) {
        if(model.getId() == null) {
            model.setCreateTime(new Date());
            replaceRecordMapper.insertSelective(model);
        } else {
            replaceRecordMapper.updateByPrimaryKey(model);
        }
        return RespUtil.success(model2vo(model));
    }

    @Override
    public RespMsg delModel(List<Long> models) {
        if(models.size() == 0) {
            models.add(-1L);
        }
        ReplaceRecordExample example = new ReplaceRecordExample();
        example.createCriteria().andIdIn(models);
        replaceRecordMapper.deleteByExample(example);
        return RespUtil.success();
    }

    private ReplaceRecordVo model2vo(ReplaceRecord model) {
        ReplaceRecordVo vo = new ReplaceRecordVo();
        vo.setId(model.getId());
        vo.setSrcName(model.getSrcName());
        vo.setSrcDia(model.getSrcDia());
        vo.setSrcRad(model.getSrcRad());
        vo.setSrcLen(model.getSrcLen());
        vo.setTarName(model.getTarName());
        vo.setTarDia(model.getTarDia());
        vo.setTarRad(model.getTarRad());
        vo.setTarLen(model.getTarLen());
        vo.setRemark(model.getRemark());
        vo.setCreateTime(DateUtil.getDateStrMini(model.getCreateTime()));
        return vo;
    }

    private ReplaceRecordExample createQueryCondition(ReplaceRecord queryModel) {
        ReplaceRecordExample example = new ReplaceRecordExample();
        example.setDistinct(true);
        ReplaceRecordExample.Criteria criteria = example.createCriteria();
        if(queryModel.getId() != null) {
            criteria.andIdEqualTo(queryModel.getId());
        }
        if(!StringUtil.isBlank(queryModel.getSrcName())) {
            criteria.andSrcNameLike(queryModel.getSrcName().trim().toUpperCase());
        }
        if(!StringUtil.isBlank(queryModel.getTarName())) {
            criteria.andTarNameLike(queryModel.getTarName().trim().toUpperCase());
        }
        return example;
    }
}
