$(function(){
	//点击审核按钮
	$("#bigShot").click(function() {
        var checkParams=$('input[class="ckSelect"]:checked');
        $.each(checkParams,function(){
         var a=$(this).parents('tr').find('td').eq(-4).html();
           if(a=="是"){
              return; 	                           
           }
        });
        bigShot(root+"cms/user/checkBigShot");		  			      
	});
	
});
/**审核为大咖*/
function bigShot(lockurl){
	var checkError = false;
	var delArray = [];
	var checkArray=[];
	$(".ckSelect").each(function(){
    	var check = $(this).is(':checked');
    	var checked=$(this).parents('tr').find('td').eq(-4).html();
    	if(checked=="否"){
    		if(check){
    			var _id = $(this).attr("ckId");
    			delArray.push(_id);
    			checkArray.push(checked);
            }
    	}
    });
    if(delArray.length == 0){
		alert("请选择要审核的项"); return;
    }
    if($.inArray("否",checkArray)==-1){
		alert("请选择不是大咖的项");
		return;
	}
    if(confirm("你确定要将选中项审核为大咖吗？")){
    	$.ajax({
			url: lockurl,
			type: 'post',
			data:{
				modelIds:delArray.join(","),
				bigShot:1
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