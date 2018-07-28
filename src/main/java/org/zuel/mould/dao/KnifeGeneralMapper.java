package org.zuel.mould.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.bean.KnifeGeneralExample;

public interface KnifeGeneralMapper {
    long countByExample(KnifeGeneralExample example);

    int deleteByExample(KnifeGeneralExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KnifeGeneral record);

    int insertSelective(KnifeGeneral record);

    List<KnifeGeneral> selectByExample(KnifeGeneralExample example);

    KnifeGeneral selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KnifeGeneral record, @Param("example") KnifeGeneralExample example);

    int updateByExample(@Param("record") KnifeGeneral record, @Param("example") KnifeGeneralExample example);

    int updateByPrimaryKeySelective(KnifeGeneral record);

    int updateByPrimaryKey(KnifeGeneral record);
}