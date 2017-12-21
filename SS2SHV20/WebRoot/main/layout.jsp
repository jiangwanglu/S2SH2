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
	<link rel="stylesheet" type="text/css" href="css/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/icon.css">
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
	.btn{
	   	 display: inline-block;
		  padding: 15px 25px;
		  font-size: 10px;
		  cursor: pointer;
		  text-align: center;   
		  text-decoration: none;
		  outline: none;
		  color: #fff;
		  background-color: #4CAF50;
		  border: none;
		  border-radius: 15px;
		  box-shadow: 0 9px #999;
	}
	.btn:hover {background-color: #DEB887}

	.btn:active {
	  background-color: #FFF8DC;
	  box-shadow: 0 5px #666;
	  transform: translateY(4px);
	}
	.demo-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}
</style>
	<script type="text/javascript">
		$(function(){
			var s = document.getElementById('divs');	
			s.style.display = "none";
			$.ajax({
				type: "POST",
				url: "MainNamedl.do",
				data:{id:0},
				dateType:"json",
				success: function (data) {
					var a = data.substring(data.indexOf("[")+1,data.lastIndexOf("]"));
					var str = a.split(",");
					var name;
					for(var i = 0;i < str.length;i++){
						name = str[i].substring(str[i].indexOf("\"")+1,str[i].lastIndexOf("\""));
						$("#o").append("<option value="+name+">"+name+"</option>");
					}
					de();
				},
			});
		});
