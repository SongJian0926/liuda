package com.web.liuda.remote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.Tickets;
import com.web.liuda.business.repository.TicketsRepository;
import com.web.liuda.remote.service.ApiTicketsService;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: 常璐
 * @times: 2015-12-30 14：00：00
 * 类的说明：前端APITickets接口实现类
 **/
@Service("ApiTicketsService")
@Transactional(readOnly = false)
public class ApiTicketsServiceImpl extends BaseService<Tickets> implements ApiTicketsService{

	@Autowired
	TicketsRepository ticketsRepository;
	
	/**
	 * author：常璐
	 * createTime：2015-12-30 14:07
	 * 查询单条TicketsVo信息
	 * @param id
	 * @return 返回单个TicketsVo对象
	 * @throws BusinessException
	 */
	@Override
	public XaResult<TicketsVo> findTicketsDetail(Long id) throws BusinessException {
		XaResult<TicketsVo> xr=new XaResult<TicketsVo>();
		//根据Id查询已发布的门票
		Tickets obj= ticketsRepository.findByIdAndStatus(id, XaConstant.Status.publish);
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), TicketsVo.class));
			if(XaUtil.isNotEmpty(xr.getObject().getValidity())&&xr.getObject().getValidity().compareTo(XaUtil.getToDayStr().substring(0, 10))<0){
				xr.getObject().setGroupBuy(0);
				xr.getObject().setValidity(xr.getObject().getValidity());
				xr.getObject().setGroupPrice(xr.getObject().getGroupPrice());
			}else{
				/** 参加团购需要的字段 start */
				xr.getObject().setGroupBuy(xr.getObject().getGroupBuy());
				xr.getObject().setValidity(xr.getObject().getValidity());
				xr.getObject().setGroupPrice(xr.getObject().getGroupPrice());
			}
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}