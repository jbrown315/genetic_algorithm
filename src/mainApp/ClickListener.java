package mainApp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener {

	private ChromosomeComponent component;
	private int x1;
	private int y1;
	private boolean border = true;
	
	int rows;
	
	public ClickListener(ChromosomeComponent component) {
		this.component = component;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();
		rows = this.component.chromosome.rows;
		if(x1 > 300 || y1 > rows*30) {
			border = false;
		}
		else {
			border = true;
			x1 = x1/30;
			y1 = y1/30;
		}
		if(border) {
			if(this.component.chromosome.bits.get(y1 * 10 + x1) == 1) {
				this.component.chromosome.bits.set(y1 * 10 + x1, 0);
			}
			else {
				this.component.chromosome.bits.set(y1 * 10 + x1, 1);
			}
		}
		this.component.repaint();
		//this.component.file.in();
//		this.component.chromosome.bits.get()

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
