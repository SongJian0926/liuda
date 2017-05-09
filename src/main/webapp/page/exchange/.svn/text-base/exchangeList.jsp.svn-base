<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>兑换码</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	
	var contentUrl = root + "cms/order/findExchangeNEStatusPage";

	function getfilter(){
		var _jsonFilter = "{";
			   if($("#search_bussniessName").val() && $("#search_bussniessName").val().length > 0){
			_jsonFilter += "'search_LIKE_bussniessName':'"+$("#search_bussniessName").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_orderNo").val() && $("#search_orderNo").val().length > 0){
			_jsonFilter += "'search_LIKE_orderNo':'"+$("#search_orderNo").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_orderStatus").val() && $("#search_orderStatus").val().length > 0){
			_jsonFilter += "'search_EQ_orderStatus':'"+$("#search_orderStatus").val().replace(/'/g,"\"").trim()+"',";
		}
		       if(($("#startTime").val() && $("#startTime").val().length > 0) && $("#endTime").val().length<=0){
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商家名称：</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="商家名称"  class="form-control input-sm" id="search_bussniessName" name="bussniessName"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">兑换码状态：</label>
       				 			<div class="col-md-8">
									<select class="form-control input-sm" id="search_orderStatus" name="orderStatus">
										<option value="">请选择</option>
										<option value="1">未兑换</option>
										<option value="2">已兑换</option>
									</select>
								</div>
      						</div>
      						<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">兑换时间：</label>
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
	                  </form>
                    
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
				               		 
				               		  <th width="80">兑换商家</th>
				               		  <th width="80">兑换商品</th>
				               		  <th width="80">兑换时间</th>
				               		  <th width="80">兑换码状态</th>  
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
									
									<td>{{if orderType==0}}{{getLineStr:roomVo.hotelName 20}}{{else orderType==1}}{{getLineStr:ticketsVo.touristName 20}}{{/if}}</td>
									<td>{{if orderType==0}}{{getLineStr:roomVo.type 20}}{{else orderType==1}}{{getLineStr:ticketsVo.ticketName 20}}{{/if}}</td>
									<td>{{:testTime}}</td>
									<td>{{if orderStatus==2}}已兑换{{else orderStatus==1}}未兑换{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>