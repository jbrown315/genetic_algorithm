package mainApp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class Chromosome {
	private static final int HEIGHT = 30;
	private static final int WIDTH = 30;
	
	ArrayList<Integer> bits;
	int rows = 10;
	int fitness;
	
	public Chromosome(int total) {
		bits = new ArrayList<Integer>();
		Random random = new Random();
		for(int x = 0; x < total; x++) {
			bits.add(random.nextInt(2));
		}
		rows = total/10;
	}
	
	public void drawOn(Graphics2D g2) {

		for(int x = 0; x < 10; x++) {
			for(int i = 0; i < rows; i++) {
				int index = 10 * i + x;
				if (bits.get(index) == 1) {
					g2.setColor(Color.GREEN);

				}
				else {
					g2.setColor(Color.BLACK);

				}
				g2.fillRect(0 + x*HEIGHT,0 + i * WIDTH, WIDTH, HEIGHT);
				if (bits.get(index) == 1) {
					g2.setColor(Color.BLACK);

				}
				else {
					g2.setColor(Color.GREEN);

				}
				g2.drawRect(0 + x*HEIGHT,0 + i * WIDTH, WIDTH, HEIGHT);
			}
			
		}

		
	}
	
	public Chromosome mutate(int num) {
		Random rand = new Random();
		for(int i = 0; i < bits.size(); i++) {
			if(num > rand.nextInt(100)) {
				if(bits.get(i) == 1) {
					bits.set(i, 0);
				}
				else {
					bits.set(i, 1);
				}
			}
		}
		return this;
	}
	
	public Chromosome mutate(int num, JLabel name) {
		Random rand = new Random();
		for(int i = 0; i < bits.size(); i++) {
			if(num > rand.nextInt(100)) {
				if(name.getText().charAt(name.getText().length() - 1) != ')') {
					name.setText(name.getText() + " (mutated)");
				}
				if(bits.get(i) == 1) {
					bits.set(i, 0);
				}
				else {
					bits.set(i, 1);
				}
			}
		}
		return this;
	}
}
