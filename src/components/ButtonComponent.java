package components;

import javax.swing.JPanel;

import listeners.MouseListeners;

public class ButtonComponent extends HTMLComponent implements PressableComponent{

	private MouseListeners listener;

	/**
	 * ensures: ButtonComponent is constructed
	 * @param fileName
	 * @param filePath
	 * @param viewport
	 */
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
