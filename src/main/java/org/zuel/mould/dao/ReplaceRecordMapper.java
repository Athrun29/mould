package org.zuel.mould.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zuel.mould.bean.ReplaceRecord;
import org.zuel.mould.bean.ReplaceRecordExample;

public interface ReplaceRecordMapper {
    long countByExample(ReplaceRecordExample example);

    int deleteByExample(ReplaceRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReplaceRecord record);

    int insertSelective(ReplaceRecord record);

    List<ReplaceRecord> selectByExample(ReplaceRecordExample example);

    ReplaceRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReplaceRecord record, @Param("example") ReplaceRecordExample example);

    int updateByExample(@Param("record") ReplaceRecord record, @Param("example") ReplaceRecordExample example);

    int updateByPrimaryKeySelective(ReplaceRecord record);

    int updateByPrimaryKey(ReplaceRecord record);
}