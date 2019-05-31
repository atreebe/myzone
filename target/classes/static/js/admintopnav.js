$(document).ready(function(){
	/*导航栏*/
	var htmlStr = '<header>';
	htmlStr += '<div class="container-fluid">';
	htmlStr += '<div class="row">';
	/*Logo*/
	htmlStr += '<div class="col-xs-10 col-sm-8 col-md-4">'
	htmlStr += '<a href="#">';
	htmlStr += '<img src="../img/Logo_top.png" alt="logo">';
	htmlStr += '</a>';
	htmlStr += '</div>';//End of col

	/*Title*/
	htmlStr += '<div class="col-xs-10 col-sm-8 col-md-5" style="padding:0">';
	htmlStr += '<h1 style="float:left;margin-left:-9999px">Swustvis后台管理系统</h1>';
	htmlStr += '<h2 style="text-align: left">Swustvis后台管理系统</h2>';
	htmlStr += '</div>';//End of col

	/*Button*/
	htmlStr += '<div class="col-xs-12 col-sm-4 col-md-3 home_button" >';
	htmlStr += '<button class="btn btn-default btn-sm" type="button" th:text="${username}">用户</button>';
	htmlStr += '<button class="btn btn-default btn-sm" type="button" >退出</button>';
	htmlStr += '</div>';

	htmlStr += '</div>';//End of row
	htmlStr += '</div>';//End of container-fluid
	htmlStr += '</header>';//End of header

	$('#home').html(htmlStr);
});

$(document).ready(function(){
	/*导航栏*/
	var htmlStr = '<footer>';
	htmlStr += '<div class="container-fluid">';
	htmlStr += '<div class="row">';
	/*Logo*/
	htmlStr += '<div class="col-xs-5 col-sm-4 col-md-2">'
	htmlStr += '<a href="#">';
	htmlStr += '<img src="../img/Logo_top.png" alt="logo">';
	htmlStr += '</a>';
	htmlStr += '</div>';//End of col



	htmlStr += '</div>';//End of row
	htmlStr += '</div>';//End of container-fluid
	htmlStr += '</footer>';//End of header

	$('#footer').html(htmlStr);
});