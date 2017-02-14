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
			<c:if test="${user.getRole() == 0}">
				<div class="span2">
					<a id="modal-694172" href="#addNode" role="button"
						class="btn btn-primary btn-block" data-toggle="modal">添加节点</a>

					<div id="addNode" class="modal hide fade" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h3 id="myModalLabel">添加节点</h3>
						</div>
						<div class="modal-body">
							<div class="alert alert-info">
								增加Hadoop集群的节点，主机名是该节点的hostname,IP是节点的ip地址，用户，
								密码和端口分别是hadoop用户名，密码和端口号，下面的节点角色可多选。</div>
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label" for="hostname">主机名</label>
									<div class="controls">
										<input type="text" id="hostname" placeholder="主机名">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="ip">IP地址</label>
									<div class="controls">
										<input type="text" id="ip" placeholder="IP地址">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="os">操作系统</label>
									<div class="controls">
										<input type="text" id="os" placeholder="操作系统">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="sshuser">节点SSH用户</label>
									<div class="controls">
										<input type="text" id="sshuser" placeholder="节点SSH用户">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="sshpass">节点SSH密码</label>
									<div class="controls">
										<input type="text" id="sshpass" placeholder="节点SSH密码">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="sshport">节点SSH端口</label>
									<div class="controls">
										<input type="text" id="sshport" placeholder="节点SSH端口">
									</div>
								</div>
								<div class="control-group modal_checkbox_group">
									<label>节点角色</<label> <label class="checkbox">
												<input type="checkbox" id="namenode"> Namenode
										</label> <label class="checkbox"> <input type="checkbox"
												id="datanode"> Datanode
										</label> <label class="checkbox"> <input type="checkbox"
												id="secondarynamenode"> SecondaryNamenode
										</label> <label class="checkbox"> <input type="checkbox"
												id="resourcemanager"> Resourcemanager
										</label> <label class="checkbox"> <input type="checkbox"
												id="nodemanager"> Nodemanager
										</label>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
							<button id="submitAddHadoopNode" class="btn btn-primary">添加</button>
						</div>
					</div>
				</div>
			</c:if>
			<div class="span10">
				<c:if test="${list.size() != 0}">
					<h3 align="center">Hadoop节点列表</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>编号</th>
								<th>主机名</th>
								<th>IP地址</th>
								<th>节点角色</th>
								<th>创建时间</th>
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
											&nbsp;&nbsp;namenode
										</c:if> <c:if test="${hadoopnode['datanode'] == true}">
											&nbsp;&nbsp;datanode
										</c:if> <c:if test="${hadoopnode['secondarynamenode'] == true}">
											&nbsp;&nbspsecondarynamenode
										</c:if> <c:if test="${hadoopnode['resourcemanager'] == true}">
											&nbsp;&nbsp;resourcemanager
										</c:if> <c:if test="${hadoopnode['nodemanager'] == true}">
											&nbsp;&nbsp;nodemanager
										</c:if></td>
									<td>${hadoopnode['ctime']}</td>
									<td>
										<div class="btn-group">
											<a class="btn" href="#update_hadoop_node_${hadoopnode['id']}"
												data-toggle="modal"><i class="icon-wrench"></i> 修改节点</a> <a
												class="btn btn-danger"
												href="#delete_hadoop_node_${hadoopnode['id']}"
												data-toggle="modal"><i class="icon-remove"></i> 删除节点</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:forEach items="${hadoopnodelist}" var="hadoopnode"
						varStatus="no">
						<div id="update_hadoop_node_${hadoopnode['id']}"
							class="modal hide fade" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h3 id="myModalLabel">修改节点</h3>
							</div>
							<div class="modal-body">
								<form class="form-horizontal">
									<div class="control-group">
										<label class="control-label" for="hostname_${hadoopnode['id']}">主机名</label>
										<div class="controls">
											<input type="text" id="hostname_${hadoopnode['id']}" placeholder="主机名"
												value="${hadoopnode['hostname']}">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="ip_${hadoopnode['id']}">IP地址</label>
										<div class="controls">
											<input type="text" id="ip_${hadoopnode['id']}" placeholder="IP地址"
												value="${hadoopnode['ip']}">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="os_${hadoopnode['id']}">操作系统</label>
										<div class="controls">
											<input type="text" id="os_${hadoopnode['id']}" placeholder="操作系统"
												value="${hadoopnode['os']}">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="sshuser_${hadoopnode['id']}">节点SSH用户</label>
										<div class="controls">
											<input type="text" id="sshuser_${hadoopnode['id']}" placeholder="节点SSH用户"
												value="${hadoopnode['sshuser']}">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="sshpass_${hadoopnode['id']}">节点SSH密码</label>
										<div class="controls">
											<input type="text" id="sshpass_${hadoopnode['id']}" placeholder="节点SSH密码"
												value="${hadoopnode['sshpass']}">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="sshport_${hadoopnode['id']}">节点SSH端口</label>
										<div class="controls">
											<input type="text" id="sshport_${hadoopnode['id']}" placeholder="节点SSH端口"
												value="${hadoopnode['sshport']}">
										</div>
									</div>
									<div class="control-group modal_checkbox_group">
										<label>节点角色<label> 
										<c:choose>
											<c:when test="${hadoopnode['namenode'] == true}">
												<label class="checkbox"> 
													<input type="checkbox" id="namenode_${hadoopnode['id']}" checked> Namenode
												</label>
											</c:when>
										<c:otherwise>
										<label class="checkbox"> 
											<input type="checkbox" id="namenode_${hadoopnode['id']}"> Namenode
										</label>
										</c:otherwise>
												</c:choose> <c:choose>
													<c:when test="${hadoopnode['datanode'] == true}">
														<label class="checkbox"> <input type="checkbox"
															id="datanode_${hadoopnode['id']}" checked> Datanode
														</label>
													</c:when>
													<c:otherwise>
														<label class="checkbox"> <input type="checkbox"
															id="datanode_${hadoopnode['id']}"> Datanode
														</label>
													</c:otherwise>
												</c:choose> <c:choose>
													<c:when test="${hadoopnode['secondarynamenode'] == true}">
														<label class="checkbox"> <input type="checkbox"
															id="secondarynamenode_${hadoopnode['id']}" checked> Secondarynamenode
														</label>
													</c:when>
													<c:otherwise>
														<label class="checkbox"> <input type="checkbox"
															id="secondarynamenode_${hadoopnode['id']}"> Secondarynamenode
														</label>
													</c:otherwise>
												</c:choose> <c:choose>
													<c:when test="${hadoopnode['resourcemanager'] == true}">
														<label class="checkbox"> <input type="checkbox"
															id="resourcemanager_${hadoopnode['id']}" checked> Resourcemanager
														</label>
													</c:when>
													<c:otherwise>
														<label class="checkbox"> <input type="checkbox"
															id="resourcemanager_${hadoopnode['id']}"> Resourcemanager
														</label>
													</c:otherwise>
												</c:choose> <c:choose>
													<c:when test="${hadoopnode['nodemanager'] == true}">
														<label class="checkbox"> <input type="checkbox"
															id="nodemanager_${hadoopnode['id']}" checked> Nodemanager
														</label>
													</c:when>
													<c:otherwise>
														<label class="checkbox"> <input type="checkbox"
															id="nodemanager_${hadoopnode['id']}"> Nodemanager
														</label>
													</c:otherwise>
												</c:choose>
									</div>
									<input type="hidden" id="curNode" value="${hadoopnode['id']}"/>
								</form>
							</div>
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
								<button update="${hadoopnode['id']}" class="btn btn-primary submitUpdateHadoopNode">保存</button>
							</div>
						</div>
					</c:forEach>
					
					
					<c:forEach items="${hadoopnodelist}" var="hadoopnode"
						varStatus="no">
						<div id="delete_hadoop_node_${hadoopnode['id']}"
							class="modal hide fade" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h3 id="myModalLabel">删除节点</h3>
							</div>
							<div class="modal-body">
								<p>主机名称：${hadoopnode['hostname']}</p>
								<p>主机IP：${hadoopnode['ip']}</p>
								<div class="alert alert-error">注意:删除节点前请确认关闭当前节点的Hadoop进程！！！</div>
							</div>
							<input type="hidden" id="curDelHadoopNode" value="${hadoopnode['id']}"/>
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
								<button del="${hadoopnode['id']}" class="btn btn-danger submitDeleteHadoopNode">删除</button>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>

		</div>
		</html>
	</c:otherwise>
</c:choose>



