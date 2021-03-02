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
@Table ( name ="group_members" , schema = "")
public class GroupMembersEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "groupId" )
	private Long groupId;

   	@Column(name = "memberId" )
	private Long memberId;

   	@Column(name = "displayName" )
	private String displayName;

   	@Column(name = "role" )
	private Long role;

   	@Column(name = "isDeleted" )
	private Integer isDeleted;

   	@Column(name = "groupNickname" )
	private String groupNickname;

   	@Column(name = "region" )
	private String region;

   	@Column(name = "phone" )
	private String phone;

   	@Column(name = "WeChat" )
	private String weChat;

   	@Column(name = "Alipay" )
	private String alipay;

   	@Column(name = "memberDesc" )
	private String memberDesc;

   	@Column(name = "timestamp" )
	private Long timestamp;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

	public GroupMembersEntity() {
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

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Long getRole() {
		return this.role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getGroupNickname() {
		return this.groupNickname;
	}

	public void setGroupNickname(String groupNickname) {
		this.groupNickname = groupNickname;
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

	public String getWeChat() {
		return this.weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getAlipay() {
		return this.alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getMemberDesc() {
		return this.memberDesc;
	}

	public void setMemberDesc(String memberDesc) {
		this.memberDesc = memberDesc;
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
					"memberId='" + memberId + '\'' +
					"displayName='" + displayName + '\'' +
					"role='" + role + '\'' +
					"isDeleted='" + isDeleted + '\'' +
					"groupNickname='" + groupNickname + '\'' +
					"region='" + region + '\'' +
					"phone='" + phone + '\'' +
					"weChat='" + weChat + '\'' +
					"alipay='" + alipay + '\'' +
					"memberDesc='" + memberDesc + '\'' +
					"timestamp='" + timestamp + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
				'}';
	}

}
