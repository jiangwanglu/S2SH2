<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>添加巡检计划</title>
 <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico" /> 
		<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" href="css/jquery-labelauty.css">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
ul { list-style-type: none;}
li { display: inline-block;}
li { margin: 10px 0;}
input.labelauty + label { font: 12px "Microsoft Yahei";}

</style>
</head>
<style>
</style>
<body>
<div style="width:100%;text-align:center";>
	<ul class="dowebok">
		
		<li><a href="#" class="button button-khaki"  onclick="chk()"><span>添加</span></a></li>
	 </ul>
</div>
<div style="text-align:center;clear:both;margin-top:50px">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-labelauty.js"></script>
<script>
<%String id = request.getParameter("id"); %>		
$(function(){
	$.ajax({
		url: "queryJSPdl.do",
		type:"POST",
		success:function(data){
			var strs= new Array(); //定义一数组 
			strs=data.split(","); //字符分割
			for (i=0;i<strs.length ;i++ ){ 
				  if(strs[i] != ''){
				  	 if(i % 5 == 0){
				  	 	$(".dowebok").append("</p>");
				  	 }
				  	 $(".dowebok").append("<li><input type='checkbox' name='kt' value='"+strs[i]+"' data-labelauty='"+strs[i]+"'/></li>");
				  }
			}
			 $(':input').labelauty();
		},
		fail:function(status,xml){
			alert("读取失败,请重新进入!");
		}
	});
});

function chk(){
	var obj = document.getElementsByName('kt');
	var s = 0;
	var array = new Array();
	for(var i=0; i<obj.length; i++){
		if(obj[i].checked){
			array[s] = obj[i].value;
			s++;
		}
	}
	var ktjson = JSON.stringify(array);
	$.ajax({
        url: "Patrolkdl.do",              //请求地址
        type: "POST",                       //请求方式
        data: { name: ktjson , i : <%=id%>},        //请求参数3
        dataType: "json",
        success: function (response, xml) {
            alert("计划添加成功");
            $.ajax({url:"plandl.do",success:function(response,xml){window.location.href="/S2SH2/jquery-pretty-radio-checkbox/add.jsp";}});
        },
        fail: function (status) {
            alert("计划添加失败");
        }
    });
}
</script>
<div style="text-align:center;clear:both;">
</div>
</body>
</html>