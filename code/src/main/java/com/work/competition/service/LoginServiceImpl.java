package com.work.competition.service;

import com.work.competition.dao.ILoginService;
import com.work.competition.dto.*;
import com.work.competition.mapper.TbUserInfoMapper;
import com.work.competition.mapper.TbUserMapper;
import com.work.competition.mapper.TbStateBondsMapper;
import com.work.competition.mapper.TbFinancialEnterpriseBondsMapper;
import com.work.competition.mapper.TbFundMapper;
import com.work.competition.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;
    @Autowired
    private TbStateBondsMapper tbStateBondsMapper;
    @Autowired
    private TbFinancialEnterpriseBondsMapper tbFinancialEnterpriseBondsMapper;
    @Autowired
    private TbFundMapper tbFundMapper;

    /**
     * 用户名邮箱密码注册
     *
     * @param registerDto
     * @return
     */
    @Override
    public Map register(RegisterDto registerDto) {
        Map map = new HashMap();
        //1.验证用户名是否存在
        Example exampleAcc = new Example(TbUser.class);
        exampleAcc.createCriteria().andEqualTo("accountNumber", registerDto.getAccountNumber());
        List<TbUser> tbUserAccs = tbUserMapper.selectByExample(exampleAcc);
        if (tbUserAccs.size() > 0) {
            map.put("code", -1);
            map.put("msg", "用户名已存在");
            return map;
        }
        //2.验证邮箱是否存在
        Example exampleEmail = new Example(TbUser.class);
        exampleEmail.createCriteria().andEqualTo("email", registerDto.getEmail());
        List<TbUser> tbUserEmails = tbUserMapper.selectByExample(exampleEmail);
        if (tbUserEmails.size() > 0) {
            map.put("code", -2);
            map.put("msg", "邮箱已存在");
            return map;
        }
        //3.插入数据
        TbUser tbUser = new TbUser();
        tbUser.setAccountNumber(registerDto.getAccountNumber());
        tbUser.setEmail(registerDto.getEmail());
        tbUser.setPassword(registerDto.getPassword());
        tbUser.setCreateAt(new Date());
        tbUserMapper.insert(tbUser);
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", tbUser);

        return map;
    }

    /**
     * 用户名/邮箱密码登录
     *
     * @param emailOrAccountNumber
     * @param password
     * @return
     */
    @Override
    public Map login(String emailOrAccountNumber, String password) {
        Map map = new HashMap();
        //1.验证用户名邮箱是否进来
        List<TbUser> tbUsers = tbUserMapper.selectByEmOrAcc(emailOrAccountNumber);
        if (tbUsers.size() == 0) {
            map.put("code", -1);
            map.put("msg", "用户名或邮箱不存在");
            return map;
        }
        //2.验证密码是否正确
        TbUser tbUser = tbUserMapper.selectByEmOrAccAndPa(emailOrAccountNumber, password);
        if (tbUser == null) {
            map.put("code", -2);
            map.put("msg", "密码错误");
            return map;
        }
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", tbUser);
        return map;
    }

    /**
     * 验证用户是否报过名
     *
     * @param userId
     * @return
     */
    @Override
    public Map isApply(String userId) {
        Map map = new HashMap();
        Example example = new Example(TbUserInfo.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<TbUserInfo> tbUserInfos = tbUserInfoMapper.selectByExample(example);
        if (tbUserInfos.size() == 0) {
            map.put("code", "-1");
            map.put("msg", "未报名");
            return map;
        }
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", tbUserInfos.get(0));
        return map;
    }

    /**
     * 报名
     *
     * @param applyDto
     * @return
     */
    @Override
    public Map apply(ApplyDto applyDto) {
        Map map = new HashMap();
        Example example = new Example(TbUserInfo.class);
        example.createCriteria().andEqualTo("userId", applyDto.getUserId());
        List<TbUserInfo> tbUserInfos = tbUserInfoMapper.selectByExample(example);
        if (tbUserInfos.size() > 0) {
            map.put("code", "-1");
            map.put("msg", "已报名");
            map.put("data", tbUserInfos.get(0));
            return map;
        }

        TbUserInfo tbUserInfo = new TbUserInfo();
        tbUserInfo.setUserId(applyDto.getUserId());
        tbUserInfo.setUsername(applyDto.getUsername());
        tbUserInfo.setSchool(applyDto.getSchool());

        tbUserInfo.setPhone(applyDto.getPhone());
        tbUserInfo.setApplyTime(new Date());
        tbUserInfoMapper.insert(tbUserInfo);
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", tbUserInfo);
        return map;
    }

    /**
     * 查询所有
     *
     * @param
     * @return
     */
    @Override
    public Map searchAll(String form) {
        Map map = new HashMap();
        if(form.equals("1")){
            List<TbStateBonds> tbStateBonds = tbStateBondsMapper.searchAll();
            map.put("code", 200);
            map.put("msg", "success");
            map.put("data", tbStateBonds);
        }else if(form.equals("2")){
            List<TbFinancialEnterpriseBonds> tbFinancialEnterpriseBonds = tbFinancialEnterpriseBondsMapper.searchAll();
            map.put("code", 200);
            map.put("msg", "success");
            map.put("data", tbFinancialEnterpriseBonds);
        }else if(form.equals("3")){
            List<TbFund> tbFunds = tbFundMapper.searchAll();
            map.put("code", 200);
            map.put("msg", "success");
            map.put("data", tbFunds);
        }else{
            map.put("code", -1);
            map.put("msg", "选择表单错误");
        }
        return map;
    }

    /**
     * 查询指定
     *
     * @param
     * @return
     */
    @Override
    public Map searchOne(String form,String name) {
        Map map = new HashMap();
        if(form.equals("1")){
            TbStateBonds tbStateBonds = tbStateBondsMapper.searchOne(name);
            if(tbStateBonds == null)
            {
                map.put("code", -1);
                map.put("msg", "不存在此债券");
            }else{
                map.put("code", 200);
                map.put("msg", "success");
                map.put("data", tbStateBonds);
            }
        }else if(form.equals("2")){
            TbFinancialEnterpriseBonds tbFinancialEnterpriseBonds = tbFinancialEnterpriseBondsMapper.searchOne(name);
            if(tbFinancialEnterpriseBonds == null)
            {
                map.put("code", -1);
                map.put("msg", "不存在此债券");
            }else{
                map.put("code", 200);
                map.put("msg", "success");
                map.put("data", tbFinancialEnterpriseBonds);
            }
        }else if(form.equals("3")){
            TbFund tbFund = tbFundMapper.searchOne(name);
            if(tbFund == null)
            {
                map.put("code", -1);
                map.put("msg", "不存在此债券");
            }else {
                map.put("code", 200);
                map.put("msg", "success");
                map.put("data", tbFund);
            }
        }else{
            map.put("code", -1);
            map.put("msg", "选择表单错误");
        }
        return map;
    }


    /**
     * 插入国债
     *
     * @param stateBondsDto
     * @return
     */
    @Override
    public Map insertStateBonds(StateBondsDto stateBondsDto) {
        Map map = new HashMap();
        Example example = new Example(TbStateBonds.class);
        example.createCriteria().andEqualTo("bondName", stateBondsDto.getBondName());
        List<TbStateBonds> tbStateBonds = tbStateBondsMapper.selectByExample(example);
        if (tbStateBonds.size() > 0) {
            map.put("code", "-1");
            map.put("msg", "已存在");
            map.put("data", tbStateBonds.get(0));
            return map;
        }

        TbStateBonds tbStateBonds1 = new TbStateBonds();
        tbStateBonds1.setBondName(stateBondsDto.getBondName());
        tbStateBonds1.setNation(stateBondsDto.getNation());
        tbStateBonds1.setInterest(stateBondsDto.getInterest());
        tbStateBonds1.setPrice(stateBondsDto.getPrice());
        tbStateBonds1.setTime(stateBondsDto.getTime());

        System.out.println(tbStateBonds1);
        System.out.println(stateBondsDto);
        System.out.println(stateBondsDto.getInterest());

        tbStateBondsMapper.insert(tbStateBonds1);
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", tbStateBonds1);
        return map;
    }

    /**
     * 插入企业债
     *
     * @param financialEnterpriseBondsDto
     * @return
     */
    @Override
    public Map insertFinancialEnterpriseBonds(FinancialEnterpriseBondsDto financialEnterpriseBondsDto) {
        Map map = new HashMap();
        Example example = new Example(TbFinancialEnterpriseBonds.class);
        example.createCriteria().andEqualTo("bondName", financialEnterpriseBondsDto.getBondName());
        List<TbFinancialEnterpriseBonds> tbFinancialEnterpriseBonds = tbFinancialEnterpriseBondsMapper.selectByExample(example);
        if (tbFinancialEnterpriseBonds.size() > 0) {
            map.put("code", "-1");
            map.put("msg", "已存在");
            map.put("data", tbFinancialEnterpriseBonds.get(0));
            return map;
        }

        TbFinancialEnterpriseBonds tbFinancialEnterpriseBonds1 = new TbFinancialEnterpriseBonds();
        tbFinancialEnterpriseBonds1.setBondName(financialEnterpriseBondsDto.getBondName());
        tbFinancialEnterpriseBonds1.setEnterprise(financialEnterpriseBondsDto.getEnterprise());
        tbFinancialEnterpriseBonds1.setInterest(financialEnterpriseBondsDto.getInterest());
        tbFinancialEnterpriseBonds1.setPrice(financialEnterpriseBondsDto.getPrice());
        tbFinancialEnterpriseBonds1.setTime(financialEnterpriseBondsDto.getTime());

        tbFinancialEnterpriseBondsMapper.insert(tbFinancialEnterpriseBonds1);
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", tbFinancialEnterpriseBonds1);
        return map;
    }

    /**
     * 插入基金
     *
     * @param fundDto
     * @return
     */
    @Override
    public Map insertFund(FundDto fundDto) {
        Map map = new HashMap();
        Example example = new Example(TbFund.class);
        example.createCriteria().andEqualTo("fundName", fundDto.getFundName());
        List<TbFund> tbFunds = tbFundMapper.selectByExample(example);
        if (tbFunds.size() > 0) {
            map.put("code", "-1");
            map.put("msg", "已存在");
            map.put("data", tbFunds.get(0));
            return map;
        }

        TbFund tbFund = new TbFund();
        tbFund.setFundName(fundDto.getFundName());
        tbFund.setCurrentValue(fundDto.getCurrentValue());
        tbFund.setFundShares(fundDto.getFundShares());
        tbFund.setRedemptionFee(fundDto.getRedemptionFee());
        tbFund.setSubscriptionAmount(fundDto.getSubscriptionAmount());
        tbFund.setAgent(fundDto.getAgent());

        tbFundMapper.insert(tbFund);
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", tbFund);
        return map;
    }


}
