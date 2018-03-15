package model;

import java.io.Serializable;
import java.util.Arrays;


public class Crop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
    private byte[] image;
    private String name;
    private float weight;
    private float costPerUnit;
    private String available;
    private int quantity;
    
  
    public Crop()
    {
    	
    }
    
    public Crop(String email)
    {
    	this.email = email;
    }
	public Crop(String email, byte[] image, String name, float weight, float costPerUnit, String available,
			int quantity) {
		super();
		this.email = email;
		this.image = image;
		this.name = name;
		this.weight = weight;
		this.costPerUnit = costPerUnit;
		this.available = available;
		this.quantity = quantity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getCostPerUnit() {
		return costPerUnit;
	}

	public void setCostPerUnit(float costPerUnit) {
		this.costPerUnit = costPerUnit;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Crop [email=" + email + ", image=" + Arrays.toString(image) + ", name=" + name + ", weight=" + weight
				+ ", costPerUnit=" + costPerUnit + ", available=" + available + ", quantity=" + quantity + "]";
	}

	
	
    
    
}
