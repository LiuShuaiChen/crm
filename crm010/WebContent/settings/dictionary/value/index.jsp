<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%	
	    String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	    System.out.println("/crm002/WebContent/settings/dictionary/value/index.jsp");
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("#editBtn").click(function(){
			
			var $xz = $("input[name='xz']:checked");
			if ($xz.length != 1) {
				alert("请选择一条记录进行编辑");
			}else {
				var id = $xz.val();
				window.location.href="settings/dictionary/value/edit.do?id=" + id;
			}
		})
		
		
		$("#deleteBtn").click(function(){
			var $xz = $("input[name='xz']:checked");
			if ($xz.length == 0) {
				alert("请选择一条记录删除");
			}else {
				/* var id = $xz.val();
				window.location.href="settings/dictionary/value/delete.do?id="+id; */
				
				var rel = "settings/dictionary/value/delete.do?";
				for (var i = 0; i < $xz.length; i++) {
					rel += "id=" + $($xz[i]).val();
					
					if (i < $xz.length - 1) {
						rel += "&";
					}
				}
				window.location.href = rel;
			}
		})
	})

</script>

<title>Insert title here</title>
</head>
<body>
<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典值列表JSP</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='settings/dictionary/value/add.do'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>	
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" /></td>
					<td>序号</td>
					<td>字典值</td>
					<td>文本</td>
					<td>排序号</td>
					<td>字典类型编码</td>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty dictionaryValueList }">
				<tr>
					<td colspan="6" align="center">
					没有相关记录
				</tr>
			</c:if>
			
			<c:if test="${!empty dictionaryValueList }">
				<c:forEach items="${dictionaryValueList }" var="d" varStatus="vs">
				
					<tr class="active">
						<td><input type="checkbox" name="xz" value="${d.id }" /></td>
						<td>${vs.count }</td>
						<td>${d.value } </td>
						<td>${d.text } </td>
						<td>${d.orderNo } </td>
						<td>${d.code } </td>
					</tr>
				
				</c:forEach>
			
			</c:if>
			
			</tbody>
			
		
		</table>
	</div>
</body>
</html>