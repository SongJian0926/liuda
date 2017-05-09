<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>酒店 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript" src="../../js/custom/hoteledit.js"></script>
        <script type="text/javascript">
        
		/* $(function(){
			getDataById(root + 'cms/hotel/findHotelById',"#hotelTmpl","#hotelContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/hotel/saveHotel','hotelList.jsp');
			});
		}); */
        </script>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box">
                      <div class="box-header">
                      <br>
                          <input type="button" value="确定" id="saveOrupdate" class="btn btn-success">
                          <button onclick="backAction('hotelList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="hotelContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="hotelTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店名称：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写酒店名称" class="form-control input-sm" data-rule="required;length[0~50]" id="hotelName" name="hotelName" value="{{:hotelName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
                        	{{if picurl}}<img id="myImageShow" src="../../{{:picurl}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl" name="picurl" value="{{:picurl}}"/>
                        	<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">轮播图：</label>
					{{if pics1}}{{for pics1}}
       				<div class="col-md-2" style="width:100px;margin-right:20px;" >
						<div class="upload_div">
                        	<img id="myImageShow{{:#index+1}}" src="../../{{:}}" width="100px" height="100px" />
                        	<input type="hidden" id="imgUrl{{:#index+1}}" name="picurl" value="{{:}}"/>
                        	<input type="file" id="uploadPhotoFile{{:#index+1}}" name="photoFile" class="upload_file">
						</div>
					</div>
					{{/for}}
 					
					{{if pics1.length==1}}
					<div class="col-md-1" style="width:100px;margin-right:20px;" >
						<div class="upload_div">
                        	{{if picurl2}}<img id="myImageShow2" src="../../{{:picurl2}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow2" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl2" name="picurl2" value="{{:picurl2}}"/>
                        	<input type="file" id="uploadPhotoFile2" name="photoFile" class="upload_file">
						</div>
					</div>
					
					<div class="col-md-1" style="width:100px;margin-right:20px;">
						<div class="upload_div">
                        	{{if picurl3}}<img id="myImageShow3" src="../../{{:picurl3}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow3" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl3" name="picurl3" value="{{:picurl3}}"/>
                        	<input type="file" id="uploadPhotoFile3" name="photoFile" class="upload_file">
						</div>
					</div>
					<div class="col-md-1" style="width:100px;margin-right:20px;">
						<div class="upload_div">
                        	{{if picurl4}}<img id="myImageShow4" src="../../{{:picurl4}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow4" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl4" name="picurl4" value="{{:picurl4}}"/>
                        	<input type="file" id="uploadPhotoFile4" name="photoFile" class="upload_file">
						</div>
					</div>
					{{/if}}
					{{if pics1.length==2}}
					<div class="col-md-1" style="width:100px;margin-right:20px;">
						<div class="upload_div">
                        	{{if picurl3}}<img id="myImageShow3" src="../../{{:picurl3}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow3" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl3" name="picurl3" value="{{:picurl3}}"/>
                        	<input type="file" id="uploadPhotoFile3" name="photoFile" class="upload_file">
						</div>
					</div>
					<div class="col-md-1" style="width:100px;margin-right:20px;">
						<div class="upload_div">
                        	{{if picurl4}}<img id="myImageShow4" src="../../{{:picurl4}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow4" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl4" name="picurl4" value="{{:picurl4}}"/>
                        	<input type="file" id="uploadPhotoFile4" name="photoFile" class="upload_file">
						</div>
					</div>
					{{/if}}
					{{if pics1.length==3}}
					<div class="col-md-1" style="width:100px;margin-right:20px;">
						<div class="upload_div">
                        	{{if picurl4}}<img id="myImageShow3" src="../../{{:picurl4}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow4" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl4" name="picurl3" value="{{:picurl4}}"/>
                        	<input type="file" id="uploadPhotoFile4" name="photoFile" class="upload_file">
						</div>
					</div>
					{{/if}}
					{{else}}
					<div class="col-md-1" style="width:100px;margin-right:20px;" >
						<div class="upload_div">
                        	{{if picurl1}}<img id="myImageShow1" src="../../{{:picurl1}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow1" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl1" name="picurl1" value="{{:picurl1}}"/>
                        	<input type="file" id="uploadPhotoFile1" name="photoFile" class="upload_file">
						</div>
					</div>
					<div class="col-md-1" style="width:100px;margin-right:20px;" >
						<div class="upload_div">
                        	{{if picurl2}}<img id="myImageShow2" src="../../{{:picurl2}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow2" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl2" name="picurl2" value="{{:picurl2}}"/>
                        	<input type="file" id="uploadPhotoFile2" name="photoFile" class="upload_file">
						</div>
					</div>
					<div class="col-md-1" style="width:100px;margin-right:20px;">
						<div class="upload_div">
                        	{{if picurl3}}<img id="myImageShow3" src="../../{{:picurl3}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow3" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl3" name="picurl3" value="{{:picurl3}}"/>
                        	<input type="file" id="uploadPhotoFile3" name="photoFile" class="upload_file">
						</div>
					</div>
					<div class="col-md-1" style="width:100px;margin-right:20px;">
						<div class="upload_div">
                        	{{if picurl4}}<img id="myImageShow4" src="../../{{:picurl4}}" width="100px" height="100px" />
                        	{{else}}<img id="myImageShow4" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl4" name="picurl4" value="{{:picurl4}}"/>
                        	<input type="file" id="uploadPhotoFile4" name="photoFile" class="upload_file">
						</div>
					</div>
				{{/if}}
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店类型：</label>
       				 <div class="col-md-3">
					
					<input type="text" placeholder="请填写酒店类型" class="form-control input-sm" data-rule="required;length[0~20]" id="hotelType" name="hotelType" value="{{:hotelType}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">电话：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写电话" class="form-control input-sm" data-rule="required;mobile_tel" id="telphone" name="telphone" value="{{:telphone}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店地址：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写酒店地址" class="form-control input-sm" data-rule="required;length[1~255]" id="address" name="address" value="{{:address}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店政策：</label>
       				 <div class="col-md-3">
					<textarea class="form-control input-sm" id="policy" placeholder="请填写酒店政策" style="resize:none;" rows="8" name="policy" data-rule="required;length[0~500]" value={{:policy}}>{{:policy}}</textarea>
						
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店提示：</label>
       				 <div class="col-md-3">
					<textarea class="form-control input-sm" id="prompt" placeholder="请填写酒店提示" style="resize:none;" rows="8" name="prompt" data-rule="required;length[0~500]" value={{:prompt}}>{{:prompt}}</textarea>
						
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店介绍：</label>
       				 <div class="col-md-3">
						<textarea class="form-control input-sm" id="introduce" placeholder="请填写酒店介绍" style="resize:none;" rows="8" name="introduce" data-rule="required;length[0~500]" value={{:introduce}}>{{:introduce}}</textarea>
						
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>