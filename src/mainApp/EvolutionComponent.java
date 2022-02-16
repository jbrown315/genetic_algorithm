package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;

import javax.swing.JComponent;


public class EvolutionComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	
	int runs = -1;
	int gens = 100;
	
	Population population = new Population();
	ArrayList<Integer> bcoords = new ArrayList<Integer>();
	ArrayList<Integer> acoords = new ArrayList<Integer>();
	ArrayList<Integer> wcoords = new ArrayList<Integer>();
	ArrayList<Integer> ucoords = new ArrayList<Integer>();

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.drawLine(50,20,50,220); //LV
		g2d.drawLine(50,20,700,20);//TH
		g2d.drawLine(700,20,700,220);//RV
		g2d.drawLine(50,220,700,220);//BH
		g2d.drawString("% Fit", 35, 8);
		g2d.drawString("# of Gen", 705, 220);
		
		
		if(runs == -1) {
			g2d.drawString("# of Gens: " + 0, 620, 15);
		}
		else {
			g2d.drawString("# of Gens: " + runs, 620, 15);
		}
		for(int i = 0; i < 11; i++) {
			g2d.drawLine(45, 20 + 20 * i, 55, 20 + 20 * i);
			g2d.drawString(String.valueOf(100 - 10 * i), 20, 20 + 20 * i);
		}
		for(int i = 0; i < 11; i++) {
			g2d.drawLine(50 + 65* i, 225, 50 + 65* i, 215);
			g2d.drawString(String.valueOf(gens - gens/10 * i), 695 - 65 * i, 240);
		}
		
		int y4 = (int) (((double) (population.unique) / (double) (population.population.get(0).bits.size())) * 100);
		g2d.setColor(Color.YELLOW);
		
		if(runs >= 0) {
			ucoords.add((int) (50+runs* (6.5 / (gens / 100.0))));
			ucoords.add(220-y4*2);
		}
		if(ucoords.size() > 2) {
			for(int i = 0; i < ucoords.size()-2; i+=2) {
				g2d.setStroke(new BasicStroke(3));
                g2d.draw(new Line2D.Float(ucoords.get(0 + i), ucoords.get(1+i), ucoords.get(2+i), ucoords.get(3+i)));
			}
		}
		
		
		int y1 = (int) (((double) (population.bestFit) / (double) (population.population.get(0).bits.size())) * 100);
		g2d.setColor(Color.GREEN);
		
		if(runs >= 0) {
			bcoords.add((int) (50+runs* (6.5 / (gens / 100.0))));
			bcoords.add(220-y1*2);
		}
		if(bcoords.size() > 2) {
			for(int i = 0; i < bcoords.size()-2; i+=2) {
				g2d.setStroke(new BasicStroke(3));
                g2d.draw(new Line2D.Float(bcoords.get(0 + i), bcoords.get(1+i), bcoords.get(2+i), bcoords.get(3+i)));
			}
		}
		
		int y2 = (int) (((double) (population.aveFit) / (double) (population.population.get(0).bits.size())) * 100);
		g2d.setColor(Color.ORANGE);
		
		if(runs >= 0) {
			acoords.add((int) (50+runs* (6.5 / (gens / 100.0))));
			acoords.add(220-y2*2);
		}
		if(acoords.size() > 2) {
			for(int i = 0; i < acoords.size()-2; i+=2) {
				g2d.setStroke(new BasicStroke(3));
                g2d.draw(new Line2D.Float(acoords.get(0 + i), acoords.get(1+i), acoords.get(2+i), acoords.get(3+i)));
			}
		}
		
		int y3 = (int) (((double) (population.worstFit) / (double) (population.population.get(0).bits.size())) * 100);
		g2d.setColor(Color.RED);
		
		if(runs >= 0) {
			wcoords.add((int) (50+runs* (6.5 / (gens / 100.0))));
			wcoords.add(220-y3*2);
		}
		if(wcoords.size() > 2) {
			for(int i = 0; i < wcoords.size()-2; i+=2) {
				g2d.setStroke(new BasicStroke(3));
                g2d.draw(new Line2D.Float(wcoords.get(0 + i), wcoords.get(1+i), wcoords.get(2+i), wcoords.get(3+i)));
			}
		}
		
		
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(600, 120, 12, 12);
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(600, 140, 12, 12);
		g2d.setColor(Color.RED);
		g2d.fillRect(600, 160, 12, 12);
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(600, 180, 12, 12);
		
		g2d.setColor(Color.BLACK);
		g2d.drawString("Best Fit: " + population.bestFit, 620, 130);
		g2d.drawString("Ave Fit: " + population.aveFit, 620, 150);
		g2d.drawString("Worst Fit: " + population.worstFit, 620, 170);
		g2d.drawString("# of Unique: " + population.unique, 620, 190);

	} // paintComponent
}
