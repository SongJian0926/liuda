<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>字典子表 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript" src="../../js/custom/dictItem.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById1(root + 'cms/dictItem/findDictItemById',"#dictItemTmpl","#dictItemContent");
			$("#saveOrupdate").click(function(){
				if($("#dictId").val().length<1){
					alert("字典名称不能为空！");
					return ;
				}
				
				saveData(root + 'cms/dictItem/saveDictItem','dictItemList.jsp');
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
                          <button onclick="backAction('dictItemList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="dictItemContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="dictItemTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">字典名称：</label>
       				 <div class="col-md-3">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<select id="dictId" name="dictId" style="width:240px" class="form-control input-sm" value="{{:dictId}}">
						</select>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">选项名称：</label>
       				 <div class="col-md-3">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<input type="text" placeholder="请填写选项名称" style="width:240px" class="form-control input-sm" data-rule="required;length[0~20]" id="dictName" name="dictName" value="{{:dictName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">排序：</label>
       				 <div class="col-md-3">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<input type="text" placeholder="请填写排序" style="width:240px" class="form-control input-sm" data-rule="required;length[0~11];number" id="sort" name="sort" value="{{:sort}}" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>