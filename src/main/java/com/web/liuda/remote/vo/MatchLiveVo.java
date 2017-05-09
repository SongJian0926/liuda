package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MatchLiveVo
 * @Description:赛事直播 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="赛事直播Vo对象")
public class MatchLiveVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="直播内容,直播内容")
	private String content;
	@ApiModelProperty(value="视频,视频")
	private String mediaPath;
	@ApiModelProperty(value="图片,图片")
	private String mediaImg;
		
	public MatchLiveVo(Long id,Long matchId,String content,String mediaPath) {
		this.id = id;
		this.matchId = matchId;
		this.content = content;
		this.mediaPath = mediaPath;
	}
	
	public MatchLiveVo() {
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
	
		public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	public Long getMatchId(){
		return matchId;
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

	public String getMediaImg() {
		return mediaImg;
	}

	public void setMediaImg(String mediaImg) {
		this.mediaImg = mediaImg;
	}
		
}

