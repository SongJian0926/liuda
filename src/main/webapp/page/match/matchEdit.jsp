<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>赛事 编辑页面</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <jsp:include page="../com/header.jsp"></jsp:include>
        <script type="text/javascript" src="../../js/comm/editcomm.js"></script>
        <script type="text/javascript" src="../../js/comm/editMatch.js"></script>
        <script type="text/javascript">
		$(function(){
			getMatchDataById(root + 'cms/macth/findMacthById',"#matchTmpl","#matchContent","#guessTmpl","#guessContent");
			$("#saveOrupdate").click(function(){
				saveMatch(root + 'cms/macth/saveMacth','matchList.jsp');
			});
			//上传图片
		    uploadphoto("macth");
		});
        </script>
		<style>
		<!--
			.txt-span{
				display:inline-block; 
				width: 200px;
				margin-left: 2%;
				padding: 0px 10px 0px 10px;

			}
			.txt-prize{
				width: 70px;
				margin-left: 2%;
				padding: 0px 10px 0px 10px;

			}
			.txt-prizeoption{
				display:inline-block; 
				width: 100px;
				margin-left: 2%;
				padding: 0px 10px 0px 10px;

			}
			.txt-prizeoption1{
				display:inline-block; 
				width: 100px;
				margin-left: 2%;
				padding: 0px 10px 0px 10px;

			}
			.img{
				height:100px;
				width:100px;
			}
		-->
		</style>
    </head>
    <body>
      <section>
          <div>
              <div class="col-xs-12">
                  <div class="box" style="">
                      <div class="box-header">
                      <br>
                          <input type="button" value="确定" id="saveOrupdate" class="btn btn-success">
                          <button onclick="backAction('matchList.jsp');" class="btn btn-info" >返回</button>
                      </div>
                     <form id="submit_form" class="form-horizontal">
                        <div class="tab" id="matchContent">
                  	    </div>
                        <div class="tab" id="guessContent">
                  	    </div>
                  	 </form>
              </div>
          </div>
      </section>
      <script type="text/x-jsrender" id="matchTmpl">
			<fieldset> 
				<input type="hidden" id="tId" name="id" value="{{:id}}" />
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">赛事类型：</label>
       				 <div class="col-md-3">
                         <select name="matchType" data-rule="required;" id="matchType" value="{{:matchType}}">
							<option value="1">赛事</option>
							<option value="2">活动</option>
						</select>                  
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">赛事标签：</label>
       				 <div class="col-md-3">
                         <select name="dictItemId" data-rule="required;" id="dictItemId" value="{{:dictItemId}}"></select>                  
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">赛事名称：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写赛事名称" class="form-control input-sm" data-rule="required;length[1~100]" id="title" name="title" value="{{:title}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">赛事图片：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
							{{if logo}}<img id="myImageLogoShow" src="../../{{:logo}}" class="img">
                            {{else}}<img id="myImageLogoShow" src="" class="img">{{/if}}
                            <input type="hidden" id="logo" name="logo" value="{{:logo}}"></input>
		                    <input type="file" id="uploadLogoPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">赛事地址：</label>
       				 <div class="col-md-3">
                        <select name="provinceId"  id="provinceId"></select>  
						<select name="cityId"  id="cityId"></select>  
						<select name="areaId"  id="areaId" value="{{:areaId}}" data-rule="required;"></select>                
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">详细地址：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写详细地址" class="form-control input-sm" data-rule="required;length[0~50]" id="address" name="address" value="{{:address}}" />
					</div>
      			</div>
				<div class="form-group">
					<label class="col-md-2 control-label controls" for="formGroupInputSmall">赛事时间：</label>
					<div class="col-md-3">
						<input type="text" placeholder="开始时间" readOnly class="form-control input-sm" style="width:125px;display:inline-block; " id="startdate" name="startdate" value="{{:startdate}}" />至
						<input type="text" placeholder="结束时间" readOnly class="form-control input-sm" style="width:125px;display:inline-block; " id="enddate" name="enddate" value="{{:enddate}}" />
					</div>
				</div>
				<div class="form-group">
					<br/>
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">参赛费用：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写参赛费用" class="form-control input-sm" data-rule="required;length[1~50];money;nozero" id="price" name="price" value="{{:price}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">参赛订金：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写参赛订金" class="form-control input-sm" data-rule="required;length[1~50];money;nozero" id="deposit" name="deposit" value="{{:deposit}}" />
					</div>
      			</div>
				<div class="form-group">
					<label class="col-md-2 control-label controls" for="formGroupInputSmall">报名截止时间：</label>
					<div class="col-md-3">
						<div class="datetime_clear"></div>
						<input type="text" placeholder="报名截止时间" readOnly class="form-control input-sm" id="deadline" name="deadline" value="{{:deadline}}" />
					</div>
				</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">人数限制：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写人数限制" class="form-control input-sm" data-rule="required;length[0~50];plus" id="maxNum" name="maxNum" value="{{:maxNum}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">参赛条件：</label>
       				 <div class="col-md-3">
						<textarea type="text" placeholder="请填写参赛条件" class="form-control input-sm" data-rule="length[0~500]" id="condition" name="condition" value="{{:condition}}" >{{:condition}}</textarea>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">活动详情：</label>
       				 <div class="col-md-3">
						<textarea type="text" placeholder="请填写活动详情" class="form-control input-sm" data-rule="required;length[0~100000]" id="content" name="content" value="{{:content}}" >{{:content}}</textarea>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">活动URL：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写活动URL" class="form-control input-sm" data-rule="length[0~255]" id="url" name="url" value="{{:url}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">赛事图片：</label>
       				 <div class="col-md-3" style="display:inline-block;">
                        {{if mediaPath}}
							{{for mediaPath.split(",")}}
								<div class="upload_div divmedia"  style="display:inline-block;" >
									<input name="mediaPath" style="display:none" id="mediaPath{{:#index+1}}" value="{{:}}"></input>
									<img src="../../{{:}}" class="img" id="img{{:#index+1}}">
									<input type="file" id="uploadPhotoFile{{:#index+1}}" name="photoFile" class="upload_file" >
								</div>
							{{/for}}
						{{else}}
							<div class="upload_div divmedia"  style="display:inline-block;" >
							<img id="myImageShow" src="" class="img"/>
							<input type="file" id="uploadPhotoFile" name="photoFile" class="upload_file">
							</div>
						{{/if}}
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">观赛门票名称：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写观赛门票名称" class="form-control input-sm" data-rule="required;length[0~15]" id="ticketName" name="ticketName" value="{{:ticketName}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">观赛门票图片：</label>
       				 <div class="col-md-3">
						<div class="upload_div">
							{{if ticketImages}}<img id="myImageTicketImagesShow" src="../../{{:ticketImages}}" class="img">
                            {{else}}<img id="myImageTicketImagesShow" src="" width="100px" height="100px">{{/if}}
                            <input type="hidden" id="ticketImages" name="ticketImages" value="{{:ticketImages}}"></input>
		                    <input type="file" id="uploadTicketImagesPhotoFile" name="photoFile" class="upload_file">
                        </div>
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">观赛门票价格：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写观赛门票价格" class="form-control input-sm" data-rule="required;length[0~50];money;nozero" id="ticketPrice" name="ticketPrice" value="{{:ticketPrice}}" />
					</div>
      			</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">观赛门票库存：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="请填写观赛门票库存" class="form-control input-sm" maxlength=6 data-rule="required;length[0~50];plus" id="ticketStockNum" name="ticketStockNum" value="{{:ticketStockNum}}" />
					</div>
      			</div>
				<div class="form-group">
					<label class="col-md-2 control-label controls" for="formGroupInputSmall">观赛门票截止时间：</label>
					<div class="col-md-3">
						<div class="datetime_clear"></div>
						<input type="text" placeholder="观赛门票截止时间" readOnly class="form-control input-sm" id="deadlineView" name="deadlineView" value="{{:deadlineView}}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label controls" for="formGroupInputSmall">观赛门票优惠时间：</label>
					<div class="col-md-3">
						<div class="datetime_clear"></div>
						<input type="text" placeholder="开始时间" readOnly class="form-control input-sm" style="width:125px;display:inline-block; " id="favStartTime" name="favStartTime" value="{{:favStartTime}}" />至
						<input type="text" placeholder="结束时间" readOnly class="form-control input-sm" style="width:125px;display:inline-block; " id="favEndTime" name="favEndTime" value="{{:favEndTime}}" />
					</div>
				</div>
				<div class="form-group">
        			<label class="col-md-2 control-label controls" for="formGroupInputSmall">观赛门票优惠价格：</label>
       				 <div class="col-md-3">
						<input type="text" placeholder="观赛门票优惠价格" class="form-control input-sm" data-rule="length[0~50];money;nozero" id="favourablePrice" name="favourablePrice" value="{{:favourablePrice}}" />
					</div>
      			</div>
			</fieldset>
	</script>
	<script id="guessTmpl" type="text/x-jsrender">
