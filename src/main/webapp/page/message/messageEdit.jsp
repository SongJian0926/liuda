<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>消息中心 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/message/findMessageById',"#messageTmpl","#messageContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/message/saveMessage','messageList.jsp');
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
                          <button onclick="backAction('messageList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="messageContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="messageTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">消息内容：</label>
       				 
<div class="col-md-3">
<div style="color:#FF0000;font-size:15px;margin-top:35px;float:right">*</div>
						<textarea class="form-control input-sm" style="width:240px;resize:none;" name="content" id="content" placeholder="请填写消息 内容" rows="8" name="content" data-rule="required;length[0~500]" value={{:content}}>{{:content}}</textarea>
					</div>
      			</div>
				{{!--<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">消息类型：</label>
       				 <div class="col-md-3">
                        <select id="isdel" name="type" class="form-control input-sm" value="{{:type}}">
                            <option value="0" {{if type==0}}selected{{/if}}>系统消息</option>						
						</select>
					</div>
      			</div>--}}
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">消息标题：</label>
       				 <div class="col-md-3">
						<input type="text" readonly="readonly" class="form-control input-sm" data-rule="required;length[0~255]" id="title" name="平台消息" value="平台消息" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>