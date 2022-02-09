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
	PopulationViewer popViewer;
	JButton run;
	
	boolean graphed = false;
	
	String choice;
	int mrate;
	int popSize;

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
		
		popViewer = new PopulationViewer();
		
		popViewer.runApp();
		
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

		input.setText("1");
		input2.setText("100");
		input3.setText("100");
		input4.setText("100");

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

		alterPop();
		run.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if(run.getText().equals("Restart")) {
					
					runApp();
				}
				choice = (String) selection.getSelectedItem();
				if(input.getText().equals("")) {
					System.out.println("Invalid Mutation Rate!");
				}
				else if(input.getText().equals("")) {
					System.out.println("Invalid Population Size!");
				}
				else {
					mrate = Integer.valueOf(input.getText());
					popSize = Integer.valueOf(input2.getText());
					if(mrate >= 0 && mrate <= 100 && popSize >= 0 && popSize <= 100) {
						if(choice.equals("Truncate")) {
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
								popViewer.drawingComponent.population = new Population(popSize);
								t.start();
							}
						}
						else {
							System.out.println("INVALID");
						}
					}
					else {
						System.out.println("Invalid Mutation Rate!");
					}
				}
			}
	    });
				
		
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public void alterPop() {
		t = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				popViewer.drawingComponent.population = new Population(popSize);
				popViewer.drawingComponent.population.truncate(mrate);
				popViewer.drawingComponent.repaint();
				drawingComponent.repaint();
				drawingComponent.runs++;
				drawingComponent.population = popViewer.drawingComponent.population;
				if(drawingComponent.runs >= 100) {
					run.doClick();
					run.setText("Restart");
				}
				bestFitViewer.drawingComponent.chromosome = popViewer.drawingComponent.population.population.get(0);
				bestFitViewer.drawingComponent.repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		EvolutionViewer app = new EvolutionViewer();
		app.runApp();
	} // main
} // ViewerMain
