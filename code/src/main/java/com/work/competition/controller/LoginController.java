package com.work.competition.controller;

import com.work.competition.dao.ILoginService;
import com.work.competition.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    /**
     * 用户名邮箱密码注册
     *
     * @param registerDto
     * @return
     */
    @PostMapping("/register")
    public Map register(@RequestBody RegisterDto registerDto) {
        return iLoginService.register(registerDto);
    }


    /**
     * 用户名/邮箱密码登录
     *
     * @param emailOrAccountNumber
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Map login(@RequestParam("emailOrAccountNumber") String emailOrAccountNumber,
                     @RequestParam("password") String password) {
        return iLoginService.login(emailOrAccountNumber, password);
    }


    /**
     * 验证用户是否报过名
     *
     * @param userId
     * @return
     */
    @GetMapping("/isApply")
    public Map isApply(@RequestParam("userId") String userId) {
        return iLoginService.isApply(userId);
    }


    /**
     * 报名
     *
     * @param applyDto
     * @return
     */
    @PostMapping("/apply")
    public Map apply(@RequestBody ApplyDto applyDto) {
        return iLoginService.apply(applyDto);
    }


    /**
     * 查询所有
     *
     * @param form
     * @return
     */
    @GetMapping("/searchAll")
    public Map searchAll(@RequestParam("form") String form) {
        return iLoginService.searchAll(form);
    }


    /**
     * 查询指定
     *
     * @param form
     * @param name
     * @return
     */
    @GetMapping("/searchOne")
    public Map searchONE(@RequestParam("form") String form,@RequestParam("name") String name) {
        return iLoginService.searchOne(form,name);
    }


    /**
     * 插入国债
     *
     * @param stateBondsDto
     * @return
     */
    @PostMapping("/insertStateBonds")
    public Map insertStateBonds(@RequestBody StateBondsDto stateBondsDto) {
        return iLoginService.insertStateBonds(stateBondsDto);
    }


    /**
     * 插入企业债
     *
     * @param financialEnterpriseBondsDto
     * @return
     */
    @PostMapping("/insertFinancialEnterpriseBonds")
    public Map insertFinancialEnterpriseBonds(@RequestBody FinancialEnterpriseBondsDto financialEnterpriseBondsDto) {
        return iLoginService.insertFinancialEnterpriseBonds(financialEnterpriseBondsDto);
    }


    /**
     * 插入基金
     *
     * @param fundDto
     * @return
     */
    @PostMapping("/insertFund")
    public Map insertFund(@RequestBody FundDto fundDto) {
        return iLoginService.insertFund(fundDto);
    }

}
