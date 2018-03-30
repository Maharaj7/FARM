package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;

import communication.Client_Farmer;
import controller.Client_Controller;
import model.Basket;

import java.awt.SystemColor;

public class FarmerDashBoard {

    public JFrame frmFarmerDashboard;
    String name,lname,email1;
    float earnings;
    Client_Controller ls = new Client_Controller();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FarmerDashBoard window = new FarmerDashBoard();
                    window.frmFarmerDashboard.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FarmerDashBoard() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmFarmerDashboard = new JFrame();
        frmFarmerDashboard.setTitle("Farmer DashBoard");
        frmFarmerDashboard.setBounds(100, 100, 772, 703);
        frmFarmerDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmFarmerDashboard.getContentPane().setLayout(null);
        
        JButton button_1 = new JButton("");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmFarmerDashboard.dispose();
                CropsDesktop window = new CropsDesktop();
				window.frmCropsDesktop.setVisible(true);
            }
        });
        button_1.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/crops.jpg")));
        button_1.setBounds(48, 461, 173, 131);
        frmFarmerDashboard.getContentPane().add(button_1);
        
        JLabel lblViewAllMy = new JLabel("View All my Customers");
        lblViewAllMy.setFont(new Font("Khmer MN", Font.PLAIN, 19));
        lblViewAllMy.setBounds(285, 604, 179, 35);
        frmFarmerDashboard.getContentPane().add(lblViewAllMy);
        
        JButton viewAllCustomersBtn = new JButton("");
        viewAllCustomersBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frmFarmerDashboard.dispose();
        		CustomerList window = new CustomerList();
				window.frame.setVisible(true);
        	}
        });
        viewAllCustomersBtn.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/veiwAllCustomers.png")));
        viewAllCustomersBtn.setBounds(307, 461, 145, 131);
        frmFarmerDashboard.getContentPane().add(viewAllCustomersBtn);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(48, 392, 669, 12);
        frmFarmerDashboard.getContentPane().add(separator);
        
        JLabel userDetailsLbl = new JLabel("User Details");
        userDetailsLbl.setForeground(Color.WHITE);
        userDetailsLbl.setFont(new Font("Khmer MN", Font.PLAIN, 19));
        userDetailsLbl.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/userIcon.png")));
        userDetailsLbl.setBounds(66, 93, 173, 29);
        frmFarmerDashboard.getContentPane().add(userDetailsLbl);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(46, 118, 671, 12);
        frmFarmerDashboard.getContentPane().add(separator_1);
        
        JLabel myDashboardLbl = new JLabel("My DashBoard");
        myDashboardLbl.setFont(new Font("Herculanum", Font.BOLD, 22));
        myDashboardLbl.setBounds(297, 29, 192, 29);
        frmFarmerDashboard.getContentPane().add(myDashboardLbl);
        
        JLabel logoutLbl = new JLabel("");
        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frmFarmerDashboard.dispose();
                LoginScreen window = new LoginScreen();
                window.frame.setVisible(true);
            }
        });
        logoutLbl.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/logoutButton.png")));
        logoutLbl.setBounds(610, 6, 107, 40);
        frmFarmerDashboard.getContentPane().add(logoutLbl);
        
        JLabel lblLiveChat = new JLabel("Live Chat");
        lblLiveChat.setFont(new Font("Khmer MN", Font.PLAIN, 19));
        lblLiveChat.setBounds(598, 610, 94, 23);
        frmFarmerDashboard.getContentPane().add(lblLiveChat);
        
        JButton button = new JButton("");
        button.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/LiveChatImg.png")));
        button.setBounds(558, 452, 145, 140);
        frmFarmerDashboard.getContentPane().add(button);
        
        JLabel lblCrops = new JLabel("Crops");
        lblCrops.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrops.setFont(new Font("Khmer MN", Font.PLAIN, 19));
        lblCrops.setBounds(93, 604, 83, 35);
        frmFarmerDashboard.getContentPane().add(lblCrops);
			
        JLabel label_1 = new JLabel("");
        label_1.setBounds(273, 150, 216, 148);
        frmFarmerDashboard.getContentPane().add(label_1);
       
		
		name = ls.getFname();
		lname = ls.getLname();
		 email1 = ls.getEmail();
		 String photo = ls.image();
		 earnings = ls.getEarnings();
		 
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(photo).getImage().getScaledInstance(label_1.getWidth(),label_1.getHeight(),Image.SCALE_SMOOTH));
	    label_1.setIcon(imageIcon);
	    
	    JLabel namelbl = new JLabel(name+" "+lname);
	    namelbl.setForeground(SystemColor.activeCaption);
	    namelbl.setBounds(66, 356, 153, 14);
	    frmFarmerDashboard.getContentPane().add(namelbl);
	    
	    JLabel emaillbl = new JLabel(email1);
	    emaillbl.setForeground(SystemColor.activeCaption);
	    emaillbl.setBounds(579, 356, 131, 14);
	    frmFarmerDashboard.getContentPane().add(emaillbl);
	    
	    Client_Farmer farmer = new Client_Farmer();
		farmer.sendAction("request customerPurchaseList");
		farmer.sendEmail();
		ArrayList<Basket> list = new ArrayList<Basket>();
		ArrayList<Basket> bas = new ArrayList<Basket>();
		farmer.sendCustomerPurchaseList(bas);
		farmer.receiveResponse();
		list = farmer.receiveCustomerPurchaseList();
		
		float totalCost=0.0f;
		for(int i=0; i<list.size(); i++)
		{
			totalCost = totalCost + list.get(i).getCost();
			
		}
	    
	    JLabel earninglbl = new JLabel(String.valueOf(totalCost));
	    earninglbl.setForeground(SystemColor.activeCaption);
	    earninglbl.setBounds(371, 356, 91, 14);
	    frmFarmerDashboard.getContentPane().add(earninglbl);
	    
	    JLabel lblName = new JLabel("Name");
	    lblName.setForeground(SystemColor.activeCaption);
	    lblName.setFont(new Font("Gurmukhi Sangam MN", Font.PLAIN, 14));
	    lblName.setBounds(66, 322, 68, 23);
	    frmFarmerDashboard.getContentPane().add(lblName);
	    
	    JLabel lblEarnings = new JLabel("Total Earnings");
	    lblEarnings.setForeground(SystemColor.activeCaption);
	    lblEarnings.setFont(new Font("Gurmukhi Sangam MN", Font.PLAIN, 14));
	    lblEarnings.setBounds(346, 322, 119, 23);
	    frmFarmerDashboard.getContentPane().add(lblEarnings);
	    
	    JLabel lblEmailAddress = new JLabel("Email Address");
	    lblEmailAddress.setForeground(SystemColor.activeCaption);
	    lblEmailAddress.setFont(new Font("Gurmukhi Sangam MN", Font.PLAIN, 14));
	    lblEmailAddress.setBounds(608, 322, 93, 23);
	    frmFarmerDashboard.getContentPane().add(lblEmailAddress);
	    
	    
	    JLabel label = new JLabel("");
	    label.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/bckgrd2.png")));
	    label.setBounds(0, 79, 772, 448);
	    frmFarmerDashboard.getContentPane().add(label);
        
    }
}
