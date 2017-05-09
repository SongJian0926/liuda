package com.web.liuda.remote.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Shop;
import com.web.liuda.business.entity.Standard;
import com.web.liuda.business.repository.ShopRepository;
import com.web.liuda.business.repository.StandardRepository;
import com.web.liuda.remote.service.ApiShopService;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.StandardVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: yanghui
 * @times: 2015-12-30下午12:43:00
 * 类的说明：前端APIShop接口实现类
 **/
@Service("ApiShopService")
@Transactional(readOnly = false)
public class ApiShopServiceImpl extends BaseService<Shop> implements ApiShopService{

	@Autowired
	ShopRepository shopRepository;

	@Autowired
	StandardRepository standardRepository;
	
	@Override
	public XaResult<Page<ShopVo>> getShops(Integer nextPage, Integer pageSize,
			String shopName, String sort, String sortType,Integer groupBuy) {
		XaResult<Page<ShopVo>> xr = new XaResult<Page<ShopVo>>();
		StringBuffer sql = new StringBuffer();
		StringBuffer countSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//获取本月的第一天时间
		Calendar c = Calendar.getInstance();   
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstTime = format.format(c.getTime())+" 00:00:00";
		/*sql.append("select * from (select s.id,s.shop_name,");
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) price,s.img_url,");
		countSql.append("select count(*) from (select s.id,s.shop_name,");
		countSql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) price,s.img_url,");
		sql.append("(select sum(sr.buy_number) from tb_xa_shoprecord sr where sr.shop_id=s.id and sr.status=").append(XaConstant.Status.valid);
		sql.append(" and sr.create_time between ").append("'");
		sql.append(firstTime).append("'").append(" and '").append(XaUtil.getToDayStr()).append("'");
		sql.append(" GROUP BY sr.shop_id) shopSales");
		sql.append(",(select count(*) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupBuy,");
		
		countSql.append("(select sum(sr.buy_number) from tb_xa_shoprecord sr where sr.shop_id=s.id and sr.status=").append(XaConstant.Status.valid);
		countSql.append(" and sr.create_time between ").append("'");
		countSql.append(firstTime).append("'").append(" and '").append(XaUtil.getToDayStr()).append("'");
		countSql.append(" GROUP BY sr.shop_id) shopSales");
		countSql.append(",(select count(*) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupBuy,");
		
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_price=(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE())) price1,");
		sql.append("(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupPrice");
		sql.append(",case WHEN (select min(a.group_price) from tb_xa_standard a where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) is not null THEN ");
		sql.append("(select min(a.group_price) from tb_xa_standard a ");
		sql.append(" where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) ");
		sql.append(" ELSE (select min(sd.price) from tb_xa_standard sd where sd.status=1 and sd.shop_id=s.id) end orderPrice ");
		sql.append(" from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		
		sql.append(",case WHEN (select min(a.group_price) from tb_xa_standard a where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) <= (select min(sd.price) from tb_xa_standard sd where sd.status=1 and sd.shop_id=s.id) ");
		sql.append(" THEN (select min(a.group_price) from tb_xa_standard a ");
		sql.append(" where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) ");
		sql.append(" ELSE (select min(sd.price) from tb_xa_standard sd where sd.status=1 and sd.shop_id=s.id) end orderPrice ");
		sql.append(" from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		
		countSql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_price=(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE())) price1,");
		countSql.append("(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupPrice");
		countSql.append(",case WHEN (select min(a.group_price) from tb_xa_standard a where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) <= (select min(sd.price) from tb_xa_standard sd where sd.status=1 and sd.shop_id=s.id) ");
		countSql.append(" THEN (select min(a.group_price) from tb_xa_standard a ");
		countSql.append(" where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) ");
		countSql.append(" ELSE (select min(sd.price) from tb_xa_standard sd where sd.status=1 and sd.shop_id=s.id) end orderPrice ");
		
		countSql.append(" from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		
		//countSql.append(" from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		if(XaUtil.isNotEmpty(shopName)){
			sql.append(" and s.shop_name like ?");
			countSql.append(" and s.shop_name like ?");
			params.add("%" + shopName + "%");
		}
		if(sort.equals("price")){
			sort="orderPrice";
		}
		sql.append(" order by ").append(sort).append(" ").append(sortType).append(") t");
		countSql.append(" order by ").append(sort).append(" ").append(sortType).append(") t");
		//countSql.append(") t");
		if(XaUtil.isNotEmpty(groupBuy)){
			if(groupBuy == JConstant.BooleanStatus.FALSE){
				sql.append(" where t.groupBuy=").append(groupBuy);
				countSql.append(" where t.groupBuy=").append(groupBuy);
			}else{
				sql.append(" where t.groupBuy >=").append(groupBuy);
				countSql.append(" where t.groupBuy >=").append(groupBuy);
			}
		}*/
        sql.append("select * from (select s.id,s.shop_name,");
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) price,s.img_url,");
		countSql.append("select count(*) from (select s.id,s.shop_name,");
		countSql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) price,s.img_url,");
		//countSql.append("select count(*) from (select s.id,s.shop_name,s.price,s.img_url,");
		sql.append("(select sum(sr.buy_number) from tb_xa_shoprecord sr where sr.shop_id=s.id and sr.status=").append(XaConstant.Status.valid);
		sql.append(" and sr.create_time between ").append("'");
		sql.append(firstTime).append("'").append(" and '").append(XaUtil.getToDayStr()).append("'");
		sql.append(" GROUP BY sr.shop_id) shopSales");
		sql.append(",(select count(*) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupBuy,");
		
		countSql.append("(select sum(sr.buy_number) from tb_xa_shoprecord sr where sr.shop_id=s.id and sr.status=").append(XaConstant.Status.valid);
		countSql.append(" and sr.create_time between ").append("'");
		countSql.append(firstTime).append("'").append(" and '").append(XaUtil.getToDayStr()).append("'");
		countSql.append(" GROUP BY sr.shop_id) shopSales");
		countSql.append(",(select count(*) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupBuy,");
		
		//countSql.append("(select count(*) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(") groupBuy ");
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_price=(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE())) price1,");
		sql.append("(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupPrice");
		sql.append(",case WHEN (select min(a.group_price) from tb_xa_standard a where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) is not null THEN ");
		sql.append("(select min(a.group_price) from tb_xa_standard a ");
		sql.append(" where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) ");
		sql.append(" ELSE (select min(sd.price) from tb_xa_standard sd where sd.status=1 and sd.shop_id=s.id) end orderPrice ");
		sql.append(" from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		
		countSql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_price=(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE())) price1,");
		countSql.append("(select min(a.group_price) from tb_xa_standard a where a.status=").append(XaConstant.Status.valid).append(" and a.shop_id=s.id and a.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and a.validity>=CURDATE()) groupPrice");
		countSql.append(",case WHEN (select min(a.group_price) from tb_xa_standard a where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) is not null THEN ");
		countSql.append("(select min(a.group_price) from tb_xa_standard a ");
		countSql.append(" where a.status=1 and a.shop_id=s.id and a.group_buy=1 and a.validity>=CURDATE()) ");
		countSql.append(" ELSE (select min(sd.price) from tb_xa_standard sd where sd.status=1 and sd.shop_id=s.id) end orderPrice ");
		countSql.append(" from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		
		//countSql.append(" from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		if(XaUtil.isNotEmpty(shopName)){
			sql.append(" and s.shop_name like ?");
			countSql.append(" and s.shop_name like ?");
			params.add("%" + shopName + "%");
		}
		if(sort.equals("price")){
			sort="orderPrice";
		}
		sql.append(" order by ").append(sort).append(" ").append(sortType).append(") t");
		countSql.append(" order by ").append(sort).append(" ").append(sortType).append(") t");
		//countSql.append(") t");
		if(XaUtil.isNotEmpty(groupBuy)){
			if(groupBuy == JConstant.BooleanStatus.FALSE){
				sql.append(" where t.groupBuy=").append(groupBuy);
				countSql.append(" where t.groupBuy=").append(groupBuy);
			}else{
				sql.append(" where t.groupBuy >=").append(groupBuy);
				countSql.append(" where t.groupBuy >=").append(groupBuy);
			}
		}
		List<Object[]> objs = this.queryParams(sql.toString(), nextPage * pageSize, pageSize, params);
		List<Object[]> count = this.queryParams(countSql.toString(), null, null, params);
		List<ShopVo> vos = new ArrayList<ShopVo>();
		for(Object[] obj : objs){
			ShopVo vo = new ShopVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setShopName((String)obj[1]);
			DecimalFormat df = new DecimalFormat("#.00"); 
			vo.setPrice(XaUtil.isEmpty(obj[2]) ? 0 : Double.valueOf(df.format((Double)obj[2])));
			vo.setImgUrl((String)obj[3]);
			vo.setShopSales(XaUtil.isEmpty(obj[4]) ? 0 : new BigDecimal(obj[4] + "").intValue());
			//团购信息
			if(((BigInteger)obj[5]).intValue() > 0){
				vo.setGroupBuy(JConstant.BooleanStatus.TRUE);
				vo.setGroupPrice(XaUtil.isEmpty(obj[7]) ? null : Double.valueOf(df.format((Double)obj[7])));
				if(XaUtil.isNotEmpty(obj[6])){
					//返回一条团购价格最低的商品信息
					vo.setPrice(XaUtil.isEmpty(obj[6]) ? 0 : Double.valueOf(df.format((Double)obj[6])));
				}
			}else{
				vo.setGroupBuy(JConstant.BooleanStatus.FALSE);
			}
			vos.add(vo);
		}
		Page<ShopVo> page = new MyPage<ShopVo>(nextPage, pageSize, vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	
	@Override
	public XaResult<ShopVo> findShopDetail(Long ShopId,Long userId) {
		//获取本月的第一天时间
		Calendar c = Calendar.getInstance();   
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstTime = format.format(c.getTime())+" 00:00:00";
		XaResult<ShopVo> xr = new XaResult<ShopVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("select s.id,s.shop_name,");
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) price,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.SHOP).append(" and i.object_id=").append(ShopId).append(") pics,");
		sql.append("(select sum(sr.buy_number) from tb_xa_shoprecord sr where sr.shop_id=").append(ShopId).append(" and sr.status=").append(XaConstant.Status.valid);
		sql.append(" and sr.create_time between ").append("'");
		sql.append(firstTime).append("'").append(" and '").append(XaUtil.getToDayStr()).append("'");
		sql.append(") shopSales,");
		sql.append("s.shop_desc,(select count(*) from tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.object_id=s.id and c.status=").append(XaConstant.Status.valid);
		sql.append(" and c.type=").append(JConstant.ObjectType.SHOP).append(" and u.status=").append(XaConstant.Status.valid).append(") shopComments,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.SHOP_INTRO).append(" and i.object_id=").append(ShopId).append(") picsIntro,");
		if(XaUtil.isNotEmpty(userId)){
			sql.append("(select COUNT(*) from tb_xa_history his where his.user_id=").append(userId).append(" and his.status=1 and his.type=2 and his.object_id=").append(ShopId).append(") isLike, ");
		}
		sql.append("(select min(sd.group_price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_buy="+JConstant.BooleanStatus.TRUE+" and sd.validity>=CURDATE()) groupprice, ");
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_price=(select min(sd.group_price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) and sd.group_buy="+JConstant.BooleanStatus.TRUE+" and sd.validity>=CURDATE()) price1,");
		sql.append("(select sd.group_buy from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.validity>=CURDATE() group by s.id) groupBuy,s.img_url,s.limit_number ");
		sql.append("from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		sql.append(" and s.id=").append(ShopId);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		ShopVo vo = new ShopVo();
		//判断商品是否存在
		if(XaUtil.isEmpty(objs)){
			xr.error("商品已下架！");
			return xr;
		}
		for(Object[] obj : objs){
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setShopName((String)obj[1]);
			DecimalFormat df = new DecimalFormat("#.00"); 
			vo.setPrice(XaUtil.isEmpty(obj[2]) ? 0 : Double.valueOf(df.format((Double)obj[2])));
			vo.setPics1(XaUtil.isEmpty(obj[3]) ? null : ((String)obj[3]).split(","));
			vo.setShopSales(XaUtil.isEmpty(obj[4]) ? 0 : new BigDecimal(obj[4] + "").intValue());
			vo.setShopDesc((String)obj[5]);
			vo.setShopComments(XaUtil.isEmpty(obj[6]) ? 0 : ((BigInteger)obj[6]).intValue());
			vo.setImgUrlpics(XaUtil.isEmpty(obj[7]) ? null : ((String)obj[7]).split(","));
			List<Standard> standard = standardRepository.findByShopIdAndStatus(ShopId, XaConstant.Status.valid);
			List<StandardVo> standardVo = new ArrayList<StandardVo>();
			for(Standard s : standard){
				StandardVo svo=new StandardVo();
				svo.setId(s.getId());
				svo.setImgUrl(s.getImgUrl());
				svo.setPorperty(s.getPorperty());
				svo.setPrice(s.getPrice());
				String aa=XaUtil.getToDayStr().substring(0, 10); 
				if(XaUtil.isNotEmpty(s.getValidity())&&s.getValidity().compareTo(aa)<0){
					svo.setGroupBuy(0);
				}else{
					svo.setGroupPrice(s.getGroupPrice());
					svo.setGroupBuy(s.getGroupBuy());
				}
				
				svo.setShopId(s.getShopId());
				svo.setStocks(s.getStocks());
				standardVo.add(svo);
			}
			vo.setStandards(standardVo);
			vo.setIsLike(Integer.valueOf(obj[8].toString()));
			vo.setGroupPrice(XaUtil.isEmpty(obj[9]) ? null : Double.valueOf(df.format((Double)obj[9])));
			vo.setGroupBuy((Integer)obj[11]);
			vo.setImgUrl((String)obj[12]);
			vo.setLimitNumber((Integer)obj[13]);
		}
		xr.setObject(vo);
		return xr;
	}
	@Override
	public XaResult<ShopVo> findShopDetail(Long ShopId) {
		//获取本月的第一天时间
		Calendar c = Calendar.getInstance();   
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstTime = format.format(c.getTime())+" 00:00:00";
		XaResult<ShopVo> xr = new XaResult<ShopVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("select s.id,s.shop_name,");
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) price,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.SHOP).append(" and i.object_id=").append(ShopId).append(") pics,");
		sql.append("(select sum(sr.buy_number) from tb_xa_shoprecord sr where sr.shop_id=").append(ShopId).append(" and sr.status=").append(XaConstant.Status.valid);
		sql.append(" and sr.create_time between ").append("'");
		sql.append(firstTime).append("'").append(" and '").append(XaUtil.getToDayStr()).append("'");
		sql.append(") shopSales,");
		sql.append("s.shop_desc,(select count(*) from tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.object_id=s.id and c.status=").append(XaConstant.Status.valid);
		sql.append(" and c.type=").append(JConstant.ObjectType.SHOP).append(" and u.status=").append(XaConstant.Status.valid).append(") shopComments,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.SHOP_INTRO).append(" and i.object_id=").append(ShopId).append(") picsIntro, ");
		sql.append("(select min(sd.group_price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_buy="+JConstant.BooleanStatus.TRUE+" and sd.validity>=CURDATE()) groupprice, ");
		sql.append("(select min(sd.price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.group_price=(select min(sd.group_price) from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id) and sd.group_buy="+JConstant.BooleanStatus.TRUE+" and sd.validity>=CURDATE()) price1,");
		sql.append("(select sd.group_buy from tb_xa_standard sd where sd.status=").append(XaConstant.Status.valid).append(" and sd.shop_id=s.id and sd.validity>=CURDATE() group by s.id) groupBuy,s.img_url,s.limit_number ");
		sql.append("from tb_xa_shop s where s.status=").append(XaConstant.Status.publish);
		sql.append(" and s.id=").append(ShopId);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		//判断商品是否存在
		if(XaUtil.isEmpty(objs)){
			xr.error("商品已下架！");
			return xr;
		}
		ShopVo vo = new ShopVo();
		for(Object[] obj : objs){
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setShopName((String)obj[1]);
			DecimalFormat df = new DecimalFormat("#.00"); 
			vo.setPrice(XaUtil.isEmpty(obj[2]) ? 0 : Double.valueOf(df.format((Double)obj[2])));
			vo.setPics1(XaUtil.isEmpty(obj[3]) ? null : ((String)obj[3]).split(","));
			vo.setShopSales(XaUtil.isEmpty(obj[4]) ? 0 : new BigDecimal(obj[4] + "").intValue());
			vo.setShopDesc((String)obj[5]);
			vo.setShopComments(XaUtil.isEmpty(obj[6]) ? 0 : ((BigInteger)obj[6]).intValue());
			vo.setImgUrlpics(XaUtil.isEmpty(obj[7]) ? null : ((String)obj[7]).split(","));
			List<Standard> standard = standardRepository.findByShopIdAndStatus(ShopId, XaConstant.Status.valid);
			List<StandardVo> standardVo = new ArrayList<StandardVo>();
			for(Standard s : standard){
				StandardVo svo=new StandardVo();
				svo.setId(s.getId());
				svo.setImgUrl(s.getImgUrl());
				svo.setPorperty(s.getPorperty());
				svo.setPrice(s.getPrice());
				
				String aa=XaUtil.getToDayStr().substring(0, 10); 
				if(XaUtil.isNotEmpty(s.getValidity())&&s.getValidity().compareTo(aa)<0){
					svo.setGroupBuy(0);
				}else{
					svo.setGroupPrice(s.getGroupPrice());
					svo.setGroupBuy(s.getGroupBuy());
				}
				svo.setShopId(s.getShopId());
				svo.setStocks(s.getStocks());
				standardVo.add(svo);
			}
			vo.setStandards(standardVo);
			vo.setIsLike(0);
			vo.setGroupPrice(XaUtil.isEmpty(obj[8]) ? null : Double.valueOf(df.format((Double)obj[8])));
			vo.setGroupBuy((Integer)obj[10]);
			vo.setImgUrl((String)obj[11]);
			vo.setLimitNumber((Integer)obj[12]);
		}
		xr.setObject(vo);
		return xr;
	}
	@Override
	public XaResult<ShopVo> findOne(Long id) throws BusinessException {
		return null;
	}

	@Override
	public XaResult<List<ShopVo>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XaResult<List<ShopVo>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XaResult<ShopVo> createModel(Shop model) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XaResult<ShopVo> updateModel(Shop model) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XaResult<ShopVo> multiOperate(String ids, Integer status)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
