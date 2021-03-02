package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.PermissionEntity;
import com.xtalk.server.xtalkserver.model.PermissionNodeModel;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/21 8:42 下午
 */
public interface PermissionService {
    PermissionEntity add(PermissionEntity entity);

    PermissionEntity update(PermissionEntity entity);

    PermissionEntity del(Long id);

    Optional<PermissionEntity> getById(Long id);

    List<PermissionNodeModel> getResult();

    List<PermissionNodeModel> getChildrens(PermissionNodeModel root, List<PermissionNodeModel> all);
}
