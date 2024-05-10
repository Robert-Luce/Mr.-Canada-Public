package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import components.ButtonComponent;
import components.ThumbnailComponent;

/**
 * @author lucerc
 */
public class MouseListeners implements MouseListener {

	private ButtonComponent component;

	public MouseListeners(ButtonComponent buttonComponent) {
		this.component = buttonComponent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.component.getBounds().contains(e.getX(), e.getY())) {
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
