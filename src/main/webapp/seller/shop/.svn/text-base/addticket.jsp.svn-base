<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <link rel="stylesheet" type="text/css" href="../css/comm.css">
	    <link rel="stylesheet" type="text/css" href="../css/addticket.css">
	    <title>添加景点</title>
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
		    <jsp:include page="header-shop.jsp"></jsp:include>
		    <div class="right_content fl">
		        <div class="ticket">
	                <div class="ticket_content">
	                    <form id="submit-form">
	                    </form>
	                    <form id="submit-bottom-form">
	                    </form>
	                </div>
	            </div> 
	        </div>
                <script id="ticketsdetailContentTmple" type="text/x-jsrender">
                    <h1 class="fl">{{if id}}编辑商品{{else}}添加商品{{/if}}</h1>
                    <span style="color:#FF0000;font-size:12px">&nbsp;&nbsp;所有上架商品需经后台工作人员审核，3日内完成审核</span>
                      <input type="hidden" id="tId" name="id" value="{{:id}}" />
	                  <div class="ticket_name">
	                     <label>门票名称</label>
                         <div class="value">
							 {{!--<select id="ticketName" name="ticketName" style="width:322px;height:33px;margin-left:2px">
	                          	
	                         </select>--}}
                           <input type="text" data-rule="required;length[0~20]" name="ticketName" id="ticketName" value="{{:ticketName}}">
                           <span style="color:#FF0000;font-size:15px;">*</span>
                           <span class="msg-box n-right warn-font" style="position:static;" for="ticketName">
                         </div>
	                  </div>
	                  <div class="ticket_text">
	                     <label>门票价格</label>
	                     <div class="value">
	                       <input type="text" maxlength="7" data-rule="required;money;nozero" id="price" name="price" id="price" value="{{:price}}"><span class="tp">&nbsp;元&nbsp;&nbsp;<span style="color:#FF0000;font-size:15px;">*</span></span>
                           <span class="msg-box n-right" style="position:static;" for="price"></span>
	                     </div>
	                  </div>
	                  <div class="ticket_text">
	                     <label>包含项目</label>
	                     <div class="value">
	                        <input type="text" data-rule="required;length[0~1000]" name="items" id="items" value="{{:items}}">
                            <span style="color:#FF0000;font-size:15px;">*</span>
                            <span class="msg-box n-right warn-font" style="position:static;" for="items">
	                      </div>
	                   </div>
                       <div class="buy_text">
                           <label>购买须知</label>
                           <div class="buy_value">
                               <textarea name="buyNotes" data-rule="required;length[0~2000]" id="buyNotes" value="{{:buyNotes}}">{{:buyNotes}}</textarea>
                               <span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                               <span class="msg-box n-right warn-font" style="position:static;" for="buyNotes">                                               
                            </div>
                       </div>
                       <div class="buy_text">
                           <label>兑票须知</label>
                           <div class="buy_value">
                               <textarea name="exchangeNotes" data-rule="required;length[0~1000]" id="exchangeNotes" value="{{:exchangeNotes}}">{{:exchangeNotes}}</textarea>
                               <span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                               <span class="msg-box n-right warn-font" style="position:static;" for="exchangeNotes">                          
                           </div>
                       </div>
	                  <div class="ticket_text">
	                     <label>预定时间</label>
	                     <div class="value">
	                        <input type="text" id="predTime" readonly="readonly" data-rule="required" name="predTime" value="{{:predTime}}">
                            <span style="color:#FF0000;font-size:15px;">*</span>
                            <span class="msg-box n-right warn-font" style="position:static;" for="predTime">
	                      </div>
	                   </div>
                       <div class="room-data">
                           <div class="li-img1">
                              <label class="font-sty">商品图片</label>
                           </div>
                           <ul class="ul-sty ul-img">
                            {{if imagesvo}}
                              <input type="hidden" id="image" value="" />
                              {{for imagesvo.pics1}}
                                <li class="li-img">
                                  <label class="left-top-box" style="display:none">
									<input type="checkbox" class="checklogo"><span>&nbsp;</span>
								  </label>
                                  <input name="imgUrl" style="display:none" value="{{:}}"></input>
                                  <img src="../../{{:}}" class="img">
                                </li>
                              {{/for}}
                               <li class="li-img" style="{{if imagesvo.pics1.length==4}}display:none{{/if}}">
                            	    <div class="upload_div">
                       	               <img id="myImageShow" src="../images/camera.png" class="img">
                       	               <input type="hidden" id="imgUrl" name="picurl" value="">
                       	               <input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
                                     </div>
                               </li>
                              {{else}}
                               <li class="li-img">
                                 	<div class="upload_div">
		                               <img id="myImageShow" src="../images/camera.png" class="img">
		                        	   <input type="hidden" id="imgUrl" name="picurl" value="">
		                        	   <input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
		                            </div>
	                           </li>
                             {{/if}}
                           </ul>
                       </div>
                        <div class="room-data1 fl">
                               <label class="font-sty lbl-item">最多可添加4张</label>
                        </div>
                        <div class="room-data2 fl">
                               <a href="javascript:void(0);" class="a-quxiao" style="visibility:hidden">取消</a>
                           		<a href="javascript:void(0);" class="a-edit">编辑</a>
                        </div>
                   </script>
                  <script id="ticketsgroupContentTmple" type="text/x-jsrender">
                  <div class="group_buy">
                       <div class="group_buy_title goods_mang">
                          <label>
					      <input type="checkbox" id="checkAll" {{if groupBuy==1}}checked{{/if}}/><span>参加团购</span>
			           </label>
                       </div>
                       <div class="ticket_text">
                           <label style="{{if groupBuy==1}}color:#333333{{else}}color:#d8d8d8{{/if}}" class="fco">团购价格</label>
                            <div class="value">
                                <input type="text" id="groupPrice" {{if groupBuy!=1}}readOnly{{else}}data-rule="required;money;nozero"{{/if}} maxlength="7" name="groupPrice" value="{{:groupPrice}}"><span style="{{if groupBuy==1}}color:#333333{{else}}color:#d8d8d8{{/if}}" class="fco tp">&nbsp;元</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="groupPrice"></span>                            
                            </div>
                       </div>
                       <div class="ticket_text">
                           <label style="{{if groupBuy==1}}color:#333333{{else}}color:#d8d8d8{{/if}}" class="fco">使用期限</label>
                            <div class="value">
                                <input type="text" id="validity"  name="validity" readOnly value="{{:validity}}">
                            </div>
                       </div>
                       <div class="ticket_text_button">
                           <input type="button" class="offer button_sty cacel" value="取消"/>
                           <input type="button" class="sub button_sty" value="提交" id="sub"/>
                       </div>
                   </div>
            </script>
		</div>
	</body>
	 <script type="text/javascript" src="../js/addgoods.js"></script>
	 <script type="text/javascript">
		//上传图片
		uploadphoto("tickets");
        </script>
</html>