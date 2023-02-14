<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="../basic-page.jsp" />
<title>Dashboard | ${orgName}</title>
<link rel="stylesheet" type="text/css"
	href="${assetPath}/assets/css/login/style.css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet" />
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
			});
</script>
</head>
<body>
	<jsp:include page="../pgheader.jsp" />
	<section class="middle_content_area_auto" style="height: 80vh;">
		<div class="dashboard_bg_area">
			<h1>
				Welcome to <b style="text-transform: uppercase;">${orgName} IVR</b>
				Report
			</h1>
			<br /> <span class="sht_h_st">Get started by opening a
				report.</span>
		</div>
	</section>
	<jsp:include page="../pgfooter.jsp" />
</body>
</html>