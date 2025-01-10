package com.yunshu.youforest.db.properties;

import com.yunshu.youforest.web.properties.AbstractProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mybatis-plus")
@Getter
@Setter
public class YouforestMybatisPlusProperties extends AbstractProperties {
    public static YouforestMybatisPlusProperties getConfig(){
        return getConfig(YouforestMybatisPlusProperties.class);
    }
    private GlobalConfig globalConfig = new GlobalConfig();

    private pagination pagination = new pagination();

    // GlobalConfig 类
    @Getter
    @Setter
    public static class GlobalConfig {

        private SqlLog sqlLog = new SqlLog();
        private SqlSafe sqlSafe = new SqlSafe();
        private AutoFillField autoFillField = new AutoFillField();
    }

    // SqlLog 类
    @Getter
    @Setter
    public static class SqlLog {
        private boolean enabled = true;
    }

    // SqlSafe 类
    @Getter
    @Setter
    public static class SqlSafe {
        private boolean enabled = false;
    }

    // AutoFillField 类
    @Getter
    @Setter
    public static class AutoFillField {
        private boolean enabled = true;
        private String createId = "create_id";
        private String createName = "create_name";
        private String createTime = "create_time";
        private String modifyId = "modify_id";
        private String modifyName = "modify_name";
        private String modifyTime = "modify_time";
        private String deleted = "deleted";
    }

    @Getter
    @Setter
    public static class pagination {
        private boolean enabled = true;
        private String dbType = "mysql";
    }
}
