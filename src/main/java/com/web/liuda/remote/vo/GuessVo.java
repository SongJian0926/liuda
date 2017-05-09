package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: GuessVo
 * @Description:赛事竞猜表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事竞猜表Vo对象")
public class GuessVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="赛事标题,赛事标题")
	private String title;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="赔率,赔率")
	private Double odds;
	@ApiModelProperty(value="状态,状态")
	private Integer status;
	
	@ApiModelProperty(value="竞猜选项,竞猜选项")
	private List<GuessOptionVo> guessOptionList;
	@ApiModelProperty(value="个人竞猜选项,个人竞猜选项")
	private GuessLogVo guessLogVo;
	
	@ApiModelProperty(value="二次抽奖,二次抽奖")
	private List<PrizeOptionVo> prizeOptionList;
		
	public GuessVo(Long id,String title,Long matchId,Double odds) {
		this.id = id;
		this.title = title;
		this.matchId = matchId;
		this.odds = odds;
	}
	
	public GuessVo() {
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
	
		public void setTitle(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
		public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	public Long getMatchId(){
		return matchId;
	}
		public void setOdds(Double odds){
		this.odds=odds;
	}
	
	public Double getOdds(){
		return odds;
	}

	public List<GuessOptionVo> getGuessOptionList() {
		return guessOptionList;
	}

	public void setGuessOptionList(List<GuessOptionVo> guessOptionList) {
		this.guessOptionList = guessOptionList;
	}

	public GuessLogVo getGuessLogVo() {
		return guessLogVo;
	}

	public void setGuessLogVo(GuessLogVo guessLogVo) {
		this.guessLogVo = guessLogVo;
	}

	public List<PrizeOptionVo> getPrizeOptionList() {
		return prizeOptionList;
	}

	public void setPrizeOptionList(List<PrizeOptionVo> prizeOptionList) {
		this.prizeOptionList = prizeOptionList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
		
}

