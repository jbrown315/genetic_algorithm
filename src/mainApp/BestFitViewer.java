package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class BestFitViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(312, 335);
	
	JFrame viewerFrame;
	Files file;
	BestFitComponent drawingComponent;

	// *********************************************************************

	public void runApp() {
		final int frameLocX = 275;
		final int frameLocY = 75;
		
		this.viewerFrame = new JFrame();
		this.viewerFrame.setSize(VIEWER_SIZE);
		this.viewerFrame.setTitle("Best Fit Chromosome");
		this.viewerFrame.setLocation(frameLocX, frameLocY);
		viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up the component
		drawingComponent = new BestFitComponent();
		this.viewerFrame.add(drawingComponent, BorderLayout.CENTER);
		
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public static void main(String[] args) {
		ChromosomeViewer app = new ChromosomeViewer();
		app.runApp();
	} // main
} // ViewerMain
