package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;



public class ChromosomeViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(500, 400);
	
	JFrame viewerFrame;
	File file;

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
		this.viewerFrame.add(drawingComponent, BorderLayout.CENTER);
		
		JLabel name = new JLabel();
		name.setText("New Chromosome");
		
		viewerFrame.add(name, BorderLayout.NORTH);
		
		file = new File(drawingComponent);
		
		JPanel panel = new JPanel();
		JButton load = new JButton("Load File");
		
		this.viewerFrame.add(panel, BorderLayout.SOUTH);
		
		panel.add(load);
		
		load.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				drawingComponent.chromosome.bits = file.in(name);
				//System.out.println(drawingComponent.chromosome.bits);
				drawingComponent.chromosome.rows = drawingComponent.chromosome.bits.size() / 10;
				drawingComponent.repaint();
	    	}
	    });

		
		MouseListener click = new ClickListener(drawingComponent, name);
		drawingComponent.addMouseListener(click);
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public static void main(String[] args) {
		ChromosomeViewer app = new ChromosomeViewer();
		app.runApp();
	} // main
} // ViewerMain
