<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!doctype html>
<html lang="en">
<head>
<jsp:include page="../basic-page.jsp" />
<title>${heading}</title>
<link rel="stylesheet" type="text/css"
	href="${assetPath}/assets/css/login/style.css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
			});
</script>
</head>
<body>
	<jsp:include page="../pgheader.jsp" />
	<section class="middle_content_area_auto">


		<div>
			<div class="input_form" id="login-box">
				<h1>
					<span style="font-weight: 600">${heading}</span> 
				</h1>
				<div class="message success nt_message_error closingnote error"
					id="errorDiv" style="display: none;">
					<span></span> <a class="cancel_btn_nt" onclick="closeError()"><i
						class="fas fa-times"></i></a>
				</div>

				<form id="user-create" name="loginForm" class="helpdesk_domain"
					onsubmit="return false" autocomplete="off">
					<div class="input-append">
					
					<div class="inpu_label_form"><label for="username">User Name : </label>
						<input id="username" class="input-subdomain_form margin-t" value="${user.userName}"
							placeholder="User Name*" type="text" name="username" required="true" autocomplete="off">
					</div>
						
					<div class="inpu_label_form"><label for="login-id">Login Id : </label>
						
						<input id="login-id" class="input-subdomain_form margin-t"
							placeholder="Login id*" type="text" name="login-id"
							required="true" value="${user.loginId}">
					</div>
					
					<div class="inpu_label_form"><label for="org-email-id">Organization Email Id : </label>
					
						<input id="org-email-id" class="input-subdomain_form margin-t"
							placeholder="Org Email Id*" type="text" name="org-email-id"
							required="true" value="${user.orgEmailId}">
				</div>			
				
					<div class="inpu_label_form"><label for="emp-id">Employee Id : </label>
									
						<input id="emp-id" class="input-subdomain_form margin-t"
							placeholder="Employee Id" type="text" name="emp-id"
							required="false" value="${user.empId}">
						</div>	
							
					<div class="inpu_label_form"><label for="contact">Contact : </label>
														
						<input id="contact" class="input-subdomain_form margin-t"
							placeholder="Contact Number" type="number" name="contact"
							required="false" value="${user.contact}">
							</div>
						
					<div class="inpu_label_form"><label for="gender">Gender : </label>
														
						<select class="input_style margin-t" name="gender" id="gender" >
							<option value="Male" <c:if test = "${fn:containsIgnoreCase(user.gender, 'male')}">selected</c:if> >Male</option>
							<option value="Female" <c:if test = "${fn:containsIgnoreCase(user.gender, 'female')}">selected</c:if> >Female</option>
							<option value="others" <c:if test = "${fn:containsIgnoreCase(user.gender, 'others')}">selected</c:if> >others</option>
		       			</select>
					</div>	
						
					<div class="inpu_label_form"><label for="password">Password : </label>
														
						<input id="password" class="input-subdomain_form margin-t"
							placeholder="Password" type="password" name="password"
							required="true" autocomplete="off" <c:if test = "${!empty user.password}">value="${user.password}</c:if> ">
					</div>		
					
					<div class="inpu_label_form"><label for="role">Role : </label>
														
						<select class="input_style margin-t" name="role" id="role" >
							<c:forEach var="role" items="${roles}">
								<option value="${role.roleId}" <c:if test = "${fn:containsIgnoreCase(user.role.roleId, role.roleId)}">selected</c:if> >${role.name}</option>
							</c:forEach>
		       			</select>
		       		</div>
						
						
						<c:if test = "${type == 'edit'}">
							<div class="inpu_label_form"><label for="active">Account Active Status :</label>
								<select class="input_style margin-t" name="active" id="active" >
									<option value="1" <c:if test = "${user.active}">selected</c:if> >Active</option>
									<option value="0" <c:if test = "${!user.active}">selected</c:if> >Inactive</option>
				       			</select>
							</div>
							
							<div class="inpu_label_form"><label for="locked">Account Lock Status :</label>
							<select class="input_style margin-t" name="locked" id="locked" >
								<option value="1" <c:if test = "${user.locked}">selected</c:if> >Locked</option>
								<option value="0" <c:if test = "${!user.locked}">selected</c:if> >Unlocked</option>
			       			</select></div>
						</c:if>
							
							
							
							
					</div>
					<input name="submit" type="submit" class="btn_big" value="${heading}">
					
					<input id="userId" type="hidden"  value="${user.userId}">
				</form>
			</div>
		</div>
	</section>
	<jsp:include page="../pgfooter.jsp" />
</body>
</html>
<script
	src="${assetPath}/assets/js/users/upsert-user.js"></script>
