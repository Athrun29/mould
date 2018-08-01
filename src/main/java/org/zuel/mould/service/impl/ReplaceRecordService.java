package org.zuel.mould.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.mould.bean.ReplaceRecord;
import org.zuel.mould.dao.ReplaceRecordMapper;
import org.zuel.mould.service.IReplaceRecordService;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.vo.ReplaceRecordVo;

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
        return null;
    }

    @Override
    public RespMsg getModel(Long id) {
        return null;
    }

    @Override
    public RespMsg saveModel(ReplaceRecord model) {
        return null;
    }

    @Override
    public void delModel(List<Long> models) {

    }
}
