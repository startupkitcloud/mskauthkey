package com.mangobits.startupkit.authkey;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserAuthKeyServiceImpl implements UserAuthKeyService {
	

//	@EJB
//	private UserService userService;
	
	
	
	@Inject
	@New
	private UserAuthKeyDAO userAuthKeyDAO;
	
	

	@Override
	public UserAuthKey createKey(String idUser, UserAuthKeyTypeEnum type) throws Exception {
		
		UserAuthKey key;

		key = loadKeyByUser(idUser, type);

		if(key == null){

			key = new UserAuthKey();
			key.setCreationDate(new Date());
			key.setIdUser(idUser);
			key.setType(type);

			if(type.equals(UserAuthKeyTypeEnum.EMAIL)){
				//ramdom uuid para email
				key.setKey(UUID.randomUUID().toString());
			}
			else{
				//4 digitos ramdomicos para SMS
				key.setKey(generateSMSNumber());
			}

			userAuthKeyDAO.insert(key);
		}
		
		return key;
	}

	
	
	@Override
	public Boolean validateKey(UserAuthKey key) throws Exception {
		
		Boolean validated = false;

		UserAuthKey keyBase = loadKeyByUser(key.getIdUser(), key.getType());

		if(keyBase != null && keyBase.getKey().equals(key.getKey())){
			validated = true;
			userAuthKeyDAO.delete(keyBase);
		}
		
		return validated;
	}
	
	
	
	private UserAuthKey loadKeyByUser(String idUser, UserAuthKeyTypeEnum type) throws Exception{
		
		UserAuthKey key = null;

		Map<String, Object> params = new HashMap<>();
		params.put("idUser", idUser);
		params.put("type", type);

		key = userAuthKeyDAO.retrieve(params);
		
		return key;
	}
	
	
	
	private String generateSMSNumber() throws Exception {
		
		String numberStr = null;

		boolean numberFound = false;

		while(!numberFound){

			Integer number = (int)(Math.random() * 10000);
			numberStr = String.valueOf(number);

			Map<String, Object> params = new HashMap<>();
			params.put("key", numberStr);

			UserAuthKey key = userAuthKeyDAO.retrieve(params);
			numberFound = number > 999 && key == null;
		}
		
		return numberStr;
	}
}
