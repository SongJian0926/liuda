<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="UTF-8">
	<title>商家商品管理</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<jsp:include page="../com/header.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/comm/listcomm.js"></script>
	<script type="text/javascript">
	var contentUrl = root + "cms/room/findBusinessShopList";
	var editUrl = 'hotelEdit.jsp?id=';
	var operateUrl = root + 'cms/hotel/operateHotelByIds';
	function getfilter(){
		var _jsonFilter = "{";
				if($("#search_shopName").val() && $("#search_shopName").val().length > 0){
			_jsonFilter += "'search_LIKE_shopName':'"+$("#search_shopName").val().replace(/'/g,"\"").trim()+"',";
		}     
		        if($("#search_businessName").val() && $("#search_businessName").val().length > 0){
			_jsonFilter += "'search_LIKE_businessName':'"+$("#search_businessName").val().replace(/'/g,"\"").trim()+"',";
		}
		        if($("#search_status").val() && $("#search_status").val().length > 0){
			_jsonFilter += "'search_EQ_status':'"+$("#search_status").val().replace(/'/g,"\"").trim()+"',";
		}
				if(_jsonFilter != "{"){
			_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
		}
		_jsonFilter += "}";
		return _jsonFilter;
	}
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
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商品名：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="商品名" class="form-control input-sm" id="search_shopName" name="shopname"/>
								</div>
      						</div>
      						<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">商家名：</label>
       				 			<div class="col-md-8">
									<input type="text" placeholder="商家名" class="form-control input-sm" id="search_businessName" name="businessName"/>
								</div>
      						</div>
      						<div class="form-group  col-md-3 search-form">
        						<label class="col-md-4 control-label controls" for="formGroupInputSmall">状态：</label>
       				 			<div class="col-md-8">
									<select class="form-control input-sm" id="search_status" name="status">
      				 			     <option value="" class="form-control input-sm">请选择</option>
                                     <option value="1" class="form-control input-sm">待审核</option>
							         <option value="2" class="form-control input-sm">上架</option>	
							         <option value="-1" class="form-control input-sm">下架</option>						
				                  </select>
								</div>
      						</div>
				            <input type="button" value="查询" id="search" class="btn btn-primary searchBtn">
                      	</div>
	                  </form>
                      <div class="box-body table-responsive" style="clear:both;">
                          <input type="button" value="查看" id="detail" class="btn btn-color">
                          <input type="button" value="审批" id="checkSome" class="btn btn-color">
                          <input type="button" value="上架" id="upSome" class="btn btn-color">
                          <input type="button" value="下架" id="downSome" class="btn btn-color">
                      </div>
                      <div class="box-body table-responsive">
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th width="30"><input type="checkbox" id="_selectAll" /></th>
          		               		  <th width="80">商品名称</th>
				               		  <th width="80">商家名称</th>
				               		  <th width="50">图片</th>
				               		  <th width="80">价格</th>
				               		  <th width="40">是否团购</th>
				               		  <th width="80">团购价格</th>
				               		  <th width="80">有效期</th>
				               		  <th width="80">状态</th>
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
									<td><input id="ck_{{:id}}" ckId="{{:id}}" ckType="{{:businessType}}" class="ckSelect" type="checkbox" /></td>
									<td><xmp>{{subString:type}}</xmp></td>
									<td><xmp>{{subString:hotelName}}</xmp></td>
									<td>{{if logo}}<img src="../../{{:logo}}" height="40" width="50">{{else}}<img src="" height="40" width="50">{{/if}}</td>
									<td><xmp>{{:price}}</xmp></td>
                                    <td>{{if groupBuy==0}}否{{else}}是{{/if}}</td>
                                    <td><xmp>{{:groupPrice}}</xmp></td>
                                    <td><xmp>{{:validity}}</xmp></td>
									<td>{{if status==1}}待审批{{else status==-1}}下架{{else status==2}}上架{{/if}}</td>
								</tr>
						  </script>
                  </div>
              </div>
          </div>
      </section>
      <!-- 详细信息 -->
      <div class="modal fade" id="myModals" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="validateForm" class="form-horizontal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">详细信息</h4>
					</div>
					<div class="modal-bodyclass">
                       <table class="search-table">
                       	   <tbody class="shopdetail">
                       	    </tbody>
                       	    <script id="hotelContentTmple" type="text/x-jsrender">
                              <tr>
	                            <td class="search_td"><label>房间类型:</label></td>
	                            <td><div>{{:type}}</div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>商家名称:</label></td>
	                            <td><div class="businessName"></div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>包含项目:</label></td>
	                            <td><div>{{:item}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>购买须知:</label></td>
	                            <td><div>{{:buyNote}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>价格:</label></td>
	                            <td><div>{{:price}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>面积:</label></td>
	                            <td><div>{{:area}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>床位:</label></td>
	                            <td><div>{{:beds}}</div></td>
	                          </tr>
                               <tr>
	                            <td class="search_td"><label>浴室:</label></td>
	                            <td><div>{{:bathRoom}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>通讯:</label></td>
	                            <td><div>{{:communication}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>设施:</label></td>
	                            <td><div>{{:establishment}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>图片:</label></td>
	                            <td>
                                    {{if imagesvo}}
                                       {{for imagesvo.pics1}}
                                         <img src="../../{{:}}" class="img" height="40" width="50">
                                       {{/for}}
                                     {{/if}}
                                </td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>是否团购:</label></td>
	                            <td><div>{{if groupBuy==0}}否{{else}}是{{/if}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>团购价格:</label></td>
	                            <td><div>{{:groupPrice}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>使用期限:</label></td>
	                            <td><div>{{:validity}}</div></td>
	                          </tr>
                            </script>
                            <script id="ticketsContentTmple" type="text/x-jsrender">
                              <tr>
	                            <td class="search_td"><label>门票类型:</label></td>
	                            <td><div>{{:ticketName}}</div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>商家名称:</label></td>
	                            <td><div class="businessName"></div></td>
	                          </tr>
	                          <tr>
	                            <td class="search_td"><label>门票价格:</label></td>
	                            <td><div>{{:price}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>包含项目:</label></td>
	                            <td><div>{{:items}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>购买须知:</label></td>
	                            <td><div>{{:buyNotes}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>兑票须知:</label></td>
	                            <td><div>{{:exchangeNotes}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>图片:</label></td>
	                            <td>
                                    {{if imagesvo}}
                                       {{for imagesvo.pics1}}
                                         <img src="../../{{:}}" class="img" height="40" width="50">
                                       {{/for}}
                                     {{/if}}
                                </td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>是否团购:</label></td>
	                            <td><div>{{if groupBuy==0}}否{{else}}是{{/if}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>团购价格:</label></td>
	                            <td><div>{{:groupPrice}}</div></td>
	                          </tr>
                              <tr>
	                            <td class="search_td"><label>使用期限:</label></td>
	                            <td><div>{{:validity}}</div></td>
	                          </tr>
                            </script>
                       </table>
                    </div>
				</div>
			</div>
		</form>
	</div>
    </body>
</html>