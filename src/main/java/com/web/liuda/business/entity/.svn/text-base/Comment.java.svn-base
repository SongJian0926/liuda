package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Comment 
* @Description: 评论定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_comment")
@ApiModel(value="评论定义表")
public class Comment extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="评论内容,评论内容")
	private String content;
	@ApiModelProperty(value="评论类型,评论类型，0：酒店 1：景点 2：商品")
	private Integer type;
	@ApiModelProperty(value="对象Id,对象Id（酒店Id、景点Id、商品Id）")
	private Long objectId;
	@ApiModelProperty(value="评论分数,评论分数")
	private Float score;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="订单Id,订单Id")
	private Long orderId;
	@ApiModelProperty(value="商品规格Id,商品规格Id")
	private Long standardId;	
	
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=false,length=50,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=false,length=50)
	public Integer getType(){
		return type;
	}
	public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	@Column(nullable=false,length=50)
	public Long getObjectId(){
		return objectId;
	}
	public void setScore(Float score){
		this.score=score;
	}
	
	@Column(nullable=false,length=50)
	public Float getScore(){
		return score;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=false,length=50)
	public Long getUserId(){
		return userId;
	}
	@Column(nullable=false,length=50)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	@Column(nullable=true,length=20)
	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
		

}
