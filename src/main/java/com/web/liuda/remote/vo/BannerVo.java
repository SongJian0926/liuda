package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: BannerVo
 * @Description:Banner图 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="Banner图Vo对象")
public class BannerVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="图片,图片地址")
	private String picurl;
	@ApiModelProperty(value="链接地址,链接地址")
	private String linkurl;
	@ApiModelProperty(value="类型,类型")
	private Integer type;
	@ApiModelProperty(value="排序值,排序值")
	private Integer sort;
		
	public BannerVo(Long id,String picurl,String linkurl,Integer type,Integer sort) {
		this.id = id;
		this.picurl = picurl;
		this.linkurl = linkurl;
		this.type = type;
		this.sort = sort;
	}
	
	public BannerVo() {
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
		public void setLinkurl(String linkurl){
		this.linkurl=linkurl;
	}
	
	public String getLinkurl(){
		return linkurl;
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
		
}

