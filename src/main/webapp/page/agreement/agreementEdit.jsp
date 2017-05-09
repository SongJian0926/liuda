<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>agreement 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript" src="../../js/custom/agreementedit.js"></script>
        <script type="text/javascript">
		$(function(){
			/* getDataById(root + 'cms/agreement/findAgreementById',"#agreementTmpl","#agreementContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/agreement/saveAgreement','agreementList.jsp');
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
                          <button onclick="backAction('agreementList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="agreementContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="agreementTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
			<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">类型：</label>
       				 <div class="col-md-3">
						<select id="search_type" class="form-control input-sm" style="width:300px" name="type" value={{:type}}>
	       				 		
	       				 		<option value="1" {{if type==1}}selected{{/if}}>投票说明</option>
	       				 		<option value="2" {{if type==2}}selected{{/if}}>活动报名协议</option>
	       				 		<option value="3" {{if type==3}}selected{{/if}}>赛事报名协议</option>
								<option value="4" {{if type==4}}selected{{/if}}>竞猜说明</option>
	       				</select>
					</div>
      			</div>
	        	<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">内容：</label>
					<div class="col-md-8">					
						<div style="color:#FF0000;font-size:15px;margin-top:30px;float:right">*</div>
       					<div id="content" style="height:300px;width:700px" class="col-md-9" ></div>
					</div>
      			</div>
				
				
			</fieldset>
	</script>
    </body>
</html>