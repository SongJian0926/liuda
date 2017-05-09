<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>消息中心管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/message/findMessageNEStatusPagetwo";
 	var editUrl = 'messageEdit.jsp?id=';
	var operateUrl = root + 'cms/message/operateMessageByIds'; 
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_content").val() && $("#search_content").val().length > 0){
			_jsonFilter += "'search_LIKE_content':'"+$("#search_content").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_type").val() && $("#search_type").val().length > 0){
			_jsonFilter += "'search_EQ_type':'"+$("#search_type").val()+"',";
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
			location.href = root + 'cms/message/fileDownload?jsonFilter=' + encodeURI(encodeURI(getfilter()));
		}
	});
	//修改
	$(function(){
		$("#updateSometwo").click(function(){
			var updateArray = [];
			var personArray = [];
			$(".ckSelect").each(function(){
		    	var check=$(this).is(':checked');
		    	var v = $(this).parents('tr').find('input[type="hidden"]').val();
		    	if(check){
					var _id = $(this).attr("ckId");
					updateArray.push(_id);
					if(v == 1){
						personArray.push(v);
					}
		        }
		    });
			if(updateArray.length == 0){
				alert("请选择要编辑的项"); return;
			}
			if(updateArray.length > 1){
				alert("每次只能编辑一项"); return;
			}
			if(personArray.length > 0){
				alert("您选择的是个人消息，不能编辑"); return;
			}
			var from = window.location.href.split("?")[0] + "?nextPage=" + (parseInt($("#_click_page").val())+1) + "&pageSize=" + $("#selectPageSize").val();
			location.href = editUrl + updateArray[0] + '&from=' + from;
		});
		//删除
		$("#deleteSometwo").click(function(){
			var personArray = [];
			$(".ckSelect").each(function(){
		    	var check = $(this).is(':checked');
		    	var v = $(this).parents('tr').find('input[type="hidden"]').val();
		    	if(check && v== 1){
					personArray.push(v);
		        }
		    });
		    if(personArray.length > 0){
		    	alert("您选择的项中有个人消息，个人消息不能删除！");
		    	return;
		    }
		    del(operateUrl + '?modelIds=');
		});
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">消息内容：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="消息内容" class="form-control input-sm" id="search_content" name="content"/>
								</div>
      						</div>
			            	<!-- <div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">消息类型：</label>
       				 			<div class="col-md-8">
			 						<select id="search_type" name="type" class="form-control input-sm" value="{{:type}}">
			                        	<option value="">全部类型</option>
			                            <option value="0" {{if type==0}}selected{{/if}}>系统消息</option>
										<option value="1" {{if type==1}}selected{{/if}}>个人消息</option>							
									</select>
 							</div> 
      						</div>-->
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      </form>
                      	<div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="新增" id="addSome" class="btn btn-color">
                          <input type="button" value="编辑" id="updateSometwo" class="btn btn-color">
                          <input type="button" value="删除" id="deleteSometwo" class="btn btn-color">
                           <input type="button" value="导出" id="dataexport" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="10"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="155">消息内容</th>
				               		 <!--  <th width="80">消息类型</th> -->
				               		  <th width="30">时间</th>
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
									<input  type="hidden" value="{{:type}}" />
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" class="ckSelect" type="checkbox" /></td>
									<td style="text-align:left;vertical-align:middle;">{{getLineStr:content 58}}</td>
									{{!--<td>{{if type==1}}个人消息{{else type==0}}系统消息{{else}}无效{{/if}}</td>--}}
									<td>{{:createTime}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
    </body>
</html>