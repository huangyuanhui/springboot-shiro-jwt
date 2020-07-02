package com.cmdc.interfaces.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : wuwensheng
 * @date : 13:27 2020/7/1
 */
@Getter
@Setter
@ToString
public class PassWordLoginDTO {
    @NotNull(message = "用户密码登录传递的id不能为空")
    private String userId;
    @NotBlank(message = "用户密码登录传递的密码名称不能为空")
    private String password;
}
