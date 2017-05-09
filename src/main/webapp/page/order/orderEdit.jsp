<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>订单 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/order/findOrderById',"#orderTmpl","#orderContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/order/saveOrder','orderList.jsp');
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
                          <button onclick="backAction('orderEdit.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="orderContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="orderTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
	        	{{!--<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">图片：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
                        	<img id="myImageShow" src="../../{{:imgUrl}}" width="100px" height="100px" />
                        	<input type="hidden" id="imgUrl" name="imgUrl" value="{{:imgUrl}}"/>
                        	<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>--}}
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">订单编号：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写订单编号" class="form-control input-sm" data-rule="required;length[0~50]" id="orderNo" name="orderNo" value="{{:orderNo}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">用户Id：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写用户Id" class="form-control input-sm" data-rule="required;length[0~50]" id="userId" name="userId" value="{{:userId}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">对象Id：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写对象Id" class="form-control input-sm" data-rule="required;length[0~50]" id="objectId" name="objectId" value="{{:objectId}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">订单数量：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写订单数量" class="form-control input-sm" data-rule="required;length[0~50]" id="orderNum" name="orderNum" value="{{:orderNum}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">商品价格：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写商品价格" class="form-control input-sm" data-rule="required;length[0~50]" id="price" name="price" value="{{:price}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">订单状态：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写订单状态" class="form-control input-sm" data-rule="required;length[0~50]" id="orderStatus" name="orderStatus" value="{{:orderStatus}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">支付类型：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写支付类型" class="form-control input-sm" data-rule="required;length[0~50]" id="payType" name="payType" value="{{:payType}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">支付状态：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写支付状态" class="form-control input-sm" data-rule="required;length[0~50]" id="payStatus" name="payStatus" value="{{:payStatus}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">联系人：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写联系人" class="form-control input-sm" data-rule="required;length[0~50]" id="userName" name="userName" value="{{:userName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">手机号：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写手机号" class="form-control input-sm" data-rule="required;length[0~11]" id="mobile" name="mobile" value="{{:mobile}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">入住时间：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写入住时间" class="form-control input-sm" data-rule="required;length[0~50]" id="checkinTime" name="checkinTime" value="{{:checkinTime}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">离开时间：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写离开时间" class="form-control input-sm" data-rule="required;length[0~50]" id="leaveTime" name="leaveTime" value="{{:leaveTime}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">订单类型：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写订单类型" class="form-control input-sm" data-rule="required;length[0~50]" id="orderType" name="orderType" value="{{:orderType}}" />
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>