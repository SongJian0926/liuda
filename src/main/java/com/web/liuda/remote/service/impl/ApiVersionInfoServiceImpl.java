package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.VersionInfo;
import com.web.liuda.business.repository.VersionInfoRepository;
import com.web.liuda.remote.service.ApiVersionInfoService;
import com.web.liuda.remote.vo.VersionInfoVo;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIVersionInfo接口实现类
 **/
@Service("ApiVersionInfoService")
@Transactional(readOnly = false)
public class ApiVersionInfoServiceImpl extends BaseService<VersionInfo> implements ApiVersionInfoService{

	@Autowired
	VersionInfoRepository versionInfoRepository;

	@Override
	public XaResult<VersionInfoVo> findVersionInfo() {
		XaResult<VersionInfoVo> xr = new XaResult<VersionInfoVo>();
		String sql = "SELECT v.* FROM tb_xa_versioninfo v ORDER BY v.create_time desc LIMIT 1";
		List<Object[]> objs = this.query(sql, null, null);
		if(XaUtil.isNotEmpty(objs) && objs.size() > 0){
			VersionInfoVo vo = new VersionInfoVo();
			vo.setId(((BigInteger)objs.get(0)[0]).longValue());
			vo.setCreateTime((String)objs.get(0)[1]);
			vo.setDownloadUrl((String)objs.get(0)[4]);
			vo.setIsforceUpdate((Integer)objs.get(0)[5]);
			vo.setUpdateContent((String)objs.get(0)[6]);
			vo.setVersionCode((String)objs.get(0)[7]);
			xr.success(vo);
		}else{
			xr.error("暂无版本信息");
		}
		return xr;
	}
	
}
