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
function upload(modelName) {
	$(document).on('change', '#uploadPhotoFile', function() {
		$.ajaxFileUpload({
			url : '../../cms/' + modelName + '/photoUpload',
			secureuri : false,
			fileElementId : 'uploadPhotoFile',
			type : 'POST',
			dataType : 'json',
			success : function(data, status) {
				if (data) {
					var ds = data.object.split(":");
					if (ds[0] == 1) {
						$("#logo").val(ds[1]);
						$("#myImageShow").attr('src', "../../" + ds[1]);
						$("#myImageShow").attr('width', '100px');
						$("#myImageShow").attr('height', '100px');
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
//定义变量，判断是新增还是修改,0是新增,1是修改
var m="";
$(document).ready(function(){
	//多选框全选与全否效果
	$('#voteContent').on('change','#_selectAll',function () {
		$('#voteContent').find('.ckSelect').prop('checked', $(this).prop('checked'));
    });
	$('#voteContent').on('click','.ckSelect',function () {
		$('#voteContent').find('#_selectAll').prop('checked', $('#voteContent').find('.ckSelect').length == $('#voteContent').find('.ckSelect').filter(':checked').length);
    });
	//规格属性确定
	$('#vote_update').click(function(){
		$('#standard_Form').isValid(function(result){
			if(!result){
				alert("还有不符合的字段，请检查");
				return;
			}
			if(result){
					var _content = "<tr><td><input ckId='" + $('#add_standard').val() + "' class='ckSelect' type='checkbox' ckType='add' /></td>";
					_content += "<td>" + $('#standardName').val() +"</td></tr>";
					if(m==0){
						$('#voteContent').find('#standard_content').append(_content);
					}else if(m==1){
						var _this = $(".ckSelect:checked");
						var _td = $(_this).parents('tr').find('td');
						$(_td).eq(1).html($('#standardName').val());
					}
				$('#vote_Modal').modal('hide');
		    }
		});
	});
	//新增
	$('#voteContent').on('click','.add_img',function(){
		$('#add_standard').val('');
		$('#standardName').val('');
		m=0;
		$('#vote_Modal').modal();
	});
	//编辑
	$('#voteContent').on('click','.edit_img',function(){
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
		$('#standardName').val($(_td).eq(1).html());
		m=1;
		$('#vote_Modal').modal();
	});
	//删除
	$('#voteContent').on('click','.delete_img',function(){
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
		if(confirm("确定要删除该选项吗？")){
			$(".ckSelect:checked").each(function(){
		    	$(this).parents('tr').remove();
		    });
		}
	});
	//保存
	$('#saveOrupdate').click(function(){
		$('#submit_form').isValid(function(result) {
			if(!result){
				alert("还有不符合规定的字段填写，请检查！");
				return;
		    }
			if($('#logo').val()==null || $('#logo').val()==0){
				alert("您还没有上传图片");
				return;
			}
			//规格属性
			var _standard = "[";
			$('#voteContent').find('#standard_content').find('tr').each(function(){
				_standard += "{";
				_standard += "\"id\":\"" + $(this).find('input[type="checkbox"]').attr('ckId') + "\",";
				_standard += "\"optionName\":\"" +  $(this).find('td').eq(1).html() + "\",";
				_standard += "}";
			});
			_standard += "]";
			if(_standard == "[]"){
				alert('请至少添加一个投票选项');
				return;
			}
			if(ue.getContent()==null || ue.getContent() == ""){
				alert("投票说明不能为空");
				return;
			}
			$("#saveOrupdate").addClass('disable_btn');
			$.ajax({
				url : root + 'cms/vote/saveVoteing',
				type : "POST",
				data : {
					id: $('#tId').val(),
					title: $('#title').val(),
					logo: $('#logo').val(),
					isRadio: $('#isRadio').val(),
					deadline: $("#deadline").val(),
					content: ue.getContent(),
					standard: _standard
				},
				success : function(data) {
					if (data.code == 1) {
						backAction('voteList.jsp');
					} else {
						$("#saveOrupdate").removeClass('disable_btn');
						alert(data.message);
					}
				}
			});
		});
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
				var contentHtml = $(tmpl).render(data.object, {
					mydata : data
				});
				$(content).html(contentHtml);
				
				var voteOptionVoList=data.object.voteOptionVoList;
				if(voteOptionVoList){
					var _content = "";
					for(var i=0;i<voteOptionVoList.length;i++){
						 _content +="<tr><td><input ckId='" + voteOptionVoList[i].id + "' class='ckSelect' type='checkbox' ckType='add' /></td>";
						 _content += "<td>" + voteOptionVoList[i].optionName +"</td></tr>";
					}
					$(content).find('#standard_content').html(_content);
				}
				//实例化富文本编辑器
				ue = UE.getEditor('content',{toolbars:[[
		            'fullscreen', 'source', '|', 'undo', 'redo', '|',
		            'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
		            'rowspacingtop', 'rowspacingbottom', 'lineheight', '|'],
		            ['customstyle', 'paragraph','fontfamily', 'fontsize', '|',
		            'directionalityltr', 'directionalityrtl', 'indent', '|',
		            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
		           '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|'],
		            ['simpleupload',  'insertvideo', 'pagebreak', 'template', 'background', '|',
		            'horizontal', 'date', 'time', 'spechars',  '|',
		            'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts'
		           
		        ]]});
				ue.addListener("ready", function () {
				        // editor准备好之后才可以使用
					if(data.object.content){
				        ue.setContent(data.object.content);
					}
			    });
				//日期加载器
				$("#deadline").datetimepicker({minView: "hour",format: 'yyyy-mm-dd hh:ii', startDate: new Date(),language : 'zh-CN',autoclose: true}).on('changeDate',function(ev){
					$("#deadline").datetimepicker('hide');
            	});
			} else {
				var contentHtml = $(tmpl).render("");
				$(content).html(contentHtml);
			}
		}
	});
}
