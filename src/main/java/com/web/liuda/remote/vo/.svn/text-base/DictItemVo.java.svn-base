package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DictItemVo
 * @Description:字典子表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="字典子表Vo对象")
public class DictItemVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="字典主表Id,字典主表Id")
	private Long dictId;
	@ApiModelProperty(value="选项名称,选项名称")
	private String dictName;
	@ApiModelProperty(value="排序,排序值")
	private Integer sort;
		
	public DictItemVo(Long id,Long dictId,String dictName,Integer sort) {
		this.id = id;
		this.dictId = dictId;
		this.dictName = dictName;
		this.sort = sort;
	}
	
	public DictItemVo() {
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
	
		public void setDictId(Long dictId){
		this.dictId=dictId;
	}
	
	public Long getDictId(){
		return dictId;
	}
		public void setDictName(String dictName){
		this.dictName=dictName;
	}
	
	public String getDictName(){
		return dictName;
	}
		public void setSort(Integer sort){
		this.sort=sort;
	}
	
	public Integer getSort(){
		return sort;
	}
		
}

