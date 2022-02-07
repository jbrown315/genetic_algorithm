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
	
	Population population = new Population();
	ArrayList<Integer> bcoords = new ArrayList<Integer>();
	ArrayList<Integer> acoords = new ArrayList<Integer>();
	ArrayList<Integer> wcoords = new ArrayList<Integer>();

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
		int y1 = population.bestFit;
		g2d.setColor(Color.GREEN);
		
		if(runs >= 0) {
			bcoords.add((int) (50+runs*5.5));
			bcoords.add(250-y1*2);
		}
		if(bcoords.size() > 2) {
			for(int i = 0; i < bcoords.size()-2; i+=2) {
				g2d.setStroke(new BasicStroke(3));
                g2d.draw(new Line2D.Float(bcoords.get(0 + i), bcoords.get(1+i), bcoords.get(2+i), bcoords.get(3+i)));
			}
		}
		
		int y2 = population.aveFit;
		g2d.setColor(Color.ORANGE);
		
		if(runs >= 0) {
			acoords.add((int) (50+runs*5.5));
			acoords.add(250-y2*2);
		}
		if(acoords.size() > 2) {
			for(int i = 0; i < acoords.size()-2; i+=2) {
				g2d.setStroke(new BasicStroke(3));
                g2d.draw(new Line2D.Float(acoords.get(0 + i), acoords.get(1+i), acoords.get(2+i), acoords.get(3+i)));
			}
		}
		
		int y3 = population.worstFit;
		g2d.setColor(Color.RED);
		
		if(runs >= 0) {
			wcoords.add((int) (50+runs*5.5));
			wcoords.add(250-y3*2);
		}
		if(wcoords.size() > 2) {
			for(int i = 0; i < wcoords.size()-2; i+=2) {
				g2d.setStroke(new BasicStroke(3));
                g2d.draw(new Line2D.Float(wcoords.get(0 + i), wcoords.get(1+i), wcoords.get(2+i), wcoords.get(3+i)));
			}
		}

	} // paintComponent
}
