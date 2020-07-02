package com.cmdc.infrastructure.exception;


import com.cmdc.interfaces.dto.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : wuwensheng
 * @date : 15:54 2020/5/29
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 拦截业务异常，返回业务异常信息
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(CmdcException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(CmdcException exception) {
        int code = exception.getCode();
        String message = exception.getMessage();
        return new JsonResult(code, message);
    }


    @ExceptionHandler
    @ResponseBody
    public JsonResult<ErrorEnum> ErrorHandler(AuthorizationException e) {
        log.error("权限校验失败！", e);
        return new JsonResult<>(ErrorEnum.NO_AUTH.getCode(),ErrorEnum.NO_AUTH.getMsg());
    }


    @ExceptionHandler
    @ResponseBody
    public JsonResult ErrorHandler(AuthenticationException e) {
        log.error("用户名或密码错误,用户登录失败！", e);
        return new JsonResult<>(ErrorEnum.ERROR_ACCOUNT.getCode(),ErrorEnum.ERROR_ACCOUNT.getMsg());
    }

}
