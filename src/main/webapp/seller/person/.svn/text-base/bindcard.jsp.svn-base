<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/bindcard.css">
    <title>绑定银行卡</title>
</head>
<body>
<jsp:include page="../header-top.jsp"></jsp:include>
<div class="content">
    <jsp:include page="header-person.jsp"></jsp:include>
    <div class="right_content fl">
        <div class="bind_content">
            <div class="bind_cont">
                <div class="title"><img src="../images/mybankcard.png"/><span class="title_font">绑定银行卡</span></div>
                <div class="box-content">
                <div style="height: 20px;"><label id="check" style="color: red"></label></div>
                    <form id="submit_form">
                    	<div class="select">
	          			 	 <label>银行卡类型</label>
	                           <div>
	                          	 <input type="hidden" id="forget" value="">
	                          	 <label><input name="type" type="radio" value="0"/><span>对私</span></label>
	                          	 <label><input  name="type" type="radio" value="1"/><span>对公</span></label>
	                          </div>
	                   		 </div>
                        <div class="bind_name">
                            <label id="nameReal">收款名字</label>
                            <div>
                                <input id="name" class="p4" type="text" data-rule="required;length[~30];" name="bind_name" vale=""/>
                                <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="name"></span>
                            </div>
                        </div>
                        <div class="bind_name">
                            <label>账户</label>
                            <div>
                                <input id="bind_account" data-rule="required;number;length[15~21];" type="text" name="bind_account" vale=""/>
                                <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="bind_account"></span>
                            </div>
                        </div>
                        <div class="bind_name">
                            <label>银行名称</label>
                            <div>
                                <input id="bank_name" class="p4"  data-rule="required;length[~20];" type="text" name="bank_name" vale=""/>
                                <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="bank_name"></span>
                            </div>
                        </div>
                        <div class="bind_name">
                            <label>支行名称</label>
                            <div>
                                <input id="bind_bank_name" class="p4" data-rule="required;length[~20];" type="text" name="bind_bank_name" vale=""/>
                                <span class="warn">*</span>
                                <span class="msg-box n-right warn-font" style="position:static;" for="bind_bank_name"></span>
                            </div>
                        </div>
                        <div class="bind_name">
                            <label>开户城市</label>
                            <div>
                               <select class=""  id="sent_bank" name="expressCompany" data-rule="required" style="width: 305px;height: 35px;margin-left: 14px;border:1px solid #d8d8d8;'">
										<option value="">请选择</option>
										<option value="01">总行</option>
										<option value="02">天津</option>
										<option value="03/09">上海</option>
										<option value="04">山西</option>
										<option value="05">内蒙</option>
										<option value="06">辽宁</option>
										<option value="07">吉林</option>
										<option value="08">黑龙江</option>
										<option value="10">江苏</option>
										<option value="11">北京</option>
										<option value="12">安徽</option>
										<option value="13">福建</option>
										<option value="15">山东</option>
										<option value="14">江西</option>
										<option value="16">河南</option>
										<option value="17">湖北</option>
										<option value="18">湖南</option>
										<option value="19">浙江</option>
										<option value="20">广西</option>
										<option value="21">海南</option>
										<option value="22">四川</option>
										<option value="23">贵州</option>
										<option value="24">云南</option>
										<option value="25">西藏</option>
										<option value="26">陕西</option>
										<option value="27">甘肃</option>
										<option value="28">青海</option>
										<option value="29">宁夏</option>
										<option value="30">新疆</option>
										<option value="31">重庆</option>
										<option value="34">大连</option>
										<option value="38">青岛</option>
										<option value="39">宁波</option>
										<option value="40">厦门</option>
										<option value="41">深圳</option>
										<option value="44">光东</option>
										<option value="50">河北</option>
										<option value="97">香港</option>
									</select>
									<span class="warn">*</span>
                                    <span class="msg-box n-right warn-font" style="position:static;" for="sent_bank"></span>
                            </div>
                        </div>
                    </form>
                    <button class="ok" id="ok" style="cursor: pointer">确认绑定</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="../js/bindcard.js"></script>
</html>