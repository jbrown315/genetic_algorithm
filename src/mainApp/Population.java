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
	int height = 5;
	int width = 5;
	
	ArrayList<Chromosome> population;
	ArrayList<Chromosome> newPop;
	
	int bestFit = 0;
	int aveFit = 0;
	int worstFit = 0;
	int len = 100;
	int factor = 10000;
	
	int r = 10;
	int elite = 0;
	boolean cross = false;
	
	int fitmethod = 0;
	
	double numInRow;
	double numOfRows;
	double lastRowCount;

	int unique;
	
	double bookCorrect;
	double bookIncorrect;
	double bookUndecided;
	
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
	
	public Population(int len, int genomes, boolean paper) {
		if(newPop == null) {
			population = new ArrayList<Chromosome>();
			for(int i = 0; i < len; i++) {
				Chromosome temp = new Chromosome(genomes, true);
				temp.rows = r;
				population.add(temp);
//				System.out.println("TEST");
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
		numInRow = Math.sqrt(population.size());
		numOfRows = (population.size() / numInRow) + 1;
		lastRowCount = population.size() % numInRow;
		if(lastRowCount == 0) {
			numOfRows -= 1;
		}
		width = (int) (50/ (numInRow + 1));
		height = (int) (50/ (numInRow + 1));
		if(population.size() == 100) {
			width = 5;
			height = 5;
		}
		
		int numInRowInt = (int) numInRow;
		for(Chromosome chr : population) {
			int i = 0;
			int rowCount = 0;
			g2.translate(-width, 0);
			for(int bit : chr.bits) {
				if (bit == 1) {
					g2.setColor(Color.GREEN);

				}
				else {
					g2.setColor(Color.BLACK);

				}
				if(i >= 10) {
					g2.translate(-width*9, height);
					i=0;
					rowCount++;
				}
				else {
					g2.translate(width, 0);
				}
				g2.fillRect(0, 0, width, height);
				i++;
			}
			int fac = 0;
			if(chr.bits.size()%10 == 0) {
				fac = 9;
			}
			else {
				fac = chr.bits.size()%10 - 1;
			}
			g2.translate(-width*fac,-height*rowCount);
			if(count == numInRowInt) {
				g2.translate(-width*12*(numInRowInt-1),height*12);
				count = 1;
			}
			else {
				g2.translate(width*(12),0);
				count++;
			}
			Fitness fit = new Fitness(chr);
			if(fitmethod == 0) {
				chr.fitness = fit.countOnes();
			}
			else if(fitmethod == 1) {
				chr.fitness = fit.matchSmile();
			}
			else {
				chr.fitness = fit.consecutive();
			}
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
				if(fitmethod == 0) {
					population.get(i).fitness = fit.countOnes();
				}
				else if(fitmethod == 1) {
					population.get(i).fitness = fit.matchSmile();
				}
				else if(fitmethod == 2) {
					population.get(i).fitness = fit.consecutive();
				}
				x++;
			}
			else {
				if(cross == true) {
					ArrayList<ArrayList<Integer>> fix = new ArrayList<ArrayList<Integer>>();
					if(i+1 != population.size()) {
						fix = crossover(population.get(i).bits, population.get(i+1).bits);
						population.get(i).bits = new ArrayList<Integer>();
						population.get(i+1).bits = new ArrayList<Integer>();
						
						for(int bit : fix.get(0)) {
							population.get(i).bits.add(bit);
						}
						for(int bit : fix.get(1)) {
							population.get(i+1).bits.add(bit);
						}
					}
				}
				population.set(i, population.get(i).mutate(mrate));
				if(fitmethod == 0) {
					population.get(i).fitness = fit.countOnes();
				}
				else if(fitmethod == 1) {
					population.get(i).fitness = fit.matchSmile();
				}
				else if(fitmethod == 2) {
					population.get(i).fitness = fit.consecutive();
				}
				x++;
			}
		}
		population = sortByFit(population);
		unique = unique(population);
		bestFit = population.get(0).fitness;
		aveFit = 0;
		for(Chromosome chr : population) {
			aveFit += chr.fitness;
		}
		aveFit = aveFit/population.size();
		worstFit = population.get(population.size() - 1).fitness;

	}
	
	/**
	 * Roulette wheel ranking system
	 * ranks the chromosome's fitness as a percent of total fitness then randomly selects.
	 * @param mrate
	 */
	public void roulette(int mrate) {
		
		Chromosome temp = new Chromosome(r*10);
		ArrayList<Integer> temp2 = new ArrayList<Integer>();

		double fitTotal = 0;
		for(Chromosome chr : population) {
			Fitness fit = new Fitness(chr);
			if(fitmethod == 0) {
				chr.fitness = fit.countOnes();
			}
			else if(fitmethod == 1) {
				chr.fitness = fit.matchSmile();
			}
			else {
				chr.fitness = fit.consecutive();
			}
			fitTotal += chr.fitness;
		}
			Random rand = new Random();
			ArrayList<Chromosome> finalpop = new ArrayList<Chromosome>();
			
			boolean odd = false;
			if(population.size()%2 == 1) {
				odd = true;
			}
			
			double tempTotal = 0;
			
			for(int i = 0; i < population.size()/2; i++) {
				tempTotal = fitTotal;
				for(Chromosome chr : population) {
					double pick = rand.nextDouble() * tempTotal;
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
					if(fitmethod == 0) {
						population.get(i).fitness = fit.countOnes();
					}
					else if(fitmethod == 1) {
						population.get(i).fitness = fit.matchSmile();
					}
					else if(fitmethod == 2) {
						population.get(i).fitness = fit.consecutive();
					}
					x++;
				}
				else {
					if(cross == true) {
						ArrayList<ArrayList<Integer>> fix = new ArrayList<ArrayList<Integer>>();
						if(i+1 != population.size()) {
							fix = crossover(population.get(i).bits, population.get(i+1).bits);
							population.get(i).bits = new ArrayList<Integer>();
							population.get(i+1).bits = new ArrayList<Integer>();
							
							for(int bit : fix.get(0)) {
								population.get(i).bits.add(bit);
							}
							for(int bit : fix.get(1)) {
								population.get(i+1).bits.add(bit);
							}
						}
					}
					population.set(i, population.get(i).mutate(mrate));
					if(fitmethod == 0) {
						population.get(i).fitness = fit.countOnes();
					}
					else if(fitmethod == 1) {
						population.get(i).fitness = fit.matchSmile();
					}
					else if(fitmethod == 2) {
						population.get(i).fitness = fit.consecutive();
					}
					x++;
				}
			}
			population = sortByFit(population);
			unique = unique(population);
			bestFit = population.get(0).fitness;
			aveFit = 0;
			for(Chromosome chr : population) {
				aveFit += chr.fitness;
			}
			aveFit = aveFit/population.size();
			worstFit = population.get(population.size() - 1).fitness;
		}

	
	public void ranked(int mrate) {
		Chromosome temp = new Chromosome(r*10);
		ArrayList<Integer> temp2 = new ArrayList<Integer>();

		Random rand = new Random();
		ArrayList<Chromosome> finalpop = new ArrayList<Chromosome>();

		population = sortByFit(population);
		int p = population.size();
		for(Chromosome chr : population) {
			chr.rank = p;
			p--;
		}
		boolean odd = false;
		if(population.size()%2 == 1) {
			odd = true;
		}
		
		for(int i = 0; i < population.size()/2; i++) {
			int pick = rand.nextInt(1, population.size() - 1);
			for(Chromosome chr : population) {
				
				
				if(pick == chr.rank) {
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
				}
			}
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
				if(fitmethod == 0) {
					population.get(i).fitness = fit.countOnes();
				}
				else if(fitmethod == 1) {
					population.get(i).fitness = fit.matchSmile();
				}
				else if(fitmethod == 2) {
					population.get(i).fitness = fit.consecutive();
				}
				x++;
			}
			else {
				if(cross == true) {
					ArrayList<ArrayList<Integer>> fix = new ArrayList<ArrayList<Integer>>();
					if(i+1 != population.size()) {
						fix = crossover(population.get(i).bits, population.get(i+1).bits);
						population.get(i).bits = new ArrayList<Integer>();
						population.get(i+1).bits = new ArrayList<Integer>();
						
						for(int bit : fix.get(0)) {
							population.get(i).bits.add(bit);
						}
						for(int bit : fix.get(1)) {
							population.get(i+1).bits.add(bit);
						}
					}
				}
				population.set(i, population.get(i).mutate(mrate));
				if(fitmethod == 0) {
					population.get(i).fitness = fit.countOnes();
				}
				else if(fitmethod == 1) {
					population.get(i).fitness = fit.matchSmile();
				}
				else if(fitmethod == 2) {
					population.get(i).fitness = fit.consecutive();
				}
				x++;
			}
		}
		population = sortByFit(population);
		unique = unique(population);
		bestFit = population.get(0).fitness;
		aveFit = 0;
		for(Chromosome chr : population) {
			aveFit += chr.fitness;
		}
		aveFit = aveFit/population.size();
		worstFit = population.get(population.size() - 1).fitness;
	}
	
	public void truncateSpecial(int mrate) {
		Chromosome temp = new Chromosome(r*10);
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		boolean plague = false;
		
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
		
		Random rand = new Random();
		for(int i = 0; i < population.size(); i++) {
			Fitness fit = new Fitness(population.get(i));
			
			if(x < elite) {
				if(fitmethod == 0) {
					population.get(i).fitness = fit.countOnes();
				}
				else if(fitmethod == 1) {
					population.get(i).fitness = fit.matchSmile();
				}
				else if(fitmethod == 2) {
					population.get(i).fitness = fit.consecutive();
				}
				x++;
			}
			else {
				if(cross == true) {
					ArrayList<ArrayList<Integer>> fix = new ArrayList<ArrayList<Integer>>();
					if(i+1 != population.size()) {
						fix = crossover(population.get(i).bits, population.get(i+1).bits);
						population.get(i).bits = new ArrayList<Integer>();
						population.get(i+1).bits = new ArrayList<Integer>();
						
						for(int bit : fix.get(0)) {
							population.get(i).bits.add(bit);
						}
						for(int bit : fix.get(1)) {
							population.get(i+1).bits.add(bit);
						}
					}
				}
				
				population.set(i, population.get(i).mutate(mrate));
				
				if(fitmethod == 0) {
					population.get(i).fitness = fit.countOnes();
				}
				else if(fitmethod == 1) {
					population.get(i).fitness = fit.matchSmile();
				}
				else if(fitmethod == 2) {
					population.get(i).fitness = fit.consecutive();
				}
				x++;
				int choice = rand.nextInt(factor);
				if(choice == 1) {
					if(factor > 1000) {
						factor -= 1000;
					}
					else {
						factor = 2;
					}
					population.remove(i);
				}
			}
		}
		population = sortByFit(population);
		unique = unique(population);
		bestFit = population.get(0).fitness;
		aveFit = 0;
		for(Chromosome chr : population) {
			aveFit += chr.fitness;
		}
		aveFit = aveFit/population.size();
		worstFit = population.get(population.size() - 1).fitness;

	}
	
	public void paper(int mrate) {
		Chromosome temp = new Chromosome(r*10);
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		Random rand = new Random();
		ArrayList<Chromosome> finalpop = new ArrayList<Chromosome>();
		
		bookCorrect = 0;
		bookIncorrect = 0;
		bookUndecided = 0;
		

		for(int i = 0; i < population.size(); i++) {
				int current = 0;
				int tot = 0;
				for(int bit : population.get(i).bits) {
					if(bit == 1) {
						tot++;
						current++;
					}
					else if(bit == 0) {
						tot++;
					}
				}
				double before = (double) current / (double) tot;
				for(int x = 0; x < 100; x++) {
					temp = new Chromosome(r*10);
					temp2 = new ArrayList<Integer>();
					for(int bit : population.get(i).bits) {
						if(bit == 2) {
							int q = rand.nextInt(2);
							if(q == 1) {
								temp2.add(2);
							}
							else {
								q = rand.nextInt(2);
								if(q == 1) {
									temp2.add(q);
								}
								else {
									temp2.add(q);
								}
							}
						}
						else{
							temp2.add(bit);
						}
					}
					temp.bits = temp2;
					int newFit = 0;
					for(int bit : temp.bits) {
						newFit += bit;
					}
					double newReal = (double) newFit / (double) temp.bits.size();
					if(before < newReal) {
						temp.fitness = (int) newReal;
						finalpop.add(temp);
						bookIncorrect += 1;
						break;
					}
				}
				if(finalpop.size() != i + 1) {
					population.get(i).fitness = (int) before;
					finalpop.add(population.get(i));
					bookCorrect += 1;
				}
				bookUndecided = 1000 - (bookCorrect);
				
				
		}
		population = new ArrayList<Chromosome>();
		for(int i = 0; i < finalpop.size(); i++) {
			temp = new Chromosome(r*10);
			temp2 = new ArrayList<Integer>();
			for(int bit : finalpop.get(i).bits) {
				temp2.add(bit);
			}
			temp.bits = temp2;
			population.add(temp);
		}
		ArrayList<Chromosome> newPopAgain = new ArrayList<Chromosome>();
		for(int x = 0; x < 1000; x++) {
			int first = rand.nextInt(20);
			int next = rand.nextInt(20);
			Chromosome newChr1 = new Chromosome(r*10);
			ArrayList<ArrayList<Integer>> fix = new ArrayList<ArrayList<Integer>>();
			fix = crossover(population.get(first).bits, population.get(next).bits);
			newChr1.bits = new ArrayList<Integer>();
			for(int bit : fix.get(0)) {
				newChr1.bits.add(bit);
			}
			newPopAgain.add(newChr1);
		}
		population = new ArrayList<Chromosome>();
		for(int i = 0; i < newPopAgain.size(); i++) {
			temp = new Chromosome(r*10);
			temp2 = new ArrayList<Integer>();
			for(int bit : newPopAgain.get(i).bits) {
				temp2.add(bit);
			}
			temp.bits = temp2;
			population.add(temp);
		}
		population = sortByFit(population);
//		System.out.println("BOOK CORRECT: " + bookCorrect);
//		System.out.println("BOOK INCORRECT: " + bookIncorrect);
//		System.out.println("BOOK UNDECIDED: " + bookUndecided);

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
	
	public ArrayList<ArrayList<Integer>> crossover(ArrayList<Integer> one, ArrayList<Integer> two) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> p1 = new ArrayList<Integer>();
		ArrayList<Integer> p2 = new ArrayList<Integer>();
		ArrayList<Integer> p3 = new ArrayList<Integer>();
		ArrayList<Integer> p4 = new ArrayList<Integer>();
		Random rand = new Random();
		int random = rand.nextInt(one.size());
		for(int a = 0; a < one.size(); a++) {
			if(a < random) {
				p1.add(one.get(a));
			}
			else {
				p2.add(one.get(a));
			}
		}
		for(int a = 0; a < two.size(); a++) {
			if(a < random) {
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
		result.add(one);
		result.add(two);
		return result;
	}
	
	public int unique(ArrayList<Chromosome> pop) {
		int count = 0;
		for (int i = 0; i < pop.size() - 1; i++) {
			if(pop.get(i).fitness == pop.get(i+1).fitness) {
				if(pop.get(i).bits.equals(pop.get(i+1).bits)) {
					count++;
				}
			}
		}
		return pop.size() - count;
	}
	
}
