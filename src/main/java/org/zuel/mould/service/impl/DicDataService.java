package org.zuel.mould.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.mould.bean.BaseDic;
import org.zuel.mould.bean.BaseDicExample;
import org.zuel.mould.dao.BaseDicMapper;
import org.zuel.mould.service.IDicDataService;

import java.util.List;

@Service
public class DicDataService implements IDicDataService {

    @Autowired
    private BaseDicMapper baseDicMapper;

    @Override
    public List<BaseDic> selectByParentId(Long parent) {
        BaseDicExample baseDicExample = new BaseDicExample();
        baseDicExample.setDistinct(true);
        baseDicExample.createCriteria().andParentEqualTo(parent);
        return baseDicMapper.selectByExample(baseDicExample);
    }
}
