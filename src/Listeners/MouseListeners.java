package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import components.HTMLComponent;

public class MouseListeners implements MouseListener {

	private HTMLComponent component;

	public MouseListeners(HTMLComponent component) {
		this.component = component;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if((this.component.getX() <= e.getX() ||this.component.getX()+this.component.getHTMLWidth() >= e.getX()) && (this.component.getY() <= e.getY() ||this.component.getY()+this.component.getHTMLHeight() >= e.getY())) {
			this.component.pressed();
		}
		
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
