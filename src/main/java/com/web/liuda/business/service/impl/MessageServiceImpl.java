package com.web.liuda.business.service.impl;

import java.math.BigInteger;
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

import com.web.liuda.business.entity.Message;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.MessageRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.business.service.MessageService;
import com.web.liuda.remote.vo.MessageVo;
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

@Service("MessageService")
@Transactional(readOnly = false)
public class MessageServiceImpl extends BaseService<Message> implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;
	
	//导出数据
		@Transactional(rollbackFor = Exception.class)
		public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException {	
		
			List<Object> params = new ArrayList<Object>();
			StringBuffer sql = new StringBuffer();
			sql.append("select m.id,m.content,m.create_time from tb_xa_message m LEFT JOIN tb_xa_user u on m.user_id=u.id ");
			sql.append("where m.status<>").append(XaConstant.Status.delete);
			//内容查询条件
			if(XaUtil.isNotEmpty(filterParams.get("LIKE_content"))){
				sql.append(" and m.content like ?");
				params.add("%" + filterParams.get("LIKE_content") + "%");
			}
			//消息类型查询条件
			/*if(XaUtil.isNotEmpty(filterParams.get("EQ_type"))){
				sql.append(" and m.type = ?");
				params.add(filterParams.get("EQ_type"));
			}*/
			//用户名查询条件
			/*if(XaUtil.isNotEmpty(filterParams.get("LIKE_userName"))){
				sql.append(" and u.user_name like ?");
				params.add("%" + filterParams.get("LIKE_userName") + "%");
			}*/
			sql.append(" and m.create_time>=date_sub(NOW(),interval 30 day) ");
			sql.append(" order by m.create_time desc");
			List<Object[]> objs = this.queryParams(sql.toString(), null, null, params);
			List<MessageVo> vos = new ArrayList<MessageVo>();
			for(Object[] obj : objs){
				MessageVo vo = new MessageVo();
				vo.setId(((BigInteger)obj[0]).longValue());
				vo.setContent((String)obj[1]);
				//vo.setTypeName((Integer)obj[2]==0?"系统消息":"个人消息");
				//vo.setUserName((String)obj[3]);
				//vo.setStatus((Integer) obj[4]);
				vo.setCreateTime((String)obj[2]);
				vos.add(vo);
			}
		    //excel结构
		    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
		    excelColumns.add(new ExcelColumn(0, "content", "消息内容", Cell.CELL_TYPE_STRING));
	        //excelColumns.add(new ExcelColumn(1, "typeName", "消息类型", Cell.CELL_TYPE_STRING));
	        //excelColumns.add(new ExcelColumn(2, "userName", "用户名", Cell.CELL_TYPE_STRING));
	        excelColumns.add(new ExcelColumn(1, "createTime", "消息时间", Cell.CELL_TYPE_STRING));
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
	 * 查询单条Message信息
	 * @param tId
	 * @return 返回单个Message对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Message> findOne(Long modelId) throws BusinessException {
		Message obj = new Message();
		if(modelId != 0){
			obj = messageRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Message> xr = new XaResult<Message>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Message数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Message集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Message>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Message> page = messageRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Message.class), pageable);
		XaResult<Page<Message>> xr = new XaResult<Page<Message>>();
		xr.setObject(page);
		return xr;
	}

	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MessageVo>> findListNEStatusByFilterTwo(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		XaResult<Page<MessageVo>> xr = new XaResult<Page<MessageVo>>();
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		StringBuffer countSql = new StringBuffer();
		sql.append("select m.id,m.content,m.type,u.user_name,m.status,m.create_time from tb_xa_message m LEFT JOIN tb_xa_user u on m.user_id=u.id ");
		sql.append("where m.status<>").append(status);
		
		countSql.append("select count(*) from tb_xa_message m LEFT JOIN tb_xa_user u on m.user_id=u.id where m.status<>").append(status);
		//内容查询条件
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_content"))){
			sql.append(" and m.content like ?");
			countSql.append(" and m.content like ?");
			params.add("%" + filterParams.get("LIKE_content") + "%");
		}
		//消息类型查询条件
		if(XaUtil.isNotEmpty(filterParams.get("EQ_type"))){
			sql.append(" and m.type = ?");
			countSql.append(" and m.type = ?");
			params.add(filterParams.get("EQ_type"));
		}
		//用户名查询条件
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_userName"))){
			sql.append(" and u.user_name like ?");
			countSql.append(" and u.user_name like ?");
			params.add("%" + filterParams.get("LIKE_userName") + "%");
		}
		sql.append(" and m.create_time>=date_sub(NOW(),interval 30 day) ");
		countSql.append(" and m.create_time>=date_sub(NOW(),interval 30 day) ");
		sql.append(" order by m.create_time desc");
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(), params);
		List<Object[]> count = this.queryParams(countSql.toString(), null, null, params);
		List<MessageVo> listMessageVo=new ArrayList<MessageVo>();
		for(Object[] obj : objs){
			MessageVo vo=new MessageVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setContent((String)obj[1]);
			vo.setType((Integer)obj[2]);
			vo.setUserName((String)obj[3]);
			vo.setStatus((Integer)obj[4]);
			vo.setCreateTime((String)obj[5]);
			listMessageVo.add(vo);
		}
		Page<MessageVo> page=new MyPage<MessageVo>(pageable.getPageNumber(),pageable.getPageSize(),listMessageVo,Integer.valueOf(count.get(0)+""));
		xr.setObject(page);
		return xr;
	}
	
	/**
	 * 分页查询状态status的Message数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Message集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Message>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Message> page = messageRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Message.class), pageable);
		XaResult<Page<Message>> xr = new XaResult<Page<Message>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Message信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Message> saveOrUpdate(Message model) throws BusinessException {
		Message obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = messageRepository.findOne(model.getId());
		}else{
			obj = new Message();
		}
		obj.setContent(model.getContent());
		obj.setType(0);
		obj.setUserId(model.getUserId());
		obj.setTitle("平台消息");
		obj = messageRepository.save(obj);
		/*消息推送开始*/
		List<User> users = userRepository.findByStatusNot(XaConstant.Status.delete);
		List<String> strs = new ArrayList<String>();
		for(User user : users){
			strs.add(user.getMobile());
		}
		PushMessgeUtil.pushMessage(obj.getContent(),strs);
		
		/*消息推送结束*/
		XaResult<Message> xr = new XaResult<Message>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Message状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Message对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Message> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Message> xr = new XaResult<Message>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Message obj = messageRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = messageRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
