<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>房间管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/room/findRoomList";
	var editUrl = 'roomEdit.jsp?id=';
	var operateUrl = root + 'cms/room/operateRoomByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_hotelName").val() && $("#search_hotelName").val().length > 0){
			_jsonFilter += "'search_LIKE_hotel_name':'"+$("#search_hotelName").val().replace(/'/g,"\"").trim()+"',";
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">酒店名：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="酒店名" class="form-control input-sm" id="search_hotelName" name="hotelName"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">酒店名称</th>
				               		  <th width="80">房间类型</th>
				               		  <th width="80">房间价格</th>
				               		  <th width="80">房间logo</th>
				               		  <th width="80">是否含早</th>
				               		  <th width="80">面积</th>
				               		  <th width="80">床位</th>
				               		  <th width="80">浴室</th>
				               		  <th width="80">通讯</th>
				               		  <th width="80">设施</th>
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
									<td>{{subString:hotelName}}</td>
									<td>{{:type}}</td>
									<td>{{:price}}</td>
									<td>{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{if breakfast==0}}无{{else breakfast==1}}单早{{else breakfast==2}}双早{{/if}}</td>
									<td>{{:area}}</td>
									<td>{{:beds}}</td>
									<td>{{:bathRoom}}</td>
									<td>{{:communication}}</td>
									<td>{{:establishment}}</td>
									<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>