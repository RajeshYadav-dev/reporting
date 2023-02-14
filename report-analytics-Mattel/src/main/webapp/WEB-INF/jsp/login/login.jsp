<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../basic-page.jsp" />
<title>Log in | ${orgName}</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">


<link rel="stylesheet" type="text/css"
	href="${assetPath}/assets/css/login/style.css" />


</head>
<body>

	<div class="company_logo">
		<img
			src="${assetPath}/assets/images/Mattel_LoginPageLogo.png"
			alt="${orgName}" />
	</div>
	<div class="input_form" id="login-box">
		<h1>
			<span style="font-weight: 600">REPORTING</span>
		</h1>
		<div class="message success nt_message_error closingnote error"
			id="errorDiv">
			<span></span> <a class="cancel_btn_nt" onclick="closeError()"><i
				class="fas fa-times"></i></a>
		</div>

		<form id="user-login" name='loginForm' class="helpdesk_domain"
			onsubmit="return false">
			<div class='input-append'>
				<input id="username" class="input-subdomain_form"
					placeholder='User Name*' type='text' name='username' required /> <input
					id="password" class="input-subdomain_form margin-t"
					placeholder='Password*' type='password' name='password' required />
			</div>
			<input name='submit' type='submit' class="btn_big" value='Log In' />
		</form>
	</div>
	<jsp:include page="../pgfooter.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js">
		
	</script>
	<script src="${assetPath}/assets/js/common/common.js"></script>
	<script src="${assetPath}/assets/js/login/login.js"></script>


</body>
</html>