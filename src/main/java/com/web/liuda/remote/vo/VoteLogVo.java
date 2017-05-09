package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: VoteLogVo
 * @Description:投票记录 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="投票记录Vo对象")
public class VoteLogVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="投票主表Id,投票主表Id")
	private Long voteId;
	@ApiModelProperty(value="投票选项Id,投票选项Id")
	private Long voteOptionId;
		
	public VoteLogVo(Long id,Long userId,Long voteId,Long voteOptionId) {
		this.id = id;
		this.userId = userId;
		this.voteId = voteId;
		this.voteOptionId = voteOptionId;
	}
	
	public VoteLogVo() {
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
		public void setVoteId(Long voteId){
		this.voteId=voteId;
	}
	
	public Long getVoteId(){
		return voteId;
	}
		public void setVoteOptionId(Long voteOptionId){
		this.voteOptionId=voteOptionId;
	}
	
	public Long getVoteOptionId(){
		return voteOptionId;
	}
		
}

