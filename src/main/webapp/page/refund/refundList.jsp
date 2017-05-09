<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>退款管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/refundOrder/findRefundOrderVoNEStatusPage";
 	var editUrl = "refundEdit.jsp";
	var operateUrl = root + "cms/refundOrder/operateRefundOrderByIds";
	
	function getfilter(){
		var _jsonFilter = "{";
		 	if($("#startTime").val() && $("#startTime").val().length > 0){
				_jsonFilter += "'search_GTE_createTime':'"+$("#startTime").val()+"',";
			}       
	        if($("#endTime").val() && $("#endTime").val().length > 0){
				_jsonFilter += "'search_LTE_createTime':'"+$("#endTime").val()+" 23:59:59"+"',";
			} 
			if($("#search_refundStatus").val() && $("#search_refundStatus").val() != ""){
				_jsonFilter += "'search_EQ_refundStatus':'"+$("#search_refundStatus").val()+"',";
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">申请时间：</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="开始时间" readOnly class="form-control input-sm" id="startTime" name="createTime"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">至</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="结束时间" readOnly class="form-control input-sm" id="endTime" name="createTime"/>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">退款状态：</label>
       				 			<div class="col-md-8">
									<select id="search_refundStatus"> 
										<option value="">请选择</option>
										<option value="1">申请中</option>
										<option value="2">审核不通过</option>
										<option value="3">退款中</option>
										<option value="4">退款成功</option>
									</select>
								</div>
      						</div>
							<input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                 <!--  </form> -->
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="审核通过" id="successRefundSome" class="btn btn-color" status="3">
                          <input type="button" value="审核不通过" id="failRefundSome" class="btn btn-color" status="2">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <!-- <th width="30">俱乐部ID</th>-->
          		               		  <th width="80">退款单号</th>
				               		  <th width="5">支付平台</th>
				               		  <th width="60">类型</th>
				               		  <th width="100">名称</th>
				               		  <th width="60">姓名</th>
				               		  <th width="60">手机号</th>
				               		  <th width="30">退款金额</th>
				               		  <th width="10">审核状态</th>
				               		 <!--  <th width="50">审核时间</th> -->
				               		  <th width="50">创建时间</th>
				               		  <th width="120">退款原因</th>
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
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:refundStatus}}" ckType="{{:matchOrderVo.type}}" class="ckSelect" type="checkbox" /></td>
									<td>{{:refundNo}}</td>
									<td><xmp>{{if matchOrderVo.payType==0}}微信{{else matchOrderVo.payType==1}}支付宝{{else matchOrderVo.payType==2}}银联{{else}}无效{{/if}}</xmp></td>
									<td><xmp>{{if matchOrderVo.type==1}}赛事活动{{else matchOrderVo.type==2}}俱乐部活动{{else}}无效{{/if}}</xmp></td>
									<td>{{getLineStr:matchTitle 10}}</td>
									<td><xmp>{{:matchOrderDetailVo.realName}}</xmp></td>
									<td><xmp>{{:matchOrderDetailVo.mobile}}</xmp></td>
									<td>{{:refundAmt}}</td>
									<td>{{if refundStatus==1}}申请中{{else refundStatus==2}}审核不通过{{else refundStatus==3}}退款中{{else refundStatus==4}}退款成功{{else}}无效{{/if}}</td>
									{{!--<td>{{:refundApplyDate}}</td>--}}
									<td>{{:createTime}}</td>
									<td style="text-align:left;vertical-align:middle;">{{getLineStr:reason 15}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>