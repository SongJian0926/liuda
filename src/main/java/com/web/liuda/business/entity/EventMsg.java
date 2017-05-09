package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: EventMsg 
* @Description: 俱乐部留言表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_eventmsg")
@ApiModel(value="俱乐部留言表定义表")
public class EventMsg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="评论或者回复内容,comment_id不为空，表示针对comment_id进行回复；comment_id为空，表示评论 ")
	private String content;
	@ApiModelProperty(value="活动Id,活动Id")
	private Long eventId;
	@ApiModelProperty(value="留言表Id,留言表Id")
	private Long event_msg_id;
	@ApiModelProperty(value="图片,评论或者回复的图片")
	private String mediaPath;
		
	
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
	
	@Column(nullable=true,length=2000,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setEventId(Long eventId){
		this.eventId=eventId;
	}
	
	@Column(nullable=true,length=20)
	public Long getEventId(){
		return eventId;
	}
	public void setEvent_msg_id(Long event_msg_id){
		this.event_msg_id=event_msg_id;
	}
	
	@Column(nullable=true,length=20)
	public Long getEvent_msg_id(){
		return event_msg_id;
	}
	public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	@Column(nullable=true,length=1000)
	public String getMediaPath(){
		return mediaPath;
	}
		

}
