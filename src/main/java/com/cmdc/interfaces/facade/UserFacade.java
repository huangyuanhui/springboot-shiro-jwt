package com.cmdc.interfaces.facade;

import com.cmdc.application.service.UserService;
import com.cmdc.infrastructure.exception.CmdcException;
import com.cmdc.infrastructure.exception.ErrorEnum;
import com.cmdc.interfaces.dto.JsonResult;
import com.cmdc.interfaces.dto.request.CodeLoginDTO;
import com.cmdc.interfaces.dto.request.PassWordLoginDTO;
import com.cmdc.interfaces.dto.request.RegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author : wuwensheng
 * @date : 10:24 2020/7/1
 */
@RestController
@Slf4j
public class UserFacade {
    @Autowired
    private UserService userService;

    @RequiresRoles(value = {"admin","common"},logical = Logical.OR)
    @RequiresPermissions(value = {"resetPassword","queryMyUserInfo"},logical = Logical.OR)
    @GetMapping(value = "/v1.0/shiro/getUserPermission")
    public JsonResult getUserPermission() throws Exception {
        log.info("访问接口getUserPermission成功");
        return new JsonResult();
    }


    @RequiresRoles(value = {"admin"})
    @GetMapping(value = "/v1.0/shiro/getUser")
    public JsonResult getUser() throws Exception {
        log.info("访问getUser接口成功");
        return new JsonResult();
    }

    @PostMapping(value = "/user/passwordLogin",name = "用户密码登录")
    public JsonResult passwordLogin(@RequestBody @Valid  PassWordLoginDTO passWordLoginDTO){
        log.info("传递的请求参数:{}",passWordLoginDTO);
        JsonResult jsonResult;
        try {
           jsonResult=new JsonResult<>( userService.passWordLogin(passWordLoginDTO.getUserId(),passWordLoginDTO.getPassword()));
        }catch (CmdcException e){
            throw e;
        }catch (Exception e){
            log.info("错误信息:{}",e);
            if(e instanceof IncorrectCredentialsException){
                throw e;
            }
            throw new CmdcException(ErrorEnum.SERVER_ERROR);
        }
        return jsonResult;
    }

    //此为shiro开放端口
    @PostMapping(value = "/user/register",name = "用户注册")
    public JsonResult userRegister(@RequestBody @Valid RegisterDTO registerDTO){
        JsonResult jsonResult;
        try {
            userService.register(registerDTO.getUserId(),registerDTO.getUserName(),registerDTO.getPassword(),registerDTO.getRemark());
            jsonResult=new JsonResult();
        }catch (CmdcException e){
            throw e;
        }
         catch (Exception e){
            throw new CmdcException(ErrorEnum.SERVER_ERROR);
        }
        return jsonResult;
    }

  // 此为shiro的开放端口 为用户发送登录验证码
    @GetMapping(value = "/user/sendVerificationCode")
    public JsonResult<ErrorEnum> sendVerificationCode(String userId){
        JsonResult<ErrorEnum> jsonResult;
        if(StringUtils.isEmpty(userId)) throw new CmdcException(ErrorEnum.BAD_PARAM);
        try{
            userService.sendVerificationCode(userId);
            jsonResult=new JsonResult<ErrorEnum>();
        }catch (CmdcException e){ //如果是我们的异常 直接扔出去
            throw e;
        }catch (Exception e){ //如果不是我们期待的异常,返回服务器错误就好
            log.info("错误信息",e);
            throw new CmdcException(ErrorEnum.SERVER_ERROR);
        }
        return jsonResult;
    }

    @PostMapping(value = "/user/verificationCodeLogin",name = "用户验证码登录")
    public JsonResult verificationCodeLogin(@RequestBody @Valid CodeLoginDTO codeLoginDTO){
        JsonResult jsonResult ;
        try {
            jsonResult=new JsonResult<>(userService.verificationCodeLogin(codeLoginDTO.getUserId(),codeLoginDTO.getCode()));
        }catch (CmdcException e){
            throw e;
        }catch (Exception e){
            log.info("异常:{}",e);
            throw new CmdcException(ErrorEnum.SERVER_ERROR);
        }
        return jsonResult;
    }

}
