$(document).ready(function(){
    changeStyle();  // 设置各栏目的样式
});
// 设置各栏目的样式
function changeStyle(){
	var href = location.href;//得到地址
    var strs = href.split("/");//得到分割的字符串
    var fileFullName = strs[strs.length-1];//得到（字符串数组最后一个）链接完整名称
    var fileName = '';//设置空名
    if(fileFullName == ''){//固定到首页
    	fileName = 'index';
    	$('#'+fileName).attr('class', 'active');
    }
    else{//跳转到相应页面，赋值链接完整名称
    	fileName = fileFullName.split('.')[0];//得到字符串数组第一个
    	$('#'+fileName).attr('class', 'active');
    }
}

//新闻/例会详情页面
function Display(){
    var href = location.href;//得到地址
    var strs = href.split("#");//得到分割的字符串
    var fileFullName = strs[strs.length-1];//得到（字符串数组最后一个）
    $('#'+fileFullName).attr('style','display:block');//显示对应id
}
