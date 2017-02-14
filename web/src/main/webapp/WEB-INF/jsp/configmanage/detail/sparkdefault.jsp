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
    <jsp:include page="../../nav.jsp"/>
    <div class="row-fluid">
        <%--<div class="span2"></div>--%>
      <div class="span12">
        <h3 align="center">spark-defaults.conf配置项</h3>
        <a class="btn btn_primary" href="#add_spark_default_setting" data-toggle="modal">添加配置</a>
        <a class="btn btn_primary addFromCurrentFile" fileindex="${filename}" href="javascript:void(0); ">根据现有配置文件生成</a>
        <a class="btn btn_primary" href="<%=baseUrl%>/admin/configmanage/spark" data-toggle="modal">返回配置列表</a>
        <br/>
        <table class="table">
          <thead>
          <tr>
            <th>编号</th>
            <th>配置名称</th>
            <th>配置值</th>
            <th>配置描述</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:set value="0" var="counter"></c:set>
          <c:forEach items="${sparkdefaultsettinglist}" var="item" varStatus="no">
            <c:choose>
              <c:when test="${item['value'] == '$'}"></c:when>
              <c:otherwise>
                <c:set value="${counter+1}" var="counter"></c:set>
                <tr>
                  <td>${counter}</td>
                  <td>${item.name}</td>
                  <td>${item.value}</td>
                  <td>${item.description}</td>
                  <td>
                    <a class="btn" href="#update_spark_default_setting_${item['id']}"
                       data-toggle="modal"><i class="icon-wrench"></i> 修改配置</a>
                    <a class="btn btn-danger" href="#delete_default_setting_${item['id']}"
                       data-toggle="modal"><i class="icon-remove"></i> 删除配置</a>
                  </td>
                </tr>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          </tbody>
        </table>
        <div id="add_spark_default_setting"
             class="modal hide fade" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×
            </button>
            <h3 id="myModalLabel">添加配置</h3>
          </div>
          <div class="alert alert-error">注意:添加的参数名称不能重复,否则会导致添加失败。</div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="control-group">
                <label class="control-label" for="default_setting_name_add">配置名</label>
                <div class="controls">
                  <input type="text" id="default_setting_name_add" placeholder="配置名"
                         value="">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="default_setting_value_add">配置值</label>
                <div class="controls">
                  <input type="text" id="default_setting_value_add" placeholder="配置值"
                         value="">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="default_setting_description_add">配置描述</label>
                <div class="controls">
                  <input type="text" id="default_setting_description_add" placeholder="配置描述"
                         value="">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            <button class="btn btn-primary submitAddSparkDefaultSetting">保存
            </button>
          </div>
        </div>
        <c:forEach items="${sparkdefaultsettinglist}" var="setting" varStatus="no">
          <c:choose>
            <c:when test="${item['value'] == '$'}"></c:when>
            <c:otherwise>
              <div id="update_spark_default_setting_${setting['id']}"
                   class="modal hide fade" tabindex="-1" role="dialog"
                   aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal"
                          aria-hidden="true">×
                  </button>
                  <h3 id="myModalLabel">修改配置</h3>
                </div>
                <div class="modal-body">
                  <form class="form-horizontal">
                    <div class="control-group">
                      <label class="control-label" for="default_setting_name_${setting['id']}">配置名</label>
                      <div class="controls">
                        <input type="text" id="default_setting_name_${setting['id']}" placeholder="配置名"
                               value="${setting['name']}">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="default_setting_value_${setting['id']}">配置值</label>
                      <div class="controls">
                        <input type="text" id="default_setting_value_${setting['id']}" placeholder="配置值"
                               value="${setting['value']}">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="default_setting_description_${setting['id']}">配置描述</label>
                      <div class="controls">
                        <input type="text" id="default_setting_description_${setting['id']}" placeholder="配置描述"
                               value="${setting['description']}">
                      </div>
                    </div>
                    <input type="hidden" id="curNode" value="${setting['id']}"/>
                  </form>
                </div>
                <div class="modal-footer">
                  <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                  <button update="${setting['id']}" class="btn btn-primary submitUpdateSparkDefaultSetting">保存
                  </button>
                </div>
              </div>
            </c:otherwise>
          </c:choose>
        </c:forEach>
        <c:forEach items="${sparkdefaultsettinglist}" var="setting"
                   varStatus="no">
          <div id="delete_default_setting_${setting['id']}"
               class="modal hide fade" tabindex="-1" role="dialog"
               aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"
                      aria-hidden="true">×
              </button>
              <h3 id="myModalLabel">删除配置项</h3>
            </div>
            <div class="modal-body">
              <p>配置名称：${setting['name']}</p>

              <p>配置值：${setting['value']}</p>

              <div class="alert alert-error">注意:删除此配置项会对下一次任务的提交产生效果。</div>
            </div>
            <input type="hidden" id="curDelDefaultSetting" value="${setting['id']}"/>

            <div class="modal-footer">
              <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
              <button del="${setting['id']}" class="btn btn-danger submitDeleteSparkDefaultSetting">删除</button>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </c:otherwise>
</c:choose>
