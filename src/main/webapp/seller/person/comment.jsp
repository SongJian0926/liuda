<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>评价管理</title>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="../css/comm.css">
		<link rel="stylesheet" type="text/css" href="../css/comment.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="content">
			<jsp:include page="header-person.jsp"></jsp:include>
			<div class="right_content1 fl bor-sty">
				<div class="search">
					<div class="search-menu">
						 <div class="search_startTime fl">开始日期:
				      <div class="datetime_clear"></div>
				         <input type="text" readOnly class="input-search cal" id="startTime">
				      </div>
				      <div class="search_endTime fl">结束日期:
				      <div class="datetime_clear"></div>
				         <input type="text" readOnly class="input-search cal" id="endTime">
				      </div>
						<input type="button" class="search-btn font-sty-color" name="search" id="search" value="查询"/>
					</div>
				</div>
				<script id="commentListTmple" type="text/x-jsrender">
				
				<div class="comment-content bor-sty" comment-id={{:id}}>
					 <div class="">
						<ul class="font-sty ul-sty ">
							<li class="li-order">
								<label class=" li-order" >订单号：</label>
								<span>{{:orderVo.orderNo}}</span>
								<span class="span-a"><a href="javascript:void(0);" {{if replyCommentVo}}class="reply-btn c-font"{{else}}class="reply-btn a-font"{{/if}}>
								{{if replyCommentVo}}已回复{{else}}回复{{/if}}</a></span>
							</li>
							<li class="li-order">
								<label class=" li-order" ></label>
								<span>{{if roomVo}}{{:roomVo.type}}{{else}}{{:ticketsVo.ticketName}}{{/if}}</span>
							</li>
							<li class="li-order" data-id={{:userVo.id}} id="userId"> 
								<label class=" li-order" ></label>
								<span>{{:userVo.userName}}:{{:content}}</span>
							</li>
						</ul>
					</div>
				{{if imagesVo}}
					<div class="img-comment">
						<ul class="ul-sty li-order">
							{{for imagesVo.pics1}}
							<li class="img-li"><img src="../../{{:}}" width="75px" height="75px" /></li>
							{{!--<li class="img-li"><img src="../images/comment1.jpg" width="75px" height="75px" /></li>
							<li class="img-li"><img src="../images/comment1.jpg" width="75px" height="75px" /></li>--}}
							{{/for}}
						</ul>
					</div>
				{{/if}}
					<div class=" time-sty li-order">{{:createTime}}</div>
					
					<div class="reply-comment1" style="display:none">
						<div class="reply-comment">

							<textarea class="rely-area order-list" id="replyComment" placeholder="我的回复..." name="replyComment" value=""></textarea>
							{{!--<ul class="ul-sty order-list img-comment upload-reply">
								<li class="img-li">
	                                	<div class="upload_div">
	                        	      		<img id="myImageShow" src="../images/camera.png" class="img">
	                        	      		<input type="hidden" id="imgUrl" name="picurl" value="">
	                        	      		<input type="file" id="uploadPhotoFile{{:#index+1}}" name="photoFile" class="upload_file">
	                                  </div>
	                        	</li>
							</ul>--}}
							<div class="require-btn">
								<a href="javascript:void(0);" 
								class="order-list a-font confirm">确认回复</a>
								<a href="javascript:void(0);" class="a-font cancel">取消</a>
							</div>

						</div>
					</div>
					{{if replyCommentVo}}
					<div class="reply-content" style="position:relative !important;" data-id={{:replyCommentVo.id}}>
						<div class="reply-text" style="width:595px;" >我的回复：<span id="reply" val="{{:replyCommentVo.replyComment}}">{{:replyCommentVo.replyComment}}</span></div>
						<div style="width:95px;position:absolute;top:10px;right:0">&nbsp;&nbsp;<a href="javascript:void(0);" class="a-font a-del">删除</a><a href="javascript:void(0);" class="a-font a-edit">编辑</a></div>
						{{!--{{if imagesVo1}}
						<ul class="ul-sty img-comment li-order">
							{{for imagesVo1.pics1}}
							<li class="img-li">
								<img id="my../imageshow4" src="../../{{:}}" width="75px" height="75px"/>
							</li>{{/for}}
							
						</ul>
						{{/if}}--}}
						<ul class="ul-sty li-order">
							<li class="time-sty">
								<span>{{:replyCommentVo.createTime}}</span>
							</li>
						</ul>
					</div>
				{{/if}}
				</div>
				</script>
				<!-- 内容 -->
				 <div class="nogoods" style="display:none">
			             <img src="../images/shangpin.png">
			             <p>暂无数据</p>
			      </div>
				<div class="con1" id="comment-list">
					
				</div>
				
				<!-- 分页 -->
				<div class="page_big_div con1" id="displayPage">
				</div>
				<!-- 分页结束 -->
			</div>
		</div>
	</body>
	<script src="../js/comment.js"></script>
</html>