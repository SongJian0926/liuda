<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>结算管理编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript">
		$(function(){
			//获取参数id
			var tId=getUrlParam("id");
			
			if(!tId){
				alert('id参数不能为空');
				return;
			}
			//根据Id查询记录
			$.ajax({
				url:root + 'cms/businessInfo/findBalanceById',
				type:'post',
				dataType:'json',
				data:{
					modelId:tId
				},
				success:function(data){
					console.log(data);
					//如果返回数据，将数据渲染到模板中；否则，模板为空
					if(data.code == 1){
						var contentHtml=$('#balanceTmpl').render(data.object,{mydata: data});
						$("#balanceContent").html(contentHtml);
					}else{
						alert(data.message);
					}
				}
			});
			/* $("#balanceContent").on("click","#propotion",function(){
			 
			}); */
			var propotions=0;
			$("#balanceContent").on("blur","#propotion",function(){
			
			  var x=$("#propotion").val();
			  if(parseInt(x)>100||parseInt(x)<1){
			  	return;
			  }
			  var y=100-parseInt(x);
			  propotions=y;
			});
			/* $("#balanceContent").on("blur","#propotions",function(){
			  var x=$("#propotions").val();
			   if(parseInt(x)>100||parseInt(x)<1){
			  	return;
			  }
			  var y=100-parseInt(x);
			  $("#propotion").val(y);
			}); */
			//点击确认
			$("#saveOrupdate").click(function(){
				$('#submit_form').isValid(function(result){
				    if(!result){
						alert("还有不符合规定的字段填写，请检查！");
						return;
				    }
				  //判断账期
				   /* var a=$('#accountPeriod').val();
				   var b=$('#accountPeriods').val();
				  
				   if(parseInt(a)>parseInt(b)){
				    	alert("账期的截止日期不能小于开始日期");
				    	return;
				    }
				   */
				    $.ajax({
					url:root + 'cms/businessInfo/newSaveBalance',
					type:"POST",
					data:{
						accountPeriod:$('#accountPeriod').val(),
						propotion:$('#propotion').val(),
						/* accountPeriods:$('#accountPeriods').val(), */
						propotions:100-$('#propotion').val(),
						id:tId
					},
					success:function(data){
						if(data.code==1){
							var backurl="balanceList.jsp";
							backAction(backurl);
						}else{
							alert(data.message);
						}
					}
				});
				 });
				 
				
			});
		});
        </script>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box" style="height:900px">
                      <div class="box-header">
                      <br>
                          <input type="button" value="确定" id="saveOrupdate" class="btn btn-success">
                          <button onclick="backAction('addressList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="balanceContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="balanceTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">酒店名称：</label>
       				 <div class="col-md-3">
						<input type="text" readOnly class="form-control input-sm" data-rule="required;length[0~50]" id="objectName" name="objectName" value="{{:hotelVo.hotelName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">账期：</label>
      				 
