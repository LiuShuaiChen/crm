<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println("/crm004/WebContent/settings/qx/user/index.jsp");
    %>
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
	
	var pageNo = 1;
	var pageSize = 0;
	var pageCount = 0;
	var total = 0;
	pageList();
	
	
	
	
	function pageList(){
		var count = 0;
		var name = $("#myName").val();
		var deptId = $("#myDeptId").val();
		var lockStatus = $("#myLockStatus").val();
		var startTime = $("#myStartTime").val();
		var endTime = $("#myEndTime").val();
		
		$.post(
				"settings/qx/user/list.do",
				{"pageNo":pageNo,"name":name,"deptId":deptId,"lockStatus":lockStatus,"startTime":startTime,"endTime":endTime},
				function(data){
					$("#mytBody").html("");
					pageNo = data.pu.pageNo;
					pageCount = data.pu.pageCount;
					pageSize = data.pu.pageSize;
					total = data.pu.total;
					
					$("#myPageNo").html(pageNo);
					$("#myPageCount").html(pageCount);
					$("#myPageSize").html(pageSize);
					$("#myTotal").html(total);
					
					$(data.uList).each(function(){
						$("#mytBody").append("<tr class='active' ><td><input type='checkbox' name='xz' value='"+this.id+"' /></td><td>"+((++count)+(pageNo-1) * pageCount)+"</td><td><a  href='settings/qx/user/detail.jsp?id="+this.id+"'>"+this.loginAct+"</a></td><td>"+this.name+"</td><td>"+this.deptId+"</td><td>"+this.email+"</td><td>"+this.expireTime+"</td><td>"+this.allowIps+"</td><td><a href='javascript:void(0);' id='zt' aaa='"+this.id+"' style='text-decoration: none;'>"+this.lockStatus+"</a></td><td>"+this.createBy+"</td><td>"+this.createTime+"</td><td>"+this.editBy+"</td><td>"+this.editTime+"</td></tr>")
					})
					btnCss();
				},
				"json"
			)
		}
	
	$("#query").click(function(){
		pageList();
	})
	
	
	 function btnCss(){
		if (pageNo == 1) {
			$("#firstPage").addClass("disabled");
			$("#proPage").addClass("disabled");
			$("#nextPage").removeClass("disabled");
			$("#lastPage").removeClass("disabled");
		}else if (pageNo == pageSize) {
			$("#firstPage").removeClass("disabled");
			$("#proPage").removeClass("disabled");
			$("#nextPage").addClass("disabled");
			$("#lastPage").addClass("disabled");
		}else {
			$("#firstPage").removeClass("disabled");
			$("#proPage").removeClass("disabled");
			$("#nextPage").removeClass("disabled");
			$("#lastPage").removeClass("disabled");
		}
		
		if (pageSize == 1) {
			$("#firstPage").addClass("disabled");
			$("#proPage").addClass("disabled");
			$("#nextPage").addClass("disabled");
			$("#lastPage").addClass("disabled");
		}
	
	}
	
	$("#firstPage").click(function(){
		if (pageNo == 1) {
			return false;
		}else {
			pageNo = 1;
			pageList();
		}
	})
	
	$("#proPage").click(function(){
		if (pageNo == 1) {
			return false;
		}else {
			pageNo--;
			pageList();
		}
	})
	
	$("#nextPage").click(function(){
		if (pageNo == pageSize) {
			return false;
		}else{
			pageNo++;
			pageList();
		}
	})
	
	$("#lastPage").click(function(){
		if (pageNo == pageSize) {
			return false;
		}else {
			pageNo = pageSize;
			pageList();
		}
	}) 
	
	
	
	$("#createBtn").click(function(){
		
		//打开模态窗口前 清空表单
		$("#create-loginActNo").val("");
		$("#create-username").val("");
		$("#myLockStatus").val("0");
		$("#create-loginPwd").val("");
		$("#create-confirmPwd").val("");
		$("#create-email").val("");
		$("#create-org").val("");
		$("#create-allowIps").val("");
		$("#create-expireTime").val("");
		
		$("#createUserModal").modal("show"); 
		
	})
	
	
	$("#saveBtn").click(function(){
		var loginAct = $("#create-loginActNo").val();
		
		var name = $("#create-username").val();
		var loginPwd = $("#create-loginPwd").val();
		var email = $("#create-email").val();
		var expireTime = $("#create-expireTime").val();
		var lockStatus = $("#create-lockStatus").val();
		var deptId = $("#create-org").val();
		var allowIps = $("#create-allowIps").val();

		$.post(
			"settings/qx/user/save.do",
			{"loginAct":loginAct,"name":name,"loginPwd":loginPwd,"email":email,"expireTime":expireTime,"lockStatus":lockStatus,"deptId":deptId,"allowIps":allowIps},
			function(data){
				if (data == "success") {
					pageNo = 1;
					pageList();
					$("#createUserModal").modal("hidden");
				}else {
					alert("添加失败");
				}
			}

		)
	})
	
 	$("#deleteBtn").click(function(){
		var $xz = $("input[name='xz']:checked");
		if ($xz.length == 0) {
			alert("请选择记录删除");
		}else {
			var rel = "";
			for (var i = 0; i < $xz.length; i++) {
				rel += "id=" +$($xz[i]).val();
				if (i<$xz.length - 1) {
					rel += "&";
				}
			}
			
			$.post(
				"settings/qx/user/delete.do",
				rel,
				function(data){
					if (data == "success") {
						pageNo = 1;
						pageList();
						alert("删除成功");
					}else {
						alert("删除失败");
					}
				}
			)
		}
	}) 
	
 	$("#mytBody").on("click","#zt",function(){
		
		//attr取值
		var id = $(this).attr("aaa");
		
		$.post(
				"settings/qx/user/changeStatus.do",
				{"id":id},
				function(data){
					if (daya = "success") {
						pageList();
						
					}else {
						alert("更新状态失败");
					}
				}
				
				)
	}) 
	
})


