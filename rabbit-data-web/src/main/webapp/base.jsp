<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 导入js css的库文件 -->

<!-- ECharts 数据可视化需引入的js -->
<!--Step:1 引入一个模块加载器，如esl.js或者require.js-->
<script src="<%=request.getContextPath() %>/jslib/esl.js"></script>
<!-- jquery-1.11.1.min.js -->
<script src="<%=request.getContextPath() %>/jslib/jquery-1.11.1.min.js"></script>

<!-- Bootstrap -->
<link href="<%=request.getContextPath() %>/csslib/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/jslib/bootstrap.min.js"></script>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Rabbit Data</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    
</body>