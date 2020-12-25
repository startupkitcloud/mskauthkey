package org.startupkit.authkey;


import org.startupkit.core.dao.AbstractDAO;

public class UserAuthKeyDAO extends AbstractDAO<UserAuthKey> {
	
	public UserAuthKeyDAO(){
		super(UserAuthKey.class);
	}
	

	@Override
	public Object getId(UserAuthKey obj) {
		return obj.getId();
	}
}
