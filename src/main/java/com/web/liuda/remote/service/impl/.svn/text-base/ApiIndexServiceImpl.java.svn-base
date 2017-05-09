package com.web.liuda.remote.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Hotel;
import com.web.liuda.remote.service.ApiIndexService;
import com.web.liuda.remote.vo.ClubVo;
import com.web.liuda.remote.vo.DictItemVo;
import com.web.liuda.remote.vo.GuideAppendVo;
import com.web.liuda.remote.vo.GuideVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.liuda.remote.vo.IndexVo;
import com.web.liuda.remote.vo.IndexVo.GroupBuyVo;
import com.web.liuda.remote.vo.MacthVo;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.TouristVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端ApiIndex接口实现类
 **/
@Service("ApiIndexService")
@Transactional(readOnly = false)
public class ApiIndexServiceImpl extends BaseService<Hotel> implements ApiIndexService{

	@Override
	public XaResult<IndexVo> getIndexInfo(Double lng,Double lat) {
		XaResult<IndexVo> xr = new XaResult<IndexVo>();
		String sql = "SELECT * from v_indexInfo";
		List<Object[]> objs = this.query(sql, null, null);
		IndexVo index = new IndexVo();
		List<HotelVo> hotels = new ArrayList<HotelVo>();
		List<TouristVo> tourists = new ArrayList<TouristVo>();
		List<ShopVo> shops = new ArrayList<ShopVo>();
		//查询团购
		//String groupSql = "call pro_groupInfo()";
		
		List<GroupBuyVo> buys = new ArrayList<GroupBuyVo>();
		
		String touristgroupSql = "call pro_touristgroupInfo()";
		List<Object[]> touristgroups = this.queryCall(touristgroupSql.toString(), null);
		
		for(Object[] obj : touristgroups){
			GroupBuyVo buy = new GroupBuyVo();
			buy.setId(((BigInteger)obj[0]).longValue());
			buy.setImgUrl((String)obj[1]);
			buy.setTitle((String)obj[2]);
			buy.setType(XaUtil.isEmpty(obj[3]) ? "" : Integer.valueOf(obj[3]+"") + "");
			buy.setPrice((Double)obj[4]);
			buys.add(buy);
		}
		String groupSql = "call pro_hotelgroupInfo()";
		List<Object[]> groups = this.queryCall(groupSql.toString(), null);
		for(Object[] obj : groups){
			GroupBuyVo buy = new GroupBuyVo();
			buy.setId(((BigInteger)obj[0]).longValue());
			buy.setImgUrl((String)obj[1]);
			buy.setTitle((String)obj[2]);
			buy.setType(XaUtil.isEmpty(obj[3]) ? "" : Integer.valueOf(obj[3]+"") + "");
			buy.setPrice((Double)obj[4]);
			buys.add(buy);
		}
		String shopgroupSql = "call pro_shopgroupInfo()";
		List<Object[]> shopgroups = this.queryCall(shopgroupSql.toString(), null);
		
		for(Object[] obj : shopgroups){
			GroupBuyVo buy = new GroupBuyVo();
			buy.setId(((BigInteger)obj[0]).longValue());
			buy.setImgUrl((String)obj[1]);
			buy.setTitle((String)obj[2]);
			buy.setType(XaUtil.isEmpty(obj[3]) ? "" : Integer.valueOf(obj[3]+"") + "");
			buy.setPrice((Double)obj[4]);
			buys.add(buy);
		}
		
		index.setGroups(buys);
		for(Object[] obj : objs){
			if(((BigInteger)obj[3]).intValue() == JConstant.ObjectType.HOTEL){
				HotelVo hotel = new HotelVo();
				hotel.setId(((BigInteger)obj[0]).longValue());
				hotel.setPicurl((String)obj[1]);
				hotel.setHotelName((String)obj[2]);
				hotel.setPrice(XaUtil.isEmpty(obj[4]) ? 0 : (Double)obj[4]);
				hotel.setAddress((String)obj[5]);
				hotels.add(hotel);
			}else if(((BigInteger)obj[3]).intValue() == JConstant.ObjectType.TOURIST){
				TouristVo tourist = new TouristVo();
				tourist.setId(((BigInteger)obj[0]).longValue());
				tourist.setImgUrl((String)obj[1]);
				tourist.setTouristName((String)obj[2]);
				tourist.setPrice(XaUtil.isEmpty(obj[4]) ? 0 : (Double)obj[4]);
				tourist.setAddress((String)obj[5]);
				tourists.add(tourist);
			}else if(((BigInteger)obj[3]).intValue() == JConstant.ObjectType.SHOP){
				ShopVo shop = new ShopVo();
				shop.setId(((BigInteger)obj[0]).longValue());
				shop.setImgUrl((String)obj[1]);
				shop.setShopName((String)obj[2]);
				shop.setPrice(XaUtil.isEmpty(obj[4]) ? 0 : (Double)obj[4]);
				shops.add(shop);
			}
		}
		index.setHotels(hotels);
		index.setTourists(tourists);
		index.setShops(shops);
		index.setMatchs(getIndexMatch());
		index.setClubs(getIndexClub());
		index.setGuides(getIndexGuide());
		xr.setObject(index);
		return xr;
	}
	/*
	 * 首页攻略内容
	 * author：changlu
	 * time:2016-04-21 12:00:00
	 */
	public List<GuideVo> getIndexGuide() {
		StringBuffer sql=new StringBuffer("select g.id,g.title,g.last_update,u.user_name,");
		sql.append("g.type,d.dict_name,");
		sql.append("(select ga.content from tb_xa_guideappend ga where ga.guide_id=g.id and status=1 order by create_time limit 1) content,");
		sql.append("(select ga.media_path from tb_xa_guideappend ga where ga.guide_id=g.id and status=1 and ga.media_path is not NULL order by create_time limit 1) media_path,g.pageview ");
		sql.append("from tb_xa_guide g ");
		sql.append("inner join tb_xa_user u on u.id=g.user_id ");
		sql.append("inner join tb_xa_dictitem d on d.id=g.dict_item_id ");
		sql.append("where g.status="+XaConstant.Status.publish+"");
		sql.append(" order by g.last_update desc limit 2 ");
		
		List<Object[]> objs = this.query(sql.toString(), null,null);
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
	/*
	 * 首页赛事内容
	 * author：changlu
	 * time:2016-04-21 12:23:00
	 */
	public List<MacthVo> getIndexMatch() {
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,c.media_path,c.logo,c.startdate,c.enddate,c.match_status,c.price from tb_xa_macth c where status="+XaConstant.Status.publish+" ");
		sql.append(" order by c.create_time desc limit 1 ");
		List<Object[]> objs = this.query(sql.toString(), null, null);
		List<MacthVo> vos = new ArrayList<MacthVo>();
		for(Object[] obj : objs){
			MacthVo vo = new MacthVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setMediaPath((String)obj[2]);
			vo.setLogo((String)obj[3]);
			vo.setStartdate((String)obj[4]);
			vo.setEnddate((String)obj[5]);
			vo.setMatchStatus((Integer)obj[6]);
			vo.setPrice(XaUtil.isEmpty(obj[7])?null:Double.valueOf(obj[7]+""));
			vos.add(vo);
		}
		return vos;
	}
	/*
	 * 首页江湖内容
	 * author：changlu
	 * time:2016-04-21 12:43:00
	 */
	public List<ClubVo> getIndexClub() {
		StringBuffer sql=new StringBuffer("SELECT c.id,c.title,d.dict_name,c.logo,dLevel.dict_name level,c.lng,c.lat,c.area_code ");
		sql.append("from tb_xa_club c inner join tb_xa_dictitem d on d.id=c.type inner join tb_xa_dictitem dLevel on dLevel.id=c.level ");
		sql.append(" where c.`status`="+XaConstant.Status.valid+" and c.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+"");
		
		sql.append(" order by c.create_time desc limit 3 ");
		List<Object[]> objs = this.query(sql.toString(),null,null);
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
			vos.add(vo);
		}
		return vos;
	}
}
