$(function(){
	$("input").focus(function(){
		$("#checkData").html("");
	});
	//确认更改
	$("#button").click(function(){
		$("#button").addClass("disabled_btn");	
		if($("#oldphone").val().length<1){
			alert("旧手机号不能为空！");
			return;
		}
		if($("#mobile").val().length<1){
			alert("新手机号不能为空！");
			return;
		}
		if($("#password").val().length<1){
			alert("密码不能为空！");
			return;
		}
		if($("#code").val().length<1){
			alert("验证码不能为空！");
			return;
		}
		$.ajax({
			url: getRootPath()+'cms/businessInfo/updatMobile',
			type: 'post',
			data:{
				oldmobile: trimAndDelQuotation($("#oldphone").val()),
				newmobile: trimAndDelQuotation($("#mobile").val()),
				password: $("#password").val(),
				code: $("#code").val(),
				type: JSON.parse($.cookie("busniessInfo")).type,
				id: JSON.parse($.cookie("busniessInfo")).id
			},success : function(data){
				if(data.code==1){
					if(data.object){
						alert("修改成功");
						$("#button").removeClass("disabled_btn");
						window.location.href="businessInfo.jsp";
					}
				}else{
					$("#button").removeClass("disabled_btn");
					$("#checkData").html(data.message);
				}
			}
		});
	});
});