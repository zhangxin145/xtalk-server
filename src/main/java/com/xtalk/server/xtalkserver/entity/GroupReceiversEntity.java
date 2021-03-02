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
@Table ( name ="group_receivers" , schema = "")
public class GroupReceiversEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "userId" )
	private Long userId;

   	@Column(name = "groupId" )
	private Long groupId;

   	@Column(name = "groupName" )
	private String groupName;

   	@Column(name = "groupPortraitUri" )
	private String groupPortraitUri;

   	@Column(name = "requesterId" )
	private Long requesterId;

   	@Column(name = "receiverId" )
	private Long receiverId;

   	@Column(name = "type" )
	private Long type;

   	@Column(name = "status" )
	private Long status;

   	@Column(name = "deletedUsers" )
	private String deletedUsers;

   	@Column(name = "isRead" )
	private Long isRead;

   	@Column(name = "joinInfo" )
	private String joinInfo;

   	@Column(name = "timestamp" )
	private Long timestamp;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

	public GroupReceiversEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupPortraitUri() {
		return this.groupPortraitUri;
	}

	public void setGroupPortraitUri(String groupPortraitUri) {
		this.groupPortraitUri = groupPortraitUri;
	}

	public Long getRequesterId() {
		return this.requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public Long getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDeletedUsers() {
		return this.deletedUsers;
	}

	public void setDeletedUsers(String deletedUsers) {
		this.deletedUsers = deletedUsers;
	}

	public Long getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}

	public String getJoinInfo() {
		return this.joinInfo;
	}

	public void setJoinInfo(String joinInfo) {
		this.joinInfo = joinInfo;
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
					"userId='" + userId + '\'' +
					"groupId='" + groupId + '\'' +
					"groupName='" + groupName + '\'' +
					"groupPortraitUri='" + groupPortraitUri + '\'' +
					"requesterId='" + requesterId + '\'' +
					"receiverId='" + receiverId + '\'' +
					"type='" + type + '\'' +
					"status='" + status + '\'' +
					"deletedUsers='" + deletedUsers + '\'' +
					"isRead='" + isRead + '\'' +
					"joinInfo='" + joinInfo + '\'' +
					"timestamp='" + timestamp + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
				'}';
	}

}
