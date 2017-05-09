package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: RulesDesc 
* @Description: 条款注意事项表定义表  (暂时不用)
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_rulesdesc")
@ApiModel(value="条款注意事项表定义表")
public class RulesDesc extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="内容,内容,每个模块只有一条说明")
	private String content;
	@ApiModelProperty(value="类型,1.竞猜说明，2.投票说明，3.报名协议")
	private Integer type;
	@ApiModelProperty(value="是否推送,0::否，1:是")
	private Integer needPush;
		
	
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
	
	@Column(nullable=true,length=11)
	public Integer getType(){
		return type;
	}
	public void setNeedPush(Integer needPush){
		this.needPush=needPush;
	}
	
	@Column(nullable=true,length=11)
	public Integer getNeedPush(){
		return needPush;
	}
		

}
