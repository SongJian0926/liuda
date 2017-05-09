package com.web.liuda.remote.service;

import com.web.liuda.remote.vo.WxPayVo;
import com.web.webstart.base.util.XaResult;

public interface ApiWxPayService {
	
	/**
	 * 得到支付的参数
	 * @return
	 */
	public XaResult<WxPayVo> getPayParams(String orderNo,String orderPrice,String nofity_url,String ip) throws Exception;
	
	/**
	 * 查询订单是否支付成功
	 * @param nonce_str
	 * @param transaction_id
	 * @return
	 */
	public Boolean getTradeByReturn(String nonce_str, String transaction_id) throws Exception;

}
