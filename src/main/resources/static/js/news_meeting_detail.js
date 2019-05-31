$(document).ready(function(){
    Display();  // 设置各栏目的样式
});
//新闻/例会详情页面
function Display(){
    var href = location.href;//得到地址
    var strs = href.split("#");//得到分割的字符串
    var fileFullName = strs[strs.length-1];//得到（字符串数组最后一个）
    $('#'+fileFullName).attr('style','display:block');//显示对应id
}

