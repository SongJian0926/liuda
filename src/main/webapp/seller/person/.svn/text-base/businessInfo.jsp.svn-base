<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>商家资料</title>
		<link rel="stylesheet" type="text/css" href="../css/businessInfo.css">
		<link rel="stylesheet" type="text/css" href="../css/insertData.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
			<jsp:include page="header-person.jsp"></jsp:include>
			<div class="right_content fl">
			    <div class="info">
  				 	<div class="ht">
  				 	  <input type="button" class="edit-bt" value="编辑" id="edit"></input></div>
  				 	  <div class="info_Detail">
  				 	  <form id="submit_form">
                          <div>
                               <label style="display: none;" class="edit-name"><span style="font-weight: bold;">名称：</span></label>
                               <span class="title" id="ojectName" style="font-weight: bold;"></span>
                               <input type="text" id="inputName" data-rule="required;length[~20]" style="display: none;" name="ojectName" class="edit-show" value=""/>
                               <span class="warn" style="display: none;">*</span>
                               <span class="msg-box n-right warn-font" style="position:static;" for="inputName"></span>
                           </div>
                               <div><span style="font-weight: bold;">手机号：</span>&nbsp;&nbsp;<label class="pho" id="phone"></label>
                               <a href="updatphone.jsp"   class="edit">更改</a>
                         	   <input type="hidden" name="mobile" id="mobile" value=""/>
							   <input type="hidden" name="code" value="null"/>
                          </div>
                          <div>
                             	  <span style="font-weight: bold;">电话：</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="title" id="telephoe"></span>
                               <input type="text" id="inputTelepho" data-rule="required;tel;" style="display: none;" name="telphone" class="mobile-show">
                               <span class="warn" style="display: none;">*</span>
                               <span class="msg-box n-right warn-font" style="position:static;" for="inputTelepho"></span>
                          </div>
                          <div class="tourist">
                              	 <span style="font-weight: bold;"> 营业时间：</span><span  class="title" id="time"></span>
                               <!--  <input type="text" id="inputTime"  style="display: none;" name="time" class="edit-show edit-time"> -->
		                        <!-- <div class="datetime_clear"></div>
						         <input type="text" readOnly class="input-search" id="inputTime">
						      	</div> -->
						      	<select id="start_time" style="display: none;" class="edit-show1 edit-time">
						      		<option value="00:00">00:00</option>
						      		<option value="00:30">00:30</option>
						      		<option value="01:00">01:00</option>
						      		<option value="01:30">01:30</option>
						      		<option value="02:00">02:00</option>
						      		<option value="02:30">02:30</option>
						      		<option value="03:00">03:00</option>
						      		<option value="03:30">03:30</option>
						      		<option value="04:00">04:00</option>
						      		<option value="04:30">04:30</option>
						      		<option value="05:00">05:00</option>
						      		<option value="05:30">05:30</option>
						      		<option value="06:00">06:00</option>
						      		<option value="06:30">06:30</option>
						      		<option value="07:00">07:00</option>
						      		<option value="07:30">07:30</option>
						      		<option value="08:00">08:00</option>
						      		<option value="08:30">08:30</option>
						      		<option value="09:00">09:00</option>
						      		<option value="09:30">09:30</option>
						      		<option value="10:00">10:00</option>
						      		<option value="10:30">10:30</option>
						      		<option value="11:00">11:00</option>
						      		<option value="11:30">11:30</option>
						      		<option value="12:00">12:00</option>
						      		<option value="12:30">12:30</option>
						      		<option value="13:00">13:00</option>
						      		<option value="13:30">13:30</option>
						      		<option value="14:00">14:00</option>
						      		<option value="14:30">14:30</option>
						      		<option value="15:00">15:00</option>
						      		<option value="15:30">15:30</option>
						      		<option value="16:00">16:00</option>
						      		<option value="16:30">16:30</option>
						      		<option value="17:00">17:00</option>
						      		<option value="17:30">17:30</option>
						      		<option value="18:00">18:00</option>
						      		<option value="18:30">18:30</option>
						      		<option value="19:00">19:00</option>
						      		<option value="19:30">19:30</option>
						      		<option value="20:00">20:00</option>
						      		<option value="20:30">20:30</option>
						      		<option value="21:00">21:00</option>
						      		<option value="21:30">21:30</option>
						      		<option value="22:00">22:00</option>
						      		<option value="22:30">22:30</option>
						      		<option value="23:00">23:00</option>
						      		<option value="23:30">23:30</option>
						      		<option value="24:00">24:00</option>
						      	</select><span class="edit-show1" style="display: none;">-
						      	<select id="end_time" style="display: none;" class="edit-show1 edit-time">
						      		<option value="00:00">00:00</option>
						      		<option value="00:30">00:30</option>
						      		<option value="01:00">01:00</option>
						      		<option value="01:30">01:30</option>
						      		<option value="02:00">02:00</option>
						      		<option value="02:30">02:30</option>
						      		<option value="03:00">03:00</option>
						      		<option value="03:30">03:30</option>
						      		<option value="04:00">04:00</option>
						      		<option value="04:30">04:30</option>
						      		<option value="05:00">05:00</option>
						      		<option value="05:30">05:30</option>
						      		<option value="06:00">06:00</option>
						      		<option value="06:30">06:30</option>
						      		<option value="07:00">07:00</option>
						      		<option value="07:30">07:30</option>
						      		<option value="08:00">08:00</option>
						      		<option value="08:30">08:30</option>
						      		<option value="09:00">09:00</option>
						      		<option value="09:30">09:30</option>
						      		<option value="10:00">10:00</option>
						      		<option value="10:30">10:30</option>
						      		<option value="11:00">11:00</option>
						      		<option value="11:30">11:30</option>
						      		<option value="12:00">12:00</option>
						      		<option value="12:30">12:30</option>
						      		<option value="13:00">13:00</option>
						      		<option value="13:30">13:30</option>
						      		<option value="14:00">14:00</option>
						      		<option value="14:30">14:30</option>
						      		<option value="15:00">15:00</option>
						      		<option value="15:30">15:30</option>
						      		<option value="16:00">16:00</option>
						      		<option value="16:30">16:30</option>
						      		<option value="17:00">17:00</option>
						      		<option value="17:30">17:30</option>
						      		<option value="18:00">18:00</option>
						      		<option value="18:30">18:30</option>
						      		<option value="19:00">19:00</option>
						      		<option value="19:30">19:30</option>
						      		<option value="20:00">20:00</option>
						      		<option value="20:30">20:30</option>
						      		<option value="21:00">21:00</option>
						      		<option value="21:30">21:30</option>
						      		<option value="22:00">22:00</option>
						      		<option value="22:30">22:30</option>
						      		<option value="23:00">23:00</option>
						      		<option value="23:30">23:30</option>
						      		<option value="24:00">24:00</option>
						      	</select>
                                <span class="warn" style="display: none;">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="inputTime"></span>
                          </div>
                          <div>
                            	  <span style="font-weight: bold;">  商家简介：</span><span class="title" id="introduce"></span>
                               <textarea id="inputTroduce" data-rule="required;length[~1000]" name="introduce" style="display: none;resize:none;border-color: #d8d8d8" class="edit-show1 edit-time edit-intro"  rows="10" cols="40"></textarea>
                               <span class="warn" style="vertical-align: top;display: none;">*</span>
                               <span class="msg-box n-right warn-font" style="position:static;" for="inputTroduce">
                           </div>
                           <div class="hotel">
                              	  <span style="font-weight: bold;">酒店类型：</span><span class="title" id="hotelType"></span>
                                <input type="text" id="inputhotelType" style="display: none;" name="hotelType" class="edit-show edit-time">
                                <span class="warn" style="display: none;">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="inputhotelType"></span>
                          </div>
                          <div class="hotel">
                              	  <span style="font-weight: bold;">酒店提示：</span><span class="title" id="prompt"></span>
                                <input type="text" id="inputprompt" style="display: none;" name="prompt" class="edit-show edit-time">
                                <span class="warn" style="display: none;">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="inputprompt"></span>
                          </div>
                          <div class="hotel">
                              	 <span style="font-weight: bold;"> 酒店政策：</span><span class="title" id="policy"></span>
                                <input type="text" id="inputpolicy" style="display: none;" name="policy" class="edit-show edit-time">
                                <span class="warn" style="display: none;">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="inputpolicy"></span>
                          </div>
                          <div class="tourist">
                              	  <span style="font-weight: bold;">温馨提示：</span><span  class="title" id="note"></span>
                                <input type="text" id="inputnote" style="display: none;" name="note"  class="edit-show edit-time">
                                <span class="warn" style="display: none;">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="inputnote"></span>
                          </div>
                          <div>
                          	           <span style="font-weight: bold;">地址：</span><span class="title" id="address"></span>
                                <input type="text" id="inputAddress" data-rule="required;address;length[~50]" style="display: none;" name="address" class="edit-show">
                                <span class="warn" style="display: none;">*</span>
                               <span class="msg-box n-right warn-font" style="position:static;" for="inputAddress"></span>
                          </div>
                      </form>
  				   </div>
  				 </div> 
  				  <div class="add_img">
	                            	 <div class="li-img1" style="">
	                        		 	<label class="font-s"><span style="font-weight: bold;">商家图片</span></label>
	                        		 </div>
	                        		 <ul class="ul-sty">
	                        		 
	                        		 <li class="li-img imgs" id="p">
	                        		 	 	<div class="upload_div" style="margin-left: 0px;width: 75px;height: 75px">
		                        		 	 	 <img src="../images/camera.png" id="myImageShow" name="picurl1" class="img">
		                        		 	 	 <input type="hidden"  name="picurl" class="imgUrl"  value=""/>
	                                 			 <input name="photoFile" id="uploadPhotoFile" value="" type="file" class="upload_file"></input>
                             		    	</div>
                             		    </li>
	                        		 </ul>
	                   		    </div>
	                   		    <div class="moeven"><span>最多添加4张图片</span>
	                   		    <span class="editt">
	                   		         <a id="a-qx" href="javascript:void(0);" style="visibility: hidden;">取消</a>
	                   		         <a id="a-ok" href="javascript:void(0);" style="margin-left: 40px;" >编辑</a>
	                   		        </span>
	                   		    </div>
  				  <div class="search-but">
                 <input id="save" type="submit" class="search-bt edit-name" value="提交" style="display: none;cursor: pointer;"></input>
              </div>
  			</div>  
	</body>
	<script src="../js/businessInfo.js"></script>
</html>