package com.work.competition.mapper;

import com.work.competition.pojo.TbStateBonds;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbStateBondsMapper extends Mapper<TbStateBonds> {

    @Select("SELECT id,bond_name bondName,nation,interest,price,time from tb_state_bonds")
    List<TbStateBonds> searchAll();

    @Select("SELECT id,bond_name bondName,nation,interest,price,time from tb_state_bonds where bond_name = #{name}")
    TbStateBonds searchOne(String name);

}
