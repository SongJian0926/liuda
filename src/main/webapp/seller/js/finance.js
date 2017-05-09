$(document).ready(function(){
	/*var businessType=0;
	var id=1;
	var businessId=1;*/
	var nextPage=0;
	var pageSize=10;
	var id=JSON.parse($.cookie("busniessInfo")).goodsId;
	var businessType=JSON.parse($.cookie("busniessInfo")).type;
	var businessId=JSON.parse($.cookie("busniessInfo")).id;
	content(getfilter(id,businessType,businessId),businessType,nextPage,pageSize);
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(2).addClass('active');
	//加载时间日历
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
	//账户总额
	totalFinance(businessId,businessType);
	//查询列表
	$('.search-menu').on('click','.search-btn',function(){
		nextPage=0;
		content(getfilter(id,businessType,businessId),businessType,nextPage,pageSize);
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
			 content(getfilter(id,businessType,businessId),businessType,nextPage,pageSize);
		 }	
	});
	//回到第一页、回到最后一页、前一页、下一页
	$("#displayPage").on('click','#example2_first,#example2_last,#example2_previous,#example2_next',function(){
		 nextPage=$(this).attr("thispage");
		 content(getfilter(id,businessType,businessId),businessType,nextPage,pageSize);
	});
	//跳到指定页码
	$("#displayPage").on('click','li:last input',function(){
		 //获得页码值
		 var pagenum=$(this).parent().prev().find('.page_num_input').val();
		 if(pagenum<=0){
			 return;
		 }
		 nextPage=pagenum-1;
		 content(getfilter(id,businessType,businessId),businessType,nextPage,pageSize);
	});
});
function getfilter(id,businessType,businessId){
	var _jsonFilter = "{";
			
	        if(($("#startTime").val() && $("#startTime").val().length > 0)){
		_jsonFilter += "'search_GT_createTime':'"+$("#startTime").val()+"',";
	}       
	        if(($("#endTime").val() && $("#endTime").val().length > 0)){
		_jsonFilter += "'search_LT_createTime':'"+$("#endTime").val()+"',";
	} 
	        if(($("#status").val() && $("#status").val().length > 0)){
		_jsonFilter += "'search_EQ_status':'"+$("#status").val().replace("'","\"").trim()+"',";
		    
	}      
	        if(businessId> 0){
		_jsonFilter += "'search_EQ_business_id':'"+businessId+"',";
				
	}  
	        if(businessType==0){
	        	if(id>0){
    	    	_jsonFilter += "'search_EQ_id':'"+id+"',";
	        	}   
	        _jsonFilter += "'search_EQ_business_type':'"+businessType+"',";
    }
	        if(businessType==1){
		        if(id>0){
	    	_jsonFilter += "'search_EQ_id':'"+id+"',";
		       }   
		    _jsonFilter += "'search_EQ_business_type':'"+businessType+"',";
	}
			if(_jsonFilter != "{"){
		_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
	} 
	_jsonFilter += "}";
	return _jsonFilter;
}
function content(getfilter,businessType,nextPage,pageSize){
	$.ajax({
		url:root+'cms/cashRecord/findFinanceList',
		type:'post',
		dataType:'json',
		data:{	
			jsonFilter:getfilter,
			nextPage:nextPage,
			pageSize:pageSize
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
				//列表内容不为空
				if(data.object.content.length>0){
					var tblContentHtml = $("#hoteldealTmple").render(data.object.content);
					$("#financeList").html(tblContentHtml);   
					//加载分页器
	            	loadTmpl.renderExternalTemplate("seller_page", "#displayPage",data.object, 1);
	            	$('.nogoods').hide();
					$('.child_menu').show();
					$('.search').show();
					$('.con1').show();
					$('.page_big_div').show();
				}else{
					if($("#status").val().length>0||$("#startTime").val().length>0||$("#endTime").val().length>0){
						$('.nogoods').show();
						$('.child_menu').show();
						$('.search').show();
						$('.con1').hide();
						$('.page_big_div').hide();
					}else{
						$('.nogoods').show();
						$('.child_menu').hide();
						$('.search').hide();
						$('.con1').hide();
						$('.page_big_div').hide();
					}
	            	
				}
			}
			else{
				alert(data.message);
			}
		}
	});
}
function totalFinance(businessId,businessType){
	$.ajax({
		url:root+'cms/cashRecord/findFinance',
		type:'post',
		dataType:'json',
		data:{	
			businessId:businessId,
			businessType:businessType
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
				var tblContentHtml = $("#headdealTmple").render(data.object);
            	$("#headListTmpl").html(tblContentHtml);        
				//总收入
				/*if(data.object.orderVo){
					var num=parseFloat(data.object.orderVo.total).toFixed(2);
					$(".totalmoney").html(num);
					
					
				}else{
					$(".totalmoney").html(parseFloat(0).toFixed(2));
				}
				//提现
				if(data.object.money){
					var ynum=parseFloat(data.object.money).toFixed(2);
					$(".getmoney").html(ynum);
				}else{
					$(".getmoney").html(parseFloat(0).toFixed(2));
				}
				//当前余额
				var num1=parseFloat(data.object.remain).toFixed(2);
				$(".remainmoney").html(num1);
			
				//可提现余额
				var ynum1=parseFloat(data.object.cashMoney).toFixed(2);
				$(".cangetmoney").html(ynum1);
				*/
			}
			else{
				
				alert(data.message);
			}
		}
	});
}