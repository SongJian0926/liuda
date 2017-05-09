$(document).ready(function(){
//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(3).addClass('active');
	var checkBox="<label class='left-box' style='display: none;'><input type='checkbox' class='checklogo'><span>&nbsp</span></label>"
//左上角点击事件，触发动态页面
     $(".edit-bt").click(function(){
        if( $(".edit-bt").val()=="编辑"){
        	$(".edi").css("display","");
        	if($(".imgUrlNum").size()==4){
        		$('#p,.imgs,#uploadPhotoFile,#myImageShow').hide();
        	}
        	$(this).css("background","url('../images/iconfont-fanhui.png') no-repeat 15px #00b9b4");
        	$(this).val("返回");
            $(".title").hide();
            $("#inputName").val($("#ojectName").html());
            $("#inputTelepho").val($("#telephoe").html());
            $("#inputAddress").val($("#address").html());
            var _opentime=$("#time").html();
            var _starttime=_opentime.substr(0,5);
            var _endtime=_opentime.substr(6,10);
            $("#start_time").val(_starttime);
            $("#end_time").val(_endtime);
            //$("#inputTime").val($("#time").html());
            $("#inputTroduce").val($("#introduce").html());
            $("#inputhotelType").val($("#hotelType").html());
			$("#inputpolicy").val($("#policy").html());
			$("#inputprompt").val($("#prompt").html());
			$("#inputnote").val($("#note").html());
            $(".fe,.li-img1,.edit-name,#p,.moeven,.edit,.warn").show();
            $('.edit-intro,.edit-show,.edit-show1,.mobile-show').css("display","inline-block");
            $('.pho').css("margin-left","40px");
            $('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
        }else{
        	 window.location.href="businessInfo.jsp";
        	 $('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
        }
     });
     
//获取请求地址
     if(JSON.parse($.cookie("busniessInfo")).type==0){//酒店是0
     	var urlparams=getRootPath()+'cms/hotel/newFindHotelById';
     	var updateUrl=getRootPath()+'cms/hotel/updataHotel';
     	$("#inputhotelType").attr("data-rule","required;length[~10]");
     	$("#inputprompt,#inputpolicy").attr("data-rule","required;length[~300]");
     	$(".tourist").hide();
     	$(".tourist>input").removeAttr("name");
     	
     }else{
    	$("#inputnote").attr("data-rule","required;length[~300]");
    	$("#inputTime").attr("data-rule","required;length[~15]");
    	var urlparams=getRootPath()+'cms/tourist/newFindTouristById';
     	var updateUrl=getRootPath()+'cms/tourist/updataTourist';
     	$(".hotel").hide();
     	$(".hotel>input").removeAttr("name");
     }
 //获取商家资料
    $.ajax({
    		url: urlparams,
    		type: 'post',
    		data:{
    			modelId: JSON.parse($.cookie("busniessInfo")).goodsId
    		},success: function(data){
    			console.log(data);
    			if(data.code==1){
    				$("#ojectName").html(JSON.parse($.cookie("busniessInfo")).businessName);
    				$("#ojectName").html(data.object.hotelName);
    				$("#ojectName").html(data.object.ticketName);
    				$("#phone").html(data.object.mobile);
    				$("#mobile").val(data.object.mobile);
    				$("#telephoe").html(data.object.telphone);
    				$("#address").html(data.object.address); 
    				$("#time").html(data.object.businessTime)
    				$("#introduce").html(data.object.introduce);
    				$("#introduce").html(data.object.touristDesc);
    				$("#hotelType").html(data.object.hotelType);
    				$("#note").html(data.object.notes);
    				$("#policy").html(data.object.policy);
    				$("#prompt").html(data.object.prompt);
    				//显示图片
    				var arr=((data.object.picurl).split(","));
    					for(var i=0;i<arr.length;i++){
						$('.ul-sty li:last').before(' <li class="li-img">'+checkBox+'<input name="imgUrl" class="imgUrlNum" style="display:none" value='+arr[i]+'></input><img src="'+root+arr[i]+'" class="img"/></li>');
    				}
    				$("#p,.edit,.moeven").hide();
    				$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});	
    				
    			}else{
    					alert("查询失败");
    			}
    		}
    	});
//图片上传
 	$(document).on('change','#uploadPhotoFile',function(){
 		
 		$.ajaxFileUpload({
 			url : '../../cms/' + 'businessInfo' + '/photoUpload',
 			secureuri : false,
 			fileElementId : 'uploadPhotoFile',
 			type : 'POST',
 			dataType : 'json',
 			success : function(data, status) {
 				console.log(data);
 				if (data) {
 					var ds = data.object.split(":");
 					if (ds[0] == 1) {
 						if($('.ul-sty li').length==4){
 							$('.ul-sty li:last').before(' <li class="li-img">'+checkBox+'<input  name="imgUrl" class="imgUrlNum"  style="display:none" value='+ds[1]+'></input><img  src="../../'+ds[1]+'" class="img"/></li>');
							$('.ul-sty li:last').hide();
 						}else{
							$('.ul-sty li:last').before(' <li class="li-img">'+checkBox+'<input  name="imgUrl" class="imgUrlNum" style="display:none" value='+ds[1]+'></input><img src="../../'+ds[1]+'" class="img"/></li>');
 						}
 					} else {
 						alert(ds[1]);
 					}
 				}
 			},
 			error : function(data, status, e) {
 				alert(data);
 			}
 		});
 	 });
 	//图片编辑 
	$("#a-ok").click(function(e){
		e.preventDefault();
		if($("#a-ok").text()=="编辑"){
			$("#a-qx").css("visibility","visible");
			$("#a-ok").text("删除");
			$(".left-box").css("display","");
		}else{
			if($('.checklogo:checked').closest("li").size()==0){
				alert("请勾选要删除的图片");
				return;
			}
			$("#a-qx").css("visibility","hidden");
			$("#a-ok").text("编辑");
			$(".left-box").css("display","none");
			$('.checklogo:checked').closest("li").remove();
			if($(".imgUrlNum").size()!=4){
				$('#p,.imgs,#uploadPhotoFile,#myImageShow').show();
				$('.ul-sty li:last').show();
			}
		}
	});
	$("#a-qx").click(function(e){
		e.preventDefault();
		$("#a-qx").css("visibility","hidden");
		$("#a-ok").text("编辑");
		$(".left-box").css("display","none");
	});
 //保存信息
 	 $("#save").click(function(){
 		$('#submit_form').isValid(function(result){
	 		var imgUrl="";//图片的路径
	 		param=$('#submit_form').serialize();
			params = decodeURIComponent(param,true);
			var open_time;
			if(JSON.parse($.cookie("busniessInfo")).type==1){
				if($("#start_time").val().length==0){
					alert("开始时间不能为空！");
					return;
				}
				if($("#end_time").val().length==0){
					alert("结束时间不能为空！");
					return;
				}
				if($("#start_time").val()>$("#end_time").val()){
					alert("开始时间不能大于结束时间");
					return;
				}
				
				open_time=$("#start_time").val()+"-"+$("#end_time").val();
			}
			
			for(var i=0;i<$(".imgUrlNum").size();i++){
				imgUrl+=$(".imgUrlNum").eq(i).val()+",";
			}
			if (!result) {
				alert("还有不符合规定的字段填写，请检查！");
				return;
			}
			if(imgUrl==""){
				alert("至少一张图片");
				return;
			}
			$("#save").addClass("disabled_btn");
				$.ajax({
						url: updateUrl,
						type: 'post',
						data: {
							imgUrl: imgUrl,
							id: JSON.parse($.cookie("busniessInfo")).goodsId,
							code: "null",
							model: params,
							opentime:open_time,
							businessUserId: JSON.parse($.cookie("busniessInfo")).id
						},success: function(data){
						    if(data.code==1){
						    	$.cookie("busniessInfo",JSON.stringify(data.object),{path:'/liuda/seller'});
						    /*	sessionStorage.setItem("busniessInfo",JSON.stringify(data.object));*/
							  	alert("保存成功");
							  	$("#save").removeClass("disabled_btn");
								window.location.href="businessInfo.jsp";
							}else{
								$("#save").removeClass("disabled_btn");
								alert(data.message);
							}
				   	}
				});
			 });
 	});
});