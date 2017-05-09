<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>赛事管理</title>
		
		<link rel="stylesheet" type="text/css" href="../css/match.css">
		<link rel="stylesheet" type="text/css" href="../css/comm.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style>  
  .white_content { 
  display: none;  
  position: absolute;  
  top: 25%;  left: 25%; 
  width: 400px;  height: 400px; 
  padding: 16px; 
  background-color: white; 
  z-index:1002; 
  overflow: auto; 
  }  </style> 
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
			<jsp:include page="header-shop.jsp"></jsp:include>
			<div class="right_content fl">
				<!-- <div class="search_order" style="overflow:hidden">
				      <div class="search_startTime fl">开始日期:
				      <div class="datetime_clear"></div>
				         <input type="text" readOnly class="input-search" id="startTime">
				      </div>
				      <div class="search_endTime fl">结束日期:
				      <div class="datetime_clear"></div>
				         <input type="text" readOnly class="input-search" id="endTime">
				      </div>
				      <div class="search_status fl">状态:
				         <select class="input-searchl" id="matchStatus">
				            <option value="">全部</option>
				         	<option value="1">审核中</option>
				        	<option value="2">审核成功</option>
				         </select>
				      </div>
			   </div> -->
			   <div class="box-body table-responsive" style="clear:both;margin:20px;">
                    <input type="button" value="添加" id="addSome" class="bt-add">
                    <input type="button" value="修改" id="updateSome" class="bt-update">
                    <input type="button" value="查看" id="showSome" class="bt-show">
                    <input type="button" value="上架" id="upSome" class="bt-up" status="2">
                    <input type="button" value="下架" id="downSome" class="bt-down" status="-1">
               </div>
			   <script id="listContentTmple" type="text/x-jsrender">
			   	     <tr>
				   	  	 <td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" class="checkitem" type="checkbox" /></td>
						 <td>{{:title}}</td>
						 <td>{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
						 <td>{{:price}}</td>
						 <td>{{:startdate}}</td>
						 <td>{{:enddate}}</td>
						 <td>{{:maxNum}}</td>
						 <td>{{if matchStatus==1}}报名中{{else matchStatus==2}}车手报道{{else matchStatus==3}}比赛中{{else matchStatus==4}}已结束{{else}}无效{{/if}}</td>
				   	  	 <td>{{if status==-1}}已下架{{else status==1}}待审核{{else status==2}}已上架{{else}}无效{{/if}}</td>
				   	  	 <td><a href="javascript:void(0);" onclick="javascript:openlive({{:id}})">直播</a>
							 <a href="javascript:void(0);" onclick="javascript:openguess({{:id}})">竞猜</a> <a href="">抽奖</a></td>
				   	  </tr>
				</script>
				<div class="nogoods" style="display:none">
			             <img src="../images/shangpin.png">
			             <p>暂无数据</p>
			    </div>
			   <table  class="table-border">
			      <tr>
			   	  	  <td width="30px"></td>
			   	  	  <td width="100px">赛事名称</td>
			   	  	  <td width="60px">赛事图片</td>
			   	  	  <td width="60px">参赛价格</td>
               		  <th width="100px">开始时间</th>
               		  <th width="100px">结束时间</th>
               		  <th width="60px">参赛人数</th>
               		  <th width="60px">状态</th>
			   	  	  <td width="60px">上/下架</td>
			   	  	  <td width="100px">操作</td>
			   	  </tr>
			   	  <tbody id="list-content">
				   	  
			   	  </tbody>
			   	  <!-- 分页 -->
			   	  <tr>
			   	  <td colspan="10" class="page_big_div" id="displayPage">
			   	  </td>
			   	  </tr>
				  <!-- 分页结束 -->
			   </table>
		   </div>
		</div>
		
	<!-- 直播 -->
	<div id="liveModel" style="display:none;" class="white_content">
		<form id="liveForm" class="form-horizontal">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">直播信息</h4>
					</div>
					<div class="modal-bodyclass">
						<center>
						<textarea style="width:80%" type="text" placeholder="请填写文字说明" class="form-control input-sm" data-rule="required;length[0~255]" id="txtcontent" name="content" >
						</textarea>
						<br/>
						<input type="hidden" id="hidLiveMatchId" name="matchId"/>
						<input type="button" value="添加" id="addMatchLive" onclick="addLive()" class="btn btn-success">
						<input type="button" value="关闭" onclick='$("#liveModel").hide()' class="btn btn-success">
						</center>
						<br/>
						<table id="livetable" class="table table-bordered table-hover" style="width:80%">
                            <thead>
                                <tr>
          		               		<th width="150">文字说明</th>
          		               		<th width="80">视频</th>
          		               		<th width="80">图片</th>
                                </tr>
                            </thead>
                            <!-- 表格内容 start -->
                            <tbody class="matchdetail" id="liveBody"></tbody>
                            <!-- 表格内容 end -->
                        </table>
							<!-- 分页标签 start -->
							<div class="row page_big_div" id="displayPage"></div>
							<!-- 分页标签 end -->
							<script id="liveContentTmple" type="text/x-jsrender">
								<tr>
									<td>{{:content}}</td>
									<td align="center">{{if mediaPath}}<img src="../../{{:mediaPath}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td align="center">{{if mediaImg}}<img src="../../{{:mediaImg}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
								</tr>
							</script>
                    </div>
		</form>
	</div>
	
	<!-- 竞猜 -->
	<div id="guessModel" style="display:none;"  class="white_content">
		<form id="guessForm" class="form-horizontal">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">竞猜信息</h4>
			</div>
			<div class="modal-bodyclass">
				<center>
				<table id="guesstable" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th width="50">选择</th>
							<th width="80">竞猜选项</th>
							<th width="80">是否正确</th>
						</tr>
					</thead>
					<!-- 表格内容 start -->
					<tbody class="matchdetail" id="guessBody"></tbody>
					<!-- 表格内容 end -->
				</table>
					<script id="guessContentTmple" type="text/x-jsrender">
						<tr align="center"> 
							<td><input id="ck_{{:id}}" class="ckSelect" name="ckoption" value="{{:id}}" {{if isRight==1}}checked{{else}}{{/if}} type="radio" /></td>
							<td>{{:option}}</td>
							<td>{{if isRight==1}}正确{{else}}错误{{/if}}</td>
						</tr>
					</script>
				<input type="hidden" id="hidGuessMatchId" name="matchId"/>
				<input type="button" value="确定" onclick="setGuess()" class="btn btn-success">
				<input type="button" value="计算" onclick="computeGuess()" class="btn btn-success">
				<input type="button" value="关闭" onclick='$("#guessModel").hide()' class="btn btn-success">
				</center>
			</div>
		</form>
	</div>
	</body>
	
	<script type="text/javascript" src="../js/match.js"></script>
</html>