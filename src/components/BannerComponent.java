package components;

import javax.swing.JFrame;

public class BannerComponent extends HTMLComponent {
	private static final String BANNER_FILE_PATH = "Banner";
	private static final String BANNER_FILE_NAME = "Banner";

	/**
	 * @param frame
	 */
	public BannerComponent(JFrame frame) {
		super(BANNER_FILE_NAME, BANNER_FILE_PATH, frame);
		this.repaint();
	}

	public void open() {
		this.getFrame().add(this);
		this.repaint();
	}

	
}