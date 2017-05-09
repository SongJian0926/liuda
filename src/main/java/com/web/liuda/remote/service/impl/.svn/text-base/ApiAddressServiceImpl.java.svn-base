package com.web.liuda.remote.service.impl;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Address;
import com.web.liuda.remote.vo.AddressVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.repository.AddressRepository;
import com.web.liuda.remote.service.ApiAddressService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIAddress接口实现类
 **/
@Service("ApiAddressService")
@Transactional(readOnly = false)
public class ApiAddressServiceImpl extends BaseService<Address> implements ApiAddressService{

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public XaResult<AddressVo> findOne(Long tId) throws BusinessException {
		Address obj = addressRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<AddressVo> xr = new XaResult<AddressVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),AddressVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<AddressVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Address> page = addressRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Address.class), pageable);
		XaResult<List<AddressVo>> xr = new XaResult<List<AddressVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), AddressVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<AddressVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Address> page = addressRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Address.class), pageable);
		XaResult<List<AddressVo>> xr = new XaResult<List<AddressVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), AddressVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<AddressVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<AddressVo> xr = new XaResult<AddressVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Address obj = addressRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = addressRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), AddressVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<AddressVo> createModel(Address model)
			throws BusinessException {
		XaResult<AddressVo> xr = new XaResult<AddressVo>();
		Address obj = null;
		if(model.getIsDefault()==JConstant.BooleanStatus.TRUE){
			List<Address> address=addressRepository.findByUserIdAndStatusNot(model.getUserId(), XaConstant.Status.delete);
			if(address.size()>0){
				String sql="update tb_xa_address set is_default=" + JConstant.BooleanStatus.FALSE + " where user_id=? and status<>" + XaConstant.Status.delete;
				List<Object> params=new ArrayList<Object>(); 
			    params.add(model.getUserId());
				Integer count=this.delete(sql, params);
				if(count>0){
					obj = addressRepository.save(model);
				}else{
					xr.error("新增失败！");
					return xr;
				}
			}else{
				obj = addressRepository.save(model);	
			}
		}else{
			obj = addressRepository.save(model);	
		}
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), AddressVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<AddressVo> updateModel(Address model)
			throws BusinessException {
		Address obj = addressRepository.findByIdAndStatusNot(model.getId(),XaConstant.Status.delete);
		XaResult<AddressVo> xr = new XaResult<AddressVo>();
		if (XaUtil.isNotEmpty(obj)) {
			if(XaUtil.isNotEmpty(model.getConsigneeName())){
				obj.setConsigneeName(model.getConsigneeName());
			}
			if(XaUtil.isNotEmpty(model.getMobile())){
				obj.setMobile(model.getMobile());
			}
			if(XaUtil.isNotEmpty(model.getProvince())){
				obj.setProvince(model.getProvince());
			}
			if(XaUtil.isNotEmpty(model.getCity())){
				obj.setCity(model.getCity());
			}
			if(XaUtil.isNotEmpty(model.getArea())){
				obj.setArea(model.getArea());
			}
			if(XaUtil.isNotEmpty(model.getDetailAddress())){
				obj.setDetailAddress(model.getDetailAddress());
			}
			/*如果是否默认不为空，如果该地址为默认地址不可将此地址改为非默认地址；
			 *如果该地址为非默认地址，将此地址改为默认地址，要将该用户之前的默认地址改为非默认地址*/
			if(XaUtil.isNotEmpty(model.getIsDefault())){
				if(model.getIsDefault()==JConstant.BooleanStatus.TRUE){
					List<Address> address=addressRepository.findByUserIdAndStatusNot(obj.getUserId(), XaConstant.Status.delete);
					if(address.size()>1){
						String sql="update tb_xa_address set is_default=" + JConstant.BooleanStatus.FALSE + " where user_id=? and " +
								"id<>"+ obj.getId() +" and status<>" + XaConstant.Status.delete;
						List<Object> params=new ArrayList<Object>(); 
					    params.add(obj.getUserId());
						Integer count=this.delete(sql, params);
						if(count>0){
							obj.setIsDefault(model.getIsDefault());
							obj = addressRepository.save(obj);	
						}else{
							xr.error("地址修改失败！");
							return xr;
						}
					}else{
						obj.setIsDefault(model.getIsDefault());
						obj = addressRepository.save(obj);	
					}
				}
				if(model.getIsDefault()==JConstant.BooleanStatus.FALSE){
					obj.setIsDefault(model.getIsDefault());
					obj = addressRepository.save(obj);
				}
				/*if(model.getIsDefault()==JConstant.BooleanStatus.FALSE){
					if(obj.getIsDefault()==JConstant.BooleanStatus.TRUE){
						xr.error("不能将此地址改为非默认地址！必须有一个默认地址");
						return xr;
					}
					obj.setIsDefault(model.getIsDefault());
					obj = addressRepository.save(obj);
				}*/
			}else{
				obj = addressRepository.save(obj);
			}
			/*if(XaUtil.isNotEmpty(model.getIsDefault())){
				obj.setIsDefault(model.getIsDefault());
			}*/
			obj = addressRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), AddressVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
