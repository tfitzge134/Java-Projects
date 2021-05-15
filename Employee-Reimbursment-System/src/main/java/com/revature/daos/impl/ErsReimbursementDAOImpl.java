package com.revature.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.revature.BusinessException;
import com.revature.daos.ErsReimbursementDAO;
import com.revature.models.ErsReimbursement;

import util.ConnectionUtil;

public class ErsReimbursementDAOImpl implements ErsReimbursementDAO {

	@Override
	public boolean add(ErsReimbursement reimb) throws BusinessException {

		// you have to insert in tbs that have a key dependency(fk)
//		String sql = "insert into ers.ers_reimbursement_type"
//				+ "(reimb_, reimb_type) values(?,?)";
//		String sql1 = "insert into ers.reimb_status(reimb_status_id, reimb_status)values(?,?)";
//		String sql2 = "insert into ers.ers_users_roles("
//				+ "ers_users_role_id, user_role)values(?,?)";
		String sql = "INSERT INTO ers.ers_reimbursement(reimb_amount, reimb_submitted,"
				+ "reimb_author, reimb_status_id, reimb_type_id) VALUES(?, ?, ?, ?, ?)";

		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, reimb.getReimbAmount());
			ps.setDate(2, reimb.getReimbSubmitted());
			ps.setInt(3, reimb.getReimbAuthor());
			ps.setInt(4, reimb.getReimbStatusId());
			ps.setInt(5, reimb.getReimbTypeId());

			int count = ps.executeUpdate();
			if (count == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	public List<ErsReimbursement> getAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
