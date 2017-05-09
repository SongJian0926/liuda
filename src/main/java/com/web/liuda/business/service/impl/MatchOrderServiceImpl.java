package com.web.liuda.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.business.repository.MatchOrderRepository;
import com.web.liuda.business.service.MatchOrderService;
import com.web.liuda.remote.vo.MatchOrderVo;
import com.web.liuda.remote.vo.UserVo;

@Service("MatchOrderService")
@Transactional(readOnly = false)
public class MatchOrderServiceImpl extends BaseService<MatchOrder> implements MatchOrderService {

	@Autowired
	private MatchOrderRepository matchOrderRepository;
	
	/**
	 * 查询单条MatchOrder信息
	 * @param tId
	 * @return 返回单个MatchOrder对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<MatchOrder> findOne(Long modelId) throws BusinessException {
		MatchOrder obj = new MatchOrder();
		if(modelId != 0){
			obj = matchOrderRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<MatchOrder> xr = new XaResult<MatchOrder>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的MatchOrder数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchOrder集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchOrder>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchOrder> page = matchOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrder.class), pageable);
		XaResult<Page<MatchOrder>> xr = new XaResult<Page<MatchOrder>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的MatchOrder数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchOrder集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchOrder>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchOrder> page = matchOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrder.class), pageable);
		XaResult<Page<MatchOrder>> xr = new XaResult<Page<MatchOrder>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存MatchOrder信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchOrder> saveOrUpdate(MatchOrder model) throws BusinessException {
		MatchOrder obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = matchOrderRepository.findOne(model.getId());
		}else{
			obj = new MatchOrder();
		}
		obj.setUserId(model.getUserId());
		obj.setMatchId(model.getMatchId());
		obj.setOrderNumber(model.getOrderNumber());
		obj.setOrderStatus(model.getOrderStatus());
		obj.setTotalAmt(model.getTotalAmt());
		obj.setRefundAmt(model.getRefundAmt());
		obj.setPayType(model.getPayType());
		obj.setTradeNum(model.getTradeNum());
		obj.setIsFull(model.getIsFull());
		obj.setOfflineAmt(model.getOfflineAmt());
		obj.setOnlineAmt(model.getOnlineAmt());
		obj.setType(model.getType());
		obj = matchOrderRepository.save(obj);
		XaResult<MatchOrder> xr = new XaResult<MatchOrder>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改MatchOrder状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回MatchOrder对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchOrder> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<MatchOrder> xr = new XaResult<MatchOrder>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MatchOrder obj = matchOrderRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = matchOrderRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<Page<MatchOrderVo>> findMatchOrderVoNEStatusPage(Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<Page<MatchOrderVo>> xrvo = new XaResult<Page<MatchOrderVo>>();
		String starttime = "0000-00-00";
		String endtime = "9999-99-99";
		Integer orderStatus = 0;
		
		if(XaUtil.isNotEmpty(filterParams.get("GTE_createTime"))){
			starttime = filterParams.get("GTE_createTime").toString();
		}
		if(XaUtil.isNotEmpty(filterParams.get("LTE_createTime"))){
			endtime = filterParams.get("LTE_createTime").toString();
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_orderStatus"))){
			orderStatus = Integer.parseInt(filterParams.get("EQ_orderStatus").toString());
		}
		
		Page<Object[]> objlst = matchOrderRepository.findMatchOrderByTimeAndOrderStatus(starttime,endtime,orderStatus,orderStatus,pageable);
		List<MatchOrderVo> vos = new ArrayList<MatchOrderVo>();
		for(Object[] ro : objlst)
		{
			MatchOrderVo rvo = new MatchOrderVo();
			rvo.setId(Long.parseLong(ro[0].toString()));
			rvo.setOrderNo(ro[1]==null?"":ro[1].toString());
			rvo.setPayType(Integer.parseInt(ro[2].toString()));
			rvo.setType(Integer.parseInt(ro[3].toString()));
			rvo.setOrderNumber(Integer.parseInt(ro[4].toString()));
			rvo.setTotalAmt(ro[5]==null?Double.NaN:Double.valueOf(ro[5].toString()));
			rvo.setOnlineAmt(ro[6]==null?Double.NaN:Double.valueOf(ro[6].toString()));
			rvo.setOfflineAmt(ro[7]==null?Double.NaN:Double.valueOf(ro[7].toString()));
			rvo.setIsFull(Integer.parseInt(ro[8].toString()));
			rvo.setOrderStatus(Integer.parseInt(ro[9].toString()));
			rvo.setCreateTime(ro[10]==null?"":ro[10].toString());
			UserVo uvo = new UserVo();
			uvo.setUserName(ro[11]==null?"":ro[11].toString());
			uvo.setMobile(ro[12]==null?"":ro[12].toString());
			rvo.setUserVo(uvo);
			vos.add(rvo);
		}
		Page<MatchOrderVo> page= new MyPage<MatchOrderVo>(pageable.getPageNumber(),pageable.getPageSize(), vos, Integer.parseInt(String.valueOf(objlst.getTotalElements())));
		xrvo.setObject(page);
		return xrvo;
	}
	
}
