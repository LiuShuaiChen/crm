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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link
	href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
	type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="jquery/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
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
	
	/* //*******页面加载成功之后,显示首页数据 ******* ********** *****************************/
	display(1,5);
	/* //*******页面加载成功之后,显示首页数据******* ********** **************************** */
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~时间插件 ~~~~~~~开始~~~~~~~~~~~~~~~~~~~~~~~~~~~  */  
	//时间插件
	$("#create-nextContactTime,#edit-nextContactTime").datetimepicker({
		  language: 'zh-CN',//显示中文
		  format: 'yyyy-mm-dd',//显示格式
		  minView: "month",//设置只显示到月份
		  initialDate: new Date(),//初始化当前日期
		  autoclose: true,//选中自动关闭
		  todayBtn: true,//显示今日按钮
		  clearBtn:true//显示清空按钮
	})
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~时间插件 ~~~~~~~~~~~结束~~~~~~~~~~~~~~~~~~~~~~~~~~~  */  
	
	
	
	/* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 */
	$("#createClueBtn").click(function(){
		
		//模态窗口开启之前  需要清空表单
		$("#create-owner").val("");
		$("#create-company").val("");
		$("#create-appellation").val("");
		$("#create-fullName").val("");
		$("#create-job").val("");
		$("#create-email").val("");
		$("#create-phone").val("");
		$("#create-website").val("");
		$("#create-mphone").val("");
		$("#create-state").val("");
		$("#create-source").val("");
		$("#create-empNums").val("");
		$("#create-industry").val("");
		$("#create-grade").val("");
		$("#create-annualIncome").val("");
		$("#create-description").val("");
		$("#create-contactSummary").val("");
		$("#create-nextContactTime").val("");
		$("#create-country").val("");
		$("#create-province").val("");
		$("#create-city").val("");
		$("#create-street").val("");
		$("#create-zipcode").val("");
		
		$.ajax({
			url:'workbench/clue/queryUser.do',
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
				$("#create-owner").html(htmlStr);
				//显示模态窗口
				$("#createClueModal").modal("show");
			}
		});
		
	});
	/* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 *//* 创建线索 */
	
	
	
	/* 保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 */
	$("#saveNewClueBtn").click(function() {
		var owner = $("#create-owner").val();
		var company = $("#create-company").val();
		var phone = $("#create-phone").val();
		var fullName = $("#create-fullName").val();
		var job = $("#create-job").val();
		var email = $("#create-email").val();
		var appellation = $("#create-appellation").val();
		var website = $("#create-website").val();
		var mphone = $("#create-mphone").val();
		var state = $("#create-state").val();
		var source = $("#create-source").val();
		var empNums = $("#create-empNums").val();
		var industry = $("#create-industry").val();
		var grade = $("#create-grade").val();
		var annualIncome = $("#create-annualIncome").val();
		var description = $("#create-description").val();
		var contactSummary = $("#create-contactSummary").val();
		var nextContactTime = $("#create-nextContactTime").val();
		var country = $("#create-country").val();
		var province = $("#create-province").val();
		var city = $("#create-city").val();
		var street = $("#create-street").val();
		var zipcode = $("#create-zipcode").val();
		
		//表单验证 ** 名称不能为空**
		if (fullName == null || fullName.length == 0) {
			alert("名称不能为空");
			return;
		}
		//表单验证 ** 公司名称不能为空**
		if (company == null || company.length == 0) {
			alert("名称不能为空");
			return;
		}
		
		$.ajax({
			url:'worbench/clue/saveClue.do',
			type:'post',
			data:{
				owner:owner,
				company:company,
				phone:phone,
				fullName:fullName,
				job:job,
				email:email,
				appellation:appellation,
				website:website,
				mphone:mphone,
				state:state,
				source:source,
				empNums:empNums,
				industry:industry,
				grade:grade,
				annualIncome:annualIncome,
				description:description,
				contactSummary:contactSummary,
				nextContactTime:nextContactTime,
				country:country,
				province:province,
				city:city,
				street:street,
				zipcode:zipcode
			},
			success:function(data){
				if(data.success){
					//关闭模态窗口
					$("#createClueModal").modal("hide");
					//刷新列表
					display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
				}else {
					alert("添加失败");
					//模态窗口不关闭
					$("#createClueModal").modal("show");
				}
			}
			
		});
					
	});
	/* 保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 *保存新创建的线索 */
	
	
	/* 获取id 拿到线索 放到修改模态窗口中 *//* 获取id 拿到线索 放到修改模态窗口中 *//* 获取id 拿到线索 放到修改模态窗口中 */
	$("#editClueBtn").click(function(){
		
		if($("#clueListBody input[type='checkbox']:checked").size()!=1){
			alert("请选择要修改的线索");
			return;
		}
		
		$.ajax({
			url:"workbench/clue/edit.do",
			data:{
				id:$("#clueListBody input[type='checkbox']:checked").val()
			},
			type:"post",
			success:function(data){
				if (data.success) {
					//设置所有者
					var htmlStr = "";
					$.each(data.userList,function(index,obj){
						if(obj.id==data.clue.owner){
							htmlStr+="<option value='"+obj.id+"' selected>"+obj.name+"</option>";
						}else{
							htmlStr+="<option value='"+obj.id+"'>"+obj.name+"</option>";
						}
					});
					
					$("#edit-owner").html(htmlStr);

					$("#edit-clueId").val(data.clue.id);
					$("#edit-company").val(data.clue.company);
					$("#edit-appellation").val(data.clue.appellation);
					$("#edit-fullName").val(data.clue.fullName);
					$("#edit-job").val(data.clue.job);
					$("#edit-email").val(data.clue.email);
					$("#edit-phone").val(data.clue.phone);
					$("#edit-website").val(data.clue.website);
					$("#edit-mphone").val(data.clue.mphone);
					$("#edit-state").val(data.clue.state);
					$("#edit-source").val(data.clue.source);
					$("#edit-empNums").val(data.clue.empNums);
					$("#edit-industry").val(data.clue.industry);
					$("#edit-grade").val(data.clue.grade);
					$("#edit-annualIncome").val(data.clue.annualIncome);
					$("#edit-description").val(data.clue.description);
					$("#edit-contactSummary").val(data.clue.contactSummary);
					$("#edit-nextContactTime").val(data.clue.nextContactTime);
					$("#edit-province").val(data.clue.province);
					$("#edit-country").val(data.clue.country)
					$("#edit-city").val(data.clue.city);
					$("#edit-street").val(data.clue.street);
					$("#edit-zipcode").val(data.clue.zipcode);
					
					//显示模态窗口
					$("#editClueModal").modal("show");
					
				}else {
					alert("获取线索失败");
					$("#editClueModal").modal("hide");
				}
			}
		});
		
	});
	/* 获取id 拿到线索 放到修改模态窗口中 *//* 获取id 拿到线索 放到修改模态窗口中 *//* 获取id 拿到线索 放到修改模态窗口中 */
	
	/* 修改线索 完成update *//* 修改线索 完成update *//* 修改线索 完成update *//* 修改线索 完成update *//* 修改线索 完成update */
	$("#updateClueBtn").click(function(){
		//获取表单
		var id = $("#edit-clueId").val();
		var owner = $("#edit-owner").val();
		var company = $("#edit-company").val();
		var appellation = $("#edit-appellation").val();
		var fullName = $("#edit-fullName").val();
		var job = $("#edit-job").val();
		var email = $("#edit-email").val();
		var phone = $("#edit-phone").val();
		var website = $("#edit-website").val();
		var mphone = $("#edit-mphone").val();
		var state = $("#edit-state").val();
		var source = $("#edit-source").val();
		var empNums = $("#edit-empNums").val();
		var industry = $("#edit-industry").val();
		var grade = $("#edit-grade").val();
		var annualIncome = $("#edit-annualIncome").val();
		var description = $("#edit-description").val();
		var contactSummary = $("#edit-contactSummary").val();
		var nextContactTime = $("#edit-nextContactTime").val();
		var country = $("#edit-country").val()
		var province = $("#edit-province").val();
		var city= $("#edit-city").val();
		var street= $("#edit-street").val();
		var zipcode = $("#edit-zipcode").val();
		
		//表单验证
		
		//发送请求
		$.ajax({
			url:"workbench/clue/update.do",
			data:{
				id:id,
				owner:owner,
				company:company,
				appellation:appellation,
				fullName:fullName,
				job:job,
				email:email,
				phone:phone,
				mphone:mphone,
				website:website,
				state:state,
				source:source,
				empNums:empNums,
				industry:industry,
				grade:grade,
				annualIncome:annualIncome,
				description:description,
				contactSummary:contactSummary,
				nextContactTime:nextContactTime,
				country:country,
				province:province,
				city:city,
				street:street,
				zipcode:zipcode
			},
			type:"post",
			success:function(data){
				if (data.success) {
					$("#editClueModal").modal("hide");
					display($("#pageNoDiv").bs_pagination('getOption', 'currentPage'),$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
				}else {
					alert("更新线索失败");
					$("#editClueModal").modal("show");
				}
			}
		});
		
	})
	/* 修改线索 完成update *//* 修改线索 完成update *//* 修改线索 完成update *//* 修改线索 完成update *//* 修改线索 完成update */
	
});

/* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 */
function display(pageNo, pageSize){
	$.ajax({
		url:"workbench/clue/list.do",
		data:{
			pageNo:pageNo,
			pageSize:pageSize,
			name:$.trim($("#query-name").val()),
			company:$.trim($("#query-company").val()),
			phone:$.trim($("#query-phone").val()),
			source:$.trim($("#query-source").val()),
			owner:$.trim($("#query-owner").val()),
			mphone:$.trim($("#query-mphone").val()),
			state:$.trim($("#query-state").val()),
			industry:$.trim($("#query-industry").val()),
			grade:$.trim($("#query-grade").val())
		},
		type:"post",
		success:function(data){
			var htmlStr = "";
			$.each(data.dataList,function(index,obj){                                                                                          
				htmlStr += "<tr>";
				htmlStr += "<td><input value='"+obj.id+"' type='checkbox' /></td>";
				htmlStr += "<td><a style='text-decoration: none; cursor: pointer;' onclick='window.location.href=\"workbench/clue/detail.do?id="+obj.id+"\";'>"+obj.fullName+" "+obj.appellation+"</a></t>";
				htmlStr += "<td>"+obj.company+"</td>";
				htmlStr += "<td>"+obj.phone+"</td>";
				htmlStr += "<td>"+obj.mphone+"</td>";
				htmlStr += "<td>"+obj.email+"</td>";
				htmlStr += "<td>"+obj.source+"</td>";
				htmlStr += "<td>"+obj.owner+"</td>";
				htmlStr += "<td>"+obj.job+"</td>";
				htmlStr += "<td>"+obj.website+"</td>";
				htmlStr += "<td>"+obj.state+"</td>";
				htmlStr += "<td>"+obj.industry+"</td>";
				htmlStr += "<td>"+obj.empNums+"</td>";
				htmlStr += "<td>"+obj.annualIncome+"</td>";
				htmlStr += "<td>"+obj.grade+"</td>";
				htmlStr += "<td>"+obj.createBy+"</td>";
				htmlStr += "<td>"+obj.createTime+"</td>";
				htmlStr += "<td>"+obj.editBy+"</td>";
				htmlStr += "<td>"+obj.editTime+"</td>";
				htmlStr += "<td>"+obj.country+obj.province+obj.city+obj.street+"</td>";
				htmlStr += "<td>"+obj.description+"</td>";
				htmlStr += "<td>"+obj.contactSummary+"</td>";
				htmlStr += "<td>"+obj.nextContactTime+"</td>";
				htmlStr += "</tr>";
			});
			$("#clueListBody").html(htmlStr);
			
			//隔行换颜色
			$("#clueListBody tr even").addClass("active");
			
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
/* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 *//* 线索列表显示 */

</script>


<title>Insert title here</title>
</head>
<body>

	<!-- 创建线索的模态窗口 -->
	<div class="modal fade" id="createClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建线索</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner"></select>
							</div>
							<label for="create-company" class="col-sm-2 control-label">公司<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-company">
							</div>
						</div>

						<div class="form-group">
							<label for="create-appellation" class="col-sm-2 control-label">称呼</label>
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
							<label for="create-fullName" class="col-sm-2 control-label">姓名<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-fullName">
							</div>
						</div>

						<div class="form-group">
							<label for="create-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-job">
							</div>
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-email">
							</div>
						</div>

						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone">
							</div>
							<label for="create-website" class="col-sm-2 control-label">网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-website">
							</div>
						</div>

						<div class="form-group">
							<label for="create-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-mphone">
							</div>
							<label for="create-state" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-state">
									<option></option>
									<c:if test="${!empty clueStateList }">
										<c:forEach var="cs" items="${clueStateList }">
											<option value="${cs.id }">${cs.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="create-source" class="col-sm-2 control-label">来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-source">
									<option></option>
									<c:if test="${!empty sourceList }">
										<c:forEach var="sl" items="${sourceList }">
											<option value="${sl.id }">${sl.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<label for="create-empNums" class="col-sm-2 control-label">员工数</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-empNums">
							</div>
						</div>

						<div class="form-group">
							<label for="create-industry" class="col-sm-2 control-label">行业</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-industry">
									<option></option>
									<c:if test="${!empty industryList }">
										<c:forEach var="indu" items="${industryList }">
											<option value="${indu.id }">${indu.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<label for="create-grade" class="col-sm-2 control-label">等级</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-grade">
									<option></option>
									<c:if test="${!empty industryList }">
										<c:forEach var="grade" items="${gradeList }">
											<option value="${grade.id }">${grade.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="create-annualIncome" class="col-sm-2 control-label">年收入</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-annualIncome">
							</div>
						</div>
						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div style="position: relative; top: 15px;">
							<div class="form-group">
								<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="create-contactSummary"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-nextContactTime" readonly>
								</div>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top: 10px;"></div>

						<div style="position: relative; top: 20px;">
							<div class="form-group">
								<label for="create-country" class="col-sm-2 control-label">国家/地区</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-country">
								</div>
								<label for="create-province" class="col-sm-2 control-label">省/市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-province">
								</div>
							</div>

							<div class="form-group">
								<label for="create-city" class="col-sm-2 control-label">城市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-city">
								</div>
								<label for="create-street" class="col-sm-2 control-label">街道</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-street">
								</div>
							</div>

							<div class="form-group">
								<label for="create-zipcode" class="col-sm-2 control-label">邮编</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-zipcode">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveNewClueBtn" type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改线索的模态窗口 -->
	<div class="modal fade" id="editClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改线索</h4>
					<input type="text" id="edit-clueId">
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="edit-owner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">
									<option>zhangsan</option>
									<option>lisi</option>
									<option>wangwu</option>
								</select>
							</div>
							<label for="edit-company" class="col-sm-2 control-label">公司<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-company"
									value="动力节点">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-appellation" class="col-sm-2 control-label">称呼</label>
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
							<label for="edit-fullName" class="col-sm-2 control-label">姓名<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-fullName"
									value="李四">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-job"
									value="CTO">
							</div>
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-email"
									value="lisi@bjpowernode.com">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone"
									value="010-84846003">
							</div>
							<label for="edit-website" class="col-sm-2 control-label">网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-website"
									value="http://www.bjpowernode.com">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-mphone"
									value="12345678901">
							</div>
							<label for="edit-state" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-state">
									<option></option>
									<c:if test="${!empty clueStateList }">
										<c:forEach var="cs" items="${clueStateList }">
											<option value="${cs.id }">${cs.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-source" class="col-sm-2 control-label">来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-source">
									<option></option>
									<c:if test="${!empty sourceList }">
										<c:forEach var="source" items="${sourceList }">
											<option value="${source.id }">${source.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<label for="edit-empNums" class="col-sm-2 control-label">员工数</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-empNums"
									value="100">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-industry" class="col-sm-2 control-label">行业</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-industry">
									<option></option>
									<c:if test="${!empty industryList }">
										<c:forEach var="indu" items="${industryList }">
											<option value="${indu.id }">${indu.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<label for="edit-grade" class="col-sm-2 control-label">等级</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-grade">
									<option></option>
									<c:if test="${!empty industryList }">
										<c:forEach var="grade" items="${gradeList }">
											<option value="${grade.id }">${grade.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-annualIncome" class="col-sm-2 control-label">年收入</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-annualIncome"
									value="10,000,000">
							</div>
						</div>
						<div class="form-group">
							<label for="edit-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">这是一条线索的描述信息</textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div style="position: relative; top: 15px;">
							<div class="form-group">
								<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3"
										id="edit-contactSummary">这个线索即将被转换</textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control"
										id="edit-nextContactTime" readonly value="2017-05-01">
								</div>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top: 10px;"></div>

						<div style="position: relative; top: 20px;">
							<div class="form-group">
								<label for="edit-country" class="col-sm-2 control-label">国家/地区</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-country"
										value="中国">
								</div>
								<label for="edit-province" class="col-sm-2 control-label">省/市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-province"
										value="北京市">
								</div>
							</div>

							<div class="form-group">
								<label for="edit-city" class="col-sm-2 control-label">城市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-city"
										value="北京市">
								</div>
								<label for="edit-street" class="col-sm-2 control-label">街道</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-street"
										value="亦庄大族企业湾10号楼A座3层">
								</div>
							</div>

							<div class="form-group">
								<label for="edit-zipcode" class="col-sm-2 control-label">邮编</label>
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
					<button id="updateClueBtn" type="button" class="btn btn-primary" >更新</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 导入线索的模态窗口 -->
	<div class="modal fade" id="importClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">导入线索</h4>
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
				<h3>线索列表</h3>
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
							<div class="input-group-addon">名称</div>
							<input class="form-control" type="text" id="query-name">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">公司</div>
							<input class="form-control" type="text" id="query-company">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">电话</div>
							<input class="form-control" type="text" id="query-phone">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">来源</div>
							<select class="form-control" id="query-source">
								<option></option>
								<c:if test="${!empty sourceList }">
									<c:forEach var="source" items="${sourceList }">
										<option value="${source.id }">${source.text }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>

					<br>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">所有者</div>
							<input class="form-control" type="text" id="query-owner">
						</div>
					</div>



					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">手机</div>
							<input class="form-control" type="text" id="query-mphone">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="query-state">
								<option></option>
								<c:if test="${!empty clueStateList }">
									<c:forEach var="cs" items="${clueStateList }">
										<option value="${cs.id }">${cs.text }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">行业</div>
							<select class="form-control" id="query-industry">
								<option></option>
								<c:if test="${!empty industryList }">
									<c:forEach var="indu" items="${industryList }">
										<option value="${indu.id }">${indu.text }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">等级</div>
							<select class="form-control" id="query-grade">
								<option></option>
								<c:if test="${!empty industryList }">
									<c:forEach var="grade" items="${gradeList }">
										<option value="${grade.id }">${grade.text }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
				</form>
				<br>
					<button id="queryConditionBtn" type="submit" class="btn btn-default">查询</button>
			</div>
			
			
			<div class="btn-toolbar" role="toolbar"
				style="background-color: #F7F7F7; height: 50px; position: relative; top: 50px;">
				<div class="btn-group" style="position: relative; top: 18%;">
					<button id="createClueBtn" type="button" class="btn btn-primary">
						<span class="glyphicon glyphicon-plus"></span> 创建
					</button>
					<button id="editClueBtn" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-pencil"></span> 修改
					</button>
					<button type="button" class="btn btn-danger">
						<span class="glyphicon glyphicon-minus"></span> 删除
					</button>
					
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#importClueModal">
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
								名称</a></li>
						<!-- 线索名称=姓名+称呼 -->
						<li><a href="javascript:void(0);"><input type="checkbox" />
								公司</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								电话</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								手机</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								邮箱</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								来源</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								所有者</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								职位</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								网站</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								状态</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								行业</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								员工数</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								年收入</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								等级</a></li>
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
						<li><a href="javascript:void(0);"><input type="checkbox" />
								下次联系时间</a></li>
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
			<div style="position: relative; top: 50px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" /></td>
							<td>名称</td>
							<td>公司</td>
							<td>电话</td>
							<td>手机</td>
							<td>邮箱</td>
							<td>来源</td>
							<td>所有者</td>
							<td>职位</td>
							<td>网站</td>
							<td>状态</td>
							<td>行业</td>
							<td>员工数</td>
							<td>年收入</td>
							<td>等级</td>
							<td>创建者</td>
							<td>创建时间</td>
							<td>修改者</td>
							<td>修改时间</td>
							<td width="10%">地址</td>
							<td width="10%">描述</td>
							<td>联系纪要</td>
							<td>下次联系时间</td>
						</tr>
					</thead>
					<tbody id="clueListBody"> </tbody>
				</table>
				<div id="pageNoDiv"></div> 
			</div>

		</div>

	</div>
</body>
</html>