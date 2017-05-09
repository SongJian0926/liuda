$(document).ready(function(){
	
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(3).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(1).addClass('active_nav');
	
	$.ajax({
		url: getRootPath()+"cms/myBank/checkBnakCard",
		type: 'post',
		data:{
			id: JSON.parse($.cookie("busniessInfo")).id,
		},success: function(data){
			if(data.code==1){
				$("#mybankName").html(data.object.realName);
				$("#mybankCardData").html(data.object.account);
				$("#mybankCardName").html(data.object.bankName);	
			}else{
				window.location.href="bindcard.jsp";
			}
		}
	});
		$("#deletMybankCard").click(function(){
			 if (!confirm("确认要删除？")) { 
				 window.location.href="bankcard.jsp";
			 }else{
				 $.ajax({
						url: getRootPath()+"cms/myBank/updataBnakCard",
						type: 'post',
						data:{
							id: JSON.parse($.cookie("busniessInfo")).id,
						},success: function(data){
							if(data.code==1){
								window.location.href="bindcard.jsp";
							}else{
								alert(data.message);
							}
						}
				});
			 }
		});
});