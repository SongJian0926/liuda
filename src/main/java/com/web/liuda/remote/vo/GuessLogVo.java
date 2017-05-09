package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: GuessLogVo
 * @Description:赛事记录表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事记录表Vo对象")
public class GuessLogVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="竞赛选项Id,竞赛选项Id")
	private Long optionId;
	@ApiModelProperty(value="押注积分,押注积分")
	private Integer betScore;
	@ApiModelProperty(value="用户Id,竞猜人")
	private Long userId;
		
	public GuessLogVo(Long id,Long matchId,Long optionId,Integer betScore,Long userId) {
		this.id = id;
		this.matchId = matchId;
		this.optionId = optionId;
		this.betScore = betScore;
		this.userId = userId;
	}
	
	public GuessLogVo() {
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
		public void setOptionId(Long optionId){
		this.optionId=optionId;
	}
	
	public Long getOptionId(){
		return optionId;
	}
		public void setBetScore(Integer betScore){
		this.betScore=betScore;
	}
	
	public Integer getBetScore(){
		return betScore;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		
}

