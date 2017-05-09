<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>图片 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/images/findImagesById',"#imagesTmpl","#imagesContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/images/saveImages','imagesList.jsp');
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
                          <button onclick="backAction('imagesEdit.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="imagesContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="imagesTmpl">
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
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片地址：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写图片地址" class="form-control input-sm" data-rule="required;length[0~255]" id="picurl" name="picurl" value="{{:picurl}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片类型：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写图片类型" class="form-control input-sm" data-rule="required;length[0~50]" id="type" name="type" value="{{:type}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">排序值：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写排序值" class="form-control input-sm" data-rule="required;length[0~50]" id="sort" name="sort" value="{{:sort}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">对象id：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写对象id" class="form-control input-sm" data-rule="required;length[0~50]" id="objectId" name="objectId" value="{{:objectId}}" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>