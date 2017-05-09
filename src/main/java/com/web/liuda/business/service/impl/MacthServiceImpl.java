package com.web.liuda.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Guess;
import com.web.liuda.business.entity.GuessOption;
import com.web.liuda.business.entity.Macth;
import com.web.liuda.business.entity.PrizeOption;
import com.web.liuda.business.repository.MacthRepository;
import com.web.liuda.business.service.AreaService;
import com.web.liuda.business.service.GuessOptionService;
import com.web.liuda.business.service.GuessService;
import com.web.liuda.business.service.MacthService;
import com.web.liuda.business.service.PrizeOptionService;
import com.web.liuda.remote.vo.GuessVo;
import com.web.liuda.remote.vo.MacthVo;

@Service("MacthService")
@Transactional(readOnly = false)
public class MacthServiceImpl extends BaseService<Macth> implements MacthService {

	@Autowired
	private MacthRepository macthRepository;
	@Autowired
	private GuessService guessService;
	@Autowired
	private GuessOptionService guessOptionService;
	@Autowired
	private PrizeOptionService prizeOptionService;
	@Autowired
	private AreaService areaService;
	
	/**
	 * 查询单条Macth信息
	 * @param tId
	 * @return 返回单个Macth对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Macth> findOne(Long modelId) throws BusinessException {
		Macth obj = new Macth();
		if(modelId != 0){
			obj = macthRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Macth> xr = new XaResult<Macth>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Macth数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Macth集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Macth>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Macth> page = macthRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Macth.class), pageable);
		XaResult<Page<Macth>> xr = new XaResult<Page<Macth>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Macth数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Macth集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Macth>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Macth> page = macthRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Macth.class), pageable);
		XaResult<Page<Macth>> xr = new XaResult<Page<Macth>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Macth信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public XaResult<Macth> saveOrUpdate(Macth model) throws BusinessException {
		Macth obj = null;
		XaResult<Macth> xr = new XaResult<Macth>();
		if(XaUtil.isNotEmpty(model.getId())){
			obj = macthRepository.findOne(model.getId());
			if(!obj.getStatus().equals(XaConstant.Status.valid))
			{
				xr.error("状态已经不是审核中，无法修改");
				return xr;
			}
		}else{
			obj = new Macth();
		}
		
		String areaName = areaService.getAreaName(model.getAreaId());
		model.setAreaCode(areaName);
		
		//时间控制
		obj.setTitle(model.getTitle());
		obj.setLogo(model.getLogo());
		obj.setStartdate(model.getStartdate());
		obj.setEnddate(model.getEnddate());
		obj.setMatchStatus(model.getMatchStatus());
		obj.setMatchType(model.getMatchType());
		obj.setMerchantId(model.getMerchantId());
		obj.setMediaPath(model.getMediaPath());
		obj.setPrice(model.getPrice());
		obj.setDeposit(model.getDeposit());
		obj.setMaxNum(model.getMaxNum());
		obj.setContent(model.getContent());
		obj.setUrl(model.getUrl());
		obj.setTicketImages(model.getTicketImages());
		obj.setTicketName(model.getTicketName());
		obj.setTicketStockNum(model.getTicketStockNum());
		obj.setTicketPrice(model.getTicketPrice());
		obj.setFavourablePrice(model.getFavourablePrice());
		obj.setFavStartTime(model.getFavStartTime());
		obj.setFavEndTime(model.getFavEndTime());
		obj.setLuckDraw(model.getLuckDraw());
		obj.setAddress(model.getAddress());
		obj.setLng(model.getLng());
		obj.setLat(model.getLat());
		obj.setDeadline(model.getDeadline());
		obj.setDeadlineView(model.getDeadlineView());
		obj.setAreaCode(model.getAreaCode());
		obj.setAreaId(model.getAreaId());
		obj.setCondition(model.getCondition());
		
		obj.setMatchStatus(JConstant.EventStatus.ENROLL);
//		obj.setMatchType(JConstant.MatchType.SELLER);
		obj.setTicketTotleNum(model.getTicketStockNum());
		obj.setDictItemId(model.getDictItemId());
//		if(model.getMatchType().equals(JConstant.MatchType.OFFICIAL))
//		{
//			obj.setBusinessUserId(null);
//		}
//		else
//		{
//			obj.setBusinessUserId(model.getBusinessUserId());
//		}
		//根据地址换为经度和纬度
		obj.setLng(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLng());
		obj.setLat(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLat());
		
		obj = macthRepository.save(obj);
		xr.setObject(obj);
		return xr;
	}
	/**
	 * 保存Macth信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Macth> saveOrUpdate(Macth model, GuessVo guessVo) throws BusinessException {
		Macth obj = null;
		XaResult<Macth> xr = new XaResult<Macth>();
		if(XaUtil.isNotEmpty(model.getId())){
			obj = macthRepository.findOne(model.getId());
			if(!obj.getStatus().equals(XaConstant.Status.valid))
			{
				xr.error("只有待审核的状态才允许修改:"+obj.getTitle());
				return xr;
			}
		}else{
			obj = new Macth();
		}
		
		String areaName = areaService.getAreaName(model.getAreaId());
		model.setAreaCode(areaName);
		
		//时间控制
		obj.setTitle(model.getTitle());
		obj.setLogo(model.getLogo());
		obj.setStartdate(model.getStartdate());
		obj.setEnddate(model.getEnddate());
		obj.setMatchStatus(model.getMatchStatus());
		obj.setMatchType(model.getMatchType());
		obj.setMerchantId(model.getMerchantId());
		obj.setMediaPath(model.getMediaPath());
		obj.setPrice(model.getPrice());
		obj.setDeposit(model.getDeposit());
		obj.setMaxNum(model.getMaxNum());
		obj.setContent(model.getContent());
		obj.setUrl(model.getUrl());
		obj.setTicketImages(model.getTicketImages());
		obj.setTicketName(model.getTicketName());
		obj.setTicketStockNum(model.getTicketStockNum());
		obj.setTicketPrice(model.getTicketPrice());
		obj.setFavourablePrice(model.getFavourablePrice());
		obj.setFavStartTime(model.getFavStartTime());
		obj.setFavEndTime(model.getFavEndTime());
		obj.setLuckDraw(model.getLuckDraw());
		obj.setAddress(model.getAddress());
		obj.setLng(model.getLng());
		obj.setLat(model.getLat());
		obj.setDeadline(model.getDeadline());
		obj.setDeadlineView(model.getDeadlineView());
		obj.setAreaCode(model.getAreaCode());
		obj.setAreaId(model.getAreaId());
		obj.setCondition(model.getCondition());
		
		obj.setMatchStatus(JConstant.EventStatus.ENROLL);
//		obj.setMatchType(JConstant.MatchType.SELLER);
		obj.setTicketTotleNum(model.getTicketStockNum());
		obj.setDictItemId(model.getDictItemId());
//		obj.setBusinessUserId(model.getBusinessUserId());
//		if(model.getMatchType().equals(JConstant.MatchType.OFFICIAL))
//		{
//			obj.setBusinessUserId(null);
//		}
//		else
//		{
//			obj.setBusinessUserId(model.getBusinessUserId());
//		}
		//根据地址换为经度和纬度
		obj.setLng(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLng());
		obj.setLat(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLat());
		
		obj = macthRepository.save(obj);
		List<Long> guessoptionids = new ArrayList<Long>();
		List<Long> prizeoptionids = new ArrayList<Long>();
		XaResult<GuessVo> oldguess = guessService.findByMacthIdAndNotStatus(obj.getId(), XaConstant.Status.delete);
		if(guessVo!=null)
		{
			Guess guess = JSON.parseObject(JSON.toJSONString(guessVo), Guess.class);
			guess.setMatchId(obj.getId());
			guessService.saveOrUpdate(guess);
			if(guessVo.getGuessOptionList()!=null && guessVo.getGuessOptionList().size()>0)
			{
				for(int i=0;i<guessVo.getGuessOptionList().size();i++)
				{
					GuessOption guessOption = JSON.parseObject(JSON.toJSONString(guessVo.getGuessOptionList().get(i)), GuessOption.class);
					guessOption.setMatchId(obj.getId());
					guessOptionService.saveOrUpdate(guessOption);
					if(guessOption.getId()!=null) guessoptionids.add(guessOption.getId());
				}
				if(guessVo.getPrizeOptionList()!=null && guessVo.getPrizeOptionList().size()>0)
				{
					for(int i=0;i<guessVo.getPrizeOptionList().size();i++)
					{
						PrizeOption prizeOption = JSON.parseObject(JSON.toJSONString(guessVo.getPrizeOptionList().get(i)), PrizeOption.class);
						prizeOption.setMatchId(obj.getId());
						prizeOptionService.saveOrUpdate(prizeOption);
						if(prizeOption.getId()!=null) prizeoptionids.add(prizeOption.getId());
					}
				}
			}
		}
		//删除多余的选项
		String delguessoptionids = "";
		String delprizeoptionids = "";
		if(oldguess!=null && oldguess.getObject() instanceof GuessVo)
		{
			if(oldguess.getObject()!=null && oldguess.getObject().getGuessOptionList()!=null)
			{
				for(int i=0;i<oldguess.getObject().getGuessOptionList().size();i++)
				{
					if(!guessoptionids.contains(oldguess.getObject().getGuessOptionList().get(i).getId()))
					{
						delguessoptionids+=oldguess.getObject().getGuessOptionList().get(i).getId()+",";
					}
				}
			}
			if(oldguess.getObject()!=null && oldguess.getObject().getPrizeOptionList()!=null)
			{
				for(int i=0;i<oldguess.getObject().getPrizeOptionList().size();i++)
				{
					if(!prizeoptionids.contains(oldguess.getObject().getPrizeOptionList().get(i).getId()))
					{
						delprizeoptionids+=oldguess.getObject().getPrizeOptionList().get(i).getId()+",";
					}
				}
			}
			if(guessVo==null)
			{
				guessService.multiOperate(String.valueOf(oldguess.getObject().getId()), XaConstant.Status.delete);
			}
			if(!delguessoptionids.equals(""))
			{
				delguessoptionids = delguessoptionids.substring(0, delguessoptionids.length()-1);
				guessOptionService.multiOperate(delguessoptionids, XaConstant.Status.delete);
			}
			if(!delprizeoptionids.equals(""))
			{
				delprizeoptionids = delprizeoptionids.substring(0, delprizeoptionids.length()-1);
				prizeOptionService.multiOperate(delprizeoptionids, XaConstant.Status.delete);
			}
		}
		
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Macth状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Macth对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Macth> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Macth> xr = new XaResult<Macth>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Macth obj = macthRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = macthRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<Macth> upAndDownMatch(String matchIds, Integer status) throws BusinessException {
		XaResult<Macth> xr = new XaResult<Macth>();
		if(XaUtil.isNotEmpty(matchIds)){
			String[] rids = matchIds.split(",");
			//已结束的不能上架，未结束的不能下架？
			
			for(String rid : rids){
				Macth obj=macthRepository.findByIdAndStatusNot(Long.parseLong(rid), XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
				  //审批状态的直接上架
				  if(status.equals(XaConstant.Status.valid)){
					  if(obj.getStatus()==XaConstant.Status.valid){
						  if(obj.getMatchStatus().equals(JConstant.EventStatus.EVENTEND))
						  {
							  xr.error("已结束的赛事不能上架:"+obj.getTitle());
							  return xr;
						  }
						  obj.setStatus(XaConstant.Status.publish);
						  obj = macthRepository.save(obj);
					  }
				  //下架商品直接上架
				  }else if(status.equals(XaConstant.Status.publish)){
					  if(obj.getStatus()==XaConstant.Status.locked){
						  if(obj.getMatchStatus().equals(JConstant.EventStatus.EVENTEND))
						  {
							  xr.error("已结束的赛事不能上架:"+obj.getTitle());
							  return xr;
						  }
						  obj.setStatus(XaConstant.Status.publish);
						  obj = macthRepository.save(obj);
					  }
				  //上架商品直接下架
				  }else if(status.equals(XaConstant.Status.locked)){
					  if(obj.getStatus()==XaConstant.Status.publish){
						  if(!obj.getMatchStatus().equals(JConstant.EventStatus.EVENTEND))
						  {
							  xr.error("未结束的赛事不能下架:"+obj.getTitle());
							  return xr;
						  }
						  obj.setStatus(XaConstant.Status.locked);
						  obj = macthRepository.save(obj);
					  }
				  }
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
		    }
		}
		return xr;
	}

	@Override
	public XaResult<MacthVo> findMacthById(Long modelId) throws BusinessException {
		XaResult<MacthVo> xr=new XaResult<MacthVo>();
		if(XaUtil.isNotEmpty(modelId)){
			XaResult<Macth> match = this.findOne(modelId);
			if(match.getObject()!=null)
			{
				MacthVo matchVo = JSON.parseObject(JSON.toJSONString(match.getObject()), MacthVo.class);
				XaResult<GuessVo> guess = guessService.findByMacthIdAndNotStatus(modelId, XaConstant.Status.delete);
				matchVo.setGuess(JSON.parseObject(JSON.toJSONString(guess.getObject()), GuessVo.class));
				xr.setObject(matchVo);
			}
			else
			{
				xr.error("该赛事不存在");
			}
		}else{
			xr.error("id不能为空");
			return xr;
		}
		return xr;
	}

	
}
