package com.work.competition.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FundDto implements Serializable {
    private String fundName;
    private Double currentValue;
    private Double fundShares;
    private Double redemptionFee;
    private Double subscriptionAmount;
    private String agent;
}
