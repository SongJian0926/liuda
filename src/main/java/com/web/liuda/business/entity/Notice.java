package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Notice 
* @Description: 公告通知定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_notice")
@ApiModel(value="公告通知定义表")
public class Notice extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="标题,标题")
	private String title;
	@ApiModelProperty(value="内容,内容")
	private String content;
	@ApiModelProperty(value="简介,简介")
	private String introduce;
	@ApiModelProperty(value="图片,图片")
	private String imgPath;
	@ApiModelProperty(value="是否推送,0:否，1:是")
	private Integer needPush;
	@ApiModelProperty(value="公告类型,1:黑板报，2:活动公告，3:新闻报道")
	private Integer type;
		
	
	public void setTitle(String title){
		this.title=title;
	}
	
	@Column(nullable=true,length=255)
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=2000,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setImgPath(String imgPath){
		this.imgPath=imgPath;
	}
	
	@Column(nullable=true,length=255)
	public String getImgPath(){
		return imgPath;
	}
	public void setNeedPush(Integer needPush){
		this.needPush=needPush;
	}
	
	@Column(nullable=true,length=11)
	public Integer getNeedPush(){
		return needPush;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=true,length=11)
	public Integer getType(){
		return type;
	}
	
	@Column(nullable=true,length=2000,columnDefinition="MEDIUMTEXT")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
		

}
