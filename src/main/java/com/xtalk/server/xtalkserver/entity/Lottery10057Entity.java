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
@Table ( name ="lottery_10057" , schema = "")
public class Lottery10057Entity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "lotCode" )
	private Long lotCode;

   	@Column(name = "lotName" )
	private String lotName;

   	@Column(name = "iconUrl" )
	private String iconUrl;

   	@Column(name = "totalCount" )
	private Long totalCount;

   	@Column(name = "preDrawCode" )
	private String preDrawCode;

   	@Column(name = "drawCount" )
	private Long drawCount;

   	@Column(name = "preDrawDate" )
	private Date preDrawDate;

   	@Column(name = "preDrawTime" )
	private Date preDrawTime;

   	@Column(name = "drawTime" )
	private Date drawTime;

   	@Column(name = "preDrawIssue" )
	private Long preDrawIssue;

   	@Column(name = "drawIssue" )
	private Long drawIssue;

   	@Column(name = "firstNum" )
	private Long firstNum;

   	@Column(name = "secondNum" )
	private Long secondNum;

   	@Column(name = "thirdNum" )
	private Long thirdNum;

   	@Column(name = "fourthNum" )
	private Long fourthNum;

   	@Column(name = "fifthNum" )
	private Long fifthNum;

   	@Column(name = "sixthNum" )
	private Long sixthNum;

   	@Column(name = "seventhNum" )
	private Long seventhNum;

   	@Column(name = "eighthNum" )
	private Long eighthNum;

   	@Column(name = "ninthNum" )
	private Long ninthNum;

   	@Column(name = "tenthNum" )
	private Long tenthNum;

   	@Column(name = "sumFS" )
	private Long sumFs;

   	@Column(name = "sumBigSamll" )
	private Long sumBigSamll;

   	@Column(name = "sumSingleDouble" )
	private Long sumSingleDouble;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

   	@Column(name = "deletedAt" )
	private Date deletedAt;

   	@Column(name = "drawDt" )
	private String drawDt;

   	@Column(name = "status" )
	private String status;

	public Lottery10057Entity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLotCode() {
		return this.lotCode;
	}

	public void setLotCode(Long lotCode) {
		this.lotCode = lotCode;
	}

	public String getLotName() {
		return this.lotName;
	}

	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getPreDrawCode() {
		return this.preDrawCode;
	}

	public void setPreDrawCode(String preDrawCode) {
		this.preDrawCode = preDrawCode;
	}

	public Long getDrawCount() {
		return this.drawCount;
	}

	public void setDrawCount(Long drawCount) {
		this.drawCount = drawCount;
	}

	public Date getPreDrawDate() {
		return this.preDrawDate;
	}

	public void setPreDrawDate(Date preDrawDate) {
		this.preDrawDate = preDrawDate;
	}

	public Date getPreDrawTime() {
		return this.preDrawTime;
	}

	public void setPreDrawTime(Date preDrawTime) {
		this.preDrawTime = preDrawTime;
	}

	public Date getDrawTime() {
		return this.drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	public Long getPreDrawIssue() {
		return this.preDrawIssue;
	}

	public void setPreDrawIssue(Long preDrawIssue) {
		this.preDrawIssue = preDrawIssue;
	}

	public Long getDrawIssue() {
		return this.drawIssue;
	}

	public void setDrawIssue(Long drawIssue) {
		this.drawIssue = drawIssue;
	}

	public Long getFirstNum() {
		return this.firstNum;
	}

	public void setFirstNum(Long firstNum) {
		this.firstNum = firstNum;
	}

	public Long getSecondNum() {
		return this.secondNum;
	}

	public void setSecondNum(Long secondNum) {
		this.secondNum = secondNum;
	}

	public Long getThirdNum() {
		return this.thirdNum;
	}

	public void setThirdNum(Long thirdNum) {
		this.thirdNum = thirdNum;
	}

	public Long getFourthNum() {
		return this.fourthNum;
	}

	public void setFourthNum(Long fourthNum) {
		this.fourthNum = fourthNum;
	}

	public Long getFifthNum() {
		return this.fifthNum;
	}

	public void setFifthNum(Long fifthNum) {
		this.fifthNum = fifthNum;
	}

	public Long getSixthNum() {
		return this.sixthNum;
	}

	public void setSixthNum(Long sixthNum) {
		this.sixthNum = sixthNum;
	}

	public Long getSeventhNum() {
		return this.seventhNum;
	}

	public void setSeventhNum(Long seventhNum) {
		this.seventhNum = seventhNum;
	}

	public Long getEighthNum() {
		return this.eighthNum;
	}

	public void setEighthNum(Long eighthNum) {
		this.eighthNum = eighthNum;
	}

	public Long getNinthNum() {
		return this.ninthNum;
	}

	public void setNinthNum(Long ninthNum) {
		this.ninthNum = ninthNum;
	}

	public Long getTenthNum() {
		return this.tenthNum;
	}

	public void setTenthNum(Long tenthNum) {
		this.tenthNum = tenthNum;
	}

	public Long getSumFs() {
		return this.sumFs;
	}

	public void setSumFs(Long sumFs) {
		this.sumFs = sumFs;
	}

	public Long getSumBigSamll() {
		return this.sumBigSamll;
	}

	public void setSumBigSamll(Long sumBigSamll) {
		this.sumBigSamll = sumBigSamll;
	}

	public Long getSumSingleDouble() {
		return this.sumSingleDouble;
	}

	public void setSumSingleDouble(Long sumSingleDouble) {
		this.sumSingleDouble = sumSingleDouble;
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

	public String getDrawDt() {
		return this.drawDt;
	}

	public void setDrawDt(String drawDt) {
		this.drawDt = drawDt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"lotCode='" + lotCode + '\'' +
					"lotName='" + lotName + '\'' +
					"iconUrl='" + iconUrl + '\'' +
					"totalCount='" + totalCount + '\'' +
					"preDrawCode='" + preDrawCode + '\'' +
					"drawCount='" + drawCount + '\'' +
					"preDrawDate='" + preDrawDate + '\'' +
					"preDrawTime='" + preDrawTime + '\'' +
					"drawTime='" + drawTime + '\'' +
					"preDrawIssue='" + preDrawIssue + '\'' +
					"drawIssue='" + drawIssue + '\'' +
					"firstNum='" + firstNum + '\'' +
					"secondNum='" + secondNum + '\'' +
					"thirdNum='" + thirdNum + '\'' +
					"fourthNum='" + fourthNum + '\'' +
					"fifthNum='" + fifthNum + '\'' +
					"sixthNum='" + sixthNum + '\'' +
					"seventhNum='" + seventhNum + '\'' +
					"eighthNum='" + eighthNum + '\'' +
					"ninthNum='" + ninthNum + '\'' +
					"tenthNum='" + tenthNum + '\'' +
					"sumFs='" + sumFs + '\'' +
					"sumBigSamll='" + sumBigSamll + '\'' +
					"sumSingleDouble='" + sumSingleDouble + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
					"deletedAt='" + deletedAt + '\'' +
					"drawDt='" + drawDt + '\'' +
					"status='" + status + '\'' +
				'}';
	}

}
