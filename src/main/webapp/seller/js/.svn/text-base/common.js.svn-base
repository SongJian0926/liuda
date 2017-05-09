//保留两位小数
$.views.converters({
	"getTwo":function(str){
		return parseFloat(str).toFixed(2);
	}
});
//验证码倒计时	
	var wait=60;
	function time(o){
		if(wait == 0){
			o.removeAttr("disabled");	
			o.html("获取验证码");
			o.val("获取验证码");
			o.css("background-color","#00b7b2");
			wait=60
			}else{
			o.attr("disabled", true);
			o.html("重新发送" + wait + "");
			o.val("重新发送" + wait + "");
			o.css("background-color","#CCC");
			wait--;
			setTimeout(function(){time(o)},1000)
		} 
	}
	
//检测Cookie
function getCookie(){
	 //获取cookie字符串
    var strCookie=$.cookie("busniessInfo");  
	if(strCookie=="out" || strCookie==null){
	window.location.href = "../login.jsp";
  }
}
$(document).ready(function(){
	
	//检测Cookie
	getCookie();
	//获取验证码
	$("#getCode").click(function(e){
		$("#getCode").addClass("disabled_btn");
		e.preventDefault();
			$.ajax({
				url: root+'cms/businessInfo/sendCode',
				type: 'post',
				data:{
					mobile: trimAndDelQuotation($("#mobile").val()),
					forget: $("#forget").val()
				},success : function(data){
					if(data.code==1){
						if(data.object){
							time($("#getCode"));
						}
						$("#getCode").removeClass("disabled_btn");
					}else{
						$("#getCode").removeClass("disabled_btn");
						$("#checkData").html(data.message);
					}
				}
			});
	});
	//设置左边菜单栏高度和右边内容一样
	$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
	if(JSON.parse($.cookie("busniessInfo")).type!=3){
		//菜单导航点击事件
		$('.nav_menu').find('li').click(function(){
			location.href = $(this).attr('data-url');
		});
		//左边导航菜单点击事件
		$('.left_child_menu').find('a').click(function(e){
			e.preventDefault();
			if($(this).attr('data-url')=="checkBank"){
				$.ajax({
					url: getRootPath()+"cms/myBank/checkBnakCard",
					type: 'post',
					data:{
						id: JSON.parse($.cookie("busniessInfo")).id,
					},success: function(data){
						if(data.code==1){
							window.location.href="bankcard.jsp";
						}else{
							window.location.href="bindcard.jsp";
						}
					}
				});
			}else{
				location.href = $(this).attr('data-url');
			}
		});
	}else{
		$('.nav_menu').find('li:first-child').removeClass("active");
		$('.nav_menu').find('li:last-child').addClass("active");
	}
	
	//多选框全选与全否效果
	$('#checkAll').change(function () {
        $('.checkitem').prop('checked', $(this).prop('checked'));
    });
	$(".show-list").on('click','.checkitem',function() {
        $('#checkAll').prop('checked', $('.checkitem').length == $('.checkitem').filter(':checked').length);
    });
    
    //左上角动态显示用户信息
	if(JSON.parse($.cookie("busniessInfo")).businessName == null){
	}else{
		if((JSON.parse($.cookie("busniessInfo")).businessName).length>8){
			 $("#userName").html((JSON.parse($.cookie("busniessInfo")).businessName).substring(0,6)+"...");
		}else{
			$("#userName").html(JSON.parse($.cookie("busniessInfo")).businessName);
		}
	}
   
    //选择页码事件
	var pagenum=null;
	$("#displayPage").on('keyup','.page_num_input',function(){
		 //获得页码值
		 pagenum=$('.page_num_input').val();
		 var reg=/^[1-9]*[1-9][0-9]*$/;
		 var result= new RegExp(reg);
		 var regs=pagenum.match(result);
		 var maxPage=$(this).parents('#displayPage').find('li:eq(-5)').attr('thispage');
		 var a=maxPage*1+1*1;
		 //如果输入的页码小于0或者大于总页数
		 if(pagenum<=0||pagenum>a){
			 $('.page_num_input').val(null);
		 }
		 if(!regs){
			 $('.page_num_input').val(null);
		 }
	});
	//退出登陆
	$("#getout").click(function(){
		$.cookie('busniessInfo', '', { expires: -1 });
		location.href = $(this).attr('data-url');
	});
    
});