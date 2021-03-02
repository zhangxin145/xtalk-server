package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.UserPermissionEntity;
import com.xtalk.server.xtalkserver.entity.VipEntity;
import com.xtalk.server.xtalkserver.model.VipEditModel;
import com.xtalk.server.xtalkserver.model.VipModel;
import com.xtalk.server.xtalkserver.model.VipQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/28 7:01 下午
 */
public interface VipService {
    Page<VipModel> getVipPage(VipQueryVo vo);

    UserPermissionEntity addPermission(UserPermissionEntity entity);

    UserPermissionEntity updatePermission(UserPermissionEntity entity);

    UserPermissionEntity delPermission(Long id);

    Optional<List<UserPermissionEntity>> getPermission();

    VipEntity addVip(VipEditModel model);

    VipEntity updateVip(VipEditModel model);

    VipEntity delVip(Long id);

    List<VipEntity> updateVipStatus(List<Long> vipIds, Integer status);

    Optional<List<VipEntity>> getAllVip();
}
