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
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;

public class FarmerDashBoard {

    JFrame frmFarmerDashboard;
    String name,lname,email1;
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
        frmFarmerDashboard.setBounds(100, 100, 768, 671);
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
        button_1.setBounds(48, 419, 173, 131);
        frmFarmerDashboard.getContentPane().add(button_1);
        
        JLabel lblViewAllMy = new JLabel("View All my Customers");
        lblViewAllMy.setFont(new Font("Khmer MN", Font.PLAIN, 19));
        lblViewAllMy.setBounds(285, 562, 220, 35);
        frmFarmerDashboard.getContentPane().add(lblViewAllMy);
        
        JButton viewAllCustomersBtn = new JButton("");
        viewAllCustomersBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        viewAllCustomersBtn.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/veiwAllCustomers.png")));
        viewAllCustomersBtn.setBounds(307, 419, 145, 131);
        frmFarmerDashboard.getContentPane().add(viewAllCustomersBtn);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(48, 333, 669, 12);
        frmFarmerDashboard.getContentPane().add(separator);
        
        JLabel userDetailsLbl = new JLabel("User Details");
        userDetailsLbl.setForeground(Color.WHITE);
        userDetailsLbl.setFont(new Font("Khmer MN", Font.PLAIN, 19));
        userDetailsLbl.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/userIcon.png")));
        userDetailsLbl.setBounds(66, 93, 173, 29);
        frmFarmerDashboard.getContentPane().add(userDetailsLbl);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(46, 118, 646, 12);
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
        lblLiveChat.setBounds(598, 568, 94, 23);
        frmFarmerDashboard.getContentPane().add(lblLiveChat);
        
        JButton button = new JButton("");
        button.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/LiveChatImg.png")));
        button.setBounds(558, 410, 145, 140);
        frmFarmerDashboard.getContentPane().add(button);
        
        JLabel lblCrops = new JLabel("Crops");
        lblCrops.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrops.setFont(new Font("Khmer MN", Font.PLAIN, 19));
        lblCrops.setBounds(93, 562, 83, 35);
        frmFarmerDashboard.getContentPane().add(lblCrops);
			
        JLabel label_1 = new JLabel("");
        label_1.setBounds(301, 141, 163, 140);
        frmFarmerDashboard.getContentPane().add(label_1);
        LoginScreen ls = new LoginScreen();
		
		name = ls.getFname();
		lname = ls.getLname();
		 email1 = ls.getEmail();
		 byte[] photo = ls.image();
		 
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(photo).getImage().getScaledInstance(label_1.getWidth(),label_1.getHeight(),Image.SCALE_SMOOTH));
	    label_1.setIcon(imageIcon);
	    
	    JLabel label = new JLabel("");
	    label.setIcon(new ImageIcon(FarmerDashBoard.class.getResource("/resources/bckgrd2.png")));
	    label.setBounds(0, 0, 752, 499);
	    frmFarmerDashboard.getContentPane().add(label);
			
        
    }
}
