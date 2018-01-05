<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>报表</title>
	<link rel="stylesheet" type="text/css" href="../jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../jquery-easyui/demo/demo.css">
	<script type="text/javascript" src="../jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../jquery-easyui/jquery.easyui.min.js"></script>
	<script src="../jquery-easyui/laydate/laydate.js"></script> <!-- 改成你的路径 -->
</head>
<style>
	table.gridtable {
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	table.gridtable th {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #dedede;
	}
	table.gridtable td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
	}
	html,body{
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	#cc{
		width:100%;
		min-height:100%;
		position:fixed;
		background-color:#c7c7c7;
	}
	.demo-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}
	#font{
		font-size:15px;
	}
	div{float:left}
</style>
<body>
	<div id="cc" class="easyui-layout">
		<div data-options="region:'north',title:'选项'"	style="height:200px"><br>
			<div style="width:40%;height:80%;background-color: #E6EFFF;margin-left:10px">
				<span id="font"  style="margin:10px">日期：</span><input type="text" class="demo-input" placeholder="请选择日期" id="test1"  style="margin:10px">
				<a href="#" onclick="equipment(this,'空调巡检记录表')"   name="DF-KT" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="margin:10px;width:60px;height:50px">空调</a>
				<a href="#" onclick="equipment(this,'给排水巡检记录表')" name="DF-GP" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="margin:10px;width:70px;height:50px">给排水</a>
				<a href="#" onclick="equipment(this,'供配电巡检记录表')" name="DF-QD" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="margin:10px;width:70px;height:50px">供配电</a>
			</div>
			<div class="main" style="width:53%;height:80%;background-color: #E6EFFF;margin-left:5%">
				<table style="margin:10px;width:100%;height:100%" border="0" >
					<thead align="center">
						<tr id="plantype">
							
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div data-options="region:'center',title:'内容'" >
			<div style="margin:10px;width:99%;height:97%;">
				<table style="margin:10px;width:98%;height:98%" border="2"  id="s" class="gridtable">
					<thead id="ke" align="center">
						<caption><h2 id="biaoti"></h2></caption>
					</thead>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	lay('#version').html('-v'+ laydate.v);
	//执行一个laydate实例
	laydate.render({
	  elem: '#test1' //指定元素
	});
	
	$(function(){
		
	});
	function equipment(i,name){
		$("#ke").empty();
		$("#plantype").empty();
		$("#biaoti").empty();
		var star = document.getElementById("test1").value;
		if(star == ""){
			alert("请先选择日期!!!");
		}else{
			$.ajax({type:"post",url:"Reportdl.do",data:{name:i.name,date:star},dataType:"Json",success:function(data){
				var msg = eval("("+data+")");
				$("#biaoti").append(""+name+"");
				for(var i=0;i<msg.length;i++){
					if(i==0){
						$("#ke").append("<th>巡检时间</tr>");
					}
					$("#plantype").append("<td><strong>"+msg[i].name+"</strong></td>");
					var ind = msg[i].valuename.split(",");
					for(var j=0;j<ind.length;j++){
						$("#ke").append("<th>"+ind[j]+"</tr>");
					}
					if(i == (msg.length - 1)){
						$("#ke").append("<th>巡检人</tr>");
					}
				}
				tabletr(msg);
			},});
		}
	}
	
	function tabletr(data){
		var star = document.getElementById("test1").value;
		if(star == ""){
			alert("请先选择日期!!!");
		}else{
			$.ajax({
				type:"post",
				url:"plantypedl.do",
				data:{name:data[0].name,date:star},
				dataType:"Json",
				success:function(da){
					var arrParse = JSON.parse(da);
					da = da.split(",");
					for(var i=0;i<arrParse.length;i++){
						$("#ke").append("<tr><td>"+arrParse[i]+"</td></tr>");
						for(var j=0;j<data.length;j++){
							$.ajax({
								type:"post",
								url:"plantype1dl.do",
								data:{value:data[j].value,name:data[j].unitid,date:arrParse[i]},
								dataType:"Json",
								success:function(de){
									alert(de);
								}
							});
						} 
					}
				}
			}); 
		}
	}
</script>
</html>