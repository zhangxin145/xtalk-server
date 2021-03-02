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
@Table ( name ="group_bulletins" , schema = "")
public class GroupBulletinsEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "groupId" )
	private Long groupId;

   	@Column(name = "content" )
	private String content;

   	@Column(name = "timestamp" )
	private Long timestamp;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

	public GroupBulletinsEntity() {
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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
					"groupId='" + groupId + '\'' +
					"content='" + content + '\'' +
					"timestamp='" + timestamp + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
				'}';
	}

}
