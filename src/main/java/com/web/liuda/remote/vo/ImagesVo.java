package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ImagesVo
 * @Description:图片 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="图片Vo对象")
public class ImagesVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="图片地址,图片地址")
	private String picurl;
	@ApiModelProperty(value="图片类型,图片类型，0：酒店(轮播图)、1：酒店介绍(介绍图)、2：评论、3：景点(轮播图)、4：商品(轮播图)、5：景点介绍(介绍图)、6：商品介绍(介绍图)、7：回复、8：房间(轮播图)、9：门票(轮播图)")
	private Integer type;
	@ApiModelProperty(value="排序值,排序值")
	private Integer sort;
	@ApiModelProperty(value="对象id,对象id")
	private Long objectId;
	@ApiModelProperty(value="图片,图片")	
	private List<String> pics1;
	
	public ImagesVo(Long id,String picurl,Integer type,Integer sort,Long objectId) {
		this.id = id;
		this.picurl = picurl;
		this.type = type;
		this.sort = sort;
		this.objectId = objectId;
	}
	
	public ImagesVo() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
		public void setPicurl(String picurl){
		this.picurl=picurl;
	}
	
	public String getPicurl(){
		return picurl;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}
		public void setSort(Integer sort){
		this.sort=sort;
	}
	
	public Integer getSort(){
		return sort;
	}
		public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	public Long getObjectId(){
		return objectId;
	}

	public List<String> getPics1() {
		return pics1;
	}

	public void setPics1(List<String> pics1) {
		this.pics1 = pics1;
	}
		
}

