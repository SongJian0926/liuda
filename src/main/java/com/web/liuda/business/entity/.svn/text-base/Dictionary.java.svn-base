package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web.webstart.base.entity.BaseEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: Dictionary 
* @Description: 数据字典表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_dictionary")
@ApiModel(value="数据字典")
public class Dictionary extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="名称,名称")
	private String name;
	
	@ApiModelProperty(value="类型,0、资讯来源 1、其他")
	private Integer type;

	
	@Column(nullable=false,length=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(nullable=false,length=50)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
