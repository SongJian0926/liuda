package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: EventReview 
* @Description: 俱乐部活动回顾表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_eventreview")
@ApiModel(value="俱乐部活动回顾表定义表")
public class EventReview extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="回顾标题,标题")
	private String title;
	@ApiModelProperty(value="内容,内容")
	private String content;
	@ApiModelProperty(value="图片或者视频,图片或者视频")
	private String mediaPath;
	@ApiModelProperty(value="发布人,发布人")
	private Long userId;
	@ApiModelProperty(value="活动Id,活动Id")
	private Long eventId;
		
	
	public void setTitle(String title){
		this.title=title;
	}
	
	@Column(nullable=true,length=255)
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=500)
	public String getContent(){
		return content;
	}
	public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	@Column(nullable=true,length=500)
	public String getMediaPath(){
		return mediaPath;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setEventId(Long eventId){
		this.eventId=eventId;
	}
	
	@Column(nullable=true,length=20)
	public Long getEventId(){
		return eventId;
	}
		

}
