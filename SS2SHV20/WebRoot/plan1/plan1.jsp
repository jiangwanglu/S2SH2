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
	<link rel="stylesheet" href="../button/styles/main.css" type="text/css" media="screen" charset="utf-8" />
	<link href="../Checkbox/css/styles.css" rel="stylesheet" />
	
	<link href="../Checkbox/css/inserthtml.com.radios.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../Checkbox/css/default.css">
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
	div{float:left;}
	#checkbox{padding:20px;font-size:5px;}
	.clean-button{position:absolute;top:200px;left:20px;bottom:2;}
</style>
<body>
	<div id="cc" class="easyui-layout">
		<div data-options="region:'north',title:'选项'"	style="height:200px"><br>
			<div style="width:40%;height:80%;background-color: #E6EFFF;margin-left:10px">
				<span id="font"  style="margin:10px">日期：</span><input type="text" class="demo-input" placeholder="请选择日期" id="test1"  style="margin:10px">
				<a href="#" onclick="equipment(this,'空调巡检记录表')"   name="DF-KT"  class="easyui-linkbutton" data-options="iconCls:'icon-kt'" style="margin:10px;width:60px;height:50px">空调</a>
				<a href="#" onclick="equipment(this,'给排水巡检记录表')" name="DF-GP"  class="easyui-linkbutton" data-options="iconCls:'icon-gps'" style="margin:10px;width:70px;height:50px">给排水</a>
				<a href="#" onclick="equipment(this,'供配电巡检记录表')" name="DF-QD" class="easyui-linkbutton" data-options="iconCls:'icon-gpd'" style="margin:10px;width:70px;height:50px">供配电</a>
			</div>
			<div class="main" style="width:53%;height:80%;background-color: #E6EFFF;margin-left:5%">
				<table style="margin:10px;width:100%;height:100%">
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
	
	
	<div id="win" class="easyui-window" title="选择设备(不选择点击确定是全选)" style="width:30%;height:30%"data-options="iconCls:'icon-xz',modal:true">
		<div id="checkbox">
		</div>
		<div class="clean-button">
		
		</div>
	</div>
</body>
<script>
	lay('#version').html('-v'+ laydate.v);
	//执行一个laydate实例
	laydate.render({
	  elem: '#test1' //指定元素
	});
	
	var de;
	
	$(function(){
		$('#win').window('close');
	});
	function equipment(i,name){
		$("#biaoti").empty();
		var star = document.getElementById("test1").value;
		$('#win').window('open');
		$.ajax({
			type:"post",
			url:"planndl.do",
			data:{name:i.name,data:star},
			dataType:"Json",
			success:function(data){
				$("#checkbox").empty();
				$(".clean-button").empty();
				var msg = eval("("+data+")");
				de = msg;
				for(var j=0;j<msg.length;j++){
					$("#checkbox").append("<input style=\"padding:50px\" type=\"checkbox\" name=\"checkbox\" value="+msg[j]+" id=\"checkbox-7-"+j+"\" /><label for=\"checkbox-7-"+j+"\"><span>"+msg[j]+"</span></label>");
				}
				$(".clean-button").append("<span onclick=\"equi()\" class=\"text\">确定</span>");
			}
		});
		$("#biaoti").append(name);
	}
	function equi(){
		$("#plantype").empty();	
		$('#win').window('close');
		var star = document.getElementById("test1").value;
		var obj = document.getElementsByName("checkbox");
		var s=''; 
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				$("#plantype").append("<td>"+obj[i].value+"</td>");
				s+=obj[i].value+','; //如果选中，将value添加到变量s中 
			}
		}
		if(s == ""){
			for(var i=0;i<de.length;i++){
				$("#plantype").append("<td>"+de[i]+"</td>");
			}
			alert(de);
			$.ajax({
				type:"post",
				url:"generatehtmldl.do",
				data:{planname:de.toString()},
				dataType:"Json",
				success:function(data){
					
				}
			});
		}else{
			alert(s);
			$.ajax({
				type:"post",
				url:"generatehtmldl.do",
				data:{planname:s},
				dataType:"Json",
				success:function(data){
					
				}
			});
		}
	}
</script>
</html>