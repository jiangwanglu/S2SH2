<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts tjt</title>
		<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
		<script type="text/javascript"  >
		var tTmp = "";
		var dataTmp = "";
		var dataTmp1 = "";
		var d = new Date();
		var i=0;
		var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		$(function () {
    		GetseriesValue();  //获取数据源信息
		});
		
		
		function GetseriesValue(){
        	$.ajax({ 
            	type: "post",
            	dataType: "json",
            	url: "datenhgk.do",
            	data:{time:str},
            	success: function (data) {
            	var msg = eval("("+data+")");
                $.each(msg, function (i, field) {
               	tTmp += "'"+field.time + "',";
               	dataTmp += field.nh + ",";
               	dataTmp1 += field.nhair + ",";
            	});
            	tTmp=tTmp.substring(0, tTmp.length - 1);
            	dataTmp=dataTmp.substring(0, dataTmp.length - 1);
            	dataTmp1=dataTmp1.substring(0, dataTmp1.length - 1);
            	GetData();
            	}
        	});
		}
		
		function GetData(){
		new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'column'
            },
    
            title: {
                text: '日能耗信息表'
            },
            
            exporting: {
            enabled:false
			},
            credits: {
            enabled: false
            },
    
            xAxis: {
                categories: eval("[" + tTmp + "]")
            },
    
            yAxis: {
                allowDecimals: false,
                min: 0,
                title: {
                    text: '每时能耗'
                }
            },
    
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +': '+ this.y +'<br/>'+
                        '总能耗: '+ this.point.stackTotal;
                }
            },
    
            plotOptions: {
                column: {
                    stacking: 'normal'
                }
            },
    
            series: [{
                name: '空调能耗',
                data: eval("[" + dataTmp1 + "]"),
                stack: 'male'
            }, {
                name: '其他能耗',
                color:"#408080",
                data: eval("[" + dataTmp + "]"),
                stack: 'male'
            }]
        });
		}; 
	</script>
	</head>
	<body>
	<script src="<%=path%>/js/highcharts.js"></script>
	<script src="<%=path%>/js/modules/exporting.js"></script>
	<div float:left>
	<div id="container" style="min-width: 90%; height: 400px; margin: 0 auto"></div>

	</body>
</html>
