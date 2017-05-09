package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: FeedBack 
* @Description: 意见反馈定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_feedback")
@ApiModel(value="意见反馈定义表")
public class FeedBack extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="反馈内容,反馈内容")
	private String content;
	
	
	private String username;
	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=50)
	public Long getUserId(){
		return userId;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}

	public FeedBack(Long id,String createtime,String content,Long userId,String userName) {
		super();
		this.setId(id);
		this.setCreateTime(createtime);
		this.content = content;
		this.userId=userId;
		this.username=userName;
	}
	public FeedBack(Long id,String createtime,String content,String userName) {
		super();
		this.setId(id);
		this.setCreateTime(createtime);
		this.content = content;	
		this.username=userName;
	}
	public FeedBack() {
	}
		

}
