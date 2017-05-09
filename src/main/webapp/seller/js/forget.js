//获取根目录
	function getRootPath(){ 
	     var pathName = window.location.pathname.substring(1); 
	     var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/')); 
	     return window.location.protocol + '//' + window.location.host + '/'+ webName + '/'; 
	}
//去掉字符串两边的空格并去掉双引号，如:"    dd" aa  ",格式化后"dd aa";
	function trimAndDelQuotation(str){
		if(str==""){
			return str;
		}
		var _temp=str.replace(/\"*/g,"");
		_temp = $.trim(_temp);
		return _temp;
}
$(function(){
	var wait=60;
	function time(o){
		if(wait == 0){
			o.removeAttr("disabled");	
			o.val("获取验证码");
			o.css("background-color","#00b7b2");
			wait=60
			}else{
			o.attr("disabled", true);
			o.val("重新发送(" + wait + ")");
			o.css("background-color","#CCC");
			wait--;
			setTimeout(function(){time(o)},1000)
		} 
	}
	//获取焦点，提示消失
	$("#mobile").focus(function(){
		$("#check").html("");
	});
	//发送验证码
	$("#getCheck").click(function(){
		$("#getCheck").addClass("disabled_btn");
		$.ajax({
			url: getRootPath()+'cms/businessInfo/sendCode',
			type: 'post',
			data:{
				mobile: trimAndDelQuotation($(".mobile").val()),
				forget: "forget"
			},success : function(data){
				if(data.code==1){
					if(data.object){
					$("#getCheck").removeClass("disabled_btn");
					time($("#getCheck"));
					}
				}else{
					$("#getCheck").removeClass("disabled_btn");
					$("#check").html(data.message)
				}
			}
		});
	});
	//提交修改密码
	$("#forgetBtn").click(function(){
		$("#forgetBtn").addClass("disabled_btn");
		$.ajax({
			url: getRootPath()+'cms/businessInfo/forget',
			type: 'post',
			data:{
				code: $(".code").val(),
				mobile: trimAndDelQuotation($(".mobile").val()),
				password: $(".password").val()
			},success : function(data){
				if(data.code==1){
					if(data.object){
					alert("修改成功");
					$("#forgetBtn").removeClass("disabled_btn");
					window.location.href="login.jsp";
					}
				}else{
					$("#forgetBtn").removeClass("disabled_btn");
					$("#check").html(data.message)
				}
			}
		});
	});
});







