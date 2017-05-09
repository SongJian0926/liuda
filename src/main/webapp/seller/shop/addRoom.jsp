<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>添加房间</title>
		<link rel="stylesheet" type="text/css" href="../css/comm.css">
		<link rel="stylesheet" type="text/css" href="../css/addRoom.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
			<jsp:include page="header-shop.jsp"></jsp:include>
			<div class="right_content fl bor-sty">
			   <div class="ticket">
	                <div class="ticket_content">
			             <form id="submit-form">
			             </form>
			             <form id="submit-bottom-form">
	                     </form>
			        </div>
			    </div>
			</div>
			<script id="roomdetailContentTmple" type="text/x-jsrender">
                <input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="title font-title">{{if id}}编辑商品{{else}}添加商品{{/if}}
                <span style="color:#FF0000;font-size:12px">所有上架商品需经后台工作人员审核，3日内完成审核</span>
                </div>
				<div class="center-content">
					<div class="room-data">
						<label class="font-sty">房间名称</label>
							{{!--<select id="type" name="type" style="width:322px;height:33px;margin-left:12px">
	                          	
	                         </select>--}}
						<input id="roomType" name="type"  data-rule="required;length[0~16]" type="text" class="font-sty txt-inpt" value="{{:type}}"/>
                        <span style="color:#FF0000;font-size:15px">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="roomType"></span>  
					</div>
					<div class="room-data">
						<label class="font-sty">房间价格</label>
						<input id="roomPrice" name="price" maxlength="7" data-rule="required;money;nozero" type="text" class="font-sty txt-inpt" value="{{:price}}"/><span class="font-sty tp">元   <span style="color:#FF0000;font-size:15px">*</span></span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="roomPrice"></span> 
                    </div>
					<div class="room-data">
						<label class="font-sty">包含项目</label>
						<input id="project" name="item" type="text" data-rule="required;length[0~1000]" class="font-sty txt-inpt" value="{{:item}}"/>
                        <span style="color:#FF0000;font-size:15px">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="project"></span>
					</div>
					<div class="room-data">
						<label class="font-sty lbl">购买须知</label>
						<textarea class="area-room font-sty" id="prompt" data-rule="required;length[1~2000]" name="buyNote" value="{{:buyNote}}">{{:buyNote}}</textarea>
                        <span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="prompt"></span>
					</div>

					<div class="room-data">
						<div>
							<ul class="ul-sty ul-room">
								<li class="li-room"><label class="font-sty">房型简介</label></li>
								<li><ul class="ul-sty">
									<li class="li-type"><span class="span-type">面积</span>
									<input  name="area" type="text" data-rule="required;pluss" class="font-sty type-inpt" id="area" value="{{:area}}"/><span class="tp">m²&nbsp;<span style="color:#FF0000;font-size:15px">*</span></span>
									<span class="msg-box n-right warn-font" style="position:static;" for="area"></span></li> 
                                    <li class="li-type"><span class="span-type">床位</span>
									<input  name="beds" type="text" data-rule="required;pluss" class="font-sty type-inpt" value="{{:beds}}" id="beds"/>
                                    <span style="color:#FF0000;font-size:15px">*</span>
                                    <span class="msg-box n-right warn-font" style="position:static;" for="beds"></span></li>
									<li class="li-type"><span class="span-type">浴室</span>
									<input  name="bathRoom" type="text" data-rule="required;length[0~200]" id="bathRoom" class="font-sty type-inpt" value="{{:bathRoom}}"/>
                                    <span style="color:#FF0000;font-size:15px">*</span>
                                    <span class="msg-box n-right warn-font" style="position:static;" for="bathRoom"></span></li>
									<li class="li-type"><span class="span-type">通讯</span>
									<input  name="communication" type="text" data-rule="required;length[0~200]" id="communication" class="font-sty type-inpt" value="{{:communication}}"/>
                                    <span style="color:#FF0000;font-size:15px">*</span>
                                    <span class="msg-box n-right warn-font" style="position:static;" for="communication"></span></li>
									<li class="li-type"><span class="span-type">设施</span>
									<input  name="establishment" type="text" data-rule="required;length[0~200]" id="establishment" class="font-sty type-inpt" value="{{:establishment}}"/>
                                    <span style="color:#FF0000;font-size:15px">*</span>
                                    <span class="msg-box n-right warn-font" style="position:static;" for="establishment"></span></li></ul>
								</li>
							</ul>
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
                                  <input name="logo" style="display:none" id="photo" value="{{:}}"></input>
                                  <img src="../../{{:}}" class="img">
                                </li>
                              {{/for}}
							<li class="li-img" style="{{if imagesvo.pics1.length==4}}display:none{{else}}{{/if}}">
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
                         <label class="font-sty lbl-item">最多可添加4张</label>
                         <a href="javascript:void(0);" class="a-quxiao" style="visibility:hidden">取消</a>
                         <a href="javascript:void(0);" class="a-edit">编辑</a>
					</div>
				</div>
                </script>
                <script id="roomgroupContentTmple" type="text/x-jsrender">
				<div class="goods_mang">
					<label>
							<input type="checkbox" id="checkAll" {{if groupBuy==1}}checked{{/if}}/><span>参加团购</span>
					</label>
				</div>
				{{!--<div class="title font-title"><input type="checkbox" class="tugou" />
				<label class="" >参加团购</label>--}}</div>
				<div class="room-data">
					<label class="font-sty fco" style="{{if groupBuy==1}}color:#333333{{else}}color:#d8d8d8{{/if}}">团购价格</label>
					<input id="groupPrice" name="groupPrice" type="text" {{if groupBuy!=1}}readOnly{{else}}data-rule="required;money;nozero"{{/if}} maxlength="7" class="font-sty txt-inpt"  value="{{:groupPrice}}"/><span class="font-sty fco tp" style="{{if groupBuy==1}}color:#333333{{else}}color:#d8d8d8{{/if}}">元</span>
                    <span class="msg-box n-right warn-font" style="position:static;" for="groupPrice"></span>				
                </div>
				<div class="room-data">
					<label class="font-sty fco" style="{{if groupBuy==1}}color:#333333{{else}}color:#d8d8d8{{/if}}">使用期限</label>
					<input type="text" id="validity" class="font-sty txt-inpt" readOnly value="{{:validity}}" name="validity"/>
				</div>
				<div class="room-data">
					<input type="button" class="cacel font-sty" value="取消"/>
					<input type="button" class="required font-sty sub" value="提交"/>
				</div>
              </script>
		</div>
	</body>
	<script type="text/javascript" src="../js/addgoods.js"></script>
	 <script type="text/javascript">
		//上传图片
		uploadphoto("room");
     </script>
</html>