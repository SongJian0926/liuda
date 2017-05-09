package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DictVo
 * @Description:字典主表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="字典主表Vo对象")
public class DictVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="字典名称,字典名称")
	private String dictName;
 	
 	@ApiModelProperty(value="字典类型,1:攻略标签，2:赛事标签，3:俱乐部类型，4:俱乐部等级，5:俱乐部兴趣标签")
	private Integer type;
 	
 	/*
 	 * app端攻略标签、赛事标签、俱乐部类型、俱乐部等级使用
 	 * author:changlu
 	 * time:2016-04-08 12:16:00
 	 **/
 	private DictItemVo dictItemVo;
		
	public DictVo(Long id,String dictName) {
		this.id = id;
		this.dictName = dictName;
	}
	
	public DictVo() {
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
	
		public void setDictName(String dictName){
		this.dictName=dictName;
	}
	
	public String getDictName(){
		return dictName;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public DictItemVo getDictItemVo() {
		return dictItemVo;
	}

	public void setDictItemVo(DictItemVo dictItemVo) {
		this.dictItemVo = dictItemVo;
	}
			
}

