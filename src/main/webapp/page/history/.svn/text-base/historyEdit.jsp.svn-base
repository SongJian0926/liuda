<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>收藏 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/history/findHistoryById',"#historyTmpl","#historyContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/history/saveHistory','historyList.jsp');
			});
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
                          <button onclick="backAction('historyEdit.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="historyContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="historyTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
	        	{{!--<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
                        	<img id="myImageShow" src="../../{{:imgUrl}}" width="100px" height="100px" />
                        	<input type="hidden" id="imgUrl" name="imgUrl" value="{{:imgUrl}}"/>
                        	<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>--}}
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">用户Id：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写用户Id" class="form-control input-sm" data-rule="required;length[0~50]" id="userId" name="userId" value="{{:userId}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">对象Id：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写对象Id" class="form-control input-sm" data-rule="required;length[0~50]" id="objectId" name="objectId" value="{{:objectId}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">类型：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写类型" class="form-control input-sm" data-rule="required;length[0~50]" id="type" name="type" value="{{:type}}" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>