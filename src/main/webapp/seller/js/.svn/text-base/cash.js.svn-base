$(document).ready(function(){
	//隐藏提示框
	$(".ban,.men").hide();
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(3).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(2).addClass('active_nav');
	
	//获取账户余额
	$.ajax({
		url: root+'cms/cashRecord/findRemain',
		type: 'post',
		data:{
			 businessId: JSON.parse($.cookie("busniessInfo")).id,
			 businessType: JSON.parse($.cookie("busniessInfo")).type
		},success : function(data){
			console.log(data);
			 if(data.code==1){
				 if(data.object.isCash=="未绑定银行卡"){
					 alert("请先绑定银行卡");
					 window.location.href="bindcard.jsp";
					 return;
				 }
				 if(data.object.remain==0.00){
					$(".innerspan").html(0);
					$("#remain").html(00);
					$("#yu").html(0);
					$("#yuy").html(00);
				 }else{
					var num=Math.round(data.object.remain*100)/100+"";
					var arr=num.split(".");
					$("#inners").html(arr[1]);
					var ynum=Math.round(data.object.cashMoney*100)/100+"";
					var parr=ynum.split(".");
					$("#yu").html(parr[0]);
					$("#yuy").html(parr[1]);
					$("#remain").html(parseInt(data.object.remain));
				}
				 	$("#day").html(data.object.accountPeriod);
					$("#por").html(data.object.isProportion);
					
					if(data.object.isCash=="不可提现"){
						$("input").attr("readonly","readonly");
						$("input").attr("data-rule","");
						$("#forgetBtn").attr("disabled","disabled");
					}
			 }else{
				 alert("失败");
			 }
		}
	});
	
	//提现提交
	$("#forgetBtn").click(function(){
		
			/*if($("#cashNum").val()>$("#yu").html()){
				$("#checkData").html("提取金额不能大于可提现余额");
				return;
			}*/
			$('#submit_form').isValid(function(result){
				if (!result) {
					alert("还有不符合规定的字段填写，请检查！");
					return;
				}
				//获取用户输入的提现金额和可提取金额
				var a=parseInt($("#cashNum").val()*100);
				var b=parseInt($("#yu").html()*100);
				var c=parseInt($("#yuy").html());
				var d=b+c;
				//提现金额不能大于可提现金额
				if(a>d){
					$("#checkData").html("提取金额不能大于可提现余额");
					return;
				}
				
			 $("#forgetBtn").addClass("disabled_btn");	
			 $.ajax({
					url: root+'cms/cashRecord/saveNewCashRecord',
					type: 'post',
					data:{
						mobile: trimAndDelQuotation($("#mobile").val()),
						id: JSON.parse($.cookie("busniessInfo")).id,
						code: $(".code").val(),
						cashNum: $("#cashNum").val(),
						password: $("input[type='password']").val(),
						type: JSON.parse($.cookie("busniessInfo")).type
					},success : function(data){
						if(data.code==1){
							$("#forgetBtn").removeClass("disabled_btn");
						    $(".ban,.men").show();
						}
						else{
							$("#forgetBtn").removeClass("disabled_btn");
							$("#checkData").html(data.message);
						}
					}
			});
		});
	});
	$("#ban_show").click(function(){
		window.location.href="businessInfo.jsp";
	});
});