package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: VersionInfoVo
 * @Description:版本信息 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="版本信息Vo对象")
public class VersionInfoVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="版本Code,版本code")
	private String versionCode;
	@ApiModelProperty(value="更新内容,更新内容")
	private String updateContent;
	@ApiModelProperty(value="是否强制更新,0、否 1、是")
	private Integer isforceUpdate;
	@ApiModelProperty(value="下载地址,下载地址")
	private String downloadUrl;
		
	public VersionInfoVo(Long id,String versionCode,String updateContent,Integer isforceUpdate,String downloadUrl) {
		this.id = id;
		this.versionCode = versionCode;
		this.updateContent = updateContent;
		this.isforceUpdate = isforceUpdate;
		this.downloadUrl = downloadUrl;
	}
	
	public VersionInfoVo() {
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
	
		public void setVersionCode(String versionCode){
		this.versionCode=versionCode;
	}
	
	public String getVersionCode(){
		return versionCode;
	}
		public void setUpdateContent(String updateContent){
		this.updateContent=updateContent;
	}
	
	public String getUpdateContent(){
		return updateContent;
	}
		public void setIsforceUpdate(Integer isforceUpdate){
		this.isforceUpdate=isforceUpdate;
	}
	
	public Integer getIsforceUpdate(){
		return isforceUpdate;
	}
		public void setDownloadUrl(String downloadUrl){
		this.downloadUrl=downloadUrl;
	}
	
	public String getDownloadUrl(){
		return downloadUrl;
	}
		
}

