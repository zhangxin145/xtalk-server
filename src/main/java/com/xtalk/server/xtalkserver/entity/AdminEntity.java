package com.xtalk.server.xtalkserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-21 20:22:07 
 */

@Entity
@Table ( name ="admin" , schema = "")
public class AdminEntity {


	/**
	 * 用户ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 登录名
	 */
   	@Column(name = "account" )
	private String account;

	/**
	 * 姓名
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 头像图片地址——暂时不启用
	 */
   	@Column(name = "head_img_url" )
	private String headImgUrl;

	/**
	 * 手机号码
	 */
   	@Column(name = "mobile" )
	private String mobile;

	/**
	 * 密码加盐-后续优化
	 */
   	@Column(name = "salt" )
	private String salt;

	/**
	 * 登录密码
	 */
	@JsonIgnore
   	@Column(name = "password" )
	private String password;

	/**
	 * 创建时间
	 */
   	@Column(name = "ctime" )
	private Date ctime;

	/**
	 * 创建人
	 */
   	@Column(name = "cid" )
	private Long cid;

	/**
	 * 修改时间
	 */
   	@Column(name = "utime" )
	private Date utime;

	/**
	 * 修改人
	 */
   	@Column(name = "uid" )
	private Long uid;

	/**
	 * 用户状态:0=正常,1=禁用
	 */
   	@Column(name = "status" )
	private Integer status;

	/**
	 * 逻辑删除:0=未删除,1=已删除
	 */
   	@Column(name = "deleted" )
	private Integer deleted;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImgUrl() {
		return this.headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Date getUtime() {
		return this.utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"account='" + account + '\'' +
					"name='" + name + '\'' +
					"headImgUrl='" + headImgUrl + '\'' +
					"mobile='" + mobile + '\'' +
					"salt='" + salt + '\'' +
					"password='" + password + '\'' +
					"ctime='" + ctime + '\'' +
					"cid='" + cid + '\'' +
					"utime='" + utime + '\'' +
					"uid='" + uid + '\'' +
					"status='" + status + '\'' +
					"deleted='" + deleted + '\'' +
				'}';
	}

}
