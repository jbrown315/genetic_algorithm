package mainApp;


/**
 * Class: MainApp
 * @author W22_R_201
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 */
public class MainApp {
	
	
	private void runApp() {
			
	} // runApp

	/**
	 * ensures: runs the application
	 * @param args unused
	 */
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		EvolutionViewer evo = new EvolutionViewer();
		
		mainApp.runApp();		
		evo.runApp();
	} // main

}