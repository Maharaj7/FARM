package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

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
	@SuppressWarnings("serial")
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
		lblSubtotal.setBounds(518, 415, 61, 16);
		frmCustomerBasket.getContentPane().add(lblSubtotal);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frmCustomerBasket.dispose();
				CheckoutPage window = new CheckoutPage();
				window.frmCheckOut.setVisible(true);
			}
		});
		label.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/checkoutBtn.png")));
		label.setBounds(276, 437, 111, 35);
		frmCustomerBasket.getContentPane().add(label);
		
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
		
		
		DefaultTableModel model  = new DefaultTableModel();
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
		 		TableModel tmodel = table.getModel();
		 		
			}
		});
		 Object[] columnNames = new Object[3];
		 
		 columnNames[0]="Item Name";
		 columnNames[1] = "Quantity";
		 columnNames[2] = "cost";
		
	
		 model.setColumnIdentifiers(columnNames);
		 
		  Object[] row = new Object[3];

		  for(int i=0; i <list.size(); i++)
		{
			//  if(list.get(i).getImage()	!= null)
			 // {
				 // ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage().getScaledInstance(190,160,Image.SCALE_SMOOTH));
				//  row[6] = image; 
			//  }
			//  else{
			//	  row[6] = null;
			//  }
			  
			  row[0] = list.get(i).getItemName();
			  row[1] = list.get(i).getQuantity();
			  row[2] = list.get(i).getCost();
			  
			  
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
	}
}
