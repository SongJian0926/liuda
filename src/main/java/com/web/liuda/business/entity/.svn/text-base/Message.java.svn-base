package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web.liuda.business.constant.JConstant;
import com.web.webstart.base.entity.BaseEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: Message 
* @Description: 消息中心定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_message")
@ApiModel(value="消息中心定义表")
public class Message extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="消息标题,消息标题")
	private String title;
	@ApiModelProperty(value="消息内容,消息内容")
	private String content;
	@ApiModelProperty(value="消息类型,消息类型，0、系统消息 1、个人消息")
	private Integer type = JConstant.BooleanStatus.FALSE;
	@ApiModelProperty(value="用户Id,用户Id，当为系统消息时该字段为空")
	private Long userId;
	
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
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=50)
	public Long getUserId(){
		return userId;
	}

	@Column(nullable=false,length=50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Message(){}
	public Message(String content,Long userId,String title)
	{
		this.content=content;
		this.userId=userId;
		this.title=title;
	}

}
