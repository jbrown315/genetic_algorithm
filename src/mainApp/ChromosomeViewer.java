package mainApp;
import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.event.MouseListener;



public class ChromosomeViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(500, 400);
	private JFrame viewerFrame;

	// *********************************************************************

	private void runApp() {
		final int frameLocX = 250;
		final int frameLocY = 250;

		this.viewerFrame = new JFrame();
		this.viewerFrame.setSize(VIEWER_SIZE);
		this.viewerFrame.setTitle("Chromosome");
		this.viewerFrame.setLocation(frameLocX, frameLocY);
		viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up the component
		ChromosomeComponent drawingComponent = new ChromosomeComponent();
		this.viewerFrame.add(drawingComponent);
		
		MouseListener click = new ClickListener(drawingComponent);
		drawingComponent.addMouseListener(click);
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public static void main(String[] args) {
		ChromosomeViewer app = new ChromosomeViewer();
		app.runApp();
	} // main
} // ViewerMain
