package mainApp;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;



public class ChromosomeViewer {
	public static final Dimension VIEWER_SIZE = new Dimension(500, 400);
	private JFrame viewerFrame;
	
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
		
		file = new File(drawingComponent);
		
		JPanel panel = new JPanel();
		JButton load = new JButton("Load File");
		
		this.viewerFrame.add(panel, BorderLayout.SOUTH);
		
		panel.add(load);
		
		load.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				drawingComponent.chromosome.bits = file.in();
				System.out.println(drawingComponent.chromosome.bits);
				drawingComponent.repaint();
	    	}
	    });

//		public static void main(String[] args) {
//			String nButtonsString = JOptionPane
//					.showInputDialog("How many buttons would you like?");
//			int nButtons = Integer.parseInt(nButtonsString);
//			JFrame myFrame = new JFrame("Linear Lights Out!");
//			HashMap<Integer, JButton> buttons = new HashMap<Integer, JButton>();
//			JPanel panel1 = new JPanel();
//			JPanel panel2 = new JPanel();
//			//myFrame.add(panel1, BorderLayout.CENTER);
//			myFrame.add(panel2, BorderLayout.SOUTH);
//			// you'll want to think about how you want to manage the state of the game
//			//also you want to add some buttons and stuff
//			
//			gameReset(nButtons, panel1, buttons, myFrame);
//
//			JButton newGame = new JButton("New Game");
//			JButton quit = new JButton("Quit");
//			
//		    newGame.addActionListener(new ActionListener() {
//		    	@Override
//		        public void actionPerformed(ActionEvent e) {
//		        	//gameReset(nButtons, panel1, buttons, myFrame);
//		    		myFrame.dispose();
//		    		main(args);
//		    		
//		    	}
//		    });
		
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
