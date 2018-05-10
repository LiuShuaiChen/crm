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
		
		//定制字段
		$("#definedColumns > li").click(function(e) {
			//防止下拉菜单消失
	        e.stopPropagation();
	    });
		
	});
	
</script>

<script type="text/javascript">
$(function(){
	
	/* 列表显示 */
	
	function display(pageNo,pageSize){
		
		$.ajax({
			url:"workbench/customer/listingCustomer.do",
			type:"post",
			data:{
				pageNo:pageNo,
				pageSize:pageSize,
				name:$.trim($("#query-name").val()),
				owner:$.trim($("#query-owner").val()),
				phone:$("#query-phone").val(),
				website:$("#query-website").val(),
				grade:$.trim($("#query-grade").val()),
				industry:$.trim($("#query-industry").val())
			},
			type:"post",
			success:function(data){
				var htmlStr = "";
				$.each(data.dataList,function(index,obj){
					htmlStr += "<tr>";
					htmlStr += "<td><input value='"+obj.id+"' type='checkbox' /></td>";
					htmlStr += "<td name='name'><a style='text-decoration: none; cursor: pointer;' onclick='window.location.href=\"workbench/customer/detail/lookCustomerDetail.do?id="+obj.id+"\";'>"+obj.name+"</a></td>";
					htmlStr += "<td>"+(obj.owner==null?'':obj.owner)+"</td>";
					htmlStr += "<td>"+(obj.grade==null?'':obj.grade)+"</td>";
					htmlStr += "<td>"+(obj.phone==null?'':obj.phone)+"</td>";
					htmlStr += "<td>"+(obj.website==null?'':obj.website)+"</td>";
					htmlStr += "<td>"+(obj.industry==null?'':obj.industry)+"</td>";
					htmlStr += "<td>"+(obj.annualIncome==null?'':obj.annualIncome)+"</td>";
					htmlStr += "<td>"+(obj.empNums==null? '' :obj.empNums)+"</td>";
					htmlStr += "<td>"+(obj.createBy==null?'':obj.createBy)+"</td>";
					htmlStr += "<td>"+(obj.createTime==null ?'':obj.createTime)+"</td>";
					htmlStr += "<td>"+(obj.editBy==null?'':obj.editBy)+"</td>";
					htmlStr += "<td>"+(obj.editTime==null?'':obj.editTime)+"</td>";
					htmlStr += "<td>"+(obj.country==null?'':obj.country) + (obj.province==null?'':obj.province) + (obj.city==null?'':obj.city) + (obj.street==null?'':obj.street) +"</td>";
					htmlStr += "<td>"+(obj.description==null?'':obj.description)+"</td>";
					htmlStr += "</tr>";
				});
				$("#customerTbody").html(htmlStr);
				
				
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
				    
				    visiblePageLinks:5,//显示的卡片数
				    
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
	/* 列表显示 */
	
	//给"查询"按钮添加单击事件
		$("#queryCustomerBtn").click(function(){
			display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
		});
	
	
	/* //*******页面加载成功之后,显示首页数据 ******* ********** *****************************/
	display(1,5);
	/* //*******页面加载成功之后,显示首页数据******* ********** **************************** */
	
	//给"查询"按钮添加单击事件
		$("#queryCustomerBtn").click(function(){
			display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
		});
	
	/* 创建新客户  ==>打开模态窗口*/
	$("#createContactsBtn").click(function(){
		
		//打开 创建客户的模态窗口 清空表单
		$("#create-customerOwner").val("");
		$("#create-customerName").val("");
		$("#create-grade").val("");
		$("#create-phone").val("");
		$("#create-website").val("");
		$("#create-annualIncome").val("");
		$("#create-empnums").val("");
		$("#create-industry").val("");
		$("#create-description").val("");
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
				$("#create-customerOwner").html(htmlStr);
				//显示模态窗口
				$("#createClueModal").modal("show");
			}
		});
		
		//打开模态窗口
		$("#createCustomerModal").modal("show");
		
	});
	/* 创建新客户  ==>打开模态窗口*/
	
	
	
	/* 保存客户信息 发送请求 */
	$("#saveContactsBtn").click(function(){
		var customerOwner = $("#create-customerOwner").val();
		var customerName = $("#create-customerName").val();
		var grade = $("#create-grade").val();
		var phone = $("#create-phone").val();
		var website = $("#create-website").val();
		var annualIncome  = $("#create-annualIncome").val();
		var empnums = $("#create-empnums").val();
		var industry = $("#create-industry").val();
		var description = $("#create-description").val();
		var country = $("#create-country").val();
		var province = $("#create-province").val();
		var city = $("#create-city").val();
		var street = $("#create-street").val();
		var zipcode = $("#create-zipcode").val();
		
		//表单验证
		
		
		//发送请求
		$.ajax({
			url:"worbench/customer/SaveCustomer.do",
			type:"post",
			data:{
				customerOwner:customerOwner,
				customerName:customerName,
				grade:grade,
				phone:phone,
				website:website,
				annualIncome:annualIncome,
				empnums:empnums,
				industry:industry,
				description:description,
				country:country,
				province:province,
				city:city,
				street:street,
				zipcode:zipcode
			},
			
			success:function(data){
				if (data.success) {
					//创建成功 关闭模态窗口
					$("#createCustomerModal").modal("hide");
					//刷新列表
					display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
				}else {
					alert("创建失败");
					$("#createCustomerModal").modal("show");
				}
			}
		});
		
	});
	/* 保存客户信息 发送请求 */
	
	
	/* 修改 客户信息  */
	$("#editCustomerBtn").click(function(){
		
		if($("#customerTbody input[type='checkbox']:checked").size() != 1){
			alert("请选择修改一条市场活动");
			return;
		}
		$.ajax({
			url:"workbench/customer/editCustomer.do",
			data:{
				id:$("#customerTbody input[type='checkbox']:checked").val()
			},
			type:"post",
			success:function(data){
			
				if(data.success){
					//设置所有者
					var htmlStr="";
					$.each(data.userList,function(index,obj){
						if(obj.id==data.customer.owner){
							htmlStr+="<option value='"+obj.id+"' selected>"+obj.name+"</option>";
						}else{
							htmlStr+="<option value='"+obj.id+"'>"+obj.name+"</option>";
						}
					});
					$("#edit-customerOwner").html(htmlStr);
					
					//设置其他数据
					$("#edit-customerId").val(data.customer.id);
					$("#edit-customerName").val(data.customer.name);
					$("#edit-grade").val(data.customer.grade);
					$("#edit-phone").val(data.customer.phone);
					$("#edit-website").val(data.customer.website);
					$("#edit-annualIncome").val(data.customer.annualIncome);
					$("#edit-empnums").val(data.customer.empNums);
					$("#edit-industry").val(data.customer.industry);
					$("#edit-description").val(data.customer.description);
					$("#edit-country").val(data.customer.country);
					$("#edit-province").val(data.customer.province);
					$("#edit-city").val(data.customer.city);
					$("#edit-street").val(data.customer.street);
					$("#edit-zipcode").val(data.customer.zipcode); 

					//显示模态窗口
					$("#editCustomerModal").modal("show");
				}else {
					alert("获取信息失败");
					$("#editCustomerModal").modal("hide");
				}
			}
		});
		
	});
	/* 修改 客户信息  */
	
	
	/* 更新客户信息 */
	$("#updateCustomerBtn").click(function(){
		//获取表单
		
		var id =$("#edit-customerId").val();
		var owner =$("#edit-customerOwner").val();
		var name =$("#edit-customerName").val();
		var grade =$("#edit-grade").val();
		var phone =$("#edit-phone").val();
		var website =$("#edit-website").val();
		var annualIncome =$("#edit-annualIncome").val();
		var empNums =$("#edit-empnums").val();
		var industry =$("#edit-industry").val();
		var description =$("#edit-description").val();
		var country =$("#edit-country").val();
		var province =$("#edit-province").val();
		var city =$("#edit-city").val();
		var street =$("#edit-street").val();
		var zipcode =$("#edit-zipcode").val(); 
		
		
		//表单验证
		
		//发送请求
		$.ajax({
			
			url:"workbench/customer/updateCustomer.do",
			data:{
				id:id,
				owner:owner,
				name:name,
				grade:grade,
				phone:phone,
				website:website,
				annualIncome:annualIncome,
				empNums:empNums,
				industry:industry,
				description:description,
				country:country,
				province:province,
				city:city,
				street:street,
				zipcode:zipcode
			},
			type:"post",
			success:function(data){
				if (data.success) {
					$("#editCustomerModal").modal("hide");
					display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
				}else {
					alert("更新信息失败");
					$("#editCustomerModal").modal("show");
				}
				
			}
		});
		
		
	});
	/* 更新客户信息 */
	
})

</script>



</head>
<body>

	<!-- 创建客户的模态窗口 -->
	<div class="modal fade" id="createCustomerModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建客户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-customerOwner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-customerOwner">
								
									<!-- <option>zhangsan</option>
									<option>lisi</option>
									<option>wangwu</option> -->
								</select>
							</div>
							<label for="create-customerName" class="col-sm-2 control-label">名称<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-customerName">
							</div>
						</div>

						<div class="form-group">
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
							<label for="create-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone">
							</div>
						</div>

						<div class="form-group">

							<label for="create-website" class="col-sm-2 control-label">网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-website">
							</div>

							<label for="create-annualIncome" class="col-sm-2 control-label">年收入</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-annualIncome">
							</div>
						</div>

						<div class="form-group">
							<label for="create-empnums" class="col-sm-2 control-label">员工数</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-empnums">
							</div>
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
								<label for="create-country" class="col-sm-2 control-label">开票地址-国家/地区</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-country">
								</div>
								<label for="create-province" class="col-sm-2 control-label">开票地址-省/市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-province">
								</div>
							</div>

							<div class="form-group">
								<label for="create-city" class="col-sm-2 control-label">开票地址-城市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-city">
								</div>
								<label for="create-street" class="col-sm-2 control-label">开票地址-街道</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-street">
								</div>
							</div>

							<div class="form-group">
								<label for="create-zipcode" class="col-sm-2 control-label">开票地址-邮编</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="create-zipcode">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					
					<button type="button" class="btn btn-primary" id="saveContactsBtn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改客户的模态窗口 -->
	<div class="modal fade" id="editCustomerModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改客户</h4>
					<input type="hidden" id="edit-customerId">
					
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="edit-customerOwner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-customerOwner">
									<!-- <option>zhangsan</option>
									<option>lisi</option>
									<option>wangwu</option> -->
								</select>
							</div>
							<label for="edit-customerName" class="col-sm-2 control-label">名称<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-customerName" value="Administrator">
							</div>
						</div>

						<div class="form-group">
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
							<label for="edit-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone" value="010-84846003">
							</div>
						</div>

						<div class="form-group">

							<label for="edit-website" class="col-sm-2 control-label">网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-website" value="http://www.bjpowernode.com">
							</div>

							<label for="edit-annualIncome" class="col-sm-2 control-label">年收入</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-annualIncome" value="10,000,000">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-empnums" class="col-sm-2 control-label">员工数</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-empnums" value="100">
							</div>
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

						</div>



						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>

						<div
							style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div style="position: relative; top: 15px;">
							<div class="form-group">
								<label for="edit-country" class="col-sm-2 control-label">开票地址-国家/地区</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-country" value="中国">
								</div>
								<label for="edit-province" class="col-sm-2 control-label">开票地址-省/市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-province" value="北京市">
								</div>
							</div>

							<div class="form-group">
								<label for="edit-city" class="col-sm-2 control-label">开票地址-城市</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-city" value="北京市">
								</div>
								<label for="edit-street" class="col-sm-2 control-label">开票地址-街道</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-street" value="大兴区大族企业湾10号楼A座3层">
								</div>
							</div>

							<div class="form-group">
								<label for="edit-zipcode" class="col-sm-2 control-label">开票地址-邮编</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-zipcode" value="100176">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="updateCustomerBtn" type="button" class="btn btn-primary" >更新</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 导入客户的模态窗口 -->
	<div class="modal fade" id="importActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">导入客户</h4>
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
				<h3>客户列表</h3>
			</div>
		</div>
	</div>

	<div
		style="position: relative; top: -20px; left: 0px; width: 130%; height: 100%;">

		<div style="width: 130%; position: absolute; top: 5px; left: 10px;">

			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form"
					style="position: relative; top: 8%; left: 5px;">

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">名称</div>
							<input id="query-name" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">所有者</div>
							<input id="query-owner" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">电话</div>
							<input id="query-phone" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">网站</div>
							<input id="query-website" class="form-control" type="text">
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
				</form><br>
					<button id="queryCustomerBtn" type="submit" class="btn btn-default">查询</button>
			</div>
			<div class="btn-toolbar" role="toolbar"
				style="background-color: #F7F7F7; height: 50px; position: relative; top: 8px;">
				<div class="btn-group" style="position: relative; top: 18%;"> <button id="createContactsBtn" type="button" class="btn btn-primary" > <span class="glyphicon glyphicon-plus"></span> 创建</button>
					<button id="editCustomerBtn" type="button" class="btn btn-default" > <span class="glyphicon glyphicon-pencil"></span> 修改 </button>
					<button type="button" class="btn btn-danger"> <span class="glyphicon glyphicon-minus"></span> 删除 </button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal"> <span class="glyphicon glyphicon-import"></span> 导入
					</button>
					<button type="button" class="btn btn-default"> <span class="glyphicon glyphicon-export"></span> 导出
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
						<li><a href="javascript:void(0);"><input type="checkbox" />
								所有者</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								等级</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								电话</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								网站</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								行业</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								年收入</a></li>
						<li><a href="javascript:void(0);"><input type="checkbox" />
								员工数</a></li>
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
			<div style="position: relative; top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" /></td>
							<td>名称</td>
							<td>所有者</td>
							<td>等级</td>
							<td>电话</td>
							<td>网站</td>
							<td>行业</td>
							<td>年收入</td>
							<td>员工数</td>
							<td>创建者</td>
							<td>创建时间</td>
							<td>修改者</td>
							<td>修改时间</td>
							<td>地址</td>
							<td width="10%">描述</td>
						</tr>
					</thead>
					<tbody id="customerTbody">
						<!-- <tr>
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;"
								onclick="window.location.href='detail.html';">Administrator</a></td>
							<td>zhangsan</td>
							<td>已获得</td>
							<td>010-84846003</td>
							<td>http://www.bjpowernode.com</td>
							<td>中小企业</td>
							<td>10,000,000</td>
							<td>100</td>
							<td>zhangsan</td>
							<td>2017-01-18 10:10:10</td>
							<td>zhangsan</td>
							<td>2017-01-19 10:10:10</td>
							<td></td>
							<td>这是一条线索的描述信息 （线索转换之后会将线索的描述转换到客户的描述中）</td>
						</tr>
						<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;"
								onclick="window.location.href='detail.html';">Administrator</a></td>
							<td>zhangsan</td>
							<td>已获得</td>
							<td>010-84846003</td>
							<td>http://www.bjpowernode.com</td>
							<td>中小企业</td>
							<td>10,000,000</td>
							<td>100</td>
							<td>zhangsan</td>
							<td>2017-01-18 10:10:10</td>
							<td>zhangsan</td>
							<td>2017-01-19 10:10:10</td>
							<td></td>
							<td>这是一条线索的描述信息 （线索转换之后会将线索的描述转换到客户的描述中）</td>
						</tr> -->
					</tbody>
				</table>
			</div>
					<div id="pageNoDiv"></div>

		</div>

	</div>
</body>
</html>