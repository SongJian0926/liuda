package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Guess 
* @Description: 赛事竞猜表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_guess")
@ApiModel(value="赛事竞猜表定义表")
public class Guess extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="赛事标题,赛事标题")
	private String title;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="赔率,赔率")
	private Double odds;
		
	
	public void setTitle(String title){
		this.title=title;
	}
	
	@Column(nullable=true,length=255)
	public String getTitle(){
		return title;
	}
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setOdds(Double odds){
		this.odds=odds;
	}
	
	@Column(nullable=true,length=18)
	public Double getOdds(){
		return odds;
	}
		

}
