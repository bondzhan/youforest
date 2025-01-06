package com.yunshu.youforest.web.interceptor;

import com.yunshu.youforest.web.model.ApiResult;
import com.yunshu.youforest.web.model.SystemErrorType;
import com.yunshu.youforest.web.model.SystemException;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @Author: bond
 * @Description 默认全局异常处理类
 * @Date: 2025/1/6 14:10

 */
@Slf4j
@Order
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ApiResult missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("missing servlet request parameter exception:{}", ex.getMessage());
        return ApiResult.fail(SystemErrorType.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResult argumentInvalidException(MethodArgumentNotValidException ex) {
        log.warn("service exception:{}", ex.getMessage());
        return ApiResult.fail(SystemErrorType.ARGUMENT_NOT_VALID, ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ApiResult httpMessageConvertException(HttpMessageNotReadableException ex) {
        log.warn("http message convert exception:{}", ex.getMessage());
        return ApiResult.fail(SystemErrorType.ARGUMENT_NOT_VALID, "数据解析错误：" + ex.getMessage());
    }

    @ExceptionHandler(value = {MultipartException.class})
    public ApiResult uploadFileLimitException(MultipartException ex) {
        log.warn("upload file size limit:{}", ex.getMessage());
        return ApiResult.fail(SystemErrorType.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiResult notSupportedMethodException(HttpRequestMethodNotSupportedException ex) {
        log.warn("http request method not supported exception {}", ex.getMessage());
        return ApiResult.fail(SystemErrorType.METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResult noHandlerFoundException(ServletException ex) {
        log.warn("No static resource exception:{}", ex.getMessage());
        return ApiResult.fail(SystemErrorType.RESOURCE_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiResult notSupportedMethodException(HttpMediaTypeNotSupportedException ex) {
        log.warn("http request method not supported exception {}", ex.getMessage());
        return ApiResult.fail(SystemErrorType.METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(value = {SystemException.class})
    public ApiResult baseException(SystemException ex) {
        log.error("base exception:{}", ex.getMessage());
        return ApiResult.fail(ex.getErrorType());
    }

    @ExceptionHandler(value = {Exception.class, Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult exception(Throwable ex) {
        log.error("exception: ", ex);
        return ApiResult.fail();
    }
}