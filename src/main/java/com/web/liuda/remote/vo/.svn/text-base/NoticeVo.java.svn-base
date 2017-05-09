package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: NoticeVo
 * @Description:公告通知 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="公告通知Vo对象")
public class NoticeVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	@ApiModelProperty(value="创建时间")
	private String createTime;
 	@ApiModelProperty(value="标题,标题")
	private String title;
 	@ApiModelProperty(value="简介,简介")
	private String introduce;
	@ApiModelProperty(value="内容,内容")
	private String content;
	@ApiModelProperty(value="图片,图片")
	private String imgPath;
	@ApiModelProperty(value="是否推送,0:否，1:是")
	private Integer needPush;
	@ApiModelProperty(value="公告类型,1:黑板报，2:活动公告，3:新闻报道")
	private Integer type;
		
	public NoticeVo(Long id,String title,String content,String imgPath,Integer needPush,Integer type) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.imgPath = imgPath;
		this.needPush = needPush;
		this.type = type;
	}
	
	public NoticeVo() {
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
		public void setImgPath(String imgPath){
		this.imgPath=imgPath;
	}
	
	public String getImgPath(){
		return imgPath;
	}
		public void setNeedPush(Integer needPush){
		this.needPush=needPush;
	}
	
	public Integer getNeedPush(){
		return needPush;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
		
}

