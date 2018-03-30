package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import communication.Client_Farmer;
import model.Crop;

public class Crops_DataHandler {
	private Client_Controller ls = new Client_Controller();

	public void addCrops(String name,String weight,String costper,String available, String quantity, JTextField textField,JTextField textField_1,JTextField textField_2,JTextField textField_3,JLabel imagLabel,byte[] personImage,String fileName)
	{
		Client_Farmer fam = new Client_Farmer();
		fam.sendAction("request crops");
		fam.sendExactEmail(ls.getEmail());; // sends person email to the server in order to query specific data
		ArrayList<Crop> list = new ArrayList<Crop>();

		ArrayList<Crop> crops = new ArrayList<Crop>();
		fam.sendCropList(crops);

		fam.receiveResponse();
		list = fam.receiveCropData();

		int found =0;
		for(int i =0; i<list.size();i++)
		{
			if(list.get(i).getName().compareToIgnoreCase(name)==0)
			{
				found =1;
				break;
			}

		}

		if(found == 1)
		{
			JOptionPane.showMessageDialog(null, "Crop Already exist, Please use Update Option.");
		}
		else{
			if( name.isEmpty() || weight.isEmpty() || quantity.isEmpty()
					|| available.isEmpty() )
			{
				JOptionPane.showMessageDialog(null, "Fields cannot be left empty");
			}if (personImage == null)
			{
				JOptionPane.showMessageDialog(null, "Please Upload an Image");
			}
		
			else{
				try{
					String selection;
					if(Integer.parseInt(quantity) >= 1)

					{
						selection = "In Stock";
					}
					else{
						selection = "Not In Stock";
					}

					Client_Farmer fam1 = new Client_Farmer();


					fam1.sendAction("Add Crop");
					
					 File source = new File(fileName);
					 String path = "C:\\Users\\Maharaj\\git\\FARM\\src\\cropImages"+"\\"+name+".jpg";
						File dest = new File(path);
				         try{
				        	 Files.copy(source.toPath(), dest.toPath());
				         }
				         catch(IOException e1)
				         {
				        	 e1.printStackTrace();
				         }
					
					
					Crop crop = new Crop(ls.getEmail(),path,name,Float.parseFloat(weight),Float.parseFloat(costper),selection,Integer.parseInt(quantity));
					fam1.sendCrop(crop);
					fam1.receiveResponse();


					JOptionPane.showMessageDialog(null, "Crop Added!");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					imagLabel.setIcon(null);


				}
				catch(NullPointerException e11)
				{
					e11.printStackTrace();
				}
				catch(NumberFormatException e11)
				{
					JOptionPane.showMessageDialog(null, "Please enter numeric data, not words", " Error message", JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}
}
