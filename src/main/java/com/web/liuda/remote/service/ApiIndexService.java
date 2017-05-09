package com.web.liuda.remote.service;

import com.web.liuda.remote.vo.IndexVo;
import com.web.webstart.base.util.XaResult;

public interface ApiIndexService {
	
	/**
	 * 获取首页信息
	 * @return
	 */
	public XaResult<IndexVo> getIndexInfo(Double lng,Double lat);

}
