$(function(){
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(3).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(4).addClass('active_nav');
	
	//密码验证
	$(".pwd input").focus(function(){
	$("#check").html("");
	});
	$("#updata").click(function(){
			if($("#newPwd").val()!=$("#checkpwd").val()){
			   $("#check").html("两次密码不同,请重新输入"); return;
			}
			$('#submit_form').isValid(function(result) {
				if (!result) {
					alert("还有不符合规定的字段填写，请检查！");
					return;
				}
				$("#updata").addClass("disabled");
				$(".disabled").attr("disabled",true);
				$.ajax({
					url:getRootPath()+'cms/businessInfo/updatepwd',
					type: 'post',
					data: {
						id: JSON.parse($.cookie("busniessInfo")).id,
						password: $("#oldpwd").val(),
						newPassword:$("#newPwd").val()
					},success: function(data){
						 if(data.code==1){
							 if(data.object==true){
							 alert("密码修改成功");
							 $("#updata").removeClass("disabled");
							 $("#updata").attr("disabled",false);
							 window.location.href="../login.jsp";
							 }else{
							 $("#button").removeClass("disabled");
							 $("#button").attr("disabled",false);
							 $("#updata").bind("click");
							 alert("密码修改失败");	 
							 }
						 }else{
							 $("#updata").removeClass("disabled");
							 $("#updata").attr("disabled",false);
							 $("#check").html(data.message);
						 }
					}
				});
			});
	});
});