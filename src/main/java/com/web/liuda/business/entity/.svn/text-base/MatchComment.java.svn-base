package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: MatchComment 
* @Description: 赛事评论定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_matchcomment")
@ApiModel(value="赛事评论定义表")
public class MatchComment extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="评论或者回复内容,评论或者回复内容")
	private String content;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="评论Id,评论Id")
	private Long commentId;
	@ApiModelProperty(value="点赞数量,点赞数量")
	private Integer praiseNum;
		
	
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
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setCommentId(Long commentId){
		this.commentId=commentId;
	}
	
	@Column(nullable=true,length=20)
	public Long getCommentId(){
		return commentId;
	}
	public void setPraiseNum(Integer praiseNum){
		this.praiseNum=praiseNum;
	}
	
	@Column(nullable=true,length=11)
	public Integer getPraiseNum(){
		return praiseNum;
	}
		

}
