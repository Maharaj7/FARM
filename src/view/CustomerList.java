package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import communication.Client_Customer;
import communication.Client_Farmer;
import model.Basket;

public class CustomerList {

	 JFrame frame;
	 JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerList window = new CustomerList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 499, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(28, 109, 628, 284);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 5, 463, 224);
		panel2.setLayout(null);
		
		
		Client_Customer  fam = new Client_Customer();
		fam.sendAction("request customer basket");
		ArrayList<Basket> list = new ArrayList<Basket>();
		
		ArrayList<Basket> crops = new ArrayList<Basket>();
	    fam.sendBasketList(crops);

		list = fam.receiveBasketData();
		
		
		Client_Farmer farmer = new Client_Farmer();
		farmer.sendAction("request customerPurchaseList");
		farmer.sendEmail();
		ArrayList<Basket> listt = new ArrayList<Basket>();
		ArrayList<Basket> bas = new ArrayList<Basket>();
		farmer.sendCustomerPurchaseList(bas);
		farmer.receiveResponse();
		listt = farmer.receiveCustomerPurchaseList();
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
		
		 Object[] columnNames = new Object[4];
		 
		 columnNames[0]="Customer Name      ";
		 columnNames[1] = "Customer Email   ";
		 columnNames[2] = "Location       ";
		 columnNames[3] = "Amount Spent";
	
		 model.setColumnIdentifiers(columnNames);
		 
		  Object[] row = new Object[4];

		  for(int i=0; i <listt.size(); i++)
		{
			  
			  row[0] = listt.get(i).getcName();
			  row[1] = listt.get(i).getCustomerEmail();
			  row[2] = listt.get(i).getLocation();
			  row[3] = listt.get(i).getCost();
			   model.addRow(row); 
			   
		}

		  table.setModel(model);
		  JScrollPane  scrollPane = new JScrollPane(table);
			scrollPane.setBounds(39, 244, 545, -186);

			panel2.setLayout(new BorderLayout());
		panel2.add(scrollPane,BorderLayout.CENTER);
		 
			
		panel.setLayout(null);
		panel.add(panel2);
		
		
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JButton btnMyDashboard = new JButton("My DashBoard");
		btnMyDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				FarmerDashBoard window = new FarmerDashBoard();
				window.frmFarmerDashboard.setVisible(true);
			}
		});
		
		btnMyDashboard.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnMyDashboard.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/backIcon.png")));
		menuBar.add(btnMyDashboard);
	}

}
