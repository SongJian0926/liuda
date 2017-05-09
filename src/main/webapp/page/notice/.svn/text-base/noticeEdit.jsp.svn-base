<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>公告通知 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
         <script type="text/javascript" src="../../js/custom/noticeedit.js"></script>
        <script type="text/javascript">
		$(function(){
			//getDataById(root + 'cms/notice/findNoticeById',"#noticeTmpl","#noticeContent");
			/* $("#saveOrupdate").click(function(){
				saveData(root + 'cms/notice/saveNotice','noticeList.jsp');
			}); */
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
                          <button onclick="backAction('noticeList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="noticeContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="noticeTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
	        	
				
				
				{{!--<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">是否推送：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写是否推送" class="form-control input-sm" data-rule="required;length[0~11]" id="needPush" name="needPush" value="{{:needPush}}" />
					</div>
      			</div>--}}
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">公告类型：</label>
       				 <div class="col-md-4">
<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<select id="search_type" class="form-control input-sm" style="width:320px" name="type" value={{:type}}>
	       				 		<option value="">请选择</option>
	       				 		<option value="1" {{if type==1}}selected{{/if}}>黑板报</option>
	       				 		<option value="2" {{if type==2}}selected{{/if}}>活动公告</option>
	       				 		<option value="3" {{if type==3}}selected{{/if}}>新闻报道</option>
	       				</select>
					</div>
      			</div>
<div class="form-group news" style="display:none">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">标题：</label>
       				 <div class="col-md-4">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<input type="text" placeholder="请填写标题" style="width:320px" class="form-control input-sm" data-rule="length[1~20]" id="title" name="title" value="{{:title}}" />
					</div>
      			</div>
<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
       				 <div class="col-md-2">
						<div style="color:#FF0000;font-size:15px;margin-top:30px;float:right">*</div>
						<div class="upload_div">
                        	{{if imgPath}}<img id="myImageShow" src="../../{{:imgPath}}" width="100px" height="100px" />
							{{else}}<img id="myImageShow" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl" name="imgPath" value="{{:imgPath}}"/>
                        	<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>
<div class="form-group news" style="display:none">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">简介：</label>
       				 <div class="col-md-4">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<textarea rows="5" cols="20" class="form-control input-sm" style="resize:none;width:330px" data-rule="length[1~200]" id="introduce" name="introduce" value="{{:introduce}}">{{:introduce}}</textarea>						
					</div>
      			</div>
				<div class="form-group">
					
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">内容：</label>
<div class="col-md-8">					
<div style="color:#FF0000;font-size:15px;margin-top:30px;float:right">*</div>
       				 <div id="content" style="height:300px;width:700px" class="col-md-9" >
					</div>
</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>