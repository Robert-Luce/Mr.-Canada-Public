package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		this.setPreferredSize(new Dimension(BannerComponent.BANNER_WIDTH, BannerComponent.BANNER_HEIGHT));
		this.getFrame().setSize(BannerComponent.BANNER_WIDTH, BannerComponent.BANNER_HEIGHT);
		this.repaint();
	}

	public void open() {
		this.getFrame().add(this);
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String[] splitFileData = this.getFileData().split(" ");
		int savedWidthIndex = 0;
		int savedHeightIndex = 0;
		for (int widthIndex = 0; widthIndex < splitFileData.length; widthIndex++) {
			if (splitFileData[widthIndex].contains("width=")) {
				savedWidthIndex = widthIndex;
				break;
			}
		}
		for (int heightIndex = 0; heightIndex < splitFileData.length; heightIndex++) {
			if (splitFileData[heightIndex].contains("height=")) {
				savedHeightIndex = heightIndex;
				break;
			}
		}
		double scaleX = Math.abs((double) this.getFrame().getContentPane().getWidth() / BannerComponent.BANNER_WIDTH);
		double scaleY = Math.abs((double) this.getFrame().getContentPane().getHeight() / BannerComponent.BANNER_HEIGHT);
		double scale = Math.min(scaleX, scaleY);
		int scaledWidth = (int) (BannerComponent.BANNER_WIDTH * scale);
		int scaledHeight = (int) (BannerComponent.BANNER_HEIGHT * scale);
		splitFileData[savedWidthIndex] = "width=\"" + scaledWidth + "\"";
		splitFileData[savedHeightIndex] = "height=\"" + scaledHeight + "\"";
		this.getLabel().setSize(scaledWidth, scaledHeight);
		this.setFileData(String.join(" ", splitFileData));
		this.getLabel().setText(this.getFileData());
		this.getLabel().update(g);
		this.getPanel().update(g);
	}
}