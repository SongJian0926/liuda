package com.web.liuda.business.service.impl;

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

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.FondRecord;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.FondRecordRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.business.service.FondRecordService;
import com.web.liuda.remote.vo.FondRecordVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.excelUtil.ExcelColumn;
import com.web.webstart.base.excelUtil.ExcelHead;
import com.web.webstart.base.excelUtil.ExcelHelper;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.PushMessgeUtil;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("FondRecordService")
@Transactional(readOnly = false)
public class FondRecordServiceImpl extends BaseService<FondRecord> implements FondRecordService {

	@Autowired
	private FondRecordRepository fondRecordRepository;
	@Autowired
	private UserRepository userRepository;
	/**
	 * 查询单条FondRecord信息
	 * @param tId
	 * @return 返回单个FondRecord对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<FondRecord> findOne(Long modelId) throws BusinessException {
		FondRecord obj = new FondRecord();
		if(modelId != 0){
			obj = fondRecordRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<FondRecord> xr = new XaResult<FondRecord>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * 分页查询用户提现记录
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象FondRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<FondRecordVo>> findFondRecordList(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		StringBuffer sql=new StringBuffer("select fr.id,fr.price,fr.origin,u.user_name,fr.present_state,fr.bank_card_id,fr.card_holder,fr.idcard,fr.opening_bank,fr.opening_area,fr.sub_bank_nam ");
		sql.append(" from tb_xa_fondrecord fr inner join tb_xa_user u on u.id=fr.user_id where fr.status="+XaConstant.Status.valid+" ");
		
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append(" from tb_xa_fondrecord fr inner join tb_xa_user u on u.id=fr.user_id where fr.status="+XaConstant.Status.valid+" ");
		List<Object> params=new ArrayList<Object>();
		if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))){
			sql.append(" and u.user_name like ? ");
			countsql.append(" and u.user_name like ? ");
			params.add("%"+filterParams.get("EQ_userId").toString()+"%");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_present_state"))){
			sql.append(" and fr.present_state=? ");
			countsql.append(" and fr.present_state=? ");
			params.add(Integer.valueOf(filterParams.get("EQ_present_state")+""));
		}
		sql.append(" order by fr.create_time desc ");
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(),params);
		List<Object[]> count = this.queryParams(countsql.toString(), null, null,params);
		List<FondRecordVo> vos=new ArrayList<FondRecordVo>();
		for(Object[] obj:objs){
			FondRecordVo vo=new FondRecordVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setPrice(XaUtil.isEmpty(obj[1])?null:Double.valueOf(obj[1]+""));
			vo.setOrigin((String)obj[2]);
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[3]);
			vo.setUserVo(userVo);
			vo.setPresentState(XaUtil.isEmpty(obj[4])?null:Integer.valueOf(obj[4]+""));
			vo.setBankCardId((String)obj[5]);
			vo.setCardHolder((String)obj[6]);
			vo.setIdcard((String)obj[7]);
			vo.setOpeningBank((String)obj[8]);
			vo.setOpeningArea((String)obj[9]);
			vo.setSubBankNam((String)obj[10]);
			vos.add(vo);
		}
		XaResult<Page<FondRecordVo>>xr=new XaResult<Page<FondRecordVo>>();
		Page<FondRecordVo> page = new MyPage<FondRecordVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
		
	}
	/**
	 * 分页查询状态非status的FondRecord数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象FondRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<FondRecord>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<FondRecord> page = fondRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FondRecord.class), pageable);
		XaResult<Page<FondRecord>> xr = new XaResult<Page<FondRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的FondRecord数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象FondRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<FondRecord>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<FondRecord> page = fondRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FondRecord.class), pageable);
		XaResult<Page<FondRecord>> xr = new XaResult<Page<FondRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存FondRecord信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<FondRecord> saveOrUpdate(FondRecord model) throws BusinessException {
		FondRecord obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = fondRecordRepository.findOne(model.getId());
		}else{
			obj = new FondRecord();
		}
		obj.setPrice(model.getPrice());
		obj.setOrigin(model.getOrigin());
		obj.setUserId(model.getUserId());
		obj.setType(model.getType());
		obj.setBankCardId(model.getBankCardId());
		obj.setCardHolder(model.getCardHolder());
		obj.setIdcard(model.getIdcard());
		obj.setOpeningBank(model.getOpeningBank());
		obj.setOpeningArea(model.getOpeningArea());
		obj.setSubBankNam(model.getSubBankNam());
		obj = fondRecordRepository.save(obj);
		XaResult<FondRecord> xr = new XaResult<FondRecord>();
		xr.setObject(obj);
		return xr;
	}
	/**
	 * 修改FondRecord状态，打款中、提现失败、提现成功
	 * author:changlu
	 * time:2016-05-10 10:27:00
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回FondRecord对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<FondRecord> changePresentState(
			String modelIds,Integer presentState) throws BusinessException {
		XaResult<FondRecord> xr = new XaResult<FondRecord>();
		
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				FondRecord obj = fondRecordRepository.findByIdAndStatusNot(Long.parseLong(id),XaConstant.Status.delete);
				
				if (XaUtil.isNotEmpty(obj)) {
					obj.setPresentState(presentState);
					obj = fondRecordRepository.save(obj);
					//打款失败，把钱退还至用户账户，发送推送提醒用户提现失败
					if(presentState==JConstant.PresentState.TRANSFERFAIL){
						User user=new User();
						user=userRepository.findByIdAndStatusNot(obj.getUserId(),XaConstant.Status.delete);
						//账户为空，置为0.00
						if(XaUtil.isEmpty(user.getBalance())){
							user.setBalance(0.00);
						}
						user.setBalance(user.getBalance()+obj.getPrice());
						user=userRepository.save(user);
						/*给用户推消息，消息推送开始*/
						List<String> strs = new ArrayList<String>();
						strs.add(user.getMobile());
						PushMessgeUtil.pushMessage("您好，您的提现申请失败，申请的提现金额已退还至您的账户，请注意查收，谢谢！",strs);
						/*消息推送结束*/
					}
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * 修改FondRecord状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回FondRecord对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<FondRecord> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<FondRecord> xr = new XaResult<FondRecord>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				FondRecord obj = fondRecordRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = fondRecordRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//用户提现管理导出数据
	@Transactional(rollbackFor = Exception.class)
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException {	
		StringBuffer sql=new StringBuffer("select fr.id,fr.price,fr.origin,u.user_name,fr.present_state,fr.bank_card_id,fr.card_holder,fr.idcard,fr.opening_bank,fr.opening_area,fr.sub_bank_nam ");
		sql.append(" from tb_xa_fondrecord fr inner join tb_xa_user u on u.id=fr.user_id where fr.status="+XaConstant.Status.valid+" ");
		
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append(" from tb_xa_fondrecord fr inner join tb_xa_user u on u.id=fr.user_id where fr.status="+XaConstant.Status.valid+" ");
		List<Object> params=new ArrayList<Object>();
		if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))){
			sql.append(" and u.user_name like ? ");
			countsql.append(" and u.user_name like ? ");
			params.add("%"+filterParams.get("EQ_userId").toString()+"%");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_present_state"))){
			sql.append(" and fr.present_state=? ");
			countsql.append(" and fr.present_state=? ");
			params.add(Integer.valueOf(filterParams.get("EQ_present_state")+""));
		}
		sql.append(" order by fr.create_time desc ");
		List<Object[]> objs = this.queryParams(sql.toString(), null, null,params);
		List<FondRecordVo> vos=new ArrayList<FondRecordVo>();
		for(Object[] obj:objs){
			FondRecordVo vo=new FondRecordVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setPrice(XaUtil.isEmpty(obj[1])?null:Double.valueOf(obj[1]+""));
			vo.setOrigin((String)obj[2]);
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[3]);
			vo.setUserVo(userVo);
			vo.setPresentState(XaUtil.isEmpty(obj[4])?null:Integer.valueOf(obj[4]+""));
			vo.setBankCardId((String)obj[5]);
			vo.setCardHolder((String)obj[6]);
			vo.setIdcard((String)obj[7]);
			vo.setOpeningBank((String)obj[8]);
			vo.setOpeningArea((String)obj[9]);
			vo.setSubBankNam((String)obj[10]);
			vos.add(vo);
		}
	    //excel结构
	    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
	    excelColumns.add(new ExcelColumn(0, "price", "金额", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(1, "origin", "来源", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(2, "userVo.userName", "用户名", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(3, "bankCardId", "银行卡号", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(4, "cardHolder", "持卡人", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(5, "idcard", "身份证号", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(6, "openingBank", "开户银行", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(7, "openingArea", "所属区域", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(8, "subBankNam", "支行名称", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(9, "presentState", "提现状态", Cell.CELL_TYPE_STRING));
	    // 需要特殊转换的单元
	    Map<String,Map> excelColumnsConvertMap = new HashMap<String,Map>();
	    Map<Integer,String> isReceive = new HashMap<Integer,String>();
	    isReceive.put(1,"已提交");
        isReceive.put(2,"提现成功");
        isReceive.put(3,"提现失败");
        isReceive.put(4,"打款中");
	    excelColumnsConvertMap.put("presentState", isReceive);
	   
	    //设置头部
	    ExcelHead head = new ExcelHead();
	    head.setRowCount(1); // 模板中头部所占行数
	    head.setColumns(excelColumns);  // 列的定义
	    head.setColumnsConvertMap(excelColumnsConvertMap); // 列的转换
	    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
	    ExcelHelper.getInstanse().exportExcelFile(response,head, vos);
	}
	
}
