<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>订单核销</title>
		<!-- <link href="bootatrap/css/bootstrap.css" rel="stylesheet" type="text/css" /> -->
		<link rel="stylesheet" type="text/css" href="../css/orderCancel.css">
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
			 <form id="validateForm" class="form-horizontal">
			    <div class="cancel_no">输入商品兑换码
                    <input type="text"  name="orderNo" id="orderNo" class="search_cancel" data-rule="required;exchangeCode;"></input>
                    <input type="submit" value="校验" class="cancelbtn"></input>
                    <label style="color:#FF0000;font-size:15px;margin-top:4px">*</label>       	
                    <span class="msg-box n-right warn-font" style="position:static;" for="orderNo"></span>
                   
			    </div>
			   <!--   <div id="msg_holder"></div> -->
			   
			      </form>	
			    <div class="cancel_result" style="display:none;">
			    	检验结果
			    </div>
			    <div style="margin-bottom:50px;" id="result">
			    </div>
			    <script id="successTmple" type="text/x-jsrender">
 				
					<div class="cancel_sucess">校验成功！</div>
			    	<div class="cancel-content">
                       <div class="info" style="position:relative;">
                             <label>订单号：</label><span>{{:orderNo}}</span>
                             <label class="rt">联系人：</label><span class="user-sty">{{:userName}}</span>
                       </div> 
                       <div class="info" style="position:relative;">
                             <span>{{if roomVo}}{{:roomVo.type}}{{else}}{{:ticketsVo.ticketName}}{{/if}}</span><span class="num">X {{:orderNum}}</span>
                             <label class="rt">手机号：</label><span class="mobile-sty">{{:mobile}}</span>
                       </div> 
                       <div class="info">
                       	      <label>校验时间：</label><span>{{:testTime}}</span>
                       </div> 
			    	</div> 
				
				</script>
			   
			   <div style="margin-bottom:50px;display:none;" id="fail">
			    	<div class="cancel_error">校验失败！该兑换码已被使用，不能再次兑换！</div>
			    	<div class="error_img">
                      <img src="../images/error_cry.png"> 
			    	</div>
			    </div>
			     <div style="margin-bottom:50px;display:none;" id="fail1">
			    	<div class="cancel_error">校验失败！兑换码不存在，请重试！</div>
			    	<div class="error_img">
                      <img src="../images/error_cry.png"> 
			    	</div>
			    </div>
			    <div style="margin-bottom:50px;display:none;" id="fail2">
			    	<div class="cancel_error">校验失败！预定的房间，未支付，请支付</div>
			    	<div class="error_img">
                      <img src="../images/error_cry.png"> 
			    	</div>
			    </div>
			     <div style="margin-bottom:50px;display:none;" id="fail3">
			    	<div class="cancel_error">校验失败！购买的门票，未支付，请支付</div>
			    	<div class="error_img">
                      <img src="../images/error_cry.png"> 
			    	</div>
			    </div>
			</div>
		</div>
	</body>
	<script src="../js/ordervalidate.js"></script>
</html>