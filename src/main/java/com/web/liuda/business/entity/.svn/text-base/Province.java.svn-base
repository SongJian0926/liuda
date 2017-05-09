package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Province 
* @Description: 省份表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_province")
@ApiModel(value="省份表定义表")
public class Province extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="省份代码,省份代码")
	private String provinceCode;
	@ApiModelProperty(value="省份名称,省份名称")
	private String provinceName;
		
	
	public void setProvinceCode(String provinceCode){
		this.provinceCode=provinceCode;
	}
	
	@Column(nullable=true,length=255)
	public String getProvinceCode(){
		return provinceCode;
	}
	public void setProvinceName(String provinceName){
		this.provinceName=provinceName;
	}
	
	@Column(nullable=true,length=255)
	public String getProvinceName(){
		return provinceName;
	}
		

}
