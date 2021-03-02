package com.xtalk.server.xtalkserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xin.z
 * @date 2021/1/31 2:46 下午
 */
@Slf4j
@ApiModel(description = "所有Entity的父类")
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity<E, ID> implements Cloneable, Serializable {


    public <T extends BaseEntity<E, ID>> T append(Object append) {
        copyNotNullProperties(append, this);
        return (T) this;
    }

    public <T extends BaseEntity<E, ID>> T toModel() {
        return (T) this;
    }

    public <T extends BaseEntity<E, ID>> T toEntity() {
        return (T) this;
    }

    @SneakyThrows
    public <T extends BaseEntity<E, ID>> T toModel(Class<T> cls) {
        if (this.getClass().equals(cls)) {
            return (T) this;
        }
        T target = cls.newInstance();
        copyNotNullProperties(this, target);
        return target;
    }

    @SneakyThrows
    public <T extends BaseEntity<E, ID>> T toEntity(Class<T> cls) {
        if (this.getClass().equals(cls)) {
            return (T) this;
        }
        T target = cls.newInstance();
        copyNotNullProperties(this, target);
        return target;
    }

    @SneakyThrows
    public Object clone() {
        return super.clone();
    }

    @SneakyThrows
    public <T extends BaseEntity<E, ID>> T copy() {
        return (T) clone();
    }

    @SneakyThrows
    public <T extends BaseEntity<E, ID>> T copy(Class<T> cls) {
        return copy();
    }

    public static void copyNotNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    private static String[] getNullProperties(Object source) {
        BeanWrapper srcBean = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value == null)
                noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }

    private static String[] getNotNullProperties(Object source) {
        BeanWrapper srcBean = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null)
                noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }
}

