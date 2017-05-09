<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	    <meta name="author" content="sj" />
	    <title>商家信息</title>
	    <link rel="stylesheet" type="text/css" href="../css/insertData.css">
	    <link rel="stylesheet" type="text/css" href="../css/addticket.css">
	    <script src="../js/jquery-1.11.0.min.js"></script>
	    <script src="../js/common.js"></script>
	    <script src="../js/perfectlnfo.js"></script>
	</head>
	<body>
	<div class="men"></div><div class="ban"><p class="ban_p1">欢迎使用溜达溜达平台</p>
    <p class="ban_p2">快去完善商家信息吧~</p><button class="ban_button" id="ban_show" style="cursor: pointer">立即完善</button></div>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
		    <jsp:include page="header-person.jsp"></jsp:include>
		    <div class="right_content fl">
		       <div class="insert-context">
		       			<div class="select">
	          			 	 <label>商家性质</label>
	                           <div>
	                          	 <input type="hidden" id="forget" value="">
	                          	
	                          	<label><input name="type" type="radio" value="景点"/><span>景点</span></label>
	                          	 <label><input  name="type" type="radio" value="酒店"/><span>酒店</span></label>
	                          </div>
	                   		 </div>
                    	<form id="submit_form">
                    	<div class="text">
	                	        <div class="context select">
	                                    <label>名称</label>
	                                    <div class="">
	                                         <input type="text" data-rule="required;length[~20]"  id="name" name="touristName"/>
	                                         <span class="warn">*</span>
                                             <span class="msg-box n-right warn-font" style="position:static;" for="name"></span>
	                                    </div>
	                                </div>
	                            <div class="context select f1">
	                                <label>绑定手机</label>
	                                <div class="">
	                                    <input type="text"  id="mobile"  data-rule="mobile;required" placeholder="手机号"  name="mobile"/>
	                                    <span class="warn">*</span>
                                        <span class="msg-box n-right warn-font" style="position:static;" for="mobile"></span>
	                                </div>
	                            </div>
	                            <div class="context select f3">
	                                <label>验证码</label>
	                                <div class="">
	                                   <input id="code" name="code" placeholder="验证码" data-rule="required;number;length[6]" type="text" style="margin-right: 10px;height: 32px;"/>
	                                   <button id="getCode1" style="height: 33px;">获取验证码</button> 
	                                   <span class="warn">*</span>
	                                   <span class="msg-box n-right" style="position:static;" for="code"></span>
	                                </div>
	                            </div>
	                            <div class="context select">
	                                <label>电话</label>
	                                <div class="">
	                                    <input type="text" id="telphone" placeholder="固定电话"  data-rule="required;tel;"  name="telphone" />
	                                    <span class="warn">*</span>
                                        <span class="msg-box n-right warn-font" style="position:static;" for="telphone"></span>
	                                </div>
	                            </div>
	                            <div class="context select f1 s2">
	                                <label>营业时间</label>
	                                <div class="">
	                                    <!-- <input data-rule="required;length[~15]" type="text" id="time"   /> -->
	                                <select id="start_time" class="edit-show1 edit-time">
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
						      	</select>-
						      	<select id="end_time" class="edit-show1 edit-time">
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
	                                </div>
	                            </div>
	                            <div class="context select">
	                                <label>简介</label>
	                                <div class="">
	                                    <textarea id="introduce" data-rule="required;length[~1000]"  name="touristDesc"></textarea>
	                                    <span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                                        <span class="msg-box n-right warn-font" style="position:static;" for="introduce"></span>
	                                </div>
	                            </div>
	                            <div class="context select f1 s1" style="display: none">
	                                <label>酒店类型</label>
	                                <div class="">
	                                    <input type="text"  data-rule="required;length[~30]" id="hotelType"/>
	                                    <span class="warn">*</span>
                                        <span class="msg-box n-right warn-font" style="position:static;" for="hotelType"></span>
	                                </div>
	                            </div>
	                            <div class="context select f1 s1" style="display: none">
	                                <label>酒店提示</label>
	                                <div class="">
	                                    <textarea  id="prompt"  data-rule="required;length[~15]"></textarea>
	                                     <span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                                         <span class="msg-box n-right warn-font" style="position:static;" for="prompt"></span>
	                                </div>
	                            </div>
	                            <div class="context select f1 s1" style="display: none">
	                                <label>酒店政策</label>
	                                <div class="">
	                                    <input type="text" data-rule="required;length[~200]" id="policy"/>
	                                    <span class="warn">*</span>
                                        <span class="msg-box n-right warn-font" style="position:static;" for="policy"></span>
	                                </div>
	                            </div>
	                            <div class="context select f1 s2">
	                                <label>温馨提示</label>
	                                <div class="">
	                                    <textarea data-rule="required;length[~300]"  id="sy"></textarea>
	                                    <span style="color:#FF0000;font-size:15px;vertical-align: top;">*</span>
                                        <span class="msg-box n-right warn-font" style="position:static;" for="sy"></span>
	                                </div>
	                            </div>
	                            <div class="context select f1">
	                                <label>地理位置</label>
	                                <div class="">
	                                    <input type="text" id="dec" data-rule="required;address;length[~50]"  name="address"/>
	                                    <span class="warn">*</span>
                                        <span class="msg-box n-right warn-font" style="position:static;" for="dec"></span>
	                                </div>
	                            </div>
	                            </form>
	                            <div class="add_img">
	                            	 <div class="li-img1" style="margin-right: 30px;">
	                        		 	<label class="font-sty">商品图片</label>
	                        		 </div>
	                        		 <ul class="ul-sty">
	                        		 <li class="li-img">
	                        		 	 	<div class="upload_div" style="margin-left: 0px;">
		                        		 	 	 <img src="../images/camera.png" id="myImageShow" name="picurl1" class="img">
		                        		 	 	 <input type="hidden"  name="picurl" class="imgUrl"  value=""/>
	                                 			 <input name="photoFile" id="uploadPhotoFile" value="" type="file" class="upload_file"></input>
                             		    	</div>
                             		    </li>
	                        		 </ul>
	                   		    </div>
	                   		    <div class="memo"><span>最多添加4张图片</span>
	                   		    <span class="edit">
	                   		         <a id="a-quxiao" href="javascript:void(0);" style="visibility: hidden;">取消</a>
	                   		         <a id="a-edit" href="javascript:void(0);" style="margin-left: 40px;" >编辑</a>
	                   		        </span></div>
	                       	    <div class="submit">
	                            	<input type="button" id="save" style="cursor: pointer;margin-bottom: 30px;" value="提交">
	                           </div>
	                </div>
		       </div>
		    </div>
		</div>
	</body>
</html>