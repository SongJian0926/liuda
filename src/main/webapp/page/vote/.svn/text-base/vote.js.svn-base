$.views.converters({
	"subString":function(str){
		if(str==null || str==""){
			return "";
		}
    	if(str.length>15){
    		return str.substring(0,15)+"...";
    	}else{
    		return str;
    	}
	},
}); 
//初始化分页参数
var nextPage = 0;
var pageSize = 10;
function dipscontent1(jsonFilter,urls,search){
	if(search==1){
		nextPage=0;
	}
	/*if(getUrlParam('nextPage')){
		nextPage = getUrlParam('nextPage') - 1;
	}
	if($("#_click_page").val() != undefined && $("#_click_page").val()){
		nextPage = $("#_click_page").val();
	}*/
	if(typeof(nextPage) == 'undefined' || nextPage==""){
		nextPage = 0;
		$("#_click_page").val(nextPage);
	}
	if(getUrlParam('pageSize')){
		pageSize = getUrlParam('pageSize');
	}
	if($("#selectPageSize").val() && $("#selectPageSize").val() != undefined){
		pageSize = $("#selectPageSize").val();
	}
	if(typeof(pageSize) == 'undefined' || pageSize==""){
		pageSize = 10;
		$("#selectPageSize").val(pageSize);
	}
	$.ajax({
		url:urls,
		dataType:'json',
		type:'POST',
		data:{
			jsonFilter: jsonFilter,
			nextPage: nextPage,
			pageSize:pageSize
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
            	var tblContentHtml = $("#tableContentTmple").render(data.object.content);
            	$("#mycontent").html(tblContentHtml);
            	//加载分页器
            	loadTmpl.renderExternalTemplate("page", "#displayPage", data.object , 1);
            	 //活动页面加载时间日历
            	$("#startTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
            		var starttime=$("#startTime").val();
            		$("#endTime").datetimepicker('setStartDate',starttime);
            		$("#startTime").datetimepicker('hide');
            	});
				$("#endTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
            		var endtime=$("#endTime").val();
            		$("#startTime").datetimepicker('setEndDate',endtime);
            		$("#endTime").datetimepicker('hide');
            	});
			}
			else{
				alert(data.message);
			}
		}
	});
}
/** 加载内容 */
function dipscontent(jsonFilter,urls){
	if(getUrlParam('nextPage')){
		nextPage = getUrlParam('nextPage') - 1;
	}
	if($("#_click_page").val() != undefined && $("#_click_page").val()){
		nextPage = $("#_click_page").val();
	}
	if(typeof(nextPage) == 'undefined' || nextPage==""){
		nextPage = 0;
		$("#_click_page").val(nextPage);
	}
	if(getUrlParam('pageSize')){
		pageSize = getUrlParam('pageSize');
	}
	if($("#selectPageSize").val() && $("#selectPageSize").val() != undefined){
		pageSize = $("#selectPageSize").val();
	}
	if(typeof(pageSize) == 'undefined' || pageSize==""){
		pageSize = 10;
		$("#selectPageSize").val(pageSize);
	}
	$.ajax({
		url:urls,
		dataType:'json',
		type:'POST',
		data:{
			jsonFilter: jsonFilter,
			nextPage: nextPage,
			pageSize:pageSize
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
            	var tblContentHtml = $("#tableContentTmple").render(data.object.content);
            	$("#mycontent").html(tblContentHtml);
            	//加载分页器
            	loadTmpl.renderExternalTemplate("page", "#displayPage", data.object , 1);
            	 //活动页面加载时间日历
            	$("#startTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
            		var starttime=$("#startTime").val();
            		$("#endTime").datetimepicker('setStartDate',starttime);
            		$("#startTime").datetimepicker('hide');
            	});
				$("#endTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
            		var endtime=$("#endTime").val();
            		$("#startTime").datetimepicker('setEndDate',endtime);
            		$("#endTime").datetimepicker('hide');
            	});
			}
			else{
				alert(data.message);
			}
		}
	});
}
/** 删除事件 */
function del(delurl){
	var checkError = false;
	var delArray = [];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	if(check){
			var _id = $(this).attr("ckId");
			delArray.push(_id);
        }
    });
    if(delArray.length == 0){
		alert("请选择要删除的项"); return;
    }
    if(confirm("你确定要删除选中项吗？")){
    	var url = delurl + delArray.join(",");
    	$.ajax({
			url: url,
			type: 'post',
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl)
					$("#_selectAll").attr("checked",false);
				}else{
					alert(data.msg);
				}
			}
        });
    }
}
/** 启用事件 */
function unlock(unlockurl){
	var checkError = false;
	var delArray = [];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	if(check){
			var _id = $(this).attr("ckId");
			delArray.push(_id);
        }
    });
    if(delArray.length == 0){
		alert("请选择要启用的项"); return;
    }
    if(confirm("你确定要启用选中项吗？")){
    	$.ajax({
			url: unlockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				status:2
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
					$("#_selectAll").attr("checked",false);
				}else{
					alert(data.msg);
				}
			}
        });
    }
}
//查看详细
function findDetail(url){
	   var updateArray = [];
	    var id;
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				id =$(this).attr("ckId");
				updateArray.push(id);
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
		$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		data:{
			modelId:id
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
				var tblContentHtml = $("#commentContentTmple").render(data.object);
	        	$(".detail").html(tblContentHtml);
	        }
		}
	   });
	   $("#myModals").modal();
}
//回车触发查询按钮
$(function(){
	document.onkeydown = function(e){
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	dipscontent(getfilter(),contentUrl);
	     }
	}
	//表格列拖动以及行高亮选中
	$("#example2").resizableColumns({});
	$("#example2").tableGrid({
		checkAllId: "_selectAll",	//全选框的ID属性
		singleCheckboxClass: "ckSelect",
		selectRowClass: "warning",
		paging: "displayPage",
		pageAjax: function(){
			dipscontent(getfilter(),contentUrl);
		}
	});
	//点击查询按钮
	$("#search").click(function(){
		dipscontent1(getfilter(),contentUrl,1);
	});
	//发布按钮
	$("#openSome").click(function() {
		var updateStatus = [];
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _status =$(this).attr("ckStatus");
				updateStatus.push(_status);
	        }
	    });
		for(var i=0;i<updateStatus.length;i++){
			if(updateStatus[i] != 1){
				alert("只能发布未发布的项"); return;
			}
		}
		unlock(operateUrl);
	});
	//点击修改按钮
	$("#updateSome").click(function(){
		var updateArray = [];
		var updateStatus = [];
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _id =$(this).attr("ckId");
				var _status =$(this).attr("ckStatus");
				updateArray.push(_id);
				updateStatus.push(_status);
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要编辑的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能编辑一项"); return;
		}
		if(updateStatus[0] != 1){
			alert("只能编辑未发布的项"); return;
		}
		location.href = editUrl + updateArray[0];
	});
	//点击新增按钮
	$("#addSome").click(function(){
		location.href = editUrl + 0;
	});
	//详细按钮
	$(document).on('click','#detail',function(){
		findDetail(detailurl);
	});
	//点击删除按钮
	$("#deleteSome").click(function() {
         del(operateUrl + '?modelIds=');
	});
});
$(document).ready(function() {
	//清除时间
	$(".datetime_clear").click(function(){
		$(this).next().val('');
		if($(this).next().attr('id')=='startTime'){
			var starttime=$("#startTime").val();
			$("#endTime").datetimepicker('setStartDate',starttime);
			$("#startTime").datetimepicker('hide');
		}
		if($(this).next().attr('id')=='endTime'){
			var endtime=$("#endTime").val();
			$("#startTime").datetimepicker('setEndDate',endtime);
			$("#endTime").datetimepicker('hide');
		}
	});
});