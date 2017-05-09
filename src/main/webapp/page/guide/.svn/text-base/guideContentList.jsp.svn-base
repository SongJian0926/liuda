<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>攻略表管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/guideAppend/findGuideAppendNEStatusPage";
	var _from = getUrlParam('from');
	var _title = getUrlParam('title');
	var _autor = getUrlParam('autor');
	var _appendId = getUrlParam('appendId');
	function getfilter(){
		var _jsonFilter = "{";
			_jsonFilter += "'search_EQ_guideId':'"+getUrlParam('id')+"',";
				if($("#search_content").val() && $("#search_content").val().length > 0){
			_jsonFilter += "'search_LIKE_content':'"+$("#search_content").val().replace(/'/g,"\"").trim()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += ","+"'search_NE_id':'"+_appendId+"',";
		_jsonFilter += "}";
		return _jsonFilter;
	}
	//查看详情
	function lookDetail(id,path_url,content,time){
		
		$("#append_time").html('');	
		$('#append_content').val('');
		$('#append_img').html('');
		if(path_url){
			var _pathArr = path_url.split(',');
			if(_pathArr.length > 0){
				
				if(_pathArr[0].substring(_pathArr[0].indexOf('.')) == ".mp4" || _pathArr[0].substring(_pathArr[0].indexOf('.')) == ".mov" ){
					var _video = "<video controls='controls'><source src='" + root  + _pathArr[0] + "' type='video/ogg'><source src='" + root  + _pathArr[0] + "' type='video/mp4'>Your browser does not support the video tag</video>";
					$('#append_img').html(_video);
				}else{
					var _imgs = "";
					$.each(_pathArr,function(){
						_imgs += '<img src="' + root + this + '" style="width:50px;height:50px;margin-right:10px;" />';
					});
					$('#append_img').html(_imgs);
				}
			}
		}
		$('#append_time').html(time);
		$('#append_content').val(content);
		$('#lookDetailModal').modal();
		$('#mycontent').find('tr').removeClass('warning');
	}
	
	/* $("#mycontent").on("click",".lookdetail",function(){
		alert(1);
		var tId=$(this).attr("ckId").val();
		$.ajax({
			url: root + 'cms/guideAppend/findGuideAppendById',
			type: 'post',
			data:{
				id:tId,
			},
			dataType: 'json',
			success: function(data){
				console.log(data);
			
				if(data.code==1){
					$("#append_time").html('');	
					$('#append_content').val('');
					$('#append_img').html('');
					if(data.object.mediaPath){
						var _pathArr = path_url.split(',');
						if(_pathArr.length > 0){
							if(_pathArr[0].substring(_pathArr[0].indexOf('.')) == ".mp4" || _pathArr[0].substring(_pathArr[0].indexOf('.')) == ".rmvb" ){
								var _video = "<video width='320' height='170' controls='controls'><source src='" + root  + _pathArr[0] + "' type='video/ogg'><source src='" + root  + _pathArr[0] + "' type='video/mp4'>Your browser does not support the video tag</video>";
								$('#append_img').html(_video);
							}else{
								var _imgs = "";
								$.each(_pathArr,function(){
									_imgs += '<img src="' + root + this + '" style="width:50px;height:50px;margin-right:10px;" />';
								});
								$('#append_img').html(_imgs);
							}
						}
					}
					$('#append_time').html(data.object.createTime);
					$('#append_content').html(data.object.content);
					$('#lookDetailModal').modal();
					$('#mycontent').find('tr').removeClass('warning');
				}else{
					alert(data.message);
				}
			}
        });
	}); */
	
	function backurl(){
		/* if(_from){
			location.href = _from;
		}else{
			
			 _from.substring(''); */
			location.href = 'guideList.jsp?nextPage='+getUrlParam('nextPage1');
		
	}
	$(document).ready(function(){
		$('#title').html(_title);
		$('#autor').html(_autor);
		//查看追加详情
		$("#mycontent").on('click',".lookdetail",function(){
			
			var tId=$(this).attr("ckId");
			$.ajax({
				url: root + 'cms/guideAppend/findGuideAppendById',
				type: 'post',
				data:{
					modelId:tId,
				},
				dataType: 'json',
				success: function(data){
					console.log(data);
				
					if(data.code==1){
						$("#append_time").html('');	
						$('#append_content').val('');
						$('#append_img').html('');
						if(data.object.mediaPath){
							var _pathArr = data.object.mediaPath.split(',');
							if(_pathArr.length > 0){
								if(_pathArr[0].substring(_pathArr[0].indexOf('.')) == ".mp4" || _pathArr[0].substring(_pathArr[0].indexOf('.')) == ".mov" ){
									var _video = "<video width='320' height='170' controls='controls'><source src='" + root  + _pathArr[0] + "' type='video/ogg'><source src='" + root  + _pathArr[0] + "' type='video/mp4'>Your browser does not support the video tag</video>";
									$('#append_img').html(_video);
								}else{
									var _imgs = "";
									$.each(_pathArr,function(){
										_imgs += '<img src="' + root + this + '" style="width:50px;height:50px;margin-right:10px;" />';
									});
									$('#append_img').html(_imgs);
								}
							}
						}
						$('#append_time').html(data.object.createTime);
						$('#append_content').val(data.object.content);
						$('#lookDetailModal').modal();
						$('#mycontent').find('tr').removeClass('warning');
					}else{
						alert(data.message);
					}
				}
	        });
		});
	});
	</script>
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">内容：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="内容" class="form-control input-sm" id="search_content" name="content"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-info">
                      	</div>
	                 <!--  </form> -->
	                  <div class="form-horizontal" style="clear:both;">
	                  		<div class="form-group">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">攻略标题：</label>
       				 			<div id="title" style="padding-top:7px;"></div>
      						</div>
	                  		<div class="form-group">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">作者：</label>
       				 			<div id="autor" style="padding-top:7px;"></div>
      						</div>
	                  </div>
                      <div class="box-body table-responsive" style="clear:both;">
                          <!-- <input type="button" value="编辑" id="updateSome" class="btn btn-info"> -->
                          <!-- <input type="button" value="启用" id="openSome" class="btn btn-success">
                          <input type="button" value="锁定" id="lockSome" class="btn btn"> -->
                          <!-- <input type="button" value="删除" id="deleteSome" class="btn btn-danger"> -->
                          <input type="button" value="返回" onclick="backurl();" class="btn btn-info">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><!-- <input type="checkbox" id="_selectAll" /> --></th>
          		               		  <th width="80%">追加内容</th>
				               		  <th width="80">追加日期</th>
				               		  <th width="80">操作</th>
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
									<td><input style="display:none" id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" class="ckSelect" type="checkbox" />{{:#index + 1}}</td>
									<td>{{getSubStr:content 50}}</td>
									<td>{{:createTime}}</td>
									<td>
										{{!--<input type="button" value="查看详情" ckId="{{:id}}" id="lookdetail" onclick="lookDetail({{:id}},'{{:mediaPath}}','{{:content}}','{{:createTime}}');" class="btn btn-info lookdetail" style="width:auto;">--}}
<input type="button" value="查看详情" ckId="{{:id}}" class="lookdetail btn btn-info" style="width:auto;">
									</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
	<!-- 查看详情对话框 -->
      <div class="modal fade" id="lookDetailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">详情</h4>
					</div>
					<div class="modal-body" id="lookDetail">
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">图片/视频：</label>
							<div class="controls col-md-7" style="line-height:30px;" id="append_img"></div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">追加时间：</label>
							<div class="controls col-md-7" style="line-height:30px;" id="append_time"></div>
						</div>
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">内容：</label>
							<div class="controls col-md-7" style="line-height:30px;">
								<!-- <div class="form-control input-sm" style="width:330px; height:280px;border:1px solid #FF0000;overflow:hidden;" id="append_content" ></div> -->
								<textarea class="form-control input-sm" style="resize:none;height:150px;" disabled id="append_content" placeHolder="请输入审核意见"></textarea>
							</div>
						</div>	
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</form>
	</div>
    </body>
</html>