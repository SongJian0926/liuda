package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: ReplyComment 
* @Description: 回复评论定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_replycomment")
@ApiModel(value="回复评论定义表")
public class ReplyComment extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="评论Id,评论Id")
	private Long commentId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="商家Id,商家Id")
	private Long businessUserId;
	@ApiModelProperty(value="回复内容,回复内容")
	private String replyComment;
		
	
	public void setCommentId(Long commentId){
		this.commentId=commentId;
	}
	
	@Column(nullable=true,length=50)
	public Long getCommentId(){
		return commentId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=50)
	public Long getUserId(){
		return userId;
	}
	public void setBusinessUserId(Long businessUserId){
		this.businessUserId=businessUserId;
	}
	
	@Column(nullable=true,length=50)
	public Long getBusinessUserId(){
		return businessUserId;
	}
	public void setReplyComment(String replyComment){
		this.replyComment=replyComment;
	}
	
	@Column(nullable=false,length=50,columnDefinition="MEDIUMTEXT")
	public String getReplyComment(){
		return replyComment;
	}
		

}
