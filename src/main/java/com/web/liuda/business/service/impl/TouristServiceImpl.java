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
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Tourist;
import com.web.liuda.business.repository.TouristRepository;
import com.web.liuda.business.service.TouristService;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.liuda.remote.vo.TouristVo;

@Service("TouristService")
@Transactional(readOnly = false)
public class TouristServiceImpl extends BaseService<Tourist> implements TouristService {

	@Autowired
	private TouristRepository touristRepository;
	
	/**
	 * 查询单条Tourist信息
	 * @param tId
	 * @return 返回单个Tourist对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Tourist> findOne(Long modelId) throws BusinessException {
		Tourist obj = new Tourist();
		if(modelId != 0){
			obj = touristRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Tourist> xr = new XaResult<Tourist>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * 查询单条TouristVo信息
	 * @param tId
	 * @return 返回单个TouristVo对象
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<TouristVo> newFindOne(Long model) {
		XaResult<TouristVo> xr=new XaResult<TouristVo>();
		StringBuffer sql=new StringBuffer("");
		sql.append("select t.tourist_name,t.mobile,t.telphone,t.tourist_desc,t.business_time,t.address,t.img_url,GROUP_CONCAT(i.picurl),t.notes ");
		sql.append("from tb_xa_tourist t INNER JOIN tb_xa_images i on t.id=i.object_id");
		sql.append(" where t.id=? and i.type="+JConstant.ImageType.TOURIST+"");
		List<Object> params=new ArrayList<Object>();
		params.add(model);
		List<Object[]> objs=this.queryParams(sql.toString(), 0, 0, params);
		for(Object[] obj:objs){
			TouristVo t=new TouristVo();
			t.setTicketName((String)obj[0]);
			t.setMobile((String)obj[1]);
			t.setTelphone((String)obj[2]);
			t.setTouristDesc((String)obj[3]);
			t.setBusinessTime((String)obj[4]);
			t.setAddress((String)obj[5]);
			t.setPicurl((String)obj[7]);
			t.setNotes((String)obj[8]);
			xr.setObject(t);
		}
		return xr;
	}
	/**
	 * 分页查询状态非status的Tourist数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Tourist集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Tourist>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Tourist> page = touristRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Tourist.class), pageable);
		XaResult<Page<Tourist>> xr = new XaResult<Page<Tourist>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Tourist数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Tourist集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Tourist>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Tourist> page = touristRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Tourist.class), pageable);
		XaResult<Page<Tourist>> xr = new XaResult<Page<Tourist>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Tourist信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<BusinessInfoVo> newSaveOrUpdate(Tourist model,String[] arr) {
		//判断该用户是否创建过商家资料
		XaResult<BusinessInfoVo> xr = new XaResult<BusinessInfoVo>();
		Tourist  tourist = touristRepository.findByBusinessUserIdAndStatusNot(model.getBusinessUserId(), XaConstant.Status.delete);
			if(XaUtil.isEmpty(tourist)){
				//保存景点
				Tourist obj = new Tourist();
				obj.setStatus(XaConstant.Status.publish);
				obj.setTouristName(model.getTouristName());
				obj.setImgUrl(model.getImgUrl());
				obj.setTelphone(model.getTelphone());
				obj.setMobile(model.getMobile());
				obj.setBusinessTime(model.getBusinessTime());
				obj.setAddress(model.getAddress());
				obj.setTouristDesc(model.getTouristDesc());
				obj.setNotes(model.getNotes());
				obj.setLng(HttpURLConnectionUtil.getLocation(model.getAddress()).getResult().getLocation().getLng());
				obj.setLat(HttpURLConnectionUtil.getLocation(model.getAddress()).getResult().getLocation().getLat());
				obj.setBusinessUserId(model.getBusinessUserId());
				obj = touristRepository.save(obj);
				
				//把图保存到image表中
				for(int i=0;i<arr.length;i++){
				  String sql="insert into tb_xa_images(create_time,object_id,picurl,sort,type) " +
						"values("+"'"+XaUtil.getToDayStr()+"'"+","+obj.getId()+","+"'"+arr[i]+"'"+",0,"+JConstant.ImageType.TOURIST+")";
				    this.insert(sql);
				}
				
				//返回BusinessInfoVo，前端设置Cookie
				BusinessInfoVo businessInfoVo=new BusinessInfoVo();
				businessInfoVo.setId(model.getBusinessUserId());
				businessInfoVo.setGoodsId(obj.getId());
				businessInfoVo.setType(1);
				businessInfoVo.setBusinessName(model.getTouristName());
				xr.setObject(businessInfoVo);
			}else{
				xr.error("该账户已使用！");
			}
		return xr;
	}

	/**
	 * 修改Tourist状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Tourist对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Tourist> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Tourist> xr = new XaResult<Tourist>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Tourist obj = touristRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = touristRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	public XaResult<Tourist> saveOrUpdate(Tourist t) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 通过商品id查找商品
	 * @param model
	 * @return 返回Tourist对象
	 * @throws BusinessException
	 */
	@Override
	public XaResult<Tourist> findTouistByBusinessId(Long model) {
		XaResult<Tourist> xr=new XaResult<Tourist>();
		Tourist tourist=touristRepository.findByBusinessUserIdAndStatusNot(model, XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(tourist)){
			xr.setObject(tourist);
		}else{
			xr.error("查询失败");
		}
		return xr;
	}
	@Override
	public XaResult<BusinessInfoVo> updataTouist(Long id, Tourist tourist,
			String[] arr) {
		Double lng=HttpURLConnectionUtil.getLocation(tourist.getAddress()).getResult().getLocation().getLng();
		Double lat=HttpURLConnectionUtil.getLocation(tourist.getAddress()).getResult().getLocation().getLat();
		//把图保存到image表中
		for(int i=0;i<arr.length;i++){
			String sql="insert into tb_xa_images(create_time,object_id,picurl,sort,type) " +
			"values("+"'"+XaUtil.getToDayStr()+"'"+","+id+","+"'"+arr[i]+"'"+",0,"+JConstant.ImageType.TOURIST+")";
			this.insert(sql);
		}
		
		//修改数据
		String sql="UPDATE tb_xa_tourist t set " +
				"t.tourist_name="+"'"+tourist.getTouristName()+"'"+",t.lat="+lat+",t.lng="+lng+",t.mobile="+"'"+tourist.getMobile()+"'"+",t.img_url="+"'"+arr[0]+"'"+",t.telphone="+"'"+tourist.getTelphone()+"'"+"," +
				"t.business_time="+"'"+tourist.getBusinessTime()+"'"+",t.tourist_desc="+"'"+tourist.getTouristDesc()+"'"+",t.notes="+"'"+tourist.getNotes()+"'"+",t.address="+"'"+tourist.getAddress()+"'"+" " +
				"where t.id="+id+"";
		this.insert(sql);		
		//返回BusinessInfoVo 设置Cookie
			System.out.println();
			  XaResult<BusinessInfoVo> xr = new XaResult<BusinessInfoVo>();
			  BusinessInfoVo businessInfoVo=new BusinessInfoVo();
			  businessInfoVo.setId(tourist.getBusinessUserId());
			  businessInfoVo.setGoodsId(id);
			  businessInfoVo.setType(1);
			  businessInfoVo.setBusinessName(tourist.getTouristName());
			  xr.setObject(businessInfoVo);
			  return xr;
	}

	


	
}
