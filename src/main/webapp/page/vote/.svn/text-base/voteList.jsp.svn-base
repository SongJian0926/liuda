<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>投票主表管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="vote.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/vote/findVoteNEStatusPage";
 	var editUrl = 'voteEdit.jsp?id=';
	var operateUrl = root + 'cms/vote/operateVoteByIds'; 
	var detailurl=root+"cms/vote/findVoteByIds";
	function getfilter(){
		var _jsonFilter = "{";
				if(($("#startTime").val() && $("#startTime").val().length > 0) && $("#endTime").val()<=0){
			_jsonFilter += "'search_GTE_deadline':'"+$("#startTime").val()+"',";
		}       
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && $("#startTime").val().length<=0){
			_jsonFilter += "'search_LTE_deadline':'"+$("#endTime").val()+"',";
		}
				if($("#search_title").val() && $("#search_title").val().length > 0){
			_jsonFilter += "'search_LIKE_title':'"+$("#search_title").val().replace("'","\"")+"',";
		}	
				if(($("#endTime").val() && $("#endTime").val().length > 0) && ($("#startTime").val() && $("#startTime").val().length > 0)){
			_jsonFilter += "'search_BETWEEN_deadline':'"+$("#startTime").val()+","+$("#endTime").val()+"',";
		}
				if($("#search_num").val() && $("#search_num").val().length > 0){
			_jsonFilter += "'search_EQ_num':'"+$("#search_num").val().replace("'","\"")+"',";
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
	                  <form id="search_form" class="form-horizontal">
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
                      		<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">标题：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="标题" class="form-control input-sm" id="search_title" name="title"/>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">截至日期：</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" readOnly placeholder="开始时间" class="form-control input-sm" id="startTime" name="deadline"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">至</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" readOnly placeholder="结束时间" class="form-control input-sm" id="endTime" name="deadline"/>
								</div>
      						</div>
			            	
			            	
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-color">
                          <input type="button" value="发布" id="openSome" class="btn btn-color">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-color">
                          <input type="button" value="查看" id="detail" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
				               		  <th width="80">标题</th>
				               		  <th width="80">图片</th>
				               		  <th width="80">投票方式</th>
				               		  <th width="80">投票总数</th>
				               		  <th width="80">截止日期</th>
				               		  <th width="80">状态</th>
				               		  <th width="80">创建时间 </th>
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
									<td>{{subString:title}}</td>
									<td>{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td>{{if isRadio == 1}}单选{{else isRadio==2}}多选{{/if}}</td>
									<td>{{if num}}{{:num}}票{{else}}0票{{/if}}</td>
									<td>{{:deadline}}</td>
									<td>{{if status==2}}已发布{{else status==1}}未发布{{else status==-1}}已结束{{else}}其他{{/if}}</td>
									<td>{{:createTime}}</td>
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
						<h4 class="modal-title" id="myModalLabel">投票详情</h4>
					</div>
					<div class="modal-body detail" id="detail_content">
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</form>
	</div>
       <script id="commentContentTmple" type="text/x-jsrender">
			<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">投票标题：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{:title}}
				</div>
			</div>
			<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">截至日期：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{:deadline}}
				</div>
				<label for="userPhoto2" class="control-label col-md-2">投票方式：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{if isRadio == 1}}单选{{else isRadio==2}}多选{{/if}}
				</div>
			</div>
			<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">图片：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}
				</div>
				<label for="userPhoto2" class="control-label col-md-2">投票数量：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{if num}}{{:num}}票{{else}}0票{{/if}}
				</div>
			</div>
			<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">具体票数：</label>
			</div>
			<div class="form-group">
					<label class="col-md-1 control-label controls" for="formGroupInputSmall">&nbsp;</label>
       				 <div class="col-md-10">
						<table id="standard_table" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
          		               		  <th width="80">选项内容</th>
				               		  <th width="30">票数</th>
                                  </tr>
                              </thead>
                              <tbody>
									{{for voteOptionVoList}}
									<tr>
											<td>{{subString:optionName}}</td>
											<td>{{if num}}{{:num}}票{{else}}0票{{/if}}</td>
									</tr>
									{{/for}}
                              </tbody>
                          </table>
					  </div>
      			  </div>
     </script>
    </body>
</html>