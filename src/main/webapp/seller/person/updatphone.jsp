<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="author" content="zhanglin" />
		<title>修改手机号</title>
		<link rel="stylesheet" type="text/css" href="../css/businessInfo.css">
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
   <div class="edit-phone">
				<div class="fl eimg">
					<img src="../images/liuda.png" width="60px" height="22px">
				</div>
				<div class="fl ep">
					修改手机号
				</div>
				<div class="edit_detail_tel">
				<from>
					<div style="height: 20px;"><span id="checkData" style="color: red"></span></div>
					<table>
						<tr>
						   <td>原手机号</td>
						   <td><input data-rule="mobile;required" id="oldphone" type="text" class="tr-width" />  <span class="warn">*</span>
						   </td>
						</tr>
						<tr>
							<td>登录密码</td>
							<td><input type="password" id="password" class="tr-width" data-rule="required"></input>  <span class="warn">*</span>
							</td>
						</tr>
						<tr>	
							<td>新手机号</td>
							<td><input data-rule="mobile;required"  id="mobile" type="text" class="tr-width" ></input>  <span class="warn">*</span>
							</td>
						</tr>
						<tr>
							<td>验证码</td>
							<td><input id="code" type="text" value="" data-rule="required;number;length[6]"></input>  
							
							<input type="button" id="getCode" value="获取验证码" style="cursor: pointer;height: 33px;" class="bt-yan"></input> <span class="warn">*</span></td>
							<input type="hidden" id="forget"  value=""/>
						</tr>
					</table>
				
				</div>
				<div>
					<div class="search-but"><input id="button"  type="button"  style="cursor: pointer;"  class="search-bt" value="确认更改"></input></div>
				<div>
			</from>	
			</div>
		</div>
		</div>
	</div>
  </body>
  <script src="../js/updatphone.js"></script>
</html>
