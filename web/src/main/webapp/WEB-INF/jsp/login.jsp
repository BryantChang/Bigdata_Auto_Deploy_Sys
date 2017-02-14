<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--css  -->
	<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="/autodeploymentsys/static/css/reset.css">
    <link rel="stylesheet" href="/autodeploymentsys/static/css/supersized.css">
    <link rel="stylesheet" href="/autodeploymentsys/static/css/style.css">
    
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>

	<script type="text/javascript">
		
	</script>
	
	
	<div style="margin: 0 auto; padding-top: 100px;" align="center">
	<h1 align="center">登&nbsp;&nbsp;录</h1>
		<form action="doLogin" method="post">
			<input type="text" name="username" class="text" placeholder="用户名">
            <input type="password" name="password" class="text" placeholder="密码">
            <input type="password" name="vcode" class="vcode text" placeholder="验证码"><br/>
            <p id="code"><img id="vcode" src="vcode"/><a id="change_code" href="javascript:void(0);">换一张</a></p>
            <br/>
            <p id="toreg">仍没有账户请<a href="reg">注册</a></p>
            <button type="submit">登录</button>
			<p style="color: red;">${error }</p>
		</form>
	</div>
	<script src="/autodeploymentsys/static/js/jquery-1.8.2.min.js"></script>
	<script src="/autodeploymentsys/static/js/supersized.3.2.7.min.js"></script>
	<script src="/autodeploymentsys/static/js/supersized-init.js"></script>
	<script src="/autodeploymentsys/static/js/scripts.js"></script>
	<script src="/autodeploymentsys/static/js/main.js"></script>
</body>
</html>