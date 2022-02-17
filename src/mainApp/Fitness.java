package mainApp;

public class Fitness {

	Chromosome chromosome;
	
	public Fitness(Chromosome chromosome) {
		this.chromosome = chromosome;
	}
	
	public int countOnes() {
		int total = 0;
		for(int bit : this.chromosome.bits) {
			total += bit;
		}
		return total;
	}
	
	public int matchSmile() {
		int[] smile = {1,1,1,1,1,1,1,1,1,1,
					   1,1,1,1,1,1,1,1,1,1,
					   1,1,1,1,1,1,1,1,1,1,
					   1,1,0,1,1,1,1,0,1,1,
					   1,1,1,1,1,1,1,1,1,1,
					   1,1,1,1,1,1,1,1,1,1,
					   1,0,1,1,1,1,1,1,0,1,
					   1,0,0,0,0,0,0,0,0,1,
					   1,1,1,1,1,1,1,1,1,1,
					   1,1,1,1,1,1,1,1,1,1};
		int total = 0;
		for(int i = 0; i < this.chromosome.bits.size(); i++) {
			if(this.chromosome.bits.get(i) == smile[i]) {
				total++;
			}
		}
		return total;
	}
	
	public int consecutive() {
		int total = 0;
		int temp = 0;
		int i = 0;
		while(true && i < this.chromosome.bits.size() - 1) {
			if(this.chromosome.bits.get(i) == 1) {
				temp++;
				i++;
			}
			else if(i < this.chromosome.bits.size() - 1){
				i++;
				if(temp > total) {
					total = temp;
					temp = 0;
				}
			}
			else {
				break;
			}
		}
		if(temp > total) {
			total = temp;
		}
		return total;
	}
	
	public int paper() {
		int total = 0;
		for(int bit : this.chromosome.bits) {
			total += bit;
			if(bit == 2) {
				total -= bit;
			}
		}
		return total;
	}
	
}
