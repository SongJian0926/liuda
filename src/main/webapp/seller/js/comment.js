var pic1s="";
$(document).ready(function(){
	upload("replyComment");
	/*var businessType=0;
	var businessId=1;*/
	var nextPage=0;
	var pageSize=3;
	var businessType=JSON.parse($.cookie("busniessInfo")).type;
	var businessId=JSON.parse($.cookie("busniessInfo")).id;
	//添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(3).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(3).addClass('active_nav');
	var userId;
	var id;
	var delId;
	/*回复*/
	$('#comment-list').on('click','.reply-btn',function(){
		if($(this).html().trim()=="已回复"){
			return;
		}
		$(this).addClass('b-font');
		$(this).parents('.comment-content').find('.reply-comment1').show();
		userId=$(this).parents('.comment-content').find('#userId').attr('data-id');
		id=$(this).parents('.comment-content').attr('comment-id');
	});
	
	/*确认回复*/
	$('#comment-list').on('click','.confirm',function(){
		//如果是编辑回复内容
		if($(this).parents('.comment-content').find('.reply-text').text().length>0){
			//获取回复的文本内容
			var content=$(this).parents('.comment-content').find('#replyComment').val();
			/*var delId=$(this).parents('.reply-content').attr('data-id');
			alert(delId);
			return;*/
			edit(delId,businessId,userId,id,content,nextPage,pageSize);
			//回复框隐藏
			$(this).parents('.comment-content').find('.reply-comment1').hide();
			
		}else{//首次回复
			//获取回复的文本内容
			var content=$(this).parents('.comment-content').find('#replyComment').val();
			saveReply(businessId,userId,id,content,pic1s,nextPage,pageSize);
			$(this).parents('.comment-content').find('.reply-comment1').hide();
			$(this).parents('.comment-content').find('.reply-btn').html('已回复').addClass('c-font');
			
		}
		
	});
	/*取消*/
	$('#comment-list').on('click','.cancel',function(){
		//如果是在编辑回复的时候取消
		if($(this).parents('.comment-content').find('.reply-text').text().length>0){
			//回复框隐藏
			$(this).parents('.con1').find('.reply-comment1').hide();
			//回复内容显示
			$(this).parents('.comment-content').find('.reply-content').show();
		}else{
			//清空回复的内容
			$(this).parents('.comment-content').find('#replyComment').val('');
			var len=$(this).parents('.comment-content').find('.reply-comment li').length;
			//如果上传了图片，将上传的图片移除掉
			if(len>1){
				$(this).parents('.comment-content').find('.reply-comment ul').html('<li class="img-li"><div class="upload_div"><img id="myImageShow" src="../images/camera.png" class="img"><input type="hidden" id="imgUrl" name="picurl" value=""><input type="file" id="uploadPhotoFile{{:#index+1}}" name="photoFile" class="upload_file"></div></li>');
			}
			$('#comment-list').find('.reply-btn').removeClass('b-font');
			$(this).parents('.con1').find('.reply-comment1').hide();
			$(this).parents('.con1').find('.reply-btn').addClass('a-font');
		}
		
	});
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
	//加载列表
	getContent(getfilter(),businessId,businessType,nextPage,pageSize);
	//查询
	$('.search-menu').on('click','.search-btn',function(){
		nextPage=0;
		getContent(getfilter(),businessId,businessType,nextPage,pageSize);
	});
	//页码点击事件
	$("#displayPage").on('click','li',function(){
		 //获得当前页码的点击事件,没有id,style属性的li标签就是页码
		 var a=$(this).attr("id"); 
		 var b=$(this).attr("style");
		 if(typeof(a)=="undefined"&&typeof(b)=="undefined"){
			 //获得页码值
			 nextPage=$(this).attr("thispage");
			 getContent(getfilter(),businessId,businessType,nextPage,pageSize);
		 }	
	});
	//回到第一页、回到最后一页、前一页、下一页
	$("#displayPage").on('click','#example2_first,#example2_last,#example2_previous,#example2_next',function(){
		 nextPage=$(this).attr("thispage");
		 getContent(getfilter(),businessId,businessType,nextPage,pageSize);
	});
	//跳到指定页码
	$("#displayPage").on('click','li:last input',function(){
		 //获得页码值
		 var pagenum=$(this).parent().prev().find('.page_num_input').val();
		 if(pagenum<=0){
			 return;
		 }
		 nextPage=pagenum-1;
		 getContent(getfilter(),businessId,businessType,nextPage,pageSize);
	});
	//删除商家回复
	$('#comment-list').on('click','.a-del',function(){
		var delId=$(this).parents('.reply-content').attr('data-id');
		
		del(delId,nextPage,pageSize);
		$(this).parents('.con1').find('.reply-btn').addClass('a-font');
	});
	//编辑商家回复
	$('#comment-list').on('click','.a-edit',function(){
		//回复框显示
		$(this).parents('.comment-content').find('.reply-comment1').show();
		//获取回复的内容
		var con=$(this).parents('.comment-content').find('#reply').text();
		
		//回复内容隐藏
		$(this).parents('.comment-content').find('.reply-content').hide();
		
		//将内容放入回复框中
		$(this).parents('.comment-content').find('#replyComment').val(con);
		
		userId=$(this).parents('.comment-content').find('#userId').attr('data-id');
		id=$(this).parents('.comment-content').attr('comment-id');
		//要编辑的回复ID
		delId=$(this).parents('.reply-content').attr('data-id');

	});
	
});
function getfilter(){
	var _jsonFilter = "{";
	        if(($("#startTime").val() && $("#startTime").val().length > 0)){
		_jsonFilter += "'search_GTE_create_time':'"+$("#startTime").val()+"',";
	}       
	        if(($("#endTime").val() && $("#endTime").val().length > 0) ){
		_jsonFilter += "'search_LTE_create_time':'"+$("#endTime").val()+"',";
	} 
	        if(_jsonFilter != "{"){
		_jsonFilter = _jsonFilter.substring(0,_jsonFilter.length - 1);
	} 
	_jsonFilter += "}";
	return _jsonFilter;
}
//加载评论列表
function getContent(jsonFilter,businessId,businessType,nextPage,pageSize){
	$.ajax({
		url:root+'cms/comment/findCommentList',
		type:'post',
		dataType:'json',
		data:{
			jsonFilter:jsonFilter,
			businessId:businessId,
			businessType:businessType,
			nextPage:nextPage,
			pageSize:pageSize
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
				//列表内容不为空
				if(data.object.content.length>0){
					var tblContentHtml = $("#commentListTmple").render(data.object.content);
					$("#comment-list").html(tblContentHtml);     
					//加载分页器
	            	loadTmpl.renderExternalTemplate("seller_page", "#displayPage",data.object, 1);
	            	$('.nogoods').hide();
					$('.search').show();
					$('.con1').show();
					
					if($('.right_content1').height()>350){
						$('.left_child_menu').css({'height':$('.right_content1').height() + 'px'});
					}else{
						$('.left_child_menu').css({'height':350 + 'px'});
					}
					
				}else{
					//如果状态不为空，头部的查询条件不隐藏
					if($("#startTime").val().length>0||$("#endTime").val().length>0){
						$('.nogoods').show();
						$('.search').show();
						$('.con1').hide();
						$('.left_child_menu').css({'height':$('.right_content1').height() + 'px'});
					}else{
						$('.nogoods').show();
						$('.search').hide();
						$('.con1').hide();
						$('.left_child_menu').css({'height':$('.right_content1').height() + 'px'});
					}
					
				}
				
			}
			else{
				alert(data.message);
			}
		}
	});
}
//确认回复
function saveReply(businessId,userId,commentId,content,pic1s,nextPage,pageSize){
	$.ajax({
		url:root+'cms/replyComment/saveReplyComment?pics1='+pic1s,
		type:'post',
		data:{
			businessUserId:businessId,
			replyComment:content,
			userId:userId,
			commentId:commentId
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
				var businessType=JSON.parse($.cookie("busniessInfo")).type;
				var businessId=JSON.parse($.cookie("busniessInfo")).id;
				getContent(getfilter(),businessId,businessType,nextPage,pageSize);
				}
			else{
				alert(data.message);
			}
		}
	});
}
//上传图片
function upload(modelName) {
	$(document).on('change','.upload_file', function() {
		var uploadid=$(this).parents('.upload-reply').find('.upload_file').attr('id');
		var _x = $(this).parents('.upload-reply').find('li');
		var _y = $(this).parents('.upload-reply').find('li:last');
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId :$(this).parents('.upload-reply').find('.upload_file').attr('id'),
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						if(_x.length==4){
							//将上传的图片拼在上传图片的按钮前面
							_y.before(' <li class="img-li"><input name="imgUrl" style="display:none;" value='+ds[1]+'></input><img src="../../'+ds[1]+'" class="img"></li>');
							//上传图片的按钮隐藏
							_y.hide();
						}else{
							$(_y).before(' <li class="img-li"><input name="imgUrl" style="display:none;" value='+ds[1]+'></input><img src="../../'+ds[1]+'" class="img"></li>');
						}
						pic1s=pic1s+ds[1]+",";
						
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
/** 删除事件 */
function del(id,nextPage,pageSize){
	
    if(confirm("你确定要删除选中项吗？")){
    	var url = root+"cms/replyComment/operateReplyCommentByIds";
    	$.ajax({
			url: url,
			type: 'post',
			dataType: 'json',
			data:{
				modelIds:id
			},
			success: function(data){
				if(data.code==1){
					
					var businessType=JSON.parse($.cookie("busniessInfo")).type;
					var businessId=JSON.parse($.cookie("busniessInfo")).id;
					getContent(getfilter(),businessId,businessType,nextPage,pageSize);
				}else{
					alert(data.msg);
				}
			}
        });
    }
}
/** 编辑事件 */
function edit(id,businessId,userId,commentId,content,nextPage,pageSize){
	
	var url = root+"cms/replyComment/saveReplyComment";
	$.ajax({
		url: url,
		type: 'post',
		dataType: 'json',
		data:{
			id:id,
			businessUserId:businessId,
			replyComment:content,
			userId:userId,
			commentId:commentId
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
				var businessType=JSON.parse($.cookie("busniessInfo")).type;
				var businessId=JSON.parse($.cookie("busniessInfo")).id;
				getContent(getfilter(),businessId,businessType,nextPage,pageSize);
				}
			else{
				alert(data.message);
			}
		}
    });
    
}