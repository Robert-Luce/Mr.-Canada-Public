package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
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
		this.setPreferredSize(new Dimension(BannerComponent.BANNER_WIDTH, BannerComponent.BANNER_HEIGHT));
		this.frame.setSize(BannerComponent.BANNER_WIDTH, BannerComponent.BANNER_HEIGHT);
		this.repaint();
	}

	public void open() {
		this.frame.add(this, BorderLayout.NORTH);
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.frame.getHeight() < BannerComponent.BANNER_HEIGHT || this.frame.getWidth() < BannerComponent.BANNER_WIDTH) {
			String[] splitFileData = this.fileData.split(" ");
			int savedWidthIndex = 0;
			int savedHeightIndex = 0;
			for (int widthIndex = 0; widthIndex < splitFileData.length; widthIndex++) {
				if (splitFileData[widthIndex].contains("width=")){
					savedWidthIndex = widthIndex;
					break;
				}
			}
			for (int heightIndex = 0; heightIndex < splitFileData.length; heightIndex++) {
				if (splitFileData[heightIndex].contains("height=")){
					savedHeightIndex = heightIndex;
					break;
				}
			}
			if (this.frame.getHeight() < this.frame.getWidth()) {
				splitFileData[savedWidthIndex] = "width=\"" + this.frame.getHeight()*BannerComponent.BANNER_WIDTH/BannerComponent.BANNER_HEIGHT + "\"";
				splitFileData[savedHeightIndex] = "height=\"" + this.frame.getHeight() + "\"";
			} else {
				splitFileData[savedHeightIndex] = "height=\"" + this.frame.getWidth()*BannerComponent.BANNER_HEIGHT/BannerComponent.BANNER_WIDTH + "\"";
				splitFileData[savedWidthIndex] =  "width=\"" + this.frame.getWidth() + "\"";
			}
			this.fileData = String.join(" ", splitFileData);
		}
		this.label.setText(this.fileData);
		this.label.update(g);
	}
}