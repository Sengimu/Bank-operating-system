package com.work.competition.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_state_bonds")
public class TbStateBonds {
    @Id//主键
    @KeySql(useGeneratedKeys = true)//自增
    private Integer id;
    private String bondName;
    private String nation;
    private Double interest;
    private Double price;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date time;

}
