package com.revature.daos;

import com.revature.BusinessException;
import com.revature.models.ErsUser;

public interface ErsUsersDAO {
	public boolean addUser(ErsUser user) throws BusinessException;

	public boolean isEmailInUse(String email) throws BusinessException;

	public ErsUser verifyPassword(String username, String password) throws BusinessException;

	public ErsUser getByEmail(String email) throws BusinessException;

	public boolean deleteById(int id) throws BusinessException;

}
