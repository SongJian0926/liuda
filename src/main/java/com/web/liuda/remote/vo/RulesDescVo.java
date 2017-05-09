package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: RulesDescVo
 * @Description:条款注意事项表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="条款注意事项表Vo对象")
public class RulesDescVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="内容,内容,每个模块只有一条说明")
	private String content;
	@ApiModelProperty(value="类型,1.竞猜说明,2.投票说明,3.报名协议")
	private Integer type;
	@ApiModelProperty(value="是否推送,0::否，1:是")
	private Integer needPush;
		
	public RulesDescVo(Long id,String content,Integer type,Integer needPush) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.needPush = needPush;
	}
	
	public RulesDescVo() {
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
		public void setNeedPush(Integer needPush){
		this.needPush=needPush;
	}
	
	public Integer getNeedPush(){
		return needPush;
	}
		
}

