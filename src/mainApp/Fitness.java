package mainApp;

public class Fitness {

	Chromosome chromosome;
	
	public Fitness(Chromosome chromosome) {
		this.chromosome = chromosome;
	}
	
	public int countsOnes() {
		int total = 0;
		for(int bit : this.chromosome.bits) {
			total += bit;
		}
		return total;
	}
	
}
