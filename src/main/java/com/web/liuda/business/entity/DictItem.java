package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web.webstart.base.entity.BaseEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: DictItem 
* @Description: 字典子表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_dictitem")
@ApiModel(value="字典子表定义表")
public class DictItem extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="字典主表Id,字典主表Id")
	private Long dictId;
	@ApiModelProperty(value="选项名称,选项名称")
	private String dictName;
	@ApiModelProperty(value="排序,排序值")
	private Integer sort;

	/**
	 * 字典主表名称
	 * author：changlu
	 * 后台管理字典选项列表使用
	 */
	/*private String dictItemName;*/
	
	public void setDictId(Long dictId){
		this.dictId=dictId;
	}
	
	@Column(nullable=true,length=20)
	public Long getDictId(){
		return dictId;
	}
	public void setDictName(String dictName){
		this.dictName=dictName;
	}
	
	@Column(nullable=true,length=255)
	public String getDictName(){
		return dictName;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	
	@Column(nullable=true,length=11)
	public Integer getSort(){
		return sort;
	}
	
	public DictItem() {
		super();
	}

	public DictItem(Long id, String dictName, Integer sort, Long dictId) {
		super();
		this.setId(id);
		this.dictName = dictName;
		this.sort = sort;
		this.dictId = dictId;
		
	}

}
