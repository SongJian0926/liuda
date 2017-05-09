	package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: VoteLog 
* @Description: 投票记录定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_votelog")
@ApiModel(value="投票记录定义表")
public class VoteLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="投票主表Id,投票主表Id")
	private Long voteId;
	@ApiModelProperty(value="投票选项Id,投票选项Id")
	private Long voteOptionId;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setVoteId(Long voteId){
		this.voteId=voteId;
	}
	
	@Column(nullable=true,length=20)
	public Long getVoteId(){
		return voteId;
	}
	public void setVoteOptionId(Long voteOptionId){
		this.voteOptionId=voteOptionId;
	}
	
	@Column(nullable=true,length=20)
	public Long getVoteOptionId(){
		return voteOptionId;
	}
		

}
