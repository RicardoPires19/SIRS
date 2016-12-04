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
	
	private int id;
	
	private int credit;
	
	public User(){}
	
	public User(String firsthName, String surName, String passWord, String email) {
		this.firsthName = firsthName;
		this.surName = surName;
		this.passWord = passWord;
		this.email = email;
	}

	public User(String firsthName, String surName, String passWord, String email, int credit) {
		this.firsthName = firsthName;
		this.surName = surName;
		this.passWord = passWord;
		this.email = email;
		this.credit = credit;
	}
	
	public User(int id, String firsthName, String surName, String passWord, String email, int credit) {
		this.firsthName = firsthName;
		this.surName = surName;
		this.passWord = passWord;
		this.email = email;
		this.id=id;
		this.credit = credit;
	}

	public String getFirsthName() {
		return firsthName;
	}

	public void setFirsthName(String firsthName) {
		this.firsthName = firsthName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
}
