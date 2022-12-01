package com.work.competition.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {
    private String accountNumber;
    private String email;
    private String password;
}
