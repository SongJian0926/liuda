<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>用户 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/user/findUserById',"#userTmpl","#userContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/user/saveUser','userList.jsp');
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
                          <button onclick="backAction('userEdit.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="userContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="userTmpl">
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
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">用户名：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写用户名" class="form-control input-sm" data-rule="required;length[0~50]" id="userName" name="userName" value="{{:userName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">密码：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写密码" class="form-control input-sm" data-rule="required;length[0~50]" id="password" name="password" value="{{:password}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">手机号：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写手机号" class="form-control input-sm" data-rule="required;length[0~11]" id="mobile" name="mobile" value="{{:mobile}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">头像：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写头像" class="form-control input-sm" data-rule="required;length[0~255]" id="photo" name="photo" value="{{:photo}}" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>