<%@page import="java.lang.annotation.Target"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println("/crm012/WebContent/workbench/clue/detail.jsp");
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/localization/en.js"></script>
<script type="text/javascript" src="jquery/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.css"></script>
<script type="text/javascript" src="jquery/datetimepicker/css/bootstrap-datetimepicker.css"></script>

<script type="text/javascript">
	
	$(function(){
		$('#edit-nextContactTime').datetimepicker({
			  language: 'zh-CN',//显示中文
			  format: 'yyyy-mm-dd',//显示格式
			  minView: "month",//设置只显示到月份
			  initialDate: new Date(),//初始化当前日期
			  autoclose: true,//选中自动关闭
			  todayBtn: true,//显示今日按钮
			  clearBtn:true//显示清空按钮
		});
	});

	//默认情况下取消和保存按钮是隐藏的
	var cancelAndSaveBtnDefault = true;
	
	$(function(){
		$("#remark").focus(function(){
			if(cancelAndSaveBtnDefault){
				//设置remarkDiv的高度为130px
				$("#remarkDiv").css("height","130px");
				//显示
				$("#cancelAndSaveBtn").show("2000");
				cancelAndSaveBtnDefault = false;
			}
		});
		
		$("#cancelBtn").click(function(){
			//显示
			$("#cancelAndSaveBtn").hide();
			//设置remarkDiv的高度为130px
			$("#remarkDiv").css("height","90px");
			cancelAndSaveBtnDefault = true;
		});
		
		$(".remarkDiv").mouseover(function(){
			$(this).children("div").children("div").show();
		});
		
		$(".remarkDiv").mouseout(function(){
			$(this).children("div").children("div").hide();
		});
		
		$(".myHref").mouseover(function(){
			$(this).children("span").css("color","red");
		});
		
		$(".myHref").mouseout(function(){
			$(this).children("span").css("color","#E6E6E6");
		});
		
	});
	
	

	
