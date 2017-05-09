package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: City 
* @Description: 城市表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_city")
@ApiModel(value="城市表定义表")
public class City extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="省份代码,省份代码")
	private String provinceCode;
	@ApiModelProperty(value="城市代码,城市代码")
	private String cityCode;
	@ApiModelProperty(value="城市名称,城市名称")
	private String cityName;
		
	
	public void setProvinceCode(String provinceCode){
		this.provinceCode=provinceCode;
	}
	
	@Column(nullable=true,length=255)
	public String getProvinceCode(){
		return provinceCode;
	}
	public void setCityCode(String cityCode){
		this.cityCode=cityCode;
	}
	
	@Column(nullable=true,length=255)
	public String getCityCode(){
		return cityCode;
	}
	public void setCityName(String cityName){
		this.cityName=cityName;
	}
	
	@Column(nullable=true,length=255)
	public String getCityName(){
		return cityName;
	}
		

}
