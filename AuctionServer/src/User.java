import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class User {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	//@XmlElement
	private String firsthName;
	
	private String surName;

	private String passWord;
	
	private String email;
	
	private String id;
	public User(){}

	public User(String firsthName, String surName, String passWord, String email) {
		this.firsthName = firsthName;
		this.surName = surName;
		this.passWord = passWord;
		this.email = email;

	}
	
	public User(String firsthName, String surName, String passWord, String email,String id) {
		this.firsthName = firsthName;
		this.surName = surName;
		this.passWord = passWord;
		this.email = email;
		this.id=id;

	}
	
	
	

}
