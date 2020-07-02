package com.cmdc.infrastructure.enums;

/**
 * @author : wuwensheng
 * @date : 13:35 2020/7/1
 */
public enum  LoginEnum {
    BY_PASSWORD("Password"),
    BY_CODE("Code")
    ;

    public String getLoginType() {
        return loginType;
    }

    private String loginType;

    LoginEnum(String loginType) {
        this.loginType = loginType;
    }
}
