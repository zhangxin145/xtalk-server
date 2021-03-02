package com.xtalk.server.xtalkserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @Author zx
 * @Date 2021-01-19 23:18:06
 */

@Entity
@Table(name = "users", schema = "")
public class UsersEntity extends BaseEntity<UsersEntity, Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "phone")
    private String phone;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "portraitUri")
    private String portraitUri;

    @Column(name = "vip_id")
    private Long vipId;

    @Column(name = "lock_status")
    private Integer lockStatus;

    //	 机器人角色，1 普通用户  2 管理员
    @Column(name = "role")
    private Integer role;

    //
    @Column(name = "type")
    private Integer type;

    @JsonIgnore
    @Column(name = "passwordHash")
    private String passwordHash;
    @JsonIgnore
    @Column(name = "passwordSalt")
    private String passwordSalt;

    @Column(name = "rongCloudToken")
    private String rongCloudToken;

    @Column(name = "gender")
    private String gender;

    @Column(name = "stAccount")
    private String stAccount;

    @Column(name = "account")
    private String account;

    @Column(name = "phoneVerify")
    private Long phoneVerify;
    @Column(name = "flag")
    private Long flag;

    @Column(name = "stSearchVerify")
    private Long stSearchVerify;

    @Column(name = "friVerify")
    private Long friVerify;

    @Column(name = "groupVerify")
    private Long groupVerify;

    @Column(name = "pokeStatus")
    private Long pokeStatus;

    @Column(name = "groupCount")
    private Long groupCount;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "createdAt")
    private Date createdAt;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getVipId() {
        return vipId;
    }

    public void setVipId(Long vipId) {
        this.vipId = vipId;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "deletedAt")
    private Date deletedAt;

    public UsersEntity() {
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortraitUri() {
        return this.portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return this.passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getRongCloudToken() {
        return this.rongCloudToken;
    }

    public void setRongCloudToken(String rongCloudToken) {
        this.rongCloudToken = rongCloudToken;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStAccount() {
        return this.stAccount;
    }

    public void setStAccount(String stAccount) {
        this.stAccount = stAccount;
    }

    public Long getPhoneVerify() {
        return this.phoneVerify;
    }

    public void setPhoneVerify(Long phoneVerify) {
        this.phoneVerify = phoneVerify;
    }

    public Long getStSearchVerify() {
        return this.stSearchVerify;
    }

    public void setStSearchVerify(Long stSearchVerify) {
        this.stSearchVerify = stSearchVerify;
    }

    public Long getFriVerify() {
        return this.friVerify;
    }

    public void setFriVerify(Long friVerify) {
        this.friVerify = friVerify;
    }

    public Long getGroupVerify() {
        return this.groupVerify;
    }

    public void setGroupVerify(Long groupVerify) {
        this.groupVerify = groupVerify;
    }

    public Long getPokeStatus() {
        return this.pokeStatus;
    }

    public void setPokeStatus(Long pokeStatus) {
        this.pokeStatus = pokeStatus;
    }

    public Long getGroupCount() {
        return this.groupCount;
    }

    public void setGroupCount(Long groupCount) {
        this.groupCount = groupCount;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                "region='" + region + '\'' +
                "phone='" + phone + '\'' +
                "nickname='" + nickname + '\'' +
                "portraitUri='" + portraitUri + '\'' +
                "passwordHash='" + passwordHash + '\'' +
                "passwordSalt='" + passwordSalt + '\'' +
                "rongCloudToken='" + rongCloudToken + '\'' +
                "gender='" + gender + '\'' +
                "stAccount='" + stAccount + '\'' +
                "phoneVerify='" + phoneVerify + '\'' +
                "stSearchVerify='" + stSearchVerify + '\'' +
                "friVerify='" + friVerify + '\'' +
                "groupVerify='" + groupVerify + '\'' +
                "pokeStatus='" + pokeStatus + '\'' +
                "groupCount='" + groupCount + '\'' +
                "timestamp='" + timestamp + '\'' +
                "createdAt='" + createdAt + '\'' +
                "updatedAt='" + updatedAt + '\'' +
                "deletedAt='" + deletedAt + '\'' +
                '}';
    }

}
