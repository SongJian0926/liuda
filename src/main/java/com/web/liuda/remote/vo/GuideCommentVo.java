package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: GuideCommentVo
 * @Description:攻略评论表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="攻略评论表Vo对象")
public class GuideCommentVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="评论内容,评论内容")
	private String content;
	@ApiModelProperty(value="攻略Id,攻略Id")
	private Long guideId;
	@ApiModelProperty(value="用户实体")
	private UserVo userVo;
		
	public GuideCommentVo(Long id,Long userId,String content,Long guideId) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.guideId = guideId;
	}
	
	public GuideCommentVo() {
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
	
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setContent(String content){
		this.content=content;
	}
	
	public String getContent(){
		return content;
	}
		public void setGuideId(Long guideId){
		this.guideId=guideId;
	}
	
	public Long getGuideId(){
		return guideId;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
		
}

