package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: AreaVo
 * @Description:区域表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="区域表Vo对象")
public class AreaVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="城市代码,城市代码")
	private String cityCode;
	@ApiModelProperty(value="区域代码,区域代码")
	private String areaCode;
	@ApiModelProperty(value="区域名称,区域名称")
	private String areaName;
		
	public AreaVo(Long id,String cityCode,String areaCode,String areaName) {
		this.id = id;
		this.cityCode = cityCode;
		this.areaCode = areaCode;
		this.areaName = areaName;
	}
	
	public AreaVo() {
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
	
		public void setCityCode(String cityCode){
		this.cityCode=cityCode;
	}
	
	public String getCityCode(){
		return cityCode;
	}
		public void setAreaCode(String areaCode){
		this.areaCode=areaCode;
	}
	
	public String getAreaCode(){
		return areaCode;
	}
		public void setAreaName(String areaName){
		this.areaName=areaName;
	}
	
	public String getAreaName(){
		return areaName;
	}
		
}

