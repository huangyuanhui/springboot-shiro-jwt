package com.cmdc.interfaces.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : wuwensheng
 * @date : 15:33 2020/7/2
 */
@Getter
@Setter
@ToString
public class CodeLoginDTO {
    @NotBlank(message = "用户验证码登录传递的手机号不可为空")
    @Size(max = 11,min = 11,message = "传递的验证码登录手机号必须为11位")
    private String userId;
    @NotNull(message = "用户验证码登录传递的验证码不可为空")
    @Size(max = 6,min = 6,message = "传递的验证码登录验证码必须为6位")
    private String code;
}
