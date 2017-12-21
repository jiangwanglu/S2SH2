<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>添加保养计划</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link rel="stylesheet" href="css/normalize.css">

    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
<style>
.button {
  display: inline-block;
  padding: 15px 25px;
  font-size: 24px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
  position:relative;
  top:50px;
  float:right 
}
.button:hover {background-color: #97C8FF}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
.div{width:100px;height:50px;border:1px solid #F00} 
</style>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-labelauty.js"></script>
<script>
$(function(){
	$.ajax({
		type: "POST",
		url: "MaintainQueryNamedl.do",
		success: function (data) {
			var a = data.substring(data.indexOf("[")+1,data.lastIndexOf("]"));
			var str = a.split(",");
			var name;
			for(var i = 0;i < str.length;i++){
				name = str[i].substring(str[i].indexOf("\"")+1,str[i].lastIndexOf("\""));
				$("#o").append("<option value="+name+">"+name+"</option>");
			}
		},
		
	});
});
function clicke(){
	var local = document.getElementById("o").value;
	var lower =document.getElementById("name").value;
	$.ajax({
		type:"POST",
		url:"MaintainAdddl.do",
		data:{local:local,lower:lower},
		dataType: "json",
		success:function(data){
			alert("添加成功");
			window.location.href="/S2SH2/maintain/main.jsp";
		},
		fail: function (status) {
            alert("计划添加失败");
        },
	}); 
}
</script>
</head>

<body>
<div style="text-align:center;clear:both;">
<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
</div>

设备名
<div class="select">
<select id="o">
</select>
</div>
<form name="myform"> 
	</p>保养日期<input id="name" type="date" value="2015-09-24"/>
	</p><button class="button" onClick="clicke()">添加保养</button>
</form>
<script src='jquery.js'></script>
</body>
</html>