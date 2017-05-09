<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>意见反馈管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/feedBack/findFeedBack";
	/* var editUrl = 'feedBackEdit.jsp?id=';
	var operateUrl = root + 'cms/feedBack/operateFeedBackByIds'; */
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_userName").val() && $("#search_userName").val().length > 0){
			_jsonFilter += "'search_LIKE_userName':'"+$("#search_userName").val().replace(/'/g,"\"").trim()+"',";
		}
		        if(($("#startTime").val() && $("#startTime").val().length > 0) && $("#endTime").val()<=0){
			_jsonFilter += "'search_GTE_createTime':'"+$("#startTime").val()+"',";
		}       
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && $("#startTime").val().length<=0){
			_jsonFilter += "'search_LTE_createTime':'"+$("#endTime").val()+"',";
		} 
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && ($("#startTime").val() && $("#startTime").val().length > 0)){
			_jsonFilter += "'search_BETWEEN_createTime':'"+$("#startTime").val()+","+$("#endTime").val()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		} 
		_jsonFilter += "}";
		return _jsonFilter;
	}
	//导出
	$(document).on('click','#dataexport',function(){
	    //获取数据长度
	    var tr=$("#mycontent").find('tr').length;
	    if(tr==0){
	        alert("没有数据，不能导出");
	        return;
	    }else{
	        location.href = root + 'cms/feedBack/fileDownload?jsonFilter=' + encodeURI(encodeURI(getfilter()));
	    }
	});
	
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
									<input type="text" placeholder="用户名" class="form-control input-sm" id="search_userName" name="userId"/>
								</div>
							</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">反馈时间：</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" readOnly placeholder="开始时间" class="form-control input-sm" id="startTime" name="createTime"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">至</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" readOnly placeholder="结束时间" class="form-control input-sm" id="endTime" name="createTime"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
	                  <div class="box-body table-responsive" style="clear:both;">
	                 <!--   <input type="button" value="导出" id="dataexport" class="btn"> -->
	                   <input type="button" value="详细" id="feedBankDetail" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">用户名</th>
				               		  <th width="80" id="content">反馈内容</th>
				               		  <th width="80">反馈时间</th>
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
									<td><input id="ck_{{:id}}" ckId="{{:id}}"  class="ckSelect" type="checkbox" /></td>
									<td>{{:username}}</td>
									<td>{{subString:content}}</td>
									<td>{{:createTime}}</td>
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
						<button type="button" id="closeFeedBack" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">反馈信息</h4>
					</div>
					<div class="modal-body">
                       <table>
                       	   <tbody>
                       		  <tr>
	                            <td class="search_td"><label for="userPhoto2">用户名:</label></td>
	                            <td><div id="name"></div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label for="userType">反馈内容:</label></td>
	                            <td id="contents"></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label for="userPassword">反馈时间:</label></td>
	                            <td><div id="time"></div></td>
	                          </tr>
                       	    </tbody>
                       </table>
                    </div>
				</div>
			</div>
		</form>
	</div>
    </body>
</html>