package components;

import javax.swing.JPanel;

import listeners.MouseListeners;

public class ButtonComponent extends HTMLComponent implements PressableComponent{

	protected MouseListeners listener;

	public ButtonComponent(String fileName, String filePath, JPanel viewport) {
		super(fileName, filePath, viewport);
		this.listener = new MouseListeners(this);
	}



	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		
	}

}
