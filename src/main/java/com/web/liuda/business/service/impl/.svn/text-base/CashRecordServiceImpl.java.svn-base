package com.web.liuda.business.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.BusinessInfo;
import com.web.liuda.business.entity.CashRecord;
import com.web.liuda.business.entity.MyBank;
import com.web.liuda.business.repository.BusinessInfoRepository;
import com.web.liuda.business.repository.CashRecordRepository;
import com.web.liuda.business.repository.MyBankRepository;
import com.web.liuda.business.service.CashRecordService;
import com.web.liuda.remote.vo.CashRecordVo;
import com.web.liuda.remote.vo.OrderVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.excelUtil.ExcelColumn;
import com.web.webstart.base.excelUtil.ExcelHead;
import com.web.webstart.base.excelUtil.ExcelHelper;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("CashRecordService")
@Transactional(readOnly = false)
public class CashRecordServiceImpl extends BaseService<CashRecord> implements CashRecordService {

	@Autowired
	private CashRecordRepository cashRecordRepository;
	@Autowired
	private BusinessInfoRepository businessInfoRepository;
	@Autowired
	private MyBankRepository myBankRepository;
	/**
	 * 查询单条CashRecord信息
	 * @param tId
	 * @return 返回单个CashRecord对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<CashRecord> findOne(Long modelId) throws BusinessException {
		CashRecord obj = new CashRecord();
		if(modelId != 0){
			obj = cashRecordRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<CashRecord> xr = new XaResult<CashRecord>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//pc端提现管理的余额
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<CashRecordVo> findRemain(Long businessId, Integer businessType)
			throws BusinessException {
		XaResult<CashRecordVo> xr = new XaResult<CashRecordVo>();
		CashRecordVo vo = new CashRecordVo();
		if(XaUtil.isEmpty(myBankRepository.findByBusinessUserIdAndStatusNot(businessId,XaConstant.Status.delete))){
			vo.setIsCash("未绑定银行卡");
			xr.setObject(vo);
			return xr;
		}
		/*BusinessInfo businessInfo=businessInfoRepository.findByIdAndStatusNot(businessId, XaConstant.Status.delete);
		int numPropotion=1;*/
		/*if(XaUtil.isNotEmpty(businessInfo)){
			if(XaUtil.isNotEmpty(businessInfo.getAccountPeriod())){
				vo.setAccountPeriod(businessInfo.getAccountPeriod()+"号到"+businessInfo.getAccountPeriods()+"号");
				int today=Integer.parseInt(XaUtil.getToDayStr().split(" ")[0].split("-")[2]);
				if(businessInfo.getAccountPeriod()<=today && today<=businessInfo.getAccountPeriods()){
					vo.setIsCash("可提现");
				}else{
					vo.setIsCash("不可提现");
				}
			}else{
				vo.setAccountPeriod("[暂未设置]");
				vo.setIsCash("不可提现");
			}
			if(XaUtil.isNotEmpty(businessInfo.getPropotion())){
				vo.setIsProportion(businessInfo.getPropotion()+":"+businessInfo.getPropotions());
				numPropotion=businessInfo.getPropotion()+businessInfo.getPropotions();
			}else{
				vo.setIsProportion("[暂未设置]");
			}
		}*/
		String sql="call pro_remain(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(businessType);
		params.add(businessId);
		List<Object[]> objs = this.queryCall(sql, params);
		OrderVo orderVo = new OrderVo();
		if(XaUtil.isNotEmpty((objs.get(0))[3])){
			//总收入
			double f= Double.parseDouble((objs.get(0))[3].toString()); 
			BigDecimal b = new BigDecimal(f);  
			//保留两位小数
			double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			orderVo.setTotal((XaUtil.isEmpty((objs.get(0))[3])?0.00:f1));
			vo.setOrderVo(orderVo);
			//如果有支出
			if(XaUtil.isNotEmpty((objs.get(0))[4])){
				//支出
				double f2= Double.parseDouble((objs.get(0))[4].toString()); 
				BigDecimal b1 = new BigDecimal(f2);  
				//保留两位小数
				double f3 = b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				vo.setMoney((XaUtil.isEmpty((objs.get(0))[4])?0.00:f3));
				//余额
				double a=(XaUtil.isEmpty((objs.get(0))[3])?0.00:f1)-(XaUtil.isEmpty((objs.get(0))[4])?0.00:f3);
				BigDecimal b3 = new BigDecimal(a);  
				//保留两位小数
				double f4 = b3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				//余额
				vo.setRemain(f4);
			}else{						
				//余额
				vo.setRemain((XaUtil.isEmpty((objs.get(0))[3])?0.00:f1));
			}
		}
		//查询可提取的总额、支出
			
