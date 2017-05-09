package com.web.liuda.business.service.impl;

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

import com.alibaba.fastjson.JSON;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.excelUtil.ExcelColumn;
import com.web.webstart.base.excelUtil.ExcelHead;
import com.web.webstart.base.excelUtil.ExcelHelper;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.business.entity.MatchOrderDetail;
import com.web.liuda.business.entity.RefundOrder;
import com.web.liuda.business.repository.MatchOrderDetailRepository;
import com.web.liuda.business.repository.MatchOrderRepository;
import com.web.liuda.business.repository.RefundOrderRepository;
import com.web.liuda.business.service.MatchOrderDetailService;
import com.web.liuda.remote.vo.MatchOrderDetailVo;

@Service("MatchOrderDetailService")
@Transactional(readOnly = false)
public class MatchOrderDetailServiceImpl extends BaseService<MatchOrderDetail> implements MatchOrderDetailService {

	@Autowired
	private MatchOrderDetailRepository matchOrderDetailRepository;
	@Autowired
	private MatchOrderRepository matchOrderRepository;
	@Autowired
	private RefundOrderRepository refundOrderRepository;
	
	/**
	 * 查询单条MatchOrderDetail信息
	 * @param tId
	 * @return 返回单个MatchOrderDetail对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<MatchOrderDetail> findOne(Long modelId) throws BusinessException {
		MatchOrderDetail obj = new MatchOrderDetail();
		if(modelId != 0){
			obj = matchOrderDetailRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<MatchOrderDetail> xr = new XaResult<MatchOrderDetail>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的MatchOrderDetail数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchOrderDetail集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchOrderDetail>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderDetail.class), pageable);
		XaResult<Page<MatchOrderDetail>> xr = new XaResult<Page<MatchOrderDetail>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的MatchOrderDetail数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchOrderDetail集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchOrderDetail>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderDetail.class), pageable);
		XaResult<Page<MatchOrderDetail>> xr = new XaResult<Page<MatchOrderDetail>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存MatchOrderDetail信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchOrderDetail> saveOrUpdate(MatchOrderDetail model) throws BusinessException {
		MatchOrderDetail obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = matchOrderDetailRepository.findOne(model.getId());
		}else{
			obj = new MatchOrderDetail();
		}
		obj.setRealName(model.getRealName());
		obj.setMobile(model.getMobile());
		obj.setSex(model.getSex());
		obj.setBloodType(model.getBloodType());
		obj.setIdCard(model.getIdCard());
		obj.setProfileNum(model.getProfileNum());
		obj.setMatchGroup(model.getMatchGroup());
		obj.setCarModel(model.getCarModel());
		obj.setCarTeam(model.getCarTeam());
		obj.setEmeMobile(model.getEmeMobile());
		obj.setExperience(model.getExperience());
		obj.setHonor(model.getHonor());
		obj.setSubAmt(model.getSubAmt());
		obj.setMatchId(model.getMatchId());
		obj.setMatchOrderId(model.getMatchOrderId());
		obj.setCarNumber(model.getCarNumber());
		obj.setAge(model.getAge());
		obj.setIsValid(model.getIsValid());
		obj.setType(model.getType());
		obj.setIsRefund(model.getIsRefund());
		obj = matchOrderDetailRepository.save(obj);
		XaResult<MatchOrderDetail> xr = new XaResult<MatchOrderDetail>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改MatchOrderDetail状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回MatchOrderDetail对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchOrderDetail> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<MatchOrderDetail> xr = new XaResult<MatchOrderDetail>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MatchOrderDetail obj = matchOrderDetailRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = matchOrderDetailRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public void fileDownloadMatchOrder(Map<String, Object> filterParams, HttpServletResponse response) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		
		List<MatchOrderDetail> lst ;//= matchOrderDetailRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), MatchOrderDetail.class));
		
		Long matchId = Long.valueOf(filterParams.get("EQ_matchId").toString());
		Integer type = Integer.parseInt(filterParams.get("EQ_type").toString());
		Integer isRefund = Integer.parseInt(filterParams.get("EQ_isRefund").toString());
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findMatchOrderDetailByMatchIdAndTypePage(matchId,type,isRefund,null);
		lst = page.getContent();
		if(lst.size()>0)
		{
			//excel结构
		    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
		    excelColumns.add(new ExcelColumn(0, "realName", "联系人", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(1, "mobile", "手机号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(2, "sex", "性别", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(3, "bloodType", "血型", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(4, "idCard", "身份证号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(5, "profileNum", "驾驶证档案号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(6, "matchGroup", "参赛组别", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(7, "carModel", "参赛车型", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(8, "carTeam", "所属车队", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(9, "emeMobile", "紧急联系人手机号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(10, "experience", "参赛经历", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(11, "honor", "荣誉", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(12, "isValid", "是否核销", Cell.CELL_TYPE_STRING));
		    
		    // 需要特殊转换的单元
		    Map<String,Map> excelColumnsConvertMap = new HashMap<String,Map>();
		    Map<Integer,String> isReceiveSex = new HashMap<Integer,String>();
		    isReceiveSex.put(0,"男");
		    isReceiveSex.put(1,"女");
		    excelColumnsConvertMap.put("sex", isReceiveSex);
		    Map<String,String> isReceiveValid = new HashMap<String,String>();
		    isReceiveValid.put("0","否");
		    isReceiveValid.put("1","是");
		    excelColumnsConvertMap.put("isValid", isReceiveValid);
		    
		    //设置头部
		    ExcelHead head = new ExcelHead();
		    head.setRowCount(1); // 模板中头部所占行数
		    head.setColumns(excelColumns);  // 列的定义
		    head.setColumnsConvertMap(excelColumnsConvertMap); // 列的转换
		    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
		    ExcelHelper.getInstanse().exportExcelFile(response,head, lst);
		}
	}

	@Override
	public void fileDownloadEventOrder(Map<String, Object> filterParams, HttpServletResponse response) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		
		List<MatchOrderDetail> lst ;//= matchOrderDetailRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), MatchOrderDetail.class));
		
		Long matchId = Long.valueOf(filterParams.get("EQ_matchId").toString());
		Integer type = Integer.parseInt(filterParams.get("EQ_type").toString());
		Integer isRefund = Integer.parseInt(filterParams.get("EQ_isRefund").toString());
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findMatchOrderDetailByMatchIdAndTypePage(matchId,type,isRefund,null);
		lst = page.getContent();
		if(lst.size()>0)
		{
			//excel结构
		    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
		    excelColumns.add(new ExcelColumn(0, "realName", "姓名", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(1, "mobile", "手机号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(2, "sex", "性别", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(3, "age", "年龄", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(4, "idCard", "身份证号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(5, "carModel", "车型", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(6, "carNumber", "车牌号码", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(7, "isValid", "是否核销", Cell.CELL_TYPE_STRING));
		    
		    // 需要特殊转换的单元
		    Map<String,Map> excelColumnsConvertMap = new HashMap<String,Map>();
		    Map<Integer,String> isReceiveSex = new HashMap<Integer,String>();
		    isReceiveSex.put(0,"男");
		    isReceiveSex.put(1,"女");
		    excelColumnsConvertMap.put("sex", isReceiveSex);
		    Map<String,String> isReceiveValid = new HashMap<String,String>();
		    isReceiveValid.put("0","否");
		    isReceiveValid.put("1","是");
		    excelColumnsConvertMap.put("isValid", isReceiveValid);
		    
		    //设置头部
		    ExcelHead head = new ExcelHead();
		    head.setRowCount(1); // 模板中头部所占行数
		    head.setColumns(excelColumns);  // 列的定义
		    head.setColumnsConvertMap(excelColumnsConvertMap); // 列的转换
		    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
		    ExcelHelper.getInstanse().exportExcelFile(response,head, lst);
		}
	}

	@Override
	public XaResult<MatchOrderDetail> updateRredeemCode(Long matchId, String redeemCode) throws BusinessException {
		XaResult<MatchOrderDetail> xr = new XaResult<MatchOrderDetail>();
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		filters.put("redeemCode", new SearchFilter("redeemCode", Operator.EQ, redeemCode));
		MatchOrderDetail detail = matchOrderDetailRepository.findOne(DynamicSpecifications.bySearchFilter(filters.values(), MatchOrderDetail.class));
		if(detail==null)
		{
			xr.error("该兑换码不存在");
			return xr;
		}
		if(detail.getIsValid().equals(String.valueOf(JConstant.BooleanStatus.TRUE)))
		{
			xr.error("该兑换码已兑换");
			return xr;
		}
		if(!detail.getMatchId().equals(matchId))
		{
			xr.error("该兑换码不属于该赛事");
			return xr;
		}
		if(detail.getIsRefund().equals(JConstant.BooleanStatus.TRUE))
		{
			xr.error("该订单已退款");
			return xr;
		}
		//兑换码是否已付款
		MatchOrder order = matchOrderRepository.findByIdAndStatusNot(detail.getMatchOrderId(), XaConstant.Status.delete);
		if(!order.getOrderStatus().equals(JConstant.MatchOrderStatus.ENROLLSUCCESS))
		{
			xr.error("该兑换码无效");
			return xr;
		}
		//兑换码是否在申请退款中
		RefundOrder refundOrder = refundOrderRepository.findByOrderDetailIdAndStatus(detail.getId(), XaConstant.Status.valid);
		if(refundOrder!=null && refundOrder.getRefundStatus().equals(JConstant.RefundStatus.APPLYREFUND))
		{
			xr.error("该订单状态为退款申请中，无法兑换");
			return xr;
		}
		
		detail.setIsValid(String.valueOf(JConstant.BooleanStatus.TRUE));
		matchOrderDetailRepository.save(detail);
		xr.setObject(detail);
		return xr;
	}

	@Override
	public XaResult<Page<MatchOrderDetail>> findMatchOrderDetailByMatchIdAndTypePage(Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Long matchId = Long.valueOf(filterParams.get("EQ_matchId").toString());
		Integer type = Integer.parseInt(filterParams.get("EQ_type").toString());
		Integer isRefund = Integer.parseInt(filterParams.get("EQ_isRefund").toString());
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findMatchOrderDetailByMatchIdAndTypePage(matchId,type,isRefund,pageable);
		XaResult<Page<MatchOrderDetail>> xr = new XaResult<Page<MatchOrderDetail>>();
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<Page<MatchOrderDetailVo>> findMatchOrderDetailVoNEStatusPage(Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderDetail.class), pageable);
		XaResult<Page<MatchOrderDetailVo>> xr = new XaResult<Page<MatchOrderDetailVo>>();
		List<MatchOrderDetailVo> vos = JSON.parseArray(JSON.toJSONString(page.getContent()), MatchOrderDetailVo.class);
		for(MatchOrderDetailVo vo : vos)//查退款表
		{

			RefundOrder refundOrder = refundOrderRepository.findByOrderDetailIdAndStatus(vo.getId(), XaConstant.Status.valid);
			if(refundOrder!=null)
			{
				vo.setEnrollStatus(refundOrder.getRefundStatus());
			}
			else
			{
				vo.setEnrollStatus(JConstant.RefundStatus.VALID);
			}
		}
		Page<MatchOrderDetailVo> pvo= new MyPage<MatchOrderDetailVo>(pageable.getPageNumber(),pageable.getPageSize(), vos, Integer.parseInt(String.valueOf(page.getTotalElements())));
		

		xr.setObject(pvo);
		return xr;
	}
	
}
