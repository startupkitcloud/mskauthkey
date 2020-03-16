package com.mangobits.startupkit.authkey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name="userAuthKey")
@Indexed
public class UserAuthKey {
	
	
	@Id
	@DocumentId
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
	
	
	@Field
	private String idUser;
	
	
	
	@Field
	private String key;
	
	
	@Field
	@Enumerated(EnumType.STRING)
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