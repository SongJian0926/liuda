package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: IndexVo
 * @Description:首页 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="首页Vo对象")
public class IndexVo {
	
	@ApiModelProperty(value="酒店信息")
	private List<HotelVo> hotels;
	
	@ApiModelProperty(value="商品信息")
	private List<ShopVo> shops;
	
	@ApiModelProperty(value="景点信息")
	private List<TouristVo> tourists;
	
	@ApiModelProperty(value="团购信息")
	private List<GroupBuyVo> groups;
	
	@ApiModelProperty(value="攻略信息")
	private List<GuideVo> guides;
	
	@ApiModelProperty(value="赛事信息")
	private List<MacthVo> matchs;
	
	@ApiModelProperty(value="江湖信息")
	private List<ClubVo> clubs;
	
	@ApiModel(value="团购Vo对象")
	public static class GroupBuyVo{
		
		@ApiModelProperty(value="id")
		private Long id;
		@ApiModelProperty(value="标题")
		private String title;
		@ApiModelProperty(value="价格")
		private Double price;
		@ApiModelProperty(value="图片")
		private String imgUrl;
		@ApiModelProperty(value="类型")
		private String type;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	}

	public List<HotelVo> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelVo> hotels) {
		this.hotels = hotels;
	}

	public List<ShopVo> getShops() {
		return shops;
	}

	public void setShops(List<ShopVo> shops) {
		this.shops = shops;
	}

	public List<TouristVo> getTourists() {
		return tourists;
	}

	public void setTourists(List<TouristVo> tourists) {
		this.tourists = tourists;
	}

	public List<GroupBuyVo> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupBuyVo> groups) {
		this.groups = groups;
	}

	public List<GuideVo> getGuides() {
		return guides;
	}

	public void setGuides(List<GuideVo> guides) {
		this.guides = guides;
	}

	public List<MacthVo> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<MacthVo> matchs) {
		this.matchs = matchs;
	}

	public List<ClubVo> getClubs() {
		return clubs;
	}

	public void setClubs(List<ClubVo> clubs) {
		this.clubs = clubs;
	}
	
}
