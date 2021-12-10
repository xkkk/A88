$(function () {
	//提示框
	$(".btnhref").on("click", function () {
		if ($(this).parents(".switch-tab").find(".switch-drop").css("display") == "block") {
			$(this).parents(".switch-tab").find(".switch-drop").css('display', 'none');
			$(this).find('.fuhao').text('+');
		} else {
			$(this).parents(".switch-tab").find(".switch-drop").css('display', 'block');
			$(this).find('.fuhao').text('-');
		}
	});

	$(".jinggao").on("click", function () {
		if ($(this).parents(".switch-tab").find(".switch-drop").css("display") == "block") {
			$(this).removeClass('jinggao-dianji');
			$(this).parents(".switch-tab").find(".switch-drop").css({
				'border': '2px solid rgba(241, 142, 6, 0.81)',
				'border-top': 'none'
			});
		} else {
			$(this).addClass('jinggao-dianji');
			$(this).parents(".switch-tab").find(".switch-drop").css({
				'border': '2px solid rgba(241, 142, 6, 0.81)',
				'border-top': 'none'
			});
		}
	})

	$(".tishi").on("click", function () {
		if ($(this).parents(".switch-tab").find(".switch-drop").css("display") == "block") {
			$(this).removeClass('tishi-dianji');
			$(this).parents(".switch-tab").find(".switch-drop").css({
				'border': '2px solid #4bb0f9',
				'border-top': 'none'
			});
		} else {
			$(this).addClass('tishi-dianji');
			$(this).parents(".switch-tab").find(".switch-drop").css({
				'border': '2px solid #4bb0f9',
				'border-top': 'none'
			});
		}
	})

	$(".zhuyi").on("click", function () {
		if ($(this).parents(".switch-tab").find(".switch-drop").css("display") == "block") {
			$(this).removeClass('zhuyi-dianji');
			$(this).parents(".switch-tab").find(".switch-drop").css({
				'border': '2px solid rgba(6, 241, 226, 0.81)',
				'border-top': 'none'
			});
		} else {
			$(this).addClass('zhuyi-dianji');
			$(this).parents(".switch-tab").find(".switch-drop").css({
				'border': '2px solid rgba(6, 241, 226, 0.81)',
				'border-top': 'none'
			});
		}
	})

	$("img.lazy").lazyload({
		effect: "fadeIn",
		threshold: 200
	});

})