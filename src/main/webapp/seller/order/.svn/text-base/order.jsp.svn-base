<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>订单管理</title>
		
		<link rel="stylesheet" type="text/css" href="../css/order.css">
		<link rel="stylesheet" type="text/css" href="../css/comm.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
			<jsp:include page="header-order.jsp"></jsp:include>
			<div class="right_content fl">
				<div class="search_order" style="overflow:hidden">
				      <div class="search_startTime fl">开始日期:
				      <div class="datetime_clear"></div>
				         <input type="text" readOnly class="input-search" id="startTime">
				      </div>
				      <div class="search_endTime fl">结束日期:
				      <div class="datetime_clear"></div>
				         <input type="text" readOnly class="input-search" id="endTime">
				      </div>
				      <div class="search_status fl">状态:
				         <select class="input-searchl" id="orderStatus">
				            <option value="">全部</option>
				         	<option value="0">未支付</option>
				        	<option value="1">已支付</option>
				        	<option value="2">待评价</option>
				         	<option value="3">已评价</option>
				         	<option value="6">失效</option>
				         </select>
				      </div>
			   </div>
			   <div class="search-but"><input type="submit" class="search-bt" value="查询"></input></div>
			   <script id="listContentTmple" type="text/x-jsrender">
			   	     <tr style="cursor:pointer;">
				   	  	  <td width="190px" class="a" data-url="orderDetail.jsp">{{:orderNo}}</td>
				   	  	  <td width="190px">{{if roomVo}}{{subString:roomVo.type}}{{else}}{{subString:ticketsVo.ticketName}}{{/if}}</td>
				   	  	  <td width="190px">{{:createTime}}</td>
				   	  	  <td width="190px">{{if orderStatus==0}}未支付{{else orderStatus==1}}已支付
{{else orderStatus==2}}待评价{{else orderStatus==3}}已评价{{else orderStatus==4}}待收货{{else orderStatus==5}}已收货{{else orderStatus==6}}失效{{/if}}</td>
				   	  </tr>
				</script>
				<div class="nogoods" style="display:none">
			             <img src="../images/shangpin.png">
			             <p>暂无数据</p>
			    </div>
			   <table  class="table-border">
			      <tr>
			   	  	  <td width="190px" data-url="orderDetail.jsp">订单编号</td>
			   	  	  <td width="190px">商品类型</td>
			   	  	  <td width="190px">创建时间</td>
			   	  	  <td width="190px">订单状态</td>
			   	  </tr>
			   	  <tbody id="list-content">
				   	  
			   	  </tbody>
			   </table>
			  <!-- 分页 -->
				<div class="page_big_div" id="displayPage">
				</div>
				<!-- 分页结束 -->
		   </div>
		</div>
	</body>
	<script type="text/javascript" src="../js/order.js"></script>
</html>