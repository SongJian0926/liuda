<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>赛事管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/macth/findMacthNEStatusPage";
 	var editUrl = "matchEdit.jsp";
	var operateUrl = root + "cms/macth/operateMacthByIds";
	$(function(){
		//直播
		$(document).on('click','#liveMatchSome',function(){
			var updateArray = [];
			$(".ckSelect").each(function(){
				var check=$(this).is(':checked');
				if(check){
					var _id =$(this).attr("ckId");
					updateArray.push(_id);
				}
			});
			if(updateArray.length == 0){
				alert("请选择要查看的项"); return;
			}
			if(updateArray.length > 1){
				alert("每次只能选择一项"); return;
			}
			openlive(updateArray[0]);
		});
		//竞猜
		$(document).on('click','#guessMatchSome',function(){
			var updateArray = [];
			$(".ckSelect").each(function(){
				var check=$(this).is(':checked');
				if(check){
					var _id =$(this).attr("ckId");
					updateArray.push(_id);
				}
			});
			if(updateArray.length == 0){
				alert("请选择要查看的项"); return;
			}
			if(updateArray.length > 1){
				alert("每次只能选择一项"); return;
			}
			openguess(updateArray[0]);
		});
		//抽奖
		$(document).on('click','#prizeMatchSome',function(){
			var updateArray = [];
			$(".ckSelect").each(function(){
				var check=$(this).is(':checked');
				if(check){
					var _id =$(this).attr("ckId");
					updateArray.push(_id);
				}
			});
			if(updateArray.length == 0){
				alert("请选择要查看的项"); return;
			}
			if(updateArray.length > 1){
				alert("每次只能选择一项"); return;
			}
			openprize(updateArray[0]);
		});

		//上传图片
	   $(document).on('change','#uploadPhotoFile',function() {
			$.ajaxFileUpload({
				url : '../../cms/matchLive/photoUpload',
				secureuri : false,
				fileElementId : 'uploadPhotoFile',
				type : 'POST',
				dataType : 'json',
				success : function(data, status) {
					console.log(data);
					if (data) {
						var ds = data.object.split(":");
						if (ds[0] == 1) {
							$("#myImageShow").attr("src","../.."+ds[1]); 
							$("#mediaImg").attr("value",ds[1]);
						} else {
							alert(ds[1]);
						}
					}
				},
				error : function(data, status, e) {
					alert(data);
				}
			});
		});
		//上传视频
	   $(document).on('change','#uploadVideoFile',function() {
			$.ajaxFileUpload({
				url : '../../cms/matchLive/videoUpload',
				secureuri : false,
				fileElementId : 'uploadVideoFile',
				type : 'POST',
				dataType : 'json',
				success : function(data, status) {
					console.log(data);
					if (data) {
						var ds = data.object.split(":");
						if (ds[0] == 1) {
							$("#myVideoShow").attr("src","../.."+ds[1]); 
							$("#mediaPath").attr("value",ds[1]);
						} else {
							alert(ds[1]);
						}
					}
				},
				error : function(data, status, e) {
					alert(data);
				}
			});
		});
	});
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_title").val() && $("#search_title").val().length > 0){
			_jsonFilter += "'search_LIKE_title':'"+$("#search_title").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_matchStatus").val() && $("#search_matchStatus").val() != ""){
			_jsonFilter += "'search_EQ_matchStatus':'"+$("#search_matchStatus").val()+"',";
		}
				if($("#search_status").val() && $("#search_status").val() != ""){
			_jsonFilter += "'search_EQ_status':'"+$("#search_status").val()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	//function openMatchMemberList(id)
	//{
	//	window.open("matchMemberList.jsp?matchId="+id, "window", "width:800px;height:600px;status:no;help:no;resizable:yes"); 
	//}
	//function openMatchViewMemberList(id)
	//{
	//	window.open("matchMemberViewList.jsp?matchId="+id, "window", "width:500px;height:400px;status:no;help:no;resizable:yes"); 
	//}
	function openlive(matchid)
	{
		//$("#myImageShow").attr("src",""); 
		//$("#mediaImg").attr("value","");
		$("#myVideoShow").attr("src",""); 
		$("#mediaPath").attr("value","");
		$("#txtcontent").val("");
		refreshLive(matchid);
		$("#liveModel").modal();
	}
	function addLive()
	{
		if($("#myVideoShow").attr("src")=="" && $.trim($("#txtcontent").val())=="")
		{
			alert("文字和视频不能都为空");
			return;
		}
		$.ajax({
			url : root + "cms/matchLive/saveMatchLive",
			type : "POST",
			data : $("#liveForm").serialize(),
			success : function(data) {
				if (data.code == 1) {
					alert("添加成功");
					$("#myVideoShow").attr("src",""); 
					$("#mediaPath").attr("value","");
					$("#txtcontent").val("");
					refreshLive($("#hidLiveMatchId").val());
				} else {
					alert(data.message);
				}
			}
		});
	}
	function refreshLive(matchid)
	{
		var _jsonFilter = "{'search_EQ_matchId':'"+matchid+"'}";
		url= root + 'cms/matchLive/findMatchLiveNEStatusPage';
			$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			data:{
				jsonFilter:_jsonFilter
			},
			success:function(data){
				console.log(data);
				if(data.code == 1){
					if(data.object && data.object.content)
					{
						var tblContentHtml = $("#liveContentTmple").render(data.object.content);
						$("#liveBody").html(tblContentHtml);
					}
					else
					{
						$("#liveBody").html("");
					}
					$("#hidLiveMatchId").val(matchid);
			    }
			}
		   });
	}
	function openguess(matchid)
	{
		refreshGuess(matchid);
		$("#guessModel").modal();
	}
	function setGuess()
	{
		var guessoptionid = $('input[name="ckoption"]:checked').val();
		if(guessoptionid==undefined || guessoptionid==null)
		{
			alert("请选择一个选项");
			return;
		}
		$.ajax({
			url : root + "cms/guess/setGuessOption",
			type : "POST",
			dataType:'json',
			data : {
				matchId:$("#hidGuessMatchId").val(),
				optionId:guessoptionid
			},
			success : function(data) {
				if (data.code == 1) {
					alert("修改成功");
					refreshGuess($("#hidGuessMatchId").val());
				} else {
					alert(data.message);
				}
			}
		});
	}
	function computeGuess()
	{
		var guessoptionid = $('input[name="ckoption"]:checked').val();
		if(guessoptionid==undefined || guessoptionid==null)
		{
			alert("请选择一个选项");
			return;
		}
		$.ajax({
			url : root + "cms/guess/computeGuess",
			type : "POST",
			dataType:'json',
			data : {
				matchId:$("#hidGuessMatchId").val(),
				optionId:guessoptionid
			},
			success : function(data) {
				if (data.code == 1) {
					alert("计算成功");
					refreshGuess($("#hidGuessMatchId").val());
				} else {
					alert(data.message);
				}
			}
		});
	}
	function refreshGuess(matchid)
	{
		url= root + 'cms/guess/findByMacthIdAndNotStatus';
			$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			data:{
				matchId:matchid
			},
			success:function(data){
				console.log(data);
				if(data.code == 1){
					if(data.object && data.object.guessOptionList)
					{
						var tblContentHtml = $("#guessContentTmple").render(data.object.guessOptionList);
						$("#guessBody").html(tblContentHtml);
					}
					else
					{
						$("#guessBody").html("");
					}
					$("#guessTitle").text(data.object.title);
					if(data.object.status==2)
					{
						$("#guessResult1").hide();
						$("#guessResult2").show();
					}
					else
					{
						$("#guessResult1").show();
						$("#guessResult2").hide();
					}
					$("#hidGuessMatchId").val(matchid);
			    }
			}
		   });
	}
	function openprize(matchid)
	{
		refreshPrize(matchid);
		$("#prizeModel").modal();
	}
	function setPrize()
	{
		$.ajax({
			url : root + "cms/prizeOption/setPrizeOptionResult",
			type : "POST",
			dataType:'json',
			data : {
				matchId:$("#hidPrizeMatchId").val()
			},
			success : function(data) {
				if (data.code == 1) {
					alert("抽奖成功");
					refreshPrize($("#hidPrizeMatchId").val());
				} else {
					alert(data.message);
				}
			}
		});
	}
	function publishPrize()
	{
		$.ajax({
			url : root + "cms/prizeOption/publishPrize",
			type : "POST",
			dataType:'json',
			data : {
				matchId:$("#hidPrizeMatchId").val()
			},
			success : function(data) {
				if (data.code == 1) {
					alert("发布成功");
					refreshPrize($("#hidPrizeMatchId").val());
				} else {
					alert(data.message);
				}
			}
		});
	}
	function refreshPrize(matchid)
	{
		url= root + 'cms/prizeOption/findByMacthIdAndNotStatus';
			$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			data:{
				matchId:matchid
			},
			success:function(data){
				console.log(data);
				if(data.code == 1){
					if(data.object)
					{
						var tblContentHtml = $("#prizeContentTmple").render(data.object);
						$("#prizeBody").html(tblContentHtml);
						if(data.object.length>0 && data.object[0].prizeResultList && data.object[0].prizeResultList.length>0 && data.object[0].prizeResultList[0].status==2)
						{
							$("#prizeResult1").hide();
							$("#prizeResult2").show();
						}
						else
						{
							$("#prizeResult1").show();
							$("#prizeResult2").hide();
						}
					}
					else
					{
						$("#prizeBody").html("");
						$("#prizeResult1").show();
						$("#prizeResult2").hide();
					}
					$("#hidPrizeMatchId").val(matchid);
			    }
			}
		   });
	}
	</script>
	<style>
		<!--
			.img{
				width:100px;
				height:100px;
				border:none;
				background:#ddd;
				border-radius:4px;
				text-align:center;
				line-height:100px;
			}
		-->
	</style>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box">
	                  <!-- <form id="search_form" class="form-horizontal"> -->
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">赛事名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="赛事名称" class="form-control input-sm" id="search_title" name="search_title"/>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">赛事状态：</label>
       				 			<div class="col-md-8">
									<select id="search_matchStatus"> 
										<option value="">请选择</option>
										<option value="1">报名中</option>
										<option value="2">车手报道</option>
										<option value="3">比赛中</option>
										<option value="4">已结束</option>
									</select>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">上/下架：</label>
       				 			<div class="col-md-8">
									<select id="search_status"> 
										<option value="">请选择</option>
										<option value="1">待审核</option>
										<option value="2">上架</option>
										<option value="-1">下架</option>
									</select>
								</div>
      						</div>
							<input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                 <!--  </form> -->
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="添加" id="addMatchSome" class="btn btn-color">
                          <input type="button" value="修改" id="updateMatchSome" class="btn btn-color">
                          <input type="button" value="查看" id="detailMatch" class="btn btn-color">
                          <input type="button" value="审批" id="checkMatchSome" class="btn btn-color" status="1">
                          <input type="button" value="上架" id="upMatchSome" class="btn btn-color" status="2">
                          <input type="button" value="下架" id="downMatchSome" class="btn btn-color" status="-1">
                          <input type="button" value="直播" id="liveMatchSome" class="btn btn-color">
                          <input type="button" value="竞猜" id="guessMatchSome" class="btn btn-color" style="display:none">
                          <input type="button" value="抽奖" id="prizeMatchSome" class="btn btn-color" style="display:none">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="50">赛事类型</th>
          		               		  <th width="50">赛事名称</th>
				               		  <th width="50">赛事图片</th>
				               		  <th width="50">参赛价格</th>
				               		  <th width="50">赛事开始时间</th>
				               		  <th width="50">赛事结束时间</th>
				               		  <th width="50">参赛人数</th>
				               		  <th width="50">赛事状态</th>
				               		  <th width="50">上/下架</th>
				               		  <th width="50">报名人员</th>
				               		  <!--<th width="50">操作</th>-->
                                  </tr>
                              </thead>
                              <!-- 表格内容 start -->
                              <tbody id="mycontent"></tbody>
                              <!-- 表格内容 end -->
                          </table>
                          <!-- 分页标签 start -->
                          <div class="row page_big_div" id="displayPage"></div>
                          <!-- 分页标签 end -->
                          <script id="tableContentTmple" type="text/x-jsrender">
								<tr>
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" ckType="{{:matchType}}" class="ckSelect" type="checkbox" /></td>
									<td>{{if matchType==1}}赛事{{else matchType==2}}活动{{else}}{{/if}}</td>
									<td>{{subString:title}}</td>
									<td>{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{:price}}</td>
									<td>{{:startdate}}</td>
									<td>{{:enddate}}</td>
									<td>{{:maxNum}}</td>
						 			<td>{{if matchStatus==1}}报名中{{else matchStatus==2}}车手报道{{else matchStatus==3}}比赛中{{else matchStatus==4}}已结束{{else}}无效{{/if}}</td>
									<td>{{if status==-1}}下架{{else status==1}}待审核{{else status==2}}上架{{else}}无效{{/if}}</td>
									<td>
										<input type="button" value="参赛" onclick="location.href='matchMemberList.jsp?matchId={{:id}}'" class="btn btn-info" style="width:auto;">
										<input type="button" value="观赛" onclick="location.href='matchMemberViewList.jsp?matchId={{:id}}'" class="btn btn-info" style="width:auto;">
									</td>
									<!--<td>
										<input type="button" value="直播" onclick="openlive({{:id}})" class="btn btn-info" style="width:auto;">
										<input type="button" value="竞猜" onclick="openguess({{:id}})" class="btn btn-info" style="width:auto;">
										<input type="button" value="抽奖" onclick="openprize({{:id}})" class="btn btn-info" style="width:auto;">
									</td>-->
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
      
      <!-- 详细信息 -->
      <div class="modal fade" id="myModals" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">详细信息</h4>
					</div>
					<div class="modal-bodyclass">
                       <table class="search-table">
                       	   <tbody class="matchdetail">
                       	    </tbody>
                       	    <script id="matchContentTmple" type="text/x-jsrender">
                              <tr>
	                            <td class="search_td"><label>赛事名称:</label></td>
	                            <td><div>{{:title}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>赛事图片:</label></td>
	                            <td><img src="../../{{:logo}}" class="img" height="40" width="50"></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>赛事地址:</label></td>
	                            <td><div>{{:areaCode}}{{:address}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>赛事时间:</label></td>
	                            <td><div>{{:startdate}} - {{:enddate}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>参赛费用:</label></td>
	                            <td><div>{{:price}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>参赛订金:</label></td>
	                            <td><div>{{:deposit}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>报名截止时间:</label></td>
	                            <td><div>{{:deadline}}</div></td>
	                          </tr>
                               <tr>
	                            <td class="search_td"><label>人数限制:</label></td>
	                            <td><div>{{:maxNum}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>参赛条件:</label></td>
	                            <td><div>{{:condition}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>活动详情:</label></td>
	                            <td><div>{{:content}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>图片:</label></td>
	                            <td>
                                    {{if mediaPath}}
                                       {{for mediaPath.split(",")}}
                                         <img src="../../{{:}}" class="img" height="40" width="50">
                                       {{/for}}
                                     {{/if}}
                                </td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>观赛门票名称:</label></td>
	                            <td><div>{{:ticketName}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>观赛门票图片:</label></td>
	                            <td><img src="../../{{:ticketImages}}" class="img" height="40" width="50"></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>观赛门票价格:</label></td>
	                            <td><div>{{:ticketPrice}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>观赛门票库存:</label></td>
	                            <td><div>{{:ticketStockNum}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>观赛门票截止时间:</label></td>
	                            <td><div>{{:deadlineView}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>观赛门票优惠时间:</label></td>
	                            <td><div>{{:favStartTime}} - {{:favEndTime}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>观赛门票优惠价格:</label></td>
	                            <td><div>{{:favourablePrice}}</div></td>
	                          </tr>
                            </script>
                       </table>
                    </div>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 直播 -->
	<div class="modal fade" id="liveModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="liveForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">直播信息</h4>
					</div>
					<div class="modal-bodyclass">
						<label class="col-md-2 control-label controls" for="formGroupInputSmall">文字：</label>
						<textarea type="text" placeholder="请填写文字说明" class="form-control input-sm" data-rule="length[0~255]" id="txtcontent" name="content" ></textarea>
						<br/>
						<!--
						<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
						<div class="col-md-3">
							<div class="upload_div">
								<img id="myImageShow" src="" class="img">
								<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
								<input type="hidden" id="mediaImg" name="mediaImg"></input>
							</div>
						</div>
						-->
						<label class="col-md-2 control-label controls" for="formGroupInputSmall">视频：</label>
						<div class="col-md-3">
							<div class="upload_div">
								<video id="myVideoShow" src="" class="img"></video>
								<input type="file" id="uploadVideoFile" name="videoFile" class="upload_file">
								<input type="hidden" id="mediaPath" name="mediaPath"></input>
							</div>
						</div>
						<br/>
						<input type="hidden" id="hidLiveMatchId" name="matchId"/>
						<center>
						<input type="button" value="添加" id="addMatchLive" onclick="addLive()" class="btn btn-success">
						</center>
						<table id="livetable" class="table table-bordered table-hover">
                            <thead>
                                <tr>
          		               		<th width="150">文字说明</th>
          		               		<th width="80">视频</th>
          		               		<!--<th width="80">图片</th>-->
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
									<td>
										{{if mediaPath}}
										<video src="../../{{:mediaPath}}" controls="controls" height="40" width="50">您的浏览器不支持 video 标签。</video>
										{{else}}无{{/if}}
									</td>
									<!--<td>{{if mediaImg}}<img src="../../{{:mediaImg}}" height="40" width="50">{{else}}无{{/if}}</td>-->
								</tr>
							</script>
                    </div>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 竞猜 -->
	<div class="modal fade" id="guessModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="guessForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">竞猜信息</h4>
					</div>
					<div class="modal-bodyclass">
						<span id="guessTitle"></span><br/>
						<span id="guessResult1" style="display:none">竞猜结果未发布</span>
						<span id="guessResult2" style="display:none">竞猜结果已发布</span>
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
								<tr>
									<td><input id="ck_{{:id}}" class="ckSelect" name="ckoption" value="{{:id}}" {{if isRight==1}}checked{{else}}{{/if}} type="radio" /></td>
									<td>{{:option}}</td>
									<td>{{if isRight==1}}正确{{else}}错误{{/if}}</td>
								</tr>
							</script>
						<input type="hidden" id="hidGuessMatchId" name="matchId"/>
						<center>
						<input type="button" value="确定" onclick="setGuess()" class="btn btn-success">
						<input type="button" value="计算" onclick="computeGuess()" class="btn btn-success">
						</center>
                    </div>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 抽奖 -->
	<div class="modal fade" id="prizeModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="prizeForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">抽奖信息</h4>
					</div>
					<div class="modal-bodyclass">
						<span id="prizeResult1" style="display:none">抽奖结果未发布</span>
						<span id="prizeResult2" style="display:none">抽奖结果已发布</span>
						<table id="prizetable" class="table table-bordered table-hover">
                            <thead>
                                <tr>
          		               		<th width="30">等级</th>
          		               		<th width="30">人数</th>
          		               		<th width="50">奖品</th>
          		               		<th width="80">中奖名单</th>
                                </tr>
                            </thead>
                            <!-- 表格内容 start -->
                            <tbody class="matchdetail" id="prizeBody"></tbody>
                            <!-- 表格内容 end -->
                        </table>
							<script id="prizeContentTmple" type="text/x-jsrender">
								<tr>
									<td>{{:level}}</td>
									<td>{{:num}}</td>
									<td>{{:prize}}</td>
									<td>
										{{if prizeResultList}}
										{{for prizeResultList}}
                                         <!--<img src="../../{{:}}" class="img" height="40" width="50">-->
											<span><xmp>{{:userVo.userName}}  {{:userVo.mobile}}</xmp></span>
										{{/for}}
                                     {{/if}}
									</td>
								</tr>
							</script>
						<input type="hidden" id="hidPrizeMatchId" name="matchId"/>
						<center>
						<input type="button" value="抽奖" onclick="setPrize()" class="btn btn-success">
						<input type="button" value="发布" onclick="publishPrize()" class="btn btn-success">
						</center>
                    </div>
				</div>
			</div>
		</form>
	</div>
    </body>
</html>