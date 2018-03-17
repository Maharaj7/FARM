package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import communication.Client_Farmer;
import model.Crop;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JComboBox;

public class PurchaseCrops_Customers {

	JFrame frmPurchaseCrops;
    private JTable table;
    JPanel panel;
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

	
					viewAllCropsInternalFrame.setVisible(true);
					flag++;
					
					JPanel panel = new JPanel();
					panel.setBounds(10, 35, 607, 294);
					panel.setLayout(null);
					
					

					  Client_Farmer  fam = new Client_Farmer();
						fam.sendAction("request all crops");
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
						viewAllCropsInternalFrame.add(panel);
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
		byFarmerItem.addMouseListener(new MouseAdapter() {
			int flag = 0;
			@Override
			public void mouseReleased(MouseEvent e) {
				if(flag == 0) // the 
				{
					JInternalFrame filterByFarmerinternalFrame = new JInternalFrame("Filter By Farmer",true,true,true,true);
					filterByFarmerinternalFrame.setFrameIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewByFarmer.png")));
					filterByFarmerinternalFrame.setBounds(100, 100, 450, 300);
					JComboBox<String> comboBox = new JComboBox<String>();
					comboBox.setBounds(256, 11, 116, 20);
					filterByFarmerinternalFrame.getContentPane().add(comboBox);
					
					JButton btnNewButton = new JButton("Generate");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
						}
					});
					btnNewButton.setBounds(256, 42, 116, 23);
					filterByFarmerinternalFrame.getContentPane().add(btnNewButton);
					
					panel = new JPanel();
					panel.setBounds(10, 64, 607, 299);
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
						panel.add(scrollPane);
					
						filterByFarmerinternalFrame.getContentPane().add(panel);
					filterByFarmerinternalFrame.setBounds(398, 79, 644, 404);
					desktopPane.add(filterByFarmerinternalFrame);
					filterByFarmerinternalFrame.getContentPane().setLayout(null);
					
	
					filterByFarmerinternalFrame.setVisible(true);
					flag++;

					filterByFarmerinternalFrame.addInternalFrameListener(new InternalFrameAdapter(){
						public void internalFrameClosing(InternalFrameEvent e) {
							flag = 0; 
						}
					});
				}
			}
		});

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
