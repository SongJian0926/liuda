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
$.views.converters("getLineStr", function(str,len) {
	if(str==undefined || str==null){
		return "";
	}
	if(str.length>len){
		var strs = '';
		for(var i=0;i<str.length;i+=len){
			strs += str.substr(i,len) + '<br>';
		}
		return strs;
	}
	return str;
});
//初始化分页参数
var nextPage = 0;
var pageSize = 10;

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

/**打款*/
function sendMoney(lockurl){
	var checkError = false;
	var delArray = [];
	var checkArray=[];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	var checked=$(this).parents('tr').find('td').last().html();
    	if(checked=="已提交"){
	    	if(check){
				var _id = $(this).attr("ckId");
				delArray.push(_id);
				checkArray.push(checked);
	        }
    	}
    });
    if(delArray.length == 0){
		alert("请选择要确认打款的项"); return;
    }
    if($.inArray("已提交",checkArray)==-1&&$.inArray("打款失败",checkArray)==-1){
		alert("请选择已提交或者打款失败的项");
		return;
	}
    if(confirm("你确定要确认打款选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				cashStatus:2
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
/**打款失败*/
function sendFail(lockurl){
	var checkError = false;
	var delArray = [];
	var checkArray=[];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	var checked=$(this).parents('tr').find('td').last().html().trim();
    	if(checked=="打款中"){
	    	if(check){
				var _id = $(this).attr("ckId");
				delArray.push(_id);
				checkArray.push(checked);
	        }
    	}
    });
    if(delArray.length == 0){
		alert("请选择要确认打款失败的项"); return;
    }
    if($.inArray("打款中",checkArray)==-1){
		alert("请选择打款中的项");
		return;
	}
    if(confirm("你确定要确认打款失败选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				cashStatus:3
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
/**打款成功*/
function sendSuccess(lockurl){
	var checkError = false;
	var delArray = [];
	var checkArray=[];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	var checked=$(this).parents('tr').find('td').last().html().trim();
    	if(checked=="打款中"||checked=="打款失败"){
	    	if(check){
				var _id = $(this).attr("ckId");
				delArray.push(_id);
				checkArray.push(checked);
	        }
    	}
    });
    if(delArray.length == 0){
		alert("请选择要确认打款成功的项"); return;
    }
    if($.inArray("打款中",checkArray)==-1&&$.inArray("打款失败",checkArray)==-1){
		alert("请选择打款中或者打款失败的项");
		return;
	}
    if(confirm("你确定要确认打款成功选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				cashStatus:1
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
/** 锁定事件 */
function lock(lockurl){
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
		alert("请选择要锁定的项"); return;
    }
    if(confirm("你确定要锁定选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				status:-1
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
//商家管理下架事件
function lock1(lockurl){
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
		alert("请选择要锁定的项"); return;
    }
    if(confirm("你确定要锁定选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				status:1
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
				status:1
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
/** 商品管理上架事件 */
function unlock1(unlockurl){
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

//回车触发查询按钮
$(function(){
	document.onkeydown = function(e){
	    var ev = document.all ? window.event : e;
	    
	    if(ev.keyCode==13) {
	    	
	    	dipscontent1(getfilter(),contentUrl,1);
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
	
	//点击新增按钮
	$("#addSome").click(function(){
		location.href = editUrl + 0;
	});
			
	//点击修改按钮
	$("#updateSome").click(function(){
		var updateArray = [];
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _id =$(this).attr("ckId");
				updateArray.push(_id);
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要编辑的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能编辑一项"); return;
		}
		var from = window.location.href.split("?")[0] + "?nextPage=" + (parseInt($("#_click_page").val())+1) + "&pageSize=" + $("#selectPageSize").val();
		location.href = editUrl + updateArray[0] + '&from=' + from;
	});
			
	//点击删除按钮
	$("#deleteSome").click(function() {
         del(operateUrl + '?modelIds=');
	});
	//点击banner管理的删除按钮
	$("#addbannerSome").click(function() {
		 var num=$("#mycontent").children().last().children().first().children().attr("data-id");
		 if(num==5){
			 alert("bannner图最多五张");
			 return;
		 }
		 location.href = editUrl + 0;
	});
	//点击锁定按钮
	$("#lockSome").click(function() {
		lock(operateUrl);
	});
	//商品管理点击下架按钮
	$("#lockSome1").click(function() {
		lock1(operateUrl);
	});
	
	//点击启用按钮
	$("#openSome").click(function() {
		unlock(operateUrl);
	});
	//商家管理点击上架按钮
	$("#openSome1").click(function() {
		unlock1(operateUrl);
	});
	
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
	
	 //点击发布按钮
	$("#announce").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
           if(a=="已发布"){
              return; 	                           
           }
        });
        check(url);		  			      
	});
	//点击打款
	$("#sendMoney").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
           if(a=="打款中"){
              return; 	                           
           }
        });
        sendMoney(root+"cms/cashRecord/sendMoney");		  			      
	});
	//点击打款失败
	$("#sendFail").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
           if(a=="提现成功"||a=="已提交"){
              return; 	                           
           }
        });
        sendFail(root+"cms/cashRecord/sendMoney");		  			      
	});
	
	//点击打款成功
	$("#sendSuccess").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
           if(a=="提现成功"){
              return; 	                           
           }
        });
        sendSuccess(root+"cms/cashRecord/sendMoney");		  			      
	});
	//商家商品的审批、上架、下架
	$("#checkSome,#upSome,#downSome").click(function() {
		//获得点击按钮的值
		var btnVar=$(this).val();
        var checkError = false;
        //房间
    	var roomArray = [];
    	//门票
    	var ticketsArray = [];
    	//提示语
    	var speak;
    	//商品状态
    	var status=[];
    	$(".ckSelect").each(function(){
        	var check = $(this).is(':checked');
        	if(check){ 
        		//获取商户类型
        		var type= $(this).attr("ckType");
        		var _id = $(this).attr("ckId");
        		var stat=$(this).parents('tr').find('td').last().html();
        		//将所有的状态放进数组
        		status.push(stat);
        		//如果是房间，放进房间数组，如果是门票，放进门票数组
        		if(type==0){
        			roomArray.push(_id);
        		}else{
        			ticketsArray.push(_id);
        		}
            }
        });
        if(roomArray.length == 0 && ticketsArray.length==0){
    		alert("请选择项"); return;
        }
        if(btnVar=="审批"){
        	if($.inArray("上架",status)!=-1 || $.inArray("下架",status)!=-1){
        		alert("请选择待审批的项！");
            	return;
        	}
        }else if(btnVar=="上架"){
        	if($.inArray("待审批",status)!=-1 || $.inArray("上架",status)!=-1){
	        	alert("请选择已下架的项！");
	        	return;
        	}
        }else if(btnVar=="下架"){
        	if($.inArray("下架",status)!=-1|| $.inArray("待审批",status)!=-1){
	        	alert("请选择已上架的项！");
	        	return;
        	}
        }
        if(btnVar=="审批"){
        	speak="你确定要审批吗？";
        }else if(btnVar=="上架"){
        	speak="你确定要上架吗？";
        }else if(btnVar=="下架"){
        	speak="你确定要下架吗？";
        }
        if(confirm(speak)){
    	$.ajax({
			url: '../../cms/room/upAnddownback',
			type: 'post',
			data:{
				roommodelIds:roomArray.join(","),
				ticketsmodelIds:ticketsArray.join(","),
				btnVar:btnVar
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
	});
	 //点击撤销发布按钮
	$("#revoke").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
           if(a=="未发布"){
              return; 	  
           }
        });
        uncheck(url);		  			      
	});
	
	//分页点击事件
	$('#example2_paginate').find('a').click(function(){
		dipscontent(getfilter(),contentUrl);
	});
	//详细按钮
	$(document).on('click','#detail',function(){
	    var updateArray = [];
	    var id;
	    //商家类型(房间或者门票)
	    var type;
	    //请求路径
	    var url;
	    //商家名称
	    var name;
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				id =$(this).attr("ckId");
				type=$(this).attr("ckType");
				updateArray.push(id);
				name=$(this).parents('tr').find('td:eq(2)').text();
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
		if(type==0){
			url='../../cms/room/findById';
		}else{
			url= '../../cms/tickets/findById';
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
					if(type==0){
						var tblContentHtml = $("#hotelContentTmple").render(data.object);
		            	$(".shopdetail").html(tblContentHtml);
		            	$('.businessName').html(name);
				    }else{
				    	var tblContentHtml = $("#ticketsContentTmple").render(data.object);
		            	$(".shopdetail").html(tblContentHtml);
		            	$('.businessName').html(name);
				    }
			    }
			}
		   });
	$("#myModals").modal();
	});
	
	
	//反馈意见详细按钮
	$(document).on('click','#feedBankDetail',function(){
		
	    var updateArray = [];
	    var id;
	    var time;
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				id =$(this).attr("ckId");
				updateArray.push(id);
				time=$(this).parents('tr').find('td').last().html();
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
		$.ajax({
		url:root + "cms/feedBack/findFeedBackById",
		type:'post',
		dataType:'json',
		data:{
			modelId:id,
			createTime:time
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
			   $("#name").text(data.object.username);
			   $("#contents").text(data.object.content);
			   $("#time").text(data.object.createTime);
			}else{
			}
		}
	   });
	$("#myModals").modal();
	});

	//赛事的审批、上架、下架
	$("#checkMatchSome,#upMatchSome,#downMatchSome").click(function() {
		//获得点击按钮的值
		var btnVar=$(this).attr('status');
        var checkError = false;
        //赛事
    	var matchArray = [];
    	//提示语
    	var speak;
    	//商品状态
    	var status=[];
    	$(".ckSelect").each(function(){
        	var check = $(this).is(':checked');
        	if(check){ 
        		//获取商户类型
        		var _id = $(this).attr("ckId");
        		var stat= $(this).attr("ckStatus");
        		//将所有的状态放进数组
        		status.push(stat);
        		//放进赛事数组
        		matchArray.push(_id);
        		
            }
        });
        if(matchArray.length == 0){
    		alert("请选择项"); return;
        }
        if(btnVar=="1"){
        	if($.inArray("2",status)!=-1 || $.inArray("-1",status)!=-1){
        		alert("请选择待审批的项！");
            	return;
        	}
        }else if(btnVar=="2"){
        	if($.inArray("1",status)!=-1 || $.inArray("2",status)!=-1){
	        	alert("请选择已下架的项！");
	        	return;
        	}
        }else if(btnVar=="-1"){
        	if($.inArray("-1",status)!=-1|| $.inArray("1",status)!=-1){
	        	alert("请选择已上架的项！");
	        	return;
        	}
        }
        if(btnVar=="1"){
        	speak="你确定要审批吗？";
        }else if(btnVar=="2"){
        	speak="你确定要上架吗？";
        }else if(btnVar=="-1"){
        	speak="你确定要下架吗？";
        }
        if(confirm(speak)){
    	$.ajax({
			url: '../../cms/macth/upAndDownMatch',
			type: 'post',
			data:{
				matchIds:matchArray.join(","),
				status:btnVar
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
					$("#_selectAll").attr("checked",false);
				}else{
					alert(data.message);
				}
			}
        });
       }
	});
	//赛事详细按钮
	$(document).on('click','#detailMatch',function(){
	    var updateArray = [];
	    var id;
	    //请求路径
	    var url;
	    //商家名称
	    //var name;
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				id =$(this).attr("ckId");
				updateArray.push(id);
				//name=$(this).parents('tr').find('td:eq(2)').text();
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
			url= '../../cms/macth/findMacthById';
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
					var tblContentHtml = $("#matchContentTmple").render(data.object);
	            	$(".matchdetail").html(tblContentHtml);
	            	//$('.businessName').html(name);
			    }
			}
		   });
	$("#myModals").modal();
	});

	//点击新增按钮
	$("#addMatchSome").click(function(){
		location.href = editUrl;
	});
			
	//点击修改按钮
	$("#updateMatchSome").click(function(){
		var updateArray = [];
		var cktype = "";
		var ckstatus = "";
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _id =$(this).attr("ckId");
				updateArray.push(_id);
				cktype = $(this).attr("ckType");
				ckstatus = $(this).attr("ckStatus");
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要编辑的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能编辑一项"); return;
		}
		//if(cktype=="1")
		//{
		//	alert("只允许修改官方活动"); return;
		//}
		if(ckstatus!="1")
		{
			alert("只允许修改未审核的赛事"); return;
		}
		location.href = editUrl+'?id=' + updateArray[0];
	});

	//俱乐部详细按钮
	$(document).on('click','#detailClub',function(){
	    var updateArray = [];
	    var id;
	    //请求路径
	    var url;
	    //商家名称
	    //var name;
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				id =$(this).attr("ckId");
				updateArray.push(id);
				//name=$(this).parents('tr').find('td:eq(2)').text();
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
			url= '../../cms/club/findClubById';
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
					var tblContentHtml = $("#clubContentTmple").render(data.object);
	            	$(".clubdetail").html(tblContentHtml);
	            	//$('.businessName').html(name);
			    }
			}
		   });
	$("#myModals").modal();
	});

	//俱乐部的审批
	$("#successClubSome,#failClubSome").click(function() {
		//获得点击按钮的值
		var btnVar=$(this).attr('status');
		//获得点击按钮的值
        var checkError = false;
        //俱乐部
    	var clubArray = [];
    	//商品状态
    	var status=[];
    	$(".ckSelect").each(function(){
        	var check = $(this).is(':checked');
        	if(check){ 
        		//获取商户类型
        		var _id = $(this).attr("ckId");
        		var stat= $(this).attr("ckStatus");
        		//将所有的状态放进数组
        		status.push(stat);
        		//放进赛事数组
        		clubArray.push(_id);
            }
        });
        if(clubArray.length == 0){
    		alert("请选择项"); return;
        }
        
    	if($.inArray("2",status)!=-1 || $.inArray("3",status)!=-1){
    		alert("请选择待审批的项！");
        	return;
    	}
        
        if(confirm("你确定要审批吗？")){
    	$.ajax({
			url: '../../cms/club/applyClub',
			type: 'post',
			data:{
				clubIds:clubArray.join(","),
				status:btnVar
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
					$("#_selectAll").attr("checked",false);
				}else{
					alert(data.message);
				}
			}
        });
       }
	});

	//俱乐部活动的推荐
	$("#recommendClubSome,#recommendNotClubSome").click(function() {
		//获得点击按钮的值
		var btnVar=$(this).attr('status');
		//获得点击按钮的值
        var checkError = false;
        //俱乐部
    	var clubArray = [];
    	$(".ckSelect").each(function(){
        	var check = $(this).is(':checked');
        	if(check){ 
        		//获取商户类型
        		var _id = $(this).attr("ckId");
        		//放进赛事数组
        		clubArray.push(_id);
            }
        });
        if(clubArray.length == 0){
    		alert("请选择项"); return;
        }
        
        if(confirm("你确定要修改吗？")){
    	$.ajax({
			url: '../../cms/club/recommendClub',
			type: 'post',
			data:{
				clubIds:clubArray.join(","),
				status:btnVar
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
					$("#_selectAll").attr("checked",false);
				}else{
					alert(data.message);
				}
			}
        });
       }
	});
	
	//点击新增按钮
	$("#addClubSome").click(function(){
		location.href = editUrl;
	});
			
	//点击修改按钮
	$("#updateClubSome").click(function(){
		var updateArray = [];
		var ckuser = "";
		var ckstatus = "";
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _id =$(this).attr("ckId");
				updateArray.push(_id);
				ckuser = $(this).attr("ckUser");
				ckstatus = $(this).attr("ckStatus");
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要编辑的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能编辑一项"); return;
		}
		if(ckuser!="0")
		{
			alert("不允许修改会员创建的俱乐部"); return;
		}
		if(ckstatus!="1")
		{
			alert("只允许修改未审核的俱乐部"); return;
		}
		location.href = editUrl+'?id=' + updateArray[0];
	});

	//俱乐部活动详细按钮
	$(document).on('click','#detailEvent',function(){
	    var updateArray = [];
	    var id;
	    //请求路径
	    var url;
	    //商家名称
	    //var name;
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				id =$(this).attr("ckId");
				updateArray.push(id);
				//name=$(this).parents('tr').find('td:eq(2)').text();
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
			url= '../../cms/clubEvent/findClubEventById';
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
					var tblContentHtml = $("#eventContentTmple").render(data.object);
	            	$(".eventdetail").html(tblContentHtml);
	            	//$('.businessName').html(name);
			    }
			}
		   });
	$("#myModals").modal();
	});

	//俱乐部活动的审批
	$("#successEventSome,#failEventSome").click(function() {
		//获得点击按钮的值
		var btnVar=$(this).attr('status');
		//获得点击按钮的值
        var checkError = false;
        //俱乐部活动
    	var eventArray = [];
    	//商品状态
    	var status=[];
    	$(".ckSelect").each(function(){
        	var check = $(this).is(':checked');
        	if(check){ 
        		//获取商户类型
        		var _id = $(this).attr("ckId");
        		var stat= $(this).attr("ckStatus");
        		//将所有的状态放进数组
        		status.push(stat);
        		//放进赛事数组
        		eventArray.push(_id);
            }
        });
        if(eventArray.length == 0){
    		alert("请选择项"); return;
        }
        
    	if($.inArray("2",status)!=-1 || $.inArray("3",status)!=-1){
    		alert("请选择待审批的项！");
        	return;
    	}
        
        if(confirm("你确定要审批吗？")){
    	$.ajax({
			url: '../../cms/clubEvent/applyClubEvent',
			type: 'post',
			data:{
				eventIds:eventArray.join(","),
				status:btnVar
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					dipscontent(getfilter(),contentUrl);
					$("#_selectAll").attr("checked",false);
				}else{
					alert(data.message);
				}
			}
        });
       }
	});
	//退款的审批
	$("#successRefundSome,#failRefundSome").click(function() {
		//获得点击按钮的值
		var btnVar=$(this).attr('status');
		//获得点击按钮的值
        var checkError = false;
        //俱乐部活动
    	var eventArray = [];
    	//商品状态
    	var status=[];
    	var ckType="0";
    	$(".ckSelect").each(function(){
        	var check = $(this).is(':checked');
        	if(check){ 
        		//获取商户类型
        		var _id = $(this).attr("ckId");
        		var stat= $(this).attr("ckStatus");
        		//将所有的状态放进数组
        		status.push(stat);
        		//放进赛事数组
        		eventArray.push(_id);
        		if(ckType!="2")
        		{
        			ckType=$(this).attr("ckType");
        		}
            }
        });
        if(eventArray.length == 0){
    		alert("请选择项"); return;
        }
        
    	if($.inArray("2",status)!=-1 || $.inArray("3",status)!=-1){
    		alert("请选择待审批的项！");
        	return;
    	}
    	
    	if(btnVar=='2' && ckType=="2")//如果有活动不允许选择不通过
    	{
    		alert("俱乐部活动必须选择审批通过");
    	}
        
        if(confirm("你确定要审批吗？")){
    	$.ajax({
			url: '../../cms/refundOrder/applyRefundOrder',
			type: 'post',
			data:{
				refundIds:eventArray.join(","),
				status:btnVar
			},
			dataType: 'json',
			success: function(data){
				if(data.code==1){
					if(data.object!="")
					{
						dipscontent(getfilter(),contentUrl);
					}
					$("#selectHeadId").html(data.object);
					//alert(data.object);
					$("#_selectAll").attr("checked",false);
				}else{
					alert(data.message);
					dipscontent(getfilter(),contentUrl);
				}
			}
        });
       }
	});
});