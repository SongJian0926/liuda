$(document).ready(function(){
	$('#auditupdate').click(function(){
		if(!$('#audit_applyMemo').val()){
			alert("请输入审核意见！");
			return;
		}
		if($('#audit_applyMemo').val().length>100){
			alert("审核意见最多输入100个字！");
			return;
		}
		$.ajax({
			url: root + 'cms/guide/audit',
			type: 'post',
			data:{
				id:$('#audit_id').val(),
				applyStatus:$('#audit_applyStatus').val(),
				applyMemo:$('#audit_applyMemo').val()
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
					$('#auditModal').modal('hide');
				}else{
					alert(data.message);
				}
			}
        });
	});
	//拉取景点列表数据
	$.ajax({
		url: root + 'api/tourist/findTourists',
		type: 'post',
		data:{
			nextPage:0,
			pageSize:1000000,
			lng:0,
			lat:0
		},
		dataType: 'json',
		success: function(data){
			if(data.code==1){
				var _html = "<option value=''>全部</option>";
				$.each(data.object.content,function(){
					_html += "<option value='" + this.id + "'>" + this.touristName + "</option>";
				});
				$('#bandtourist').html(_html);
			}else{
				alert(data.message);
			}
		}
    });
	//确定绑定景点
	$('#bandtouristupdate').click(function(){
		if(!$('#bandtourist').val()){
			alert("请选择景点！");
			return;
		}
		$.ajax({
			url: root + 'cms/guide/bandTourist',
			type: 'post',
			data:{
				id:$('#band_id').val(),
				objectType:1,	//0:酒店  1：景点   目前业务只绑定景点
				objectId:$('#bandtourist').val()
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
					$('#bandtouristModal').modal('hide');
				}else{
					alert(data.message);
				}
			}
        });
	});
});
//发布
function toPulish(id){
	if(confirm("确认要发布吗？发布后将显示在app")){
		$.ajax({
			url: operateUrl,
			type: 'post',
			data:{
				modelIds:id,
				status:2
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
				}else{
					alert(data.message);
				}
			}
        });
	}
}
//精品审核
function audit(id){
	$('#audit_id').val(id);
	$('#auditModal').modal();
}
//绑定景点
function bandtourist(id){
	$('#bandtourist').val('');
	$('#band_id').val(id);
	$('#bandtouristModal').modal();
}
//查看详情
function lookDetail(id){
	$.ajax({
		url: root + 'cms/guide/findDetail',
		type: 'post',
		data:{
			modelId:id
		},
		dataType: 'json',
		success: function(data){
			console.log(data);
			$('#mycontent').find('tr').removeClass('warning');
			if(data.code==1){
				var tblContentHtml = $("#lookDetailTmple").render(data.object);
            	$("#lookDetail").html(tblContentHtml);
            	$('#lookDetailModal').modal();
            
			}else{
				alert(data.message);
			}
		},
		error: function(){
			alert('服务器异常！');
		}
    });
}
//跳转
function turnappend(id,title,autor,appendId){
	var _from = location.href + '?nextPage=' + $('#displayPage').find('.active').find('a').html();
	
	window.location.href = 'guideContentList.jsp?id=' + id + '&title=' + escape(title) + '&autor=' + escape(autor) + '&from=' + _from + '&appendId=' + appendId+'&nextPage1=' + $('#displayPage').find('.active').find('a').html();
}