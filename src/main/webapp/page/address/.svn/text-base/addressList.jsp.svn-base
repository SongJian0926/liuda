<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>收货地址管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	$.views.converters({
		"subString":function(str){
			if(str==null || str==""){
				return "";
			}
	    	if(str.length>30){
	    		return str.substring(0,30)+"...";
	    	}else{
	    		return str;
	    	}
		},
	}); 
	var contentUrl = root + "cms/address/findAddressList";
	var editUrl = 'addressEdit.jsp?id=';
	var operateUrl = root + 'cms/address/operateAddressByIds';
	function getfilter(){
		var _jsonFilter = "{";
			/* 	
				if($("#search_Province").val() && $("#search_Province").val().length > 0){
			_jsonFilter += "'search_EQ_Province':'"+$("#search_Province").val().replace("'","\"")+"',";
		}
				if($("#search_city").val() && $("#search_city").val().length > 0){
			_jsonFilter += "'search_EQ_city':'"+$("#search_city").val().replace("'","\"")+"',";
		} */
				if($("#search_userName").val() && $("#search_userName").val().length > 0){
			_jsonFilter += "'search_LIKE_user_name':'"+$("#search_userName").val().replace(/'/g,"\"").trim()+"',";
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
	                 <!--  <form id="search_form" class="form-horizontal"> -->
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
			            	<!-- <div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">省份：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="省份" class="form-control input-sm" id="search_Province" name="Province"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">城市：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="城市" class="form-control input-sm" id="search_city" name="city"/>
								</div>
      						</div> -->
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">用户名：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="用户名" class="form-control input-sm" id="search_userName" name="userName"/>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  <!-- </form> -->
                      <div class="box-body table-responsive" style="clear:both;">
                          <!-- <input type="button" value="新增" id="addSome" class="btn btn-success">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-info">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-danger"> -->
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <!-- <th width="30"><input type="checkbox" id="_selectAll" /></th> -->
				               		  <th width="20"></th>
				               		  <th width="80">所属用户</th>
          		               		  <th width="80">收货人</th>
				               		  <th width="80">手机号</th>
				               		  <th width="80">省份</th>
				               		  <th width="80">城市</th>
				               		  <th width="80">区域</th>
				               		  <th width="80">详细地址</th>
				               		  <th width="10">是否默认</th>
				               		  <!-- <th width="80">状态</th> -->
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
									{{!--<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" class="ckSelect" type="checkbox" /></td>--}}
									<td>{{:#index+1}}</td>									
									<td><xmp>{{:userName}}</xmp></td>
									<td><xmp>{{:consigneeName}}</xmp></td>
									<td>{{:mobile}}</td>
									<td>{{:province}}</td>
									<td>{{:city}}</td>
									<td>{{:area}}</td>
									<td>{{getLineStr:detailAddress 10}}</td>
									<td>{{if isDefault==0}}否{{else}}是{{/if}}</td>
									{{!--<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>--}}
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>