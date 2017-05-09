<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>已上架</title>
		<link rel="stylesheet" type="text/css" href="../css/goods.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
			<jsp:include page="header-shop.jsp"></jsp:include>
			<div class="right_content fl">
				<div class="goods_mang">
					<input type="submit" class="search-down" value="下架管理"></input>
					<label style="display:none;" class="check-all">
						<input type="checkbox" id="checkAll" /><span>全选</span>
					</label>
				</div>
				<div class="goods_items">
				     <div class="nogoods" style="display:none">
			             <img src="../images/shangpin.png">
			             <p>您还没有上传过商品哦，立即去添加吧~</p>
			         </div>
					<ul class="show-list">
					</ul>
					<script id="roomlistContentTmple" type="text/x-jsrender">
                        <li class="item fl"> 
					       <div>
					       	  <img src="../../{{:logo}}" width="180px" height="160px">
					       </div>
					       <div class="thumb fl" style="display:none;">
					         <label>
							  	<input type="checkbox" class="checkitem" ckId="{{:id}}"/><span></span>
					         </label>
					       </div>
					       <div class="thum fl">
					           <div class="ht">{{subString:type}}</div>
					       	   <div>￥{{if groupBuy==1}}{{:groupPrice}}{{else}}{{:price}}{{/if}}
					        	  {{if groupBuy==1}}<label class="agoPrice">{{:price}}</label><img src="../images/tuan.png">{{/if}}
                               </div>
					       </div>
					    </li>
                     </script>
                     <script id="ticketslistContentTmple" type="text/x-jsrender">
                        <li class="item fl"> 
                           <input type="hidden" value="{{:ticketsvo.id}}" class="gId">
					       <div>
					       	  <img src="../../{{:ticketsvo.imgUrl}}" width="180px" height="160px">
					       </div>
					       <div class="thumb fl" style="display:none;">
					         <label>
							  	<input type="checkbox" class="checkitem" ckId="{{:ticketsvo.id}}"/><span></span>
					         </label>
					       </div>
					       <div class="thum fl">
					           <div class="ht">{{subString:ticketsvo.ticketName}}</div>
					       	   <div>￥{{if ticketsvo.groupBuy==1}}{{:ticketsvo.groupPrice}}{{else}}{{:ticketsvo.price}}{{/if}}
                                   {{if ticketsvo.groupBuy==1}}<label class="agoPrice">{{:ticketsvo.price}}</label><img src="../images/tuan.png">{{/if}}</div>
					       </div>
					    </li>
                     </script>
				</div>
				<!-- 分页 -->
				<div class="page_big_div" id="displayPage">
				</div>
				<!-- 分页结束 -->
				<div class="goods-bottom">
				   <input type="submit" class="search-quxiao canelUp" value="取消" style="display:none;"></input>
				   <input type="submit" class="search-xiajia okDown" value="下架" style="display:none;"></input>
				</div>
		   </div>
		</div>
	</body>
	<script type="text/javascript" src="../js/shangjiagoods.js"></script>
	<script type="text/javascript" src="../js/goods.js"></script>
</html>