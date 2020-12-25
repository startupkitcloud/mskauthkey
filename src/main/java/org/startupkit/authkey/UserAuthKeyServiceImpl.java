package org.startupkit.authkey;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserAuthKeyServiceImpl implements UserAuthKeyService {

	
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
		
		boolean validated = false;

		UserAuthKey keyBase = loadKeyByUser(key.getIdUser(), key.getType());

		if(keyBase != null && keyBase.getKey().equals(key.getKey())){
			validated = true;
			userAuthKeyDAO.delete(keyBase);
		}
		
		return validated;
	}
	
	
	
	private UserAuthKey loadKeyByUser(String idUser, UserAuthKeyTypeEnum type) throws Exception{
		return userAuthKeyDAO.retrieve(userAuthKeyDAO.createBuilder()
				.appendParamQuery("idUser", idUser)
				.appendParamQuery("type", type)
				.build());
	}
	
	
	
	private String generateSMSNumber() throws Exception {
		
		String numberStr = null;

		boolean numberFound = false;

		while(!numberFound){

			int number = (int)(Math.random() * 10000);
			numberStr = String.valueOf(number);

			UserAuthKey key = userAuthKeyDAO.retrieve(userAuthKeyDAO.createBuilder()
					.appendParamQuery("key", numberStr)
					.build());

			numberFound = number > 999 && key == null;
		}
		
		return numberStr;
	}
}
