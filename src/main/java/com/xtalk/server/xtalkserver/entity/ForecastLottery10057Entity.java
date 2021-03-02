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
@Table ( name ="forecast_lottery_10057" , schema = "")
public class ForecastLottery10057Entity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "lotCode" )
	private Long lotCode;

   	@Column(name = "preDrawIssue" )
	private Long preDrawIssue;

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

	public ForecastLottery10057Entity() {
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

	public Long getPreDrawIssue() {
		return this.preDrawIssue;
	}

	public void setPreDrawIssue(Long preDrawIssue) {
		this.preDrawIssue = preDrawIssue;
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

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"lotCode='" + lotCode + '\'' +
					"preDrawIssue='" + preDrawIssue + '\'' +
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
				'}';
	}

}
