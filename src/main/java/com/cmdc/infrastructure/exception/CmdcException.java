package com.cmdc.infrastructure.exception;

import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
public class CmdcException extends RuntimeException {
    private int code;
    private String message;

    public CmdcException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMsg();
    }

    public CmdcException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}