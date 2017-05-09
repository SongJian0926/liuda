$(document).ready(function() {
	// 景点id或者酒店id
	var id = JSON.parse($.cookie("busniessInfo")).goodsId;
	// 商户类型
	var businessType = JSON.parse($.cookie("busniessInfo")).type;
	//商品保存的url(景点或酒店)
	var turl;
	// 获得编辑页面的id
	var gid = getUrlParam("id");
	//存删除的照片
	var photo = null;
	//创建date
	var d=new Date();
	//年
	var year=d.getFullYear();
	//月
	var month=d.getMonth()+1;
	//日
	var day=d.getDate();
	//当前时间
	var time=year+"-"+month+"-"+day;

	
	// 酒店
	if (businessType == 0) {
		$.ajax({
			url : '../../cms/room/findById',
			type : 'post',
			dataType : 'json',
			data : {
				modelId : gid
			},
			success : function(data) {
				console.log(data);
				if (data.code == 1) {
					
					// 编辑页面，查找详细信息
					var tblContentHtml = $("#roomdetailContentTmple").render(data.object);
					$("#submit-form").html(tblContentHtml);
					var tbContentHtml = $("#roomgroupContentTmple").render(data.object);
					$("#submit-bottom-form").html(tbContentHtml);
					//查询酒店类型
					/*$.ajax({
						url : root+'cms/dictionary/findDictionaryNEStatusPage',
						type : 'post',
						dataType : 'json',
						data : {
							pageSize:100000000,
							jsonFilter: "{'search_EQ_type':'"+businessType+"'}"
						},
						success:function(data1){
							if(data1.code == 1){	
								var _options = "<option value=''>请选择</option>";
				            	$.each(data1.object.content,function(){
				            		if(data.object.type==this.id){
				            			_options += "<option value='" + this.id + "' selected>" + this.name + "</option>";
				            		}
				            		else{
				            			_options += "<option value='" + this.id + "' >" + this.name + "</option>";
				            		}
				            	});
				            	$('#type').html(_options);
				            	
							}
							else{
								alert(data1.message);
							}
						}
					});*/
				} else {
					// 添加页面
					var tblContentHtml = $("#roomdetailContentTmple").render("");
					$("#submit-form").html(tblContentHtml);
					var tbContentHtml = $("#roomgroupContentTmple").render("");
					$("#submit-bottom-form").html(tbContentHtml);
				}
			}
		});
	} else {
		// 景点
		$.ajax({
			url : '../../cms/tickets/findById',
			type : 'post',
			dataType : 'json',
			data : {
				modelId : gid
			},
			success : function(data) {
				console.log(data);
				
				if (data.code == 1) {
					// 编辑页面，查找详细信息
					var tblContentHtml = $("#ticketsdetailContentTmple").render(data.object);
					$("#submit-form").html(tblContentHtml);
					var tbContentHtml = $("#ticketsgroupContentTmple").render(data.object);
					$("#submit-bottom-form").html(tbContentHtml);
					if($("#checkAll").is(':checked')){
						$("#validity").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
							$("#endTime").datetimepicker('setStartDate',starttime);
						});
					}
					$("#predTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true,startDate:time});
					//查询景点类型
					/*$.ajax({
						url : root+'cms/dictionary/findDictionaryNEStatusPage',
						type : 'post',
						dataType : 'json',
						data : {
							pageSize:100000000,
							jsonFilter: "{'search_EQ_type':'"+businessType+"'}"
						},
						success:function(data1){
							if(data1.code == 1){	
								var _options = "<option value=''>请选择</option>";
				            	$.each(data1.object.content,function(){
				            		if(data.object.ticketName==this.id){
				            			_options += "<option value='" + this.id + "' selected>" + this.name + "</option>";
				            		}
				            		else{
				            			_options += "<option value='" + this.id + "' >" + this.name + "</option>";
				            		}
				            	});
				            	$('#ticketName').html(_options);
				            	
							}
							else{
								alert(data1.message);
							}
						}
					});*/
				} else {
					// 添加页面
					var tblContentHtml = $("#ticketsdetailContentTmple").render("");
					$("#submit-form").html(tblContentHtml);
					var tbContentHtml = $("#ticketsgroupContentTmple").render("");
					$("#submit-bottom-form").html(tbContentHtml);
					$("#predTime").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true,startDate:time});
				}
			}
		});
	}
	
	// 编辑
	$("#submit-form").on('click','.a-edit',function() {
		if ($(".a-edit").text() == "编辑") {
			// "取消"出现
			$(".a-quxiao").css("visibility","visible");
			// "确定"按钮出现
			$(".a-edit").text("删除");
			// 可勾选的框出现
			$('.left-top-box').show();
		} else {
			if($('.checklogo:checked').closest("li").size()==0){
				alert("请勾选要删除的图片");
				return;
			}
			// 所有被选中的项
			var checkParams = $('input[class="checklogo"]:checked');
			var attr = $("#image").val();
			$.each(checkParams, function() {
				$(this).parents('li').remove();
				attr = attr+ $(this).parents('li').children().first().val()+ ",";
			});
			// 如果所有图片被删除，照相机出现
			if ($('.ul-img li').length <= 4) {
				$('.ul-img li').show();
			}
			// 存入删除的图片路径
			$("#image").attr('value', attr);
			// "取消"消失
			$(".a-quxiao").css("visibility","hidden");
			// "编辑"按钮出现
			$(".a-edit").text("编辑");
			// 可勾选的框消失
			$('.left-top-box').css('display','none');
		}
	});
	
	// 图片的取消按钮
	$("#submit-form").on('click', '.a-quxiao', function() {
		// "取消"消失
		$(".a-quxiao").css("visibility", "hidden");
		// "编辑"按钮出现
		$(".a-edit").text("编辑");
		// 可勾选的框消失
		$('.left-top-box').css('display', 'none');
	});
	
	// 取消
	$("#submit-bottom-form").on('click', '.cacel', function() {
		window.location.href = '../shop/addshopList.jsp';
	});
	
	// 确定按钮事件
	$("#submit-bottom-form").on('click','.sub',function() {
		
		//团购价和有效期
		var groupPrice=$('#groupPrice').val();
		var validity=$('#validity').val();
		photo = $('#image').val();
		if (businessType == 1) {
			//有团购的路径(勿删除)
			turl = '../../cms/tickets/saveGoods?touristId='+ id + '&photo=' + photo +'&groupPrice='+groupPrice+'&validity='+validity;
			//turl = '../../cms/tickets/saveGoodsNoGroup?touristId='+ id + '&photo=' + photo;
		} else {
			//有团购的路径(勿删除)
			turl = '../../cms/room/saveGoods?hotelId='+ id + '&photo=' + photo+'&groupPrice='+groupPrice+'&validity='+validity;
			//turl = '../../cms/room/saveGoodsNoGroup?hotelId='+ id + '&photo=' + photo;
		}
		$('#submit-form').isValid(function(result) {
			//获得是否团购的值
			var checkattr=$('#checkAll').is(':checked');
			var b;
			if(checkattr==true){
				$('#submit-bottom-form').isValid(function(results) {
					if(!results||!result){
					    b=false;
					}
				});
			}else{
				if(!result){
					alert("还有不符合规定的字段填写，请检查！");
					return;
				}
			}
			if(b==false){
				alert("还有不符合规定的字段填写，请检查！");
				return;
			}
			/*if (!result) {
				alert("还有不符合规定的字段填写，请检查！");
				return;
			}*/
			//如果团购被勾选，在对团购的值进行验证
			if($('#checkAll').is(':checked')){
				$('#submit-bottom-form').isValid(function(results) {
					if(!results){
						alert("还有不符合规定的字段填写，请检查！");
						return;
					}
				});
				if($("#submit-bottom-form").find("#validity").val()<$("#submit-form").find("#predTime").val()){
					alert("使用期限不能小于预定时间！");
					return;
				}
				//门票
				if(parseFloat($("#submit-bottom-form").find("#groupPrice").val())>parseFloat($("#submit-form").find("#price").val())){
					alert("团购价格不能大于原价！");
					return;
				}
				//房间
				if(parseFloat($("#submit-bottom-form").find("#groupPrice").val())>parseFloat($("#submit-form").find("#roomPrice").val())){
					alert("团购价格不能大于原价！");
					return;
				}
			}
			if ($('.ul-img li').length == 1) {
				alert("您还没有上传图片");
				return;
			}
			if ($('.left-top-box').css('display') != 'none') {
				alert('还有图片未编辑好');
				return;
			}
			$("#submit-bottom-form").find(".sub").attr("disabled", "disabled");
			$.ajax({
				url : turl,
				type : "POST",
				data : $("#submit-form").serialize(),
				success : function(data) {
					if (data.code == 1) {
						if(gid!=null){
							alert("已提交后台审核");
							$("#submit-bottom-form").find(".sub").removeAttr("disabled");
							window.location.href = '../shop/xiajiashopList.jsp';
							
						}else{
							$("#submit-bottom-form").find(".sub").removeAttr("disabled");
							window.location.href = '../shop/addshopList.jsp';
						}
					
					} else {
						alert(data.message);
					}
				}
			});
		});
	});
	// 参加团购
	$("#submit-bottom-form").on('click','#checkAll',function() {
		var check = $("#checkAll").is(':checked');
		if (check) {
			// 参加
			$(".fco").css("color", "#333333");
			// 文本框可编辑
			$("#groupPrice").removeAttr("readonly");
			//$("#validity").removeAttr("readonly");
			// 团购文本框加验证
			$("#groupPrice").attr("data-rule","required;money");
			$("#validity").attr("data-rule","required;length[0~16]");
			$("#validity").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true,startDate:time});
		} else {
			// 不参加
			$(".fco").css("color", "#d8d8d8");
			// 文本框可编辑
			$("#groupPrice").attr("readonly","readonly");
			$("#validity").attr("readonly","readonly");
			// 清除文本框的值
			$("#groupPrice").val("");
			$("#validity").val("");
			// 去除团购框的验证属性
			$('#submit-bottom-form').validator("destroy");
			//去除日历初始化事件
			$('#validity').datetimepicker('remove');
			}
		});
});
	//参加团购
	$("#submit-form").on('click','#groupPrice',function() {
		$("#msg-box").css("position","static");
	});
	/** 图片上传处理预留,有图片上传可打开此注释 */
	function uploadphoto(modelName) {
		$(document).on('change','#uploadPhotoFile',function() {
			if ($('.left-top-box').css('display') == 'block') {
				alert('编辑状态，不能上传照片');
				return;
			}
		    $.ajaxFileUpload({
				url : '../../cms/' + modelName+ '/photoUpload',
				secureuri : false,
				fileElementId : 'uploadPhotoFile',
				type : 'POST',
				dataType : 'json',
				success : function(data, status) {
					console.log(data);
					if (data) {
						var ds = data.object.split(":");
						if (ds[0] == 1) {
							if (modelName == 'tickets') {
								if ($('.ul-sty li').length == 4) {
									$('.ul-sty li:last').before(' <li class="li-img"><label class="left-top-box" style="display:none"><input type="checkbox" class="checklogo"><span>&nbsp;</span></label><input name="imgUrl" style="display:none" value='+ ds[1]+ '></input><img src="../../'+ ds[1]
															+ '" class="img"></li>');
									$('.ul-sty li:last').hide();
								} else {
									$('.ul-sty li:last').before(' <li class="li-img"><label class="left-top-box" style="display:none"><input type="checkbox" class="checklogo"><span>&nbsp;</span></label><input name="imgUrl" style="display:none" value='+ ds[1]+ '></input><img src="../../'+ ds[1]
															+ '" class="img"></li>');
								}
							}
							if (modelName == 'room') {
								if ($('.ul-img li').length == 4) {
									$('.ul-img li:last').before(' <li class="li-img"><label class="left-top-box" style="display:none"><input type="checkbox" class="checklogo"><span>&nbsp;</span></label><input name="logo" style="display:none" value='+ ds[1]+ '></input><img src="../../'+ ds[1]
															+ '" class="img"></li>');
									$('.ul-img li:last')
											.hide();
								} else {
									$('.ul-img li:last').before(' <li class="li-img"><label class="left-top-box" style="display:none"><input type="checkbox" class="checklogo"><span>&nbsp;</span></label><input name="logo" style="display:none" value='+ ds[1]+ '></input><img src="../../'+ ds[1]
															+ '" class="img"></li>');
								}
							}
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