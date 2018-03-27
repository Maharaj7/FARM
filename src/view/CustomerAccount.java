package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CustomerAccount {

	JFrame frame;
	private JTextField textField;
	 static float cFunds=0.0f;
	 LoginScreen ls = new LoginScreen();
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
		
		JLabel lblFullNameOf = new JLabel(ls.getFname()+ " " +ls.getLname() + " Account");
		lblFullNameOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullNameOf.setBounds(209, 38, 176, 16);
		frame.getContentPane().add(lblFullNameOf);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(209, 65, 176, 12);
		frame.getContentPane().add(separator);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance: $"+cFunds);
		lblCurrentBalance.setBounds(48, 198, 117, 16);
		frame.getContentPane().add(lblCurrentBalance);
		
		JLabel lblAddFunds = new JLabel("Enter Funds to be Added:");
		lblAddFunds.setBounds(231, 94, 131, 16);
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
		textField.setBounds(252, 121, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				float funds =0.0f;
				funds = funds + Float.parseFloat(textField.getText());
				cFunds = funds;
				JOptionPane.showMessageDialog(null, "Funds Added!");
				btnAdd.setEnabled(false);
				
				}
				catch(NullPointerException | NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Ivalid Value");
				}
			}
		});
		btnAdd.setBounds(252, 149, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblNewBalance = new JLabel("New Balance: ");
		lblNewBalance.setBounds(423, 199, 86, 14);
		frame.getContentPane().add(lblNewBalance);
	}
	float returnFunds()
	{
		return cFunds;
	}
}
