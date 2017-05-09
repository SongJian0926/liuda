package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: GuideComment 
* @Description: 攻略评论表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_guidecomment")
@ApiModel(value="攻略评论表定义表")
public class GuideComment extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="评论内容,评论内容")
	private String content;
	@ApiModelProperty(value="攻略Id,攻略Id")
	private Long guideId;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=1000,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setGuideId(Long guideId){
		this.guideId=guideId;
	}
	
	@Column(nullable=true,length=20)
	public Long getGuideId(){
		return guideId;
	}
		

}
