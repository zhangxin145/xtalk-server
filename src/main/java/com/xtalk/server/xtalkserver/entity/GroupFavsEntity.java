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
@Table ( name ="group_favs" , schema = "")
public class GroupFavsEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "userId" )
	private Long userId;

   	@Column(name = "groupId" )
	private Long groupId;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

	public GroupFavsEntity() {
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
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
				'}';
	}

}
