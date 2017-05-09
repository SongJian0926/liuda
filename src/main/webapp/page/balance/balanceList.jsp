<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>结算管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/businessInfo/findBalanceList";
	var editUrl = 'balanceEdit.jsp?id=';
	var operateUrl = root + 'cms/businessInfo/operateBusinessInfoById';
	function getfilter(){
		var _jsonFilter = "{";
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
           	            	<!-- <div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商家名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="商家名称" class="form-control input-sm" id="search_businessName" name="businessName"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">联系电话：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="联系电话" class="form-control input-sm" id="search_telphone" name="telphone"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn"> -->
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color"> 
                          <!-- <input type="button" value="删除" id="deleteSome" class="btn btn-danger"> -->
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
				               		  <th width="80">商家名称</th>
				               		  <th width="80">账期</th>
				               		  <th width="80">比例</th>
				               		  <th width="80">结算金额</th>
				               		 
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
									<td><xmp>{{:hotelVo.hotelName}}</xmp></td>
									{{!--<td><xmp>{{if accountPeriod}}{{:accountPeriod}}号到{{:accountPeriods}}号{{/if}}</xmp></td>--}}
									<td><xmp>{{if accountPeriod}}{{:accountPeriod}}{{/if}}</xmp></td>
									<td><xmp>{{if propotion}}{{:propotion}}：{{:propotions}}{{/if}}</xmp></td>
									<td><xmp>{{:hotelVo.totalPrice}}</xmp></td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>