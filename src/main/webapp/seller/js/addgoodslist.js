$(document).ready(function() {
	// 审批状态1
	var status = 1;
	// 酒店或者门票的id
	var id = JSON.parse($.cookie("busniessInfo")).goodsId;
	// 商户类型
	var businessType = JSON.parse($.cookie("busniessInfo")).type;
	// 分页变量
	var nextPage = 0;
	var pageSize = 6;
	// 列表显示数据
	list(status, businessType, id, nextPage, pageSize);

	// 添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(0).addClass('active');
  

	// 添加商品
	$('.search-bt').click(function() {
		if (businessType == 0) {
			location.href = '../shop/addRoom.jsp';
		} else {
			location.href = '../shop/addticket.jsp';
		}

	});

	// 页码点击事件
	$("#displayPage").on('click','li',function() {
		// 获得当前页码的点击事件,没有id,style属性的li标签就是页码
		var a = $(this).attr("id");
		var b = $(this).attr("style");
		if (typeof (a) == "undefined"&& typeof (b) == "undefined") {
			// 获得页码值
			nextPage = $(this).attr("thispage");
			list(status, businessType, id, nextPage,pageSize);
		}
	});
	
	// 回到第一页、回到最后一页、前一页、下一页
	$("#displayPage").on('click','#example2_first,#example2_last,#example2_previous,#example2_next',function() {
		nextPage = $(this).attr("thispage");
		list(status, businessType, id,nextPage, pageSize);
	});
	
	// 跳到指定页码
	$("#displayPage").on('click','li:last input',function() {
		// 获得页码值
		var pagenum = $(this).parent().prev().find('.page_num_input').val();
		if (pagenum > 0) {
			nextPage = pagenum - 1;
			list(status, businessType, id, nextPage,pageSize);
		}
	});

});
