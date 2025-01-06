package com.yunshu.youforest.web.model;

import lombok.Getter;

/**
 * @Author: bond
 * @Description 系统基础异常
 * @Date: 2025/1/6 14:06
 */
@Getter
public class SystemException extends RuntimeException {
    /**
     * 异常对应的错误类型
     */
    private final ApiErrorType errorType;

    /**
     * 默认是系统异常
     */
    public SystemException() {
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }

    /**
     * @param errorType 错误类型
     */
    public SystemException(ApiErrorType errorType) {
        this.errorType = errorType;
    }

    /**
     * @param errorType 错误类型
     * @param message   错误信息
     */
    public SystemException(ApiErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * @param errorType 错误类型
     * @param message   错误信息
     * @param cause     异常
     */
    public SystemException(ApiErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
