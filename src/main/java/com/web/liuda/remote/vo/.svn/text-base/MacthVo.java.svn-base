package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MacthVo
 * @Description:赛事表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事表Vo对象")
public class MacthVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="赛事标题,赛事标题")
	private String title;
	@ApiModelProperty(value="开始时间 ,开始时间 ")
	private String startdate;
	@ApiModelProperty(value="结束时间,结束时间")
	private String enddate;
	@ApiModelProperty(value="赛事状态,1.报名中，2.车手报道 ，3.比赛中，4.已结束")
	private Integer matchStatus;
	@ApiModelProperty(value="赛事类型,1.商家赛事、2.官方活动")
	private Integer matchType;
	@ApiModelProperty(value="赛事来源,0.表示官方，其他表示：商户id")
	private Long merchantId;
	@ApiModelProperty(value="赛事图片,赛事图片")
	private String mediaPath;
	@ApiModelProperty(value="赛事头像,赛事头像")
	private String logo;
	@ApiModelProperty(value="参赛费用,参赛费用")
	private Double price;
	@ApiModelProperty(value="定金,定金")
	private Double deposit;
	@ApiModelProperty(value="最大参赛人数,最大参赛人数")
	private Integer maxNum;
	@ApiModelProperty(value="活动说明,活动说明")
	private String content;
	@ApiModelProperty(value="门票图片,门票图片")
	private String ticketImages;
	@ApiModelProperty(value="门票名称,门票名称")
	private String ticketName;
	@ApiModelProperty(value="门票数量,门票数量")
	private Integer ticketStockNum;
	@ApiModelProperty(value="门票价格,门票价格")
	private Double ticketPrice;
	@ApiModelProperty(value="优惠价格,优惠价格")
	private Double favourablePrice;
	@ApiModelProperty(value="优惠开始时间,优惠开始时间 ")
	private String favStartTime;
	@ApiModelProperty(value="优惠结束时间 ,优惠结束时间 ")
	private String favEndTime;
	@ApiModelProperty(value="抽奖状态,0表示未抽奖，1，表示已经抽过奖，2表示没有二次抽奖环节 ")
	private Integer luckDraw;
	@ApiModelProperty(value="地址,地址")
	private String address;
	@ApiModelProperty(value="经度 ,经度 ")
	private Double lng;
	@ApiModelProperty(value="纬度 ,纬度 ")
	private Double lat;
	@ApiModelProperty(value="参赛报名截止日,参赛报名截止日")
	private String deadline;
	@ApiModelProperty(value="观赛报名截止日,观赛报名截止日")
	private String deadlineView;
	@ApiModelProperty(value="是否线上核销,1：是，0：否")
	private Integer isOnline;	
	@ApiModelProperty(value="报告条件,报告条件")
	private String condition;
	@ApiModelProperty(value="兴趣标签,取至字典表")
	private Long dictItemId;
	@ApiModelProperty(value="门票总数,门票总数")
	private Integer ticketTotleNum;
	@ApiModelProperty(value="区域代码,区域代码")
	private String areaCode;
	@ApiModelProperty(value="区域Id,区域Id")
	private Long areaId;
	@ApiModelProperty(value="url,url")
	private String url;
	@ApiModelProperty(value="竞猜,竞猜")
	private GuessVo guess;
	
	@ApiModelProperty(value="是否已关注,1：是，0：否")
	private Integer isCollect;
	@ApiModelProperty(value="关注ID")
	private Long collectId;
		
	public MacthVo(Long id,String title,String startdate,String enddate,Integer matchStatus,Integer matchType,Long merchantId,String mediaPath,Double price,Double deposit,Integer maxNum,String content,String ticketImages,String ticketName,Integer ticketStockNum,Double ticketPrice,Double favourablePrice,String favStartTime,String favEndTime,Integer luckDraw,String address,Double lng,Double lat,String deadline,String deadlineView) {
		this.id = id;
		this.title = title;
		this.startdate = startdate;
		this.enddate = enddate;
		this.matchStatus = matchStatus;
		this.matchType = matchType;
		this.merchantId = merchantId;
		this.mediaPath = mediaPath;
		this.price = price;
		this.deposit = deposit;
		this.maxNum = maxNum;
		this.content = content;
		this.ticketImages = ticketImages;
		this.ticketName = ticketName;
		this.ticketStockNum = ticketStockNum;
		this.ticketPrice = ticketPrice;
		this.favourablePrice = favourablePrice;
		this.favStartTime = favStartTime;
		this.favEndTime = favEndTime;
		this.luckDraw = luckDraw;
		this.address = address;
		this.lng = lng;
		this.lat = lat;
		this.deadline = deadline;
		this.deadlineView = deadlineView;
	}
	
	public MacthVo() {
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
		public void setStartdate(String startdate){
		this.startdate=startdate;
	}
	
	public String getStartdate(){
		return startdate;
	}
		public void setEnddate(String enddate){
		this.enddate=enddate;
	}
	
	public String getEnddate(){
		return enddate;
	}
		public void setMatchStatus(Integer matchStatus){
		this.matchStatus=matchStatus;
	}
	
	public Integer getMatchStatus(){
		return matchStatus;
	}
		public void setMatchType(Integer matchType){
		this.matchType=matchType;
	}
	
	public Integer getMatchType(){
		return matchType;
	}
		public void setMerchantId(Long merchantId){
		this.merchantId=merchantId;
	}
	
	public Long getMerchantId(){
		return merchantId;
	}
		public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	public String getMediaPath(){
		return mediaPath;
	}
		public void setPrice(Double price){
		this.price=price;
	}
	
	public Double getPrice(){
		return price;
	}
		public void setDeposit(Double deposit){
		this.deposit=deposit;
	}
	
	public Double getDeposit(){
		return deposit;
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
		public void setTicketImages(String ticketImages){
		this.ticketImages=ticketImages;
	}
	
	public String getTicketImages(){
		return ticketImages;
	}
		public void setTicketName(String ticketName){
		this.ticketName=ticketName;
	}
	
	public String getTicketName(){
		return ticketName;
	}
		public void setTicketStockNum(Integer ticketStockNum){
		this.ticketStockNum=ticketStockNum;
	}
	
	public Integer getTicketStockNum(){
		return ticketStockNum;
	}
		public void setTicketPrice(Double ticketPrice){
		this.ticketPrice=ticketPrice;
	}
	
	public Double getTicketPrice(){
		return ticketPrice;
	}
		public void setFavourablePrice(Double favourablePrice){
		this.favourablePrice=favourablePrice;
	}
	
	public Double getFavourablePrice(){
		return favourablePrice;
	}
		public void setFavStartTime(String favStartTime){
		this.favStartTime=favStartTime;
	}
	
	public String getFavStartTime(){
		return favStartTime;
	}
		public void setFavEndTime(String favEndTime){
		this.favEndTime=favEndTime;
	}
	
	public String getFavEndTime(){
		return favEndTime;
	}
		public void setLuckDraw(Integer luckDraw){
		this.luckDraw=luckDraw;
	}
	
	public Integer getLuckDraw(){
		return luckDraw;
	}
		public void setAddress(String address){
		this.address=address;
	}
	
	public String getAddress(){
		return address;
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
		public void setDeadline(String deadline){
		this.deadline=deadline;
	}
	
	public String getDeadline(){
		return deadline;
	}
		public void setDeadlineView(String deadlineView){
		this.deadlineView=deadlineView;
	}
	
	public String getDeadlineView(){
		return deadlineView;
	}
	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}	
	public Long getDictItemId() {
		return dictItemId;
	}

	public void setDictItemId(Long dictItemId) {
		this.dictItemId = dictItemId;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getTicketTotleNum() {
		return ticketTotleNum;
	}

	public void setTicketTotleNum(Integer ticketTotleNum) {
		this.ticketTotleNum = ticketTotleNum;
	}

	public Long getCollectId() {
		return collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public GuessVo getGuess() {
		return guess;
	}

	public void setGuess(GuessVo guess) {
		this.guess = guess;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

