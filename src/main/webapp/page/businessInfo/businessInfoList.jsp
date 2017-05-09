<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>商家用户管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/businessInfo/findBusinessInfoNEStatusPage";
	var editUrl = 'businessInfoEdit.jsp?id=';
	var operateUrl = root + 'cms/businessInfo/operateBusinessInfoByIds';
	function getfilter(){
		var _jsonFilter = "{";
		if($("#search_businessName").val() && $("#search_businessName").val().length > 0){
			_jsonFilter += "'search_LIKE_businessName':'"+$("#search_businessName").val().replace(/'/g,"\"").trim()+"',";
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
	$(document).ready(function(){
		//生成账号
		$('#createSome').click(function(){
			$('#createCountModal').modal();
		});
		//确定生成
		$('#createAcount').click(function(){
			$.ajax({
				url : root + 'cms/businessInfo/saveBusinessInfo',
				type : "POST",
				data : {
					numbers: $('#accountNumber').val()
				},
				success : function(data) {
					if (data.code == 1) {
						window.location.reload();
					} else {
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
	                  <form id="search_form" class="form-horizontal">
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
           	            	<div class="form-group  col-md-3 search-form">
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
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                           <input type="button" value="生成账号" id="createSome" class="btn btn-color">
                           <input type="button" value="启用" id="openSome" class="btn btn-color">
                           <input type="button" value="停用" id="lockSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">账号</th>
				               		  <th width="100">商家名称</th>
				               		  <th width="80">商家图片</th>
				               		  <th width="80">商家类型</th>
				               		  <th width="80">联系电话</th>
				               		  <th width="200">商家地址</th>
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
									<td>{{:account}}</td>
									<td>{{getLineStr:businessName 10}}</td>
									<td>{{if imgUrl}}<img src="../../{{:imgUrl}}" height="40" width="50">{{/if}}</td>
									<td>{{if type == 0}}酒店{{else type == 1}}景点{{/if}}</td>
									<td>{{:telphone}}</td>
									<td style="text-align:left;vertical-align:middle;">{{getLineStr:address 25}}</td>
                                    <td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
      <div class="modal fade" id="createCountModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="createCountForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">生成账号</h4>
					</div>
					<div class="modal-body" id="detail_content">
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-4">请选择个数：</label>
							<div class="controls col-md-3">
								<select class="form-control input-sm" id="accountNumber">
									<option value="1">1</option>
									<option value="5">5</option>
									<option value="10">10</option>
									<option value="20">20</option>
								</select>
							</div>
							
						</div>
						<div class="form-group">
							
								
								<div class="controls col-md-3"><font style="color:red;padding-left:90px">注：初始密码是：123456</font></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="createAcount" class="btn btn-primary">确定</button>
					</div>
				</div>
			</div>
		</form>
	</div>
    </body>
</html>