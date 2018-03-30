package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import communication.Client_Farmer;
import model.Crop;


import javax.swing.JFileChooser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class UpdateCrop {

	 JFrame frmCropUpdate;
	 static String fileName;
	 byte[] cropImage;
	public JTextField textField;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
    public JLabel iImage;
    public String email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCrop window = new UpdateCrop();
					window.frmCropUpdate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateCrop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCropUpdate = new JFrame();
		frmCropUpdate.setTitle("Crop Update");
		frmCropUpdate.setBounds(100, 100, 450, 297);
		frmCropUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCropUpdate.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Khmer MN", Font.PLAIN, 16));
		lblName.setForeground(new Color(255, 99, 71));
		lblName.setBounds(39, 38, 57, 20);
		frmCropUpdate.getContentPane().add(lblName);
		
		textField = new JTextField();
	
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(119, 38, 86, 20);
		frmCropUpdate.getContentPane().add(textField);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Khmer MN", Font.PLAIN, 16));
		lblQuantity.setForeground(new Color(255, 99, 71));
		lblQuantity.setBounds(39, 75, 68, 20);
		frmCropUpdate.getContentPane().add(lblQuantity);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_2.setEditable(true);
			}
		});
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(119, 75, 86, 20);
		frmCropUpdate.getContentPane().add(textField_2);
		
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setFont(new Font("Khmer MN", Font.PLAIN, 16));
		lblWeight.setForeground(new Color(255, 99, 71));
		lblWeight.setBounds(39, 112, 57, 20);
		frmCropUpdate.getContentPane().add(lblWeight);
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_3.setEditable(true);
			}
		});
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(119, 112, 86, 20);
		frmCropUpdate.getContentPane().add(textField_3);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Khmer MN", Font.PLAIN, 16));
		lblCost.setForeground(new Color(255, 99, 71));
		lblCost.setBounds(39, 151, 57, 20);
		frmCropUpdate.getContentPane().add(lblCost);
		
		textField_4 = new JTextField();
		textField_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_4.setEditable(true);
			}
		});
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(119, 151, 86, 20);
		frmCropUpdate.getContentPane().add(textField_4);
		
		
		iImage = new JLabel("");
		iImage.setBounds(267, 59, 141, 112);
		frmCropUpdate.getContentPane().add(iImage);
		
		
		JButton btnUpdateImage = new JButton("Update Image");
		btnUpdateImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				fileName = f.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(iImage.getWidth(),iImage.getHeight(),Image.SCALE_SMOOTH));
				iImage.setIcon(imageIcon);

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
					cropImage = bos.toByteArray();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}


			}
		});
		btnUpdateImage.setBounds(267, 183, 125, 23);
		frmCropUpdate.getContentPane().add(btnUpdateImage);

		
		JButton btnNewButton = new JButton("Update Crop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				{
					Client_Farmer fam11 = new Client_Farmer();
					fam11.sendAction("Update Crop");
					
					 File source = new File(fileName);
					 String path = "C:\\Users\\Maharaj\\git\\FARM\\src\\cropImages"+"\\"+textField.getText()+".jpg";
						File dest = new File(path);
				         try{
				        	 Files.copy(source.toPath(), dest.toPath());
				         }
				         catch(IOException e1)
				         {
				        	 e1.printStackTrace();
				         }
					
					
					String ava;
					if(Integer.parseInt(textField_2.getText())>=1)
					{
					   ava = "In Stock"	;
					}
					else{
						ava = "Not in Stock"	;
					}
					Crop crop = new Crop(email,path,textField.getText(),Float.parseFloat(textField_3.getText()),Float.parseFloat(textField_4.getText()),ava,Integer.parseInt(textField_2.getText()));
					fam11.sendExactCropName(textField.getText());
					fam11.sendExactEmail(email);
					fam11.sendCrop(crop);
					fam11.receiveResponse();
					
					JOptionPane.showMessageDialog(null, "Crop Updated");
					frmCropUpdate.dispose();
				}
			}
		});
		btnNewButton.setBounds(178, 222, 125, 23);
		frmCropUpdate.getContentPane().add(btnNewButton);
		

		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UpdateCrop.class.getResource("/resources/bckgrd4.jpg")));
		label.setBounds(0, 0, 450, 275);
		frmCropUpdate.getContentPane().add(label);
	}
	

}
