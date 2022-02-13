package mainApp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * Population Class
 * @author brownjs
 *
 */
public class Population {
	private static final int HEIGHT = 5;
	private static final int WIDTH = 5;
	
	ArrayList<Chromosome> population;
	ArrayList<Chromosome> newPop;
	
	int bestFit = 0;
	int aveFit = 0;
	int worstFit = 0;
	int len = 100;
	
	int r = 10;
	int elite = 0;
	boolean cross = false;
	
	/**
	 * Population constructor with zero parameters
	 */
	public Population() {
		if(newPop == null) {
			population = new ArrayList<Chromosome>();
			for(int i = 0; i < len; i++) {
				Chromosome temp = new Chromosome(r*10);
				temp.rows = r;
				population.add(temp);
			}
		}
		else {
			population = newPop;
		}
	}
	
	/**
	 * Population Constructor with a variable length and chromosome length
	 * @param len
	 * @param genomes
	 */
	public Population(int len, int genomes) {
		if(newPop == null) {
			population = new ArrayList<Chromosome>();
			for(int i = 0; i < len; i++) {
				Chromosome temp = new Chromosome(genomes);
				temp.rows = r;
				population.add(temp);
			}
		}
		else {
			population = newPop;
		}
	}
	/**
	 * draws the population onto the provided graphics
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		int count = 1;
		g2.translate(20,20);
		for(Chromosome chr : population) {
			int i = 0;
			int rowCount = 0;
			g2.translate(-WIDTH, 0);
			for(int bit : chr.bits) {
				if (bit == 1) {
					g2.setColor(Color.GREEN);

				}
				else {
					g2.setColor(Color.BLACK);

				}
				if(i >= 10) {
					g2.translate(-WIDTH*9, HEIGHT);
					i=0;
					rowCount++;
				}
				else {
					g2.translate(WIDTH, 0);
				}
				g2.fillRect(0, 0, WIDTH, HEIGHT);
				i++;
			}
			int fac = 0;
			if(chr.bits.size()%10 == 0) {
				fac = 9;
			}
			else {
				fac = chr.bits.size()%10 - 1;
			}
			g2.translate(-WIDTH*fac,-HEIGHT*rowCount);
			if(count == 10) {
				g2.translate(-540,60);
				count = 1;
			}
			else {
				g2.translate(60,0);
				count++;
			}
			Fitness fit = new Fitness(chr);
			chr.fitness = fit.countOnes();
		}
	}
	
	/**
	 * truncation selection algorithm
	 * sorts the population by fitness and then drops the bottom half and mutates the top half
	 * @param mrate
	 */
	public void truncate(int mrate) {
		Chromosome temp = new Chromosome(r*10);
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		
		population = sortByFit(population);
		
		ArrayList<Chromosome> finalpop = new ArrayList<Chromosome>();
		boolean odd = false;
		if(population.size()%2 == 1) {
			odd = true;
		}
		for(int i = 0; i < population.size()/2; i++) {
			temp = new Chromosome(r*10);
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
				temp = new Chromosome(r*10);
				temp2 = new ArrayList<Integer>();
				for(int bit : finalpop.get(i).bits) {
					temp2.add(bit);
				}
				temp.bits = temp2;
				population.add(temp);
			}
		}
		if(odd) {
			temp = new Chromosome(r*10);
			temp2 = new ArrayList<Integer>();
			for(int bit : finalpop.get(finalpop.size() - 1).bits) {
				temp2.add(bit);
			}
			temp.bits = temp2;
			population.add(temp);
		}
		population = sortByFit(population);
		int x = 0;
		for(int i = 0; i < population.size(); i++) {
			Fitness fit = new Fitness(population.get(i));
			if(x < elite) {
				population.get(i).fitness = fit.countOnes();
				x++;
			}
			else {
				if(cross == true) {
					crossover(population.get(i).bits, population.get(i+1).bits);
				}
				population.set(i, population.get(i).mutate(mrate));
				population.get(i).fitness = fit.countOnes();
				x++;
			}
		}
		population = sortByFit(population);
		bestFit = population.get(0).fitness;
		aveFit = 0;
		for(Chromosome chr : population) {
			aveFit += chr.fitness;
		}
		aveFit = aveFit/population.size();
		worstFit = population.get(population.size() - 1).fitness;
//		System.out.println(population.get(98).fitness);

	}
	
	/**
	 * Roulette wheel ranking system
	 * ranks the chromosome's fitness as a percent of total fitness then randomly selects.
	 * @param mrate
	 */
	public void roulette(int mrate) {
		Chromosome temp = new Chromosome(r*10);
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
//		System.out.println("SIZE1" + population.size());

		double fitTotal = 0;
		for(Chromosome chr : population) {
			fitTotal += chr.fitness;
		}
//		System.out.println("FITTOTAL: " + fitTotal);
//		int q = 0;
		if(fitTotal != 0) {
//			for(Chromosome chr : population) {
//				chr.percent = (chr.fitness / fitTotal) * 100.0;	
////				System.out.println(chr.percent);
////				System.out.println(q);
//				q++;
//
//			}
////			System.out.println("SIZE2" + population.size());
//			System.out.println("FITTOTAL: " + fitTotal);
//			double update = 100;
//			for(Chromosome chr : population) {
////				System.out.println(chr.percent);
//				update = update - chr.percent;
////				System.out.println("UPDATE" + update);
//				chr.percent = update;
//			}
//			System.out.println("TEST");
			Random rand = new Random();
			ArrayList<Chromosome> finalpop = new ArrayList<Chromosome>();
			
			//CREATE TEMP FOR TOTAL FITNESS
			// comparing totalfit * random double < fitness
			// AT END HAVE 2 LOOPS and 1 IF ELSE
			// DONT FORGET TO BREAK AFTER SELECTION
			// in else fitTotal = fitTotal - chr.fitness
			double tempTotal = 0;
			for(int i = 0; i < population.size(); i++) {
				 tempTotal = fitTotal;
				
//				double pick = rand.nextDouble() * 100;
				for(Chromosome chr : population) {
					double pick = rand.nextDouble() * tempTotal;
					// fitTotal = fitTotal - chr.fitness
					if(pick < chr.fitness) {
						temp = new Chromosome(r*10);
						temp2 = new ArrayList<Integer>();
						for(int bit : population.get(i).bits) {
							temp2.add(bit);
						}
						temp.bits = temp2;
						finalpop.add(temp);
						break;
					}
					else {
						tempTotal = tempTotal - chr.fitness;
					}
				}
				
				
			}
			
			population = new ArrayList<Chromosome>();
			for(Chromosome chr : finalpop) {
				population.add(chr);
			}
			population = sortByFit(population);
			int x = 0;
			for(int i = 0; i < population.size(); i++) {
				Fitness fit = new Fitness(population.get(i));
				if(x < elite) {
					population.get(i).fitness = fit.countOnes();
					x++;
				}
				else {
					if(cross == true) {
						crossover(population.get(i).bits, population.get(i+1).bits);
					}
					population.set(i, population.get(i).mutate(mrate));
					population.get(i).fitness = fit.countOnes();
					x++;
				}
			}
			population = sortByFit(population);
			bestFit = population.get(0).fitness;
			aveFit = 0;
			for(Chromosome chr : population) {
				aveFit += chr.fitness;
			}
			aveFit = aveFit/population.size();
			worstFit = population.get(population.size() - 1).fitness;
		}
	}
	/**
	 * Helper function to sort the population by fitness
	 * @param current
	 * @return
	 */
	public ArrayList<Chromosome> sortByFit(ArrayList<Chromosome> current) {
		ArrayList<Chromosome> newpop = new ArrayList<Chromosome>();
		ArrayList<Integer> fits = new ArrayList<Integer>();
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
		return newpop;
	}
	
	public void crossover(ArrayList<Integer> one, ArrayList<Integer> two) {
		ArrayList<Integer> p1 = new ArrayList<Integer>();
		ArrayList<Integer> p2 = new ArrayList<Integer>();
		ArrayList<Integer> p3 = new ArrayList<Integer>();
		ArrayList<Integer> p4 = new ArrayList<Integer>();
		for(int a = 0; a < one.size(); a++) {
			if(a < one.size()/2) {
				p1.add(one.get(a));
			}
			else {
				p2.add(one.get(a));
			}
		}
		for(int a = 0; a < two.size(); a++) {
			if(a < two.size()/2) {
				p3.add(two.get(a));
			}
			else {
				p4.add(two.get(a));
			}
		}
		one = new ArrayList<Integer>();
		two = new ArrayList<Integer>();
		one.addAll(p1);
		one.addAll(p4);
		two.addAll(p3);
		two.addAll(p2);
	}
	
}
