package com.web.liuda.remote.service;

import com.web.liuda.remote.vo.VersionInfoVo;
import com.web.webstart.base.util.XaResult;

public interface ApiVersionInfoService{
	
	/**
	 * 查询版本信息
	 * @return
	 */
	public XaResult<VersionInfoVo> findVersionInfo();

}
