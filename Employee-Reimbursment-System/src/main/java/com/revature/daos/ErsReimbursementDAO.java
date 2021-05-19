package com.revature.daos;

import java.util.List;

import com.revature.BusinessException;
import com.revature.models.ErsReimbursement;

public interface ErsReimbursementDAO {
	public boolean add(ErsReimbursement reimb) throws BusinessException;

	public List<ErsReimbursement> getAll() throws BusinessException;

//	// Employee can view their resolved Reimbursementrequest
	public List<ErsReimbursement> viewResolved(int employeeId) throws BusinessException;

	// employee can view information
	public List<ErsReimbursement> getReimbursement() throws BusinessException;

//manager can approve reimb
	public boolean approveReimbursement(int reimbId) throws BusinessException;

//manager can reject
	public boolean rejectReimbursement(int reimbId) throws BusinessException;

	public List<ErsReimbursement> getAllPending() throws BusinessException;

	public List<ErsReimbursement> getAllResolved() throws BusinessException;

	public List<ErsReimbursement> getByUserId(int userId) throws BusinessException;

	public List<ErsReimbursement> getAllResolvedForUser(int userId) throws BusinessException;

	public List<ErsReimbursement> getAllPendingForUser(int userId) throws BusinessException;

}
/*
 * An Employee can view their pending reimbursement requests An Employee can
 * view their resolved reimbursement requests An Employee can view their
 * information An Employee can update their information
 */
