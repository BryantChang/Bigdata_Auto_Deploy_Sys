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
%>



<jsp:include page="../nav.jsp" />
<div class="row-fluid">
	<div class="span2">
		<a id="modal-694172" href="#addCluster" role="button"
		   class="btn btn-primary btn-block" data-toggle="modal">添加集群</a>

		<div id="addCluster" class="modal hide fade" role="dialog"
			 aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				<h3 id="myModalLabel">添加集群</h3>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					增加集群，需要用户填写，集群的名称以及描述。
				</div>
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="clustername">集群名称</label>
						<div class="controls">
							<input type="text" id="clustername" placeholder="集群名称">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="desc"></label>
						<div class="controls">
							<input type="text" id="desc" placeholder="集群名称">
						</div>
					</div>

				</form>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				<button id="submitAddHadoopNode" class="btn btn-primary">添加</button>
			</div>
		</div>
	</div>
</div>

</div>
</html>




