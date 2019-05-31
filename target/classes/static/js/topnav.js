$(document).ready(function(){
	/*导航栏*/
	var htmlStr = '<nav class="navbar navbar-default" role="navigation">';
	htmlStr += '<div class="container" style="padding:0">';
	htmlStr += '<row>';

	/*Logo图片*/
	htmlStr += '<div class="col-md-4 col-xs-12" style="padding: 0">'
	htmlStr += '<a href="index.html">';
	htmlStr += '<img src="img/Logo_top.png" alt="logo">';
	htmlStr += '</a>';
	htmlStr += '</div>';//End of col-4

	/*导航菜单*/
	htmlStr += '<div class="col-md-8 col-xs-12" style="padding:0">'

	/*隐藏按钮*/
	htmlStr += '<div class="navbar-header ">';
	htmlStr += '<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">';
	htmlStr += '<span class="icon-bar"></span>';
	htmlStr += '<span class="icon-bar"></span>';
	htmlStr += '<span class="icon-bar"></span>';
	htmlStr += '</button>';
	htmlStr += '</div>';
	/*列表菜单*/
	htmlStr += '<div class="collapse navbar-collapse navbar-position" id="example-navbar-collapse">';
	htmlStr += '<ul class="nav navbar-nav navbar-right">';
	htmlStr += '<li id="index"><a href="index.html">首页</a></li>';
	htmlStr += '<li id="team"><a href="team.html">成员</a></li>';
	htmlStr += '<li id="academic"><a href="academic.html">论文</a></li>';
	htmlStr += '<li id="scientific"><a href="scientific.html">项目</a></li>';
	htmlStr += '<li id="honor"><a href="honor.html">学生获奖</a></li>';
	htmlStr += '<li id="picture"><a href="picture.html">照片</a></li>';
	htmlStr += '<li id="contact"><a href="contact.html">联系我们</a></li>';
	htmlStr += '</ul>';
	htmlStr += '</div>';

	htmlStr += '</div>';//End of col-8
	htmlStr += '</row>';//End of row
	htmlStr += '</div>';//End of container
	htmlStr +='</nav>';//End of nav
	htmlStr += '</div>';//End of main_menu_bg"
	$('#home').html(htmlStr);
});
