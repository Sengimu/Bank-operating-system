package com.work.competition.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class TbUser {
    @Id//主键
    @KeySql(useGeneratedKeys = true)//自增
    private Integer id;
    private String accountNumber;
    private String email;
    private String password;
    private Date createAt;

}