<div class="col-md-3">
{{!--<select name="accountPeriod" id="accountPeriod" value="{{:accountPeriod}}">
<option value="1" {{if accountPeriod==1}}selected{{/if}}>1</option>
<option value="2" {{if accountPeriod==2}}selected{{/if}}>2</option>
<option value="3" {{if accountPeriod==3}}selected{{/if}}>3</option>
<option value="4" {{if accountPeriod==4}}selected{{/if}}>4</option>
<option value="5" {{if accountPeriod==5}}selected{{/if}}>5</option>
<option value="6" {{if accountPeriod==6}}selected{{/if}}>6</option>
<option value="7" {{if accountPeriod==7}}selected{{/if}}>7</option>
<option value="8" {{if accountPeriod==8}}selected{{/if}}>8</option>
<option value="9" {{if accountPeriod==9}}selected{{/if}}>9</option>
<option value="10" {{if accountPeriod==10}}selected{{/if}}>10</option>
<option value="11" {{if accountPeriod==11}}selected{{/if}}>11</option>
<option value="12" {{if accountPeriod==12}}selected{{/if}}>12</option>
<option value="13" {{if accountPeriod==13}}selected{{/if}}>13</option>
<option value="14" {{if accountPeriod==14}}selected{{/if}}>14</option>
<option value="15" {{if accountPeriod==15}}selected{{/if}}>15</option>
<option value="16" {{if accountPeriod==16}}selected{{/if}}>16</option>
<option value="17" {{if accountPeriod==17}}selected{{/if}}>17</option>
<option value="18" {{if accountPeriod==18}}selected{{/if}}>18</option>
<option value="19" {{if accountPeriod==19}}selected{{/if}}>19</option>
<option value="20" {{if accountPeriod==20}}selected{{/if}}>20</option>
<option value="21" {{if accountPeriod==21}}selected{{/if}}>21</option>
<option value="22" {{if accountPeriod==22}}selected{{/if}}>22</option>
<option value="23" {{if accountPeriod==23}}selected{{/if}}>23</option>
<option value="24" {{if accountPeriod==24}}selected{{/if}}>24</option>
<option value="25" {{if accountPeriod==25}}selected{{/if}}>25</option>
<option value="26" {{if accountPeriod==26}}selected{{/if}}>26</option>
<option value="27" {{if accountPeriod==27}}selected{{/if}}>27</option>
<option value="28" {{if accountPeriod==28}}selected{{/if}}>28</option>
<option value="29" {{if accountPeriod==29}}selected{{/if}}>29</option>
<option value="30" {{if accountPeriod==30}}selected{{/if}}>30</option>
<option value="31" {{if accountPeriod==31}}selected{{/if}}>31</option>
</select>号到
<select name="accountPeriods" id="accountPeriods" value="{{:accountPeriods}}">
<option value="1" {{if accountPeriods==1}}selected{{/if}}>1</option>
<option value="2" {{if accountPeriods==2}}selected{{/if}}>2</option>
<option value="3" {{if accountPeriods==3}}selected{{/if}}>3</option>
<option value="4" {{if accountPeriods==4}}selected{{/if}}>4</option>
<option value="5" {{if accountPeriods==5}}selected{{/if}}>5</option>
<option value="6" {{if accountPeriods==6}}selected{{/if}}>6</option>
<option value="7" {{if accountPeriods==7}}selected{{/if}}>7</option>
<option value="8" {{if accountPeriods==8}}selected{{/if}}>8</option>
<option value="9" {{if accountPeriods==9}}selected{{/if}}>9</option>
<option value="10" {{if accountPeriods==10}}selected{{/if}}>10</option>
<option value="11" {{if accountPeriods==11}}selected{{/if}}>11</option>
<option value="12" {{if accountPeriods==12}}selected{{/if}}>12</option>
<option value="13" {{if accountPeriods==13}}selected{{/if}}>13</option>
<option value="14" {{if accountPeriods==14}}selected{{/if}}>14</option>
<option value="15" {{if accountPeriods==15}}selected{{/if}}>15</option>
<option value="16" {{if accountPeriods==16}}selected{{/if}}>16</option>
<option value="17" {{if accountPeriods==17}}selected{{/if}}>17</option>
<option value="18" {{if accountPeriods==18}}selected{{/if}}>18</option>
<option value="19" {{if accountPeriods==19}}selected{{/if}}>19</option>
<option value="20" {{if accountPeriods==20}}selected{{/if}}>20</option>
<option value="21" {{if accountPeriods==21}}selected{{/if}}>21</option>
<option value="22" {{if accountPeriods==22}}selected{{/if}}>22</option>
<option value="23" {{if accountPeriods==23}}selected{{/if}}>23</option>
<option value="24" {{if accountPeriods==24}}selected{{/if}}>24</option>
<option value="25" {{if accountPeriods==25}}selected{{/if}}>25</option>
<option value="26" {{if accountPeriods==26}}selected{{/if}}>26</option>
<option value="27" {{if accountPeriods==27}}selected{{/if}}>27</option>
<option value="28" {{if accountPeriods==28}}selected{{/if}}>28</option>
<option value="29" {{if accountPeriods==29}}selected{{/if}}>29</option>
<option value="30" {{if accountPeriods==30}}selected{{/if}}>30</option>
<option value="31" {{if accountPeriods==31}}selected{{/if}}>31</option>
</select>号--}}
<div style="color:#FF0000;font-size:15px;margin-top:5px;float:right">*</div>
<div style="font-size:15px;margin-top:5px;float:right;maigin-right:10px">天</div>

<input type="text" class="form-control input-sm" style="width:220px" data-rule="required;plus" id="accountPeriod" name="accountPeriod" value="{{:accountPeriod}}" />


						{{!--<input type="text" class="form-control input-sm" data-rule="required;number" id="accountPeriod" name="accountPeriod" value="{{:accountPeriod}}" />号到
						<input type="text" class="form-control input-sm" data-rule="required;number" id="accountPeriod" name="accountPeriod" value="{{:accountPeriod}}" />号--}}
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">比例：</label>
       				 <div class="col-md-3">
商家<input type="text" data-rule="required;propotion" id="propotion" name="propotion" value="{{:propotion}}" />%
<label style="color:#FF0000;font-size:15px;margin-top:4px">*</label>  
{{!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
						平台<input type="text" data-rule="required;propotion" id="propotions" name="propotion" value="{{:propotions}}" />%--}}
					</div>
      			</div>
			</fieldset>
	</script>
    </body>
</html>