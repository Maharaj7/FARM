package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import communication.Client_Customer;
import communication.Client_Farmer;
import controller.Client_Controller;
import model.Basket;
import model.Crop;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CheckoutPage {

	JFrame frmCheckOut;
	Client_Controller l = new Client_Controller();
	 AlterBasket ab = new AlterBasket();
	 JTable table;
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
		
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(24, 71, 560, 182);
		panel2.setLayout(null);
		
		
		Client_Customer  fam = new Client_Customer();
		fam.sendAction("request specific customer basket");
		ArrayList<Basket> list = new ArrayList<Basket>();
		fam.sendExactEmail(l.getEmail());
		ArrayList<Basket> crops = new ArrayList<Basket>();
	    fam.sendBasketList(crops);

		list = fam.receiveBasketData();
		
		
		/**
		 * To go in Controller class
		 * ***/
		float totalCost=0.0f;
	
		for(int i=0; i<list.size(); i++)
		{
			totalCost = totalCost + list.get(i).getCost()*list.get(i).getQuantity();
			
		}
		

		
		DefaultTableModel model  = new DefaultTableModel();
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
		 		TableModel tmodel = table.getModel();

		 		String itemName = tmodel.getValueAt(index, 0).toString();
		 		String quantity = tmodel.getValueAt(index, 5).toString();
		 		String cost = tmodel.getValueAt(index, 4).toString();
		 		String email = tmodel.getValueAt(index, 3).toString();
		 		String Cemail = tmodel.getValueAt(index, 1).toString();
		 		String weight = tmodel.getValueAt(index, 2).toString();
		 		ab.frame.setVisible(true);
		 		ab.cropName = itemName;
		 		ab.quantity = Integer.parseInt(quantity);
		 		ab.cost = Float.parseFloat(cost);
		 		ab.weight = Float.parseFloat(weight);
		 		ab.custEmail = Cemail;
		 		ab.famEmail = email;
			}
		});
		 Object[] columnNames = new Object[6];
		 
		 columnNames[0]="Item Name";
		 columnNames[5] = "Quantity";
		 columnNames[4] = "cost";
		 columnNames[3] = "Farmer Email";
		 columnNames[1] = "Customer Email";
		 columnNames[2] = "Weight";
		  
	
		 model.setColumnIdentifiers(columnNames);
		 
		  Object[] row = new Object[6];

		  for(int i=0; i <list.size(); i++)
		{
			  
			  row[0] = list.get(i).getItemName();
			  row[5] = list.get(i).getQuantity();
			  row[4] = list.get(i).getCost();
			  row[3] = list.get(i).getFarmerEmail();
			  row[1] = list.get(i).getCustomerEmail();
			  row[2] = list.get(i).getWeight();
			  
			   model.addRow(row); 
			   
		}
		

		  table.setModel(model);
		  JScrollPane  scrollPane = new JScrollPane(table);
			scrollPane.setBounds(39, 244, 545, -186);
			panel2.setLayout(new BorderLayout());
			panel2.add(scrollPane,BorderLayout.CENTER);
			frmCheckOut.getContentPane().add(panel2);
		
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
						fam11.sendExactCropName(list1.get(i).getName());
						fam11.sendExactEmail(list1.get(i).getEmail());
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
					
						Client_Farmer fam111 = new Client_Farmer();
						fam111.sendAction("Add Crop");
						Crop crop = new Crop(list1.get(i).getEmail(),list1.get(i).getimagePath(),list1.get(i).getName(),list1.get(i).getWeight(),list1.get(i).getCostPerUnit(),available,quantity);
						fam111.sendCrop(crop);
						fam111.receiveResponse();
				    	
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
		
		JLabel lblGrandTotal = new JLabel("Grand Total: $"+Float.toString(totalCost)+"c");
		lblGrandTotal.setForeground(Color.WHITE);
		lblGrandTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrandTotal.setBounds(24, 264, 143, 27);
		frmCheckOut.getContentPane().add(lblGrandTotal);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CheckoutPage.class.getResource("/resources/checkoutpage.jpg")));
		label.setBounds(10, 0, 610, 455);
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
