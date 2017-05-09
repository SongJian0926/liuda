package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Dict 
* @Description: 字典主表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_dict")
@ApiModel(value="字典主表定义表")
public class Dict extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="字典名称,字典名称")
	private String dictName;
	@ApiModelProperty(value="字典类型,1:攻略标签，2:赛事标签，3:俱乐部类型，4:俱乐部等级，5:俱乐部兴趣标签")
	private Integer type;
		
	
	public void setDictName(String dictName){
		this.dictName=dictName;
	}
	
	@Column(nullable=true,length=50)
	public String getDictName(){
		return dictName;
	}
	@Column(nullable=true,length=11)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
		

}
