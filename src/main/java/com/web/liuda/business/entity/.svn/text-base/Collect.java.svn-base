package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Collect 
* @Description: 关注定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_collect")
@ApiModel(value="关注定义表")
public class Collect extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="对象Id,对象Id:活动Id,赛事Id")
	private Long objectId;
	@ApiModelProperty(value="类型,类型:1.赛事，2.活动")
	private Integer type;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
		
	
	public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	@Column(nullable=true,length=20)
	public Long getObjectId(){
		return objectId;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=true,length=11)
	public Integer getType(){
		return type;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
		

}
