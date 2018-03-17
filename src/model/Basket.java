package model;

import java.io.Serializable;

public class Basket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String farmerEmail;
	private String customerEmail;
	private String itemName;
	private int quantity;
	private float cost;
	private float weight;

	
	public Basket() {
		// TODO Auto-generated constructor stub
	}


	public Basket(String farmerEmail, String customerEmail, String itemName, int quantity, float cost, float weight) {
		super();
		this.farmerEmail = farmerEmail;
		this.customerEmail = customerEmail;
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
		this.weight = weight;

	}


	public String getFarmerEmail() {
		return farmerEmail;
	}


	public void setFarmerEmail(String farmerEmail) {
		this.farmerEmail = farmerEmail;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public float getCost() {
		return cost;
	}


	public void setCost(float cost) {
		this.cost = cost;
	}


	public float getWeight() {
		return weight;
	}


	public void setWeight(float weight) {
		this.weight = weight;
	}	
	
}
