<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>投票主表 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="voteEdit.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/vote/findVoteByIds',"#voteTmpl","#voteContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/vote/saveVote','voteList.jsp');
			});
			upload("vote");
		});
        </script>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box" style="height:900px">
                      <div class="box-header">
                      <br>
                          <input type="button" value="确定" id="saveOrupdate" class="btn btn-success">
                          <button onclick="backAction('voteList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="voteContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="voteTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">标题：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写标题" class="form-control input-sm" data-rule="required;length[0~30]" id="title" name="title" value="{{:title}}" />
					</div>
      			</div>
	        	<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
							<img id="myImageShow" {{if logo}}<img src="../../{{:logo}}" height="100px" width="100px">{{else}}<img src="" height="100px" width="100px">{{/if}} />
                        	<img id="myImageShow" src="../../{{:logo}}" width="100px" height="100px" />
                        	<input type="hidden" id="logo" name="logo" value="{{:logo}}"/>
                        	<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file"/>
                        </div>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">投票方式：</label>
       				 <div class="col-md-3">
                        <select id="isRadio" name="isRadio" data-rule="required;" class="form-control input-sm" value="{{:isRadio}}">
							<option value="" selected>请选择</option>
							<option value="1" {{if isRadio==1}}selected{{/if}}>单选</option>
							<option value="2" {{if isRadio==2}}selected{{/if}}>多选</option>		
						</select>					
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">截止日期：</label>
					<div class="col-md-3">					
						<input type="text" readOnly placeholder="结束时间" style="width:315spx;" class="form-control input-sm" id="deadline"data-rule="required;"  value="{{:deadline}}" name="deadline"/>
					</div>
      			</div>				
				 <div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">投票选项：</label>
       				 <div class="col-md-5">
						<img src="../../image/add.png" class="add_img" style="cursor:pointer;margin-right:20px;" data-type="2">
						<img src="../../image/edit.png" class="edit_img" style="cursor:pointer;margin-right:20px;">
						<img src="../../image/delete.png" class="delete_img" style="cursor:pointer;">
					</div>
      			</div>
				<div class="form-group">
					<label class="col-md-1 control-label controls" for="formGroupInputSmall">&nbsp;</label>
       				 <div class="col-md-10" style="width:500px;">
						<table id="standard_table" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
									  <th width="15"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">选项内容</th>
                                  </tr>
                              </thead>
                              <tbody id="standard_content">
                              </tbody>
                          </table>
					  </div>
      			  </div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">投票说明：</label>
       				<div class="col-md-9" id="content" name="content" style="height:300px;">
					</div> 
      			</div>
			</fieldset>
	</script>
	<!-- 添加规格属性弹出框 -->
	<div class="modal fade" id="vote_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="standard_Form" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content" style="width:800px;margin-left:-100px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">投票选项</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="add_standard" value="" />
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-4">选项内容：</label>
							<div class="controls col-md-5">
								<input type="text" placeholder="请填写选项内容" id="standardName"
								data-rule="required;length[1~15]" name="brand" class="form-control input-sm" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" id="vote_update" class="btn btn-primary">
							确定</button>
					</div>
				</div>
				</div>
		</form>
	</div>
	<!-- 添加规格属性弹出框 end -->
    </body>
</html>