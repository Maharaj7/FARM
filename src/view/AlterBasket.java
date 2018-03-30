package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import communication.Client_Customer;
import model.Basket;
import model.Crop;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AlterBasket {

	JFrame frame;
	public String cropName, custEmail,famEmail;
    public int quantity; 
    public float weight,cost;
    private JTextField textField;
    LoginScreen l = new LoginScreen();
 
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

				
				 Client_Customer cus = new Client_Customer();
				 
				 cus.sendAction("Remove basket item");
				  
				 cus.sendExactName(cropName);
				 
				 
				 
		 		 cus.receiveResponse();
				JOptionPane.showMessageDialog(null, cropName+" Removed");
				 frame.dispose();
			}
		});
		btnRemoveFromBasket.setBounds(77, 92, 240, 23);
		frame.getContentPane().add(btnRemoveFromBasket);
		
		JLabel lblUpdateQuantity = new JLabel("Update Quantity");
		lblUpdateQuantity.setBounds(142, 11, 89, 14);
		frame.getContentPane().add(lblUpdateQuantity);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(185, 67, 46, 14);
		frame.getContentPane().add(lblOr);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Client_Customer fam1 = new Client_Customer();
				fam1.sendAction("request all crops");
				ArrayList<Crop> list1 = new ArrayList<Crop>();
				
				ArrayList<Crop> crops1 = new ArrayList<Crop>();
			    fam1.sendCropList(crops1);

				fam1.receiveResponse();
				list1 = fam1.receiveCropData();
				int actualQuantity = 0;
				for(int i=0; i<list1.size(); i++)
				{
					if(list1.get(i).getName().compareToIgnoreCase(cropName)==0)
					{
						actualQuantity=list1.get(i).getQuantity();
					}
				}
				
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter quantity!!");
				}
				else if(Integer.parseInt(textField.getText()) > actualQuantity)
				{
					JOptionPane.showMessageDialog(null, "insufficient Quantity of Item ");
				}
				else{
					
					Client_Customer cus = new Client_Customer();
					cus.sendAction("Update Quantity");
					
					
					Basket basket = new Basket(famEmail,custEmail,cropName,Integer.parseInt(textField.getText()),cost,weight);
					
					cus.sendExactName(cropName);
					cus.sendBasket(basket);
					cus.receiveResponse();
	
					JOptionPane.showMessageDialog(null, "Quantity Updated!");
		               frame.dispose();
				}
				
			}
		});
		btnNewButton.setBounds(195, 33, 122, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(77, 36, 97, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
