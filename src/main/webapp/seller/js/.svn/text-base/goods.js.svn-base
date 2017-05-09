$.views.converters({
	"subString":function(str){
		
		if(str==null || str==""){
		
			return "";
		}
		
    	if(str.length>9){
    	
    		return str.substring(0,9)+"...";
    	}else{
    	
    		return str;
    	}
	},
});
// 酒店或者门票的id
var id = JSON.parse($.cookie("busniessInfo")).goodsId;
// 商户类型
var businessType = JSON.parse($.cookie("busniessInfo")).type;
// 分页变量
var nextPage = 0;
var pageSize = 6;
var status=null;
//列表数据的显示   
function list(status,businessType,id,nextPage,pageSize){
	$.ajax({
		url:'../../cms/room/findRoomOrTicketsList',
		type:'post',
		dataType:'json',
		data:{				
			id:id,
			businessType:businessType,
			status:status,
			nextPage:nextPage,
			pageSize:pageSize
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){	
				if(data.object.content.length!=0){
					//如果是酒店
					if(businessType==0){
						var tblContentHtml = $("#roomlistContentTmple").render(data.object.content);
						$(".show-list").html(tblContentHtml);
						//加载分页器
		            	loadTmpl.renderExternalTemplate("seller_page", "#displayPage",data.object, 1);
		            	//上下架消失、全选消失
		            	$(".okDown").css('display', 'none');
						$(".canelUp").css('display', 'none');
						$(".check-all").css('display', 'none');
						//左边随右边内容高度变
						$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
					}else{
						var tblContentHtml = $("#ticketslistContentTmple").render(data.object.content);
						$(".show-list").html(tblContentHtml);
						//加载分页器
		            	loadTmpl.renderExternalTemplate("seller_page", "#displayPage",data.object, 1);
		            	//上下架消失、全选消失
		            	$(".okDown").css('display', 'none');
						$(".canelUp").css('display', 'none');
						$(".check-all").css('display', 'none');
						//左边随右边内容高度变
						$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
					}
				}else{
					    //无商品显示，有商品消失
					    $('.show-list').css('display','none');
					    $('.nogoods').show();
					    //分页消失
					    $('#displayPage').css('display','none');
					    //上下架消失、全选消失
		            	$(".okDown").css('display', 'none');
						$(".canelUp").css('display', 'none');
						$(".check-all").css('display', 'none');
						//左边随右边内容高度变
						$('.left_child_menu').css({'height':$('.right_content').height() + 'px'});
				}
			}
			else{
				alert(data.message);
			}
		}
	});	
}
//商品的上架或者下架
function upAnddown(status,businessType,id){
	var checkError = false;
	var delArray = [];
	$(".checkitem").each(function(){
    	var check = $(this).is(':checked');
    	if(check){
			var _id = $(this).attr("ckId");
			delArray.push(_id);
        }
    });
	
    if(delArray.length == 0){
		alert("请选择项"); return;
    }
	var url = '../../cms/room/upAnddown?modelIds=' + delArray.join(",");
	$.ajax({
		url: url,
		type: 'post',
		dataType: 'json',
		data:{				
			businessType:businessType,
			status:status,
			id:id
		},
		success: function(data){
			if(data.code==1){
				if(status==2){
					status=2;
					list(status,businessType,id,nextPage,pageSize);
				}else{
					status=-1;
					list(status,businessType,id,nextPage,pageSize);
				}
			}else{
				alert(data.msg);
			}
		}
    });
}