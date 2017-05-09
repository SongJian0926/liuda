$(document).ready(function() {
	// 景点id或者酒店id
	var id = "";//JSON.parse($.cookie("busniessInfo")).matchId;
	// 商户类型
	var businessType = "";//JSON.parse($.cookie("busniessInfo")).type;
	//商品保存的url(景点或酒店)
	var turl;
	// 获得编辑页面的id
	var gid = getUrlParam("id");
	var type = getUrlParam("type");
	//创建date
	var d=new Date();
	//年
	var year=d.getFullYear();
	//月
	var month=d.getMonth()+1;
	//日
	var day=d.getDate();
	//当前时间
	var time=year+"-"+month+"-"+day;
	
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(3).addClass('active_nav');
	
	if(gid!=null)
	{
		$.ajax({
			url : '../../cms/macth/findMacthById',
			type : 'post',
			dataType : 'json',
			data : {
				modelId : gid
			},
			success : function(data) {
				console.log(data);
				if (data.code == 1) {
					// 编辑页面，查找详细信息
					var tblContentHtml = $("#matchdetailContentTmple").render(data.object);
					$("#submit-form").html(tblContentHtml);
					$("#startdate").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
					$("#enddate").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
					$("#deadline").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
					$("#deadlineView").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
					$("#favStartTime").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
					$("#favEndTime").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
					//省市区
					initArea(data.object.areaId);
					getDictItem(data.object.dictItemId);
					//竞猜，二次抽奖
					initGuess(data.object.guess,type);
				} else {
					alert("找不到该数据");
				}
			}
		});
	}
	else
	{
		// 添加页面
		var tblContentHtml = $("#matchdetailContentTmple").render("");
		$("#submit-form").html(tblContentHtml);
		var tblGuessContentHtml = $("#guessContentTmple").render("");
		$("#submit-guess-form").html(tblGuessContentHtml);
		$("#startdate").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
		$("#enddate").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
		$("#deadline").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
		$("#deadlineView").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
		$("#favStartTime").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
		$("#favEndTime").datetimepicker({format: 'yyyy-mm-dd HH:mm',language : 'zh-CN',autoclose: true,startDate:time});
		//省市区
		initArea("");
		getDictItem("");
	}
	
	
	// 编辑
	$("#submit-form").on('click','.a-edit',function() {
		if ($(".a-edit").text() == "编辑") {
			// "取消"出现
			$(".a-quxiao").css("visibility","visible");
			// "确定"按钮出现
			$(".a-edit").text("删除");
			// 可勾选的框出现
			$('.left-top-box').show();
		} else {
			if($('.checklogo:checked').closest("li").size()==0){
				alert("请勾选要删除的图片");
				return;
			}
			// 所有被选中的项
			var checkParams = $('input[class="checklogo"]:checked');
			var attr = $("#image").val();
			$.each(checkParams, function() {
				$(this).parents('li').remove();
				attr = attr+ $(this).parents('li').children().first().val()+ ",";
			});
			// 如果所有图片被删除，照相机出现
			if ($('.ul-img li').length <= 4) {
				$('.ul-img li').show();
			}
			// 存入删除的图片路径
			$("#image").attr('value', attr);
			// "取消"消失
			$(".a-quxiao").css("visibility","hidden");
			// "编辑"按钮出现
			$(".a-edit").text("编辑");
			// 可勾选的框消失
			$('.left-top-box').css('display','none');
		}
	});
	
	// 图片的取消按钮
	$("#submit-form").on('click', '.a-quxiao', function() {
		// "取消"消失
		$(".a-quxiao").css("visibility", "hidden");
		// "编辑"按钮出现
		$(".a-edit").text("编辑");
		// 可勾选的框消失
		$('.left-top-box').css('display', 'none');
	});
	// 添加竞猜选项
	$("#submit-guess-form").on('click', '.addGuessOption', function() {
		if($("#guessOption").val()=='')
		{
			alert("选项不能为空");
		}
		else
		{
			addGuessOption(0,$("#guessOption").val());
		}
	});

	// 添加二次抽奖选项
	$("#submit-guess-form").on('click', '.addPrize', function() {
		if($("#prizeLevel").val()=='' || $("#prizeNum").val()=='' || $("#prize").val()=='')
		{
			alert("等级，人数，奖品都不能为空");
		}
		else
		{
			addPrize(0,$("#prizeLevel").val(),$("#prizeNum").val(),$("#prize").val());
		}
	});	
	// 取消
	$("#submit-guess-form").on('click', '.cacel', function() {
		window.location.href = '../shop/matchList.jsp';
	});
	
	// 确定按钮事件
	$("#submit-guess-form").on('click','.sub',function() {
		
		$('#submit-form').isValid(function(result) {
			if ($('.ul-img li').length == 1) {
				alert("您还没有上传图片");
				return;
			}
			//if ($('.left-top-box').css('display') != 'none') {
			//	alert('还有图片未编辑好');
			//	return;
			//}
			if(result==false){
				alert("还有不符合规定的字段填写，请检查！");
				return;
			}
			var params = "";
			if(gid!=null)
			{
				params = params + 'id='+gid+'&';
			}
			params = params + 'businessUserId='+JSON.parse($.cookie("busniessInfo")).id;
			//params = params + '&matchType=2';
			//if(gid==null) params = params + '&logo='+$("#myImageLogoShow").attr("value");
			//if(gid==null) params = params + '&ticketImages='+$("#myImageTicketImagesShow").attr("value");
			//params = params + '&areaName='+$("#provinceName").find("option:selected").text()+$("#cityName").find("option:selected").text()+$("#areaName").find("option:selected").text();
			params = params + '&areaName='+$("#areaName").find("option:selected").val();
			var guessJson="";
			if($("#guessTitle").val()!="" && $("#guessOdds").val()!="" && $("span[name='option']").length>0)
			{
				guessJson+="{";
				if($("#guessId").val()!="")
				{
					guessJson+="\"id\":\""+$("#guessId").val()+"\",";
				}
				guessJson+="\"title\":\""+$("#guessTitle").val()+"\",";
				guessJson+="\"odds\":"+$("#guessOdds").val()+",";
				guessJson+="\"guessOptionList\":[";
				for(var i=0;i<$("span[name='option']").length;i++)
				{
					guessJson += "{";
					if($("span[name='optionid']").eq(i).text()!=0)
					{
						guessJson += "\"id\":"+$("span[name='optionid']").eq(i).text()+",";
					}
					guessJson += "\"option\":\""+$("span[name='option']").eq(i).text()+"\"},";
				}
				guessJson = guessJson.substring(0,guessJson.length-1);
				guessJson+="]";
				if($("span[name='level']").length>0)
				{
					guessJson+=",\"prizeOptionList\":[";
					for(var i=0;i<$("span[name='level']").length;i++)
					{
						guessJson += "{";
						if($("span[name='prizeid']").eq(i).attr('value')!=0)
						{
							guessJson += "\"id\":"+$("span[name='prizeid']").eq(i).attr('value')+",";
						}
						guessJson += "\"level\":\""+$("span[name='level']").eq(i).attr('value')+"\",";
						guessJson += "\"num\":\""+$("span[name='num']").eq(i).attr('value')+"\",";
						guessJson += "\"prize\":\""+$("span[name='prize']").eq(i).attr('value')+"\"},";
					}
					guessJson = guessJson.substring(0,guessJson.length-1);
					guessJson+="]";
				}
				guessJson+="}";
			}
			params = params + '&guessJson='+guessJson;

			if (businessType == "") {
				turl = '../../cms/macth/saveMacth?'+params;
			}
			
			$.ajax({
				url : turl,
				type : "POST",
				data : $("#submit-form").serialize(),
				success : function(data) {
					if (data.code == 1) {
						if(gid!=null){
							alert("已提交后台审核");
							window.location.href = '../shop/matchList.jsp';
						}else{
							alert("已提交后台审核");
							window.location.href = '../shop/matchList.jsp';
						}
					} else {
						alert(data.message);
					}
				}
			});
		});
	});

});

