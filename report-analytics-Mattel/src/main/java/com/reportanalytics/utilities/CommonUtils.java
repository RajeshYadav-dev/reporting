package com.reportanalytics.utilities;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

	public static boolean checkPathExists(String path, String pathConfig) throws Exception {
		if (null != path && null != pathConfig) {
			String[] paths = pathConfig.trim().split(";");
			for (int i = 0; i < paths.length; i++) {
				if (paths[i].equalsIgnoreCase(path))
					return true;
			}
		}
		return false;
	}

	public static String getValueFromMap(Map<String, Object> data, String key) {
		if (null != data && !data.isEmpty() && null != key) {
			return null != data.get(key) ? data.get(key).toString() : null;
		} else
			return null;
	}

	public static String getTokenFromCookie(HttpServletRequest request) {
		Cookie cookie[] = request.getCookies();

		if (null != cookie && cookie.length > 0) {

			for (int i = 0; i < cookie.length; i++) {
				if (cookie[i].getName().equalsIgnoreCase("token")) {
					return cookie[i].getValue();
				}
			}
		}

		return null;
	}
}
