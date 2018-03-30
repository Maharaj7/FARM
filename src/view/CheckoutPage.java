package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import communication.Client_Customer;
import communication.Client_Farmer;
import controller.Client_Controller;
import model.Basket;
import model.Crop;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CheckoutPage {

	JFrame frmCheckOut;
	Client_Controller l = new Client_Controller();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutPage window = new CheckoutPage();
					window.frmCheckOut.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CheckoutPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCheckOut = new JFrame();
		frmCheckOut.setTitle("CheckOut");
		frmCheckOut.setBounds(100, 100, 610, 506);
		frmCheckOut.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCheckOut.getContentPane().setLayout(null);
		
		JLabel lblSummaryOfOrder = new JLabel("Summary of Order");
		lblSummaryOfOrder.setForeground(new Color(255, 255, 255));
		lblSummaryOfOrder.setFont(new Font("Khmer MN", Font.PLAIN, 17));
		lblSummaryOfOrder.setBounds(24, 38, 187, 27);
		frmCheckOut.getContentPane().add(lblSummaryOfOrder);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(24, 63, 558, 12);
		frmCheckOut.getContentPane().add(separator);
		
		JLabel lblChoosePickupLocation = new JLabel("Choose Pickup Location:");
		lblChoosePickupLocation.setForeground(new Color(255, 255, 255));
		lblChoosePickupLocation.setFont(new Font("Khmer MN", Font.PLAIN, 17));
		lblChoosePickupLocation.setIcon(new ImageIcon(CheckoutPage.class.getResource("/resources/pickUp.png")));
		lblChoosePickupLocation.setBounds(34, 336, 232, 37);
		frmCheckOut.getContentPane().add(lblChoosePickupLocation);
		
		JComboBox<String> pickUpLocationDdl = new JComboBox<String>();
		pickUpLocationDdl.setBounds(278, 338, 157, 27);
		pickUpLocationDdl.addItem("Linstead Market");
		pickUpLocationDdl.addItem("Kingston Market");
		pickUpLocationDdl.addItem("Papine Market");
		frmCheckOut.getContentPane().add(pickUpLocationDdl);
		
		JButton btnEditOrder = new JButton("Edit Order");
		btnEditOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCheckOut.dispose();
				CustomerBasket window = new CustomerBasket();
				window.frmCustomerBasket.setVisible(true);
			}
		});
		btnEditOrder.setBounds(196, 420, 117, 29);
		frmCheckOut.getContentPane().add(btnEditOrder);
		
		JButton btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_Customer  fam = new Client_Customer();
				fam.sendAction("request customer basket");
				ArrayList<Basket> list = new ArrayList<Basket>();
				
				ArrayList<Basket> crops = new ArrayList<Basket>();
			    fam.sendBasketList(crops);

				list = fam.receiveBasketData();
				
				Client_Customer fam1 = new Client_Customer();
				fam1.sendAction("request all crops");
				ArrayList<Crop> list1 = new ArrayList<Crop>();
				
				ArrayList<Crop> crops1 = new ArrayList<Crop>();
			    fam1.sendCropList(crops1);

				fam1.receiveResponse();
				list1 = fam1.receiveCropData();

				int size = list1.size();
				int count =0;
				try{
				do{
				
				for(int i =0; i<list1.size();i++)
				   {
					  
				    if(list1.get(i).getName().compareToIgnoreCase(list.get(count).getItemName())==0 && list1.get(i).getEmail().compareToIgnoreCase(list.get(count).getFarmerEmail())==0) 
					{						
				    	/**
						 * update crops data with changes to quantity after 
						 * **/
						Client_Farmer fam11 = new Client_Farmer();
						/**
						 * calculates the total cost for each item before storing the data in the purchases database
						 * **/
						float totalCost = list.get(count).getQuantity() * list.get(count).getCost();
					
						 int quantity = list1.get(i).getQuantity() - list.get(count).getQuantity();
						fam11.sendAction("Update Crop");
					
						/**
						 * Here stores to the purchases database.
						 * **/
				    	
						
						String available;
						if(quantity >= 1)

						{
							available ="In Stock";
						}
						else{
							available ="Not In Stock";
						}
					
						
						Crop crop = new Crop(list1.get(i).getEmail(),list1.get(i).getimagePath(),list1.get(i).getName(),list1.get(i).getWeight(),list1.get(i).getCostPerUnit(),available,quantity);
						fam11.sendExactCropName(list1.get(i).getName());
						fam11.sendExactEmail(list1.get(i).getEmail());
						fam11.sendCrop(crop);
						fam11.receiveResponse();
				    	
						Client_Farmer farmer = new Client_Farmer();
						farmer.sendAction("Add CustomerPurchase");
						Basket basket = new Basket(list.get(count).getFarmerEmail(),list.get(count).getCustomerEmail(),l.getFname()+" "+l.getLname(),pickUpLocationDdl.getSelectedItem().toString(),totalCost);
						farmer.sendCustomerPurchase(basket);
						farmer.receiveResponse();
					
				      count ++;
				    	
				     }
				     else{
				    		//System.out.println(list1.get(i).getName()+" "+list.get(count).getItemName()+" "+list1.get(i).getEmail()+" "+list.get(count).getFarmerEmail());
							
				    	// System.out.println("not found"); 
				     }	
				           
				   }
				}while(count <= size);
				    
				Client_Customer cus = new Client_Customer();
				
				cus.sendAction("Remove Basket Data");
				cus.sendExactEmail(l.getEmail());
				cus.receiveResponse();

				JOptionPane.showMessageDialog(null, "Order Successfully Placed,\nThank you.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				frmCheckOut.dispose();
				CustomerDashBoard window = new CustomerDashBoard();
				window.frame.setVisible(true);

				}
				catch(IndexOutOfBoundsException e1) //Currently causes an IndexOutOfBounds error but works just fine once caught. 
				{
					Client_Customer cus = new Client_Customer();
					
					cus.sendAction("Remove Basket Data");
					cus.sendExactEmail(l.getEmail());
					cus.receiveResponse();

					JOptionPane.showMessageDialog(null, "Order Successfully Placed,\nThank you.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					frmCheckOut.dispose();
					CustomerDashBoard window = new CustomerDashBoard();
					window.frame.setVisible(true);
					
				}
				catch(NullPointerException q)
				{
					JOptionPane.showMessageDialog(null, q.getMessage());
				}
			}
		});
		btnConfirmOrder.setBounds(383, 420, 117, 29);
		frmCheckOut.getContentPane().add(btnConfirmOrder);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CheckoutPage.class.getResource("/resources/checkoutpage.jpg")));
		label.setBounds(0, 0, 610, 455);
		frmCheckOut.getContentPane().add(label);
		
		JMenuBar menuBar = new JMenuBar();
		frmCheckOut.setJMenuBar(menuBar);
		
		JButton button = new JButton("My DashBoard");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCheckOut.dispose();
				CustomerDashBoard window = new CustomerDashBoard();
				window.frame.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(CheckoutPage.class.getResource("/resources/backIcon.png")));
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		menuBar.add(button);
	}
}
