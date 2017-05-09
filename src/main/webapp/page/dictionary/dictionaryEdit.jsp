<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>数据字典管理编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/dictionary/findDictionaryById',"#dictionaryTmpl","#dictionaryContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/dictionary/savedictionary','dictionaryList.jsp');
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
                          <button onclick="backAction('dictionaryList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                      
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="dictionaryContent">
                  	    </div>
                  	 </form>
                  	 
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="dictionaryTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">字典名称：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写字典名称" class="form-control input-sm" data-rule="required;length[1~10]" id="name" name="name" value="{{:name}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">字典类型：</label>
       				 <div class="col-md-3">
                        <select id="isdel" name="type" class="form-control input-sm" value="{{:type}}">
                            <option value="0" {{if type==0}}selected{{/if}}>酒店类型</option>
							<option value="1" {{if type==1}}selected{{/if}}>景点类型</option>							
						</select>
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>