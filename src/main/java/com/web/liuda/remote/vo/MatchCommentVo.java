package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MatchCommentVo
 * @Description:赛事评论 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事评论Vo对象")
public class MatchCommentVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
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

	@ApiModelProperty(value="用户实体")
	private UserVo userVo;
	@ApiModelProperty(value="回复评论列表,回复评论列表")
	private List<MatchCommentVo> replyCommentList;
	@ApiModelProperty(value="赛事评论使用,评论数量")
	private Integer commentNum;
		
	public MatchCommentVo(Long id,Long userId,String content,Long matchId,Long commentId,Integer praiseNum) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.matchId = matchId;
		this.commentId = commentId;
		this.praiseNum = praiseNum;
	}
	
	
//	public MatchCommentVo(Long id,String createtime,Long userId, String content,Long matchId,Long commentId,Integer praiseNum, UserVo userVo) {
//		this.id = id;
//		this.createTime = createtime;
//		this.userId = userId;
//		this.content = content;
//		this.matchId = matchId;
//		this.commentId = commentId;
//		this.praiseNum = praiseNum;
//		this.userVo = userVo;
//	}
	
	
	public MatchCommentVo() {
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
	
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setContent(String content){
		this.content=content;
	}
	
	public String getContent(){
		return content;
	}
		public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	public Long getMatchId(){
		return matchId;
	}
		public void setCommentId(Long commentId){
		this.commentId=commentId;
	}
	
	public Long getCommentId(){
		return commentId;
	}
		public void setPraiseNum(Integer praiseNum){
		this.praiseNum=praiseNum;
	}
	
	public Integer getPraiseNum(){
		return praiseNum;
	}

	public List<MatchCommentVo> getReplyCommentList() {
		return replyCommentList;
	}

	public void setReplyCommentList(List<MatchCommentVo> replyCommentList) {
		this.replyCommentList = replyCommentList;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}


	public Integer getCommentNum() {
		return commentNum;
	}


	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
		
}

