
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println("/crm010/WebContent/workbench/activity/detail.html");
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
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
		
		$("#remarkDivList").on("mouseover", ".remarkDiv", function(){
			$(this).children("div").children("div").show();
		});
		
		$("#remarkDivList").on("mouseout", ".remarkDiv", function(){
			$(this).children("div").children("div").hide();
		});
		
		$("#remarkDivList").on("mouseover", ".myHref", function(){
			$(this).children("span").css("color","red");
		});
		
		$("#remarkDivList").on("mouseout", ".myHref", function(){
			$(this).children("span").css("color","#E6E6E6");
		});
	});
	
</script>

<script type="text/javascript">
$(function(){
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~时间插件 ~~~~~~~开始~~~~~~~~~~~~~~~~~~~~~~~~~~~  */  
	
	//时间插件
	$("#edit-startDate,#edit-endDate").datetimepicker({
		  language: 'zh-CN',//显示中文
		  format: 'yyyy-mm-dd',//显示格式
		  minView: "month",//设置只显示到月份
		  initialDate: new Date(),//初始化当前日期
		  autoclose: true,//选中自动关闭
		  todayBtn: true,//显示今日按钮
		  clearBtn:true//显示清空按钮
	});
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~时间插件 ~~~~~~~~~~~结束~~~~~~~~~~~~~~~~~~~~~~~~~~~  */  
	
	
	
	/* 市场活动编辑*********市场活动编辑***********市场活动编辑*********市场活动编辑*********市场活动编辑*市场活动编辑***市场活动编辑 */
	$("#editMarketActivityDetailBtn").click(function(){
	 	 $.ajax({
			url:'workbench/activity/editMarketActivity.do',
			data:{
				id:$("#edit-marketActivityId").val()
			},
			type:'post',
			success:function(data){
		
					
			 //设置所有者
				var htmlStr="";
				$.each(data.userList,function(index,obj){
					if (obj.id == data.marketActivity.owner) {						
						htmlStr += "<option value='"+obj.id+"' selected>"+obj.name+"</option>"
					}else {
						htmlStr += "<option value='"+obj.id+"'>"+obj.name+"</option>"
					}
				}); 
					// 将用户填到所有者标签中
					$("#edit-marketActivityOwner").html(htmlStr);
				
					$("#edit-marketActivityType").val(data.marketActivity.type);
					$("#edit-marketActivityName").val(data.marketActivity.name);
					$("#edit-marketActivityState").val(data.marketActivity.state);
					$("#edit-startDate").val(data.marketActivity.startDate);
					$("#edit-endDate").val(data.marketActivity.endDate);
					$("#edit-actualCost").val(data.marketActivity.actualCost);
					$("#edit-budgetCost").val(data.marketActivity.budgetCost);
					$("#edit-description").val(data.marketActivity.description);
					$("#editActivityModal").modal("show");
				
			}

		});  
		
	});
	/* 市场活动编辑*********市场活动编辑***********市场活动编辑*********市场活动编辑*********市场活动编辑*市场活动编辑***市场活动编辑 */
	
	
	
	/* ******************************市场活动详情页 更新************************************************************ */
	$("#UpdateMarketActivityDetailBtn").click(function(){
		//获取参数
		var id="${param.id}";
		var owner = $("#edit-marketActivityOwner").val();
		var type = $("#edit-marketActivityType").val();
		var name = $("#edit-marketActivityName").val();
		var state = $("#edit-marketActivityState").val();
		var startDate = $("#edit-startDate").val();
		var endDate = $("#edit-endDate").val();
		var actualCost = $("#edit-actualCost").val();
		var budgetCost = $("#edit-budgetCost").val();
		var description = $("#edit-description").val();

		//表单验证
		if (description == null || description.length == 0) {
			alert("描述不能为空");
			return;
		}

		if(startDate != null && startDate.length != 0 && endDate != null && endDate.length != 0){
			if(startDate > endDate){
				alert("开始日期不能比结束日期大");
				return;
			}
		}

		var regExp = /^([1-9]{1}[0-9]*||0)$/;
		if(!regExp.test(actualCost)){
			alert("实际成本只能是非负整数");
			return;
		}
		if(!regExp.test(budgetCost)){
			alert("预算成本只能是非负整数");
			return;
		}

		//发送请求
		$.ajax({
			url:'workbench/activity/detail/updateMarketActivityDetailById.do',
			data:{
				id:id,
				owner:owner,
				type:type,
				name:name,
				state:state,
				startDate:startDate,
				endDate:endDate,
				actualCost:actualCost,
				budgetCost:budgetCost,
				description:description
			},
			type:'post',
			success:function(data){
				if(data.success){
					$("#editActivityModal").modal("hide");
					//更新列表  局部刷新市场活动详细信息
					reflushDetailMarketActivity();
					//关闭模态窗口
				}else {
					alert("更新失败");
					//不关闭模态窗口
					$("#editActivityModal").modal("show");
				}
			}
		});
	});
	/* ******************************市场活动详情页 更新************************************************************ */
	
	
	
	
	
	
	/* 市场活动明细页 添加备注 ********************************************/
	$("#createMarketActivityRemarkBtn").click(function(){
		
		//收集参数
		var noteContent = $.trim($("#remark").val());
		var activityId = '${marketActivity.id}';

		//表单验证  例如验证一些和谐信息
		if (noteContent == null || noteContent.length == 0) {
			alert("备注内容不能为空");
			return;
		}
		
		//发送请求
		$.ajax({
			url:'workbench/activity/detail/createMarketActivityRemark.do',
			data:{
				/* 把收集到的参数 装入data容器 */
					noteContent:noteContent,
					activityId:activityId
			},
			type:'post',
			success:function(data){
				if (data.success) {
					//更新备注列表		
					var htmlStr = "";
					htmlStr += "<div id='div_"+data.remark.id+"' class='remarkDiv' style='height: 60px;'>";
					htmlStr += "<img title='${user.name }' src='image/user-thumbnail.png' style='width: 30px; height:30px;'>";
					htmlStr += "<div style='position: relative; top: -40px; left: 40px;' >";
					htmlStr += "<h5>"+data.remark.noteContent+"</h5>";
					htmlStr += "<font color='gray'>市场活动</font> <font color='gray'>-</font> <b>${activity.name }</b> <small style='color: gray;'> "+data.remark.noteTime+" 由${user.name }创建</small>";
					htmlStr += "<div style='position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;'>";
					htmlStr += "<a remark_id='"+data.remark.id+"' name='editA' class='myHref' href='javascript:void(0);'><span class='glyphicon glyphicon-edit' style='font-size: 20px; color: #E6E6E6;'></span></a>";
					htmlStr += "&nbsp;&nbsp;&nbsp;&nbsp;";
					htmlStr += "<a remark_id='"+data.remark.id+"' name='removeA' class='myHref' href='javascript:void(0);'><span class='glyphicon glyphicon-remove' style='font-size: 20px; color: #E6E6E6;'></span></a>";
					htmlStr += "</div>";
					htmlStr += "</div>";
					htmlStr += "</div>";
					$("#remarkDiv").before(htmlStr);
					//清空输入框
					$("#remark").val('');
					
				}else {
					alert("提交失败!");
				}
			}
		
		});
		
	});
	/* 市场活动明细页 添加备注 ********************************************/
	
	/* 备注  删除  图标 添加 单击 事件 * * * * * *  * * * * * *  * * * * * *  * * * * * * * */
	$("#remarkDivList").on("click","a[name='removeA']",function(){
		
		var id = $(this).attr("remark_id");
		
		$.ajax({
			url:'workbench/activity/detail/remark/deleteMarketActivityRemark',
			data:{
				id:id
				},
			type:'post',
			success:function(data){
				if (data.success) {
					//更新备注列表
					$("#div_"+id).remove();
				}else {
					alert("删除失败");
				}
			}
		});
		
	});
	/* 备注  删除  图标 添加 单击 事件 * * * * * *  * * * * * *  * * * * * *  * * * * * * * */
	
	
	
	/* 备注  修改 图标 添加 单击 事件 * * * * * *  * * * * * *  * * * * * *  * * * * * * * */
	$("#remarkDivList").on("click","a[name='editA']",function(){
		//设置数据
		$("#edit-noteContent").val($("#div_"+$(this).attr("remark_id")+" h5:eq(0)").html());
		$("#remarkId").val($(this).attr("remark_id"));
		//显示模态窗口
		$("#editActivityRemarkModal").modal("show");
		
	});
	/* 备注  修改 图标 添加 单击 事件 * * * * * *  * * * * * *  * * * * * *  * * * * * * * */
	
	/* 修改备注模态窗口中的 更新 按钮 */
	$("#updateMarketActivityReamrkBtn").click(function(){
		//收集参数
		var id = $("#remarkId").val();
		var noteContent = $.trim($("#edit-noteContent").val());
		
		//表单验证
		if (noteContent == null || noteContent.length == 0) {
			alert("备注内容不能为空");
			return;
		}
		
		//发送请求
		$.ajax({
			url:'workbench/activity/detail/UpdateMarketActivityRemark.do',
			data:{
				id:id,
				noteContent:noteContent
			},
			type:'post',
			success:function(data){
				if (data.success) {
					//更新列表
					
					$("#div_"+id+" h5:eq(0)").html(noteContent);
					$("#div_"+id+" small:eq(0)").html(" "+data.remark.editTime+" 由 ${user.name}  修改");
					reflushDetailMarketActivity();
					$("#editActivityRemarkModal").modal("hide");
				}else {
					alert("修改失败");
					$("#editActivityRemarkModal").modal("show");
				}
			}
		});
		
	});
	/* 修改备注模态窗口中的 更新 按钮 */
	
	/* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 */
	$("#deleteMarketActivityDetailBtn").click(function(){
		$.ajax({
			url:"workbench/acitivty/detail/deleteMarketActivityDetail.do",
			data:{id:"${param.id}"},
			type:"post",
			success:function(data){
				if (data.success) {
					alert("删除成功");
					window.location.href="workbench/activity/index.jsp";
				}else {
					alert("删除失败");
				}
			}
			
		});
	})
	/* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 *//* 市场活动详情页中的删除 */
})

