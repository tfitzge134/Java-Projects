package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.models.ErsUser;
import com.revature.models.ErsUserRole;

public class ErsUserUtil {

	public static int getCurrentUserRoleId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ErsUser user = (ErsUser) session.getAttribute("current-user");
		if (user != null) {
			ErsUserRole role = (ErsUserRole) session.getAttribute("current-user-role");
			return role.getErsUserroleId();
		}
		return -1;
	}

}
