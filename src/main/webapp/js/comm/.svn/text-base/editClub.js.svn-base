var gid = null;
$(document).ready(function() {
	  gid = getUrlParam("id");
});
//通过ID获取单条数据(赛事编辑页面使用)
function getClubDataById(url, tmpl, content) {
	var tId = getUrlParam("id");
	if (!tId) {
		var contentHtml = $(tmpl).render("");
		$(content).html(contentHtml);
		getType("");
		getLevel("");
		getInterest("");
		initArea("");
		initUser("");
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
				getType(data.object.type);
				getLevel(data.object.level);
				getInterest(data.object.interest);
				initArea(data.object.areaId);
				initUser(data.object.userId);
			} else {
				var contentHtml = $(tmpl).render("");
				$(content).html(contentHtml);
				getType("");
				getLevel("");
				getInterest("");
				initArea("");
				initUser("");
			}
		}
	});
}

//保存房间
function saveClub(url, backUrl) {
	 
	var params = "";
	if(gid!=null)
	{
		params = params + 'id='+gid+'&';
	}
	params = params + 'areaName='+$("#areaName").find("option:selected").val();
	$('#submit_form').isValid(function(result) {
		if (!result) {
			alert("还有不符合规定的字段填写，请检查！");
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
function initUser(objid) {
	$.ajax({
		url:root+'cms/user/findUserList',
		dataType:'json',
		type:'POST',
		data:{
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";									
				$.each(data1.object,function(){
	  		if(objid == this.id){
	  			_options += "<option value='" + this.id + "' selected>" + this.userName + "-" + this.mobile + "</option>";					       
	  		}else{
	  			_options += "<option value='" + this.id + "'>" + this.userName + "-" + this.mobile + "</option>";					       
	  		}	            	
	});
  	$('#userId').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}


//通过ID获取房间的单条数据
function getType(objid) {
	$.ajax({
		url:root+'cms/dictItem/findDictItemByType',
		dataType:'json',
		type:'POST',
		data:{
			"type":3
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";									
      	$.each(data1.object,function(){
      		if(objid == this.id){
      			_options += "<option value='" + this.id + "' selected>" + this.dictName + "</option>";					       
      		}else{
      			_options += "<option value='" + this.id + "'>" + this.dictName + "</option>";					       
      		}	            	
      	});
      	$('#type').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}

//通过ID获取房间的单条数据
function getLevel(objid) {
	$.ajax({
		url:root+'cms/dictItem/findDictItemByType',
		dataType:'json',
		type:'POST',
		data:{
			"type":4
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";									
    	$.each(data1.object,function(){
    		if(objid == this.id){
    			_options += "<option value='" + this.id + "' selected>" + this.dictName + "</option>";					       
    		}else{
    			_options += "<option value='" + this.id + "'>" + this.dictName + "</option>";					       
    		}	            	
    	});
    	$('#level').html(_options);					            	
			}
			else{
				alert(data1.message);
			}
		}
	});
}

//通过ID获取房间的单条数据
function getInterest(objid) {
	$.ajax({
		url:root+'cms/dictItem/findDictItemByType',
		dataType:'json',
		type:'POST',
		data:{
			"type":5
		},
		success:function(data1){
			if(data1.code == 1){
				var _options = "<option value=''>请选择</option>";									
          	$.each(data1.object,function(){
          		if(objid == this.id){
          			_options += "<option value='" + this.id + "' selected>" + this.dictName + "</option>";					       
          		}else{
          			_options += "<option value='" + this.id + "'>" + this.dictName + "</option>";					       
          		}	            	
          	});
          	$('#interest').html(_options);					            	
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
