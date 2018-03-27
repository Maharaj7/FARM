package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import communication.Client_Farmer;
import model.Crop;
import model.Farmer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SearchCrops {

     JFrame frame;
	private JTable table;
	private JTable table1;
	ImageIcon icon;
	CheckOut checkOut = new CheckOut();
	CheckOut checkOut1 = new CheckOut();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCrops window = new SearchCrops();
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
	public SearchCrops() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 845, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 829, 454);
		frame.getContentPane().add(tabbedPane);
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 5, 804, 402);
		panel2.setLayout(null);
		
		

		    Client_Farmer  fam = new Client_Farmer();
			fam.sendAction("request all crops");
			ArrayList<Crop> list = new ArrayList<Crop>();
			
			ArrayList<Crop> crops = new ArrayList<Crop>();
		    fam.sendCropList(crops);

			fam.receiveResponse();
			list = fam.receiveCropData();
			
			
			DefaultTableModel model  = new DefaultTableModel();
			 table = new JTable(model){
				 @Override
					public boolean isCellEditable(int row, int column) // Disables cells in the table from being edited.
					{
						return false;
					}
		        @SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
	            public Class getColumnClass(int column)
	            {
	               switch (column)
	                        {
	                            case 6: return ImageIcon.class;
	                            default: return Object.class;
	                        }
	                    }}; 
			 table.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		
			 		int index = table.getSelectedRow();
			 		TableModel tmodel = table.getModel();
			 		ImageIcon image;
			 		image = (ImageIcon) tmodel.getValueAt(index, 6);
			 		checkOut.Image.setIcon(image);
			 		icon = (ImageIcon) checkOut.Image.getIcon();
			 		
			 		
			 		String name = tmodel.getValueAt(index, 0).toString();
			 		String quantity = tmodel.getValueAt(index, 4).toString();
			 		String weight = tmodel.getValueAt(index, 1).toString();
			 		String cost = tmodel.getValueAt(index, 2).toString();
			 		String ava = tmodel.getValueAt(index, 3).toString();
			 		String email = tmodel.getValueAt(index, 5).toString();
			 		checkOut.frame.setVisible(true);
			 		checkOut.textField.setText(name);
			 		checkOut.textField_1.setText(quantity);
			 		checkOut.textField_2.setText(weight);
			 		checkOut.textField_3.setText(cost);
			 		checkOut.textField_4.setText(ava);
			 		checkOut.email = email;
			 		
			 	}
			 });
			
	                    table.setRowHeight(120);
			
			 Object[] columnNames = new Object[7];
			 
			 columnNames[6]="Image";
			 columnNames[0]="Name";
			 columnNames[1]="Weight";
			 columnNames[2]="Cost";
			 columnNames[3]="Available";
			 columnNames[4]="Quantity";
			 columnNames[5]="Email";
		
			 model.setColumnIdentifiers(columnNames);
			 
			  Object[] row = new Object[7];

			  for(int i=0; i <list.size(); i++)
			{
				  if(list.get(i).getImage()	!= null)
				  {
					  ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage().getScaledInstance(190,160,Image.SCALE_SMOOTH));
					  row[6] = image; 
				  }
				  else{
					  row[6] = null;
				  }
				  
				  row[0] = list.get(i).getName();
				  row[1] = list.get(i).getWeight();
				  row[2] = list.get(i).getCostPerUnit();
				  row[3] = list.get(i).getAvailable();
				  row[4] = list.get(i).getQuantity();
				  row[5] = list.get(i).getEmail();
				  
				   model.addRow(row); 
				   
			}
			

			  table.setModel(model);
			  JScrollPane  scrollPane = new JScrollPane(table);
				scrollPane.setBounds(39, 244, 545, -186);

				panel2.setLayout(new BorderLayout());
			panel2.add(scrollPane,BorderLayout.CENTER);
			
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(panel2);
		tabbedPane.addTab("View All Crops", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Filter by Farmers", null, panel_1, null);
		panel_1.setLayout(null);
		
		Client_Farmer fam1 = new Client_Farmer();
		fam1.sendAction("request farmer list");
		ArrayList<Farmer> list1 = new ArrayList<Farmer>();
		
		ArrayList<Farmer> crops1 = new ArrayList<Farmer>();
	    fam1.sendFarmerList(crops1);
 
		fam1.receiveResponse();
		list1 = fam1.receiveFarmerData();
	
		JComboBox<String> comboBox = new JComboBox<String>();
		 for(int i=0; i<list1.size();i++)
			{
				comboBox.addItem(list1.get(i).getEmail());
			}
		comboBox.setBounds(281, 12, 133, 20);
		panel_1.add(comboBox);
		JPanel panel_2 = new JPanel();
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Client_Farmer fam = new Client_Farmer();
				fam.sendAction("request crops");
				fam.sendExactEmail(comboBox.getSelectedItem().toString());
				ArrayList<Crop> list = new ArrayList<Crop>();
				
				ArrayList<Crop> crops = new ArrayList<Crop>();
			    fam.sendCropList(crops);

				fam.receiveResponse();
				list = fam.receiveCropData();
				
				
				DefaultTableModel model  = new DefaultTableModel();
				 table1 = new JTable(model){
					 @Override
						public boolean isCellEditable(int row, int column) // Disables cells in the table from being edited.
						{
							return false;
						}
			        @SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
		            public Class getColumnClass(int column)throws ArrayIndexOutOfBoundsException
		            {
		               switch (column)
		                        {
		                            case 6: return ImageIcon.class;
		                            default: return Object.class;
		                        }
		                    }}; 
				 table1.addMouseListener(new MouseAdapter() {
				 	@Override
				 	public void mouseClicked(MouseEvent e) {
				 		
				 		int index = table1.getSelectedRow();
				 		TableModel tmodel = table1.getModel();
				 		ImageIcon image;
				 		image = (ImageIcon) tmodel.getValueAt(index, 6);
				 		checkOut1.Image.setIcon(image);
						
				 		
				 		String name = tmodel.getValueAt(index, 0).toString();
				 		String quantity = tmodel.getValueAt(index, 4).toString();
				 		String weight = tmodel.getValueAt(index, 1).toString();
				 		String cost = tmodel.getValueAt(index, 2).toString();
				 		String ava = tmodel.getValueAt(index, 3).toString();
				 		String email = tmodel.getValueAt(index, 5).toString();
				 		checkOut1.frame.setVisible(true);
				 		checkOut1.textField.setText(name);
				 		checkOut1.textField_1.setText(quantity);
				 		checkOut1.textField_2.setText(weight);
				 		checkOut1.textField_3.setText(cost);
				 		checkOut1.textField_4.setText(ava);
				 		checkOut1.email = email;
				 		
				 	}
				 });
				
		                    table1.setRowHeight(120);
				
				 Object[] columnNames = new Object[7];
				 
				 columnNames[6]="Image";
				 columnNames[0]="Name";
				 columnNames[1]="Weight";
				 columnNames[2]="Cost";
				 columnNames[3]="Available";
				 columnNames[4]="Quantity";
				 columnNames[5]="Email";
			
				 model.setColumnIdentifiers(columnNames);
				 
				  Object[] row = new Object[7];

				  for(int i=0; i <list.size(); i++)
				{
					  if(list.get(i).getImage()	!= null)
					  {
						  ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage().getScaledInstance(190,160,Image.SCALE_SMOOTH));
						  row[6] = image; 
					  }
					  else{
						  row[6] = null;
					  }
					  
					  row[0] = list.get(i).getName();
					  row[1] = list.get(i).getWeight();
					  row[2] = list.get(i).getCostPerUnit();
					  row[3] = list.get(i).getAvailable();
					  row[4] = list.get(i).getQuantity();
					  row[5] = list.get(i).getEmail();
					  
					   model.addRow(row); 
					   
				}
				  table1.setModel(model);
				  JScrollPane  scrollPane = new JScrollPane(table1);
					scrollPane.setBounds(39, 244, 545, -186);
					
					panel_2.setLayout(new BorderLayout());
					panel_2.add(scrollPane,BorderLayout.CENTER);
					
			}
		});
		btnGenerate.setBounds(424, 11, 89, 23);
		panel_1.add(btnGenerate);
		
		
		
		
		panel_2.setBounds(10, 43, 804, 358);
		panel_1.add(panel_2);
		
		JLabel lblSelectFarmer = new JLabel("Select Farmer:");
		lblSelectFarmer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectFarmer.setBounds(162, 15, 109, 14);
		panel_1.add(lblSelectFarmer);
		JMenuBar menuBar = new JMenuBar();
		JButton btnMyDashboard = new JButton("My DashBoard");
		btnMyDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CustomerDashBoard window = new CustomerDashBoard();
				window.frame.setVisible(true);
			}
		});
		frame.setJMenuBar(menuBar);
		btnMyDashboard.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnMyDashboard.setIcon(new ImageIcon(CustomerBasket.class.getResource("/resources/backIcon.png")));
		menuBar.add(btnMyDashboard);
	}
}
