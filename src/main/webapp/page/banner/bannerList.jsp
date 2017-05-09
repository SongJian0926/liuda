<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>Banner图管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/banner/findBannerNEStatusPage";
	var editUrl = 'bannerEdit.jsp?id=';
	var operateUrl = root + 'cms/banner/operateBannerByIds';
	function getfilter(){
		var _jsonFilter = "{";
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
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addbannerSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">图片</th>
				               		  <th width="80">链接地址</th>
				               		  <th width="80">排序值</th>
				               		  <th width="80">状态</th>
                                  </tr>
                              </thead>
                              <!-- 表格内容 start -->
                              <tbody id="mycontent"></tbody>
                              <!-- 表格内容 end -->
                          </table>
                          <!-- 分页标签 start -->
                          <div class="row page_big_div" id="displayPage" style="display:none"></div>
                          <!-- 分页标签 end -->
                          <script id="tableContentTmple" type="text/x-jsrender">
								<tr>
									<td><input id="ck_{{:id}}" ckId="{{:id}}" data-id="{{:#index+1}}" ckStatus="{{:status}}" class="ckSelect" type="checkbox" /></td>
									<td>{{if picurl}}<img src="../../{{:picurl}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td style="text-align:left;vertical-align:middle;" >{{getLineStr:linkurl 100}}</td>
									<td>{{:sort}}</td>
									<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>