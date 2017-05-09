package com.web.liuda.remote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.UserCard;
import com.web.liuda.business.repository.UserCardRepository;
import com.web.liuda.remote.service.ApiUserCardService;
import com.web.liuda.remote.vo.UserCardVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIUserCard接口实现类
 **/
@Service("ApiUserCardService")
@Transactional(readOnly = false)
public class ApiUserCardServiceImpl extends BaseService<UserCard> implements ApiUserCardService{

	@Autowired
	UserCardRepository userCardRepository;
	
	@Override
	public XaResult<UserCardVo> findOne(Long tId) throws BusinessException {
		UserCard obj = userCardRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<UserCardVo> xr = new XaResult<UserCardVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),UserCardVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<UserCardVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<UserCard> page = userCardRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), UserCard.class), pageable);
		XaResult<List<UserCardVo>> xr = new XaResult<List<UserCardVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), UserCardVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<UserCardVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<UserCard> page = userCardRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), UserCard.class), pageable);
		XaResult<List<UserCardVo>> xr = new XaResult<List<UserCardVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), UserCardVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	/*
	 * 银行卡列表
	 * author:changlu
	 * time:2016-04-26 10:13:00
	 */
	public XaResult<List<UserCardVo>> findUserCardList(Long userId) throws BusinessException {
		List<UserCard> usercards=userCardRepository.findByUserIdAndStatus(userId, XaConstant.Status.valid);
		XaResult<List<UserCardVo>> xr = new XaResult<List<UserCardVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(usercards), UserCardVo.class));
		for(int i=0;i<usercards.size();i++){
			xr.getObject().get(i).setId(usercards.get(i).getId());
		}
		return xr;
	}
	@Override
	public XaResult<UserCardVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<UserCardVo> xr = new XaResult<UserCardVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				UserCard obj = userCardRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = userCardRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), UserCardVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//绑定银行卡
	@Override
	public XaResult<UserCardVo> createModel(UserCard model)
			throws BusinessException {
		XaResult<UserCardVo> xr = new XaResult<UserCardVo>();
		List<UserCard> uc = userCardRepository.findByUserIdAndStatus(model.getUserId(), XaConstant.Status.valid);
		if(XaUtil.isEmpty(uc)){
			UserCard obj = userCardRepository.save(model);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), UserCardVo.class));
			xr.getObject().setId(obj.getId());
		}else{
			xr.error("添加失败,该用户已绑定银行卡");
		}
		return xr;
	}

	@Override
	public XaResult<UserCardVo> updateModel(UserCard model)
			throws BusinessException {
		UserCard obj = userCardRepository.findOne(model.getId());
		XaResult<UserCardVo> xr = new XaResult<UserCardVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setBankCardId(model.getBankCardId());
		obj.setCardHolder(model.getCardHolder());
		obj.setIdcard(model.getIdcard());
		obj.setOpeningBank(model.getOpeningBank());
		obj.setOpeningArea(model.getOpeningArea());
		obj.setSubBankNam(model.getSubBankNam());
			obj = userCardRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), UserCardVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//修改银行卡
	@Transactional
	public XaResult<String> updataUserCard(Long userId,Long id, UserCard model) {
		XaResult<String> xr =new XaResult<String>();
		UserCard userCard = userCardRepository.findByUserIdAndIdAndStatus(userId,id, XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(userCard)){
			userCard.setBankCardId(model.getBankCardId());
			userCard.setCardHolder(model.getCardHolder());
			userCard.setIdcard(model.getIdcard());
			userCard.setOpeningBank(model.getOpeningBank());
			userCard.setOpeningArea(model.getOpeningArea());
			userCard.setSubBankNam(model.getSubBankNam());
			userCardRepository.save(userCard);
			xr.setMessage("修改成功");
		}else{
			xr.error("修改失败,该银行卡不存在");
		}
		return xr;
	}

}
