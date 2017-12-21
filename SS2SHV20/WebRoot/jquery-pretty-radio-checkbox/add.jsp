<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
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
    width: 1800px;
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



/*----------------------*/

.zebra td, .zebra th {
    padding: 10px;
    border-bottom: 1px solid #f2f2f2;    
}

.zebra tbody tr:nth-child(even) {
    background: #f5f5f5;
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
}

.zebra th {
    text-align: left;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
    border-bottom: 1px solid #ccc;
    background-color: #eee;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#f5f5f5), to(#eee));
    background-image: -webkit-linear-gradient(top, #f5f5f5, #eee);
    background-image:    -moz-linear-gradient(top, #f5f5f5, #eee);
    background-image:     -ms-linear-gradient(top, #f5f5f5, #eee);
    background-image:      -o-linear-gradient(top, #f5f5f5, #eee); 
    background-image:         linear-gradient(top, #f5f5f5, #eee);
}

.zebra th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;  
}

.zebra th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.zebra th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.zebra tfoot td {
    border-bottom: 0;
    border-top: 1px solid #fff;
    background-color: #f1f1f1;  
}

.zebra tfoot td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.zebra tfoot td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}

.zebra tfoot td:only-child{
    -moz-border-radius: 0 0 6px 6px;
    -webkit-border-radius: 0 0 6px 6px
    border-radius: 0 0 6px 6px
}
</style>
</head>

<body>
<h2>计划</h2>
<table class="bordered">
    <thead>
    	<tr>
        	<th>月份(1-12)</th>        
        	<th>巡检目录</th>
        	<th>操作</th>
    	</tr>
    </thead>
 	 <%@page import="com.pp.test.bo.Patrol;"%>
     <%
     	Patrol tor1 =  (Patrol)session.getAttribute("1patrol");
     	Patrol tor2 =  (Patrol)session.getAttribute("2patrol");
     	Patrol tor3 =  (Patrol)session.getAttribute("3patrol");
     	Patrol tor4 =  (Patrol)session.getAttribute("4patrol");
     	Patrol tor5 =  (Patrol)session.getAttribute("5patrol");
     	Patrol tor6 =  (Patrol)session.getAttribute("6patrol");
     	Patrol tor7 =  (Patrol)session.getAttribute("7patrol");
     	Patrol tor8 =  (Patrol)session.getAttribute("8patrol");
     	Patrol tor9 =  (Patrol)session.getAttribute("9patrol");
     	Patrol tor10 = (Patrol)session.getAttribute("10patrol");
     	Patrol tor11 = (Patrol)session.getAttribute("11patrol");
     	Patrol tor12 = (Patrol)session.getAttribute("12patrol");
     %>
    <tr>
        <td>1月</td>        
        <td><%=tor1.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=1">修改</a></td>
    </tr>        
    <tr>
        <td>2月</td>         
        <td><%= tor2.getName()%></td>
      	<td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=2">修改</a></td>
    </tr>
    <tr>
        <td>3月</td>         
        <td><%= tor3.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=3">修改</a></td>
    </tr>    
    <tr>
        <td>4月</td> 
        <td><%= tor4.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=4">修改</a></td>
        
    </tr>
    <tr>
        <td>5月</td> 
        <td><%= tor5.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=5">修改</a></td>
    </tr>
    <tr>
        <td>6月</td> 
        <td><%= tor6.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=6">修改</a></td>
    </tr>
    <tr>
        <td>7月</td> 
        <td><%= tor7.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=7">修改</a></td>
    </tr>    
    <tr>

        <td>8月</td> 
        <td><%= tor8.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=8">修改</a></td>
    </tr>
    <tr>
        <td>9月</td> 
        <td><%= tor9.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=9">修改</a></td>
    </tr>
    <tr>
        <td>10月</td> 
        <td><%= tor10.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=10">修改</a></td>
    </tr> 
    <tr>
        <td>11月</td> 
        <td><%= tor11.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=11">修改</a></td>
    </tr>
    <tr>
        <td>12月</td> 
        <td><%= tor12.getName()%></td>
        <td><a href="/S2SH2/jquery-pretty-radio-checkbox/index.jsp?id=12">修改</a></td>
    </tr> 
</table>
</body>
</html>
