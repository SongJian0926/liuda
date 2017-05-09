<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>订单详情</title>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<!-- <link href="bootatrap/css/bootstrap.css" rel="stylesheet" type="text/css" > -->
		<link rel="stylesheet" type="text/css" href="../css/orderDetail.css">
		<script type="text/javascript" src="../js/orderDetail.js"></script>
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	</head>
	<body>
		
		<div class="content">
			<jsp:include page="header-order.jsp"></jsp:include>
			<script id="detailContentTmple" type="text/x-jsrender">
			<div class="order">
			   	   <label class="order_no ft dis">订单号：</label><span class="ft">{{:orderNo}}</span>
			   	   <span class="order_status">{{if orderStatus==0}}未支付{{else orderStatus==1}}已支付
{{else orderStatus==2}}待评价{{else orderStatus==3}}已评价{{else orderStatus==4}}待收货{{else orderStatus==5}}已收货{{/if}}</span>
			   </div>
			   <div class="order_detail">
			   	  <label class="dis order_t">创建时间：</label><span>{{:createTime}}</span>
			   </div>

			   <div class="order_detail">
			   	 <span class="dis">{{if roomVo}}{{:roomVo.type}}{{else ticketsVo}}{{:ticketsVo.ticketName}}{{/if}}</span>
				<span class="order_num">{{if ticketsVo}}x {{/if}}{{:orderNum}}
				{{if hotelVo}}间/{{:hotelVo.dayNumber}}晚</span> 
			   </div>
			   <div class="order_detail">
			   	 <span class="dis">到店时间：</span><span>{{:hotelVo.checkinTime}}</span> 
			   </div>
               <div class="order_detail">
               	 <span class="dis">离店日期：</span><span class>{{:hotelVo.leaveTime}}</span> 
               </div>
			{{else}}</span></div>{{/if}}
               <div class="order_hr">
               	 <span>订单金额：</span><span class="price">￥{{getTwo:orderPrice}}</span> 
               </div>
             
               <div class="order_detail">
               	 <span class="dis">联系人：</span><span>{{:userName}}</span> 
               </div>
               <div class="order_detail">
               	 <span class="dis">手机号：</span><span>{{:mobile}}</span> 
               </div>
               <div class="order_detail">
               	 <span class="dis">兑换码：</span><span>{{:orderNo}}</span> 
               </div>
			   {{if testTime}}
               		<div class="order_detail">
               	 		<span class="dis">校验时间：</span><span>{{:testTime}}</span> 
               		</div>
				{{/if}}
			</script>
			<div class="right_content fl">
				<div id="order-detail" class="detail">
				</div>
			   
			</div>
		</div>
	</body>
	
</html>