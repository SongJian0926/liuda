package com.web.liuda.business.service.impl;

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
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.entity.Guess;
import com.web.liuda.business.entity.GuessOption;
import com.web.liuda.business.entity.PrizeOption;
import com.web.liuda.business.repository.GuessOptionRepository;
import com.web.liuda.business.repository.GuessRepository;
import com.web.liuda.business.repository.PrizeOptionRepository;
import com.web.liuda.business.service.GuessLogService;
import com.web.liuda.business.service.GuessOptionService;
import com.web.liuda.business.service.GuessService;
import com.web.liuda.remote.vo.GuessOptionVo;
import com.web.liuda.remote.vo.GuessVo;
import com.web.liuda.remote.vo.PrizeOptionVo;
import com.web.liuda.remote.vo.PrizeResultVo;

@Service("GuessService")
@Transactional(readOnly = false)
public class GuessServiceImpl extends BaseService<Guess> implements GuessService {

	@Autowired
	private GuessRepository guessRepository;
	@Autowired
	private GuessOptionRepository guessOptionRepository;
	@Autowired
	private PrizeOptionRepository prizeOptionRepository;
	@Autowired
	private GuessOptionService guessOptionService;
	@Autowired
	private GuessLogService guessLogService;
	
	/**
	 * 查询单条Guess信息
	 * @param tId
	 * @return 返回单个Guess对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Guess> findOne(Long modelId) throws BusinessException {
		Guess obj = new Guess();
		if(modelId != 0){
			obj = guessRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Guess> xr = new XaResult<Guess>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Guess数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Guess集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Guess>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Guess> page = guessRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Guess.class), pageable);
		XaResult<Page<Guess>> xr = new XaResult<Page<Guess>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Guess数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Guess集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Guess>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Guess> page = guessRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Guess.class), pageable);
		XaResult<Page<Guess>> xr = new XaResult<Page<Guess>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Guess信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Guess> saveOrUpdate(Guess model) throws BusinessException {
		Guess obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = guessRepository.findOne(model.getId());
		}else{
			obj = new Guess();
		}
		obj.setTitle(model.getTitle());
		obj.setMatchId(model.getMatchId());
		obj.setOdds(model.getOdds());
		obj = guessRepository.save(obj);
		XaResult<Guess> xr = new XaResult<Guess>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Guess状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Guess对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Guess> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Guess> xr = new XaResult<Guess>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Guess obj = guessRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = guessRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
	public XaResult<GuessVo> findByMacthIdAndNotStatus(Long matchId, int status) throws BusinessException {
		
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("status", new SearchFilter("status", Operator.NE, status));
		}
		if(XaUtil.isNotEmpty(matchId))
		{
			filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		}
		Guess guess = guessRepository.findOne(DynamicSpecifications.bySearchFilter(filters.values(), Guess.class));
		XaResult<GuessVo> xr = new XaResult<GuessVo>();
		if(guess!=null)
		{
			GuessVo guessvo = JSON.parseObject(JSON.toJSONString(guess),GuessVo.class);
			
			//选项
			List<GuessOption> lst = guessOptionRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), GuessOption.class));
			List<GuessOptionVo> lstvo = new ArrayList<GuessOptionVo>();
			Map<Long,GuessOptionVo> map = new HashMap<Long,GuessOptionVo>();
			for(int i=0;i<lst.size();i++)
			{
				GuessOptionVo gov = JSON.parseObject(JSON.toJSONString(lst.get(i)),GuessOptionVo.class);
				gov.setOptionPercent(0);
				lstvo.add(gov);
				map.put(gov.getId(), gov);
			}
			guessvo.setGuessOptionList(lstvo);
			
			//查二次抽奖内容
			List<PrizeOption> polst = prizeOptionRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), PrizeOption.class));
			List<PrizeOptionVo> lstpo = new ArrayList<PrizeOptionVo>();
			Map<Long,PrizeOptionVo> mappo = new HashMap<Long,PrizeOptionVo>();
			for(int i=0;i<polst.size();i++)
			{
				PrizeOptionVo pov = JSON.parseObject(JSON.toJSONString(polst.get(i)),PrizeOptionVo.class);
				pov.setPrizeResultList(new ArrayList<PrizeResultVo>());
				lstpo.add(pov);
				mappo.put(pov.getId(), pov);
			}
			guessvo.setPrizeOptionList(lstpo);
			
			xr.setObject(guessvo);
		}
		return xr;
	}

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Guess> computeGuess(Long matchId, Long optionId) throws BusinessException {
		XaResult<Guess> xr = new XaResult<Guess>();
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(matchId))
		{
			filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		}
		Guess guess = guessRepository.findOne(DynamicSpecifications.bySearchFilter(filters.values(), Guess.class));
		if(guess==null)
		{
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		else if(guess.getStatus().equals(XaConstant.Status.publish))
		{
			xr.error("该赛事已经计算过了");
		}
		else
		{
			guessOptionService.setGuessOption(matchId, optionId);
			Double odds = guess.getOdds();
			guessLogService.computeOdds(matchId,optionId,odds);
			guess.setStatus(XaConstant.Status.publish);
			guessRepository.save(guess);
		}
		return xr;
	}

	@Override
	public XaResult<Guess> setGuessOption(Long matchId, Long optionId) throws BusinessException {
		XaResult<Guess> xr = new XaResult<Guess>();
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(matchId))
		{
			filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		}
		Guess guess = guessRepository.findOne(DynamicSpecifications.bySearchFilter(filters.values(), Guess.class));
		if(guess==null)
		{
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		else if(guess.getStatus().equals(XaConstant.Status.publish))
		{
			xr.error("该赛事已经计算过了");
		}
		else
		{
			guessOptionService.setGuessOption(matchId, optionId);
		}
		return xr;
	}
}
