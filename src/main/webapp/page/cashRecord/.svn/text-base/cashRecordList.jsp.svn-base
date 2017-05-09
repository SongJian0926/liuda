<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>商家端提现记录</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/cashRecord/findCashRecordNEStatusPage";
	var editUrl = 'cashRecordEdit.jsp?id=';
	var operateUrl = root + 'cms/cashRecord/operateCashRecordByIds';
	function getfilter(){
		var _jsonFilter = "{";
			
				if($("#search_type").val() && $("#search_type").val().length > 0){
			_jsonFilter += "'search_EQ_type':'"+$("#search_type").val().replace("'","\"").trim()+"',";
		}
				if($("#search_cashStatus").val() && $("#search_cashStatus").val().length > 0){
			_jsonFilter += "'search_EQ_cashStatus':'"+$("#search_cashStatus").val().replace("'","\"").trim()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	//导出
	$(document).on('click','#dataexport',function(){
		
	    //获取数据长度
	    var tr=$("#mycontent").find('tr').length;
	    if(tr==0){
	        alert("没有数据，不能导出！");
	        return;
	    }else{
	        location.href = root + 'cms/cashRecord/fileDownload?jsonFilter=' + encodeURI(encodeURI(getfilter()));
	    }
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
           	            	<!-- <div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商家名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="商家名称" class="form-control input-sm" id="search_objectId" name="objectId"/>
								</div>
      						</div> -->
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商家类型：</label>
       				 			<div class="col-md-8">
									<select id="search_type" class="form-control input-sm" name="type">
       				 				<option value="">请选择</option>
       				 				<option value="0">酒店</option>
       				 				<option value="1">景点</option>
       				 			</select>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">提现状态：</label>
       				 			<div class="col-md-8">
									<select id="search_cashStatus" class="form-control input-sm" name="cashStatus">
	       				 				<option value="">请选择</option>
	       				 				<option value="0">已提交</option>
	       				 				<option value="1">提现成功</option>
	       				 				<option value="2">打款中</option>
	       				 				<option value="3">打款失败</option>
	       				 			</select>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <!-- <input type="button" value="新增" id="addSome" class="btn btn-success">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-info">
                          <input type="button" value="启用" id="openSome" class="btn btn-success">
                          <input type="button" value="锁定" id="lockSome" class="btn btn">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-danger"> -->
                           <input type="button" value="导出" id="dataexport" class="btn btn-color">
                           <input type="button" value="打款" id="sendMoney" class="btn btn-color">
                           <input type="button" value="打款失败" id="sendFail" class="btn btn-color">
                           <input type="button" value="打款成功" id="sendSuccess" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">商家名称</th>
				               		  <th width="80">商家类型</th>
				               		  <th width="80">提现金额</th>
				               		  <th width="80">真实姓名</th>
				               		  <th width="80">开户银行</th>
				               		  <th width="80">银行卡号</th>
				               		  <th width="80">银行支行</th>
				               		  <th width="80">货币码</th>
				               		  <th width="80">支付用途</th>
				               		  <th width="80">开户城市</th>
				               		  <th width="80">创建时间</th>
				               		  <th width="80">提现状态</th>
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
									<td style="text-align:left;vertical-align:middle;">{{getLineStr:objectName 5}}</td>
									<td>{{if type==0}}酒店{{else type==1}}景点{{/if}}</td>
									<td>{{:money}}</td>
									<td>{{if mybank}}{{getLineStr:mybank.realName 7}}{{else}}{{/if}}</td>
									<td style="text-align:left;vertical-align:middle;">{{getLineStr:bankName 5}}</td>
									<td>{{:account}}</td>
									<td style="text-align:left;vertical-align:middle;">{{if mybank}}{{getLineStr:mybank.childBankName 5}}{{else}}{{/if}}</td>
									<td>{{if mybank}}{{if mybank.currencyCode=='01'}}01 人民币{{else}}其他{{/if}}{{else}}{{/if}}</td>
									<td>{{if mybank}}{{:mybank.payUse}}{{else}}{{/if}}</td>
									<td>{{if mybank}}{{:mybank.cityName}}{{else}}{{/if}}</td>
									<td>{{:createTime}}</td>
									<td>{{if cashStatus==0}}已提交{{else cashStatus==1}}提现成功{{else cashStatus==2}}打款中{{else cashStatus==3}}打款失败{{/if}}</td>
							</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>