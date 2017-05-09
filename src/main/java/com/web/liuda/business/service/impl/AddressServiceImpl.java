package com.web.liuda.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.Address;
import com.web.liuda.business.repository.AddressRepository;
import com.web.liuda.business.service.AddressService;
import com.web.liuda.remote.vo.AddressVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("AddressService")
@Transactional(readOnly = false)
public class AddressServiceImpl extends BaseService<Address> implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * 查询单条Address信息
	 * @param tId
	 * @return 返回单个Address对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Address> findOne(Long modelId) throws BusinessException {
		Address obj = new Address();
		if(modelId != 0){
			obj = addressRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Address> xr = new XaResult<Address>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * 分页查询状态非status的Address数据
	 * @param pageSize
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Address集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<AddressVo>> findAddressList(
			Integer nextPage,Integer pageSize, Map<String, Object> filterParams) throws BusinessException {
		StringBuffer sql=new StringBuffer("select a.id,a.consignee_name,a.mobile,a.province,");
		sql.append("a.city,a.area,a.detail_address,a.is_default,u.user_name,a.status from ");
		sql.append("tb_xa_address a INNER JOIN  tb_xa_user u on a.user_id=u.id ");
		sql.append("where u.`status`<>3 and a.`status`<>3");
		//统计数据库中数据个数
		StringBuffer sql1 = new StringBuffer("select count(*) from ");
		sql1.append("tb_xa_user u inner join tb_xa_address a on a.user_id=u.id ");
		sql1.append("where u.`status`<>3 and a.`status`<>3");
		
		Set<Entry<String,Object>> enties = filterParams.entrySet();
		for(Entry<String,Object> entry : enties){
			//得到键
			String key = entry.getKey();
			//得到值
			Object value = entry.getValue();
			//如果有查询条件
			if(XaUtil.isNotEmpty(value)){
				//将字符串转化为数组
				String[] arr = key.split("_");
				//将查询条件拼接在sql语句的where条件中
				if(key.equals("LIKE_user_name")){
			    sql.append(" and u.").append(arr[1]).append("_").append(arr[2]).append(" like '%").append(value).append("%'");
			    sql1.append(" and u.").append(arr[1]).append("_").append(arr[2]).append(" like '%").append(value).append("%'");
			}
		  }
		}
		sql.append(" order by a.create_time desc ");
		List<Object[]> objs=this.query(sql.toString(), nextPage*pageSize, pageSize);
		List<AddressVo> vos=new ArrayList<AddressVo>();
		for (Object[] obj : objs) {	
			AddressVo vo=new AddressVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setConsigneeName((String)obj[1]);
			vo.setMobile((String)obj[2]);
			vo.setProvince((String)obj[3]);
			vo.setCity((String)obj[4]);
			vo.setArea((String)obj[5]);
			vo.setDetailAddress((String)obj[6]);
			vo.setIsDefault(((Integer)obj[7]).intValue());
			vo.setUserName((String)obj[8]);
		    vo.setStatus(((Integer)obj[9]).intValue());
			vos.add(vo);
		}
		
		//执行查询语句
		List<Object[]> obj1=this.query(sql1.toString(), null, null);
		//将得到的查询结果强转成int类型
		int count=Integer.parseInt(obj1.get(0)+"");				
		//调用分页
		Page<AddressVo> page= new MyPage<AddressVo>(nextPage.intValue(),pageSize.intValue(),vos,count);
		//创建结果集对象xr
		XaResult<Page<AddressVo>> xr=new XaResult<Page<AddressVo>>();
		//将page放入xr
		xr.setObject(page);
		return xr;
	}
	/**
	 * 查询详细的Address数据
	 * @param nextPage
	 * @param pageSize
	 * @param filterParams
	 * @return 返回对象Address集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<AddressVo> findAddressDetail(Long id) throws BusinessException {
		StringBuffer sql=new StringBuffer("select a.id,a.consignee_name,a.mobile,a.province,");
		sql.append("a.city,a.area,a.detail_address,a.is_default,u.user_name,a.status from ");
		sql.append("tb_xa_address a INNER JOIN  tb_xa_user u on a.user_id=u.id ");
		sql.append("where u.`status`<>3 and a.`status`<>3 and a.id=?");
		List<Object> params=new ArrayList<Object>();
		//创建结果集对象xr
		XaResult<AddressVo> xr=new XaResult<AddressVo>();
		if(XaUtil.isNotEmpty(id)){
			params.add(id);
			List<Object[]> objs=this.queryParams(sql.toString(), null, null, params);
			AddressVo vo=new AddressVo();
			for (Object[] obj : objs) {	
				vo.setId(((BigInteger)obj[0]).longValue());
				vo.setConsigneeName((String)obj[1]);
				vo.setMobile((String)obj[2]);
				vo.setProvince((String)obj[3]);
				vo.setCity((String)obj[4]);
				vo.setArea((String)obj[5]);
				vo.setDetailAddress((String)obj[6]);
				vo.setIsDefault(((Integer)obj[7]).intValue());
				vo.setUserName((String)obj[8]);
			    vo.setStatus(((Integer)obj[9]).intValue());
			}
			xr.setObject(vo);
		}else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Address数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Address集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Address>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Address> page = addressRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Address.class), pageable);
		XaResult<Page<Address>> xr = new XaResult<Page<Address>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Address数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Address集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Address>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Address> page = addressRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Address.class), pageable);
		XaResult<Page<Address>> xr = new XaResult<Page<Address>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Address信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Address> saveOrUpdate(Address model) throws BusinessException {
		Address obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = addressRepository.findOne(model.getId());
		}else{
			obj = new Address();
		}
		obj.setConsigneeName(model.getConsigneeName());
		obj.setMobile(model.getMobile());
		obj.setProvince(model.getProvince());
		obj.setCity(model.getCity());
		obj.setArea(model.getArea());
		obj.setDetailAddress(model.getDetailAddress());
		obj.setIsDefault(model.getIsDefault());
		obj = addressRepository.save(obj);
		XaResult<Address> xr = new XaResult<Address>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Address状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Address对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Address> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Address> xr = new XaResult<Address>();
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
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
