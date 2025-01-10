package com.yunshu.youforest.web.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author lucoo
 * @Date 2021/6/26 15:44
 **/
@Getter
@Setter
public class LoginContext {
    private String userId;
    private String userName;
    private Long tenantId;
}
