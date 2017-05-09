<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>财务管理</title>
		<link rel="stylesheet" type="text/css" href="../css/comm.css">
		<link rel="stylesheet" type="text/css" href="../css/finance.css">
		<jsp:include page="../header-top.jsp"></jsp:include>
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="content finance bor-sty">
		<script id="headdealTmple" type="text/x-jsrender">
				
				<li class="child_menu_background pic0"><div class="menu1 font-sty-color">总收入
					</div><div class="menu2 font-sty-color">￥{{if orderVo}}{{getTwo:orderVo.total}}{{else}}0.00{{/if}}</div></li>
				<li class="child_menu_background pic1"><div class="menu1 font-sty-color">已提现
					</div><div class="menu2 font-sty-color">￥{{if money}}{{getTwo:money}}{{else}}0.00{{/if}}</div></li>
				<li class="child_menu_background pic2"><div class="menu1 font-sty-color">当前余额</div>
				<div class="menu2 font-sty-color">￥{{if remain}}{{getTwo:remain}}{{else}}0.00{{/if}}</div></li>
				<li class="child_menu_background pic3"><div class="menu1 font-sty-color">可提现余额</div>
				<div class="menu2 font-sty-color">￥{{if cashMoney}}{{getTwo:cashMoney}}{{else}}0.00{{/if}}</div></li>	
		</script>
			
			<div class="child_menu ">
				<ul class="menu ul-sty" id="headListTmpl">
				</ul>
						
			</div>
			<div class="search">
			
				<div class="search-menu">
					<div class="search_startTime  fl">开始日期:
					 <div class="datetime_clear"></div>
					<input type="text" class="input-search1 cal" id="startTime" name="starTime"/>
					</div>
					<div class="search_endTime fl">结束日期:
					 <div class="datetime_clear"></div>
					<input type="text" class="input-search1 cal" id="endTime" name="endTime"/>
					</div>
					<label class=" font-sty label-title" >状态：</label>
					<select class="input-tx font-sty" id="status" name="status">
						<option value="">全部</option>
						<option value="0">收入</option>
						<option value="1">支出</option>
					</select>
					<input type="button" class="search-btn font-sty-color" name="search" id="search" value="查询"/>
				</div>
			</div>
			<!-- 酒店 -->
			<script id="hoteldealTmple" type="text/x-jsrender">
				<div class="order-list" style="position: relative;">
					{{if orderNo}}
					<ul class="font-sty ul-sty ul-order-list">
						<li class="li-order" >
							<label class="li-order" >订单号：</label>
							<span>{{:orderNo}}</span>
						</li>
						<li class="li-order">
							<label class=" li-order" ></label>
							<span>{{:objectName}}</span>
						</li>
						<li class="li-order">
							<label class=" li-order" >创建时间：</label>
							<span>{{:createTime}}</span>
						</li>
					</ul>
					<span class="addMoney">+{{:money}}</span>
					{{else}}
					<ul class="font-sty ul-sty ul-order-list">
						<li class="li-order">
							<label class=" li-order" ></label>
							<span>提现</span>
						</li>
						<li class="li-order">
							<label class=" li-order" >创建时间：</label>
							<span>{{:createTime}}</span>
						</li>
					</ul>
					<span class="tixian">-{{:money}}</span>
				{{/if}}
				</div>
			
			</script>
			<div class="nogoods" style="display:none">
			             <img src="../images/shangpin.png">
			             <p>暂无数据</p>
			</div>
			<div class="con1" id="financeList">
				 
				</div>
				<!-- 分页 -->
				<div class="page_big_div" id="displayPage">
				</div>
				<!-- 分页结束 -->
			</div>
		</div>
	</body>
	<script type="text/javascript" src="../js/finance.js"></script>
</html>