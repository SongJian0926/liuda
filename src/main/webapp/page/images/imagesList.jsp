<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>图片管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/images/findImagesNEStatusPage";
	var editUrl = 'imagesEdit.jsp?id=';
	var operateUrl = root + 'cms/images/operateImagesByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_picurl").val() && $("#search_picurl").val().length > 0){
			_jsonFilter += "'search_EQ_picurl':'"+$("#search_picurl").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_type").val() && $("#search_type").val().length > 0){
			_jsonFilter += "'search_EQ_type':'"+$("#search_type").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_sort").val() && $("#search_sort").val().length > 0){
			_jsonFilter += "'search_EQ_sort':'"+$("#search_sort").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_objectId").val() && $("#search_objectId").val().length > 0){
			_jsonFilter += "'search_EQ_objectId':'"+$("#search_objectId").val().replace(/'/g,"\"").trim()+"',";
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">图片地址：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="图片地址" class="form-control input-sm" id="search_picurl" name="picurl"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">图片类型：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="图片类型" class="form-control input-sm" id="search_type" name="type"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">排序值：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="排序值" class="form-control input-sm" id="search_sort" name="sort"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">对象id：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="对象id" class="form-control input-sm" id="search_objectId" name="objectId"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color">
                          <input type="button" value="启用" id="openSome" class="btn btn-color">
                          <input type="button" value="锁定" id="lockSome" class="btn btn">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">图片地址</th>
				               		  <th width="80">图片类型</th>
				               		  <th width="80">排序值</th>
				               		  <th width="80">对象id</th>
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
									<td>{{:picurl}}</td>
									<td>{{:type}}</td>
									<td>{{:sort}}</td>
									<td>{{:objectId}}</td>
									<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>