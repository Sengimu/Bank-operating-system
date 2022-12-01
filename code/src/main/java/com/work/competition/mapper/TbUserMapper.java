package com.work.competition.mapper;

import com.work.competition.pojo.TbUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbUserMapper extends Mapper<TbUser> {

    @Select("SELECT id,account_number accountNumber,email,`password`,create_at createAt from tb_user where email = #{accountNumber} or account_number = #{accountNumber} ")
    List<TbUser> selectByEmOrAcc(String accountNumber);

    @Select("SELECT id,account_number accountNumber,email,`password`,create_at createAt from tb_user where password = #{password} and  (email = #{accountNumber} or account_number = #{accountNumber}) ")
    TbUser selectByEmOrAccAndPa(@Param("accountNumber") String accountNumber,
                                      @Param("password") String password);

}
