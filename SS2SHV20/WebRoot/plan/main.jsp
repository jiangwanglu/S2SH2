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
	<title>计划</title>
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
  html, body {
            width:100%;
            height:100%;
            margin:0px;
            padding:0px;
        }
        #one {
            width:100%;
            height:80%;
            margin:auto;
            margin-top:50px;
        }
	</style>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type:"POST",
			url:"queryPlandl.do",
			success:function(data){
				var msg = eval("("+data+")");
            	var tr = $("#Tr");
            	$.each(msg, function (i, field) {
            		var clonedTr = tr.clone();
            		clonedTr.children("td").each(function(inner_index){
            			switch(inner_index){
            				case(0):   
                            	$(this).html(field.data.split(":")[0] +":"+ field.data.split(":")[1]);  
                            	break;
                            case(1):
                            	$(this).html(field.name);
                            	break;
                            case(2):
                            	$(this).html(field.endata.split(":")[0] +":"+ field.endata.split(":")[1]);
                            	break;
                            case(3):
                            	$(this).html("<button value="+field.id+" onclick=\"modify(this.value)\">修改计划中的设备</button>");
                            	break;
                            case(4):
                            	$(this).html("<button value="+field.id+" onclick=\"updata(this.value)\">修改时间</button>");
                            	break;
            			}
            		});
            		clonedTr.insertAfter(tr);
            	});
            	$("#Tr").hide();
			}
		});
	});
	function modify(da){
		$("#query").window("open");
		$.ajax({
			type:"POST",
			url:"queryJSPdl.do",
			success:function(data){
				var strs= new Array(); //定义一数组 
				strs=data.split(","); //字符分割
				for (i=0;i<strs.length ;i++ ){
					if(strs[i] != ""){
						$("#query").append("<input type=\"checkbox\" name=\"kt\" value="+strs[i]+" id=checkbox"+i+" class=\"chk_1\" /><label for=checkbox"+i+">"+strs[i]+"</label>");
					}
				}
				$("#query").append("</p><button value="+da+" onclick=\"add(this.value)\" style=\"margin-top:10px\">确定</button><button onclick=\"shut()\">取消</button></center>");
			}
		});
	}
	function add(da){
		var name1=document.getElementsByName('kt');
		var DeviceName=''; 
		for(var i=0; i<name1.length; i++){ 
			if(name1[i].checked) DeviceName+=name1[i].value+','; //如果选中，将value添加到变量s中 
		}
		$.ajax({
			type:"POST",
			url:"updatePlandl.do",
			data:{name:DeviceName,id:da},
			dataType:"Json",
			success:function(data){
				window.location.reload();
			}
		});
	}
	function shut(){
		$("#query").empty();
		$("#updata").empty();
		$("#query").window("close");
		$("#updata").window("close");
	}
	function updata(value){
		$("#updata").window("open");
		$.ajax({
			type:"POST",
			url:"queryDatadl.do",
			data:{id:value},
			dataType:"Json",
			success:function(data){
				var msg = eval("("+data+")");
				var msg1 = eval("("+msg+")");
				for(var a=0;a<msg1.length;a++){
					$("#updata").append("<h3>开始时间:<input id=\"data1\" type=\"time\" value="+msg1[a].data+">  结束时间:<input type=\"time\" id=\"data2\" value="+msg1[a].endata+"></h3><button style=\"margin-top:50px\" value="+value+" onclick=\"updataData(this.value)\">提交</button><button style=\"margin-Left:180px\" onclick=\"shut()\">取消</button>");
				} 
			}
		});
	}
	function updataData(value){
		var data1 = document.getElementById("data1").value;
		var data2 = document.getElementById("data2").value;
		$.ajax({
			type:"POST",
			url:"updataDatadl.do",
			data:{id:value,data1:data1,data2:data2},
			dataType:"Json",
			success:function(data){
				window.location.reload();
			}
		});
	}
</script>
</head>
<body>
	<div id="one">
		<table class="imagetable">
  				<tr>
    				<th>开始时间</th>
   					<th>设备名称</th>
   					<th>结束时间</th>
   					<th>修改(需要巡检的设备)</th>
   					<th>修改时间</th>
  				</tr>
  				<tr id="Tr">
  					<td></td>
  					<td></td>
  					<td></td>
  					<td></td>
  					<td></td>
  				</tr>
			</table>
	</div>
	<div id="query" class="easyui-window" title="修改内容"  style="width:700px;height:800px;padding:5px;"
			 data-options="iconCls:'icon-save',closed:true,modal:true,minimizable:false,maximizable:false,closable:false,collapsible:true">
		
	</div>
	<div id="updata" class="easyui-window" title="修改时间"  style="width:500px;height:150px;padding:5px;"
			 data-options="iconCls:'icon-save',closed:true,modal:true,minimizable:false,maximizable:false,closable:false,collapsible:true">
	</div>
</body>
</html>