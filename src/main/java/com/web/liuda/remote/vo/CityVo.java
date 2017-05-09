package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: CityVo
 * @Description:城市表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="城市表Vo对象")
public class CityVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="省份代码,省份代码")
	private String provinceCode;
	@ApiModelProperty(value="城市代码,城市代码")
	private String cityCode;
	@ApiModelProperty(value="城市名称,城市名称")
	private String cityName;
		
	public CityVo(Long id,String provinceCode,String cityCode,String cityName) {
		this.id = id;
		this.provinceCode = provinceCode;
		this.cityCode = cityCode;
		this.cityName = cityName;
	}
	
	public CityVo() {
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
	
		public void setProvinceCode(String provinceCode){
		this.provinceCode=provinceCode;
	}
	
	public String getProvinceCode(){
		return provinceCode;
	}
		public void setCityCode(String cityCode){
		this.cityCode=cityCode;
	}
	
	public String getCityCode(){
		return cityCode;
	}
		public void setCityName(String cityName){
		this.cityName=cityName;
	}
	
	public String getCityName(){
		return cityName;
	}
		
}

