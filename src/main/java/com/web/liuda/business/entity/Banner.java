package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Banner 
* @Description: Banner图定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_banner")
@ApiModel(value="Banner图定义表")
public class Banner extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="图片,图片地址")
	private String picurl;
	@ApiModelProperty(value="链接地址,链接地址")
	private String linkurl;
	@ApiModelProperty(value="类型,类型")
	private Integer type;
	@ApiModelProperty(value="排序值,排序值")
	private Integer sort;
		
	
	public void setPicurl(String picurl){
		this.picurl=picurl;
	}
	
	@Column(nullable=false,length=255)
	public String getPicurl(){
		return picurl;
	}
	public void setLinkurl(String linkurl){
		this.linkurl=linkurl;
	}
	
	@Column(nullable=true,length=255)
	public String getLinkurl(){
		return linkurl;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=true,length=50)
	public Integer getType(){
		return type;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	
	@Column(nullable=true,length=50)
	public Integer getSort(){
		return sort;
	}
		

}
