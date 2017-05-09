<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>用户管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript" src="../../js/custom/user.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/user/findUserNEStatusPage";
	var editUrl = 'userEdit.jsp?id=';
	var operateUrl = root + 'cms/user/operateUserByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_userName").val() && $("#search_userName").val().length > 0){
			_jsonFilter += "'search_LIKE_userName':'"+$("#search_userName").val().replace(/'/g,"\"").trim()+"',";
		}
				
				if($("#search_mobile").val() && $("#search_mobile").val().length > 0){
			_jsonFilter += "'search_LIKE_mobile':'"+$("#search_mobile").val().replace(/'/g,"\"").trim()+"',";
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">手机号：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="手机号" class="form-control input-sm" id="search_mobile" name="mobile"/>
								</div>
      						</div>
	                         <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
				      		<div class="box-body table-responsive" style="clear:both;">
				           <input type="button" value="启用" id="openSome" class="btn btn-color">
                           <input type="button" value="停用" id="lockSome" class="btn btn-color">
                           <input type="button" value="审核" id="bigShot" class="btn btn-color">
                      </div>
                      	</div>
	                  </form>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">用户名</th>
				               		  <th width="80">手机号</th>
				               		  <th width="80">头像</th>
				               		  <th width="80">是否是大咖</th>
				               		  <th width="80">邀请码</th>
				               		  <th width="80">大咖申请状态</th>
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
									<td>{{:userName}}</td>
									<td>{{:mobile}}</td>
									<td>{{if photo}}<img src="../../{{:photo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{if bigShot==1}}是{{else}}否{{/if}}</td>
									<td>{{:exclusiveCode}}</td>
									<td>{{if applyStatus==1}}提交审核{{else applyStatus==2}}审核通过{{else applyStatus==3}}审核失败{{/if}}</td>
									<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>