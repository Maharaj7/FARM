package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Client_Controller;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Font;

public class CheckOut {

	JFrame frame;
	public JTextField name;
	public JTextField quantity;
	public JTextField weight;
	public JTextField cost;
	private JLabel lblQty;
	private JButton btnAddToBasket;
	private JLabel lblAvailable;
	public JTextField textField_4;
	public JLabel Image;
	public String email;
	public ImageIcon pic;
	private Client_Controller clientController;

	//Client_Controller l = new Client_Controller();
	private JTextField quantityInput;
	private JLabel label;

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
		lblName.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		lblName.setForeground(new Color(0, 0, 139));
		lblName.setBounds(8, 40, 57, 20);
		frame.getContentPane().add(lblName);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		lblQuantity.setForeground(new Color(0, 0, 139));
		lblQuantity.setBounds(8, 101, 68, 20);
		frame.getContentPane().add(lblQuantity);

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		lblWeight.setForeground(new Color(0, 0, 139));
		lblWeight.setBounds(8, 138, 57, 20);
		frame.getContentPane().add(lblWeight);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		lblCost.setForeground(new Color(0, 0, 139));
		lblCost.setBounds(8, 177, 57, 20);
		frame.getContentPane().add(lblCost);

		name = new JTextField();
		name.setBounds(90, 40, 86, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);

		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(90, 101, 86, 20);
		frame.getContentPane().add(quantity);

		weight = new JTextField();
		weight.setColumns(10);
		weight.setBounds(90, 138, 86, 20);
		frame.getContentPane().add(weight);

		cost = new JTextField();
		cost.setColumns(10);
		cost.setBounds(90, 177, 86, 20);
		frame.getContentPane().add(cost);

		quantityInput = new JTextField();
		quantityInput.setBounds(244, 213, 86, 21);
		frame.getContentPane().add(quantityInput);
		quantityInput.setColumns(10);

		name.setEditable(false);
		quantity.setEditable(false);
		weight.setEditable(false);
		cost.setEditable(false);

		lblQty = new JLabel("Qty:");
		lblQty.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		lblQty.setForeground(new Color(0, 0, 139));
		lblQty.setBounds(204, 217, 39, 17);
		frame.getContentPane().add(lblQty);

		Image = new JLabel("");
		Image.setBounds(227, 43, 125, 148);
		frame.getContentPane().add(Image);

		btnAddToBasket = new JButton("Add to Basket");
		btnAddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientController = new Client_Controller();
				clientController.addToBasket(email, name.getText(), quantityInput.getText(),
						quantity.getText(), cost.getText(), weight.getText(), frame);
			}
		});
		btnAddToBasket.setBounds(146, 261, 139, 23);
		frame.getContentPane().add(btnAddToBasket);

		lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		lblAvailable.setForeground(new Color(0, 0, 139));
		lblAvailable.setBounds(8, 68, 70, 20);
		frame.getContentPane().add(lblAvailable);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(90, 68, 86, 20);
		frame.getContentPane().add(textField_4);

		label = new JLabel("");
		label.setIcon(new ImageIcon(CheckOut.class.getResource("/resources/basketBgrd3-Edit.jpg")));
		label.setBounds(0, 0, 416, 301);
		frame.getContentPane().add(label);

	}
}
