package com.yunshu.youforest.db.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Bond
 * @Date 2025/1/8
 * @Description
 */
@Data
@NoArgsConstructor
public class BaseTenantPo extends BasePo implements Serializable {

    @Schema(title = "租户ID", description = "租户的逻辑隔离", requiredMode = Schema.RequiredMode.REQUIRED, example = "10001")
    @TableField(value = "tenant_id", fill = FieldFill.INSERT)
    private Long tenantId;

}
