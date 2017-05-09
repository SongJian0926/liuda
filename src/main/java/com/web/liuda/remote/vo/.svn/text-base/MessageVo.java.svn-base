package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MessageVo
 * @Description:消息中心 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="消息中心Vo对象")
public class MessageVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
	@ApiModelProperty(value="消息标题,消息标题")
	private String title;
 	@ApiModelProperty(value="消息内容,消息内容")
	private String content;
	@ApiModelProperty(value="消息类型,消息类型，0、系统消息 1、个人消息")
	private Integer type;
	@ApiModelProperty(value="用户Id,用户Id，当为系统消息时该字段为空")
	private Long userId;
	@ApiModelProperty(value="状态")
	private Integer status;
	@ApiModelProperty(value="用户名,用户名")
	private String userName;
	@ApiModelProperty(value="类型名字")
	private String typeName;
	@ApiModelProperty(value="用于标示是否未读，0：未读，1：已读")
	private Integer isRead;	
	public MessageVo(Long id,String content,String typeName,String userName,Integer status)
	{
		this.id=id;
		this.content=content;
		this.typeName=typeName;
		this.userName=userName;
		this.status=status;
	}
	public MessageVo(Long id,String content,Integer type,String userName,Integer status)
	{
		this.id=id;
		this.content=content;
		this.type=type;
		this.userName=userName;
		this.status=status;
	}
	public MessageVo(Long id,String content,Integer type,Long userId) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.userId = userId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public MessageVo() {
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
	
		public void setContent(String content){
		this.content=content;
	}
	
	public String getContent(){
		return content;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
		
}

