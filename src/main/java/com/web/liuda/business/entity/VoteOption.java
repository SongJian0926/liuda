package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: VoteOption 
* @Description: 投票选项表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_voteoption")
@ApiModel(value="投票选项表定义表")
public class VoteOption extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="选项名称,选项名称")
	private String optionName;
	@ApiModelProperty(value="投票Id,投票Id")
	private Long voteId;
	@ApiModelProperty(value="投票数量,投票数量")
	private Integer num;
		
	
	public void setOptionName(String optionName){
		this.optionName=optionName;
	}
	
	@Column(nullable=true,length=255)
	public String getOptionName(){
		return optionName;
	}
	public void setVoteId(Long voteId){
		this.voteId=voteId;
	}
	
	@Column(nullable=true,length=20)
	public Long getVoteId(){
		return voteId;
	}
	public void setNum(Integer num){
		this.num=num;
	}
	
	@Column(nullable=true,length=11)
	public Integer getNum(){
		return num;
	}
		

}
