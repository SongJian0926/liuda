<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <link rel="stylesheet" type="text/css" href="../css/cash.css">
	    <title>提现管理</title>
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
		    <jsp:include page="header-person.jsp"></jsp:include>
	    <div class="men"></div><div class="ban"><p class="ban_p1">提交成功</p>
        <p class="ban_p2">您的提现金额将于5个工作日内到账，请耐心等待，谢谢！</p><button class="ban_button" id="ban_show" style="cursor: pointer">确定</button></div>
		    <div class="right_content fl">
		        <div class="box-font">
 		            <p>账&nbsp户&nbsp余&nbsp额： ￥<span id="remain"></span>.<span  class="innerspan" id="inners">00</span></p>
 		            <p>可提现余额： ￥<span id="yu"></span>.<span class="innerspan" id="yuy">00</span></p>
	<!--  		            <p style="margin-left: 280px; font-size: 13px; color: red;">（账期为<span style="font-size: 13px; color: red;" id="day"></span>&nbsp&nbsp&nbsp比例为<span id="por" style="font-size: 13px;color: red;"></span>）</p>
	 --> 		        </div><div class="line"></div>
		        <div class="box-body">
		        <div style="height: 20px;"><span id="checkData" style="color: red"></span></div><br/>
		            <form id="submit_form">
		                <div class="account text">
		                    <label>提现金额</label>
		                    <div><!--  -->
		                        <input type="text" name="cashNum" data-rule="required;integer;range[1~]" id="cashNum">
		                        <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="cashNum"></span>
		                        <p>注：提取金额不得大于提现金额</p>
		                    </div>
		                </div>
		
		                <div class="password text">
		                    <label>手机号&nbsp;&nbsp;&nbsp;</label>
		                    <div>
		                        <input type="text" name="mobile" data-rule="mobile;required" id="mobile">
		                        <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="mobile"></span>
		                    </div>
		                </div>
		                <div class="password text cher test">
		                    <label>验证码&nbsp;&nbsp;&nbsp;</label>
		                    <div>
		                      <input autocomplete="off" class="code" name="userName" id="code" placeholder="验证码" data-rule="required;number;" type="text"><button class="test-pwd" id="getCode" style="cursor: pointer">获取验证码</button>
		                      <span class="warn">*</span>
                              <span class="msg-box n-right warn-font" style="position:static;" for="code"></span>
		                   	  <input type="hidden" id="forget" value="forget"/>
		                    </div>
		                </div>
		                <div class="password text pwd">
		                    <label>登录密码</label>
		                    <div>
		                        <input autocomplete="off" type="password" name="userName" data-rule="required;" id="passw">
		                        <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="passw"></span>
		                    </div>
		                </div>
		            </form>
		            <div class="group">
		                <input type="button" class="dis" id="forgetBtn" value="提交" style="cursor: pointer" />
		            </div>
		        </div>
		    </div>
		</div>
	</body>
	<script src="../js/cash.js"></script>
</html>