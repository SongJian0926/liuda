<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>收藏 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="shop.js"></script>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box">
                      <div class="box-header">
                      <br>
                          <input type="button" value="确定" id="saveOrupdate" class="btn btn-success">
                          <button onclick="backAction('shopList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="shopContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="shopTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}"/>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">商品名称：</label>
					<div class="col-md-5">
						<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<input type="text" placeholder="商品名称" style="width:434px" class="form-control input-sm" data-rule="商品名称: required;length[1~28];" id="shopName" name="shopName" value="{{:shopName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">商品图片：</label>
 <div class="col-md-2">
<div style="color:#FF0000;font-size:15px;margin-top:35px;float:right">*</div>
						<div class="upload_div">
                        	{{if imgUrl}}<img id="myImageShow0" src="../../{{:imgUrl}}" width="100px" height="100px" />
							{{else}}<img id="myImageShow0" src="" width="100px" height="100px" />{{/if}}
                        	<input type="hidden" id="imgUrl0" name="imgUrl" value="{{:imgUrl}}" data-rule="商品图片: required;"/>
                        	<input type="file" id="uploadPhotoFile0" name="photoFile" class="upload_file" data-type="0">
                        </div>
					</div>
      			</div>
				<div class="form-group" id="imgUrls_div">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">轮播图：</label>
       				 <div class="col-md-6">
						<img src="../../image/add.png" class="add_img" style="cursor:pointer;" data-type="0">
					</div>
      			</div>
				<div class="form-group" id="desImgs_div">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">介绍图：</label>
       				 <div class="col-md-6">
						<img src="../../image/add.png" class="add_img" style="cursor:pointer;" data-type="1">
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">商品介绍：</label>
       				 <div class="col-md-5">
						<textarea class="form-control" id="shopDesc" name="shopDesc" rows="8" value="{{:shopDesc}}" style="resize:none;">{{:shopDesc}}</textarea>
					</div>
      			</div> 
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">限购个数：</label>
       				 <div class="col-md-5">
						<input type="text" placeholder="限购个数,最大6位" class="form-control input-sm" data-rule="plus" maxlength="6" id="limitNumber" name="limitNumber" value="{{:limitNumber}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">规格属性：</label>
					<div class="col-md-2">
					<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
						<img src="../../image/add.png" class="add_img" style="cursor:pointer;margin-right:20px;" data-type="2">
						<img src="../../image/edit.png" class="edit_img" style="cursor:pointer;margin-right:20px;">
						<img src="../../image/delete.png" class="delete_img" style="cursor:pointer;">
					</div>
      			</div>
				
				<div class="form-group">
					<label class="col-md-1 control-label controls" for="formGroupInputSmall">&nbsp;</label>
       				 <div class="col-md-10">
						<table id="standard_table" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
									  <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">规格名称</th>
				               		  <th width="80">规格图片</th>
				               		  <th width="80">库存量</th>
				               		  <th width="80">价格</th>
				               		  <th width="80">是否团购</th>
				               		  <th width="80">团购价格</th>
				               		  <th width="80">有效期</th>
                                  </tr>
                              </thead>
                              <tbody id="standard_content">
                              </tbody>
                          </table>
					  </div>
      			  </div>
			</fieldset>
	</script>
	<!-- 添加规格属性弹出框 -->
	<div class="modal fade" id="standard_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="standard_Form" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content" style="width:800px;margin-left:-100px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">规格属性</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="add_standard" value="" />
						<div class="form-group">
							<label for="userPhoto2" class="control-label col-md-4">规格名称：</label>
							<label style="color:#FF0000;font-size:15px;margin-top:4px">*</label>  
							<div class="controls col-md-5">
								<input type="text" placeholder="请填写规格名称"
									data-rule="规格名称:required;" id="porperty"
									name="porperty" class="form-control input-sm" />
							</div>
						</div>
						<!-- Select Basic -->
						<div class="form-group">
							<label for="userType" class="control-label col-md-4">图片：</label>
							<label style="color:#FF0000;font-size:15px;margin-top:4px">*</label>
							<div class="controls col-md-2">
								<div class="upload_div">
		                        	<img id="myImageShow9" src="" width="100px" height="100px" />
		                        	<input type="hidden" id="imgUrl9" name="imgUrl" value=""/>
		                        	<input type="file" id="uploadPhotoFile9" name="photoFile" class="upload_file1">
		                        </div>
							</div>
						</div>
						<div class="form-group">
							<label for="userPassword" class="control-label col-md-4">库存量：</label>
							<label style="color:#FF0000;font-size:15px;margin-top:4px">*</label>  
							<div class="controls col-md-5">
								<input type="text" placeholder="请填写库存量" maxlength="7"
									data-rule="库存量:required;digits;length[1~7];plus" id="stocks"
									name="stocks" class="form-control input-sm" />
							</div>
						</div>
						<div class="form-group">
							<label for="userPassword" class="control-label col-md-4">价格：</label>
							<label style="color:#FF0000;font-size:15px;margin-top:4px">*</label>  
							<div class="controls col-md-5">
								<input type="text" placeholder="请填写价格"
									data-rule="价格:required;number;nozero" id="price" maxlength="7"
									name="price" class="form-control input-sm" />
							</div>
						</div>
						<div class="form-group">
							<label for="userPassword" class="control-label col-md-4">
								<input type="checkbox" id="groupBuy" value="" /> 是否团购
							</label>
							<div class="controls col-md-5">
								
							</div>
						</div>
						<div class="form-group">
							<label for="userPassword" class="control-label col-md-4">团购价格：</label>
							<div class="controls col-md-5">
								<input type="text" placeholder="请填写团购价格" id="groupPrice"
									name="groupPrice" class="form-control input-sm" maxlength="7" readOnly />
							</div>
						</div>
						<div class="form-group">
							<label for="userPassword" class="control-label col-md-4">有效期：</label>
							<div class="controls col-md-5">
								<input type="text" placeholder="有效期" id="validity"
									name="validity" class="form-control input-sm" readOnly />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" id="standard_update" class="btn btn-primary">
							确定</button>
					</div>
				</div>
				</div>
		</form>
	</div>
	<!-- 添加规格属性弹出框 end -->
    </body>
</html>