package com.cmdc.interfaces.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : wuwensheng
 * @date : 23:10 2020/7/1
 */
@Getter
@Setter
@ToString
public class RegisterDTO {
    //用户手机号
    @NotNull(message = "用户输入的手机号不可为空")
    @Size(max = 11,min = 11,message = "手机号必须为11位")
    private String userId;
    //用户输入的密码
    @NotBlank(message = "用户输入的注册密码不可为空")
    @Size(max = 8,min = 6,message = "用户输入的密码长度必须在6-8之间")
    private String password;
    //用户名称
    private String userName;
    //用户
    private String remark;
}
