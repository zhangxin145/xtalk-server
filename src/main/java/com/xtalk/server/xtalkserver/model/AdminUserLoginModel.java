package com.xtalk.server.xtalkserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xtalk.server.xtalkserver.entity.AdminEntity;
import com.xtalk.server.xtalkserver.entity.PermissionEntity;
import com.xtalk.server.xtalkserver.entity.RoleEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/22 8:44 下午
 */
@Slf4j
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserLoginModel {
    @ApiModelProperty("User")
    private AdminEntity admin;
    @ApiModelProperty("Token")
    private UserAuthModel token;

    private List<RoleEntity> roles;

    private List<PermissionEntity> perminssion;
}
