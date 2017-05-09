package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: RoomVo
 * @Description:房间 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="房间Vo对象")
public class RoomVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="酒店Id,酒店Id")
	private Long hotelId;
	@ApiModelProperty(value="房间类型,房间类型")
	private String type;
	@ApiModelProperty(value="包含项目,包含项目")
	private String item;
	@ApiModelProperty(value="购买须知,购买须知")
	private String buyNote;
	@ApiModelProperty(value="房间价格,房间价格")
	private Double price;
	@ApiModelProperty(value="房间logo,房间图片")
	private String logo;
	@ApiModelProperty(value="是否含早,是否含早 0：无早；1：单早，2：双早")
	private Integer breakfast;
	@ApiModelProperty(value="是否团购,是否团购，0、否，1、是")
	private Integer groupBuy;
	@ApiModelProperty(value="团购价格,团购价格")
	private Double groupPrice;
	@ApiModelProperty(value="有效期,有效期")
	private String validity;
	@ApiModelProperty(value="面积,面积")
	private Integer area;
	@ApiModelProperty(value="床位,床位")
	private Integer beds;
	@ApiModelProperty(value="浴室,浴室")
	private String bathRoom;
	@ApiModelProperty(value="通讯,通讯")
	private String communication;
	@ApiModelProperty(value="设施,设施（包含各种空调，宽带，吹风机等）")
	private String establishment;
	@ApiModelProperty(value="电话,电话，手机号或电话号码")
	private String telphone;
	@ApiModelProperty(value="酒店地址,酒店地址")
	private String address;
	@ApiModelProperty(value="酒店名称,酒店名称")
	private String hotelName;
	@ApiModelProperty(value = "状态，0为无效，1为正常,2为发布,3删除 参看XaConstant.Status")
	private Integer status;
	
	/*author:yanghui
	 * 2016-01-11
	 * web端显示商品列表(门票或者房间)、图片*/
	private TicketsVo ticketsvo;
	private ImagesVo imagesvo;
	/*author:yanghui
	 * 2016-01-25
	 * 后台显示商品类型，0酒店1*/
	private Integer businessType;
	
	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public RoomVo(Long id,Long hotelId,String type,String item,String buyNote,Double price,String logo,Integer breakfast,Integer groupBuy,Double groupPrice,String validity,Integer area,Integer beds,String bathRoom,String communication,String establishment) {
		this.id = id;
		this.hotelId = hotelId;
		this.type = type;
		this.item = item;
		this.buyNote = buyNote;
		this.price = price;
		this.logo = logo;
		this.breakfast = breakfast;
		this.groupBuy = groupBuy;
		this.groupPrice = groupPrice;
		this.validity = validity;
		this.area = area;
		this.beds = beds;
		this.bathRoom = bathRoom;
		this.communication = communication;
		this.establishment = establishment;
	}
	
	public RoomVo() {
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
	
		public void setHotelId(Long hotelId){
		this.hotelId=hotelId;
	}
	
	public Long getHotelId(){
		return hotelId;
	}
		
	public void setType(String type) {
			this.type = type;
		}

	
	public void setItem(String item){
		this.item=item;
	}
	
	public String getItem(){
		return item;
	}
		public void setBuyNote(String buyNote){
		this.buyNote=buyNote;
	}
	
	public String getBuyNote(){
		return buyNote;
	}
		public void setPrice(Double price){
		this.price=price;
	}
	
	public Double getPrice(){
		return price;
	}
		public void setLogo(String logo){
		this.logo=logo;
	}
	
	public String getLogo(){
		return logo;
	}
		public void setBreakfast(Integer breakfast){
		this.breakfast=breakfast;
	}
	
	public Integer getBreakfast(){
		return breakfast;
	}
		public void setGroupBuy(Integer groupBuy){
		this.groupBuy=groupBuy;
	}
	
	public Integer getGroupBuy(){
		return groupBuy;
	}
		public void setGroupPrice(Double groupPrice){
		this.groupPrice=groupPrice;
	}
	
	public Double getGroupPrice(){
		return groupPrice;
	}
		public void setValidity(String validity){
		this.validity=validity;
	}
	
	public String getValidity(){
		return validity;
	}
		public void setArea(Integer area){
		this.area=area;
	}
	
	public Integer getArea(){
		return area;
	}
		public void setBeds(Integer beds){
		this.beds=beds;
	}
	
	public Integer getBeds(){
		return beds;
	}
		public void setBathRoom(String bathRoom){
		this.bathRoom=bathRoom;
	}
	
	public String getBathRoom(){
		return bathRoom;
	}
		public void setCommunication(String communication){
		this.communication=communication;
	}
	
	public String getCommunication(){
		return communication;
	}
		public void setEstablishment(String establishment){
		this.establishment=establishment;
	}
	
	public String getEstablishment(){
		return establishment;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TicketsVo getTicketsvo() {
		return ticketsvo;
	}

	public void setTicketsvo(TicketsVo ticketsvo) {
		this.ticketsvo = ticketsvo;
	}
	public ImagesVo getImagesvo() {
		return imagesvo;
	}

	public void setImagesvo(ImagesVo imagesvo) {
		this.imagesvo = imagesvo;
	}
	
}

