package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JInternalFrame;

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
		frmPurchaseCrops.setBounds(100, 100, 639, 546);
		frmPurchaseCrops.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPurchaseCrops.getContentPane().setLayout(null);
		
		JButton viewAllBtn = new JButton("");
		viewAllBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JInternalFrame allCropsInternalFrame = new JInternalFrame("All Crops");
				allCropsInternalFrame.setFrameIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewAllCrops_Cus.png")));
				allCropsInternalFrame.setClosable(true);
				allCropsInternalFrame.setBounds(20, 342, 260, 147);
				frmPurchaseCrops.getContentPane().add(allCropsInternalFrame);
				allCropsInternalFrame.setVisible(true);
			}
		});
		
		viewAllBtn.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewAllCrops_Cus.png")));
		viewAllBtn.setBounds(110, 123, 170, 129);
		frmPurchaseCrops.getContentPane().add(viewAllBtn);
		
		JButton filterByFarmersBtn = new JButton("");
		filterByFarmersBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JInternalFrame FilteredByFarmerInternalFrame = new JInternalFrame("Filtered By Farmer");
				FilteredByFarmerInternalFrame.setFrameIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewByFarmer.png")));
				FilteredByFarmerInternalFrame.setClosable(true);
				FilteredByFarmerInternalFrame.setBounds(347, 342, 260, 147);
				frmPurchaseCrops.getContentPane().add(FilteredByFarmerInternalFrame);
				FilteredByFarmerInternalFrame.setVisible(true);
				
			}
		});
		
		filterByFarmersBtn.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/viewByFarmer.png")));
		filterByFarmersBtn.setBounds(377, 123, 144, 129);
		frmPurchaseCrops.getContentPane().add(filterByFarmersBtn);
		
		JLabel lblViewAllCrops = new JLabel("View All");
		lblViewAllCrops.setBounds(172, 264, 63, 16);
		frmPurchaseCrops.getContentPane().add(lblViewAllCrops);
		
		JLabel lblFilterByFarmers = new JLabel("Filter By Farmers");
		lblFilterByFarmers.setBounds(400, 264, 112, 16);
		frmPurchaseCrops.getContentPane().add(lblFilterByFarmers);
		
		JLabel lblAddCrops = new JLabel("Add Crops");
		lblAddCrops.setIcon(new ImageIcon(PurchaseCrops_Customers.class.getResource("/resources/addCrop_sml.png")));
		lblAddCrops.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblAddCrops.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCrops.setBounds(265, 40, 134, 27);
		frmPurchaseCrops.getContentPane().add(lblAddCrops);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(212, 212, 212));
		separator.setBounds(82, 318, 500, 12);
		frmPurchaseCrops.getContentPane().add(separator);
		
		JMenuBar menuBar = new JMenuBar();
		frmPurchaseCrops.setJMenuBar(menuBar);
		
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
