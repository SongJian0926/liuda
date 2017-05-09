package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ReplyCommentVo
 * @Description:回复评论 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="回复评论Vo对象")
public class ReplyCommentVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="评论Id,评论Id")
	private Long commentId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="商家Id,商家Id")
	private Long businessUserId;
	@ApiModelProperty(value="回复内容,回复内容")
	private String replyComment;
		
	public ReplyCommentVo(Long id,Long commentId,Long userId,Long businessUserId,String replyComment) {
		this.id = id;
		this.commentId = commentId;
		this.userId = userId;
		this.businessUserId = businessUserId;
		this.replyComment = replyComment;
	}
	
	public ReplyCommentVo() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
		public void setCommentId(Long commentId){
		this.commentId=commentId;
	}
	
	public Long getCommentId(){
		return commentId;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setBusinessUserId(Long businessUserId){
		this.businessUserId=businessUserId;
	}
	
	public Long getBusinessUserId(){
		return businessUserId;
	}
		public void setReplyComment(String replyComment){
		this.replyComment=replyComment;
	}
	
	public String getReplyComment(){
		return replyComment;
	}
		
}

