package com.xtalk.server.xtalkserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @Author zx
 * @Date 2021-01-21 20:20:44
 */

@Entity
@Table(name = "permission", schema = "")
public class PermissionEntity {


    /**
     * 权限ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 所属父级权限ID
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 唯一标示-方便前端
     */
    @Column(name = "code")
    private String code;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;


    /**
     * 权限名称
     */
    @Column(name = "sort")
    private Long sort;

    /**
     * URL
     */
    @Column(name = "uri")
    private String uri;

    /**
     * 创建时间
     */
    @Column(name = "ctime")
    private Date ctime;

    /**
     * 创建人
     */
    @Column(name = "cid")
    private Long cid;

    /**
     * 修改时间
     */
    @Column(name = "utime")
    private Date utime;

    /**
     * 修改人
     */
    @Column(name = "uid")
    private Long uid;

    /**
     * 类型 1-页面 2-按钮
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 逻辑删除:0=未删除,1=已删除
     */
    @Column(name = "deleted")
    private Integer deleted;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return this.pid;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getCtime() {
        return this.ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Long getCid() {
        return this.cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Date getUtime() {
        return this.utime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                "pid='" + pid + '\'' +
                "code='" + code + '\'' +
                "name='" + name + '\'' +

                "uri='" + uri + '\'' +
                "ctime='" + ctime + '\'' +
                "cid='" + cid + '\'' +
                "utime='" + utime + '\'' +
                "uid='" + uid + '\'' +
                "deleted='" + deleted + '\'' +
                '}';
    }

}
