<%@page import="com.bryantchang.autodepsys.bean.HadoopNode"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bryantchang.autodepsys.bean.User"%>
<%@ page import="com.bryantchang.autodepsys.constant.Constants" %>
<%
  User user = (User) request.getAttribute("user");
  ArrayList<HadoopNode> list = (ArrayList<HadoopNode>) request.getAttribute("hadoopnodelist");
  String baseUrl = Constants.BaseUrl;
%>

<c:choose>
  <c:when test="${user == null}">
    <script>
      location.href = "<%=baseUrl%>";
    </script>
  </c:when>
  <c:otherwise>
    <jsp:include page="../nav.jsp" />
    <div class="row-fluid">
      <div class="span2">
        <h4 align="center">HDFS集群操作</h4>
        <a  id="btn_startHDFSCluster" href="#" role="button"
           class="btn btn-primary btn-block hadoopClusterOps" type="hdfs" ops="start"  data-toggle="modal">启动HDFS</a>
        <a  id="btn_stopHDFSCluster" href="#" role="button"
            class="btn btn-danger btn-block hadoopClusterOps" type="hdfs" ops="stop" data-toggle="modal">停止HDFS</a>
        <br/>
        <br/>
        <h4 align="center">YARN集群操作</h4>
        <a  id="btn_startYARNCluster" href="#" role="button"
            class="btn btn-primary btn-block hadoopClusterOps" type="yarn" ops="start" data-toggle="modal">启动YARN</a>
        <a  id="btn_stopYARNCluster" href="#" role="button"
            class="btn btn-danger btn-block hadoopClusterOps" type="yarn" ops="stop" data-toggle="modal">停止YARN</a>
      </div>
      <div class="span10">
        <c:if test="${list.size() != 0}">
          <h3 align="center">Hadoop节点列表</h3>
          <table class="table table-striped">
            <thead>
            <tr>
              <th>编号</th>
              <th>主机名</th>
              <th>IP地址</th>
              <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hadoopnodelist}" var="hadoopnode"
                       varStatus="no">
              <tr>
                <td>${no.count}</td>
                <td>${hadoopnode['hostname']}</td>
                <td>${hadoopnode['ip']}</td>
                <td><c:if test="${hadoopnode['namenode'] == true}">
                  <div class="btn-group">
                    <button nodeid="${hadoopnode['id']}" ip="${hadoopnode['ip']}" nodetype="namenode" class="btn hadoopNodeOps dropdown-toggle" data-toggle="dropdown">namenode<span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li id="status_namenode_${hadoopnode['id']}">&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:</li>
                      <li class="divider"></li>
                      <li><a data-toggle="modal" logname="hadoop-${hadoopnode['sshuser']}-namenode-${hadoopnode['hostname']}" class="hadoopViewLogbtn" href="#hadoopViewLog"><i class="icon-search"></i>查看日志</a></li>
                      <li class="divider"></li>
                      <li><a class="hadoopOps" nodetype="namenode" clustertype="hdfs" ip="${hadoopnode['ip']}" opstype="start" href="#"><i class="icon-play"></i>启动namenode</a></li>
                      <li><a class="hadoopOps" nodetype="namenode" clustertype="hdfs" ip="${hadoopnode['ip']}" opstype="stop" href="#"><i class="icon-stop"></i>停止namenode</a></li>
                    </ul>
                  </div>
                </c:if> <c:if test="${hadoopnode['datanode'] == true}">
                  <div class="btn-group">
                    <button nodeid="${hadoopnode['id']}" ip="${hadoopnode['ip']}" nodetype="datanode" class="btn hadoopNodeOps dropdown-toggle" data-toggle="dropdown">datanode<span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li id="status_datanode_${hadoopnode['id']}">&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:</li>
                      <li class="divider"></li>
                      <li><a data-toggle="modal" logname="hadoop-${hadoopnode['sshuser']}-datanode-${hadoopnode['hostname']}" class="hadoopViewLogbtn" href="#hadoopViewLog"><i class="icon-search"></i>查看日志</a></li>
                      <li class="divider"></li>
                      <li><a class="hadoopOps" nodetype="datanode" clustertype="hdfs" ip="${hadoopnode['ip']}" opstype="start" href="#"><i class="icon-play"></i>启动datanode</a></li>
                      <li><a class="hadoopOps" nodetype="datanode" clustertype="hdfs" ip="${hadoopnode['ip']}" opstype="stop" href="#"><i class="icon-stop"></i>停止datanode</a></li>
                    </ul>
                  </div>
                </c:if> <c:if test="${hadoopnode['secondarynamenode'] == true}">
                  <div class="btn-group">
                    <button nodeid="${hadoopnode['id']}" ip="${hadoopnode['ip']}"  nodetype="secondarynamenode" class="btn hadoopNodeOps dropdown-toggle" data-toggle="dropdown">secondarynamenode<span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li id="status_secondarynamenode_${hadoopnode['id']}">&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:</li>
                      <li class="divider"></li>
                      <li><a data-toggle="modal" logname="hadoop-${hadoopnode['sshuser']}-secondarynamenode-${hadoopnode['hostname']}" class="hadoopViewLogbtn" href="#hadoopViewLog"><i class="icon-search"></i>查看日志</a></li>
                      <li class="divider"></li>
                      <li><a class="hadoopOps" nodetype="secondarynamenode" clustertype="hdfs" ip="${hadoopnode['ip']}" opstype="start" href="#"><i class="icon-play"></i>启动secondarynamenode</a></li>
                      <li><a class="hadoopOps" nodetype="secondarynamenode" clustertype="hdfs" ip="${hadoopnode['ip']}" opstype="stop" href="#"><i class="icon-stop"></i>停止secondarynamenode</a></li>
                    </ul>
                  </div>
                </c:if> <c:if test="${hadoopnode['resourcemanager'] == true}">
                  <div class="btn-group">
                    <button nodeid="${hadoopnode['id']}" ip="${hadoopnode['ip']}" nodetype="resourcemanager" class="btn hadoopNodeOps dropdown-toggle" data-toggle="dropdown">resourcemanager<span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li id="status_resourcemanager_${hadoopnode['id']}">&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:</li>
                      <li class="divider"></li>
                      <li><a data-toggle="modal" logname="yarn-${hadoopnode['sshuser']}-resourcemanager-${hadoopnode['hostname']}" class="hadoopViewLogbtn" href="#hadoopViewLog"><i class="icon-search"></i>查看日志</a></li>
                      <li class="divider"></li>
                      <li><a class="hadoopOps" nodetype="resourcemanager" clustertype="yarn" ip="${hadoopnode['ip']}" opstype="start" href="#"><i class="icon-play"></i>启动resourcemanager</a></li>
                      <li><a class="hadoopOps" nodetype="resourcemanager" clustertype="yarn" ip="${hadoopnode['ip']}" opstype="stop" href="#"><i class="icon-stop"></i>停止resourcemanager</a></li>
                    </ul>
                  </div>
                </c:if> <c:if test="${hadoopnode['nodemanager'] == true}">
                  <div class="btn-group">
                    <button nodeid="${hadoopnode['id']}" ip="${hadoopnode['ip']}" nodetype="nodemanager" class="btn hadoopNodeOps dropdown-toggle" data-toggle="dropdown">nodemanager<span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li id="status_nodemanager_${hadoopnode['id']}">&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:</li>
                      <li class="divider"></li>
                      <li><a data-toggle="modal" logname="yarn-${hadoopnode['sshuser']}-nodemanager-${hadoopnode['hostname']}" class="hadoopViewLogbtn" href="#hadoopViewLog"><i class="icon-search"></i>查看日志</a></li>
                      <li class="divider"></li>
                      <li><a class="hadoopOps" nodetype="nodemanager" clustertype="yarn" ip="${hadoopnode['ip']}" opstype="start" href="#"><i class="icon-play"></i>启动nodemanager</a></li>
                      <li><a class="hadoopOps" nodetype="nodemanager" clustertype="yarn" ip="${hadoopnode['ip']}" opstype="stop" href="#"><i class="icon-stop"></i>停止nodemanager</a></li>
                    </ul>
                  </div>
                </c:if></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <div id="hadoopNodeOpsModal"
               class="modal hide fade" tabindex="-1" role="dialog"
               aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
              <div id="hadoop_node_ops_body" class="modal-body">操作执行中...</div>
            </div>
            <div class="modal-footer">
              <button id="hadoopNodeOpsClose" type="button" disabled="disabled" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
          </div>

          <div id="hadoopViewLog"
               class="modal hide fade" tabindex="-1" role="dialog"
               aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
              <div id="view_log_body" class="modal-body"></div>
            </div>
            <div class="modal-footer">
              <button id="hadoopViewLogClose" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </c:if>
      </div>
    </div>
    </html>
  </c:otherwise>
</c:choose>
