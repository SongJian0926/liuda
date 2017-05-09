package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: AddressVo
 * @Description:收货地址 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="收货地址Vo对象")
public class AddressVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="收货人,收货人")
	private String consigneeName;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="省份,省份")
	private String province;
	@ApiModelProperty(value="城市,城市")
	private String city;
	@ApiModelProperty(value="区域,区域")
	private String area;
	@ApiModelProperty(value="详细地址,详细地址")
	private String detailAddress;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId ;
	@ApiModelProperty(value="是否默认,是否默认   0：否，1：是")
	private Integer isDefault;
	@ApiModelProperty(value="用户名,用户名")
	private String userName;
	@ApiModelProperty(value = "状态，0为无效，1为正常,2为发布,3删除 参看XaConstant.Status")
	private Integer status;
	
	public AddressVo(Long id,String consigneeName,String mobile,String province,String city,String area,String detailAddress,Long userId ,Integer isDefault) {
		this.id = id;
		this.consigneeName = consigneeName;
		this.mobile = mobile;
		this.province = province;
		this.city = city;
		this.area = area;
		this.detailAddress = detailAddress;
		this.userId  = userId ;
		this.isDefault = isDefault;
	}
	
	public AddressVo() {
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
	
		public void setConsigneeName(String consigneeName){
		this.consigneeName=consigneeName;
	}
	
	public String getConsigneeName(){
		return consigneeName;
	}
		public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
		public void setProvince(String province){
		this.province=province;
	}
	
	public String getProvince(){
		return province;
	}
		public void setCity(String city){
		this.city=city;
	}
	
	public String getCity(){
		return city;
	}
		public void setArea(String area){
		this.area=area;
	}
	
	public String getArea(){
		return area;
	}
		public void setDetailAddress(String detailAddress){
		this.detailAddress=detailAddress;
	}
	
	public String getDetailAddress(){
		return detailAddress;
	}
		public void setUserId (Long userId ){
		this.userId =userId ;
	}
	
	public Long getUserId (){
		return userId ;
	}
		public void setIsDefault(Integer isDefault){
		this.isDefault=isDefault;
	}
	
	public Integer getIsDefault(){
		return isDefault;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
		
}

