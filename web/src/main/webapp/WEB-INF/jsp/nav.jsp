<%@page import="javax.sound.midi.Soundbank"%>
<%@ page import="com.bryantchang.autodepsys.constant.Constants" %>
<%@page import="com.bryantchang.autodepsys.bean.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>大数据自助管理系统</title>
<link rel="stylesheet" href="/autodeploymentsys/static/css/layout.css"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/autodeploymentsys/static/css/blogsys/blogsys_main.css" />
<link rel="stylesheet" href="/autodeploymentsys/static/css/modal.css" />
<!--[if lt IE 9]>
		<link rel="stylesheet" href="/autodeploymentsys/static/css/ie.css" type="text/css" media="screen" />
		
		<![endif]-->
<script type="text/javascript"
	src="/autodeploymentsys/static/js/jquery.js"></script>
<script src="/autodeploymentsys/static/js/blog_admin/hideshow.js"
	type="text/javascript"></script>
<script
	src="/autodeploymentsys/static/js/blog_admin/jquery.tablesorter.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/autodeploymentsys/static/js/blog_admin/jquery.equalHeight.js"></script>
<script type="text/javascript"
	src="/autodeploymentsys/static/js/bootstrap.js"></script>
<script
	src="/autodeploymentsys/static/js/autosys/clustermanage/hadoopNode.js"
	type="text/javascript"></script>
	<script
			src="/autodeploymentsys/static/js/autosys/clustermanage/sparkNode.js"
			type="text/javascript"></script>
	<script
			src="/autodeploymentsys/static/js/autosys/configmanage/hadoopConfig.js"
			type="text/javascript"></script>
	<script
			src="/autodeploymentsys/static/js/autosys/configmanage/sparkConfig.js"
			type="text/javascript"></script>
	<script
		src="/autodeploymentsys/static/js/autosys/configpush/hadoop.js"
		type="text/javascript"></script>
	<script
			src="/autodeploymentsys/static/js/autosys/nodeops/hadoop.js"
			type="text/javascript"></script>
	<script
			src="/autodeploymentsys/static/js/autosys/nodeops/spark.js"
			type="text/javascript"></script>
	<%
	    User user = (User) request.getAttribute("user");
		String baseUrl = Constants.BaseUrl;
		String hadoopHome = Constants.HADOOP_HOME;
		String sparkHome = Constants.SPARK_HOME;
		String userId = user.getId();
	%>
	<script type="application/javascript">
		var baseUrl = "<%= baseUrl%>";
		var hadoop_home = "<%=hadoopHome%>";
		var spark_home = "<%=sparkHome%>";
	</script>
</head>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="header" class="span12">
			<div class="navbar navbar-inverse">
				<div class="navbar-inner">
					<div class="container-fluid">
						<a data-target=".navbar-responsive-collapse"
							data-toggle="collapse" class="btn btn-navbar"> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
						</a> <a href="/autodeploymentsys/admin/index" class="brand">网站首页</a>
						<div class="nav-collapse collapse navbar-responsive-collapse">
						    <ul class="nav">
                            	<li><a href="/autodeploymentsys/admin/clustermanage/index?userid=<%=userId%>">集群管理</a></li>
                            </ul>
							<ul class="nav">
								<li class="dropdown"><a data-toggle="dropdown"
									class="dropdown-toggle" href="">节点配置管理 <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a
											href="/autodeploymentsys/admin/configmanage/hadoop">Hadoop</a></li>
										<li><a
											href="/autodeploymentsys/admin/configmanage/spark">Spark</a></li>
									</ul></li>
								<li class="dropdown"><a data-toggle="dropdown"
									class="dropdown-toggle" href="">节点操作管理 <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="/autodeploymentsys/admin/nodeops/hadoop">Hadoop</a></li>
										<li><a href="/autodeploymentsys/admin/nodeops/spark">Spark</a></li>
									</ul></li>
							</ul>
							<ul class="nav">
								<li><a href="#">节点监控</a></li>
							</ul>
							<ul class="nav">
								<li><a href="#">用户管理</a></li>
							</ul>
							<ul class="nav">
								<li><a href="/autodeploymentsys/doLogout">登出</a></li>
							</ul>
							<ul class="nav pull-right">
								<li><a href="#">关于本系统</a></li>
							</ul>
						</div>
						<!-- /.nav-collapse -->
					</div>
				</div>
				<!-- /navbar-inner -->
			</div>
		</div>
	</div>