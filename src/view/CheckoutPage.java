package view;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CheckoutPage {

	JFrame frmCheckOut;

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
		frmCheckOut.setBounds(100, 100, 610, 486);
		frmCheckOut.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCheckOut.getContentPane().setLayout(null);
		
		JLabel lblSummaryOfOrder = new JLabel("Summary of Order");
		lblSummaryOfOrder.setFont(new Font("Khmer MN", Font.PLAIN, 15));
		lblSummaryOfOrder.setBounds(24, 38, 130, 27);
		frmCheckOut.getContentPane().add(lblSummaryOfOrder);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(24, 63, 515, 12);
		frmCheckOut.getContentPane().add(separator);
		
		JLabel lblChoosePickupLocation = new JLabel("Choose Pickup Location:");
		lblChoosePickupLocation.setFont(new Font("Khmer MN", Font.PLAIN, 15));
		lblChoosePickupLocation.setIcon(new ImageIcon(CheckoutPage.class.getResource("/resources/pickUp.png")));
		lblChoosePickupLocation.setBounds(24, 255, 197, 37);
		frmCheckOut.getContentPane().add(lblChoosePickupLocation);
		
		JComboBox<String> pickUpLocationDdl = new JComboBox<String>();
		pickUpLocationDdl.setBounds(219, 257, 157, 27);
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
		btnEditOrder.setBounds(163, 317, 117, 29);
		frmCheckOut.getContentPane().add(btnEditOrder);
		
		JButton btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Order Successfully Placed,\nThank you.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnConfirmOrder.setBounds(341, 317, 117, 29);
		frmCheckOut.getContentPane().add(btnConfirmOrder);
		
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
