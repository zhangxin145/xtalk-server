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
@Table ( name ="group_syncs" , schema = "")
public class GroupSyncsEntity {
	@Id // 添加一个空的id标识，因为jpa在映射实体是需要一个id，这个必须

   	@Column(name = "groupId" )
	private Long groupId;

   	@Column(name = "syncInfo" )
	private Integer syncInfo;

   	@Column(name = "syncMember" )
	private Integer syncMember;

   	@Column(name = "dismiss" )
	private Integer dismiss;

	public GroupSyncsEntity() {
	}

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Integer getSyncInfo() {
		return this.syncInfo;
	}

	public void setSyncInfo(Integer syncInfo) {
		this.syncInfo = syncInfo;
	}

	public Integer getSyncMember() {
		return this.syncMember;
	}

	public void setSyncMember(Integer syncMember) {
		this.syncMember = syncMember;
	}

	public Integer getDismiss() {
		return this.dismiss;
	}

	public void setDismiss(Integer dismiss) {
		this.dismiss = dismiss;
	}

	@Override
	public String toString() {
		return "{" +
					"groupId='" + groupId + '\'' +
					"syncInfo='" + syncInfo + '\'' +
					"syncMember='" + syncMember + '\'' +
					"dismiss='" + dismiss + '\'' +
				'}';
	}

}
