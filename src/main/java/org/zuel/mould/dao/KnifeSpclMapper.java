package org.zuel.mould.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zuel.mould.bean.KnifeSpcl;
import org.zuel.mould.bean.KnifeSpclExample;

public interface KnifeSpclMapper {
    long countByExample(KnifeSpclExample example);

    int deleteByExample(KnifeSpclExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KnifeSpcl record);

    int insertSelective(KnifeSpcl record);

    List<KnifeSpcl> selectByExample(KnifeSpclExample example);

    KnifeSpcl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KnifeSpcl record, @Param("example") KnifeSpclExample example);

    int updateByExample(@Param("record") KnifeSpcl record, @Param("example") KnifeSpclExample example);

    int updateByPrimaryKeySelective(KnifeSpcl record);

    int updateByPrimaryKey(KnifeSpcl record);
}