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
	int rank = 0;
	
	public Chromosome(int total) {
		bits = new ArrayList<Integer>();
		Random random = new Random();
		for(int x = 0; x < total; x++) {
			bits.add(random.nextInt(2));
		}
		rows = total/10;
	}
	
	public void drawOn(Graphics2D g2) {
		
		int rowCount = 0;
		int i = 0;
		g2.translate(-WIDTH, 0);
		for(int bit : bits) {
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
			if (bit == 1) {
				g2.setColor(Color.BLACK);

			}
			else {
				g2.setColor(Color.GREEN);

			}
			g2.drawRect(0, 0, WIDTH, HEIGHT);
			i++;
		}
		int fac = 0;
		if(bits.size()%10 == 0) {
			fac = 9;
		}
		else {
			fac = bits.size()%10 - 1;
		}
		g2.translate(-WIDTH*fac,-HEIGHT*rowCount);		
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