		String sqCashMoney="call pro_cashmoney(?,?)";
		List<Object[]> objs1 = this.queryCall(sqCashMoney, params);
		Double cash=0.0;
		//有提现
		if(XaUtil.isNotEmpty((objs1.get(0))[5])){
			
			double pd= Double.parseDouble((objs1.get(0))[5].toString()); 
			BigDecimal d = new BigDecimal(pd);  
			double pd1 = d.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		System.out.println(cash);
		//如果总收入不为空
		if(XaUtil.isNotEmpty((objs1.get(0))[3])){
			//总收入
			double fs= Double.parseDouble((objs1.get(0))[3].toString()); 
			BigDecimal b = new BigDecimal(fs);  
			//保留两位小数
			double fs1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			//如果有支出
			if(XaUtil.isNotEmpty((objs1.get(0))[5])){
				//支出
				double fs2= Double.parseDouble((objs1.get(0))[5].toString()); 
				BigDecimal bs1 = new BigDecimal(fs2);  
				//保留两位小数
				double fs3 = bs1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				vo.setMoney((XaUtil.isEmpty((objs1.get(0))[5])?0.00:fs3));
				//余额
				double as=(XaUtil.isEmpty((objs1.get(0))[3])?0.00:fs1)-(XaUtil.isEmpty((objs1.get(0))[5])?0.00:fs3);
				BigDecimal bs3 = new BigDecimal(as);  
				//保留两位小数
				double fs4 = bs3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				//余额
				//vo.setRemain(fs4);
				BusinessInfo business=businessInfoRepository.findByIdAndStatusNot(businessId,XaConstant.Status.delete);
				//该商家未设置比例
				if(XaUtil.isEmpty(business.getPropotion())&&XaUtil.isEmpty(business.getPropotions())){
					//可提取余额就是余额
					vo.setCashMoney(fs4);
				}else{
					int p1=business.getPropotion();
					int p2=business.getPropotions();
					//余额
					double t=(XaUtil.isEmpty((objs1.get(0))[3])?0.00:fs1*p1/(p1+p2))-(XaUtil.isEmpty((objs1.get(0))[5])?0.00:fs3);
					BigDecimal t1 = new BigDecimal(t);  
					//保留两位小数
					fs4 = t1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					
					cash=fs4;
					//可提现余额
					vo.setCashMoney(Double.valueOf(XaUtil.formatCurrency(cash)));
				}
				
			}else{//没有支出
				BusinessInfo business=businessInfoRepository.findByIdAndStatusNot(businessId,XaConstant.Status.delete);
				if(XaUtil.isEmpty(business.getPropotion())&&XaUtil.isEmpty(business.getPropotions())){
					//可提现余额
					vo.setCashMoney((XaUtil.isEmpty((objs1.get(0))[3])?0.00:fs1));
				}else{
					int p1=business.getPropotion();
					int p2=business.getPropotions();
					cash=(XaUtil.isEmpty((objs1.get(0))[3])?0.00:fs1)*p1/(p1+p2);
					//可提现余额
					vo.setCashMoney(Double.valueOf(XaUtil.formatCurrency(cash)));
				}
				
			}
		}
		if(XaUtil.isEmpty(vo.getCashMoney())){
			vo.setCashMoney(0.00);
			vo.setIsCash("不可提现");
		}
		if(XaUtil.isEmpty(vo.getRemain())){
			vo.setRemain(0.00);
		}
		xr.setObject(vo);
		return xr;
	}
	/**
	 * pc端查询财务
	 * author:changlu
	 * @param businessId
	 * @param businessType
	 * @return 返回对象CashRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<CashRecordVo> findFinancelist(Long businessId, Integer businessType)
			throws BusinessException {
		XaResult<CashRecordVo> xr = new XaResult<CashRecordVo>();
		//查询总收入和支出
		String sql="call pro_newfinance(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(businessType);
		params.add(businessId);
		List<Object[]> objs = this.queryCall(sql, params);
		CashRecordVo vo = new CashRecordVo();
		OrderVo orderVo = new OrderVo();
		if(XaUtil.isNotEmpty((objs.get(0))[3])){
			//总收入
			double f= Double.parseDouble((objs.get(0))[3].toString()); 
			BigDecimal b = new BigDecimal(f);  
			//保留两位小数
			double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			orderVo.setTotal((XaUtil.isEmpty((objs.get(0))[3])?0.00:f1));
			vo.setOrderVo(orderVo);
			//如果有支出
			if(XaUtil.isNotEmpty((objs.get(0))[4])){
				//支出
				double f2= Double.parseDouble((objs.get(0))[4].toString()); 
				BigDecimal b1 = new BigDecimal(f2);  
				//保留两位小数
				double f3 = b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				vo.setMoney((XaUtil.isEmpty((objs.get(0))[4])?0.00:f3));
				//余额
				double a=(XaUtil.isEmpty((objs.get(0))[3])?0.00:f1)-(XaUtil.isEmpty((objs.get(0))[4])?0.00:f3);
				BigDecimal b3 = new BigDecimal(a);  
				//保留两位小数
				double f4 = b3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				//余额
				vo.setRemain(f4);
				
			}else{
				//余额
				vo.setRemain((XaUtil.isEmpty((objs.get(0))[3])?0.00:f1));
			}
		}

		//查询可提取的总额、支出
		String sqCashMoney="call pro_cashmoney(?,?)";
		List<Object[]> objs1 = this.queryCall(sqCashMoney, params);
		Double cash=0.0;
		//如果总收入不为空
		if(XaUtil.isNotEmpty((objs1.get(0))[3])){
			//总收入
			double fs= Double.parseDouble((objs1.get(0))[3].toString()); 
			BigDecimal b = new BigDecimal(fs);  
			//保留两位小数
			double fs1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			//如果有支出
			if(XaUtil.isNotEmpty((objs1.get(0))[4])){
				//支出
				double fs2= Double.parseDouble((objs.get(0))[4].toString()); 
				BigDecimal bs1 = new BigDecimal(fs2);  
				//保留两位小数
				double fs3 = bs1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				vo.setMoney((XaUtil.isEmpty((objs.get(0))[4])?0.00:fs3));
				//余额
				double as=(XaUtil.isEmpty((objs.get(0))[3])?0.00:fs1)-(XaUtil.isEmpty((objs.get(0))[4])?0.00:fs3);
				BigDecimal bs3 = new BigDecimal(as);  
				//保留两位小数
				double fs4 = bs3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				//余额
				//vo.setRemain(fs4);
				BusinessInfo business=businessInfoRepository.findByIdAndStatusNot(businessId,XaConstant.Status.delete);
				//该商家未设置比例
				if(XaUtil.isEmpty(business.getPropotion())&&XaUtil.isEmpty(business.getPropotions())){
					//可提取余额就是余额
					vo.setCashMoney(fs4);
				}else{
					int p1=business.getPropotion();
					int p2=business.getPropotions();
					//余额
					double t=(XaUtil.isEmpty((objs1.get(0))[3])?0.00:fs1*p1/(p1+p2))-(XaUtil.isEmpty((objs1.get(0))[4])?0.00:fs3);
					BigDecimal t1 = new BigDecimal(t);  
					//保留两位小数
					fs4 = t1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					
					cash=fs4;
					//可提现余额
					vo.setCashMoney(Double.valueOf(XaUtil.formatCurrency(cash)));
				}
				
			}else{//没有支出
				BusinessInfo business=businessInfoRepository.findByIdAndStatusNot(businessId,XaConstant.Status.delete);
				if(XaUtil.isEmpty(business.getPropotion())&&XaUtil.isEmpty(business.getPropotions())){
					//可提现余额
					vo.setCashMoney((XaUtil.isEmpty((objs1.get(0))[3])?0.00:fs1));
				}else{
					int p1=business.getPropotion();
					int p2=business.getPropotions();
					cash=(XaUtil.isEmpty((objs1.get(0))[3])?0.00:fs1)*p1/(p1+p2);
					//可提现余额
					vo.setCashMoney(Double.valueOf(XaUtil.formatCurrency(cash)));
				}
				
			}
			
		
		}
		//可提取余额为空
		if(XaUtil.isEmpty((objs1.get(0))[3])&&XaUtil.isNotEmpty((objs.get(0))[3])){
			//可提现余额
			vo.setCashMoney(0.00);
		}
		xr.setObject(vo);
		return xr;
	}
	/**
	 * pc端分页查询财务管理列表
	 * author:changlu
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象CashRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<CashRecordVo>> findList(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		XaResult<Page<CashRecordVo>> xr = new XaResult<Page<CashRecordVo>>();
		//查询列表数据
		String sql="call pro_finance_list(?,?,?,?,?,?,?,?)";
		//统计数据条数
		String sql1="call pro_count_finance_list(?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(filterParams.get("GT_createTime"));
		params.add(filterParams.get("LT_createTime"));
		params.add(filterParams.get("EQ_status"));
		params.add(filterParams.get("EQ_business_type"));
		params.add(filterParams.get("EQ_business_id"));
		params.add(filterParams.get("EQ_id"));
		//执行统计数据
		List<Object[]> obj1s=this.queryCall(sql1, params);
		params.add(pageable.getPageNumber()*pageable.getPageSize());
		params.add(pageable.getPageSize());
		//执行查询数据列表
		List<Object[]> objs=this.queryCall(sql, params);
		List<CashRecordVo> vos = new ArrayList<CashRecordVo>();
		for(Object[] obj : objs){
			CashRecordVo vo = new CashRecordVo();
			vo.setCreateTime((String)obj[0]);
			vo.setMoney(XaUtil.isEmpty(obj[1])?0.00:(Double)obj[1]);
			if(XaUtil.isEmpty(filterParams.get("EQ_status"))||Integer.valueOf(filterParams.get("EQ_status").toString())!=1){
				vo.setObjectName((String)obj[2]);
				vo.setOrderNo((String)obj[3]);
			}
			
			vos.add(vo);
		}
		
		//将得到的查询结果强转成int类型
		int count=Integer.parseInt(obj1s.get(0)+"");				
		//调用分页
		Page<CashRecordVo> page= new MyPage<CashRecordVo>(pageable.getPageNumber(),pageable.getPageSize(),vos,count);
		//将page放入xr
		xr.setObject(page);
		return xr;	
	}

	/**
	 * 分页查询状态status的CashRecord数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象CashRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<CashRecord>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<CashRecord> page = cashRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), CashRecord.class), pageable);
		XaResult<Page<CashRecord>> xr = new XaResult<Page<CashRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存CashRecord信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<CashRecord> saveOrUpdate(CashRecord model) throws BusinessException {
		CashRecord obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = cashRecordRepository.findOne(model.getId());
		}else{
			obj = new CashRecord();
		}
		obj.setObjectId(model.getObjectId());
		obj.setType(model.getType());
		obj.setMoney(model.getMoney());
		obj.setCashStatus(model.getCashStatus());
		obj = cashRecordRepository.save(obj);
		XaResult<CashRecord> xr = new XaResult<CashRecord>();
		xr.setObject(obj);
		return xr;
	}
	//确认打款，打款，打款失败
	@Transactional(rollbackFor = Exception.class)
	public XaResult<CashRecord> sendMoney(
			String modelIds,Integer cashStatus) throws BusinessException {
		XaResult<CashRecord> xr = new XaResult<CashRecord>();
		
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				CashRecord obj = cashRecordRepository.findByIdAndStatusNot(Long.parseLong(id),XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setCashStatus(cashStatus);
					obj = cashRecordRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
	/**
	 * 修改CashRecord状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回CashRecord对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<CashRecord> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<CashRecord> xr = new XaResult<CashRecord>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				CashRecord obj = cashRecordRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = cashRecordRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	/**
	 * 后台提现管理列表
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象CashRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<CashRecord>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		StringBuffer sql=new StringBuffer("select c.id,(select h.hotel_name from tb_xa_hotel h where h.status=1 and h.business_user_id=c.object_id) hotelName,");
		sql.append("(select t.tourist_name from tb_xa_tourist t where t.status=1 and t.business_user_id=c.object_id ) touristName,");
		sql.append("c.type,c.cash_status,c.create_time,c.money,c.account,c.bank_name,c.object_id from tb_xa_cashrecord c where c.status=1");
		StringBuffer countsql=new StringBuffer("select count(*) from tb_xa_cashrecord c where c.status=1");
		if(XaUtil.isNotEmpty(filterParams.get("EQ_type"))){
			sql.append(" and c.type").append("=").append(filterParams.get("EQ_type"));
			countsql.append(" and c.type").append("=").append(filterParams.get("EQ_type"));
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_cashStatus"))){
			sql.append(" and c.cash_status").append(" =").append(filterParams.get("EQ_cashStatus"));
			countsql.append(" and c.cash_status").append(" =").append(filterParams.get("EQ_cashStatus"));
		}
		sql.append(" order by c.create_time desc ");
		List<Object[]> objs=this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(), null);
		List<Object[]> objs1=this.queryParams(countsql.toString(), null, null, null);
		List<CashRecord> vos=new ArrayList<CashRecord>();
		for(Object[] obj:objs){
			CashRecord vo=new CashRecord();
			vo.setId(((BigInteger)obj[0]).longValue());
			//酒店
			if(XaUtil.isNotEmpty(obj[1])){
				vo.setObjectName((String)obj[1]);
			}
			//景点
			if(XaUtil.isNotEmpty(obj[2])){
				vo.setObjectName((String)obj[2]);
			}
			vo.setType((Integer)obj[3]);
			vo.setCashStatus((Integer)obj[4]);
			vo.setCreateTime((String)obj[5]);
			vo.setMoney((Double)obj[6]);
			vo.setAccount((String)obj[7]);
			vo.setBankName((String)obj[8]);
			//MyBank mybank=myBankRepository.findByAccountAndStatusNot(vo.getAccount(), XaConstant.Status.delete);
			MyBank mybank=myBankRepository.findByAccountAndStatusNotAndBusinessUserId(vo.getAccount(), XaConstant.Status.delete,XaUtil.isEmpty(obj[9])? null:((BigInteger)obj[9]).longValue());
			
			if(XaUtil.isNotEmpty(mybank)){
				vo.setMybank(mybank);
			}
			
			vos.add(vo);
		}
		//将得到的查询结果强转成int类型
		int count=Integer.parseInt(objs1.get(0)+"");				
		//调用分页
		Page<CashRecord> page= new MyPage<CashRecord>(pageable.getPageNumber(),pageable.getPageSize(),vos,count);
		XaResult<Page<CashRecord>> xr = new XaResult<Page<CashRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 提现功能
	 * @param Long id
	 * @param cashNum
	 * @param password
	 * @param mobile
	 * @param type
	 */
	@Override
	public XaResult<Boolean> saveNewCashRecord(Long id, Double cashNum,
			String password, String mobile, Integer type)
			throws BusinessException {
		XaResult<Boolean> xr=new XaResult<Boolean>();
		StringBuffer sql=new StringBuffer("");
		sql.append("select bus.id from tb_xa_businessinfo bus ");
		sql.append("INNER JOIN (select h.business_user_id,h.hotel_name objectName,0 type,h.mobile from tb_xa_hotel h");
		sql.append(" union");
		sql.append(" select t.business_user_id,t.tourist_name objectName,1 type,t.mobile from tb_xa_tourist t) th");
		sql.append(" on bus.id=th.business_user_id where th.mobile=? and bus.`password`=? and th.business_user_id=? ");
		List<Object> params=new ArrayList<Object>();
		params.add(mobile);
		params.add(password);
		params.add(id);
		List<Object[]> objs=this.queryParams(sql.toString(), 0, 0, params);
		if(XaUtil.isNotEmpty(objs)){
			//查询商家 的银行卡信息
			MyBank myBank=myBankRepository.findByBusinessUserIdAndStatusNot(id,XaConstant.Status.delete);
			String account=myBank.getAccount();
			String bankname=myBank.getBankName();
			String sql2="insert into tb_xa_cashrecord(create_time,cash_status,money,object_id,type,account,bank_name) values("+"'"+XaUtil.getToDayStr()+"'"+",'0',"+cashNum+","+id+","+type+","+"'"+account+"'"+","+"'"+bankname+"'"+")";
			this.insert(sql2);
			
			xr.setObject(true);
		}else{
			xr.error("手机和密码不匹配");
		}
		return xr;
	}
	//提现管理导出数据
	@Transactional(rollbackFor = Exception.class)
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException {	
		StringBuffer sql=new StringBuffer("select c.id,(select h.hotel_name from tb_xa_hotel h where h.status=1 and h.business_user_id=c.object_id) hotelName,");
		sql.append("(select t.tourist_name from tb_xa_tourist t where t.status=1 and t.business_user_id=c.object_id ) touristName,");
		sql.append("c.type,c.cash_status,c.create_time,c.money,c.account,c.bank_name from tb_xa_cashrecord c where c.status=1");
		StringBuffer countsql=new StringBuffer("select count(*) from tb_xa_cashrecord c where c.status=1");
		if(XaUtil.isNotEmpty(filterParams.get("EQ_type"))){
			sql.append(" and c.type").append("=").append(filterParams.get("EQ_type"));
			countsql.append(" and c.type").append("=").append(filterParams.get("EQ_type"));
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_cashStatus"))){
			sql.append(" and c.cash_status").append(" =").append(filterParams.get("EQ_cashStatus"));
			countsql.append(" and c.cash_status").append(" =").append(filterParams.get("EQ_cashStatus"));
		}
		sql.append(" order by c.create_time desc ");
		List<Object[]> objs=this.queryParams(sql.toString(), null,null, null);
		List<CashRecord> vos=new ArrayList<CashRecord>();
		for(Object[] obj:objs){
			CashRecord vo=new CashRecord();
			vo.setId(((BigInteger)obj[0]).longValue());
			//酒店
			if(XaUtil.isNotEmpty(obj[1])){
				vo.setObjectName((String)obj[1]);
			}
			//景点
			if(XaUtil.isNotEmpty(obj[2])){
				vo.setObjectName((String)obj[2]);
			}
			vo.setType((Integer)obj[3]);
			vo.setCashStatus((Integer)obj[4]);
			vo.setCreateTime((String)obj[5]);
			vo.setMoney((Double)obj[6]);
			vo.setAccount((String)obj[7]);
			vo.setBankName((String)obj[8]);
			vos.add(vo);
		}
	    //excel结构
	    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
	    excelColumns.add(new ExcelColumn(0, "objectName", "商家名称", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(1, "type", "商家类型", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(2, "money", "提现金额", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(3, "bankName", "开户银行", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(4, "account", "银行卡号", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(5, "createTime", "创建时间", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(6, "cashStatus", "提现状态", Cell.CELL_TYPE_STRING));
	    
	    // 需要特殊转换的单元
	    Map<String,Map> excelColumnsConvertMap = new HashMap<String,Map>();
	    Map<Integer,String> isReceive = new HashMap<Integer,String>();
	    isReceive.put(0,"已提交");
        isReceive.put(1,"提现成功");
        isReceive.put(2,"打款中");
        isReceive.put(3,"打款失败");
	    excelColumnsConvertMap.put("cashStatus", isReceive);
	    Map<Integer,String> isReceive1 = new HashMap<Integer,String>();
	    isReceive1.put(0,"酒店");
        isReceive1.put(1,"景点");
	    excelColumnsConvertMap.put("type", isReceive1);
	    //设置头部
	    ExcelHead head = new ExcelHead();
	    head.setRowCount(1); // 模板中头部所占行数
	    head.setColumns(excelColumns);  // 列的定义
	    head.setColumnsConvertMap(excelColumnsConvertMap); // 列的转换
	    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
	    ExcelHelper.getInstanse().exportExcelFile(response,head, vos);
	}
}
