<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>赛事活动订单管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/matchOrder/findMatchOrderVoNEStatusPage";
 	var editUrl = "matchOrderEdit.jsp";
	var operateUrl = root + "cms/matchOrder/operateMatchOrderByIds";
	
	function getfilter(){
		var _jsonFilter = "{";
		 	if($("#startTime").val() && $("#startTime").val().length > 0){
				_jsonFilter += "'search_GTE_createTime':'"+$("#startTime").val()+"',";
			}       
	        if($("#endTime").val() && $("#endTime").val().length > 0){
				_jsonFilter += "'search_LTE_createTime':'"+$("#endTime").val()+" 23:59:59"+"',";
			} 
			if($("#search_orderStatus").val() && $("#search_orderStatus").val() != ""){
				_jsonFilter += "'search_EQ_orderStatus':'"+$("#search_orderStatus").val()+"',";
			}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
		
	$(document).on('click','#detailMatchOrderSome',function(){
		var updateArray = [];
	    var id="";
	    //商家名称
	    //var name;
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				id =$(this).attr("ckId");
				updateArray.push(id);
				//name=$(this).parents('tr').find('td:eq(2)').text();
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
		window.open("matchOrderDetailList.jsp?matchOrderId="+id, "window", "dialogWidth:1000px;dialogHeight:600px;scroll:yes;status:no;help:no;resizable:yes"); 
	});
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">订单时间：</label>
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">订单状态：</label>
       				 			<div class="col-md-8">
									<select id="search_orderStatus"> 
										<option value="">请选择</option>
										<option value="1">未支付</option>
										<option value="2">已支付</option>
										<!-- <option value="3">取消报名</option> -->
										<option value="4">订单失效</option>
									</select>
								</div>
      						</div>
							<input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                 <!--  </form> -->
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="订单明细" id="detailMatchOrderSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <!-- <th width="30">俱乐部ID</th>-->
          		               		  <th width="80">订单号</th>
				               		  <th width="60">支付平台</th>
				               		  <th width="50">类型</th>
				               		  <th width="60">姓名</th>
				               		  <th width="60">手机号</th>
				               		  <th width="50">订单数量</th>
				               		  <th width="50">总金额</th>
				               		  <th width="50">线上金额</th>
				               		  <th width="50">线下金额</th>
				               		  <th width="50">是否全款</th>
				               		  <th width="50">订单状态</th>
				               		  <th width="50">订单时间</th>
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
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:orderStatus}}" ckType="{{:type}}" class="ckSelect" type="checkbox" /></td>
									<td>{{:orderNo}}</td>
									<td>{{if payType==0}}微信{{else payType==1}}支付宝{{else payType==2}}银联{{else}}无效{{/if}}</td>
									<td>{{if type==1}}赛事活动{{else type==2}}俱乐部活动{{else}}无效{{/if}}</td>
									<td><xmp>{{:userVo.userName}}</xmp></td>
									<td><xmp>{{:userVo.mobile}}</xmp></td>
									<td>{{:orderNumber}}</td>
									<td>{{:totalAmt}}</td>
									<td>{{:onlineAmt}}</td>
									<td>{{:offlineAmt}}</td>
									<td>{{if isFull==0}}定金{{else isFull==1}}全款{{else}}无效{{/if}}</td>
									<td>{{if orderStatus==1}}未支付{{else orderStatus==2}}已支付{{else orderStatus==4}}订单失效{{else}}无效{{/if}}</td>
									<td>{{:createTime}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>