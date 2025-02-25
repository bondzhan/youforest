package com.yunshu.youforest.web.model2;

import com.yunshu.youforest.web.model2.page.PageResponse;
import com.yunshu.youforest.web.model2.page.PageList;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @Description TODO
 * @Author lucoo
 * @Date 2021/6/23 18:28
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** 响应成功码 */
    private Boolean success = false;
    private String code;
    private String msg;
    private String traceId;
    private T data;

    public static <T> ApiResult<T> success() {
        ApiResult<T> apiResult = ApiResultBuilderManager.build(YouforestErrorCode.SUCCESS.getModularCode(), YouforestErrorCode.SUCCESS.getMsg());
        apiResult.setSuccess(true);
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> response = success();
        response.setData(data);
        return response;
    }

    public static <T, R extends PageResponse<T>> ApiResult<R> successPage(List<T> data) {
        ApiResult<R> response = success();
        if (data instanceof PageList) {
            response.setData((R) new PageResponse((PageList) data));
        }
        return response;
    }

    public static <T> ApiResult<T> error(String code, String message, Object... params) {
        ApiResult<T> apiResult = ApiResultBuilderManager.build(code, message, params);
        apiResult.setSuccess(false);
        return apiResult;
    }

    public static <T> ApiResult<T> error() {
        return error(YouforestErrorCode.SYSTEM_ERROR);
    }

    public static <T> ApiResult<T> error(String message) {
        return error(YouforestErrorCode.SYSTEM_ERROR.getCode(), message);
    }

    public static <T> ApiResult<T> error(ApiErrorCode error, Object... params) {
        return error(error.getCode(), error.getMsg(), params);
    }

    public static <T> ApiResult<T> error(String message, ApiErrorCode error, Object... params) {
        return error(error.getCode(), message, params);
    }

    @Transient
    public Boolean isOk() {
        if (success == null) {
            return false;
        }
        return success;
    }

    @Transient
    public Boolean isNotOk() {
        return !this.isOk();
    }

    @Transient
    public Boolean isSucceed() {
        return success != null && success && data != null;
    }

    @Transient
    public Boolean isNotSucceed() {
        return !isSucceed();
    }
}
