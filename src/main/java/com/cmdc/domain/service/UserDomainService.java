package com.cmdc.domain.service;

import com.cmdc.domain.entity.User;
import com.cmdc.domain.mapper.UserMapper;
import com.cmdc.domain.mapper.UserRoleMapper;
import com.cmdc.infrastructure.common.Constant;
import com.cmdc.infrastructure.enums.LoginEnum;
import com.cmdc.infrastructure.exception.CmdcException;
import com.cmdc.infrastructure.exception.ErrorEnum;
import com.cmdc.infrastructure.shirotoken.CustomizedToken;
import com.cmdc.infrastructure.util.CommonsUtils;
import com.cmdc.infrastructure.util.JwtUtil;
import com.cmdc.infrastructure.util.RedisUtil;
import com.cmdc.interfaces.dto.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author : wuwensheng
 * @date : 13:33 2020/7/1
 */
@Service
@Slf4j
public class UserDomainService {
    @Autowired
    private UserMapper userMapper;
    //todo 其实这里是需要发送事件的
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedisUtil redisUtil;

    public User selectByName(String userName) {
        return userMapper.selectByName(userName);
    }

    public User selectById(String userId) {
        return userMapper.selectById(userId);
    }

    public String passwordLogin(String userId, String passWord) {
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 校验userId是否为空
        if (StringUtils.isEmpty(userId)) throw new CmdcException(ErrorEnum.ACCOUNT_UNUSUAL);
        // 校验数据库中此user是否存在
        User user = this.selectById(userId);
        if (user == null) throw new CmdcException(ErrorEnum.ACCOUNT_UNUSUAL);
        // 制作CustomizedToken执行登录
        CustomizedToken customizedToken = new CustomizedToken(userId, passWord, LoginEnum.BY_PASSWORD.getLoginType());
        subject.login(customizedToken);
        // 若登陆成功返回相关token
        return JwtUtil.sign(user.getUserId(), Constant.TOKEN_SECRET);
    }

    public void register(String userId, String userName, String password, String remark) {
        // 首先检查此用户是否在数据库
        if (this.selectById(userId) != null) throw new CmdcException(500, "该手机号已经注册");
        // 制作用户密码,然后将用户插入user表中
        String encryptPassword = CommonsUtils.encryptPassword(password, String.valueOf(userId));
        log.info("加密之后的用户密码是:{}", encryptPassword);
        this.insetUser(User.getUser(userId, userName, encryptPassword, remark));
        // 增加用户角色中间表,注册最基本角色
        userRoleMapper.insert(userId, 200);
    }

    public void insetUser(User user) {
        userMapper.insertUser(user);
    }

    public void sendVerificationCode(String userId) {
        //根据userId查询这个用户
        if (StringUtils.isEmpty(this.selectById(userId))) throw new CmdcException(ErrorEnum.USER_NOT_EXISTS);
        //获取六位随机数
        int code = CommonsUtils.getCode();
        log.info("获取到的六位随机数是:{}", code);
        //将六位数加密
        String encryptPassword = CommonsUtils.encryptPassword(String.valueOf(code), userId);
        log.info("本次加密的密码是:{}", encryptPassword);
        //存储redis
        redisUtil.set(Constant.REDIS_LOGIN_CODE + userId, encryptPassword, Constant.CODE_EXPIRE_TIME);
    }

    public String verificationCodeLogin(String userId, String code) {
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 校验数据库中此user是否存在
        User user = this.selectById(userId);
        if (user == null) throw new CmdcException(ErrorEnum.ACCOUNT_UNUSUAL);
        // 制作CustomizedToken执行登录
        CustomizedToken customizedToken = new CustomizedToken(userId, code, LoginEnum.BY_CODE.getLoginType());
        subject.login(customizedToken);
        // 若登陆成功返回相关token
        String sign = JwtUtil.sign(user.getUserId(), Constant.TOKEN_SECRET);
        //删除redis中的验证码
        redisUtil.del(Constant.REDIS_LOGIN_CODE + userId);
        return sign;
    }


}
