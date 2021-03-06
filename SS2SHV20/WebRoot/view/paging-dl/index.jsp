<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Highcharts Example</title>

		<script type="text/javascript" src="../../js/jquery.min.js"></script>
		<script type="text/javascript" src="../../jedate/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="../../jedate/jquery.jedate.js"></script>
		<link type="text/css" rel="stylesheet" href="../../jedate/skin/jedate.css">
		<link type="text/css" rel="stylesheet" href="../../css/calander.css">
		<script type="text/javascript">
		var dataTmpA = "";
		var dataTmpB = "";
		var dataTmpC = "";
		var temptime = "";
		function Getseries(){
			$("#jinx1").attr("disabled",true);
			$("#jinx2").attr("disabled",true);
			$("#jinx3").attr("disabled",true);
			$("#jinx4").attr("disabled",true);
			$("#jinx5").attr("disabled",true);
			$.ajax({ 
            	type: "post",
            	dataType: "json",
            	data:{time:temptime},  
            	url: "loadmacdl.do",
            	success: function (data) {
            		var msg = eval("("+data+")");
            		var jsons = eval(data);
            		var index=new Array();
            		for (var key in jsons){
            			var i=jsons[key];
            			index.push(i);
            			if(i=="1"){
            			$("#jinx1").attr("disabled",false);
            			}else if(i=="2"){
            			$("#jinx2").attr("disabled",false);
            			}else if(i=="3"){
            			$("#jinx3").attr("disabled",false);
            			}else if(i=="4"){
            			$("#jinx4").attr("disabled",false);
            			}else if(i=="5"){
            			$("#jinx5").attr("disabled",false);
            			}		
            		}
            		if(index.length>0){
            			GetseriesValue(index[0]);
            			redraw();
            			
            		}
  					
            	}
        	});
		}
		function GetseriesValue(index){
        	$.ajax({ 
            	type: "post",
            	dataType: "json",
            	data:{time:temptime,flag:index},  
            	url: "loaddl.do",
            	success: function (data) {
            		dataTmpA = "";
					dataTmpB = "";
					dataTmpC = "";
            		var msg = eval("("+data+")");
                	$.each(msg, function (i, field) {
                	//拼接json数据集字符串
                	dataTmpA += field.dianliuA + ",";
               	 	dataTmpB += field.dianliuB + ",";
               	 	dataTmpC += field.dianliuC + ",";
            		});
            		dataTmpB=dataTmpB.substring(0, dataTmpB.length - 1);
            		dataTmpA=dataTmpA.substring(0, dataTmpA.length - 1);
            		dataTmpC=dataTmpC.substring(0, dataTmpC.length - 1);
            		GetData();
            	}
        	});
		}
		
		function GetData() {
  		new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'spline'
            },
            title: {
                text: '进线瞬时电流'
            },
            exporting: {
            enabled:false
			},
            credits: {
            enabled: false
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Wind speed (m/s)'
                },
                min: 0,
                minorGridLineWidth: 0,
                gridLineWidth: 0,
                alternateGridColor: null,
                plotBands: [{ // Light air
                    from: 3,
                    to: 200,
                    color: 'rgba(68, 170, 213, 0.1)',
                    label: {
                        text: '',
                        style: {
                            color: '#606060'
                        }
                    }
                }, { // Light breeze
                    from: 200,
                    to: 400,
                    color: 'rgba(0, 0, 0, 0)',
                    label: {
                        text: '',
                        style: {
                            color: '#606060'
                        }
                    }
                }, { // Gentle breeze
                    from: 400,
                    to: 600,
                    color: 'rgba(68, 170, 213, 0.1)',
                    label: {
                        text: '',
                        style: {
                            color: '#606060'
                        }
                    }
                }, { // Moderate breeze
                    from: 600,
                    to: 800,
                    color: 'rgba(0, 0, 0, 0)',
                    label: {
                        text: '',
                        style: {
                            color: '#606060'
                        }
                    }
                }, { // Fresh breeze
                    from: 800,
                    to: 1000,
                    color: 'rgba(68, 170, 213, 0.1)',
                    label: {
                        text: '',
                        style: {
                            color: '#606060'
                        }
                    }
                }, { // Strong breeze
                    from: 1000,
                    to: 1200,
                    color: 'rgba(0, 0, 0, 0)',
                    label: {
                        text: '',
                        style: {
                            color: '#606060'
                        }
                    }
                }, { // High wind
                    from: 1200,
                    to: 1500,
                    color: 'rgba(68, 170, 213, 0.1)',
                    label: {
                        text: '',
                        style: {
                            color: '#606060'
                        }
                    }
                }]
            },
            tooltip: {
                formatter: function() {
                        return ''+
                        Highcharts.dateFormat('%H:%M:%S', this.x) +': '+ this.y +' A';
                }
            },
            plotOptions: {
                spline: {
                    lineWidth: 4,
                    states: {
                        hover: {
                            lineWidth: 5
                        }
                    },
                    marker: {
                        enabled: false,
                        states: {
                            hover: {
                                enabled: true,
                                symbol: 'circle',
                                radius: 5,
                                lineWidth: 1
                            }
                        }
                    },
                    pointInterval: 1000, // one hour
                    pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)
                }
            },
            series: [{
                name: '电流A',
                data: eval("[" + dataTmpA + "]") 
    
            }, {
                name: '电流B',
                data: eval("[" + dataTmpB + "]") 
            },{
                name: '电流C',
                data: eval("[" + dataTmpC + "]") 
            }]
            ,
            navigation: {
                menuItemStyle: {
                    fontSize: '10px'
                }
            }
        });
    	};
    	
    	function redraw() {
    // create the chart
    	$('#container').highcharts({
        series:  [{
                name: '电流A',
                data: eval("[" + dataTmpA + "]") 
    
            }, {
                name: '电流B',
                data: eval("[" + dataTmpB + "]") 
            },{
                name: '电流C',
                data: eval("[" + dataTmpC + "]") 
            }],
    	});
		};
    	
    	function onbutton1() {
			GetseriesValue(1);
			redraw();
		};
		function onbutton2() {
			GetseriesValue(2); 
			redraw();
		};
		function onbutton3() {
			GetseriesValue(3);
			redraw();
		};
		function onbutton4() {
			GetseriesValue(4);
			redraw();
		};
		function onbutton5() {
			GetseriesValue(5);
			redraw();
		};
		
		</script>
	</head>
