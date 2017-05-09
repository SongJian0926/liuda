<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>收货地址 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			getDataById(root + 'cms/address/findAddressById',"#addressTmpl","#addressContent");
			$("#saveOrupdate").click(function(){
				saveData(root + 'cms/address/saveAddress','addressList.jsp');
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
                          <button onclick="backAction('addressList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="addressContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="addressTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">收货人：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写收货人" class="form-control input-sm" data-rule="required;length[0~50]" id="consigneeName" name="consigneeName" value="{{:consigneeName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">手机号：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写手机号" class="form-control input-sm" data-rule="required;length[0~50];mobile" id="mobile" name="mobile" value="{{:mobile}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">省份：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写省份" class="form-control input-sm" data-rule="required;length[0~50]" id="province" name="province" value="{{:province}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">城市：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写城市" class="form-control input-sm" data-rule="required;length[0~50]" id="city" name="city" value="{{:city}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">区域：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写区域" class="form-control input-sm" data-rule="required;length[0~50]" id="area" name="area" value="{{:area}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">详细地址：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写详细地址" class="form-control input-sm" data-rule="required;length[0~50]" id="detailAddress" name="detailAddress" value="{{:detailAddress}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">用户名：</label>
       				 <div class="col-md-3">
						<input type="text"  class="form-control input-sm" data-rule="required;length[0~50]" id="userName " name="userName " value="{{:userName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">是否默认：</label>
       				 <div class="col-md-3">
                        <select id="isDefault" name="isDefault" class="form-control input-sm" value="{{:isDefault}}">
                          <option value="0" {{if isDefault==0}}selected{{/if}}>否</option>	
                          <option value="1" {{if isDefault==1}}selected{{/if}}>是</option>
						</select>
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>