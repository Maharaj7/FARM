package controller;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import communication.Client_Customer;
import communication.Client_Farmer;
import model.Basket;
import model.Customer;
import model.Farmer;
import view.CustomerDashBoard;
import view.FarmerDashBoard;


public class Client_Controller {
	private static String pImage;
	private static String email, fname, lname, pass;
	private static float earnings;
	static float cFunds=0.0f;
	
	 /**
     * After requesting a login to the server it responses 
     * by querying the database and returning 
     * required data that is used to display the persons
     * image and name once the specific Dash board is called
     * */

	public void customerLogin(String email1, String password, JFrame frame,JTextField textField,JPasswordField passwordField)
	{
		 Customer data = new Customer();
		 try{
		 Client_Customer cus = new Client_Customer();

         cus.sendAction("Request Login");             
		 Customer customer = new Customer(email1,password);
           cus.sendCustomer(customer);
           if(cus.isLoginTrue()== true)
		   {
            data = cus.receiveData(); 
            email = data.getEmail();
			fname = data.getfName();
			lname = data.getlName();
			pImage = data.getphotoPath();
			earnings = data.getFunds();
			
			frame.dispose();

			CustomerDashBoard window = new CustomerDashBoard();
			window.frame.setVisible(true);
		   }
           else{
			   JOptionPane.showMessageDialog(null, "Email and Password is not correct");
			   textField.setText("");
				passwordField.setText("");
		   }
			
		 }
		 catch (NullPointerException q) {
				JOptionPane.showMessageDialog(null, "Email and Password is not correct");
				textField.setText("");
				passwordField.setText("");
			}
			
	}
	
	
	public void farmerLogin(String email1, String password, JFrame frame, JFrame frmFarmerDashboard,JTextField textField,JPasswordField passwordField )
	{
		Farmer data = new Farmer();
		try {
		Client_Farmer cus = new Client_Farmer();

         cus.sendAction("farmer");            

		   Farmer farmer = new Farmer(email1,password);
		   cus.sendFarmer(farmer);
		   if(cus.isLoginTrue()== true)
		   {
			    data = cus.receiveData();
	            fname = data.getfName();
				lname = data.getlName();
				pImage = data.getphotoPath();
				earnings = data.getEarnings();
				email = data.getEmail();
				
				frame.dispose();

				FarmerDashBoard window = new FarmerDashBoard();
				window.frmFarmerDashboard.setVisible(true);
		   }
		   else{
			   JOptionPane.showMessageDialog(null, "Email and Password is not correct");
			   textField.setText("");
				passwordField.setText("");
		   }

           
		} catch (NullPointerException q) {
			JOptionPane.showMessageDialog(null, "Email and Password is not correct");
			textField.setText("");
			passwordField.setText("");
		}
	}
	
	public float addFundsToAccount(String amount, JButton btnAdd)
	{
		try{
			float funds =0.0f;
			funds = funds + Float.parseFloat(amount);
			cFunds = funds;
			
			Client_Customer customer = new Client_Customer();
			Customer cus = new Customer(getFname(),getLname(),getEmail(),image(),getPass(),cFunds);
			customer.sendAction("Update Customer");
			customer.sendExactEmail(email);
			customer.sendCustomer(cus);
			customer.receiveResponse();
			
			JOptionPane.showMessageDialog(null, "Funds Added!");
			btnAdd.setEnabled(false);
			
			}
			catch(NullPointerException | NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Ivalid Value");
			}
		return cFunds;
	}
	
	public void addToBasket(String email,String name,String quantityInput,String quantity,String cost,String weight,JFrame frame)
	{
		Client_Customer customer = new Client_Customer();
		customer.sendAction("request customer basket");
		ArrayList<Basket> list = new ArrayList<Basket>();
		
		ArrayList<Basket> bas = new ArrayList<Basket>();
		
		customer.sendBasketList(bas);
		list = customer.receiveBasketData();
		
		int found =0;
		for(int i=0; i<list.size();i++)
		{
			if(list.get(i).getItemName().compareTo(name)==0)
			{
				found =1;
				break;
			}
		}
		
		if(found ==1)
		{
			JOptionPane.showMessageDialog(null, "Crop Already added to basket.");
		}
		
		else if(Integer.parseInt(quantity)<Integer.parseInt(quantityInput))
		{
			JOptionPane.showMessageDialog(null, "insufficient Quantity of Item ");
		}
		else{
			 
			Client_Customer cus = new Client_Customer();
			Basket basket = new Basket(email,getEmail(),name,Integer.parseInt(quantityInput),Float.parseFloat(cost),Float.parseFloat(weight));
			cus.sendAction("Add to Basket");
			cus.sendBasket(basket);
			cus.receiveResponse();
		
			JOptionPane.showMessageDialog(null, "Added to Basket!");
			frame.dispose();
		}
	}
	
	
	
	public String image() {
		return pImage;
	}

	public String getEmail() {
		return email;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public float getEarnings() {
		return earnings;
	}

	public String getPass() {
		return pass;
	}
}
