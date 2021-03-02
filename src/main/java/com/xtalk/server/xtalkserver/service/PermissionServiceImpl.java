package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.PermissionEntity;
import com.xtalk.server.xtalkserver.model.PermissionNodeModel;
import com.xtalk.server.xtalkserver.repository.PermissionRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xin.z
 * @date 2021/1/21 8:42 下午
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    SecurityAuditor securityAuditor;

    @Override
    public PermissionEntity add(PermissionEntity entity) {
        checkParam(entity);
        Optional<List<PermissionEntity>> permission = permissionRepository.findByCodeAndDeleted(entity.getCode(), 0);
        permission.ifPresent(a -> {
            if (a.size() > 0) {
                throw new IllegalArgumentException("编码已存在，请重新输入");
            }
        });
        Assert.isTrue(entity.getType() != null || entity.getType() > 0,
                "类型不能为空");
        Long pid = entity.getPid();
        pid = (pid == null || pid < 0) ? 0 : pid;
        if (pid > 0) {
            permissionRepository.findById(pid).orElseGet(() -> {
                throw new IllegalArgumentException("pid 有误");
            });
        }
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        entity.setPid(pid);
        entity.setUri("");
        entity.setCid(currentAdminId);
        entity.setUid(currentAdminId);
        entity.setDeleted(0);
        entity.setCtime(new Date());
        entity.setUtime(new Date());
        return permissionRepository.save(entity);
    }

    private void checkParam(PermissionEntity entity) {
        Assert.isTrue(StringUtils.isNotBlank(entity.getName()),
                "菜单名称不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getCode()),
                "编码不能为空");

    }

    @Override
    public PermissionEntity update(PermissionEntity entity) {
        Assert.isTrue(entity.getId() != null || entity.getId() > 0,
                "id不能为空");
        checkParam(entity);
        PermissionEntity update = permissionRepository.findByIdAndDeleted(entity.getId(), 0)
                .orElseGet(() -> {
                    throw new IllegalArgumentException("id 有误");
                });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        Optional<List<PermissionEntity>> permission = permissionRepository.findByCodeAndDeleted(entity.getCode(), 0);
        permission.ifPresent(a -> {
            if (a.size() > 0) {
                Long id = a.get(0).getId();
                if (id != entity.getId()) {
                    throw new IllegalArgumentException("编码已存在，请重新输入");
                }

            }
        });
        Long sort = entity.getSort();
        if (sort != null) {

        }
        update.setSort(sort);
        update.setName(entity.getName());
        update.setCode(entity.getCode());
        update.setUid(currentAdminId);
        update.setUtime(new Date());
        return permissionRepository.save(update);
    }


    @Override
    public PermissionEntity del(Long id) {
        PermissionEntity update = permissionRepository.findByIdAndDeleted(id, 0)
                .orElseGet(() -> {
                    throw new IllegalArgumentException("id有误/该菜单已被删除");
                });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        update.setUtime(new Date());
        update.setUid(currentAdminId);
        update.setDeleted(1);
        //todo 暂时不级连删除子权限
        return permissionRepository.save(update);
    }


    @Override
    public Optional<PermissionEntity> getById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public List<PermissionNodeModel> getResult() {
        Optional<List<PermissionEntity>> result = permissionRepository.findByDeleted(0);
        if (!result.isPresent()) {
            return null;
        }
        List<PermissionEntity> allResult = result.get();
        if (allResult.size() > 0) {
            List<PermissionNodeModel> all = allResult.stream()
                    .map(p -> {
                        PermissionNodeModel model = new PermissionNodeModel();
                        BeanUtils.copyProperties(p, model);
                        return model;
                    }).collect(Collectors.toList());

            List<PermissionNodeModel> parents = all.stream().filter(p -> p.getPid() == 0)
                    .map(
                            (m) -> {
                                m.setChildList(getChildrens(m, all));
                                return m;
                            }
                    ).collect(Collectors.toList());
            return parents;

        }

        return null;
    }

    @Override
    public List<PermissionNodeModel> getChildrens(PermissionNodeModel root, List<PermissionNodeModel> all) {
        List<PermissionNodeModel> children = all.stream().filter(m -> {
            return Objects.equals(m.getPid(), root.getId());
        }).map(
                (m) -> {
                    m.setChildList(getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }
}
