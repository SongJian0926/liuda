package com.web.liuda.remote.service.impl;

import java.math.BigDecimal;
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
import com.web.liuda.business.constant.position.Location;
import com.web.liuda.business.entity.Club;
import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.business.entity.DictItem;
import com.web.liuda.business.repository.ClubMemberRepository;
import com.web.liuda.business.repository.ClubRepository;
import com.web.liuda.business.repository.DictItemRepository;
import com.web.liuda.remote.service.ApiClubService;
import com.web.liuda.remote.vo.ClubEventVo;
import com.web.liuda.remote.vo.ClubMemberVo;
import com.web.liuda.remote.vo.ClubVo;
import com.web.liuda.remote.vo.DictItemVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIClub接口实现类
 **/
@Service("ApiClubService")
@Transactional(readOnly = false)
public class ApiClubServiceImpl extends BaseService<Club> implements ApiClubService{

	@Autowired
	ClubRepository clubRepository;
	@Autowired
	ClubMemberRepository clubMemberRepository;
	@Autowired
	DictItemRepository dictItemRepository;
	/**
	 * 我创建的俱乐部
	 * author:changlu
	 * time:2016-05-30 12:20:00
	 */
	public XaResult<Page<ClubVo>> findMyCreateClubList(Long userId,Pageable pageable) throws BusinessException{
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name type,c.logo,dLevel.dict_name level,c.apply_status,c.address,c.content,c.media_path,c.area_code,interest.dict_name interest,c.type types,c.level levels,c.interest interests ");
		sql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type ");
		sql.append(" inner join tb_xa_dictitem dLevel on dLevel.id=c.level inner join tb_xa_dictitem interest on interest.id=c.interest where c.status="+XaConstant.Status.valid+" and c.user_id="+userId+"");
		sql.append(" order by c.create_time desc ");
		List<Object[]> objs=this.query(sql.toString(),pageable.getPageNumber()*pageable.getPageSize(),pageable.getPageSize() );
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type ");
		countsql.append(" inner join tb_xa_dictitem dLevel on dLevel.id=c.level inner join tb_xa_dictitem interest on interest.id=c.interest where c.status="+XaConstant.Status.valid+" and c.user_id="+userId+"");
		List<Object[]> count=this.query(countsql.toString(),null,null);
		List<ClubVo> vos=new ArrayList<ClubVo>();
		for(Object[] obj : objs){
			ClubVo vo=new ClubVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[2]);
			dictItemVo.setId(XaUtil.isEmpty(obj[11])?null:((BigInteger)obj[11]).longValue());
			vo.setClubTypeVo(dictItemVo);
			vo.setLogo((String)obj[3]);
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			clubLevelVo.setId(XaUtil.isEmpty(obj[12])?null:((BigInteger)obj[12]).longValue());
			vo.setClubLevelVo(clubLevelVo);
			vo.setApplyStatus(XaUtil.isEmpty(obj[5])?null:Integer.valueOf(obj[5]+""));
			vo.setAddress((String)obj[6]);
			vo.setContent((String)obj[7]);
			vo.setMediaPath((String)obj[8]);
			vo.setAreaCode((String)obj[9]);
			//标签信息(俱乐部兴趣标签)
			DictItemVo interestVo=new DictItemVo();
			interestVo.setDictName((String)obj[10]);
			interestVo.setId(XaUtil.isEmpty(obj[13])?null:((BigInteger)obj[13]).longValue());
			vo.setInterestVo(interestVo);
			vos.add(vo);
		}
		XaResult<Page<ClubVo>> xr=new XaResult<Page<ClubVo>>();
		//分页
		Page<ClubVo> page = new MyPage<ClubVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	/**
	 * 我加入的俱乐部
	 * author:changlu
	 * time:2016-05-30 12:20:00
	 */
	public XaResult<Page<ClubVo>> findMyJoinClubList(Long userId,Pageable pageable) throws BusinessException{
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name type,c.logo,dLevel.dict_name level ");
		sql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type ");
		sql.append(" inner join tb_xa_dictitem dLevel on dLevel.id=c.level inner join tb_xa_clubmember cm on cm.club_id=c.id");
		sql.append(" where c.status="+XaConstant.Status.valid+" and cm.user_id="+userId+" and cm.status="+XaConstant.Status.valid+" and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.member_type<>"+JConstant.ClubRole.MINISTER+" ");
		sql.append(" order by cm.create_time desc ");
		List<Object[]> objs=this.query(sql.toString(),pageable.getPageNumber()*pageable.getPageSize(),pageable.getPageSize());
		StringBuffer countsql=new StringBuffer("SELECT count(*) ");
		countsql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type ");
		countsql.append(" inner join tb_xa_dictitem dLevel on dLevel.id=c.level inner join tb_xa_clubmember cm on cm.club_id=c.id");
		countsql.append(" where c.status="+XaConstant.Status.valid+" and cm.user_id="+userId+" and cm.status="+XaConstant.Status.valid+" and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.member_type<>"+JConstant.ClubRole.MINISTER+" ");
		List<Object[]> count=this.query(countsql.toString(),null,null);
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
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			vo.setClubLevelVo(clubLevelVo);
			vos.add(vo);
		}
		
