<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="flora" />
		<title>添加赛事</title>
		<link rel="stylesheet" type="text/css" href="../css/comm.css">
		<link rel="stylesheet" type="text/css" href="../css/addMatch.css">
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
			             <form id="submit-guess-form">
	                     </form>
			        </div>
			    </div>
			</div>
			<script id="matchdetailContentTmple" type="text/x-jsrender">
                <input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="title font-title">{{if id}}编辑赛事{{else}}添加赛事{{/if}}
                <span style="color:#FF0000;font-size:12px">所有上架商品需经后台工作人员审核，3日内完成审核</span>
                </div>
				<div class="center-content">
					<div class="match-data">
	                    <label class="font-sty">赛事标签</label>
						<select name="dictItemId" id="dictItemId" class="sel" data-rule="required;"></select>
						<span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="dictItemId"></span>
					</div>
					<div class="match-data">
						<label class="font-sty">赛事名称</label>
						<input id="matchTitle" name="title"  data-rule="required;length[0~16]" type="text" class="font-sty txt-inpt" value="{{:title}}"/>
                        <span style="color:#FF0000;font-size:15px">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="matchTitle"></span>  
					</div>
					<div class="match-data">
						<div class="li-img1">
	                         <label class="font-sty">赛事图片</label>
						</div>
						<ul class="ul-sty">
                            {{if logo}}
                              <input type="hidden" id="imageLogo" value="" />
                                <li class="li-img">
								  <label class="left-top-box" style="display:none">
									<input type="checkbox" class="checklogo"><span>&nbsp;</span>
								   </label>
                                  <img src="../../{{:logo}}" class="img">
                                </li>
                            {{else}}
                               <li class="li-img">
                               	<div class="upload_div">
		                        	<img id="myImageLogoShow" src="../images/camera.png" class="img">
		                        	<input type="hidden" id="imgLogoUrl" name="picLogourl" value="">
		                        	<input type="file" id="uploadLogoPhotoFile" name="photoFile" class="upload_file">
		                        </div>
	                        </li>
                          {{/if}}
                            <input name="logo" style="display:none" id="logo" value="{{:logo}}"></input>
						</ul>
					</div>
					<div><br><br><br><br></div>
					<div class="match-data">
	                    <label class="font-sty">赛事地址</label>
						<select name="provinceId"  class="sel" id="provinceName"></select>  
						<select name="cityId"  id="cityName"></select>  
						<select name="areaId"  id="areaName" data-rule="required;"></select>
						<span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="areaId"></span>
					</div>
					<div class="match-data">
						<label class="font-sty">详细地址</label>
						<input id="address" name="address"  data-rule="required;length[0~16]" type="text" class="font-sty txt-inpt" value="{{:address}}"/>
						<span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="address"></span>
					</div>
					<div class="match-data">
						<label class="font-sty">赛事时间</label>
							<div class="value">
	                        	<input type="text" id="startdate" name="startdate" data-rule="required" value="{{:startdate}}">
                            	<span style="color:#FF0000;font-size:15px;">*</span>
                            	<span class="msg-box n-right warn-font" style="position:static;display:none" for="startdate">
	                      	</div><label class="font-sty">至</label>
				      		<div class="value">
	                        	<input type="text" id="enddate" name="enddate" data-rule="required" value="{{:enddate}}">
                            	<span style="color:#FF0000;font-size:15px;">*</span>
                            	<span class="msg-box n-right warn-font" style="position:static;" for="enddate">
	                     	</div>
					</div>
					<div class="match-data">
						<label class="font-sty">参赛费用</label>
						<input id="matchPrice" name="price" data-rule="required;money" type="text" class="font-sty txt-inpt" value="{{:price}}"/>
						<span class="font-sty tp">元   <span style="color:#FF0000;font-size:15px">*</span></span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="matchPrice"></span> 
                    </div>
					<div class="match-data">
						<label class="font-sty">参赛订金</label>
						<input id="deposit" name="deposit" type="text" data-rule="required;money" class="font-sty txt-inpt" value="{{:deposit}}"/>
                        <span class="font-sty tp">元   <span style="color:#FF0000;font-size:15px">*</span></span>
						<span class="msg-box n-right warn-font" style="position:static;" for="deposit"></span>
					</div>
					<div class="match-data">
						<label class="font-sty">报名截止时间</label>
						<div class="value">
	                        <input type="text" id="deadline" name="deadline" data-rule="required" value="{{:deadline}}">
                            <span style="color:#FF0000;font-size:15px;">*</span>
                            <span class="msg-box n-right warn-font" style="position:static;" for="deadline">
	                    </div>
					</div>
					<div class="match-data">
						<label class="font-sty">人数限制</label>
						<input id="maxNum" name="maxNum" type="text" data-rule="required;digits" class="font-sty txt-inpt" value="{{:maxNum}}"/>
                        <span style="color:#FF0000;font-size:15px">*</span>
						<span class="msg-box n-right warn-font" style="position:static;" for="maxNum"></span>
					</div>
					<div class="match-data">
						<label class="font-sty lbl">活动详情</label>
						<textarea class="area-match font-sty" id="matchContent" data-rule="required" name="content" value="{{:content}}">{{:content}}</textarea>
                        <span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="matchContent"></span>
					</div>
					<div class="match-data">
					    <div class="li-img1">
	                         <label class="font-sty">赛事图片</label>
	                     </div>
						<ul class="ul-sty ul-img">
                            {{if mediaPath}}
                              <input type="hidden" id="image" value="" />
                              {{for mediaPath.split(",")}}
                                <li class="li-img">
								  <label class="left-top-box" style="display:none">
									<input type="checkbox" class="checklogo"><span>&nbsp;</span>
								   </label>
                                  <input name="mediaPath" style="display:none" id="photo" value="{{:}}"></input>
                                  <img src="../../{{:}}" class="img">
                                </li>
                              {{/for}}
							<li class="li-img" style="{{if mediaPath.split(",").length==4}}display:none{{else}}{{/if}}">
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
					<div class="match-data">
						<label class="font-sty">观赛门票名称</label>
						<input id="matchTicketName" name="ticketName" type="text" data-rule="required" class="font-sty txt-inpt" value="{{:ticketName}}"/>
                        <span style="color:#FF0000;font-size:15px">*</span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="matchTicketName"></span>
					</div>
					<div class="match-data">
						<div class="li-img1">
	                         <label class="font-sty">观赛门票图片</label>
						</div>
						<ul class="ul-sty">
                            {{if ticketImages}}
                              <input type="hidden" id="imageTicketImages" value="" />
                                <li class="li-img">
								  <label class="left-top-box" style="display:none">
									<input type="checkbox" class="checklogo"><span>&nbsp;</span>
								   </label>
                                  <img src="../../{{:ticketImages}}" class="img">
                                </li>
                            {{else}}
                               <li class="li-img">
                               	<div class="upload_div">
		                        	<img id="myImageTicketImagesShow" src="../images/camera.png" class="img">
		                        	<input type="hidden" id="imgTicketImagesUrl" name="picTicketImagesurl" value="">
		                        	<input type="file" id="uploadTicketImagesPhotoFile" name="photoFile" class="upload_file">
		                        </div>
	                        </li>
                          {{/if}}
                           <input id="ticketImages" style="display:none" id="ticketImages" value="{{:ticketImages}}"></input>
						</ul>
					</div>
					<div class="match-data">
						<label class="font-sty">观赛门票价格</label>
						<input id="ticketPrice" name="ticketPrice" data-rule="required;money" type="text" class="font-sty txt-inpt" value="{{:ticketPrice}}"/>
						<span class="font-sty tp">元   <span style="color:#FF0000;font-size:15px">*</span></span>
                        <span class="msg-box n-right warn-font" style="position:static;" for="ticketPrice"></span> 
					</div>
					<div class="match-data">
						<label class="font-sty">观赛门票库存</label>
						<input id="ticketStockNum" name="ticketStockNum" type="text" data-rule="required;digits" class="font-sty txt-inpt" value="{{:ticketStockNum}}"/>
                        <span style="color:#FF0000;font-size:15px">*</span>
						<span class="msg-box n-right warn-font" style="position:static;" for="ticketStockNum"></span>
					</div>
					<div class="match-data">
						<label class="font-sty">观赛门票截止时间</label>
						<div class="value">
	                        <input type="text" id="deadlineView" name="deadlineView" data-rule="required" value="{{:deadlineView}}">
                            <span style="color:#FF0000;font-size:15px;">*</span>
                            <span class="msg-box n-right warn-font" style="position:static;" for="deadlineView">
	                    </div>
					</div>
					<div class="match-data">
						<label class="font-sty">观赛门票优惠时间</label>
	                     	<div class="value">
	                        	<input type="text" id="favStartTime" name="favStartTime" value="{{:favStartTime}}">
	                      	</div><label class="font-sty">至</label>
				      		<div class="value">
	                        	<input type="text" id="favEndTime" name="favEndTime" value="{{:favEndTime}}">
	                     	</div>
					</div>
					<div class="match-data">
						<label class="font-sty">观赛门票优惠价格</label>
						<input id="favourablePrice" name="favourablePrice" data-rule="money" type="text" class="font-sty txt-inpt" value="{{:favourablePrice}}"/>
						<span class="font-sty tp">元  </span>
					</div>
				</div>
            </script>
			<script id="guessContentTmple" type="text/x-jsrender">
				<!-- 竞猜 -->
					<div class="subtitle font-title">设置竞猜内容</div>
					<div class="match-data">
						<label class="font-sty">竞猜标题</label>
						<input id="guessId" name="guessId" type="text" style="display:none" value="{{:id}}"/>
						<input id="guessTitle" name="guessTitle"  data-rule="length[0~16]" type="text" class="font-sty txt-inpt" value="{{:title}}"/>
					</div>
					<div class="match-data">
						<label class="font-sty">竞猜赔率</label>
						<input id="guessOdds" name="guessOdds" type="text" data-rule="money" class="font-sty txt-inpt" value="{{:odds}}"/>
					</div>
					<div class="match-data">
						<label class="font-sty">竞猜选项</label>
						<input id="guessOption" name="guessOption"  data-rule="length[0~16]" type="text" class="font-sty txt-inpt" value=""/>
						<input type="button" class="addGuessOption font-sty" value="+"/>
                        <span class="msg-box n-right warn-font" style="position:static;" for="guessOption"></span> 
					</div>
					<div id="guessOptionList" class="match-data"></div>
				<!-- 二次抽奖 -->
					<div class="subtitle font-title">设置二次抽奖</div>
					<div class="match-data">
						<label class="font-sty">抽奖等级</label>
						<input id="prizeLevel" name="prizeLevel"  data-rule="length[0~10];digits" type="text" class="font-sty txt-prize" value=""/>(等奖)
                        <span class="msg-box n-right warn-font" style="position:static;" for="prizeLevel"></span> 
						<input id="prizeNum" name="prizeNum"  data-rule="length[0~10];digits" type="text" class="font-sty txt-prize" value=""/>(人)
                        <span class="msg-box n-right warn-font" style="position:static;" for="prizeNum"></span> 
						<input id="prize" name="prize"  data-rule="length[0~50]" type="text" class="font-sty txt-prize" value=""/>(奖品)
						<input type="button" class="addPrize font-sty" value="+"/>
					</div>
					<div id="prizeOptionList" class="match-data"></div>
				<div class="room-data">
					<input type="button" class="cacel font-sty" value="取消"/>
					<input type="button" class="required font-sty sub" value="提交审核"/>
				</div>
            </script>
		</div>
	</body>
	<script type="text/javascript" src="../js/addmatch.js"></script>
	 <script type="text/javascript">
		//上传图片
		uploadphoto("macth");
     </script>
</html>