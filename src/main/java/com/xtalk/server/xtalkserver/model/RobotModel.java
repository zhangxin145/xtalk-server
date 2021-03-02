package com.xtalk.server.xtalkserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xtalk.server.xtalkserver.entity.TaskEntity;
import com.xtalk.server.xtalkserver.entity.UsersEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/2/27 8:58 下午
 */
@Slf4j
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RobotModel extends UsersEntity {
    private List<TaskEntity> tasks;
    private String chatRoomNames;
}
