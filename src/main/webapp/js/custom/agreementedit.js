$(function(){
		/** 富文本处理全局参数 */
		var ue;
		//获取参数id
		var tId=getUrlParam("id");
		
		if(!tId){
			alert('id参数不能为空');
			return;
		}
		//根据Id查询记录
		$.ajax({
			url:root + 'cms/agreement/findAgreementById',
			type:'post',
			dataType:'json',
			data:{
				modelId:tId
			},
			success:function(data){
				console.log(data);
				//如果返回数据，将数据渲染到模板中；否则，模板为空
				if(data.code == 1){
					var contentHtml=$('#agreementTmpl').render(data.object,{mydata: data});
					$("#agreementContent").html(contentHtml);
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
						if(data.object.content)
					        ue.setContent(data.object.content);
				    });
					
				}else{
					var contentHtml=$('#agreementTmpl').render("");
					$("#agreementContent").html(contentHtml);
				}
				
			}
		});
		

		//点击确认新增或者编辑保存数据
		$("#saveOrupdate").click(function(){
    		$('#submit_form').isValid(function(result){
			    if(!result){
					alert("还有不符合规定的字段填写，请检查！");
					return;
			    }
			  
			    $("#saveOrupdate").addClass('disable_btn');
			    var id=$("#tId").val();
				$.ajax({
					url:root + 'cms/agreement/saveAgreement',
					type:"POST",
					data:{
						content:ue.getContent(),
						type:$('#search_type').val(),
						id:id
					},
					success:function(data){
						if(data.code==1){
							var backurl="agreementList.jsp";
							backAction(backurl);
						}else{
							alert(data.message);
							$("#saveOrupdate").removeClass('disable_btn');
						}
					}
				});
			});
		});
		
	});
