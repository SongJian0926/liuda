package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: PrizeResultVo
 * @Description:二次抽奖结果 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="二次抽奖结果Vo对象")
public class PrizeResultVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="奖项等级Id,奖项等级Id")
	private Long prizeOptionId;
	@ApiModelProperty(value="状态,状态")
	private Integer status;
	
	@ApiModelProperty(value="用户实体")
	private UserVo userVo;
		
	public PrizeResultVo(Long id,Long matchId,Long userId,Long prizeOptionId) {
		this.id = id;
		this.matchId = matchId;
		this.userId = userId;
		this.prizeOptionId = prizeOptionId;
	}
	
	public PrizeResultVo() {
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
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setPrizeOptionId(Long prizeOptionId){
		this.prizeOptionId=prizeOptionId;
	}
	
	public Long getPrizeOptionId(){
		return prizeOptionId;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
		
}

