package com.xtalk.server.xtalkserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/23 4:32 下午
 */
@Slf4j
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminQueryVo {
    @ApiModelProperty("页数")
    private Integer pageNumber;
    @ApiModelProperty("每页大小")
    private Integer pageSize;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("角色状态")
    private Integer status;
    @ApiModelProperty("创建时间begin")
    private String createTimeBegin;
    @ApiModelProperty("创建时间end")
    private String createTimeEnd;
    @ApiModelProperty("角色ids")
    private List<Long> roleIds;
}
