package model;

public class Customer implements java.io.Serializable{
   
	
	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String email;
	private byte[] photo;
	private String password;
	
	
	 public Customer()
	 {
		 
	 }
	
	public Customer(String fName, String lName, String email, byte[] photo, String password) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.photo = photo;
		this.password = password;
	}
	
	public Customer(String email, String password)
	{
		super();
		this.password = password;
		this.email = email;
		
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
 
	
	
	
}
