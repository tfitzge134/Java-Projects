package com.revature.daos;

import java.util.List;

import com.revature.models.ErsReimbursement;

public interface ReimbursementDAO {
	// employee can add reimbursement
	// TODAY
	public int addReimbursment(ErsReimbursement reimbursement);// throws BusinessException;

//	// Employee can view their resolved reimbursmentrequest
	//// TODAY
	public List<ErsReimbursement> viewResolved(int employeeId);// throws BusinessException;

	// employee can view information
	// TODAY
	public List<ErsReimbursement> getReimbursment();// throws BusinessException;
//manager can approve reimb
	// TODAY

	public int approveReimbursement(int reimbId);// throws BusinessException;
//manager can reject
	// TODAY

	public int rejectReimbursement(int reimbid);// throws BusinessException;

}
/*
 * An Employee can view their pending reimbursement requests An Employee can
 * view their resolved reimbursement requests An Employee can view their
 * information An Employee can update their information
 */
