package view;

import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import communication.Client_Customer;
import communication.Client_Farmer;
import model.Basket;
import model.Crop;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckOut {

	JFrame frame;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	private JComboBox<String> comboBox;
	private JLabel lblQty;
	private JButton btnAddToBasket;
	private JLabel lblAvailable;
	public JTextField textField_4;
	public JLabel Image;
	public String email;
	public ImageIcon pic;

    LoginScreen l = new LoginScreen();
    private JTextField textField_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut window = new CheckOut();
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
	public CheckOut() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Add to Basket");
		frame.setBounds(100, 100, 416, 323);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 40, 57, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 101, 57, 14);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(10, 138, 57, 14);
		frame.getContentPane().add(lblWeight);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(10, 177, 57, 14);
		frame.getContentPane().add(lblCost);
		
		textField = new JTextField();
		textField.setBounds(90, 40, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(90, 101, 86, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(90, 138, 86, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(90, 177, 86, 20);
		frame.getContentPane().add(textField_3);
		
		textField_5 = new JTextField("0");
		textField_5.setBounds(243, 203, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		textField_5.setVisible(false);
		
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		
		String list[]={"1","2","3","4","5","6","7","8","9","10"};
		comboBox = new JComboBox<String>();
		for(int i=0; i<list.length;i++)
		{
			comboBox.addItem(list[i]);	
		}
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(arg0.getSource().equals(comboBox.getSelectedItem().equals("10+")))
				{
					comboBox.setVisible(false);
					textField_5.setVisible(true);
					
				}
				if(Integer.parseInt(textField_5.getText()) <= 9)
				{
					comboBox.setVisible(true);
					textField_5.setVisible(false);
				}
				
			}
		});
		
		comboBox.setBounds(243, 203, 57, 20);
		
		
		frame.getContentPane().add(comboBox);
		
		lblQty = new JLabel("Qty:");
		lblQty.setBounds(190, 206, 46, 14);
		frame.getContentPane().add(lblQty);
		
		Image = new JLabel("");
		Image.setBounds(227, 43, 125, 148);
		frame.getContentPane().add(Image);
		
		
		btnAddToBasket = new JButton("Add to Basket");
		btnAddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(textField_1.getText())<Integer.parseInt((String) comboBox.getSelectedItem()))
				{
					JOptionPane.showMessageDialog(null, "insufficient Quantity of Item ");
				}
				else{
						
					 
					Client_Customer cus = new Client_Customer();
					Basket basket = new Basket(email,l.getEmail(),textField.getText(),Integer.parseInt(textField_1.getText()),Float.parseFloat(textField_3.getText()),Float.parseFloat(textField_2.getText()));
					cus.sendAction("Add to Basket");
					cus.sendBasket(basket);
					cus.receiveResponse();
					
					
					JOptionPane.showMessageDialog(null, "Added to Basket!");
					frame.dispose();
				}
			}
		});
		btnAddToBasket.setBounds(213, 238, 139, 23);
		frame.getContentPane().add(btnAddToBasket);
		
		lblAvailable = new JLabel("Available");
		lblAvailable.setBounds(10, 68, 70, 14);
		frame.getContentPane().add(lblAvailable);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(90, 68, 86, 20);
		frame.getContentPane().add(textField_4);
		
		
		
		
	}
}
