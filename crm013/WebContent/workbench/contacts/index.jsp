<%@page import="java.lang.annotation.Target"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println("/crm008/WebContent/workbench/clue/index.jsp");
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<!--  PAGINATION plugin -->
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/localization/en.js"></script>


<script type="text/javascript">

	$(function(){
		
		//定制字段
		$("#definedColumns > li").click(function(e) {
			//防止下拉菜单消失
	        e.stopPropagation();
	    });
		
	});
	
</script>

<script type="text/javascript">
$(function(){
	
	$("#createContactsBtn").click(function(){
		
		//清空表单
		$("#create-contactsOwner").val("");
		$("#create-clueSource").val("");
		$("#create-surname").val("");
		$("#create-appellation").val("");
		$("#create-job").val("");
		$("#create-mphone").val("");
		$("#create-email").val("");
		$("#create-birth").val("");
		$("#create-customerName").val("");
		$("#create-description").val("");
		$("#create-contactSummary").val("");
		$("#create-country").val("");
		$("#create-province").val("");
		$("#create-city").val("");
		$("#create-street").val("");
		$("#create-zipcode").val("");
		
		//获取所有者 信息 
		$.ajax({
			url:'settings/qx/user/GetUserOwner.do',
			type:'post',
			success:function(data){
				//设置所有者
				var htmlStr="";
				$.each(data,function(index,obj){
					if (obj.id == '${user.id}') {
						htmlStr += "<option value= '"+obj.id+"' selected>" + obj.name + "</option>";
					}else {
						htmlStr += "<option value= '"+obj.id+"'>" + obj.name + "</option>";
					}
				});
				$("#create-contactsOwner").html(htmlStr);
				//显示模态窗口
				$("#createContactsModal").modal("show");
			}
		});
	});
	
	
	$("#saveContactsBtn").click(function(){
		//获取参数
		var owner = $("#create-contactsOwner").val();
		var source = $("#create-clueSource").val();
		var fullName = $("#create-surname").val();
		var appellation = $("#create-appellation").val();
		var job = $("#create-job").val();
		var mphone = $("#create-mphone").val();
		var email = $("#create-email").val();
		var birth = $("#create-birth").val();
		var customerName = $("#create-customerName").val();
		var description = $("#create-description").val();
		var contactSummary = $("#create-contactSummary").val();
		var country = $("#create-country").val();
		var province = $("#create-province").val();
		var city = $("#create-city").val();
		var street = $("#create-street").val();
		var zipcode = $("#create-zipcode").val();
		
		//表单验证
		
		
		//发送请求
		$.ajax({
			url:"workbench/contacts/createContacts.do",
			data:{
				owner:owner,
				source:source,
				fullName:fullName,
				appellation:appellation,
				job:job,
				mphone:mphone,
				email:email,
				birth:birth,
				customerName:customerName,
				description:description,
				contactSummary:contactSummary,
				country:country,
				province:province,
				city:city,
				street:street,
				zipcode:zipcode
			},
			type:"post",
			success:function(data){
				if (data.success) {
					//创建成果 模态窗口关闭 刷新列表
					$("#createContactsModal").modal("hide");
					display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
					
				}else {
					alert("创建联系人失败");
					//模态窗口不关闭
					$("#createContactsModal").modal("show");
				}
			}
		})
		
	});
	
	//当页面加载成功之后，显示第一页数据
	display(1,5);
	//给"查询"按钮添加单击事件
	$("#queryContactsBtn").click(function(){
		display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
	});
	
	
	
	$("#editContactsBtn").click(function(){
		
		if($("#contactsTbody input[type='checkbox']:checked").size()!=1){
			alert("请正确选择要修改的市场活动！");
			return;
		}
		
		$.ajax({

			url:"workbench/contacts/editContacts.do",
			data:{
				id:$("#contactsTbody input[type='checkbox']:checked").val()
			},
			type:"post",

			success:function(data){

				//设置所有者
				if(data.success){
					//设置所有者
					var htmlStr="";
					$.each(data.userList,function(index,obj){
						if(obj.id==data.contacts.owner){
							htmlStr+="<option value='"+obj.id+"' selected>"+obj.name+"</option>";
						}else{
							htmlStr+="<option value='"+obj.id+"'>"+obj.name+"</option>";
						}
					});

					$("#edit-contactsOwner").html(htmlStr);

					$("#edit-contactsId").val(data.contacts.id);
					$("#edit-clueSource").val(data.contacts.source);
					$("#edit-surname").val(data.contacts.name);
					$("#edit-appellation").val(data.contacts.appellation);
					$("#edit-job").val(data.contacts.job);
					$("#edit-mphone").val(data.contacts.mphone);
					$("#edit-email").val(data.contacts.email);
					$("#edit-birth").val(data.contacts.borth);
					$("#edit-customerName").val(data.contacts.customerId);
					$("#edit-describe").val(data.contacts.description);
					$("#edit-contactSummary").val(data.contacts.contactSummary);
					$("#edit-country").val(data.contacts.country);
					$("#edit-province").val(data.contacts.province);
					$("#edit-city").val(data.contacts.city);
					$("#edit-street").val(data.contacts.street);
					$("#edit-zipcode").val(data.contacts.zipcode);
					
					//打开模态窗口
					$("#editContactsModal").modal("show");
			}else {
				alert("获取联系人失败");
				$("#editContactsModal").modal("hide");
			}
			
		}
		
	});
	

		
	});
		
	
})
/* 分页查询 列表显示 */
	function display(pageNo,pageSize){
		$.ajax({
			url:"workbench/contacts/listingContacts.do",
			data:{
				pageNo:pageNo,
				pageSize:pageSize,
				owner:$.trim($("#query-owner").val()),
				fullName:$.trim($("#query-fullName").val()),
				name:$.trim($("#query-name").val()),
				source:$("#query-source").val(),
				birth:$.trim($("#query-birth").val()),
			},
			type:"post",
			success:function(data){
				//设置联系人列表
				var htmlStr = "";
				
				$.each(data.dataList,function(index,obj){

					htmlStr+="<tr class='active'>";
					htmlStr+="<td><input type='checkbox' /></td>";
					htmlStr+="<td><a style='text-decoration: none; cursor: pointer;' onclick='window.location.href='detail.html';'>"+obj.fullName+"</a></td>";
					htmlStr+="<td>"+obj.appellation+"</td>";
					htmlStr+="<td>"+(obj.customerId==null ? '' : obj.customerId)+"</td>";
					htmlStr+="<td>"+obj.owner+"</td>";
					htmlStr+="<td>"+obj.source+"</td>";
					htmlStr+="<td>"+obj.email+"</td>";
					htmlStr+="<td>"+obj.birth+"</td>";
					htmlStr+="<td>"+obj.job+"</td>";
					htmlStr+="<td>"+obj.mphone+"</td>";
					htmlStr+="<td>"+obj.createBy+"</td>";
					htmlStr+="<td>"+obj.createTime+"</td>";
					htmlStr+="<td>"+obj.editBy+"</td>";
					htmlStr+="<td>"+obj.editTime+"</td>";
					htmlStr+="<td>"+obj.country + obj.province + obj.city + obj.street + obj.zipcode+"</td>";
					htmlStr+="<td>"+obj.description+"</td>";
					htmlStr+="<td>"+obj.contactSummary+"</td>";
					htmlStr+="</tr>";
					
				});
				
				$("#contactsTbody").html(htmlStr);
				
				$("#contactsTbody tr:even").addClass("active");
				//设置翻页 
				//计算总页数
				var totalPage = 1;
				if(data.totalCount % pageSize == 0){
					totalPage = data.totalCount / pageSize;
				}else {
					totalPage = parseInt(data.totalCount / pageSize) + 1;
				}
				
				$("#pageNoDiv").bs_pagination({
					currentPage: pageNo,//当前页号
					rowsPerPage: pageSize,//每页显示的条数
				    totalPages: totalPage,//总页数
				    totalRows: data.totalCount,//总记录条数
				    
				    visiblePageLinks: 5,//显示的卡片数
				    
				    showGoToPage: true,//是否显示"跳转到第几页"
				    showRowsPerPage: true,//是否显示"每页显示多少条"
				    showRowsInfo: true,//是否显示记录信息
				    
				    //当页号改变的时候，执行的回调函数。
				    onChangePage: function(event,obj) {
				    	display(obj.currentPage,obj.rowsPerPage);
				    }
				  });
			}
			
		});
	}
	/* 分页查询 列表显示 */
