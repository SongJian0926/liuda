//通过ID获取单条数据
function getDataById1(url,tmpl,content){
	var tId=getUrlParam("id");

	if(!tId){
		alert('id参数不能为空');
		return;
	}
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		data:{
			modelId:tId
		},
		success:function(data){
			console.log(data);
			if(data.code == 1){
				var contentHtml=$(tmpl).render(data.object,{mydata: data});
				$(content).html(contentHtml);
				
			}else{
				var contentHtml=$(tmpl).render("");
				$(content).html(contentHtml);
			}
			//从数据库中查询字典主表Id
			
			$.ajax({
				url:root+'cms/dict/findDictNEStatusPage',
				dataType:'json',
				type:'POST',
				data:{		
					pageSize:100000,
					
				},
				success:function(data1){
					if(data1.code == 1){	
						var _options = "<option value=''>请选择</option>";
		            	$.each(data1.object.content,function(){
		            		if(data.object.dictId==this.id){
		            			_options += "<option value='" + this.id + "' selected>" + this.dictName + "</option>";
		            		}
		            		else{
		            			_options += "<option value='" + this.id + "' >" + this.dictName + "</option>";
		            		}
		            	});
		            	$('#dictId').html(_options);
		            	
					}
					else{
						alert(data1.message);
					}
				}
			});
		}
	});
}