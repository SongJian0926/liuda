package com.web.liuda.remote.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Comment;
import com.web.liuda.remote.service.ApiCommentService;
import com.web.liuda.remote.vo.CommentVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiCommentController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 评论信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Comment", description = "评论接口", position = 10)
@Controller
@RequestMapping("/api/comment")
public class ApiCommentController extends BaseController {

	@Autowired
	private ApiCommentService commentService;
	
	/**
	 * @Title: findComments
	 * @Description: 评论列表
	 * @return    
	 */
	@ApiOperation(value="评论列表",notes="评论列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findComments",method=RequestMethod.POST)
	public XaResult<Page<CommentVo>> findComments(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("类型,字段名:type,0：酒店、1：景点、2：商品") @RequestParam(defaultValue = "0") Integer type,
		@ApiParam("对象Id,字段名:objectId,(商品Id、酒店Id、景点Id)") @RequestParam(required = false) Long objectId,
		@ApiParam("用户Id,字段名:userId") @RequestParam(required = false) Long userId
	) throws BusinessException{
		XaResult<Page<CommentVo>> xr = new XaResult<Page<CommentVo>>();
		if(XaUtil.isEmpty(type)){
			xr.error("类型不能为空");
			return xr;
		}
		if(type != 0 && type != 1 && type !=2){
			xr.error("没有此类型");
			return xr;
		}
		if(XaUtil.isEmpty(userId) && XaUtil.isEmpty(objectId)){
			xr.error("对象Id和用户Id不能同时为空");
			return xr;
		}
		if(XaUtil.isNotEmpty(objectId) && XaUtil.isNotEmpty(userId)){
			xr.error("对象Id和用户Id只能一个有值");
			return xr;
		}
		return commentService.findCommentList(nextPage, pageSize, objectId, userId, type);
	}
	
	/**
	 * @Title: save
	 * @Description: 新增一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="评论",notes="评论,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveComment",method=RequestMethod.POST)
	public XaResult<CommentVo> saveComment(
		@ApiParam("评论内容,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("评论类型,字段名:type 0：酒店 1：景点 2：商品") @RequestParam(value = "type") Integer type,
		@ApiParam("对象Id,字段名:objectId 酒店Id、景点Id、商品Id") @RequestParam(value = "objectId") Long objectId,
		@ApiParam("商品规格Id,字段名:standardId 商品规格Id") @RequestParam(required=false) Long standardId,
		@ApiParam("评论分数,字段名:score (对象为酒店和景点时使用)0.5到5之间（包含0.5和5）") @RequestParam(required=false,value = "score") Float score,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("订单Id,字段名:orderId 必填") @RequestParam Long orderId,
		@ApiParam("评论图片,字段名:imageurl，多个图片地址以逗号分割") @RequestParam(required = false) String imageurl,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<CommentVo> xr = new XaResult<CommentVo>();
		Comment model = new Comment();
		if(XaUtil.isEmpty(content)){
			xr.error("评论内容不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("评论类型不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(objectId)){
			xr.error("对象Id不能为空！");
			return xr;
		}
		
		if(type==JConstant.ObjectType.HOTEL||type==JConstant.ObjectType.TOURIST){
			if(XaUtil.isEmpty(score)){
				xr.error("评分不能为空！");
				return xr;
			}
			if(!Validator.isDouble(score.toString())){
				xr.error("输入的评分是非法的！");
				return xr;
			}
			if(score<0.5||score>5){
				xr.error("输入的评分是非法的！");
				return xr;
			}
			model.setScore(score);
		}
		
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(orderId)){
			xr.error("订单Id不能为空！");
			return xr;
		}
		
		if(!(type==0||type==1||type==2)){
			xr.error("输入的评论类型是非法的！");
			return xr;
		}
		if(!Validator.isNumber(objectId.toString())){
			xr.error("输入的对象Id是非法的！");
			return xr;
		}
		
		if(!Validator.isNumber(userId.toString())){
			xr.error("输入的用户Id是非法的！");
			return xr;
		}
		if(type==2){
			if(XaUtil.isEmpty(standardId)){
				xr.error("对商品进行评价时，商品规格Id不能为空！");
				return xr;
			}
		}
		model.setContent(content);
		model.setType(type);
		model.setObjectId(objectId);
		model.setUserId(userId);
		model.setOrderId(orderId);
		model.setStandardId(standardId);
		return commentService.createModel(model,imageurl);
	}		
}

