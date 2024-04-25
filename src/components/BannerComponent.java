package components;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BannerComponent extends HTMLComponent {
	private static final String BANNER_FILE_PATH = "Banner";
	private static final String BANNER_FILE_NAME = "Banner";

	/**
	 * @param viewport
	 */
	public BannerComponent(JPanel viewport) {
		super(BANNER_FILE_NAME, BANNER_FILE_PATH, viewport);
		this.repaint();
	}
	
}