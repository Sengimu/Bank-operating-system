package com.work.competition.mapper;

import com.work.competition.pojo.TbFund;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbFundMapper extends Mapper<TbFund> {

    @Select("SELECT id,fund_name fundName,current_value currentValue,fund_shares fundShares,redemption_fee redemptionFee,subscription_amount subscriptionAmount,agent from tb_fund")
    List<TbFund> searchAll();

    @Select("SELECT id,fund_name fundName,current_value currentValue,fund_shares fundShares,redemption_fee redemptionFee,subscription_amount subscriptionAmount,agent from tb_fund where fund_name = #{name}")
    TbFund searchOne(String name);

}
