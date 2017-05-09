package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MyBankVo
 * @Description:我的银行卡 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="我的银行卡Vo对象")
public class MyBankVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="商家用户Id,商家用户Id")
	private Long businessUserId;
	@ApiModelProperty(value="用户名,用户名")
	private String realName;
	@ApiModelProperty(value="账号,账号")
	private String account;
	@ApiModelProperty(value="银行名称,银行名称")
	private String bankName;
		
	public MyBankVo(Long id,Long businessUserId,String realName,String account,String bankName) {
		this.id = id;
		this.businessUserId = businessUserId;
		this.realName = realName;
		this.account = account;
		this.bankName = bankName;
	}
	
	public MyBankVo() {
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
	
		public void setBusinessUserId(Long businessUserId){
		this.businessUserId=businessUserId;
	}
	
	public Long getBusinessUserId(){
		return businessUserId;
	}
		public void setRealName(String realName){
		this.realName=realName;
	}
	
	public String getRealName(){
		return realName;
	}
		public void setAccount(String account){
		this.account=account;
	}
	
	public String getAccount(){
		return account;
	}
		public void setBankName(String bankName){
		this.bankName=bankName;
	}
	
	public String getBankName(){
		return bankName;
	}
		
}

