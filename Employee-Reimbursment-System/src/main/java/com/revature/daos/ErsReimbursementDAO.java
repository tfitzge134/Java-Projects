package com.revature.daos;

import java.util.List;

import com.revature.BusinessException;
import com.revature.models.ErsReimbursement;

public interface ErsReimbursementDAO {
	public boolean add(ErsReimbursement reimb) throws BusinessException;

	public List<ErsReimbursement> getAll() throws BusinessException;

//	// Employee can view their resolved reimbursmentrequest
	public List<ErsReimbursement> viewResolved(int employeeId) throws BusinessException;

	// employee can view information
	public List<ErsReimbursement> getReimbursment() throws BusinessException;

//manager can approve reimb
	public boolean approveReimbursement(int reimbId) throws BusinessException;
//manager can reject

	public int rejectReimbursement(int reimbid) throws BusinessException;

	public List<ErsReimbursement> getAllByStatus(int statusId) throws BusinessException;

}
/*
 * An Employee can view their pending reimbursement requests An Employee can
 * view their resolved reimbursement requests An Employee can view their
 * information An Employee can update their information
 */
