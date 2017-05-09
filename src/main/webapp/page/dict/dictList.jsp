<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>字典主表管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/dict/findDictNEStatusPage";
	var editUrl = 'dictEdit.jsp?id=';
	var operateUrl = root + 'cms/dict/operateDictByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_dictName").val() && $("#search_dictName").val().length > 0){
			_jsonFilter += "'search_LIKE_dictName':'"+$("#search_dictName").val().replace(/'/g,"\"").trim()+"',";
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">字典名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="字典名称" class="form-control input-sm" id="search_dictName" name="dictName"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color">
                        <!--   <input type="button" value="启用" id="openSome" class="btn btn-success">
                          <input type="button" value="锁定" id="lockSome" class="btn btn"> -->
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">字典名称</th>
          		               		  <th width="80">类型</th>
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
									<td><xmp>{{getLineStr:dictName 20}}</xmp></td>
									<td>{{if type==1}}攻略标签{{else type==2}}赛事标签{{else type==3}}俱乐部类型{{else type==4}}俱乐部等级{{else type==5}}俱乐部兴趣标签{{/if}}</td>
									<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>