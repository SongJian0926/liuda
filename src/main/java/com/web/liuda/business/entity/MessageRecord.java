package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: MessageRecord 
* @Description: 消息记录定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_messagerecord")
@ApiModel(value="消息记录定义表")
public class MessageRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="消息Id,消息Id")
	private Long messageId;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setMessageId(Long messageId){
		this.messageId=messageId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMessageId(){
		return messageId;
	}
		

}
