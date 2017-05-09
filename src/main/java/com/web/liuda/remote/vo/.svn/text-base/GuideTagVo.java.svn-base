package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: GuideTagVo
 * @Description:攻略标签表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="攻略标签表Vo对象")
public class GuideTagVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="攻略Id,攻略Id")
	private Long guideId;
	@ApiModelProperty(value="字典子表Id,字典子表Id")
	private Long dictItemId;
		
	public GuideTagVo(Long id,Long guideId,Long dictItemId) {
		this.id = id;
		this.guideId = guideId;
		this.dictItemId = dictItemId;
	}
	
	public GuideTagVo() {
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
		public void setDictItemId(Long dictItemId){
		this.dictItemId=dictItemId;
	}
	
	public Long getDictItemId(){
		return dictItemId;
	}
		
}

