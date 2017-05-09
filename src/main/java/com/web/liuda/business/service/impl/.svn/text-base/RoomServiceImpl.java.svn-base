package com.web.liuda.business.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import jodd.io.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Room;
import com.web.liuda.business.entity.Tickets;
import com.web.liuda.business.repository.ImagesRepository;
import com.web.liuda.business.repository.RoomRepository;
import com.web.liuda.business.repository.TicketsRepository;
import com.web.liuda.business.service.RoomService;
import com.web.liuda.remote.vo.ImagesVo;
import com.web.liuda.remote.vo.RoomVo;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("RoomService")
@Transactional(readOnly = false)
public class RoomServiceImpl extends BaseService<Room> implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	@Autowired
	private ImagesRepository  imagesRepository;
	
	/**
	 * 查询单条Room信息
	 * @param tId
	 * @return 返回单个Room对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Room> findOne(Long modelId) throws BusinessException {
		Room obj = new Room();
		if(modelId != 0){
			obj = roomRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Room> xr = new XaResult<Room>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Room数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Room集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Room>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Room> page = roomRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Room.class), pageable);
		XaResult<Page<Room>> xr = new XaResult<Page<Room>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Room数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Room集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Room>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Room> page = roomRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Room.class), pageable);
		XaResult<Page<Room>> xr = new XaResult<Page<Room>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Room信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Room> saveOrUpdate(Room model) throws BusinessException {
		Room obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = roomRepository.findOne(model.getId());
		}else{
			obj = new Room();
		}
		obj.setHotelId(model.getHotelId());
		obj.setType(model.getType());
		obj.setItem(model.getItem());
		obj.setBuyNote(model.getBuyNote());
		obj.setPrice(model.getPrice());
		obj.setLogo(model.getLogo());
		obj.setBreakfast(model.getBreakfast());
		obj.setGroupBuy(model.getGroupBuy());
		obj.setGroupPrice(model.getGroupPrice());
		obj.setValidity(model.getValidity());
		obj.setArea(model.getArea());
		obj.setBeds(model.getBeds());
		obj.setBathRoom(model.getBathRoom());
		obj.setCommunication(model.getCommunication());
		obj.setEstablishment(model.getEstablishment());
		obj = roomRepository.save(obj);
		XaResult<Room> xr = new XaResult<Room>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Room状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Room对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Room> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Room> xr = new XaResult<Room>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Room obj = roomRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = roomRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * 分页查询状态非status的Room数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Room集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<RoomVo>> findRoomList(
			Integer nextPage,Integer pageSize, Map<String, Object> filterParams) throws BusinessException {
		StringBuffer sql=new StringBuffer("select r.id,r.type,h.address,h.telphone,");
		sql.append("r.logo,r.item,r.area,r.beds,r.bath_room,r.communication,");
		sql.append("r.establishment,r.price,h.hotel_name,r.breakfast,r.status  from tb_xa_room r INNER JOIN ");
		sql.append("tb_xa_hotel h on r.hotel_id=h.id ");
		sql.append("where h.status<>3 and r.status<>3");
		Set<Entry<String,Object>> enties = filterParams.entrySet();
		//遍历map集合
		for(Entry<String,Object> entry : enties){
			//得到键
			String key = entry.getKey();
			//得到值
			Object value = entry.getValue();
			//如果有查询条件
			if(XaUtil.isNotEmpty(value)){
				//将字符串转化为数组
				String[] arr = key.split("_");
				//将查询条件拼接在sql语句的where条件中
				if(key.equals("LIKE_hotel_name")){
			    sql.append(" and h.").append(arr[1]).append("_").append(arr[2]).append(" like '%").append(value).append("%'");
			}
		  }
		}
		List<Object[]> objs=this.query(sql.toString(), nextPage*pageSize, pageSize);
		List<RoomVo> vos=new ArrayList<RoomVo>();
		for (Object[] obj : objs) {	
			RoomVo vo=new RoomVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setType((String)obj[1]);
			vo.setAddress((String)obj[2]);
			vo.setTelphone((String)obj[3]);
			vo.setLogo((String)obj[4]);
			vo.setItem((String)obj[5]);
			vo.setArea(((Integer)obj[6]).intValue());
			vo.setBeds(((Integer)obj[7]).intValue());
			vo.setBathRoom((String)obj[8]);
			vo.setCommunication((String)obj[9]);
			vo.setEstablishment((String)obj[10]);
			vo.setPrice(((Double)obj[11]).doubleValue());
			vo.setHotelName((String)obj[12]);
			vo.setBreakfast(((Integer)obj[13]).intValue());
			vo.setStatus(((Integer)obj[14]).intValue());
			vos.add(vo);
		}
		//统计数据库中数据个数
		StringBuffer sql1 = new StringBuffer("select count(*) from ");
		sql1.append("tb_xa_room r inner join tb_xa_hotel h on r.hotel_id=h.id ");
		sql1.append("where h.status<>3 and r.status<>3");
		//执行查询语句
		List<Object[]> obj1=this.query(sql1.toString(), null, null);
		//将得到的查询结果强转成int类型
		int count=Integer.parseInt(obj1.get(0)+"");				
		//调用分页
		Page<RoomVo> page= new MyPage<RoomVo>(nextPage.intValue(),pageSize.intValue(),vos,count);
		//创建结果集对象xr
		XaResult<Page<RoomVo>> xr=new XaResult<Page<RoomVo>>();
		//将page放入xr
		xr.setObject(page);
		return xr;
	}
	/**
	 * 分页查询状态非status的房间数据或者门票数据
	 * @param status
	 * @param nextPage
	 * @param pageSize
	 * @param businessUserId
	 * @param businessType
	 * @return 返回对象RoomVo集合
	 * @throws BusinessException
	 */
	@Override
	public XaResult<Page<RoomVo>> findRoomOrTicketsList(Integer nextPage,
			Integer pageSize, Long id,int businessType,Integer status) throws BusinessException {
		XaResult<Page<RoomVo>>  xr=new XaResult<Page<RoomVo>>();
		StringBuffer sqlTickets=new StringBuffer();
		StringBuffer sqlRoom=new StringBuffer();
		StringBuffer sql = new StringBuffer();
		List<RoomVo> vos = new ArrayList<RoomVo>(); 
	    int count = 0;
		//类型是否是hotel，商家是酒店
		if(businessType==JConstant.ObjectType.HOTEL){
			sqlRoom.append(" select r.id,r.type,r.price,r.group_buy,r.group_price,r.logo from tb_xa_room r ");
			sqlRoom.append("where r.hotel_id=").append(id);
			sqlRoom.append(" and r.status=").append(status);
			sqlRoom.append(" order by r.create_time desc");
			List<Object[]> objs=this.query(sqlRoom.toString(), nextPage*pageSize, pageSize);
		    for(Object[] roomObj : objs){  
		    	RoomVo vo = new RoomVo();	
		    	vo.setId(((BigInteger)roomObj[0]).longValue());
		    	vo.setType((String)roomObj[1]);
		    	vo.setPrice((Double)roomObj[2]);
		    	vo.setGroupBuy((Integer)roomObj[3]);
		    	vo.setGroupPrice(XaUtil.isEmpty(roomObj[4])?0:(Double)roomObj[4]);
		    	vo.setLogo((String)roomObj[5]);
		    	vos.add(vo);
		    }
		   //统计数据库中数据个数
		    sql.append("select count(*) from tb_xa_room r ");
		    sql.append("where r.hotel_id=").append(id).append(" and r.status=").append(status);
		   //执行查询语句
			List<Object[]> obj1=this.query(sql.toString(), null, null);
			//将得到的查询结果强转成int类型
		   count=Integer.parseInt(obj1.get(0)+"");
		}else{
		//count为空，商家是景点
			sqlTickets.append("select t.id,t.img_url,t.ticket_name,t.price,t.group_buy,t.group_price from tb_xa_tickets t ");
			sqlTickets.append("where t.tourist_id=").append(id);
			sqlTickets.append(" and t.status=").append(status);
			sqlTickets.append(" order by t.create_time desc");
			List<Object[]> objs=this.query(sqlTickets.toString(), nextPage*pageSize, pageSize );
		    for(Object[] ticketsObj : objs){  
		    	RoomVo vo = new RoomVo();	
		    	TicketsVo ticketsvo=new TicketsVo();
		    	ticketsvo.setId(((BigInteger)ticketsObj[0]).longValue());
		    	ticketsvo.setImgUrl((String)ticketsObj[1]);
		    	ticketsvo.setTicketName((String)ticketsObj[2]);
		    	ticketsvo.setPrice((Double)ticketsObj[3]);
		    	ticketsvo.setGroupBuy((Integer)ticketsObj[4]);
		    	ticketsvo.setGroupPrice(XaUtil.isEmpty(ticketsObj[5])?0:(Double)ticketsObj[5]);
		    	vo.setTicketsvo(ticketsvo);
		    	vos.add(vo);
		    }
		    //统计数据库中数据个数
		    sql.append("select count(*) from tb_xa_tickets t ");
		    sql.append("where t.tourist_id=").append(id).append(" and t.status=").append(status);
		    //执行查询语句
			List<Object[]> obj1=this.query(sql.toString(), null, null);
			//将得到的查询结果强转成int类型
		    count=Integer.parseInt(obj1.get(0)+"");
		}
		Page<RoomVo> page= new MyPage<RoomVo>(nextPage.intValue(),pageSize.intValue(),vos,count);
	    xr.setObject(page);
		return xr;
	}
	/**
	 * 修改商品上架或者下架  -1下架   2上架   1正常(待审批)
	 * @param businessUserId
	 * @param businessType
	 * @param modelIds
	 * @return 返回RoomVo对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<RoomVo> upanddown(
			String modelIds,int businessType,Integer status,Long id) throws BusinessException {
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		//酒店
		if(businessType==JConstant.ObjectType.HOTEL){
			if(modelIds != null){
				String[] ids = modelIds.split(",");
				for(String sid : ids){
					Room obj=roomRepository.findByHotelIdAndId(id, Long.parseLong(sid));
					if (XaUtil.isNotEmpty(obj)) {
						//状态是-1，商品要上架
						if(XaConstant.Status.locked==status){
							obj.setStatus(XaConstant.Status.valid);
							obj = roomRepository.save(obj);
						}else{
					    //商品直接下架
							obj.setStatus(XaConstant.Status.locked);
							obj = roomRepository.save(obj);
						}
					} else {
						throw new BusinessException(XaConstant.Message.object_not_find);
					}
				}
			}
		}else{
	    //景点
			if(modelIds != null){
				String[] ids = modelIds.split(",");
				for(String sid : ids){
					Tickets tobj=ticketsRepository.findByTouristIdAndId(id, Long.parseLong(sid));
					if (XaUtil.isNotEmpty(tobj)) {
						//状态是-1，商品要上架
						if(XaConstant.Status.locked==status){
							tobj.setStatus(XaConstant.Status.valid);
							tobj = ticketsRepository.save(tobj);
						}else{
					    //商品直接下架
							tobj.setStatus(XaConstant.Status.locked);
							tobj = ticketsRepository.save(tobj);
						}
					} else {
						throw new BusinessException(XaConstant.Message.object_not_find);
					}
				}
			}
		}
		return xr;
	}
	/**
	 * web端保存房间(有团购)
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Room> saveandUpdate(Room model,Long hotelId,String photo,HttpServletRequest request,Double groupPrice,String validity) throws BusinessException {
		//获取项目路径
		String root=request.getSession().getServletContext().getRealPath("/");
		Room obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = roomRepository.findOne(model.getId());
		}else{
			obj = new Room();
		}
		obj.setHotelId(hotelId);
		obj.setType(model.getType());
		obj.setItem(model.getItem());
		obj.setBuyNote(model.getBuyNote());
		obj.setPrice(model.getPrice());
		obj.setArea(model.getArea());
		obj.setBeds(model.getBeds());
		obj.setBathRoom(model.getBathRoom());
		obj.setCommunication(model.getCommunication());
		obj.setEstablishment(model.getEstablishment());
		//传0，默认无早
		obj.setBreakfast(JConstant.BooleanStatus.FALSE);
		if(XaUtil.isNotEmpty(groupPrice) && XaUtil.isNotEmpty(validity)){
			obj.setGroupPrice(groupPrice);
			obj.setValidity(validity);
		}
		obj.setStatus(XaConstant.Status.valid);
		//团购价格是否为空，判断是否团购
		if(XaUtil.isEmpty(model.getGroupPrice())){
			obj.setGroupBuy(JConstant.BooleanStatus.FALSE);
		}else{
			obj.setGroupBuy(JConstant.BooleanStatus.TRUE);
		}
		//分开拿到的图片
		String[] ids = model.getLogo().split(",");
		//第一张是房间的主图
		obj.setLogo(ids[0]);
		//保存房间
		obj = roomRepository.save(obj);
		//判断id是否为空
		if(XaUtil.isNotEmpty(obj.getId())){
			//在存入图片之前删除之前的图片(不是新增才会删除)
			if(XaUtil.isNotEmpty(model.getId())){
			   StringBuffer sql1=new StringBuffer("delete from tb_xa_images where object_id=");
			   sql1.append(obj.getId()).append(" and type=").append(JConstant.ImageType.room);
			   this.delete(sql1.toString(), null);
			   //删除服务器上的图片
			   if(XaUtil.isNotEmpty(photo)){
					 try {
						  String[] imgs = photo.split(",");
						  for (int i = 0; i < imgs.length; i++) {
							  FileUtil.deleteFile(root+imgs[i]);
						  }
					   } catch (IOException e) {
						  e.printStackTrace();
					   }
				 }
			 }
			 StringBuffer sql2=new StringBuffer();
			 sql2.append("insert into tb_xa_images(object_id,picurl,type,create_time) values ");
			 for (int i = 0; i < ids.length; i++) {
				if(i>0 && i<ids.length){
					sql2.append(",");
				}
				sql2.append("('").append((obj.getId()).longValue()).append("','");
				sql2.append(ids[i]).append("','");
				sql2.append(JConstant.ImageType.room).append("','").append(XaUtil.getToDayStr()).append("')");
			}
			//执行插入语句
			this.insert(sql2.toString());
		}
		XaResult<Room> xr = new XaResult<Room>();
		xr.setObject(obj);
		return xr;
	}
	/**
	 * web端保存房间(无团购)
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Room> saveandUpdateNoGroup(Room model,Long hotelId,String photo,HttpServletRequest request) throws BusinessException {
		//获取项目路径
		String root=request.getSession().getServletContext().getRealPath("/");
		Room obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = roomRepository.findOne(model.getId());
		}else{
			obj = new Room();
		}
		obj.setHotelId(hotelId);
		obj.setType(model.getType());
		obj.setItem(model.getItem());
		obj.setBuyNote(model.getBuyNote());
		obj.setPrice(model.getPrice());
		obj.setArea(model.getArea());
		obj.setBeds(model.getBeds());
		obj.setBathRoom(model.getBathRoom());
		obj.setCommunication(model.getCommunication());
		obj.setEstablishment(model.getEstablishment());
		//传0，默认无早
		obj.setBreakfast(JConstant.BooleanStatus.FALSE);
		obj.setStatus(XaConstant.Status.valid);
		obj.setGroupBuy(JConstant.BooleanStatus.FALSE);
		//分开拿到的图片
		String[] ids = model.getLogo().split(",");
		//第一张是房间的主图
		obj.setLogo(ids[0]);
		//保存房间
		obj = roomRepository.save(obj);
		//判断id是否为空
		if(XaUtil.isNotEmpty(obj.getId())){
			//在存入图片之前删除之前的图片(不是新增才会删除)
			if(XaUtil.isNotEmpty(model.getId())){
			   StringBuffer sql1=new StringBuffer("delete from tb_xa_images where object_id=");
			   sql1.append(obj.getId()).append(" and type=").append(JConstant.ImageType.room);
			   this.delete(sql1.toString(), null);
			   //删除服务器上的图片
			   if(XaUtil.isNotEmpty(photo)){
					 try {
						  String[] imgs = photo.split(",");
						  for (int i = 0; i < imgs.length; i++) {
							  FileUtil.deleteFile(root+imgs[i]);
						  }
					   } catch (IOException e) {
						  e.printStackTrace();
					   }
				 }
			 }
			 StringBuffer sql2=new StringBuffer();
			 sql2.append("insert into tb_xa_images(object_id,picurl,type,create_time) values ");
			 for (int i = 0; i < ids.length; i++) {
				if(i>0 && i<ids.length){
					sql2.append(",");
				}
				sql2.append("('").append((obj.getId()).longValue()).append("','");
				sql2.append(ids[i]).append("','");
				sql2.append(JConstant.ImageType.room).append("','").append(XaUtil.getToDayStr()).append("')");
			}
			//执行插入语句
			this.insert(sql2.toString());
		}
		XaResult<Room> xr = new XaResult<Room>();
		xr.setObject(obj);
		return xr;
	}
	/**
	 * 查询单条Room信息
	 * @param tId
	 * @return 返回单个Room对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<RoomVo> findOneRoom(Long modelId) throws BusinessException {
		Room obj = new Room();
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		if(XaUtil.isNotEmpty(modelId)){
			obj = roomRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
			if (XaUtil.isNotEmpty(obj)) {
				RoomVo rvo=new RoomVo();
				ImagesVo ivo=new ImagesVo();
				//room转化成roomvo
			    rvo=JSON.parseObject(JSON.toJSONString(obj),RoomVo.class);
				//查找图片
			    StringBuffer sql=new StringBuffer("select GROUP_CONCAT(i.picurl) ");
			    sql.append(" from tb_xa_images i where i.object_id=").append(modelId);
			    sql.append(" and i.type=").append(JConstant.ImageType.room);
			    sql.append(" and i.status<>").append(XaConstant.Status.delete);
			    List<Object[]> obj1=this.query(sql.toString(), null, null);
			    List<String> pics1 = new ArrayList<String>();
				String[] pic=(obj1.get(0)+"").split(",");
			    for (int i = 0; i < pic.length; i++) {
					pics1.add(pic[i]);
				}
				//找到图片放到images中
				ivo.setPics1(pics1);
				rvo.setImagesvo(ivo);
				xr.setObject(rvo);
			} else {
				throw new BusinessException(XaConstant.Message.object_not_find);
			}
		}
		return xr;
	}
	/**
	 * 分页查询状态非status的Room数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Room集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<RoomVo>> findBusinessShopList(
			Integer nextPage, Integer pageSize, Map<String,Object> filterParams) throws BusinessException {
		//Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);、
		XaResult<Page<RoomVo>> xr = new XaResult<Page<RoomVo>>();
		StringBuffer sql=new StringBuffer("select r.id,r.type,h.hotel_name,r.logo,r.price,r.group_buy,");
		sql.append("r.group_price,r.validity,r.status,r.create_time time,0 ty from tb_xa_room r");
		sql.append(" INNER JOIN tb_xa_hotel h on r.hotel_id=h.id where r.status<>").append(XaConstant.Status.delete);
		sql.append(" and h.status<>").append(XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_shopName"))){
			sql.append(" and r.type").append(" like '%").append(filterParams.get("LIKE_shopName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_businessName"))){
			sql.append(" and h.hotel_name").append(" like '%").append(filterParams.get("LIKE_businessName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_status"))){
			sql.append(" and r.status").append("=").append(filterParams.get("EQ_status"));
		}
		sql.append(" UNION ALL ");
		sql.append("select t.id,t.ticket_name,tr.tourist_name,t.img_url,t.price,t.group_buy,");
		sql.append("t.group_price,t.validity,t.status,t.create_time time,1 ty from tb_xa_tickets t");
		sql.append(" INNER JOIN tb_xa_tourist tr on t.tourist_id=tr.id where t.status<>").append(XaConstant.Status.delete);
		sql.append(" and tr.status<>").append(XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_shopName"))){
			sql.append(" and t.ticket_name").append(" like '%").append(filterParams.get("LIKE_shopName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_businessName"))){
			sql.append(" and tr.tourist_name").append(" like '%").append(filterParams.get("LIKE_businessName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_status"))){
			sql.append(" and t.status").append("=").append(filterParams.get("EQ_status"));
		}
		sql.append(" ORDER BY time DESC");
		List<Object[]> objs=this.query(sql.toString(), nextPage*pageSize, pageSize);
		List<RoomVo> vos=new ArrayList<RoomVo>();
		for (Object[] obj : objs) {
			RoomVo  vo=new RoomVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setType((String)obj[1]);
			vo.setHotelName((String)obj[2]);
			vo.setLogo((String)obj[3]);
			vo.setPrice(XaUtil.isEmpty(obj[4])?null:((Double)obj[4]).doubleValue());
			vo.setGroupBuy(XaUtil.isEmpty(obj[5])?null:((Integer)obj[5]).intValue());
			vo.setGroupPrice(XaUtil.isEmpty(obj[6])?null:((Double)obj[6]).doubleValue());
			vo.setValidity(XaUtil.isEmpty(obj[6])?null:(String)obj[7]);
			vo.setStatus(((Integer)obj[8]).intValue());
			vo.setCreateTime((String)obj[9]);
			vo.setBusinessType(((BigInteger)obj[10]).intValue());
			vos.add(vo);
		}
		StringBuffer sqlcount=new StringBuffer("select count(*) from tb_xa_room r INNER JOIN tb_xa_hotel h on r.hotel_id=h.id  where r.status<>").append(XaConstant.Status.delete);
		sqlcount.append(" and h.status<>").append(XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_shopName"))){
			sqlcount.append(" and r.type").append(" like '%").append(filterParams.get("LIKE_shopName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_businessName"))){
			sqlcount.append(" and h.hotel_name").append(" like '%").append(filterParams.get("LIKE_businessName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_status"))){
			sqlcount.append(" and r.status").append("=").append(filterParams.get("EQ_status"));
		}
		sqlcount.append(" UNION ALL ");
		sqlcount.append("select count(*) from tb_xa_tickets t INNER JOIN tb_xa_tourist tr on t.tourist_id=tr.id where t.status<>").append(XaConstant.Status.delete);
		sqlcount.append(" and tr.status<>").append(XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_shopName"))){
			sqlcount.append(" and t.ticket_name").append(" like '%").append(filterParams.get("LIKE_shopName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_businessName"))){
			sqlcount.append(" and tr.tourist_name").append(" like '%").append(filterParams.get("LIKE_businessName")).append("%'");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_status"))){
			sqlcount.append(" and t.status").append("=").append(filterParams.get("EQ_status"));
		}
		List<Object[]> obj1=this.query(sqlcount.toString(), null, null);
	    int roomcount=Integer.parseInt(obj1.get(0)+"");
	    int ticketcount=Integer.parseInt(obj1.get(1)+"");
	    int count=roomcount+ticketcount;
	    Page<RoomVo> page= new MyPage<RoomVo>(nextPage.intValue(),pageSize.intValue(),vos,count);
	    xr.setObject(page);
		return xr;
	}
	/**
	 * 后台修改商品上架或者下架或者审批  -1下架   2上架   1正常(待审批)
	 * @param businessUserId
	 * @param businessType
	 * @param modelIds
	 * @return 返回RoomVo对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<RoomVo> upAnddownback(
			String roommodelIds,String ticketsmodelIds,String btnVar) throws BusinessException {
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		//房间
		if(XaUtil.isNotEmpty(roommodelIds)){
			String[] rids = roommodelIds.split(",");
			for(String rid : rids){
				Room obj=roomRepository.findByIdAndStatusNot(Long.parseLong(rid), XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
				  //审批状态的直接上架
				  if("审批".equals(btnVar)){
					  if(obj.getStatus()==XaConstant.Status.valid){
						  obj.setStatus(XaConstant.Status.publish);
						  obj = roomRepository.save(obj);
					  }
				  //下架商品直接上架
				  }else if("上架".equals(btnVar)){
					  if(obj.getStatus()==XaConstant.Status.locked){
						  obj.setStatus(XaConstant.Status.publish);
						  obj = roomRepository.save(obj);
					  }
				  //上架商品直接下架
				  }else if("下架".equals(btnVar)){
					  if(obj.getStatus()==XaConstant.Status.publish){
						  obj.setStatus(XaConstant.Status.locked);
						  obj = roomRepository.save(obj);
					  }
				  }
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
		    }
		}
		//门票
		if(XaUtil.isNotEmpty(ticketsmodelIds)){
			String[] tids = ticketsmodelIds.split(",");
			for(String tid : tids){
				Tickets obj=ticketsRepository.findByIdAndStatusNot(Long.parseLong(tid), XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
				  //审批状态的直接上架
				  if("审批".equals(btnVar)){
					  if(obj.getStatus()==XaConstant.Status.valid){
						  obj.setStatus(XaConstant.Status.publish);
						  obj = ticketsRepository.save(obj);
					  }
				  //下架商品直接上架
				  }else if("上架".equals(btnVar)){
					  if(obj.getStatus()==XaConstant.Status.locked){
						  obj.setStatus(XaConstant.Status.publish);
						  obj = ticketsRepository.save(obj);
					  }
				  //上架商品直接下架
				  }else if("下架".equals(btnVar)){
					  if(obj.getStatus()==XaConstant.Status.publish){
						  obj.setStatus(XaConstant.Status.locked);
						  obj = ticketsRepository.save(obj);
					  }
				  }
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
		    }
		}
		return xr;
    }
}