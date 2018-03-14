package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerBasket {

	JFrame frmCustomerBasket;

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
		lblSubtotal.setBounds(518, 415, 61, 16);
		frmCustomerBasket.getContentPane().add(lblSubtotal);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frmCustomerBasket.dispose();
				//CheckoutPage window = new CheckoutPage();
				//window.frmCheckOut.setVisible(true);
			}
		});
		label.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/checkoutBtn.png")));
		label.setBounds(276, 437, 111, 35);
		frmCustomerBasket.getContentPane().add(label);
		
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
				PurchaseCrops_Customers window = new PurchaseCrops_Customers();
				window.frmPurchaseCrops.setVisible(true);
			}
		});
		btnAddCrops.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnAddCrops.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/addCrop_sml.png")));
		menuBar.add(btnAddCrops);
	}
}
