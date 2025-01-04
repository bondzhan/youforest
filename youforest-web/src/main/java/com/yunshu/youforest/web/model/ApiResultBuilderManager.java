package com.yunshu.youforest.web.model;

import java.util.ServiceLoader;

public class ApiResultBuilderManager {
    private static ApiResultBuilder apiResultBuilder;
    static {
        if(apiResultBuilder==null){
            synchronized (ApiResultBuilderManager.class){
                if(apiResultBuilder==null){
                    ServiceLoader<ApiResultBuilder> apiResultBuilders = ServiceLoader.load(ApiResultBuilder.class);
                    for (ApiResultBuilder api : apiResultBuilders) {
                        apiResultBuilder = api;
                        if(api.getClass().isAnnotationPresent(ApiResultBuilderPrimary.class)){
                            break;
                        }
                    }
                }
                if(apiResultBuilder==null){
                    apiResultBuilder=new DefaultApiResultBuilder();
                }
            }
        }
    }
    public static ApiResult build(String code,String msg,Object... params){
        return apiResultBuilder.build(code,msg,params);
    }
}
