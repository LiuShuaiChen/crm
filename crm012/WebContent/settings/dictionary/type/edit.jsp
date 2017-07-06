<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link
	href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
	type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript"
	src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">

$(function(){
	$("#update").click(function(){
		$("#myForm").submit();
	})
})


</script>
<title>Insert title here</title>
</head>
<body>

	<div style="position: relative; left: 30px;">
		<h3>修改字典类型</h3>
		<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="update">更新</button>
			<button type="button" class="btn btn-default"
				onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form class="form-horizontal" role="form" id="myForm"
		action="settings/dictionary/type/update.do" method="post">

		<div class="form-group">
			<label for="create-code" class="col-sm-2 control-label">编码<span
				style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="code" name="code"
					value="${dictionaryType.code }" style="width: 200%;"
					placeholder="编码作为主键，不能是中文">
			</div>
		</div>

		<div class="form-group">
			<label for="create-name" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="name" name="name"
					value="${dictionaryType.name }" style="width: 200%;">
			</div>
		</div>

		<div class="form-group">
			<label for="create-describe" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 300px;">
				<textarea class="form-control" rows="3" id="description"
					name="description" value="${dictionaryType.description }"
					style="width: 200%;">${dictionaryType.description }</textarea>
			</div>
		</div>
	</form>

	<div style="height: 200px;"></div>

</body>
</html>