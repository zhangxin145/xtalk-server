package com.xtalk.server.xtalkserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xin.z
 * @date 2021/1/26 10:04 下午
 */
@Slf4j
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserQueryVo {

    private Long id;

    private String phone;

    private Long vip;

    private String nickName;

    private Integer pageNumber;

    private Integer pageSize;


}
