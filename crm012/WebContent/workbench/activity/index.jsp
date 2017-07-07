
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println("/crm010/WebContent/workbench/activity/detail.jsp");
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
		
		//以下日历插件在FF中存在兼容问题，在IE浏览器中可以正常使用。
		/*
		$("#startTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
		});
		
		$("#endTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
		});
		*/
		
		//定制字段
		$("#definedColumns > li").click(function(e) {
			//防止下拉菜单消失
	        e.stopPropagation();
	    });
		
	});
	
	


$(function(){
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~时间插件 ~~~~~~~开始~~~~~~~~~~~~~~~~~~~~~~~~~~~  */  
	
	//时间插件
	$("#create-startTime,#create-endTime,#edit-startDate,#edit-endDate,#query-startDate,#query-endDate").datetimepicker({
		  language: 'zh-CN',//显示中文
		  format: 'yyyy-mm-dd',//显示格式
		  minView: "month",//设置只显示到月份
		  initialDate: new Date(),//初始化当前日期
		  autoclose: true,//选中自动关闭
		  todayBtn: true,//显示今日按钮
		  clearBtn:true//显示清空按钮
	})
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~时间插件 ~~~~~~~~~~~结束~~~~~~~~~~~~~~~~~~~~~~~~~~~  */  
	
	
	
	/********************** *********************创建市场活动 **********************************************/
	$("#createActivityBtn").click(function(){
		
		/* 清空表单 */
		$("#create-marketActivityOwner").val("");
		$("#create-marketActivityType").val("");
		$("#create-marketActivityName").val("");
		$("#create-marketActivityState").val("");
		$("#create-startTime").val("");
		$("#create-endTime").val("");
		$("#create-actualCost").val("");
		$("#create-budgetCost").val("");
		$("#create-describe").val("");
		
		$.ajax({
			url:'workbench/activity/createMarketActivity.do',
			type:'post',
			success:function(data){
				//设置所有者
				var htmlStr = "";
				$.each(data,function(index,obj){
					if (obj.id == '${user.id}') {
						htmlStr += "<option value= '"+obj.id+"' selected>" + obj.name + "</option>";
					}else {
						htmlStr += "<option value= '"+obj.id+"'>" + obj.name + "</option>";
					}
				});
				
				$("#create-marketActivityOwner").html(htmlStr);
				//显示模态窗口
				$("#createActivityModal").modal("show");
			}
		});
		
	});
	/********************** *********************创建市场活动 **********************************************/
	
	
	/************** ********** ****************保存市场活动************* ***** ********************* */
	$("#saveActivityBtn").click(function(){
		//收集参数
		var owner = $("#create-marketActivityOwner").val();
		var type = $("#create-marketActivityType").val();
		var name = $.trim($("#create-marketActivityName").val());
		var state = $("#create-marketActivityState").val();
		var startDate = $("#create-startTime").val();
		var endDate = $("#create-endTime").val();
		var actualCost = $.trim($("#create-actualCost").val());
		var budgetCost = $.trim($("#create-budgetCost").val());
		var description = $.trim($("#create-describe").val());
		
		//表单验证 ** 名称不能为空**
		if (name == null || name.length == 0) {
			alert("名称不能为空");
			return;
		}
		
		//表单验证 ** 验证结束日期不能比开始日期小 **
		if (startDate != null && startDate.length > 0) {
			if (endDate != null && endDate.length > 0) {
				if (startDate > endDate) {
					alert("结束日期不能在开始日期前")
					return;
				}
			}
		}
		
		var regExp = /^([1-9][0-9]*||0)$/;
		if (!regExp.test(budgetCost)) {
			alert("预算成本只能为非负整数");
			return;
		}
		
		if (!regExp.test(actualCost)) {
			alert("实际成本只能为非负整数");
			return;
		}

		//发送请求
		$.ajax({
			url:'workbench/activity/saveMarketActivity.do',
			data:{
				owner:owner,
				type:type,
				name:name,
				state:state,
				startDate:startDate,
				endDate:endDate,
				budgetCost:budgetCost,
				actualCost:actualCost,
				description:description
			},
			type:'post',
			success:function(data){
				if (data.success) {
					//关闭模态窗口
					$("#createActivityModal").modal("hide");
					//刷新列表
					display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
				}else {
					alert("创建失败");
					$("#createActivityModal").modal("show");
				}
			}
			
			
		});
	});
	
	/************** ********** ****************保存市场活动************* ***** ********************* */
	
	
	
	
	/* //*******页面加载成功之后,显示首页数据 ******* ********** *****************************/
	display(1,5);
	/* //*******页面加载成功之后,显示首页数据******* ********** **************************** */
	
	
	
	
	/** ********** ***************** //当 "查询"按钮 添加 单击事件 * ********** ******************/
	$("#queryActivityButton").click(function(){
		display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
	});
	/** ********** ***************** //当 "查询"按钮 添加 单击事件 * ********** ******************/
	
	
	/********* ********************** *************当页面加载成功之后 "添加字段"中的所有checkBox都默认选中状态******** ************** */
	$("#definedColumns input[type='checkbox']").prop("checked",true);
	//添加字段 中的 所有checkbox 添加单击事件
	$("#definedColumns input[type='checkbox']").click(function(){
		if($(this).prop("checked")){
			$("#activityListTable td[name="+this.name+"]").show();
		}else {
			$("#activityListTable td[name="+this.name+"]").hide();
		}
	});
	/********* ********************** *************当页面加载成功之后 "添加字段"中的所有checkBox都默认选中状态******** ************** */
	
	
	/************** 删除按钮  添加单击事件********************************************/
	$("#deleteActivityBtn").click(function(){
		//判断是否选中记录
		var ids = $("#activityListTBody input[type='checkbox']:checked");
		if (ids.size() == 0) {
			alert("请选择要删除的市场活动");
			return;
		}
		
		if (window.confirm("确定要删除吗")) {
			var idsStr = "";
			$.each(ids,function(index,obj){
				idsStr += "id=" + obj.value + "&";
			});
			$.ajax({
				url:'workbench/activity/deleteMarketActivity.do',
				data:idsStr.substr(0,idsStr.length-1),
				type:'post',
				success:function(data){
					if (data.success) {
						alert("删除成功");
						display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
					}else {
						alert("删除失败");
					}
				}
			});
		}
		
	});
	/************** 删除按钮  添加单击事件********************************************/
	
	
	/**************全选  单选***全选  单选***全选  单选*********全选  单选*********全选  单选**************全选  单选******/
	//给全选按钮 添加单击事件
	$("#chk_all").click(function(){
		$("#activityListTBody input[type='checkbox']").prop("checked",$(this).prop("checked"));
	});
	//给列表中的所有checkbox添加单击事件
	$("#activityListTBody").on("click","input[type='checkbox']",function(){
		if ($("#activityListTBody input[type='checkbox']").size()==$("#activityListTBody input[type='checkbox']:checked").size()) {
			$("#chk_all").prop("checked",true);
		}else {
			$("#chk_all").prop("checked",false);
			}
		});
	/**************全选  单选***全选  单选***全选  单选*********全选  单选*********全选  单选**************全选  单选******/
	
	
	
	/* ************根据id 获取市场活动  进行修改  *************************************************************************/
	$("#editActivityBtn").click(function(){
		if ($("#activityListTBody input[type='checkbox']:checked").size() != 1){
			alert("请正确选择要修改的一条市场活动记录");
			return;
		}
			$.ajax({
				url:'workbench/activity/editMarketActivity.do',
				data:{
					id:$("#activityListTBody input[type='checkbox']:checked").val()
				},
				type:'post',
				success:function(data){
					if (data.success) {
						//设置所有者
						var htmlStr="";
						$.each(data.userList,function(index,obj){
							if (obj.id == data.marketActivity.owner) {
								htmlStr += "<option value='"+obj.id+"' selected>"+obj.name+"</option>"
							}else {
								htmlStr += "<option value='"+obj.id+"'>"+obj.name+"</option>"
							}
						});
						$("#edit-marketActivityOwner").html(htmlStr);						
						$("#edit-marketActivityId").val(data.marketActivity.id);
						$("#edit-marketActivityType").val(data.marketActivity.type);
						$("#edit-marketActivityName").val(data.marketActivity.name);
						$("#edit-marketActivityState").val(data.marketActivity.state);
						$("#edit-startDate").val(data.marketActivity.startDate);
						$("#edit-endDate").val(data.marketActivity.endDate);
						$("#edit-actualCost").val(data.marketActivity.actualCost);
						$("#edit-budgetCost").val(data.marketActivity.budgetCost);
						$("#edit-description").val(data.marketActivity.description);
						
						$("#editActivityModal").modal("show"); 
					}else {
						alert("市场活动信息 获取 失败");
						$("#editActivityModal").modal("hide");
						return;
					}
				}
			});
		
		
	});
	/* ************根据id 获取市场活动  进行修改修改修改修改  *****************************************************************************/
	
	
	/* ************根据id 获取市场活动  进行更新更新更新更新更新更新更新  *****************************************************/
	$("#updateMarketActivityBtn").click(function(){
		var id = $("#edit-marketActivityId").val();
		var	owner = $("#edit-marketActivityOwner").val();
		var type = $("#edit-marketActivityType").val();
		var name = $("#edit-marketActivityName").val();
		var state = $("#edit-marketActivityState").val();
		var startDate = $("#edit-startDate").val();
		var endDate = $("#edit-endDate").val();
		var actualCost = $("#edit-actualCost").val();
		var budgetCost = $("#edit-budgetCost").val();
		var description = $("#edit-description").val();
		
		/* 表单验证   =====   --!   需要完成  2017年6月30日 作业*/
		
		$.ajax({
			url:'workbench/activity/updateEditMarketActivity.do',
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
				if (data.success) {
					//关闭模态窗口
					$("#editActivityModal").modal("hide");
					//更新列表
					display($("#pageNoDiv").bs_pagination('getOption', 'currentPage'),$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
					
				}else {
					alert("修改失败");
					//模态窗口不关闭
					$("#editActivityModal").modal("show");
				}
			}
		});
		
		
		
	});
	
	/* 市场活动列表 导出 ***********************************************/
	//给"导出"按钮添加单击事件
	$("#exportActivityBtn").click(function(){
		window.location.href=
		"workbench/activity/exportMarketActivity.do?name="
		+$.trim($("#query-name").val())
		+"&owner="+$.trim($("#query-owner").val())
		+"&type="+$("#query-type").val()
		+"&state="+$("#query-state").val()
		+"&startDate="+$.trim($("#query-startDate").val())
		+"&endDate="+$.trim($("#query-endDate").val());
	});
	/* 市场活动列表 导出 ***********************************************/
	
	
	/* 导入市场活动********导入市场活动************导入市场活动*******导入市场活动导入市场活动导入市场活动******/
	$("#importActivityBtn").click(function(){
		//收集参数
		var maFile=$("#activityFile")[0].files[0];
		
		var fileName=$("#activityFile").val();
		var suffix=(fileName.substr(fileName.lastIndexOf(".")+1)).toUpperCase();
		if(!(suffix=='XLS'||suffix=='XLSX')){
			alert("只能上传XLS或者XLSX类型的文件！");
			return;
		}
		
		if(maFile.size>1024*1024*5){
			alert("上传文件不能超过5M!");
			return;
		}
		
		
		//发送请求
		/*
		FormData是jquery提供的一个接口，可以，模拟键值对向服务器提交参数；
		它最大的优势是可以提交二进制数据。
		*/
		var formData=new FormData();
		formData.append("activityFile",maFile);
		$.ajax({
			url:'workbench/activity/remark/importMarketActivity.do',
			data:formData,
			type:'post',
			processData:false,//processData主要是配合contentType使用的，在ajax对参数进行x-www-form-urlencoded编码之前，把所有的数据统一转化为字符串；现在，不需要进行x-www-form-urlencoded格式的编码，则也不需要进行字符串转化了。
			contentType:false,//默认情况下，ajax向服务器发送请求采用x-www-form-urlencoded编码格式，这种格式只能处理文本数据；如果有上传文件这种二进制数据，则要设置contentType为false,使ajax不再对参数做x-www-form-urlencoded格式的编码
			success:function(data){
				if(data.success){
					alert("成功导入"+data.count+"条数据！");
					$("#importActivityModal").modal("hide");
					display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
				}else{
					alert("导入失败！请检查文件格式！");
					$("#importActivityModal").modal("show");
				}
			}
		});
	
	});
	
	
	/* 导入市场活动********导入市场活动************导入市场活动*******导入市场活动导入市场活动导入市场活动******/
	
	
});

	/* //******* ********** ************** 市场活动列表显示************ ********** ************ ********** ********* */
	function display(pageNo,pageSize){

		$.ajax({
			url:'workbench/activity/queryMarketActivityForPage.do',
			data:{
				pageNo:pageNo,
				pageSize:pageSize,
				name:$.trim($("#query-name").val()),
				owner:$.trim($("#query-owner").val()),
				type:$("#query-type").val(),
				state:$("#query-state").val(),
				startDate:$.trim($("#query-startDate").val()),
				endDate:$.trim($("#query-endDate").val())
			},
			type:'post',
			success:function(data){
				//设置市场活动列表
				var htmlStr = "";
				$.each(data.dataList,function(index,obj){
				htmlStr += "<tr>";
				htmlStr += "<td><input value='"+obj.id+"' type = 'checkbox'/></td>";
				if ($("#definedColumns input[name='name']").prop("checked")) {
					htmlStr += "<td name = 'name'><a style = 'text-decoration : none; cursor : pointer; 'onclick = 'window.location.href = \"workbench/activity/detailMarketActivity.do?id="+obj.id+"\";'>"+obj.name+"</a></td>";
				}else {
					htmlStr += "<td name = 'name'><a style = 'display : none; cursor : pointer; 'onclick = 'window.location.href = \"workbench/activity/detailMarketActivity.do?id="+obj.id+"\";'>"+obj.name+"</a></td>";
				}
				
				if ($("#definedColumns input[name=type]").prop("checked")) {
					htmlStr += "<td name = 'type'>"+obj.type+"</td>";
				}else {
					htmlStr += "<td name = 'type' style='display:none'>"+obj.type+"</td>";
				}
				
				if ($("#definedColumns input[name=state]").prop("checked")) {
					htmlStr += "<td name = 'state'>"+obj.state+"</td>";
				}else {
					htmlStr += "<td name = 'state' style='display:none'>"+obj.state+"</td>";
				}
				
				if ($("#definedColumns input[name=startDate]").prop("checked")) {
					htmlStr += "<td name = 'startDate'>"+obj.startDate+"</td>";
				}else {
					htmlStr += "<td name = 'startDate' style='display:none'>"+obj.startDate+"</td>";
				}
				
				if ($("#definedColumns input[name=endDate]").prop("checked")) {
					htmlStr += "<td name = 'endDate'>"+obj.endDate+"</td>";
				}else {
					htmlStr += "<td name = 'endDate' style='display:none'>"+obj.endDate+"</td>";
				}
				
				if ($("#definedColumns input[name=owner]").prop("checked")) {
					htmlStr += "<td name = 'owner'>"+obj.owner+"</td>";
				}else {
					htmlStr += "<td name = 'owner' style='display:none'>"+obj.owner+"</td>";
				}
				
				if ($("#definedColumns input[name=budgetCost]").prop("checked")) {
					htmlStr += "<td name = 'budgetCost'>"+obj.budgetCost+"</td>";
				}else {
					htmlStr += "<td name = 'budgetCost' style='display:none'>"+obj.budgetCost+"</td>";
				}
				
				
				
				
				if ($("#definedColumns input[name=actualCost]").prop("checked")) {
					htmlStr += "<td name = 'actualCost'>"+obj.actualCost+"</td>";
				}else {
					htmlStr += "<td name = 'actualCost' style='display:none'>"+obj.actualCost+"</td>";
				}
				
				if ($("#definedColumns input[name=createBy]").prop("checked")) {
					htmlStr += "<td name = 'createBy'>"+obj.createBy+"</td>";
				}else {
					htmlStr += "<td name = 'createBy' style='display:none'>"+obj.createBy+"</td>";
				}
				
				if ($("#definedColumns input[name=createTime]").prop("checked")) {
					htmlStr += "<td name = 'createTime'>"+obj.createTime+"</td>";
				}else {
					htmlStr += "<td name = 'createTime' style='display:none'>"+obj.createTime+"</td>";
				}
				
				if ($("#definedColumns input[name=editBy]").prop("checked")) {
					htmlStr += "<td name = 'editBy'>"+(obj.editBy==null?'':obj.editBy)+"</td>";
				}else {
					htmlStr += "<td name = 'editBy' style='display:none'>"+(obj.editBy==null?'':obj.editBy)+"</td>";
				}
				
				if ($("#definedColumns input[name=editTime]").prop("checked")) {
					htmlStr += "<td name = 'editTime'>"+(obj.editTime==null?'':obj.editTime)+"</td>";
				}else {
					htmlStr += "<td name = 'editTime' style='display:none'>"+(obj.editTime==null?'':obj.editTime)+"</td>";
				}
				
				if ($("#definedColumns input[name=description]").prop("checked")) {
					htmlStr += "<td name = 'description'>"+obj.description+"</td>";
				}else {
					htmlStr += "<td name = 'description' style='display:none'>"+obj.description+"</td>";
				}
				
				htmlStr += "</tr>";
				});
				
				$("#activityListTBody").html(htmlStr);
				
				//隔行换颜色
				$("#activityListTBody tr even").addClass("active");
				
				//设置总页数  已被插件取代
				/* $("#totalCount").html(data.totalCount); */
				
				var totalPage = 1;
				if (data.totalCount % pageSize == 0) {
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
		/* //******* ********** ************** 市场活动列表显示******************** ********** **** ********** ********* */
		
		
		
	}
	

</script>
<title>Insert title here</title>
</head>
<body>
	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建市场活动</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-marketActivityOwner"
								class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityOwner">

								</select>
							</div>
							<label for="create-marketActivityType"
								class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityType">
									<option></option>
									<c:if test="${!empty activityTypeList }">
										<c:forEach var="at" items="${activityTypeList }">
											<option value="${at.id }">${at.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="create-marketActivityName"
								class="col-sm-2 control-label">名称<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control"
									id="create-marketActivityName">
							</div>
							<label for="create-marketActivityState"
								class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityState">
									<option></option>
									<c:if test="${!empty acitivityStatusList }">
										<c:forEach var="as" items="${acitivityStatusList }">
											<option value="${as.id }">${as.text }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-startTime"
									readonly>
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-endTime"
									readonly>
							</div>
						</div>

						<div class="form-group">
							<label for="create-actualCost" class="col-sm-2 control-label">实际成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-actualCost">
							</div>
							<label for="create-budgetCost" class="col-sm-2 control-label">预算成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-budgetCost">
							</div>
						</div>

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe"></textarea>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="saveActivityBtn" class="btn btn-primary">保存</button>
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

					<form class="form-horizontal" role="form">
						<input id="edit-marketActivityId" type="hidden">
						<div class="form-group">
							<label for="edit-marketActivityOwner"
								class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">

								</select>
							</div>
							<label for="edit-marketActivityType"
								class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityType">
									<option></option>
									<c:if test="${!empty activityTypeList }">
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
								<input type="text" class="form-control"
									id="edit-marketActivityName" value="发传单">
							</div>
							<label for="edit-marketActivityState"
								class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityState">
									<option></option>
									<c:if test="${!empty acitivityStatusList }">
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
								<input type="text" class="form-control" id="edit-startDate"
									readonly>
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endDate"
									readonly>
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
								<textarea class="form-control" rows="3" id="edit-description">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="updateMarketActivityBtn" type="button"
						class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 导入市场活动的模态窗口 -->
	<div class="modal fade" id="importActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
				</div>
				<div class="modal-body" style="height: 350px;">
					<div style="position: relative; top: 20px; left: 50px;">
						请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
					</div>
					<div style="position: relative; top: 40px; left: 50px;">
						<input type="file" id="activityFile">
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
					<button type="button" id="importActivityBtn" >导入</button>
				</div>
			</div>
		</div>
	</div>

	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div
		style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 130%; position: absolute; top: 5px; left: 10px;">

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
							<div class="input-group-addon">所有者</div>
							<input class="form-control" type="text" id="query-owner">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">类型</div>
							<select class="form-control" id="query-type">
								<option></option>
								<c:if test="${not empty activityTypeList }">
									<c:forEach var="at" items="${activityTypeList }">
										<option value="${at.id }">${at.text }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>

					<br>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="query-state">
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
						<div class="input-group">
							<div class="input-group-addon">开始日期</div>
							<input class="form-control" type="text" id="query-startDate"
								readonly />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">结束日期</div>
							<input class="form-control" type="text" id="query-endDate"
								readonly>
						</div>
					</div>

					<button type="button" id="queryActivityButton"
						class="btn btn-default">查询</button>

				</form>
			</div>
			<div class="btn-toolbar" role="toolbar"
				style="background-color: #F7F7F7; height: 50px; position: relative; top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
					<button type="button" id="createActivityBtn"
						class="btn btn-primary">
						<span class="glyphicon glyphicon-plus"></span> 创建
					</button>
					<button type="button" id="editActivityBtn" class="btn btn-default">
						<span class="glyphicon glyphicon-pencil"></span> 修改
					</button>
					<button type="button" id="deleteActivityBtn" class="btn btn-danger">
						<span class="glyphicon glyphicon-minus"></span> 删除
					</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#importActivityModal">
						<span id="" class="glyphicon glyphicon-import"></span> 导入
					</button>
					<button id="exportActivityBtn" type="button"
						class="btn btn-default">
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
						<li><a href="javascript:void(0);"><input name="name"
								type="checkbox" /> 名称</a></li>
						<li><a href="javascript:void(0);"><input name="type"
								type="checkbox" /> 类型</a></li>
						<li><a href="javascript:void(0);"><input name="state"
								type="checkbox" /> 状态</a></li>
						<li><a href="javascript:void(0);"><input name="startDate"
								type="checkbox" /> 开始日期</a></li>
						<li><a href="javascript:void(0);"><input name="endDate"
								type="checkbox" /> 结束日期</a></li>
						<li><a href="javascript:void(0);"><input name="owner"
								type="checkbox" /> 所有者</a></li>
						<li><a href="javascript:void(0);"><input
								name="budgetCost" type="checkbox" /> 预算成本</a></li>
						<li><a href="javascript:void(0);"><input
								name="actualCost" type="checkbox" /> 实际成本</a></li>
						<li><a href="javascript:void(0);"><input name="createBy"
								type="checkbox" /> 创建者</a></li>
						<li><a href="javascript:void(0);"><input
								name="createTime" type="checkbox" /> 创建时间</a></li>
						<li><a href="javascript:void(0);"><input name="editBy"
								type="checkbox" /> 修改者</a></li>
						<li><a href="javascript:void(0);"><input name="editTime"
								type="checkbox" /> 修改时间</a></li>
						<li><a href="javascript:void(0);"><input
								name="description" type="checkbox" /> 描述</a></li>
					</ul>
				</div>

				<div class="btn-group"
					style="position: relative; top: 18%; left: 8px;">
					<form class="form-inline" role="form">
						<div class="form-group has-feedback">
							<input type="email" class="form-control" style="width: 300px;"
								placeholder="支持任何字段搜索"> <span
								class="glyphicon glyphicon-search form-control-feedback"></span>
						</div>
					</form>
				</div>
			</div>
			<div style="position: relative; top: 10px;">
				<table id="activityListTable" class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input id="chk_all" type="checkbox" /></td>
							<td name='name'>名称</td>
							<td name='type'>类型</td>
							<td name='state'>状态</td>
							<td name='startDate'>开始日期</td>
							<td name='endDate'>结束日期</td>
							<td name='owner'>所有者</td>
							<td name='budgetCost'>预算成本</td>
							<td name='actualCost'>实际成本</td>
							<td name='createBy'>创建者</td>
							<td name='createTime'>创建时间</td>
							<td name='editBy'>修改者</td>
							<td name='editTime'>修改时间</td>
							<td name='description' width="10%">描述</td>
						</tr>
					</thead>


					<tbody id="activityListTBody">

					</tbody>
				</table>
				<div id="pageNoDiv"></div>
			</div>




		</div>
	</div>
</body>
</html>