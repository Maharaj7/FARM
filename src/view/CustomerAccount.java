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
import javax.swing.SwingConstants;

import controller.Client_Controller;

public class CustomerAccount {

	JFrame frame;
	private JTextField textField;
	private Client_Controller clientController;
	Client_Controller ls = new Client_Controller();
	 static float cFunds=0.0f;
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
		frame.setBounds(100, 100, 679, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Customer Account");
		frame.getContentPane().setLayout(null);
		
		JLabel lblFullNameOf = new JLabel(ls.getFname()+ " " +ls.getLname() + " Account");
		lblFullNameOf.setFont(new Font("Herculanum", Font.PLAIN, 20));
		lblFullNameOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullNameOf.setBounds(194, 50, 323, 29);
		frame.getContentPane().add(lblFullNameOf);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(274, 78, 176, 12);
		frame.getContentPane().add(separator);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance: $"+ls.getEarnings());
		lblCurrentBalance.setBounds(95, 268, 169, 16);
		frame.getContentPane().add(lblCurrentBalance);
		
		JLabel lblAddFunds = new JLabel("Enter Funds to be Added:");
		lblAddFunds.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		lblAddFunds.setBounds(274, 127, 267, 35);
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
		btnMyDashboard.setBounds(264, 329, 163, 29);
		frame.getContentPane().add(btnMyDashboard);
		
		textField = new JTextField();
		textField.setBounds(320, 174, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				clientController = new Client_Controller();
				cFunds =	clientController.addFundsToAccount(textField.getText(),btnAdd);
			}
		});
		btnAdd.setBounds(320, 202, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblNewBalance = new JLabel("New Balance: ");
		lblNewBalance.setBounds(461, 269, 152, 14);
		frame.getContentPane().add(lblNewBalance);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CustomerAccount.class.getResource("/resources/addFunds.jpg")));
		label.setBounds(0, 0, 679, 409);
		frame.getContentPane().add(label);
	}
	
	float returnFunds()
	{
		return cFunds;
	}
}
