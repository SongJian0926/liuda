package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ProvinceVo
 * @Description:省份表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="省份表Vo对象")
public class ProvinceVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="省份代码,省份代码")
	private String provinceCode;
	@ApiModelProperty(value="省份名称,省份名称")
	private String provinceName;
		
	public ProvinceVo(Long id,String provinceCode,String provinceName) {
		this.id = id;
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
	}
	
	public ProvinceVo() {
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
		public void setProvinceName(String provinceName){
		this.provinceName=provinceName;
	}
	
	public String getProvinceName(){
		return provinceName;
	}
		
}

