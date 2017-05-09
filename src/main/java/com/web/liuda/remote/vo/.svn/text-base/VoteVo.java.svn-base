package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: VoteVo
 * @Description:投票主表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="投票主表Vo对象")
public class VoteVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="截止日期,截止日期")
	private String deadline;
	@ApiModelProperty(value="标题,标题")
	private String title;
	@ApiModelProperty(value="投票说明,投票说明")
	private String content;
	@ApiModelProperty(value="是否单选,1：表示单选，2：表示多选 ")
	private Integer isRadio;
	@ApiModelProperty(value="投票总数,投票总数")
	private Integer num;
	@ApiModelProperty(value="头像")
	private String logo;
	@ApiModelProperty(value="是否过去,0未过期 1,已过期")
	private Integer isOld;
	@ApiModelProperty(value="用户是否投票,0,否 1,是")
	private Integer userIsVote;
	@ApiModelProperty(value="投票选项,投票选项")
	private List<VoteOptionVo> voteOptionVoList;
	@ApiModelProperty(value="投票选项,投票选项")
	private Integer votePepoNum;
	
	public VoteVo(Long id,String deadline,String title,String content,Integer isRadio,Integer num) {
		this.id = id;
		this.deadline = deadline;
		this.title = title;
		this.content = content;
		this.isRadio = isRadio;
		this.num = num;
	}
	
	public VoteVo() {
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
	
		public void setDeadline(String deadline){
		this.deadline=deadline;
	}
	
	public String getDeadline(){
		return deadline;
	}
		public void setTitle(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
		public void setContent(String content){
		this.content=content;
	}
		public Integer getUserIsVote() {
			return userIsVote;
		}

		public void setUserIsVote(Integer userIsVote) {
			this.userIsVote = userIsVote;
		}	

		public String getContent() {
			return content;
		}

		public Integer getIsRadio() {
			return isRadio;
		}

		public void setIsRadio(Integer isRadio) {
			this.isRadio = isRadio;
		}

		public void setNum(Integer num){
		this.num=num;
	}
	
	public Integer getVotePepoNum() {
			return votePepoNum;
		}

		public void setVotePepoNum(Integer votePepoNum) {
			this.votePepoNum = votePepoNum;
		}

	public Integer getNum(){
		return num;
	}

	public Integer getIsOld() {
		return isOld;
	}

	public void setIsOld(Integer isOld) {
		this.isOld = isOld;
	}

	public List<VoteOptionVo> getVoteOptionVoList() {
		return voteOptionVoList;
	}

	public void setVoteOptionVoList(List<VoteOptionVo> voteOptionVoList) {
		this.voteOptionVoList = voteOptionVoList;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	
}

