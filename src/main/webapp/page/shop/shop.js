function upload(modelName) {
	$(document).on('change', '.upload_file', function() {
		var _id = $(this).attr('id');
		var _index = $(this).attr('data-type');
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : _id,
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$("#imgUrl" + _index).val(ds[1]);
						$("#myImageShow" + _index).attr('src', "../../" + ds[1]);
						$("#myImageShow" + _index).attr('width', '100px');
						$("#myImageShow" + _index).attr('height', '100px');
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
function upload1(modelName){
	$(document).on('change', '#uploadPhotoFile9', function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile9',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$("#imgUrl9").val(ds[1]);
						$("#myImageShow9").attr('src', "../../" + ds[1]);
						$("#myImageShow9").attr('width', '100px');
						$("#myImageShow9").attr('height', '100px');
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

//返回函数
function backAction(backurl) {
	if (getUrlParam('from')) {
		location.href = getUrlParam('from');
	} else {
		window.location.href = backurl;
	}
}

$(document).ready(function(){
	
	upload('shop');
	upload1('standard');
	
	//加载数据
	getDataById(root + 'cms/shop/findShopById',"#shopTmpl","#shopContent");
	//定义变量，判断是新增还是修改,0是新增,1是修改
	var m="";
	//添加图片
	var num = 0;
	$('#shopContent').on('click','.add_img',function(){
		var num = $(this).parent().find('.upload_div').length;
		var index = $(this).attr('data-type');
		if(index == 0){
			num += 1;
			var ht = '<div class="upload_div" style="float: left; margin-right: 15px;"><img id="myImageShow' + num + '" src="" width="100px" height="100px" /><input type="hidden" id="imgUrl' + num + '" name="imgUrl" value=""/><input type="file" id="uploadPhotoFile' + num + '" name="photoFile" class="upload_file" data-type="' + num + '"></div>';
			$(this).before(ht);
			if($(this).parent().find('.upload_div').length >= 4){
				$(this).hide();
			}
		}else if(index == 1){
			num += 5;
			var ht = '<div class="upload_div" style="float: left; margin-right: 15px;"><img id="myImageShow' + num + '" src="" width="100px" height="100px" /><input type="hidden" id="imgUrl' + num + '" name="imgUrl" value=""/><input type="file" id="uploadPhotoFile' + num + '" name="photoFile" class="upload_file" data-type="' + num + '"></div>';
			$(this).before(ht);
			if($(this).parent().find('.upload_div').length >= 4){
				$(this).hide();
			}
		}else{
			$('#add_standard').val('');
			$('#porperty').val('');
			$('#myImageShow9').attr('src','');
			$('#imgUrl9').val('');
			$('#stocks').val('');
			$('#price').val('');
			$('#groupBuy').attr('checked',false);
			$('#groupPrice').val('');
			$('#validity').val('');
			m=0;
			$('#standard_Modal').modal();
		}
	});
	
	//是否参加团购
	$('#groupBuy').change(function(){
		//创建date
		var d=new Date();
		//年
		var year=d.getFullYear();
		//月
		var month=d.getMonth()+1;
		//日
		var day=d.getDate();
		var time=year+"-"+month+"-"+day;
		if($(this).prop('checked')){
			$("#validity").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true,startDate:time});
			$('#groupPrice').attr({'readOnly':false,'data-rule':'团购价格:required;number;'});
		}else{
			$("#validity").datetimepicker('remove').val('');
			$('#groupPrice').attr({'readOnly':true,'data-rule':''}).val('');
		}
	});
	
	//规格属性确定
	$('#standard_update').click(function(){
		$('#standard_Form').isValid(function(result){
			if (!result) {
				alert("还有不符合规定的字段填写，请检查！");
				return;
			}
			if($("#imgUrl9").val().length==0){
				alert("图片不能为空！");
				return;
			}
			var _groupBuy = "",_groupBuyH = "",_validity = "";
			if($('#groupBuy').prop('checked')){
				_groupBuy = "是";
				_groupBuyH = "1";
				_validity = $('#validity').val();
			}else{
				_groupBuy = "否";
				_groupBuyH = "0";
			}
			
			if($("#groupBuy").prop('checked')){
				if($("#groupPrice").val().length<1){
					alert('请输入团购价格！');
					return;
				}
				if(parseFloat($("#groupPrice").val())>parseFloat($("#price").val())){
					alert('团购价格不能大于原价！');
					return;
				}
				if($("#validity").val().length<1){
					alert('请选择有效期!');
					return;
				}
			}
			$("#standard_Form").addClass('disable_btn');
			var _content = "<tr><td><input ckId='" + $('#add_standard').val() + "' ckGroupBuy='" + _groupBuyH + "' class='ckSelect' type='checkbox' ckType='add' /></td>";
			_content += "<td>" + $('#porperty').val() + "</td><td><img src=../../" + $('#imgUrl9').val() + " width='50' height='50' data-url='" + $('#imgUrl9').val() + "' /></td><td>" + $('#stocks').val() + "</td><td>" + $('#price').val() + "</td><td>" + _groupBuy + "</td><td>" + $('#groupPrice').val() + "</td><td>" + _validity + "</td></tr>";
			//if($('#add_standard').val()){
			if(m==1){
				var _this = $(".ckSelect:checked");
				var _td = $(_this).parents('tr').find('td');
				$(_this).attr('ckGroupBuy',_groupBuyH);
				$(_td).eq(1).html($('#porperty').val());
				$(_td).eq(2).find('img').attr({'src':'../../'+$('#imgUrl9').val(),'data-url':$('#imgUrl9').val()});
				$(_td).eq(3).html($('#stocks').val());
				$(_td).eq(4).html($('#price').val());
				$(_td).eq(5).html(_groupBuy);
				$(_td).eq(6).html($('#groupPrice').val());
				$(_td).eq(7).html(_validity);
				$(_this).attr('checked',false);
			}else{
				$('#shopContent').find('#standard_content').append(_content);
			}
			$("#standard_Form").removeClass('disable_btn');
			$('#standard_Modal').modal('hide');
		});
		
	});
	
	//保存商品信息
	$('#saveOrupdate').click(function(){
		
		$('#submit_form').isValid(function(result) {
			if (!result) {
				alert("还有不符合规定的字段填写，请检查！");
				return;
			}
			//轮播图
			var _imgurls = [];
			$('#shopContent').find('#imgUrls_div').find('input[name="imgUrl"]').each(function(){
				_imgurls.push($(this).val());
			});
			//介绍图
			var _desimgs = [];
			$('#shopContent').find('#desImgs_div').find('input[name="imgUrl"]').each(function(){
				_desimgs.push($(this).val());
			});
			//规格属性
			var _standard = "[";
			$('#shopContent').find('#standard_content').find('tr').each(function(){
				_standard += "{";
				_standard += "\"id\":\"" + $(this).find('input[type="checkbox"]').attr('ckId') + "\",";
				_standard += "\"groupBuy\":\"" + $(this).find('input[type="checkbox"]').attr('ckGroupBuy') + "\",";
				_standard += "\"porperty\":\"" + $(this).find('td').eq(1).html() + "\",";
				_standard += "\"imgUrl\":\"" + $(this).find('img').attr('data-url') + "\",";
				_standard += "\"stocks\":\"" + $(this).find('td').eq(3).html() + "\",";
				_standard += "\"price\":\"" + $(this).find('td').eq(4).html() + "\",";
				_standard += "\"groupPrice\":\"" + $(this).find('td').eq(6).html() + "\",";
				_standard += "\"validity\":\"" + $(this).find('td').eq(7).html() + "\",";
				_standard += "\"createUser\":\"" + $(this).find('input[type="checkbox"]').attr('ckType') + "\"";
				_standard += "}";
				if($('#shopContent').find('#standard_content').find('tr').index($(this)) != $('#shopContent').find('#standard_content').find('tr').length - 1){
					_standard += ",";
				}
			});
			_standard += "]";
			if(_standard == "[]"){
				alert('请至少添加一个规格属性');
				return;
			}
			if($("#imgUrl0").val().length==0){
				alert('商品图片不能为空！');
				return;
			}
			$("#saveOrupdate").addClass('disable_btn');
			$.ajax({
				url : root + 'cms/shop/saveShop',
				type : "POST",
				data : {
					id: $('#tId').val(),
					shopName: $('#shopName').val(),
					imgUrl: $('#imgUrl0').val(),
					shopDesc: $('#shopDesc').val(),
					limitNumber: $('#limitNumber').val(),
					imgurls: _imgurls.toString(),
					desimgs: _desimgs.toString(),
					standard: _standard
				},
				success : function(data) {
					if (data.code == 1) {
						backAction('shopList.jsp');
					} else {
						$("#saveOrupdate").removeClass('disable_btn');
						alert(data.message);
					}
				}
			});
		});
	});
	
	//编辑属性
	$('#shopContent').on('click','.edit_img',function(){
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
		var _this = $(".ckSelect:checked");
		var _td = $(_this).parents('tr').find('td');
		
		$('#add_standard').val($(_this).attr('ckId'));
		$('#porperty').val($(_td).eq(1).html());
		//判断是否有图
		if($(_this).parents('tr').find('img').attr('data-url').length==0){
			alert($(_this).parents('tr').find('img').attr('data-url'));
			$('#myImageShow9').attr('src','');
		}else{
			$('#myImageShow9').attr('src','../../' + $(_this).parents('tr').find('img').attr('data-url'));
		}
		
		$('#imgUrl9').val($(_this).parents('tr').find('img').attr('data-url'));
		$('#stocks').val($(_td).eq(3).html());
		$('#price').val($(_td).eq(4).html());
		
		/*$('#groupBuy').attr('checked',$(_this).attr('ckGroupBuy') == 0 ? false : true);
		$('#groupPrice').val($(_td).eq(6).html());
		$('#validity').val($(_td).eq(7).html());
		m=1;
		$('#standard_Modal').modal();*/
		
		if($(_this).attr('ckGroupBuy') == 1 ){
			//创建date
			var d=new Date();
			//年
			var year=d.getFullYear();
			//月
			var month=d.getMonth()+1;
			//日
			var day=d.getDate();
			var time=year+"-"+month+"-"+day;
			//$('#groupBuy').attr('checked',true);
			$("#groupBuy").prop('checked',true);
			$("#validity").datetimepicker({minView: "month",format: 'yyyy-mm-dd',language : 'zh-CN',autoclose: true,startDate:time});
			$('#groupPrice').attr({'readOnly':false,'data-rule':'团购价格:money;number;nozero'});
			//$("#validity").datetimepicker('add').val('');
			//$('#groupPrice').attr({'readOnly':false,'data-rule':'团购价格不能为空'}).val('');
		}
		if($(_this).attr('ckGroupBuy') == 0 ){
			$('#groupBuy').prop('checked',false);
			$("#validity").datetimepicker('remove').val('');
			$('#groupPrice').attr({'readOnly':true,'data-rule':''}).val('');
		}
		
		//$('#groupBuy').attr('checked',$(_this).attr('ckGroupBuy') == 0 ? false : true);
		$('#groupPrice').val($(_td).eq(6).html());
		$('#validity').val($(_td).eq(7).html());
		m=1;
		$('#standard_Modal').modal();
		
	});
	
	//删除属性
	$('#shopContent').on('click','.delete_img',function(){
		var deleteArray = [];
		$(".ckSelect").each(function(){
	    	var check=$(this).is(':checked');
	    	if(check){
				var _id =$(this).attr("ckId");
				deleteArray.push(_id);
	        }
	    });
		if(deleteArray.length == 0){
			alert("请选择要删除的项"); return;
		}
		if(confirm("确定要删除选中规格吗？")){
			$(".ckSelect:checked").each(function(){
		    	$(this).parents('tr').remove();
		    });
		}
	});
	
	//多选框全选与全否效果
	$('#shopContent').on('change','#_selectAll',function () {
		$('#shopContent').find('.ckSelect').prop('checked', $(this).prop('checked'));
    });
	$('#shopContent').on('click','.ckSelect',function () {
		$('#shopContent').find('#_selectAll').prop('checked', $('#shopContent').find('.ckSelect').length == $('#shopContent').find('.ckSelect').filter(':checked').length);
    });
	
});

//通过ID获取单条数据
function getDataById(url, tmpl, content) {
	var tId = getUrlParam("id");
	if (!tId) {
		alert('id参数不能为空');
		return;
	}
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : {
			modelId : tId
		},
		success : function(data) {
			console.log(data);
			if (data.code == 1) {
				var contentHtml = $(tmpl).render(data.object, {mydata : data});
				$(content).html(contentHtml);
				//加载轮播图
				if(data.object.imgUrlpics){
					var ht = "";
					$.each(data.object.imgUrlpics,function(i){
						ht += '<div class="upload_div" style="float: left; margin-right: 15px;"><img id="myImageShow' + (i+1) + '" src="../../' + this + '" width="100px" height="100px" /><input type="hidden" id="imgUrl' + (i+1) + '" name="imgUrl" value="' + this + '"/><input type="file" id="uploadPhotoFile' + (i+1) + '" name="photoFile" class="upload_file" data-type="' + (i+1) + '"></div>';
					});
					$(content).find('.add_img').eq(0).before(ht);
					if(data.object.imgUrlpics.length >= 4){
						$(content).find('.add_img').eq(0).hide();
					}
				}
				//加载介绍图
				if(data.object.pics1){
					var desImg = "";
					$.each(data.object.pics1,function(i){
						desImg += '<div class="upload_div" style="float: left; margin-right: 15px;"><img id="myImageShow' + (i+5) + '" src="../../' + this + '" width="100px" height="100px" /><input type="hidden" id="imgUrl' + (i+5) + '" name="imgUrl" value="' + this + '"/><input type="file" id="uploadPhotoFile' + (i+5) + '" name="photoFile" class="upload_file" data-type="' + (i+5) + '"></div>';
					});
					$(content).find('.add_img').eq(1).before(desImg);
					if(data.object.pics1.length >= 4){
						$(content).find('.add_img').eq(1).hide();
					}
				}
				//加载规格属性
				if(data.object.standards){
					var _standard = "";
					$.each(data.object.standards,function(i){
						var _groupBuy = "",_groupPrice = "",_validity = "";
						if(this.groupBuy == 0){
							_groupBuy = "否";
						}else{
							_groupBuy = "是";
							_groupPrice = this.groupPrice;
							_validity = this.validity;
						}
						_standard += "<tr><td><input ckId='" + this.id + "' ckGroupBuy='" + this.groupBuy + "' class='ckSelect' type='checkbox' ckType='update' /></td>";
						if(this.imgUrl.length==0){
							_standard += "<td>" + this.porperty + "</td><td><img src='' width='50' height='50' data-url='" + this.imgUrl + "' /></td><td>" + this.stocks + "</td><td>" + this.price + "</td><td>" + _groupBuy + "</td><td>" + _groupPrice + "</td><td>" + _validity + "</td></tr>";
						}else{
							_standard += "<td>" + this.porperty + "</td><td><img src=../../" + this.imgUrl + " width='50' height='50' data-url='" + this.imgUrl + "' /></td><td>" + this.stocks + "</td><td>" + this.price + "</td><td>" + _groupBuy + "</td><td>" + _groupPrice + "</td><td>" + _validity + "</td></tr>";
						}
						
					});
					$(content).find('#standard_content').html(_standard);
				}
			} else {
				var contentHtml = $(tmpl).render("");
				$(content).html(contentHtml);
			}
		}
	});
}