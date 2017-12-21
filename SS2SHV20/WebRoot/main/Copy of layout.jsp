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
	<link rel="stylesheet" type="text/css" href="http://www.helloweba.com/demo/css/main.css" />
	<link rel="stylesheet" href="css/normalize.css" />
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="style.css" />
	<script src="modernizr.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
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
			var data = "[{\"content\":\"\\\"1、检查空调主机电压、电流，供油温度。\\\\r\\\\n2、检查空调主机电箱内电气元件。\\\\r\\\\n3、紧固各线路，主电路、控制回路。\\\\r\\\\n4、清洁电箱内、外，清浩空调主机机身。\\\\r\\\\n5、检查各传感器是否正常。\\\\r\\\\n6、检查空调主机起动、运行正常。\\\"dasdasfasfdfd\",\"dateofexecution\":\"\",\"degree\":\"月度\",\"enddate\":\"\",\"executiondata\":\"1997-10-17\",\"executor\":\"\",\"id\":452,\"implementation\":\"\",\"maintenancecategory\":\"3#空调主机\",\"unitid\":\"DF-KT-40-003\",\"whether\":\"1\"}]";
			var msg = eval("("+data+")");
			  for(var o in msg){  
		        $("#Tr").after("<tr><td>"+msg[o].implementation+"</td></tr>");
		      }  
		});
	</script>
</head>
<body>
		<div id="content" region="center" title="保养详情" style="padding:5px;">
			<table class="imagetable" border="1" style="width:100%;padding:5px">
  				<tr>
    				<th>序号</th>
   					<th>设备类别</th>
   					<th>设备名称</th>
   					<th>运行时间(小时)</th>
   					<th>第一次计划执行日期</th>
   					<th>执行周期</th>
   					<th>执行机构</th>
   					<th>操作</th>
  				</tr>
  				<tr id="Tr">
  					
  				</tr>
			</table>
		</div>
</body>
</html>