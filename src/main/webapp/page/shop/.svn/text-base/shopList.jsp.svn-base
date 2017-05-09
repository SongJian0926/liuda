<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>商品管理管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/shop/findShopsNEStatusPage";
 	var editUrl = 'shopEdit.jsp?id=';
	var operateUrl = root + 'cms/shop/operateShopByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_shopName").val() && $("#search_shopName").val().length > 0){
			_jsonFilter += "'search_LIKE_shopName':'"+$("#search_shopName").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_price").val() && $("#search_price").val().length > 0){
			_jsonFilter += "'search_LIKE_price':'"+$("#search_price").val().replace(/'/g,"\"").trim()+"',";
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
	                  <!-- <form id="search_form" class="form-horizontal"> -->
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商品名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="商品名称" class="form-control input-sm" id="search_shopName" name="shopName"/>
								</div>
      						</div>
							<input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                 <!--  </form> -->
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                          <input type="button" value="上架" id="openSome1" class="btn btn-color">
                          <input type="button" value="下架" id="lockSome1" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">商品名称</th>
				               		  <th width="80">商品图片</th>
				               		  <th width="80">商品介绍</th>
				               		 <!--  <th width="80">商品规格</th>
				               		  <th width="80">商品库存</th> -->
				               		  <th width="80">商品状态</th>
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
									<td>{{:shopName}}</td>
									<td>{{if imgUrl}}<img src="../../{{:imgUrl}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{subString:shopDesc}}</td>
									<td>{{if status==1}}未上架{{else status==2}}已上架{{else status==3}}已删除{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>