<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>攻略表 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById1(root + 'cms/guide/findGuideList',"#guideTmpl","#guideContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/guide/saveGuide','guideList.jsp');
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
                          <button onclick="backAction('guideList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="guideContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="guideTmpl">
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
						<input type="text" readOnly class="form-control input-sm" data-rule="required;length[0~20]" id="userId" name="userId" value="{{:userVo.userName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">标题：</label>
       				 <div class="col-md-3">
						<input type="text" readOnly class="form-control input-sm" data-rule="length[0~255]" id="title" name="title" value="{{:title}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">类型：</label>
       				 <div class="col-md-3">
						<input type="text" readOnly class="form-control input-sm" data-rule="length[0~11]" id="type" name="type" value="{{if type==0}}普通{{else type==1}}精品{{/if}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">浏览量：</label>
       				 <div class="col-md-3">
						<input type="text" readOnly class="form-control input-sm" data-rule="length[0~11]" id="pageview" name="pageview" value="{{:pageview}}" />
					</div>
      			</div>
				
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">是否发布：</label>
       				<div class="col-md-3">
						<select id="status" name="status" class="form-control input-sm" value="{{:status}}">
							<option value="1" {{if status==1}}selected{{/if}}>未发布</option>
							<option value="2" {{if status==2}}selected{{/if}}>发布</option>
						</select>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">申精状态：</label>
       				 <div class="col-md-3">
							<select id="applyStatus" class="form-control input-sm" name="applyStatus">
	       				 		<option value="" >全部</option>
								<option value="1" {{if applyStatus==1}}selected{{/if}}>申请中</option>	 
	       				 		<option value="2" {{if applyStatus==2}}selected{{/if}}>审核通过</option>
								<option value="3" {{if applyStatus==3}}selected{{/if}}>审核失败</option>
	       				 	</select>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">审核备注：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写审核备注" class="form-control input-sm" data-rule="length[0~255]" id="applyMemo" name="applyMemo" value="{{:applyMemo}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">对象Id：</label>
       				 <div class="col-md-3">
						<input type="text" class="form-control input-sm" data-rule="length[0~20]" id="objectId" name="objectId" value="{{:objectId}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">对象类型：</label>
       				 <div class="col-md-3">
							<select id="search_type" class="form-control input-sm" name="objectType">
	       				 		<option value="" >全部</option>
								<option value="0" {{if objectType==0}}selected{{/if}}>普通</option>	 
	       				 		<option value="1" {{if objectType==1}}selected{{/if}}>精品</option>
	       				 	</select>
					</div>
      			</div>
				
				
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">最后更新日期：</label>
       				 <div class="col-md-3">
						<input type="text" readOnly class="form-control input-sm" data-rule="length[0~50]" id="lastUpdate" name="lastUpdate" value="{{:lastUpdate}}" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>