package listeners;

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
		if(component.getBounds().contains(e.getX(), e.getY())) {
			this.component.pressed();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

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
