package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.AdminEntity;
import com.xtalk.server.xtalkserver.model.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/21 8:43 下午
 */
@Service
public interface AdminUserService {
    AdminUserLoginModel loginByPwd(String account, String password) throws Exception;

    AdminEntity add(AdminVo adminVo);

    AdminEntity update(AdminVo adminVo);

    AdminEntity del(Long id);

    List<AdminEntity> changeStatus(AdminChangeStatusModel model);

    AdminModel getById(Long id);

    AdminEntity setPassById(Long id, String pass, String rePass);

    Page<List<AdminModel>> getResultPage(AdminQueryVo queryVo);

    List<PermissionNodeModel> getPermissionByAdminId(Long adminId);
}
