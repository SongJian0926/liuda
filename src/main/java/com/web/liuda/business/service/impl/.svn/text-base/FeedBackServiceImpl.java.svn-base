package com.web.liuda.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.FeedBack;
import com.web.liuda.business.repository.FeedBackRepository;
import com.web.liuda.business.service.FeedBackService;
import com.web.liuda.remote.vo.FeedBackVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.excelUtil.ExcelColumn;
import com.web.webstart.base.excelUtil.ExcelHead;
import com.web.webstart.base.excelUtil.ExcelHelper;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("FeedBackService")
@Transactional(readOnly = false)
public class FeedBackServiceImpl extends BaseService<FeedBack> implements FeedBackService {

	@Autowired
	private FeedBackRepository feedBackRepository;
	
	/**
	 * 查询单条FeedBack信息
	 * @param tId
	 * @param createTime
	 * @return 返回单个FeedBack对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<FeedBack> findOneUser(Long modelId,String createTime) throws BusinessException {
		FeedBack obj = new FeedBack();
		XaResult<FeedBack> xr = new XaResult<FeedBack>();
		if (XaUtil.isNotEmpty(modelId) && XaUtil.isNotEmpty(createTime)) {
			obj =feedBackRepository.findOneUser(modelId, createTime);
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的FeedBack数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象FeedBack集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<FeedBack>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<FeedBack> page = feedBackRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FeedBack.class), pageable);
		XaResult<Page<FeedBack>> xr = new XaResult<Page<FeedBack>>();
		xr.setObject(page);
		return xr;
	}
	/**
	 * 分页查询状态非status的FeedBack数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Channel集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<FeedBack>> findList(
			Integer status,Map<String, Object> filterParams,Pageable pageable) throws BusinessException {
		String userName="";
		String createTime="";
		Page<FeedBack> page = null;
		//判断查询条件是否为空
		XaResult<Page<FeedBack>>  xr=new XaResult<Page<FeedBack>>();
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_userName"))){
			userName = filterParams.get("LIKE_userName").toString();
			if(XaUtil.isNotEmpty(filterParams.get("GTE_createTime"))){
			  createTime = filterParams.get("GTE_createTime").toString();
			  createTime=createTime+" 00:00:00";
			  page=feedBackRepository.findUser1("%"+userName+"%",createTime,pageable);
			}
			else if(XaUtil.isNotEmpty(filterParams.get("LTE_createTime"))){
				createTime = filterParams.get("LTE_createTime").toString();
				createTime=createTime+" 23:59:59";
			    page=feedBackRepository.findUser2("%"+userName+"%",createTime,pageable);
			}else if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
				createTime = filterParams.get("BETWEEN_createTime").toString();
				String[] timeArray=createTime.split(",");
				page=feedBackRepository.findUser3("%"+userName+"%",timeArray[0]+" 00:00:00",timeArray[1]+" 23:59:59",pageable);
			}else{
				page=feedBackRepository.findUser1("%"+userName+"%",createTime,pageable);
			}
		}
		else if(XaUtil.isNotEmpty(filterParams.get("GTE_createTime"))){
			createTime = filterParams.get("GTE_createTime").toString();
			createTime=createTime+" 00:00:00";
			page=feedBackRepository.findUser1("%"+userName+"%",createTime,pageable);
		}
		else if(XaUtil.isNotEmpty(filterParams.get("LTE_createTime"))){
			createTime = filterParams.get("LTE_createTime").toString();
			createTime=createTime+" 23:59:59";
			page=feedBackRepository.findUser2("%"+userName+"%",createTime,pageable);
		}
		else if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			createTime = filterParams.get("BETWEEN_createTime").toString();
			String[] timeArray=createTime.split(",");
			page=feedBackRepository.findUser3("%"+userName+"%",timeArray[0]+" 00:00:00",timeArray[1]+" 23:59:59",pageable);
		}else{
			page=feedBackRepository.findUser(pageable);
		}
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的FeedBack数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象FeedBack集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<FeedBack>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<FeedBack> page = feedBackRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FeedBack.class), pageable);
		XaResult<Page<FeedBack>> xr = new XaResult<Page<FeedBack>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存FeedBack信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<FeedBack> saveOrUpdate(FeedBack model) throws BusinessException {
		FeedBack obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = feedBackRepository.findOne(model.getId());
		}else{
			obj = new FeedBack();
		}
		obj.setUserId(model.getUserId());
		obj.setContent(model.getContent());
		obj = feedBackRepository.save(obj);
		XaResult<FeedBack> xr = new XaResult<FeedBack>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改FeedBack状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回FeedBack对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<FeedBack> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<FeedBack> xr = new XaResult<FeedBack>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				FeedBack obj = feedBackRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = feedBackRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//导出数据
	@Transactional(rollbackFor = Exception.class)
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException {	
		//此处构造一个对象的集合
		StringBuffer sql = new StringBuffer();
		String username="";
		String starttime="";
		String endTime="";
		String createTime="";
		sql.append("select u.user_name,f.content,f.create_time from tb_xa_feedback  f INNER JOIN ");
		sql.append("tb_xa_user u on  f.user_id=u.id where u.`status`<>3 and f.`status`<>3 ");
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_userName"))){
			username = filterParams.get("LIKE_userName").toString();
			sql.append(" and u.user_name").append(" like '%").append(username).append("%'");
		}
	    if(XaUtil.isNotEmpty(filterParams.get("GTE_createTime"))){
			starttime = filterParams.get("GTE_createTime").toString();
			sql.append(" and f.create_time>'").append(starttime).append(" 00:00:00").append("'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("LTE_createTime"))){
			endTime = filterParams.get("LTE_createTime").toString();
			sql.append(" and f.create_time<'").append(endTime).append(" 23:59:59").append("'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			createTime = filterParams.get("BETWEEN_createTime").toString();
			String[] timeArray=createTime.split(",");
			sql.append(" and f.create_time between '").append(timeArray[0]).append(" 00:00:00").append("' and '").append(timeArray[1]).append(" 23:59:59").append("'");
		}		
		List<Object[]> objs = this.query(sql.toString(), null, null);
		//创建User集合对象vos
		List<FeedBackVo> vos = new ArrayList<FeedBackVo>();
		//遍历objs
		for(Object[] obj : objs){
			//创建FeedBackVo对象vo
			FeedBackVo vo = new FeedBackVo();
			//遍历obj集合，将其放入vo中
			vo.setUsername((String)obj[0]);
			vo.setContent((String)obj[1]);
			vo.setCreateTime((String)obj[2]);
			//将vo添加到vos集合中
			vos.add(vo);
		}
	    //excel结构
	    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
	    excelColumns.add(new ExcelColumn(0, "username", "用户名", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(1, "content", "反馈内容", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(2, "createTime", "反馈时间", Cell.CELL_TYPE_STRING));
	    //设置头部
	    ExcelHead head = new ExcelHead();
	    head.setRowCount(1); // 模板中头部所占行数
	    head.setColumns(excelColumns);  // 列的定义
	    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
	    ExcelHelper.getInstanse().exportExcelFile(response,head,vos);
	    /*XaResult<String> xr = new XaResult<String>();
	    xr.setCode(1);
		//xr.setObject(XaConstant.Code.success+":"+picturePath+"/"+photoFile.getOriginalFilename());
		return xr;*/
	}
	/**
	 * 查询单条Activity信息
	 * @param tId
	 * @return 返回单个Activity对象
	 * @throws BusinessException
	 */
	@Override
	public XaResult<FeedBack> findOne(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
