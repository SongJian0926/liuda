<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>财务统计</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/order/findAccountList";
	function getfilter(){
		var _jsonFilter = "{";
				if(($("#startTime").val() && $("#startTime").val().length > 0) && $("#endTime").val()<=0){
			_jsonFilter += "'search_GT_createTime':'"+$("#startTime").val()+"',";
		}       
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && $("#startTime").val().length<=0){
			_jsonFilter += "'search_LT_createTime':'"+$("#endTime").val()+"',";
		} 
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && ($("#startTime").val() && $("#startTime").val().length > 0)){
			_jsonFilter += "'search_BETWEEN_createTime':'"+$("#startTime").val()+","+$("#endTime").val()+"',";
		}
		        if(($("#objectName").val() && $("#objectName").val().length > 0) ){
					_jsonFilter += "'search_LIKE_objectname':'"+$("#objectName").val().replace(/'/g,"\"").trim()+"',";
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
	        location.href = root + 'cms/order/fileDownload?jsonFilter=' + encodeURI(encodeURI(getfilter()));
	    }
	});
	
	</script>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box">
                  <!-- <form id="search_form" class="form-horizontal">-->
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
                      		<div class="form-group  col-md-3 search-form">
	    						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商家名称：</label>
	   				 			<div class="col-md-8">
									<input type="text" class="form-control input-sm" id="objectName" name="objectName"/>
								</div>
							</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">统计时间：</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="开始时间" readOnly class="form-control input-sm" id="startTime" name="startTime"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">至</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="结束时间" readOnly class="form-control input-sm" id="endTime" name="endTime"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
                      	<!-- </form>-->
                      <div class="box-body table-responsive" style="clear:both;">
                      <input type="button" value="导出" id="dataexport" class="btn btn-success">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <!-- <th width="30"><input type="checkbox" id="_selectAll" /></th> -->
                                      <th width="20"></th>
                                      <th width="80">商家名称</th>
                                      <th width="80">销量</th>
				               		  <th width="80">总额</th>
				               		  
				               		 
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
									{{!--<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" class="ckSelect" type="checkbox" /></td>--}}
									<td>{{:#index+1}}</td>										
									<td><xmp>{{:hotel.hotelName}}</xmp></td>
									<td>{{:id}}</td>
									<td>{{:orderPrice}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>