package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: FansVo
 * @Description:粉丝表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="粉丝表Vo对象")
public class FansVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="粉丝Id,粉丝Id")
	private Long fansId;
	@ApiModelProperty(value="用户实体")
	private UserVo userVo;
	@ApiModelProperty(value="粉丝数量")
	private Integer fansNum;
	@ApiModelProperty(value="关注数量")
	private Integer closeNum;
	@ApiModelProperty(value="关注集合,关注")
	private List<FansVo> myCloseList;
	@ApiModelProperty(value="我的好友,我的好友")
	private List<FansVo> myMyFrindList;
	
	public FansVo(Long id,Long userId,Long fansId) {
		this.id = id;
		this.userId = userId;
		this.fansId = fansId;
	}
	
	public FansVo() {
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
	
	public Integer getFansNum() {
			return fansNum;
		}

		public void setFansNum(Integer fansNum) {
			this.fansNum = fansNum;
		}
		
	public List<FansVo> getMyMyFrindList() {
			return myMyFrindList;
		}

		public void setMyMyFrindList(List<FansVo> myMyFrindList) {
			this.myMyFrindList = myMyFrindList;
		}

		
	public Integer getCloseNum() {
			return closeNum;
		}

		public void setCloseNum(Integer closeNum) {
			this.closeNum = closeNum;
		}

		public List<FansVo> getMyCloseList() {
			return myCloseList;
		}

		public void setMyCloseList(List<FansVo> myCloseList) {
			this.myCloseList = myCloseList;
		}

	public Long getUserId(){
		return userId;
	}
		public void setFansId(Long fansId){
		this.fansId=fansId;
	}
	
	public Long getFansId(){
		return fansId;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
		
}

