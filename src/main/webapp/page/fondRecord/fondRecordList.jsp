<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>现金记录管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript" src="../../js/custom/fondRecord.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/fondRecord/findFondRecordNEStatusPage";
	var editUrl = 'fondRecordEdit.jsp?id=';
	var operateUrl = root + 'cms/fondRecord/operateFondRecordByIds';
	function getfilter(){
		var _jsonFilter = "{";
				
				if($("#search_userId").val() && $("#search_userId").val().length > 0){
			_jsonFilter += "'search_EQ_userId':'"+$("#search_userId").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_present_state").val() && $("#search_present_state").val().length > 0){
			_jsonFilter += "'search_EQ_present_state':'"+$("#search_present_state").val().replace(/'/g,"\"").trim()+"',";
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
	        alert("没有数据，不能导出");
	        return;
	    }else{
	        location.href = root + 'cms/fondRecord/fileDownload?jsonFilter=' + encodeURI(encodeURI(getfilter()));
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
           	            	
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">用户名：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="用户名" class="form-control input-sm" id="search_userId" name="userName"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">提现状态：</label>
       				 			<div class="col-md-8">
       				 				<select id="search_present_state" class="form-control input-sm" name="present_state">
	       				 				<option value="">请选择</option>
	       				 				<option value="1">已提交</option>
	       				 				<option value="2">提现成功</option>
	       				 				<option value="3">打款失败</option>
	       				 				<option value="4">打款中</option>
	       				 			</select>
									
								</div>
      						</div>			            
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                       <input type="button" value="导出" id="dataexport" class="btn btn-color">
                       <input type="button" value="打款" id="transfer" class="btn btn-color">
                       <input type="button" value="打款失败" id="transferFail" class="btn btn-color">
                       <input type="button" value="打款成功" id="transferSuccess" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">金额</th>
				               		  <th width="80">来源</th>
				               		  <th width="80">用户名</th>
				               		  <th width="80">银行卡号</th>
				               		  <th width="80">持卡人</th>
				               		  <th width="80">身份证号</th>
				               		  <th width="80">开户银行</th>
				               		  <th width="80">所属区域</th>
				               		  <th width="80">支行名称</th>
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
									<td>{{:price}}</td>
									<td>{{:origin}}</td>
									<td>{{:userVo.userName}}</td>
									<td>{{:bankCardId}}</td>
									<td>{{:cardHolder}}</td>
									<td>{{:idcard}}</td>
									<td>{{:openingBank}}</td>
									<td>{{:openingArea}}</td>
									<td>{{:subBankNam}}</td>
									<td>{{if presentState==1}}已提交{{else presentState==2}}打款成功 {{else presentState==3}}打款失败{{else  presentState==4}}打款中{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>