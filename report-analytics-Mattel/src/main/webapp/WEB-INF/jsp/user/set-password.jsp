<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="../basic-page.jsp" />
<title>Set Password</title>
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
					<span style="font-weight: 600">Create New Password</span>
				</h1>
				<div class="message success nt_message_error closingnote error"
					id="errorDiv" style="display: none;">
					<span></span> <a class="cancel_btn_nt" onclick="closeError()"><i
						class="fas fa-times"></i></a>
				</div>

				<form id="set-password" name="loginForm" class="helpdesk_domain"
					onsubmit="return false">
					<div class="input-append">
						<input id="password" class="input-subdomain_form"
							placeholder="Enter Password*" type="password" name="password"
							required="true"> <input id="re-password"
							class="input-subdomain_form margin-t"
							placeholder="Confirm Password*" type="password" name="re-password"
							required="true">

					</div>
					<input name="submit" type="submit" class="btn_big"
						value="Create Password">
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="../pgfooter.jsp" />
</html>
<script src="${assetPath}/assets/js/users/set-password.js"></script>
