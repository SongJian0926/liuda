package com.wxpay;

/**
 * 统一下单请求数据封装
 * @author zhanglin
 * @time 2016-01-04 12:32
 * 
 */
public class PayReqRefundData {
	
	//公众账号ID	微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid = "";
	//商户号		微信支付分配的商户号
    private String mch_id = "";
    //随机字符串	随机字符串，不长于32位。推荐随机数生成算法
    private String nonce_str = "";
    //签名		签名，详见签名生成算法
    private String sign = "";
    //商品描述		商品或支付单简要描述
    private String body = "";
    //商户订单号	商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
    private String out_trade_no = "";
    //总金额		订单总金额，单位为分
    private int total_fee = 0;
    //微信订单号
    private String transaction_id = "";
    
    //商户退款单号 	商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
    private String out_refund_no = "";
    //退款金额 		退款总金额，订单总金额，单位为分，只能为整数
    private int refund_fee = 0;
    //操作员 		操作员帐号, 默认为商户号
    private String op_user_id = "";
    
	public String getAppid() {
		return appid;
	}
	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getMch_id() {
		return mch_id;
	}
	
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
	public String getNonce_str() {
		return nonce_str;
	}
	
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	public int getTotal_fee() {
		return total_fee;
	}
	
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	
	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
    
	@Override
	public String toString() {
		return "PayReqData [appid=" + appid + ", mch_id=" + mch_id
				+ ", nonce_str=" + nonce_str
				+ ", sign=" + sign 
				+ ", body=" + body 
				+ ", out_trade_no=" + out_trade_no 
				+ ", total_fee=" + total_fee
				+ ", getAppid()=" + getAppid() 
				+ ", getMch_id()=" + getMch_id()
				+ ", getNonce_str()=" + getNonce_str() 
				+ ", getSign()=" + getSign() 
				+ ", getBody()=" + getBody() 
				+ ", getOut_trade_no()=" + getOut_trade_no()
				+ ", getTotal_fee()=" + getTotal_fee()
				+ ", getOut_refund_no()=" + getOut_refund_no()
				+ ", getRefund_fee()=" + getRefund_fee()
				+ ", getOp_user_id()=" + getOp_user_id()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
