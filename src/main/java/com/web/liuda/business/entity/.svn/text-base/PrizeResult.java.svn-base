package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: PrizeResult 
* @Description: 二次抽奖结果定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_prizeresult")
@ApiModel(value="二次抽奖结果定义表")
public class PrizeResult extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="奖项等级Id,奖项等级Id")
	private Long prizeOptionId;
	
	
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setPrizeOptionId(Long prizeOptionId){
		this.prizeOptionId=prizeOptionId;
	}
	
	@Column(nullable=true,length=20)
	public Long getPrizeOptionId(){
		return prizeOptionId;
	}


}
