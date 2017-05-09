<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>酒店管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/hotel/findHotelNEStatusPage";
	var editUrl = 'hotelEdit.jsp?id=';
	var operateUrl = root + 'cms/hotel/operateHotelByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_hotelName").val() && $("#search_hotelName").val().length > 0){
			_jsonFilter += "'search_LIKE_hotelName':'"+$("#search_hotelName").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_hotelType").val() && $("#search_hotelType").val().length > 0){
			_jsonFilter += "'search_LIKE_hotelType':'"+$("#search_hotelType").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_telphone").val() && $("#search_telphone").val().length > 0){
			_jsonFilter += "'search_LIKE_telphone':'"+$("#search_telphone").val().replace(/'/g,"\"").trim()+"',";
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">酒店名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="酒店名称" class="form-control input-sm" id="search_hotelName" name="hotelName"/>
								</div>
      						</div>
			            	
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">酒店类型：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="酒店类型" class="form-control input-sm" id="search_hotelType" name="hotelType"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">电话：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="电话" class="form-control input-sm" id="search_telphone" name="telphone"/>
								</div>
      						</div>
			            	
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color">
                         <!--  <input type="button" value="启用" id="openSome" class="btn btn-success">
                          <input type="button" value="锁定" id="lockSome" class="btn btn"> -->
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">酒店名称</th>
				               		  <th width="80">酒店图片</th>
				               		  <th width="80">酒店类型</th>
				               		  <th width="80">电话</th>
				               		  <th width="80">酒店地址</th>
				               		  <th width="80">状态</th>
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
									<td><xmp>{{subString:hotelName}}</xmp></td>
									<td>{{if picurl}}<img src="../../{{:picurl}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td><xmp>{{subString:hotelType}}</xmp></td>
									<td>{{:telphone}}</td>
									<td><xmp>{{subString:address}}</xmp></td>
									<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>