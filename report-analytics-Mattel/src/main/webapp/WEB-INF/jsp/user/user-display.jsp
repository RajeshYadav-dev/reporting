<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="../basic-page.jsp" />
<title>Call Detail Report | ${orgName}</title>
<link rel="stylesheet" type="text/css"
	href="${assetPath}/assets/css/login/style.css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<link type="text/css" rel="stylesheet"
	href="${assetPath}/assets/css/jquery.datetimepicker.min.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>

html {
	background: #f9f9f9;
}

body {
	background: #f9f9f9;
}

</style>
<script type="text/javascript">
	$(document).ready(
			function() {

				$('.search-iocn-sungdesk, .tab_top_container-middle, .co_bro')
						.click(function() {
						});

				$("body").click(function(e) {

					if (e.target.className == "toogle_left_btn") {
						$('.user_profile_tooltip').toggle();

					} else {
						$('.user_profile_tooltip').hide();
					}
				});

				$('.cancel_btn_nt').click(function() {
					$('.closingnote').remove();

				});
			});
</script>
</head>
<body>
	<jsp:include page="../pgheader.jsp" />
	<section class="middle_content_area_auto" id="hld">
		<div class="content_header">
			<div class="content_heading_left">
				<h2>Users</h2>
			</div>
			<div class="clear"></div>
		</div>
		<div class="content_start_area" style="padding-top: 19px;">
			<table style="width: 100%" class="table_grid" cellpadding="0"
				cellspacing="0">
				<tr>
					<td style="width: 1%"><a href="${assetPath}/user/create"><input
							type="submit" value="Create User" class="add_btn"></a></td>

				</tr>
			</table>

			<table id="report-table" style="width: 100%" class="table_grid"
				cellpadding="0" cellspacing="0">

				<tr>
					<th style="width: 10%">User Name</th>
					<th style="width: 10%">Login Id</th>
					<th style="width: 10%">Contact</th>
					<th style="width: 5%">Gender</th>
					<th style="width: 10%">Employee Id</th>
					<th style="width: 10%">Org Email</th>
					<th style="width: 5%">Role</th>
					<th style="width: 5%">Is Active</th>
					<th style="width: 5%">Is Locked</th>
					<th style="width: 10%">Created On</th>
					<th style="width: 5%"></th>



				</tr>
				<c:if test="${empty userList}">
					<td id="no-result" colspan="100"
						style="width: 3%; text-align: center;">No Result Found</a>
					</td>
				</c:if>
				<c:forEach var="user" items="${userList}">

					<c:if test = "${!user.deleted}">
						<tr class="table-dynamic-data user-row-${user.userId}">
							<td>${user.userName}</td>
							<td>${user.loginId}</td>
							<td>${user.contact}</td>
							<td>${user.gender}</td>
							<td>${user.empId}</td>
							<td>${user.orgEmailId}</td>
							<td>${user.role.name}</td>
							<td>${user.active}</td>
							<td>${user.locked}</td>
							<td><fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value = "${user.createdOn}" /></td>
							<td> <c:if test = "${!fn:containsIgnoreCase(user.role.name, 'admin')}"><a href="${assetPath}/user/edit?userId=${user.userId}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>  &nbsp; &nbsp; &nbsp; <i style="cursor: pointer" onClick="deleteUserAlert(${user.userId})" class="fa fa-trash" aria-hidden="true"></i></c:if></td>
	
	
						</tr>
					</c:if>
				</c:forEach>
			</table>


		</div>
	</section>
	<input type="hidden" id="base-path" value="${apiBasePath}">
	<jsp:include page="../pgfooter.jsp"></jsp:include>
	<script type="text/javascript" src="${assetPath}/assets/js/datetimepicker.js"></script>
	<script type="text/javascript">
		function downloadReport() {
			$("form-export-report").submit(function() {
				alert("Submitted");
			});
		}
	</script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="${assetPath}/assets/js/users/user-display.js"></script>
</body>
</html>