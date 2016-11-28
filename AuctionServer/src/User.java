import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	@XmlElement
	public String firsthName;
	@XmlElement
	public String surName;
	@XmlElement
	public String passWord;
	@XmlElement
	public String email;
	
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
