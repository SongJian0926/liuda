package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Agreement 
* @Description: agreement定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_agreement")
@ApiModel(value="agreement定义表")
public class Agreement extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="内容,内容")
	private String content;
	@ApiModelProperty(value="类型,1,投票说明；2，活动报名协议；3，赛事报名协议;4，竞猜说明")
	private Integer type;
		
	
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=2000,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=true,length=50)
	public Integer getType(){
		return type;
	}
		

}
