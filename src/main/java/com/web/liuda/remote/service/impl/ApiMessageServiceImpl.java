package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.Message;
import com.web.liuda.business.entity.MessageRecord;
import com.web.liuda.business.repository.MessageRecordRepository;
import com.web.liuda.business.repository.MessageRepository;
import com.web.liuda.remote.service.ApiMessageService;
import com.web.liuda.remote.vo.MessageVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIMessage接口实现类
 **/
@Service("ApiMessageService")
@Transactional(readOnly = false)
public class ApiMessageServiceImpl extends BaseService<Message> implements ApiMessageService{

	@Autowired
	MessageRepository messageRepository;
	@Autowired
	MessageRecordRepository messageRecordRepository;
	/**
	 * 消息详情
	 * author:changlu
	 * time:2016-05-03 16:23:00
	 * @param tId
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public XaResult<MessageVo> findOneMessage(Long tId,Long userId) throws BusinessException {
		Message obj = messageRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<MessageVo> xr = new XaResult<MessageVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MessageVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		//如果是登录用户，往消息记录表中插入数据
		if(XaUtil.isNotEmpty(userId)){
			List<MessageRecord> messages=messageRecordRepository.findByUserIdAndMessageIdAndStatus(userId,tId,XaConstant.Status.valid);
			if(XaUtil.isEmpty(messages)){
				MessageRecord messageRecord=new MessageRecord();
				messageRecord.setUserId(userId);
				messageRecord.setMessageId(tId);
				messageRecordRepository.save(messageRecord);
			}
			
		}
		return xr;
	}
	
	@Override
	public XaResult<MessageVo> findOne(Long tId) throws BusinessException {
		Message obj = messageRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<MessageVo> xr = new XaResult<MessageVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MessageVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<MessageVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		return null;
	}

	//@Override
	public XaResult<List<MessageVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		return null;
	}
	//查询我的消息列表
	@Override
	public XaResult<Page<MessageVo>> findPageNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		/*if (status == null) {
			status = XaConstant.Status.delete;
		}
		String endDate1="";
		try {
			endDate1 = XaUtil.getSevenStr(XaUtil.getToDayStr(),31);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		//我的消息(30天以内的消息)
		//Page<Message> messages = messageRepository.findByUserIdAndStatusNotOrUserIdIsNullAndStatusNotAndCreateTimeAfter(Long.valueOf(filterParams.get("EQ_userId").toString()),status,status,endDate1,pageable);
		String sql="call pro_message_list(?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(filterParams.get("EQ_userId"));
		params.add(pageable.getPageNumber()*pageable.getPageSize());
		params.add(pageable.getPageSize());
		List<Object[]> messages =this.queryCall(sql, params);
		List<MessageVo> vos =new ArrayList<MessageVo>();
		for(Object[] obj:messages){
			MessageVo vo=new MessageVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setCreateTime((String)obj[2]);
			vo.setIsRead(XaUtil.isEmpty(obj[3])?0:1);
			vo.setType(XaUtil.isEmpty(obj[4])?null:Integer.valueOf(obj[4]+""));
			vo.setContent((String)obj[5]);
			vos.add(vo);
		}
		//将查询的结果转为List<MessageVo>
		//List<MessageVo> vos=JSON.parseArray(JSON.toJSONString(messages.getContent()), MessageVo.class);
		Integer count=null;
		//统计记录条数
		if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))){
			count=messageRepository.getCountPage(Long.valueOf(filterParams.get("EQ_userId").toString()));
		}else{
			count=messageRepository.getCountPage();
		}
		
		Page<MessageVo> pages=new MyPage<MessageVo>(pageable.getPageNumber(),pageable.getPageSize(),vos,count);
		XaResult<Page<MessageVo>> xr = new XaResult<Page<MessageVo>>();
		xr.setObject(pages);
		return xr;
	}
	
	/**
	 * 查询是否有新消息
	 */
	@Override
	public XaResult<MessageVo> findNewMessage(Long userId)
			throws BusinessException {
		String sql="call pro_newmessage(?)";
		List<Object> params=new ArrayList<Object>();
		params.add(userId);
		List<Object[]> messages =this.queryCall(sql, params);
		MessageVo vo=new MessageVo();
		if(XaUtil.isNotEmpty(messages)){
			
			int count=XaUtil.isEmpty(messages.get(0))?null: Integer.valueOf(messages.get(0) + "");
			if(count>0){
				vo.setIsRead(0);
			}else{
				vo.setIsRead(1);
			}
		}
		
		XaResult<MessageVo> xr = new XaResult<MessageVo>();
		xr.setObject(vo);
		return xr;
	}
	@Override
	public XaResult<MessageVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		return null;
	}

	@Override
	public XaResult<MessageVo> createModel(Message model)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<MessageVo> updateModel(Message model)
			throws BusinessException {
		return null;
	}

}
