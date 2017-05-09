<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <link rel="stylesheet" type="text/css" href="../css/pwdManager.css">
	    <title>密码管理</title>
	</head>
	<body>
		<jsp:include page="../header-top.jsp"></jsp:include>
		<div class="content">
		    <jsp:include page="header-person.jsp"></jsp:include>
		    <div class="right_content fl">
		         <div class="pwd_body">
            <div class="pwd_content">
                <div class="pwd_title"><p>修改密码</p></div>
                <div class="pwd_from">
                    <form id="submit_form">
                    <div style="height: 20px;"><label id="check" style="color: red"></label></div>
                        <div class="pwd">
                            <label>原始密码</label>
                            <div>
                           	 <input type="password" data-rule="required" id="oldpwd" value="" name="oldPwd">
                           	 <span class="warn">*</span>
                             <span class="msg-box n-right warn-font" style="position:static;" for="oldpwd"></span>
                            </div>
                        </div>
                        <div class="pwd">
                            <label class="p3">新密码</label>
                            <div>
                                <input type="password" value="" data-rule="密码:required;password" id="newPwd" name="newPwd"><label></label>
                                <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="newPwd"></span>
                            </div>
                        </div>
                        <div class="pwd">
                            <label class="p4">确认密码</label>
                            <div>
                                <input  type="password" value="" data-rule="确认密码:required;match(newPwd);" id="checkpwd" name="aginPwd"><label></label>
							    <span class="warn">*</span>
							    <span class="msg-box n-right warn-font" style="position:static;" for="checkpwd"></span>                           
							</div>
                        </div>
                        <div class="ispwd">
                            <input id="updata" type="button" style="cursor: pointer" value="确认修改">
                        </div>
                    </form>
                </div>
            </div>
		    </div>
		</div>
	</body>
	<script src="../js/pwdManager.js"></script>
</html>