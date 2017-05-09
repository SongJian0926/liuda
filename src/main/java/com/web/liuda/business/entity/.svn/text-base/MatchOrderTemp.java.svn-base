package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: MatchOrderTemp 
* @Description: 赛事报名临时表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_matchordertemp")
@ApiModel(value="赛事报名临时表定义表")
public class MatchOrderTemp extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="真实姓名,真实姓名")
	private String realName;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="性别,0:男，1:女")
	private Integer sex;
	@ApiModelProperty(value="血型,血型")
	private String bloodType;
	@ApiModelProperty(value="身份证号 ,身份证号 ")
	private String idCard;
	@ApiModelProperty(value="驾驶证档案号,驾驶证档案号")
	private String profileNum;
	@ApiModelProperty(value="参赛组别,参赛组别")
	private String matchGroup;
	@ApiModelProperty(value="参赛车型,参赛车型")
	private String carModel;
	@ApiModelProperty(value="所属车队,所属车队")
	private String carTeam;
	@ApiModelProperty(value="紧急联系人手机号 ,紧急联系人手机号 ")
	private String emeMobile;
	@ApiModelProperty(value="参赛经历,参赛经历")
	private String experience;
	@ApiModelProperty(value="荣誉,荣誉")
	private String honor;
	@ApiModelProperty(value="车牌号码,车牌号码")
	private String carNumber;
	@ApiModelProperty(value="年龄,年龄")
	private Integer age;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;	
	@ApiModelProperty(value="报名类型,1：赛事报名；2：俱乐部活动报名")
	private Integer type;
	
	public void setRealName(String realName){
		this.realName=realName;
	}
	
	@Column(nullable=true,length=50)
	public String getRealName(){
		return realName;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	@Column(nullable=true,length=50)
	public String getMobile(){
		return mobile;
	}
	public void setSex(Integer sex){
		this.sex=sex;
	}
	
	@Column(nullable=true,length=11)
	public Integer getSex(){
		return sex;
	}
	public void setBloodType(String bloodType){
		this.bloodType=bloodType;
	}
	
	@Column(nullable=true,length=20)
	public String getBloodType(){
		return bloodType;
	}
	public void setIdCard(String idCard){
		this.idCard=idCard;
	}
	
	@Column(nullable=true,length=50)
	public String getIdCard(){
		return idCard;
	}
	public void setProfileNum(String profileNum){
		this.profileNum=profileNum;
	}
	
	@Column(nullable=true,length=50)
	public String getProfileNum(){
		return profileNum;
	}
	public void setMatchGroup(String matchGroup){
		this.matchGroup=matchGroup;
	}
	
	@Column(nullable=true,length=50)
	public String getMatchGroup(){
		return matchGroup;
	}
	public void setCarModel(String carModel){
		this.carModel=carModel;
	}
	
	@Column(nullable=true,length=50)
	public String getCarModel(){
		return carModel;
	}
	public void setCarTeam(String carTeam){
		this.carTeam=carTeam;
	}
	
	@Column(nullable=true,length=50)
	public String getCarTeam(){
		return carTeam;
	}
	public void setEmeMobile(String emeMobile){
		this.emeMobile=emeMobile;
	}
	
	@Column(nullable=true,length=50)
	public String getEmeMobile(){
		return emeMobile;
	}
	public void setExperience(String experience){
		this.experience=experience;
	}
	
	@Column(nullable=true,length=2000)
	public String getExperience(){
		return experience;
	}
	public void setHonor(String honor){
		this.honor=honor;
	}
	
	@Column(nullable=true,length=2000)
	public String getHonor(){
		return honor;
	}
	public void setCarNumber(String carNumber){
		this.carNumber=carNumber;
	}
	
	@Column(nullable=true,length=50)
	public String getCarNumber(){
		return carNumber;
	}
	public void setAge(Integer age){
		this.age=age;
	}
	
	@Column(nullable=true,length=11)
	public Integer getAge(){
		return age;
	}
	@Column(nullable=true,length=20)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(nullable=true,length=20)
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	@Column(nullable=true,length=11)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
		

}
