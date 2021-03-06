package com.cmdc.application.service;

import org.springframework.transaction.annotation.Transactional;


/**
 * @author : wuwensheng
 * @date : 13:26 2020/7/1
 */
public interface UserService {
    String passWordLogin(String userId,String passWord);

    void register( String userId, String userName,String password, String remark);

    void sendVerificationCode(String userId);

    String verificationCodeLogin(String userId, String code);
}
