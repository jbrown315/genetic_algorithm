package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class ChromosomeComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	
	Chromosome chromosome = new Chromosome();
	Files file = new Files(this);

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		chromosome.drawOn(g2d);


	} // paintComponent
}
