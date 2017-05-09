package com.web.liuda.remote.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Collect;
import com.web.liuda.business.entity.Macth;
import com.web.liuda.business.repository.CollectRepository;
import com.web.liuda.business.repository.MacthRepository;
import com.web.liuda.remote.service.ApiMatchService;
import com.web.liuda.remote.vo.MacthVo;
import com.web.webstart.base.constant.XaConstant;
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
 * 类的说明：前端APIMatch接口实现类
 **/
@Service("ApiMatchService")
@Transactional(readOnly = false)
public class ApiMatchServiceImpl extends BaseService<Macth> implements ApiMatchService{

	@Autowired
	MacthRepository matchRepository;
	@Autowired
	CollectRepository collectRepository;

	/**
	 * 查询赛事列表
	 * author:flora.li
	 * time:2016-04-20 14:16:00
	 * @param status
	 * @param matchType
	 * @param dictItemId
	 * @param startdate
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<MacthVo>> findMatchList(Integer status, Integer matchType, String dictItemIds, String startdate, Pageable pageable)
			throws BusinessException {
		
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("status", new SearchFilter("status", Operator.EQ, status));
		}
		if(XaUtil.isNotEmpty(matchType))
		{
			filters.put("matchType", new SearchFilter("matchType", Operator.EQ, matchType));
		}
		if(XaUtil.isNotEmpty(dictItemIds))
		{
			String[] ss = dictItemIds.split(",");
			List<Long> lst = new ArrayList<Long>();
			for(int i=0;i<ss.length;i++)
			{
				lst.add(Long.parseLong(ss[i]));
			}
			filters.put("dictItemId", new SearchFilter("dictItemId", Operator.IN, lst));
		}
		if(XaUtil.isNotEmpty(startdate))
		{
			List<String> dates = new ArrayList<String>();
			dates.add(startdate+"-01");
			dates.add(startdate+"-31");
			String sdate = startdate+"-01"+","+startdate+"-31";
			filters.put("startdate", new SearchFilter("startdate", Operator.BETWEEN, sdate));
		}
		Page<Macth> page = matchRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), Macth.class), pageable);
		List<Macth> pagecount = matchRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), Macth.class));
		XaResult<Page<MacthVo>> xr = new XaResult<Page<MacthVo>>();
		
		List<MacthVo> vos = new ArrayList<MacthVo>();
		for(Macth obj : page.getContent()){
			MacthVo vo = JSON.parseObject(JSON.toJSONString(obj),MacthVo.class);
			vos.add(vo);
		}
		
		Page<MacthVo> pagevo = new MyPage<MacthVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, pagecount.size());
		xr.setObject(pagevo);
		
		return xr;
	}

	@Override
	public XaResult<MacthVo> findByMatchIdAndUserId(Long id, Long userId)throws BusinessException {
		Macth obj = matchRepository.findByIdAndStatusNot(id,XaConstant.Status.delete);
		XaResult<MacthVo> xr = new XaResult<MacthVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MacthVo.class));
			xr.getObject().setId(obj.getId());
			
			//判断门票优惠价格
			XaResult<Double> xrRealPrice = this.getRealTicketPrice(xr.getObject());
			if(xrRealPrice.getCode()==XaConstant.Code.success)
			{
				xr.getObject().setTicketPrice(xrRealPrice.getObject());
			}
			
			if(userId!=null)
			{
				Collect collect=collectRepository.findByObjectIdAndUserIdAndTypeAndStatus(id,userId,JConstant.MatchOrderType.MATCHORDER,XaConstant.Status.valid);
				//判断用户是否已关注该活动
				if(XaUtil.isNotEmpty(collect)){
					xr.getObject().setCollectId(collect.getId());
					xr.getObject().setIsCollect(JConstant.BooleanStatus.TRUE);
				}else{
					xr.getObject().setIsCollect(JConstant.BooleanStatus.FALSE);
				}
			}
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}


	
	@Override
	public XaResult<MacthVo> findOne(Long id) throws BusinessException {
		Macth obj = matchRepository.findByIdAndStatusNot(id,XaConstant.Status.delete);
		XaResult<MacthVo> xr = new XaResult<MacthVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MacthVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<MacthVo>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<List<MacthVo>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MacthVo> createModel(Macth model) throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MacthVo> updateModel(Macth model) throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MacthVo> multiOperate(String ids, Integer status)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MacthVo> updateTicketStockNum(Long id, Integer orderNum) throws BusinessException {
		XaResult<MacthVo> xr = new XaResult<MacthVo>();
		Macth obj = matchRepository.findByIdAndStatusNot(id,XaConstant.Status.delete);
		if (XaUtil.isNotEmpty(obj)) {
			obj.setTicketStockNum(obj.getTicketStockNum()+orderNum);
			matchRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MacthVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<Double> getRealTicketPrice(MacthVo match) throws BusinessException {
		XaResult<Double> xr = new XaResult<Double>();
		Double realPrice = match.getTicketPrice();
		//判断单价
		if(match.getFavourablePrice()!=null)
		{
			Date now = new Date();
			Date favStartTime = null;
			Date favEndTime = null;
			if(match.getFavStartTime()!=null)
			{
				favStartTime = XaUtil.formatDateString2Date(match.getFavStartTime(),"yyyy-MM-dd HH:mm");
				if(favStartTime==null)
				{
					xr.error("日期格式转换错误{"+match.getFavStartTime()+"}，应为{yyyy-MM-dd HH:mm}");
					return xr;
				}
			}
			if(match.getFavEndTime()!=null)
			{
				favEndTime = XaUtil.formatDateString2Date(match.getFavEndTime(),"yyyy-MM-dd HH:mm");
				if(favEndTime==null)
				{
					xr.error("日期格式转换错误{"+match.getFavEndTime()+"}，应为{yyyy-MM-dd HH:mm}");
					return xr;
				}
			}
			if(favStartTime!=null && favEndTime!=null)
			{
				if(now.compareTo(favStartTime)>=0 && now.compareTo(favEndTime)<=0)
				{
					realPrice = match.getFavourablePrice();
				}
			}
			else if(favStartTime!=null)
			{
				if(now.compareTo(favStartTime)>=0)
				{
					realPrice = match.getFavourablePrice();
				}
			}
			else if(favEndTime!=null)
			{
				if(now.compareTo(favEndTime)<=0)
				{
					realPrice = match.getFavourablePrice();
				}
			}
			else
			{
				realPrice = match.getFavourablePrice();
			}
		}
		xr.setObject(realPrice);
		return xr;
	}

}
