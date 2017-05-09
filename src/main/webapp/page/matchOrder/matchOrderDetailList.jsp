<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>订单详情</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/matchOrderDetail/findMatchOrderDetailVoNEStatusPage";
 	var editUrl = "matchOrderDetailEdit.jsp";
	var operateUrl = root + "cms/matchOrderDetail/operateMatchOrderDetailByIds";
	
	function getfilter(){
		var _jsonFilter = "{";
			if(getUrlParam("matchOrderId")){
				_jsonFilter += "'search_EQ_matchOrderId':'"+getUrlParam("matchOrderId")+"',";
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
                      	</div>
	                 <!--  </form> -->
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
         		               		<th width="60">联系人</th>
									<th width="60">手机号</th>
									<th width="60">性别</th>
									<th width="60">血型</th>
									<th width="60">身份证号</th>
									<th width="60">驾驶证档案号</th>
									<th width="60">参赛组别</th>
									<th width="60">参赛车型</th>
									<th width="60">所属车队</th>
									<th width="60">紧急联系人手机号</th>
									<th width="60">是否核销</th>
									<th width="60">状态</th>
									<th width="60">参赛经历 </th>
									<th width="60">荣誉</th>
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
									<td>{{:realName}}</td>
									<td>{{:mobile}}</td>
									<td>{{if sex==0}}男{{else sex==1}}女{{else}}无效{{/if}}</td>
									<td>{{:bloodType}}</td>
									<td>{{:idCard}}</td>
									<td>{{:profileNum}}</td>
									<td>{{:matchGroup}}</td>
									<td>{{:carModel}}</td>
									<td>{{:carTeam}}</td>
									<td>{{:emeMobile}}</td>
									<td>{{if isValid==0}}否{{else isValid==1}}是{{else}}无效{{/if}}</td>
									<td>{{if enrollStatus==0}}正常{{else enrollStatus==1}}退款申请中{{else enrollStatus==2}}退款审核失败{{else enrollStatus==3}}退款中{{else enrollStatus==4}}退款成功{{else}}无效{{/if}}</td>
									<td>{{subString:experience}}</td>
									<td>{{subString:honor}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>