package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: VoteOptionVo
 * @Description:投票选项表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="投票选项表Vo对象")
public class VoteOptionVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="选项名称,选项名称")
	private String optionName;
	@ApiModelProperty(value="投票Id,投票Id")
	private Long voteId;
	@ApiModelProperty(value="投票数量,投票数量")
	private Integer num;
		
	public VoteOptionVo(Long id,String optionName,Long voteId,Integer num) {
		this.id = id;
		this.optionName = optionName;
		this.voteId = voteId;
		this.num = num;
	}
	
	public VoteOptionVo() {
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
	
		public void setOptionName(String optionName){
		this.optionName=optionName;
	}
	
	public String getOptionName(){
		return optionName;
	}
		public void setVoteId(Long voteId){
		this.voteId=voteId;
	}
	
	public Long getVoteId(){
		return voteId;
	}
		public void setNum(Integer num){
		this.num=num;
	}
	
	public Integer getNum(){
		return num;
	}
		
}

