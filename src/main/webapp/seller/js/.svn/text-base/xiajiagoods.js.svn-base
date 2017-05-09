$(document).ready(function() {
	// 下架状态-1
	var status = -1;
	// 商户id
	var id = JSON.parse($.cookie("busniessInfo")).goodsId;
	// 商户类型
	var businessType = JSON.parse($.cookie("busniessInfo")).type;
	// 分页变量
	var nextPage = 0;
	var pageSize = 6;
	// 列表显示数据
	list(status, businessType, id, nextPage, pageSize)

	// 添加样式
	$('.nav_menu').find('li').removeClass('active');
	$('.nav_menu').find('li').eq(0).addClass('active');
	$('.left_child_menu').find('a').removeClass('active_nav');
	$('.left_child_menu').find('a').eq(2).addClass('active_nav');

	// 上架管理按钮
	$('.search-up').click(function() {
		// 如果有商品全选字样,全选框显示
		if ($('.nogoods').css('display') == 'none') {
			$(".check-all").css('display', 'display');
			// 单选框显示
			$(".thumb").css('display', 'display');
			$(".thum").css('padding-left', '0px');
			// 取消按钮显示
			$(".search-quxiao-up").css('display','display');
			// 上架按钮显示
			$(".search-shangjia-up").css('display','display');
		} else {
			$(".check-all").css('display', 'none');
		}
	});

	// 取消按钮事件
	$(".search-quxiao-up").click(function() {
		// 全选字样,全选框消失
		$(".check-all").css('display', 'none');
		$("#checkAll").removeAttr("checked");
		// 单选框显示
		$(".thumb").css('display', 'none');
		$(".checkitem").removeAttr("checked");
		$(".thum").css('padding-left', '15px');
		// 取消按钮显示
		$(".search-quxiao-up").css('display', 'none');
		// 上架按钮显示
		$(".search-shangjia-up").css('display', 'none');
	});
	
	// 上架按钮事件
	$(".search-shangjia-up").click(function() {
		var status = -1;
		upAnddown(status, businessType, id);
	});

	// 房间编辑事件
	$(".show-list").on('click','.item .img,.ht',function() {
		// 获得编辑的ID
		var gid = $(this).parents('.item').children().find('.checkitem').attr('ckId');
		if (businessType == 0) {
			// 酒店
			window.location.href = '../shop/addRoom.jsp?id='+ gid;
		} else {
			// 景点
			window.location.href = '../shop/addticket.jsp?id='+ gid;
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