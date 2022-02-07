package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class PopulationComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	
	Population population = new Population();

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		population.drawOn(g2d);
	}
}
