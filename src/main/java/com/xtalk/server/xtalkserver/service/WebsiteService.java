package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.FriendsterWebsiteEntity;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/20 5:04 下午
 */
public interface WebsiteService {
    List<FriendsterWebsiteEntity> get(FriendsterWebsiteEntity entity);

    FriendsterWebsiteEntity del(long id);

    FriendsterWebsiteEntity update(FriendsterWebsiteEntity entity);

    FriendsterWebsiteEntity create(FriendsterWebsiteEntity entity);
}