		XaResult<Page<ClubVo>> xr=new XaResult<Page<ClubVo>>();
		//分页
		Page<ClubVo> page = new MyPage<ClubVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	/**
	 * 俱乐部详情
	 * author:changlu
	 * time:2016-04-15 12:20:00
	 */
	public XaResult<ClubVo> findClubById(Long tId,Long userId) throws BusinessException {
		String sql="call pro_club_detail(?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(userId);
		params.add(tId);
		List<Object[]> objs=this.queryCall(sql, params);
		ClubVo vo = new ClubVo();
		for(Object[] obj:objs){
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[2]);
			vo.setClubTypeVo(dictItemVo);
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			vo.setClubLevelVo(clubLevelVo);
			vo.setLogo((String)obj[3]);
			vo.setLng(XaUtil.isEmpty(obj[5]) ? null :(Double.valueOf((BigDecimal)obj[5]+"")));
			vo.setLat(XaUtil.isEmpty(obj[6]) ? null :(Double.valueOf((BigDecimal)obj[6]+"")));
			vo.setAreaCode((String)obj[7]);
			vo.setContent((String)obj[8]);
			vo.setMediaPath((String)obj[9]);
			vo.setAddress((String)obj[10]);
			vo.setMemberNum(XaUtil.isEmpty(obj[11])?null:Integer.valueOf(obj[11]+""));
			vo.setEventNum(XaUtil.isEmpty(obj[12])?null:Integer.valueOf(obj[12]+""));
			//如果用户登录
			if(XaUtil.isNotEmpty(userId)){
				//是否加入该俱乐部
				vo.setMemberType(XaUtil.isEmpty(obj[13])?null:Integer.valueOf(obj[13]+""));
				//未加入
				if(XaUtil.isEmpty(obj[13])){
					vo.setIsJoin(JConstant.BooleanStatus.FALSE);
				}else{
					//已加入
					vo.setIsJoin(JConstant.BooleanStatus.TRUE);
					//是否有新成员加入(俱乐部部长和管理员可看见)
					if(Integer.valueOf(obj[13]+"")==JConstant.ClubRole.ADMIN||Integer.valueOf(obj[13]+"")==JConstant.ClubRole.MINISTER){
						vo.setNewMember(XaUtil.isEmpty(obj[14])?null:Integer.valueOf(obj[14]+""));
					}
				}
				
			}else{
				vo.setIsJoin(JConstant.BooleanStatus.FALSE);
			}
		}
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		xr.setObject(vo);
		return xr;
	}
	/**
	 * web端俱乐部详情
	 * author:changlu
	 * time:2016-04-26 19:20:00
	 */
	public XaResult<ClubVo> findClubDetailById(Long tId,Long userId) throws BusinessException {
		String sql="call pro_club_detail(?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(userId);
		params.add(tId);
		List<Object[]> objs=this.queryCall(sql, params);
		ClubVo vo = new ClubVo();
		
		for(Object[] obj:objs){
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[2]);
			vo.setClubTypeVo(dictItemVo);
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			vo.setClubLevelVo(clubLevelVo);
			vo.setLogo((String)obj[3]);
			vo.setLng(XaUtil.isEmpty(obj[5]) ? null :(Double.valueOf((BigDecimal)obj[5]+"")));
			vo.setLat(XaUtil.isEmpty(obj[6]) ? null :(Double.valueOf((BigDecimal)obj[6]+"")));
			vo.setAreaCode((String)obj[7]);
			vo.setContent((String)obj[8]);
			vo.setMediaPath((String)obj[9]);
			vo.setAddress((String)obj[10]);
			vo.setMemberNum(XaUtil.isEmpty(obj[11])?null:Integer.valueOf(obj[11]+""));
			vo.setEventNum(XaUtil.isEmpty(obj[12])?null:Integer.valueOf(obj[12]+""));
			//如果用户登录
			if(XaUtil.isNotEmpty(userId)){
				//是否加入该俱乐部
				vo.setMemberType(XaUtil.isEmpty(obj[13])?null:Integer.valueOf(obj[13]+""));
				//未加入
				if(XaUtil.isEmpty(obj[13])){
					vo.setIsJoin(JConstant.BooleanStatus.FALSE);
				}else{
					//已加入
					vo.setIsJoin(JConstant.BooleanStatus.TRUE);
					//是否有新成员加入
					if(Integer.valueOf(obj[13]+"")==JConstant.ClubRole.ADMIN||Integer.valueOf(obj[13]+"")==JConstant.ClubRole.MINISTER){
						vo.setNewMember(XaUtil.isEmpty(obj[14])?null:Integer.valueOf(obj[14]+""));
					}
				}
				
			}else{
				vo.setIsJoin(JConstant.BooleanStatus.FALSE);
			}
			
			vo.setClubMembers(findClubMemberList(tId));
			vo.setClubEvents(findClubEventList(tId));
		}
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		xr.setObject(vo);
		return xr;
	}
	//俱乐部成员
	public List<ClubMemberVo> findClubMemberList(Long clubId)
			throws BusinessException {
		StringBuffer sql=new StringBuffer("select cm.id cmId,u.id userId,cm.member_type,u.user_name,u.photo ");
		sql.append("from tb_xa_clubmember cm inner join tb_xa_user u on u.id=cm.user_id ");
		sql.append("where cm.status=1 and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.club_id="+clubId+" order by cm.member_type asc,cm.create_time desc limit 4 ");
	
		List<Object[]> objs = this.query(sql.toString(),null,null);
		
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
		return vos;
	}
	//俱乐部活动
	public List<ClubEventVo> findClubEventList(Long clubId)
				throws BusinessException {
		StringBuffer sql=new StringBuffer("select cv.id,cv.title,cv.logo,cv.starttime,cv.endtime,cv.deadline,cv.event_status,cv.max_num,cv.price ");
		sql.append(" from tb_xa_clubevent cv where cv.status="+XaConstant.Status.publish+" and cv.club_id="+clubId+" order by cv.create_time desc limit 2 ");
		List<Object[]> objs = this.query(sql.toString(), null, null);
		List<ClubEventVo> vos = new ArrayList<ClubEventVo>();
		for(Object[] obj : objs){
			ClubEventVo vo = new ClubEventVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setLogo((String)obj[2]);
			vo.setStarttime((String)obj[3]);
			vo.setEndtime((String)obj[4]);
			vo.setDeadline((String)obj[5]);
			vo.setMaxNum(XaUtil.isEmpty(obj[7])?null:Integer.valueOf(obj[7]+""));
			vo.setPrice(XaUtil.isEmpty(obj[8])?null:Double.valueOf(obj[8]+""));
			//报名中
			if(XaUtil.getToDayStr().compareTo((String)obj[5])<0){
				vo.setEventStatus(JConstant.EventStatus.ENROLL);
			}
			//未开始
			else if(XaUtil.getToDayStr().compareTo((String)obj[5])>0&&XaUtil.getToDayStr().compareTo((String)obj[3])<0){
				vo.setEventStatus(JConstant.EventStatus.NOBEGIN);
			}
			//进行中
			else if(XaUtil.getToDayStr().compareTo((String)obj[3])>0&&XaUtil.getToDayStr().compareTo((String)obj[4])<0){
				vo.setEventStatus(JConstant.EventStatus.EVENTING);
			}
			//已结束
			else if(XaUtil.getToDayStr().compareTo((String)obj[4])>0){
				vo.setEventStatus(JConstant.EventStatus.EVENTEND);
			}
			vos.add(vo);
		}
		
		return vos;
	}
	@Override
	public XaResult<ClubVo> findOne(Long tId) throws BusinessException {
		Club obj = clubRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<ClubVo> xr = new XaResult<ClubVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),ClubVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/*
	 * 查询俱乐部列表
	 * author:changlu
	 * time:2016-04-11 14:20:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 * 
	 */
	public XaResult<Page<ClubVo>> findClubList(
			Integer status, Map<String, Object> filterParams,Long type,String areaCode, Pageable pageable,Double lng, Double lat,String title)
			throws BusinessException {
		XaResult<Page<ClubVo>> xr=new XaResult<Page<ClubVo>>();
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name,c.logo,dLevel.dict_name level,c.lng,c.lat,c.area_code ");
		//用户Id不为空
		if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))){
			sql.append(",(select COUNT(*) from tb_xa_clubmember cm INNER JOIN tb_xa_user u on u.id=cm.user_id where cm.club_id=c.id and cm.user_id="+filterParams.get("EQ_userId")+" and cm.status="+XaConstant.Status.valid+" and u.status="+XaConstant.Status.valid+" and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" ) isJoin, ");
			sql.append("(select cm.member_type from tb_xa_clubmember cm INNER JOIN tb_xa_user u on u.id=cm.user_id where cm.club_id=c.id and cm.user_id="+filterParams.get("EQ_userId")+" and cm.status="+XaConstant.Status.valid+" and u.status="+XaConstant.Status.valid+" and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" ) memberType ");
		}
		sql.append(",fnGetDistance("+lng+","+lat+",c.lng,c.lat) distance ");
		sql.append("from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type inner join tb_xa_dictitem dLevel on dLevel.id=c.level ");
		sql.append(" where c.`status`="+XaConstant.Status.valid+" and c.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+"");
		//统计数据条数
		StringBuffer countsql=new StringBuffer("select count(*) from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type inner join tb_xa_dictitem dLevel on dLevel.id=c.level ");
		countsql.append("where c.`status`="+XaConstant.Status.valid+" and c.apply_status=2");
		
		List<Object> params=new ArrayList<Object>(); 
		if(XaUtil.isNotEmpty(areaCode)){
			sql.append(" and c.area_code=?");
			countsql.append(" and c.area_code=?");
			params.add(areaCode);
		}
		if(XaUtil.isNotEmpty(type)){
			sql.append(" and c.type="+type+"");
			countsql.append(" and c.type="+type+"");
		}
		if(XaUtil.isNotEmpty(title)){
			sql.append(" and c.title like ? ");
			countsql.append(" and c.title like ? ");
			params.add("%"+title+"%");
		}
		sql.append(" order by distance asc,c.create_time desc ");
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize(),params);
		List<Object[]> count = this.queryParams(countsql.toString(), null,null,params);
		List<ClubVo> vos = new ArrayList<ClubVo>();
		for(Object[] obj : objs){
			ClubVo vo = new ClubVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[2]);
			vo.setClubTypeVo(dictItemVo);
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			vo.setClubLevelVo(clubLevelVo);
			vo.setLogo((String)obj[3]);
			vo.setLng(XaUtil.isEmpty(obj[5]) ? null :(Double.valueOf((BigDecimal)obj[5]+"")));
			vo.setLat(XaUtil.isEmpty(obj[6]) ? null :(Double.valueOf((BigDecimal)obj[6]+"")));
			vo.setAreaCode((String)obj[7]);
			//如果用户登录
			if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))&&XaUtil.isNotEmpty(obj[8])){
				//是否加入该俱乐部
				vo.setIsJoin(Integer.valueOf(obj[8]+"")>=1?1:0);
				vo.setMemberType(XaUtil.isEmpty(obj[9])?null:Integer.valueOf(obj[9]+""));
			}else{
				vo.setIsJoin(JConstant.BooleanStatus.FALSE);
			}
			//根据用户经纬度计算用户到酒店的距离
			Location start = new Location();
			start.setLng(lng);
			start.setLat(lat);
			Location end = new Location();
			end.setLng(XaUtil.isEmpty(obj[4]) ? null : (Double.valueOf((BigDecimal)obj[5]+"")));
			end.setLat(XaUtil.isEmpty(obj[5]) ?null : (Double.valueOf((BigDecimal)obj[6]+"")));
			double distance = HttpURLConnectionUtil.getDistance(start, end);
			vo.setDistance(new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
			vos.add(vo);
		}
		//分页
		Page<ClubVo> page = new MyPage<ClubVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
		
	}
	/**
	 * 查询推荐俱乐部列表
	 * author:changlu
	 * time:2016-04-13 14:20:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<List<ClubVo>> findRecomendClub(Integer status, Map<String, Object> filterParams)
			throws BusinessException{
		XaResult<List<ClubVo>> xr=new XaResult<List<ClubVo>>();
		StringBuffer sql=new StringBuffer("select c.id,c.title,d.dict_name type,c.logo,dLevel.dict_name level ");
		sql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type inner join tb_xa_dictitem dLevel on dLevel.id=c.level ");
		sql.append(" where c.status="+XaConstant.Status.valid+" and c.is_recommend="+JConstant.BooleanStatus.TRUE+" and c.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" ");
		if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))&&Long.valueOf(filterParams.get("EQ_userId")+"")>0){
			sql.append(" and c.id not in (select cm.club_id from tb_xa_clubmember cm where cm.status="+XaConstant.Status.valid+" and cm.user_id="+Long.valueOf(filterParams.get("EQ_userId")+"")+" and cm.apply_status in ("+JConstant.ApplyStatus.CHECK+","+JConstant.ApplyStatus.CHECKSUCCEED+","+JConstant.ApplyStatus.INVITING+")  )");
		}
		sql.append(" order by c.create_time desc limit 6 ");
		List<Object[]> objs = this.query(sql.toString(), null, null);
		List<ClubVo> vos=new ArrayList<ClubVo>();
		for(Object[] obj : objs){
			ClubVo vo = new ClubVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[2]);
			vo.setClubTypeVo(dictItemVo);
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			vo.setClubLevelVo(clubLevelVo);
			vo.setLogo((String)obj[3]);
			vos.add(vo);
		}
		xr.setObject(vos);
		return xr;
		
	}
	
	
	/*
	 * 我的俱乐部
	 * author:changlu
	 * time:2016-04-19 10:14:00
	 */
	public XaResult<List<ClubVo>> findMyClubList(Long userId) throws BusinessException{
		List<ClubVo> vos=new ArrayList<ClubVo>();
		ClubVo vo=new ClubVo();
		vo.setMyCreateClubVos(findMyCreateClubList(userId));
		vo.setJoinClubVos(findJoinClubList(userId));
		vo.setNewClubVos(findNewClubList(userId));
		vos.add(vo);
		XaResult<List<ClubVo>> xr=new XaResult<List<ClubVo>>();
		xr.setObject(vos);
		return xr;
	}
	//我创建的俱乐部
	public List<ClubVo> findMyCreateClubList(Long userId) throws BusinessException{
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name type,c.logo,dLevel.dict_name level,c.apply_status,c.address,c.content,c.media_path,c.area_code,interest.dict_name interest,c.type types,c.level levels,c.interest interests ");
		sql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type ");
		sql.append(" inner join tb_xa_dictitem dLevel on dLevel.id=c.level inner join tb_xa_dictitem interest on interest.id=c.interest where c.status="+XaConstant.Status.valid+" and c.user_id="+userId+"");
		List<Object[]> objs=this.query(sql.toString(),null,null );
		List<ClubVo> vos=new ArrayList<ClubVo>();
		for(Object[] obj : objs){
			ClubVo vo=new ClubVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[2]);
			dictItemVo.setId(XaUtil.isEmpty(obj[11])?null:((BigInteger)obj[11]).longValue());
			vo.setClubTypeVo(dictItemVo);
			vo.setLogo((String)obj[3]);
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			clubLevelVo.setId(XaUtil.isEmpty(obj[12])?null:((BigInteger)obj[12]).longValue());
			vo.setClubLevelVo(clubLevelVo);
			vo.setApplyStatus(XaUtil.isEmpty(obj[5])?null:Integer.valueOf(obj[5]+""));
			vo.setAddress((String)obj[6]);
			vo.setContent((String)obj[7]);
			vo.setMediaPath((String)obj[8]);
			vo.setAreaCode((String)obj[9]);
			//标签信息(俱乐部兴趣标签)
			DictItemVo interestVo=new DictItemVo();
			interestVo.setDictName((String)obj[10]);
			interestVo.setId(XaUtil.isEmpty(obj[13])?null:((BigInteger)obj[13]).longValue());
			vo.setInterestVo(interestVo);
			vos.add(vo);
		}
		return vos;
	}
	
	//得到新俱乐部集合
	public List<ClubVo> findNewClubList(Long userId){
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name type,c.logo,dLevel.dict_name level,cm.id clubMemberId");
		sql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type ");
		sql.append(" inner join tb_xa_dictitem dLevel on dLevel.id=c.level inner join tb_xa_clubmember cm on cm.club_id=c.id");
		sql.append(" where c.status="+XaConstant.Status.valid+" and cm.user_id="+userId+" and cm.status="+XaConstant.Status.valid+" and cm.apply_status="+JConstant.ApplyStatus.INVITING+" ");
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
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			vo.setClubLevelVo(clubLevelVo);
			vo.setClubMemberId(XaUtil.isEmpty(obj[5])?null:((BigInteger)obj[5]).longValue());
			vos.add(vo);
		}
		
		return vos;
	}
	//用户已加入的俱乐部集合
	public List<ClubVo> findJoinClubList(Long userId){
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name type,c.logo,dLevel.dict_name level ");
		sql.append(" from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type ");
		sql.append(" inner join tb_xa_dictitem dLevel on dLevel.id=c.level inner join tb_xa_clubmember cm on cm.club_id=c.id");
		sql.append(" where c.status="+XaConstant.Status.valid+" and cm.user_id="+userId+" and cm.status="+XaConstant.Status.valid+" and cm.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" and cm.member_type<>"+JConstant.ClubRole.MINISTER+" ");
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
			//标签信息(俱乐部等级)
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName((String)obj[4]);
			vo.setClubLevelVo(clubLevelVo);
			vos.add(vo);
		}
		
		return vos;
	}
	@Override
	public XaResult<List<ClubVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Club> page = clubRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Club.class), pageable);
		XaResult<List<ClubVo>> xr = new XaResult<List<ClubVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), ClubVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<ClubVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Club> page = clubRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Club.class), pageable);
		XaResult<List<ClubVo>> xr = new XaResult<List<ClubVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), ClubVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<ClubVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<ClubVo> xr = new XaResult<ClubVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Club obj = clubRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = clubRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//创建俱乐部
	@Override
	public XaResult<ClubVo> createModel(Club model)
			throws BusinessException {
		XaResult<ClubVo> xr = new XaResult<ClubVo>();
		String sql="select di.id from tb_xa_dictitem di inner join tb_xa_dict d on d.id=di.dict_id where d.type=4 and di.status=1 and di.dict_name=? order by di.create_time desc limit 1";
		List<Object> params=new ArrayList<Object>();
		params.add("初级俱乐部");
		List<Object[]> objs=this.queryParams(sql.toString(),null,null,params );
		model.setLevel(Long.valueOf(objs.get(0)+""));
		Club obj = clubRepository.save(model);
		if(XaUtil.isNotEmpty(obj)){
			ClubMember clubMember=new ClubMember();
			clubMember.setClubId(obj.getId());
			clubMember.setApplyStatus(JConstant.ApplyStatus.CHECKSUCCEED);
			clubMember.setMemberType(JConstant.ClubRole.MINISTER);
			clubMember.setUserId(model.getUserId());
			clubMemberRepository.save(clubMember);
		}
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubVo.class));
		xr.getObject().setId(obj.getId());
		DictItem dictItem=dictItemRepository.findByIdAndStatusNot(xr.getObject().getType(),XaConstant.Status.delete);
		//标签信息(俱乐部类型)
		DictItemVo dictItemVo=new DictItemVo();
		dictItemVo.setDictName(dictItem.getDictName());
		xr.getObject().setClubTypeVo(dictItemVo);
		
		//标签信息(俱乐部等级)
		DictItem dictItem1=dictItemRepository.findByIdAndStatusNot(xr.getObject().getLevel(),XaConstant.Status.delete);
		DictItemVo clubLevelVo=new DictItemVo();
		clubLevelVo.setDictName(dictItem1.getDictName());
		xr.getObject().setClubLevelVo(clubLevelVo);
		
		return xr;
	}
	/**
	 * 修改俱乐部信息
	 * author:changlu
	 * time:2016-05-04 10:38:00
	 */
	@Override
	public XaResult<ClubVo> updateModel(Club model)
			throws BusinessException {
		Club obj = clubRepository.findOne(model.getId());
		String sql="select di.id from tb_xa_dictitem di inner join tb_xa_dict d on d.id=di.dict_id where d.type=4 and di.status=1 and di.dict_name=? order by di.create_time desc limit 1";
		List<Object> params=new ArrayList<Object>();
		params.add("初级俱乐部");
		List<Object[]> objs=this.queryParams(sql.toString(),null,null,params );
		model.setLevel(Long.valueOf(objs.get(0)+""));
		XaResult<ClubVo> xr = new XaResult<ClubVo>();
		if (XaUtil.isNotEmpty(obj)) {
			obj.setTitle(model.getTitle());
			obj.setLogo(model.getLogo());
			obj.setType(model.getType());
			obj.setLevel(model.getLevel());
			obj.setAddress(model.getAddress());
			obj.setSort(model.getSort());
			obj.setLng(model.getLng());
			obj.setLat(model.getLat());
			obj.setContent(model.getContent());
			obj.setMediaPath(model.getMediaPath());
			obj.setAreaCode(model.getAreaCode());
			obj.setApplyStatus(model.getApplyStatus());
			obj = clubRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubVo.class));
			xr.getObject().setId(obj.getId());
			DictItem dictItem=dictItemRepository.findByIdAndStatusNot(xr.getObject().getType(),XaConstant.Status.delete);
			//标签信息(俱乐部类型)
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName(dictItem.getDictName());
			xr.getObject().setClubTypeVo(dictItemVo);
			
			//标签信息(俱乐部等级)
			DictItem dictItem1=dictItemRepository.findByIdAndStatusNot(xr.getObject().getLevel(),XaConstant.Status.delete);
			DictItemVo clubLevelVo=new DictItemVo();
			clubLevelVo.setDictName(dictItem1.getDictName());
			xr.getObject().setClubLevelVo(clubLevelVo);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
