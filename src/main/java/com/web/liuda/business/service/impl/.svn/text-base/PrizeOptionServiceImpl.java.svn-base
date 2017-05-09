package com.web.liuda.business.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
import com.web.liuda.business.entity.GuessLog;
import com.web.liuda.business.entity.PrizeOption;
import com.web.liuda.business.entity.PrizeResult;
import com.web.liuda.business.repository.GuessLogRepository;
import com.web.liuda.business.repository.PrizeOptionRepository;
import com.web.liuda.business.repository.PrizeResultRepository;
import com.web.liuda.business.service.PrizeOptionService;
import com.web.liuda.remote.vo.PrizeOptionVo;
import com.web.liuda.remote.vo.PrizeResultVo;
import com.web.liuda.remote.vo.UserVo;

@Service("PrizeOptionService")
@Transactional(readOnly = false)
public class PrizeOptionServiceImpl extends BaseService<PrizeOption> implements PrizeOptionService {

	@Autowired
	private PrizeOptionRepository prizeOptionRepository;
	@Autowired
	private PrizeResultRepository prizeResultRepository;
	@Autowired
	private GuessLogRepository guessLogRepository;
	
	/**
	 * 查询单条PrizeOption信息
	 * @param tId
	 * @return 返回单个PrizeOption对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<PrizeOption> findOne(Long modelId) throws BusinessException {
		PrizeOption obj = new PrizeOption();
		if(modelId != 0){
			obj = prizeOptionRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<PrizeOption> xr = new XaResult<PrizeOption>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的PrizeOption数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象PrizeOption集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<PrizeOption>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<PrizeOption> page = prizeOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), PrizeOption.class), pageable);
		XaResult<Page<PrizeOption>> xr = new XaResult<Page<PrizeOption>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的PrizeOption数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象PrizeOption集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<PrizeOption>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<PrizeOption> page = prizeOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), PrizeOption.class), pageable);
		XaResult<Page<PrizeOption>> xr = new XaResult<Page<PrizeOption>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存PrizeOption信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<PrizeOption> saveOrUpdate(PrizeOption model) throws BusinessException {
		PrizeOption obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = prizeOptionRepository.findOne(model.getId());
		}else{
			obj = new PrizeOption();
		}
		obj.setMatchId(model.getMatchId());
		obj.setLevel(model.getLevel());
		obj.setNum(model.getNum());
		obj.setPrize(model.getPrize());
		obj = prizeOptionRepository.save(obj);
		XaResult<PrizeOption> xr = new XaResult<PrizeOption>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改PrizeOption状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回PrizeOption对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<PrizeOption> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<PrizeOption> xr = new XaResult<PrizeOption>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				PrizeOption obj = prizeOptionRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = prizeOptionRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<List<PrizeOptionVo>> findByMacthIdAndNotStatus(Long matchId, Integer status) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("status", new SearchFilter("status", Operator.NE, status));
		}
		if(XaUtil.isNotEmpty(matchId))
		{
			filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
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
			List<Object[]> prlst = prizeResultRepository.findByMatchIdAndStatusNot(XaConstant.Status.delete,matchId);
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
					grv.setStatus(Integer.parseInt(obj[7].toString()));
					UserVo userVo = new UserVo();
					userVo.setId(Long.parseLong(obj[2].toString()));
					userVo.setUserName(obj[5]==null?null:obj[5].toString());
					userVo.setPhoto(obj[6]==null?null:obj[6].toString());
					userVo.setMobile(obj[8]==null?null:obj[8].toString());
					grv.setUserVo(userVo);
					
					mappo.get(Long.parseLong(obj[4].toString())).getPrizeResultList().add(grv);
				}
			}
		}
		
		XaResult<List<PrizeOptionVo>> xr = new XaResult<List<PrizeOptionVo>>();
		xr.setObject(lstpo);
		return xr;
		
	}

	@Override
	public XaResult<List<PrizeOptionVo>> setPrizeOptionResult(Long matchId) throws BusinessException {
		XaResult<List<PrizeOptionVo>> xr = new XaResult<List<PrizeOptionVo>>();
		//查询是否已经发布抽奖
		List<Object[]> prlst = prizeResultRepository.findByMatchId(XaConstant.Status.publish,matchId);
		if(prlst.size()>0)
		{
			xr.error("抽奖结果已经发布，无法再次抽奖");
			return xr;
		}
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		//查二次抽奖内容(抽奖内容要排序)
		Sort sort = new Sort(new Order(Direction.ASC, "level"));
		List<PrizeOption> polst = prizeOptionRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), PrizeOption.class),sort);
		if(polst.size()==0)
		{
			xr.error("未设置抽奖内容，无法抽奖");
			return xr;
		}
		//查询竞猜总人数
		List<GuessLog> gllst = guessLogRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), GuessLog.class));
		if(gllst.size()==0)
		{
			xr.error("没有人竞猜，无法抽奖");
			return xr;
		}
		//删除之前的抽奖结果
		List<PrizeResult> oprlst = prizeResultRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), PrizeResult.class));
		prizeResultRepository.delete(oprlst);
		
		//以下开始抽奖
		Integer totlePrize = 0;	//所有奖品总数
		//Map<Long,Integer> plmap = new HashMap<Long,Integer>();	//奖品等级，奖品数量
		List<Long> plist = new ArrayList<Long>();
		for(PrizeOption po : polst)
		{
			totlePrize += po.getNum();
			//plmap.put(po.getId(), po.getNum());
			for(int i=0;i<po.getNum();i++)
			{
				plist.add(po.getId());
			}
		}
		
		Collections.shuffle(gllst);//把候选人名单排序打乱
		
		List<GuessLog> prizePople;//中奖人名单
		if(totlePrize>=gllst.size())//所有人都中奖
		{
			prizePople = gllst;
		}
		else//选取部分人中奖
		{
			prizePople = new ArrayList<GuessLog>();
			int totalPople = gllst.size(); //总人数
			for(int i=0;i<totlePrize;i++)
			{
		        int num = (int) (Math.random() * totalPople); // 注意不要写成(int)Math.random()*3，这个结果为0，因为先执行了强制转换
		        prizePople.add(gllst.get(num));
		        gllst.remove(num);
		        totalPople --;
			}
		}
		Collections.shuffle(prizePople);//把中奖人名单排序打乱
		//中奖过程赋值
		for(int i=0;i<prizePople.size();i++)
		{
			PrizeResult pr = new PrizeResult();
			pr.setMatchId(matchId);
			pr.setStatus(XaConstant.Status.valid);
			pr.setPrizeOptionId(plist.get(i));
			pr.setUserId(prizePople.get(i).getUserId());
			pr.setCreateTime(XaUtil.getToDayStr());
			prizeResultRepository.save(pr);
		}
		return xr;
	}

	@Override
	public XaResult<List<PrizeOptionVo>> publishPrize(Long matchId) throws BusinessException {
		XaResult<List<PrizeOptionVo>> xr = new XaResult<List<PrizeOptionVo>>();
		//查询是否已经发布抽奖
		List<Object[]> prlst = prizeResultRepository.findByMatchId(XaConstant.Status.publish,matchId);
		if(prlst.size()>0)
		{
			xr.error("抽奖结果已经发布过了");
			return xr;
		}
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		List<PrizeResult> oprlst = prizeResultRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), PrizeResult.class));
		if(oprlst.size()==0)
		{
			xr.error("尚未抽奖，无法发布");
			return xr;
		}
		for(PrizeResult pr : oprlst)
		{
			pr.setStatus(XaConstant.Status.publish);
			prizeResultRepository.save(pr);
		}
		return xr;
	}
	
}
