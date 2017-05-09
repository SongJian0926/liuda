<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>字典子表管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/dictItem/findDictItemNEStatusPage";
	var editUrl = 'dictItemEdit.jsp?id=';
	var operateUrl = root + 'cms/dictItem/operateDictItemByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_dictId").val() && $("#search_dictId").val().length > 0){
			_jsonFilter += "'search_EQ_dictId':'"+$("#search_dictId").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_dictName").val() && $("#search_dictName").val().length > 0){
			_jsonFilter += "'search_EQ_dictName':'"+$("#search_dictName").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_sort").val() && $("#search_sort").val().length > 0){
			_jsonFilter += "'search_EQ_sort':'"+$("#search_sort").val().replace(/'/g,"\"").trim()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	$(function(){
		$.ajax({
			url:root+'cms/dict/findDictNEStatusPage',
			dataType:'json',
			type:'POST',
			data:{		
				pageSize:100000,
				
			},
		
			success:function(data){
					if(data.code == 1){
						var _options = "<option value=''>请选择</option>";					
		            	$.each(data.object.content,function(){
		            			_options += "<option value='" + this.id + "'>" + this.dictName + "</option>";
		            	});
		            	$('#search_dictId').html(_options);				            	
					}
					else{
						alert(data.message);
					}
				}
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">字典名称：</label>
       				 			<div class="col-md-8">
       				 			<select id="search_dictId" class="form-control input-sm" name="dictId">
       				 			</select>
									<!-- <input type="text" placeholder="字典名称" class="form-control input-sm" id="search_dictId" name="dictId"/> -->
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">选项名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="字典选项名称" class="form-control input-sm" id="search_dictName" name="dictName"/>
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
				               		  <th width="80">字典选项名称</th>
				               		  <th width="80">排序</th>

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
									<td><input id="ck_{{:dictItemVo.id}}" ckId="{{:dictItemVo.id}}" ckStatus="{{:dictItemVo.status}}" class="ckSelect" type="checkbox" /></td>
									<td><xmp>{{getLineStr:dictName 20}}</xmp></td>
									<td><xmp>{{getLineStr:dictItemVo.dictName 20}}</xmp></td>
									<td>{{:dictItemVo.sort}}</td>
									
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>