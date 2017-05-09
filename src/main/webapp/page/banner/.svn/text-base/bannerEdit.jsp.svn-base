<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Banner图 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/banner/findBannerById',"#bannerTmpl","#bannerContent");
			$("#saveOrupdate").click(function(){
				if($("#imgUrl").val().length==0){
					alert("图片不能为空！");
					return;
				}
				$.ajax({
					url : root + "cms/banner/findBannerSort",
					type : 'post',
					dataType : 'json',
					data : {
						id:$("#tId").val()
					},
					success : function(data) {
						console.log(data);
						if (data.code == 1) {
							$("#bannerSort").html(data.object);
						} else {
							$("#bannerSort").html("");
						}
					}
				});
				saveData(root + 'cms/banner/saveBanner','bannerList.jsp');
			});
			
		});
		//上传图片
	    upload("banner");
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
                          <button onclick="backAction('bannerList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="bannerContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="bannerTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
{{!--&nbsp;&nbsp;<label style="color:#FF0000;font-size:15px;">*</label>--}}    				
 <div class="col-md-2">
<div style="color:#FF0000;font-size:15px;margin-top:30px;float:right">*</div>
						<div class="upload_div">

                        	{{if picurl}}<img id="myImageShow" src="../../{{:picurl}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow" src="" width="100px" height="100px" />{{/if}}
							<input type="hidden" id="imgUrl" name="picurl" value="{{:picurl}}"/>
                        	<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">链接地址：</label>
					{{!--&nbsp;&nbsp;<label style="color:#FF0000;font-size:15px;margin-top:4px">*</label>--}}
       				 <div class="col-md-7">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<input type="text" placeholder="请填写链接地址" style="width:570px" class="form-control input-sm" data-rule="required;length[0~255];url" id="linkurl" name="linkurl" value="{{:linkurl}}" />

                     </div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">排序值：</label>
 					<div class="col-md-7">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<input type="text" placeholder="请填写排序值" style="width:570px" class="form-control input-sm" data-rule="required;length[0~50];sort;remote[post:../../cms/banner/checkRole, sort, id]" id="sort" name="sort" value="{{:sort}}" />
						<input type="hidden" class="form-control input-sm"  id="bannerSort" />
					</div>
      			</div>
				
			</fieldset>
	</script>
    </body>
</html>