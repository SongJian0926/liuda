$(function(){
	//点击打款
	$("#transfer").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
           if(a=="打款中"){
              return; 	                           
           }
        });
        transfer(root+"cms/fondRecord/sendMoney");		  			      
	});
	//点击打款失败
	$("#transferFail").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
         if(a=="提现成功"||a=="已提交"||a=="打款失败"){
             return; 	                           
          }
        });
        transferFail(root+"cms/fondRecord/sendMoney");		  			      
	});
	//点击打款成功
	$("#transferSuccess").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').last().html();
           if(a=="打款中"){
              return; 	                           
           }
        });
        transferSuccess(root+"cms/fondRecord/sendMoney");		  			      
	});
});
/**打款*/
function transfer(lockurl){
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
		alert("请选择要打款的项"); return;
    }
    if($.inArray("已提交",checkArray)==-1){
		alert("请选择已提交的项");
		return;
	}
    if(confirm("你确定要打款选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				presentState:4
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
function transferFail(lockurl){
	var checkError = false;
	var delArray = [];
	var checkArray=[];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	var checked=$(this).parents('tr').find('td').last().html();
    	if(checked=="打款中"){
	    	if(check){
				var _id = $(this).attr("ckId");
				delArray.push(_id);
				checkArray.push(checked);
	        }
    	}
    });
    if(delArray.length == 0){
		alert("请选择要打款失败的项"); return;
    }
    if($.inArray("打款中",checkArray)==-1){
		alert("请选择打款中的项");
		return;
	}
    if(confirm("你确定要打款失败选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				presentState:3
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
function transferSuccess(lockurl){
	var checkError = false;
	var delArray = [];
	var checkArray=[];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	var checked=$(this).parents('tr').find('td').last().html();
    	if(checked=="打款中"){
	    	if(check){
				var _id = $(this).attr("ckId");
				delArray.push(_id);
				checkArray.push(checked);
	        }
    	}
    });
    if(delArray.length == 0){
		alert("请选择要打款成功的项"); return;
    }
    if($.inArray("打款中",checkArray)==-1){
		alert("请选择打款中的项");
		return;
	}
    if(confirm("你确定要打款成功选中项吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				presentState:2
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