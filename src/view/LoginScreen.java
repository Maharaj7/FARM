package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import communication.Client_Customer;
import communication.Client_Farmer;
import model.Customer;
import model.Farmer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
public class LoginScreen {

    private static byte[] pImage;
    private static String email,fname,lname;
    JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    static String stream;
    private JButton btnSignUpAsFarmer;
    private JLabel lblSignIn;
    private JLabel label;
    private JLabel lblNewUser;
    private JButton btnSignUpAsCus;
    private JButton btnLoginAsCustomer;
    private JLabel lblSignIn_1;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginScreen window = new LoginScreen();
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
    public LoginScreen() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Free Agri-Relations Management");
        frame.getContentPane().setBackground(SystemColor.activeCaption);
        frame.setBounds(100, 100, 645, 468);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setForeground(Color.LIGHT_GRAY);
        panel.setBounds(144, 78, 354, 319);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
    
        
        
        textField = new JTextField();
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                if(textField.getText().equals("Email Address")){
                    textField.setText("");
                } 
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().equals("")){
                    textField.setText("Email Address");
                }
            }
        });
        textField.setBounds(68, 36, 242, 33);
        panel.add(textField);
        
        
        textField.setColumns(10);
        
    
        
        passwordField = new JPasswordField();
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField.getText().equals("Password")){
                    textField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().equals("")){
                    textField.setText("Password");
                }
            }
        });
        passwordField.setBounds(68, 81, 242, 33);
        panel.add(passwordField);
        
        btnSignUpAsFarmer = new JButton("Sign Up as Farmer");
        btnSignUpAsFarmer.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        btnSignUpAsFarmer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	FarmerRegistrationPage window = new FarmerRegistrationPage();
                window.frmFarmersRegistrationPage_1.setVisible(true);
            }
        });
        
        btnSignUpAsFarmer.setForeground(new Color(128, 128, 0));
        btnSignUpAsFarmer.setBackground(SystemColor.textHighlight);
        btnSignUpAsFarmer.setBounds(77, 214, 220, 33);
        panel.add(btnSignUpAsFarmer);
        
        lblNewUser = new JLabel("New User?");
        lblNewUser.setFont(new Font("Khmer MN", Font.PLAIN, 17));
        lblNewUser.setBackground(new Color(0, 0, 0));
        lblNewUser.setForeground(new Color(0, 0, 128));
        lblNewUser.setBounds(27, 171, 117, 42);
        panel.add(lblNewUser);
        
        btnSignUpAsCus = new JButton("Sign Up as Customer");
        btnSignUpAsCus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerRegistration window = new CustomerRegistration();
                window.frame.setVisible(true);
            }
        });
        btnSignUpAsCus.setForeground(new Color(128, 128, 0));
        btnSignUpAsCus.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        btnSignUpAsCus.setBackground(SystemColor.textHighlight);
        btnSignUpAsCus.setBounds(77, 259, 220, 33);
        panel.add(btnSignUpAsCus);
        
        btnLoginAsCustomer = new JButton("Login as Customer");
        btnLoginAsCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try{
            	 /**
                 * After requesting a login to the server it responses 
                 * by querying the database and returning 
                 * required data that is used to display the persons
                 * image and name once the specific Dash board is called
                 * */
                 Client_Customer cus = new Client_Customer();

                 cus.sendAction("Request Login");            
                 Customer data = new Customer();
                 
                  @SuppressWarnings("deprecation")
				Customer customer = new Customer(textField.getText(),passwordField.getText());
                   cus.sendCustomer(customer);        
                    cus.receiveResponse(); 
                	 data = cus.receiveData(); // Data is returned by a Customer Object and is stored in data;
                     
                     email = data.getEmail();
                     fname = data.getfName();
                     lname = data.getlName();
                     pImage = data.getPhoto();
                     frame.dispose();
                    
                    CustomerDashBoard window = new CustomerDashBoard();
                    window.frame.setVisible(true);
               }catch(NullPointerException q)
               {
            	   JOptionPane.showMessageDialog(null, "Email and Password is not correct");
            	   frame.setVisible(true);
            	   textField.setText("");
            	   passwordField.setText("");
               }
                    
                
            }
        });
        btnLoginAsCustomer.setForeground(new Color(128, 128, 0));
        btnLoginAsCustomer.setBackground(Color.BLUE);
        btnLoginAsCustomer.setBounds(179, 126, 152, 34);
        panel.add(btnLoginAsCustomer);
        
        lblSignIn_1 = new JLabel("Sign In");
        lblSignIn_1.setFont(new Font("Khmer MN", Font.PLAIN, 17));
        lblSignIn_1.setForeground(new Color(0, 0, 128));
        lblSignIn_1.setBackground(Color.BLACK);
        lblSignIn_1.setBounds(32, 6, 78, 29);
        panel.add(lblSignIn_1);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(LoginScreen.class.getResource("/resources/email.png")));
        lblNewLabel.setBounds(42, 36, 24, 29);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(LoginScreen.class.getResource("/resources/password.png")));
        lblNewLabel_1.setBounds(42, 81, 30, 25);
        panel.add(lblNewLabel_1);
        
        JButton btnLoginAsFarmer = new JButton("Login as Farmer");
        btnLoginAsFarmer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
               	 /**
                    * After requesting a login to the server it responses 
                    * by querying the database and returning 
                    * required data that is used to display the persons
                    * image and name once the specific Dash board is called
                    * */
              
        			  Client_Farmer cus = new Client_Farmer();
                       cus.sendAction("farmer");            
                       Farmer data = new Farmer();
                       
                        @SuppressWarnings("deprecation")
      				Farmer customer = new Farmer(textField.getText(),passwordField.getText());
                         cus.sendFarmer(customer);        
                          cus.receiveResponse(); 
                      	 data = cus.receiveData(); // Data is returned by a Customer Object and is stored in data;
                           
                           email = data.getEmail();
                           fname = data.getfName();
                           lname = data.getlName();
                           pImage = data.getPhoto();
                         
                           
                           frame.dispose();
                           
                        FarmerDashBoard window = new FarmerDashBoard();
                        window.frmFarmerDashboard.setVisible(true);
                        
                       
                  }catch(NullPointerException q)
                  {
                	  JOptionPane.showMessageDialog(null, "Email and Password is not correct");
               	   frame.setVisible(true);
               	   textField.setText("");
               	   passwordField.setText("");
                  }
        	}
        });
        btnLoginAsFarmer.setForeground(new Color(128, 128, 0));
        btnLoginAsFarmer.setBackground(Color.BLUE);
        btnLoginAsFarmer.setBounds(17, 125, 152, 34);
        panel.add(btnLoginAsFarmer);
        
        lblSignIn = new JLabel("Free Agri-Relations Management (FARM)");
        lblSignIn.setForeground(Color.WHITE);
        lblSignIn.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblSignIn.setBounds(95, 26, 463, 32);
        frame.getContentPane().add(lblSignIn);
        
        label = new JLabel("");
        label.setIcon(new ImageIcon(LoginScreen.class.getResource("/resources/next.jpg")));
        label.setBounds(0, 0, 645, 448);
        frame.getContentPane().add(label);
        
    
    }
    
    public byte[] image()
    {
        return pImage;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getFname()
    {
        return fname;
    }
    
    public String getLname()
    {
        return lname;
    }
}
