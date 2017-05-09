package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: GuideVo
 * @Description:攻略表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="攻略表Vo对象")
public class GuideVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="标题,标题")
	private String title;
	@ApiModelProperty(value="类型,0：普通;1：精品")
	private Integer type;
	@ApiModelProperty(value="浏览量,浏览量")
	private Integer pageview;
	@ApiModelProperty(value="对象Id,门票或酒店ID")
	private Long objectId;
	@ApiModelProperty(value="对象类型,0:酒店；1：门票")
	private Integer objectType;
	@ApiModelProperty(value="申精状态,1：申请中，2，申请通过，3，申请失败")
	private Integer applyStatus;
	@ApiModelProperty(value="审核备注,审核人员填写的审核备注")
	private String applyMemo;
	@ApiModelProperty(value="最后更新日期,最后更新日期")
	private String lastUpdate;
	@ApiModelProperty(value="兴趣标签,取至字典表")
	private Long dictItemId;
	@ApiModelProperty(value="状态,1：正常，2，发布，3，删除，-1，锁定")
	private Integer status;
	
	/**
	 * author:changlu
	 * time:2016-04-08 16:25:00 
	 * app端攻略列表使用
	 */
	@ApiModelProperty(value="用户表")
	private UserVo userVo;
	@ApiModelProperty(value="攻略追加表")
	private GuideAppendVo guideAppendVo;
	@ApiModelProperty(value="字典表")
	private DictItemVo dictItemVo;
	/**
	 * author:changlu
	 * time:2016-04-08 16:25:00 
	 * app端攻略详情使用
	 */
	@ApiModelProperty(value="攻略追加内容集合")
	private List<GuideAppendVo> guideAppendVos;
	@ApiModelProperty(value="攻略详情中提到的酒店或者景点")
	private HotelVo hotelVo;
	@ApiModelProperty(value="门票")
	private TicketsVo ticketsVo;
	@ApiModelProperty(value="对象名称")
	private String objectName;
	@ApiModelProperty(value="发布内容")
	private String content;
		
	public GuideVo(Long id,Long userId,String title,Integer type,Integer pageview,Long objectId,Integer objectType,Integer applyStatus,String applyMemo,String lastUpdate) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.type = type;
		this.pageview = pageview;
		this.objectId = objectId;
		this.objectType = objectType;
		this.applyStatus = applyStatus;
		this.applyMemo = applyMemo;
		this.lastUpdate = lastUpdate;
	}
	
	public GuideVo() {
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
	
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setTitle(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}
		public void setPageview(Integer pageview){
		this.pageview=pageview;
	}
	
	public Integer getPageview(){
		return pageview;
	}
		public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	public TicketsVo getTicketsVo() {
			return ticketsVo;
		}

		public void setTicketsVo(TicketsVo ticketsVo) {
			this.ticketsVo = ticketsVo;
		}

	public Long getObjectId(){
		return objectId;
	}
		public void setObjectType(Integer objectType){
		this.objectType=objectType;
	}
	
	public Integer getObjectType(){
		return objectType;
	}
		public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	public Integer getApplyStatus(){
		return applyStatus;
	}
		public void setApplyMemo(String applyMemo){
		this.applyMemo=applyMemo;
	}
	
	public String getApplyMemo(){
		return applyMemo;
	}
	public void setLastUpdate(String lastUpdate){
		this.lastUpdate=lastUpdate;
	}
	
	public String getLastUpdate(){
		return lastUpdate;
	}

	public Long getDictItemId() {
		return dictItemId;
	}

	public void setDictItemId(Long dictItemId) {
		this.dictItemId = dictItemId;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public GuideAppendVo getGuideAppendVo() {
		return guideAppendVo;
	}

	public void setGuideAppendVo(GuideAppendVo guideAppendVo) {
		this.guideAppendVo = guideAppendVo;
	}

	public DictItemVo getDictItemVo() {
		return dictItemVo;
	}

	public void setDictItemVo(DictItemVo dictItemVo) {
		this.dictItemVo = dictItemVo;
	}

	public List<GuideAppendVo> getGuideAppendVos() {
		return guideAppendVos;
	}

	public void setGuideAppendVos(List<GuideAppendVo> guideAppendVos) {
		this.guideAppendVos = guideAppendVos;
	}

	public HotelVo getHotelVo() {
		return hotelVo;
	}

	public void setHotelVo(HotelVo hotelVo) {
		this.hotelVo = hotelVo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
		
}

