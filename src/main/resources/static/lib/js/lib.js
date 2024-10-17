
$(document).ready(function($) {

	// 浮动头部
	$(window).scroll(function(event) {
		if( $('#nv').length ){
			var _top = $(window).scrollTop();
			var _ntop = $('#nv').offset().top;
			if( _top>_ntop ){
				$('#header').addClass('open');
			}else{
				$('#header').removeClass('open');
			}
		}
	});
	
	$('.adBox .remove').click(function(){
		$(this).parents('.adBox').remove();
	});

	// 弹出
	$('.fancyBtn').click(function(){
		var _id = $(this).attr('href');
		$(_id).css({
		    'top':$(window).scrollTop() + $(window).height()/2 - $(_id).height()/2
		}).stop().fadeIn();
		$('body').append('<div class="overlay"></div>');
		return false;
	});
	$('.fancyClose').click(function(){
		$(this).parents('.fancyBox').stop().fadeOut();
		$('.overlay').remove();
	});

	// 用户
	$('.a-user').click(function(e){
		$(this).find('ul').stop().slideToggle();
		e.stopPropagation();
	});
	$(document).click(function(){
		$('.a-user ul').stop().slideUp();
	});

	// 搜索框
	$('.soBtn').click(function(){
		$('.hdr').stop().slideToggle();
	});
	
	// 主导航
	$('.mainNav').click(function() {
		// if( !$(this).hasClass('open') ){
			$(this).toggleClass('on').find('.main-nav-ul').stop().slideToggle();
		// }
	});

	// 手机导航
	$('.menuBtn').click(function(){
		$('#aside').addClass('open');
	});
	$('#aside .closeBtn').click(function(){
		$(this).parents('#aside').removeClass('open');
	});

	// 返回顶部
	$('#toTop').click(function(){
		$('body,html').animate({
			scrollTop:0
		}, 500);
	});

	// 下拉框
	$('.nice-sel input').click(function(event) {
		$(this).siblings('ul').stop().toggle();
	});
	$('.nice-sel li').click(function(event) {
		var _val = $(this).text();
		$(this).parents('ul').siblings('input').val(_val);
		$(this).parents('ul').stop().hide();
	});
	
	// 微信弹出二维码
	$('.btn_weixin').click(function(){
		$('.code_weixin').stop().fadeToggle();
	});
	$('.code_weixin .btn_close').click(function(){
		$(this).parents('.code_weixin').stop().fadeOut();
	});

	// 选项卡
	$(".TAB li").click(function(){
		var $vv=$(this).parent(".TAB").attr("id");
		$($vv).hide();
		$(this).parent(".TAB").find("li").removeClass("on");
		var xx=$(this).parent(".TAB").find("li").index(this);
		$($vv).eq(xx).show();
		$(this).addClass("on");
	});

	$(".TAB_CLICK li").click(function(){
		var tab=$(this).parent(".TAB_CLICK");
		var con=tab.attr("id");
		var on=tab.find("li").index(this);
		$(this).addClass('on').siblings(tab.find("li")).removeClass('on');
		$(con).eq(on).show().siblings(con).hide();
	});

});