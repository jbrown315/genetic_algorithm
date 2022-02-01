package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Files {
	
	String name = null;
	ChromosomeComponent com;
	ArrayList<Integer> in;
	
	public Files(ChromosomeComponent com) {
		this.com = com;
	}
	
	public Files(String name) {
		this.name = name;
	}
	
	public ArrayList<Integer> in(JLabel label) {
		in = new ArrayList<Integer>();
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Text Files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(chooser);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       //System.out.println("You chose to open this file: " + chooser.getSelectedFile());
	       Scanner myReader;
		try {
			myReader = new Scanner(chooser.getSelectedFile());
	        String data = myReader.nextLine();
	        for(int i = 0; i < data.length(); i++) {
	        	in.add(Character.getNumericValue(data.charAt(i)));
	        }
	        label.setText(chooser.getSelectedFile().getName());
		    myReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	    }
		return in;
	}
	
	public void ex(ArrayList<Integer> output) {
	    String result = "";
	    for(int i : output) {
	    	result = result + Integer.toString(i);
	    }
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("user.dir"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try (FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt")) {
	            fw.write(result);
	            fw.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
