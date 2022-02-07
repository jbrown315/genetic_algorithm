package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class EvolutionViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(765, 345);
	
	JFrame viewerFrame;
	Files file;
	Timer t;
	int iterations = 0;
	EvolutionComponent drawingComponent;
	BestFitViewer bestFitViewer;
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
		
		bestFitViewer = new BestFitViewer();
		
		bestFitViewer.runApp();
		
		this.viewerFrame.add(drawingComponent, BorderLayout.CENTER);
		
		JLabel title = new JLabel("Fitness over Generations", SwingConstants.CENTER);
		this.viewerFrame.add(title, BorderLayout.NORTH);

		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel rate = new JLabel("M Rate: _/N");
		JTextField input = new JTextField(3);
		JLabel select = new JLabel("Selection");
		String[] options = {"Truncate", "Routlette Wheel", "Ranked"};
		JComboBox<String> selection = new JComboBox<String>(options);
		JLabel cross = new JLabel("Crossover");
		JCheckBox crossover = new JCheckBox();
		JLabel popsize = new JLabel("Population Size");
		JTextField input2 = new JTextField(3);
		JLabel gens = new JLabel("Generations");
		JTextField input3 = new JTextField(3);
		JLabel genome = new JLabel("Genomes");
		JTextField input4 = new JTextField(3);
		JLabel elite = new JLabel("Eliteism%");
		JTextField input5 = new JTextField(3);
		run = new JButton("Start");
				
		this.viewerFrame.add(panel, BorderLayout.SOUTH);

		rate.setFont(new Font("Serif", Font.BOLD, 10));
		select.setFont(new Font("Serif", Font.BOLD, 10));
		cross.setFont(new Font("Serif", Font.BOLD, 10));
		popsize.setFont(new Font("Serif", Font.BOLD, 10));
		gens.setFont(new Font("Serif", Font.BOLD, 10));
		genome.setFont(new Font("Serif", Font.BOLD, 10));
		elite.setFont(new Font("Serif", Font.BOLD, 10));
		selection.setFont(new Font("Serif", Font.BOLD, 10));
		input.setFont(new Font("Serif", Font.BOLD, 10));
		input2.setFont(new Font("Serif", Font.BOLD, 10));
		input3.setFont(new Font("Serif", Font.BOLD, 10));
		input4.setFont(new Font("Serif", Font.BOLD, 10));
		input5.setFont(new Font("Serif", Font.BOLD, 10));
		run.setFont(new Font("Serif", Font.BOLD, 10));
		title.setFont(new Font("Serif", Font.BOLD, 15));

		
		panel.add(rate);
		panel.add(input);
		panel.add(select);
		panel.add(selection);
		panel.add(cross);
		panel.add(crossover);
		panel.add(popsize);
		panel.add(input2);
		panel.add(gens);
		panel.add(input3);
		panel.add(genome);
		panel.add(input4);
		panel.add(elite);
		panel.add(input5);
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
				bestFitViewer.drawingComponent.chromosome = component.population.population.get(0);
				bestFitViewer.drawingComponent.repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		EvolutionViewer app = new EvolutionViewer();
		app.runApp();
	} // main
} // ViewerMain
