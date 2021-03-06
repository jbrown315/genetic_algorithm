package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;



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
	public static final Dimension VIEWER_SIZE = new Dimension(765, 375);
	
	JFrame viewerFrame;
	Timer t;
	int iterations = 0;
	EvolutionComponent drawingComponent;
	BestFitViewer bestFitViewer;
	PopulationViewer popViewer;
	JButton run;
	JButton reset;
	JLabel main;
	
	boolean graphed = false;
	
	String choice;
	String fitChoice;
	int mrate;
	int popSize;
	int generations;
	int genLen;
	int elitism;
	boolean checked;
	int terminateStatus;
	
	boolean paperResult;	
	int method;

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

		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel2 = new JPanel();
		JPanel control = new JPanel(new GridLayout(2,1));
		JLabel rate = new JLabel("M Rate: _/N");
		JTextField input = new JTextField(3);
		JLabel select = new JLabel("Selection");
		String[] options = {"Truncate", "Roulette Wheel", "Ranked", "Truncate*"};
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
		JLabel fitSel = new JLabel("Fit Method");
		String[] fitOptions = {"All Ones", "Smile", "Consecutive Ones"};
		JComboBox<String> fitType = new JComboBox<String>(fitOptions);
		run = new JButton("Start");
		reset = new JButton("Reset");
		
		JLabel term = new JLabel("Terminate?");
		JTextField terminate = new JTextField(3);
		
		JLabel paperRes = new JLabel("Reproduce Paper Result?");
		JCheckBox paper = new JCheckBox();
		
		this.viewerFrame.add(control, BorderLayout.SOUTH);

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
		reset.setFont(new Font("Serif", Font.BOLD, 10));
		title.setFont(new Font("Serif", Font.BOLD, 15));
		fitType.setFont(new Font("Serif", Font.BOLD, 10));
		fitSel.setFont(new Font("Serif", Font.BOLD, 10));
		term.setFont(new Font("Serif", Font.BOLD, 10));
		terminate.setFont(new Font("Serif", Font.BOLD, 10));
		paperRes.setFont(new Font("Serif", Font.BOLD, 10));
		paper.setFont(new Font("Serif", Font.BOLD, 10));

		input.setText("1");
		input2.setText("100");
		input3.setText("100");
		input4.setText("100");
		input5.setText("1");
		terminate.setText("100");
