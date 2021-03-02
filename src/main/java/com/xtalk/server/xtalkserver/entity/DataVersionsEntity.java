package com.xtalk.server.xtalkserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-19 23:18:06 
 */

@Entity
@Table ( name ="data_versions" , schema = "")
public class DataVersionsEntity {

	@Id

   	@Column(name = "userId" )
	private Long userId;

   	@Column(name = "userVersion" )
	private Long userVersion;

   	@Column(name = "blacklistVersion" )
	private Long blacklistVersion;

   	@Column(name = "friendshipVersion" )
	private Long friendshipVersion;

   	@Column(name = "groupVersion" )
	private Long groupVersion;

   	@Column(name = "groupMemberVersion" )
	private Long groupMemberVersion;

	public DataVersionsEntity() {
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserVersion() {
		return this.userVersion;
	}

	public void setUserVersion(Long userVersion) {
		this.userVersion = userVersion;
	}

	public Long getBlacklistVersion() {
		return this.blacklistVersion;
	}

	public void setBlacklistVersion(Long blacklistVersion) {
		this.blacklistVersion = blacklistVersion;
	}

	public Long getFriendshipVersion() {
		return this.friendshipVersion;
	}

	public void setFriendshipVersion(Long friendshipVersion) {
		this.friendshipVersion = friendshipVersion;
	}

	public Long getGroupVersion() {
		return this.groupVersion;
	}

	public void setGroupVersion(Long groupVersion) {
		this.groupVersion = groupVersion;
	}

	public Long getGroupMemberVersion() {
		return this.groupMemberVersion;
	}

	public void setGroupMemberVersion(Long groupMemberVersion) {
		this.groupMemberVersion = groupMemberVersion;
	}

	@Override
	public String toString() {
		return "{" +
					"userId='" + userId + '\'' +
					"userVersion='" + userVersion + '\'' +
					"blacklistVersion='" + blacklistVersion + '\'' +
					"friendshipVersion='" + friendshipVersion + '\'' +
					"groupVersion='" + groupVersion + '\'' +
					"groupMemberVersion='" + groupMemberVersion + '\'' +
				'}';
	}

}
