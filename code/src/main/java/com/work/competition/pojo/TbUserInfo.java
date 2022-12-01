package com.work.competition.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user_info")//对应数据库
public class TbUserInfo {
    @Id//主键
    @KeySql(useGeneratedKeys = true)//自增
    private Integer id;
    private Integer userId;
    private String username;
    private String school;
    private String phone;
    private Date applyTime;

}
