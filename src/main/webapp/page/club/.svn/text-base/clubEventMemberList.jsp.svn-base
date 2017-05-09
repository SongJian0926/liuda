<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>活动报名人员管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	//var contentUrl = root + "cms/matchOrderDetail/findMatchOrderDetailNEStatusPage";
	var contentUrl = root + "cms/matchOrderDetail/findMatchOrderDetailByMatchIdAndTypePage";
	
	function getfilter(){
		var _jsonFilter = "{";
		if(getUrlParam("clubEventId")){
			_jsonFilter += "'search_EQ_matchId':'"+getUrlParam("clubEventId")+"',";
		}
		_jsonFilter += "'search_EQ_type':'2',";
		_jsonFilter += "'search_EQ_isRefund':'0',";
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
			url : root + "cms/matchOrderDetail/updateRredeemCode",
			type : "POST",
			dataType:'json',
			data : {
				matchId:getUrlParam("clubEventId"),
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
	        location.href = root + 'cms/matchOrderDetail/fileDownloadEventOrder?jsonFilter=' + getfilter();
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
	          	<div>
	              <div class="col-xs-12">
	                  <div class="box">
	                      <div class="box-body table-responsive">
	                          <table id="example2" class="table table-bordered table-hover">
	                              <thead>
	                                  <tr>
					               		  <th width="60">姓名</th>
	          		               		  <th width="60">手机号</th>
					               		  <th width="60">性别</th>
					               		  <th width="60">年龄</th>
					               		  <th width="60">身份证</th>
					               		  <th width="60">车型</th>
					               		  <th width="60">车牌号码</th>
					               		  <th width="60">是否核销</th>
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
									<td>{{:age}}</td>
									<td>{{:idCard}}</td>
									<td>{{:carModel}}</td>
									<td>{{:carNumber}}</td>
									<td>{{if isValid==0}}否{{else isValid==1}}是{{else}}无效{{/if}}</td>
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