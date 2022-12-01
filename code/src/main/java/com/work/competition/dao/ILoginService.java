package com.work.competition.dao;

import com.work.competition.dto.*;

import java.util.Map;

public interface ILoginService {

    /**
     * 用户名邮箱密码注册
     *
     * @param registerDto
     * @return
     */
    Map register(RegisterDto registerDto);


    /**
     * 用户名/邮箱密码登录
     *
     * @param emailOrAccountNumber
     * @param password
     * @return
     */
    Map login(String emailOrAccountNumber, String password);


    /**
     * 验证用户是否报过名
     *
     * @param userId
     * @return
     */
    Map isApply(String userId);


    /**
     * 报名
     *
     * @param applyDto
     * @return
     */
    Map apply(ApplyDto applyDto);


    /**
     * 查询所有
     *
     * @param form
     * @return
     */
    Map searchAll(String form);


    /**
     * 查询指定
     *
     * @param form
     * @param name
     * @return
     */
    Map searchOne(String form,String name);


    /**
     * 插入国债
     *
     * @param stateBondsDto
     * @return
     */
    Map insertStateBonds(StateBondsDto stateBondsDto);


    /**
     * 插入企业债
     *
     * @param financialEnterpriseBondsDto
     * @return
     */
    Map insertFinancialEnterpriseBonds(FinancialEnterpriseBondsDto financialEnterpriseBondsDto);


    /**
     * 插入基金
     *
     * @param fundDto
     * @return
     */
    Map insertFund(FundDto fundDto);

}
