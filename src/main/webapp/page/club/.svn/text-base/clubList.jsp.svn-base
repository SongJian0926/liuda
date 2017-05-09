<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>俱乐部管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/club/findClubNEStatusPage";
 	var editUrl = "clubEdit.jsp";
	var operateUrl = root + "cms/club/operateClubByIds";
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_title").val() && $("#search_title").val().length > 0){
					_jsonFilter += "'search_LIKE_title':'"+$("#search_title").val().replace(/'/g,"\"").trim()+"',";
				}
				if($("#search_applyStatus").val() && $("#search_applyStatus").val() != ""){
					_jsonFilter += "'search_EQ_applyStatus':'"+$("#search_applyStatus").val()+"',";
				}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	function openMemberList(id)
	{
		window.open("clubMemberList.jsp?clubId="+id, "window", "dialogWidth:800px;dialogHeight:600px;status:no;help:no;resizable:yes"); 
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">俱乐部名称：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="俱乐部名称" class="form-control input-sm" id="search_title" name="search_title"/>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">审核状态：</label>
       				 			<div class="col-md-8">
									<select id="search_applyStatus"> 
										<option value="">请选择</option>
										<option value="1">审核中</option>
										<option value="2">审核通过</option>
										<option value="3">审核不通过</option>
									</select>
								</div>
      						</div>
							<input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                 <!--  </form> -->
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="添加" id="addClubSome" class="btn btn-color">
                          <input type="button" value="修改" id="updateClubSome" class="btn btn-color">
                          <input type="button" value="查看" id="detailClub" class="btn btn-color">
                          <input type="button" value="审批通过" id="successClubSome" class="btn btn-color" status="2">
                          <input type="button" value="审批不通过" id="failClubSome" class="btn btn-color" status="3">
                          <input type="button" value="推荐" id="recommendClubSome" class="btn btn-color" status="1">
                          <input type="button" value="不推荐" id="recommendNotClubSome" class="btn btn-color" status="0">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <!--<th width="30">俱乐部ID</th>-->
          		               		  <th width="80">俱乐部名称</th>
				               		  <th width="60">俱乐部图片</th>
				               		  <th width="50">审核状态</th>
				               		  <th width="30">是否推荐</th>
				               		  <!--<th width="50">部长</th>-->
				               		  <th width="40">创建人</th>
				               		  <th width="80">创建时间</th>
				               		  <th width="80">操作</th>
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
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckUser="{{:isUserAdd}}" ckStatus="{{:applyStatus}}" class="ckSelect" type="checkbox" /></td>
									<!--<td>{{:id}}</td>-->
									<td>{{subString:title}}</td>
									<td>{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{if applyStatus==1}}审核中{{else applyStatus==2}}审核通过{{else applyStatus==3}}审核不通过{{else}}无效{{/if}}</td>
									<td>{{if isRecommend==1}}是{{else}}否{{/if}}</td>
						 			<!--<td>{{:userId}}</td>-->
						 			<td>{{if isUserAdd==0}}后台{{else}}用户{{/if}}</td>
									<td>{{:createTime}}</td>
				   	  	 			<td>
										<input type="button" value="俱乐部成员" onclick="openMemberList({{:id}})" class="btn btn-info" style="width:auto;">
										<input type="button" value="活动" onclick="location.href='clubEventList.jsp?clubId={{:id}}'" class="btn btn-info" style="width:auto;">
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
                       	   <tbody class="clubdetail">
                       	    </tbody>
                       	    <script id="clubContentTmple" type="text/x-jsrender">
                              <tr>
	                            <td class="search_td"><label>俱乐部名称:</label></td>
	                            <td><div>{{:title}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>俱乐部图片:</label></td>
	                            <td><img src="../../{{:logo}}" class="img" height="40" width="50"></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>所属区域:</label></td>
	                            <td><div>{{:areaCode}}</div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>俱乐部地址:</label></td>
	                            <td><div>{{:address}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>审核状态:</label></td>
	                            <td><div>{{if applyStatus==1}}审核中{{else applyStatus==2}}审核成功{{else applyStatus==3}}审核失败{{else}}无效{{/if}}</div></td>
	                          </tr>
                               <tr>
	                            <td class="search_td"><label>是否推荐:</label></td>
	                            <td><div>{{if isRecommend==1}}是{{else isRecommend==0}}否{{else}}无效{{/if}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>俱乐部简介:</label></td>
	                            <td><div>{{:content}}</div></td>
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
                            </script>
                       </table>
                    </div>
				</div>
			</div>
		</form>
	</div>

    </body>
</html>