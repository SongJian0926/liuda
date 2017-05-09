/** 图片上传处理预留,有图片上传可打开此注释 */
function upload(modelName) {
	$(document).on('change', '#uploadPhotoFile', function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$("#imgUrl").val(ds[1]);
						$("#myImageShow").attr('src', "../../" + ds[1]);
						$("#myImageShow").attr('width', '100px');
						$("#myImageShow").attr('height', '100px');
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
	$(document).on('change', '#uploadPhotoFile1', function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile1',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					imgUrls=ds[1]+','+imgUrls;
					if (ds[0] == 1) {
						$("#imgUrl1").val(ds[1]);
						$("#myImageShow1").attr('src', "../../" + ds[1]);
						$("#myImageShow1").attr('width', '100px');
						$("#myImageShow1").attr('height', '100px');
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
	$(document).on('change', '#uploadPhotoFile2', function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile2',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					imgUrls=ds[1]+','+imgUrls;
					if (ds[0] == 1) {
						$("#imgUrl2").val(ds[1]);
						$("#myImageShow2").attr('src', "../../" + ds[1]);
						$("#myImageShow2").attr('width', '100px');
						$("#myImageShow2").attr('height', '100px');
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
	$(document).on('change', '#uploadPhotoFile3', function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile3',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					imgUrls=ds[1]+','+imgUrls;
					if (ds[0] == 1) {
						$("#imgUrl3").val(ds[1]);
						$("#myImageShow3").attr('src', "../../" + ds[1]);
						$("#myImageShow3").attr('width', '100px');
						$("#myImageShow3").attr('height', '100px');
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
	$(document).on('change', '#uploadPhotoFile4', function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile4',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					imgUrls=ds[1]+','+imgUrls;
					alert(imgUrls);
					if (ds[0] == 1) {
						$("#imgUrl4").val(ds[1]);
						$("#myImageShow4").attr('src', "../../" + ds[1]);
						$("#myImageShow4").attr('width', '100px');
						$("#myImageShow4").attr('height', '100px');
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
// 返回函数
function backAction(backurl) {
	if (getUrlParam('from')) {
		location.href = getUrlParam('from');
	} else {
		window.location.href = backurl;
	}
}
// 通过ID获取单条数据
function getDataById(url, tmpl, content) {
	var tId = getUrlParam("id");
	if (!tId) {
		alert('id参数不能为空');
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
			if (data.code == 1) {
				var contentHtml = $(tmpl).render(data.object, {
					mydata : data
				});
				$(content).html(contentHtml);
			} else {
				var contentHtml = $(tmpl).render("");
				$(content).html(contentHtml);
			}
		}
	});
}

//通过ID获取单条数据(攻略编辑页面使用)
function getDataById1(url, tmpl, content) {
	var tId = getUrlParam("id");
	if (!tId) {
		alert('id参数不能为空');
		return;
	}
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : {
			id : tId
		},
		success : function(data) {
			console.log(data);
			if (data.code == 1) {
				var contentHtml = $(tmpl).render(data.object.content);
				$(content).html(contentHtml);
			} else {
				var contentHtml = $(tmpl).render("");
				$(content).html(contentHtml);
			}
		}
	});
}
// 保存数据
function saveData(url, backUrl) {
	$('#submit_form').isValid(function(result) {
		if (!result) {
			alert("还有不符合规定的字段填写，请检查！");
			return;
		}
		$("#saveOrupdate").addClass('disable_btn');
		$.ajax({
			url : url,
			type : "POST",
			data : $("#submit_form").serialize(),
			success : function(data) {
				if (data.code == 1) {
					backAction(backUrl);
				} else {
					$("#saveOrupdate").removeClass('disable_btn');
					alert(data.message);
				}
			}
		});
	});
}
//保存房间
function saveRoom(url, backUrl) {
	$('#submit_form').isValid(function(result) {
		if (!result) {
			alert("还有不符合规定的字段填写，请检查！");
			return;
		} 
		if($("#hotelName").val()==""){
			alert("没有选择酒店");
			return;
		}
		$.ajax({
			url : url,
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
function getRoomDataById(url, tmpl, content) {
	var tId = getUrlParam("id");
	if (!tId) {
		alert('id参数不能为空');
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
			if (data.code == 1) {
				var contentHtml = $(tmpl).render(data.object, {mydata : data});
				$(content).html(contentHtml);
			} else {
				var contentHtml = $(tmpl).render("");
				$(content).html(contentHtml);
			}
			//显示酒店列表
			$.ajax({
			url:root+'cms/hotel/findHotelNEStatusPage',
			dataType:'json',
			type:'POST',
			data:{
				
			},
			success:function(data1){
				if(data1.code == 1){
					var _options = "<option value=''>请选择</option>";									
	            	$.each(data1.object.content,function(){
	            		if(data.object.hotelId == this.id){
	            			_options += "<option value='" + this.id + "' selected>" + this.hotelName + "</option>";					       
	            		}else{
	            			_options += "<option value='" + this.id + "'>" + this.hotelName + "</option>";					       
	            		}	            	
	            	});
	            	$('#hotelName').html(_options);					            	
				}
				else{
					alert(data1.message);
				}
			}
		});
		}
	});
}
$(document).ready(function() {
	// 清除时间
	$("#submit_form").on('click', '.datetime_clear', function() {
		$(this).next().val('');
	});
});