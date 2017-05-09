package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web.webstart.base.entity.BaseEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: ClubMember 
* @Description: 俱乐部成员定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_clubmember")
@ApiModel(value="俱乐部成员定义表")
public class ClubMember extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="俱乐部Id,俱乐部Id")
	private Long clubId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="成员类型,1.部长，2.管理员，3.普通成员 ")
	private Integer memberType;
	@ApiModelProperty(value="申请状态,1.审核中，2.审核成功，3.审核失败,4.邀请中")
	private Integer applyStatus;
	
	
	public void setClubId(Long clubId){
		this.clubId=clubId;
	}
	
	@Column(nullable=true,length=20)
	public Long getClubId(){
		return clubId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setMemberType(Integer memberType){
		this.memberType=memberType;
	}
	
	@Column(nullable=true,length=11)
	public Integer getMemberType(){
		return memberType;
	}
	public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	@Column(nullable=true,length=11)
	public Integer getApplyStatus(){
		return applyStatus;
	}
	
	public ClubMember() {
		super();
	}
		

}
