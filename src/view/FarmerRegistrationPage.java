package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import communication.Client_Farmer;
import model.Farmer;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FarmerRegistrationPage {


    String fileName = null;
    byte[] personImage = null;
    JFrame frmFarmersRegistrationPage_1;
    private JTextField emaiTextField;
    private JTextField fNameTextField;
    private JTextField lNameTextField;
    private JTextField farmAddressTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FarmerRegistrationPage window = new FarmerRegistrationPage();
                    window.frmFarmersRegistrationPage_1.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FarmerRegistrationPage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmFarmersRegistrationPage_1 = new JFrame();
        frmFarmersRegistrationPage_1.setTitle("Farmer’s Registration Page");
        frmFarmersRegistrationPage_1.setBounds(100, 100, 661, 528);
        frmFarmersRegistrationPage_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmFarmersRegistrationPage_1.getContentPane().setLayout(null);
        
        JLabel lblFarmerReg = new JLabel("Farmer Registration Page");
        lblFarmerReg.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblFarmerReg.setIcon(null);
        lblFarmerReg.setBounds(157, 24, 254, 29);
        frmFarmersRegistrationPage_1.getContentPane().add(lblFarmerReg);
        
        JLabel lblUploadImageOf = new JLabel("Select Image to Upload:");
        lblUploadImageOf.setBounds(461, 151, 158, 16);
        frmFarmersRegistrationPage_1.getContentPane().add(lblUploadImageOf);
        
       
        
        JLabel lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setBounds(25, 91, 105, 16);
        frmFarmersRegistrationPage_1.getContentPane().add(lblEmailAddress);
        
        emaiTextField = new JTextField();
        emaiTextField.setBounds(157, 86, 239, 26);
        frmFarmersRegistrationPage_1.getContentPane().add(emaiTextField);
        emaiTextField.setColumns(10);
        
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(25, 139, 92, 16);
        frmFarmersRegistrationPage_1.getContentPane().add(lblFirstName);
        
        fNameTextField = new JTextField();
        fNameTextField.setBounds(157, 134, 239, 26);
        frmFarmersRegistrationPage_1.getContentPane().add(fNameTextField);
        fNameTextField.setColumns(10);
        
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(25, 189, 92, 16);
        frmFarmersRegistrationPage_1.getContentPane().add(lblLastName);
        
        lNameTextField = new JTextField();
        lNameTextField.setColumns(10);
        lNameTextField.setBounds(157, 184, 239, 26);
        frmFarmersRegistrationPage_1.getContentPane().add(lNameTextField);
        
        JLabel lblFarmAddress = new JLabel("Farm Address:");
        lblFarmAddress.setBounds(25, 232, 105, 16);
        frmFarmersRegistrationPage_1.getContentPane().add(lblFarmAddress);
        
        farmAddressTextField = new JTextField();
        farmAddressTextField.setColumns(10);
        farmAddressTextField.setBounds(157, 227, 239, 26);
        frmFarmersRegistrationPage_1.getContentPane().add(farmAddressTextField);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(25, 281, 77, 16);
        frmFarmersRegistrationPage_1.getContentPane().add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(157, 276, 239, 26);
        frmFarmersRegistrationPage_1.getContentPane().add(passwordField);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setBounds(25, 330, 120, 16);
        frmFarmersRegistrationPage_1.getContentPane().add(lblConfirmPassword);
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(157, 325, 239, 26);
        frmFarmersRegistrationPage_1.getContentPane().add(confirmPasswordField);
        
        JLabel imgLabel = new JLabel("");
        imgLabel.setBounds(465, 214, 154, 115);
        frmFarmersRegistrationPage_1.getContentPane().add(imgLabel);

        
        JButton chooseImgButton = new JButton("Choose Image");
        chooseImgButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                fileName = f.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(imgLabel.getWidth(),imgLabel.getHeight(),Image.SCALE_SMOOTH));
                imgLabel.setIcon(imageIcon);
                
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
        chooseImgButton.setBounds(461, 179, 154, 23);
        frmFarmersRegistrationPage_1.getContentPane().add(chooseImgButton);
        
        JButton btnRegister = new JButton("Register Now");
        btnRegister.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
            	if( fNameTextField.getText().isEmpty() || lNameTextField.getText().isEmpty() || farmAddressTextField.getText().isEmpty()
    					|| passwordField.getText().isEmpty() ||confirmPasswordField.getText().isEmpty() || emaiTextField.getText().isEmpty() )
    				 {
    					 JOptionPane.showMessageDialog(null, "Fields cannot be left empty");
    				 }
    				 else{
    					
    						 Client_Farmer cus = new Client_Farmer();
    						 cus.sendAction("Add Farmer");
    						
    						 Farmer farmer = new Farmer(fNameTextField.getText(),lNameTextField.getText(),emaiTextField.getText(),farmAddressTextField.getText(),passwordField.getText(),personImage);
    						 cus.sendFarmer(farmer);
    			             cus.receiveResponse();
    			         
    					JOptionPane.showMessageDialog(null, "Data Saved!");
    					fNameTextField.setText(" ");
    					lNameTextField.setText(" ");
    					farmAddressTextField.setText(" ");
    					passwordField.setText("");
    					emaiTextField.setText("");
    					confirmPasswordField.setText("");
    	                 
    			
    				 }
            }
        });
        btnRegister.setBounds(289, 401, 133, 29);
        frmFarmersRegistrationPage_1.getContentPane().add(btnRegister);
        JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FarmerRegistrationPage.class.getResource("/resources/farm2.jpg")));
		label.setBounds(0, 0, 645, 506);
		frmFarmersRegistrationPage_1.getContentPane().add(label);
    }
}
