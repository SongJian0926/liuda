package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: GuessOption 
* @Description: 赛事选项表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_guessoption")
@ApiModel(value="赛事选项表定义表")
public class GuessOption extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="竞猜选项,竞猜选项")
	private String option;
	@ApiModelProperty(value="正确选项,1：表示正确;其他：错误")
	private Integer  isRight;
		
	
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setOption(String option){
		this.option=option;
	}
	
	@Column(name="[option]",nullable=true,length=50)
	public String getOption(){
		return option;
	}
	public void setIsRight(Integer isRight){
		this. isRight= isRight;
	}
	
	@Column(nullable=true,length=11)
	public Integer getIsRight(){
		return  isRight;
	}
		

}
