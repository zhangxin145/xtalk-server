package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.UsersEntity;
import com.xtalk.server.xtalkserver.model.RobotChangeStatusModel;
import com.xtalk.server.xtalkserver.model.RobotModel;
import com.xtalk.server.xtalkserver.model.RobotQueryVo;
import com.xtalk.server.xtalkserver.model.RobotVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/2/27 8:41 下午
 */
public interface RobotService {
    List<UsersEntity> changeStatus(RobotChangeStatusModel model);

    UsersEntity add(RobotVo robotVo);

    UsersEntity edit(RobotVo robotVo);

    Page<RobotModel> getResultPage(RobotQueryVo queryVo);
}
