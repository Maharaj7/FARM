package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import communication.Client_Farmer;
import model.Crop;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test {

	 JFrame frame;
      JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 643, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 35, 607, 294);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		

		  Client_Farmer  fam = new Client_Farmer();
			fam.sendAction("request crops");
			fam.sendEmail(); // sends person email to the server in order to query specific data
			ArrayList<Crop> list = new ArrayList<Crop>();
			
			ArrayList<Crop> crops = new ArrayList<Crop>();
		    fam.sendCropList(crops);

			fam.receiveResponse();
			list = fam.receiveCropData();
			
			
			DefaultTableModel model  = new DefaultTableModel();
			
			 table = new JTable(model){
		        @SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
	            public Class getColumnClass(int column)
	            {
	               switch (column)
	                        {
	                            case 5: return ImageIcon.class;
	                            default: return Object.class;
	                        }
	                    }}; 
	                    table.setRowHeight(120);
			
			 Object[] columnNames = new Object[6];
			 
			 columnNames[5]="Image";
			 columnNames[0]="Name";
			 columnNames[1]="Weight";
			 columnNames[2]="Cost";
			 columnNames[3]="Available";
			 columnNames[4]="Quantity";
		
			 model.setColumnIdentifiers(columnNames);
			 
			  Object[] row = new Object[6];

			  for(int i=0; i <list.size(); i++)
			{
				  if(list.get(i).getImage()	!= null)
				  {
					  ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage().getScaledInstance(190,160,Image.SCALE_SMOOTH));
					  row[5] = image; 
				  }
				  else{
					  row[5] = null;
				  }
				  
				  row[0] = list.get(i).getName();
				  row[1] = list.get(i).getWeight();
				  row[2] = list.get(i).getCostPerUnit();
				  row[3] = list.get(i).getAvailable();
				  row[4] = list.get(i).getQuantity();
				  
				   model.addRow(row); 
				   
			}
			

			  table.setModel(model);
			  JScrollPane  scrollPane = new JScrollPane(table);
				scrollPane.setBounds(39, 244, 545, -186);

				panel.setLayout(new BorderLayout());
			panel.add(scrollPane,BorderLayout.CENTER);
	}

}
