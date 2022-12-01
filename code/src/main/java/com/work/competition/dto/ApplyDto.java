package com.work.competition.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApplyDto implements Serializable {
    private Integer userId;
    private String username;
    private String school;
    private String phone;
}
