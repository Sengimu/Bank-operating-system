package com.work.competition.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FinancialEnterpriseBondsDto implements Serializable {
    private String bondName;
    private String enterprise;
    private Double interest;
    private Double price;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date time;
}
