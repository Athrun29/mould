package org.zuel.mould.service;

import org.zuel.mould.bean.BaseDic;

import java.util.List;

public interface IDicDataService {

    List<BaseDic> selectByParentId(Long parent);
}
