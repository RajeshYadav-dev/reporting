<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
    <div class="hdrl"></div>
    <div class="hdrr"></div>
		<h1><a href="">Admin</a></h1>
		<h1><a href="">User</a></h1>
    
    <ul id="nav">
    	<li class='active'>
    		<a href="<%=application.getContextPath()%>/dashboard">Dashboard</a>
    	</li>
    	
    	<li >
    		<a href="<%=application.getContextPath()%>/ivrCalls">IVR Calls</a>
    	</li>  
    	
		<li ><a href="#">Settings</a>
        	<ul>
        		<li><a href="<%=application.getContextPath()%>/promotionalMessage">Promotional Message</a>
                <!--  <li><a href="">User Accounts</a> -->
            </ul>
        </li>
		
        <li ><a href="#">Reports</a>
	        <ul>
	        	<li><a href="<%=application.getContextPath()%>/callSummaryReport">Call Summary</a></li>
	        	<li><a href="<%=application.getContextPath()%>/backendReport">Backend Summary</a></li>
	        	<li><a href="<%=application.getContextPath()%>/languageReport">Language Summary</a></li>
	        	<li><a href="<%=application.getContextPath()%>/selfServiceReport">Self-Service Summary</a></li>
	        	<li><a href="<%=application.getContextPath()%>/menuUtilReport">Menu Utilization Summary</a></li>
	        	<li><a href="<%=application.getContextPath()%>/terminateTypeReport">Terminate Type Summary</a></li>
	        	<li><a href="<%=application.getContextPath()%>/terminateReasonReport">Terminate Reason Summary</a></li>
	        	     
	        </ul>
        </li>
    </ul>	
    <p class="user">${principal.username} | <a href="<%=application.getContextPath()  + "/logout" %>">Logout</a></p>
</div>