</script>


</head>
<body>


	<!-- 创建联系人的模态窗口 -->
	<div class="modal fade" id="createContactsModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"
						onclick="$('#createContactsModal').modal('hide');">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建联系人</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-contactsOwner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-contactsOwner">
								<!-- 	<option>zhangsan</option>
									<option>lisi</option>
									<option>wangwu</option> -->
								</select>
							</div>
							<label for="create-clueSource" class="col-sm-2 control-label">来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-clueSource">
									<option></option>
									<c:if test="${!empty sourceList }">
										<c:forEach var="sl" items="${sourceList }">
											<option value="${sl.id }">${sl.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="create-surname" class="col-sm-2 control-label">姓名<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-surname">
							</div>
							<label for="create-call" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-appellation">
									<option></option>
								<c:if test="${!empty appellationList }">
										<c:forEach var="at" items="${appellationList }">
											<option value="${at.id }">${at.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>

						</div>

						<div class="form-group">
							<label for="create-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-job">
							</div>
							<label for="create-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-mphone">
							</div>
						</div>

						<div class="form-group" style="position: relative;">
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-email">
							</div>
							<label for="create-birth" class="col-sm-2 control-label">生日</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-birth">
							</div>
						</div>

						<div class="form-group" style="position: relative;">
							<label for="create-customerName" class="col-sm-2 control-label">客户名称</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-customerName"
									placeholder="支持自动补全，输入客户不存在则新建">
							</div>
						</div>

						<div class="form-group" style="position: relative;">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div class="form-group" style="position: relative; top: 13px;">
							<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3"
									id="create-contactSummary"></textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top: 10px;"></div>

						<div style="position: relative; top: 20px;">
							<div class="form-group">
								<label for="create-country" class="col-sm-2 control-label">邮寄地址-国家/地区</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-country">
								</div>
								<label for="create-province" class="col-sm-2 control-label">邮寄地址-省/市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-province">
								</div>
							</div>

							<div class="form-group">
								<label for="create-city" class="col-sm-2 control-label">邮寄地址-城市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-city">
								</div>
								<label for="create-street" class="col-sm-2 control-label">邮寄地址-街道</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-street">
								</div>
							</div>

							<div class="form-group">
								<label for="create-zipcode" class="col-sm-2 control-label">邮寄地址-邮编</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-zipcode">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveContactsBtn" type="button" class="btn btn-primary" >保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改联系人的模态窗口 -->
	<div class="modal fade" id="editContactsModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改联系人</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="edit-contactsOwner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-contactsOwner">
									
								</select>
							</div>
							<label for="edit-clueSource" class="col-sm-2 control-label">来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-clueSource">
									<option></option>
									<c:if test="${!empty sourceList }">
										<c:forEach var="sl" items="${sourceList }">
											<option value="${sl.id }">${sl.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-surname" class="col-sm-2 control-label">姓名<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-surname"
									value="李四">
							</div>
							<label for="edit-call" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-appellation">
									<option></option>
								<c:if test="${!empty appellationList }">
										<c:forEach var="at" items="${appellationList }">
											<option value="${at.id }">${at.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-job"
									value="CTO">
							</div>
							<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-mphone"
									value="12345678901">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-email"
									value="lisi@bjpowernode.com">
							</div>
							<label for="edit-birth" class="col-sm-2 control-label">生日</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-birth">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-customerName" class="col-sm-2 control-label">客户名称</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-customerName"
									placeholder="支持自动补全，输入客户不存在则新建" value="Administrator">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">这是一条线索的描述信息</textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div class="form-group" style="position: relative; top: 13px;">
							<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-contactSummary"></textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top: 10px;"></div>

						<div style="position: relative; top: 20px;">
							<div class="form-group">
								<label for="create-country" class="col-sm-2 control-label">邮寄地址-国家/地区</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-country"
										value="中国">
								</div>
								<label for="create-province" class="col-sm-2 control-label">邮寄地址-省/市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-province"
										value="北京市">
								</div>
							</div>

							<div class="form-group">
								<label for="create-city" class="col-sm-2 control-label">邮寄地址-城市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-city"
										value="北京市">
								</div>
								<label for="create-street" class="col-sm-2 control-label">邮寄地址-街道</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-street"
										value="大兴区亦庄大族企业湾10号楼A座3层">
								</div>
							</div>

							<div class="form-group">
								<label for="create-zipcode" class="col-sm-2 control-label">邮寄地址-邮编</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-zipcode"
										value="100176">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateContactsBtn">更新</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 导入联系人的模态窗口 -->
	<div class="modal fade" id="importContactsModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">导入联系人</h4>
				</div>
				<div class="modal-body" style="height: 350px;">
					<div style="position: relative; top: 20px; left: 50px;">
						请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
					</div>
					<div style="position: relative; top: 40px; left: 50px;">
						<input type="file">
					</div>
					<div
						style="position: relative; width: 400px; height: 320px; left: 45%; top: -40px;">
						<h3>重要提示</h3>
						<ul>
							<li>给定文件的第一行将视为字段名。</li>
							<li>请确认您的文件大小不超过5MB。</li>
							<li>从XLS/XLSX文件中导入全部重复记录之前都会被忽略。</li>
							<li>复选框值应该是1或者0。</li>
							<li>日期值必须为MM/dd/yyyy格式。任何其它格式的日期都将被忽略。</li>
							<li>日期时间必须符合MM/dd/yyyy hh:mm:ss的格式，其它格式的日期时间将被忽略。</li>
							<li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
							<li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">导入</button>
				</div>
			</div>
		</div>
	</div>


	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>联系人列表</h3>
			</div>
		</div>
	</div>

	<div
		style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

		<div style="width: 200%; position: absolute; top: 5px; left: 10px;">

			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form"
					style="position: relative; top: 8%; left: 5px;">

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">所有者</div>
							<input id="query-owner" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">姓名</div>
							<input id="query-name" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">客户名称</div>
							<input id="query-fullName" class="form-control" type="text">
						</div>
					</div>


					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">来源</div>
							<select class="form-control" id="query-source">
								<option></option>
								<c:if test="${!empty sourceList }">
										<c:forEach var="sl" items="${sourceList }">
											<option value="${sl.id }">${sl.text }</option>
										</c:forEach>
									</c:if>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">生日</div>
							<input id="query-birth" class="form-control" type="text">
						</div>
					</div>
				</form><br>
					<button id="queryContactsBtn" type="submit" class="btn btn-default">查询</button>
			</div>
			<div class="btn-toolbar" role="toolbar"
				style="background-color: #F7F7F7; height: 50px; position: relative; top: 10px;">
				<div class="btn-group" style="position: relative; top: 18%;">
					<button type="button" class="btn btn-primary" id="createContactsBtn">
						<span class="glyphicon glyphicon-plus"></span> 创建
					</button>
					<button id="editContactsBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#editContactsModal">
						<span class="glyphicon glyphicon-pencil"></span> 修改
					</button>
					<button type="button" class="btn btn-danger">
						<span class="glyphicon glyphicon-minus"></span> 删除
					</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#importContactsModal">
						<span class="glyphicon glyphicon-import"></span> 导入
					</button>
					<button type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-export"></span> 导出
					</button>
				</div>

				<div class="btn-group"
					style="position: relative; top: 18%; left: 5px;">
					<button type="button" class="btn btn-default">添加字段</button>
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul id="definedColumns" class="dropdown-menu" role="menu">
						<li><a href="javascript:void(0);"><input type="checkbox" />
								姓名</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								称呼</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								客户名称</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								所有者</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								来源</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								邮箱</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								生日</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								职位</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								手机</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								创建者</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								创建时间</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								修改者</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								修改时间</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								地址</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								描述</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								联系纪要</a></li>
					</ul>
				</div>

				<div class="btn-group"
					style="position: relative; top: 18%; left: 8px;">
					<form class="form-inline" role="form">
						<div class="form-group has-feedback">
							<input type="text" class="form-control" style="width: 300px;"
								placeholder="支持任何字段搜索"> <span
								class="glyphicon glyphicon-search form-control-feedback"></span>
						</div>
					</form>
				</div>
			</div>
			<div style="position: relative; top: 20px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" /></td>
							<td>姓名</td>
							<td>称呼</td>
							<td>客户名称</td>
							<td>所有者</td>
							<td>来源</td>
							<td>邮箱</td>
							<td>生日</td>
							<td>职位</td>
							<td>手机</td>
							<td>创建者</td>
							<td>创建时间</td>
							<td>修改者</td>
							<td>修改时间</td>
							<td>地址</td>
							<td>描述</td>
							<td>联系纪要</td>
						</tr>
					</thead>
					<tbody id="contactsTbody">
						<!-- <tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;"
								onclick="window.location.href='detail.html';">李四</a></td>
							<td>先生</td>
							<td>Administrator</td>
							<td>zhangsan</td>
							<td>广告</td>
							<td>lisi@bjpowernode.com</td>
							<td></td>
							<td>CTO</td>
							<td>12345678901</td>
							<td>zhangsan</td>
							<td>2017-01-18 10:10:10</td>
							<td>zhangsan</td>
							<td>2017-01-19 10:10:10</td>
							<td>中国北京市大兴区大族企业湾10号楼A座3层</td>
							<td>这是一条线索的描述信息 （线索转换之后会将线索的描述转换到联系人的描述中）</td>
							<td></td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;"
								onclick="window.location.href='detail.html';">李四</a></td>
							<td>先生</td>
							<td>Administrator</td>
							<td>zhangsan</td>
							<td>广告</td>
							<td>lisi@bjpowernode.com</td>
							<td></td>
							<td>CTO</td>
							<td>12345678901</td>
							<td>zhangsan</td>
							<td>2017-01-18 10:10:10</td>
							<td>zhangsan</td>
							<td>2017-01-19 10:10:10</td>
							<td>中国北京市大兴区大族企业湾10号楼A座3层</td>
							<td>这是一条线索的描述信息 （线索转换之后会将线索的描述转换到联系人的描述中）</td>
							<td></td>
						</tr> -->
					</tbody>
				</table>
			</div>
			<div id="pageNoDiv"></div>
			<!-- <div style="height: 50px; position: relative; top: 10px;">
				<div>
					<button type="button" class="btn btn-default"
						style="cursor: default;">
						共<b>50</b>条记录
					</button>
				</div>
				<div class="btn-group"
					style="position: relative; top: -34px; left: 110px;">
					<button type="button" class="btn btn-default"
						style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							10 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default"
						style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative; top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div> -->

		</div>

	</div>
</body>
</html>