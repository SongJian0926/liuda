package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: GuideTag 
* @Description: 攻略标签表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_guidetag")
@ApiModel(value="攻略标签表定义表")
public class GuideTag extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="攻略Id,攻略Id")
	private Long guideId;
	@ApiModelProperty(value="字典子表Id,字典子表Id")
	private Long dictItemId;
		
	
	public void setGuideId(Long guideId){
		this.guideId=guideId;
	}
	
	@Column(nullable=true,length=20)
	public Long getGuideId(){
		return guideId;
	}
	public void setDictItemId(Long dictItemId){
		this.dictItemId=dictItemId;
	}
	
	@Column(nullable=true,length=20)
	public Long getDictItemId(){
		return dictItemId;
	}
		

}
