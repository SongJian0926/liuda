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
$(document).ready(function(){
	$(".userName").val('');
	function login(userName,passworld){
			$.ajax({
			 url: getRootPath() + "cms/businessInfo/loginCheck",
			 type: 'post',
			 data: { account:trimAndDelQuotation(userName),
				    password:trimAndDelQuotation(passworld)
		     },success : function (data){
				if(data.code==1){
				   $.cookie("busniessInfo",JSON.stringify(data.object));
				   if(data.object.type==3){
						window.location.href="person/perfectInfo.jsp?userName="+userName;
				   }else{
						window.location.href="person/businessInfo.jsp";
				   }
				}else{
					$("#loginBtn").removeClass("disabled_btn");
					$("#check").html(data.message);
				}
		     }
		});
	}	
	$("#loginBtn").click(function(){
	   $("#loginBtn").addClass("disabled_btn");
	   var userName=$(".userName").val();
	   var passWorld=$(".password").val();
	   login(userName,passWorld);
	});
	$.cookie("busniessInfo","out");
})