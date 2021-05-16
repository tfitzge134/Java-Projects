package com.revature.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.BusinessException;
import com.revature.daos.ErsUserDAO;
import com.revature.models.ErsUser;

import util.ConnectionUtil;

public class ErsUserDAOImpl implements ErsUserDAO {

	@Override
	public boolean addUser(ErsUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailInUse(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ErsUser verifyPassword(String username, String password) throws BusinessException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_users where ers_username = ? and ers_password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				ErsUser user = new ErsUser();
				user.setErsUserId(rs.getInt("ers_users_id"));
				user.setUserFirstname(rs.getString("user_first_name"));
				user.setUserLastname(rs.getString("user_last_name"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserRoleId(rs.getInt("user_role_id"));
				user.setErsUsername(rs.getString("ers_username"));
				return user;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public ErsUser getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

//	public static void main(String[] args) throws SQLException {
//
//		ErsUserDAO dao = new ErsUserDAOImpl();
//		try {
//			ErsUser user = dao.verifyPassword("joe", "abc");
//			if (user == null) {
//				System.out.print("Invalid login credentials.<br>");
//			} else {
//				System.out.print("Login Success<br>");
//				System.out.print(user.toString());
//			}
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.print("Error occurred: " + e.getMessage());
//		}
//	}
}
