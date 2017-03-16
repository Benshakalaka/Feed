package feed.web.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static final String X_REQUESTED_WIDTH = "X-Requested-With";
	public static final String XML_HTTP_REQUEST = "XMLHttpRequest";

	private RequestUtil() {
	}

	/**
	 * 判断请求是否是ajax请求
	 * 
	 * @paramrequest HttpServletRequest
	 * @return 是否ajax请求.
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String xr = request.getHeader(X_REQUESTED_WIDTH);
		return (xr != null && XML_HTTP_REQUEST.equalsIgnoreCase(xr));
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if (cookieName.equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
