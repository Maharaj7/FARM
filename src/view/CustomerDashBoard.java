package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import java.awt.Font;

//import controller.SqlConnection;


public class CustomerDashBoard {

	public JFrame frame;
    String name,lname,email1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDashBoard window = new CustomerDashBoard();
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
	public CustomerDashBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 739, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Customer DashBoard");
		
		JButton addFundsbtn = new JButton("");
		addFundsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CustomerAccount window = new CustomerAccount();
				window.frame.setVisible(true);
			}
		});
		addFundsbtn.setBackground(Color.WHITE);
		addFundsbtn.setIcon(new ImageIcon(CustomerDashBoard.class.getResource("/resources/addFunds.png")));
		addFundsbtn.setBounds(51, 359, 161, 151);
		frame.getContentPane().add(addFundsbtn);
		
		JButton searchCropsBtn = new JButton("");
		searchCropsBtn.setToolTipText("Purchase crops here");
		
		searchCropsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				PurchaseCrops_Customers window = new PurchaseCrops_Customers();
				window.frmPurchaseCrops.setVisible(true);
			}
		});
		
		searchCropsBtn.setIcon(new ImageIcon(CustomerDashBoard.class.getResource("/resources/searchCrops.png")));
		searchCropsBtn.setBounds(290, 359, 168, 151);
		frame.getContentPane().add(searchCropsBtn);
		
		JButton myBasketBtn = new JButton("");
		myBasketBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CustomerBasket window = new CustomerBasket();
				window.frmCustomerBasket.setVisible(true);
			}
		});
		
		
		myBasketBtn.setIcon(new ImageIcon(CustomerDashBoard.class.getResource("/resources/myBasket.png")));
		myBasketBtn.setBounds(523, 359, 154, 151);
		frame.getContentPane().add(myBasketBtn);
		
		JLabel lblAddFundsTo = new JLabel("Add Funds to Account");
		lblAddFundsTo.setFont(new Font("Khmer MN", Font.PLAIN, 19));
		lblAddFundsTo.setBounds(31, 526, 218, 29);
		frame.getContentPane().add(lblAddFundsTo);
		
		JLabel lblSearchCrops = new JLabel("Search Crops");
		lblSearchCrops.setFont(new Font("Khmer MN", Font.PLAIN, 19));
		lblSearchCrops.setBounds(323, 529, 135, 22);
		frame.getContentPane().add(lblSearchCrops);
		
		JLabel lblMyBasket = new JLabel("My Basket");
		lblMyBasket.setFont(new Font("Khmer MN", Font.PLAIN, 19));
		lblMyBasket.setBounds(550, 529, 99, 23);
		frame.getContentPane().add(lblMyBasket);
		
		JLabel lblUserDetails = new JLabel("User Details");
		lblUserDetails.setFont(new Font("Khmer MN", Font.PLAIN, 19));
		lblUserDetails.setForeground(new Color(255, 255, 255));
		lblUserDetails.setIcon(new ImageIcon(CustomerDashBoard.class.getResource("/resources/userIcon.png")));
		lblUserDetails.setBounds(51, 93, 161, 29);
		frame.getContentPane().add(lblUserDetails);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 118, 646, 12);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(41, 325, 646, 12);
		frame.getContentPane().add(separator_1);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.dispose();
				LoginScreen window = new LoginScreen();
				window.frame.setVisible(true);
			}
		});
		label.setIcon(new ImageIcon(CustomerDashBoard.class.getResource("/resources/logoutButton.png")));
		label.setBounds(592, 6, 107, 40);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(267, 125, 203, 156);
		frame.getContentPane().add(label_1);
		
		
        LoginScreen ls = new LoginScreen();
		
		name = ls.getFname();
		lname = ls.getLname();
		 email1 = ls.getEmail();
		 byte[] photo = ls.image();
		 
		// ImageIcon imageIcon = new ImageIcon(new ImageIcon(photo).getImage().getScaledInstance(label_1.getWidth(),label_1.getHeight(),Image.SCALE_SMOOTH));
			//label_1.setIcon(imageIcon);
			
		
		JLabel lblNewLabel = new JLabel(name+" "+lname);
		lblNewLabel.setBounds(91, 300, 125, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label_2 = new JLabel(email1);
		label_2.setBounds(507, 300, 170, 23);
		frame.getContentPane().add(label_2);

		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(CustomerDashBoard.class.getResource("/resources/bckgrd1.jpg")));
		label_3.setBounds(20, 21, 692, 554);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("My DashBoard");
		label_4.setFont(new Font("Herculanum", Font.BOLD, 22));
		label_4.setBounds(266, 23, 192, 29);
		frame.getContentPane().add(label_4);
	}
}
