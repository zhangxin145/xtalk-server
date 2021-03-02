package com.xtalk.server.xtalkserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xtalk.server.xtalkserver.entity.ChatroomsEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/31 1:45 下午
 */
@Slf4j
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatRoomVo extends ChatroomsEntity {

    // 群主
    private Long owner;

    // 管理员
    private List<Long> manager;

    // 关键字
    private List<String> keys;

    // 机器人
    private List<Long> robots;

}
