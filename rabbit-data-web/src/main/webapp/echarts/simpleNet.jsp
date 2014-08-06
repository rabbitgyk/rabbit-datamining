<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../base.jsp"></jsp:include>
<title>简单关系网络</title>
<script type="text/javascript">
	
    // Step:3 conifg ECharts's path, link to echarts.js from current page.
    // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
    require.config({
        paths:{ 
            'echarts': '<%=request.getContextPath() %>/jslib/echarts',
            'echarts/chart/force': '<%=request.getContextPath() %>/jslib/echarts'
        }
    });
    
    // Step:4 require echarts and use it in the callback.
    // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
    require(
        [
            'echarts',
            'echarts/chart/force'
        ],
        function (ec) {
            var myChart = ec.init(document.getElementById('main'));
            $.post('<%=request.getContextPath() %>/simpleNet/get',
            	function (result) {
            	//alert(result.obj.nodes);
            		var option = {
            			    title : {
            			        text: result.obj.title,
            			        subtext: '数据来自UCI',
            			        x:'right',
            			        y:'bottom'
            			    },
            			    tooltip : {
            			        trigger: 'item',
            			        formatter: '{a} : {b}'
            			    },
            			    legend: {
            			        x: 'left',
            			        data:result.obj.categories
            			    },
            			    series : [
            			        {
            			            type:'force',
            			            name : '属性之间关系',
            			            categories : [
            			                {
            			                    name: result.obj.categories[0],
            			                    itemStyle: {
            			                        normal: {
            			                            color : '#87cdfa'
            			                        }
            			                    }
            			                },
            			                {
            			                    name:result.obj.categories[1],
            			                    itemStyle: {
            			                        normal: {
            			                            color : '#9acd32'
            			                        }
            			                    }
            			                }
            			            ],
            			            itemStyle: {
            			                normal: {
            			                    label: {
            			                        show: true,
            			                        textStyle: {
            			                            color: '#800080'
            			                        }
            			                    },
            			                    nodeStyle : {
            			                        brushType : 'both',
            			                        strokeColor : 'rgba(255,215,0,0.4)',
            			                        lineWidth : 8
            			                    }
            			                },
            			                emphasis: {
            			                    label: {
            			                        show: false
            			                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
            			                    },
            			                    nodeStyle : {
            			                        r: 30
            			                    },
            			                    linkStyle : {}
            			                }
            			            },
            			            minRadius : 15,
            			            maxRadius : 25,
            			            density : 0.05,
            			            attractiveness : 1.2,
            			            nodes : result.obj.nodes,
            			            links : result.obj.links
            			        }
            			    ]
            			};
            		myChart.setOption(option);
            }, 'JSON');
            
        }
    );
</script>
</head>
<body>
	<div class="container" style="margin-top:80px; margin-bottom:30px;">
		<!--Step:2 为ECharts准备一个具备大小（宽高）的Dom-->
		<div id="main" style="height:500px;width:700px;border:1px solid #ccc;padding:10px;"></div>
	</div><!-- /.container -->
</body>
</html>