/* 局部刷新 */ /* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 */
	function reflushDetailMarketActivity(){
		$.ajax({
			url:"workbench/activity/detail/editMarketActivityDetail.do",
			data:{
				id:"${param.id}"
			},
			type:"post",
			success:function(data){
				if(data.success){
					// 修改后标题
					$("#activityTitle").html("市场活动-"+data.marketActivity.name+"<small>  "+data.marketActivity.startDate+" ~ "+data.marketActivity.endDate+"</small>");
					// 修改后详细信息
					$("#marketActivityOwner").html(data.marketActivity.owner);
					$("#marketActivityType").html(data.marketActivity.type);
					$("#marketActivityState").html(data.marketActivity.state);
					$("#marketActivityName").html(data.marketActivity.name);
					$("#marketActivityStartDate").html(data.marketActivity.startDate);
					$("#marketActivityEndDate").html(data.marketActivity.endDate);
					$("#marketActivityActualCost").html(data.marketActivity.actualCost);
					$("#MarketActivityBudgetCost").html(data.marketActivity.budgetCost);
					$("#marketActivityEditBy").html(data.marketActivity.editBy+"&nbsp;&nbsp;");
					$("#marketActivityEditTime").html(data.marketActivity.editTime);
					$("#marketActivityDescription").html(data.marketActivity.description);
					// 修改后备注信息中(市场活动名称改变)
					$("b[name='marketingActivitiesId']").html(data.marketActivity.name);
				}else{
					alert("刷新失败");
				}
			}
		});
	}
	/* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 *//* 局部刷新 */

