<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		$("#myBody").html("");
		
		/* 获取到的表单值 */
		
		$.ajax({
			url:'settings/dept/list.do',
			data:{
				pageNo:pageNo
			},
			type:'post',
			//dataType:"json",
			success:function(data){
				//var pu = data.pu;
				
				//先更新全局变量
				pageNo = data.pu.pageNo;
				pageSize = data.pu.pageSize;
				pageCount = data.pu.pageCount;
				total = data.pu.total;
				
				//还得更新页面中显示的内容
				$("#myTotal").html(total);
				$("#myPageCount").html(pageCount);
				$("#myPageSize").html(pageSize);
				$("#myPageNo").html(pageNo);

				var htmlStr = "";
				$.each(data.dList, function(index, obj){
					//此时里面的每一个this都代表一个dept对象
					htmlStr += "<tr class='active'>";
					htmlStr += "<td><input type='checkbox' name='xz' value='"+obj.id+"'/></td>";
					htmlStr += "<td>"+obj.no+"</td>";
					htmlStr += "<td>"+obj.name+"</td>";
					htmlStr += "<td>"+obj.manager+"</td>";
					htmlStr += "<td>"+obj.phone+"</td>";
					htmlStr += "<td>"+obj.description+"</td>";
					htmlStr += "</tr>";
				});
				

				$("#myBody").html(htmlStr);
				btnCss();
			}
		});
		

	}
	
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
	} 
	$("#nextPage").click(function(){
		if (pageNo == pageSize) {
			return false;
		}else{
			pageNo++;
			pageList();
		}
	})
	
	$("#proPage").click(function(){
		if (pageNo == 1) {
			return false;
		}else{
			pageNo--;
			pageList();
		}
	})
	
	$("#firstPage").click(function(){
		if (pageNo == 1) {
			return false;
		}else{
			pageNo = 1;
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
	
	$("#createDeptBtn").click(function(){
		//每次点击创建按钮 要清空表单值
		$("#create-code").val("");
		$("#create-name").val("");
		$("#create-manager").val("");
		$("#create-phone").val("");
		$("#create-describe").val("");
		
		$("#createDeptModal").modal("show");
	})
	
	$("#saveBtn").click(function(){
		
		//获取表单值
		var no = $("#create-code").val();
		var name = $("#create-name").val();
		var manager = $("#create-manager").val();
		var phone = $("#create-phone").val();
		var description = $("#create-describe").val();
		$.post(
			"settings/dept/save.do",
			{"no":no,"name":name,"manager":manager,"phone":phone,"description":description},
			function(data){
				if (data == "success") {
					pageNo = 1;
					pageList();
					$("#createDeptModal").modal("hide");
				}else {
					slert("添加失败");
				}
			}
		)
	})
	
	
	$("#editDeptBtn").click(function(){
		
		var $xz = $("input[name='xz']:checked");
		
		if ($xz.length != 1) {
			alert("请选择一条需要编辑的记录");
			return;
		}else {
			var id = $xz.val();
			
			$.post(
				"settings/dept/edit.do",
				{"id":id},
				function(data){
					$("#edit-id").val(data.id);
					$("#edit-code").val(data.no);
					$("#edit-name").val(data.name);
					$("#edit-manager").val(data.manager);
					$("#edit-phone").val(data.phone);
					$("#edit-description").val(data.description);
					
					$("#editDeptModal").modal("show");
				},
				"json"
			)
		}
	})
	
	$("#updateBtn").click(function(){
		
		var id = $("#edit-id").val();
		var no = $("#edit-code").val();
		var name = $("#edit-name").val();
		var manager = $("#edit-manager").val();
		var phone = $("#edit-phone").val();
		var description = $("#edit-description").val();
		
		$.post(
			"settings/dept/update.do",
			{"id":id,"no":no,"name":name,"manager":manager,"phone":phone,"description":description},
			function(data){
				if (data == "success") {
					pageNo = 1;
					pageList();
					$("#editDeptModal").modal("hide");
				}else {
					alert("修改失败");
				}
			}
		)
	})
	
	
	
	$("#deleteDeptBtn").click(
						function() {

							var $xz = $("input[name='xz']:checked");
							if ($xz.length == 0) {
								alert("请选择需要删除的记录");
							} else {
								if (confirm("您确定要删除所选择的记录么")) {
									var rel = "";
									for (var i = 0; i < $xz.length; i++) {
										rel += "id=" + $($xz[i]).val();

										if (i < $xz.length - 1) {
											rel += "&";
										}

									}
									$.post("settings/dept/delete.do", rel,
											function(data) {
												if (data == "success") {
													pageNo = 1;
													pageList();
													alert("删除成功");
												} else {
													alert("删除失败");
												}
											})
								}
							}

						})

			})
</script>
<title>部门列表</title>
</head>
<body>

	<!-- 我的资料 -->
	<div class="modal fade" id="myInformation" role="dialog">
		<div class="modal-dialog" role="document" style="width: 30%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">我的资料</h4>
				</div>
				<div class="modal-body">
					<div style="position: relative; left: 40px;">
						姓名：<b>张三</b><br> <br> 登录帐号：<b>zhangsan</b><br> <br>
						组织机构：<b>1005，市场部，二级部门</b><br> <br> 邮箱：<b>zhangsan@bjpowernode.com</b><br>
						<br> 失效时间：<b>2017-02-14 10:10:10</b><br> <br>
						允许访问IP：<b>127.0.0.1,192.168.100.2</b>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改密码的模态窗口 -->
	<div class="modal fade" id="editPwdModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 70%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="oldPwd" class="col-sm-2 control-label">原密码</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="oldPwd"
									style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="newPwd" class="col-sm-2 control-label">新密码</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="newPwd"
									style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="confirmPwd" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="confirmPwd"
									style="width: 200%;">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="window.location.href='../login.html';">更新</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 退出系统的模态窗口 -->
	<div class="modal fade" id="exitModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 30%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">离开</h4>
				</div>
				<div class="modal-body">
					<p>您确定要退出系统吗？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="window.location.href='../../login.html';">确定</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 顶部 -->
	<div id="top"
		style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div
			style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
			CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;Administrator</span>
		</div>
		<div style="position: absolute; top: 15px; right: 15px;">
			<ul>
				<li class="dropdown user-dropdown"><a href="javascript:void(0)"
					style="text-decoration: none; color: white;"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user"></span> zhangsan <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="../../workbench/index.html"><span
								class="glyphicon glyphicon-home"></span> 工作台</a></li>
						<li><a href="../index.html"><span
								class="glyphicon glyphicon-wrench"></span> 系统设置</a></li>
						<li><a href="javascript:void(0)" data-toggle="modal"
							data-target="#myInformation"><span
								class="glyphicon glyphicon-file"></span> 我的资料</a></li>
						<li><a href="javascript:void(0)" data-toggle="modal"
							data-target="#editPwdModal"><span
								class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
						<li><a href="javascript:void(0);" data-toggle="modal"
							data-target="#exitModal"><span
								class="glyphicon glyphicon-off"></span> 退出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>

	<!-- 创建部门的模态窗口 -->
	<div class="modal fade" id="createDeptModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-plus"></span> 新增部门
					</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-code" class="col-sm-2 control-label">编号<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-code"
									name="code" style="width: 200%;"
									placeholder="编号为四位数字，不能为空，具有唯一性"> <input type="hidden"
									name="id">
							</div>
						</div>

						<div class="form-group">
							<label for="create-name" class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-name"
									name="name" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-manager" class="col-sm-2 control-label">负责人</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-manager"
									name="manager" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone"
									name="phone" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 55%;">
								<textarea class="form-control" rows="3" id="create-describe"
									name="description"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改部门的模态窗口 -->
	<div class="modal fade" id="editDeptModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-edit"></span> 编辑部门
					</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-code" class="col-sm-2 control-label">编号<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-code"
									name="code" style="width: 200%;"
									placeholder="编号为四位数字，不能为空，具有唯一性" value="${deptList.code }">
								<input type="hidden" id="edit-id" name="id"
									value="${deptList.id }">
							</div>
						</div>

						<div class="form-group">
							<label for="create-name" class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-name"
									name="name" style="width: 200%;" value="${deptList.name}">
							</div>
						</div>

						<div class="form-group">
							<label for="create-manager" class="col-sm-2 control-label">负责人</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-manager"
									name="manager" style="width: 200%;"
									value="${deptList.manager }">
							</div>
						</div>

						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone"
									name="phone" style="width: 200%;" value="${deptList.phone }">
							</div>
						</div>

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 55%;">
								<textarea class="form-control" rows="3" id="edit-description"
									name="description">${deptList.description }</textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
				</div>
			</div>
		</div>
	</div>

	<div style="width: 95%">
		<div>
			<div style="position: relative; left: 30px; top: -10px;">
				<div class="page-header">
					<h3>部门列表</h3>
				</div>
			</div>
		</div>
		<div class="btn-toolbar" role="toolbar"
			style="background-color: #F7F7F7; height: 50px; position: relative; left: 30px; top: -30px;">
			<div class="btn-group" style="position: relative; top: 18%;">
				<!-- 创建  insertBtn 事件 -->
				<button type="button" class="btn btn-primary" id="createDeptBtn">
					<span class="glyphicon glyphicon-plus"></span> 创建
				</button>

				<button type="button" class="btn btn-default" id="editDeptBtn">
					<span class="glyphicon glyphicon-edit"></span> 编辑
				</button>
				<button type="button" class="btn btn-danger" id="deleteDeptBtn">
					<span class="glyphicon glyphicon-minus"></span> 删除
				</button>
			</div>
		</div>
		<div style="position: relative; left: 30px; top: -10px;">
			<table class="table table-hover">
				<thead>
					<tr style="color: #B3B3B3;">
						<td><input type="checkbox" /></td>
						<td>编号</td>
						<td>名称</td>
						<td>负责人</td>
						<td>电话</td>
						<td>描述</td>
					</tr>
				</thead>
				<tbody id="myBody">
				</tbody>
			</table>
		</div>

		<div style="height: 50px; width: 500px; position: relative; top: 0px; left: 30px;">

			<div>
				<button type="button" class="btn btn-default"
					style="cursor: default;">
					共<b id="myTotal"></b>条记录
				</button>
			</div>
			<div class="btn-group"
				style="position: relative; top: -34px; left: 110px;">

				<button type="button" class="btn btn-default"
					style="cursor: default;">
					共<b id="myPageSize"></b>页
				</button>
				<button type="button" class="btn btn-default"
					style="cursor: default;">
					<b id="myPageCount"></b>条/页
				</button>
				<button type="button" class="btn btn-default"
					style="cursor: default;">
					当前是第<b id="myPageNo"></b>页
				</button>

			</div>

			<div style="position: relative; top: -88px; left: 385px;">

				<nav>
				<ul class="pagination">

					<li id="firstPage"><a>首页</a></li>
					<li id="proPage"><a>上一页</a></li>
					<li id="nextPage"><a>下一页</a></li>
					<li id="lastPage"><a>末页</a></li>

				</ul>
				</nav>
			</div>
		</div>
</body>
</html>