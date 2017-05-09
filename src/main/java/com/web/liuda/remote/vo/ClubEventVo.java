package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ClubEventVo
 * @Description:俱乐部活动 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="俱乐部活动Vo对象")
public class ClubEventVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	@ApiModelProperty(value="俱乐部Id，俱乐部Id")
	private Long clubId;
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="活动名称,活动名称")
	private String title;
	@ApiModelProperty(value="活动主图,列表展示用")
	private String logo;
	@ApiModelProperty(value="活动开始时间,开始时间")
	private String starttime;
	@ApiModelProperty(value="活动结束时间,结束时间")
	private String endtime;
	@ApiModelProperty(value="活动状态,1:报名中;2:活动未开始;3:活动进行中;4:已结束")
	private Integer eventStatus;
	@ApiModelProperty(value="活动费用,活动费用/人")
	private Double price;
	@ApiModelProperty(value="报名截止日期,报名截止日期")
	private String deadline;
	@ApiModelProperty(value="最大人数限制,最大人数限制")
	private Integer maxNum;
	@ApiModelProperty(value="活动说明 ,活动说明")
	private String content;
	@ApiModelProperty(value="区域,区域")
	private String areaCode;
	@ApiModelProperty(value="详细地址,详细地址")
	private String address;
	@ApiModelProperty(value="定金,定金")
	private Double disposit;
	@ApiModelProperty(value="图片,图片路径")
	private String mediaPath;
	@ApiModelProperty(value="是否线上核销,1：是，0：否")
	private Integer isOnline;
	@ApiModelProperty(value="报名条件,报名条件")
	private String requirement;
	@ApiModelProperty(value="经度,经度")
	private Double lng;
	@ApiModelProperty(value="纬度,纬度")
	private Double lat;
	
	/*我的活动列表使用
	 * author:changlu
	 * time:2016-04-20 17:20:00
	 */
	@ApiModelProperty(value="活动类型：1，赛事活动；2，俱乐部活动")
	private Integer type;
	@ApiModelProperty(value="订单状态：1:未支付，2:已支付，3:取消报名,4:失效")
	private Integer orderStatus;
	@ApiModelProperty(value="我的活动列表使用，订单Id")
	private Long matchOrderId;
	@ApiModelProperty(value="成员类型：1:部长，2:管理员，3:普通成员")
	private Integer memberType;
	/*
	 * 活动详情使用
	 * author:changlu
	 * time:2016-04-13 14:20:00
	 */
	@ApiModelProperty(value="是否已报名,1：是，0：否")
	private Integer isEnroll;
	@ApiModelProperty(value="是否是该俱乐部成员,1：是，0：否")
	private Integer isJoin;
	@ApiModelProperty(value="是否已关注,1：是，0：否")
	private Integer isCollect;
	@ApiModelProperty(value="俱乐部活动详情使用，关注Id")
	private Long collectId;
	
	public ClubEventVo(Long id,String title,String logo,String starttime,String endtime,Integer eventStatus,Double price,String deadline,Integer maxNum,String content,String address,Double disposit,String mediaPath) {
		this.id = id;
		this.title = title;
		this.logo = logo;
		this.starttime = starttime;
		this.endtime = endtime;
		this.eventStatus = eventStatus;
		this.price = price;
		this.deadline = deadline;
		this.maxNum = maxNum;
		this.content = content;
		this.address = address;
		this.disposit = disposit;
		this.mediaPath = mediaPath;
	}
	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	public ClubEventVo() {
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
	
		public void setTitle(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
		public void setLogo(String logo){
		this.logo=logo;
	}
	
	public String getLogo(){
		return logo;
	}
		public void setStarttime(String starttime){
		this.starttime=starttime;
	}
	
	public String getStarttime(){
		return starttime;
	}
		public void setEndtime(String endtime){
		this.endtime=endtime;
	}
	
	public String getEndtime(){
		return endtime;
	}
	public void setEventStatus(Integer eventStatus){
		this.eventStatus=eventStatus;
	}
	
	public Integer getEventStatus(){
		return eventStatus;
	}
		public void setPrice(Double price){
		this.price=price;
	}
	
	public Double getPrice(){
		return price;
	}
		public void setDeadline(String deadline){
		this.deadline=deadline;
	}
	
	public String getDeadline(){
		return deadline;
	}
		public void setMaxNum(Integer maxNum){
		this.maxNum=maxNum;
	}
	
	public Integer getMaxNum(){
		return maxNum;
	}
		public void setContent(String content){
		this.content=content;
	}
	
	public String getContent(){
		return content;
	}
		public void setAddress(String address){
		this.address=address;
	}
	
	public String getAddress(){
		return address;
	}
		public void setDisposit(Double disposit){
		this.disposit=disposit;
	}
	
	public Double getDisposit(){
		return disposit;
	}
		public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	public String getMediaPath(){
		return mediaPath;
	}
	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Integer getIsEnroll() {
		return isEnroll;
	}
	public void setIsEnroll(Integer isEnroll) {
		this.isEnroll = isEnroll;
	}
	public Integer getIsJoin() {
		return isJoin;
	}
	public void setIsJoin(Integer isJoin) {
		this.isJoin = isJoin;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}	
	public void setLng(Double lng){
		this.lng=lng;
	}
	
	public Double getLng(){
		return lng;
	}
	public void setLat(Double lat){
		this.lat=lat;
	}
	
	public Double getLat(){
		return lat;
	}
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
	public Long getMatchOrderId() {
		return matchOrderId;
	}
	public void setMatchOrderId(Long matchOrderId) {
		this.matchOrderId = matchOrderId;
	}
	public Long getCollectId() {
		return collectId;
	}
	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	
}

