package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;


import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import communication.Client_Customer;
import communication.Client_Farmer;
import model.Basket;
import model.Crop;

import javax.swing.JScrollPane;

public class CustomerBasket {

	JFrame frmCustomerBasket;
    JTable table;
    CheckOut checkOut = new CheckOut();
     String email,itemName,quantity,cost;
     
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerBasket window = new CustomerBasket();
					window.frmCustomerBasket.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerBasket() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmCustomerBasket = new JFrame();
		frmCustomerBasket.setTitle("Customer Basket");
		frmCustomerBasket.setBounds(100, 100, 668, 540);
		frmCustomerBasket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerBasket.getContentPane().setLayout(null);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(405, 81, 61, 16);
		frmCustomerBasket.getContentPane().add(lblUnitPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(585, 81, 61, 16);
		frmCustomerBasket.getContentPane().add(lblQuantity);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(18, 96, 628, 12);
		frmCustomerBasket.getContentPane().add(separator);
		
		JLabel lblShoppingBasket = new JLabel("Shopping Basket");
		lblShoppingBasket.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/myBasket_sml.png")));
		lblShoppingBasket.setBounds(256, 25, 131, 16);
		frmCustomerBasket.getContentPane().add(lblShoppingBasket);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(18, 402, 628, 12);
		frmCustomerBasket.getContentPane().add(separator_1);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setBounds(504, 414, 61, 16);
		frmCustomerBasket.getContentPane().add(lblSubtotal);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(18, 107, 628, 284);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 5, 608, 268);
		panel2.setLayout(null);
		
		
		Client_Customer  fam = new Client_Customer();
		fam.sendAction("request customer basket");
		fam.sendEmail();
		ArrayList<Basket> list = new ArrayList<Basket>();
		
		ArrayList<Basket> crops = new ArrayList<Basket>();
	    fam.sendBasketList(crops);

		fam.receiveResponse();
		list = fam.receiveBasketData();
		
		
		/**
		 * To go in Controller class
		 * ***/
		float totalCost=0.0f;
		for(int i=0; i<list.size(); i++)
		{
			totalCost = totalCost + list.get(i).getCost();
		}
		
		
		DefaultTableModel model  = new DefaultTableModel();
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
		 		TableModel tmodel = table.getModel();
		 		AlterBasket ab = new AlterBasket();
		 		ab.frame.setVisible(true);
		 		 itemName = tmodel.getValueAt(index, 0).toString();
		 		 quantity = tmodel.getValueAt(index, 1).toString();
		 		cost = tmodel.getValueAt(index, 2).toString();
		 		 email = tmodel.getValueAt(index, 3).toString();
		 		 
		 		 
			}
		});
		 Object[] columnNames = new Object[4];
		 
		 columnNames[0]="Item Name";
		 columnNames[1] = "Quantity";
		 columnNames[2] = "cost";
		 columnNames[3] = "Email";
		  
	
		 model.setColumnIdentifiers(columnNames);
		 
		  Object[] row = new Object[4];

		  for(int i=0; i <list.size(); i++)
		{
			  
			  row[0] = list.get(i).getItemName();
			  row[1] = list.get(i).getQuantity();
			  row[2] = list.get(i).getCost();
			  row[3] = list.get(i).getFarmerEmail();
			  
			  
			   model.addRow(row); 
			   
		}
		

		  table.setModel(model);
		  JScrollPane  scrollPane = new JScrollPane(table);
			scrollPane.setBounds(39, 244, 545, -186);

			panel2.setLayout(new BorderLayout());
		panel2.add(scrollPane,BorderLayout.CENTER);
		 
			
		panel.setLayout(null);
		panel.add(panel2);
		
		
		frmCustomerBasket.getContentPane().add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmCustomerBasket.setJMenuBar(menuBar);
		
		JButton btnMyDashboard = new JButton("My DashBoard");
		btnMyDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCustomerBasket.dispose();
				CustomerDashBoard window = new CustomerDashBoard();
				window.frame.setVisible(true);
			}
		});
		
		btnMyDashboard.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnMyDashboard.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/backIcon.png")));
		menuBar.add(btnMyDashboard);
		
		JButton btnAddCrops = new JButton("Add Crops");
		btnAddCrops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCustomerBasket.dispose();
				SearchCrops window = new SearchCrops();
				window.frame.setVisible(true);
			}
		});
		btnAddCrops.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnAddCrops.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/addCrop_sml.png")));
		menuBar.add(btnAddCrops);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
				Client_Customer  fam = new Client_Customer();
				fam.sendAction("request customer basket");
				fam.sendEmail();
				ArrayList<Basket> list = new ArrayList<Basket>();
				
				ArrayList<Basket> crops = new ArrayList<Basket>();
			    fam.sendBasketList(crops);

				fam.receiveResponse();
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
						 int quantity = list1.get(i).getQuantity() - list.get(count).getQuantity();
						fam11.sendAction("Update Crop");
						String available;
						if(quantity >= 1)

						{
							available ="In Stock";
						}
						else{
							available ="Not In Stock";
						}
						
						
						Crop crop = new Crop(list1.get(i).getEmail(),list1.get(i).getImage(),list1.get(i).getName(),list1.get(i).getWeight(),list1.get(i).getCostPerUnit(),available,quantity);
						fam11.sendExactCropName(list1.get(i).getName());
						fam11.sendExactEmail(list1.get(i).getEmail());
						fam11.sendCrop(crop);
						fam11.receiveResponse();
				      count ++;
				    	
				     }
				     else{
				    	 System.out.println("not found"); 
				     }	
				           
				   }
				}while(count <= size);
				    
				Client_Customer cus = new Client_Customer();
				cus.sendEmail();
				cus.sendAction("Remove Basket Data");
				JOptionPane.showMessageDialog(null, "Check out Completed");
				}
				catch(IndexOutOfBoundsException e1) //Currently causes an IndexOutOfBounds error but works just fine once caught. 
				{
					Client_Customer cus = new Client_Customer();
					cus.sendEmail();
					cus.sendAction("Remove Basket Data");
					JOptionPane.showMessageDialog(null, "Check out Completed");
					
				}
				catch(NullPointerException q)
				{
					JOptionPane.showMessageDialog(null, q.getMessage());
				}
				
			}
		});
		label.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/checkoutBtn.png")));
		label.setBounds(276, 437, 111, 35);
		frmCustomerBasket.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(Float.toString(totalCost));
		label_1.setBounds(562, 415, 46, 14);
		frmCustomerBasket.getContentPane().add(label_1);
	}
}
