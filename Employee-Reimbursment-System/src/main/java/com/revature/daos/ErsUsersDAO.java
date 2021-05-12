package com.revature.daos;

import com.revature.models.ErsUsers;

//import businessException.BusinessException;

public interface ErsUsersDAO {
	public boolean addUser(ErsUsers user);// throws BusinessException;

	public boolean isEmailInUse(String email);// throws BusinessException;

	public ErsUsers verifyPassword(String email, String password);// throws BusinessException;

	public ErsUsers getByEmail(String email);// throws BusinessException;
	
	public boolean deleteById(int id);// throws BusinessException;

}
