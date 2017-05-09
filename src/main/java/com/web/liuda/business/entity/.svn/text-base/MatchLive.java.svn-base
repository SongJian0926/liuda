package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: MatchLive 
* @Description: 赛事直播定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_matchlive")
@ApiModel(value="赛事直播定义表")
public class MatchLive extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="直播内容,直播内容")
	private String content;
	@ApiModelProperty(value="视频,视频")
	private String mediaPath;
	@ApiModelProperty(value="图片,图片")
	private String mediaImg;
		
	
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=1000)
	public String getContent(){
		return content;
	}
	public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	@Column(nullable=true,length=2000)
	public String getMediaPath(){
		return mediaPath;
	}

	@Column(nullable=true,length=2000)
	public String getMediaImg() {
		return mediaImg;
	}

	public void setMediaImg(String mediaImg) {
		this.mediaImg = mediaImg;
	}
		

}
