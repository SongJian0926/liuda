package com.web.liuda.business.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jodd.io.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Tickets;
import com.web.liuda.business.repository.ImagesRepository;
import com.web.liuda.business.repository.TicketsRepository;
import com.web.liuda.business.service.TicketsService;
import com.web.liuda.remote.vo.ImagesVo;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("TicketsService")
@Transactional(readOnly = false)
public class TicketsServiceImpl extends BaseService<Tickets> implements TicketsService {

	@Autowired
	private TicketsRepository ticketsRepository;
	
	@Autowired
	private ImagesRepository  imagesRepository;
	
	/**
	 * 查询单条Tickets信息
	 * @param tId
	 * @return 返回单个Tickets对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Tickets> findOne(Long modelId) throws BusinessException {
		Tickets obj = new Tickets();
		if(modelId != 0){
			obj = ticketsRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Tickets> xr = new XaResult<Tickets>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Tickets数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Tickets集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Tickets>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Tickets> page = ticketsRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Tickets.class), pageable);
		XaResult<Page<Tickets>> xr = new XaResult<Page<Tickets>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Tickets数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Tickets集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Tickets>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Tickets> page = ticketsRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Tickets.class), pageable);
		XaResult<Page<Tickets>> xr = new XaResult<Page<Tickets>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Tickets信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Tickets> saveOrUpdate(Tickets model) throws BusinessException {
		Tickets obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = ticketsRepository.findOne(model.getId());
		}else{
			obj = new Tickets();
		}
		obj.setTouristId(model.getTouristId());
		obj.setTicketName(model.getTicketName());
		obj.setImgUrl(model.getImgUrl());
		obj.setPrice(model.getPrice());
		obj.setPredTime(model.getPredTime());
		obj.setItems(model.getItems());
		obj.setBuyNotes(model.getBuyNotes());
		obj.setExchangeNotes(model.getExchangeNotes());
		obj.setGroupBuy(model.getGroupBuy());
		obj.setGroupPrice(model.getGroupPrice());
		obj.setValidity(model.getValidity());
		obj = ticketsRepository.save(obj);
		XaResult<Tickets> xr = new XaResult<Tickets>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Tickets状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Tickets对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Tickets> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Tickets> xr = new XaResult<Tickets>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Tickets obj = ticketsRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = ticketsRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	/**
	 * web端保存门票信息(有团购)
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Tickets> saveandUpdate(Tickets model,Long touristId,String photo,HttpServletRequest request,Double groupPrice,String validity) throws BusinessException {
		//获取项目路径
		String root=request.getSession().getServletContext().getRealPath("/");
		Tickets tobj=null;
		XaResult<Tickets> xr = new XaResult<Tickets>();
		if(XaUtil.isNotEmpty(model.getId())){
			tobj = ticketsRepository.findOne(model.getId());
		}else{
			tobj = new Tickets();
		}
			tobj.setTouristId(touristId);
			tobj.setTicketName(model.getTicketName());
			tobj.setItems(model.getItems());
			tobj.setExchangeNotes(model.getExchangeNotes());
			tobj.setBuyNotes(model.getBuyNotes());
			tobj.setPrice(model.getPrice());
			//if(XaUtil.isNotEmpty(groupPrice) && XaUtil.isNotEmpty(validity)){
				tobj.setGroupPrice(groupPrice);
				tobj.setValidity(validity);
			//}
			tobj.setStatus(XaConstant.Status.valid);
			//团购价格是否为空，判断是否团购
			if(XaUtil.isEmpty(model.getGroupPrice())){
				tobj.setGroupBuy(JConstant.BooleanStatus.FALSE);
			}else{
				tobj.setGroupBuy(JConstant.BooleanStatus.TRUE);
			}
			//分开拿到的图片
			String[] ids =model.getImgUrl().split(",");
			tobj.setPredTime(model.getPredTime());
			//第一张是门票的主图
			tobj.setImgUrl(ids[0]);
			tobj = ticketsRepository.save(tobj);
			//判断id是否为空
			if(XaUtil.isNotEmpty(tobj.getId())){
				//在存入图片之前删除之前的图片(不是新增才会删除)
				if(XaUtil.isNotEmpty(model.getId())){
				 StringBuffer sql1=new StringBuffer("delete from tb_xa_images where object_id=");
				 sql1.append(tobj.getId()).append(" and type=").append(JConstant.ImageType.tickets);
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
					sql2.append("('").append((tobj.getId()).longValue()).append("','");
					sql2.append(ids[i]).append("','");
					sql2.append(JConstant.ImageType.tickets).append("','").append(XaUtil.getToDayStr()).append("')");
				}
				//执行插入语句
				this.insert(sql2.toString());
			}
			xr.setObject(tobj);
		return xr;
	}
	/**
	 * web端保存门票信息(有团购)
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Tickets> saveandUpdateNoGroup(Tickets model,Long touristId,String photo,HttpServletRequest request) throws BusinessException {
		//获取项目路径
		String root=request.getSession().getServletContext().getRealPath("/");
		Tickets tobj=null;
		XaResult<Tickets> xr = new XaResult<Tickets>();
		if(XaUtil.isNotEmpty(model.getId())){
			tobj = ticketsRepository.findOne(model.getId());
		}else{
			tobj = new Tickets();
		}
			tobj.setTouristId(touristId);
			tobj.setTicketName(model.getTicketName());
			tobj.setItems(model.getItems());
			tobj.setExchangeNotes(model.getExchangeNotes());
			tobj.setBuyNotes(model.getBuyNotes());
			tobj.setPrice(model.getPrice());
			tobj.setStatus(XaConstant.Status.valid);
			tobj.setGroupBuy(JConstant.BooleanStatus.FALSE);
			//分开拿到的图片
			String[] ids =model.getImgUrl().split(",");
			tobj.setPredTime(model.getPredTime());
			//第一张是门票的主图
			tobj.setImgUrl(ids[0]);
			tobj = ticketsRepository.save(tobj);
			//判断id是否为空
			if(XaUtil.isNotEmpty(tobj.getId())){
				//在存入图片之前删除之前的图片(不是新增才会删除)
				if(XaUtil.isNotEmpty(model.getId())){
				 StringBuffer sql1=new StringBuffer("delete from tb_xa_images where object_id=");
				 sql1.append(tobj.getId()).append(" and type=").append(JConstant.ImageType.tickets);
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
					sql2.append("('").append((tobj.getId()).longValue()).append("','");
					sql2.append(ids[i]).append("','");
					sql2.append(JConstant.ImageType.tickets).append("','").append(XaUtil.getToDayStr()).append("')");
				}
				//执行插入语句
				this.insert(sql2.toString());
			}
			xr.setObject(tobj);
		return xr;
	}
	/**
	 * web端查询单条Tickets信息
	 * @param tId
	 * @return 返回单个Tickets对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<TicketsVo> findOneTicket(Long modelId) throws BusinessException {
		Tickets obj = new Tickets();
		XaResult<TicketsVo> xr = new XaResult<TicketsVo>();
		if(XaUtil.isNotEmpty(modelId)){
			obj = ticketsRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
			if(XaUtil.isNotEmpty(modelId)){
				TicketsVo tvo=new TicketsVo();
				ImagesVo ivo=new ImagesVo();
				//tickets转化成ticketsvo
			    tvo=JSON.parseObject(JSON.toJSONString(obj),TicketsVo.class);
				//查找图片
			    StringBuffer sql=new StringBuffer("select GROUP_CONCAT(i.picurl) ");
			    sql.append(" from tb_xa_images i where i.object_id=").append(modelId);
			    sql.append(" and i.type=").append(JConstant.ImageType.tickets);
			    sql.append(" and i.status<>").append(XaConstant.Status.delete);
			    List<Object[]> obj1=this.query(sql.toString(), null, null);
			    List<String> pics1 = new ArrayList<String>();
				String[] pic=(obj1.get(0)+"").split(",");
			    for (int i = 0; i < pic.length; i++) {
					pics1.add(pic[i]);
				}
				//找到图片放到images中
				ivo.setPics1(pics1);
				tvo.setImagesvo(ivo);
				xr.setObject(tvo);
			 }else {
					throw new BusinessException(XaConstant.Message.object_not_find);
			 }
		  }
		return xr;
	}
	
}
