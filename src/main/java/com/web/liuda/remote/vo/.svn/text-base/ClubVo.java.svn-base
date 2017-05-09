package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ClubVo
 * @Description:俱乐部 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="俱乐部Vo对象")
public class ClubVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="名称,俱乐部名称")
	private String title;
	@ApiModelProperty(value="头像,俱乐部头像")
	private String logo;
	@ApiModelProperty(value="类型,俱乐部类型（取至字典表） ")
	private Long type;
	@ApiModelProperty(value="等级,俱乐部等级（取至字典表） ")
	private Long level;
	@ApiModelProperty(value="地址,地址")
	private String address;
	@ApiModelProperty(value="排序值,排序值")
	private Integer sort;
	@ApiModelProperty(value="经度,经度")
	private Double lng;
	@ApiModelProperty(value="纬度,纬度")
	private Double lat;
	@ApiModelProperty(value="简介,俱乐部简介")
	private String content;
	@ApiModelProperty(value="图片,俱乐部图片")
	private String mediaPath;
	@ApiModelProperty(value="所属区域 ,所属区域 ")
	private String areaCode;
	@ApiModelProperty(value="审核状态,1.审核中;2.审核成功;3.审核失败 ")
	private Integer applyStatus;
	@ApiModelProperty(value="是否推荐,1.是;0.否 ")
	private Integer isRecommend;
	@ApiModelProperty(value="兴趣标签,取至字典表 ")
	private Long interest;
	@ApiModelProperty(value="区域Id,区域Id")
	private Long areaId;
	@ApiModelProperty(value="是否用户创建,1：是，0：否")
	private Integer isUserAdd;	
	/**
	 * 江湖列表使用
	 *author：changlu
	 *time:2016-04-11 15:03:00
	 */
	@ApiModelProperty(value="1：已加入，0：未加入 ")
	private Integer isJoin;
	@ApiModelProperty(value="字典实体，俱乐部类型 ")
	private DictItemVo clubTypeVo;
	@ApiModelProperty(value="字典实体，俱乐部等级 ")
	private DictItemVo clubLevelVo;
	@ApiModelProperty(value="字典实体，俱乐部兴趣标签 ")
	private DictItemVo interestVo;
	@ApiModelProperty(value="当前位置距离俱乐部的距离 ")
	private Integer distance;
	
	/**
	 * 个人主页使用
	 *author：changlu
	 *time:2016-04-11 15:03:00
	 */
	@ApiModelProperty(value="成员类型,1.部长，2.管理员，3.普通成员")
	private Integer memberType;
	//俱乐部详情使用
	@ApiModelProperty(value="是否有成员加入,1.是，0.否")
	private Integer newMember;
	@ApiModelProperty(value="俱乐部成员数量")
	private Integer memberNum;
	@ApiModelProperty(value="俱乐部活动数量")
	private Integer eventNum;
	//我的俱乐部使用
	@ApiModelProperty(value="我创建的俱乐部集合")
	private List<ClubVo> myCreateClubVos;
	@ApiModelProperty(value="新俱乐部集合")
	private List<ClubVo> newClubVos;
	@ApiModelProperty(value="已加入俱乐部集合")
	private List<ClubVo> joinClubVos;
	@ApiModelProperty(value="俱乐部成员Id")
	private Long clubMemberId;
	
	@ApiModelProperty(value="web端俱乐部详情使用，俱乐部成员集合")
	private List<ClubMemberVo> clubMembers;
	@ApiModelProperty(value="web端俱乐部详情使用，活动集合")
	private List<ClubEventVo> clubEvents;

		
	public ClubVo(Long id,String title,String logo,Long type,Long level,String address,Integer sort,Double lng,Double lat,String content,String mediaPath,String areaCode,Integer applyStatus) {
		this.id = id;
		this.title = title;
		this.logo = logo;
		this.type = type;
		this.level = level;
		this.address = address;
		this.sort = sort;
		this.lng = lng;
		this.lat = lat;
		this.content = content;
		this.mediaPath = mediaPath;
		this.areaCode = areaCode;
		this.applyStatus = applyStatus;
	}
	
	public ClubVo() {
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
		public void setType(Long type){
		this.type=type;
	}
	
	public Long getType(){
		return type;
	}
	public void setGrade(Long level){
		this.level=level;
	}
	
	public Long getLevel(){
		return level;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getAddress(){
		return address;
	}
		public void setSort(Integer sort){
		this.sort=sort;
	}
	
	public Integer getSort(){
		return sort;
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
		public void setContent(String content){
		this.content=content;
	}
	
	public String getContent(){
		return content;
	}
		public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	public String getMediaPath(){
		return mediaPath;
	}
		public void setAreaCode(String areaCode){
		this.areaCode=areaCode;
	}
	
	public String getAreaCode(){
		return areaCode;
	}
		public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	public Integer getApplyStatus(){
		return applyStatus;
	}
	public void setIsRecommend(Integer isRecommend){
		this.isRecommend=isRecommend;
	}
	
	public Integer getIsRecommend(){
		return isRecommend;
	}	
	
	public Integer getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(Integer isJoin) {
		this.isJoin = isJoin;
	}

	public DictItemVo getClubTypeVo() {
		return clubTypeVo;
	}

	public void setClubTypeVo(DictItemVo clubTypeVo) {
		this.clubTypeVo = clubTypeVo;
	}

	public DictItemVo getClubLevelVo() {
		return clubLevelVo;
	}

	public void setClubLevelVo(DictItemVo clubLevelVo) {
		this.clubLevelVo = clubLevelVo;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getInterest() {
		return interest;
	}

	public void setInterest(Long interest) {
		this.interest = interest;
	}

	public Integer getNewMember() {
		return newMember;
	}

	public void setNewMember(Integer newMember) {
		this.newMember = newMember;
	}


	public List<ClubVo> getMyCreateClubVos() {
		return myCreateClubVos;
	}

	public void setMyCreateClubVos(List<ClubVo> myCreateClubVos) {
		this.myCreateClubVos = myCreateClubVos;
	}

	public List<ClubVo> getNewClubVos() {
		return newClubVos;
	}

	public void setNewClubVos(List<ClubVo> newClubVos) {
		this.newClubVos = newClubVos;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public List<ClubVo> getJoinClubVos() {
		return joinClubVos;
	}

	public void setJoinClubVos(List<ClubVo> joinClubVos) {
		this.joinClubVos = joinClubVos;
	}

	public Long getClubMemberId() {
		return clubMemberId;
	}

	public void setClubMemberId(Long clubMemberId) {
		this.clubMemberId = clubMemberId;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public Integer getEventNum() {
		return eventNum;
	}

	public void setEventNum(Integer eventNum) {
		this.eventNum = eventNum;
	}

	public List<ClubMemberVo> getClubMembers() {
		return clubMembers;
	}

	public void setClubMembers(List<ClubMemberVo> clubMembers) {
		this.clubMembers = clubMembers;
	}

	public List<ClubEventVo> getClubEvents() {
		return clubEvents;
	}

	public void setClubEvents(List<ClubEventVo> clubEvents) {
		this.clubEvents = clubEvents;
	}

	public DictItemVo getInterestVo() {
		return interestVo;
	}

	public void setInterestVo(DictItemVo interestVo) {
		this.interestVo = interestVo;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Integer getIsUserAdd() {
		return isUserAdd;
	}

	public void setIsUserAdd(Integer isUserAdd) {
		this.isUserAdd = isUserAdd;
	}



}

