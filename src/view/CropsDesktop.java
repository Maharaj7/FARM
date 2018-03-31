package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import communication.Client_Farmer;
import controller.Crops_DataHandler;
import model.Crop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CropsDesktop {

	static String fileName;
	byte[] personImage;
	byte[] cropImage;
	JFrame frmCropsDesktop;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JScrollPane scrollPane;
	private JTable table;
	private Crops_DataHandler cropsData;
	JInternalFrame viewAllInternalFrame;
	private static String cropName;
	UpdateCrop updateCrops = new UpdateCrop();
	static boolean show = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CropsDesktop window = new CropsDesktop();
					window.frmCropsDesktop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CropsDesktop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCropsDesktop = new JFrame();
		frmCropsDesktop.setTitle("Crops Desktop");
		frmCropsDesktop.setBounds(100, 100, 1203, 637);
		frmCropsDesktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCropsDesktop.getContentPane().setLayout(null);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1203, 555);
		frmCropsDesktop.getContentPane().add(desktopPane);

		JLabel lblWelcomeToCrops = new JLabel("Welcome To Crops Dashboard");
		lblWelcomeToCrops.setFont(new Font("Herculanum", Font.PLAIN, 60));
		lblWelcomeToCrops.setForeground(Color.LIGHT_GRAY);
		lblWelcomeToCrops.setBounds(116, 199, 1038, 95);
		desktopPane.add(lblWelcomeToCrops);

		JMenuBar menuBar = new JMenuBar();
		frmCropsDesktop.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Crops Menu");
		mnMenu.setFont(new Font("KufiStandardGK", Font.PLAIN, 18));
		menuBar.add(mnMenu);

		JMenuItem mntmAddCrops = new JMenuItem("Add Crops"); // MItem one
		mntmAddCrops.setFont(new Font("Khmer MN", Font.PLAIN, 17));
		mntmAddCrops.addMouseListener(new MouseAdapter() {
			int flag = 0;

			@Override
			public void mouseReleased(MouseEvent e) {

				if (flag == 0) {

					JInternalFrame addCropsinternalFrame = new JInternalFrame("Add Crops", true, true, false, true);
					addCropsinternalFrame.setBounds(20, 66, 375, 462);
					desktopPane.add(addCropsinternalFrame);
					addCropsinternalFrame.getContentPane().setLayout(null);

					JPanel panel = new JPanel();
					panel.setBounds(47, 42, 262, 368);
					addCropsinternalFrame.getContentPane().add(panel);
					panel.setLayout(null);

					JLabel lblNewCrops = new JLabel("New Crops");
					lblNewCrops.setForeground(new Color(255, 127, 80));
					lblNewCrops.setFont(new Font("Herculanum", Font.PLAIN, 16));
					lblNewCrops.setBounds(97, 16, 113, 16);
					panel.add(lblNewCrops);

					JLabel lblName = new JLabel("Name:");
					lblName.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
					lblName.setBounds(6, 59, 61, 16);
					panel.add(lblName);

					textField = new JTextField();
					textField.setBounds(83, 49, 173, 26);
					panel.add(textField);
					textField.setColumns(10);

					JLabel lblWeight = new JLabel("Weight(lbs):");
					lblWeight.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
					lblWeight.setBounds(6, 97, 81, 16);
					panel.add(lblWeight);

					textField_1 = new JTextField();
					textField_1.setColumns(10);
					textField_1.setBounds(83, 87, 173, 26);
					panel.add(textField_1);

					JLabel lblCostUnit = new JLabel("Unit Cost:");
					lblCostUnit.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
					lblCostUnit.setBounds(6, 139, 81, 16);
					panel.add(lblCostUnit);

					textField_2 = new JTextField();
					textField_2.setColumns(10);
					textField_2.setBounds(83, 129, 173, 26);
					panel.add(textField_2);

					JLabel lblAvailableQuantity = new JLabel("Available Quantity:");
					lblAvailableQuantity.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
					lblAvailableQuantity.setBounds(6, 218, 130, 16);
					panel.add(lblAvailableQuantity);

					textField_3 = new JTextField();
					textField_3.setColumns(10);
					textField_3.setBounds(135, 208, 75, 26);
					panel.add(textField_3);

					JLabel imagLabel = new JLabel("");
					imagLabel.setBounds(135, 258, 99, 58);
					panel.add(imagLabel);

					JButton btnUploadImage = new JButton("Upload Image");
					btnUploadImage.setFont(new Font("Hiragino Sans", Font.PLAIN, 13));
					btnUploadImage.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							JFileChooser chooser = new JFileChooser();
							chooser.showOpenDialog(null);
							File f = chooser.getSelectedFile();
							fileName = f.getAbsolutePath();
							ImageIcon imageIcon = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(
									imagLabel.getWidth(), imagLabel.getHeight(), Image.SCALE_SMOOTH));
							imagLabel.setIcon(imageIcon);

							try {
								File image = new File(fileName);
								@SuppressWarnings("resource")
								FileInputStream fis = new FileInputStream(image);
								ByteArrayOutputStream bos = new ByteArrayOutputStream();
								byte[] buf = new byte[1024];
								for (int readNum; (readNum = fis.read(buf)) != -1;) {
									bos.write(buf, 0, readNum);
								}
								personImage = bos.toByteArray();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1);
							}

						}
					});
					btnUploadImage.setBounds(6, 255, 117, 34);
					panel.add(btnUploadImage);

					JLabel imgLable = new JLabel("");
					imgLable.setBounds(135, 258, 99, 58);
					panel.add(imgLable);

					JButton btnAddCrop = new JButton("Add Crop");
					btnAddCrop.setFont(new Font("Herculanum", Font.BOLD, 14));
					btnAddCrop.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cropsData = new Crops_DataHandler();
							cropsData.addCrops(textField.getText(), textField_1.getText(), textField_2.getText(),
									textField_3.getText(), textField, textField_1, textField_2, textField_3, imagLabel,
									personImage, fileName);
							
						}
					});
					btnAddCrop.setBounds(81, 333, 117, 29);
					panel.add(btnAddCrop);

					JLabel label = new JLabel("");
					label.setIcon(new ImageIcon(CropsDesktop.class.getResource("/resources/dash1.jpg")));
					label.setBounds(0, 0, 351, 416);
					addCropsinternalFrame.getContentPane().add(label);
					addCropsinternalFrame.setVisible(true);

					flag++;

					addCropsinternalFrame.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameClosing(InternalFrameEvent e) {
							flag = 0;
						}
					});
				}

			}

		}); // end of MItem one

		mntmAddCrops.setIcon(new ImageIcon(CropsDesktop.class.getResource("/resources/addCrop.png")));
		mnMenu.add(mntmAddCrops);

		JMenuItem mntmViewAllCrops = new JMenuItem("View All Crops");
		mntmViewAllCrops.setFont(new Font("Khmer MN", Font.PLAIN, 17));
		mntmViewAllCrops.addMouseListener(new MouseAdapter() {
			int flag = 0;

			@Override
			public void mouseReleased(MouseEvent e) {

				if (flag == 0) {

					 viewAllInternalFrame = new JInternalFrame("View All Crops", true, true, true, true);
					viewAllInternalFrame.getContentPane().setBackground(new Color(218, 112, 214));
					viewAllInternalFrame.setBounds(415, 66, 375, 462);
					desktopPane.add(viewAllInternalFrame);
					viewAllInternalFrame.getContentPane().setLayout(new BorderLayout());

					showTable(viewAllInternalFrame);

					viewAllInternalFrame.setVisible(true);

					JMenuBar menuBar_1 = new JMenuBar();
					viewAllInternalFrame.setJMenuBar(menuBar_1);

					JButton btnRefresh = new JButton("Refresh");
					btnRefresh.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							show = false;
							showTable1(viewAllInternalFrame);

						}
					});
					menuBar_1.add(btnRefresh);
					flag++;

					viewAllInternalFrame.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameClosing(InternalFrameEvent e) {
							flag = 0;
						}
					});

				}

			}

		});

		mntmViewAllCrops.setIcon(new ImageIcon(CropsDesktop.class.getResource("/resources/viewAllCrops.png")));
		mnMenu.add(mntmViewAllCrops);

		JMenuItem mntmUpdateCrops = new JMenuItem("Update Crops");
		mntmUpdateCrops.setFont(new Font("Khmer MN", Font.PLAIN, 17));
		mntmUpdateCrops.addMouseListener(new MouseAdapter() {
			int flag = 0;

			@SuppressWarnings("serial")
			@Override
			public void mouseReleased(MouseEvent e) {
				if (flag == 0) {
					JInternalFrame updatCropInternalFrame = new JInternalFrame("Update Crops", true, true, true, true);
					updatCropInternalFrame.getContentPane().setBackground(new Color(218, 112, 214));
					updatCropInternalFrame.setBounds(798, 69, 375, 462);
					desktopPane.add(updatCropInternalFrame);
					updatCropInternalFrame.getContentPane().setLayout(null);
					JMenuBar menuBar_1 = new JMenuBar();
					updatCropInternalFrame.setJMenuBar(menuBar_1);
					
					
                  try{
					ArrayList<Crop> list = new ArrayList<Crop>();
					cropsData = new Crops_DataHandler();
					list = cropsData.requestCrops();

					DefaultTableModel model = new DefaultTableModel();

					table = new JTable(model) {
						@Override
						public boolean isCellEditable(int row, int column) // Disables
																			// cells
																			// in
																			// the
																			// table
																			// from
																			// being
																			// edited.
						{
							return false;
						}

						@SuppressWarnings({ "unchecked", "rawtypes" })
						@Override
						public Class getColumnClass(int column) {
							switch (column) {
							case 6:
								return ImageIcon.class;
							default:
								return Object.class;
							}
						}
					};
					table.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {

							int index = table.getSelectedRow();
							TableModel tmodel = table.getModel();
							ImageIcon image;
							image = (ImageIcon) tmodel.getValueAt(index, 6);
							updateCrops.iImage.setIcon(image);
                            String iPath = tmodel.getValueAt(index, 6).toString();
							String name = tmodel.getValueAt(index, 0).toString();
							String quantity = tmodel.getValueAt(index, 4).toString();
							String weight = tmodel.getValueAt(index, 1).toString();
							String cost = tmodel.getValueAt(index, 2).toString();
							@SuppressWarnings("unused")
							String ava = tmodel.getValueAt(index, 3).toString();
							String email = tmodel.getValueAt(index, 5).toString();
							updateCrops.frmCropUpdate.setVisible(true);
							updateCrops.textField.setText(name);
							updateCrops.textField_2.setText(quantity);
							updateCrops.textField_3.setText(weight);
							updateCrops.textField_4.setText(cost);
							updateCrops.email = email;
							updateCrops.iPath = iPath;
						}
					});
					table.setRowHeight(68);

					Object[] columnNames = new Object[7];

					columnNames[6] = "Image";
					columnNames[0] = "Name";
					columnNames[1] = "Weight";
					columnNames[2] = "Cost";
					columnNames[3] = "Available";
					columnNames[4] = "Quantity";
					columnNames[5] = "Email";
					model.setColumnIdentifiers(columnNames);

					Object[] row = new Object[7];

					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getimagePath() != null) {
							ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getimagePath()).getImage()
									.getScaledInstance(190, 160, Image.SCALE_SMOOTH));
							row[6] = image;
						} else {
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
					scrollPane = new JScrollPane(table);
					scrollPane.setBounds(39, 244, 545, -186);

					updatCropInternalFrame.getContentPane().setLayout(new BorderLayout());

					updatCropInternalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

					updatCropInternalFrame.setVisible(true);
					JButton btnRefresh = new JButton("Refresh");
					btnRefresh.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							ArrayList<Crop> list = new ArrayList<Crop>();
							cropsData = new Crops_DataHandler();
							list = cropsData.requestCrops();

							DefaultTableModel model = new DefaultTableModel();

							table = new JTable(model) {
								@Override
								public boolean isCellEditable(int row, int column) // Disables
																					// cells
																					// in
																					// the
																					// table
																					// from
																					// being
																					// edited.
								{
									return false;
								}

								@SuppressWarnings({ "unchecked", "rawtypes" })
								@Override
								public Class getColumnClass(int column) {
									switch (column) {
									case 6:
										return ImageIcon.class;
									default:
										return Object.class;
									}
								}
							};
							table.addMouseListener(new MouseAdapter() {

								@Override
								public void mouseClicked(MouseEvent e) {

									int index = table.getSelectedRow();
									TableModel tmodel = table.getModel();
									ImageIcon image;
									image = (ImageIcon) tmodel.getValueAt(index, 6);
									updateCrops.iImage.setIcon(image);
		                            String iPath = tmodel.getValueAt(index, 6).toString();
									String name = tmodel.getValueAt(index, 0).toString();
									String quantity = tmodel.getValueAt(index, 4).toString();
									String weight = tmodel.getValueAt(index, 1).toString();
									String cost = tmodel.getValueAt(index, 2).toString();
									@SuppressWarnings("unused")
									String ava = tmodel.getValueAt(index, 3).toString();
									String email = tmodel.getValueAt(index, 5).toString();
									updateCrops.frmCropUpdate.setVisible(true);
									updateCrops.textField.setText(name);
									updateCrops.textField_2.setText(quantity);
									updateCrops.textField_3.setText(weight);
									updateCrops.textField_4.setText(cost);
									updateCrops.email = email;
									updateCrops.iPath = iPath;
								}
							});
							table.setRowHeight(68);

							Object[] columnNames = new Object[7];

							columnNames[6] = "Image";
							columnNames[0] = "Name";
							columnNames[1] = "Weight";
							columnNames[2] = "Cost";
							columnNames[3] = "Available";
							columnNames[4] = "Quantity";
							columnNames[5] = "Email";
							model.setColumnIdentifiers(columnNames);

							Object[] row = new Object[7];

							for (int i = 0; i < list.size(); i++) {
								if (list.get(i).getimagePath() != null) {
									ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getimagePath()).getImage()
											.getScaledInstance(190, 160, Image.SCALE_SMOOTH));
									row[6] = image;
								} else {
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
							scrollPane = new JScrollPane(table);
							scrollPane.setBounds(39, 244, 545, -186);

							updatCropInternalFrame.getContentPane().setLayout(new BorderLayout());

							updatCropInternalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

						}
					});
					menuBar_1.add(btnRefresh);
					flag++;

					updatCropInternalFrame.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameClosing(InternalFrameEvent e) {
							flag = 0;
						}
					});
                  }
                  catch(ArrayIndexOutOfBoundsException q)
                  {
                	  
                  }

				}

			}
		});
		mntmUpdateCrops.setIcon(new ImageIcon(CropsDesktop.class.getResource("/resources/UpdateCrops.png")));
		mnMenu.add(mntmUpdateCrops);

		JButton button = new JButton("My DashBoard");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCropsDesktop.dispose();
				FarmerDashBoard window = new FarmerDashBoard();
				window.frmFarmerDashboard.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(CropsDesktop.class.getResource("/resources/backIcon.png")));
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		menuBar.add(button);
		
		
	}
	

	public String returnCropName() {

		return cropName;

	}

	@SuppressWarnings("serial")
	private void showTable1(JInternalFrame frame) {
		Client_Farmer fam = new Client_Farmer();
		fam.sendAction("request crops");
		fam.sendEmail(); // sends person email to the server in order to query
							// specific data
		ArrayList<Crop> list = new ArrayList<Crop>();

		ArrayList<Crop> crops = new ArrayList<Crop>();
		fam.sendCropList(crops);

		fam.receiveResponse();
		list = fam.receiveCropData();

		DefaultTableModel model = new DefaultTableModel();

		Object[] columnNames = new Object[6];

		columnNames[0] = "Image";
		columnNames[1] = "Name";
		columnNames[2] = "Weight";
		columnNames[3] = "Cost";
		columnNames[4] = "Available";
		columnNames[5] = "Quantity";

		model.setColumnIdentifiers(columnNames);

		Object[] row = new Object[6];

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getimagePath() != null) {
				ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getimagePath()).getImage()
						.getScaledInstance(190, 160, Image.SCALE_SMOOTH));
				row[0] = image;
			} else {
				row[5] = null;
			}

			row[1] = list.get(i).getName();
			row[2] = list.get(i).getWeight();
			row[3] = list.get(i).getCostPerUnit();
			row[4] = list.get(i).getAvailable();
			row[5] = list.get(i).getQuantity();

			model.addRow(row);

		}

		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) // Disables cells
																// in the table
																// from being
																// edited.
			{
				return false;
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 359, 407);

		scrollPane_1.setViewportView(table);
		table.setRowHeight(68);
		frame.getContentPane().add(scrollPane_1);

	}

	@SuppressWarnings("serial")
	private void showTable(JInternalFrame frame) {
		Client_Farmer fam = new Client_Farmer();
		fam.sendAction("request crops");
		fam.sendEmail(); // sends person email to the server in order to query
							// specific data
		ArrayList<Crop> list = new ArrayList<Crop>();

		ArrayList<Crop> crops = new ArrayList<Crop>();
		fam.sendCropList(crops);

		fam.receiveResponse();
		list = fam.receiveCropData();

		frame.setVisible(show);

		DefaultTableModel model = new DefaultTableModel();

		Object[] columnNames = new Object[6];

		columnNames[0] = "Image";
		columnNames[1] = "Name";
		columnNames[2] = "Weight";
		columnNames[3] = "Cost";
		columnNames[4] = "Available";
		columnNames[5] = "Quantity";

		model.setColumnIdentifiers(columnNames);

		Object[] row = new Object[6];

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getimagePath() != null) {
				ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getimagePath()).getImage()
						.getScaledInstance(190, 160, Image.SCALE_SMOOTH));
				row[0] = image;
			} else {
				row[5] = null;
			}

			row[1] = list.get(i).getName();
			row[2] = list.get(i).getWeight();
			row[3] = list.get(i).getCostPerUnit();
			row[4] = list.get(i).getAvailable();
			row[5] = list.get(i).getQuantity();

			model.addRow(row);

		}

		table = new JTable(model) {

			@Override
			public boolean isCellEditable(int row, int column) // Disables cells
																// in the table
																// from being
																// edited.
			{
				return false;
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 359, 407);

		scrollPane_1.setViewportView(table);
		table.setRowHeight(68);
		frame.getContentPane().add(scrollPane_1);

	}

}
