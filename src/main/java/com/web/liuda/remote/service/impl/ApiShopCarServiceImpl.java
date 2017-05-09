package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.ShopCar;
import com.web.liuda.business.repository.ShopCarRepository;
import com.web.liuda.remote.service.ApiShopCarService;
import com.web.liuda.remote.vo.ShopCarVo;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.StandardVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIShopCar接口实现类
 **/
@Service("ApiShopCarService")
@Transactional(readOnly = false)
public class ApiShopCarServiceImpl extends BaseService<ShopCar> implements ApiShopCarService{

	@Autowired
	ShopCarRepository shopCarRepository;

	@Override
	public XaResult<Page<ShopCarVo>> findMyCar(Long userId,Integer begin,Integer count) {
		XaResult<Page<ShopCarVo>> xr = new XaResult<Page<ShopCarVo>>();
		List<Object> params = new ArrayList<Object>();	//参数集合
		StringBuffer sql = new StringBuffer();	//列表查询语句
		StringBuffer countSql = new StringBuffer();	//数据统计语句
		sql.append("SELECT c.id,c.create_time,c.shop_number,s.id shopId,s.shop_name,d.price,");
		sql.append("(select d.group_buy from tb_xa_standard d where d.id=c.standard_id and d.status="+XaConstant.Status.valid+" and ");
		sql.append("d.validity>=CURDATE()) group_buy,");
		sql.append("(select d.group_price from tb_xa_standard d where d.id=c.standard_id and d.status="+XaConstant.Status.valid+" and ");
		sql.append("d.validity>=CURDATE() and d.group_buy="+JConstant.BooleanStatus.TRUE+" ) group_price,");
		sql.append("d.id standardId,d.porperty,d.img_url,d.stocks,s.limit_number ");
		sql.append("FROM tb_xa_shopcar c INNER JOIN tb_xa_shop s on c.shop_id=s.id ");
		sql.append("INNER JOIN tb_xa_standard d on c.standard_id=d.id ");
		sql.append("where c.status=").append(XaConstant.Status.valid);
		sql.append(" and c.user_id=? order by c.create_time desc");
		countSql.append("select count(*) FROM tb_xa_shopcar c INNER JOIN tb_xa_shop s on c.shop_id=s.id ");
		countSql.append("INNER JOIN tb_xa_standard d on c.standard_id=d.id ");
		countSql.append("where c.status=").append(XaConstant.Status.valid);
		countSql.append(" and c.user_id=?");
		params.add(userId);	//添加查询条件
		//查询的列表集合
		List<Object[]> objs = this.queryParams(sql.toString(), begin * count, count, params);
		//查询的总条数
		List<Object[]> c = this.queryParams(countSql.toString(), null, null, params);
		//数据结果集
		List<ShopCarVo> cars = new ArrayList<ShopCarVo>();
		for(Object[] obj : objs){
			//购物车信息
			ShopCarVo vo = new ShopCarVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setCreateTime((String)obj[1]);
			vo.setShopNumber((Integer)obj[2]);
			//商品信息
			ShopVo shop = new ShopVo();
			shop.setId(((BigInteger)obj[3]).longValue());
			shop.setShopName((String)obj[4]);
			shop.setPrice(XaUtil.isEmpty(obj[5]) ? 0 : (Double)obj[5]);
			if(XaUtil.isNotEmpty(obj[7])){
				shop.setTotalPrice((Integer)obj[2] * (Double)obj[7]);
			}else{
				shop.setTotalPrice((Integer)obj[2] * (Double)obj[5]);
			}
			
			shop.setGroupBuy((Integer)obj[6]);
			shop.setGroupPrice(XaUtil.isEmpty(obj[7]) ? 0 : (Double)obj[7]);
			shop.setLimitNumber(XaUtil.isEmpty(obj[12]) ? null : Integer.valueOf(obj[12]+""));
			//商品规格属性
			StandardVo standard = new StandardVo();
			standard.setId(((BigInteger)obj[8]).longValue());
			standard.setPorperty((String)obj[9]);
			standard.setImgUrl((String)obj[10]);
			standard.setStocks((Integer)obj[11]);
			shop.setStandard(standard);
			vo.setShop(shop);
			cars.add(vo);
		}
		Page<ShopCarVo> page = new MyPage<ShopCarVo>(begin, count, cars, Integer.valueOf(c.get(0)+""));
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<ShopCarVo> addCar(ShopCar shopCar) {
		XaResult<ShopCarVo> xr = new XaResult<ShopCarVo>();
		ShopCar obj = shopCarRepository.findByShopIdAndStandardIdAndUserIdAndStatusNot
				(shopCar.getShopId(),shopCar.getStandardId(),shopCar.getUserId(),XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(obj)){
			obj.setShopNumber(obj.getShopNumber()+shopCar.getShopNumber());
			obj.setId(obj.getId());
			obj = shopCarRepository.save(obj);
		}else{
			 obj = shopCarRepository.save(shopCar);
		}
		
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ShopCarVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<List<ShopCarVo>> modifyCar(String shopCar) {
		XaResult<List<ShopCarVo>> xr = new XaResult<List<ShopCarVo>>();
		List<ShopCarVo> cars = null;
		try{
			//把字符串转化为购物车集合
			cars = JSON.parseArray(shopCar, ShopCarVo.class);
			if(XaUtil.isNotEmpty(cars)){
				//循环集合修改数量
				for(ShopCarVo vo : cars){
					List<Object> params = new ArrayList<Object>();
					String sql = "update tb_xa_shopcar set shop_number=? where id=?";
					params.add(vo.getShopNumber());
					params.add(vo.getId());
					this.delete(sql, params);
				}
				xr.setObject(cars);
			}
		}catch(Exception e){
			xr.error("json格式错误");
			return xr;
		}
		return xr;
	}
	
	@Override
	public XaResult<ShopCarVo> deleteCar(String ids,Long userId) {
		XaResult<ShopCarVo> xr=new XaResult<ShopCarVo>();
		String sql="delete from tb_xa_shopcar where user_id=? and id in (";
		String[] modelId = ids.replaceAll("，", ",").split(",");
		for(String l : modelId){
			sql+=l;
			if((modelId[modelId.length-1])!=l){
				sql+=",";
			}
		}
		sql+=")";
		List<Object> params=new ArrayList<Object>(); 				   
	    params.add(userId);		
	    this.delete(sql, params);
	    return xr;
		
	}
}
