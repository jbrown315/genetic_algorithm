package mainApp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Population {
	private static final int HEIGHT = 5;
	private static final int WIDTH = 5;
	
	ArrayList<Chromosome> population;
	ArrayList<Chromosome> newPop;
	
	public Population() {
		if(newPop == null) {
			population = new ArrayList<Chromosome>();
			for(int i = 0; i < 100; i++) {
				population.add(new Chromosome());
			}
		}
		else {
			population = newPop;
		}
	}
	
	public void drawOn(Graphics2D g2) {
		int count = 1;
		g2.translate(20,20);
		for(Chromosome chr : population) {
			for(int x = 0; x < 10; x++) {
				for(int i = 0; i < 10; i++) {
					int index = 10 * i + x;
					if (chr.bits.get(index) == 1) {
						g2.setColor(Color.GREEN);
					}
					else {
						g2.setColor(Color.BLACK);
					}
					g2.fillRect(0 + x*HEIGHT,0 + i * WIDTH, WIDTH, HEIGHT);
				}
			}
			if(count == 10) {
				g2.translate(-540,60);
				count = 1;
			}
			else {
				g2.translate(60,0);
				count++;
			}
			Fitness fit = new Fitness(chr);
			chr.fitness = fit.countsOnes();
			//System.out.println(chr.fitness);
		}
	}
}
