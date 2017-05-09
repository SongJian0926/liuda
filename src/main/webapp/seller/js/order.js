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
$(document).ready(function(){
	var id=JSON.parse($.cookie("busniessInfo")).goodsId;
	var businessType=JSON.parse($.cookie("busniessInfo")).type;
	var nextPage=0;
	var pageSize=10;
	
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(1).addClass('active');
    //表头的颜色
    $("tr:eq(0)").css("background-color","#eeeeee");
    //加载时间日历
	$("#startTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
		var starttime=$("#startTime").val();
		$("#endTime").datetimepicker('setStartDate',starttime);
		$("#startTime").datetimepicker('hide');
	});
	$("#endTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
		var endtime=$("#startTime").val()
		$("#startTime").datetimepicker('setEndDate',endtime);
		$("#endTime").datetimepicker('hide');
	});
    
	//查询
	$('.search-bt').click(function(){
		nextPage=0;
		content(getfilter(id,businessType),nextPage,pageSize);
	});
	//加载列表
	content(getfilter(id,businessType),nextPage,pageSize);
	//查看详情
	$('#list-content').on('click','tr',function(){
		
		/*var orderno=$(this).parents('tr').find('td').first().html();
		var objectName=$(this).parents('tr').find('td').eq(1).html();*/
		var orderno=$(this).find('td').first().html();
		var objectName=$(this).find('td').eq(1).html();
		
		var url = 'orderDetail.jsp?orderNo='+orderno;
		location.href=url;
		
	});
	 // 清除时间
	$('.datetime_clear').click(function(){
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
	//页码点击事件
	$("#displayPage").on('click','li',function(){
		 //获得当前页码的点击事件,没有id,style属性的li标签就是页码
		 var a=$(this).attr("id"); 
		 var b=$(this).attr("style");
		 if(typeof(a)=="undefined"&&typeof(b)=="undefined"){
			 //获得页码值
			 nextPage=$(this).attr("thispage");
			 content(getfilter(id,businessType),nextPage,pageSize);
		 }	
	});
	//回到第一页、回到最后一页、前一页、下一页
	$("#displayPage").on('click','#example2_first,#example2_last,#example2_previous,#example2_next',function(){
		 nextPage=$(this).attr("thispage");
		 content(getfilter(id,businessType),nextPage,pageSize);
	});
	//跳到指定页码
	$("#displayPage").on('click','li:last input',function(){
		 //获得页码值
		 var pagenum=$(this).parent().prev().find('.page_num_input').val();
		 //如果输入的页码小于0
		 if(pagenum<=0){
			 return;
		 }
		 nextPage=pagenum-1;
		 content(getfilter(id,businessType),nextPage,pageSize);
	});
	
});
function getfilter(id,businessType){
	var _jsonFilter = "{";
			
	        if(($("#startTime").val() && $("#startTime").val().length > 0) && $("#endTime").val()<=0){
		_jsonFilter += "'search_GT_createTime':'"+$("#startTime").val()+"',";
	}       
	        if(($("#endTime").val() && $("#endTime").val().length > 0) && $("#startTime").val().length<=0){
		_jsonFilter += "'search_LT_createTime':'"+$("#endTime").val()+"',";
	} 
	        if(($("#endTime").val() && $("#endTime").val().length > 0) && ($("#startTime").val() && $("#startTime").val().length > 0)){
		_jsonFilter += "'search_BETWEEN_createTime':'"+$("#startTime").val()+","+$("#endTime").val()+"',";
	}
	        if(($("#orderStatus").val() && $("#orderStatus").val().length > 0)){
		_jsonFilter += "'search_EQ_order_status':'"+$("#orderStatus").val().replace("'","\"").trim()+"',";
	}       
	        if(businessType==0){
	        	if(id>0){
    	    	_jsonFilter += "'search_EQ_hotel_id':'"+id+"',";
	        	}   
    }
	        if(businessType==1){
		        if(id>0){
	    	_jsonFilter += "'search_EQ_tourist_id':'"+id+"',";
		       }   
	}
	        if(businessType!=null){
    	_jsonFilter += "'search_EQ_business_type':'"+businessType+"',";
    }   
			if(_jsonFilter != "{"){
		_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
	} 
	_jsonFilter += "}";
	return _jsonFilter;
}
function content(jsonFilter,nextPage,pageSize){
	$.ajax({
		url:root+'cms/order/findOrderList',
		type:'post',
		dataType:'json',
		data:{	
			jsonFilter:jsonFilter,
			nextPage:nextPage,
			pageSize:pageSize
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
				//列表内容不为空
				if(data.object.content.length>0){
					var tblContentHtml = $("#listContentTmple").render(data.object.content);
					$("#list-content").html(tblContentHtml);   
					//表格奇数行颜色
					$('#list-content').find("tr:even").css("background-color","#ffffff");
					//表格偶数行颜色
					$('#list-content').find("tr:odd").css("background-color","#f9f9f9");
					//加载分页器
	            	loadTmpl.renderExternalTemplate("seller_page", "#displayPage",data.object, 1);
	            	$('.nogoods').hide();
					$('.table-border').show();
					$('.search_order').show();
					$('.search-but').show();
					$('.page_big_div').show();
	            	$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
				}else{
					//如果状态不为空，头部的查询条件不隐藏
					if($("#orderStatus").val().length>0||$("#startTime").val().length>0||$("#endTime").val().length>0){
						$('.nogoods').show();
						$('.table-border').hide();
						$('.page_big_div').hide();
						$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
					}else{
						$('.nogoods').show();
						$('.table-border').hide();
						$('.search_order').hide();
						$('.search-but').hide();
						$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
					}
				}
			}
			else{
				alert(data.message);
			}
		}
	});
}