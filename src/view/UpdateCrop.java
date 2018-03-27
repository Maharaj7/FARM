package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import communication.Client_Farmer;
import model.Crop;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateCrop {

	 JFrame frame;
	public JTextField textField;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
    public JLabel Image;
    public String email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCrop window = new UpdateCrop();
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
	public UpdateCrop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 317);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setBounds(39, 38, 57, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setEditable(true);
			}
		});
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(119, 38, 86, 20);
		frame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("Quantity");
		label_2.setBounds(39, 75, 57, 14);
		frame.getContentPane().add(label_2);
		
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
		frame.getContentPane().add(textField_2);
		
		JLabel label_3 = new JLabel("Weight");
		label_3.setBounds(39, 112, 57, 14);
		frame.getContentPane().add(label_3);
		
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
		frame.getContentPane().add(textField_3);
		
		JLabel label_4 = new JLabel("Cost");
		label_4.setBounds(39, 151, 57, 14);
		frame.getContentPane().add(label_4);
		
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
		frame.getContentPane().add(textField_4);

		
		/*File f = new File("img.jpg");
		BufferedImage image = ImageIO.read();
		ByteArrayOutputStream b =new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", b );
		byte[] imageInByte = b.toByteArray();*/
		
		JButton btnNewButton = new JButton("Update Crop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				{
					/*Client_Farmer fam11 = new Client_Farmer();
					fam11.sendAction("Update Crop");
					Crop crop = new Crop(email,imageInByte,textField.getText(),Float.parseFloat(textField_3.getText()),Float.parseFloat(textField_4.getText()),comboBox.getSelectedItem(),Integer.parseInt(textField_2.getText()));
					fam11.sendExactCropName(textField.getText());
					fam11.sendExactEmail(email);
					fam11.sendCrop(crop);
					fam11.receiveResponse();*/
				}
			}
		});
		btnNewButton.setBounds(179, 222, 125, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel imageLable = new JLabel("");
		imageLable.setBounds(283, 52, 114, 113);
		frame.getContentPane().add(imageLable);
	}
}