//通过ID获取房间的单条数据
function getDictItem(dictItemId) {
	$.ajax({
		url:root+'cms/dictItem/findDictItemByType',
		dataType:'json',
		type:'POST',
		data:{
			"type":2
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";									
            	$.each(data1.object,function(){
            		if(dictItemId == this.id){
            			_options += "<option value='" + this.id + "' selected>" + this.dictName + "</option>";					       
            		}else{
            			_options += "<option value='" + this.id + "'>" + this.dictName + "</option>";					       
            		}	            	
            	});
            	$('#dictItemId').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}

//初始化地区
function initArea(areaId)
{
	if(areaId==null || areaId=="")
	{
		getProvince("");
		var _options = "<option value=''>请选择</option>";		
		$('#cityName').html(_options);	
		$('#areaName').html(_options);	
	}
	else
	{
		var areaCode = "";
		var cityCode = "";
		var provinceCode = "";
		$.ajax({
			url:root+'cms/area/findAreaById',
			async: false,
			dataType:'json',
			type:'POST',
			data:{
				"modelId":areaId
			},
			success:function(data1){
				if(data1.code == 1){
					areaCode = data1.object.areaCode;
					cityCode = data1.object.cityCode;
				}
				else{
					alert(data1.message);
				}
			}
		});
		$.ajax({
			url:root+'cms/city/findCityByCityCode',
			async: false,
			dataType:'json',
			type:'POST',
			data:{
				"cityCode":cityCode
			},
			success:function(data1){
				if(data1.code == 1){
					provinceCode = data1.object.provinceCode;
				}
				else{
					alert(data1.message);
				}
			}
		});
		getArea(cityCode,areaCode);
		getCity(provinceCode,cityCode);
		getProvince(provinceCode);
	}
	$("#provinceName").change(function() {
		getCity($("#provinceName").val(),"");
	});
	$("#cityName").change(function() {
		getArea($("#cityName").val(),"");
	});
}

//通过ID获取房间的单条数据
function getProvince(provinceCode) {
	$.ajax({
		url:root+'cms/province/findProvince',
		dataType:'json',
		type:'POST',
		data:{
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";									
            	$.each(data1.object,function(){
            		if(provinceCode == this.provinceCode){
            			_options += "<option value='" + this.provinceCode + "' selected>" + this.provinceName + "</option>";					       
            		}else{
            			_options += "<option value='" + this.provinceCode + "'>" + this.provinceName + "</option>";					       
            		}	            	
            	});
            	$('#provinceName').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}

//通过ID获取房间的单条数据
function getCity(provinceCode,cityCode) {
	$.ajax({
		url:root+'cms/city/findCityByProvinceCode',
		dataType:'json',
		type:'POST',
		data:{
			"provinceCode":provinceCode
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";	
            	$.each(data1.object,function(){
            		if(cityCode == this.cityCode){
            			_options += "<option value='" + this.cityCode + "' selected>" + this.cityName + "</option>";					       
            		}else{
            			_options += "<option value='" + this.cityCode + "'>" + this.cityName + "</option>";					       
            		}	            	
            	});
            	$('#cityName').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}

//通过ID获取房间的单条数据
function getArea(cityCode,areaCode) {
	$.ajax({
		url:root+'cms/area/findAreaByCityCode',
		dataType:'json',
		type:'POST',
		data:{
			"cityCode":cityCode
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";									
            	$.each(data1.object,function(){
            		if(areaCode == this.areaCode){
            			_options += "<option value='" + this.areaCode + "' selected>" + this.areaName + "</option>";					       
            		}else{
            			_options += "<option value='" + this.areaCode + "'>" + this.areaName + "</option>";					       
            		}	            	
            	});
            	$('#areaName').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}

//初始化竞猜
function initGuess(guessVo,type)
{
	if(guessVo==null)
	{
		var tblGuessContentHtml = $("#guessContentTmple").render("");
		$("#submit-guess-form").html(tblGuessContentHtml);
		return;
	}
	var tblContentHtml = $("#guessContentTmple").render(guessVo);
	$("#submit-guess-form").html(tblContentHtml);
	var guessOptionList = guessVo.guessOptionList;
	if(guessOptionList!=null && guessOptionList.length>0)
	{
		for(var i=0;i<guessOptionList.length;i++)
		{
			addGuessOption(guessOptionList[i].id,guessOptionList[i].option);
		}
	}
	var prizeOptionList = guessVo.prizeOptionList;
	if(prizeOptionList!=null && prizeOptionList.length>0)
	{
		for(var i=0;i<prizeOptionList.length;i++)
		{
			addPrize(prizeOptionList[i].id,prizeOptionList[i].level,prizeOptionList[i].num,prizeOptionList[i].prize);
		}
	}
	if(type!=null && type=="show")
	{
		$('.required').hide();
		$('.cacel').val("关闭");
	}
}

function addGuessOption(id,option)
{
	var guessOptionTemp = "<div>";
	guessOptionTemp += "<span type='text' name='optionid' class='font-sty txt-span' style='display:none;'>"+id+"</span>";
	guessOptionTemp += "<span type='text' name='option' class='font-sty txt-span'>"+option+"</span>";
	guessOptionTemp += "<input type='button' onclick='$(this).parent().remove();' value='删除'/>";
	guessOptionTemp += "</div>";
	$("#guessOptionList").append(guessOptionTemp);
}
function addPrize(id,level,num,prize)
{
	var guessOptionTemp = "<div>";
	guessOptionTemp += "<span type='text' name='prizeid' class='font-sty txt-span' style='display:none;' value='"+id+"'>"+id+"</span>";
	guessOptionTemp += "<span type='text' name='level' class='font-sty txt-prizeoption' value='"+level+"'>"+level+"等奖，</span>";
	guessOptionTemp += "<span type='text' name='num' class='font-sty txt-prizeoption1' value='"+num+"'>"+num+"人，</span>";
	guessOptionTemp += "<span type='text' name='prize' class='font-sty txt-prizeoption1' value='"+prize+"'>"+prize+"</span>";
	guessOptionTemp += "<input type='button' onclick='$(this).parent().remove();' value='删除'/>";
	guessOptionTemp += "</div>";
	$("#prizeOptionList").append(guessOptionTemp);
}

/** 图片上传处理预留,有图片上传可打开此注释 */
function uploadphoto(modelName) {
	$(document).on('change','#uploadLogoPhotoFile',function() {
		if ($('.left-top-box').css('display') == 'block') {
			alert('编辑状态，不能上传照片');
			return;
		}
	    $.ajaxFileUpload({
			url : '../../cms/' + modelName+ '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadLogoPhotoFile',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$("#myImageLogoShow").attr("src","../.."+ds[1]); 
						$("#myImageLogoShow").attr("value",ds[1]); 
						$("#logo").attr("value",ds[1]);
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
	$(document).on('change','#uploadTicketImagesPhotoFile',function() {
		if ($('.left-top-box').css('display') == 'block') {
			alert('编辑状态，不能上传照片');
			return;
		}
	    $.ajaxFileUpload({
			url : '../../cms/' + modelName+ '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadTicketImagesPhotoFile',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$("#myImageTicketImagesShow").attr("src","../.."+ds[1]); 
						$("#myImageTicketImagesShow").attr("value",ds[1]); 
						$("#ticketImages").attr("value",ds[1]);
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
	$(document).on('change','#uploadPhotoFile',function() {
		if ($('.left-top-box').css('display') == 'block') {
			alert('编辑状态，不能上传照片');
			return;
		}
	    $.ajaxFileUpload({
			url : '../../cms/' + modelName+ '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						if ($('.ul-img li').length == 4) {
							$('.ul-img li:last').before(' <li class="li-img"><label class="left-top-box" style="display:none"><input type="checkbox" class="checklogo"><span>&nbsp;</span></label><input name="mediaPath" style="display:none" value='+ ds[1]+ '></input><img src="../../'+ ds[1]
													+ '" class="img"></li>');
							$('.ul-img li:last')
									.hide();
						} else {
							$('.ul-img li:last').before(' <li class="li-img"><label class="left-top-box" style="display:none"><input type="checkbox" class="checklogo"><span>&nbsp;</span></label><input name="mediaPath" style="display:none" value='+ ds[1]+ '></input><img src="../../'+ ds[1]
													+ '" class="img"></li>');
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
}