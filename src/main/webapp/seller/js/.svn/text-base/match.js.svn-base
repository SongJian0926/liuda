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
	var nextPage=0;
	var pageSize=10;
	var businessInfoId=JSON.parse($.cookie("busniessInfo")).id;
	
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(0).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(3).addClass('active_nav');
    //表头的颜色
    $("tr:eq(0)").css("background-color","#eeeeee");
    
	//新增
	$('.bt-add').click(function(){
		location.href = '../shop/addMatch.jsp';
	});
	//修改
	$('.bt-update').click(function(){
		var updateArray = [];
		var stat;
		$(".checkitem").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _id =$(this).attr("ckId");
				updateArray.push(_id);
				stat= $(this).attr("ckStatus");
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要编辑的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能编辑一项"); return;
		}
		if(stat!="1")
		{
			alert("只有待审核的状态才允许修改");return;
		}
		location.href = '../shop/addMatch.jsp?id='+updateArray[0];
	});
	//查看
	$('.bt-show').click(function(){
		var updateArray = [];
		$(".checkitem").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _id =$(this).attr("ckId");
				updateArray.push(_id);
	        }
	    });
		if(updateArray.length == 0){
			alert("请选择要查看的项"); return;
		}
		if(updateArray.length > 1){
			alert("每次只能查看一项"); return;
		}
		location.href = '../shop/addMatch.jsp?id='+updateArray[0]+"&type=show";
	});
	//加载列表
	content(getfilter(businessInfoId),nextPage,pageSize);
	//页码点击事件
	$("#displayPage").on('click','li',function(){
		 //获得当前页码的点击事件,没有id,style属性的li标签就是页码
		 var a=$(this).attr("id"); 
		 var b=$(this).attr("style");
		 if(typeof(a)=="undefined"&&typeof(b)=="undefined"){
			 //获得页码值
			 nextPage=$(this).attr("thispage");
			 content(getfilter(businessInfoId),nextPage,pageSize);
		 }	
	});
	//回到第一页、回到最后一页、前一页、下一页
	$("#displayPage").on('click','#example2_first,#example2_last,#example2_previous,#example2_next',function(){
		 nextPage=$(this).attr("thispage");
		 content(getfilter(businessInfoId),nextPage,pageSize);
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
		 content(getfilter(businessInfoId),nextPage,pageSize);
	});
	

	//赛事的审批、上架、下架
	$("#upSome,#downSome").click(function() {
		//获得点击按钮的值
		var btnVar=$(this).attr('status');
        var checkError = false;
        //赛事
    	var matchArray = [];
    	//提示语
    	var speak;
    	//商品状态
    	var status=[];
    	$(".checkitem").each(function(){
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
        if(btnVar=="2"){
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
        if(btnVar=="2"){
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
					$("#_selectAll").attr("checked",false);
					content(getfilter(businessInfoId),nextPage,pageSize);
				}else{
					alert(data.message);
				}
			}
        });
       }
	});
});

function content(jsonFilter,nextPage,pageSize){
	$.ajax({
		url:root+'cms/macth/findMacthNEStatusPage',
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
					//$('.search_order').show();
					//$('.search-but').show();
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
						//$('.search_order').hide();
						//$('.search-but').hide();
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


function getfilter(businessId){
	var _jsonFilter = "{";

	        if(businessId!=null){
    	_jsonFilter += "'search_EQ_businessUserId':'"+businessId+"',";
    }   
			if(_jsonFilter != "{"){
		_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
	} 
	_jsonFilter += "}";
	return _jsonFilter;
}

function openlive(matchid)
{
	refreshLive(matchid);
	
	var divtest = document.getElementById("liveModel"); 
	document.getElementById('liveModel').style.display='block';
	//divtest.style.display="block"; 
	//var myWin = new ModeWindow(divtest,200,300,300,100,"i change!"); 
	//myWin.show(); 
	
	//$("#liveModel").show();
}
function addLive()
{
	$.ajax({
		url : root + "cms/matchLive/saveMatchLive",
		type : "POST",
		data : $("#liveForm").serialize(),
		success : function(data) {
			if (data.code == 1) {
				alert("添加成功");
				refreshLive($("#hidLiveMatchId").val());
			} else {
				alert(data.message);
			}
		}
	});
}
function refreshLive(matchid)
{
	var _jsonFilter = "{'search_EQ_matchId':'"+matchid+"'}";
	url= root + 'cms/matchLive/findMatchLiveNEStatusPage';
		$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		data:{
			jsonFilter:_jsonFilter
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
				if(data.object && data.object.content)
				{
					var tblContentHtml = $("#liveContentTmple").render(data.object.content);
					$("#liveBody").html(tblContentHtml);
				}
				else
				{
					$("#liveBody").html("");
				}
				$("#hidLiveMatchId").val(matchid);
		    }
		}
	   });
}
function openguess(matchid)
{
	refreshGuess(matchid);
	$("#guessModel").show();
}
function setGuess()
{
	var guessoptionid = $('input[name="ckoption"]:checked').val();
	if(guessoptionid==undefined || guessoptionid==null)
	{
		alert("请选择一个选项");
		return;
	}
	$.ajax({
		url : root + "cms/guess/setGuessOption",
		type : "POST",
		dataType:'json',
		data : {
			matchId:$("#hidGuessMatchId").val(),
			optionId:guessoptionid
		},
		success : function(data) {
			if (data.code == 1) {
				alert("修改成功");
				refreshGuess($("#hidGuessMatchId").val());
			} else {
				alert(data.message);
			}
		}
	});
}
function computeGuess()
{
	var guessoptionid = $('input[name="ckoption"]:checked').val();
	if(guessoptionid==undefined || guessoptionid==null)
	{
		alert("请选择一个选项");
		return;
	}
	$.ajax({
		url : root + "cms/guess/computeGuess",
		type : "POST",
		dataType:'json',
		data : {
			matchId:$("#hidGuessMatchId").val(),
			optionId:guessoptionid
		},
		success : function(data) {
			if (data.code == 1) {
				alert("计算成功");
				refreshGuess($("#hidGuessMatchId").val());
			} else {
				alert(data.message);
			}
		}
	});
}
function refreshGuess(matchid)
{
	url= root + 'cms/guess/findByMacthIdAndNotStatus';
		$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		data:{
			matchId:matchid
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
				if(data.object && data.object.guessOptionList)
				{
					var tblContentHtml = $("#guessContentTmple").render(data.object.guessOptionList);
					$("#guessBody").html(tblContentHtml);
				}
				else
				{
					$("#guessBody").html("");
				}
				$("#hidGuessMatchId").val(matchid);
		    }
		}
	   });
}