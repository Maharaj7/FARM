package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import communication.Client_Farmer;
import controller.Client_Controller;
import model.Crop;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Crops {
    
	static String fileName = null;
	byte[] personImage = null;
	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	Client_Controller ls = new Client_Controller();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crops window = new Crops();
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
	public Crops() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 639, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 623, 348);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Crops", null, panel, null);
		panel.setLayout(null);
		

		JLabel label = new JLabel("");
		label.setBounds(107, 76, 115, 114);
		panel.add(label);
		
		JButton button = new JButton("Upload Crop");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				fileName = f.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH));
				label.setIcon(imageIcon);
				
				try{
					File image = new File(fileName);
					@SuppressWarnings("resource")
					FileInputStream fis = new FileInputStream(image);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readNum; (readNum = fis.read(buf))!=-1;)
					{
						bos.write(buf,0,readNum);
					}
					personImage = bos.toByteArray();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button.setBounds(107, 42, 115, 23);
		panel.add(button);
		
		
		JLabel label_1 = new JLabel("ADD A NEW CROP");
		label_1.setBounds(254, 11, 127, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Crop Name");
		label_2.setBounds(273, 46, 70, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Weight");
		label_3.setBounds(273, 76, 70, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Cost per Unit");
		label_4.setBounds(273, 111, 80, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Available");
		label_5.setBounds(273, 148, 70, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Quantity");
		label_6.setBounds(273, 176, 70, 14);
		panel.add(label_6);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(371, 43, 86, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(371, 73, 86, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(371, 108, 86, 20);
		panel.add(textField_2);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(371, 145, 86, 20);
		comboBox.addItem("True");
		comboBox.addItem("False");
		panel.add(comboBox);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(371, 173, 86, 20);
		panel.add(textField_3);
		
		JButton button_1 = new JButton("Add Crop");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
						|| textField_3.getText().isEmpty() || personImage == null )
					 {
						 JOptionPane.showMessageDialog(null, "Fields cannot be left empty");
					 }
				else{
					try{
				String selection;
				if(comboBox.getSelectedItem().equals("True"))
				{
					selection = "true";
				}
				else
				{
					selection = "false";
				}
				Client_Farmer fam = new Client_Farmer();
				 
				
				fam.sendAction("Add Crop");
				Crop crop = new Crop(ls.getEmail(),fileName,textField.getText(),Float.parseFloat(textField_1.getText()),Float.parseFloat(textField_2.getText()),selection,Integer.parseInt(textField_3.getText()));
				fam.sendCrop(crop);
				fam.receiveResponse();
				
				
				JOptionPane.showMessageDialog(null, "Crop Added!");
					}
					catch(NullPointerException | NumberFormatException e1)
					{
						e1.printStackTrace();
					}
			}
			}
		});
		button_1.setBounds(254, 233, 89, 23);
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("View All Crops", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		Client_Farmer fam = new Client_Farmer();
		fam.sendAction("request crops");
		ArrayList<Crop> list = new ArrayList<Crop>();
		
		ArrayList<Crop> crops = new ArrayList<Crop>();
	    fam.sendCropList(crops);
		fam.receiveResponse();
		list = fam.receiveCropData();
		
		JTable table = new JTable();
		
		 DefaultTableModel model  = new DefaultTableModel();
		 Object[] columnNames = new Object[7];
		 columnNames[0]="Email";
		 columnNames[1]="Image";
		 columnNames[2]="Name";
		 columnNames[3]="Weight";
		 columnNames[4]="Cost";
		 columnNames[5]="Available";
		 columnNames[6]="Quantity";
		 
		 model.setColumnIdentifiers(columnNames);
		 
		  Object[] row = new Object[7];
		  for(int i=0; i <list.size(); i++)
		{
			  row[0] = list.get(i).getEmail();
			  row[1] = list.get(i).getimagePath();
			  row[2] = list.get(i).getName();
			  row[3] = list.get(i).getWeight();
			  row[4] = list.get(i).getCostPerUnit();
			  row[5] = list.get(i).getAvailable();
			  row[6] = list.get(i).getQuantity();
			 // System.out.println(list.get(i).toString());
			   model.addRow(row); 
		}
		  table.setModel(model);
		  JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(39, 244, 545, -186);
			
			
			panel_1.setLayout(new BorderLayout());
			panel_1.add(scrollPane,BorderLayout.CENTER);
	}
	
	public void showTable()
	{
		
		
	}
	
}
