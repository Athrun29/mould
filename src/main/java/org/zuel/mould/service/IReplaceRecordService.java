package org.zuel.mould.service;

import org.zuel.mould.bean.ReplaceRecord;
import org.zuel.mould.util.ReqPager;
import org.zuel.mould.util.RespMsg;

import java.util.List;

public interface IReplaceRecordService {

    void addReplaceRecord(ReplaceRecord replaceRecord);

    RespMsg getQueryList(ReqPager reqPager);

    RespMsg getModel(Long id);

    RespMsg saveModel(ReplaceRecord model);

    RespMsg delModel(List<Long> models);
}
