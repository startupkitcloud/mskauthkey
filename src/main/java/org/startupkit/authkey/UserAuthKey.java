package org.startupkit.authkey;

import org.startupkit.core.annotation.MSKEntity;
import org.startupkit.core.annotation.MSKId;

import java.util.Date;

@MSKEntity(name="userAuthKey")
public class UserAuthKey {

	@MSKId
    private String id;

	private String idUser;

	private String key;

	private UserAuthKeyTypeEnum type;

	private Date creationDate;

	public UserAuthKey(){
		
	}

	public UserAuthKey(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public UserAuthKeyTypeEnum getType() {
		return type;
	}

	public void setType(UserAuthKeyTypeEnum type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
