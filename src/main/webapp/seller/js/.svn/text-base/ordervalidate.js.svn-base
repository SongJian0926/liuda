$(document).ready(function(){

	var businessId=JSON.parse($.cookie("busniessInfo")).id;
	var businessType=JSON.parse($.cookie("busniessInfo")).type;
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(1).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(1).addClass('active_nav');
	
	
	//校验兑换码
	$('.cancelbtn').click(function(){
		$('#validateForm').isValid(function(result) {
			if (!result) {
				return;
			}
			var orderno=$('.search_cancel').val().trim();
			//加载兑换码校验结果
			content(orderno,businessId,businessType);
		});
		
	});
	
	 
	/*$(".cancel_no").on('keyup','.search_cancel',function(){
		 //获得页码值
		 pagenum=$('.search_cancel').val();
		 var reg=/^[0-9]*$/;
		 var result= new RegExp(reg);
		 var regs=pagenum.match(result);
		 if(!regs){
			 $('.search_cancel').val(null);
		 }
	});*/
});
function content(orderno,businessId,businessType){
	$.ajax({
		url:root+'cms/order/findOrder',
		type:'post',
		dataType:'json',
		data:{	
			orderNo:orderno,
			businessId:businessId,
			businessType:businessType,
			orderStatus:1
		},
		success:function(data){
			console.log(data);
			$('.cancel_result').show();
			//兑换成功
			if(data.code == 1){	
					$('#fail').hide();
					$('#fail1').hide();
					$('#fail2').hide();
					$('#fail3').hide();
					var tblContentHtml = $("#successTmple").render(data.object);
					$("#result").html(tblContentHtml);   
					$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
			}
			else{
				if(data.message=="订单未支付"&&businessType==0){
					//如果酒店订单未支付
					$('#fail1').hide();
					$("#result").html(""); 
					$('#fail').hide();
					$('#fail3').hide();
					$('#fail2').show();
					$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
				}
				if(data.message=="订单未支付"&&businessType==1){
					//如果景点订单未支付
					$('#fail1').hide();
					$("#result").html(""); 
					$('#fail').hide();
					$('#fail2').hide();
					$('#fail3').show();
					$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
				}
				//如果兑换码被兑换
				if(data.message=="兑换码已兑换"){
					$('#fail1').hide();
					$('#fail2').hide();
					$('#fail3').hide();
					$("#result").html(""); 
					$('#fail').show();
					$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
				}
				if(data.message=="兑换码不存在"){//兑换码不存在
						$('#fail').hide();
						$('#fail2').hide();
						$('#fail3').hide();
						$("#result").html(""); 
						$('#fail1').show();
						$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
					
				}
				
			}
		}
	});
}