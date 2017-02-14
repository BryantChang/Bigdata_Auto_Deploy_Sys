<%@page import="com.bryantchang.autodepsys.bean.HadoopNode"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bryantchang.autodepsys.bean.User"%>
<%@ page import="com.bryantchang.autodepsys.constant.Constants" %>
<%@ page import="com.bryantchang.autodepsys.bean.SparkNode" %>
<%
  User user = (User) request.getAttribute("user");
  ArrayList<SparkNode> list = (ArrayList<SparkNode>) request.getAttribute("sparknodelist");
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
        <h4 align="center">Spark集群操作</h4>
        <a  id="btn_startSparkCluster" href="#" role="button"
            class="btn btn-primary btn-block sparkClusterOps" type="spark" ops="start"  data-toggle="modal">启动Spark</a>
        <a  id="btn_stopSparkluster" href="#" role="button"
            class="btn btn-danger btn-block sparkClusterOps" type="spark" ops="stop" data-toggle="modal">停止Spark</a>
      </div>
      <div class="span10">
        <c:if test="${list.size() != 0}">
          <h3 align="center">Spark节点列表</h3>


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
            <c:forEach items="${sparknodelist}" var="sparknode"
                       varStatus="no">
              <tr>
                <td>${no.count}</td>
                <td>${sparknode['hostname']}</td>
                <td>${sparknode['ip']}</td>
                <td>
                <c:if test="${sparknode['master'] == true}">
                  <div class="btn-group">
                    <button masterip="${masterip}" nodeid="${sparknode['id']}" ip="${sparknode['ip']}" nodetype="master" class="btn sparkNodeOps dropdown-toggle" data-toggle="dropdown">master<span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li id="status_master_${sparknode['id']}">&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:</li>
                      <li class="divider"></li>
                      <li><a data-toggle="modal" logname="spark-${sparknode['sshuser']}-org.apache.spark.deploy.master.Master-1-${sparknode['hostname']}" class="sparkViewLogbtn" href="#sparkViewLog"><i class="icon-search"></i>查看日志</a></li>
                      <li class="divider"></li>
                      <li><a class="sparkOps" nodetype="master" clustertype="spark" ip="${sparknode['ip']}" opstype="start" href="#"><i class="icon-play"></i>启动master</a></li>
                      <li><a class="sparkOps" nodetype="master" clustertype="spark" ip="${sparknode['ip']}" opstype="stop" href="#"><i class="icon-stop"></i>停止master</a></li>
                    </ul>
                  </div>
                </c:if>
                  <c:if test="${sparknode['slave'] == true}">
                    <div class="btn-group">
                      <button masterip="${masterip}" nodeid="${sparknode['id']}" ip="${sparknode['ip']}" nodetype="slave" class="btn sparkNodeOps dropdown-toggle" data-toggle="dropdown">slave<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                        <li id="status_slave_${sparknode['id']}">&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:</li>
                        <li class="divider"></li>
                        <li><a data-toggle="modal" logname="spark-${sparknode['sshuser']}-org.apache.spark.deploy.worker.Worker-1-${sparknode['hostname']}" class="sparkViewLogbtn" href="#sparkViewLog"><i class="icon-search"></i>查看日志</a></li>
                        <li class="divider"></li>
                        <li><a class="sparkOps" masterip="${masterip}" nodetype="slave" clustertype="spark" ip="${sparknode['ip']}" opstype="start" href="#"><i class="icon-play"></i>启动slave</a></li>
                        <li><a class="sparkOps" masterip="${masterip}" nodetype="slave" clustertype="spark" ip="${sparknode['ip']}" opstype="stop" href="#"><i class="icon-stop"></i>停止slave</a></li>
                      </ul>
                    </div>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>



          <div id="sparkNodeOpsModal"
               class="modal hide fade" tabindex="-1" role="dialog"
               aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
              <div id="spark_node_ops_body" class="modal-body">操作执行中...</div>
            </div>
            <div class="modal-footer">
              <button id="sparkNodeOpsClose" type="button" disabled="disabled" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
          </div>


          <div id="sparkViewLog"
               class="modal hide fade" tabindex="-1" role="dialog"
               aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
              <div id="view_log_body" class="modal-body"></div>
            </div>
            <div class="modal-footer">
              <button id="sparkViewLogClose" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </c:if>
      </div>
    </div>
    </html>
  </c:otherwise>
</c:choose>
