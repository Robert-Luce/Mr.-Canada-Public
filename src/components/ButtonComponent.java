package components;

import javax.swing.JPanel;

import listeners.MouseListeners;
/**
 * @author lucerc
 */
public class ButtonComponent extends HTMLComponent implements PressableComponent{

	private MouseListeners listener;

	public ButtonComponent(String fileName, String filePath, JPanel viewport) {
		super(fileName, filePath, viewport);
		this.listener = new MouseListeners(this);
	}



	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		
	}



	public MouseListeners getListener() {
		return listener;
	}

}
