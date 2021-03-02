package com.xtalk.server.xtalkserver.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.FriendsterWebsiteEntity;
import com.xtalk.server.xtalkserver.entity.QFriendsterWebsiteEntity;
import com.xtalk.server.xtalkserver.repository.FriendsterWebsiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/19 11:30 下午
 */
@SpringBootTest
@Slf4j
public class Test {

    @Autowired
    EntityManager entityManager;


    @Autowired
    FriendsterWebsiteRepository friendsterWebsiteRepository;

    @org.junit.jupiter.api.Test
    void test(){
        List<FriendsterWebsiteEntity> result = friendsterWebsiteRepository.findAll();
        log.info(result.toString());

    }

    @org.junit.jupiter.api.Test
    void queryDslTest(){
        QFriendsterWebsiteEntity friendsterWebsiteEntity = QFriendsterWebsiteEntity
                .friendsterWebsiteEntity;
        List<FriendsterWebsiteEntity> result = new JPAQueryFactory(entityManager).selectFrom(friendsterWebsiteEntity)
                .where(friendsterWebsiteEntity.id.eq(1l)).fetch();
        log.info(result.toString());

    }

}
