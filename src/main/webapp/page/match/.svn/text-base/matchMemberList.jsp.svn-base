<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>赛事报名人员管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	//var contentUrl = root + "cms/matchOrderDetail/findMatchOrderDetailNEStatusPage";
	var contentUrl = root + "cms/matchOrderDetail/findMatchOrderDetailByMatchIdAndTypePage";
	
	function getfilter(){
		var _jsonFilter = "{";
		if(getUrlParam("matchId")){
			_jsonFilter += "'search_EQ_matchId':'"+getUrlParam("matchId")+"',";
		}
		_jsonFilter += "'search_EQ_type':'1',";
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
	        location.href = root + 'cms/matchOrderDetail/fileDownloadMatchOrder?jsonFilter=' + getfilter();
	    }
	});
	//详情
	function opendetail(id){
		url= root + 'cms/matchOrderDetail/findMatchOrderDetailById';
		$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		data:{
			modelId:id
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
				if(data.object)
				{
					var tblContentHtml = $("#matchContentTmple").render(data.object);
					$("#matchdetail").html(tblContentHtml);
					$("#myModals").modal();
				}
				else
				{
					$("#matchdetail").html("");
				}
		    }
		}
	   });
	}
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
												<th width="60">联系人</th>
												<th width="60">手机号</th>
												<th width="60">性别</th>
												<th width="60">血型</th>
												<th width="60">身份证号</th>
												<!-- <th width="60">驾驶证档案号</th> -->
												<th width="60">参赛组别</th>
												<th width="60">参赛车型</th>
												<th width="60">所属车队</th>
												<th width="60">紧急联系人手机号</th>
												<th width="60">是否核销</th>
												<th width="60">详情 </th>
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
											{{!--<td>{{:profileNum}}</td>--}}
											<td>{{getLineStr:matchGroup 7}}</td>
											<td>{{getLineStr:carModel 7}}</td>
											<td>{{getLineStr:carTeam 7}}</td>
											<td>{{:emeMobile}}</td>
											<td>{{if isValid==0}}否{{else isValid==1}}是{{else}}无效{{/if}}</td>
											<td><input type="button" value="详情" onclick="opendetail({{:id}})" class="btn btn-info" style="width:auto;"></td>
										</tr>
									</script>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<!-- 详细信息 -->
      <div class="modal fade" id="myModals" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">详细信息</h4>
					</div>
					<div class="modal-bodyclass">
                       <table class="search-table">
                       	   <tbody class="matchdetail" id="matchdetail">
                       	    </tbody>
                       	    <script id="matchContentTmple" type="text/x-jsrender">
                              <tr>
	                            <td class="search_td"><label>联系人:</label></td>
	                            <td><div>{{:realName}}</div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>手机号:</label></td>
	                            <td><div>{{:mobile}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>性别:</label></td>
	                            <td><div>{{if sex==0}}男{{else sex==1}}女{{else}}无效{{/if}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>血型:</label></td>
	                            <td><div>{{:bloodType}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>身份证号:</label></td>
	                            <td><div>{{:idCard}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>驾驶证档案号:</label></td>
	                            <td><div>{{:profileNum}}</div></td>
	                          </tr>
                               <tr>
	                            <td class="search_td"><label>参赛组别:</label></td>
	                            <td><div>{{:matchGroup}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>参赛车型:</label></td>
	                            <td><div>{{:carModel}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>所属车队:</label></td>
	                            <td><div>{{:carTeam}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>紧急联系人手机号:</label></td>
	                            <td><div>{{:emeMobile}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>是否核销:</label></td>
	                            <td><div>{{if isValid==0}}否{{else isValid==1}}是{{else}}无效{{/if}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>参赛经历:</label></td>
	                            <td><div>{{:experience}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>荣誉:</label></td>
	                            <td><div>{{:honor}}</div></td>
	                          </tr>
                            </script>
                       </table>
                    </div>
				</div>
			</div>
		</form>
	</div>
    </body>
</html>