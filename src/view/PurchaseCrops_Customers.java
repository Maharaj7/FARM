package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JComboBox;

public class PurchaseCrops_Customers {

	JFrame frmPurchaseCrops;

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
		byFarmerItem.addMouseListener(new MouseAdapter() {
			int flag = 0;
			@Override
			public void mouseReleased(MouseEvent e) {
				if(flag == 0) // the 
				{
					JInternalFrame filterByFarmerinternalFrame = new JInternalFrame("Filter By Farmer",true,true,true,true);
					filterByFarmerinternalFrame.setFrameIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewByFarmer.png")));
					filterByFarmerinternalFrame.setBounds(398, 79, 298, 404);
					desktopPane.add(filterByFarmerinternalFrame);
					filterByFarmerinternalFrame.getContentPane().setLayout(null);

					JLabel lblEnterFarmer = new JLabel("Select farmer:");
					lblEnterFarmer.setBounds(16, 27, 94, 16);
					filterByFarmerinternalFrame.getContentPane().add(lblEnterFarmer);

					JComboBox<String> comboBox = new JComboBox<String>();
					comboBox.addItem("John Brown"); // this drop down will be populated from DB
					comboBox.setBounds(108, 23, 136, 27);
					filterByFarmerinternalFrame.getContentPane().add(comboBox);
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