</script>



<title>Insert title here</title>
</head>
<body>

	<!-- 创建用户的模态窗口 -->
	<div class="modal fade" id="createUserModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增用户</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-loginActNo" class="col-sm-2 control-label">登录帐号<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-loginActNo">
							</div>
							<label for="create-username" class="col-sm-2 control-label">用户姓名</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-username">
							</div>
						</div>
						<div class="form-group">
							<label for="create-loginPwd" class="col-sm-2 control-label">登录密码<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="password" class="form-control" id="create-loginPwd">
							</div>
							<label for="create-confirmPwd" class="col-sm-2 control-label">确认密码<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="password" class="form-control" id="create-confirmPwd">
							</div>
						</div>
						<div class="form-group">
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-email">
							</div>
							<label for="create-expireTime" class="col-sm-2 control-label">失效时间</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-expireTime">
							</div>
						</div>
						<div class="form-group">
							<label for="create-lockStatus" class="col-sm-2 control-label">锁定状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-lockStatus">
								  <option></option>
								  <option value="0" selected="selected">启用0</option>
								  <option value="1">锁定1</option>
								</select>
							</div>
							<label for="create-org" class="col-sm-2 control-label">部门<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-org" placeholder="输入部门名称，自动补全">
							</div>
						</div>
						<div class="form-group">
							<label for="create-allowIps" class="col-sm-2 control-label">允许访问的IP</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-allowIps" style="width: 280%" placeholder="多个用逗号隔开">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>用户列表</h3>
			</div>
		</div>
	</div>
	
	<div class="btn-toolbar" role="toolbar" style="position: relative; height: 80px; left: 30px; top: -10px;">
		<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
		  
		  <div class="form-group">
		    <div class="input-group">
		      <div class="input-group-addon">用户姓名</div>
		      <input class="form-control" type="text" id="myName">
		    </div>
		  </div>
		  &nbsp;&nbsp;&nbsp;&nbsp;
		  <div class="form-group">
		    <div class="input-group">
		      <div class="input-group-addon">部门名称</div>
		      <input class="form-control" type="text" id="myDeptId">
		    </div>
		  </div>
		  &nbsp;&nbsp;&nbsp;&nbsp;
		  <div class="form-group">
		    <div class="input-group">
		      <div class="input-group-addon">锁定状态</div>
			  <select class="form-control" id="myLockStatus">
			  	  <option></option>
			      <option>锁定</option>
				  <option>启用</option>
			  </select>
		    </div>
		  </div>
		  <br><br>
		  
		  <div class="form-group">
		    <div class="input-group">
		      <div class="input-group-addon">失效时间</div>
			  <input class="form-control" type="text" id="myStartTime"  />
		    </div>
		  </div>
		  
		  ~
		  
		  <div class="form-group">
		    <div class="input-group">
			  <input class="form-control" type="text" id="myEndTime" />
		    </div>
		  </div>
		  
		  
		  
		</form>
	</div>
	
	
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px; width: 110%; top: 20px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" id="createBtn" ><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button type="button" class="btn btn-danger" id="deleteBtn" ><span class="glyphicon glyphicon-minus"></span> 删除</button>
		  
		  <!-- 高级查询提交按钮 -->
		  <button type="button" class="btn btn-default" style="background-color: orange" id="query"><span class="glyphicon glyphicon-search" ></span> 查询</button>

		</div>
		<div class="btn-group" style="position: relative; top: 18%; left: 5px;">
			<!-- <button type="button" class="btn btn-default">设置显示字段</button>
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
				<span class="sr-only">Toggle Dropdown</span>
			</button> -->
			<ul id="definedColumns" class="dropdown-menu" role="menu"> 
				<li><a href="javascript:void(0);"><input type="checkbox"/> 登录帐号</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 用户姓名</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 部门名称</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 邮箱</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 失效时间</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 允许访问IP</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 锁定状态</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 创建者</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 创建时间</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 修改者</a></li>
				<li><a href="javascript:void(0);"><input type="checkbox"/> 修改时间</a></li>
			</ul>
		</div>
	</div>
	
	<div style="position: relative; left: 30px; top: 40px; width: 110%">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" /></td>
					<td>序号</td>
					<td>登录帐号</td>
					<td>用户姓名</td>
					<td>部门名称</td>
					<td>邮箱</td>
					<td>失效时间</td>
					<td>允许访问IP</td>
					<td>锁定状态</td>
					<td>创建者</td>
					<td>创建时间</td>
					<td>修改者</td>
					<td>修改时间</td>
				</tr>
			</thead>
			<tbody id="mytBody">
				
			</tbody>
		</table>

	</div>
	<div style="height: 50px; position: relative;top: 30px; left: 30px;">
		<div>
			<button type="button" class="btn btn-default" style="cursor: default;">共<b id="myTotal"></b>条记录</button>
		</div>
		<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
			<button type="button" class="btn btn-default" style="cursor: default;">共<b id="myPageSize"></b>页</button>
			
			<button type="button" class="btn btn-default" style="cursor: default;"><b id="myPageCount"></b>条/页</button>
			<button type="button" class="btn btn-default" style="cursor: default;">当前是第<b id="myPageNo"></b>页</button>
		</div>
		<div style="position: relative;top: -88px; left: 385px;">
			<nav>
				<ul class="pagination">
					<li id="firstPage"><a href="javascript:void(0)">首页</a></li>
					<li id="proPage"><a href="javascript:void(0)">上一页</a></li>
					<li id="nextPage"><a href="javascript:void(0)">下一页</a></li>
					<li id="lastPage"><a href="javascript:void(0)">末页</a></li>
				</ul>
			</nav>
		</div>
	</div>
			
</body>
</html>