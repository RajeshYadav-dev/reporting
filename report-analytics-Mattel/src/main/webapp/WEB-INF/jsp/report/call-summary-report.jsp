<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="../basic-page.jsp" />
    <title>Report | Call Summary | Dialog</title>
    <link rel="stylesheet" type="text/css" href="${assetPath}/assets/css/login/style.css" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" />
     <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="${assetPath}/assets/css/jquery.datetimepicker.min.css" />
   
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<!--       <script type="text/javascript">
        $(document).ready(function () {
            $('.datetimepicker3').datetimepicker({
                format: 'Y-m-d',
                timepicker: false,
                  
            });
		});
    </script>  -->  
    <style>
        html {
            background: #f9f9f9;
        }

        body {
            background: #f9f9f9;
        }
    </style>    
    <script type="text/javascript">

        $(document).ready(function () {

            $('.search-iocn-sungdesk, .tab_top_container-middle, .co_bro').click(function () {
             });

            $("body").click(function (e) {

            if (e.target.className == "toogle_left_btn") {
                $('.user_profile_tooltip').toggle();
               
            } else {
                $('.user_profile_tooltip').hide();
            }          
        });
            
            $('.cancel_btn_nt').click(function(){
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
            <h2>Report : Call Summary</h2>
        </div>
         <div class="top_right_add_area">
        		 <form id="form-export-report" name="excelForm" action="<%=application.getContextPath()%>/downloadLogCallReport?${queryString}" method="post">
         			<input type="submit" value="Download Report" class="add_btn">
           		</form>
         	</div>
        <div class="clear"></div>
    </div>
    <div class="content_start_area" style="padding-top:19px;">

        <table style="width:100%" class="table_grid" cellpadding="0" cellspacing="0">
            <tr>                
                <th style="width:12%">Call Time</th>
                <th style="width:10%">UCID</th>
                <th style="width:8%">ANI</th>
                <th style="width:6%">DNIS</th>
                <th style="width:12%">Call Duration</th>
                <th style="width:6%">Language</th>
                <th style="width:8%">Terminate Type</th>
                <th style="width:10%">Terminate Reason</th>
                <th style="width:2%;text-align: center;">&nbsp;View</th>
            </tr>  
            <c:if test="${empty list}">
            	<td colspan="11" style="width:3%;text-align: center;">No Result Found</a></td>
            </c:if>          
            <c:forEach var="logCall" items="${list}">
            
            <tr>
                <td><fmt:formatDate value="${ logCall.callStartTime }" pattern="dd-MM-yyy - HH:mm:ss" /></td>
                <td>${logCall.ucid }</td>
                <td>${logCall.ani }</td>
                <td>${logCall.dnis }</td>
                <td>${logCall.callDuration} ms</td>
                <td>${logCall.language}</td>
                <td>${logCall.terminateType}</td>
                <td>${logCall.terminateReason}</td>
                <td style="text-align: center;"><a href="<%=application.getContextPath()%>/calldetail?logCallId=${logCall.ucid}" title="View" class="view_st"><i class="fas fa-eye"></i></a></td>
            </tr>
            </c:forEach>
        </table>
        
        <div class="pagination__area">
       
		         	<c:if test="${not empty list}">
		      			 <ul class="pagination_right">
		      			<c:if test="${pgNo != 1}">
		      				<li><a href="<c:url value="/logCallReport?pgNo=${(pgNo-1)}&${queryString }"/>"><i class="fas fa-chevron-left"></i> Previous</a></li>
		      			</c:if>
		      			<c:if test="${pgNo!=pgCount}">
							<li><a href="<c:url value="logCallReport?pgNo=${(pgNo+1)}&${queryString }"/>">Next <i class="fas fa-chevron-right"></i></a></li>
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
			      			</c:choose>
		      			&nbsp;Of&nbsp;${total}
						</span>
						
						
		         	</c:if>
		         	
		         </div>
        
    </div>
</section> 
  <jsp:include page="../pgfooter.jsp"></jsp:include>
<script type="text/javascript" src="${assetPath}/assets/js/datetimepicker.js"></script>
<script type="text/javascript">
function downloadReport() {
	$("form-export-report").submit(function(){
	    alert("Submitted");
	});
}
</script>
</body>
</html>