//		crossover.setSelected(true);

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
		
		panel2.add(paperRes);
		panel2.add(paper);
		panel2.add(term);
		panel2.add(terminate);
		panel2.add(fitSel);
		panel2.add(fitType);
		panel2.add(run);
		panel2.add(reset);
		
		control.add(panel);
		control.add(panel2);
		
		alterPop();
		run.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if(run.getText().equals("Finished!")) {
					return;
				}
				choice = (String) selection.getSelectedItem();
				fitChoice = (String) fitType.getSelectedItem();
				if(input.getText().equals("")) {
					System.out.println("Invalid Mutation Rate!");
				}
				else if(input2.getText().equals("")) {
					System.out.println("Invalid Population Size!");
				}
				else if(input3.getText().equals("")) {
					System.out.println("Invalid Number of Generations!");
				}
				else if(input4.getText().equals("")) {
					System.out.println("Invalid Genome Length!");
				}
				else if(input5.getText().equals("")) {
					System.out.println("Invalid Elitism!");
				}
				else if(terminate.getText().equals("")) {
					System.out.println("Invalid Elitism!");
				}
				else {
					mrate = Integer.valueOf(input.getText());
					popSize = Integer.valueOf(input2.getText());
					generations = Integer.valueOf(input3.getText());
					genLen = Integer.valueOf(input4.getText());
					elitism = Integer.valueOf(input5.getText());
					checked = crossover.isSelected();
					paperResult = paper.isSelected();
					terminateStatus = Integer.valueOf(terminate.getText());
					main = title;
					if(!paperResult) {
						if(mrate >= 0 && mrate <= 100 && popSize > 1 && popSize <= 1000 && generations >= 0 && generations <= 1000 && genLen >= 0 && genLen <= 100 && elitism >= 0 && elitism <= 100 && terminateStatus  >= 0 && terminateStatus <= 100) {
							if(choice.equals("Truncate")) {
								method = 0;
							}
							else if(choice.equals("Roulette Wheel")) {
								method = 1;
							}
							else if(choice.equals("Truncate*")) {
								method = 4;
							}
							else {
								method = 2;
							}
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
								if(drawingComponent.runs <= 1) {
									popViewer.drawingComponent.population = new Population(popSize, genLen);
									popViewer.drawingComponent.population.r = genLen / 10;
									popViewer.drawingComponent.population.elite = elitism;
									popViewer.drawingComponent.population.cross = checked;
									if(fitChoice.equals("All Ones")) {
										popViewer.drawingComponent.population.fitmethod = 0;
									}
									else if(fitChoice.equals("Smile")) {
										popViewer.drawingComponent.population.fitmethod = 1;
									}
									else if(fitChoice.equals("Consecutive Ones")) {
										popViewer.drawingComponent.population.fitmethod = 2;
									}
									drawingComponent.gens = generations;
								}
								
								t.start();
							}
							
						}
						else {
							System.out.println("Invalid Inputs!");
						}
					}
					else {
						//RUN PAPER MODEL HERE
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
							if(drawingComponent.runs <= 1) {
								method = 3;
								popViewer.drawingComponent.population = new Population(1000, 20, true);
								generations = 50;
								bestFitViewer.viewerFrame.dispose();
								popViewer.drawingComponent.population.r = genLen / 10;
								popViewer.drawingComponent.population.fitmethod = 3;
								drawingComponent.gens = generations;
								drawingComponent.paper = paperResult;
								main.setText("Reproduce Scientific Paper Result");
							}
							
							t.start();
						}
					}
				}
			}
	    });
		
		reset.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				 EvolutionViewer next = new EvolutionViewer();
				 viewerFrame.dispose();
				 popViewer.viewerFrame.dispose();
				 bestFitViewer.viewerFrame.dispose();
				 next.runApp();
			}
	    });
				
		
		this.viewerFrame.setVisible(true);
	} // runApp

	// *********************************************************************

	public void alterPop() {
		t = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(popViewer.drawingComponent.population.population.size() != 1) {
					if(method == 0) {
						popViewer.drawingComponent.population.truncate(mrate);
					}
					else if(method == 1) {
						popViewer.drawingComponent.population.roulette(mrate);
					}
					else if(method == 2) {
						popViewer.drawingComponent.population.ranked(mrate);
					}
					else if(method == 3) {
						popViewer.drawingComponent.population.paper(mrate);
					}
					else if(method == 4) {
						popViewer.drawingComponent.population.truncateSpecial(mrate);
					}
					else {
						System.out.println("???");
					}
					int temp = 0;
					if(popViewer.drawingComponent.population.lastRowCount == 0) {
						temp = 1;
					}
					else if(popViewer.drawingComponent.population.lastRowCount > popViewer.drawingComponent.population.numInRow) {
						temp = 3;
					}
					else {
						temp = 2;
					}
					popViewer.viewerFrame.setSize(new Dimension(popViewer.drawingComponent.population.width *12* ((int) (popViewer.drawingComponent.population.numInRow)) + 50, popViewer.drawingComponent.population.height * 12 * ((int) (popViewer.drawingComponent.population.numOfRows) + temp) + 50));
					popViewer.drawingComponent.repaint();
					drawingComponent.repaint();
					drawingComponent.runs++;
					drawingComponent.population = popViewer.drawingComponent.population;
					if(drawingComponent.runs >= generations) {
						run.doClick();
						run.setText("Finished!");
					}
					else if(popViewer.drawingComponent.population.bestFit == terminateStatus || popViewer.drawingComponent.population.bestFit / popViewer.drawingComponent.population.population.get(0).bits.size() == 1) {
						run.doClick();
						run.setText("Finished!");
					}
					bestFitViewer.drawingComponent.chromosome = popViewer.drawingComponent.population.population.get(0);
					bestFitViewer.drawingComponent.repaint();
				}
				else {
					run.doClick();
					run.setText("Finished!");
					popViewer.viewerFrame.dispose();
					bestFitViewer.viewerFrame.dispose();
					drawingComponent.getGraphics().drawString("Population Die Off", 600, 35);
					
				}
			}
		});
	}
	
	public static void main(String[] args) {
		EvolutionViewer app = new EvolutionViewer();
		app.runApp();
	} // main
} // ViewerMain
