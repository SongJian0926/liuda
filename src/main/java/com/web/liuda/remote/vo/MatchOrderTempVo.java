package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MatchOrderTempVo
 * @Description:赛事报名临时表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事报名临时表Vo对象")
public class MatchOrderTempVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
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
		
	public MatchOrderTempVo(Long id,String realName,String mobile,Integer sex,String bloodType,String idCard,String profileNum,String matchGroup,String carModel,String carTeam,String emeMobile,String experience,String honor,String carNumber,Integer age) {
		this.id = id;
		this.realName = realName;
		this.mobile = mobile;
		this.sex = sex;
		this.bloodType = bloodType;
		this.idCard = idCard;
		this.profileNum = profileNum;
		this.matchGroup = matchGroup;
		this.carModel = carModel;
		this.carTeam = carTeam;
		this.emeMobile = emeMobile;
		this.experience = experience;
		this.honor = honor;
		this.carNumber = carNumber;
		this.age = age;
	}
	
	public MatchOrderTempVo() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
		public void setRealName(String realName){
		this.realName=realName;
	}
	
	public String getRealName(){
		return realName;
	}
		public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
		public void setSex(Integer sex){
		this.sex=sex;
	}
	
	public Integer getSex(){
		return sex;
	}
		public void setBloodType(String bloodType){
		this.bloodType=bloodType;
	}
	
	public String getBloodType(){
		return bloodType;
	}
		public void setIdCard(String idCard){
		this.idCard=idCard;
	}
	
	public String getIdCard(){
		return idCard;
	}
		public void setProfileNum(String profileNum){
		this.profileNum=profileNum;
	}
	
	public String getProfileNum(){
		return profileNum;
	}
		public void setMatchGroup(String matchGroup){
		this.matchGroup=matchGroup;
	}
	
	public String getMatchGroup(){
		return matchGroup;
	}
		public void setCarModel(String carModel){
		this.carModel=carModel;
	}
	
	public String getCarModel(){
		return carModel;
	}
		public void setCarTeam(String carTeam){
		this.carTeam=carTeam;
	}
	
	public String getCarTeam(){
		return carTeam;
	}
		public void setEmeMobile(String emeMobile){
		this.emeMobile=emeMobile;
	}
	
	public String getEmeMobile(){
		return emeMobile;
	}
		public void setExperience(String experience){
		this.experience=experience;
	}
	
	public String getExperience(){
		return experience;
	}
		public void setHonor(String honor){
		this.honor=honor;
	}
	
	public String getHonor(){
		return honor;
	}
		public void setCarNumber(String carNumber){
		this.carNumber=carNumber;
	}
	
	public String getCarNumber(){
		return carNumber;
	}
		public void setAge(Integer age){
		this.age=age;
	}
	
	public Integer getAge(){
		return age;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
		
}