</script>

</head>
<body>
	<!-- 修改备注的模态窗口 -->
	<div class="modal fade" id="editActivityRemarkModal" role="dialog">
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




	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改市场活动</h4>
				</div>
				<div class="modal-body">
					<input type="hidden"  id="edit-marketActivityId" value="${marketActivity.id }">
					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="edit-marketActivityOwner"
								class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner"></select>
							</div>

							<label for="edit-marketActivityType"
								class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityType">
									<option></option>
									<c:if test="${not empty activityTypeList }">
										<c:forEach var="at" items="${activityTypeList }">
											<option value="${at.id }">${at.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>

						</div>

						<div class="form-group">
							<label for="edit-marketActivityName"
								class="col-sm-2 control-label">名称<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
							</div>
							<label for="edit-marketActivityState" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityState">
									<option></option>
									<c:if test="${not empty acitivityStatusList }">
										<c:forEach var="as" items="${acitivityStatusList }">
											<option value="${as.id }">${as.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startDate" readonly>
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endDate" readonly>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-actualCost" class="col-sm-2 control-label">实际成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-actualCost">
							</div>
							<label for="edit-budgetCost" class="col-sm-2 control-label">预算成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-budgetCost">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="UpdateMarketActivityDetailBtn">更新</button>
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
			<h3 id="activityTitle">市场活动-${marketActivity.name } <small>${marketActivity.startDate }~ ${marketActivity.endDate }</small></h3>
		</div>
		<div
			style="position: relative; height: 50px; width: 250px; top: -72px; left: 700px;">
			<button type="button" id="editMarketActivityDetailBtn" class="btn btn-default">
				<span class="glyphicon glyphicon-edit"></span> 编辑jsp </button>
			<button id="deleteMarketActivityDetailBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除 </button>
		</div>
	</div>

	<!-- 详细信息 -->
	<div style="position: relative; top: -70px;">
		<div style="position: relative; left: 40px; height: 30px;">
			<div style="width: 300px; color: gray;">所有者</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="marketActivityOwner">${marketActivity.owner }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">类型</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="marketActivityType">${marketActivity.type }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 10px;">
			<div style="width: 300px; color: gray;">名称</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="marketActivityName">${marketActivity.name }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">状态</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="marketActivityState">${marketActivity.state }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 20px;">
			<div style="width: 300px; color: gray;">开始日期</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="marketActivityStartDate">${marketActivity.startDate }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">结束日期</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="marketActivityEndDate">${marketActivity.endDate }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 30px;">
			<div style="width: 300px; color: gray;">实际成本</div>
			<div
				style="width: 300px; position: relative; left: 200px; top: -20px;">
				<b id="marketActivityActualCost">${marketActivity.actualCost }</b>
			</div>
			<div
				style="width: 300px; position: relative; left: 450px; top: -40px; color: gray;">预算成本</div>
			<div
				style="width: 300px; position: relative; left: 650px; top: -60px;">
				<b id="marketActivityBudgetCost">${marketActivity.budgetCost }</b>
			</div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div
				style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 40px;">
			<div style="width: 300px; color: gray;">创建者</div>
			<div
				style="width: 500px; position: relative; left: 200px; top: -20px;">
				<b>${marketActivity.createBy }&nbsp;&nbsp;</b><small
					style="font-size: 10px; color: gray;">${marketActivity.createTime }</small>
			</div>
			<div
				style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 50px;">
			<div style="width: 300px; color: gray;">修改者</div>
			<div
				style="width: 500px; position: relative; left: 200px; top: -20px;">
				<b id="marketActivityEditBy">${marketActivity.editBy }&nbsp;&nbsp;</b><small
					style="font-size: 10px; color: gray;" id="marketActivityEditTime">${marketActivity.editTime }</small>
			</div>
			<div
				style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 60px;">
			<div style="width: 300px; color: gray;">描述</div>
			<div
				style="width: 630px; position: relative; left: 200px; top: -20px;">
				<b id="marketActivityDescription"> <!--描述  --> ${marketActivity.description }
				</b>
			</div>
			<div
				style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
	</div>

	<!-- 备注 -->
	<div id="remarkDivList"
		style="position: relative; top: 30px; left: 40px;">
		<div class="page-header">
			<h4>市场活动备注</h4>
		</div>
		<c:if test="${not empty remarkList }">
			<c:forEach var="remark" items="${remarkList }">
				<div id="div_${remark.id }" class="remarkDiv" style="height: 60px;">
					<img title="${remark.notePerson }" src="image/user-thumbnail.png"
						style="width: 30px; height: 30px;">
					<div style="position: relative; top: -40px; left: 40px;">
						<h5>${remark.noteContent }</h5>
						<font color="gray">市场活动</font> <font color="gray">-</font> <b name="marketingActivitiesId">${marketActivity.name }</b>
						<small style="color: gray;">
							${remark.editFlag==0?remark.noteTime:remark.editTime } 由
							${remark.editFlag==0?remark.notePerson:remark.editPerson }${remark.editFlag==0?'创建':'修改' }</small>
						<div
							style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
							<a remark_id="${remark.id }" name="editA" class="myHref"
								href="javascript:void(0);"><span
								class="glyphicon glyphicon-edit"
								style="font-size: 20px; color: #E6E6E6;"></span></a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a remark_id="${remark.id }"
								name="removeA" class="myHref" href="javascript:void(0);"><span
								class="glyphicon glyphicon-remove"
								style="font-size: 20px; color: #E6E6E6;"></span></a>
						</div>
					</div>
				</div>

			</c:forEach>
		</c:if>



		<div id="remarkDiv"
			style="background-color: #E6E6E6; width: 870px; height: 90px;">
			<form role="form" style="position: relative; top: 10px; left: 10px;">
				<textarea id="remark" class="form-control"
					style="width: 850px; resize: none;" rows="2" placeholder="添加备注..."></textarea>
				<p id="cancelAndSaveBtn"
					style="position: relative; left: 737px; top: 10px; display: none;">
					<button id="cancelBtn" type="button" class="btn btn-default">取消</button>
					<button type="button" id="createMarketActivityRemarkBtn"
						class="btn btn-primary">保存</button>
				</p>
			</form>
		</div>
	</div>
	<div style="height: 200px;"></div>
</body>
</html>