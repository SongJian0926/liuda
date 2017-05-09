<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>攻略表管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript" src="guide.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/guide/findGuideList";
	var editUrl = 'guideEdit.jsp?id=';
	var operateUrl = root + 'cms/guide/operateGuideByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_userId").val() && $("#search_userId").val().length > 0){
			_jsonFilter += "'search_EQ_userId':'"+$("#search_userId").val().replace("'","\"")+"',";
		}
				if($("#search_title").val() && $("#search_title").val().length > 0){
			_jsonFilter += "'search_EQ_title':'"+$("#search_title").val().replace("'","\"")+"',";
		}
				if($("#search_type").val() && $("#search_type").val().length > 0){
			_jsonFilter += "'search_EQ_type':'"+$("#search_type").val().replace("'","\"")+"',";
		}
				if($("#search_applyStatus").val() && $("#search_applyStatus").val().length > 0){
			_jsonFilter += "'search_EQ_applyStatus':'"+$("#search_applyStatus").val().replace("'","\"")+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	$.views.converters({
		"subString2":function(str){
	    	return str.substring(str.indexOf(".")+1,str.length);
		},
	}); 
	$.views.tags({
		"getLastName": function(str){
			if(this.tagCtx.props.status === 0){
				if(str.substring(str.indexOf(".")+1,str.length)=="mov"||str.substring(str.indexOf(".")+1,str.length)=="mp4"){
					return true;
				}else{
					return false;
				}
			}else{
				return "";
			}
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">标题：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="标题" class="form-control input-sm" id="search_title" name="title"/>
								</div>
      						</div>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">作者：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="用户名" class="form-control input-sm" id="search_userId" name="userId"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">类型：</label>
       				 			<div class="col-md-8">
       				 				<select id="search_type" class="form-control input-sm" name="type">
	       				 				<option value="">全部</option>
										<option value="0">普通</option>	 
	       				 				<option value="1">精品</option>
	       				 			</select>
								</div>
      						</div>
			            	
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">申精状态：</label>
       				 			<div class="col-md-8">
       				 				<select id="search_applyStatus" class="form-control input-sm" name="applyStatus">
	       				 				<option value="">全部</option>
	       				 				<option value="1">申请中</option>
	       				 				<option value="2">审核通过</option>
	       				 				<option value="3">审核失败</option>
	       				 			</select>
								</div>
      						</div>
			            	
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <!-- <input type="button" value="编辑" id="updateSome" class="btn btn-info"> -->
                          <!-- <input type="button" value="启用" id="openSome" class="btn btn-success">
                          <input type="button" value="锁定" id="lockSome" class="btn btn"> -->
                          <!-- <input type="button" value="删除" id="deleteSome" class="btn btn-danger"> -->
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><!-- <input type="checkbox" id="_selectAll" /> --></th>
				               		  <th width="80">标题</th>
          		               		  <th width="50">作者</th>
				               		  <th width="80">类型</th>
				               		  <th width="80">兴趣标签</th>
				               		  <th width="80">浏览量</th>
				               		  <th width="80">申精状态</th>
				               		  <th width="80">创建日期</th>
				               		  <th width="80">状态</th>
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
									<td><!-- <input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" class="ckSelect" type="checkbox" /> -->{{:#index + 1}}</td>
									<td style="text-align:left;vertical-align:middle;">{{getLineStr:title 10}}</td>
									<td style="text-align:left;vertical-align:middle;">{{if userVo}}{{getLineStr:userVo.userName 7}}{{/if}}</td>
									<td>{{if type==0}}普通{{else type==1}}精品{{/if}}</td>
									<td><xmp>{{if dictItemVo}}{{:dictItemVo.dictName}}{{/if}}</xmp></td>									
									<td>{{:pageview}}</td>
									<td>{{if applyStatus==1}}申请中{{else applyStatus==2}}审核通过{{else applyStatus==3}}审核失败{{else}}未申请{{/if}}</td>
									<td>{{:createTime}}</td>
									<td>{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}</td>
									<td style="text-align:right;">
										{{if applyStatus==2 && !objectId}}<input type="button" value="绑定" onclick="bandtourist({{:id}});" class="btn btn-info" style="width:auto;">{{/if}}
										{{if applyStatus==1}}<input type="button" value="审核" onclick="audit({{:id}});" class="btn btn-info" style="width:auto;">{{/if}}
										{{if status==1}}<input type="button" value="发布" onclick="toPulish({{:id}});" class="btn btn-info" style="width:auto;">{{/if}}
										<input type="button" value="查看详情" onclick="lookDetail({{:id}});" class="btn btn-info" style="width:auto;">
										<input type="button" value="查看追加" onclick="turnappend({{:id}},'{{:title}}','{{if userVo}}{{:userVo.userName}}{{/if}}',{{:guideAppendVo.id}})" class="btn btn-info" style="width:auto;">
									</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
      <!-- 审核对话框 -->
      <div class="modal fade" id="auditModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">审核</h4>
					</div>
					<div class="modal-body">
						<input id="audit_id" type="hidden" />
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">审核结果：</label>
							<div class="controls col-md-6">
								<select id="audit_applyStatus" class="form-control input-sm" name="audit_applyStatus">
       				 				<option value="2">审核通过</option>
       				 				<option value="3">审核失败</option>
       				 			</select>
							</div>
						</div>
						<!-- Select Basic -->
						<div class="form-group">
							<label for="userType" class="control-label col-md-3">审核意见：</label>
							<div class="controls col-md-6">
								<textarea class="form-control input-sm" style="resize:none;height:150px;" id="audit_applyMemo" placeHolder="请输入审核意见"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" id="auditupdate" class="btn btn-primary">
							确定</button>
					</div>
				</div>
			</div>
		</form>
	</div>
      <!-- 绑定景点对话框 -->
      <div class="modal fade" id="bandtouristModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">选择景点</h4>
					</div>
					<div class="modal-body">
						<input id="band_id" type="hidden" />
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">景点：</label>
							<div class="controls col-md-6">
								<select id="bandtourist" class="form-control input-sm">
       				 				
       				 			</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" id="bandtouristupdate" class="btn btn-primary">
							确定</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 查看详情对话框 -->
      <div class="modal fade" id="lookDetailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">详情</h4>
					</div>
					<div class="modal-body" id="lookDetail"></div>
					<script id="lookDetailTmple" type="text/x-jsrender">
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">标题：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{getLineStr:title 30}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">作者：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{if userVo}}{{:userVo.userName}}{{/if}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">类型：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{if type==0}}普通{{else type==1}}精品{{/if}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">标签：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{if dictItemVo}}{{:dictItemVo.dictName}}{{/if}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">申精状态：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{if applyStatus==1}}申请中{{else applyStatus==2}}审核通过{{else applyStatus==3}}审核失败{{else}}未申请{{/if}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">状态：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{if status==1}}正常{{else status==2}}已发布{{else status==3}}已删除{{else status==-1}}已锁定{{else}}无效{{/if}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">浏览量：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{:pageview}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">创建时间：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{:createTime}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">绑定景点：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								{{:objectName}}
							</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3" {{subString2:guideAppendVo.mediaPath}}>图片/视频：</label>
							<div class="controls col-md-7" style="line-height:30px;" id="append_img">
{{!--{{getLastName guideAppendVo.mediaPath}}
<video width='320' height='170' controls='controls'><source src='../../{{:guideAppendVo.mediaPath}}' type='video/ogg'><source src='../../{{:guideAppendVo.mediaPath}}' type='video/mp4'>Your browser does not support the video tag</video>
{{/getLastName}}--}}
{{if guideAppendVo}}{{if guideAppendVo.mediaLastName=='mov'||guideAppendVo.mediaLastName=='mp4'}}
<video width='320px' height='170px' controls='controls'><source src='../../{{:guideAppendVo.mediaPath}}' type='video/ogg'><source src='../../{{:guideAppendVo.mediaPath}}' type='video/mp4'>Your browser does not support the video tag</video>

{{else}}<img src= '../../{{:guideAppendVo.mediaPath}}' style="width:50px;height:50px;margin-right:10px;" />
{{/if}}{{/if}}
</div>
						</div>	
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-3">内容：</label>
							<div class="controls col-md-6" style="line-height:30px;">
								<textarea class="form-control input-sm" style="resize:none;height:150px;" disabled id="audit_applyMemo" placeHolder="请输入审核意见">{{:content}}</textarea>
							</div>
						</div>	
					</script>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</form>
	</div>
    </body>
</html>