<body>
<script src="../../js/highcharts.js"></script>
<script src="../../js/modules/exporting.js"></script>
	<div style="width:100%; margin-bottom:0px; overflow:hidden;">
    	<ul>
    		<li class="datep"><input class="datainp wicon" id="date01" type="text" placeholder="YYYY-MM-DD hh:mm:ss" value=""  readonly></li>
			
		</ul>
	</div>
	<div style="float:left">
	<input type="button" value="1#进线" id="jinx1" disabled="true" onclick="onbutton1()"/>
	<input type="button" value="2#进线" id="jinx2" disabled="true" onclick="onbutton2()"/>
	<input type="button" value="3#进线" id="jinx3" disabled="true" onclick="onbutton3()"/>
	<input type="button" value="4#进线" id="jinx4" disabled="true" onclick="onbutton4()"/>
	<input type="button" value="5#进线" id="jinx5" disabled="true" onclick="onbutton5()"/>
	</div>
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<script type="text/javascript">
	$("#date01").jeDate({
    	festival:false,
    	ishmsVal:false,
    	minDate: $.nowDate(-30),
    	maxDate: $.nowDate(0),
    	format:"YYYY-MM-DD hh:mm:ss",
    	zIndex:300,
    	choosefun:function(val){temptime=val.context.value},
    	okfun:function(val){temptime=$("#date01").val();
    	Getseries();}
	});
</script>
</body>
</html>
