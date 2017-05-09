var gid = null;
$(document).ready(function() {
	
	  gid = getUrlParam("id");
	// 添加竞猜选项
	$("#submit_form").on('click', '.addGuessOption', function() {
		if($("#guessOption").val()=='')
		{
			alert("选项不能为空");
		}
		else
		{
			for(var i=0;i<$("span[name='option']").length;i++)
			{
				if($("span[name='option']").eq(i).text()==$("#guessOption").val())
				{
					alert("选项不能重复");
					return;
				}
			}
			addGuessOption(0,$("#guessOption").val());
		}
	});
	
	// 添加二次抽奖选项
	$("#submit_form").on('click', '.addPrize', function() {
		if($("#prizeLevel").val()=='' || $("#prizeNum").val()=='' || $("#prize").val()=='')
		{
			alert("等级，人数，奖品都不能为空");
		}
		else
		{
			for(var i=0;i<$("span[name='level']").length;i++)
			{
				if($("span[name='level']").eq(i).attr('value')==$("#prizeLevel").val())
				{
					alert("等级不能重复");
					return;
				}
			}
			addPrize(0,$("#prizeLevel").val(),$("#prizeNum").val(),$("#prize").val());
		}
	});	
});
//通过ID获取单条数据(赛事编辑页面使用)
function getMatchDataById(url, tmpl, content, ctmpl, ccontent) {
	var tId = getUrlParam("id");
	if (!tId) {
		var contentHtml = $(tmpl).render("");
		$(content).html(contentHtml);
		var ccontentHtml = $(ctmpl).render("");
		$(ccontent).html(ccontentHtml);
		getDictItem("");
		initArea("");
		initDate();
		return;
	}
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : {
			modelId : tId
		},
		success : function(data) {
			
			console.log(data);
			if (data.code == 1) {
				var contentHtml = $(tmpl).render(data.object);
				$(content).html(contentHtml);
				getDictItem(data.object.dictItemId);
				initArea(data.object.areaId);
				initDate();
				//竞猜，二次抽奖
				initGuess(data.object.guess, ctmpl, ccontent);
			} else {
				var contentHtml = $(tmpl).render("");
				$(content).html(contentHtml);
				var ccontentHtml = $(ctmpl).render("");
				$(ccontent).html(ccontentHtml);
				getDictItem("");
				initArea("");
				initDate();
			}
		}
	});
}


function initDate()
{
	//创建date
	var d=new Date();
	//年
	var year=d.getFullYear();
	//月
	var month=d.getMonth()+1;
	//日
	var day=d.getDate();
	var time=year+"-"+month+"-"+day;
 	$("#startdate").datetimepicker({format: 'yyyy-mm-dd HH:ii',language : 'zh-CN',autoclose: true,startDate:time}).on('changeDate',function(ev){
 		var startdate=$("#startdate").val();
		$("#enddate").datetimepicker('setStartDate',startdate);
		$("#deadlineView").datetimepicker('setEndDate',startdate);
		$("#deadline").datetimepicker('setEndDate',startdate);
		$("#startdate").datetimepicker('hide');
	});
	$("#enddate").datetimepicker({format: 'yyyy-mm-dd HH:ii',language : 'zh-CN',autoclose: true,startDate:time}).on('changeDate',function(ev){
		var enddate=$("#enddate").val();
		$("#startdate").datetimepicker('setEndDate',enddate);
		$("#enddate").datetimepicker('hide');
	});
	
	
 	$("#favStartTime").datetimepicker({format: 'yyyy-mm-dd HH:ii',language : 'zh-CN',autoclose: true,startDate:time}).on('changeDate',function(ev){
		var favStartTime=$("#favStartTime").val();
		$("#favEndTime").datetimepicker('setStartDate',favStartTime);
		$("#favStartTime").datetimepicker('hide');
	});
	$("#favEndTime").datetimepicker({format: 'yyyy-mm-dd HH:ii',language : 'zh-CN',autoclose: true,startDate:time}).on('changeDate',function(ev){
		var favEndTime=$("#favEndTime").val();
		$("#favStartTime").datetimepicker('setEndDate',favEndTime);
		//$("#favEndTime").datetimepicker('setStartDate',favEndTime);
		$("#favEndTime").datetimepicker('hide');
	});
	
 	$("#deadlineView").datetimepicker({format: 'yyyy-mm-dd HH:ii',language : 'zh-CN',autoclose: true,startDate:time}).on('changeDate',function(ev){
		var deadlineView=$("#deadlineView").val();
		$("#favStartTime").datetimepicker('setEndDate',deadlineView);
		$("#favEndTime").datetimepicker('setEndDate',deadlineView);
		//$("#deadlineView").datetimepicker('setStartDate',deadlineView);
		$("#deadlineView").datetimepicker('hide');
	});
 	
 	
 	$("#deadline").datetimepicker({format: 'yyyy-mm-dd HH:ii',language : 'zh-CN',autoclose: true,startDate:time}).on('changeDate',function(ev){
		//var deadlineView=$("#deadlineView").val();
		//$("#deadlineView").datetimepicker('setStartDate',deadlineView);
		$("#deadline").datetimepicker('hide');
	});
 	
}

