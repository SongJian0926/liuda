package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Vote 
* @Description: 投票主表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_vote")
@ApiModel(value="投票主表定义表")
public class Vote extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="截止日期,截止日期")
	private String deadline;
	@ApiModelProperty(value="标题,标题")
	private String title;
	@ApiModelProperty(value="投票说明,投票说明")
	private String content;
	@ApiModelProperty(value="是否单选,1：表示单选，2：表示多选 ")
	private Integer isRadio;
	@ApiModelProperty(value="投票总数,投票总数")
	private Integer num;
	@ApiModelProperty(value="头像")
	private String logo;	
	
	public void setDeadline(String deadline){
		this.deadline=deadline;
	}
	
	@Column(nullable=true,length=50)
	public String getDeadline(){
		return deadline;
	}
	public void setTitle(String title){
		this.title=title;
	}
	
	@Column(nullable=true,length=500)
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
	
	public void setNum(Integer num){
		this.num=num;
	}
	@Column(nullable=true,length=11)
	public Integer getIsRadio() {
		return isRadio;
	}

	public void setIsRadio(Integer isRadio) {
		this.isRadio = isRadio;
	}

	@Column(nullable=true,length=11)
	public Integer getNum(){
		return num;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
		

}
