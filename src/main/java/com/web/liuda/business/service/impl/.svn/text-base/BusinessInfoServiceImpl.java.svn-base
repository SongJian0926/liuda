package com.web.liuda.business.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.BusinessInfo;
import com.web.liuda.business.repository.BusinessInfoRepository;
import com.web.liuda.business.repository.HotelRepository;
import com.web.liuda.business.repository.TouristRepository;
import com.web.liuda.business.service.BusinessInfoService;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MD5Util;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("BusinessInfoService")
@Transactional(readOnly = false)
public class BusinessInfoServiceImpl extends BaseService<BusinessInfo> implements BusinessInfoService {
	
	@Autowired
	private BusinessInfoRepository businessInfoRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private TouristRepository touristRepository;

/**
* @param account
* @param password
* @return 返回单个BusinessInfo对象
* @throws BusinessException
*/
@Transactional(readOnly = true, rollbackFor = Exception.class)
public XaResult<Object> findBusinessByAccount(String account,
		String password) throws BusinessException {
	XaResult<Object> xr=new XaResult<Object>();
/*	BusinessInfo obj=businessInfoRepository.findByAccountAndStatusNot(account,XaConstant.Status.delete);*/
	String sqlGetUser="select t.account,t.`password`,t.id,t.status  from tb_xa_businessinfo t where t.account=?";
	List<Object> param=new ArrayList<Object>();
	param.add(account);
	List<Object[]> objsUser=this.queryParams(sqlGetUser, 0, 0, param);
	BusinessInfo obj=new BusinessInfo();
	for(Object[] m:objsUser){
		obj.setAccount((String)m[0]);
		obj.setPassword((String)m[1]);
		obj.setId(((BigInteger)m[2]).longValue());
		obj.setStatus((Integer)m[3]);
	}
	if(XaUtil.isNotEmpty(objsUser)){
		BusinessInfoVo businessInfoVo=new BusinessInfoVo();
		if(obj.getStatus()==XaConstant.Status.locked){
			 xr.error("该账户已经停用");
			 return xr;
		}
		if(obj.getPassword().equals(password)){
			  StringBuffer sql=new StringBuffer("");
			  sql.append("select * from(select  h.id,h.business_user_id,h.hotel_name objectName,0 type from tb_xa_hotel h ");
			  sql.append("union");
			  sql.append(" select t.id,t.business_user_id,t.tourist_name objectName,1 type from tb_xa_tourist t)th");
			  sql.append(" where th.business_user_id=?");
			  List<Object> params=new ArrayList<Object>();
			  params.add(obj.getId());
			  List<Object[]> objs=this.queryParams(sql.toString(), 0, 0, params);
				 if(XaUtil.isNotEmpty(objs)){
					 for(Object[] b:objs){
					   businessInfoVo.setGoodsId(((BigInteger)b[0]).longValue());
					   businessInfoVo.setId(((BigInteger)b[1]).longValue());
					   businessInfoVo.setBusinessName((String)b[2]);
					   businessInfoVo.setType(((BigInteger)b[3]).intValue());
					  }
					  xr.setObject(businessInfoVo);
			  }else{
				 businessInfoVo.setId(obj.getId());
				 businessInfoVo.setType(XaConstant.Status.delete);
				 xr.setObject(businessInfoVo);
			  }
		}else{
		   xr.error("密码不正确");
		}
	}else{
		  xr.error("账户不正确");
	}
return xr;
}
	/**
	 * 查询单条BusinessInfo信息
	 * @param tId
	 * @return 返回单个BusinessInfo对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<BusinessInfo> findOne(Long modelId) throws BusinessException {
		BusinessInfo obj = new BusinessInfo();
		if(modelId != 0){
			obj = businessInfoRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<BusinessInfo> xr = new XaResult<BusinessInfo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的BusinessInfo数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象BusinessInfo集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<BusinessInfo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<BusinessInfo> page = businessInfoRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), BusinessInfo.class), pageable);
		XaResult<Page<BusinessInfo>> xr = new XaResult<Page<BusinessInfo>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的BusinessInfo数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象BusinessInfo集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<BusinessInfo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<BusinessInfo> page = businessInfoRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), BusinessInfo.class), pageable);
		XaResult<Page<BusinessInfo>> xr = new XaResult<Page<BusinessInfo>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存BusinessInfo信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<BusinessInfo> saveOrUpdate(BusinessInfo model) throws BusinessException {
		BusinessInfo obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = businessInfoRepository.findOne(model.getId());
		}else{
			obj = new BusinessInfo();
		}
		obj.setAccount(model.getAccount());
		obj.setPassword(model.getPassword());
		obj = businessInfoRepository.save(obj);
		XaResult<BusinessInfo> xr = new XaResult<BusinessInfo>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改BusinessInfo状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回BusinessInfo对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<BusinessInfo> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<BusinessInfo> xr = new XaResult<BusinessInfo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				BusinessInfo obj = businessInfoRepository.findOne(Long.parseLong(id));
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = businessInfoRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * 验证手机号码  查询该手机是否存在
	 * @param mobile
	 * @return 返回Object对象 true 存在 flase 不存在
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Object> mobileCheck(String mobile) throws BusinessException {
		XaResult<Object> xr=new XaResult<Object>();
		StringBuffer sql=new StringBuffer("");
		sql.append("select * from(select  h.id,h.business_user_id,h.hotel_name objectName,0 type,h.mobile from tb_xa_hotel h");
		sql.append(" union");
		sql.append(" select t.id,t.business_user_id,t.tourist_name objectName,1 type,t.mobile from tb_xa_tourist t)th");
		sql.append(" where th.mobile=?");
		List<Object> params=new ArrayList<Object>();
		params.add(mobile);
		List<Object[]> objs=this.queryParams(sql.toString(), 0, 0, params);
		if(XaUtil.isNotEmpty(objs)){
			xr.setObject(true);
		}else{
			xr.setObject(false);
		}
		return xr;
	}
	/**
	 * 通过手机号码修改密码
	 * @param mobile
	 * @param password
	 * @return 返回Object对象 true 修改成功 flase 修改失败
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Boolean> updataByMobile(String mobile, String password)
			throws BusinessException {
		XaResult<Boolean> xr=new XaResult<Boolean>();
		StringBuffer sql=new StringBuffer("");
		sql.append("select * from(select  h.business_user_id,h.hotel_name objectName,0 type,h.mobile from tb_xa_hotel h");
		sql.append(" union");
		sql.append(" select t.business_user_id,t.tourist_name objectName,1 type,t.mobile from tb_xa_tourist t)th");
		sql.append(" where th.mobile=?");
		List<Object> params=new ArrayList<Object>();
		params.add(mobile);
		List<Object[]> objs=this.queryParams(sql.toString(), 0, 0, params); 
		BusinessInfo businessInfo=businessInfoRepository.findByIdAndStatusNot(((BigInteger)objs.get(0)[0]).longValue(), XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(businessInfo)){
			businessInfo.setPassword(password);
			businessInfoRepository.save(businessInfo);
			xr.setObject(true);
		}else{
			xr.error("该用户不存在");
		}
		return xr;
	}

	//修改原始密码
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Boolean> oldpwdUpdata(Long id, String password, String newPassword) {
		XaResult<Boolean> xr=new  XaResult<Boolean>();
		BusinessInfo obj=businessInfoRepository.findByIdAndStatusNot(id, XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(obj)){
			if(obj.getPassword().equals(password)){
				obj.setPassword(newPassword);
				businessInfoRepository.save(obj);
				xr.setObject(true);
			}else{
				xr.error("原始密码错误");
			}
		}else{
			xr.error("用户不存在");	
		}
		return xr;
	}

	@Override
	public XaResult<Page<BusinessInfoVo>> getBusinessInfos(
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<Page<BusinessInfoVo>> xr = new XaResult<Page<BusinessInfoVo>>();
		List<Object> params = new ArrayList<Object>();
		String sql = "CALL pro_businessInfo(?,?,?,?)";
		params.add(XaUtil.isEmpty(filterParams.get("LIKE_businessName")) ? null : filterParams.get("LIKE_businessName"));
		params.add(XaUtil.isEmpty(filterParams.get("LIKE_telphone")) ? null : filterParams.get("LIKE_telphone"));
		params.add(pageable.getPageNumber() * pageable.getPageSize());
		params.add(pageable.getPageSize());
		List<Object[]> objs = this.queryCall(sql.toString(), params);
		List<BusinessInfoVo> infos = new ArrayList<BusinessInfoVo>();
		Integer count = 0;
		for(Object[] obj : objs){
			BusinessInfoVo info = new BusinessInfoVo();
			info.setAccount((String)obj[0]);
			info.setId(XaUtil.isEmpty(obj[1]) ?  null : ((BigInteger)obj[1]).longValue());
			info.setBusinessName(XaUtil.isEmpty(obj[2]) ?  null : (String)obj[2]);
			info.setImgUrl(XaUtil.isEmpty(obj[3]) ?  null : (String)obj[3]);
			info.setType(XaUtil.isEmpty(obj[4]) ?  null : ((BigInteger)obj[4]).intValue());
			info.setTelphone(XaUtil.isEmpty(obj[5]) ?  null : (String)obj[5]);
			info.setAddress(XaUtil.isEmpty(obj[6]) ?  null : (String)obj[6]);
			count = (Integer)obj[7];
			info.setStatus(XaUtil.isEmpty(obj[9]) ?  null : ((Integer)obj[9]).intValue());
			info.setId(((BigInteger)obj[10]).longValue());
			infos.add(info);
		}
		Page<BusinessInfoVo> page = new MyPage<BusinessInfoVo>(pageable.getPageNumber(),pageable.getPageSize(),infos,count);
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<BusinessInfo> createAccount(Integer numbers)
			throws BusinessException {
		XaResult<BusinessInfo> xr = new XaResult<BusinessInfo>();
		StringBuffer sql = new StringBuffer();
		String dateStr = XaUtil.getToDayStr();
		sql.append("insert into tb_xa_businessinfo(create_time,account,password) values ");
		for(int i=0;i<numbers;i++){
			String account="";
			//循环判断生成的账号是否存在
			while(true){
				account=XaUtil.getRandomNumber(6);
				BusinessInfo businessInfo=businessInfoRepository.findByAccountAndStatusNot("sj"+account,XaConstant.Status.delete);
				
			if(XaUtil.isEmpty(businessInfo)){ 
				break;
				}
			}
			
			sql.append("('").append(dateStr).append("','").append("sj" +account).append("','").append(MD5Util.getMD5String("123456")).append("')");
			if(i != numbers - 1){
				sql.append(",");
			}
		}
		Integer r = this.insert(sql.toString());
		if(r > 0){
			xr.setCode(1);
			xr.setMessage("成功");
		}else{
			xr.setMessage("系统错误");
		}
		return xr;
	}
	//修改手机号
	@Override
	public XaResult<Boolean> updatMobile(String oldmobile, String password,
			String newmobile,Integer type,Long id) {
		XaResult<Boolean> xr=new XaResult<Boolean>();
		StringBuffer sql=new StringBuffer("");
		sql.append("select bus.id from tb_xa_businessinfo bus ");
		sql.append("INNER JOIN (select h.business_user_id,h.hotel_name objectName,0 type,h.mobile from tb_xa_hotel h");
		sql.append(" union");
		sql.append(" select t.business_user_id,t.tourist_name objectName,1 type,t.mobile from tb_xa_tourist t) th");
		sql.append(" on bus.id=th.business_user_id where th.mobile=? and bus.`password`=? and bus.id =?");
		List<Object> params=new ArrayList<Object>();
		params.add(oldmobile);
		params.add(password);
		params.add(id);
		List<Object[]> objs=this.queryParams(sql.toString(), 0, 0, params);
		if(XaUtil.isNotEmpty(objs)){
			String sql2="";
			if(type==0){
				sql2="update  tb_xa_hotel SET mobile="+"'"+newmobile+"'"+" WHERE business_user_id="+id+"";
			}else{
				sql2="update  tb_xa_tourist SET mobile="+"'"+newmobile+"'"+" WHERE business_user_id="+id+"";
			}
			this.insert(sql2);
			xr.setObject(true);
		}else{
			xr.error("原手机号与密码不匹配");
		}
		return xr;
	}
	/**
	 * 后台结算管理列表
	 * author：changlu
	 * time:2016-01-26 10:37:00
	 * 分页查询商家结算数据
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Order集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<BusinessInfoVo>> findBalanceList(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		XaResult<Page<BusinessInfoVo>> xr = new XaResult<Page<BusinessInfoVo>>();
		/* 查询列表*/
		StringBuffer sql=new StringBuffer("select h.id,h.hotel_name,propotion,account_period,b.id bId,b.create_time,");
		sql.append("(SELECT sum(o.order_price) from tb_xa_order o INNER JOIN tb_xa_room r ON o.object_id=r.id ");
		sql.append("WHERE r.status<>3 and o.status<>3 and o.order_type=0 and r.hotel_id=h.id and o.order_status in (1,2,3,4,5))*propotion/(propotions+propotion) totalMoney,propotions,account_periods ");
		sql.append("from tb_xa_hotel h ");
		sql.append("INNER JOIN tb_xa_businessinfo b ON b.id=h.business_user_id ");
		sql.append("WHERE h.status<>3 and b.status<>3 UNION ");
		sql.append("select t.id,t.tourist_name,propotion,account_period,b.id bId,b.create_time,");
		sql.append("(SELECT sum(o.order_price) from tb_xa_order o INNER JOIN tb_xa_tickets t1 ON o.object_id=t1.id ");
		sql.append("WHERE t1.status<>3 and o.status<>3 and o.order_type=1 and t1.tourist_id=t.id and o.order_status in (1,2,3,4,5))*propotion/(propotions+propotion) totalMoney,propotions,account_periods ");
		sql.append("from tb_xa_tourist t ");
		sql.append("INNER JOIN tb_xa_businessinfo b ON b.id=t.business_user_id ");
		sql.append("WHERE t.status<>3 and b.status<>3 ");
		sql.append(" ORDER BY create_time desc ");
		List<Object[]> objs=this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(), new ArrayList<Object>());
		List<BusinessInfoVo> vos = new ArrayList<BusinessInfoVo>();
		//遍历objs
		for(Object[] obj : objs){
			BusinessInfoVo vo=new BusinessInfoVo();
			HotelVo hotelVo=new HotelVo();
			hotelVo.setId(((BigInteger)obj[0]).longValue());
			hotelVo.setHotelName((String)obj[1]);
			if(XaUtil.isNotEmpty(obj[6])){
				double f2= Double.parseDouble(obj[6].toString()); 
				BigDecimal b1 = new BigDecimal(f2);  
				double f3 = b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				hotelVo.setTotalPrice(f3);
			}
			
			vo.setHotelVo(hotelVo);
			
			vo.setPropotion((Integer)obj[2]);
			if(XaUtil.isNotEmpty(obj[3])){
				vo.setAccountPeriod((Integer)obj[3]);
			}
			
			vo.setPropotions((Integer)obj[7]);
			
			vo.setAccountPeriods((Integer)obj[8]);
			vo.setId(((BigInteger)obj[4]).longValue());
			vos.add(vo);
		}
		/*统计数据条数*/
		StringBuffer sql1=new StringBuffer("select COUNT(*) from (");
		sql1.append("select h.id,h.hotel_name,propotion,account_period,b.id bId,b.create_time,");
		sql1.append("(SELECT sum(o.order_price) from tb_xa_order o INNER JOIN tb_xa_room r ON o.object_id=r.id ");
		sql1.append("WHERE r.status<>3 and o.status<>3 and o.order_type=0 and r.hotel_id=h.id and o.order_status>"+JConstant.OrderStatus.UN_PAY+")*propotion/(propotions+propotion) totalMoney,propotions,account_periods ");
		sql1.append("from tb_xa_hotel h ");
		sql1.append("INNER JOIN tb_xa_businessinfo b ON b.id=h.business_user_id ");
		sql1.append("WHERE h.status<>3 and b.status<>3 UNION ");
		sql1.append("select t.id,t.tourist_name,propotion,account_period,b.id bId,b.create_time,");
		sql1.append("(SELECT sum(o.order_price) from tb_xa_order o INNER JOIN tb_xa_tickets t1 ON o.object_id=t1.id ");
		sql1.append("WHERE t1.status<>3 and o.status<>3 and o.order_type=0 and t1.tourist_id=t.id and o.order_status>"+JConstant.OrderStatus.UN_PAY+")*propotion/(propotions+propotion) totalMoney,propotions,account_periods ");
		sql1.append("from tb_xa_tourist t ");
		sql1.append("INNER JOIN tb_xa_businessinfo b ON b.id=t.business_user_id ");
		sql1.append("WHERE t.status<>3 and b.status<>3 ");
		sql1.append(" ORDER BY create_time desc ");
		sql1.append(") temp");
		//统计数据条数
		List<Object[]> count=this.queryParams(sql1.toString(), null, null, new ArrayList<Object>());
		//调用分页
		Page<BusinessInfoVo> page= new MyPage<BusinessInfoVo>(pageable.getPageNumber(),pageable.getPageSize(),vos,Integer.parseInt(count.get(0)+""));
		//将page放入xr
		xr.setObject(page);
		return xr;
	}
	/**
	 * 保存BusinessInfo信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<BusinessInfo> createBalance(BusinessInfo model)
			throws BusinessException {
		BusinessInfo obj = null;
		
		if(XaUtil.isNotEmpty(model.getId())&&(model.getId()>0)){
			obj=businessInfoRepository.findOne(model.getId());
			//obj = businessInfoRepository.findByIdAndStatus(model.getId(),XaConstant.Status.valid);
		}else{
			obj = new BusinessInfo();
		}
		XaResult<BusinessInfo> xr = new XaResult<BusinessInfo>();
		obj.setPropotion(model.getPropotion());
		obj.setAccountPeriod(model.getAccountPeriod());
		obj.setPropotions(model.getPropotions());
		/*obj.setAccountPeriods(model.getAccountPeriods());*/
		obj.setId(model.getId());
		businessInfoRepository.save(obj);
		xr.setObject(obj);
		return xr;
	}
	/**
	 * 查找单条BusinessInfo信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<BusinessInfoVo> findBalanceById(Long modelId)
			throws BusinessException {
		BusinessInfoVo vo = null;
		XaResult<BusinessInfoVo> xr = new XaResult<BusinessInfoVo>();
		if(modelId>0){
			StringBuffer sql=new StringBuffer("select h.id,h.hotel_name,propotion,account_period,b.id bId,b.create_time,");
			sql.append("(SELECT sum(o.order_price) from tb_xa_order o INNER JOIN tb_xa_room r ON o.object_id=r.id ");
			sql.append("WHERE r.status<>3 and o.status<>3 and o.order_type=0 and r.hotel_id=h.id)*(1-propotion) totalMoney,propotions,account_periods ");
			sql.append("from tb_xa_hotel h ");
			sql.append("INNER JOIN tb_xa_businessinfo b ON b.id=h.business_user_id ");
			sql.append("WHERE h.status<>3 and b.status<>3 and b.id="+modelId+" UNION ");
			sql.append("select t.id,t.tourist_name,propotion,account_period,b.id bId,b.create_time,");
			sql.append("(SELECT sum(o.order_price) from tb_xa_order o INNER JOIN tb_xa_tickets t1 ON o.object_id=t1.id ");
			sql.append("WHERE t1.status<>3 and o.status<>3 and o.order_type=0 and t1.tourist_id=t.id)*(1-propotion) totalMoney,propotions,account_periods ");
			sql.append("from tb_xa_tourist t ");
			sql.append("INNER JOIN tb_xa_businessinfo b ON b.id=t.business_user_id ");
			sql.append("WHERE t.status<>3 and b.status<>3 and b.id="+modelId+" ");
			List<Object[]> objs=this.queryParams(sql.toString(), null, null, new ArrayList<Object>());
			vo=new BusinessInfoVo();
			HotelVo hotelVo=new HotelVo();
			hotelVo.setHotelName((String)objs.get(0)[1]);
			vo.setHotelVo(hotelVo);
		
			vo.setPropotion((Integer)objs.get(0)[2]);
			
			
			vo.setAccountPeriod((Integer)objs.get(0)[3]);
			vo.setPropotions((Integer)objs.get(0)[7]);
			
			
			vo.setAccountPeriods((Integer)objs.get(0)[8]);
			vo.setId(((BigInteger)objs.get(0)[4]).longValue());
			
		}else{
			xr.error("查询失败");
			return xr;
		}
		xr.setObject(vo);
		return xr;
	}
}
