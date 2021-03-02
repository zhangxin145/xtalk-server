package com.xtalk.server.xtalkserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-19 23:18:06 
 */

@Entity
@Table ( name ="group_exited_lists" , schema = "")
public class GroupExitedListsEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "groupId" )
	private Long groupId;

   	@Column(name = "quitUserId" )
	private Long quitUserId;

   	@Column(name = "quitNickname" )
	private String quitNickname;

   	@Column(name = "quitPortraitUri" )
	private String quitPortraitUri;

   	@Column(name = "quitReason" )
	private Long quitReason;

   	@Column(name = "quitTime" )
	private Long quitTime;

   	@Column(name = "operatorId" )
	private Long operatorId;

   	@Column(name = "operatorName" )
	private String operatorName;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

	public GroupExitedListsEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getQuitUserId() {
		return this.quitUserId;
	}

	public void setQuitUserId(Long quitUserId) {
		this.quitUserId = quitUserId;
	}

	public String getQuitNickname() {
		return this.quitNickname;
	}

	public void setQuitNickname(String quitNickname) {
		this.quitNickname = quitNickname;
	}

	public String getQuitPortraitUri() {
		return this.quitPortraitUri;
	}

	public void setQuitPortraitUri(String quitPortraitUri) {
		this.quitPortraitUri = quitPortraitUri;
	}

	public Long getQuitReason() {
		return this.quitReason;
	}

	public void setQuitReason(Long quitReason) {
		this.quitReason = quitReason;
	}

	public Long getQuitTime() {
		return this.quitTime;
	}

	public void setQuitTime(Long quitTime) {
		this.quitTime = quitTime;
	}

	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"groupId='" + groupId + '\'' +
					"quitUserId='" + quitUserId + '\'' +
					"quitNickname='" + quitNickname + '\'' +
					"quitPortraitUri='" + quitPortraitUri + '\'' +
					"quitReason='" + quitReason + '\'' +
					"quitTime='" + quitTime + '\'' +
					"operatorId='" + operatorId + '\'' +
					"operatorName='" + operatorName + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
				'}';
	}

}
