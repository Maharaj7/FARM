package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;

import communication.Client_Customer;

import model.Customer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CustomerRegistration {

	String fileName = null;
	byte[] personImage = null;
	JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegistration window = new CustomerRegistration();
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
	public CustomerRegistration() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 604, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		JLabel label = new JLabel("Customer Registration Page");
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBounds(116, 11, 337, 36);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Email Address:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(41, 90, 101, 16);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("First Name:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(41, 128, 89, 16);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Last Name:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(41, 163, 89, 16);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Password:");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(41, 203, 89, 16);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Select Image to Upload:");
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(434, 128, 154, 16);
		frame.getContentPane().add(label_5);
		
	
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 198, 203, 26);
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(172, 158, 203, 26);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(172, 123, 203, 26);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(172, 85, 203, 26);
		frame.getContentPane().add(textField_2);
		
		
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(406, 179, 154, 115);
		frame.getContentPane().add(label_6);
		
		JButton button_1 = new JButton("Choose Image");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				fileName = f.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(label_6.getWidth(),label_6.getHeight(),Image.SCALE_SMOOTH));
				label_6.setIcon(imageIcon);
				
				try{
					File image = new File(fileName);
					@SuppressWarnings("resource")
					FileInputStream fis = new FileInputStream(image);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readNum; (readNum = fis.read(buf))!=-1;)
					{
						bos.write(buf,0,readNum);
					}
					personImage = bos.toByteArray();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button_1.setBounds(408, 155, 154, 23);
		frame.getContentPane().add(button_1);
		
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setForeground(Color.WHITE);
		lblRetypePassword.setBounds(41, 235, 121, 16);
		frame.getContentPane().add(lblRetypePassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(172, 230, 203, 26);
		frame.getContentPane().add(passwordField_1);
		
		JButton button = new JButton("Register Now");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
			
				if( textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
					|| passwordField.getText().isEmpty() ||passwordField_1.getText().isEmpty() || personImage == null )
				 {
					 JOptionPane.showMessageDialog(null, "Fields cannot be left empty");
				 }
				else if(Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())){
					 Client_Customer cus = new Client_Customer();
					 cus.sendAction("Add Customer");
					
					 Customer customer = new Customer(textField_1.getText(),textField.getText(),textField_2.getText(),personImage,passwordField.getText());
					 cus.sendCustomer(customer);
		             cus.receiveResponse();
		         
				JOptionPane.showMessageDialog(null, "Data Saved!");
				textField.setText(" ");
				textField_1.setText(" ");
				textField_2.setText(" ");
				passwordField.setText("");
				passwordField_1.setText("");
				 frame.dispose();
				 LoginScreen login = new LoginScreen();
                 login.frame.setVisible(true);
				}
				else if(isValidEmailAddress(textField.getText())==false)
            	{
            		JOptionPane.showMessageDialog(null, "Email Address not valid");
            	}
				 else{
					
					 JOptionPane.showMessageDialog(null, "Passwords dont match!");
			
				 }
			}
		});
		button.setBounds(172, 283, 203, 29);
		frame.getContentPane().add(button);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/resources/bckgrd3.jpg")));
		label_7.setBounds(0, 0, 588, 352);
		frame.getContentPane().add(label_7);
	}
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
}
