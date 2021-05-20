package com.revature.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.BusinessException;
import com.revature.Constants;
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
				+ "reimb_author, reimb_status_id, reimb_type_id, reimb_description) VALUES(?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, reimb.getReimbAmount());
			ps.setDate(2, reimb.getReimbSubmitted());
			ps.setInt(3, reimb.getReimbAuthor());
			ps.setInt(4, reimb.getReimbStatusId());
			ps.setInt(5, reimb.getReimbTypeId());
			ps.setString(6, reimb.getReimbDescription());

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

	@Override
	public List<ErsReimbursement> getAllPending() throws BusinessException {
		List<ErsReimbursement> list = new ArrayList<ErsReimbursement>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_reimbursement where reimb_status_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Constants.PENDING_STATUS_ID);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ErsReimbursement reimb = new ErsReimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setReimbTypeId(rs.getInt("reimb_type_id"));
				reimb.setReimbAmount(rs.getDouble("reimb_amount"));
				reimb.setReimbSubmitted(rs.getDate("reimb_submitted"));
				reimb.setReimbResolved(rs.getDate("reimb_resolved"));
				reimb.setReimbAuthor(rs.getInt("reimb_author"));
				reimb.setReimbResolver(rs.getInt("reimb_resolver"));
				reimb.setReimbStatusId(rs.getInt("reimb_status_id"));
				reimb.setReimbDescription(rs.getString("reimb_description"));
				list.add(reimb);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ErsReimbursement> viewResolved(int employeeId) throws BusinessException {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public List<ErsReimbursement> getReimbursement() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveReimbursement(int reimbId) throws BusinessException {
		String sql = "UPDATE ers.ers_reimbursement SET reimb_status_id = ? " + " WHERE reimb_id = ?";

		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Constants.APPROVED_STATUS_ID);
			ps.setInt(2, reimbId);

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
	public List<ErsReimbursement> getByUserId(int userId) throws BusinessException {
		List<ErsReimbursement> list = new ArrayList<ErsReimbursement>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_reimbursement where reimb_author = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ErsReimbursement reimb = new ErsReimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setReimbTypeId(rs.getInt("reimb_type_id"));
				reimb.setReimbAmount(rs.getDouble("reimb_amount"));
				reimb.setReimbSubmitted(rs.getDate("reimb_submitted"));
				reimb.setReimbResolved(rs.getDate("reimb_resolved"));
				reimb.setReimbAuthor(rs.getInt("reimb_author"));
				reimb.setReimbResolver(rs.getInt("reimb_resolver"));
				reimb.setReimbStatusId(rs.getInt("reimb_status_id"));
				reimb.setReimbDescription(rs.getString("reimb_description"));
				list.add(reimb);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public boolean rejectReimbursement(int reimbId) throws BusinessException {
		String sql = "UPDATE ers.ers_reimbursement SET reimb_status_id = ? " + " WHERE reimb_id = ?";

		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Constants.REJECTED_STATUS_ID);
			ps.setInt(2, reimbId);

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
	public List<ErsReimbursement> getAllResolved() throws BusinessException {
		List<ErsReimbursement> list = new ArrayList<ErsReimbursement>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_reimbursement where reimb_status_id <> ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Constants.PENDING_STATUS_ID);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ErsReimbursement reimb = new ErsReimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setReimbTypeId(rs.getInt("reimb_type_id"));
				reimb.setReimbAmount(rs.getDouble("reimb_amount"));
				reimb.setReimbSubmitted(rs.getDate("reimb_submitted"));
				reimb.setReimbResolved(rs.getDate("reimb_resolved"));
				reimb.setReimbAuthor(rs.getInt("reimb_author"));
				reimb.setReimbResolver(rs.getInt("reimb_resolver"));
				reimb.setReimbStatusId(rs.getInt("reimb_status_id"));
				reimb.setReimbDescription(rs.getString("reimb_description"));
				list.add(reimb);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ErsReimbursement> getAllResolvedForUser(int userId) throws BusinessException {
		List<ErsReimbursement> list = new ArrayList<ErsReimbursement>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_reimbursement WHERE" + " reimb_author = ? AND reimb_status_id <> ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, Constants.PENDING_STATUS_ID);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ErsReimbursement reimb = new ErsReimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setReimbTypeId(rs.getInt("reimb_type_id"));
				reimb.setReimbAmount(rs.getDouble("reimb_amount"));
				reimb.setReimbSubmitted(rs.getDate("reimb_submitted"));
				reimb.setReimbResolved(rs.getDate("reimb_resolved"));
				reimb.setReimbAuthor(rs.getInt("reimb_author"));
				reimb.setReimbResolver(rs.getInt("reimb_resolver"));
				reimb.setReimbStatusId(rs.getInt("reimb_status_id"));
				reimb.setReimbDescription(rs.getString("reimb_description"));
				list.add(reimb);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ErsReimbursement> getAllPendingForUser(int userId) throws BusinessException {
		List<ErsReimbursement> list = new ArrayList<ErsReimbursement>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select * from ers.ers_reimbursement WHERE" + " reimb_author = ? AND reimb_status_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, Constants.PENDING_STATUS_ID);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ErsReimbursement reimb = new ErsReimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setReimbTypeId(rs.getInt("reimb_type_id"));
				reimb.setReimbAmount(rs.getDouble("reimb_amount"));
				reimb.setReimbSubmitted(rs.getDate("reimb_submitted"));
				reimb.setReimbResolved(rs.getDate("reimb_resolved"));
				reimb.setReimbAuthor(rs.getInt("reimb_author"));
				reimb.setReimbResolver(rs.getInt("reimb_resolver"));
				reimb.setReimbStatusId(rs.getInt("reimb_status_id"));
				reimb.setReimbDescription(rs.getString("reimb_description"));
				list.add(reimb);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
