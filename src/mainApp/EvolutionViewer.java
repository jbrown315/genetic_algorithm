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
import javax.swing.Timer;


public class EvolutionViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(665, 375);
	
	JFrame viewerFrame;
	Files file;
	Timer t;
	int iterations = 0;
	EvolutionComponent drawingComponent;
	JButton run;

	// *********************************************************************

	public void runApp() {
		final int frameLocX = 50;
		final int frameLocY = 425;
		
		this.viewerFrame = new JFrame();
		this.viewerFrame.setSize(VIEWER_SIZE);
		this.viewerFrame.setTitle("Evolution Viewer");
		this.viewerFrame.setLocation(frameLocX, frameLocY);
		viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drawingComponent = new EvolutionComponent();
		
		this.viewerFrame.add(drawingComponent, BorderLayout.CENTER);
		
		JLabel rate = new JLabel("Fitness over Generations", SwingConstants.CENTER);
		this.viewerFrame.add(rate, BorderLayout.NORTH);

		
		JPanel panel = new JPanel();
		run = new JButton("Start");
		
		this.viewerFrame.add(panel, BorderLayout.SOUTH);
		panel.add(run);

		run.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if(run.getText().equals("Start")) {
					run.setText("Stop");
				}
				else {
					run.setText("Start");
				}
				if(run.getText().equals("Start")) {
					t.stop();
				}
				else {
					t.start();
				}
			}
	    });
				
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public void alterPop(PopulationComponent component) {
		t = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				component.population.truncate();
				component.repaint();
				drawingComponent.repaint();
				drawingComponent.runs++;
				drawingComponent.population = component.population;
				if(drawingComponent.runs >= 100) {
					run.doClick();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		EvolutionViewer app = new EvolutionViewer();
		app.runApp();
	} // main
} // ViewerMain