</script>
<script type="text/javascript">


	/* 获取id 编辑 线索 *//* 获取id 编辑 线索 *//* 获取id 编辑 线索 *//* 获取id 编辑 线索 *//* 获取id 编辑 线索 */
	$(function(){
		

		$("#editClueDateilBtn").click(function(){
			$.ajax({
				url:"workbench/clue/edit.do",
				data:{
					id:$("#edit-clueId").val()
				},
				type:"post",
				success:function(data){
					if (data.success) {
						//设置所有者
						var htmlStr = "";
						$.each(data.userList,function(index,obj){
							if(obj.id == data.clue.owner){
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
			/* 获取id 编辑 线索 *//* 获取id 编辑 线索 *//* 获取id 编辑 线索 *//* 获取id 编辑 线索 *//* 获取id 编辑 线索 *//* 获取id 编辑 线索 */
			
			
			/* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 */
			$("#updateClueDetailBtn").click(function(){
				
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
				var country = $("#edit-country").val();
				var province = $("#edit-province").val();
				var city= $("#edit-city").val();
				var street= $("#edit-street").val();
				var zipcode = $("#edit-zipcode").val();
				
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
							reflushDetailClue();
							$("#editClueModal").modal("hide");
						}else {
							alert("更新线索失败");
							$("#editClueModal").modal("show");
						}
					}
				});
				
			});
			
			/* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 *//* 更新编辑 线索 */
			
			
			/*添加备注 单击 保存 按钮事件  */
			$("#saveClueRemarkBtn").click(function(){
				
			});
			
			
			
		})
		
		/* 局部刷新 */ /* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 */
		function reflushDetailClue(){
			$.ajax({
				url:"workbench/clue/detail/reflushClueDetail.do",
				data:{
					id:"${param.id}"
				},
				type:"post",
				success:function(data){
						// 修改后详细信息
						if (data.success) {
							
							$("#clueTitle").html(data.clue.fullName + data.clue.appellation +"<small> " + data.clue.company + "</small>" )
							$("#clueFullNameAndAppellation").html(data.clue.fullName + data.clue.appellation)
							$("#clueOwner").html(data.clue.owner);
							$("#clueCompany").html(data.clue.company);
							$("#clueJob").html(data.clue.job);
							$("#clueEmail").html(data.clue.email);
							$("#cluePhone").html(data.clue.phone);
							$("#clueWebsite").html(data.clue.website);
							$("#clueMphone").html(data.clue.mphone);
							$("#clueState").html(data.clue.state);
							$("#clueSource").html(data.clue.source);
							$("#clueEmpnums").html(data.clue.empNums);
							$("#clueIndustry").html(data.clue.industry);
							$("#clueGrade").html(data.clue.grade);
							$("#clueAnnuaincome").html(data.clue.annualIncome);
							$("#clueEditBy").html(data.clue.editBy + "&nbsp;&nbsp;");
							$("#clueEditTime").html(data.clue.editTime);
							$("#clueDescription").html(data.clue.description);
							$("#clueContactSummary").html(data.clue.contactSummary);
							$("#clueNextContactTime").html(data.clue.nextContactTime);
							$("#clueCountry").html(data.clue.country);
							$("#clueProvince").html(data.clue.province);
							$("#clueStreet").html(data.clue.street);
							$("#clueZipcode").html(data.clue.zipcode);
						}else {
							alert("详细页面刷新失败");
						}
				}
			});
		}
		/* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 */
	})
</script>

</head>
<body>
	
	<!-- 修改备注的模态窗口 -->
		<div class="modal fade" id="editClueRemarkModal" role="dialog">
			<div class="modal-dialog" role="document" style="width: 85%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改市场活动备注</h4>
					</div>
					<div class="modal-body">
						<input id="remarkId" type="hidden">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="edit-describe" class="col-sm-2 control-label">备注</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="edit-noteContent"></textarea>
								</div>
							</div>
	
						</form>
	
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="updateMarketActivityReamrkBtn" type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
					</div>
				</div>
			</div>
		</div>	
	


	<!-- 解除关联的模态窗口 -->
	<div class="modal fade" id="unbundModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 30%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">解除关联</h4>
				</div>
				<div class="modal-body">
					<p>您确定要解除该关联关系吗？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 关联市场活动的模态窗口 -->
	<div class="modal fade" id="bundModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">关联市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group"
						style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
							<div class="form-group has-feedback">
								<input type="text" class="form-control" style="width: 300px;"
									placeholder="请输入市场活动名称，支持模糊查询"> <span
									class="glyphicon glyphicon-search form-control-feedback"></span>
							</div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover"
						style="width: 900px; position: relative; top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td><input type="checkbox" /></td>
								<td>名称</td>
								<td>类型</td>
								<td>状态</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox" /></td>
								<td>发传单</td>
								<td>广告</td>
								<td>激活的</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>
							<tr>
								<td><input type="checkbox" /></td>
								<td>发传单</td>
								<td>广告</td>
								<td>激活的</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">关联</button>
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
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-clueId" value="${clue.id }">
						<div class="form-group">
						
							<label for="edit-owner" class="col-sm-2 control-label">所有者</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">
								</select>
							</div>
							<label for="edit-company" class="col-sm-2 control-label">公司</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-company" value="${clue.company }">
							</div>
						</div>

						<div class="form-group">
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
							<label for="edit-surname" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-fullName"
									value="${clue.fullName }">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-job"
									value="${clue.job }">
							</div>
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-email"
									value="${clue.email }">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone"
									value="${clue.phone }">
							</div>
							<label for="edit-website" class="col-sm-2 control-label">网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-website"
									value="${clue.website }">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-mphone"
									value="${clue.mphone }">
							</div>
							<label for="edit-status" class="col-sm-2 control-label">状态</label>
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
										<c:forEach var="sl" items="${sourceList }">
											<option value="${sl.id }">${sl.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<label for="edit-empnums" class="col-sm-2 control-label">员工数</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-empNums"
									value="${clue.empNums }">
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
							<label for="edit-yearIncome" class="col-sm-2 control-label">年收入</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-annualIncome"
									value="${clue.annualIncome }">
							</div>
						</div>
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">${clue.description }</textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div style="position: relative; top: 15px;">
							<div class="form-group">
								<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3"
										id="edit-contactSummary">${clue.contactSummary }</textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-nextContactTime" readonly value="${clue.nextContactTime }">
								</div>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top: 10px;"></div>

						<div style="position: relative; top: 20px;">
							<div class="form-group">
								<label for="edit-country" class="col-sm-2 control-label">国家/地区</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-country" value="${clue.country }">
								</div>
								<label for="edit-province" class="col-sm-2 control-label">省/市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-province" value="${clue.province }">
								</div>
							</div>

							<div class="form-group">
								<label for="edit-city" class="col-sm-2 control-label">城市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-city" value="${clue.city }">
								</div>
								<label for="edit-street" class="col-sm-2 control-label">街道</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-street" value="${clue.street }">
								</div>
							</div>

							<div class="form-group">
								<label for="edit-zipcode" class="col-sm-2 control-label">邮编</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-zipcode" value="${clue.zipcode }">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="updateClueDetailBtn" >更新</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 返回按钮 -->
	<div style="position: relative; top: 35px; left: 10px;">
		<a href="javascript:void(0);" onclick="window.history.back();"><span
			class="glyphicon glyphicon-arrow-left"
			style="font-size: 20px; color: #DDDDDD"></span></a>
	</div>

	<!-- 大标题 -->
	<div style="position: relative; left: 40px; top: -30px;">
		<div class="page-header">
			<h3 id="clueTitle">${clue.fullName } ${clue.appellation } <small>${clue.company }</small></h3>
			</h3>
		</div>
		<div style="position: relative; height: 50px; width: 500px; top: -72px; left: 700px;">
			<button type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-envelope"></span> 发送邮件
			</button>
			<button type="button" class="btn btn-default" onclick="window.location.href='convert.html';">
				<span class="glyphicon glyphicon-retweet"></span> 转换
			</button>
			<button id="editClueDateilBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#editClueModal">
				<span class="glyphicon glyphicon-edit"></span> 编辑
			</button>
			<button type="button" class="btn btn-danger">
				<span class="glyphicon glyphicon-minus"></span> 删除
			</button>
		</div>
	</div>

	<!-- 详细信息 -->
	<div style="position: relative; top: -70px;">
		<div style="position: relative; left: 40px; height: 30px;">
			<div style="width: 300px; color: gray;">名称</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueFullNameAndAppellation">${clue.fullName } ${clue.appellation }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">所有者</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueOwner">${clue.owner }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 10px;">
			<div style="width: 300px; color: gray;">公司</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueCompany">${clue.company }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">职位</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueJob">${clue.job }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 20px;">
			<div style="width: 300px; color: gray;">邮箱</div>
			<div style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueEmail">${clue.email }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">电话</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="cluePhone">${clue.phone }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 30px;">
			<div style="width: 300px; color: gray;">网站</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueWebsite">${clue.website }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">手机</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueMphone">${clue.mphone }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 40px;">
			<div style="width: 300px; color: gray;">状态</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueState">${clue.state }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">来源</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueSource">${clue.source }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 50px;">
			<div style="width: 300px; color: gray;">员工数</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueEmpnums">${clue.empNums }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">行业</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueIndustry">${clue.industry }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 60px;">
			<div style="width: 300px; color: gray;">等级</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueGrade">${clue.grade }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">收入</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueAnnuaincome">${clue.annualIncome }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 70px;">
			<div style="width: 300px; color: gray;">创建者</div>
			<div
				style="width: 500px; position: relative; left: 200px; top: -20px;">
				<b>${clue.createBy }&nbsp;&nbsp;</b><small
					style="font-size: 10px; color: gray;">${clue.createTime }</small>
			</div>
			<div
				style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 80px;">
			<div style="width: 300px; color: gray;">修改者</div>
			<div
				style="width: 500px; position: relative; left: 200px; top: -20px;">
				<b id="clueEditBy">${clue.editBy }&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;" id="clueEditTime">${clue.editTime }</small>
			</div>
			<div
				style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 90px;">
			<div style="width: 300px; color: gray;">描述</div>
			<div
				style="width: 630px; position: relative; left: 200px; top: -20px;">
				<b id="clueDescription"> ${clue.description }</b>
			</div>
			<div
				style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 100px;">
			<div style="width: 300px; color: gray;">联系纪要</div>
			<div
				style="width: 630px; position: relative; left: 200px; top: -20px;">
				<b id="clueContactSummary"> ${clue.contactSummary } </b>
			</div>
			<div
				style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 110px;">
			<div style="width: 300px; color: gray;">下次联系时间</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueNextContactTime">${clue.nextContactTime }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
	</div>

	<!-- 地址信息 -->
	<div style="position: relative; top: 30px; left: 40px;">
		<div class="page-header">
			<h4>地址</h4>
		</div>
		<div style="position: relative; height: 30px; top: 10px;">
			<div style="width: 300px; color: gray;">国家/地区</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueCountry">${clue.country }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">省/市</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueProvince">${clue.province }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; height: 30px; top: 20px;">
			<div style="width: 300px; color: gray;">城市</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueCity">${clue.city }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">街道</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="clueStreet">${clue.street }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; height: 30px; top: 30px;">
			<div style="width: 300px; color: gray;">邮编</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="clueZipcode">${clue.zipcode }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
	</div>

	<!-- 备注 -->
	<div style="position: relative; top: 60px; left: 40px;">
		<div class="page-header">
			<h4>备注</h4>
		</div>

		<!-- 备注1 -->
		<div class="remarkDiv" style="height: 60px;">
			<img title="zhangsan" src="image/user-thumbnail.png"
				style="width: 30px; height: 30px;">
			<div style="position: relative; top: -40px; left: 40px;">
				<h5>哎呦！</h5>
				<font color="gray">线索</font> <font color="gray">-</font> <b>李四先生-动力节点</b>
				<small style="color: gray;"> 2017-01-22 10:10:10 由zhangsan</small>
				<div
					style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
					<a class="myHref" href="javascript:void(0);"><span
						class="glyphicon glyphicon-edit"
						style="font-size: 20px; color: #E6E6E6;"></span></a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a class="myHref"
						href="javascript:void(0);"><span
						class="glyphicon glyphicon-remove"
						style="font-size: 20px; color: #E6E6E6;"></span></a>
				</div>
			</div>
		</div>

		<!-- 备注2 -->
		<div class="remarkDiv" style="height: 60px;">
			<img title="zhangsan" src="image/user-thumbnail.png"
				style="width: 30px; height: 30px;">
			<div style="position: relative; top: -40px; left: 40px;">
				<h5>呵呵！</h5>
				<font color="gray">线索</font> <font color="gray">-</font> <b>李四先生-动力节点</b>
				<small style="color: gray;"> 2017-01-22 10:20:10 由zhangsan</small>
				<div
					style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
					<a class="myHref" href="javascript:void(0);"><span
						class="glyphicon glyphicon-edit"
						style="font-size: 20px; color: #E6E6E6;"></span></a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a class="myHref"
						href="javascript:void(0);"><span
						class="glyphicon glyphicon-remove"
						style="font-size: 20px; color: #E6E6E6;"></span></a>
				</div>
			</div>
		</div>

		<div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
			<form role="form" style="position: relative; top: 10px; left: 10px;">
				<textarea id="remark" class="form-control" style="width: 850px; resize: none;" rows="2" placeholder="添加备注..."></textarea>
				<p id="cancelAndSaveBtn" style="position: relative; left: 737px; top: 10px; display: none;">
					<button id="cancelBtn" type="button" class="btn btn-default">取消</button>
					<button id="saveClueRemarkBtn" type="button" class="btn btn-primary">保存</button>
				</p>
			</form>
		</div>
	</div>

	<!-- 市场活动 -->
	<div>
		<div style="position: relative; top: 60px; left: 40px;">
			<div class="page-header">
				<h4>市场活动</h4>
			</div>
			<div style="position: relative; top: 0px;">
				<table id="activityTable" class="table table-hover"
					style="width: 900px;">
					<thead>
						<tr style="color: #B3B3B3;">
							<td>名称</td>
							<td>类型</td>
							<td>状态</td>
							<td>开始日期</td>
							<td>结束日期</td>
							<td>所有者</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>发传单</td>
							<td>广告</td>
							<td>激活的</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
							<td>zhangsan</td>
							<td><a href="javascript:void(0);" data-toggle="modal"
								data-target="#unbundModal" style="text-decoration: none;"><span
									class="glyphicon glyphicon-remove"></span>解除关联</a></td>
						</tr>
						<tr>
							<td>发传单</td>
							<td>广告</td>
							<td>激活的</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
							<td>zhangsan</td>
							<td><a href="javascript:void(0);" data-toggle="modal"
								data-target="#unbundModal" style="text-decoration: none;"><span
									class="glyphicon glyphicon-remove"></span>解除关联</a></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div>
				<a href="javascript:void(0);" data-toggle="modal"
					data-target="#bundModal" style="text-decoration: none;"><span
					class="glyphicon glyphicon-plus"></span>关联市场活动</a>
			</div>
		</div>
	</div>


	<div style="height: 200px;"></div>
</body>
</html>