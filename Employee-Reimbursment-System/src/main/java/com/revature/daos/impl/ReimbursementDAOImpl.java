package com.revature.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.BusinessException;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.ErsReimbursement;

import util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public int addReimbursment(ErsReimbursement reimbursement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ErsReimbursement> viewResolved(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ErsReimbursement> getReimbursment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int approveReimbursement(int reimbId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rejectReimbursement(int reimbid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ErsReimbursement> getAllReimbursments(int statusId) throws BusinessException {
		List<ErsReimbursement> list = new ArrayList<ErsReimbursement>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_reimbursement where reimb_status_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, statusId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ErsReimbursement reimb = new ErsReimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setReimbAmount(rs.getDouble("reimb_amount"));
				reimb.setReimbSubmitted(rs.getDate("reimb_submitted"));
				reimb.setReimbResolved(rs.getDate("reimb_resolved"));
				reimb.setReimbAuthor(rs.getInt("reimb_author"));
				reimb.setReimbResolver(rs.getInt("reimb_resolver"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
