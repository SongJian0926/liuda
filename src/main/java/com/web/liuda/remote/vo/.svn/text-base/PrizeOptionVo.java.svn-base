package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: PrizeOptionVo
 * @Description:二次抽奖奖项设置 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="二次抽奖奖项设置Vo对象")
public class PrizeOptionVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="奖项等级 ,1:一等奖，2:二等奖，3:三等奖")
	private Integer level;
	@ApiModelProperty(value="奖项数量,奖项数量")
	private Integer num;
	@ApiModelProperty(value="奖品名称 ,奖品名称 ")
	private String prize;
	
	@ApiModelProperty(value="获奖结果,获奖结果")
	private List<PrizeResultVo> prizeResultList;
		
	public PrizeOptionVo(Long id,Long matchId,Integer level,Integer num,String prize) {
		this.id = id;
		this.matchId = matchId;
		this.level = level;
		this.num = num;
		this.prize = prize;
	}
	
	public PrizeOptionVo() {
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
		public void setLevel(Integer level){
		this.level=level;
	}
	
	public Integer getLevel(){
		return level;
	}
		public void setNum(Integer num){
		this.num=num;
	}
	
	public Integer getNum(){
		return num;
	}
		public void setPrize(String prize){
		this.prize=prize;
	}
	
	public String getPrize(){
		return prize;
	}

	public List<PrizeResultVo> getPrizeResultList() {
		return prizeResultList;
	}

	public void setPrizeResultList(List<PrizeResultVo> prizeResultList) {
		this.prizeResultList = prizeResultList;
	}
		
}

