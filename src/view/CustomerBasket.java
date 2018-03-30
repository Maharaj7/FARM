package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
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
import model.Basket;


import javax.swing.JScrollPane;

public class CustomerBasket {

	JFrame frmCustomerBasket;
    JTable table;
    AlterBasket ab = new AlterBasket();
     
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
		frmCustomerBasket.setBounds(100, 100, 706, 540);
		frmCustomerBasket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerBasket.getContentPane().setLayout(null);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(459, 81, 61, 16);
		frmCustomerBasket.getContentPane().add(lblUnitPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(562, 81, 61, 16);
		frmCustomerBasket.getContentPane().add(lblQuantity);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(18, 96, 652, 12);
		frmCustomerBasket.getContentPane().add(separator);
		
		JLabel lblShoppingBasket = new JLabel("Shopping Basket");
		lblShoppingBasket.setFont(new Font("Herculanum", Font.PLAIN, 20));
		lblShoppingBasket.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/myBasket_sml.png")));
		lblShoppingBasket.setBounds(235, 25, 207, 28);
		frmCustomerBasket.getContentPane().add(lblShoppingBasket);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(18, 402, 652, 12);
		frmCustomerBasket.getContentPane().add(separator_1);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setBounds(504, 414, 61, 16);
		frmCustomerBasket.getContentPane().add(lblSubtotal);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(28, 109, 628, 284);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 5, 608, 268);
		panel2.setLayout(null);
		
		
		Client_Customer  fam = new Client_Customer();
		fam.sendAction("request customer basket");
		ArrayList<Basket> list = new ArrayList<Basket>();
		
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
				
				CheckoutPage window = new CheckoutPage();
				window.frmCheckOut.setVisible(true);
				frmCustomerBasket.dispose();
				
			}
		});
		label.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/checkoutBtn.png")));
		label.setBounds(300, 437, 111, 35);
		frmCustomerBasket.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(Float.toString(totalCost));
		label_1.setBounds(562, 415, 46, 14);
		frmCustomerBasket.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/basketBkg.jpg")));
		label_2.setBounds(0, 0, 706, 489);
		frmCustomerBasket.getContentPane().add(label_2);
	}
	
}
