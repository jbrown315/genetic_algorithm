package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class EvolutionViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(665, 375);
	
	JFrame viewerFrame;
	Files file;

	// *********************************************************************

	public void runApp() {
		final int frameLocX = 50;
		final int frameLocY = 425;
		
		this.viewerFrame = new JFrame();
		this.viewerFrame.setSize(VIEWER_SIZE);
		this.viewerFrame.setTitle("Evolution Viewer");
		this.viewerFrame.setLocation(frameLocX, frameLocY);
		viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		EvolutionComponent drawingComponent = new EvolutionComponent();
		this.viewerFrame.add(drawingComponent, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		JButton loop = new JButton("Loop");
		
		JLabel rate = new JLabel("Fitness over Generations", SwingConstants.CENTER);
		this.viewerFrame.add(rate, BorderLayout.NORTH);

		
		this.viewerFrame.add(panel, BorderLayout.SOUTH);
		panel.add(loop);

		loop.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
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
