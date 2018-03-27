package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import communication.Client_Farmer;
import model.Crop;
import model.Farmer;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PurchaseCrops_Customers {

	JFrame frmPurchaseCrops;
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
					PurchaseCrops_Customers window = new PurchaseCrops_Customers();
					window.frmPurchaseCrops.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PurchaseCrops_Customers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPurchaseCrops = new JFrame();
		frmPurchaseCrops.setTitle("Purchase Crops");
		frmPurchaseCrops.getContentPane().setBackground(SystemColor.window);
		frmPurchaseCrops.setBounds(100, 100, 735, 555);
		frmPurchaseCrops.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPurchaseCrops.getContentPane().setLayout(null);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 735, 515);
		frmPurchaseCrops.getContentPane().add(desktopPane);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/farm.jpg")));
		label.setBounds(0, 98, 735, 332);
		desktopPane.add(label);


		JMenuBar menuBar = new JMenuBar();
		frmPurchaseCrops.setJMenuBar(menuBar);

		JMenu menu = new JMenu("Add Crops");
		menu.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/addCrop_sml.png")));
		menu.setForeground(new Color(255, 127, 80));
		menu.setFont(new Font("Herculanum", Font.PLAIN, 20));
		JMenuItem viewItem = new JMenuItem("View All");
		viewItem.addMouseListener(new MouseAdapter() {
			int flag = 0;
			@Override
			public void mouseReleased(MouseEvent e) {
				if(flag == 0) 
				{
					JInternalFrame viewAllCropsInternalFrame = new JInternalFrame("View All Crops",true,true,true,true);
					viewAllCropsInternalFrame.setFrameIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewAllCrops_Cus.png")));
					viewAllCropsInternalFrame.setBounds(37, 79, 311, 404);
					desktopPane.add(viewAllCropsInternalFrame);
					viewAllCropsInternalFrame.getContentPane().setLayout(null);

					JLabel lblListOfAll = new JLabel("List of All Crops");
					lblListOfAll.setBounds(93, 20, 108, 16);
					viewAllCropsInternalFrame.getContentPane().add(lblListOfAll);
					viewAllCropsInternalFrame.setVisible(true);


					Client_Farmer  fam = new Client_Farmer();
					fam.sendAction("request all crops");
					ArrayList<Crop> list = new ArrayList<Crop>();

					ArrayList<Crop> crops = new ArrayList<Crop>();
					fam.sendCropList(crops);

					fam.receiveResponse();
					list = fam.receiveCropData();


					DefaultTableModel model  = new DefaultTableModel();
					JTable table = new JTable(model){
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

						viewAllCropsInternalFrame.getContentPane().setLayout(new BorderLayout());
						viewAllCropsInternalFrame.getContentPane().add(scrollPane,BorderLayout.CENTER);

						flag++;
						viewAllCropsInternalFrame.addInternalFrameListener(new InternalFrameAdapter(){
							public void internalFrameClosing(InternalFrameEvent e) {
								flag = 0; 
							}
						});
				}
			}
		});
		viewItem.setFont(new Font("Khmer MN", Font.PLAIN, 18));
		viewItem.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewAllCrops_Cus.png")));
		menu.add(viewItem);

		JMenuItem byFarmerItem = new JMenuItem("Filter By Farmer");
		//		byFarmerItem.addMouseListener(new MouseAdapter() {
		//			int flag = 0;
		//			@Override
		//			public void mouseReleased(MouseEvent e) {
		//				if(flag == 0) // the 
		//				{
		JInternalFrame filterByFarmerinternalFrame = new JInternalFrame("Filter By Farmer",true,true,true,true);
		filterByFarmerinternalFrame.setFrameIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewByFarmer.png")));
		filterByFarmerinternalFrame.setBounds(385, 11, 311, 404);
		desktopPane.add(filterByFarmerinternalFrame);
		filterByFarmerinternalFrame.setVisible(true);



		

		JLabel lblSelectFarmer = new JLabel("Select Farmer");
		lblSelectFarmer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectFarmer.setBounds(21, 14, 82, 14);
		filterByFarmerinternalFrame.getContentPane().add(lblSelectFarmer);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(10, 36, 275, 327);
		
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

			comboBox.setBounds(114, 11, 75, 20);
			filterByFarmerinternalFrame.getContentPane().add(comboBox);

			JButton btnTest = new JButton("Search");
			btnTest.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					Client_Farmer fam = new Client_Farmer();
					fam.sendAction("request crops");
					fam.sendExactEmail(comboBox.getSelectedItem().toString());
					ArrayList<Crop> list = new ArrayList<Crop>();
					
					ArrayList<Crop> crops = new ArrayList<Crop>();
				    fam.sendCropList(crops);

					fam.receiveResponse();
					list = fam.receiveCropData();
					
					
					DefaultTableModel model  = new DefaultTableModel();
					JTable table1 = new JTable(model){
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
						
						panel.add(scrollPane,BorderLayout.CENTER);

				}
			});
			
			filterByFarmerinternalFrame.getContentPane().setLayout(new BorderLayout());
			filterByFarmerinternalFrame.getContentPane().add(panel,BorderLayout.CENTER);

			btnTest.setBounds(199, 10, 66, 23);
			filterByFarmerinternalFrame.getContentPane().add(btnTest);




			//						flag++;
			//					filterByFarmerinternalFrame.addInternalFrameListener(new InternalFrameAdapter(){
			//						public void internalFrameClosing(InternalFrameEvent e) {
			//							flag = 0; 
			//						}
			//					});
			//				}
			//			}
			//		});

			byFarmerItem.setFont(new Font("Khmer MN", Font.PLAIN, 17));
			byFarmerItem.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewByFarmer.png")));
			menu.add(byFarmerItem);
			menuBar.add(menu);


			JButton bckToDashboardBtn = new JButton("My DashBoard");
			bckToDashboardBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			menuBar.add(bckToDashboardBtn);
			bckToDashboardBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frmPurchaseCrops.dispose();
					CustomerDashBoard window = new CustomerDashBoard();
					window.frame.setVisible(true);
				}
			});
			bckToDashboardBtn.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/backIcon.png")));

			JButton myBasketBtn = new JButton("My Basket");
			myBasketBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frmPurchaseCrops.dispose();
					CustomerBasket window = new CustomerBasket();
					window.frmCustomerBasket.setVisible(true);
				}
			});
			myBasketBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			myBasketBtn.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/myBasket_sml.png")));
			menuBar.add(myBasketBtn);
			bckToDashboardBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
	}
}
