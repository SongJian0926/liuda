package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Comment;
import com.web.liuda.business.entity.Images;
import com.web.liuda.business.entity.Order;
import com.web.liuda.business.repository.CommentRepository;
import com.web.liuda.business.repository.ImagesRepository;
import com.web.liuda.business.repository.OrderRepository;
import com.web.liuda.remote.service.ApiCommentService;
import com.web.liuda.remote.vo.CommentVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.StandardVo;
import com.web.liuda.remote.vo.TouristVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIComment接口实现类
 **/
@Service("ApiCommentService")
@Transactional(readOnly = false)
public class ApiCommentServiceImpl extends BaseService<Comment> implements ApiCommentService{

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ImagesRepository imagesRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	
	@Override
	public XaResult<List<CommentVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Comment> page = commentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Comment.class), pageable);
		XaResult<List<CommentVo>> xr = new XaResult<List<CommentVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), CommentVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<CommentVo> createModel(Comment model,String imageurl)
			throws BusinessException {
		XaResult<CommentVo> xr = new XaResult<CommentVo>();
		List<Comment> comments=commentRepository.findByOrderIdAndUserIdAndStandardIdAndStatus(model.getOrderId(),model.getUserId(),model.getStandardId(),XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(comments)){
			xr.error("该订单已评价！");
			return xr;
		}
		Comment obj = commentRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), CommentVo.class));
		xr.getObject().setId(obj.getId());
		if(XaUtil.isNotEmpty(obj)){
			if(XaUtil.isNotEmpty(imageurl)){
				String[] imageurls = imageurl.replaceAll("，", ",").split(",");
				//遍历数组imageurls
				for(String imgUrl : imageurls){
					Images iv = new Images();
					iv.setObjectId((obj.getId()).longValue());
					iv.setType(JConstant.ImageType.COMMENT);
					iv.setPicurl(imgUrl);
					//保存InfoKeywords对象
					imagesRepository.save(iv);
				}
			}
		
		}
		//修改订单状态
		if(XaUtil.isNotEmpty(obj)){
			if(XaUtil.isNotEmpty(model.getOrderId())){
				Order order=orderRepository.findOne(model.getOrderId());
				if(XaUtil.isNotEmpty(order)){
					order.setOrderStatus(JConstant.OrderStatus.COMMENTED);
					orderRepository.save(order);
				}else{
					xr.error("订单不存在");
					return xr;
				}
				
				
			}
		}
		return xr;
	}
	@Override
	public XaResult<CommentVo> updateModel(Comment model)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<CommentVo> createModel(Comment model)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<CommentVo> findOne(Long id) throws BusinessException {
		return null;
	}

	@Override
	public XaResult<List<CommentVo>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<CommentVo> multiOperate(String ids, Integer status)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<Page<CommentVo>> findCommentList(Integer nextPage,Integer pageSize,Long objectId,
			Long userId, Integer type) {
		XaResult<Page<CommentVo>> xr = new XaResult<Page<CommentVo>>();
		//参数：userId,objectId,type,nextPage,pageSize
		String sql = "call pro_commentList(?,?,?,?,?)";
		String countSql = "call pro_commentCount(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		List<Object> countParams = new ArrayList<Object>();
		//判断用户Id是否为空，不为空为个人中心我的评价，为空则为对象的评价
		if(XaUtil.isEmpty(userId)){
			params.add(null);
			params.add(objectId);
			params.add(type);
			params.add(nextPage * pageSize);
			params.add(pageSize);
			countParams.add(null);
			countParams.add(objectId);
			countParams.add(type);
		}else if(XaUtil.isEmpty(objectId)){
			params.add(userId);
			params.add(null);
			params.add(type);
			params.add(nextPage * pageSize);
			params.add(pageSize);
			countParams.add(userId);
			countParams.add(null);
			countParams.add(type);
		}
		List<Object[]> objs = this.queryCall(sql, params);
		List<Object[]> count = this.queryCall(countSql, countParams);
		List<CommentVo> comments = new ArrayList<CommentVo>();
		for(Object[] obj : objs){
			CommentVo vo = new CommentVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setObjectId(((BigInteger)obj[1]).longValue());
			vo.setContent((String)obj[2]);
			DecimalFormat df = new DecimalFormat("#.0"); 
			if(type == 0 || type == 1){
				vo.setScore(XaUtil.isEmpty(obj[3]) ? 0 : (Float)obj[3]);
			}
			vo.setType(type);
			vo.setCreateTime((String)obj[4]);
			vo.setPics(XaUtil.isEmpty(obj[5]) ? null : ((String)obj[5]).split(";"));
			//如果用户ID为空，查询的是对象的评价，否则查询的是我的评价
			if(XaUtil.isEmpty(userId)){
				vo.setUserId(((BigInteger)obj[6]).longValue());
				vo.setUserName((String)obj[7]);
				vo.setPhoto((String)obj[8]);
				vo.setReplyComment((String)obj[9]);
				vo.setReplyTime((String)obj[10]);
			}else{
				//判断评论的类型是酒店、景点还是商品，根据类型封装不同的对象列表
				if(type == 0){
					//酒店
					HotelVo hotel = new HotelVo();
					hotel.setId(((BigInteger)obj[1]).longValue());
					hotel.setHotelName((String)obj[6]);
					hotel.setPicurl((String)obj[12]);
					hotel.setType((String)obj[8]);
					hotel.setCheckinTime((String)obj[13]);
					hotel.setLeaveTime((String)obj[14]);
					hotel.setPrice((Double)obj[9]);
					hotel.setNumber((Integer)obj[15]);
					hotel.setDayNumber(XaUtil.daysBetween((String)obj[13], (String)obj[14]));
					hotel.setTotalPrice(XaUtil.daysBetween((String)obj[13], (String)obj[14]) * (Double)obj[17] * (Integer)obj[15]);
					hotel.setGroupBuy(XaUtil.isEmpty(obj[11]) ? 0 : ((BigInteger)obj[11]).intValue());
					hotel.setGroupPrice(XaUtil.isEmpty(obj[10]) ? null : (Double)obj[10]);
					vo.setReplyComment((String)obj[18]);
					vo.setReplyTime((String)obj[19]);
					vo.setHotel(hotel);
				}else if(type == 1){
					//景点
					TouristVo tourist = new TouristVo();
					tourist.setId(((BigInteger)obj[1]).longValue());
					tourist.setTouristName((String)obj[6]);
					tourist.setTicketName((String)obj[12]);
					tourist.setImgUrl((String)obj[11]);
					tourist.setPrice((Double)obj[9]);
					tourist.setNumber((Integer)obj[13]);
					tourist.setTotalPrice((Double)obj[14] * (Integer)obj[13]);
					tourist.setGroupBuy(XaUtil.isEmpty(obj[10]) ? 0 : ((BigInteger)obj[10]).intValue());
					tourist.setGroupPrice(XaUtil.isEmpty(obj[8]) ? null : (Double)obj[8]);
					vo.setReplyComment((String)obj[15]);
					vo.setReplyTime((String)obj[16]);
					vo.setTourist(tourist);
				}else{
					//商品
					ShopVo shop = new ShopVo();
					shop.setId(((BigInteger)obj[1]).longValue());
					shop.setShopName((String)obj[6]);
					shop.setImgUrl((String)obj[8]);
					StandardVo stand = new StandardVo();
					stand.setPorperty((String)obj[9]);
					stand.setGroupBuy((Integer)obj[11]);
					stand.setGroupPrice(XaUtil.isEmpty(obj[12]) ? null : (Double)obj[12]);
					shop.setStandard(stand);
					shop.setPrice((Double)obj[10]);
					vo.setShop(shop);
				}
			}
			comments.add(vo);
		}
		Page<CommentVo> page = new MyPage<CommentVo>(nextPage, pageSize, comments, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}

}
