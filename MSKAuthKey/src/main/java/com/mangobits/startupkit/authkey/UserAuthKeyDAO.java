package com.mangobits.startupkit.authkey;

import com.mangobits.startupkit.core.dao.AbstractDAO;


public class UserAuthKeyDAO extends AbstractDAO<UserAuthKey> {
	
	public UserAuthKeyDAO(){
		super(UserAuthKey.class);
	}
	

	@Override
	public Object getId(UserAuthKey obj) {
		return obj.getId();
	}
}
