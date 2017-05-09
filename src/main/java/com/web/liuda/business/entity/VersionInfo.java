package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web.webstart.base.entity.BaseEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: VersionInfo 
* @Description: 版本信息定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_versioninfo")
@ApiModel(value="版本信息定义表")
public class VersionInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="版本Code,版本code")
	private String versionCode;
	@ApiModelProperty(value="更新内容,更新内容")
	private String updateContent;
	@ApiModelProperty(value="是否强制更新,0、否 1、是")
	private Integer isforceUpdate = 0;
	@ApiModelProperty(value="下载地址,下载地址")
	private String downloadUrl;
		
	
	public void setVersionCode(String versionCode){
		this.versionCode=versionCode;
	}
	
	@Column(nullable=false,length=50)
	public String getVersionCode(){
		return versionCode;
	}
	public void setUpdateContent(String updateContent){
		this.updateContent=updateContent;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getUpdateContent(){
		return updateContent;
	}
	public void setIsforceUpdate(Integer isforceUpdate){
		this.isforceUpdate=isforceUpdate;
	}
	
	@Column(nullable=true,length=50)
	public Integer getIsforceUpdate(){
		return isforceUpdate;
	}
	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl=downloadUrl;
	}
	
	@Column(nullable=false,length=255)
	public String getDownloadUrl(){
		return downloadUrl;
	}

}
