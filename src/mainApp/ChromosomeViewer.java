package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;



public class ChromosomeViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(500, 400);
	
	JFrame viewerFrame;
	Files file;

	// *********************************************************************

	public void runApp() {
		final int frameLocX = 100;
		final int frameLocY = 100;
		
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
		
		file = new Files(drawingComponent);
		
		JPanel panel = new JPanel();
		JButton load = new JButton("Load File");
		JButton save = new JButton("Save File");
		JButton mutate = new JButton("Mutate");
		JLabel rate = new JLabel("M Rate: _/N");
		JTextField input = new JTextField(5);
		
		this.viewerFrame.add(panel, BorderLayout.SOUTH);
		
		panel.add(load);
		panel.add(save);
		panel.add(mutate);
		panel.add(rate);
		panel.add(input);
				
		load.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				drawingComponent.chromosome.bits = file.in(name);
				drawingComponent.chromosome.rows = drawingComponent.chromosome.bits.size() / 10;
				drawingComponent.repaint();
	    	}
	    });
		
		save.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				file.ex(drawingComponent.chromosome.bits);
	    	}
	    });
		
		mutate.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if(input.getText().equals("")) {
					System.out.println("ERROR: No Mutation Rate!");
				}
				else {
					try {
						int num = Integer.valueOf(input.getText());
						if(num < 0 || num > 100) {
							System.out.println("ERROR: Mutation rate out of range!");
						}
						else {
							Random rand = new Random();
							for(int i = 0; i < drawingComponent.chromosome.bits.size(); i++) {
								if(num > rand.nextInt(100)) {
									if(name.getText().charAt(name.getText().length() - 1) != ')') {
										name.setText(name.getText() + " (mutated)");
									}
									if(drawingComponent.chromosome.bits.get(i) == 1) {
										drawingComponent.chromosome.bits.set(i, 0);
									}
									else {
										drawingComponent.chromosome.bits.set(i, 1);
									}
								}
							}
						}
					}
					catch (NumberFormatException e1) {
						e1.printStackTrace();
					}
					drawingComponent.repaint();
		    	}
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
