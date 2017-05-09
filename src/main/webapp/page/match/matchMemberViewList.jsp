<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>观赛人员管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/order/findOrderNEStatusPage";
	
	function getfilter(){
		var _jsonFilter = "{";
		_jsonFilter += "'search_EQ_objectId':'"+getUrlParam("matchId")+"',";
		///_jsonFilter += "'search_BETWEEN_orderStatus':'1,5',";
		_jsonFilter += "'search_GT_orderStatus':'0',";
		_jsonFilter += "'search_LT_orderStatus':'6',";
		_jsonFilter += "'search_EQ_orderType':'3',";
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	
	//兑换码
	$(document).on('click','#btnRredeemCode',function(){
	    if($("#redeemCode").val()=="")
		{
			alert("请输入兑换码");
			return;
		}
		$.ajax({
			url : root + "cms/order/updateRredeemCode",
			type : "POST",
			dataType:'json',
			data : {
				matchId:getUrlParam("matchId"),
				redeemCode:$("#redeemCode").val()
			},
			success : function(data) {
				if (data.code == 1) {
					alert("兑换成功");
					dipscontent1(getfilter(),contentUrl,1);
				} else {
					alert(data.message);
				}
			}
		});
	});
	//导出
	$(document).on('click','#dataexport',function(){
		
	    //获取数据长度
	    var tr=$("#mycontent").find('tr').length;
	    if(tr==0){
	        alert("没有数据，不能导出");
	        return;
	    }else{
	        location.href = root + 'cms/order/fileDownloadMatchTickets?jsonFilter=' + getfilter();
	    }
	});
	</script>
    </head>
    <body>
      <section>
			<div>
				<div class="col-xs-12">
					<div class="box">
						<div class="tabSelect" id="selectHeadId">
							<br>
							<div class="form-group  col-md-3 search-form">
								<label class="col-md-4 control-label controls" for="formGroupInputSmall">输入兑换码：</label>
								<div class="col-md-8">
									<input type="text" placeholder="兑换码" class="form-control input-sm" style="width:150px" id="redeemCode" name="redeemCode"/>
								</div>
							</div>
							<div class="form-group  col-md-3 search-form">
								<div class="col-md-8">
									<input type="button" value="校验" id="btnRredeemCode" class="btn btn-color">
								</div>
							</div>
						</div>
						<div class="box-body table-responsive" style="clear:both;">
							<label style="width:90%">人员名单：</label>
							<input type="button" value="导出" id="dataexport" class="btn btn-color">
						</div>
						<div class="col-xs-12">
							<div class="box">
								<div class="box-body table-responsive">
									<table id="example2" class="table table-bordered table-hover">
										<thead>
											<tr>
												<!-- <th width="60">订单编号</th> -->
												<th width="60">联系人</th>
												<th width="60">手机号</th>
												<th width="60">订单数量</th>
												<th width="60">兑换时间</th>
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
											<!-- <td>{{:orderNo}}</td> -->
											<td>{{:userName}}</td>
											<td>{{:mobile}}</td>
											<td>{{:orderNum}}</td>
											<td>{{:testTime}}</td>
										</tr>
									</script>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
    </body>
</html>