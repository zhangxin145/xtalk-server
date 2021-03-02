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
@Table ( name ="groups" , schema = "")
public class GroupsEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "name" )
	private String name;

   	@Column(name = "portraitUri" )
	private String portraitUri;

   	@Column(name = "memberCount" )
	private Long memberCount;

   	@Column(name = "maxMemberCount" )
	private Long maxMemberCount;

   	@Column(name = "creatorId" )
	private Long creatorId;

   	@Column(name = "bulletin" )
	private String bulletin;

   	@Column(name = "certiStatus" )
	private Long certiStatus;

   	@Column(name = "isMute" )
	private Long isMute;

   	@Column(name = "clearStatus" )
	private Long clearStatus;

   	@Column(name = "clearTimeAt" )
	private Long clearTimeAt;

   	@Column(name = "memberProtection" )
	private Long memberProtection;

   	@Column(name = "copiedTime" )
	private Long copiedTime;

   	@Column(name = "timestamp" )
	private Long timestamp;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

   	@Column(name = "deletedAt" )
	private Date deletedAt;

	public GroupsEntity() {
	}

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

	public String getPortraitUri() {
		return this.portraitUri;
	}

	public void setPortraitUri(String portraitUri) {
		this.portraitUri = portraitUri;
	}

	public Long getMemberCount() {
		return this.memberCount;
	}

	public void setMemberCount(Long memberCount) {
		this.memberCount = memberCount;
	}

	public Long getMaxMemberCount() {
		return this.maxMemberCount;
	}

	public void setMaxMemberCount(Long maxMemberCount) {
		this.maxMemberCount = maxMemberCount;
	}

	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getBulletin() {
		return this.bulletin;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public Long getCertiStatus() {
		return this.certiStatus;
	}

	public void setCertiStatus(Long certiStatus) {
		this.certiStatus = certiStatus;
	}

	public Long getIsMute() {
		return this.isMute;
	}

	public void setIsMute(Long isMute) {
		this.isMute = isMute;
	}

	public Long getClearStatus() {
		return this.clearStatus;
	}

	public void setClearStatus(Long clearStatus) {
		this.clearStatus = clearStatus;
	}

	public Long getClearTimeAt() {
		return this.clearTimeAt;
	}

	public void setClearTimeAt(Long clearTimeAt) {
		this.clearTimeAt = clearTimeAt;
	}

	public Long getMemberProtection() {
		return this.memberProtection;
	}

	public void setMemberProtection(Long memberProtection) {
		this.memberProtection = memberProtection;
	}

	public Long getCopiedTime() {
		return this.copiedTime;
	}

	public void setCopiedTime(Long copiedTime) {
		this.copiedTime = copiedTime;
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
					"name='" + name + '\'' +
					"portraitUri='" + portraitUri + '\'' +
					"memberCount='" + memberCount + '\'' +
					"maxMemberCount='" + maxMemberCount + '\'' +
					"creatorId='" + creatorId + '\'' +
					"bulletin='" + bulletin + '\'' +
					"certiStatus='" + certiStatus + '\'' +
					"isMute='" + isMute + '\'' +
					"clearStatus='" + clearStatus + '\'' +
					"clearTimeAt='" + clearTimeAt + '\'' +
					"memberProtection='" + memberProtection + '\'' +
					"copiedTime='" + copiedTime + '\'' +
					"timestamp='" + timestamp + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
					"deletedAt='" + deletedAt + '\'' +
				'}';
	}

}