function de(){
	 	$.ajax({
				type:"POST",
				url:"Displaydl.do",
				success:function(data){
					var msg = eval("("+data+")");
            		var tr = $("#Tr");
            		$.each(msg, function (i, field) {
					//拼接json数据集字符串
			var clonedTr = tr.clone();
				 clonedTr.children("td").each(function(inner_index){
				 	switch(inner_index){  
						case(0):   
                            $(this).html(field.unitid);  
                            break;  
                        case(1):  
	                        $(this).html(field.category);  
                            break;  
                        case(2):  
                             $(this).html("<a href=\"#\" name="+field.name+" onclick=\"queryall(this.name)\">"+field.name+"</a>");  
                             break;  
                        case(3):  
                             $(this).html(field.running);  
                             break;
                       	case(4):
                       		 $(this).html(field.data);
                       		 break;  
                        case(5):  
                             $(this).html(field.executioncycle);  
                             break; 
                        case(6):
                        	 $(this).html(field.mechanism);  
                        	break;
                        case(7):
                        	 $(this).html("<button class=\"btn\" value="+field.unitid+","+field.executioncycle+" onclick=\"executiondelete(this)\">删除</button>");
                        	 break;
                     }		
				 });
				 clonedTr.insertAfter(tr);
			});
			$("#Tr").hide();
				}
			}); 
			hi();
	}
	function hi(){
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
	}
	function demo(){
	var s = document.getElementById('o').value;
	$(".many").empty();
	$.ajax({
		type:"POST",
		url:"MainNamedl.do",
		data:{id:1,name:s},
		dateType:"json",
		success:function(data){
			var obj=document.getElementById('oo'); 
			obj.options.length=0;  
			var msg = eval("("+data+")");
			$("#oo").append("<option>全部</option>");
			for(var i = 0;i < msg.length;i++){
				$("#oo").append("<option  value="+msg[i].unitid+">"+msg[i].units+"</option>");		
			}
		}
	});
}
function demo3(){
	$("#win").hide();
	var s = document.getElementById('oo').value;	
	$.ajax({
		type:"POST",
		url:"MainNamedl.do",
		data:{id:2,name:s},
		dateType:"json",
		success:function(data){
			$(".many").empty();
			var a = data.substring(data.indexOf("[")+1,data.lastIndexOf("]"));
			var str = a.split(",");
			var name;
			var checkbox = "checkbox";
			for(var i = 0;i < str.length;i++){
				name = str[i].substring(str[i].indexOf("\"")+1,str[i].lastIndexOf("\""));
				if(i != 0&&i % 3 == 0){
					$(".many").append("</p><input type=\"checkbox\" name=\"kt\" value="+name+" id="+checkbox+i+" class=\"chk_1\" /><label for="+checkbox+i+">"+name+"</label>");
				}else{
					$(".many").append("<input type=\"checkbox\" name=\"kt\" value="+name+" id="+checkbox+i+" class=\"chk_1\" /><label for="+checkbox+i+">"+name+"</label>");
				}
			}
			var s = document.getElementById('divs');	
			s.style.display="block";
		}
	});
}
function inputname(d){
	if(d == 1){
		$("#opo").window("close");
	}else{
		 $("#opo").window("open");
	}
}
function addmaintain(){
	var name1=document.getElementsByName('kt');
	var DeviceName=''; 
	for(var i=0; i<name1.length; i++){ 
		if(name1[i].checked) DeviceName+=name1[i].value+','; //如果选中，将value添加到变量s中 
	}
	if(DeviceName == ""){
		alert("请选择设备");
	}else{
		var DeviceClass = document.getElementById('oo').value;
		var Content;
		var obj = document.getElementsByName("Content");
		for(var i=0; i<obj.length; i ++){
	        if(obj[i].checked){
	            Content = obj[i].value;
	        }
	    }
		var ExecutionDate =  document.getElementById("test1").value;
		var Executor="";
		var Category = document.getElementById("Category").value;
	   if(Content == null||ExecutionDate == ""){
	   		alert("请选择保养类型和日期");
	   }else{
	   		 $.ajax({
				type:"POST",
				url:"addmaintaindl.do",
				data:{DeviceClass:DeviceClass , DeviceName:DeviceName , ExecutionDate:ExecutionDate,Executor:Executor ,Content:Content,Category:Category},
				dataType:"Json", 
				success:function(data){
					window.location.reload();
				}
			});
	   }	
	}
}
function executiondelete(d){
	 $.ajax({
		type:"POST",
		url:"exectiondeletedl.do",
		data:{unitid:d.value},
		dataType:"Json",
		success:function(data){
			alert("删除成功");
			window.location.reload();
		}
	}); 
}
function querymainj(){
	var options=$("#osd option:selected");
	$.ajax({
		type:"POST",
		url:"Query2dl.do",
		data:{unitid:options.val()},
		dataType:"Json",
		success:function(datas){
			$("#query").window("open");
			var msg = eval("("+datas+")");
			var myobj=eval(msg);
			var tr = $("#Que");
			$.each(myobj, function (i, field) {
				var clonedTr = tr.clone();
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){ 
						case(0):   
                            $(this).html(field.maintenancecategory);  
                            break;
                        case(1):
                        	$(this).html(field.executiondata);  
                            break;
                        case(2):
                        	$(this).html(field.dateofexecution);  
                            break;
                        case(3):
                        	$(this).html(field.enddate);  
                            break;
                        case(4):
                        	if(field.whether == 0){
                        		$(this).html("未保养"); 	
                        	}else if(field.whether == 1){
                        		$(this).html("已保养"); 
                        	}else if(field.whether == 2){
                        		$(this).html("已过期");
                        	}else if(field.whether == 3){
                        		$(this).html("待通知");
                        	}else if(field.whether == 4){
                        		$(this).html("已通知");
                        	}
                            break;
                        case(5):
                        	$(this).html(field.implementation); 
                        	break;
                        case(6):
                        	$(this).html(field.degree);
                        	break;
                       	case(7):
                       		$(this).html(field.content);
                       		break;
					}
				});
				clonedTr.insertAfter(tr);
			});
			$("#Que").hide(); 
		}
	});
}
function claos(){
	$("#query").window("close");
}
function queryall(value){
	var obj = document.getElementsByName("Content");
	$("#queryall").window("open");
	$("#queryall").css("display","block");
	$.ajax({
		type:"POST",
		url:"Query3dl.do",
		data:{unitid:value},
		dataType:"Json",
		async: false,
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
						start = "已保养"; 
	                }else if(msg[o].whether == 2){
						start = "已过期";
	                }else if(msg[o].whether == 3){
	                	start = "待通知";
	                }else if(msg[o].whether == 4){
	                	start = "已通知";
	                }
			        $("#queall").after("<tr id=\"k"+o+"\" name=\"zhi\" value="+msg.length+"><td>"+msg[o].maintenancecategory+"</td><td>"+msg[o].executiondata+"</td><td>"+msg[o].dateofexecution+"</td><td>"+msg[o].enddate+"</td><td>"+start+"</td><td>"+msg[o].implementation+"</td><td>"+msg[o].degree+"</td><td>"+msg[o].content+"</td><td><button value="+msg[o].unitid+","+msg[o].executiondata+" onclick=\"wi(this.value)\">修改</button></td><td><button value="+msg[o].unitid+","+msg[o].executiondata+" onclick=\"wis(this.value)\">删除</button></td></tr>");
			    }
			/* alert(datas);
			var msg = eval("("+datas+")");
			var myobj=eval("("+msg+")");
			var tr = $("#queall");
			$.each(myobj, function (i, field) {
				var clonedTr = tr.clone();
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){ 
						case(0):   
                            $(this).html(field.maintenancecategory);  
                            break;
                        case(1):
                        	$(this).html(field.executiondata);  
                            break;
                        case(2):
                        	$(this).html(field.dateofexecution);  
                            break;
                        case(3):
                        	$(this).html(field.enddate);  
                            break;
                        case(4):
                        	if(field.whether == 0){
                        		$(this).html("已保养"); 	
                        	}else if(field.whether == 1){
                        		$(this).html("未保养"); 
                        	}else if(field.whether == 2){
                        		$(this).html("已过期");
                        	}
                            break;
                        case(5):
                        	$(this).html(field.implementation); 
                        	break;
                        case(6):
                        	$(this).html(field.degree);
                        	break;
                       	case(7):
                       		$(this).html(field.content);
                       		break;
                       	case(8):
                        	 $(this).html("<button value="+field.maintenancecategory+","+field.executiondata+" onclick=\"wi(this.value)\">修改</button>");
                        	 break;
					}
				});
				clonedTr.insertAfter(tr);
			});
			$("#queall").hide(); */
		}
	});
	/* $("#queall").html("<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>"); */
}
function wi(a){
	$.ajax({
		type:"POST",
		url:"QueryCentent2dl.do",
		data:{name : a},
		dataType:"Json",
		success:function(data){
			$("#addconcent").window("open");
		    $("#addconcent").html("<textarea id=\"text\" style=\"width:400px; height:200px;\">"+data.substring(data.indexOf("\"")+1,data.lastIndexOf("\""))+"</textarea></p><button value=\""+a+"\" onclick=\"qin(this.value)\" style=\"margin-top:10px;\">确定</button> <button style=\"margin-top:10px;margin-left=10px;\" onclick=\"qu()\">取消</button>"); 
		},
	});
}
function wis(a){
	$.ajax({
		type:"POST",
		url:"deleteMaintenancedl.do",
		data:{name:a},
		dataType:"Json",
		success:function(data){
			alert("删除成功");
			window.location.reload();
		}
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
<body>
<div id="addconcent" class="easyui-window" title="修改本次保养内容"  style="width:500px; height:300px;padding:5px;"
			 data-options="iconCls:'icon-save',closable:true,closed:true,modal:true,maximizable:true,closable:true,collapsible:true">
</div>
<div id="queryall" class="easyui-window" title="保养记录"  style="width:90%; height:80%;padding:5px;"
			 data-options="iconCls:'icon-save',closable:true,closed:true,modal:true,maximizable:true,closable:true,collapsible:true">
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
			<th>修改</th>
			<th>删除</th>
		</tr>
		<tr id="queall">
			
		</tr>
	</table>
</div>
<div id="query" class="easyui-window" title="增加保养内容"  style="width:800px;height:800px;padding:5px;"
			 data-options="iconCls:'icon-save',closable:true,closed:true,modal:true,maximizable:true,closable:true,collapsible:true">
			 <button onclick="claos()">关闭</button>
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
		</tr>
		<tr id="Que">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>
	<div class="easyui-layout" data-options="fit:true">
		<div region="west" split="true" title="内容" style="width:490px;">
			<div class="easyui-layout" style="width:480px;height:100%">
				 <div region="north" style="height:1100px;">
					<div class="select" style="height:100px;">
						--类别:
						<select id="o" onchange="demo()" style="width:200px">
							<option>全部</option>
						</select>
						--类别:
						<select id="oo" onchange="demo3()" style="width:200px">
						<option>全部</option>
						</select>
					</div>
					</p>
					<div id="ann" class="many" style="height:180px;margin-top:10px;">
					</div>
					<div id="divs" style="height:30px">
						<button class="btn" onclick="inputname()">提交(默认保养内容)</button>
					</div>
				</div> 
			</div>
		</div>
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
  					<td></td>
  					<td></td>
  					<td></td>
  					<td></td>
  					<td></td>
  					<td></td>
  					<td></td>
  					<td></td>
  				</tr>
			</table>
		</div>
	</div>
	<div id="opo" class="easyui-window" title="添加保养"  style="width:800px;height:200px;padding:5px;"
			 data-options="iconCls:'icon-save',closed:true,modal:true,minimizable:false,maximizable:false,closable:false,collapsible:true">
    	<table height="25%" width="100%" style="border-collapse:separate; border-spacing:0px 10px;">
    		<tr>
    			<th><input type="text" class="demo-input" placeholder="请选择执行日期" id="test1"></th>
    			<th>保养类别<input id="Category" type="text" placeholder="自维或外维"/></th>
    		</tr>
    		<tr>
    			<th><input name="Content" type="radio" value="月度" />月度</label></th>
    			<th><input name="Content" type="radio" value="季度" />季度</label></th>
    			<th><input name="Content" type="radio" value="年度" />年度</label></th>
    		</tr>
    		<tr>
    			<th><button class="btn" onclick="addmaintain()">确定</button></th>
    			<th><button class="btn" onclick="inputname(1)">取消</button></th>
    		</tr>
    	</table>
	</div>
<script src="laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script>
lay('#version').html('-v'+ laydate.v);

//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});
</script>
</body>
</html>