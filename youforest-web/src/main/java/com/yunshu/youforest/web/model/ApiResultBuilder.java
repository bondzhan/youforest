package com.yunshu.youforest.web.model;

public interface ApiResultBuilder {
    ApiResult build(String code, String message,Object... params);
}
