package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: GuideAppend 
* @Description: 攻略追加表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_guideappend")
@ApiModel(value="攻略追加表定义表")
public class GuideAppend extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="攻略Id,攻略Id")
	private Long guideId;
	@ApiModelProperty(value="内容,追加内容")
	private String content;
	@ApiModelProperty(value="图片,图片")
	private String mediaPath;
		
	
	public void setGuideId(Long guideId){
		this.guideId=guideId;
	}
	
	@Column(nullable=true,length=20)
	public Long getGuideId(){
		return guideId;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=1000)
	public String getContent(){
		return content;
	}
	public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	@Column(nullable=true,length=2000)
	public String getMediaPath(){
		return mediaPath;
	}
		

}
