package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.*;
import com.xtalk.server.xtalkserver.model.RobotChangeStatusModel;
import com.xtalk.server.xtalkserver.model.RobotModel;
import com.xtalk.server.xtalkserver.model.RobotQueryVo;
import com.xtalk.server.xtalkserver.model.RobotVo;
import com.xtalk.server.xtalkserver.repository.*;
import com.xtalk.server.xtalkserver.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xin.z
 * @date 2021/2/27 8:41 下午
 */
@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskMemberRepository taskMemberRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ChatRoomMemberRepository chatRoomMemberRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Override
    public List<UsersEntity> changeStatus(RobotChangeStatusModel model) {
        List<Long> ids = model.getIds();
        List<UsersEntity> users = userRepository.findAllById(ids);
        if (users != null && users.size() > 0) {
            users.forEach(u -> {
                u.setFlag(model.getFlag());
            });
            return userRepository.saveAll(users);
        }
        return null;
    }


    @Override
    public UsersEntity add(RobotVo robotVo) {
        checkParam(robotVo);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.append(robotVo);
        usersEntity.setCreatedAt(new Date());
        usersEntity.setType(1);
        usersEntity.setFlag(0L);
        Integer random = (int) (Math.random() * 900000) + 100000;
        String account = random.toString();
        usersEntity.setAccount("r" + account);
        int salt = (int) (Math.random() * 9000) + 1000;
        String hashStr = MD5Utils.hash(account, salt);
        usersEntity.setPasswordSalt(salt + "");
        usersEntity.setPasswordHash(hashStr);
        // 默认值
        usersEntity.setRongCloudToken("");
        usersEntity.setGender("");
        usersEntity.setStAccount("");
        usersEntity.setPhone("");
        usersEntity.setPhoneVerify(1L);
        usersEntity.setStSearchVerify(1L);
        usersEntity.setFriVerify(1L);
        usersEntity.setGroupVerify(1L);
        usersEntity.setPokeStatus(1L);
        usersEntity.setGroupCount(0L);
        usersEntity.setTimestamp(0L);
        usersEntity.setUpdatedAt(new Date());
        usersEntity.setCreatedAt(new Date());
        UsersEntity result = userRepository.save(usersEntity);
        List<Long> taskIds = robotVo.getTaskIds();
        if (taskIds != null && taskIds.size() > 0) {
            List<TaskEntity> tasks = taskRepository.findAllById(taskIds);
            List<TaskMembersEntity> taskMembersEntities = tasks.stream().map(task -> {
                TaskMembersEntity taskMembersEntity = new TaskMembersEntity();
                taskMembersEntity.setTaskId(task.getId());
                taskMembersEntity.setMemberId(result.getId());
                taskMembersEntity.setDeleted(0);
                return taskMembersEntity;
            }).collect(Collectors.toList());
            taskMemberRepository.saveAll(taskMembersEntities);
        }
        return result;
    }


    private void checkParam(RobotVo robotVo) {

        String nickname = robotVo.getNickname();
        Assert.isTrue(StringUtils.isNotBlank(nickname), "昵称不能为空");
        String url = robotVo.getPortraitUri();
        Assert.isTrue(StringUtils.isNotBlank(url), "头像不能为空");

    }


    @Override
    public UsersEntity edit(RobotVo robotVo) {
        checkParam(robotVo);
        Long id = robotVo.getId();
        UsersEntity usersEntity = userRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id err");
        });
        usersEntity.append(robotVo);
        userRepository.save(usersEntity);
        Optional<List<TaskMembersEntity>> membersEntities = taskMemberRepository
                .findByTaskIdAndDeleted(usersEntity.getId(), 0);
        if (membersEntities.isPresent()) {
            List<TaskMembersEntity> taskMembersEntities = membersEntities.get();
            taskMembersEntities.forEach(a -> {
                a.setDeleted(1);
            });
            taskMemberRepository.saveAll(taskMembersEntities);
        }
        List<Long> taskIds = robotVo.getTaskIds();
        if (taskIds != null && taskIds.size() > 0) {
            List<TaskEntity> tasks = taskRepository.findAllById(taskIds);
            List<TaskMembersEntity> taskMembersEntities = tasks.stream().map(task -> {
                TaskMembersEntity taskMembersEntity = new TaskMembersEntity();
                taskMembersEntity.setTaskId(task.getId());
                taskMembersEntity.setMemberId(usersEntity.getId());
                taskMembersEntity.setDeleted(0);
                return taskMembersEntity;
            }).collect(Collectors.toList());
            taskMemberRepository.saveAll(taskMembersEntities);
        }
        return usersEntity;
    }

    @Override
    public Page<RobotModel> getResultPage(RobotQueryVo queryVo) {
        int pageNumber = queryVo.getPageNumber() == null || queryVo.getPageNumber() < 1 ? 1 : queryVo.getPageNumber();
        int pageSize = queryVo.getPageSize() == null || queryVo.getPageSize() < 1 ? 20 : queryVo.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        String chatRoomName = queryVo.getChatRoomName();


        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QUsersEntity usersEntity = QUsersEntity.usersEntity;
        JPAQuery<UsersEntity> query = jpaQueryFactory.selectFrom(usersEntity);
        String name = queryVo.getRobotName();
        if (StringUtils.isNotBlank(name)) {
            query.where(usersEntity.nickname.like("%" + name + "%"));
        }
        query.where(usersEntity.flag.notIn(2));
        query.where(usersEntity.type.eq(1));
        if (StringUtils.isNotBlank(chatRoomName)) {
            Optional<List<ChatroomsEntity>> chatRooms = chatRoomRepository
                    .findByChatroomNameLikeAndDeletedAt("%" + chatRoomName + "%", 0);
            if (chatRooms.isPresent()) {
                List<ChatroomsEntity> chatroomsEntities = chatRooms.get();
                List<Long> roomIds = chatroomsEntities.stream()
                        .map(ChatroomsEntity::getId)
                        .collect(Collectors.toList());
                if (roomIds.size() > 0) {
                    Optional<List<ChatroomMembersEntity>> membersEntities = chatRoomMemberRepository
                            .findByChatroomIdInAndIsDeleted(roomIds, 0);
                    if (membersEntities.isPresent()) {
                        List<ChatroomMembersEntity> membersEntitieList = membersEntities.get();
                        List<Long> ids = membersEntitieList
                                .stream()
                                .map(ChatroomMembersEntity::getMemberId)
                                .collect(Collectors.toList());
                        if (ids != null && ids.size() > 0) {
                            query.where(usersEntity.id.in(ids));
                        }
                    }
                }
            }
        }
        long count = query.fetchCount();
        OrderSpecifier<Long> orderBy = usersEntity.id.desc();
        query.orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<UsersEntity> result = query.fetch();
        List<Long> userIds = result.stream().map(UsersEntity::getId).collect(Collectors.toList());
        Optional<List<ChatroomMembersEntity>> chatroomMembersEntities = chatRoomMemberRepository.findByMemberIdInAndIsDeleted(userIds, 0);
        Map<Long, List<ChatroomMembersEntity>> memberMap = new HashMap<>();
        List<ChatroomsEntity> ChatroomsList = new ArrayList<>();
        if (chatroomMembersEntities.isPresent()) {
            Map<Long, List<ChatroomMembersEntity>> maps = chatroomMembersEntities.get().stream().collect(Collectors.groupingBy(ChatroomMembersEntity::getMemberId));
            if (maps != null) {
                memberMap = maps;
            }
            List<ChatroomMembersEntity> membersEntities = chatroomMembersEntities.get();
            List<Long> chatRoomIds = membersEntities.stream().map(ChatroomMembersEntity::getChatroomId).collect(Collectors.toList());
            ChatroomsList = chatRoomRepository.findAllById(chatRoomIds);
        }
        Map<Long, List<ChatroomMembersEntity>> finalMemberMap = memberMap;
        List<ChatroomsEntity> finalChatroomsList = ChatroomsList;
        List<RobotModel> robotModelList = result.stream().map(u -> {
            RobotModel robotModel = new RobotModel();
            robotModel.append(u);
            Optional<List<TaskMembersEntity>> taskMembersEntities = taskMemberRepository
                    .findByMemberIdAndDeleted(u.getId(), 0);
            if (taskMembersEntities.isPresent()) {
                List<TaskMembersEntity> taskMembersEntityList = taskMembersEntities.get();
                List<Long> taskIds = taskMembersEntityList.stream().map(TaskMembersEntity::getTaskId).collect(Collectors.toList());
                if (taskIds != null && taskIds.size() > 0) {
                    List<TaskEntity> taskEntities = taskRepository.findAllById(taskIds);
                    robotModel.setTasks(taskEntities);
                }
            }
            List<ChatroomMembersEntity> membersEntities = finalMemberMap.get(u.getId());
            if (membersEntities != null) {
                List<Long> chatRooms = membersEntities.stream().map(ChatroomMembersEntity::getChatroomId).collect(Collectors.toList());
                if (finalChatroomsList != null) {
                    String chatroomNames = finalChatroomsList.stream().filter(a -> {
                        Long id = a.getId();
                        if (chatRooms.contains(id)) {
                            return true;
                        }
                        return false;
                    }).map(ChatroomsEntity::getChatroomName).collect(Collectors.joining(",", "", ""));

                    if (chatroomNames != null) {
                        robotModel.setChatRoomNames(chatroomNames);
                    }
                }
            }
            return robotModel;
        }).collect(Collectors.toList());
        return new PageImpl(robotModelList, pageable, count);
    }
}