//保存房间
function saveMatch(url, backUrl) {
	 
	var params = "";
	 
	if(gid!=null)
	{
		params = params + 'id='+gid+'&';
	}
	params = params + 'businessUserId=0';
	//params = params + '&matchType=2';
	//params = params + '&areaName='+$("#provinceId").find("option:selected").text()+$("#cityId").find("option:selected").text()+$("#areaId").find("option:selected").text();
	params = params + '&areaName='+$("#areaName").find("option:selected").val();
	var guessJson="";
	 
	if($("#guessTitle").val()!="" && $("#guessOdds").val()!="" && $("span[name='option']").length>0)
	{
		guessJson+="{";
		if($("#guessId").val()!="")
		{
			guessJson+="\"id\":\""+$("#guessId").val()+"\",";
		}
		guessJson+="\"title\":\""+encodeURI(encodeURI($("#guessTitle").val()))+"\",";
		guessJson+="\"odds\":"+$("#guessOdds").val()+",";
		guessJson+="\"guessOptionList\":[";
		for(var i=0;i<$("span[name='option']").length;i++)
		{
			guessJson += "{";
			if($("span[name='optionid']").eq(i).text()!=0)
			{
				guessJson += "\"id\":"+$("span[name='optionid']").eq(i).text()+",";
			}
			guessJson += "\"option\":\""+encodeURI(encodeURI($("span[name='option']").eq(i).text()))+"\"},";
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
				guessJson += "\"prize\":\""+encodeURI(encodeURI($("span[name='prize']").eq(i).attr('value')))+"\"},";
			}
			guessJson = guessJson.substring(0,guessJson.length-1);
			guessJson+="]";
		}
		guessJson+="}";
	}
	params = params + '&guessJson='+guessJson;
 
	$('#submit_form').isValid(function(result) {
		if (!result) {
			alert("还有不符合规定的字段填写，请检查！");
			return;
		} 
		if($("#startdate").val().length<1){
			alert("赛事开始时间不能为空！");
			return;
		}
		if($("#enddate").val().length<1){
			alert("赛事开始时间不能为空！");
			return;
		}
		if($("#deadline").val().length<1){
			alert("报名截止日期不能为空！");
			return;
		}
		if($("#deadlineView").val().length<1){
			alert("报名截止日期不能为空！");
			return;
		}
		
		$.ajax({
			url : url+"?"+params,
			type : "POST",
			data : $("#submit_form").serialize(),
			success : function(data) {
				if (data.code == 1) {
					backAction(backUrl);
				} else {
					alert(data.message);
				}
			}
		});
	});
}

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
		$('#cityId').html(_options);	
		$('#areaId').html(_options);	
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
	$("#provinceId").change(function() {
		getCity($("#provinceId").val(),"");
	});
	$("#cityId").change(function() {
		getArea($("#cityId").val(),"");
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
          	$('#provinceId').html(_options);					            	
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
          	$('#cityId').html(_options);					            	
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
          	$('#areaId').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}

//初始化竞猜
function initGuess(guessVo,ctmpl,ccontent)
{
	if(guessVo==null)
	{
		var ccontentHtml = $(ctmpl).render("");
		$(ccontent).html(ccontentHtml);
		return;
	}
	var ccontentHtml = $(ctmpl).render(guessVo);
	$(ccontent).html(ccontentHtml);
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
						var num = $('.divmedia img').length;
						if ($('.divmedia img').length == 4) {
							$('.divmedia:last').before('<div class="upload_div divmedia" style=" display:inline-block; margin-right: 15px;"><input name="mediaPath" style="display:none"  id="mediaPath'+num+'" value='+ ds[1]+ '></input><img src="../../'+ ds[1] + '" class="img" id="img'+num+'"><input type="file" id="uploadPhotoFile'+num+'" name="photoFile" class="upload_file" ></div>');
							$('.divmedia:last').hide();
						} else {
							$('.divmedia:last').before('<div class="upload_div divmedia" style=" display:inline-block; margin-right: 15px;"><input name="mediaPath" style="display:none"  id="mediaPath'+num+'" value='+ ds[1]+ '></input><img src="../../'+ ds[1] + '" class="img" id="img'+num+'"><input type="file" id="uploadPhotoFile'+num+'" name="photoFile" class="upload_file" ></div>');
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
	$(document).on('change','#uploadPhotoFile1',function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName+ '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile1',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$('#mediaPath1').attr('value',ds[1]);
						$('#img1').attr('src',"../../"+ds[1]);
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
	$(document).on('change','#uploadPhotoFile2',function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName+ '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile2',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$('#mediaPath2').attr('value',ds[1]);
						$('#img2').attr('src',"../../"+ds[1]);
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
	$(document).on('change','#uploadPhotoFile3',function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName+ '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile3',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$('#mediaPath3').attr('value',ds[1]);
						$('#img3').attr('src',"../../"+ds[1]);
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
	$(document).on('change','#uploadPhotoFile4',function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName+ '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile4',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$('#mediaPath4').attr('value',ds[1]);
						$('#img4').attr('src',"../../"+ds[1]);
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
