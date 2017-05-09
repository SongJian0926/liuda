$(document).ready(function(){
	
	var businessType=JSON.parse($.cookie("busniessInfo")).type;
	var orderno=getUrlParam("orderNo");
	content(businessType,orderno);
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(1).addClass('active');
	
});

function content(businessType,orderno){
	$.ajax({
		url:root+'cms/order/findOrderDetail',
		type:'post',
		dataType:'json',
		data:{	
			businessType:businessType,
			orderNo:orderno
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
					data.object.orderPrice=parseFloat(data.object.orderPrice).toFixed(2);
					var tblContentHtml = $("#detailContentTmple").render(data.object);
					$("#order-detail").html(tblContentHtml);   
			}
			else{
				alert(data.message);
			}
		}
	});
}