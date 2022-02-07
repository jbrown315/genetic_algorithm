package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;




public class PopulationViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(650, 700);
	
	JFrame viewerFrame;
	Files file;

	// *********************************************************************

	public void runApp() {
		final int frameLocX = 800;
		final int frameLocY = 50;
		
		this.viewerFrame = new JFrame();
		this.viewerFrame.setSize(VIEWER_SIZE);
		this.viewerFrame.setTitle("Population Viewer");
		this.viewerFrame.setLocation(frameLocX, frameLocY);
		viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PopulationComponent drawingComponent = new PopulationComponent();
		this.viewerFrame.add(drawingComponent, BorderLayout.CENTER);
		
		EvolutionViewer test = new EvolutionViewer();
		test.alterPop(drawingComponent);
		test.runApp();
		
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public static void main(String[] args) {
		PopulationViewer app = new PopulationViewer();
		app.runApp();
	} // main
} // ViewerMain
