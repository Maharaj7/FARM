package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import controller.Client_Controller;
import model.Basket;
import model.Crop;
import model.Farmer;
import view.CropsDesktop;

public class Client_Farmer implements Serializable{

	private static final long serialVersionUID = 1L;
	private ObjectInputStream is;
	private ObjectOutputStream os;
	private Socket connection;
	Client_Controller l = new Client_Controller();
	CropsDesktop desk = new CropsDesktop();
	
	public Client_Farmer()
	{
		this.createConnection();
		this.getStreams();
	}
    
public void getStreams() {
		
		try{
			
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void createConnection() {
		try{
			
			connection  = new Socket(InetAddress.getLocalHost(),8888);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection()
	{
		try {
			os.close();
			is.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void sendAction(String action)
	{
		try {
			os.writeObject(action);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Sends current farmer's email to the server who is logged in to query the database.
	 * The email is used as the primary key in order to draw data from the database.
	 * A function is written in the Login Screen that returns the email of who ever logs in
	 * 
	 * 
	 * singed Maharaj
	 * */
	public void sendEmail()
	{
		try {
			os.writeObject(l.getEmail());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * this is new-signed Rosie Franz
	 * 
	 * */
	
	
	public void sendExactEmail(String email)
	{
		try {
			os.writeObject(email);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendCropName()
	{
		try {
			os.writeObject(desk.returnCropName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendExactCropName(String crop)
	{
		try {
			os.writeObject(crop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendFarmer(Farmer obj)
	{
		try {
			os.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendCrop(Crop crop)
	{
		try {
			os.writeObject(crop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendCustomerPurchase(Basket basket)
	{
		try {
			os.writeObject(basket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  public void sendCustomerPurchaseList(ArrayList<Basket> basket)
	  {
		  try {
				os.writeObject(basket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
	  public void sendCropList(ArrayList<Crop> crop)
	  {
		  try {
				os.writeObject(crop);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  public void sendFarmerList(ArrayList<Farmer> farmer)
	  {
		  try {
				os.writeObject(farmer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  

	public void receiveResponse()
	{
		try {
			@SuppressWarnings("unused")
			Boolean flag = (Boolean)is.readObject();
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public boolean isLoginTrue()
	{
		try {
			Boolean flag = (Boolean)is.readObject();
			return flag;
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Farmer receiveData()
	{
		try {
			   Farmer cus = (Farmer)is.readObject();
			   return cus;
		} catch (IOException | ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<Crop> receiveCropData()
	{
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Crop> cr = (ArrayList<Crop>)is.readObject();
			return cr;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Farmer> receiveFarmerData()
	{
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Farmer> cr = (ArrayList<Farmer>)is.readObject();
			return cr;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Basket> receiveCustomerPurchaseList()
	{
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Basket> cr = (ArrayList<Basket>)is.readObject();
			return cr;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
