package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: HotelVo
 * @Description:酒店 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="酒店Vo对象")
public class HotelVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="酒店名称,酒店名称")
	private String hotelName;
	@ApiModelProperty(value="酒店图片,酒店图片")
	private String picurl;
	@ApiModelProperty(value="酒店类型,酒店类型，经济型、快捷型等")
	private String hotelType;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="电话,电话号码")
	private String telphone;
	@ApiModelProperty(value="酒店地址,酒店地址")
	private String address;
	@ApiModelProperty(value="酒店政策,酒店政策")
	private String policy;
	@ApiModelProperty(value="酒店提示,酒店提示")
	private String prompt;
	@ApiModelProperty(value="酒店介绍,酒店介绍")
	private String introduce;
	@ApiModelProperty(value="经度,经度")
	private Double lng;
	@ApiModelProperty(value="纬度,纬度")
	private Double lat;
	@ApiModelProperty(value="价钱,酒店最低价格")
	private Double price;
	@ApiModelProperty(value="评分,酒店评价分")
	private Double score;
	@ApiModelProperty(value="距离,当前用户距离酒店的距离")
	private Integer distance;
	@ApiModelProperty(value="评论数,评论数")
	private Integer comments;
	@ApiModelProperty(value="轮播图,轮播图")
	private String[] pics;
	@ApiModelProperty(value="酒店介绍图片,酒店介绍图片")
	private String[] intropics;
	@ApiModelProperty(value="轮播图,轮播图")
	private List<String> pics1;
	@ApiModelProperty(value="酒店房间,酒店房间")
	private List<RoomVo> rooms;
	
	/*
	 * 移动端订单使用
	 * autor:changlu
	 * time: 2016-01-18 11:49:11
	 */
	@ApiModelProperty(value="房间logo,房间图片")
	private String roomImgForOrder;
	/**
	 * autor:zhanglin
	 * time: 2016-01-02 15:49:11
	 * desc: 个人中心我的评价使用的字段
	 */
	@ApiModelProperty(value="入住时间,个人中心我的评价用")
	private String checkinTime;
	@ApiModelProperty(value="离开时间,个人中心我的评价用")
	private String leaveTime;
	@ApiModelProperty(value="房型,房型")
	private String type;
	@ApiModelProperty(value="间数,几间")
	private Integer number;
	@ApiModelProperty(value="晚数,几晚")
	private Integer dayNumber;
	@ApiModelProperty(value="总价,总价")
	private Double totalPrice;
	
	/**
	 * 团购使用
	 * @return
	 */
	@ApiModelProperty(value="是否团购,0：否、1：是")
	private Integer groupBuy;
	@ApiModelProperty(value="团购价格,团购的最低价格")
	private Double groupPrice;
	
	@ApiModelProperty(value="是否收藏（0：否 、1：是）")
	private Integer isLike;
	
	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public HotelVo(Long id,String hotelName,String picurl,String hotelType,String telphone,String address,String policy,String prompt,String introduce,Double lng,Double lat) {
		this.id = id;
		this.hotelName = hotelName;
		this.picurl = picurl;
		this.hotelType = hotelType;
		this.telphone = telphone;
		this.address = address;
		this.policy = policy;
		this.prompt = prompt;
		this.introduce = introduce;
		this.lng = lng;
		this.lat = lat;
	}
	
	public HotelVo() {
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
	
		public void setHotelName(String hotelName){
		this.hotelName=hotelName;
	}
	
	public String getHotelName(){
		return hotelName;
	}
		public void setPicurl(String picurl){
		this.picurl=picurl;
	}
	
	public String getPicurl(){
		return picurl;
	}
		public void setHotelType(String hotelType){
		this.hotelType=hotelType;
	}
	
	public String getHotelType(){
		return hotelType;
	}
		public void setTelphone(String telphone){
		this.telphone=telphone;
	}
	
	public String getTelphone(){
		return telphone;
	}
		public void setAddress(String address){
		this.address=address;
	}
	
	public String getAddress(){
		return address;
	}
		public void setPolicy(String policy){
		this.policy=policy;
	}
	
	public String getPolicy(){
		return policy;
	}
		public void setPrompt(String prompt){
		this.prompt=prompt;
	}
	
	public String getPrompt(){
		return prompt;
	}
		public void setIntroduce(String introduce){
		this.introduce=introduce;
	}
	
	public String getIntroduce(){
		return introduce;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	public String[] getIntropics() {
		return intropics;
	}

	public void setIntropics(String[] intropics) {
		this.intropics = intropics;
	}

	public List<RoomVo> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomVo> rooms) {
		this.rooms = rooms;
	}

	public List<String> getPics1() {
		return pics1;
	}

	public void setPics1(List<String> pics1) {
		this.pics1 = pics1;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Integer getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(Integer groupBuy) {
		this.groupBuy = groupBuy;
	}

	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}

	public String getRoomImgForOrder() {
		return roomImgForOrder;
	}

	public void setRoomImgForOrder(String roomImgForOrder) {
		this.roomImgForOrder = roomImgForOrder;
	}
		
}

