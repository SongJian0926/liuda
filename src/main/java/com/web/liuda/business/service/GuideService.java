package com.web.liuda.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Guide;
import com.web.liuda.remote.vo.GuideVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface GuideService extends BaseServiceInterFace<Guide>{
	/**
	 * 后台攻略列表
	 * author:changlu
	 * time:2016-05-12 16:32:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<GuideVo>> findGuideList(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	
	/**
	 * 精品审核
	 * @param id
	 * @param applyStatus
	 * @return
	 */
	public XaResult<Guide> audit(Long id,Integer applyStatus,String applyMemo);
	
	/**
	 * 绑定景点
	 * @param id
	 * @param objectId
	 * @param objectType
	 * @return
	 */
	public XaResult<Guide> bandTourist(Long id,Long objectId,Integer objectType);
	
	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	public XaResult<GuideVo> findDetail(Long id);
	
}
