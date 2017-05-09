package com.web.liuda.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Comment;
import com.web.liuda.business.repository.CommentRepository;
import com.web.liuda.business.service.CommentService;
import com.web.liuda.remote.vo.CommentVo;
import com.web.liuda.remote.vo.ImagesVo;
import com.web.liuda.remote.vo.OrderVo;
import com.web.liuda.remote.vo.ReplyCommentVo;
import com.web.liuda.remote.vo.RoomVo;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("CommentService")
@Transactional(readOnly = false)
public class CommentServiceImpl extends BaseService<Comment> implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 分页查询状态非status的Comment数据
	 * author：changlu
	 * time:2016-01-14 16:00:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Comment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<CommentVo>> findCommentByFilter(Long businessId, Integer businessType,
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		XaResult<Page<CommentVo>> xr = new XaResult<Page<CommentVo>>();
		//查询列表
		String sql="call pro_comment_list(?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(filterParams.get("GTE_create_time"));
		params.add(filterParams.get("LTE_create_time"));
		params.add(businessType);
		params.add(businessId);
		params.add(pageable.getPageNumber()*pageable.getPageSize());
		params.add(pageable.getPageSize());
		List<Object[]> objs=this.queryCall(sql, params);
		//统计数据
		String sql1="call pro_comment_count(?,?,?,?)";
		List<Object> params1 = new ArrayList<Object>();
		params1.add(filterParams.get("GTE_create_time"));
		params1.add(filterParams.get("LTE_create_time"));
		params1.add(businessType);
		params1.add(businessId);
		List<Object[]> count=this.queryCall(sql1, params1);
		//查询回复评论的图片
		String sql2="call pro_reply_pics(?,?)";
		List<Object> params2 = new ArrayList<Object>();
		List<Object[]> replypics=new ArrayList<Object[]>();
		
		List<CommentVo> vos=new ArrayList<CommentVo>();
		for(Object[] obj:objs){
			CommentVo vo=new CommentVo();
			if(XaUtil.isNotEmpty(businessType)){
				OrderVo orderVo=new OrderVo();
				orderVo.setOrderNo((String)obj[0]);
				vo.setOrderVo(orderVo);
				//酒店
				if(businessType.equals(JConstant.ObjectType.HOTEL)){
					RoomVo roomVo=new RoomVo();
					roomVo.setType((String)obj[1]);
					vo.setRoomVo(roomVo);
				}
				//景点
				if(businessType.equals(JConstant.ObjectType.TOURIST)){
					TicketsVo ticketsVo=new TicketsVo();
					ticketsVo.setTicketName((String)obj[1]);
					vo.setTicketsVo(ticketsVo);
				}
				UserVo userVo=new UserVo();
				userVo.setUserName((String)obj[2]);
				userVo.setId(((BigInteger)obj[8]).longValue());
				vo.setUserVo(userVo);
				vo.setContent((String)obj[3]);
				ImagesVo imagesVo=new ImagesVo();
				List<String> pics1 =null;
				String[] pic=null;
				//评论图片
				if(XaUtil.isNotEmpty((String)obj[4])){
					pic= ((String)obj[4]).replaceAll("，", ",").split(",");
					pics1 = Arrays.asList(pic);
					imagesVo.setPics1(pics1);
					vo.setImagesVo(imagesVo);
				}
				//回复内容
				if(XaUtil.isNotEmpty(obj[10])){
					ReplyCommentVo replyCommentVo=new ReplyCommentVo();
					replyCommentVo.setReplyComment((String)obj[5]);
					replyCommentVo.setCreateTime((String)obj[6]);
					replyCommentVo.setId(((BigInteger)obj[10]).longValue());
					vo.setReplyCommentVo(replyCommentVo);
					params2.clear();
					params2.add(businessId);
					params2.add(replyCommentVo.getId());
					replypics=this.queryCall(sql2, params2);
				}
				vo.setCreateTime((String)obj[7]);
				vo.setId(((BigInteger)obj[9]).longValue());
			}
			/*List<String> pics2 =null;
			String[] pic2=null;
			ImagesVo imagesVo1=new ImagesVo();
			//回复图片
			if(XaUtil.isNotEmpty(replypics)){
				String strs=String.valueOf(replypics.get(0));
				pic2= (strs).replaceAll(";", ",").split(",");
				pics2 = Arrays.asList(pic2);
				imagesVo1.setPics1(pics2);
				vo.setImagesVo1(imagesVo1);
			}*/
			vos.add(vo);
		}
		//分页
		Page<CommentVo> page = new MyPage<CommentVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	/**
	 * 查询单条Comment信息
	 * @param tId
	 * @return 返回单个Comment对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Comment> findOne(Long modelId) throws BusinessException {
		Comment obj = new Comment();
		if(modelId != 0){
			obj = commentRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Comment> xr = new XaResult<Comment>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Comment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Comment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<CommentVo>> findPageNEStatusByFilter(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		StringBuffer sql=new StringBuffer("select c.id,c.create_time,c.content,c.status,c.score,c.type,u.user_name,");
		sql.append("(SELECT hotel_name from tb_xa_hotel h WHERE h.id=c.object_id AND c.type="+JConstant.ObjectType.HOTEL+") objectName, ");
		sql.append("(SELECT t.tourist_name from tb_xa_tourist t WHERE t.id=c.object_id AND c.type="+JConstant.ObjectType.TOURIST+") touristName, ");
		sql.append("(SELECT s.shop_name from tb_xa_shop s WHERE s.id=c.object_id AND c.type="+JConstant.ObjectType.SHOP+") shopName ");
		sql.append(" from tb_xa_comment c ");
		sql.append(" INNER JOIN tb_xa_user u ON u.id=c.user_id ");
		sql.append(" WHERE c.status<>"+ XaConstant.Status.delete +" AND u.status<>"+XaConstant.Status.delete);
		StringBuffer countsql = new StringBuffer("select count(*) from tb_xa_comment c ");
		countsql.append(" INNER JOIN tb_xa_user u ON u.id=c.user_id ");
		countsql.append(" WHERE c.status<>"+ XaConstant.Status.delete +" AND u.status<>"+XaConstant.Status.delete);
		Set<Entry<String,Object>> enties = filterParams.entrySet();
		//遍历map集合
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
					countsql.append(" and u.").append(arr[1]).append("_").append(arr[2]).append(" like '%").append(value).append("%'");
				}
				if(key.equals("GTE_create_time")){
					sql.append(" and c.").append(arr[1]).append("_").append(arr[2]).append(">'").append(value).append(" 00:00:00'");
					countsql.append(" and c.").append(arr[1]).append("_").append(arr[2]).append(">'").append(value).append(" 00:00:00'");
				}
				if(key.equals("LTE_create_time")){
					sql.append(" and c.").append(arr[1]).append("_").append(arr[2]).append("<'").append(value).append(" 23:59:59'");
					countsql.append(" and c.").append(arr[1]).append("_").append(arr[2]).append("<'").append(value).append(" 23:59:59'");
				}
				if(key.equals("BETWEEN_create_time")){
					String[] timeArr=value.toString().split(",");
					sql.append(" and c.").append(arr[1]).append("_").append(arr[2]).append(" between '").append(timeArr[0]+" 00:00:00'").append(" and '").append(timeArr[1]).append(" 23:59:59'");
					countsql.append(" and c.").append(arr[1]).append("_").append(arr[2]).append(" between '").append(timeArr[0]+" 00:00:00'").append(" and '").append(timeArr[1]).append(" 23:59:59'");
				}		
			}
		}
		sql.append(" order by c.create_time desc ");
		//创建Object[]集合对象objs
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(), new ArrayList<Object>());
		//创建InformationVo集合对象vos
		List<CommentVo> vos = new ArrayList<CommentVo>();
		for(Object[] obj : objs){
			//创建InformationVo对象vo
			CommentVo vo = new CommentVo();	
			//遍历obj集合，将其放入vo中
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setCreateTime((String)obj[1]);
			vo.setContent((String)obj[2]);
			vo.setStatus((Integer)obj[3]);
			vo.setScore((Float)obj[4]);
			vo.setType((Integer)obj[5]);
			vo.setUserName((String)obj[6]);
			vo.setObjectName(XaUtil.isNotEmpty(obj[7]) ? (String)obj[7] : (XaUtil.isNotEmpty(obj[8]) ? (String)obj[8] : (String)obj[9]));
			vos.add(vo);
		}	
		
		//将得到的查询结果强转成int类型
		List<Object[]> count = this.query(countsql.toString(), null, null);
		//调用分页
		Page<CommentVo> page= new MyPage<CommentVo>(pageable.getPageNumber(),pageable.getPageSize(),vos,Integer.valueOf(count.get(0) + ""));
		//创建结果集对象xr
		XaResult<Page<CommentVo>> xr=new XaResult<Page<CommentVo>>();
		//将page放入xr
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Comment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Comment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Comment>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Comment> page = commentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Comment.class), pageable);
		XaResult<Page<Comment>> xr = new XaResult<Page<Comment>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Comment信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Comment> saveOrUpdate(Comment model) throws BusinessException {
		Comment obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = commentRepository.findOne(model.getId());
		}else{
			obj = new Comment();
		}
		obj.setContent(model.getContent());
		obj.setType(model.getType());
		obj.setObjectId(model.getObjectId());
		obj.setScore(model.getScore());
		obj.setUserId(model.getUserId());
		obj = commentRepository.save(obj);
		XaResult<Comment> xr = new XaResult<Comment>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Comment状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Comment对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Comment> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Comment> xr = new XaResult<Comment>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Comment obj = commentRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = commentRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<Page<Comment>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
