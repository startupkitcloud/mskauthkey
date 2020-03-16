package com.mangobits.startupkit.authkey;

import javax.ejb.Local;

@Local
public interface UserAuthKeyService {
	
	UserAuthKey createKey(String idUser, UserAuthKeyTypeEnum type) throws Exception;

	Boolean validateKey(UserAuthKey key) throws Exception;
}
