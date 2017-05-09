package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.liuda.business.constant.JConstant;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Images 
* @Description: 图片定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_images")
@ApiModel(value="图片定义表")
public class Images extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="图片地址,图片地址")
	private String picurl;
	@ApiModelProperty(value="图片类型,图片类型，0：酒店(轮播图)、1：酒店介绍(介绍图)、2：评论、3：景点(轮播图)、4：商品(轮播图)、5：景点介绍(介绍图)、6：商品介绍(介绍图)、7：回复、8：房间(轮播图)、9：门票(轮播图)")
	private Integer type;
	@ApiModelProperty(value="排序值,排序值")
	private Integer sort = JConstant.BooleanStatus.FALSE;
	@ApiModelProperty(value="对象id,对象id")
	private Long objectId;
		
	
	public void setPicurl(String picurl){
		this.picurl=picurl;
	}
	
	@Column(nullable=false,length=255)
	public String getPicurl(){
		return picurl;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=false,length=50)
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
	public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	@Column(nullable=false,length=50)
	public Long getObjectId(){
		return objectId;
	}
		

}
