package com.web.liuda.remote.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.GuessLog;
import com.web.liuda.remote.service.ApiGuessService;
import com.web.liuda.remote.service.ApiMatchService;
import com.web.liuda.remote.service.ApiUserService;
import com.web.liuda.remote.vo.GuessOptionVo;
import com.web.liuda.remote.vo.GuessVo;
import com.web.liuda.remote.vo.GuessLogVo;
import com.web.liuda.remote.vo.MacthVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiGuessController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 赛事竞猜表信息接口
 * @author flora.li
 * @date 2016年4月20日 下午13:00:00
 * @version V1.0
 */
@Api(value = "Guess", description = "赛事竞猜表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/guess")
public class ApiGuessController extends BaseController {
	
	@Autowired
	private ApiGuessService guessService;
	@Autowired
	private ApiMatchService matchService;
	@Autowired
	private ApiUserService userService;

	/**
	 * @Title: findGuessByMatchId
	 * @Description: 赛事竞猜列表
	 * @return    
	 */
	@ApiOperation(value="赛事竞猜列表",notes="赛事竞猜列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findGuessByMatchId",method=RequestMethod.POST)
	public XaResult<GuessVo> findGuessByMatchId(
			@ApiParam("用户Id,字段名:userId") @RequestParam(required=true,value = "userId") Long userId,
			@ApiParam("赛事Id,字段名:matchId")  @RequestParam(required=true) Long matchId
	) throws BusinessException{
		XaResult<GuessVo> xr=new XaResult<GuessVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(matchId)){
			xr.error("赛事Id不能为空！");
			return xr;
		}
		return guessService.findGuessByMatchIdAndUserId(XaConstant.Status.delete, userId, matchId);
	}
	

	/**
	 * @Title: save
	 * @Description: 竞猜投注
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="竞猜投注",notes="竞猜投注,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveGuessLog",method=RequestMethod.POST)
	public XaResult<GuessLogVo> saveGuessLog(
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=true,value = "userId") Long userId,
		@ApiParam("赛事ID,字段名:matchId") @RequestParam(required=true,value = "matchId") Long matchId,
		@ApiParam("竞猜选项ID,字段名:optionId") @RequestParam(required=true,value = "optionId") Long optionId,
		@ApiParam("投注积分,字段名:betScore") @RequestParam(required=true,value = "betScore") Integer betScore,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<GuessLogVo> xr=new XaResult<GuessLogVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(matchId)){
			xr.error("赛事Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(optionId)){
			xr.error("竞猜选项Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(betScore)){
			xr.error("投注积分不能为空！");
			return xr;
		}
		if(betScore<=0){
			xr.error("投注积分必须大于0！");
			return xr;
		}

		//判断该用户是否已经投过注了
		GuessLogVo guesslogvo = guessService.findGuessLogByMatchIdAndUserId(XaConstant.Status.delete, userId, matchId).getObject();
		if(guesslogvo!=null)
		{
			xr.error("只能投注一次！");
			return xr;
		}
		
		//判断该赛事是否为进行中,只有进行中的才能投注
		MacthVo match = matchService.findOne(matchId).getObject();
		if(!match.getMatchStatus().equals(JConstant.EventStatus.EVENTING))
		{
			xr.error("只有进行中的赛事才能投注！");
			return xr;
		}
		
		//判断该选项是否存在,并且是否为该赛事
		GuessOptionVo guessoptionvo = guessService.findGuessOptionByOptionId(XaConstant.Status.valid, optionId).getObject();
		if(!guessoptionvo.getMatchId().equals(matchId))
		{
			xr.error("该竞猜选项ID{"+optionId+"}不属于该赛事ID{"+matchId+"}！");
			return xr;
		}
		
		//判断投注积分是否大于当前积分,要同时扣掉用户当前积分
		UserVo user = userService.findOne(userId).getObject();
		if(user.getPoints()==null || user.getPoints().compareTo(betScore)<0)
		{
			xr.error("积分不足！");
			return xr;
		}
		
		GuessLog model = new GuessLog();
		model.setUserId(userId);
		model.setMatchId(matchId);
		model.setOptionId(optionId);
		model.setBetScore(betScore);
		return guessService.createGuessLogModel(model);
	}
	
	
}
