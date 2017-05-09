<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>俱乐部 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript" src="../../js/comm/editClub.js"></script>
        <script type="text/javascript">
		$(function(){
			getClubDataById(root + 'cms/club/findClubById',"#clubTmpl","#clubContent");
			$("#saveOrupdate").click(function(){
				saveClub(root + 'cms/club/saveClub','clubList.jsp');
			});
			//上传图片
		    uploadphoto("club");
		});
        </script>
		<style>
		<!--
			.img{
			width:100px;
			height:100px;
			border:none;
			background:#ddd;
			border-radius:4px;
			text-align:center;
			line-height:100px;
		}
		-->
		</style>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box" style="height:900px">
                      <div class="box-header">
                      <br>
                          <input type="button" value="确定" id="saveOrupdate" class="btn btn-success">
                          <button onclick="backAction('clubList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="clubContent">
                  	    </div>
                        <div class="tab" id="guessContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="clubTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<input type="hidden" id="isUserAdd" name = "isUserAdd" value="0" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部兴趣标签：</label>
       				 <div class="col-md-3">
                         <select name="interest" data-rule="required;" id="interest" value="{{:interest}}"></select>                  
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部类型：</label>
       				 <div class="col-md-3">
                         <select name="type" data-rule="required;" id="type" value="{{:type}}"></select>                  
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部等级：</label>
       				 <div class="col-md-3">
                         <select name="level" data-rule="required;" id="level" value="{{:level}}"></select>                  
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部名称：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写俱乐部名称" class="form-control input-sm" data-rule="required;length[1~15]" id="title" name="title" value="{{:title}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部LOGO：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
							{{if logo}}<img id="myImageLogoShow" src="../../{{:logo}}" class="img">
                            {{else}}<img id="myImageLogoShow" src="" class="img">{{/if}}
                            <input type="hidden" id="logo" name="logo" value="{{:logo}}"></input>
		                    <input type="file" id="uploadLogoPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部地址：</label>
       				 <div class="col-md-3">
                        <select name="provinceId"  id="provinceId"></select>  
						<select name="cityId"  id="cityId"></select>  
						<select name="areaId"  id="areaId" value="{{:areaId}}" data-rule="required;"></select>                
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">详细地址：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写详细地址" class="form-control input-sm" data-rule="required;length[0~50]" id="address" name="address" value="{{:address}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">排序：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写排序值" class="form-control input-sm" data-rule="required;length[0~50];plus" id="sort" name="sort" value="{{:sort}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部简介：</label>
       				 <div class="col-md-3">
						<textarea type="text" placeholder="请填写俱乐部简介" class="form-control input-sm" data-rule="required;length[0~100000]" id="content" name="content" value="{{:content}}" >{{:content}}</textarea>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部图片：</label>
       				 <div class="col-md-3" style="display:inline-block;">
                        {{if mediaPath}}
							{{for mediaPath.split(",")}}
								<div class="upload_div divmedia"  style="display:inline-block;" >
									<input name="mediaPath" style="display:none" id="mediaPath{{:#index+1}}" value="{{:}}"></input>
									<img src="../../{{:}}" class="img" id="img{{:#index+1}}">
									<input type="file" id="uploadPhotoFile{{:#index+1}}" name="photoFile" class="upload_file" >
								</div>
							{{/for}}
						{{else}}
							<div class="upload_div divmedia"  style="display:inline-block;" >
							<img id="myImageShow" src="" class="img"/>
							<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
							</div>
						{{/if}}
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">俱乐部部长：</label>
       				 <div class="col-md-3">
                         <select name="userId" data-rule="required;" id="userId" value="{{:userId}}"></select>                  
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">是否推荐：</label>
       				 <div class="col-md-3">
                         <select name="isRecommend" data-rule="required;" id="isRecommend" value="{{:isRecommend}}">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>                  
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>