package com.revature.daos;

import com.revature.BusinessException;
import com.revature.models.ErsUserRole;

public interface ErsUsersRoleDAO {
	
	public ErsUserRole getById(int id) throws BusinessException;

}
