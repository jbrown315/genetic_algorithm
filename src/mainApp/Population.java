package mainApp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

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
	
	public void truncate() {
		ArrayList<Chromosome> newpop = new ArrayList<Chromosome>();
		ArrayList<Integer> fits = new ArrayList<Integer>();
		Chromosome temp = new Chromosome();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();

		for(int i = 0; i < population.size(); i++) {
			fits.add(population.get(i).fitness);
		}
		Collections.sort(fits);
		Collections.reverse(fits);
		for(int i = 0; i < fits.size(); i++) {
			for(int x = 0; x < fits.size(); x++) {
				if(population.get(x).fitness == fits.get(i)) {
					newpop.add(population.get(x));
					population.remove(x);
					break;
				}
			}
		}
		population = newpop;
		
		ArrayList<Chromosome> finalpop = new ArrayList<Chromosome>();
		for(int i = 0; i < population.size()/2; i++) {
			temp = new Chromosome();
			temp2 = new ArrayList<Integer>();
			for(int bit : population.get(i).bits) {
				temp2.add(bit);
			}
			temp.bits = temp2;
			finalpop.add(temp);
		}
		population = new ArrayList<Chromosome>();
		for(int i = 0; i < finalpop.size(); i++) {
			for(int x = 0; x < 2; x++) {
				temp = new Chromosome();
				temp2 = new ArrayList<Integer>();
				for(int bit : finalpop.get(i).bits) {
					temp2.add(bit);
				}
				temp.bits = temp2;
				population.add(temp);
			}
		}
		for(Chromosome chr : population) {
			chr = chr.mutate(1);
			Fitness fit = new Fitness(chr);
			chr.fitness = fit.countsOnes();
		}
		System.out.println("FIT OF BEST: " + population.get(0).fitness);
	}
}
