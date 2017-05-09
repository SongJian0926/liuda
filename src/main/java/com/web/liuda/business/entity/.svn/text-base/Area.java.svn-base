package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Area 
* @Description: 区域表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_area")
@ApiModel(value="区域表定义表")
public class Area extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="城市代码,城市代码")
	private String cityCode;
	@ApiModelProperty(value="区域代码,区域代码")
	private String areaCode;
	@ApiModelProperty(value="区域名称,区域名称")
	private String areaName;
		
	
	public void setCityCode(String cityCode){
		this.cityCode=cityCode;
	}
	
	@Column(nullable=true,length=255)
	public String getCityCode(){
		return cityCode;
	}
	public void setAreaCode(String areaCode){
		this.areaCode=areaCode;
	}
	
	@Column(nullable=true,length=255)
	public String getAreaCode(){
		return areaCode;
	}
	public void setAreaName(String areaName){
		this.areaName=areaName;
	}
	
	@Column(nullable=true,length=50)
	public String getAreaName(){
		return areaName;
	}
		

}
