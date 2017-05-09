package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: GuessOptionVo
 * @Description:赛事选项表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事选项表Vo对象")
public class GuessOptionVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="竞猜选项,竞猜选项")
	private String option;
	@ApiModelProperty(value="正确选项,1：表示正确;其他：错误")
	private Integer  isRight;
	
	@ApiModelProperty(value="选项百分比,选项百分比")
	private Integer optionPercent;
		
	public GuessOptionVo(Long id,Long matchId,String option,Integer  isRight) {
		this.id = id;
		this.matchId = matchId;
		this.option = option;
		this. isRight =  isRight;
	}
	
	public GuessOptionVo() {
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
	
		public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	public Long getMatchId(){
		return matchId;
	}
		public void setOption(String option){
		this.option=option;
	}
	
	public String getOption(){
		return option;
	}
		public void setIsRight(Integer isRight){
		this. isRight= isRight;
	}
	
	public Integer getIsRight(){
		return  isRight;
	}

	public Integer getOptionPercent() {
		return optionPercent;
	}

	public void setOptionPercent(Integer optionPercent) {
		this.optionPercent = optionPercent;
	}
		
}

