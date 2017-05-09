<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>公告通知管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/notice/findNoticeNEStatusPage";
	var editUrl = 'noticeEdit.jsp?id=';
	var operateUrl = root + 'cms/notice/operateNoticeByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_title").val() && $("#search_title").val().length > 0){
			_jsonFilter += "'search_LIKE_title':'"+$("#search_title").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_type").val() && $("#search_type").val().length > 0){
			_jsonFilter += "'search_EQ_type':'"+$("#search_type").val().replace(/'/g,"\"").trim()+"',";
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">公告类型：</label>
       				 			<div class="col-md-8">
       				 				<select id="search_type" class="form-control input-sm" name="type">
	       				 				<option value="">请选择</option>
	       				 				<option value="1">黑板报</option>
	       				 				<option value="2">活动公告</option>
	       				 				<option value="3">新闻报道</option>
	       				 			</select>
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
          		               		  <th width="80">标题</th>
				               		  <th width="80">图片</th>
				               		  <th width="80">公告类型</th>
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
									<td>{{getLineStr:title 30}}</td>
									<td>{{if imgPath}}<img src="../../{{:imgPath}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{if type==1}}黑板报{{else type==2}}活动公告{{else type==3}}新闻报道{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>