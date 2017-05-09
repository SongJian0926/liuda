package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Address 
* @Description: 收货地址定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_address")
@ApiModel(value="收货地址定义表")
public class Address extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="收货人,收货人")
	private String consigneeName;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="省份,省份")
	private String province;
	@ApiModelProperty(value="城市,城市")
	private String city;
	@ApiModelProperty(value="区域,区域")
	private String area;
	@ApiModelProperty(value="详细地址,详细地址")
	private String detailAddress;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId ;
	@ApiModelProperty(value="是否默认,是否默认   0：否，1：是")
	private Integer isDefault;
		
	
	public void setConsigneeName(String consigneeName){
		this.consigneeName=consigneeName;
	}
	
	@Column(nullable=false,length=50)
	public String getConsigneeName(){
		return consigneeName;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	@Column(nullable=false,length=50)
	public String getMobile(){
		return mobile;
	}
	public void setProvince(String province){
		this.province=province;
	}
	
	@Column(nullable=false,length=50)
	public String getProvince(){
		return province;
	}
	public void setCity(String city){
		this.city=city;
	}
	
	@Column(nullable=false,length=50)
	public String getCity(){
		return city;
	}
	public void setArea(String area){
		this.area=area;
	}
	
	@Column(nullable=false,length=50)
	public String getArea(){
		return area;
	}
	public void setDetailAddress(String detailAddress){
		this.detailAddress=detailAddress;
	}
	
	@Column(nullable=false,length=50)
	public String getDetailAddress(){
		return detailAddress;
	}
	public void setUserId (Long userId ){
		this.userId =userId ;
	}
	
	@Column(nullable=false,length=50)
	public Long getUserId (){
		return userId ;
	}
	public void setIsDefault(Integer isDefault){
		this.isDefault=isDefault;
	}
	
	@Column(nullable=false,length=50)
	public Integer getIsDefault(){
		return isDefault;
	}
		

}
