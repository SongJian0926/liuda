package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Guide 
* @Description: 攻略表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_guide")
@ApiModel(value="攻略表定义表")
public class Guide extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="标题,标题")
	private String title;
	@ApiModelProperty(value="类型,0：普通;1：精品")
	private Integer type;
	@ApiModelProperty(value="浏览量,浏览量")
	private Integer pageview;
	@ApiModelProperty(value="对象Id,门票或酒店ID")
	private Long objectId;
	@ApiModelProperty(value="对象类型,0:酒店；1：门票")
	private Integer objectType;
	@ApiModelProperty(value="申精状态,1：申请中，2，申请通过，3，申请失败")
	private Integer applyStatus;
	@ApiModelProperty(value="审核备注,审核人员填写的审核备注")
	private String applyMemo;
	@ApiModelProperty(value="最后更新日期,最后更新日期")
	private String lastUpdate;
	@ApiModelProperty(value="兴趣标签,取至字典表")
	private Long dictItemId;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	
	@Column(nullable=true,length=255)
	public String getTitle(){
		return title;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=true,length=11)
	public Integer getType(){
		return type;
	}
	public void setPageview(Integer pageview){
		this.pageview=pageview;
	}
	
	@Column(nullable=true,length=11)
	public Integer getPageview(){
		return pageview;
	}
	public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	@Column(nullable=true,length=20)
	public Long getObjectId(){
		return objectId;
	}
	public void setObjectType(Integer objectType){
		this.objectType=objectType;
	}
	
	@Column(nullable=true,length=11)
	public Integer getObjectType(){
		return objectType;
	}
	public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	@Column(nullable=true,length=11)
	public Integer getApplyStatus(){
		return applyStatus;
	}
	public void setApplyMemo(String applyMemo){
		this.applyMemo=applyMemo;
	}
	
	@Column(nullable=true,length=255)
	public String getApplyMemo(){
		return applyMemo;
	}
	public void setLastUpdate(String lastUpdate){
		this.lastUpdate=lastUpdate;
	}
	
	@Column(nullable=true,length=50)
	public String getLastUpdate(){
		return lastUpdate;
	}
	@Column(nullable=true,length=20)
	public Long getDictItemId() {
		return dictItemId;
	}

	public void setDictItemId(Long dictItemId) {
		this.dictItemId = dictItemId;
	}
		

}
