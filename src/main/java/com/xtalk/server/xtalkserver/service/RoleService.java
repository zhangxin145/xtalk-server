package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.RoleEntity;
import com.xtalk.server.xtalkserver.model.RoleChangeStatusModel;
import com.xtalk.server.xtalkserver.model.RoleEditModel;
import com.xtalk.server.xtalkserver.model.RoleModel;
import com.xtalk.server.xtalkserver.model.RoleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/21 8:44 下午
 */
public interface RoleService {
    Page<RoleModel> getResultPage(RoleQueryVo queryVo);

    RoleEntity add(RoleEntity entity);

    RoleEntity update(RoleEntity entity);

    RoleEntity del(Long id);

    RoleEntity editRole(RoleEditModel model);

    List<RoleEntity> changeStatus(RoleChangeStatusModel model);

    RoleModel getById(Long id);

    List<RoleEntity> getAllRole(Integer status);
}
