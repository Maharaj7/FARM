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
		frame.setBounds(100, 100, 554, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		JLabel label = new JLabel("Customer Registration Page");
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBounds(85, 11, 337, 36);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Email Address:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(10, 90, 101, 16);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("First Name:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(10, 128, 89, 16);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Last Name:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(10, 163, 89, 16);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Password:");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(10, 203, 89, 16);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Select Image to Upload:");
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(393, 58, 154, 16);
		frame.getContentPane().add(label_5);
		
	
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 198, 203, 26);
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(141, 158, 203, 26);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 123, 203, 26);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(141, 85, 203, 26);
		frame.getContentPane().add(textField_2);
		
		
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(379, 128, 154, 115);
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
		button_1.setBounds(379, 87, 154, 23);
		frame.getContentPane().add(button_1);
		
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setForeground(Color.WHITE);
		lblRetypePassword.setBounds(10, 235, 121, 16);
		frame.getContentPane().add(lblRetypePassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(141, 230, 203, 26);
		frame.getContentPane().add(passwordField_1);
		
		JButton button = new JButton("Submit Registration Info");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
			
				if( textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
					|| passwordField.getText().isEmpty() ||passwordField_1.getText().isEmpty() || personImage == null )
				 {
					 JOptionPane.showMessageDialog(null, "Fields cannot be left empty");
				 }
				 else{
					
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
	                 
			
				 }
			}
		});
		button.setBounds(141, 283, 203, 29);
		frame.getContentPane().add(button);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/resources/next.jpg")));
		label_7.setBounds(0, 0, 538, 352);
		frame.getContentPane().add(label_7);
	}

}
