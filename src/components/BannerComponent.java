package components;

import java.awt.Dimension;
import javax.swing.JFrame;

public class BannerComponent extends HTMLComponent {
	private static final String BANNER_FILE_PATH = "Banner HTML";
	private static final String BANNER_FILE_NAME = "Banner.html";
	private static final int BANNER_HEIGHT = 212;
	private static final int BANNER_WIDTH = 1440;

	/**
	 * @param frame
	 */
	public BannerComponent(JFrame frame) {
		super(BANNER_FILE_NAME, BANNER_FILE_PATH, frame);
		this.getFrame().setSize(BannerComponent.BANNER_WIDTH, BannerComponent.BANNER_HEIGHT);
		this.repaint();
		this.setHtmlWidth(BannerComponent.BANNER_WIDTH);
		this.setHtmlHeight(BannerComponent.BANNER_HEIGHT);
	}

	public void open() {
		this.getFrame().add(this);
		this.repaint();
	}

	
}