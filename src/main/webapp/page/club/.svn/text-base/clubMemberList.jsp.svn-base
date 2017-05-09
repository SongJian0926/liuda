<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>俱乐部会员管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/clubMember/findClubMemberByClubIdPage";
	
	function getfilter(){
		var _jsonFilter = "{";
		if(getUrlParam("clubId")){
			_jsonFilter += "'search_EQ_clubId':'"+getUrlParam("clubId")+"',";
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
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
				               		  <th width="60">成员类型</th>
          		               		  <th width="60">会员名称</th>
				               		  <th width="60">会员头像</th>
				               		  <th width="60">会员手机</th>
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
									<td>{{if memberType==1}}部长{{else memberType==2}}管理员{{else memberType==3}}普通成员{{else}}无效{{/if}}</td>
									<td><xmp>{{if userVo}}{{:userVo.userName}}{{/if}}</xmp></td>
									<td>{{if userVo.photo}}<img src="../../{{:userVo.photo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td><xmp>{{if userVo}}{{:userVo.mobile}}{{/if}}</xmp></td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>