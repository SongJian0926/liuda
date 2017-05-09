package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ClubMemberVo
 * @Description:俱乐部成员 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="俱乐部成员Vo对象")
public class ClubMemberVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="俱乐部Id,俱乐部Id")
	private Long clubId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="成员类型,1.部长，2.管理员，3.普通成员 ")
	private Integer memberType;
	@ApiModelProperty(value="申请状态,1.审核中，2.审核成功，3.审核失败,4.邀请中")
	private Integer applyStatus;
	//成员列表
	@ApiModelProperty(value="用户实体")
	private UserVo userVo;
	@ApiModelProperty(value="俱乐部实体")
	private List<ClubVo> clubVos;
	@ApiModelProperty(value="攻略实体")
	private List<GuideVo> guideVos;
	//个人主页使用
	@ApiModelProperty(value="是否是我关注的人,1.是，0.否")
	private Integer isFriend;	
	//个人主页使用
	@ApiModelProperty(value="个人主页使用，关注Id")
	private Long fansId;	
	
	public ClubMemberVo(Long id,Long clubId,Long userId,Integer memberType,Integer applyStatus) {
		this.id = id;
		this.clubId = clubId;
		this.userId = userId;
		this.memberType = memberType;
		this.applyStatus = applyStatus;
	}
	
	public ClubMemberVo() {
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
	
		public void setClubId(Long clubId){
		this.clubId=clubId;
	}
	
	public Long getClubId(){
		return clubId;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setMemberType(Integer memberType){
		this.memberType=memberType;
	}
	
	public Integer getMemberType(){
		return memberType;
	}
		public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	public Integer getApplyStatus(){
		return applyStatus;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public List<ClubVo> getClubVos() {
		return clubVos;
	}

	public void setClubVos(List<ClubVo> clubVos) {
		this.clubVos = clubVos;
	}

	public List<GuideVo> getGuideVos() {
		return guideVos;
	}

	public void setGuideVos(List<GuideVo> guideVos) {
		this.guideVos = guideVos;
	}

	public Integer getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Integer isFriend) {
		this.isFriend = isFriend;
	}

	public Long getFansId() {
		return fansId;
	}

	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}

	
}

