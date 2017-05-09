$(function(){
	
	$(".s1").hide();
    $(".s2").show();
    $("#prompt,#policy,#hotelType").removeAttr("name").removeAttr("data-rule");
    $("#time").attr({"name":"businessTime","data-rule":"required"});
    $("#sy").attr({"name":"notes","data-rule":"required;length[0~200];"});
    
	$("#userName").html(getUrlParam("userName"));
	$("#ban_show").click(function(){
		$(".ban,.men").hide();
	});
	var checkBox="<label class='left-box' style='display: none;'><input type='checkbox' class='checklogo'><span>&nbsp</span></label>"
	var imgUrl="";//图片的路径
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
						imgUrl+=ds[1]+",";
						if($('.ul-sty li').length==4){
							$('.ul-sty li:last').before(' <li class="li-img">'+checkBox+'<input name="imgUrl" style="display:none" value='+ds[1]+'></input><img src="../../'+ds[1]+'" class="img"/></li>');
							$('.ul-sty li:last').hide();
						}else{
							$('.ul-sty li:last').before(' <li class="li-img">'+checkBox+'<input name="imgUrl" style="display:none" value='+ds[1]+'></input><img src="../../'+ds[1]+'" class="img"/></li>');
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
	//动态显示页面的字段
    $("input[type='radio']").click(function(){
    	var value= $("input[type='radio']:checked").val();
        if(value=="景点") {
            $(".s1").hide();
            $(".s2").show();
            $("#prompt,#policy,#hotelType").removeAttr("name").removeAttr("data-rule");
            $("#time").attr({"name":"businessTime","data-rule":"required"});
            $("#sy").attr({"name":"notes","data-rule":"required;length[~300]"});
        }else{
            $(".s1").show();
            $(".s2").hide();
            $("#time,#sy").removeAttr("name").removeAttr("data-rule");
            $("#prompt").attr({"name":"prompt","data-rule":"required;length[~300]"});
            $("#policy").attr({"name":"policy","data-rule":"required;length[~300]"});
            $("#hotelType").attr({"name":"hotelType","data-rule":"required;length[~10]"});
        }
    });
    var getcodedata;
    //获取验证码
	$("#getCode1").click(function(e){
		$("#getCode1").addClass("disabled_btn");
		e.preventDefault();
			$.ajax({
				url: root+'cms/businessInfo/sendCode',
				type: 'post',
				data:{
					mobile: trimAndDelQuotation($("#mobile").val()),
					forget: $("#forget").val()
				},success : function(data){
					if(data.code==1){
						$("#getCode1").removeClass("disabled_btn");
						getcodedata=new Date();
						if(data.object){
							time($("#getCode1"));
						}
					}else{
						$("#getCode1").removeClass("disabled_btn");
						alert(data.message);
					}
				}
			});
	});
	//图片编辑 
		$("#a-edit").click(function(){
			if ($("#a-edit").text() == "编辑"){
				$('.checklogo:checked').show();
				// "取消"出现
				$("#a-quxiao").css("visibility","visible");
				// "确定"按钮出现
				$("#a-edit").text("删除");
				//勾选框出现
				$(".left-box").css("display","");
			}else{
				if($('.checklogo:checked').closest("li").size()==0){
					alert("请勾选要删除的图片");
					return;
				}
				//"取消"消失
				$("#a-quxiao").css("visibility","hidden");
				// "编辑"按钮出现
				$("#a-edit").text("编辑");
				$(".left-box").css("display","none");
				$('.checklogo:checked').closest("li").remove();
				if(($(".img").size()!=5)){
					$('.ul-sty li:last').show();
				}
			}
		});
		$("#a-quxiao").click(function(e){
			e.preventDefault();
			//"取消"消失
			$("#a-quxiao").css("visibility","hidden");
			// "编辑"按钮出现
			$("#a-edit").text("编辑");
			//勾选框出现
			$(".left-box").css("display","none");
		});
	
    //保存信息
    $("#save").click(function(){
    	$('#submit_form').isValid(function(result) {
    		var getcodedata1=new Date();
    		if(getcodedata1-getcodedata>5*60*1000){
    			alert("验证码失效，请重新获取");
    			return;
    		}
    		if($("input[type='radio']:checked").val()==null){
    			alert("请选择类型");
    			return;
    		}
    		if (!result) {
				alert("还有不符合规定的字段填写，请检查！");
				return;
			}
			if(imgUrl==""){
    			alert("至少一张图片");
    			return;
    		}
			var open_time;
			if($("input[type='radio']:checked").val()=="景点"){
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
			}
			open_time=$("#start_time").val()+"-"+$("#end_time").val();
			$("#save").addClass("disabled_btn");	
	    	var value= $("input[type='radio']:checked").val();
	    	var params=$('#submit_form').serialize();
			params = decodeURIComponent(params,true);
			
	    	if(value=="景点"){//1是景点
	    		$.ajax({
	    			url: getRootPath()+'cms/tourist/newSaveTourist',
	    			type: 'post',
	    			data: {
	    			  imgUrl: imgUrl,
	    			  mobile: trimAndDelQuotation($("#mobile").val()),
	    			  code: $("#code").val(),
	    		      model: params,
	    		      opentime:open_time,
	    			  businessUserId: JSON.parse($.cookie("busniessInfo")).id
	    			},
	    			success: function(data){
	    				if(data.code==1){// id:用户ID goodsId:商品ID type:类型 0酒店 1景点  businessName:商品名字
	    					  $.cookie("busniessInfo",JSON.stringify(data.object),{path:'/liuda/seller'});
	    					  alert("保存成功");
	    					  $("#save").removeClass("disabled_btn");
	    				 	  window.location.href="businessInfo.jsp";
	    				}else{
	    					$("#save").removeClass("disabled_btn");
	    					 alert(data.message);
	    				}
	    			}
	    	    });
	    	}else{//0是酒店
	    		$.ajax({
	    			url: getRootPath()+'cms/hotel/newSaveModel',
	    			type: 'post',
	    			data: {
	    				imgUrl: imgUrl,
	    				mobile: trimAndDelQuotation($("#mobile").val()),
	    				code: $("#code").val(),
	    				model: params,
	    				businessUserId: JSON.parse($.cookie("busniessInfo")).id
	    			},success: function(data){
	    			    if(data.code==1){
	    				  	$.cookie("busniessInfo",JSON.stringify(data.object),{path:'/liuda/seller'});
	    				  	alert("保存成功");
	    				    $("#save").removeClass("disabled_btn");
	    					window.location.href="businessInfo.jsp";
	    				}else{
	    					$("#save").removeClass("disabled_btn");
	    					alert(data.message);
	    				}
	    			}
	    		});
	    	}
    	});
    });
});