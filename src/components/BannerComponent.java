package components;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class BannerComponent extends HTMLComponent {
	private static final long serialVersionUID = 1L;
	private static final String BANNER_FILE_PATH = "Banner HTML";
	private static final String BANNER_FILE_NAME = "Banner.html";
	private static final int BANNER_HEIGHT = 212;
	private static final int BANNER_WIDTH = 1440;

	/**
	 * @param frame
	 */
	public BannerComponent(JFrame frame) {
		super(BANNER_FILE_NAME, BANNER_FILE_PATH, frame);
	}

	public void open() {
		this.frame.add(this.label, BorderLayout.NORTH);
		this.repaint();
	}

	public void repaint() {
		if (this.frame.getHeight() < BANNER_HEIGHT) {
			this.frame.setSize(this.frame.getWidth(), BANNER_HEIGHT);
		}
		if (this.frame.getWidth() < BANNER_WIDTH) {
			this.frame.setSize(BANNER_WIDTH, this.frame.getHeight());
		}
		super.repaint();
	}
}
