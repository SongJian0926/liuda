package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 微信支付需要的数据vo
 * @author zhanglin
 * @time 2016-01-04 17:55
 *
 */
@ApiModel(value="微信APP支付需要的参数VO")
public class WxPayVo {
	
	@ApiModelProperty(value="交易会话Id")
	private String prepayid;
	@ApiModelProperty(value="随机字符串")
	private String nonceStr;
	@ApiModelProperty(value="时间戳")
	private String timeStamp;
	@ApiModelProperty(value="签名")
	private String sign;
	
	public String getPrepayid() {
		return prepayid;
	}
	
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	
	public String getNonceStr() {
		return nonceStr;
	}
	
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}

}
