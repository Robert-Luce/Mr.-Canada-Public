package components;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BannerComponent extends HTMLComponent {
	private static final String BANNER_FILE_PATH = "Banner HTML";
	private static final String BANNER_FILE_NAME = "Main Banner.htm";
	/**
	 * @param fileName
	 * @param filePath
	 * @param frame
	 */
	public BannerComponent(JFrame frame) {
		super(BANNER_FILE_NAME, BANNER_FILE_PATH, frame);
		// TODO Auto-generated constructor stub
	}
	public void open() {
		this.frame.add(this.panel, BorderLayout.NORTH);
	}
}
