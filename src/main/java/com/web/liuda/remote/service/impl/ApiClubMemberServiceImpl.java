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
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.business.repository.ClubMemberRepository;
import com.web.liuda.business.repository.ClubRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.remote.service.ApiClubMemberService;
import com.web.liuda.remote.vo.ClubMemberVo;
import com.web.liuda.remote.vo.ClubVo;
import com.web.liuda.remote.vo.DictItemVo;
import com.web.liuda.remote.vo.GuideAppendVo;
import com.web.liuda.remote.vo.GuideVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIClubMember接口实现类
 **/
@Service("ApiClubMemberService")
@Transactional(readOnly = false)
public class ApiClubMemberServiceImpl extends BaseService<ClubMember> implements ApiClubMemberService{

	@Autowired
	ClubMemberRepository clubMemberRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ClubRepository clubRepository;
	
	@Override
	public XaResult<ClubMemberVo> findOne(Long tId) throws BusinessException {
		ClubMember obj = clubMemberRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),ClubMemberVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//个人主页
	public XaResult<ClubMemberVo> findOneDetail(Long userId,Long modelId)
			throws BusinessException {
		XaResult<ClubMemberVo> xr=new XaResult<ClubMemberVo>();
		StringBuffer sql=new StringBuffer("select u.id,u.user_name,u.photo,u.big_shot ");
		if(XaUtil.isNotEmpty(userId)&&userId>0){
			//查看他人资料
			if(userId!=modelId){
				sql.append(",(select count(*) from tb_xa_fans f where f.user_id="+modelId+" and f.fans_id="+userId+" and status="+XaConstant.Status.valid+") isFriend ");
				sql.append(",(select f.id from tb_xa_fans f where f.user_id="+modelId+" and f.fans_id="+userId+" and status="+XaConstant.Status.valid+") fId ");
			}
		}
		sql.append(" from tb_xa_user u where u.id="+modelId+" ");
		List<Object[]> objs = this.queryParams(sql.toString(), null, null,null);
		ClubMemberVo vo=new ClubMemberVo();
		if(XaUtil.isNotEmpty(objs)){
			UserVo userVo=new UserVo();
			userVo.setId(XaUtil.isEmpty(objs.get(0)[0])?null:((BigInteger)objs.get(0)[0]).longValue());
			userVo.setUserName((String)objs.get(0)[1]);
			userVo.setPhoto((String)objs.get(0)[2]);
			userVo.setBigShot(XaUtil.isEmpty(objs.get(0)[3])?null:Integer.valueOf(objs.get(0)[3]+""));
			vo.setUserVo(userVo);
			if(XaUtil.isNotEmpty(userId)&&userId>0){
				//查看他人资料
				if(userId!=modelId){
					vo.setIsFriend(XaUtil.isEmpty(objs.get(0)[4])?null:Integer.valueOf(objs.get(0)[4]+""));
					vo.setFansId(XaUtil.isEmpty(objs.get(0)[5])?null:Long.valueOf(objs.get(0)[5]+""));
				}
			}
			//俱乐部信息
			vo.setClubVos(getMyClub(modelId));
			//攻略信息
			vo.setGuideVos(getMyGuide(modelId));
		}
		xr.setObject(vo);
		return xr;
	}
	//我的攻略
	public List<GuideVo> getMyGuide(Long userId){
		StringBuffer sql=new StringBuffer("select g.id,g.title,g.last_update,u.user_name,");
		sql.append("g.type,d.dict_name,");
		sql.append("(select ga.content from tb_xa_guideappend ga where ga.guide_id=g.id and status=1 order by create_time limit 1) content,");
		sql.append("(select ga.media_path from tb_xa_guideappend ga where ga.guide_id=g.id and status=1 and ga.media_path is not NULL order by create_time limit 1) media_path,g.pageview ");
		
		sql.append("from tb_xa_guide g ");
		sql.append("inner join tb_xa_user u on u.id=g.user_id ");
		sql.append("inner join tb_xa_dictitem d on d.id=g.dict_item_id ");
		sql.append("where g.status="+XaConstant.Status.publish+" and g.user_id="+userId+"");
		
		sql.append(" order by g.last_update desc ");
		
		List<Object[]> objs = this.query(sql.toString(), null, null);
		List<GuideVo> vos = new ArrayList<GuideVo>();
		for(Object[] obj : objs){
			GuideVo vo = new GuideVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setLastUpdate((String)obj[2]);
			//作者信息
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[3]);
			vo.setUserVo(userVo);
			vo.setType(XaUtil.isEmpty(obj[4]) ? null:(Integer)obj[4]);
			//标签信息
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[5]);
			vo.setDictItemVo(dictItemVo);
			//攻略内容信息
			GuideAppendVo guideAppendVo=new GuideAppendVo();
			guideAppendVo.setContent((String)obj[6]);
			
