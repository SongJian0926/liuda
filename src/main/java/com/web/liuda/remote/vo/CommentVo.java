package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: CommentVo
 * @Description:评论 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="评论Vo对象")
public class CommentVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="评论内容,评论内容")
	private String content;
	@ApiModelProperty(value="评论类型,评论类型，0：酒店 1：景点 2：商品")
	private Integer type;
	@ApiModelProperty(value="对象Id,对象Id（酒店Id、景点Id、商品Id）")
	private Long objectId;
	@ApiModelProperty(value="评论分数,评论分数")
	private Float score;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="用户名,用户名")
	private String userName;
	@ApiModelProperty(value="用户头像,用户头像")
	private String photo;
	@ApiModelProperty(value="评论图片,评论图片")
	private String[] pics;
	@ApiModelProperty(value="酒店信息,酒店信息")
	private HotelVo hotel;
	@ApiModelProperty(value="景点信息,景点信息")
	private TouristVo tourist;
	@ApiModelProperty(value="商品信息,商品信息")
	private ShopVo shop;
	@ApiModelProperty(value="状态,状态，1：正常 2：发布 3：删除 ")
	private Integer status;
	@ApiModelProperty(value="对象名称,对象名称")
	private String objectName;
	/*
	 * author:changlu
	 * time:2016-01-13 11:00:00
	 * pc端评论管理使用
	 */
	private RoomVo roomVo;//房间
	private HotelVo hotelVo;//酒店
	private TicketsVo ticketsVo;//门票
	private TouristVo touristVo;//景点
	private ImagesVo imagesVo;//图片
	private ImagesVo imagesVo1;//图片1
	private ReplyCommentVo replyCommentVo;//回复评论
	private OrderVo orderVo;//订单
	private UserVo userVo;//用户
	/*   结束    */
	@ApiModelProperty(value="app端使用，商家回复内容")
	private String replyComment;
	@ApiModelProperty(value="app端使用，商家回复时间")
	private String replyTime;
	
	@ApiModelProperty(value="商品规格Id,商品规格Id")
	private Long standardId;	
	
	public CommentVo(Long id,String content,Integer type,Long objectId,Float score,Long userId) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.objectId = objectId;
		this.score = score;
		this.userId = userId;
	}
	
	public CommentVo() {
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
	
		public void setContent(String content){
		this.content=content;
	}
	
	public String getContent(){
		return content;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}
		public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	public Long getObjectId(){
		return objectId;
	}
		public void setScore(Float score){
		this.score=score;
	}
	
	public Float getScore(){
		return score;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	public HotelVo getHotel() {
		return hotel;
	}

	public void setHotel(HotelVo hotel) {
		this.hotel = hotel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public TouristVo getTourist() {
		return tourist;
	}

	public void setTourist(TouristVo tourist) {
		this.tourist = tourist;
	}

	public ShopVo getShop() {
		return shop;
	}

	public void setShop(ShopVo shop) {
		this.shop = shop;
	}

	public RoomVo getRoomVo() {
		return roomVo;
	}

	public void setRoomVo(RoomVo roomVo) {
		this.roomVo = roomVo;
	}

	public HotelVo getHotelVo() {
		return hotelVo;
	}

	public void setHotelVo(HotelVo hotelVo) {
		this.hotelVo = hotelVo;
	}

	public TicketsVo getTicketsVo() {
		return ticketsVo;
	}

	public void setTicketsVo(TicketsVo ticketsVo) {
		this.ticketsVo = ticketsVo;
	}

	public TouristVo getTouristVo() {
		return touristVo;
	}

	public void setTouristVo(TouristVo touristVo) {
		this.touristVo = touristVo;
	}

	public ImagesVo getImagesVo() {
		return imagesVo;
	}

	public void setImagesVo(ImagesVo imagesVo) {
		this.imagesVo = imagesVo;
	}

	public ReplyCommentVo getReplyCommentVo() {
		return replyCommentVo;
	}

	public void setReplyCommentVo(ReplyCommentVo replyCommentVo) {
		this.replyCommentVo = replyCommentVo;
	}

	public OrderVo getOrderVo() {
		return orderVo;
	}

	public void setOrderVo(OrderVo orderVo) {
		this.orderVo = orderVo;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public ImagesVo getImagesVo1() {
		return imagesVo1;
	}

	public void setImagesVo1(ImagesVo imagesVo1) {
		this.imagesVo1 = imagesVo1;
	}

	public String getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}	
}

