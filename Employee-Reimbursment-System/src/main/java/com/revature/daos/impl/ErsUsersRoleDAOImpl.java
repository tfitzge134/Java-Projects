package com.revature.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.BusinessException;
import com.revature.daos.ErsUsersRoleDAO;
import com.revature.models.ErsUserRole;

import util.ConnectionUtil;

public class ErsUsersRoleDAOImpl implements ErsUsersRoleDAO {

	@Override
	public ErsUserRole getById(int id) {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_user_roles where ers_users_role_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				ErsUserRole role = new ErsUserRole();
				role.setErsUserroleId(rs.getInt("ers_users_role_id"));
				role.setErsUserrole(rs.getString("user_role"));
				return role;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}

	}

}
