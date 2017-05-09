package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: FeedBackVo
 * @Description:意见反馈 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="意见反馈Vo对象")
public class FeedBackVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="反馈内容,反馈内容")
	private String content;
	@ApiModelProperty(value="qq,qq")
	private String qq;
	@ApiModelProperty(value="微信,微信")
	private String wechat;
	@ApiModelProperty(value="微博,微博")
	private String weibo;	
	private String username;
	

	public FeedBackVo(Long id, String createTime, Long userId, String content,
			String qq, String wechat, String weibo, String username) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.userId = userId;
		this.content = content;
		this.qq = qq;
		this.wechat = wechat;
		this.weibo = weibo;
		this.username = username;
	}

	public FeedBackVo() {
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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
		
}

