<%@page import="com.bryantchang.autodepsys.bean.Cluster"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bryantchang.autodepsys.bean.User"%>
<%@ page import="com.bryantchang.autodepsys.constant.Constants" %>
<%
	ArrayList<Cluster> list = (ArrayList<Cluster>) request.getAttribute("clusterlist");
	String baseUrl = Constants.BaseUrl;
	for(int i = 0; i < list.size(); i++) {
	    out.println(list.get(i).getCname());
	}
%>



<jsp:include page="../nav.jsp" />


</div>
</html>




