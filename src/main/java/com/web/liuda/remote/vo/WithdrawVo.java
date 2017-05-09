package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: WithdrawVo
 * @Description:前端提现 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="前端提现Vo对象")
public class WithdrawVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="金额,金额")
	private Double account;
	@ApiModelProperty(value="银行卡号Id, 银行卡号Id,银行卡信息确认完成后，替换成银行卡号信息 ")
	private Long caedInfoId;
		
	public WithdrawVo(Long id,Long userId,Double account,Long caedInfoId) {
		this.id = id;
		this.userId = userId;
		this.account = account;
		this.caedInfoId = caedInfoId;
	}
	
	public WithdrawVo() {
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
	
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setAccount(Double account){
		this.account=account;
	}
	
	public Double getAccount(){
		return account;
	}
		public void setCaedInfoId(Long caedInfoId){
		this.caedInfoId=caedInfoId;
	}
	
	public Long getCaedInfoId(){
		return caedInfoId;
	}
		
}

