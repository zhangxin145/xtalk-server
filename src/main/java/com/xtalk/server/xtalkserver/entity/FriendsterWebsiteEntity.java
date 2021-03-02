package com.xtalk.server.xtalkserver.entity;

import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-19 23:18:06 
 */

@Entity
@Table ( name ="friendster_website" , schema = "")
@Accessors(fluent = true)
public class FriendsterWebsiteEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "icon" )
	private String icon;


	@Column(name = "creatorId", nullable = true, columnDefinition = "bigint default 0")
	private Long creatorId;

   	@Column(name = "updateId" )
	private Long updateId;

   	@Column(name = "url" )
	private String url;

   	@Column(name = "title" )
	private String title;

   	@Column(name = "isDeleted" )
	private Integer isDeleted;

   	@Column(name = "timestamp" )
	private Long timestamp;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

   	@Column(name = "deletedAt" )
	private Date deletedAt;

	@Column(name = "name" )
   	private String name;

	public FriendsterWebsiteEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getUpdateId() {
		return this.updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
					"icon='" + icon + '\'' +
					"creatorId='" + creatorId + '\'' +
					"updateId='" + updateId + '\'' +
					"url='" + url + '\'' +
					"title='" + title + '\'' +
					"isDeleted='" + isDeleted + '\'' +
					"timestamp='" + timestamp + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
					"deletedAt='" + deletedAt + '\'' +
				'}';
	}

}
