package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: PrizeOption 
* @Description: 二次抽奖奖项设置定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_prizeoption")
@ApiModel(value="二次抽奖奖项设置定义表")
public class PrizeOption extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="奖项等级 ,1:一等奖，2:二等奖，3:三等奖")
	private Integer level;
	@ApiModelProperty(value="奖项数量,奖项数量")
	private Integer num;
	@ApiModelProperty(value="奖品名称 ,奖品名称 ")
	private String prize;
		
	
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setLevel(Integer level){
		this.level=level;
	}
	
	@Column(nullable=true,length=11)
	public Integer getLevel(){
		return level;
	}
	public void setNum(Integer num){
		this.num=num;
	}
	
	@Column(nullable=true,length=11)
	public Integer getNum(){
		return num;
	}
	public void setPrize(String prize){
		this.prize=prize;
	}
	
	@Column(nullable=true,length=255)
	public String getPrize(){
		return prize;
	}
		

}
