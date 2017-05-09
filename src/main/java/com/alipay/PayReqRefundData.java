package com.alipay;

/**
 * 统一下单请求数据封装
 * @author zhanglin
 * @time 2016-01-04 12:32
 * 
 */
public class PayReqRefundData {
	
	private String batch_no = "";
	private String batch_num = "";
	//退款详细数据，必填，格式（支付宝交易号^退款金额^备注），多笔请用#隔开
	private String detail_data = "";
	private String notify_url = "";
//	//支付宝分配给开发者的应用ID
//	private String app_id = "";
//	//接口名称
//    private String method = "";
//    //请求使用的编码格式，如utf-8,gbk,gb2312等
//    private String charset = "";
//    //商户生成签名字符串所使用的签名算法类型，目前支持RSA
//    private String sign_type = "";
//    //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
//    private String timestamp = "";
//    //调用的接口版本，固定为：1.0
//    private String version = "";
//    //订单支付时传入的商户订单号,不能和 trade_no同时为空。
//    private String out_trade_no = "";
//    //需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
//    private String refund_amount = "";
//    //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
//    private String out_request_no = "";
//    
//	public String getApp_id() {
//		return app_id;
//	}
//	public void setApp_id(String app_id) {
//		this.app_id = app_id;
//	}
//	public String getMethod() {
//		return method;
//	}
//	public void setMethod(String method) {
//		this.method = method;
//	}
//	public String getCharset() {
//		return charset;
//	}
//	public void setCharset(String charset) {
//		this.charset = charset;
//	}
//	public String getSign_type() {
//		return sign_type;
//	}
//	public void setSign_type(String sign_type) {
//		this.sign_type = sign_type;
//	}
//	public String getTimestamp() {
//		return timestamp;
//	}
//	public void setTimestamp(String timestamp) {
//		this.timestamp = timestamp;
//	}
//	public String getVersion() {
//		return version;
//	}
//	public void setVersion(String version) {
//		this.version = version;
//	}
//	public String getOut_trade_no() {
//		return out_trade_no;
//	}
//	public void setOut_trade_no(String out_trade_no) {
//		this.out_trade_no = out_trade_no;
//	}
//	public String getRefund_amount() {
//		return refund_amount;
//	}
//	public void setRefund_amount(String refund_amount) {
//		this.refund_amount = refund_amount;
//	}
//	public String getOut_request_no() {
//		return out_request_no;
//	}
//	public void setOut_request_no(String out_request_no) {
//		this.out_request_no = out_request_no;
//	}
//    
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getBatch_num() {
		return batch_num;
	}
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}
	public String getDetail_data() {
		return detail_data;
	}
	public void setDetail_data(String detail_data) {
		this.detail_data = detail_data;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
    
    
//	@Override
//	public String toString() {
//		return "PayReqData [appid=" + appid + ", mch_id=" + mch_id
//				+ ", nonce_str=" + nonce_str
//				+ ", sign=" + sign 
//				+ ", body=" + body 
//				+ ", out_trade_no=" + out_trade_no 
//				+ ", total_fee=" + total_fee
//				+ ", getAppid()=" + getAppid() 
//				+ ", getMch_id()=" + getMch_id()
//				+ ", getNonce_str()=" + getNonce_str() 
//				+ ", getSign()=" + getSign() 
//				+ ", getBody()=" + getBody() 
//				+ ", getOut_trade_no()=" + getOut_trade_no()
//				+ ", getTotal_fee()=" + getTotal_fee()
//				+ ", getOut_refund_no()=" + getOut_refund_no()
//				+ ", getRefund_fee()=" + getRefund_fee()
//				+ ", getOp_user_id()=" + getOp_user_id()
//				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
//				+ ", toString()=" + super.toString() + "]";
//	}

}
