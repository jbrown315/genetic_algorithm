package mainApp;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class File {
	
	String name = null;
	ChromosomeComponent com;
	ArrayList<Integer> in = new ArrayList<Integer>();
	
	public File(ChromosomeComponent com) {
		this.com = com;
	}
	
	public File(String name) {
		this.name = name;
	}
	
	public ArrayList<Integer> in() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Text Files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(chooser);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " + chooser.getSelectedFile());
	       Scanner myReader;
		try {
			myReader = new Scanner(chooser.getSelectedFile());
	        String data = myReader.nextLine();
	        for(int i = 0; i < data.length(); i++) {
	        	in.add(Character.getNumericValue(data.charAt(i)));
	        }
		    myReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	    }
		return in;
	}
}