<div style="display:none">
		<!-- 竞猜 -->
			<div>&nbsp;&nbsp;设置竞猜内容</div>
			<div class="form-group">
				<label class="col-md-2 control-label controls">竞猜标题：</label>
				<div class="col-md-3">
					<input id="guessId" name="guessId" type="text" style="display:none" value="{{:id}}"/>
					<input id="guessTitle" name="guessTitle"  data-rule="length[0~15]" type="text" class="form-control input-sm" value="{{:title}}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label controls">竞猜赔率：</label>
				<div class="col-md-3">
					<input id="guessOdds" name="guessOdds" type="text" data-rule="money" class="form-control input-sm" value="{{:odds}}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label controls">竞猜选项：</label>
				<div class="col-md-3">
					<input id="guessOption" name="guessOption"  data-rule="length[0~16]" style="display:inline-block;" type="text" class="form-control input-sm" value=""/>
					<input type="button" class="addGuessOption font-sty" value="+" />
					<span class="msg-box n-right warn-font" style="position:static;" for="guessOption"></span> 
				</div>
			</div>
			<div class="form-group">
        		<label class="col-md-2 control-label controls" for="formGroupInputSmall"></label>
				<div id="guessOptionList" class="col-md-3">
				</div>
			</div>
		<!-- 二次抽奖 -->
			<div>&nbsp;&nbsp;设置二次抽奖</div>
			<div class="form-group">
				<label class="col-md-2 control-label controls">抽奖等级：</label>
				<div class="col-md-3">
					<input id="prizeLevel" name="prizeLevel"  data-rule="length[0~10];digits" type="text" class="font-sty txt-prize" value=""/>(等奖)
					<span class="msg-box n-right warn-font" style="position:static;" for="prizeLevel"></span> 
					<input id="prizeNum" name="prizeNum"  data-rule="length[0~10];digits" type="text" class="font-sty txt-prize" value=""/>(人)
					<span class="msg-box n-right warn-font" style="position:static;" for="prizeNum"></span> 
					<input id="prize" name="prize"  data-rule="length[0~50]" type="text" class="font-sty txt-prize" value=""/>(奖品)
					<input type="button" class="addPrize font-sty" value="+"/>
				</div>
			</div>
			<div class="form-group">
        		<label class="col-md-2 control-label controls" for="formGroupInputSmall"></label>
				<div id="prizeOptionList" class="col-md-3"></div>
			</div>
</div>
	</script>
    </body>
</html>