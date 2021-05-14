package com.revature.daos;

import java.util.List;

import com.revature.BusinessException;
import com.revature.models.ErsReimbursement;

public interface ErsReimbursementDAO {
	public boolean add(ErsReimbursement user) throws BusinessException;

	public List<ErsReimbursement> getAll() throws BusinessException;

}
