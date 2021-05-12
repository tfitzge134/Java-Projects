package com.revature.daos;

import java.util.List;

import com.revature.models.ErsReimbursement;


public interface ReimbursementDAO {
	// employee can add reimbursement
	public int updateReimbursment(ErsReimbursement reimbursement);// throws BusinessException;

//	// Employee can view their resolved reimbursmentrequest
	public int viewResolved(int reimbId);//throws BusinessException;

	// employee  can update a reimbursement 
	
	public int updateReimbursement(int reimbId, double amount);// throws BusinessException;

	// employee can view information
	public List<ErsReimbursement> getReimbursment();// throws BusinessException;
//manager can approve reimb
	public int approveReimbursement(int reimbid, double reimbAmount);// throws BusinessException;
//manager can reject
	public int rejectReimbursement(int reimbid);// throws BusinessException;

	public int deleteById(int reimbid);// throws BusinessException;
}
/*
 * An Employee can view their pending reimbursement requests
An Employee can view their resolved reimbursement requests
An Employee can view their information
An Employee can update their information
 */
