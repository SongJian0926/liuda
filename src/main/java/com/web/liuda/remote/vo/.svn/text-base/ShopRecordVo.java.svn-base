package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ShopRecordVo
 * @Description:商品记录 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="商品记录Vo对象")
public class ShopRecordVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="商品Id,商品Id")
	private Long shopId;
	@ApiModelProperty(value="规格Id,规格Id")
	private Long standardId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="购买数量,购买数量")
	private Integer buyNumber;
		
	public ShopRecordVo(Long id,Long shopId,Long standardId,Long userId,Integer buyNumber) {
		this.id = id;
		this.shopId = shopId;
		this.standardId = standardId;
		this.userId = userId;
		this.buyNumber = buyNumber;
	}
	
	public ShopRecordVo() {
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
	
		public void setShopId(Long shopId){
		this.shopId=shopId;
	}
	
	public Long getShopId(){
		return shopId;
	}
		public void setStandardId(Long standardId){
		this.standardId=standardId;
	}
	
	public Long getStandardId(){
		return standardId;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setBuyNumber(Integer buyNumber){
		this.buyNumber=buyNumber;
	}
	
	public Integer getBuyNumber(){
		return buyNumber;
	}
		
}

