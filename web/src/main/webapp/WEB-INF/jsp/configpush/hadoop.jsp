<%@page import="com.bryantchang.autodepsys.bean.HadoopNode"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bryantchang.autodepsys.bean.User"%>
<%@ page import="com.bryantchang.autodepsys.constant.Constants" %>
<%
  ArrayList<HadoopNode> list = (ArrayList<HadoopNode>) request.getAttribute("hadoopnodelist");
  String baseUrl = Constants.BaseUrl;
%>



<jsp:include page="../nav.jsp" />
<div class="row-fluid">
  <div class="span2">
    <a fileindex="${fileindex}" clustertype="hadoop" nodes="${allnodes}" id="modal-694172" href="#pushToAll" role="button"
       class="btn btn-primary btn-block pushAll" data-toggle="modal">全部推送</a>
  </div>
  <div class="span10">
    <a class="btn btn_primary" href="<%=baseUrl%>/admin/configmanage/hadoop" data-toggle="modal">返回配置列表</a>
    <c:if test="${list.size() != 0}">
      <h3 align="center">Hadoop节点列表</h3>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>编号</th>
          <th>主机名</th>
          <th>IP地址</th>
          <th>节点角色</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${nodelist}" var="hadoopnode"
                   varStatus="no">
          <tr>
            <td>${no.count}</td>
            <td>${hadoopnode['hostname']}</td>
            <td>${hadoopnode['ip']}</td>
            <td><c:if test="${hadoopnode['namenode'] == true}">
              &nbsp;&nbsp;namenode
            </c:if> <c:if test="${hadoopnode['datanode'] == true}">
              &nbsp;&nbsp;datanode
            </c:if> <c:if test="${hadoopnode['secondarynamenode'] == true}">
              &nbsp;&nbsp;secondarynamenode
            </c:if> <c:if test="${hadoopnode['resourcemanager'] == true}">
              &nbsp;&nbsp;resourcemanager
            </c:if> <c:if test="${hadoopnode['nodemanager'] == true}">
              &nbsp;&nbsp;nodemanager
            </c:if></td>
            <td>
              <div class="btn-group">
                <a fileindex="${fileindex}" host="${hadoopnode['hostname']}" clusterType="hadoop" href="#" class="btn btn-warning pushConfig">推送至该节点</a>
              </div>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div id="config_push"
           class="modal hide fade" tabindex="-1" role="dialog"
           aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
          <div id="config_push_body" class="modal-body">配置推送中...</div>
        </div>
        <div class="modal-footer">
          <button id="configPushClose" type="button" disabled="disabled" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
      </div>
    </c:if>
  </div>
</div>

</div>
</html>




