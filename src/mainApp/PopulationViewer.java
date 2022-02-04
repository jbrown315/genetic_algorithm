package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;




public class PopulationViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(650, 675);
	
	JFrame viewerFrame;
	Files file;

	// *********************************************************************

	public void runApp() {
		final int frameLocX = 600;
		final int frameLocY = 100;
		
		this.viewerFrame = new JFrame();
		this.viewerFrame.setSize(VIEWER_SIZE);
		this.viewerFrame.setTitle("Population Viewer");
		this.viewerFrame.setLocation(frameLocX, frameLocY);
		viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PopulationComponent drawingComponent = new PopulationComponent();
		this.viewerFrame.add(drawingComponent, BorderLayout.CENTER);
		
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public static void main(String[] args) {
		PopulationViewer app = new PopulationViewer();
		app.runApp();
	} // main
} // ViewerMain
