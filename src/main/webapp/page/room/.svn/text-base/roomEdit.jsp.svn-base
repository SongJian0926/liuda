<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>房间 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getRoomDataById(root + 'cms/room/findRoomById',"#roomTmpl","#roomContent");
			$("#saveOrupdate").click(function(){
				saveRoom(root + 'cms/room/saveRoom','roomList.jsp');
			});
		//上传图片
	    upload("room");
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
                          <button onclick="backAction('roomList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="roomContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="roomTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
	        	
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店名：</label>
       				 <div class="col-md-3">
                         <select name="hotelId"  id="hotelName" value="{{:hotelId}}"></select>                  
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">房间类型：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写房间类型" class="form-control input-sm" data-rule="required;length[0~50]" id="type" name="type" value="{{:type}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">包含项目：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写包含项目" class="form-control input-sm" data-rule="required;length[0~50]" id="item" name="item" value="{{:item}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">房间价格：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写房间价格" class="form-control input-sm" data-rule="required;length[0~50];money" id="price" name="price" value="{{:price}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
                        	{{if logo}}<img id="myImageShow" src="../../{{:logo}}" width="100px" height="100px" />
							{{else}}<img id="myImageShow" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl" name="logo" value="{{:logo}}"/>
                        	<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">是否含早：</label>
       				 <div class="col-md-3">
                        <select id="breakfast" name="breakfast" class="form-control input-sm" value="{{:breakfast}}">
                            <option value="0" {{if breakfast==0}}selected{{/if}}>无</option>
							<option value="1" {{if breakfast==1}}selected{{/if}}>单早</option>	
                            <option value="2" {{if breakfast==2}}selected{{/if}}>双早</option>							
						</select>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">面积：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写面积" class="form-control input-sm" data-rule="required;length[0~50];number" id="area" name="area" value="{{:area}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">床位：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写床位" class="form-control input-sm" data-rule="required;length[0~50];plus" id="beds" name="beds" value="{{:beds}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">浴室：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写浴室" class="form-control input-sm" data-rule="required;length[0~255]" id="bathRoom" name="bathRoom" value="{{:bathRoom}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">通讯：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写通讯" class="form-control input-sm" data-rule="required;length[0~255]" id="communication" name="communication" value="{{:communication}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">设施：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写设施" class="form-control input-sm" data-rule="required;length[0~255]" id="establishment" name="establishment" value="{{:establishment}}" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>