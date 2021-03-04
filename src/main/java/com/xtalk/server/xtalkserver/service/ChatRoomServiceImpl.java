package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.*;
import com.xtalk.server.xtalkserver.model.ChatRoomModel;
import com.xtalk.server.xtalkserver.model.ChatRoomQueryVo;
import com.xtalk.server.xtalkserver.model.ChatRoomVo;
import com.xtalk.server.xtalkserver.model.MuteModel;
import com.xtalk.server.xtalkserver.repository.AdminUserRepository;
import com.xtalk.server.xtalkserver.repository.ChatRoomMemberRepository;
import com.xtalk.server.xtalkserver.repository.ChatRoomRepository;
import com.xtalk.server.xtalkserver.repository.UserRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * @date 2021/1/31 1:42 下午
 */
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatRoomMemberRepository chatRoomMemberRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityAuditor securityAuditor;

    @Autowired
    EntityManager entityManager;

    @Autowired
    AdminUserRepository adminUserRepository;

    @Override
    public ChatroomsEntity add(ChatRoomVo chatRoomVo) {
        checkParam(chatRoomVo);
        String chatroomName = chatRoomVo.getChatroomName();
        Optional<ChatroomsEntity> room = chatRoomRepository.findFirstByChatroomName(chatroomName);
        room.ifPresent(a -> {
            throw new IllegalArgumentException("name 已存在");
        });

        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        ChatroomsEntity chatroomsEntity = new ChatroomsEntity();
        BeanUtils.copyProperties(chatRoomVo, chatroomsEntity);
        List<String> keys = chatRoomVo.getKeys();
        if (keys != null && keys.size() > 0) {
            chatroomsEntity.setKeysFilter(keys.stream().collect(Collectors.joining("_", "", "")));
        }
        chatroomsEntity.setCreatorId(currentAdminId);
        chatroomsEntity.setDefendStatus(1L);
        chatroomsEntity.setUpdatedAt(new Date());
        chatroomsEntity.setCertiStatus(1l);
        //chatroomsEntity.setIsMute(1L);
        chatroomsEntity.setCreatedAt(new Date());
        ChatroomsEntity result = chatRoomRepository.save(chatroomsEntity);

        List<ChatroomMembersEntity> chatroomMembersEntities = buildMemberEntity(result.getId(), chatRoomVo.getOwner(), chatRoomVo.getManager(), chatRoomVo.getRobots());
        List<Long> robots = chatRoomVo.getRobots();
        // todo 机器人是否有任务需要启动
        chatRoomMemberRepository.saveAll(chatroomMembersEntities);

        return result;
    }

    private List<ChatroomMembersEntity> buildMemberEntity(Long roomId, Long owner, List<Long> manager, List<Long> robots) {

        ArrayList<ChatroomMembersEntity> chatroomMembersEntities = new ArrayList<>();
        ChatroomMembersEntity owners = new ChatroomMembersEntity();
        owners.setChatroomId(roomId);
        owners.setMemberId(owner);
        owners.setChatroomRole(0L);
        owners.setIsDeleted(0);
        owners.setCreatedAt(new Date());
        owners.setUpdatedAt(new Date());
        owners.setDisplayName("");
        owners.setGroupNickname("");
        owners.setIsMute(0L);
        owners.setIsRobot(0);
        chatroomMembersEntities.add(owners);

        List<ChatroomMembersEntity> managerList = manager.stream().map(a -> {
            ChatroomMembersEntity managers = new ChatroomMembersEntity();
            managers.setChatroomId(roomId);
            managers.setMemberId(a);
            managers.setChatroomRole(2L);
            managers.setIsDeleted(0);
            managers.setDisplayName("");
            managers.setGroupNickname("");
            managers.setIsMute(0L);
            managers.setIsRobot(0);
            managers.setCreatedAt(new Date());
            managers.setUpdatedAt(new Date());
            return managers;
        }).collect(Collectors.toList());
        chatroomMembersEntities.addAll(managerList);
        if (robots != null && robots.size() > 0) {
            robots.stream().map(a -> {
                ChatroomMembersEntity robot = new ChatroomMembersEntity();
                robot.setChatroomId(roomId);
                robot.setMemberId(a);
                // todo 也是2？
                robot.setChatroomRole(2L);
                robot.setDisplayName("");
                robot.setGroupNickname("");
                robot.setIsDeleted(0);
                robot.setIsMute(0L);
                robot.setIsRobot(1);
                robot.setCreatedAt(new Date());
                robot.setUpdatedAt(new Date());
                return robot;

            }).collect(Collectors.toList());

        }
        return chatroomMembersEntities;


    }

    private void checkParam(ChatRoomVo chatRoomVo) {
        Assert.isTrue(StringUtils.isNotBlank(chatRoomVo.getChatroomName()), "聊天室名称不能为空");
        Assert.isTrue(StringUtils.isNotBlank(chatRoomVo.getPortraitUri()), "聊天室头像不能为空");
        Assert.isTrue(chatRoomVo.getOwner() != null && chatRoomVo.getOwner() > 0, "群主不能为空");
        Assert.isTrue(chatRoomVo.getManager() != null && chatRoomVo.getManager().size() > 0, "管理员不能为空");

        Assert.isTrue(!chatRoomVo.getManager().contains(chatRoomVo.getOwner()), "群主和管理员不可以为同一个人");


    }

    @Override
    public ChatroomsEntity update(ChatRoomVo chatRoomVo) {
        checkParam(chatRoomVo);
        String chatroomName = chatRoomVo.getChatroomName();
        Optional<ChatroomsEntity> room = chatRoomRepository.findFirstByChatroomName(chatroomName);
        room.ifPresent(a -> {
            if (a.getId() != chatRoomVo.getId()) {
                throw new IllegalArgumentException("name 已存在");
            }
        });

        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        ChatroomsEntity update = chatRoomRepository.findById(chatRoomVo.getId()).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        update.append(chatRoomVo);
        List<String> keys = chatRoomVo.getKeys();
        if (keys != null && keys.size() > 0) {
            update.setKeysFilter(keys.stream().collect(Collectors.joining("_", "", "")));
        }

        ChatroomsEntity result = chatRoomRepository.save(update);
        List<Integer> roles = Arrays.asList(0, 2);
        Optional<List<ChatroomMembersEntity>> delMember = chatRoomMemberRepository.findByChatroomIdAndChatroomRoleInAndIsDeleted(update.getId(), roles, 0);
        if (delMember.isPresent()) {
            List<ChatroomMembersEntity> dels = delMember.get();
            for (ChatroomMembersEntity a : dels) {
                a.setIsDeleted(1);
            }
            chatRoomMemberRepository.saveAll(dels);

        }
        List<ChatroomMembersEntity> chatroomMembersEntities = buildMemberEntity(result.getId(), chatRoomVo.getOwner(), chatRoomVo.getManager(), chatRoomVo.getRobots());

        List<Long> robots = chatRoomVo.getRobots();
        // todo 机器人是否有任务需要 删除与 启动
        chatRoomMemberRepository.saveAll(chatroomMembersEntities);

        return result;


    }

    @Override
    public ChatroomsEntity del(Long id) {

        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        ChatroomsEntity del = chatRoomRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        del.setDefendStatus(0L);
        ChatroomsEntity result = chatRoomRepository.save(del);
        // todo 后面添加对任务的处理

        return result;
    }

    @Override
    public ChatRoomModel getById(Long id) {
        return null;
    }

    @Override
    public ChatroomsEntity mute(Long chatRoomId, Long isMute) {

        ChatroomsEntity update = chatRoomRepository.findById(chatRoomId).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        update.setIsMute(isMute);
        update.setUpdatedAt(new Date());
        //todo 根据禁言对后置任务的处理
        return chatRoomRepository.save(update);
    }

    @Override
    public ChatroomsEntity muteMember(MuteModel muteModel) {
        Long chatRoomId = muteModel.getChatRoomId();
        Long isMute = muteModel.getIsMute();
        List<Long> userIds = muteModel.getUserIds();
        Assert.isTrue(chatRoomId != null && chatRoomId > 0, "chatRoomId不能为空");
        Assert.isTrue(isMute != null && isMute > 0, "isMute不能为空");
        Assert.isTrue(userIds != null && userIds.size() > 0, "userIds不能为空");
        ChatroomsEntity update = chatRoomRepository.findById(chatRoomId).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        Optional<List<ChatroomMembersEntity>> members = chatRoomMemberRepository
                .findByChatroomIdAndMemberIdInAndIsDeletedAndChatroomRole(chatRoomId, muteModel.getUserIds(), 0, 2L);
        members.ifPresent(m -> {
            m.stream().forEach(a -> {
                a.setIsMute(isMute);
            });
            chatRoomMemberRepository.saveAll(m);
        });

        // todo 根据禁言对后置任务的处理
        return update;
    }

    @Override
    public ChatroomsEntity addMember(Long chatRoomId, List<Long> userIds) {
        ChatroomsEntity update = chatRoomRepository.findById(chatRoomId).orElseGet(() -> {
            throw new IllegalArgumentException("chatRoomId 有误");
        });
        Assert.isTrue(userIds != null && userIds.size() > 0, "userIds不能为空");
        List<ChatroomMembersEntity> membersEntities = userIds.stream().map(u -> {
            ChatroomMembersEntity membersEntity = new ChatroomMembersEntity();
            membersEntity.setChatroomId(chatRoomId);
            membersEntity.setMemberId(u);
            // todo 机器人任务
            membersEntity.setDisplayName("");
            membersEntity.setTimestamp(System.currentTimeMillis());
            membersEntity.setMemberId(u);
            membersEntity.setChatroomRole(1L);
            membersEntity.setIsDeleted(0);
            membersEntity.setIsMute(0L);
            membersEntity.setCreatedAt(new Date());
            membersEntity.setUpdatedAt(new Date());
            return membersEntity;
        }).collect(Collectors.toList());

        membersEntities.stream().forEach(a->{
            chatRoomMemberRepository.save(a);
        });
        return update;
    }

    @Override
    public ChatroomsEntity delMember(Long chatRoomId, List<Long> usrIds) {
        ChatroomsEntity update = chatRoomRepository.findById(chatRoomId).orElseGet(() -> {
            throw new IllegalArgumentException("chatRoomId 有误");
        });
        List<ChatroomMembersEntity> membersEntities = chatRoomMemberRepository
                .findByChatroomIdAndMemberIdInAndIsDeletedAndChatroomRole(chatRoomId, usrIds, 0, 1).orElseGet(() -> {
                    throw new IllegalArgumentException("usrIds 有误");
                });

        membersEntities.forEach(a -> {
            a.setIsDeleted(1);

        });

        chatRoomMemberRepository.saveAll(membersEntities);
        return update;
    }

    @Override
    public Page<ChatRoomModel> getPageResult(ChatRoomQueryVo queryVo) {
        int pageNumber = queryVo.getPageNumber() == null || queryVo.getPageNumber() < 1 ? 1 : queryVo.getPageNumber();
        int pageSize = queryVo.getPageSize() == null || queryVo.getPageSize() < 1 ? 20 : queryVo.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QChatroomsEntity chatroomsEntity = QChatroomsEntity.chatroomsEntity;
        JPAQuery<ChatroomsEntity> query = jpaQueryFactory.selectFrom(chatroomsEntity);
        String chatRoomName = queryVo.getChatRoomName();
        if (StringUtils.isNotBlank(chatRoomName)) {
            query.where(chatroomsEntity.chatroomName.like("%" + chatRoomName + "%"));
        }
        query.where(chatroomsEntity.defendStatus.eq(1L));
        Long ownerId = queryVo.getOwnerId();
        if (ownerId != null && ownerId > 0) {
            Optional<List<ChatroomMembersEntity>> chatroomMembersEntities = chatRoomMemberRepository.findByMemberIdAndChatroomRoleAndIsDeleted(ownerId, 0L, 0);
            chatroomMembersEntities.ifPresent(a -> {
                List<Long> roomsId = a.stream().map(ChatroomMembersEntity::getChatroomId).distinct().collect(Collectors.toList());
                if (roomsId.size() > 0) {
                    query.where(chatroomsEntity.id.in(roomsId));

                }
            });
        }


        long count = query.fetchCount();
        OrderSpecifier<Date> orderBy = chatroomsEntity.createdAt.desc();
        query.orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<ChatroomsEntity> result = Optional.ofNullable(query.fetch())
                .orElseGet(() -> {
                    throw new RuntimeException("暂无数据");
                });
        ArrayList<Long> adminIds = new ArrayList<>();
        List<Long> chatRoomIds = result.stream().map(ChatroomsEntity::getId).distinct().collect(Collectors.toList());
        List<Long> creatorIds = result.stream().map(ChatroomsEntity::getCreatorId).distinct().collect(Collectors.toList());
        adminIds.addAll(creatorIds);
        Map<Long, List<UsersEntity>> usersMap = userRepository.findAllById(creatorIds).stream().collect(Collectors.groupingBy(UsersEntity::getId));

        Optional<List<ChatroomMembersEntity>> owners = chatRoomMemberRepository.findByChatroomIdInAndIsDeletedAndChatroomRole(chatRoomIds, 0, 0L);
        Map<Long, Long> roomOwnerMaps = new HashMap<>();

        List<ChatroomMembersEntity> membersEntities = owners.get();
        roomOwnerMaps = membersEntities.stream().collect(Collectors.toMap(ChatroomMembersEntity::getChatroomId, ChatroomMembersEntity::getMemberId));
        List<Long> OwnerIds = membersEntities.stream().map(ChatroomMembersEntity::getMemberId).collect(Collectors.toList());
        //adminIds.addAll(memberAdminIds);
        List<UsersEntity> userOwners = userRepository.findAllById(OwnerIds);
        Map<Long, String> ownerMaps = new HashMap<>();
        if (userOwners != null && userOwners.size() > 0) {
            ownerMaps = userOwners.stream().collect(Collectors.toMap(UsersEntity::getId, UsersEntity::getNickname));
        }
        List<AdminEntity> admins = adminUserRepository.findAllById(adminIds);
        Map<Long, String> adminMaps = new HashMap<>();
        if (admins != null && admins.size() > 0) {
            adminMaps = admins.stream().collect(Collectors.toMap(AdminEntity::getId, AdminEntity::getName));
        }

        ArrayList<ChatRoomModel> chatRoomModels = new ArrayList<>();
        for (ChatroomsEntity room : result) {
            ChatRoomModel model = new ChatRoomModel();
            model.append(room);
            Long creatorId = room.getCreatorId();
            String name = adminMaps.get(creatorId);
            if (StringUtils.isNotBlank(name)) {
                model.setCreateName(name);
            }
            Long roomId = model.getId();
            Long memberId = roomOwnerMaps.get(roomId);
            model.setOwner(memberId);
            String ownerName = ownerMaps.get(memberId);
            Optional<List<ChatroomMembersEntity>> managers = chatRoomMemberRepository.findByChatroomIdInAndIsDeletedAndChatroomRole(chatRoomIds, 0, 2L);
            if (managers.isPresent()) {
                List<ChatroomMembersEntity> memberList = managers.get();
                if (memberList.size() > 0) {
                    model.setManagers(memberList.stream().map(ChatroomMembersEntity::getMemberId).collect(Collectors.toList()));
                }
            }


            if (StringUtils.isNotBlank(ownerName)) {
                model.setOwnerName(ownerName);
            }
            Long number = chatRoomMemberRepository.countByChatroomIdAndIsDeletedAndChatroomRole(roomId, 0, 1L);
            if (number != null) {
                model.setMemberNumber(number);
            }
            chatRoomModels.add(model);
        }
        return new PageImpl(chatRoomModels, pageable, count);
    }
}
