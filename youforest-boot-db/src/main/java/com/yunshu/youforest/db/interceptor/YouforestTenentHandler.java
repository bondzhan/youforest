package com.yunshu.youforest.db.interceptor;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.yunshu.youforest.db.properties.YouforestTenantProperties;
import com.yunshu.youforest.web.common.LoginContextHolder;
import com.yunshu.youforest.web.model.SystemErrorType;
import com.yunshu.youforest.web.model.SystemException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * @Author Bond
 * @Date 2025/1/8
 * @Description 多租户配置
 */
public class YouforestTenentHandler implements TenantLineHandler {
    private YouforestTenantProperties tenantProperties;

    public YouforestTenentHandler(YouforestTenantProperties tenantProperties) {
        this.tenantProperties = tenantProperties;
    }
    @Override
    public Expression getTenantId() {
        if(LoginContextHolder.currentTenantId() == null){
            throw new SystemException(SystemErrorType.TENANT_ID_NOT_EXIT);
        }
        // 返回租户ID的表达式
        return new LongValue(LoginContextHolder.currentTenantId());
    }

    @Override
    public String getTenantIdColumn() {
        // 返回租户ID列名
        return tenantProperties.getTenantIdColumnName();
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return tenantProperties.getIgnoreTables().stream().anyMatch(
                ignoreTable -> ignoreTable.equalsIgnoreCase(tableName.replaceAll("`",""))
        );
    }
}
