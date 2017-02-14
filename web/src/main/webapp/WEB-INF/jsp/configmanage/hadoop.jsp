<%@ page import="com.bryantchang.autodepsys.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bryantchang.autodepsys.bean.User"%>
<%@ page import="com.bryantchang.autodepsys.constant.Constants" %>
<%
    User user = (User) request.getAttribute("user");
    String baseUrl = Constants.BaseUrl;
%>


<c:choose>
    <c:when test="${user == null}">
        <script>
            location.href = "<%=baseUrl%>";
        </script>
    </c:when>
    <c:otherwise>
        <jsp:include page="../nav.jsp"/>
        <div class="row-fluid">
            <%--<div class="span2"></div>--%>
            <div class="span12">
                <h3 align="center">Hadoop配置文件列表</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>文件名</th>
                            <th>文件路径</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hadoopconfigfilelist}" var="item" varStatus="no">
                        <tr>
                            <td>${no.count}</td>
                            <td>${item.value}</td>
                            <td>$HADOOP_HOME/etc/hadoop/${item.value}</td>
                            <td>
                                <a item="${item.key}" href="javascript:void(0);" class="btn generateHadoopConfig">生成配置文件</a>
                                <a item="${item.key}" href="<%=baseUrl%>/admin/configmanage/hadoop/${item.key}" class="btn btn-danger modifyHadoopConfig"><i class="icon-wrench"></i>修改配置</a>
                                <a item="${item.key}" href="<%=baseUrl%>/api/configmanage/hadoop/downloadconfig?fileindex=${item.key}&clustertype=hadoop" class="btn btn-primary"><i class="icon-download-alt"></i>下载查看</a>
                                <a item="${item.key}" href="<%=baseUrl%>/admin/configmanage/configpush?clustertype=hadoop&fileindex=${item.key}" class="btn btn-warning">推送配置</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>
