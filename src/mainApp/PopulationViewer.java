package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




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
		
		JPanel panel = new JPanel();
		JButton loop = new JButton("Loop");
		
		this.viewerFrame.add(panel, BorderLayout.SOUTH);
		panel.add(loop);

		loop.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				drawingComponent.population.truncate();
				drawingComponent.repaint();
	    	}
	    });
		
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public static void main(String[] args) {
		PopulationViewer app = new PopulationViewer();
		app.runApp();
	} // main
} // ViewerMain
