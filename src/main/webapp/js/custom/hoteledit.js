
/** 上传图片 */
upload("hotel");
$(function(){
		
		//获取参数id
		var tId=getUrlParam("id");
		if(!tId){
			alert('id参数不能为空');
			return;
		}
		//通过ID获取单条数据
		$.ajax({
			url:root + 'cms/hotel/findOneById',
			type:'post',
			dataType:'json',
			data:{
				modelId:tId
			},
			success:function(data){
				console.log(data);
				if(data.code == 1){
					var contentHtml=$("#hotelTmpl").render(data.object,{mydata: data});
					$("#hotelContent").html(contentHtml);
				}else{
					var contentHtml=$("#hotelTmpl").render("");
					$("#hotelContent").html(contentHtml);
				}
				//加载轮播图
				var imagetype=0;
				$.ajax({
					url:root + 'cms/images/findImagesNEStatusPage',
					type:'post',
					dataType:'json',
					data:{
						jsonFilter: "{'search_EQ_objectId':'"+tId+"','search_EQ_type':'"+imagetype+"'}"
					},
					success:function(data1){
						console.log(data1);
						
						if(data1.code == 1){	
							
						}
						else{
							alert(data1.message);
						}
					}
				});
			}
		});
		//保存
		$('#saveOrupdate').click(function(){
			$('#submit_form').isValid(function(result){
			    if(!result){
					alert("还有不符合规定的字段填写，请检查！");
					return;
			    }
			    if($("#imgUrl").val().length==0){
					alert("请上传图片！");
					return;
			    }
			    if($("#imgUrl1").val().length==0&&$("#imgUrl2").val().length==0&&$("#imgUrl3").val().length==0&&$("#imgUrl4").val().length==0){
					alert("请至少上传一张轮播图片！");
					return;
			    }
			    $.ajax({
					url:root + 'cms/hotel/newSaveHotel',
					type:"POST",
					data:{
						hotelName:$("#hotelName").val(),
						picurl:$("#imgUrl").val(),
						hotelType:$("#hotelType").val(),
						telphone:$("#telphone").val(),
						address:$("#address").val(),
						policy:$("#policy").val(),
						prompt:$("#prompt").val(),
						introduce:$("#introduce").val(),
						photo1:$("#imgUrl1").val(),
						photo2:$("#imgUrl2").val(),
						photo3:$("#imgUrl3").val(),
						photo4:$("#imgUrl4").val(),
						status:1,
						id:tId
					},
					success:function(data){
						console.log(data); 
						if(data.code == 1){
							var backurl="hotelList.jsp";
							backAction(backurl);
						}else{
							alert(data.message);
						}
					}
				});
			});
		});
		
});
//返回函数
function backAction(backurl){
	if(getUrlParam('from')){
		location.href = getUrlParam('from');
	}else{
		window.location.href = backurl;
	}
}