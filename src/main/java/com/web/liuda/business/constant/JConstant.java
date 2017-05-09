package com.web.liuda.business.constant;

/**
 * 蹓跶蹓跶项目中使用到的常量
 * @author zhanglin
 * date: 2015-12-01 16:41:24
 */
public class JConstant {
	
	/**
	 * 酒店类型（等级）
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class HotelType{
		
		/**
		 * 一星级
		 */
		public static final Integer FRIST_STAR = 1;
		
		/**
		 * 二星级
		 */
		public static final Integer SECOND_STAR = 2;
		
		/**
		 * 三星级
		 */
		public static final Integer THREE_STAR = 3;
		
		/**
		 * 四星级
		 */
		public static final Integer FOUR_STAR = 4;
		
		/**
		 * 五星级
		 */
		public static final Integer FIVE_STAR = 5;

		/**
		 * 经济型
		 */
		public static final Integer ECONOMIC = 6;
		
		/**
		 * 快捷型
		 */
		public static final Integer SHORTCUT = 7;
		
		/**
		 * 商务型
		 */
		public static final Integer COMMERCE = 8;
		
	}
	
	/**
	 * Boolean类型
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class BooleanStatus{
		
		/**
		 * 否
		 */
		public static final Integer FALSE = 0;
		
		/**
		 * 是
		 */
		public static final Integer TRUE = 1;
		
	}
	
	/**
	 * 图片类型
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class ImageType{
		
		/**
		 * 酒店(轮播图)
		 */
		public static final Integer HOTEL = 0;
		
		/**
		 * 酒店介绍(介绍图)
		 */
		public static final Integer HOTEL_INTRO = 1;
		
		/**
		 * 评论
		 */
		public static final Integer COMMENT = 2;
		
		/**
		 * 景点(轮播图)
		 */
		public static final Integer TOURIST = 3;
		
		/**
		 * 商品(轮播图)
		 */
		public static final Integer SHOP = 4;
		
		/**
		 * 景点介绍(介绍图)
		 */
		public static final Integer TOURIST_INTRO = 5;
		
		/**
		 *商品介绍(介绍图)
		 */
		public static final Integer SHOP_INTRO = 6;
		/**
		 *回复
		 */
		public static final Integer REPLY = 7;
		/**
		 * author:yanghui
		 *房间(轮播图)
		 */
		public static final Integer room = 8;
		/**
		 * author:yanghui
		 *门票(轮播图)
		 */
		public static final Integer tickets = 9;
	}
	
	/**
	 * 对象类型
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class ObjectType{
		
		/**
		 * 酒店
		 */
		public static final Integer HOTEL = 0;
		
		/**
		 * 景点
		 */
		public static final Integer TOURIST = 1;
		
		/**
		 * 商品
		 */
		public static final Integer SHOP = 2;

		/**
		 * 赛事门票
		 */
		public static final Integer MATCHTICKET = 3;
	}
	
	/**
	 * 订单状态
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class OrderStatus{
		
		/**
		 * 未支付
		 */
		public static final Integer UN_PAY = 0;
		
		/**
		 * 已支付(待消费、待发货)
		 */
		public static final Integer PAYED = 1;
		
		/**
		 * 待评价
		 */
		public static final Integer UN_COMMENT = 2;
		
		/**
		 * 已评价
		 */
		public static final Integer COMMENTED = 3;
		
		/**
		 * 待收货
		 */
		public static final Integer UN_CONSIGNEE = 4;
		
		/**
		 * 已收货
		 */
		public static final Integer CONSIGNEED = 5;
		/**
		 * 失效
		 */
		public static final Integer INVALID = 6;
		
	}
	/**
	 * 公告类型
	 * @author cahnglu
	 * date: 2016-4-08 10:41:24
	 */
	public static class NoticeType{
		
		/**
		 * 黑板报
		 */
		public static final Integer BLACKBOARD = 1;
		
		/**
		 * 活动公告
		 */
		public static final Integer ACTIVITYNOTICE = 2;
		
		/**
		 * 新闻报道
		 */
		public static final Integer NEWS = 3;
		
		
	}
	/**
	 * 字典类型
	 * @author cahnglu
	 * date: 2016-4-08 10:41:24
	 */
	public static class DictType{
		
		/**
		 * 攻略标签
		 */
		public static final Integer GUIDEITEM = 1;
		
		/**
		 * 赛事标签
		 */
		public static final Integer GUESSITEM = 2;
		/**
		 * 俱乐部类型
		 */
		public static final Integer CLUBTYPE = 3;
		
		/**
		 * 俱乐部等级
		 */
		public static final Integer CLUBLEVEL = 4;
		/**
		 * 俱乐部兴趣标签
		 */
		public static final Integer CLUBINTEREST = 5;
		
		
	}
	/**
	 * 审核状态（俱乐部）
	 * @author cahnglu
	 * date: 2016-4-08 10:41:24
	 */
	public static class ApplyStatus{
		/**
		 * 审核中
		 */
		public static final Integer CHECK = 1;
		/**
		 * 审核成功
		 */
		public static final Integer CHECKSUCCEED = 2;
		/**
		 * 审核失败
		 */
		public static final Integer CHECKFAIL = 3;
		/**
		 * 邀请中
		 */
		public static final Integer INVITING = 4;
	}
	/**
	 * 成员角色（俱乐部）
	 * @author cahnglu
	 * date: 2016-4-08 10:41:24
	 */
	public static class ClubRole{
		/**
		 * 部长
		 */
		public static final Integer MINISTER = 1;
		/**
		 * 管理员
		 */
		public static final Integer ADMIN = 2;
		/**
		 * 普通成员
		 */
		public static final Integer MEMBER = 3;
	}
	
	
	/**
	 * 活动状态（俱乐部）
	 * @author cahnglu
	 * date: 2016-4-13 10:41:24
	 */
	public static class EventStatus{
		
		/**
		 * 报名中
		 */
		public static final Integer ENROLL = 1;
		
		/**
		 * 活动未开始
		 */
		public static final Integer NOBEGIN = 2;
		/**
		 * 活动进行中
		 */
		public static final Integer EVENTING = 3;
		/**
		 * 已结束
		 */
		public static final Integer EVENTEND = 4;
	}
	/**
	 * 赛事、俱乐部订单类型
	 * @author cahnglu
	 * date: 2016-4-13 14:41:24
	 */
	public static class MatchOrderType{
		/**
		 * 赛事订单
		 */
		public static final Integer MATCHORDER = 1;
		/**
		 * 俱乐部活动订单
		 */
		public static final Integer EVENTORDER = 2;
		
	}
	/**
	 * 赛事、俱乐部订单状态
	 * @author cahnglu
	 * date: 2016-4-18 14:41:24
	 */
	public static class MatchOrderStatus{
		/**
		 * 未支付
		 */
		public static final Integer UNPAY = 1;
		/**
		 * 已支付
		 */
		public static final Integer ENROLLSUCCESS = 2;
		/**
		 * 取消报名
		 */
		public static final Integer CANCELENROLL = 3;
		/**
		 * 订单失效
		 */
		public static final Integer ORDERFAILURE = 4;
	}
	/**
	 * 赛事、俱乐部订单退货状态
	 * @author changlu
	 * date: 2016-4-20 14:41:24
	 */
	public static class RefundStatus{
		/**
		 *正常状态
		 */
		public static final Integer VALID = 0;
		/**
		 * 退款申请中
		 */
		public static final Integer APPLYREFUND = 1;
		/**
		 * 退款审核失败
		 */
		public static final Integer REFUNDFAIL = 2;
		/**
		 * 退款中
		 */
		public static final Integer REFUNDING = 3;
		/**
		 * 退款成功
		 */
		public static final Integer REFUNDSUCCESS = 4;
		
	}
