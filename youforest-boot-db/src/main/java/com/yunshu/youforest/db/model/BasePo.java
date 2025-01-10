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
public class BasePo extends Model<BasePo> implements Serializable {

    @Schema(title = "创建人", description = "创建该数据记录人的唯一ID，默认为system", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private String createdId;

    @Schema(title = "创建时间", description = "创建该数据记录的时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-01-01 21:00:00")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @Schema(title = "更新人", description = "更新该数据记录人的唯一ID，默认为system", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    @TableField(value = "updated_id",fill = FieldFill.INSERT_UPDATE)
    private String updatedId;

    @Schema(title = "更新时间", description = "更新该数据记录的时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-01-1 21:00:00")
    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
