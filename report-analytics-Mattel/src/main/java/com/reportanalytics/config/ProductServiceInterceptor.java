package com.reportanalytics.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.reportanalytics.entities.Users;
import com.reportanalytics.services.SessionService;
import com.reportanalytics.services.UserService;
import com.reportanalytics.utilities.CommonUtils;

@SuppressWarnings("deprecation")
@Component
public class ProductServiceInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	SessionService sessionService;

	@Autowired
	UserService userService;

	@Value("${app.page.under.auth}")
	String underAuthPaths;

	@Value("${org.name}")
	String orgName;

	@Value("${api.base.path}")
	String apiBasePath;
	
	@Value("${app.entry.path}")
	String appEntryPath;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = null;
		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (CommonUtils.checkPathExists(path, underAuthPaths)) {

			token = CommonUtils.getTokenFromCookie(request);

			synchronized (this) {
				Map<String, Object> authUser = sessionService.validateUserCookieToken(token);

				if ((boolean) authUser.get("auth")) {

					Users user = userService.getUserByUserId(Long.parseLong(authUser.get("userId").toString()));

					if (!(boolean) authUser.get("isActive"))
						response.sendRedirect(setCompletePath("/login"));
					
					if ((boolean) authUser.get("isLocked"))
						response.sendRedirect(setCompletePath("/login"));
					
					
					request.setAttribute("userId", user.getUserId());
					request.setAttribute("userName", user.getUserName());
					request.setAttribute("role", authUser.get("role"));
					request.setAttribute("isPasswordChanged", authUser.get("isPasswordChanged"));

					if (!(boolean) authUser.get("isPasswordChanged"))
						response.sendRedirect(setCompletePath("/user/set-password"));

					if (path.equalsIgnoreCase("/login"))
						response.sendRedirect(setCompletePath("/dashboard"));

					if (path.equalsIgnoreCase("/user/create") || path.equalsIgnoreCase("/user")) {
						if (authUser.get("role").toString().equalsIgnoreCase("admin"))
							return true;
						else
							response.sendRedirect(setCompletePath("/page-not-found"));

					}
				} else if (path.equalsIgnoreCase("/login"))
					return true;
				else
					response.sendRedirect(setCompletePath("/login"));
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null != modelAndView) {
			modelAndView.getModel().put("assetPath", apiBasePath);
			modelAndView.getModel().put("apiBasePath", apiBasePath+"/");
			String role = null != request.getAttribute("role") ? (String) request.getAttribute("role") : null;
			Object isPasswordChanged = request.getAttribute("isPasswordChanged");
			Object userId = null != request.getAttribute("userId") ? request.getAttribute("userId").toString() : null;
			Object userName = null != request.getAttribute("userName") ? request.getAttribute("userName").toString()
					: null;
			boolean isPasswordChangedBool = false;
			if (null != isPasswordChanged)
				isPasswordChangedBool = (boolean) isPasswordChanged;

			modelAndView.getModel().put("role", role);
			modelAndView.getModel().put("orgName", orgName);
			modelAndView.getModel().put("isPasswordChanged", isPasswordChangedBool);
			modelAndView.getModel().put("userId", userId);
			modelAndView.getModel().put("userName", userName);
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}

	private String setCompletePath(String path) {
		if(appEntryPath.equalsIgnoreCase("/"))
			return path;
		else
			return appEntryPath+path;
	}
}
