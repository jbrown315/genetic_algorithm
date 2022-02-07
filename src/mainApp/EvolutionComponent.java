package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class EvolutionComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	
	Population population = new Population();

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.drawLine(50,50,50,250); //LV
		g2d.drawLine(50,50,600,50);//TH
		g2d.drawLine(600,50,600,250);//RV
		g2d.drawLine(50,250,600,250);//BH
		for(int i = 0; i < 11; i++) {
			g2d.drawLine(45, 50 + 20 * i, 55, 50 + 20 * i);
			g2d.drawString(String.valueOf(100 - 10 * i), 20, 50 + 20 * i);
		}
		for(int i = 0; i < 11; i++) {
			g2d.drawLine(50 + 55* i, 255, 50 + 55* i, 245);
			g2d.drawString(String.valueOf(100 - 10 * i), 595 - 55 * i, 270);
		}

	} // paintComponent
}
