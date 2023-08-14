package com.knife.config;

/**
 * 权限汇总
 * @Author geey
 * @Date 2023/8/14 17:14
 * @Version 1.0
 */
public enum PermissionEnum {
    EXAMPLE(100,"权限示例")
    ;
    private Integer code;
    private String description;

    PermissionEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
