package com.web.liuda.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Shop;
import com.web.liuda.business.entity.Standard;
import com.web.liuda.business.repository.ShopRepository;
import com.web.liuda.business.repository.StandardRepository;
import com.web.liuda.business.service.ImagesService;
import com.web.liuda.business.service.ShopService;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.StandardVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("ShopService")
@Transactional(readOnly = false)
public class ShopServiceImpl extends BaseService<ShopVo> implements ShopService{

	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private StandardRepository standardIdRepository;
	@Autowired
	private ImagesService imageService;
	/**
	 * 分页查询状态非status的Shop数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Shop集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ShopVo>> findShopList(Integer status,
			Map<String, Object> filterParams, Pageable pageable) {
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		XaResult<Page<ShopVo>> xr=new XaResult<Page<ShopVo>>();
		List<ShopVo> shopVoList = new ArrayList<ShopVo>();
		StringBuffer sql= new StringBuffer("");
		sql.append("select s.img_url,s.price,s.shop_desc,s.shop_name,sr.porperty,sr.stocks,s.id,sr.id standardId,s.`status` from tb_xa_shop s LEFT JOIN tb_xa_standard sr on  s.id=sr.shop_id where s.`status`<>3 ");
		//获取查询条件 map集合的遍历
				Set<Entry<String,Object>> enties= filterParams.entrySet();
				for(Entry<String,Object> entry:enties){
					String key=entry.getKey();
					Object value = entry.getValue();
					if(!XaUtil.isEmpty(value)){
						String[] arr= key.split("_");
						if(arr.length>2){
						sql.append(" and s.").append(arr[1]).append("_").append(arr[2]).append(" like '%").append(value).append("%'");
						}else{
							sql.append(" and s.").append(arr[1]).append(" like '%").append(value).append("%'");
						}
					}
				}
		List<Object[]> objs=this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageNumber(), pageable.getPageSize());
		for(Object[] obj:objs){
			ShopVo shop=new ShopVo();
			StandardVo standat=new StandardVo();
			shop.setShopName((String)obj[3]);
			shop.setPrice(XaUtil.isEmpty(obj[1]) ? null : (Double)obj[1]);
			shop.setShopDesc((String)obj[2]);
			shop.setImgUrl((String)obj[0]);
			standat.setPorperty((String)obj[4]);
			standat.setStocks(XaUtil.isEmpty(obj[5]) ? 0 : (Integer)obj[5]);
			standat.setId(XaUtil.isEmpty(obj[7]) ? null : ((BigInteger)obj[7]).longValue());
			shop.setStandard(standat);
			shop.setId(((BigInteger)obj[6]).longValue());
			shop.setStatus((Integer)obj[8]);
			shopVoList.add(shop);
		}
		
		Page<ShopVo> page=new MyPage<ShopVo>(pageable.getPageNumber(), pageable.getPageSize(), shopVoList, 10);
		xr.setObject(page);
		return xr;
	}
	
	/**
	 * 查询商品详情
	 * @autor zhanglin
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<ShopVo> findShopById(Long modelId) throws BusinessException {
		XaResult<ShopVo> xr=new XaResult<ShopVo>();
		if(XaUtil.isNotEmpty(modelId)){
			StringBuffer sql= new StringBuffer("");
			sql.append("select s.id,s.img_url,s.shop_desc,s.shop_name,(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i WHERE i.type=" + JConstant.ImageType.SHOP + " and i.object_id=s.id) imgUrls,(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i WHERE i.type=" + JConstant.ImageType.SHOP_INTRO + " and i.object_id=s.id) desUrls,s.limit_number from tb_xa_shop s where s.`status`<>3 and s.id=?");
			List<Object> params=new ArrayList<Object>();
			params.add(modelId);
			List<Object[]> objs=this.queryParams(sql.toString(), null, null, params);
			ShopVo shop=new ShopVo();
			for(Object[] obj:objs){
				shop.setId(((BigInteger)obj[0]).longValue());
				shop.setImgUrl((String)obj[1]);
				shop.setShopDesc((String)obj[2]);
				shop.setShopName((String)obj[3]);
				shop.setPics1(XaUtil.isEmpty(obj[5]) ? null : obj[5].toString().split(","));
				shop.setImgUrlpics(XaUtil.isEmpty(obj[4]) ? null : obj[4].toString().split(","));
				shop.setLimitNumber(XaUtil.isEmpty(obj[6])?null:(Integer)obj[6]);
				//查询商品的规格属性
				List<Standard> standards = standardIdRepository.findByShopIdAndStatus(((BigInteger)obj[0]).longValue(), XaConstant.Status.valid);
				shop.setStandards(JSON.parseArray(JSON.toJSONString(standards), StandardVo.class));
			}
			xr.setObject(shop);
		}else{
			xr.error("id不能为空");
			return xr;
		}
		return xr;
	}
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Shop> saveOrUpdate(Shop shop,String imgUrls,String desImgs,List<Standard> standards){
		XaResult<Shop> xr = new XaResult<Shop>();
		Shop s = null;
		if(XaUtil.isNotEmpty(shop.getId())){
			s = shopRepository.findOne(shop.getId());
		}else{
			s = new Shop();
		}
		s.setShopName(shop.getShopName());
		s.setImgUrl(shop.getImgUrl());
		s.setShopDesc(shop.getShopDesc());
		s.setLimitNumber(shop.getLimitNumber());
		s = shopRepository.save(s);
		if(XaUtil.isNotEmpty(s)){
			//修改
			if(XaUtil.isNotEmpty(shop.getId())){
				//删除无用的规格
				String delstandSql = "delete from tb_xa_standard where shop_id=" + s.getId() + " and id not in (";
				for(int i=0;i<standards.size();i++){
					standards.get(i).setShopId(s.getId());
					standards.get(i).setStatus(XaConstant.Status.valid);
					if(XaUtil.isNotEmpty(standards.get(i).getId())){
						delstandSql += standards.get(i).getId();
					}else{
						delstandSql += -1;
					}
					if(i != standards.size() - 1){
						delstandSql += ",";
					}
				}
				delstandSql += ")";
				System.out.println(delstandSql);
				this.delete(delstandSql, null);
				//保存和修改现有规格
				standardIdRepository.save(standards);
				//清除图片
				String delSql = "delete from tb_xa_images where object_id=? and (type=? or type=?)";
				List<Object> params1 = new ArrayList<Object>();
				params1.add(s.getId());
				params1.add(JConstant.ImageType.SHOP);
				params1.add(JConstant.ImageType.SHOP_INTRO);
				this.delete(delSql, params1);
			}else{
				//保存规格属性
				StringBuffer standSql = new StringBuffer();
				standSql.append("insert into tb_xa_standard(create_time,shop_id,porperty,stocks,img_url,price,group_buy,group_price,validity) values ");
				for(int i=0;i<standards.size();i++){
					standSql.append("('").append(XaUtil.getToDayStr()).append("',").append(s.getId()).append(",'");
					standSql.append(standards.get(i).getPorperty()).append("',").append(standards.get(i).getStocks()).append(",'");
					standSql.append(standards.get(i).getImgUrl()).append("',").append(standards.get(i).getPrice()).append(",");
					standSql.append(standards.get(i).getGroupBuy());
					if(standards.get(i).getGroupBuy() == 1){
						standSql.append(",").append(standards.get(i).getGroupPrice()).append(",'").append(standards.get(i).getValidity()).append("')");
					}else{
						standSql.append(",null,null)");
					}
					if(i != standards.size() - 1){
						standSql.append(",");
					}
				}
				System.out.println(standSql.toString());
				this.insert(standSql.toString());
			}
			//保存图片信息
			StringBuffer imgSql = new StringBuffer();
			imgSql.append("insert into tb_xa_images(create_time,object_id,picurl,sort,type) values ");
			if(XaUtil.isNotEmpty(imgUrls)){
				String[] str = imgUrls.split(",");
				for(int i=0;i<str.length;i++){
					imgSql.append("('").append(XaUtil.getToDayStr()).append("',").append(s.getId());
					imgSql.append(",'").append(str[i]).append("',").append(0).append(",").append(JConstant.ImageType.SHOP).append(")");
					if(i != str.length - 1){
						imgSql.append(",");
					}
				}
			}
			if(XaUtil.isNotEmpty(desImgs)){
				String[] str = desImgs.split(",");
				//如果商品轮播图没有值
				if(!imgSql.toString().endsWith("values ") && str.length > 0){
					imgSql.append(",");
				}
				for(int i=0;i<str.length;i++){
					imgSql.append("('").append(XaUtil.getToDayStr()).append("',").append(s.getId());
					imgSql.append(",'").append(str[i]).append("',").append(0).append(",").append(JConstant.ImageType.SHOP_INTRO).append(")");
					if(i != str.length - 1){
						imgSql.append(",");
					}
				}
			}
			System.out.println(imgSql);
			if(!imgSql.toString().endsWith("values ")){
				this.insert(imgSql.toString());
			}
		}
		xr.setObject(s);
		return xr;
	}
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Shop> multiOperate(String modelIds, Integer status)
			throws BusinessException {
		XaResult<Shop> xr = new XaResult<Shop>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Shop obj=shopRepository.findOne(Long.parseLong(id));
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = shopRepository.save(obj);
				} else {
					xr.error("XaConstant.Message.object_not_find");
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	@Override
	public XaResult<Shop> saveOrUpdate(Shop t) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public XaResult<Shop> findOne(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public XaResult<Page<Shop>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Shop> page = shopRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Shop.class), pageable);
		XaResult<Page<Shop>> xr = new XaResult<Page<Shop>>();
		xr.setObject(page);
		return xr;
	}
	@Override
	public XaResult<Page<Shop>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