//	/**
//	 * 赛事状态                                                                                                    
//	 * @author flora.li
//	 * date: 2016-4-22 14:41:24
//	 */
//	public static class MatchStatus{
//		/**
//		 * 售票中
//		 */
//		public static final Integer TICKETING = 1;
//		/**
//		 * 售票中（参赛）
//		 */
//		public static final Integer TICKETINGJOIN = 2;
//		/**
//		 * 售票中（观赛）
//		 */
//		public static final Integer TICKETINGVIEW = 3;
//		/**
//		 * 比赛未开始
//		 */
//		public static final Integer NOBEGIN = 4;
//		/**
//		 * 比赛中
//		 */
//		public static final Integer MATCHING = 5;
//		/**
//		 * 已结束
//		 */
//		public static final Integer MATCHEND = 6;
//	}
	
	
	/**
	 * 用户提现状态
	 * @author cahnglu
	 * date: 2016-5-10 14:41:24
	 */
	public static class PresentState{
		/**
		 *已提交
		 */
		public static final Integer SUBMIT = 1;
		/**
		 * 打款成功
		 */
		public static final Integer TRANSFERSUCCESS = 2;
		/**
		 * 打款失败
		 */
		public static final Integer TRANSFERFAIL = 3;
		/**
		 * 打款中
		 */
		public static final Integer SENDING = 4;
		
		
	}
	
	/**
	 * 赛事类型
	 */
	public static class MatchType{
		/**
		 *商家赛事
		 */
		public static final Integer SELLER = 1;
		/**
		 * 官方活动
		 */
		public static final Integer OFFICIAL = 2;
	}
	
	/**
	 * 
	 * */
	public static class PayType{
		/**
		 * 微信
		 */
		public static final int WXPAY = 0;
		/**
		 * 支付宝
		 */
		public static final int ALIPAY = 1;
		/**
		 * 银联
		 */
		public static final int UNIONPAY = 2;
	}
}
