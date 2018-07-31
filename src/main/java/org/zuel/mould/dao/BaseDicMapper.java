package org.zuel.mould.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zuel.mould.bean.BaseDic;
import org.zuel.mould.bean.BaseDicExample;

public interface BaseDicMapper {
    long countByExample(BaseDicExample example);

    int deleteByExample(BaseDicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseDic record);

    int insertSelective(BaseDic record);

    List<BaseDic> selectByExample(BaseDicExample example);

    BaseDic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseDic record, @Param("example") BaseDicExample example);

    int updateByExample(@Param("record") BaseDic record, @Param("example") BaseDicExample example);

    int updateByPrimaryKeySelective(BaseDic record);

    int updateByPrimaryKey(BaseDic record);
}