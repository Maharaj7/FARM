package model;


public class Farmer implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String email;
	private String address;
	private String password;
	private String photoPath;
	private float earnings;
	
	public Farmer()
	{
		
	}
	
	
     

	public Farmer( String fName, String lName, String email, String address, String password, String photoPath,
			float earnings) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.address = address;
		this.password = password;
		this.photoPath = photoPath;
		this.earnings = earnings;
	}




	public Farmer(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		
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




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getphotoPath() {
		return photoPath;
	}




	public void setphotoPath(String photoPath) {
		this.photoPath = photoPath;
	}




	public float getEarnings() {
		return earnings;
	}




	public void setEarnings(float earnings) {
		this.earnings = earnings;
	}

    

	
	

}
