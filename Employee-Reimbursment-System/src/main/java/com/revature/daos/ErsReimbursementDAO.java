package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.revature.BusinessException;
import com.revature.models.ErsReimbursement;
import com.revature.models.ErsUser;

import util.ConnectionUtil;

public interface ErsReimbursementDAO {
	public boolean add(ErsReimbursement reimb) throws BusinessException;
	
	
			

	public List<ErsReimbursement> getAll() throws BusinessException;

}
