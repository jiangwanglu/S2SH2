<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UbTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>保养</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="css/main.css" />
	<link rel="stylesheet" href="css/normalize.css" />
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<script src="modernizr.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<!-- <script src="js/jquery-1.8.3.min.js"></script> -->
	<script src="js/jquery-labelauty.js"></script>
	<style type="text/css">
	table.imagetable {
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #999999;
		border-collapse: collapse;
	}
	table.imagetable th {
		background:#b5cfd2 url('cell-blue.jpg');
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #999999;
	}
	table.imagetable td {
		background:#dcddc0 url('cell-grey.jpg');
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #999999;
	}
	div.ss{width:100px;height:50px;border:1px solid #F00} 
	table.table{
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #999999;
		border-collapse: collapse;
	}
	table.table th{
		background: url('cell-blue.jpg');
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #E8E8DO;
	}
	</style>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type:"POST",
			url:"querymaindl.do",
			success: function (data) {
				var msg = eval("("+data+")");
				for(var a=0;a<msg.length;a++){
					 $("#osd").append("<option  value="+msg[a].maintenancecategory+">"+msg[a].maintenancecategory+"</option>"); 
				} 
			},
		});
	}); 
function wind(){
	$("td Que").html("");
	var options=$("#osd option:selected");
	$.ajax({
		type:"POST",
		url:"Query2dl.do",
		data:{unitid:options.val()},
		dataType:"Json",
		success:function(datas){
			var size = 0;
			if($("#k0").length>0){
				size =  $("#k0").attr("value");
				for(var j=0;j<=size;j++){
					$("#k"+j+"").remove();
				} 
			}
			var m = eval("("+datas+")");
				var msg = eval("("+m+")");
				var start;
				for(var o in msg){
					$("#k"+o+"").remove();
					if(msg[o].whether == 0){
						start = "未保养"; 	
	                }else if(msg[o].whether == 1){
						start = "已执行"; 
	                }else if(msg[o].whether == 2){
						start = "已过期";
	                }else if(msg[o].whether == 3){
	                	start = "待通知";
	                }else if(msg[o].whether == 4){
	                	start = "已通知";
	                }
			        $("#Que").after("<tr id=\"k"+o+"\" name=\"zhi\" value="+msg.length+"><td>"+msg[o].maintenancecategory+"</td><td>"+msg[o].executiondata+"</td><td>"+msg[o].dateofexecution+"</td><td>"+msg[o].enddate+"</td><td>"+start+"</td><td>"+msg[o].implementation+"</td><td>"+msg[o].degree+"</td><td>"+msg[o].content+"</td><td>"+msg[o].executor+"</td></tr>");
			    }
		}
	});
}
function wi(a){
	$.ajax({
		type:"POST",
		url:"QueryCentent2dl.do",
		data:{name : a},
		dataType:"Json",
		success:function(data){
			$("#addconcent").window("open");
		    $("#addconcent").html("<textarea id=\"text\" style=\"width:400px; height:200px;\">"+data+"</textarea></p><button value=\""+a+"\" onclick=\"qin(this.value)\">确定</button> <button onclick=\"qu()\">取消</button>"); 
		},
	});
}
function qin(a){
	var name = a.split(",")[0];
	var data = a.split(",")[1];
	var value = $("#text").val();
	$.ajax({
		type:"POST",
		url:"updataContent2dl.do",
		data:{name : name,data : data,value : value},
		dataType:"Json",
		success:function(data){
			window.location.reload();
		}
	});
}
function qu(){
	$("#addconcent").window("close");
}
</script>
</head>
	<body class="easyui-layout">
		<div id="addconcent" class="easyui-window" title="修改本次保养内容"  style="width:500px; height:300px;padding:5px;"
			 data-options="iconCls:'icon-save',closable:true,closed:true,modal:true,maximizable:true,closable:true,collapsible:true">
		</div>
		<div data-options="region:'north',title:'查询的条件'" style="height:100px;padding-left:40%;">
			<select id="osd"  style="width:100px;height:50px;">
			</select>
			<button onclick="wind()">查询</button>
		</div>
		<div id="query" data-options="region:'center',title:'执行情况 '">
			<table class="imagetable" border="1" style="width:100%;padding:1px">
				<tr>
					<th>名称</th>
					<th>执行日期</th>
					<th>实际执行日期</th>
					<th>结束日期</th>
					<th>是否执行</th>
					<th>执行情况</th>
					<th>保养类型</th>
					<th>内容</th>
					<th>执行人</th>
				</tr>
				<tr id="Que">
				
				</tr>
			</table>
		</div>
	</body>
</html>