			//有图片
			if(XaUtil.isNotEmpty(obj[7])){
				String[] pic=null;
				pic= ((String)obj[7]).replaceAll("，", ",").split(",");
				guideAppendVo.setMediaPath(pic[0].toString());
			}else{
				guideAppendVo.setMediaPath(null);
			}
			vo.setGuideAppendVo(guideAppendVo);
			vo.setPageview(XaUtil.isEmpty(obj[8])?0 :(Integer)obj[8]);
			vos.add(vo);
		}
		
		return vos;
	}
	//我参加的俱乐部
	public List<ClubVo> getMyClub(Long userId){
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name type,c.logo,cm.member_type ");
		sql.append(" from tb_xa_club c inner join tb_xa_clubmember cm on cm.club_id=c.id inner join tb_xa_dictitem d on d.id=c.type ");
		sql.append(" where c.status="+XaConstant.Status.valid+" and c.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.status="+XaConstant.Status.valid+" and cm.user_id="+userId+" ");
		sql.append(" order by cm.create_time desc ");
		List<Object[]> objs=this.query(sql.toString(),null,null );
		List<ClubVo> vos=new ArrayList<ClubVo>();
		for(Object[] obj : objs){
			ClubVo vo=new ClubVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[2]);
			vo.setClubTypeVo(dictItemVo);
			vo.setLogo((String)obj[3]);
			vo.setMemberType(XaUtil.isEmpty(obj[4])?null:Integer.valueOf(obj[4]+""));
			vos.add(vo);
		}
		
		return vos;
	}
	/*
	 *俱乐部新成员列表
	 * author:changlu
	 * time:2016-04-11 17:41:00
	 */
	@Override
	public XaResult<Page<ClubMemberVo>> findNewClubMemberList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<Page<ClubMemberVo>> xr = new XaResult<Page<ClubMemberVo>>();
		ClubMember clubMember=new ClubMember();
		if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))){
			clubMember=clubMemberRepository.findByUserIdAndApplyStatusAndStatusNotAndClubId(Long.valueOf(filterParams.get("EQ_userId")+""),JConstant.ApplyStatus.CHECKSUCCEED,XaConstant.Status.delete,Long.valueOf(filterParams.get("EQ_clubId")+""));
		}
		if(XaUtil.isNotEmpty(clubMember)){
			//如果用户是部长或者管理员
			if(clubMember.getMemberType()==JConstant.ClubRole.MINISTER||clubMember.getMemberType()==JConstant.ClubRole.ADMIN){
				StringBuffer sql=new StringBuffer("select cm.id cmId,u.id userId,cm.member_type,u.user_name,u.photo ");
				sql.append("from tb_xa_clubmember cm inner join tb_xa_user u on u.id=cm.user_id ");
				sql.append("where cm.status=1 and cm.apply_status="+JConstant.ApplyStatus.CHECK+" and cm.club_id="+Long.valueOf(filterParams.get("EQ_clubId").toString())+"");
				sql.append(" order by cm.create_time desc ");
				StringBuffer countsql=new StringBuffer("select count(*) ");
				countsql.append("from tb_xa_clubmember cm inner join tb_xa_user u on u.id=cm.user_id ");
				countsql.append("where cm.status=1 and cm.apply_status="+JConstant.ApplyStatus.CHECK+" and cm.club_id="+Long.valueOf(filterParams.get("EQ_clubId").toString())+"");
				List<Object[]> objs = this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize());
				List<Object[]> count = this.query(countsql.toString(), null,null);
				List<ClubMemberVo> vos = new ArrayList<ClubMemberVo>();
				for(Object[] obj : objs){
					ClubMemberVo vo = new ClubMemberVo();
					vo.setId(XaUtil.isEmpty(obj[0])? null:((BigInteger)obj[0]).longValue());
					vo.setMemberType(XaUtil.isEmpty(obj[2])? null:Integer.valueOf(obj[2]+""));
					UserVo userVo=new UserVo();
					userVo.setId(XaUtil.isEmpty(obj[1])? null:((BigInteger)obj[1]).longValue());
					userVo.setUserName((String)obj[3]);
					userVo.setPhoto((String)obj[4]);
					vo.setUserVo(userVo);
					vos.add(vo);
				}
				//分页
				Page<ClubMemberVo> page = new MyPage<ClubMemberVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
				xr.setObject(page);
				
			}
		}
		
		
		return xr;
	}
	/*
	 *俱乐部成员列表
	 * author:changlu
	 * time:2016-04-11 17:41:00
	 */
	@Override
	public XaResult<Page<ClubMemberVo>> findClubMemberList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		
		StringBuffer sql=new StringBuffer("select cm.id cmId,u.id userId,cm.member_type,u.user_name,u.photo ");
		
		sql.append("from tb_xa_clubmember cm inner join tb_xa_user u on u.id=cm.user_id ");
		sql.append("where cm.status=1 and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.club_id="+Long.valueOf(filterParams.get("EQ_clubId").toString())+"");
		sql.append(" order by cm.member_type asc,cm.create_time desc ");
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append("from tb_xa_clubmember cm inner join tb_xa_user u on u.id=cm.user_id ");
		countsql.append("where cm.status=1 and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.club_id="+Long.valueOf(filterParams.get("EQ_clubId").toString())+"");
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(),null);
		List<Object[]> count = this.query(countsql.toString(), null,null);
		List<ClubMemberVo> vos = new ArrayList<ClubMemberVo>();
		for(Object[] obj : objs){
			ClubMemberVo vo = new ClubMemberVo();
			vo.setId(XaUtil.isEmpty(obj[0])? null:((BigInteger)obj[0]).longValue());
			vo.setMemberType(XaUtil.isEmpty(obj[2])? null:Integer.valueOf(obj[2]+""));
			UserVo userVo=new UserVo();
			userVo.setId(XaUtil.isEmpty(obj[1])? null:((BigInteger)obj[1]).longValue());
			userVo.setUserName((String)obj[3]);
			userVo.setPhoto((String)obj[4]);
			vo.setUserVo(userVo);
			vos.add(vo);
		}
		XaResult<Page<ClubMemberVo>> xr=new XaResult<Page<ClubMemberVo>>();
		//分页
		Page<ClubMemberVo> page = new MyPage<ClubMemberVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	/*
	 * time:2016-04-12 11:12:00
	 * author:changlu
	 * 同意加入、删除请求
	 */
	@Override
	public XaResult<ClubMemberVo> multiClubMember(Long userId,Long id,Integer applyStatus,
			Integer status) throws BusinessException {
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		ClubMember obj = clubMemberRepository.findByIdAndStatusNot(id,XaConstant.Status.delete);
		if (XaUtil.isNotEmpty(obj)) {
			//删除请求
			if(XaUtil.isNotEmpty(status)&&status==XaConstant.Status.delete){
						obj.setStatus(status);
						obj = clubMemberRepository.save(obj);
						xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
						xr.getObject().setId(obj.getId());
			}
			//同意请求
			if(XaUtil.isNotEmpty(applyStatus)&&applyStatus==JConstant.ApplyStatus.CHECKSUCCEED){
				obj.setApplyStatus(applyStatus);
				obj = clubMemberRepository.save(obj);
				xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
				xr.getObject().setId(obj.getId());
			}
			
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
			
		return xr;
	}
	//加入（同意别人的邀请）
	@Override
	public XaResult<ClubMemberVo> joinClub(Long clubEventId) throws BusinessException {
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		ClubMember obj = clubMemberRepository.findByIdAndStatusNot(clubEventId,XaConstant.Status.delete);
		if (XaUtil.isNotEmpty(obj)) {
			obj.setApplyStatus(JConstant.ApplyStatus.CHECKSUCCEED);
			obj = clubMemberRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
			xr.getObject().setId(obj.getId());
			
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
			
		return xr;
	}
	/*
	 * 邀请成员
	 */
	@Override
	public XaResult<ClubMemberVo> inviteClubMember(String modelIds,Long userId,Long clubId) 
			throws BusinessException {
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			StringBuffer sql=new StringBuffer("insert into tb_xa_clubmember (user_id,club_id,create_time,member_type,apply_status,status) values ");
			String time=XaUtil.getToDayStr();
			for(String id : ids){
				if(Long.valueOf(id)<1){
					xr.error("被邀请用户Id不能小于1！");
				}
				sql.append("("+id+","+clubId+",'"+time+"',"+JConstant.ClubRole.MEMBER+","+JConstant.ApplyStatus.INVITING+","+XaConstant.Status.valid+"),");	
				
			}
			this.insert(sql.substring(0, sql.length()-1));
					/*xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
				xr.getObject().setId(obj.getId());*/
			} else {
				throw new BusinessException(XaConstant.Message.object_not_find);
			}
			
		return xr;
	}
	//删除成员、设置管理员
	@Override
	public XaResult<ClubMemberVo> multiOperate1(String modelIds,Long clubId,
			Integer status,Integer memberType) throws BusinessException {
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			if(XaUtil.isNotEmpty(memberType)){
				String sql="update tb_xa_clubmember set member_type="+JConstant.ClubRole.MEMBER+" where status=1 and member_type<>"+JConstant.ClubRole.MINISTER+" and club_id="+clubId+" and apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+"";
				this.insert(sql);
			}
			for(String id : ids){
				ClubMember obj = clubMemberRepository.findByIdAndStatusNot(Long.parseLong(id),XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
					if(XaUtil.isNotEmpty(status)){
						obj.setStatus(status);
					}
					if(XaUtil.isNotEmpty(memberType)){
						obj.setMemberType(memberType);
					}
					obj = clubMemberRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//取消加入
	@Override
	public XaResult<ClubMemberVo> cancelJoin(Long clubId,Long userId,
			Integer status) throws BusinessException {
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		ClubMember obj = clubMemberRepository.findByUserIdAndApplyStatusAndStatusNotAndClubId(userId,JConstant.ApplyStatus.CHECKSUCCEED,XaConstant.Status.delete,clubId);
		if (XaUtil.isNotEmpty(obj)) {
			if(obj.getMemberType()==JConstant.ClubRole.MINISTER){
				xr.error("部长不能退出俱乐部！");
				return xr;
			}
			obj.setStatus(status);
			obj = clubMemberRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	
	@Override
	public XaResult<ClubMemberVo> createModel(ClubMember model)
			throws BusinessException {
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		ClubMember clubMemberobj = clubMemberRepository.findByUserIdAndStatusNotAndClubId(model.getUserId(),XaConstant.Status.delete,model.getClubId());
		if(XaUtil.isNotEmpty(clubMemberobj)){
			xr.error("已提交申请!");
			return xr;
		}else{
			ClubMember obj = clubMemberRepository.save(model);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
			xr.getObject().setId(obj.getId());
		}
		return xr;
	}
	/*
	 * 申请加入
	 * author：changlu
	 * time:2016-04-22 10:23:00
	 */
	@Override
	public XaResult<ClubMemberVo> createClubMember(ClubMember model,String clubIds)
			throws BusinessException {
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		String[] ids = clubIds.split(",");
		for(String id : ids){
			ClubMember clubMemberobj = clubMemberRepository.findByUserIdAndStatusNotAndClubId(model.getUserId(),XaConstant.Status.delete,Long.valueOf(id));
			if(XaUtil.isNotEmpty(clubMemberobj)){
				xr.error("已提交申请!");
				return xr;
			}else{
				model.setClubId(Long.valueOf(id));
				ClubMember obj = clubMemberRepository.save(model);
				xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
				xr.getObject().setId(obj.getId());
			}
		}
		return xr;
	}
	@Override
	public XaResult<ClubMemberVo> updateModel(ClubMember model)
			throws BusinessException {
		ClubMember obj = clubMemberRepository.findOne(model.getId());
		XaResult<ClubMemberVo> xr = new XaResult<ClubMemberVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setClubId(model.getClubId());
		obj.setUserId(model.getUserId());
		obj.setMemberType(model.getMemberType());
		obj.setApplyStatus(model.getApplyStatus());
			obj = clubMemberRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubMemberVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	@Override
	public XaResult<List<ClubMemberVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public XaResult<List<ClubMemberVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public XaResult<ClubMemberVo> multiOperate(String ids, Integer status)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
