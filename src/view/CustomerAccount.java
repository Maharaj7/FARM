package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CustomerAccount {

	JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerAccount window = new CustomerAccount();
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
	public CustomerAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 323);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Customer Account");
		frame.getContentPane().setLayout(null);
		
		JLabel lblFullNameOf = new JLabel("Full Name of Customer");
		lblFullNameOf.setBounds(209, 38, 176, 16);
		frame.getContentPane().add(lblFullNameOf);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(209, 65, 176, 12);
		frame.getContentPane().add(separator);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance:");
		lblCurrentBalance.setBounds(46, 181, 117, 16);
		frame.getContentPane().add(lblCurrentBalance);
		
		JLabel lblAddFunds = new JLabel("Add Funds:");
		lblAddFunds.setBounds(261, 88, 98, 16);
		frame.getContentPane().add(lblAddFunds);
		
		JButton btnMyDashboard = new JButton("My DashBoard");
		btnMyDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CustomerDashBoard window = new CustomerDashBoard();
				window.frame.setVisible(true);
			}
		});
		
		btnMyDashboard.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnMyDashboard.setIcon(new ImageIcon(CustomerAccount.class.getResource("/resources/backIcon.png")));
		btnMyDashboard.setBounds(230, 244, 163, 29);
		frame.getContentPane().add(btnMyDashboard);
		
		textField = new JTextField();
		textField.setBounds(251, 115, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAdd.setBounds(251, 143, 89, 23);
		frame.getContentPane().add(btnAdd);
	}
}
