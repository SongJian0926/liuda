package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: EventReviewVo
 * @Description:俱乐部活动回顾表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="俱乐部活动回顾表Vo对象")
public class EventReviewVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="回顾标题,标题")
	private String title;
	@ApiModelProperty(value="内容,内容")
	private String content;
	@ApiModelProperty(value="图片或者视频,图片或者视频")
	private String mediaPath;
	@ApiModelProperty(value="发布人,发布人")
	private Long userId;
	@ApiModelProperty(value="活动Id,活动Id")
	private Long eventId;
	//俱乐部活动留言列表使用
	@ApiModelProperty(value="用户实体")
	private UserVo userVo;
		
	public EventReviewVo(Long id,String title,String content,String mediaPath,Long userId,Long eventId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.mediaPath = mediaPath;
		this.userId = userId;
		this.eventId = eventId;
	}
	
	public EventReviewVo() {
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
	
		public void setTitle(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
		public void setContent(String content){
		this.content=content;
	}
	
	public String getContent(){
		return content;
	}
		public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	public String getMediaPath(){
		return mediaPath;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setEventId(Long eventId){
		this.eventId=eventId;
	}
	
	public Long getEventId(){
		return eventId;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
		
}

