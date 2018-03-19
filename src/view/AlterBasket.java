package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import communication.Client_Customer;
import model.Basket;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterBasket {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterBasket window = new AlterBasket();
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
	public AlterBasket() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 378, 183);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnRemoveFromBasket = new JButton("Remove From Basket");
		btnRemoveFromBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_Customer cl = new Client_Customer();
				cl.sendAction("Update Quantity");
				Basket bas = new Basket();
				
				//we are here 
				JOptionPane.showMessageDialog(null, "Crop Removed");
			}
		});
		btnRemoveFromBasket.setBounds(64, 92, 240, 23);
		frame.getContentPane().add(btnRemoveFromBasket);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(142, 36, 76, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblUpdateQuantity = new JLabel("Update Quantity");
		lblUpdateQuantity.setBounds(142, 11, 89, 14);
		frame.getContentPane().add(lblUpdateQuantity);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(172, 67, 46, 14);
		frame.getContentPane().add(lblOr);
	}

}
