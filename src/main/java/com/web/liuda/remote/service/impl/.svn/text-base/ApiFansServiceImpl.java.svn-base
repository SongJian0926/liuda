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
import com.web.liuda.business.entity.Fans;
import com.web.liuda.business.repository.FansRepository;
import com.web.liuda.remote.service.ApiFansService;
import com.web.liuda.remote.vo.FansVo;
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

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIFans接口实现类
 **/
@Service("ApiFansService")
@Transactional(readOnly = false)
public class ApiFansServiceImpl extends BaseService<Fans> implements ApiFansService{

	@Autowired
	FansRepository fansRepository;
	
	@Override
	public XaResult<FansVo> findOne(Long tId) throws BusinessException {
		Fans obj = fansRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<FansVo> xr = new XaResult<FansVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),FansVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/*
	 * 邀请成员列表
	 * author：changlu
	 * time:2016-04-27 14:32:00
	 */
	public XaResult<Page<FansVo>> findMyFansList(
			Integer status,Long userId,Long clubId, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<Page<FansVo>> xr=new XaResult<Page<FansVo>>();
		StringBuffer sql=new StringBuffer("select u.id userId,f.id fId,fans.id fansId,fans.user_name,fans.photo from tb_xa_fans f ");
		sql.append(" inner join tb_xa_user u on u.id=f.user_id inner join tb_xa_user fans on fans.id=f.fans_id ");
		sql.append(" where f.status="+XaConstant.Status.valid+" and u.id="+userId+" and f.fans_id in (select f.user_id from tb_xa_fans f where f.fans_id="+userId+" and f.status="+XaConstant.Status.valid+" )");
		StringBuffer countsql=new StringBuffer("select count(*) from tb_xa_fans f");
		countsql.append(" inner join tb_xa_user u on u.id=f.user_id inner join tb_xa_user fans on fans.id=f.fans_id ");
		countsql.append(" where f.status="+XaConstant.Status.valid+" and u.id="+userId+" and f.fans_id in (select f.user_id from tb_xa_fans f where f.fans_id="+userId+" and f.status="+XaConstant.Status.valid+" )");
		//俱乐部Id不为空，邀请我的好友加入俱乐部
		if(XaUtil.isNotEmpty(clubId)){
			sql.append(" and fans.id not in (select cm.user_id from tb_xa_clubmember cm where cm.club_id="+clubId+" and cm.status="+XaConstant.Status.valid+")");
			countsql.append(" and fans.id not in (select cm.user_id from tb_xa_clubmember cm where cm.club_id="+clubId+" and cm.status="+XaConstant.Status.valid+")");
		}
		sql.append(" order by f.create_time desc ");
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(),null);
		List<Object[]> count = this.query(countsql.toString(), null,null);
		List<FansVo> vos = new ArrayList<FansVo>();
		for(Object[] obj : objs){
			FansVo vo=new FansVo();
			vo.setUserId(XaUtil.isEmpty(obj[0])? null:((BigInteger)obj[0]).longValue());
			vo.setId(XaUtil.isEmpty(obj[1])? null:((BigInteger)obj[1]).longValue());
			UserVo userVo=new UserVo();
			userVo.setId(XaUtil.isEmpty(obj[2])? null:((BigInteger)obj[2]).longValue());
			userVo.setUserName((String)obj[3]);
			userVo.setPhoto((String)obj[4]);
			vo.setUserVo(userVo);
			vos.add(vo);
		}
		//分页
		Page<FansVo> page = new MyPage<FansVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;

	}
	@Override
	public XaResult<List<FansVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Fans> page = fansRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Fans.class), pageable);
		XaResult<List<FansVo>> xr = new XaResult<List<FansVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), FansVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<FansVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Fans> page = fansRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Fans.class), pageable);
		XaResult<List<FansVo>> xr = new XaResult<List<FansVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), FansVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<FansVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<FansVo> xr = new XaResult<FansVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Fans obj = fansRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = fansRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FansVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<FansVo> createModel(Fans model)
			throws BusinessException {
		XaResult<FansVo> xr = new XaResult<FansVo>();
		Fans obj = fansRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FansVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<FansVo> updateModel(Fans model)
			throws BusinessException {
		Fans obj = fansRepository.findOne(model.getId());
		XaResult<FansVo> xr = new XaResult<FansVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setFansId(model.getFansId());
			obj = fansRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FansVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//我的好友
	public XaResult<FansVo> findMyFrindList(Integer status, Long userId) throws BusinessException {
		FansVo newFansVo = new FansVo();
		XaResult<FansVo> xr = new XaResult<FansVo>();
		
		//我的粉丝人数
		StringBuffer sqlMyFansCount = new StringBuffer();
		sqlMyFansCount.append("select count(*) from (select fs.fans_id,");
		sqlMyFansCount.append("(select ff.user_id from tb_xa_fans ff where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) frindId,");
		sqlMyFansCount.append("(select u.user_name from tb_xa_user u where u.id = fs.fans_id) uName,");
		sqlMyFansCount.append("(select u.photo from tb_xa_user u where u.id = fs.fans_id) uPoto");
		sqlMyFansCount.append(" from tb_xa_fans fs where fs.user_id="+userId+" and fs.status=1) t  where frindId is null");
		 //粉丝数
		Integer fansNum = Integer.parseInt(this.query(sqlMyFansCount.toString(), 0, 0).get(0)+"");
		newFansVo.setFansNum(fansNum);
		
		//我关注的
        StringBuffer sqlMyClose = new StringBuffer("");
        sqlMyClose.append("select * from(select fs.user_id,");
        sqlMyClose.append("(select u.user_name from tb_xa_user u where u.id=fs.user_id) uName,");
        sqlMyClose.append("(select u.photo from tb_xa_user u where u.id=fs.user_id) uPoto,");
        sqlMyClose.append("(select ff.fans_id from tb_xa_fans ff where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) frindId,id");
        sqlMyClose.append("  from tb_xa_fans fs where fs.fans_id="+userId+" and fs.status=1) t  where frindId is null");
        //我的关注人数
        StringBuffer sqlMyCloseCount = new StringBuffer("");
        sqlMyCloseCount.append("select count(*) from(select fs.user_id,");
        sqlMyCloseCount.append("(select u.user_name from tb_xa_user u where u.id=fs.user_id) uName,");
        sqlMyCloseCount.append("(select u.photo from tb_xa_user u where u.id=fs.user_id) uPoto,");
        sqlMyCloseCount.append("(select ff.fans_id from tb_xa_fans ff where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) frindId");
        sqlMyCloseCount.append("  from tb_xa_fans fs where fs.fans_id="+userId+" and fs.status=1) t  where frindId is null");
        
        //关注
        List<Object[]> myClose = this.query(sqlMyClose.toString(), 0, 0);
        List<FansVo> myCloseList = new ArrayList<FansVo>();
        for(Object[] close:myClose){
        	FansVo fs = new FansVo();
        	fs.setId(Long.parseLong(close[4]+""));
        	UserVo user = new UserVo();
        	user.setId(Long.parseLong(close[0]+""));
        	user.setUserName((String)close[1]);
        	user.setPhoto((String)close[2]);
        	fs.setUserVo(user);
        	myCloseList.add(fs);
        }
        
        //关注人数
        Integer closeNum = Integer.parseInt(this.query(sqlMyCloseCount.toString(), 0, 0).get(0)+"");
        newFansVo.setCloseNum(closeNum);
        newFansVo.setMyCloseList(myCloseList);
        
        //我的好友
        StringBuffer sqlMyFrind = new StringBuffer("");
        sqlMyFrind.append("select (select ff.user_id from tb_xa_fans ff where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) frindId,");
        sqlMyFrind.append("(select u.user_name from tb_xa_fans ff INNER JOIN tb_xa_user u on ff.user_id = u.id where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) uName,");
        sqlMyFrind.append("(select u.photo from tb_xa_fans ff INNER JOIN tb_xa_user u on ff.user_id = u.id where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) uPhoto,id");
        sqlMyFrind.append("  from tb_xa_fans fs where fs.user_id="+userId+" and fs.status="+XaConstant.Status.valid+" ");
        
        List<Object[]> myMyFrind = this.query(sqlMyFrind.toString(), 0, 0);
        List<FansVo> myMyFrindList = new ArrayList<FansVo>();
        for(Object[] frind:myMyFrind){
        	FansVo fs = new FansVo();
        	if(XaUtil.isNotEmpty(frind[0])){
	        	fs.setId(Long.parseLong(frind[3]+""));
	        	UserVo user = new UserVo();
	        	user.setId(Long.parseLong(frind[0]+""));
	        	user.setUserName((String)frind[1]);
	        	user.setPhoto((String)frind[2]);
	        	fs.setUserVo(user);
	        	myMyFrindList.add(fs);
        	}
        }        
        newFansVo.setMyMyFrindList(myMyFrindList);
        xr.setObject(newFansVo);
        return xr;
	}
	//加为好友
	@Transactional
	public XaResult<FansVo> addFrinding(Long userId, Long frindId)
			throws BusinessException {
		XaResult<FansVo> xr = new XaResult<FansVo>();
		Fans fans = fansRepository.findByFansIdAndUserIdAndStatus(userId, frindId,XaConstant.Status.valid);
		if(XaUtil.isEmpty(fans)){
			/*StringBuffer addFrindSql = new StringBuffer("");
			addFrindSql.append("insert into tb_xa_fans(create_time,status,fans_id,user_id) values('"+XaUtil.getToDayStr()+"',"+XaConstant.Status.valid+","+userId+","+frindId+")");*/
			//this.insert(addFrindSql.toString());
			Fans obj=new Fans();
			obj.setFansId(userId);
			obj.setUserId(frindId);
			obj=fansRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FansVo.class));
			xr.getObject().setId(obj.getId());
		}else{
			xr.error("添加失败,该好友/关注已存在");
		}
		return xr;
	}
	//粉丝page
	@Override
	public XaResult<Page<FansVo>> findMyFansList(Integer status, Long userId,
			Pageable pageable) {
		XaResult<Page<FansVo>> xr = new XaResult<Page<FansVo>>();
		//我的粉丝需要分页
		StringBuffer sqlMyFans = new StringBuffer("");
		sqlMyFans.append("select * from (select fs.fans_id,");
		sqlMyFans.append("(select u.user_name from tb_xa_user u where u.id = fs.fans_id) uName,");
		sqlMyFans.append("(select u.photo from tb_xa_user u where u.id = fs.fans_id) uPoto,");
		sqlMyFans.append("(select ff.user_id from tb_xa_fans ff where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) frindId,id");
		sqlMyFans.append(" from tb_xa_fans fs where fs.user_id="+userId+" and fs.status=1) t where frindId is null");
		//我的粉丝人数
		StringBuffer sqlMyFansCount = new StringBuffer();
		sqlMyFansCount.append("select count(*) from (select fs.fans_id,");
		sqlMyFansCount.append("(select ff.user_id from tb_xa_fans ff where ff.user_id = fs.fans_id and ff.fans_id=fs.user_id and ff.status=1) frindId,");
		sqlMyFansCount.append("(select u.user_name from tb_xa_user u where u.id = fs.fans_id) uName,");
		sqlMyFansCount.append("(select u.photo from tb_xa_user u where u.id = fs.fans_id) uPoto");
		sqlMyFansCount.append(" from tb_xa_fans fs where fs.user_id="+userId+" and fs.status=1) t where frindId is null");
	    //粉丝
        List<Object[]> myFans = this.query(sqlMyFans.toString(), pageable.getPageSize()*pageable.getPageNumber(), pageable.getPageSize());
        List<FansVo> fansVoList = new ArrayList<FansVo>();
        for(Object[] fans:myFans){
        	FansVo fs = new FansVo();
        	fs.setId(XaUtil.isEmpty(fans[4])?null:Long.parseLong(fans[4]+""));
        	UserVo user = new UserVo();
        	user.setId(XaUtil.isEmpty(fans[0])?null:Long.parseLong(fans[0]+""));
        	user.setUserName(XaUtil.isNotEmpty(fans[1])?(String)fans[1]:"");
        	user.setPhoto(XaUtil.isNotEmpty(fans[2])?(String)fans[2]:"");
        	fs.setUserVo(user);
        	fansVoList.add(fs);
        }
        //粉丝数
        Integer fansNum = Integer.parseInt(this.query(sqlMyFansCount.toString(), 0, 0).get(0)+"");
        Page<FansVo> fansPage = new MyPage<FansVo>(pageable.getPageNumber(),pageable.getPageSize(),fansVoList,fansNum);
        xr.setObject(fansPage);
		return xr;
	}

}
