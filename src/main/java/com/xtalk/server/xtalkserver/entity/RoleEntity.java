package com.xtalk.server.xtalkserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @Author zx
 * @Date 2021-01-21 20:20:44
 */

@Entity
@Table(name = "role", schema = "")
public class RoleEntity {


    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 状态:0=正常,1=禁用
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 角色介绍
     */
    @Column(name = "intro")
    private String intro;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
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
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @Column(name = "utime")
    private Date utime;

    /**
     * 修改人
     */
    @Column(name = "uid")
    private Long uid;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
                "name='" + name + '\'' +
                "status='" + status + '\'' +
                "intro='" + intro + '\'' +
                "ctime='" + ctime + '\'' +
                "cid='" + cid + '\'' +
                "utime='" + utime + '\'' +
                "uid='" + uid + '\'' +
                "deleted='" + deleted + '\'' +
                '}';
    }

}
