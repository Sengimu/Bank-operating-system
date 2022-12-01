package com.work.competition.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_fund")
public class TbFund {
    @Id//主键
    @KeySql(useGeneratedKeys = true)//自增
    private Integer id;
    private String fundName;
    private Double currentValue;
    private Double fundShares;
    private Double redemptionFee;
    private Double subscriptionAmount;
    private String agent;

}
