<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>

body {
    width: 1000px;
    margin: 40px auto;
    font-family: 'trebuchet MS', 'Lucida sans', Arial;
    font-size: 14px;
    color: #444;
}

table {
    *border-collapse: collapse; /* IE7 and lower */
    border-spacing: 0;
    width: 100%;    
}

.bordered {
    border: solid #ccc 1px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;         
}

.bordered tr:hover {
    background: #fbf8e9;
    -o-transition: all 0.1s ease-in-out;
    -webkit-transition: all 0.1s ease-in-out;
    -moz-transition: all 0.1s ease-in-out;
    -ms-transition: all 0.1s ease-in-out;
    transition: all 0.1s ease-in-out;     
}    
    
.bordered td, .bordered th {
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    padding: 10px;
    text-align: left;    
}

.bordered th {
    background-color: #dce9f9;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
    background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
    border-top: none;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
}

.bordered td:first-child, .bordered th:first-child {
    border-left: none;
}

.bordered th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;
}

.bordered th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.bordered th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}
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


.button1{
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 16px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
}
.button1 {
    background-color: white; 
    color: black; 
    border: 2px solid #CCCCCC;
}

.button1:hover {
    background-color: #000000;
    color: white;
}
</style>
<script src="maintain/js/jquery-1.8.3.min.js"></script>
<script src="maintain/js/jquery-labelauty.js"></script>
<script type="text/javascript">
$(function(){
	/* $("#login").hide();
	$.ajax({
		type: "POST",
		url: "MaintainQuerydl.do",
		success: function (data) {
			var msg = eval("("+data+")");
            var tr = $("#cloneTr");
            $.each(msg, function (i, field) {
			//拼接json数据集字符串
			var clonedTr = tr.clone();
				 clonedTr.children("td").each(function(inner_index){
				 	switch(inner_index){  
						case(0):   
                            $(this).html(field.id);  
                            break;  
                        case(1):  
	                        $(this).html(field.name);  
                            break;  
                        case(2):  
                             $(this).html(field.rundate);  
                             break;  
                        case(3):  
                             $(this).html(field.upper);  
                             break;  
                        case(4):  
                             $(this).html(field.lower);  
                             break; 
                        case(5):
                        	if(field.state == 1){
                        		$(this).html("需要"); 	
                        	}else{
                        		$(this).html("不需要");
                        	}
                             break;  
                        case(6):
                        	if(field.state == 1){
                        		$(this).html("<a href=\"/S2SH2/MaintainUpdateStatedl.do?name="+field.name+"\">保养</a>");
                        	}else{
                        		$(this).html(" ");
                        	}
                        	break;
                     }		
				 });
				 clonedTr.insertAfter(tr);
			});
			$("#cloneTr").hide();
        },
        fail: function (status) {
            alert("计划添加失败");
        }
	}); */
	var a ="[{\"content\":\"3\",\"dateofexecution\":\"\",\"degree\":\"年度\",\"enddate\":\"\",\"executiondata\":\"2017-09-01\",\"id\":164,\"implementation\":\"\",\"maintenancecategory\":\"2#变压器\",\"unitid\":\"DF-QD-01-2\",\"whether\":\"0\"},{\"content\":\"3\",\"dateofexecution\":\"\",\"degree\":\"年度\",\"enddate\":\"\",\"executiondata\":\"2018-07-01\",\"id\":167,\"implementation\":\"\",\"maintenancecategory\":\"2#变压器\",\"unitid\":\"DF-QD-01-2\",\"whether\":\"0\"}]"
	var msg = eval("("+a+")");
	var tr = $("#cloneTr");
    $.each(msg, function (i, field){
    	var clonedTr = tr.clone();
    	$.each(msg, function (i, field) {
    		switch(inner_index){ 
    			case(0):   
    				$(this).html("ds");  
					break;  
    		}
    	});
    	 clonedTr.insertAfter(tr);
    });
});
function clicke(){
	window.location.href="/S2SH/maintain/add.jsp";
}
</script>
</head>

<body>

<h2>保养计划</h2>
<div id="t">
	<table class="bordered">
    <thead>
    <tr>
        <th>id</th>        
        <th>设备名</th>
        <th>运行时间(小时)</th>
        <th>上一次保养月份</th>
        <th>下一次保养月份</th>
        <th>是否需要</th>
        <th>保养按钮</th>
    </tr>
    </thead>
    <tbody>  
		<tr id="cloneTr">  
			<td></td>  
			<td></td>  
	        <td></td>  
            <td></td>  
		    <td></td> 
		    <td></td> 
		    <td></td>          
		</tr>  
	</tbody> 
</table>
<button class="button" onClick="clicke()">添加计划</button>
</div>
</body>
</html>
