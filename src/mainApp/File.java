package mainApp;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class File {
	
	String name = null;
	ChromosomeComponent com;
	
	public File(ChromosomeComponent com) {
		this.com = com;
	}
	
	public File(String name) {
		this.name = name;
	}
	
	public void in() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Text Files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(chooser);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	    }
	}
}
