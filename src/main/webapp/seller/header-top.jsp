<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<link rel="stylesheet" type="text/css" href="../css/common.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<script src="../js/jquery-1.11.0.min.js"></script>
		<link href="../../js/datapacker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="../../js/validator/jquery.validator.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/datapacker/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="../../js/datapacker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script type="text/javascript" src="../../js/jsviews.js"></script>
        <script type="text/javascript" src="../../js/loadTmpl.js"></script>
        <script type="text/javascript" src="../../js/base.js"></script>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript" src="../../js/ajaxfileupload.js"></script>
        <script	type="text/javascript" src="../../js/validator/jquery.validator.js"></script>
        <script type="text/javascript" src="../../js/validator/local/zh_CN.js"></script>
		<script src="../js/common.js"></script>
		<script src="../js/jquery.cookie.js"></script>
	</head>
	<body>
		<div class="header_nav">
			<div class="header_content">
				<div class="nav_icon fl">
					<span>蹓跶蹓跶</span>
				</div>
				<div class="nav_menu fl">
					<ul>
						<li class="preventMenu active" data-url="../shop/addshopList.jsp">商品管理</li>
						<li class="preventMenu" data-url="../order/order.jsp">订单管理</li>
						<li class="preventMenu" data-url="../finance/finance.jsp">财务管理</li>
						<li class="preventMenu" data-url="../person/businessInfo.jsp">个人中心</li>
					</ul>
				</div>
				<div class="nav_login_info fr">
					<img src="../images/user.png" class="fl">
					<ul>
						<li class="info userName" id="userName"></li>
						<li id="getout" class="info logout" style="cursor: pointer;" data-url="../login.jsp">退出登录</li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>