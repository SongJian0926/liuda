package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: GuideAppendVo
 * @Description:攻略追加表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="攻略追加表Vo对象")
public class GuideAppendVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="攻略Id,攻略Id")
	private Long guideId;
	@ApiModelProperty(value="内容,追加内容")
	private String content;
	@ApiModelProperty(value="图片,图片")
	private String mediaPath;
	@ApiModelProperty(value="后缀,图片后缀")
	private String mediaLastName;
		
	public GuideAppendVo(Long id,Long guideId,String content,String mediaPath) {
		this.id = id;
		this.guideId = guideId;
		this.content = content;
		this.mediaPath = mediaPath;
	}
	
	public GuideAppendVo() {
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
	
		public void setGuideId(Long guideId){
		this.guideId=guideId;
	}
	
	public Long getGuideId(){
		return guideId;
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

	public String getMediaLastName() {
		return mediaLastName;
	}

	public void setMediaLastName(String mediaLastName) {
		this.mediaLastName = mediaLastName;
	}
		
}

