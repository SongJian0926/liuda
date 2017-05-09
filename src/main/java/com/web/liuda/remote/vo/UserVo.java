package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: UserVo
 * @Description:用户 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="用户Vo对象")
public class UserVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户名,用户名")
	private String userName;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="头像,头像")
	private String photo;
	
	@ApiModelProperty(value="极光别名Id,极光别名Id")
	private String jpushKey;
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
	@ApiModelProperty(value="通过指定要求的邀请码注册成功的人数")
	private Integer poperNum;
	@ApiModelProperty(value="通过指定要求的邀请码获取的收益")
	private Double earnings;
	@ApiModelProperty(value="积分,积分")
	private Integer points;
	
	public UserVo(Long id,String userName,String mobile,String photo,String jpushKey) {
		this.id = id;
		this.userName = userName;
		this.mobile = mobile;
		this.photo = photo;
		this.jpushKey=jpushKey;
	}
	
	public UserVo(Long id,String userName,String photo) {
		this.id = id;
		this.userName = userName;
		this.photo = photo;
	}
	
	public UserVo() {
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
	
		public void setUserName(String userName){
		this.userName=userName;
	}
	
	public Double getEarnings() {
			return earnings;
		}

		public void setEarnings(Double earnings) {
			this.earnings = earnings;
		}

	public String getUserName(){
		return userName;
	}
		public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	public void setInviteCode(String inviteCode) {
			this.inviteCode = inviteCode;
		}

	public String getMobile(){
		return mobile;
	}
		public void setPhoto(String photo){
		this.photo=photo;
	}
	
	public Integer getPoperNum() {
			return poperNum;
		}

		public void setPoperNum(Integer poperNum) {
			this.poperNum = poperNum;
		}

	public String getPhoto(){
		return photo;
	}

	public String getJpushKey() {
		return jpushKey;
	}

	public void setJpushKey(String jpushKey) {
		this.jpushKey = jpushKey;
	}
	public String getInviteCode(){
		return inviteCode;
	}
		public void setExclusiveCode(String exclusiveCode){
		this.exclusiveCode=exclusiveCode;
	}
	
	public String getExclusiveCode(){
		return exclusiveCode;
	}
		public void setBigShot(Integer bigShot){
		this.bigShot=bigShot;
	}
	
	public Integer getBigShot(){
		return bigShot;
	}
		public void setEmail(String email){
		this.email=email;
	}
	
	public String getEmail(){
		return email;
	}
		public void setBalance(Double balance){
		this. balance= balance;
	}
	
	public Double getBalance(){
		return  balance;
	}
	public void setThridWchat(String thridWchat){
		this. thridWchat= thridWchat;
	}
	
	public String getThridWchat(){
		return  thridWchat;
	}
	public void setThridQq(String thridQq){
		this.thridQq=thridQq;
	}
	
	public String getThridQq(){
		return thridQq;
	}
	public void setThridWeibo(String thridWeibo){
		this.thridWeibo=thridWeibo;
	}
	
	public String getThridWeibo(){
		return thridWeibo;
	}
	public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	public Integer getApplyStatus(){
		return applyStatus;
	}
	public void setMemo(String memo){
		this.memo=memo;
	}
	
	public String getMemo(){
		return memo;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
		
}

