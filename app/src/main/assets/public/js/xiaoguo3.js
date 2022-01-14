$(function() {
	//提示框
	//	$(".btnhref").on("click", function() {
	//	if($(this).parents(".switch-tab").find(".switch-drop").css("display") == "block") {
	//		$(this).parents(".switch-tab").find(".switch-drop").css('display', 'none');
	//		$(this).find('.fuhao').text('+');
	//	} else {
	//		$(this).parents(".switch-tab").find(".switch-drop").css('display', 'block');
	//		$(this).find('.fuhao').text('-');
	//	}
	//	});

	//	$(".jinggao").on("click", function() {
	if($(".jinggao").parents(".switch-tab").find(".switch-drop").css("display") == "block") {
		$(".jinggao").removeClass('jinggao-dianji');
		$(".jinggao").parents(".switch-tab").find(".switch-drop").css({
			'border': '2px solid rgba(241, 142, 6, 0.81)',
			'border-top': 'none'
		});
	} else {
		$(".jinggao").addClass('jinggao-dianji');
		$(".jinggao").parents(".switch-tab").find(".switch-drop").css({
			'border': '2px solid rgba(241, 142, 6, 0.81)',
			'border-top': 'none'
		});
	}
	//	})

	//	$(".tishi").on("click", function() {
	if($(".tishi").parents(".switch-tab").find(".switch-drop").css("display") == "block") {
		$(".tishi").removeClass('tishi-dianji');
		$(".tishi").parents(".switch-tab").find(".switch-drop").css({
			'border': '2px solid #4bb0f9',
			'border-top': 'none'
		});
	} else {
		$(".tishi").addClass('tishi-dianji');
		$(".tishi").parents(".switch-tab").find(".switch-drop").css({
			'border': '2px solid #4bb0f9',
			'border-top': 'none'
		});
	}
	//	})

	//$(".zhuyi").on("click", function() {
	if($(".zhuyi").parents(".switch-tab").find(".switch-drop").css("display") == "block") {
		$(".zhuyi").removeClass('zhuyi-dianji');
		$(".zhuyi").parents(".switch-tab").find(".switch-drop").css({
			'border': '2px solid rgba(6, 241, 226, 0.81)',
			'border-top': 'none'
		});
	} else {
		$(".zhuyi").addClass('zhuyi-dianji');
		$(".zhuyi").parents(".switch-tab").find(".switch-drop").css({
			'border': '2px solid rgba(6, 241, 226, 0.81)',
			'border-top': 'none'
		});
	}
	//})

	$("img.lazy").lazyload({
		effect: "fadeIn",
		threshold: 200
	});

})