package com.work.competition.mapper;

import com.work.competition.pojo.TbFinancialEnterpriseBonds;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbFinancialEnterpriseBondsMapper extends Mapper<TbFinancialEnterpriseBonds> {

    @Select("SELECT id,bond_name bondName,enterprise,interest,price,time from tb_financial_enterprise_bonds")
    List<TbFinancialEnterpriseBonds> searchAll();

    @Select("SELECT id,bond_name bondName,enterprise,interest,price,time from tb_financial_enterprise_bonds where bond_name = #{name}")
    TbFinancialEnterpriseBonds searchOne(String name);

}
