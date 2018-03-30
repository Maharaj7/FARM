package model;

public class Customer implements java.io.Serializable{
   
	
	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String email;
	private String photoPath;
	private String password;
	private float funds;
	
	 public Customer()
	 {
		 
	 }
	
	
	

	public Customer(String fName, String lName, String email, String photoPath, String password, float funds) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.photoPath = photoPath;
		this.password = password;
		this.funds = funds;
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




	public String getphotoPath() {
		return photoPath;
	}




	public void setphotoPath(String photoPath) {
		this.photoPath = photoPath;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public float getFunds() {
		return funds;
	}




	public void setFunds(float funds) {
		this.funds = funds;
	}

	
}
