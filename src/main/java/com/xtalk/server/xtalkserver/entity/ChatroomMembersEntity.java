package com.xtalk.server.xtalkserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-19 23:18:06 
 */

@Entity
@Table ( name ="chatroom_members" , schema = "")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatroomMembersEntity extends BaseEntity<ChatroomMembersEntity,Long>{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "chatroomId" )
	private Long chatroomId;

   	@Column(name = "memberId" )
	private Long memberId;

   	@Column(name = "displayName" )
	private String displayName;

   	@Column(name = "chatroomRole" )
	private Long chatroomRole;

   	@Column(name = "isDeleted" )
	private Integer isDeleted;

	@Column(name = "isRobot" )
	private Integer isRobot;

   	@Column(name = "groupNickname" )
	private String groupNickname;

   	@Column(name = "region" )
	private String region;

   	@Column(name = "phone" )
	private String phone;

   	@Column(name = "memberDesc" )
	private String memberDesc;

   	@Column(name = "timestamp" )
	private Long timestamp;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

   	@Column(name = "isMute" )
	private Long isMute;




	public ChatroomMembersEntity() {
	}

	public Integer getIsRobot() {
		return isRobot;
	}

	public void setIsRobot(Integer isRobot) {
		this.isRobot = isRobot;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChatroomId() {
		return this.chatroomId;
	}

	public void setChatroomId(Long chatroomId) {
		this.chatroomId = chatroomId;
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

	public Long getChatroomRole() {
		return this.chatroomRole;
	}

	public void setChatroomRole(Long chatroomRole) {
		this.chatroomRole = chatroomRole;
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

	public Long getIsMute() {
		return this.isMute;
	}

	public void setIsMute(Long isMute) {
		this.isMute = isMute;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"chatroomId='" + chatroomId + '\'' +
					"memberId='" + memberId + '\'' +
					"displayName='" + displayName + '\'' +
					"chatroomRole='" + chatroomRole + '\'' +
					"isDeleted='" + isDeleted + '\'' +
					"groupNickname='" + groupNickname + '\'' +
					"region='" + region + '\'' +
					"phone='" + phone + '\'' +
					"memberDesc='" + memberDesc + '\'' +
					"timestamp='" + timestamp + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
					"isMute='" + isMute + '\'' +
				'}';
	}

}
