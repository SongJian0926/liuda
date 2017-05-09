package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: EventMsgVo
 * @Description:俱乐部留言表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="俱乐部留言表Vo对象")
public class EventMsgVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="评论或者回复内容,comment_id不为空，表示针对comment_id进行回复；comment_id为空，表示评论 ")
	private String content;
	@ApiModelProperty(value="活动Id,活动Id")
	private Long eventId;
	@ApiModelProperty(value="留言表Id,留言表Id")
	private Long event_msg_id;
	@ApiModelProperty(value="图片,评论或者回复的图片")
	private String mediaPath;
	//俱乐部活动留言列表使用
	@ApiModelProperty(value="用户实体")
	private UserVo userVo;
	private List<EventMsgVo> eventMsgVos;
	
	public EventMsgVo(Long id,Long userId,String content,Long eventId,Long event_msg_id,String mediaPath) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.eventId = eventId;
		this.event_msg_id = event_msg_id;
		this.mediaPath = mediaPath;
	}
	
	public EventMsgVo() {
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
		public void setEventId(Long eventId){
		this.eventId=eventId;
	}
	
	public Long getEventId(){
		return eventId;
	}
		public void setEvent_msg_id(Long event_msg_id){
		this.event_msg_id=event_msg_id;
	}
	
	public Long getEvent_msg_id(){
		return event_msg_id;
	}
		public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	public String getMediaPath(){
		return mediaPath;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public List<EventMsgVo> getEventMsgVos() {
		return eventMsgVos;
	}

	public void setEventMsgVos(List<EventMsgVo> eventMsgVos) {
		this.eventMsgVos = eventMsgVos;
	}
		
}

