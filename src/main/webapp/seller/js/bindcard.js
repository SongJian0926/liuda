$(function(){
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(3).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(1).addClass('active_nav');
	var value= $("input[type='radio']:checked").val();
	$("input[type='radio']").click(function(){
		var value= $("input[type='radio']:checked").val();
		if(value == "0"){
				$("#nameReal").html("收款名字");
		}else if(value == "1")
			$("#nameReal").html("公司名称");
	});
	$("#ok").click(function(){
		if($("input[type='radio']:checked").val()==null){
			alert("请选择类型");
			return;
		}
		$("input").focus(function(){
			$("#check").html("");
		})
		$('#submit_form').isValid(function(result) {
			if (!result) {
				alert("还有不符合规定的字段填写，请检查！");
				return;
			} 
			$("#ok").addClass("disabled_btn");
			$.ajax({
					url:getRootPath()+"cms/myBank/insertBnakCard",
					type:'post',
					data:{
						id: JSON.parse($.cookie("busniessInfo")).id,
						bindName :$("#name").val(),
						bindAccount :$("#bind_account").val(),
						bankName :$("#bank_name").val(),
						bindBankName :$("#bind_bank_name").val(),
						bindBankCityCode :$("#sent_bank").val(),
						bindBankCityName :$("#sent_bank").find("option:selected").text(),
						bankCarType :$("input[type='radio']:checked").val()
					},success: function(data){
						if(data.code==1){
							$("#ok").removeClass("disabled_btn");
							window.location.href="bankcard.jsp";
						}else{
							$("#ok").removeClass("disabled_btn");
							 $("#check").html(data.message);
						}
					}
				});
		});	
	});
	
});
