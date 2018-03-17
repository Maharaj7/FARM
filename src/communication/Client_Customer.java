package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import model.Basket;
import model.Crop;
import model.Customer;
import view.LoginScreen;

public class Client_Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	private ObjectInputStream is;
	private ObjectOutputStream os;
	private Socket connection;
	LoginScreen l = new LoginScreen();
	
	public Client_Customer()
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
	
	
	public void sendCustomer(Customer obj)
	{
		try {
			os.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendBasket(Basket obj)
	{
		try {
			os.writeObject(obj);
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
	
	public void sendEmail()
	{
		try {
			os.writeObject(l.getEmail());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Customer receiveData()
	{
		try {
			   Customer cus = (Customer)is.readObject();
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
 
	  public void sendBasketList(ArrayList<Basket> basket)
	  {
		  try {
				os.writeObject(basket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
		public ArrayList<Basket> receiveBasketData()
		{
			try {
				@SuppressWarnings("unchecked")
				ArrayList<Basket> bas = (ArrayList<Basket>)is.readObject();
				return bas;
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
}
