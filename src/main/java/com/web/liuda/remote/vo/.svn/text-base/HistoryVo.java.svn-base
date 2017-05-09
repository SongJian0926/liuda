package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: HistoryVo
 * @Description:收藏 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="收藏Vo对象")
public class HistoryVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="对象Id,对象Id")
	private Long objectId;
	@ApiModelProperty(value="类型,类型（0：酒店 1：景点 2：商品）")
	private Integer type;
	@ApiModelProperty(value="酒店信息,酒店信息")
	private HotelVo hotel;
	@ApiModelProperty(value="景点信息,景点信息")
	private TouristVo tourist;
	@ApiModelProperty(value="商品信息,商品信息")
	private ShopVo shop;
	@ApiModelProperty(value="距离,距离（酒店和景点实体用）")
	private Integer distance;
		

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public HistoryVo(Long id,Long userId,Long objectId,Integer type) {
		this.id = id;
		this.userId = userId;
		this.objectId = objectId;
		this.type = type;
	}
	
	public HistoryVo() {
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
	
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	public Long getObjectId(){
		return objectId;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}

	public HotelVo getHotel() {
		return hotel;
	}

	public void setHotel(HotelVo hotel) {
		this.hotel = hotel;
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
		
}

