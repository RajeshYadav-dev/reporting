<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<header class="header_dialog_nt">
	<c:if test="${isPasswordChanged}">
		<div class="left_dialog_logo_nt" style="padding: 15px 0px;">
			<a href="${assetPath}/dashboard" title="${orgName}"><img
				src="${assetPath}/assets/images/Mattel_LoginPageLogo.png" width="100" height="40"></a>
		</div>
		<div class="left_main_nav_dialog">
			<ul class="nav_dialog">
				<li class="st_it"><a href="${assetPath}/dashboard"
					class="top_nav_dashboard" title="Dashboard"> <i
						class="fa fa-home"></i> Home
				</a></li>
				<li class="st_it">
					<div class="top_nav_a"><i class="fa fa-file" aria-hidden="true"></i> &nbsp;Reports <i class="fa fa-caret-down"></i>
						<div class="sub_item_st">
							<div class="sub_item_bg">
								<ul>
								 <!--	<li><a href="${assetPath}/report/ivr-survey"><div class="subitem_st">
												<i class="fa fa-file" aria-hidden="true"></i> &nbsp; IVR
												Survey Report
											</div></a></li>-->
											
												<li><a href="${assetPath}/report/call-detail"><div class="subitem_st">
												<i class="fa fa-file" aria-hidden="true"></i> &nbsp; Call
												Detail Report
											</div></a></li>	
				
							</ul>													
						</div>
						</div>
					</div>
				</li>

				<c:if test="${role == 'admin'}">
					<li class="st_it"><a style="text-decoration: none;"
						href="${assetPath}/user"><div class="top_nav_a"><span><i class="fa fa-users" aria-hidden="true"></i></span>&nbsp;Users</i></div></a></li>
				</c:if>
			</ul>
		</div>
		<div class="right-container-home">
			<ul class="user-nav-home">
				<li class="user_dialog">
					<div class="click_top_user_t">
						<div class="log_out_i">
							<button id="logoutBtn" onClick="logoutUser()">&nbsp;Log
								Out</button>
						</div>
					</div> <!-- <div class="user_profile_tooltip">
						<div class="bdr_shadow_content">
							<div class="top_sht_menu">
								<a href="#">My Profile</a>
								<div class="log_out_i">
									<button id="logoutBtn" onClick="logoutUser()">&nbsp;Log
										Out</button>
								</div>
							</div>
						</div>
					</div> -->
				</li>
			</ul>
		</div>
		<div class="clear"></div>
	</c:if>
</header>
<input type="hidden" id="base-path" value="${apiBasePath}">
<script src="${assetPath}/assets/js/common/common.js"></script>