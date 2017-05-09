package com.web.liuda.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.web.liuda.business.entity.Room;
import com.web.liuda.remote.vo.RoomVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface RoomService extends BaseServiceInterFace<Room>{
	//查询房间列表
	public XaResult<Page<RoomVo>> findRoomList(
			Integer nextPage,Integer pageSize, Map<String, Object> filterParams) throws BusinessException;
   //web端查询商品列表
	public XaResult<Page<RoomVo>> findRoomOrTicketsList(Integer nextPage,
			Integer pageSize, Long id, int businessType, Integer status)
			throws BusinessException;
	//web端商品的上架下架
	public XaResult<RoomVo> upanddown(
			String modelIds,int businessType,Integer status, Long id) throws BusinessException;
	//web端房间商品的保存(有团购)
	public XaResult<Room> saveandUpdate(Room model,Long hotelId,String imgUrl,HttpServletRequest request,Double groupPrice,String validity) throws BusinessException;
	//web端房间商品的保存(无团购)
	public XaResult<Room> saveandUpdateNoGroup(Room model,Long hotelId,String photo,HttpServletRequest request) throws BusinessException;
	//web端查找房间详情
	public XaResult<RoomVo> findOneRoom(Long modelId) throws BusinessException;
	//后台查询商家商品列表
	public XaResult<Page<RoomVo>> findBusinessShopList(
	Integer nextPage, Integer pageSize, Map<String, Object> filterParams) throws BusinessException;
    //后台商品的上架、下架、审批
	public XaResult<RoomVo> upAnddownback(
			String roommodelIds,String ticketsmodelIds,String btnVar) throws BusinessException;
}
