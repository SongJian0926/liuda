<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>订单管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/order/findOrderNEStatusPage";
	var editUrl = 'orderEdit.jsp?id=';
	var operateUrl = root + 'cms/order/operateOrderByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_orderNo").val() && $("#search_orderNo").val().length > 0){
			_jsonFilter += "'search_LIKE_orderNo':'"+$("#search_orderNo").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_orderStatus").val() && $("#search_orderStatus").val().length > 0){
			_jsonFilter += "'search_EQ_orderStatus':'"+$("#search_orderStatus").val().replace(/'/g,"\"").trim()+"',";
		}
				if($("#search_orderType").val() && $("#search_orderType").val().length > 0){
					_jsonFilter += "'search_EQ_orderType':'"+$("#search_orderType").val().replace(/'/g,"\"").trim()+"',";
		}
				if(($("#startTime").val() && $("#startTime").val().length > 0) && $("#endTime").val()<=0){
			_jsonFilter += "'search_GT_createTime':'"+$("#startTime").val()+"',";
		}       
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && $("#startTime").val().length<=0){
			_jsonFilter += "'search_LT_createTime':'"+$("#endTime").val()+"',";
		} 
		        if(($("#endTime").val() && $("#endTime").val().length > 0) && ($("#startTime").val() && $("#startTime").val().length > 0)){
			_jsonFilter += "'search_BETWEEN_createTime':'"+$("#startTime").val()+","+$("#endTime").val()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
	$(document).ready(function(){	
		var goodsIds1 = [];
		//发货
		$('#addGoods').click(function(){
			var goodsIds = [];
			var error=true;
			$(".ckSelect").each(function(){
				var check=$(this).is(':checked');
				if(check){
					if($(this).attr("ckType")!=2 || $(this).attr("ckOrderStatus")!=1){
						error=false; return;
					}
					var _id=$(this).attr("ckId");
					goodsIds.push(_id);
				}
			});
			if(!error){
				alert("只能派发已支付的商品"); return;
			}
			if(goodsIds.length == 0){
				alert("请选择要派发的商品"); 
				return;
			}
			
			if(goodsIds.length >1){
				alert("只能选择一项进行发货");
				return;
			}
			goodsIds1=goodsIds;
			$("#sentGoods").modal();	
		});
		//确认
		$("#sent").click(function(){
			
			var modelIds =  goodsIds1.join(",");
			$('#sentvalidateForm').isValid(function(result) {
				
				if($("#sent_address").val().length==0){
					alert("请选择快递公司！");
					return;
				}
				if (!result) {
					alert("还有不符合规定的字段填写，请检查！");
					return;
				}
				$("#sent").addClass('disable_btn');
				$.ajax({
					url:root + 'cms/order/updateOrder',
					type:'post',
					data:{
						address: $("#sent_address").val(),
						ids:modelIds,
						sentData: $("#sentData").val()
					},success:function(data){
						if(data.code==1){
							alert("发货成功");
							$('#sent_address').val('');
							$('#sentData').val('');
							$('#sentvalidateForm').validator("destroy");
							goodsIds1.remove(0);
							dipscontent(getfilter(),contentUrl);
							$('#sentGoods').modal('hide');
							$("#sent").removeClass('disable_btn');
						}else{
							alert("发货失败");
							$("#sent").removeClass('disable_btn');
						}
					}
				});
			});
		});
		//返回
		$(".back").click(function(){
			$('#sent_address').val('');
			$('#sentData').val('');
			$('#sentvalidateForm').validator("destroy");
		});
		
		//查看详情
		$('#lookSome').click(function(){
			var updateArray = [];
			var typeArray = [];
			$(".ckSelect").each(function(){
		    	var check=$(this).is(":checked");
		    	if(check){
					var _id =$(this).attr("ckId");
					updateArray.push(_id);
					typeArray.push($(this).attr("ckType"));
		        }
		    });
			if(updateArray.length == 0){
				alert("请选择要查看的项"); return;
			}
			if(updateArray.length > 1){
				alert("每次只能查看一项"); return;
			}
			 $.ajax({
				url:root + 'cms/order/findOrderById',
				type:'post',
				dataType:'json',
				data:{
					modelId:updateArray[0],
					orderType:typeArray[0]
				},
				success:function(data){
					console.log(data);
					if(data.code == 1){
						//填充数据
						var contentHtml=$("#detailContentTmple").render(data.object,{mydata: data});
						$("#detail_content").html(contentHtml);
						$('#orderDetailModal').modal();
					}else{
						alert(data.message);
					}
				}
			}); 
		});
	});
	</script>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box">
	                  <form id="search_form" class="form-horizontal">
                      	<div class="tabSelect" id="selectHeadId">
                      		<br>
           	            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">订单编号：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="订单编号" class="form-control input-sm" id="search_orderNo" name="orderNo"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">订单状态：</label>
       				 			<div class="col-md-8">
									<select class="form-control input-sm" id="search_orderStatus" name="orderStatus">
										<option value="">请选择</option>
										<option value="0">待支付</option>
										<option value="1">已支付</option>
										<option value="2">待评价</option>
										<option value="3">已评价</option>
										<option value="4">待收货</option>
										<option value="5">已收货</option>
										<option value="6">失效</option>
									</select>
								</div>
      						</div>
      						
      						<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">下单时间：</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="开始时间" readOnly class="form-control input-sm" id="startTime" name="createTime"/>
								</div>
      						</div>
			            	<div class="form-group  col-md-3 search-form">
        						<label class="col-md-1 control-label controls" for="formGroupInputSmall">至</label>
       				 			<div class="col-md-8" style="padding-right:0;position:relative;">
       				 				<div class="datetime_clear"></div>
									<input type="text" placeholder="结束时间" readOnly class="form-control input-sm" id="endTime" name="createTime"/>
								</div>
      						</div>
      						<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">订单类型：</label>
       				 			<div class="col-md-8">
									<select class="form-control input-sm" id="search_orderType" name="orderType">
										<option value="">请选择</option>
										<option value="0">酒店</option>
										<option value="1">景点</option>
										<option value="2">商品</option>
										<option value="3">赛事门票</option>
									</select>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <!-- <input type="button" value="新增" id="addSome" class="btn btn-success">
                          <input type="button" value="编辑" id="updateSome" class="btn btn-info">
                          <input type="button" value="启用" id="openSome" class="btn btn-success">
                          <input type="button" value="锁定" id="lockSome" class="btn btn">
                          <input type="button" value="删除" id="deleteSome" class="btn btn-danger"> -->
                          <input type="button" value="查看" id="lookSome" class="btn btn-color">
                          <input type="button" value="发货" id="addGoods" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">订单编号</th>
				               		  <th width="80">订单数量</th>
				               		  <th width="80">订单价格</th>
				               		  <th width="80">订单类型</th>
				               		  <th width="80">联系人</th>
				               		  <th width="80">手机号</th>
				               		  <th width="80">订单状态</th>
				               		  <th width="80">下单时间</th>
                                  </tr>
                              </thead>
                              <!-- 表格内容 start -->
                              <tbody id="mycontent"></tbody>
                              <!-- 表格内容 end -->
                          </table>
                          <!-- 分页标签 start -->
                          <div class="row page_big_div" id="displayPage"></div>
                          <!-- 分页标签 end -->
                          <script id="tableContentTmple" type="text/x-jsrender">
								<tr>
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckStatus="{{:status}}" ckType="{{:orderType}}" ckOrderStatus="{{:orderStatus}}" class="ckSelect" type="checkbox" /></td>
									<td>{{:orderNo}}</td>
									<td>{{:orderNum}}</td>
									<td>{{if orderType == 2}}{{:price}}{{else}}{{:orderPrice}}{{/if}}</td>
									<td>{{if orderType == 0}}酒店{{else orderType == 1}}景点{{else orderType == 3}}赛事门票{{else orderType==2}}商品{{/if}}</td>
									<td>{{:userName}}</td>
									<td>{{:mobile}}</td>
									<td>{{if orderStatus == 0}}待支付{{else orderStatus == 1}}已支付{{else orderStatus == 2}}待评价{{else orderStatus == 3}}已评价{{else orderStatus == 4}}待收货{{else orderStatus == 5}}已收货{{else  orderStatus == 6}}失效{{/if}}</td>
									<td>{{:createTime}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
      <!-- 发货编辑弹出框 -->
       <div class="modal fade" id="sentGoods" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
				<form id="sentvalidateForm" class="form-horizontal">
			<div class="modal-dialog" style="background-color: #fff;height: 300px;width: 500px;">
				<div class="modal-header" style="width: 500px;background-color: #fff;">
				<button type="button" class="close back" data-dismiss="modal" 
						  aria-hidden="true">&times;</button>
					 <h4 class="modal-title" id="myModalLabel">发货内容</h4>
				</div>
					<div class="form-group  col-md-9 search-form" style="width: 400px;">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall" style="margin-top: 50px;">选择物流：</label>
       				 			
       				 			<div class="col-md-8" style="margin-top: 46px;">
									<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
									<select class="form-control input-sm" id="sent_address" name="expressCompany" style="width: 200px;height: 35px;">
										<option value="">请选择</option>
										<option value="huitongkuaidi">百世汇通</option>
										<option value="huitongkuaidi">汇通快运</option>
										<option value="yunda">韵达快运</option>
										<option value="yuantong">圆通速递</option>
										<option value="tiantian">天天快递</option>
										<option value="shunfeng">顺丰快递</option>
										<option value="shentong">申通快递</option>
										<option value="quanritongkuaidi">全日通快递</option>
										<option value="xinbangwuliu">新邦物流</option>
										<option value="youzhengguonei">邮政包裹挂号信</option>
										<option value="zhaijisong">宅急送</option>
										<option value="zhongtiekuaiyun">中铁快运</option>
										<option value="zhongtong">中通速递</option>
										<option value="zhongyouwuliu">中邮物流</option>
										<option value="jinguangsudikuaijian">京广速递</option>
										<option value="lianb">联邦快递（国内）</option>
										<option value="guotongkuaidi">国通快递</option>
										<option value="ems">ems快递</option>
										<option value="debangwuliu">德邦物流</option>
									</select>
									  
								</div>
      						</div>
      							<div class="form-group  col-md-9 search-form" style="width: 400px;margin-top: -25px;">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall" style="margin-top: 50px;">运单号：</label>
        						
       				 			<div class="col-md-8" style="margin-top: 46px;">
       				 				<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
 									<input type="text"  placeholder="运单号" data-rule="required;length[12~]" class="form-control input-sm"  style="width: 200px;height: 35px;"  id="sentData" name="expressNumber" value="" />
 									
 								</div>
      						</div>
 					<div class="box-header" style="padding:170px 90px 0px 90px;background-color: #fff;width: 500px;">
                      <br>
                          <input type="button" value="确定" id="sent" class="btn btn-success">
                         <button type="button" class="btn btn-primary back" data-dismiss="modal" aria-hidden="true" style="margin-left: 70px;">返回</button>
					</div>
				</div>
			</form>
	</div>
      <!-- 查看详情弹出框 -->
      <div class="modal fade" id="orderDetailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content" style="width:800px;margin-left:-100px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">查看详情</h4>
					</div>
					<div class="modal-body" id="detail_content">
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</form>
	</div>
      <script id="detailContentTmple" type="text/x-jsrender">
      		<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">订单编号：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{:orderNo}}
				</div>
{{if orderType!=2}}
				<label for="userPhoto2" class="control-label col-md-2">订单数量：</label>
				<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
					{{:orderNum}}
				</div>
{{/if}}
			</div>
      		<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">订单价格：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{if orderType == 2}}{{:price}}{{else}}{{:orderPrice}}{{/if}}
				</div>
				<label for="userPhoto2" class="control-label col-md-2">订单类型：</label>
				<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
					{{if orderType == 0}}酒店{{else orderType == 1}}景点{{else}}商品{{/if}}
				</div>
			</div>
      		<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">联系人：</label>
				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{:userName}}
				</div>
				<label for="userPhoto2" class="control-label col-md-2">手机号：</label>
				<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
					{{:mobile}}
				</div>
			</div>
			{{if orderType == 0}}
				<div class="form-group">
					<label for="userPhoto2" class="control-label col-md-2">入住时间：</label>
					<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
						{{if hotel && hotel.checkinTime}}{{:hotel.checkinTime}}{{/if}}
					</div>
					<label for="userPhoto2" class="control-label col-md-2">离开时间：</label>
					<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
						{{if hotel && hotel.leaveTime}}{{:hotel.leaveTime}}{{/if}}
					</div>
				</div>
				<div class="form-group">
					<label for="userPhoto2" class="control-label col-md-2">酒店名称：</label>
					<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
						{{if hotel && hotel.hotelName}}{{:hotel.hotelName}}{{/if}}
					</div>
					<label for="userPhoto2" class="control-label col-md-2">房型：</label>
					<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
						{{if hotel && hotel.type}}{{:hotel.type}}{{/if}}
					</div>
				</div>
			{{else orderType == 1}}
				<div class="form-group">
					<label for="userPhoto2" class="control-label col-md-2">景点名称：</label>
					<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
						{{if touristVo && touristVo.touristName}}{{:touristVo.touristName}}{{/if}}
					</div>
					<label for="userPhoto2" class="control-label col-md-2">门票名称：</label>
					<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
						{{if touristVo && touristVo.ticketName}}{{:touristVo.ticketName}}{{/if}}
					</div>
				</div>
			{{else orderType == 3}}
				<div class="form-group">
					
					<label for="userPhoto2" class="control-label col-md-2">赛事门票名称：</label>
					<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
						{{if macthVo && macthVo.ticketName}}{{:macthVo.ticketName}}{{/if}}
					</div>
				</div>
			{{else}}
<hr align="center"/>
			{{for shopVo}}
				<div class="form-group">
					<label for="userPhoto2" class="control-label col-md-2">商品名称：</label>
					<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
						{{getLineStr:shopName 50}}
					</div>
				</div>
				<div class="form-group">
					<label for="userPhoto2" class="control-label col-md-2">数量：</label>
					<div class="controls col-md-1" style="line-height:30px;font-weight:normal;">
						{{:shopNumber}}
					</div>
<label for="userPhoto2" class="control-label col-md-2">规格：</label>
<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
						{{getLineStr:porperty 30}}
					</div>
				</div>
				
<hr align="center"/>
			{{/for}}

			{{/if}}
      		<div class="form-group">
				<label for="userPhoto2" class="control-label col-md-2">订单状态：</label>

				<div class="controls col-md-3" style="line-height:30px;font-weight:normal;">
					{{if orderStatus == 0}}待支付{{else orderStatus == 1}}已支付{{else orderStatus == 2}}待评价{{else orderStatus == 3}}已评价{{else orderStatus == 4}}待收货{{else orderStatus == 5}}已收货{{else}}其他{{/if}}
				</div>
				<label for="userPhoto2" class="control-label col-md-2">下单时间：</label>
				<div class="controls col-md-5" style="line-height:30px;font-weight:normal;">
					{{:createTime}}
				</div>
			</div>
      </script>
    </body>
</html>