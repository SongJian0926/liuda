package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: History 
* @Description: 收藏定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_history")
@ApiModel(value="收藏定义表")
public class History extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="对象Id,对象Id")
	private Long objectId;
	@ApiModelProperty(value="类型,类型（0：酒店 1：景点 2：商品）")
	private Integer type;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=false,length=50)
	public Long getUserId(){
		return userId;
	}
	public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	@Column(nullable=false,length=50)
	public Long getObjectId(){
		return objectId;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=false,length=50)
	public Integer getType(){
		return type;
	}
		

}
