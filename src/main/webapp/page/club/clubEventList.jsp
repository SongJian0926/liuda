<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>俱乐部活动管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/clubEvent/findClubEventNEStatusPage";
 	var editUrl = "clubEventEdit.jsp";
	var operateUrl = root + "cms/clubEvent/operateClubEventByIds";
	
	function getfilter(){
		var _jsonFilter = "{";
		if(getUrlParam("clubId")){
			_jsonFilter += "'search_EQ_clubId':'"+getUrlParam("clubId")+"',";
		}
				if($("#search_title").val() && $("#search_title").val().length > 0){
			_jsonFilter += "'search_LIKE_title':'"+$("#search_title").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_eventStatus").val() && $("#search_eventStatus").val() != ""){
			_jsonFilter += "'search_EQ_eventStatus':'"+$("#search_eventStatus").val()+"',";
		}
				if($("#search_status").val() && $("#search_status").val() != ""){
			_jsonFilter += "'search_EQ_status':'"+$("#search_status").val()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	//function openEventMemberList(id)
	//{
	//	window.open("clubEventMemberList.jsp?clubEventId="+id, "window", "dialogWidth:1000px;dialogHeight:600px;scroll:yes;status:no;help:no;resizable:yes"); 
	//}
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">活动名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="俱乐部活动名称" class="form-control input-sm" id="search_title" name="search_title"/>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">活动状态：</label>
       				 			<div class="col-md-8">
									<select id="search_eventStatus"> 
										<option value="">请选择</option>
										<option value="1">报名中</option>
										<option value="2">活动未开始</option>
										<option value="3">活动进行中</option>
										<option value="4">已结束</option>
									</select>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">审核状态：</label>
       				 			<div class="col-md-8">
									<select id="search_status"> 
										<option value="">请选择</option>
										<option value="1">待审核</option>
										<option value="2">审核通过</option>
										<option value="-1">审核不通过</option>
									</select>
								</div>
      						</div>
							<input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                 <!--  </form> -->
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="查看" id="detailEvent" class="btn btn-color">
                          <input type="button" value="审批通过" id="successEventSome" class="btn btn-color" status="2">
                          <input type="button" value="审批不通过" id="failEventSome" class="btn btn-color" status="-1">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <!-- <th width="30">俱乐部ID</th>-->
          		               		  <th width="80">活动名称</th>
				               		  <th width="60">活动LOGO</th>
				               		  <th width="60">开始时间</th>
				               		  <th width="60">结束时间</th>
				               		  <th width="50">活动费用</th>
				               		  <th width="50">定金</th>
				               		  <th width="50">最大人数</th>
				               		  <!--<th width="50">是否线上核销</th>-->
				               		  <th width="50">活动状态</th>
				               		  <th width="50">审核状态</th>
				               		  <th width="80">创建时间</th>
				               		  <th width="50">操作</th>
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
									<!--<td>{{:clubId}}</td>-->
									<td>{{subString:title}}</td>
									<td>{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{:starttime}}</td>
									<td>{{:endtime}}</td>
									<td>{{:price}}</td>
									<td>{{:disposit}}</td>
									<td>{{:maxNum}}</td>
									<!--<td>{{if isOnline==1}}是{{else isOnline==0}}否{{else}}无效{{/if}}</td>-->
									<td>{{if eventStatus==1}}报名中{{else eventStatus==2}}活动未开始{{else eventStatus==3}}活动进行中{{else eventStatus==4}}已结束{{else}}无效{{/if}}</td>
									<td>{{if status==-1}}审核不通过{{else status==1}}审核中{{else status==2}}审核通过{{else}}无效{{/if}}</td>
									<td>{{:createTime}}</td>
									<td>
										<input type="button" value="报名人员" onclick="location.href='clubEventMemberList.jsp?clubEventId={{:id}}'" class="btn btn-info" style="width:auto;">
									</td>
								</tr>
						  </script>
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
                       	   <tbody class="eventdetail">
                       	    </tbody>
                       	    <script id="eventContentTmple" type="text/x-jsrender">
                              <tr>
	                            <td class="search_td"><label>活动名称:</label></td>
	                            <td><div>{{:title}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>活动图片:</label></td>
	                            <td><img src="../../{{:logo}}" class="img" height="40" width="50"></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>活动时间:</label></td>
	                            <td><div>{{:starttime}} - {{:endtime}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>活动状态:</label></td>
	                            <td><div>{{if eventStatus==1}}报名中{{else status==2}}活动未开始{{else status==3}}活动进行中{{else status==4}}已结束{{else}}无效{{/if}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>活动费用:</label></td>
	                            <td><div>{{:price}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>定金:</label></td>
	                            <td><div>{{:disposit}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>报名截止时间:</label></td>
	                            <td><div>{{:deadline}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>最大人数限制:</label></td>
	                            <td><div>{{:maxNum}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>活动说明:</label></td>
	                            <td><div>{{:content}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>所属区域:</label></td>
	                            <td><div>{{:areaCode}}</div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>活动地址:</label></td>
	                            <td><div>{{:address}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>图片:</label></td>
	                            <td>
                                    {{if mediaPath}}
                                       {{for mediaPath.split(",")}}
                                         <img src="../../{{:}}" class="img" height="40" width="50">
                                       {{/for}}
                                     {{/if}}
                                </td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>报名条件:</label></td>
	                            <td><div>{{:requirement}}</div></td>
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