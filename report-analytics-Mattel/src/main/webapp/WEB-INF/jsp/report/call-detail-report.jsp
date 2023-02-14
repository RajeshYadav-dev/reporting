<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<h2>Report : Call Detail Report</h2>
			</div>
			<div class="clear"></div>
		</div>
		<div class="content_start_area" style="padding-top: 19px;">
			<table style="width: 100%" class="table_grid" cellpadding="0"
				cellspacing="0">
				<tr>
					<td style="width: 1%">From *:</td>

					<td style="width: 1%"><input name="queryFrom" id="queryFrom"
						class="input_style datetimepicker3" autocomplete="off" value="">
					</td>
					<td style="width: 1%">To *:</td>

					<td style="width: 1%"><input name="queryTo" id="queryTo"
						class="input_style datetimepicker3" autocomplete="off" value="">
					</td>
					<td style="width: 1%"><input id="view-data" type="submit"
						value="View Report" class="add_btn"> <input
						id="report-download" type="submit" value="Download Report"
						class="add_btn"></td>

				</tr>
			</table>

			<table id="report-table" style="width: 100%" class="table_grid"
				cellpadding="0" cellspacing="0">

				<tr>
					<th style="width: 10%">UCID</th>
					<th style="width: 10%">ANI</th>
					<th style="width: 10%">Session Id</th>
					<th style="width: 10%">DNIS</th>
					<th style="width: 10%">Language</th>
					<th style="width: 10%">Call Start Time</th>
					<th style="width: 10%">Call End Time</th>
					<th style="width: 10%">Call Duration</th>
				    <th style="width: 10%">Terminate Reason</th>
					<th style="width: 10%">Terminate Type</th>
				</tr>
				<c:if test="${empty list}">
					<td id="no-result" colspan="100"
						style="width: 3%; text-align: center;">No Result Found</a>
					</td>
				</c:if>
				<c:forEach var="item" items="${list}">

					<tr class="table-dynamic-data">

						<td>${item.ucid}</td>
						<td>${item.ani}</td>
						<td>${item.sessionId}</td>
						<td>${item.dnis}</td>
						<td>${item.language}</td>
						<td>${item.callStartTime}</td>
						<td>${item.callEndTime}</td>
						<td>${item.callDuration}</td>
						<td>${item.terminateReason}</td>
						<td>${item.terminateType}</td>
					</tr>
				</c:forEach>
			</table>

			<%-- <div class="pagination__area">

				<c:if test="${not empty list}">
					<ul class="pagination_right">
						<c:if test="${pgNo != 1}">
							<li><a
								href="<c:url value="/logCallReport?pgNo=${(pgNo-1)}&${queryString }"/>"><i
									class="fas fa-chevron-left"></i> Previous</a></li>
						</c:if>
						<c:if test="${pgNo!=pgCount}">
							<li><a
								href="<c:url value="logCallReport?pgNo=${(pgNo+1)}&${queryString }"/>">Next
									<i class="fas fa-chevron-right"></i>
							</a></li>
						</c:if>

					</ul>

					<span class="showing_st">Showing&nbsp;${((pgNo-1)*maxRows) + 1}&nbsp;-&nbsp;
						<c:choose>
							<c:when test="${(pgNo==pgCount) && ((pgNo)*maxRows)>total}">
				      				${total}
				      			</c:when>
							<c:otherwise>
				      				${pgNo*maxRows}
				      			</c:otherwise>
						</c:choose> &nbsp;Of&nbsp;${total}
					</span>


				</c:if>

			</div> --%>

		</div>
	</section>
	<jsp:include page="../pgfooter.jsp"></jsp:include>
	<script type="text/javascript"
		src="${assetPath}/assets/js/datetimepicker.js"></script>
	<script type="text/javascript">
		function downloadReport() {
			$("form-export-report").submit(function() {
				alert("Submitted");
			});
		}
	</script>
	<script
		src="${assetPath}/assets/js/reports/call-detail-report/call-detail-report.js"></script>
</body>
</html>