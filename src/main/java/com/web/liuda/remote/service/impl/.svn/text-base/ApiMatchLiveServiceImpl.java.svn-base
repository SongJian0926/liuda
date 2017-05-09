package com.web.liuda.remote.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.MatchLive;
import com.web.liuda.business.repository.MatchLiveRepository;
import com.web.liuda.remote.service.ApiMatchLiveService;
import com.web.liuda.remote.vo.MatchLiveVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.webstart.base.util.SearchFilter.Operator;

/**
 * @Autor: flora.li
 * @times: 2016-04-20 14:16:00
 * 类的说明：前端APIMatchLive接口实现类
 **/
@Service("ApiMatchLiveService")
@Transactional(readOnly = false)
public class ApiMatchLiveServiceImpl extends BaseService<MatchLive> implements ApiMatchLiveService{

	@Autowired
	MatchLiveRepository matchLiveRepository;

	/**
	 * 查询赛事列表
	 * author:flora.li
	 * time:2016-04-20 14:16:00
	 * @param id
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public XaResult<Page<MatchLiveVo>> findMatchLiveList(Integer status, Long id, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("status", new SearchFilter("status", Operator.EQ, status));
		}
		if(XaUtil.isNotEmpty(id))
		{
			filters.put("matchId", new SearchFilter("matchId", Operator.EQ, id));
		}
		Page<MatchLive> page = matchLiveRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), MatchLive.class), pageable);
		List<MatchLive> pagecount = matchLiveRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), MatchLive.class));
		XaResult<Page<MatchLiveVo>> xr = new XaResult<Page<MatchLiveVo>>();
		
		List<MatchLiveVo> vos = new ArrayList<MatchLiveVo>();
		for(MatchLive obj : page.getContent()){
			MatchLiveVo vo = JSON.parseObject(JSON.toJSONString(obj),MatchLiveVo.class);
			vos.add(vo);
		}
		
		Page<MatchLiveVo> pagevo = new MyPage<MatchLiveVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, pagecount.size());
		xr.setObject(pagevo);
		
		return xr;
	}

	@Override
	public XaResult<MatchLiveVo> findOne(Long id) throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<List<MatchLiveVo>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<List<MatchLiveVo>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MatchLiveVo> createModel(MatchLive model)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MatchLiveVo> updateModel(MatchLive model)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MatchLiveVo> multiOperate(String ids, Integer status)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}
	
}
