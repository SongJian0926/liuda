package com.web.liuda.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.constant.position.ShowLocation;
import com.web.liuda.business.entity.Hotel;
import com.web.liuda.business.entity.Images;
import com.web.liuda.business.repository.HotelRepository;
import com.web.liuda.business.repository.ImagesRepository;
import com.web.liuda.business.service.HotelService;
import com.web.liuda.business.service.ImagesService;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("HotelService")
@Transactional(readOnly = false)
public class HotelServiceImpl extends BaseService<Hotel> implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private ImagesRepository imagesRepository;
	
	@Autowired
	private ImagesService imagesService;
	/**
	 * 查询单条Hotel信息
	 * @param tId
	 * @return 返回单个Hotel对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Hotel> findOne(Long modelId) throws BusinessException {
		Hotel obj = new Hotel();
		if(modelId != 0){
			obj = hotelRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Hotel> xr = new XaResult<Hotel>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * 查询单条HotelVo信息
	 * @param tId
	 * @return 返回单个HotelVo对象
	 * 
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<HotelVo> newFindOne(Long modelId) {
		XaResult<HotelVo> xr=new XaResult<HotelVo>();
		StringBuffer sql=new StringBuffer("");
		sql.append("select t.hotel_name,t.mobile,t.telphone,t.introduce,t.address,t.policy,t.prompt,t.picurl,GROUP_CONCAT(i.picurl),t.hotel_type ");
		sql.append("from tb_xa_hotel t INNER JOIN tb_xa_images i on t.id=i.object_id");
		sql.append(" where t.id=? and i.type="+JConstant.ImageType.HOTEL+"");
		List<Object> params=new ArrayList<Object>();
		params.add(modelId);
		List<Object[]> objs=this.queryParams(sql.toString(), 0, 0, params);
		for(Object[] obj:objs){
			HotelVo t=new HotelVo();
			t.setHotelName((String)obj[0]);
			t.setMobile((String)obj[1]);
			t.setTelphone((String)obj[2]);
			t.setIntroduce((String)obj[3]);
			t.setAddress((String)obj[4]);
			t.setPolicy((String)obj[5]);
			t.setPrompt((String)obj[6]);
			
			t.setPicurl((String)obj[8]);

			t.setHotelType((String)obj[9]);
			xr.setObject(t);
		}
		return xr;
	}
	/**
	 * 查询Hotel信息
	 * @param tId
	 * @return 返回单个Hotel对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<HotelVo> findHotelById(Long modelId) throws BusinessException {
		XaResult<HotelVo> xr = new XaResult<HotelVo>();
		HotelVo vo = new HotelVo();
		if(modelId != 0){
			StringBuffer sql=new StringBuffer("select h.hotel_name,h.picurl,h.hotel_type,h.telphone,h.address,h.policy,h.prompt,h.introduce,");
			sql.append("(SELECT GROUP_CONCAT(i.picurl) from tb_xa_images i WHERE i.object_id=h.id AND i.status<>"+XaConstant.Status.delete+" AND i.type="+JConstant.ObjectType.HOTEL+") pics,h.id ");
			sql.append(" from tb_xa_hotel h ");
			sql.append(" WHERE h.status<>"+XaConstant.Status.delete+" and h.id=?");
			List<Object> params=new ArrayList<Object>(); 
		    params.add(modelId);
		    List<Object[]> objs=this.queryParams(sql.toString(), null, null, params);
		    for(Object[] obj : objs){
				//遍历obj集合，将其放入vo中
		    	vo.setHotelName((String)obj[0]);
				vo.setPicurl((String)obj[1]);
				vo.setHotelType((String)obj[2]);
				vo.setTelphone((String)obj[3]);
				vo.setAddress((String)obj[4]);
				vo.setPolicy((String)obj[5]);
				vo.setPrompt((String)obj[6]);
				vo.setIntroduce((String)obj[7]);
				List<String> pics1 =null;
				String[] pic=null;
				if(XaUtil.isNotEmpty((String)obj[8])){
					pic= ((String)obj[8]).replaceAll("，", ",").split(",");
					pics1 = Arrays.asList(pic);
				}
				vo.setPics1(pics1);
				vo.setId(((BigInteger)obj[9]).longValue());
			}
		}
		if (XaUtil.isNotEmpty(vo)) {
			xr.setObject(vo);
		}else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * 分页查询状态非status的Hotel数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Hotel集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Hotel>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Hotel> page = hotelRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Hotel.class), pageable);
		XaResult<Page<Hotel>> xr = new XaResult<Page<Hotel>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Hotel数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Hotel集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Hotel>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Hotel> page = hotelRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Hotel.class), pageable);
		XaResult<Page<Hotel>> xr = new XaResult<Page<Hotel>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Information信息
	 * @param model
	 * @return businessInfoVo
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<BusinessInfoVo> newSaveModel(Hotel model,String[] arr) {
	//判断该账户是否使用
		Hotel hotel = hotelRepository.findByBusinessUserIdAndStatusNot(model.getBusinessUserId(), XaConstant.Status.delete);
		XaResult<BusinessInfoVo> xr = new XaResult<BusinessInfoVo>();
			if(XaUtil.isEmpty(hotel)){
				//保存酒店信息
				Hotel obj = new Hotel();
				obj.setStatus(1);
				obj.setHotelName(model.getHotelName());
				obj.setMobile(model.getMobile());
				obj.setPicurl(model.getPicurl());
				obj.setHotelType(model.getHotelType());
				obj.setTelphone(model.getTelphone());
				obj.setAddress(model.getAddress());
				obj.setPolicy(model.getPolicy());
				obj.setPrompt(model.getPrompt());
				obj.setIntroduce(model.getIntroduce());
				obj.setBusinessUserId(model.getBusinessUserId());
				obj.setLng(HttpURLConnectionUtil.getLocation(model.getAddress()).getResult().getLocation().getLng());
				obj.setLat(HttpURLConnectionUtil.getLocation(model.getAddress()).getResult().getLocation().getLat());
				obj = hotelRepository.save(obj);
				
				//把图保存到image表中
				for(int i=0;i<arr.length;i++){
					String sql="insert into tb_xa_images(create_time,object_id,picurl,sort,type) " +
					"values("+"'"+XaUtil.getToDayStr()+"'"+","+obj.getId()+","+"'"+arr[i]+"'"+",0,"+JConstant.ImageType.HOTEL+")";
					 this.insert(sql);
				}
				//返回BusinessInfoVo 设置Cookie
				BusinessInfoVo businessInfoVo=new BusinessInfoVo();
				businessInfoVo.setId(model.getBusinessUserId());
				businessInfoVo.setGoodsId(obj.getId());
				businessInfoVo.setType(0);
				businessInfoVo.setBusinessName(model.getHotelName());
				xr.setObject(businessInfoVo);
			}else{
				xr.error("该账户已使用！");
			}
		return xr;
	}
	
	/**
	 * 保存Hotel信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Hotel> newSaveOrUpdate(Hotel model,ArrayList<String> list) throws BusinessException {
		Hotel obj = null;
		if(XaUtil.isNotEmpty(model.getId())&&(model.getId()>0)){
			obj = hotelRepository.findOne(model.getId());
			if(XaUtil.isNotEmpty(model.getAddress())){
				if(!model.getAddress().equals(obj.getAddress())){
					ShowLocation location = HttpURLConnectionUtil.getLocation(model.getAddress().replaceAll(" ", ""));
					obj.setLng(location.getResult().getLocation().getLng());
					obj.setLat(location.getResult().getLocation().getLat());
				}
			}
		}else{
			obj = new Hotel();
			if(XaUtil.isNotEmpty(model.getAddress())){
				ShowLocation location = HttpURLConnectionUtil.getLocation(model.getAddress().replaceAll(" ", ""));
				obj.setLng(location.getResult().getLocation().getLng());
				obj.setLat(location.getResult().getLocation().getLat());
			}
		}
		obj.setHotelName(model.getHotelName());
		obj.setPicurl(model.getPicurl());
		obj.setHotelType(model.getHotelType());
		obj.setTelphone(model.getTelphone());
		obj.setAddress(model.getAddress());
		obj.setPolicy(model.getPolicy());
		obj.setPrompt(model.getPrompt());
		obj.setIntroduce(model.getIntroduce());
		obj = hotelRepository.save(obj);
		if(XaUtil.isNotEmpty(obj)){
			//channelIds不为空
			if(XaUtil.isNotEmpty(list)){
				//在存入图片之前删除之前的图片
				Images ic = new Images();
				ic.setObjectId(XaUtil.isEmpty(model.getId())?null:model.getId().longValue());
				String sql="delete from tb_xa_images where object_id=? and type=0";
				List<Object> params=new ArrayList<Object>(); 				   
			    params.add(ic.getObjectId());		
			    this.delete(sql, params);				
				
				//遍历数组labels
				for(String l : list){
					Images la = new Images();
					la.setObjectId((obj.getId()).longValue());
					la.setPicurl(l);
					la.setType(JConstant.ImageType.HOTEL);
					//保存InfoChannel对象
					imagesRepository.save(la);
				}
			}		
		}
		XaResult<Hotel> xr = new XaResult<Hotel>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Hotel状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Hotel对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Hotel> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Hotel> xr = new XaResult<Hotel>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Hotel obj = hotelRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = hotelRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//通过商户id查找酒店实体
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Hotel> findHotelByBusinessId(Long modelId)
			throws BusinessException {
		XaResult<Hotel> xr=new XaResult<Hotel>();
		Hotel hotel=hotelRepository.findByBusinessUserIdAndStatusNot(modelId, XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(hotel)){
			xr.setObject(hotel);
		}else{
			xr.error("查询失败");
		}
		return xr;
	}
	@Override
	public XaResult<Hotel> saveOrUpdate(Hotel t) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public XaResult<BusinessInfoVo> updataHotel(Long modelId, Hotel hotel,
			String[] arr) {
		
		XaResult<BusinessInfoVo> xr = new XaResult<BusinessInfoVo>();
		Double lng;
		Double lat;
			lng=HttpURLConnectionUtil.getLocation(hotel.getAddress()).getResult().getLocation().getLng();
			lat=HttpURLConnectionUtil.getLocation(hotel.getAddress()).getResult().getLocation().getLat();
		
		//把图保存到image表中
				for(int i=0;i<arr.length;i++){
					String sql="insert into tb_xa_images(create_time,object_id,picurl,sort,type) " +
					"values("+"'"+XaUtil.getToDayStr()+"'"+","+modelId+","+"'"+arr[i]+"'"+",0,"+JConstant.ImageType.HOTEL+")";
					 this.insert(sql);
				}
		
		//修改数据
		String sql="update tb_xa_hotel set hotel_name="+"'"+hotel.getHotelName()+"'"+", " +
				   "address="+"'"+hotel.getAddress()+"'"+"," +
				   "hotel_type="+"'"+hotel.getHotelType()+"'"+"" +
				   ",introduce="+"'"+hotel.getIntroduce()+"'"+"," +
				   " picurl="+"'"+arr[0]+"'"+",lat="+lat+",policy="+"'"+hotel.getPolicy()+"'"+"," +
				   " prompt="+"'"+hotel.getPrompt()+"'"+",lng="+lng+",telphone="+"'"+hotel.getTelphone()+"'"+"," +
				   " mobile="+"'"+hotel.getMobile()+"'"+" where id="+modelId+"";
	    this.insert(sql);
	  //返回BusinessInfoVo 设置Cookie
	  		BusinessInfoVo businessInfoVo=new BusinessInfoVo();
	  		businessInfoVo.setId(hotel.getBusinessUserId());
	  		businessInfoVo.setGoodsId(modelId);
	  		businessInfoVo.setType(0);
	  		businessInfoVo.setBusinessName(hotel.getHotelName());
	  		xr.setObject(businessInfoVo);
	  		return xr;
	}
	
	
	
}
