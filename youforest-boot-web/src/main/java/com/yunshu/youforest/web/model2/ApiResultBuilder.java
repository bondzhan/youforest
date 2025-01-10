package com.yunshu.youforest.web.model2;

public interface ApiResultBuilder {
    ApiResult build(String code, String message,Object... params);
}
