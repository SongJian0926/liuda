<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>字典主表 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/dict/findDictById',"#dictTmpl","#dictContent");
			$("#saveOrupdate").click(function(){
				if($("#type").val().length<1){
					alert("字典类型不能为空！");
					return;
				}
				saveData(root + 'cms/dict/saveDict','dictList.jsp');
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
                          <button onclick="backAction('dictList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="dictContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="dictTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">字典名称：</label>
       				 <div class="col-md-3">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<input type="text" style="width:240px" placeholder="请填写字典名称" class="form-control input-sm" data-rule="required;length[0~20];remote[post:../../cms/dict/checkDict, id]" id="dictName" name="dictName" value="{{:dictName}}" />
					</div>

      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">字典类型：</label>
       				<div class="col-md-3">
<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<select id="type" name="type" class="form-control input-sm" style="width:240px" value="{{:type}}">
							<option value="" selected>请选择</option>						
							<option value="1" {{if type==1}}selected{{/if}}>攻略标签</option>
							<option value="2" {{if type==2}}selected{{/if}}>赛事标签</option>
							<option value="3" {{if type==3}}selected{{/if}}>俱乐部类型</option>
							<option value="4" {{if type==4}}selected{{/if}}>俱乐部等级</option>
							<option value="5" {{if type==5}}selected{{/if}}>俱乐部兴趣标签</option>
						</select>
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>