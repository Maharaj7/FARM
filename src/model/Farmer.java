package model;

import java.util.Arrays;

public class Farmer implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String email;
	private String address;
	private String password;
	private byte[] photo;
	private float earnings;
	
	public Farmer()
	{
		
	}
	
	
	
	public Farmer(String fName, String lName, String email, String address, String password, byte[] photo,
			float earnings) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.address = address;
		this.password = password;
		this.photo = photo;
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



	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public float getEarnings() {
		return earnings;
	}



	public void setEarnings(float earnings) {
		this.earnings = earnings;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	

}
