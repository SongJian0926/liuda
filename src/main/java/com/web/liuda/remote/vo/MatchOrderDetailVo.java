package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MatchOrderDetailVo
 * @Description:赛事订单详细表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事订单详细表Vo对象")
public class MatchOrderDetailVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="真实姓名,真实姓名")
	private String realName;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="性别,0男，1女 ")
	private Integer sex;
	@ApiModelProperty(value="血型,血型")
	private String bloodType;
	@ApiModelProperty(value="身份证号,身份证号")
	private String idCard;
	@ApiModelProperty(value="驾驶证档案号,驾驶证档案号")
	private String profileNum;
	@ApiModelProperty(value="参赛组别,参赛组别")
	private String matchGroup;
	@ApiModelProperty(value="参赛车型,参赛车型")
	private String carModel;
	@ApiModelProperty(value="所属车队,所属车队")
	private String carTeam;
	@ApiModelProperty(value="紧急联系人手机号,紧急联系人手机号")
	private String emeMobile;
	@ApiModelProperty(value="参赛经历 ,参赛经历 ")
	private String experience;
	@ApiModelProperty(value="荣誉,荣誉")
	private String honor;
	@ApiModelProperty(value="小计,小计")
	private Double subAmt;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="赛事订单Id,赛事订单Id")
	private Long matchOrderId;
	@ApiModelProperty(value="车牌号码,车牌号码")
	private String carNumber;
	@ApiModelProperty(value="年龄,年龄")
	private Integer age;
	@ApiModelProperty(value="是否核销,0:否，1:是")
	private Integer isValid;
	@ApiModelProperty(value="兑换码 ")
	private String redeemCode;	
	@ApiModelProperty(value="报名类型,1：赛事报名；2：俱乐部活动报名")
	private Integer type;
	/*
	 * 订单详情
	 * author：changlu
	 * time:2016-04-20 16:49:00
	 */
	@ApiModelProperty(value="报名状态,0.正常状态1.退款申请中，2.退款审核失败，3.退款中，4.退款成功")
	private Integer enrollStatus;
		
	public MatchOrderDetailVo(Long id,String realName,String mobile,Integer sex,String bloodType,String idCard,String profileNum,String matchGroup,String carModel,String carTeam,String emeMobile,String experience,String honor,Double subAmt,Long matchId,Long matchOrderId,String carNumber,Integer age,Integer isValid) {
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
		this.subAmt = subAmt;
		this.matchId = matchId;
		this.matchOrderId = matchOrderId;
		this.carNumber = carNumber;
		this.age = age;
		this.isValid = isValid;
	}
	
	public MatchOrderDetailVo() {
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
		public void setSubAmt(Double subAmt){
		this.subAmt=subAmt;
	}
	
	public Double getSubAmt(){
		return subAmt;
	}
		public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	public Long getMatchId(){
		return matchId;
	}
		public void setMatchOrderId(Long matchOrderId){
		this.matchOrderId=matchOrderId;
	}
	
	public Long getMatchOrderId(){
		return matchOrderId;
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
	
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getRedeemCode() {
		return redeemCode;
	}
	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}

	public Integer getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(Integer enrollStatus) {
		this.enrollStatus = enrollStatus;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}

