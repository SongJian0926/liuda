package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: GuessLog 
* @Description: 赛事记录表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_guesslog")
@ApiModel(value="赛事记录表定义表")
public class GuessLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="竞赛选项Id,竞赛选项Id")
	private Long optionId;
	@ApiModelProperty(value="押注积分,押注积分")
	private Integer betScore;
	@ApiModelProperty(value="用户Id,竞猜人")
	private Long userId;
		
	
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setOptionId(Long optionId){
		this.optionId=optionId;
	}
	
	@Column(nullable=true,length=20)
	public Long getOptionId(){
		return optionId;
	}
	public void setBetScore(Integer betScore){
		this.betScore=betScore;
	}
	
	@Column(nullable=true,length=11)
	public Integer getBetScore(){
		return betScore;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
		

}
