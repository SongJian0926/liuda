package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: CollectVo
 * @Description:关注 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="关注Vo对象")
public class CollectVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="对象Id,对象Id:活动Id,赛事Id")
	private Long objectId;
	@ApiModelProperty(value="类型,类型:1.赛事，2.活动")
	private Integer type;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
		
	public CollectVo(Long id,Long objectId,Integer type,Long userId) {
		this.id = id;
		this.objectId = objectId;
		this.type = type;
		this.userId = userId;
	}
	
	public CollectVo() {
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
	
		public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	public Long getObjectId(){
		return objectId;
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
		
}

