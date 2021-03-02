package com.xtalk.server.xtalkserver.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/19 10:57 下午
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {

    <S extends T> S save(S entity);
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    long count();
    void deleteById(ID id);
    void delete(T entity);
    void deleteAll(Iterable<? extends T> entities);
    void deleteAll();

    T getOne(ID id);
    List<T> findAll();
    List<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);
    List<T> findAllById(Iterable<ID> ids);
    <S extends T> List<S> saveAll(Iterable<S> entities);
    <S extends T> List<S> findAll(Example<S> example);
    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    Optional<T> findOne(@Nullable Specification<T> spec);
    List<T> findAll(@Nullable Specification<T> spec);
    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
    List<T> findAll(@Nullable Specification<T> spec, Sort sort);
    long count(@Nullable Specification<T> spec);

    Optional<T> findOne(Predicate predicate);
    List<T> findAll(Predicate predicate);
    List<T> findAll(Predicate predicate, Sort sort);
    List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);
    List<T> findAll(OrderSpecifier<?>... orders);
    Page<T> findAll(Predicate predicate, Pageable pageable);
    long count(Predicate predicate);
    boolean exists(Predicate predicate);

}