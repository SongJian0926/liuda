<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>评论管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	$.views.converters({
		"subString":function(str){
			if(str==null || str==""){
				return "";
			}
	    	if(str.length>15){
	    		return str.substring(0,15)+"...";
	    	}else{
	    		return str;
	    	}
		},
	});
	var contentUrl = root + "cms/comment/findCommentNEStatusPage";
	var editUrl = 'commentEdit.jsp?id=';
	var operateUrl = root + 'cms/comment/operateCommentByIds';
	function getfilter(){
		var _jsonFilter = "{";
				
				if($("#search_userName").val() && $("#search_userName").val().length > 0){
			_jsonFilter += "'search_LIKE_user_name':'"+$("#search_userName").val().replace(/'/g,"\"").trim()+"',";
		}
			 if(($("#startTime").val() && $("#startTime").val().length > 0) && $("#endTime").val()<=0){
			_jsonFilter += "'search_GTE_create_time':'"+$("#startTime").val()+"',";
		}       
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && $("#startTime").val().length<=0){
			_jsonFilter += "'search_LTE_create_time':'"+$("#endTime").val()+"',";
		} 
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && ($("#startTime").val() && $("#startTime").val().length > 0)){
			_jsonFilter += "'search_BETWEEN_create_time':'"+$("#startTime").val()+","+$("#endTime").val()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	</script>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box">
	                  <form id="search_form" class="form-horizontal">
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">用户名：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="用户名" class="form-control input-sm" id="search_userName" name="userName"/>
								</div>
      						</div>
      						<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">活动时间：</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="开始时间" readOnly class="form-control input-sm" id="startTime" name="createTime"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">至</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="结束时间" readOnly class="form-control input-sm" id="endTime" name="createTime"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <!-- <input type="button" value="新增" id="addSome" class="btn btn-color"> -->
                          <!-- <input type="button" value="编辑" id="updateSome" class="btn btn-info"> -->
                          <!-- <input type="button" value="启用" id="openSome" class="btn btn-success">
                          <input type="button" value="锁定" id="lockSome" class="btn btn"> -->
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
				               		  <th width="80">评论人</th>
          		               		  <th width="150">评论内容</th>
				               		  <th width="80">评论类型</th>
				               		  <th width="80">评论对象</th>
				               		  <th width="80">评论分数</th>
				               		  <th width="80">评论时间</th>
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
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" class="ckSelect" type="checkbox" /></td>
									<td>{{:userName}}</td>
									<td>{{subString:content}}</td>
									<td>{{if type==0}}酒店{{else type==1}}景点{{else type==2}}商品{{/if}}</td>
									<td>{{subString:objectName}}</td>
									<td>{{:score}}</td>
									<td>{{:createTime}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>