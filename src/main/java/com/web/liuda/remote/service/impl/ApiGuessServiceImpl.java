package com.web.liuda.remote.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Guess;
import com.web.liuda.business.entity.GuessLog;
import com.web.liuda.business.entity.GuessOption;
import com.web.liuda.business.entity.PrizeOption;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.GuessLogRepository;
import com.web.liuda.business.repository.GuessOptionRepository;
import com.web.liuda.business.repository.GuessRepository;
import com.web.liuda.business.repository.PrizeOptionRepository;
import com.web.liuda.business.repository.PrizeResultRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.remote.service.ApiGuessService;
import com.web.liuda.remote.vo.GuessLogVo;
import com.web.liuda.remote.vo.GuessOptionVo;
import com.web.liuda.remote.vo.GuessVo;
import com.web.liuda.remote.vo.PrizeOptionVo;
import com.web.liuda.remote.vo.PrizeResultVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: flora.li
 * @times: 2016-04-21 14:16:00
 * 类的说明：前端APIGuess接口实现类
 **/
@Service("ApiGuessService")
@Transactional(readOnly = false)
public class ApiGuessServiceImpl extends BaseService<Guess> implements ApiGuessService{

	@Autowired
	GuessRepository guessRepository;
	@Autowired
	GuessOptionRepository guessOptionRepository;
	@Autowired
	GuessLogRepository guessLogRepository;
	@Autowired
	PrizeOptionRepository prizeOptionRepository;
	@Autowired
	PrizeResultRepository prizeResultRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public XaResult<GuessVo> findGuessByMatchIdAndUserId(Integer status, Long userId, Long matchId)
			throws BusinessException {
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
				if(!guess.getStatus().equals(XaConstant.Status.publish))
				{
					gov.setIsRight(JConstant.BooleanStatus.FALSE);
				}
				lstvo.add(gov);
				map.put(gov.getId(), gov);
			}
			guessvo.setGuessOptionList(lstvo);
			
			//选项比例
			if(lst.size()>0)
			{
				List<Object[]> lstLog = guessLogRepository.findByMatchIdGroupByOptionId(status,matchId);
				int iTotle = 0;
				for(int i=0;i<lstLog.size();i++)
				{
					iTotle += Integer.parseInt(lstLog.get(i)[0].toString());
				}
				if(iTotle>0)
				{
					for(int i=0;i<lstLog.size();i++)
					{
						Object[] oLog = lstLog.get(i);
						if(map.containsKey(Long.parseLong(oLog[1].toString())))
						{
							map.get(Long.parseLong(oLog[1].toString())).setOptionPercent(Math.round((float)Integer.parseInt(oLog[0].toString())*100/iTotle));
						}
					}
				}
				// 获取个人的投注记录
				GuessLogVo guessLogVo = this.findGuessLogByMatchIdAndUserId(status, userId, matchId).getObject();
				if(guessLogVo!=null)
				{
					guessvo.setGuessLogVo(guessLogVo);
				}
			}
			
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
			//查二次抽奖中奖人数
			if(polst.size()>0)
			{
				List<Object[]> prlst = prizeResultRepository.findByMatchId(XaConstant.Status.publish,matchId);
				for(Object[] obj : prlst)
				{
					if(mappo.containsKey(Long.parseLong(obj[4].toString())))
					{
						PrizeResultVo grv = new PrizeResultVo();
						grv.setId(Long.parseLong(obj[0].toString()));
						grv.setCreateTime(obj[1]==null?null:obj[1].toString());
						grv.setUserId(Long.parseLong(obj[2].toString()));
						grv.setMatchId(Long.parseLong(obj[3].toString()));
						grv.setPrizeOptionId(Long.parseLong(obj[4].toString()));
						UserVo userVo = new UserVo();
						userVo.setId(Long.parseLong(obj[2].toString()));
						userVo.setUserName(obj[5]==null?null:obj[5].toString());
						userVo.setPhoto(obj[6]==null?null:obj[6].toString());
						grv.setUserVo(userVo);
						
						mappo.get(Long.parseLong(obj[4].toString())).getPrizeResultList().add(grv);
					}
				}
//				List<PrizeResult> prlst = prizeResultRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), PrizeResult.class));
//				for(int i=0;i<prlst.size();i++)
//				{
//					PrizeResult pr = prlst.get(i);
//					if(mappo.containsKey(pr.getPrizeOptionId()))
//					{
//						PrizeResultVo grv = JSON.parseObject(JSON.toJSONString(pr),PrizeResultVo.class);
//						mappo.get(pr.getPrizeOptionId()).getPrizeResultList().add(grv);
//					}
//				}
			}
			guessvo.setPrizeOptionList(lstpo);
			
			xr.setObject(guessvo);
		}
		return xr;
	}
	
	@Override
	public XaResult<GuessVo> findOne(Long id) throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<List<GuessVo>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<List<GuessVo>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<GuessVo> createModel(Guess model)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<GuessVo> updateModel(Guess model)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<GuessVo> multiOperate(String ids, Integer status)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<GuessLogVo> createGuessLogModel(GuessLog guessLog)
			throws BusinessException {
		XaResult<GuessLogVo> xr = new XaResult<GuessLogVo>();
		GuessLog obj = guessLogRepository.save(guessLog);
		//Long.parseLong("de");
		User user = userRepository.findOne(guessLog.getUserId());
		user.setPoints(user.getPoints()-guessLog.getBetScore());
		userRepository.save(user);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuessLogVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<GuessLogVo> findGuessLogByMatchIdAndUserId(Integer status, Long userId, Long matchId) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("status", new SearchFilter("status", Operator.NE, status));
		}
		if(XaUtil.isNotEmpty(matchId))
		{
			filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		}
		if(XaUtil.isNotEmpty(userId))
		{
			filters.put("userId", new SearchFilter("userId", Operator.EQ, userId));
		}
		GuessLog obj = guessLogRepository.findOne(DynamicSpecifications.bySearchFilter(filters.values(), GuessLog.class));
		XaResult<GuessLogVo> xr = new XaResult<GuessLogVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),GuessLogVo.class));
			xr.getObject().setId(obj.getId());
		} 
		else
		{
			xr.setObject(null);
		}
//		else {
//			throw new BusinessException(XaConstant.Message.object_not_find);
//		}
		return xr;
	}

	@Override
	public XaResult<GuessOptionVo> findGuessOptionByOptionId(Integer status, Long optionId) throws BusinessException {
		GuessOption obj = guessOptionRepository.findOne(optionId);
		XaResult<GuessOptionVo> xr = new XaResult<GuessOptionVo>();
		if (XaUtil.isNotEmpty(obj) && obj.getStatus().equals(status)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),GuessOptionVo.class));
			xr.getObject().setId(obj.getId());
		} 
		else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}


}
