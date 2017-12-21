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
	<link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<script src="modernizr.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
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
			var ss = document.getElementById('win');	
			ss.style.display = "none";
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
                             $(this).html(field.name);  
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
                        	 $(this).html(field.executor);  
                             break;  
                        case(7):
                        	 $(this).html(field.mechanism);  
                        	break;
                        case(8):
                        	 $(this).html("<button value="+field.unitid+","+field.executioncycle+" onclick=\"executiondelete(this)\">删除</button>");
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
function updata(be){
	var name;
	var s = document.getElementById('oo').value;
	$("#win").show();
	$.ajax({
		type:"POST",
		url:"QueryCententdl.do",
		data:{name:s},
		dateType:"json",
		success:function(data){
			var msg = eval("("+data+")");
			alert(data);
            var tr = $("#cloneTr");
            $.each(msg, function (i, field) {
			//拼接json数据集字符串
			var clonedTr = tr.clone();
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){
						case(0):
							$(this).html(field.name);
							break;
						case(1):
							$(this).html(field.month);
							break;
						case(2):
							$(this).html(field.season);
							break;
						case(3):
							$(this).html(field.year);
							name = field.whole;
							break;
						case(4):
					 }
				 });
				 if(be == 1){
				 	
				 }else{
				 	clonedTr.insertAfter(tr);
				 }
			});
			$("#kk").empty();
			var strs= new Array();
			strs=name.split(",");
			for(var i = 0;i < strs.length;i++){
				if(i != 0&&i % 5 == 0){
					$("#kk").append("</p><input type=\"checkbox\"  name=\"val\" value="+strs[i]+" id="+i+" class=\"chk_1\" /><label for="+i+">"+strs[i]+"</label>");
				}else{
					$("#kk").append("<input type=\"checkbox\" name=\"val\" value="+strs[i]+" id="+i+" class=\"chk_1\" /><label for="+i+">"+strs[i]+"</label>");
				}
			}
			$("#kk").append("<button onclick=\"addcentent()\">+</button>");
		},
	});
	
}
function addcentent(){
	var s = document.getElementById('oo').value;
	var name=prompt("请输入要添加的内容(多个用,号隔开)","");//将输入的内容赋给变量 name 
	if(name){
		$.ajax({
			type:"POST",
			url:"addcontentdl.do",
			data:{name:name,id:s},
			dateType:"json",
			success:function(data){
				alert(name+"(添加成功)");
				updata(1);
			},
		});
	}else{
		alert("取消成功");
	}
}
function updatecentent(da){
	var sss = document.getElementById('oo').value;
	var obj=document.getElementsByName('val');
	var s=''; 
	for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked) s+=obj[i].value+','; //如果选中，将value添加到变量s中 
	} 
	$.ajax({
		type:"POST",
		url:"UpdateCententdl.do",
		data:{name:sss,id:da,centent:s},
		dataType:"json",
		success:function(data){
			alert("修改成功");
			window.location.reload();
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
	var DeviceClass = document.getElementById('oo').value;
	var Content;
	var obj = document.getElementsByName("Content");
	for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
            Content = obj[i].value;
        }
    }
	var ExecutionDate =  document.getElementById("Executiondate").value;
	var Executor = document.getElementById("Executor").value;
	var Category = document.getElementById("Category").value;
   
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

function wind(da){
	if(da == 1){
		$("#addconcent").window("close");
	}else{
		 $("#addconcent").window("open");
	}
}
function addconcent(){
	var s = document.getElementById('oo').value;
	var yue = document.getElementById("yue").value;
	var ji = document.getElementById("ji").value;
	var nian = document.getElementById("nian").value;
	var qbnr = document.getElementById("qbnr").value;
	$.ajax({
		type:"POST",
		url:"addcontenrdl.do",
		data:{s:s,yue:yue,ji:ji,nian:nian,qbnr:qbnr},
		dataType:"Json",
		success:function(data){
			$("#addconcent").window("close");
			alert("添加成功");
			updata(2);
		}
	});
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
</script>
</head>
<body>
<div id="query" class="easyui-window" title="增加保养内容"  style="width:800px;height:800px;padding:5px;"
			 data-options="iconCls:'icon-save',closed:true,modal:true,minimizable:false,maximizable:false,closable:false,collapsible:true">
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
					<div id="ann" class="many" style="height:180px;">
					</div>
					<div id="divs" style="height:30px">
						<button onclick="inputname()">提交(默认保养内容)</button>
						<button onclick="updata()">修改或查看</button>
						<button onclick="wind()">添加保养内容</button>
					</div>
					<div id="win" style="height:100px;">
						<table class="imagetable" border="1" style="width:100%;padding:1px">
								<tr>
									<th>设备名</th>
									<th>月度</th>
									<th>季度</th>
									<th>年度</th>
								</tr>
								<tr id="cloneTr">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
						</table>
						<button onclick="updatecentent(1)">修改成月度</button>
						<button onclick="updatecentent(2)">修改成季度</button>
						<button onclick="updatecentent(3)">修改成年度</button>
						</p>
						<div id="kk">
						</div>
					</div>
				</div> 
			</div>
		</div>
		<div id="content" region="center" title="保养详情" style="padding:5px;">
			<div id="tsd">
				<select id="osd"  style="width:200px">
				</select>
				<button onclick="querymainj()">查询</button>
			</div>
			<table class="imagetable" border="1" style="width:100%;padding:5px">
  				<tr>
    				<th>序号</th>
   					<th>设备类别</th>
   					<th>设备名称</th>
   					<th>运行时间(小时)</th>
   					<th>日期</th>
   					<th>执行周期</th>
   					<th>执行人</th>
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
  					<td></td>
  				</tr>
			</table>
		</div>
	</div>
	<div id="opo" class="easyui-window" title="添加保养"  style="width:800px;height:200px;padding:5px;"
			 data-options="iconCls:'icon-save',closed:true,modal:true,minimizable:false,maximizable:false,closable:false,collapsible:true">
    	<table height="25%" width="100%" style="border-collapse:separate; border-spacing:0px 10px;">
    		<tr>
    			<th><label for="meeting">计划执行日期:</label><input id="Executiondate" type="date" /></th>
    			<th>执行人:<input type="text" id="Executor"/></th>
    		</tr>
    		<tr>
    			<th><input name="Content" type="radio" value="月度" />月度</label></th>
    			<th><input name="Content" type="radio" value="季度" />季度</label></th>
    			<th><input name="Content" type="radio" value="年度" />年度</label></th>
    		</tr>
    		<tr>
    			<th>保养类别<input id="Category" type="text" placeholder="自维或外维"/></th>
    		</tr>
    		<tr>
    			<th><button onclick="addmaintain()">确定</button></th>
    			<th><button onclick="inputname(1)">取消</button></th>
    		</tr>
    	</table>
	</div>
		<div id="addconcent" class="easyui-window" title="增加保养内容"  style="width:600px;height:200px;padding:5px;"
			 data-options="iconCls:'icon-save',closed:true,modal:true,minimizable:false,maximizable:false,closable:false,collapsible:true">
			 <h1>输入多个用逗号(,)隔开      例如:1,2</h1>
			 <table height="25%" width="100%" style="border-collapse:separate; border-spacing:0px 10px;">
			 	<tr>
			 		<th>月度:<input id="yue" type="text" /></th>
			 		<th>季度:<input id="ji" type="text" /></th>
			 	</tr>
			 	<tr>
			 		<th>年度:<input id="nian" type="text" /></th>
			 		<th>全部内容:<input id="qbnr" type="text" /></th>
			 	</tr>
			 	<tr>
			 		<th><button onclick="addconcent()">确定</button></th>
			 		<th><button onclick="wind(1)">取消</button></th>
			 	</tr>
			 </table>
		</div>
</body>
</html>