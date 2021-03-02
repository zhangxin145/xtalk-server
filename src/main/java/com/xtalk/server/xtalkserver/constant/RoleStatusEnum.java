package com.xtalk.server.xtalkserver.constant;

/**
 * @author xin.z
 * @date 2021/1/22 4:57 下午
 */
public enum RoleStatusEnum {
    ROLE_STATUS_NORMAL(0, "正常"),
    ROLE_STATUS_FORBIDDEN(1, "禁用");

    private final Integer status;
    private final String name;

    public Integer getStatus() {
        return status;
    }

    RoleStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
