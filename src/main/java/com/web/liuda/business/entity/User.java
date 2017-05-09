package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;
import com.web.webstart.base.util.XaUtil;

/**
 * 
* @ClassName: User 
* @Description: 用户定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_user")
@ApiModel(value="用户定义表")
public class User extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户名,用户名")
	private String userName;
	@ApiModelProperty(value="密码,密码")
	private String password;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="头像,头像")
	private String photo;
	@ApiModelProperty(value="推送标识,推送标识")
	private String jpushKey = XaUtil.generateRandomCharAndNumber(16);
	
	/**
	 * 二期增加的字段
	 */
	@ApiModelProperty(value="邀请人的专属代码,只有大咖和俱乐部部长有")
	private String inviteCode;
	@ApiModelProperty(value="邀请码,自己拥有的")
	private String exclusiveCode;
	@ApiModelProperty(value="是否是大咖,1表示大咖，其他表示不是大咖 ")
	private Integer bigShot;
	@ApiModelProperty(value="邮箱,邮箱绑定，用于找回密码时，用邮箱收取验证码 ")
	private String email;
	@ApiModelProperty(value="账户余额,余额")
	private Double  balance;
	@ApiModelProperty(value="微信,第三方登录微信识别码 ")
	private String  thridWchat;
	@ApiModelProperty(value="qq,第三方登录QQ识别码")
	private String thridQq;
	@ApiModelProperty(value="微博,第三方登录微博识别码 ")
	private String thridWeibo;
	@ApiModelProperty(value="大咖审核标记,1.提交审核（审核中），2.审核通过，3.审核失败")
	private Integer applyStatus;
	@ApiModelProperty(value="备注,备注")
	private String memo;
	@ApiModelProperty(value="积分,积分")
	private Integer points;
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	
	@Column(nullable=true,length=50)
	public String getUserName(){
		return userName;
	}
	public void setPassword(String password){
		this.password=password;
	}
	
	@Column(nullable=false,length=50)
	public String getPassword(){
		return password;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	@Column(nullable=false,length=11)
	public String getMobile(){
		return mobile;
	}
	public void setPhoto(String photo){
		this.photo=photo;
	}
	
	@Column(nullable=true,length=255)
	public String getPhoto(){
		return photo;
	}
	@Column(nullable=true,length=50)
	public String getJpushKey() {
		return jpushKey;
	}

	public void setJpushKey(String jpushKey) {
		this.jpushKey = jpushKey;
	}
	public void setInviteCode(String inviteCode){
		this.inviteCode=inviteCode;
	}
	
	@Column(nullable=true,length=255)
	public String getInviteCode(){
		return inviteCode;
	}
	public void setExclusiveCode(String exclusiveCode){
		this.exclusiveCode=exclusiveCode;
	}
	
	@Column(nullable=true,length=255)
	public String getExclusiveCode(){
		return exclusiveCode;
	}
	public void setBigShot(Integer bigShot){
		this.bigShot=bigShot;
	}
	
	@Column(nullable=true,length=11)
	public Integer getBigShot(){
		return bigShot;
	}
	public void setEmail(String email){
		this.email=email;
	}
	
	@Column(nullable=true,length=50)
	public String getEmail(){
		return email;
	}
	public void setBalance(Double balance){
		this. balance= balance;
	}
	
	@Column(nullable=true,length=18)
	public Double getBalance(){
		return  balance;
	}
	public void setThridWchat(String thridWchat){
		this.thridWchat=thridWchat;
	}
	
	@Column(nullable=true,length=100)
	public String getThridWchat(){
		return thridWchat;
	}
	public void setThridQq(String thridQq){
		this.thridQq=thridQq;
	}
	
	@Column(nullable=true,length=100)
	public String getThridQq(){
		return thridQq;
	}
	public void setThridWeibo(String thridWeibo){
		this.thridWeibo=thridWeibo;
	}
	
	@Column(nullable=true,length=100)
	public String getThridWeibo(){
		return thridWeibo;
	}
	public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	@Column(nullable=true,length=11)
	public Integer getApplyStatus(){
		return applyStatus;
	}
	public void setMemo(String memo){
		this.memo=memo;
	}
	
	@Column(nullable=true,length=255)
	public String getMemo(){
		return memo;
	}

	@Column(nullable=true,length=11)
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}		

}
