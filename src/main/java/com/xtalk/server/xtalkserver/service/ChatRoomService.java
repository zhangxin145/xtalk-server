package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.ChatroomsEntity;
import com.xtalk.server.xtalkserver.model.ChatRoomModel;
import com.xtalk.server.xtalkserver.model.ChatRoomQueryVo;
import com.xtalk.server.xtalkserver.model.ChatRoomVo;
import com.xtalk.server.xtalkserver.model.MuteModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/31 1:41 下午
 */
public interface ChatRoomService {
    Page<ChatRoomModel> getPageResult(ChatRoomQueryVo queryVo);

    ChatroomsEntity add(ChatRoomVo chatRoomVo);

    ChatroomsEntity update(ChatRoomVo chatRoomVo);

    ChatroomsEntity del(Long id);

    ChatRoomModel getById(Long id);

    ChatroomsEntity mute(Long chatRoomId,Long isMute);

    ChatroomsEntity muteMember(MuteModel muteModel);

    ChatroomsEntity addMember(Long chatRoomId, List<Long> usrIds);

    ChatroomsEntity delMember(Long chatRoomId, List<Long> usrIds);
}
