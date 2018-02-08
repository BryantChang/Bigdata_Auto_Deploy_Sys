<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bryantchang.autodepsys.bean.User"%>
<%@ page import="com.bryantchang.autodepsys.constant.Constants" %>
<%
    String userId = (String) request.getAttribute("userid");
	String baseUrl = Constants.BaseUrl;
	out.println(userId);
%>
<c:choose>
	<c:when test="${user == null}">
		<script>
			location.href = "<%=baseUrl%>";
		</script>
	</c:when>
	<c:otherwise>
		<jsp:include page="nav.jsp" >
		    <jsp:param name="userid" value="<%=baseUrl%>" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="hero-unit">
					<h3>大数据平台自助管理系统</h3>
					<p>我们开发本系统希望能够让同学们更加方便的配置和使用大数据集群，通过我们的系统，可以动态的调整大数据集群的规模，提交任务，查看提交的任务等，使得同学们可以更简单的使用大数据平台。</p>
				</div>
			</div>
		</div>
		</div>
		</html>
	</c:otherwise>
</c:choose>



